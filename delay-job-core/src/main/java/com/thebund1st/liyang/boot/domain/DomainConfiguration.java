package com.thebund1st.liyang.boot.domain;

import com.thebund1st.liyang.domain.DomainEventPublisher;
import com.thebund1st.liyang.domain.model.DelayJob;
import com.thebund1st.liyang.domain.model.DelayJobIdentifierGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Slf4j
@Configuration
public class DomainConfiguration {

    @ConditionalOnMissingBean(DelayJobIdentifierGenerator.class)
    @Bean
    public DelayJobIdentifierGenerator delayJobIdentifierGenerator() {
        return () -> new DelayJob.Identifier(UUID.randomUUID().toString().replace("-", ""));
    }

    @ConditionalOnMissingBean(name = "liyang.DomainEventPublisherDelegate")
    @Bean(name = "liyang.DomainEventPublisherDelegate")
    public DomainEventPublisher domainEventPublisher() {
        return event -> log.info(event.toString());
    }
}
