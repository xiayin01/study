package com.kedi.config.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 消费端启动类
 *
 * @author xy
 */
@SpringBootApplication(exclude = {GsonAutoConfiguration.class, JdbcTemplateAutoConfiguration.class,
        JmxAutoConfiguration.class, MultipartAutoConfiguration.class,
        WebSocketServletAutoConfiguration.class})
@EnableDiscoveryClient
@EnableScheduling
public class ConsumerApp {

    /**
     * 刷新类
     */
    private final ContextRefresher contextRefresher;

    @Autowired
    public ConsumerApp(ContextRefresher contextRefresher) {
        this.contextRefresher = contextRefresher;
    }

    /**
     * 定时任务对标注有@ConfigurationProperties，@RefreshScope 的对象进行刷新
     */
    @Scheduled(fixedRate = 1000L)
    public void updateConfig() {
        contextRefresher.refresh();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApp.class, args);
    }
}
