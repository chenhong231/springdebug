package org.thinking.spring.ioc.overview.dependency.injection;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import org.thinking.spring.ioc.overview.domain.User;
import org.thinking.spring.ioc.overview.repository.UserRepository;

public class DependencyInjectionDemo {

    public static void main(String[] args) {

//        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
        //依赖来源一： 自定义 Bean
        UserRepository userRepository = applicationContext.getBean("userRepository", UserRepository.class);

//        System.out.println(userRepository.getUsers());

        //依赖来源二： 依赖注入(内建依赖)
        System.out.println(userRepository.getBeanFactory());
//        System.out.println(userRepository.getBeanFactory() == beanFactory);

        ObjectFactory<ApplicationContext> userFactory = userRepository.getObjectFactory();
        System.out.println(userFactory.getObject() == applicationContext);
        //依赖查找(错误)
        //System.out.println(beanFactory.getBean(BeanFactory.class));

        //依赖来源三： 容器内建Bean
        Environment environment = applicationContext.getBean(Environment.class);
        System.out.println(environment);
    }

    private static void whoIsIoCContainer(UserRepository userRepository, ApplicationContext applicationContext) {
        System.out.println(userRepository.getBeanFactory() == applicationContext);
    }
}
