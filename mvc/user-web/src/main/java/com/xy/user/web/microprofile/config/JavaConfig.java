package com.xy.user.web.microprofile.config;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigValue;
import org.eclipse.microprofile.config.spi.ConfigSource;
import org.eclipse.microprofile.config.spi.Converter;

import java.util.*;

public class JavaConfig implements Config {

    private List<ConfigSource> configSources = new LinkedList<>();

    private static Comparator<ConfigSource> configSourceComparator = (o1, o2) -> Integer.compare(o2.getOrdinal(), o1.getOrdinal());

    public JavaConfig() {
        ClassLoader loader = getClass().getClassLoader();
        ServiceLoader<ConfigSource> servletContext = ServiceLoader.load(ConfigSource.class, loader);
        servletContext.forEach(configSources::add);
        //排序
        configSources.sort(configSourceComparator);
    }

    @Override
    public <T> T getValue(String propertyName, Class<T> propertyType) {
        String propertyValue = getPropertyValue(propertyName);
        if (getConverter(propertyType).isPresent()) {
            return getConverter(propertyType).get().convert(propertyValue);
        } else {
            throw new RuntimeException("类型转换异常");
        }
    }

    @Override
    public ConfigValue getConfigValue(String s) {
        return null;
    }

    @Override
    public <T> Optional<T> getOptionalValue(String propertyName, Class<T> propertyType) {
        T value = getValue(propertyName, propertyType);
        return Optional.ofNullable(value);
    }

    @Override
    public Iterable<String> getPropertyNames() {
        return null;
    }

    protected String getPropertyValue(String propertyName) {
        String propertyValue = null;
        for (ConfigSource configSource : configSources) {
            if (propertyName.equals(configSource.getName())) {
                propertyValue = configSource.getValue(propertyName);
                break;
            }
        }
        return propertyValue;
    }

    @Override
    public Iterable<ConfigSource> getConfigSources() {
        return null;
    }

    @Override
    public <T> Optional<Converter<T>> getConverter(Class<T> propertyType) {
        Converter converter = (Converter) s -> {
            switch (propertyType.getName()) {
                case "java.lang.String":
                    return s;
                case "java.lang.Integer":
                    return Integer.valueOf(s);
                case "java.lang.Double":
                    return Double.parseDouble(s);
            }
            return null;
        };
        return Optional.of(converter);
    }

    @Override
    public <T> T unwrap(Class<T> propertyType) {
        return null;
    }
}

