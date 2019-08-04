package com.thebund1st.liyang.domain.model;

import java.util.Optional;

public interface DelayJobRepository {

    void save(DelayJob delayJob);

    Optional<DelayJob> findBy(DelayJob.Identifier id);

    DelayJob mustFindBy(DelayJob.Identifier id);
}
