package org.thinking.spring.bean.definition;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.thinking.spring.ioc.overview.domain.User;

public class BeanInstantiationDemo {

    public static void main(String[] args) {

        BeanFactory beanFactory  = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation-context.xml");

        User user = beanFactory.getBean("usr-by-static-method", User.class);
        User userByInstanceMethod = beanFactory.getBean("user-by-instance-method", User.class);
        User userByFactoryBean = beanFactory.getBean("user-by-factory-bean", User.class);

        System.out.println(user);
        System.out.println(userByInstanceMethod);
        System.out.println(userByFactoryBean);

        System.out.println(user == userByInstanceMethod);
        System.out.println(user == userByFactoryBean);
    }
}
