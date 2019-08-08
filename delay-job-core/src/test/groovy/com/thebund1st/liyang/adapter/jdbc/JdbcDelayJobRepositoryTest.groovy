package com.thebund1st.liyang.adapter.jdbc


import com.thebund1st.liyang.domain.model.DelayJobRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DuplicateKeyException

import static com.thebund1st.liyang.domain.model.DelayJobFixture.aDelayJob
import static com.thebund1st.liyang.domain.model.JobSourceFixture.aJobSource

class JdbcDelayJobRepositoryTest extends AbstractJdbcTest {

    @Autowired
    private DelayJobRepository delayJobRepository

    def "it should reject duplicate delay job"() {

        given:
        def jobSource = aJobSource().build()
        def delayJob = aDelayJob().with(jobSource).withTopic("UK_ACTIVE").build()

        and:
        delayJobRepository.save(delayJob)

        and:
        def anotherDelayJob = aDelayJob().with(jobSource).withTopic("UK_ACTIVE").build()

        when:
        delayJobRepository.save(anotherDelayJob)

        then:
        def thrown = thrown(Exception)
        assert thrown instanceof DuplicateKeyException
    }

    def "it should accept duplicate delay job given only one of them is pending"() {

        given:
        def jobSource = aJobSource().build()
        def topic = "UK_ACTIVE"

        and:
        def delayJob_1 = aDelayJob().with(jobSource).withTopic(topic).build()
        def delayJob_2 = aDelayJob().with(jobSource).withTopic(topic).closed().build()
        def delayJob_3 = aDelayJob().with(jobSource).withTopic(topic).canceled().build()

        and:
        [delayJob_1, delayJob_2, delayJob_3].forEach { delayJobRepository.save(it) }

        when:
        def found = delayJobRepository.mustFindActiveBy(jobSource, topic)

        then:
        assert found.id == delayJob_1.id
    }
}
