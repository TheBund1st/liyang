package com.thebund1st.liyang.application.impl

import com.thebund1st.liyang.domain.model.DelayJobRepository
import com.thebund1st.liyang.time.TestingTime
import org.springframework.beans.factory.annotation.Autowired

import static com.thebund1st.liyang.application.command.CancelDelayJobCommandFixture.aCancelDelayJobCommand
import static com.thebund1st.liyang.domain.model.DelayJob.Status.CANCELED
import static com.thebund1st.liyang.domain.model.DelayJobFixture.aDelayJob

class CancelDelayJobCommandHandlerImplTest extends AbstractApplicationImplTest {

    @Autowired
    private CancelDelayJobCommandHandlerImpl subject

    @Autowired
    private DelayJobRepository delayJobRepository

    def "it should trigger a delay job"() {
        given:
        def now = TestingTime.nowWithZone()
        def delayJob = aDelayJob().build()

        and:
        delayJobRepository.save(delayJob)

        and:
        def command = aCancelDelayJobCommand().with(delayJob).withWhen(now).build()

        when:
        subject.handle(command)

        then:

        def found = delayJobRepository.mustFindBy(delayJob.id)
        assert found.version == 2
        assert found.status == CANCELED
        assert found.lastModifiedAt == now.toEpochSecond()

    }
}
