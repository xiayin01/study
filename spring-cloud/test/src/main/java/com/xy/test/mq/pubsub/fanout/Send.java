package com.xy.test.mq.pubsub.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xy.test.mq.util.MqUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 发布-订阅模式，fanout->不处理路由键
 */
public class Send {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = MqUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare(MqUtil.EXCHANGE_FANOUT_NAME, "fanout");
        //发送消息
        String msg = "发布-订阅模式----》fanout";
        channel.basicPublish(MqUtil.EXCHANGE_FANOUT_NAME, "", null, msg.getBytes());
        channel.close();
        connection.close();
    }
}
