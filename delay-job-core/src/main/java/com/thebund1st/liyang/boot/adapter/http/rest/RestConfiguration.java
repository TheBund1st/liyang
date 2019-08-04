package com.thebund1st.liyang.boot.adapter.http.rest;

import com.thebund1st.liyang.boot.adapter.http.rest.assembler.ResourceAssemblerConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({
        RestEndpointConfiguration.class,
        ResourceAssemblerConfiguration.class
})
@Configuration
public class RestConfiguration {

}
