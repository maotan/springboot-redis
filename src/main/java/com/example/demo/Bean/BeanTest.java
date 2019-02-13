package com.example.demo.Bean;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ComponentProperties.class)
public class BeanTest {


    @Autowired
    private ComponentProperties componentProperties;

    /*  通过构造函数注入
    private final ComponentProperties componentProperties;
    public BeanTest(ComponentProperties componentProperties) {
        this.componentProperties = componentProperties;
    }*/

    @Bean
    public Student getStudent(){
        Student st = new Student();
        st.setId(111L);
        st.setName("sss");
        return st;
    }

    public void hello(){
        System.out.println("hello world");
    }
}

