package org.thinking.spring.bean.definition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.thinking.spring.bean.factory.DefaultUserFactory;
import org.thinking.spring.bean.factory.UserFactory;

public class SingletonBeanRegistrationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //创建一个外部单例对象
        UserFactory userFactory = new DefaultUserFactory();
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();

        beanFactory.registerSingleton("userFactory", userFactory);
        applicationContext.refresh();

        //通过依赖查找的方式来获取 UserFactory
        UserFactory userFactoryByLookup = beanFactory.getBean("userFactory", UserFactory.class);

        System.out.println(userFactoryByLookup == userFactory);

        applicationContext.close();
        System.out.println("应用上下文已关闭...");
    }
}
