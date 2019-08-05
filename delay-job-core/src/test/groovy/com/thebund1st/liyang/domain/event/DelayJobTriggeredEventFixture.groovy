package com.thebund1st.liyang.domain.event

import com.thebund1st.liyang.domain.model.DelayJob

import static com.thebund1st.liyang.domain.model.DelayJobFixture.aDelayJob

class DelayJobTriggeredEventFixture {
    private DelayJobTriggeredEvent target = new DelayJobTriggeredEvent()

    def with(DelayJob delayJob) {
        target.setSource(delayJob.getSource())
        target.setTopic(delayJob.getTopic())
        target.setExpires(delayJob.getExpires())
        this
    }

    def build() {
        target
    }

    static def aDelayJobTriggeredEvent() {
        new DelayJobTriggeredEventFixture()
                .with(aDelayJob().build())
    }
}
