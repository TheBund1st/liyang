package com.thebund1st.liyang.application.impl


import com.thebund1st.liyang.domain.model.DelayJobRepository
import com.thebund1st.liyang.time.TestingTime
import org.springframework.beans.factory.annotation.Autowired

import static com.thebund1st.liyang.application.command.TriggerDelayJobCommandFixture.aTriggerDelayJobCommand
import static com.thebund1st.liyang.domain.model.DelayJob.Status.CLOSED
import static com.thebund1st.liyang.domain.model.DelayJobFixture.aDelayJob

class TriggerDelayJobCommandHandlerImplTest extends AbstractApplicationImplTest {

    @Autowired
    private TriggerDelayJobCommandHandlerImpl subject

    @Autowired
    private DelayJobRepository delayJobRepository

    def "it should trigger a delay job"() {
        given:
        def now = TestingTime.nowWithZone()
        def delayJob = aDelayJob().withExpires(now.minusMinutes(3).toEpochSecond()).build()

        and:
        delayJobRepository.save(delayJob)

        and:
        def command = aTriggerDelayJobCommand().with(delayJob).withWhen(now).build()

        when:
        subject.handle(command)

        then:

        def found = delayJobRepository.mustFindBy(delayJob.id)
        assert found.version == 2
        assert found.status == CLOSED
        assert found.lastModifiedAt == now.toEpochSecond()
    }
}
