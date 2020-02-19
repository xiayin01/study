package com.xy.test.mq.confirm.async;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.xy.test.mq.util.MqUtil;

import java.io.IOException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * confirm(普通模式---》异步)
 */
public class Send {

    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = MqUtil.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //创建队列申明
        channel.queueDeclare(MqUtil.QUEUE_CONFIRM_NAME, false, false, false, null);
        channel.confirmSelect();
        //未确认的标识
        final SortedSet<Long> confirmSet = Collections.synchronizedSortedSet(new TreeSet<>());

        channel.addConfirmListener(new ConfirmListener() {
            //没有问题
            @Override
            public void handleAck(long deliveryTag, boolean multiple) {
                if (multiple) {
                    System.out.println("----handleAck---multiple");
                    confirmSet.headSet(deliveryTag + 1).clear();
                } else {
                    System.out.println("-----handleAck----multiple----false");
                    confirmSet.remove(deliveryTag);
                }
            }

            //有问题
            @Override
            public void handleNack(long deliveryTag, boolean multiple) {
                if (multiple) {
                    System.out.println("----handleNack---multiple");
                    confirmSet.headSet(deliveryTag + 1).clear();
                } else {
                    System.out.println("-----handleNack----multiple----false");
                    confirmSet.remove(deliveryTag);
                }
            }
        });
        //消息内容
        String msg = "test  hello confirm  async";
        while (true) {
            long seqNo = channel.getNextPublishSeqNo();
            channel.basicPublish("", MqUtil.QUEUE_CONFIRM_NAME, null, msg.getBytes());
            confirmSet.add(seqNo);
        }
    }
}
