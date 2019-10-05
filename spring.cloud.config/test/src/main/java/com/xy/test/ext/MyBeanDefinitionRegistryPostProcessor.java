package com.xy.test.ext;

import com.xy.test.entity.Blue;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * BeanDefinitionRegistryPostProcessor在所有bean定义信息将要被加载，bean实例还未创建
 * 在BeanFactoryPostProcessor之前调用
 * 可以添加组件
 *
 * @author xy
 */
@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        //bean个数
        int count = registry.getBeanDefinitionCount();
        System.out.println("MyBeanDefinitionRegistryPostProcessor...postProcessBeanDefinitionRegistry...中bean个数:" + count);
        //RootBeanDefinition definition = new RootBeanDefinition(Blue.class);
        AbstractBeanDefinition definition = BeanDefinitionBuilder.rootBeanDefinition(Blue.class).getBeanDefinition();
        registry.registerBeanDefinition("blue", definition);
        System.out.println("MyBeanDefinitionRegistryPostProcessor...postProcessBeanDefinitionRegistry..." + Arrays.toString(registry.getBeanDefinitionNames()));
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanDefinitionRegistryPostProcessor...postProcessBeanFactory...中bean个数:" + beanFactory.getBeanDefinitionCount());
    }
}
