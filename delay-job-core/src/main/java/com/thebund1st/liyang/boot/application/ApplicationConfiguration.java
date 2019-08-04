package com.thebund1st.liyang.boot.application;

import com.thebund1st.liyang.application.CreateDelayJobCommandHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean(name = "liyang.CreateDelayJobCommandHandlerImpl")
    public CreateDelayJobCommandHandler createDelayJobCommandHandler() {
        return command -> null;
    }

}
