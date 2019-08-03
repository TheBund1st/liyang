package com.thebund1st.liyang.boot.adapter.http.rest

import com.thebund1st.liyang.boot.AbstractAutoConfigurationTest
import spock.lang.Ignore


class RestEndpointConfigurationTest extends AbstractAutoConfigurationTest {

    //TODO Figure out why it fails
    @Ignore("")
    def "it should provide a adapter.http.rest.endpoints properties"() {

        when:
        def subject = this.contextRunner
                .withPropertyValues("liyang.adapter.http.rest.endpoints.createDelayJobPath=/public/delay/jobs")

        then:
        subject.run { it ->

            RestEndpointProperties properties = it.getBean("liyangRestEndpointProperties",
                    RestEndpointProperties)
            assert properties.createDelayJobPath == '/public/delay/jobs'

        }
    }
}
