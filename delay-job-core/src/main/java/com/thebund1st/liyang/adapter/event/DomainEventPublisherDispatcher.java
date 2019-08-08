package com.thebund1st.liyang.adapter.event;

import com.thebund1st.liyang.domain.DomainEventPublisher;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class DomainEventPublisherDispatcher implements DomainEventPublisher {

    private final Map<Class<?>, SpecificDomainEventPublisher<?>> publisherGroup;

    @Override
    public void publish(Object event) {
        SpecificDomainEventPublisher publisher = publisherGroup.get(event.getClass());
        //noinspection unchecked
        publisher.publish(event);
    }
}
