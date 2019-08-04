package com.thebund1st.liyang.boot.application;

import com.thebund1st.liyang.application.CreateDelayJobCommandHandler;
import com.thebund1st.liyang.application.impl.CreateDelayJobCommandHandlerImpl;
import com.thebund1st.liyang.domain.model.DelayJobIdentifierGenerator;
import com.thebund1st.liyang.domain.model.DelayJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Autowired
    private DelayJobIdentifierGenerator delayJobIdentifierGenerator;

    @Autowired
    private DelayJobRepository delayJobRepository;

    @Bean(name = "liyang.CreateDelayJobCommandHandlerImpl")
    public CreateDelayJobCommandHandler createDelayJobCommandHandler() {
        return new CreateDelayJobCommandHandlerImpl(delayJobIdentifierGenerator, delayJobRepository);
    }

}
