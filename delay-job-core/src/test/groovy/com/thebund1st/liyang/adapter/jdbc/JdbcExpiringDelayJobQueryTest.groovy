package com.thebund1st.liyang.adapter.jdbc


import com.thebund1st.liyang.domain.model.DelayJobRepository
import com.thebund1st.liyang.time.TestingTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest

import static com.github.hippoom.tdb.GenericTestDataListBuilder.listOfSize
import static com.thebund1st.liyang.domain.model.DelayJobFixture.aDelayJob

class JdbcExpiringDelayJobQueryTest extends AbstractJdbcTest {

    @Autowired
    private JdbcExpiringDelayJobQuery subject

    @Autowired
    private DelayJobRepository delayJobRepository

    def "it should create a delay job"() {
        given:
        def now = TestingTime.nowWithZone()
        def three_mins_ago = TestingTime.nowWithZone().minusMinutes(3).toEpochSecond()

        and:
        def before = subject.findBy(now.toEpochSecond(), PageRequest.of(0, 2))

        and:
        def delayJobs = listOfSize(5, { aDelayJob() })
                .theFirst(3, { it.withExpires(three_mins_ago) })
                .number(4, { it.withExpires(now.plusSeconds(1).toEpochSecond()) })
                .number(5, { it.withExpires(three_mins_ago).closed() })
                .build { it.build() }
        delayJobs.forEach { delayJobRepository.save(it) }

        when:
        def after = subject.findBy(now.toEpochSecond(), PageRequest.of(0, 2))

        then:
        assert after.totalElements == before.totalElements + 3
    }
}
