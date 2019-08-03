package com.thebund1st.liyang.adapter.http.rest

import com.thebund1st.liyang.adapter.http.web.AbstractWebMvcTest

import static com.thebund1st.liyang.application.command.CreateDelayJobCommandFixture.aCreateDelayJobCommand
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class DelayJobRestControllerTest extends AbstractWebMvcTest {

    def "it should accept create delay job command"() {
        given:
        def command = aCreateDelayJobCommand().build()

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

    }
}
