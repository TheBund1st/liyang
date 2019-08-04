package com.thebund1st.liyang.application.command;

import com.thebund1st.liyang.domain.model.DelayJob;
import com.thebund1st.liyang.time.NowSetter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TriggerDelayJobCommand implements NowSetter {

    private DelayJob.Identifier id;

    private long when;

    @Override
    public void setNow(long epochSeconds) {
        this.when = epochSeconds;
    }
}
