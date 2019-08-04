package com.thebund1st.liyang.adapter.http.web

import com.thebund1st.liyang.application.CreateDelayJobCommandHandler
import com.thebund1st.liyang.boot.adapter.http.rest.RestConfiguration
import com.thebund1st.liyang.boot.adapter.modelmapper.ModelMapperConfiguration
import com.thebund1st.liyang.time.Clock
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

@WebMvcTest
@Import([RestConfiguration, ModelMapperConfiguration])
class AbstractWebMvcTest extends Specification {

    @Autowired
    protected MockMvc mockMvc

    @SpringBean
    protected CreateDelayJobCommandHandler createDelayJobCommandHandler = Mock()

    @SpringBean
    protected Clock clock = Mock()

}
