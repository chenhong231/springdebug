package org.thinking.spring.bean.definition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.thinking.spring.bean.factory.DefaultUserFactory;
import org.thinking.spring.bean.factory.UserFactory;

@Configuration
public class BeanInitializationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationDemo.class);
        applicationContext.refresh();
        //非延迟初始化在spring应用上下文启动完成后，被初始化
        System.out.println("应用上下文已启动...");
        //依赖查找
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);

        System.out.println(userFactory);

        System.out.println("应用上下文准备关闭...");
        applicationContext.close();
        System.out.println("应用上下文已关闭...");
    }

    @Bean(initMethod = "initUserFactory", destroyMethod = "doDestroy")
    //@Lazy
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
