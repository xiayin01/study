package com.xy.test.mq.workqueue.roundrobin;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xy.test.mq.util.MqUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 一对多，轮询方式发送端
 */
public class Send {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //获取连接
        Connection connection = MqUtil.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //创建队列申明
        channel.queueDeclare(MqUtil.WORK_QUEUE, false, false, false, null);
        //消息内容
        for (int i = 0; i < 50; i++) {
            String msg = "hello " + i;
            channel.basicPublish("", MqUtil.WORK_QUEUE, null, msg.getBytes());
            Thread.sleep(i * 20);
            System.out.println("发送者----》:" + i);
        }
        channel.close();
        connection.close();
    }
}
