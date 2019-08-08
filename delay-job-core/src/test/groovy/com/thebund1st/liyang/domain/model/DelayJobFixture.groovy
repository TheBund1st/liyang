package com.thebund1st.liyang.domain.model


import com.thebund1st.liyang.time.TestingTime

import static com.thebund1st.liyang.domain.model.JobSourceFixture.aJobSource

class DelayJobFixture {
    private DelayJob target = new DelayJob()


    def with(DelayJob.Identifier value) {
        target.setId(value)
        this
    }

    def withId(String value) {
        with(new DelayJob.Identifier(value))
    }

    def with(JobSource source) {
        target.setSource(source)
        this
    }

    def withTopic(String value) {
        target.setTopic(value)
        this
    }

    def expireAfterSeconds(int value) {
        withExpires(TestingTime.nowWithZone().plusSeconds(value).toEpochSecond())
    }

    def withExpires(long value) {
        target.setExpires(value)
        this
    }

    def closed() {
        target.setStatus(DelayJob.Status.CLOSED)
        this
    }

    def canceled() {
        target.setStatus(DelayJob.Status.CANCELED)
        this
    }

    def build() {
        target
    }

    static def aDelayJob() {
        new DelayJobFixture()
                .with(nextId())
                .with(aJobSource().build())
                .withTopic("TEST_TOPIC")
                .expireAfterSeconds(60)
    }

    static DelayJob.Identifier nextId() {
        new DelayJob.Identifier(TestingIdentifier.next())
    }
}
