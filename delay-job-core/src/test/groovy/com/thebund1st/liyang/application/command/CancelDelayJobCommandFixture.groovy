package com.thebund1st.liyang.application.command

import com.thebund1st.liyang.domain.model.DelayJob
import com.thebund1st.liyang.domain.model.JobSource
import com.thebund1st.liyang.time.TestingTime

import java.time.ZonedDateTime

import static com.thebund1st.liyang.domain.model.JobSourceFixture.aJobSource

class CancelDelayJobCommandFixture {
    private CancelDelayJobCommand target = new CancelDelayJobCommand()

    def with(JobSource source) {
        target.setSource(source)
        this
    }

    def withTopic(String value) {
        target.setTopic(value)
        this
    }

    def with(DelayJob delayJob) {
        target.setSource(delayJob.getSource())
        target.setTopic(delayJob.getTopic())
        target.setWhen(TestingTime.nowWithZone().toEpochSecond())
        this
    }

    def withWhen(ZonedDateTime zonedDateTime) {
        target.setWhen(zonedDateTime.toEpochSecond())
        this
    }

    def build() {
        target
    }

    static def aCancelDelayJobCommand() {
        new CancelDelayJobCommandFixture()
                .with(aJobSource().build())
                .withTopic("TEST_TOPIC")
    }
}
