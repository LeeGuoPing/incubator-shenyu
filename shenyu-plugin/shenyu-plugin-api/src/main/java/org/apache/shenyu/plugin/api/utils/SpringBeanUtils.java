/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shenyu.plugin.api.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;

/**
 * SpringBeanUtils.
 */
public final class SpringBeanUtils {
    
    private static final SpringBeanUtils INSTANCE = new SpringBeanUtils();
    
    private ApplicationContext applicationContext;
    
    private SpringBeanUtils() {
    }
    
    /**
     * get SpringBeanUtils.
     *
     * @return SpringBeanUtils instance
     */
    public static SpringBeanUtils getInstance() {
        return INSTANCE;
    }
    
    /**
     * acquire spring bean.
     *
     * @param <T>  class
     * @param type type
     * @return bean bean
     */
    public <T> T getBean(final Class<T> type) {
        return applicationContext.getBean(type);
    }
    
    /**
     * Gets bean.
     *
     * @param <T>      the type parameter
     * @param beanName the bean name
     * @return the bean
     */
    @SuppressWarnings("all")
    public <T> T getBean(final String beanName) {
        return (T) applicationContext.getBean(beanName);
    }
    
    /**
     * Register bean.
     *
     * @param beanDefinition the bean definition
     * @param classLoader    the class loader
     * @return the string
     */
    public String registerBean(final BeanDefinition beanDefinition, final ClassLoader classLoader) {
        String beanClassName = beanDefinition.getBeanClassName();
        if (StringUtils.isBlank(beanClassName)) {
            throw new NullPointerException("beanDefinition.beanClassName is null");
        }
        String beanName = getBeanName(beanClassName);
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        beanFactory.setBeanClassLoader(classLoader);
        beanFactory.registerBeanDefinition(beanName, beanDefinition);
        return beanName;
    }
    
    private String getBeanName(final String className) {
        String name = className.substring(className.lastIndexOf(".") + 1);
        String start = name.substring(0, 1);
        String end = name.substring(1);
        return start.toLowerCase() + end;
    }
    
    /**
     * set application context.
     *
     * @param applicationContext application context
     */
    public void setApplicationContext(final ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
