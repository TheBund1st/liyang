package foo.bar.amqp


import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AmqpTestConfiguration {
    @Bean
    Queue queue() {
        return new Queue("liyang.time.to.foo.TEST_TOPIC", true)
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("FOO.TEST_TOPIC")
    }


}
