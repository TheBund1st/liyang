package com.thebund1st.liyang.boot.adapter.jdbc;

import com.thebund1st.liyang.adapter.jdbc.JdbcDelayJobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class JdbcConfiguration {

    @Bean
    public JdbcDelayJobRepository jdbcDelayJobRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcDelayJobRepository(jdbcTemplate);
    }

}
