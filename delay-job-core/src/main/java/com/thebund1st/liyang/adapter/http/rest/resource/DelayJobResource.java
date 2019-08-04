package com.thebund1st.liyang.adapter.http.rest.resource;

import lombok.Data;

@Data
public class DelayJobResource {
    private String identifier;

    private JobSource source;

    private String topic;

    private long expires;

    @Data
    public static class JobSource {
        private String context;
        private String objectId;
    }
}
