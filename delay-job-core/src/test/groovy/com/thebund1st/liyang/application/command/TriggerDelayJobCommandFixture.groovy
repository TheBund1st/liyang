package com.thebund1st.liyang.application.command

import com.thebund1st.liyang.domain.model.DelayJob
import com.thebund1st.liyang.time.TestingTime

import java.time.ZonedDateTime

import static com.thebund1st.liyang.domain.model.DelayJobFixture.aDelayJob

class TriggerDelayJobCommandFixture {
    private TriggerDelayJobCommand target = new TriggerDelayJobCommand()

    def with(DelayJob value) {
        target.setId(value.getId())
        this
    }

    def withWhen(ZonedDateTime zonedDateTime) {
        target.setWhen(zonedDateTime.toEpochSecond())
        this
    }

    def build() {
        target
    }

    static def aTriggerDelayJobCommand() {
        new TriggerDelayJobCommandFixture()
                .with(aDelayJob().build())
                .withWhen(TestingTime.nowWithZone())
    }
}
