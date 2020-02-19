package com.xy.test.mq.workqueue.fair;

import com.rabbitmq.client.*;
import com.xy.test.mq.util.MqUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 接收端
 */
public class Recv1 {

    public static void main(String[] args) throws IOException {
        Connection connection = MqUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(MqUtil.WORK_FAIR_QUEUE, false, false, false, null);
        channel.basicQos(1);//保证一次只分发一个
        Consumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, StandardCharsets.UTF_8);
                System.out.println("Recv1接收端数据----->:" + msg);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("Recv1 接收完成");
                    //手动回执
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };
        //监听队列
        boolean autoAck = false;//关闭自动应答
        channel.basicConsume(MqUtil.WORK_FAIR_QUEUE, autoAck, defaultConsumer);
    }
}
