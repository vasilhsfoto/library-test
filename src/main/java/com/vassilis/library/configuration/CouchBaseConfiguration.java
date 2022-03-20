package com.vassilis.library.configuration;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.core.convert.CouchbaseCustomConversions;
import org.springframework.data.couchbase.core.convert.translation.JacksonTranslationService;
import org.springframework.data.couchbase.core.convert.translation.TranslationService;
import org.springframework.data.couchbase.repository.auditing.EnableCouchbaseAuditing;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vassilis.library.configuration.properties.CouchbaseConfigProperties;
import com.vassilis.library.repository.converter.LocalDateConverters;
import com.vassilis.library.repository.converter.LocalTimeConverters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableCouchbaseAuditing
@EnableCouchbaseRepositories
@RequiredArgsConstructor
@Profile(Profiles.CB_PROFILE)
public class CouchBaseConfiguration extends AbstractCouchbaseConfiguration {
    private final CouchbaseConfigProperties properties;
    private final ObjectMapper objectMapper;

    @PostConstruct
    public void postConstruct() {
        System.setProperty("org.springframework.data.couchbase.useISOStringConverterForDate", "true");
        log.info("Bucket: " + properties.getBucket());
    }

    @Override
    public String getBucketName() {
        return properties.getBucket();
    }

    @Override
    public String getPassword() {
        return properties.getPassword();
    }

    @Override
    public String getUserName() {
        return properties.getUsername();
    }

    @Override
    public String getConnectionString() {
        return properties.getHost();
    }

    @Override
    public String typeKey() {
        return "_springModel";
    }

    @Override
    public CustomConversions customConversions() {
        return new CouchbaseCustomConversions(List.of(
                LocalDateConverters.LocalDateToStringConverter.INSTANCE,
                LocalDateConverters.StringToLocalDateConverter.INSTANCE,
                LocalTimeConverters.LocalTimeToStringConverter.INSTANCE,
                LocalTimeConverters.StringToLocalTimeConverter.INSTANCE
        ));
    }

    @Override
    public TranslationService couchbaseTranslationService() {
        final JacksonTranslationService jacksonTranslationService = new JacksonTranslationService();
        jacksonTranslationService.setObjectMapper(objectMapper);
        jacksonTranslationService.afterPropertiesSet();
        return jacksonTranslationService;
    }

}