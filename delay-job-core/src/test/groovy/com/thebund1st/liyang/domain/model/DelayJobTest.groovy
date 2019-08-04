package com.thebund1st.liyang.domain.model

import com.thebund1st.liyang.time.TestingTime
import spock.lang.Specification

import static com.thebund1st.liyang.domain.model.DelayJob.Status.CLOSED
import static com.thebund1st.liyang.domain.model.DelayJobFixture.aDelayJob

class DelayJobTest extends Specification {

    def "it should mark job closed"() {
        given:
        def now = TestingTime.nowWithZone()

        def delayJob = aDelayJob().withExpires(now.minusSeconds(1).toEpochSecond()).build()

        when:
        delayJob.triggerAt(now.toEpochSecond())

        then:
        assert delayJob.status == CLOSED
    }

    def "it should not mark job closed given not yet expired"() {
        given:
        def now = TestingTime.nowWithZone()

        def expires = now.plusSeconds(1).toEpochSecond()
        def delayJob = aDelayJob().withId("1").withExpires(expires).build()

        when:
        delayJob.triggerAt(now.toEpochSecond())

        then:
        def thrown = thrown(Exception)
        assert thrown.message == "Cannot trigger delay job 1, got status PENDING and expires ${expires}"
    }

    def "it should not mark job closed given closed"() {
        given:
        def now = TestingTime.nowWithZone()

        def expires = now.minusSeconds(1).toEpochSecond()
        def delayJob = aDelayJob()
                .withId("1")
                .withExpires(expires)
                .closed()
                .build()

        when:
        delayJob.triggerAt(now.toEpochSecond())

        then:
        def thrown = thrown(Exception)
        assert thrown.message == "Cannot trigger delay job 1, got status CLOSED and expires ${expires}"
    }
}
