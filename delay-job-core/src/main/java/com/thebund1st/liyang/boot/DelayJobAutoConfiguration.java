package com.thebund1st.liyang.boot;

import com.thebund1st.liyang.boot.adapter.http.rest.RestConfiguration;
import com.thebund1st.liyang.boot.adapter.jdbc.JdbcConfiguration;
import com.thebund1st.liyang.boot.adapter.modelmapper.ModelMapperConfiguration;
import com.thebund1st.liyang.boot.application.ApplicationConfiguration;
import com.thebund1st.liyang.boot.domain.DomainConfiguration;
import com.thebund1st.liyang.boot.time.TimeConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//TODO figure out is @ComponentScan is a good practice or not?
@ComponentScan(basePackages = {
        "com.thebund1st.liyang.adapter.http",
})
@Import({
        RestConfiguration.class,
        ModelMapperConfiguration.class,
        DomainConfiguration.class,
        JdbcConfiguration.class,
        TimeConfiguration.class,
        ApplicationConfiguration.class
})
@Configuration
public class DelayJobAutoConfiguration {


}
