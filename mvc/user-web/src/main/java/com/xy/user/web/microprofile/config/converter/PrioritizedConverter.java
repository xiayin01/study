package com.xy.user.web.microprofile.config.converter;

import org.eclipse.microprofile.config.spi.Converter;

/**
 * {@link Converter} 排序
 *
 * @param <T>
 */
public class PrioritizedConverter<T> implements Converter<T>, Comparable<PrioritizedConverter<T>> {

    private final Converter<T> converter;

    private final int priority;

    public PrioritizedConverter(Converter<T> converter, int priority) {
        this.converter = converter;
        this.priority = priority;
    }

    @Override
    public int compareTo(PrioritizedConverter<T> other) {
        return Integer.compare(other.priority, this.priority);
    }

    @Override
    public T convert(String value) throws IllegalArgumentException, NullPointerException {
        return this.converter.convert(value);
    }

    public int getPriority() {
        return priority;
    }

    public Converter<T> getConverter() {
        return converter;
    }
}
