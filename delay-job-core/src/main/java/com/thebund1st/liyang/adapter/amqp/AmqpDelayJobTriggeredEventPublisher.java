package com.thebund1st.liyang.adapter.amqp;

import com.thebund1st.liyang.adapter.event.SpecificDomainEventPublisher;
import com.thebund1st.liyang.domain.event.DelayJobTriggeredEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@RequiredArgsConstructor
public class AmqpDelayJobTriggeredEventPublisher implements SpecificDomainEventPublisher<DelayJobTriggeredEvent> {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publish(DelayJobTriggeredEvent event) {
        rabbitTemplate.convertAndSend("liyang.DelayJobTriggered",
                event.getSource().getContext() + "." + event.getTopic(),
                event);
    }

    @Override
    public Class<DelayJobTriggeredEvent> getEventClass() {
        return DelayJobTriggeredEvent.class;
    }

}
