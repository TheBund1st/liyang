package com.thebund1st.liyang.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
public class DelayJob {

    private Identifier id;

    private int version = 1;

    private JobSource source;

    private String topic;

    private long expires;
    private long createdAt;
    private long lastModifiedAt;

    public DelayJob(Identifier id) {
        this.id = id;
    }

    public DelayJob() {
    }

    @Getter
    @Setter
    @EqualsAndHashCode
    public static class Identifier {
        private String value;

        public Identifier(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}
