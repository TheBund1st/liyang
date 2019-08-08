package com.thebund1st.liyang.adapter.event;

public interface SpecificDomainEventPublisher<T> {

    void publish(T event);

    Class<T> getEventClass();
}
