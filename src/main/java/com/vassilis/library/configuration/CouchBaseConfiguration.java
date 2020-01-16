package com.vassilis.library.configuration;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.cluster.ClusterInfo;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vassilis.library.configuration.properties.CouchbaseConfigProperties;
import com.vassilis.library.repository.converter.LocalDateConverters.LocalDateToStringConverter;
import com.vassilis.library.repository.converter.LocalDateConverters.StringToLocalDateConverter;
import com.vassilis.library.repository.converter.LocalTimeConverters.LocalTimeToStringConverter;
import com.vassilis.library.repository.converter.LocalTimeConverters.StringToLocalTimeConverter;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.config.BeanNames;
import org.springframework.data.couchbase.core.convert.CouchbaseCustomConversions;
import org.springframework.data.couchbase.core.convert.translation.JacksonTranslationService;
import org.springframework.data.couchbase.core.convert.translation.TranslationService;
import org.springframework.data.couchbase.repository.auditing.EnableCouchbaseAuditing;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Slf4j
@EqualsAndHashCode(callSuper = false)
@Configuration
@EnableCouchbaseAuditing
@RequiredArgsConstructor
public class CouchBaseConfiguration extends AbstractCouchbaseConfiguration {

    private final CouchbaseConfigProperties properties;

    private final ObjectMapper objectMapper;

    @PostConstruct
    public void postConstruct() {
        System.setProperty("org.springframework.data.couchbase.useISOStringConverterForDate", "true");
        log.info("Bucket: " + properties.getBucket());
    }

    @Override
    protected List<String> getBootstrapHosts() {
        return Arrays.asList(properties.getHosts().split(","));
    }

    @Override
    protected String getBucketName() {
        return properties.getBucket();
    }

    @Override
    protected String getBucketPassword() {
        return null;
    }

    @Override
    protected String getUsername() {
        return properties.getUsername();
    }

    @Override
    @Bean(destroyMethod = "shutdown", name = BeanNames.COUCHBASE_ENV)
    public CouchbaseEnvironment couchbaseEnvironment() {
        System.setProperty("com.couchbase.allowHostnamesAsSeedNodes", "true");
        System.setProperty("com.couchbase.forceDnsLookupOnReconnect", "true");
        return super.couchbaseEnvironment();
    }

    @Override
    @Bean(name = BeanNames.COUCHBASE_CLUSTER_INFO)
    public ClusterInfo couchbaseClusterInfo() throws Exception {
        return this.couchbaseCluster().authenticate(properties.getUsername(),
                properties.getPassword()).clusterManager().info();
    }

    @Override
    public Bucket couchbaseClient() throws Exception {
        return couchbaseCluster().openBucket(getBucketName());
    }

    @Override
    public String typeKey() {
        return "_springModel";
    }

    @Override
    public CustomConversions customConversions() {
        return new CouchbaseCustomConversions(Arrays.asList(
                LocalDateToStringConverter.INSTANCE,
                StringToLocalDateConverter.INSTANCE,
                LocalTimeToStringConverter.INSTANCE,
                StringToLocalTimeConverter.INSTANCE
        ));
    }

    @Override
    public TranslationService translationService() {
        final JacksonTranslationService jacksonTranslationService = new JacksonTranslationService();
        jacksonTranslationService.setObjectMapper(objectMapper);
        jacksonTranslationService.afterPropertiesSet();
        return jacksonTranslationService;
    }
}