package com.thebund1st.liyang.boot.domain;

import com.thebund1st.liyang.domain.model.DelayJob;
import com.thebund1st.liyang.domain.model.DelayJobIdentifierGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class DomainConfiguration {

    @ConditionalOnMissingBean(DelayJobIdentifierGenerator.class)
    @Bean
    public DelayJobIdentifierGenerator delayJobIdentifierGenerator() {
        return () -> new DelayJob.Identifier(UUID.randomUUID().toString().replace("-", ""));
    }

}
