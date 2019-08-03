package com.thebund1st.liyang.boot;

import com.thebund1st.liyang.boot.adapter.http.rest.RestEndpointConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//TODO figure out is @ComponentScan is a good practice or not?
@ComponentScan(basePackages = {
        "com.thebund1st.liyang.adapter.http",
})
@Import({
        RestEndpointConfiguration.class
})
@Configuration
public class DelayJobAutoConfiguration {


}
