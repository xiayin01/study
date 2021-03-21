package com.xy.user.web.microprofile.config.converter;


import org.eclipse.microprofile.config.spi.Converter;

import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

import static java.util.ServiceLoader.load;

public class Converters implements Iterable<Converter> {

    //默认优先级
    public static final int DEFAULT_PRIORITY = 100;
    //类型转换器，有优先级的
    private final Map<Class<?>, PriorityQueue<PrioritizedConverter>> typedConverters = new HashMap<>();

    private ClassLoader classLoader;
    //是否加载了系统配置
    private boolean addedDiscoveredConverters = false;

    public Converters() {
        this(Thread.currentThread().getContextClassLoader());
    }

    public Converters(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public Iterator<Converter> iterator() {
        List<Converter> allConverters = new LinkedList<>();
        for (PriorityQueue<PrioritizedConverter> converters : typedConverters.values()) {
            for (PrioritizedConverter converter : converters) {
                allConverters.add(converter.getConverter());
            }
        }
        return allConverters.iterator();
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public void addConverter(Converter converter, int priority, Class<?> convertedType) {
        PriorityQueue priorityQueue = typedConverters.computeIfAbsent(convertedType, t -> new PriorityQueue<>());
        priorityQueue.offer(new PrioritizedConverter(converter, priority));
    }

    public void addConverter(Converter converter, int priority) {
        Class<?> convertedType = resolveConvertedType(converter);
        addConverter(converter, priority, convertedType);
    }

    public void addConverter(Converter converter) {
        addConverter(converter, DEFAULT_PRIORITY);
    }

    public void addConverters(Iterable<Converter> converters) {
        converters.forEach(this::addConverter);
    }

    public void addConverters(Converter... converters) {
        addConverters(Arrays.asList(converters));
    }

    public List<Converter> getConverters(Class<?> convertedType) {
        PriorityQueue<PrioritizedConverter> prioritizedConverters = typedConverters.get(convertedType);
        if (prioritizedConverters == null || prioritizedConverters.isEmpty()) {
            return Collections.emptyList();
        }
        List<Converter> converters = new LinkedList<>();
        for (PrioritizedConverter prioritizedConverter : prioritizedConverters) {
            converters.add(prioritizedConverter.getConverter());
        }
        return converters;
    }

    public void addDiscoveredConverters() {
        if (addedDiscoveredConverters) {
            return;
        }
        addConverters(load(Converter.class, classLoader));
        addedDiscoveredConverters = true;
    }

    protected Class<?> resolveConvertedType(Converter<?> converter) {
        assertConverter(converter);
        Class<?> convertedType = null;
        Class<?> converterClass = converter.getClass();
        while (null != converterClass) {
            convertedType = resolveConvertedType(converterClass);
            if (null != convertedType) {
                break;
            }
            Type superType = converterClass.getGenericSuperclass();
            if (superType instanceof ParameterizedType) {
                convertedType = resolveConvertedType(superType);
            }
            if (convertedType != null) {
                break;
            }
            converterClass = converterClass.getSuperclass();
        }
        return convertedType;
    }

    private void assertConverter(Converter<?> converter) {
        Class<?> converterClass = converter.getClass();
        if (converterClass.isInterface()) {
            throw new IllegalArgumentException("The implementation class of Converter must not be an interface!");
        }
        if (Modifier.isAbstract(converterClass.getModifiers())) {
            throw new IllegalArgumentException("The implementation class of Converter must not be abstract!");
        }
    }

    private Class<?> resolveConvertedType(Class<?> converterClass) {
        Class<?> convertedType = null;
        for (Type superInterface : converterClass.getGenericInterfaces()) {
            convertedType = resolveConvertedType(superInterface);
            if (convertedType != null) {
                break;
            }
        }
        return convertedType;
    }

    /**
     * 读取泛型类上具体的类型#AbstractConverter<Double>
     *
     * @param type type
     * @return 如：Double
     */
    private Class<?> resolveConvertedType(Type type) {
        Class<?> convertedType = null;
        if (type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) type;
            if (pType.getRawType() instanceof Class) {
                //rawType=AbstractConverter
                Class<?> rawType = (Class) pType.getRawType();
                if (Converter.class.isAssignableFrom(rawType)) {
                    Type[] arguments = pType.getActualTypeArguments();
                    if (arguments.length == 1 && arguments[0] instanceof Class) {
                        convertedType = (Class) arguments[0];
                    }
                }
            }
        }
        return convertedType;
    }
}
