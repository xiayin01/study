package com.xy.test.mq.confirm.sync;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xy.test.mq.util.MqUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * confirm(普通模式---》同步---》发送一条情况)
 */
public class SendOne {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //获取连接
        Connection connection = MqUtil.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //创建队列申明
        channel.queueDeclare(MqUtil.QUEUE_CONFIRM_NAME, false, false, false, null);
        channel.confirmSelect();
        //消息内容
        String msg = "test  hello confirm";
        channel.basicPublish("", MqUtil.QUEUE_CONFIRM_NAME, null, msg.getBytes());
        //确认
        if (!channel.waitForConfirms()) {
            System.out.println("confirm fail");
        } else {
            System.out.println("confirm ok");
        }
        channel.close();
        connection.close();
    }
}
