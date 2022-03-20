package com.vassilis.library.configuration.properties;

import com.vassilis.library.configuration.Profiles;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Data
@Configuration
@ConfigurationProperties(prefix = "couchbase")
@Profile(Profiles.CB_PROFILE)
public class CouchbaseConfigProperties {

    private String host;
    private String username;
    private String password;
    private String bucket;
}