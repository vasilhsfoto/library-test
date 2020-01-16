package com.vassilis.library.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "couchbase")
public class CouchbaseConfigProperties {

    private String hosts;
    private String username;
    private String password;
    private String bucket;

}