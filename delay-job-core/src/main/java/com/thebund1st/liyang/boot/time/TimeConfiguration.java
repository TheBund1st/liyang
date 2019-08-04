package com.thebund1st.liyang.boot.time;

import com.thebund1st.liyang.time.Clock;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TimeConfiguration {

    @ConditionalOnMissingBean(Clock.class)
    @Bean(name = "liyang.time.Clock")
    public Clock clock() {
        return new Clock();
    }

}
