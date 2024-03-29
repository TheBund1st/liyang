package com.thebund1st.liyang.domain.model;

import com.thebund1st.liyang.domain.event.DelayJobTriggeredEvent;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.thebund1st.liyang.domain.model.DelayJob.Status.CANCELED;
import static com.thebund1st.liyang.domain.model.DelayJob.Status.CLOSED;
import static com.thebund1st.liyang.domain.model.DelayJob.Status.PENDING;

@Getter
@Setter
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
public class DelayJob {

    private Identifier id;

    private int version = 1;

    private JobSource source;

    private String topic;

    private Status status = PENDING;

    private long expires;
    private long createdAt;
    private long lastModifiedAt;

    public DelayJob(Identifier id) {
        this.id = id;
    }

    public DelayJob() {
    }

    public DelayJobTriggeredEvent triggeredAt(long when) {
        if (status == PENDING && this.expires <= when) {
            this.status = CLOSED;
            this.lastModifiedAt = when;
            DelayJobTriggeredEvent event = new DelayJobTriggeredEvent();
            event.setId(getId());
            event.setVersion(getVersion());
            event.setSource(getSource());
            event.setTopic(getTopic());
            event.setExpires(getExpires());
            return event;
        } else {
            throw new IllegalStateException(String.format("Cannot trigger delay job %s, got status %s and expires %d",
                    getId(), getStatus(), getExpires()));
        }
    }

    public void canceledAt(long when) {
        if (status == PENDING) {
            this.status = CANCELED;
            this.lastModifiedAt = when;
        } else {
            throw new IllegalStateException(String.format("Cannot cancel delay job %s, got status %s and expires %d",
                    getId(), getStatus(), getExpires()));
        }
    }

    public boolean isPending() {
        return getStatus().equals(PENDING);
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

    public enum Status {
        UNKNOWN(-1), PENDING(0), CLOSED(2), CANCELED(3);

        @Getter
        private int value;

        Status(int value) {
            this.value = value;
        }

        public static Status of(int value) {
            for (Status status : values()) {
                if (status.getValue() == value) {
                    return status;
                }
            }
            return UNKNOWN;
        }
    }
}
