package com.xy.test.ext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * bean后置处理器
 *
 * @author xy
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        //bean个数
        int count = beanFactory.getBeanDefinitionCount();
        System.out.println("MyBeanFactoryPostProcessor...postProcessBeanFactory...中bean个数:" + count);
        String[] names = beanFactory.getBeanDefinitionNames();
        System.out.println("MyBeanFactoryPostProcessor...postProcessBeanFactory..." + Arrays.toString(names));
    }
}
