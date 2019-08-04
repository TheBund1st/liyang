package com.thebund1st.liyang.application;

import com.thebund1st.liyang.domain.model.DelayJob;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExpiringDelayJobQuery {
    Page<DelayJob.Identifier> findBy(Long expireBeforeThis, Pageable pageable);
}
