package com.thebund1st.liyang.application.command;

import com.thebund1st.liyang.domain.model.JobSource;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CreateDelayJobCommand {

    private JobSource source;

    private String topic;

    private long expires;

    private long when;
}
