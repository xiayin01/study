package com.xy.user.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 订单服务消息生产者
 *
 * @author xy
 */
@Component
@Slf4j
public class MqProduct {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 生成消息
     *
     * @param content 内容
     */
    public void orderServerSendDelayMessage(Object content) {
        long delayTime = 0;
        this.rabbitTemplate.convertAndSend(
                "delay_exchange",
                "delay_key",
                content,
                message -> {
                    //注意这里时间可以使long，而且是设置header
                    message.getMessageProperties().setHeader("x-delay", delayTime);
                    return message;
                }
        );
        log.info("发送内容:{},延迟消费时间:{}", content, delayTime);
    }
}
