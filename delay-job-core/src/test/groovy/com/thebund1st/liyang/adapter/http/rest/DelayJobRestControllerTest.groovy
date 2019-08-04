package com.thebund1st.liyang.adapter.http.rest

import com.thebund1st.liyang.adapter.http.web.AbstractWebMvcTest

import java.time.ZonedDateTime

import static com.thebund1st.liyang.application.command.CreateDelayJobCommandFixture.aCreateDelayJobCommand
import static com.thebund1st.liyang.domain.model.DelayJobFixture.aDelayJob
import static org.hamcrest.Matchers.equalTo
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class DelayJobRestControllerTest extends AbstractWebMvcTest {

    def "it should accept create delay job command"() {
        given:
        def now = ZonedDateTime.now()
        clock.now() >> now

        and:
        def delayJob = aDelayJob().build()
        def command = aCreateDelayJobCommand().with(delayJob).withWhen(now).build()

        and:
        createDelayJobCommandHandler.handle(command) >> delayJob

        when:
        def resultActions = mockMvc.perform(
                post("/api/delay/jobs")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content("""
                            {
                                "source": {
                                    "context": "${command.getSource().getContext()}",
                                    "objectId": "${command.getSource().getObjectId()}"
                                },
                                "topic": "${command.getTopic()}",
                                "expires": ${command.getExpires()}
                            }
                        """)
        )

        then:
        resultActions
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("identifier", equalTo(delayJob.getId().value)))
                .andExpect(jsonPath("source.context", equalTo(delayJob.getSource().getContext())))
                .andExpect(jsonPath("source.objectId", equalTo(delayJob.getSource().getObjectId())))
                .andExpect(jsonPath("topic", equalTo(delayJob.getTopic())))
                .andExpect(jsonPath("expires", equalTo(delayJob.getExpires().intValue())))

    }
}
