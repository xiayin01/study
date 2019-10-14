package com.xy.test.config;

import feign.Client;
import feign.httpclient.ApacheHttpClient;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

/**
 * httpClient配置
 *
 * @author xy
 */
@Configuration
public class CloseableHttpClientConfig {

    public CloseableHttpClient httpClient() {
        // 最终存活时间还需要看服务端的keep-alive设置,和空闲时间以及间歇的validate是否通过
        PoolingHttpClientConnectionManager pool = new PoolingHttpClientConnectionManager(30, TimeUnit.SECONDS);
        pool.setMaxTotal(2000);
        // 目前只有一个路由,默认等于最大值,根据业务并发量设置
        pool.setDefaultMaxPerRoute(2000);
        // 检查非活动连接,避免服务端重启后或者服务端keep-alive过期主动关闭连接造成失效,对于微服务场景可能还比较普遍,但受限HTTP设计理念,这也并发完全可靠,使用re-try/re-execute机制来弥补,考虑到可能很多Niginx配置为5秒keep-alive
        // TODO 这个值还待商榷
        pool.setValidateAfterInactivity(5 * 1000);
        return HttpClients.custom()
                .setConnectionManager(pool)
                // 连接空闲10s就回收,这个会启动独立线程检测,所以必须声明destroy方法来关闭独立线程
                .evictIdleConnections(10, TimeUnit.SECONDS)
                // 建立连接时间和从连接池获取连接时间,以及数据传输时间
                .setDefaultRequestConfig(RequestConfig.custom()
                        // http建立连接超时时间
                        .setConnectTimeout(1000)
                        // 从连接池获取连接超时时间
                        .setConnectionRequestTimeout(3000)
                        // socket超时时间
                        .setSocketTimeout(10000)
                        .build())
                // 自定义重试机制
                .setRetryHandler((exception, executionCount, context) -> {
                    // 目前只允许重试一次
                    if (executionCount > 1) {
                        return false;
                    }
                    // 如果是服务端主动关闭连接的,数据并没有被服务端接受,可以重试
                    if (exception instanceof NoHttpResponseException) {
                        return true;
                    }
                    // 不要重试SSL握手异常
                    if (exception instanceof SSLHandshakeException) {
                        return false;
                    }
                    // 超时
                    if (exception instanceof InterruptedIOException) {
                        return false;
                    }
                    // 目标服务器不可达
                    if (exception instanceof UnknownHostException) {
                        return false;
                    }
                    // SSL握手异常
                    if (exception instanceof SSLException) {
                        return false;
                    }
                    HttpClientContext clientContext = HttpClientContext.adapt(context);
                    HttpRequest request = clientContext.getRequest();
                    String get = "GET";
                    // GET方法是幂等的,可以重试
                    if (request.getRequestLine().getMethod().equalsIgnoreCase(get)) {
                        return true;
                    }
                    return false;
                })
                // 默认的ConnectionKeepAliveStrategy就是动态根据keep-alive计算的
                .build();
    }

    /**
     * 如果没有ribbon,不需要服务发现和负载均衡功能,必须申明此bean,如果需要,则千万不能申明此bean,
     * 否则将不具备服务发现和负载功能
     *
     * @param closeableHttpClient httpClient
     * @return client
     */
    @Bean
    public Client apacheHttpClient(CloseableHttpClient closeableHttpClient) {
        return new ApacheHttpClient(closeableHttpClient);
    }
}
