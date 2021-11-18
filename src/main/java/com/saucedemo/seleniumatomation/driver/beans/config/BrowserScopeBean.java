package com.saucedemo.seleniumatomation.driver.beans.config;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BrowserScopeBean {

    @Bean
    public static BeanFactoryPostProcessor beanFactoryPostProcessor(){
        return new BrowserBeanFactoryPostProcessor();
    }
}
