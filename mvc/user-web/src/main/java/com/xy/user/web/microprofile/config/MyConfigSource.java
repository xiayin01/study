package com.xy.user.web.microprofile.config;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.Set;

public class MyConfigSource implements ConfigSource {

    private static final String NAME="application.name";

    @Override
    public int getOrdinal() {
        return 900;
    }

    @Override
    public Set<String> getPropertyNames() {
        return null;
    }

    @Override
    public String getValue(String s) {
        return null;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
