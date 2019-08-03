package com.thebund1st.liyang.adapter.http.web

import com.thebund1st.liyang.boot.adapter.http.rest.RestEndpointConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

@WebMvcTest
@Import(RestEndpointConfiguration)
class AbstractWebMvcTest extends Specification {

    @Autowired
    protected MockMvc mockMvc

}
