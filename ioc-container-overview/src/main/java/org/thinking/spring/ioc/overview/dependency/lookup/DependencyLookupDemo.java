package org.thinking.spring.ioc.overview.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.thinking.spring.ioc.overview.domain.User;

public class DependencyLookupDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-lookup-context.xml");
        User user = (User) beanFactory.getBean("user");
        System.out.println(user);
    }
}
