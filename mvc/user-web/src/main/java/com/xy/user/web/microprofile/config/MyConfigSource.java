package com.xy.user.web.microprofile.config;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class MyConfigSource implements ConfigSource {

    private static final String NAME = "application.name";

    private final Map<String, Object> source;

    public MyConfigSource() throws IOException {
        /*Map properties = System.getProperties();
        this.source = new HashMap<>(properties);*/
        //读取配置文件
        Properties properties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("META-INF/microprofile-config.property");
        properties.load(inputStream);
        Map map = properties;
        this.source = new HashMap<>(map);
    }

    @Override
    public int getOrdinal() {
        return 900;
    }

    @Override
    public Set<String> getPropertyNames() {
        return source.keySet();
    }

    @Override
    public String getValue(String s) {
        return (String) source.get(s);
    }

    @Override
    public String getName() {
        return "Java System Properties";
    }
}
