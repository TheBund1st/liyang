package com.thebund1st.liyang.application.impl

import com.thebund1st.liyang.adapter.jdbc.AbstractJdbcTest
import com.thebund1st.liyang.boot.adapter.jdbc.JdbcConfiguration
import com.thebund1st.liyang.boot.application.ApplicationConfiguration
import com.thebund1st.liyang.domain.DomainEventPublisher
import com.thebund1st.liyang.domain.model.DelayJobIdentifierGenerator
import org.spockframework.spring.SpringBean
import org.springframework.context.annotation.Import

@Import([ApplicationConfiguration, JdbcConfiguration])
class AbstractApplicationImplTest extends AbstractJdbcTest {

    @SpringBean
    protected DelayJobIdentifierGenerator delayJobIdentityGenerator = Mock()

    @SpringBean(name = "liyang.DomainEventPublisher")
    protected DomainEventPublisher domainEventPublisher = Mock()

}
