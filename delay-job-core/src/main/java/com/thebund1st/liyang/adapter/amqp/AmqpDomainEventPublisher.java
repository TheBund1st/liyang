package com.thebund1st.liyang.adapter.amqp;

import com.thebund1st.liyang.domain.DomainEventPublisher;
import com.thebund1st.liyang.domain.event.DelayJobTriggeredEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@RequiredArgsConstructor
public class AmqpDomainEventPublisher implements DomainEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publish(Object event) {
        DelayJobTriggeredEvent delayJobTriggered = (DelayJobTriggeredEvent) event;
        rabbitTemplate.convertAndSend("liyang.DelayJobTriggered",
                delayJobTriggered.getSource().getContext() + "." + delayJobTriggered.getTopic(),
                event);
    }
}
