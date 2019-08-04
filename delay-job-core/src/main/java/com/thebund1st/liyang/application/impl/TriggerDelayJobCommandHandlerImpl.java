package com.thebund1st.liyang.application.impl;

import com.thebund1st.liyang.application.TriggerDelayJobCommandHandler;
import com.thebund1st.liyang.application.command.TriggerDelayJobCommand;
import com.thebund1st.liyang.domain.model.DelayJob;
import com.thebund1st.liyang.domain.model.DelayJobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
public class TriggerDelayJobCommandHandlerImpl implements TriggerDelayJobCommandHandler {

    private final DelayJobRepository delayJobRepository;

    @Override
    public void handle(TriggerDelayJobCommand command) {
        DelayJob delayJob = delayJobRepository.mustFindBy(command.getId());
        delayJob.triggerAt(command.getWhen());
        delayJobRepository.update(delayJob);
    }
}
