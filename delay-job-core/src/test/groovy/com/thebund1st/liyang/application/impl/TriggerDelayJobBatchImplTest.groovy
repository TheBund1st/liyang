package com.thebund1st.liyang.application.impl

import com.thebund1st.liyang.application.ExpiringDelayJobQuery
import com.thebund1st.liyang.application.TriggerDelayJobCommandHandler
import com.thebund1st.liyang.application.command.TriggerDelayJobCommand
import com.thebund1st.liyang.domain.model.DelayJob
import com.thebund1st.liyang.time.Clock
import com.thebund1st.liyang.time.TestingTime
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import spock.lang.Specification

import static com.thebund1st.liyang.domain.model.DelayJobFixture.aDelayJob

class TriggerDelayJobBatchImplTest extends Specification {

    private ExpiringDelayJobQuery expiringDelayJobQuery = Mock()
    private TriggerDelayJobCommandHandler triggerDelayJobCommandHandler = Mock()
    private Clock clock = Mock()
    private TriggerDelayJobBatchImpl subject

    def setup() {
        subject = new TriggerDelayJobBatchImpl(
                expiringDelayJobQuery,
                triggerDelayJobCommandHandler,
                clock)
        subject.setBatchSize(2)
    }

    def "it should find expired delay jobs"() {
        given:
        def now = TestingTime.nowWithZone()
        clock.now() >> now

        and:
        def dj_1 = aDelayJob().build()
        def dj_2 = aDelayJob().build()
        def dj_3 = aDelayJob().build()

        def jobsOnPage1 = [dj_1.id, dj_2.id]

        def jobsOnPage2 = [dj_3.id]

        expiringDelayJobQuery.findBy(now.toEpochSecond(), PageRequest.of(0, 2)) >>
                new PageImpl<DelayJob.Identifier>(jobsOnPage1, PageRequest.of(0, 2), 3)

        expiringDelayJobQuery.findBy(now.toEpochSecond(), PageRequest.of(1, 2)) >>
                new PageImpl<DelayJob.Identifier>(jobsOnPage2, PageRequest.of(1, 2), 3)


        when:
        subject.start()

        then:

        with(triggerDelayJobCommandHandler) {
            1 * handle({
                it.id == dj_1.id
                it.when == now.toEpochSecond()
            } as TriggerDelayJobCommand)
            1 * handle({
                it.id == dj_2.id
                it.when == now.toEpochSecond()
            } as TriggerDelayJobCommand)
            1 * handle({
                it.id == dj_3.id
                it.when == now.toEpochSecond()
            } as TriggerDelayJobCommand)
        }

    }
}
