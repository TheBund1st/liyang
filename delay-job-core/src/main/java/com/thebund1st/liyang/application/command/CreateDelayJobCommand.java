package com.thebund1st.liyang.application.command;

import com.thebund1st.liyang.domain.model.JobSource;
import com.thebund1st.liyang.time.NowSetter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CreateDelayJobCommand implements NowSetter {

    private JobSource source;

    private String topic;

    private long expires;

    private long when;

    @Override
    public void setNow(long epochSeconds) {
        this.when = epochSeconds;
    }
}
