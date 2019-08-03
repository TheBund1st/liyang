package com.thebund1st.liyang.application.command

import com.thebund1st.liyang.domain.model.JobSource
import com.thebund1st.liyang.time.TestingTime

import static com.thebund1st.liyang.domain.model.JobSourceFixture.aJobSource

class CreateDelayJobCommandFixture {
    private CreateDelayJobCommand target = new CreateDelayJobCommand()

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

    static def aCreateDelayJobCommand() {
        new CreateDelayJobCommandFixture()
                .with(aJobSource().build())
                .withTopic("TEST_TOPIC")
                .expireAfterSeconds(60)
    }
}
