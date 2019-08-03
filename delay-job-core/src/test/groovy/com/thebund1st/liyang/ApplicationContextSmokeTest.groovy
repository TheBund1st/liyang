package com.thebund1st.liyang

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class ApplicationContextSmokeTest extends Specification {

    def "it should load application context"() {
        when: "it launch the application context"

        then: "it should load successfully"
    }
}
