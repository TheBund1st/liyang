package com.thebund1st.liyang.domain.model;

import java.util.Optional;

public interface DelayJobRepository {

    void save(DelayJob delayJob);

    Optional<DelayJob> findActiveBy(DelayJob.Identifier id);

    DelayJob mustFindBy(DelayJob.Identifier id);

    void update(DelayJob delayJob);

    Optional<DelayJob> findActiveBy(JobSource source, String topic);

    DelayJob mustFindActiveBy(JobSource source, String topic);
}
