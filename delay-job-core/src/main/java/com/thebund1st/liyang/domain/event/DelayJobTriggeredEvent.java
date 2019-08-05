package com.thebund1st.liyang.domain.event;

import com.thebund1st.liyang.domain.model.DelayJob;
import com.thebund1st.liyang.domain.model.JobSource;
import lombok.Data;

@Data
public class DelayJobTriggeredEvent {
    private DelayJob.Identifier id;

    private int version;

    private JobSource source;

    private String topic;

    private long expires;
}
