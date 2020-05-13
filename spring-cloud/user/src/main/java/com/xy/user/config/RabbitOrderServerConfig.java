package com.xy.user.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.HashMap;
import java.util.Map;

/**
 * 订单服务rabbit配置类
 *
 * @author xy
 */
@Configuration
public class RabbitOrderServerConfig {

    /**
     * 延时队列交换机
     * 注意这里的交换机类型：CustomExchange
     *
     * @return CustomExchange
     */
    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<>(1);
        args.put("x-delayed-type", "direct");
        return new CustomExchange("delay_exchange", "x-delayed-message", true, false, args);
    }

    /**
     * 延时队列
     *
     * @return Queue
     */
    @Bean("order_server_queue")
    @Primary
    public Queue delayQueue() {
        return new Queue("order_server_queue", true);
    }

    @Bean("test_queue")
    public Queue delayTestQueue() {
        return new Queue("test_queue", true);
    }

    /**
     * 给延时队列绑定交换机
     *
     * @return Binding
     */
    @Bean
    public Binding groupBuyDelayBinding(Queue cfgDelayQueue, CustomExchange cfgUserDelayExchange) {
        return BindingBuilder.bind(cfgDelayQueue).to(cfgUserDelayExchange).with("delay_key").noargs();
    }

    /*@Bean
    public Binding skuStatus(Queue status, CustomExchange cfgUserDelayExchange) {
        return BindingBuilder.bind(status).to(cfgUserDelayExchange).with("sku_status").noargs();
    }*/

}
