package com.xy.user.mq;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 订单服务消费者
 *
 * @author xy
 */
@Component
@Slf4j
public class MqConsumer {

    /**
     * 处理业务数据
     *
     * @param content 业务数据
     * @param message 消息
     * @param channel 管道
     * @throws IOException 异常
     */
    @RabbitListener(queues = "order_server_queue")
    public void orderServerReceiveDelay(Object content, Message message, Channel channel) throws IOException {
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //确认消息已经消费 	参数2（true=设置后续消息为自动确认消费  false=为手动确认）
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                try {
                    System.out.println("-----content------" + content);
                } catch (Exception e) {
                    log.error(e.getMessage());
                    /**
                     * basicRecover方法是进行补发操作，
                     * 其中的参数如果为true是把消息退回到queue但是有可能被其它的consumer(集群)接收到，
                     * 设置为false是只补发给当前的consumer
                     */
                    channel.basicRecover(false);
                }
            }
        };
        //是否手动确认消息  true 是  false否
        channel.basicConsume("success-queue", false, consumer);
    }
}
