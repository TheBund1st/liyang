package com.thebund1st.liyang.domain.model


import com.thebund1st.liyang.time.TestingTime

import static com.thebund1st.liyang.domain.model.JobSourceFixture.aJobSource

class DelayJobFixture {
    private DelayJob target = new DelayJob()


    def withId(DelayJob.Identifier value) {
        target.setId(value)
        this
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
        target.setExpires(TestingTime.nowWithZone().plusSeconds(value).toEpochSecond())
        this
    }

    def build() {
        target
    }

    static def aDelayJob() {
        new DelayJobFixture()
                .withId(nextId())
                .with(aJobSource().build())
                .withTopic("TEST_TOPIC")
                .expireAfterSeconds(60)
    }

    static DelayJob.Identifier nextId() {
        new DelayJob.Identifier(TestingIdentifier.next())
    }
}
