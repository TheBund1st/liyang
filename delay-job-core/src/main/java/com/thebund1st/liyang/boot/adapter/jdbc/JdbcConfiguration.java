package com.thebund1st.liyang.boot.adapter.jdbc;

import com.thebund1st.liyang.adapter.jdbc.JdbcDelayJobRepository;
import com.thebund1st.liyang.adapter.jdbc.JdbcExpiringDelayJobQuery;
import com.thebund1st.liyang.adapter.jdbc.PostTransactionCommitDomainEventPublisher;
import com.thebund1st.liyang.domain.DomainEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;

@Configuration
public class JdbcConfiguration {

    @Resource(name = "liyang.DomainEventPublisherDelegate")
    private DomainEventPublisher domainEventPublisher;

    @Bean
    public JdbcDelayJobRepository jdbcDelayJobRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcDelayJobRepository(jdbcTemplate);
    }

    @Bean
    public JdbcExpiringDelayJobQuery jdbcExpiringDelayJobQuery(JdbcTemplate jdbcTemplate) {
        return new JdbcExpiringDelayJobQuery(jdbcTemplate);
    }

    @Bean(name = "liyang.DomainEventPublisher")
    public PostTransactionCommitDomainEventPublisher postTransactionCommitDomainEventPublisher() {
        return new PostTransactionCommitDomainEventPublisher(domainEventPublisher);
    }
}
