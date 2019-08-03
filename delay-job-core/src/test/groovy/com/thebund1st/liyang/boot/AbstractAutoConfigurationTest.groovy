package com.thebund1st.liyang.boot


import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.test.context.runner.ApplicationContextRunner
import spock.lang.Specification

abstract class AbstractAutoConfigurationTest extends Specification {
    protected final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(DelayJobAutoConfiguration.class))
}
