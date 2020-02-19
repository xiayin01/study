package com.xy.test.mq.workqueue.fair;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xy.test.mq.util.MqUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 一对多，手动反馈，公平模式，发送者
 */
public class Send {

    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException {
        //获取连接
        Connection connection = MqUtil.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //每个消费者发送确认消息之前，消息队列不发送下一个消息给消费者，一次只处理一个消息
        //限制发送给同一个消费者不得超过一条数据
        channel.basicQos(1);
        //创建队列申明
        boolean durable = true;//消息持久化
        channel.queueDeclare(MqUtil.WORK_FAIR_QUEUE, durable, false, false, null);
        //消息内容
        for (int i = 0; i < 50; i++) {
            String msg = "hello " + i;
            channel.basicPublish("", MqUtil.WORK_FAIR_QUEUE, null, msg.getBytes());
            Thread.sleep(i * 20);
            System.out.println("发送者----》:" + i);
        }
        channel.close();
        connection.close();
    }
}
