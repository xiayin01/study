package com.xy.test.mq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xy.test.mq.util.MqUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 一对一，生产者
 */
public class Send {

    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = MqUtil.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //创建队列申明
        channel.queueDeclare(MqUtil.SIMPLE_QUEUE, false, false, false, null);
        //消息内容
        String msg = "test  hello";
        channel.basicPublish("", MqUtil.SIMPLE_QUEUE, null, msg.getBytes());
        channel.close();
        connection.close();
    }
}
