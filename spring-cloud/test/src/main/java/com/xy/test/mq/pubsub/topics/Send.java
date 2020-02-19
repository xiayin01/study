package com.xy.test.mq.pubsub.topics;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xy.test.mq.util.MqUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * topic
 */
public class Send {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = MqUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare(MqUtil.EXCHANGE_TOPIC_NAME, "topic");
        //发送消息
        String msg = "发布-订阅模式----》topic";
        String routingKey = MqUtil.ROUTING_KEY_ERROR;
        channel.basicPublish(MqUtil.EXCHANGE_TOPIC_NAME, "goods.add", null, msg.getBytes());
        channel.close();
        connection.close();
    }
}
