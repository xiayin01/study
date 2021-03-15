package com.xy.user.web.microprofile.config;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigBuilder;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;

import java.util.Iterator;
import java.util.ServiceLoader;

public class JavaEEConfigProviderResolver extends ConfigProviderResolver {


    @Override
    public Config getConfig() {
        return getConfig(null);
    }

    @Override
    public Config getConfig(ClassLoader classLoader) {
        ClassLoader loader = classLoader;
        if (loader == null) {
            loader = Thread.currentThread().getContextClassLoader();
        }
        ServiceLoader<Config> servletContext = ServiceLoader.load(Config.class, loader);
        Iterator<Config> iterator = servletContext.iterator();
        return iterator.next();
    }

    @Override
    public ConfigBuilder getBuilder() {
        return null;
    }

    @Override
    public void registerConfig(Config config, ClassLoader classLoader) {

    }

    @Override
    public void releaseConfig(Config config) {

    }
}
