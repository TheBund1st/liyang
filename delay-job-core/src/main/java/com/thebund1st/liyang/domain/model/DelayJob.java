package com.thebund1st.liyang.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DelayJob {

    private String id;

    private JobSource source;

    private String topic;

    private long expires;
}
