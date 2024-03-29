package com.thebund1st.liyang.application.impl;

import com.thebund1st.liyang.application.TriggerDelayJobCommandHandler;
import com.thebund1st.liyang.application.command.TriggerDelayJobCommand;
import com.thebund1st.liyang.domain.DomainEventPublisher;
import com.thebund1st.liyang.domain.event.DelayJobTriggeredEvent;
import com.thebund1st.liyang.domain.model.DelayJob;
import com.thebund1st.liyang.domain.model.DelayJobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
public class TriggerDelayJobCommandHandlerImpl implements TriggerDelayJobCommandHandler {

    private final DelayJobRepository delayJobRepository;

    private final DomainEventPublisher domainEventPublisher;

    @Override
    public void handle(TriggerDelayJobCommand command) {
        DelayJob delayJob = delayJobRepository.mustFindBy(command.getId());
        DelayJobTriggeredEvent event = delayJob.triggeredAt(command.getWhen());
        delayJobRepository.update(delayJob);
        domainEventPublisher.publish(event);
    }
}
