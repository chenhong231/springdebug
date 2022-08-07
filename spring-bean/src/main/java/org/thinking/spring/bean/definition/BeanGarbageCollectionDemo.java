package org.thinking.spring.bean.definition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.thinking.spring.bean.factory.DefaultUserFactory;
import org.thinking.spring.bean.factory.UserFactory;

public class BeanGarbageCollectionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationDemo.class);
        applicationContext.refresh();


        applicationContext.close();
        System.out.println("应用上下文已关闭...");

        System.gc();
    }
}
