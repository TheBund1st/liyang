package com.thebund1st.liyang.boot.adapter.http.rest;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestEndpointConfiguration {

    @ConfigurationProperties(prefix = "liyang.adapter.http.rest.endpoints")
    @Bean(name = "liyangRestEndpointProperties") // to be compatible with spring-el
    public RestEndpointProperties restEndpointProperties() {
        return new RestEndpointProperties();
    }

}
