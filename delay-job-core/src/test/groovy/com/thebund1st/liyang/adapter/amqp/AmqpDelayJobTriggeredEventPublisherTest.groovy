package com.thebund1st.liyang.adapter.amqp

import com.jayway.jsonpath.JsonPath
import foo.bar.amqp.AmqpTestConfiguration
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

import static com.thebund1st.liyang.domain.event.DelayJobTriggeredEventFixture.aDelayJobTriggeredEvent
import static com.thebund1st.liyang.domain.model.DelayJobFixture.aDelayJob
import static com.thebund1st.liyang.domain.model.JobSourceFixture.aJobSource

@Import(AmqpTestConfiguration)
@SpringBootTest
@ActiveProfiles(profiles = "commit")
class AmqpDelayJobTriggeredEventPublisherTest extends Specification {

    @Autowired
    private AmqpDelayJobTriggeredEventPublisher subject

    @Autowired
    private RabbitTemplate rabbitTemplate

    def "it should send DelayJobTriggeredEvent to specified queue"() {
        given:
        def queueName = "liyang.time.to.foo.TEST_TOPIC"

        and:
        drain(queueName)

        and:
        def event = aDelayJobTriggeredEvent()
                .with(
                aDelayJob().with(aJobSource().withContext("FOO").build())
                        .withTopic("TEST_TOPIC").build()
        ).build()

        when:
        subject.publish(event)

        then:
        def received = rabbitTemplate.receive(queueName, 1000)
        def body = new String(received.body)
        assert JsonPath.read(body, "version") == event.version
        assert JsonPath.read(body, "source.context") == event.source.context
        assert JsonPath.read(body, "source.objectId") == event.source.objectId
        assert JsonPath.read(body, "topic") == event.topic
        assert JsonPath.read(body, "expires") == event.expires
    }

    def drain(String queueName) {
        def message = rabbitTemplate.receive(queueName, 1000)
        if (message != null) {
            drain(queueName)
        }
    }
}
