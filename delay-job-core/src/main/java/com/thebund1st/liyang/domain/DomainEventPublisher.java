package com.thebund1st.liyang.domain;

public interface DomainEventPublisher {

    void publish(Object event);
}
