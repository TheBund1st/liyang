package com.thebund1st.liyang.boot.adapter.http.rest;

import com.thebund1st.liyang.adapter.http.mvc.method.NowSetterRequestBodyAdvice;
import com.thebund1st.liyang.boot.adapter.http.rest.assembler.ResourceAssemblerConfiguration;
import com.thebund1st.liyang.time.Clock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.Collections;

@Import({
        RestEndpointConfiguration.class,
        ResourceAssemblerConfiguration.class
})
@Configuration
public class RestConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private Clock clock;

    @Override
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
        RequestMappingHandlerAdapter requestMappingHandlerAdapter = super.requestMappingHandlerAdapter();
        requestMappingHandlerAdapter.setRequestBodyAdvice(
                Collections.singletonList(new NowSetterRequestBodyAdvice(clock))
        );
        return requestMappingHandlerAdapter;
    }

}
