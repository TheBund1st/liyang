package com.thebund1st.liyang.application.impl;

import com.thebund1st.liyang.application.ExpiringDelayJobQuery;
import com.thebund1st.liyang.application.TriggerDelayJobCommandHandler;
import com.thebund1st.liyang.application.command.TriggerDelayJobCommand;
import com.thebund1st.liyang.domain.model.DelayJob;
import com.thebund1st.liyang.time.Clock;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Slf4j
@RequiredArgsConstructor
public class TriggerDelayJobBatchImpl implements com.thebund1st.liyang.application.TriggerDelayJobBatch {

    private final ExpiringDelayJobQuery expiringDelayJobQuery;

    private final TriggerDelayJobCommandHandler triggerDelayJobCommandHandler;

    private final Clock clock;

    @Setter
    private int batchSize = 100;

    @Override
    public void start() {
        doTriggerDelayJobBy(clock.now().toEpochSecond(), PageRequest.of(0, batchSize));
    }

    private void doTriggerDelayJobBy(Long now, Pageable pageable) {
        Page<DelayJob.Identifier> page = expiringDelayJobQuery
                .findBy(now, pageable);
        page.forEach(identifier -> {
            try {
                triggerDelayJobCommandHandler.handle(toTriggerDelayJobCommand(identifier));
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        });
        if (page.hasNext()) {
            doTriggerDelayJobBy(now, page.nextPageable());
        }
    }

    private TriggerDelayJobCommand toTriggerDelayJobCommand(DelayJob.Identifier identifier) {
        TriggerDelayJobCommand command = new TriggerDelayJobCommand();
        command.setId(identifier);
        command.setNow(clock.now().toEpochSecond());
        return command;
    }
}
