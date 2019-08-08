package com.thebund1st.liyang.boot.adapter.event;

import com.thebund1st.liyang.adapter.event.DomainEventPublisherDispatcher;
import com.thebund1st.liyang.adapter.event.SpecificDomainEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class EventConfiguration {

    @Bean(name = "liyang.DomainEventPublisherDelegate")
    protected DomainEventPublisherDispatcher domainEventPublisher(
            List<SpecificDomainEventPublisher<?>> publisherGroup) {
        return new DomainEventPublisherDispatcher(
                publisherGroup.stream()
                        .collect(Collectors.toMap(SpecificDomainEventPublisher::getEventClass, p -> p))
        );
    }
}
