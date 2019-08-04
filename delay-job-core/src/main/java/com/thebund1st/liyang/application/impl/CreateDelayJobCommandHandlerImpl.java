package com.thebund1st.liyang.application.impl;

import com.thebund1st.liyang.application.CreateDelayJobCommandHandler;
import com.thebund1st.liyang.application.command.CreateDelayJobCommand;
import com.thebund1st.liyang.domain.model.DelayJob;
import com.thebund1st.liyang.domain.model.DelayJobIdentifierGenerator;
import com.thebund1st.liyang.domain.model.DelayJobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
public class CreateDelayJobCommandHandlerImpl implements CreateDelayJobCommandHandler {

    private final DelayJobIdentifierGenerator delayJobIdentifierGenerator;

    private final DelayJobRepository delayJobRepository;

    @Override
    public DelayJob handle(CreateDelayJobCommand command) {
        DelayJob delayJob = new DelayJob(delayJobIdentifierGenerator.next());
        delayJob.setSource(command.getSource());
        delayJob.setTopic(command.getTopic());
        delayJob.setExpires(command.getExpires());
        delayJob.setCreatedAt(command.getWhen());
        delayJob.setLastModifiedAt(command.getWhen());
        delayJobRepository.save(delayJob);
        return delayJob;
    }
}
