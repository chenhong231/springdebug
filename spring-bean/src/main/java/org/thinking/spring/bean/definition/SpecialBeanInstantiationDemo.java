package org.thinking.spring.bean.definition;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.thinking.spring.bean.factory.DefaultUserFactory;
import org.thinking.spring.bean.factory.UserFactory;
import org.thinking.spring.ioc.overview.domain.User;

import java.util.Iterator;
import java.util.ServiceLoader;

import static java.util.ServiceLoader.load;

public class SpecialBeanInstantiationDemo {
    public static void main(String[] args) {

        ApplicationContext applicationContext  = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-context.xml");
        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();

        ServiceLoader<UserFactory> serviceLoader = beanFactory.getBean("userFactoryServiceLoader", ServiceLoader.class);
        displayServiceLoader(serviceLoader);
        //demoServiceLoader();

        // 创建 UserFactory 对象,通过 AutowireCapableBeanFactory
        UserFactory userFactory = beanFactory.createBean(DefaultUserFactory.class);
        System.out.println(userFactory.createUser());
    }

//    public static void demoServiceLoader() {
//        ServiceLoader<UserFactory> serviceLoader = load(UserFactory.class, Thread.currentThread().getContextClassLoader());
//        displayServiceLoader(serviceLoader);
//    }

    private static void displayServiceLoader(ServiceLoader<UserFactory> serviceLoader) {
        final Iterator<UserFactory> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            UserFactory userFactory = iterator.next();
            System.out.println(userFactory.createUser());
        }
    }
}
