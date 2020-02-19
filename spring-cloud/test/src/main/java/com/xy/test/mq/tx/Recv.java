package com.xy.test.mq.tx;

import com.rabbitmq.client.*;
import com.xy.test.mq.util.MqUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 接收端
 */
public class Recv {

    public static void main(String[] args) throws IOException {
        Connection connection = MqUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(MqUtil.SIMPLE_QUEUE, false, false, false, null);
        //监听队列
        channel.basicConsume(MqUtil.SIMPLE_QUEUE, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                String msg = new String(body, StandardCharsets.UTF_8);
                System.out.println("接收端数据tx----->:" + msg);
            }
        });
    }
}
