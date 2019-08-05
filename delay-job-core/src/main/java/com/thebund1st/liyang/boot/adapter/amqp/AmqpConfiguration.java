package com.thebund1st.liyang.boot.adapter.amqp;

import com.thebund1st.liyang.adapter.amqp.AmqpDomainEventPublisher;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfiguration {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Bean(name = "liyang.DomainEventPublisherDelegate")
    protected AmqpDomainEventPublisher amqpDomainEventPublisher() {
        return new AmqpDomainEventPublisher(rabbitTemplate);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("liyang.DelayJobTriggered");
    }

    @Bean(name = "liyang.amqp.Jackson2JsonMessageConverter")
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
