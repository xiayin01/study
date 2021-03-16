package com.xy.user.web.microprofile.config;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyConfigSource implements ConfigSource {

    private static final String NAME = "application.name";

    private final Map<String, Object> source;

    public MyConfigSource() {
        Map properties = System.getProperties();
        this.source = new HashMap<>(properties);
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
