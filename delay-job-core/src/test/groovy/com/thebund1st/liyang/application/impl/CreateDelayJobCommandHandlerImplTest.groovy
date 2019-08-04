package com.thebund1st.liyang.application.impl


import com.thebund1st.liyang.domain.model.DelayJobFixture
import com.thebund1st.liyang.domain.model.DelayJobRepository
import org.springframework.beans.factory.annotation.Autowired

import static com.thebund1st.liyang.application.command.CreateDelayJobCommandFixture.aCreateDelayJobCommand
import static com.thebund1st.liyang.domain.model.DelayJob.Status.PENDING

class CreateDelayJobCommandHandlerImplTest extends AbstractApplicationImplTest {

    @Autowired
    private CreateDelayJobCommandHandlerImpl subject

    @Autowired
    private DelayJobRepository delayJobRepository

    def "it should create a delay job"() {
        given:
        def command = aCreateDelayJobCommand().build()

        and:
        delayJobIdentityGenerator.next() >> DelayJobFixture.nextId()

        when:
        def delayJob = subject.handle(command)

        then:
        assert delayJob.id != null
        assert delayJob.version == 1
        assert delayJob.source == command.source
        assert delayJob.topic == command.topic
        assert delayJob.expires == command.expires
        assert delayJob.status == PENDING

        def found = delayJobRepository.mustFindBy(delayJob.id)
        assert found.version == 1
        assert found.source == command.source
        assert found.topic == command.topic
        assert found.expires == command.expires
        assert found.createdAt == command.when
        assert found.lastModifiedAt == command.when
        assert found.status == PENDING
    }
}
