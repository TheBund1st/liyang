package com.thebund1st.liyang.application.impl

import com.thebund1st.liyang.boot.adapter.jdbc.JdbcConfiguration
import com.thebund1st.liyang.boot.application.ApplicationConfiguration
import com.thebund1st.liyang.domain.model.DelayJobFixture
import com.thebund1st.liyang.domain.model.DelayJobIdentifierGenerator
import com.thebund1st.liyang.domain.model.DelayJobRepository
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

import static com.thebund1st.liyang.application.command.CreateDelayJobCommandFixture.aCreateDelayJobCommand
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE
import static org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED

@Import([ApplicationConfiguration, JdbcConfiguration])
@AutoConfigureTestDatabase(replace = NONE)
@Transactional(propagation = NOT_SUPPORTED)
@DataJdbcTest
@ActiveProfiles(profiles = "commit")
class CreateDelayJobCommandHandlerImplTest extends Specification {

    @Autowired
    private CreateDelayJobCommandHandlerImpl subject

    @Autowired
    private DelayJobRepository delayJobRepository

    @SpringBean
    private DelayJobIdentifierGenerator delayJobIdentityGenerator = Mock()

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

        def found = delayJobRepository.mustFindBy(delayJob.id)
        assert found.version == 1
        assert found.source == command.source
        assert found.topic == command.topic
        assert found.expires == command.expires
        assert found.createdAt == command.when
        assert found.lastModifiedAt == command.when
    }
}
