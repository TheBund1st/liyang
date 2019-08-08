package com.thebund1st.liyang.application.impl;

import com.thebund1st.liyang.application.CancelDelayJobCommandHandler;
import com.thebund1st.liyang.application.command.CancelDelayJobCommand;
import com.thebund1st.liyang.domain.model.DelayJob;
import com.thebund1st.liyang.domain.model.DelayJobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
public class CancelDelayJobCommandHandlerImpl implements CancelDelayJobCommandHandler {

    private final DelayJobRepository delayJobRepository;

    @Override
    public DelayJob handle(CancelDelayJobCommand command) {
        DelayJob delayJob = delayJobRepository.mustFindActiveBy(command.getSource(), command.getTopic());
        delayJob.canceledAt(command.getWhen());
        delayJobRepository.update(delayJob);
        return delayJob;
    }
}
