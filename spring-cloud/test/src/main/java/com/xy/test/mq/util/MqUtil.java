package com.xy.test.mq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class MqUtil {

    /**
     * 一对一
     */
    public static final String SIMPLE_QUEUE = "one_to_one_queue";
    /**
     * 一对多（轮询）
     */
    public static final String WORK_QUEUE = "ont_to_more_queue";
    /**
     * 公平发送
     */
    public static final String WORK_FAIR_QUEUE = "work_fair_queue";
    /**
     * 交换机(Fanout)
     */
    public static final String EXCHANGE_FANOUT_NAME = "exchange_fanout";
    /**
     * 交换机(Direct)
     */
    public static final String EXCHANGE_DIRECT_NAME = "exchange_direct";
    /**
     * 交换机(Topic)
     */
    public static final String EXCHANGE_TOPIC_NAME = "exchange_topic";
    /**
     * 路由key
     */
    public static final String ROUTING_KEY_ERROR = "error";
    /**
     * 路由key
     */
    public static final String ROUTING_KEY_INFO = "info";
    /**
     * 路由key
     */
    public static final String ROUTING_KEY_WARNING = "warning";
    /**
     * 发布-订阅消息
     */
    public static final String PUB_SUB_QUEUE_MAIL = "pub_sub_queue_mail";
    /**
     * 发布-订阅消息
     */
    public static final String PUB_SUB_QUEUE_SMS = "pub_sub_queue_sms";
    /**
     * confirm模式
     */
    public static final String QUEUE_CONFIRM_NAME = "queue_confirm";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost("127.0.0.1");
            connectionFactory.setPort(5762);
            connectionFactory.setVirtualHost("/vhost_xy");
            connectionFactory.setUsername("xy");
            connectionFactory.setPassword("xy");
            connection = connectionFactory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
