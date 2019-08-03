package com.thebund1st.liyang.domain.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static lombok.AccessLevel.PRIVATE;

/**
 * The object that creates a job.
 *
 * @since 0.1.0
 */
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PRIVATE)
public class JobSource {
    private String context;
    private String objectId;

    public static JobSource of(String context, String objectId) {
        return new JobSource(context, objectId);
    }
}
