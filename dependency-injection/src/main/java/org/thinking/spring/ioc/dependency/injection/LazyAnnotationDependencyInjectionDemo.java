package org.thinking.spring.ioc.dependency.injection;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thinking.spring.ioc.dependency.injection.annotation.UserGroup;

import java.util.Collection;
import java.util.Set;

@Configuration
public class LazyAnnotationDependencyInjectionDemo {

    @Autowired
    private User user;

    @Autowired
    private ObjectProvider<User> userObjectProvider;

    @Autowired
    private ObjectFactory<Set<User>> usersObjectFactory;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册Configuration Class 配置类
        applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();

        LazyAnnotationDependencyInjectionDemo demo = applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);

        System.out.println("demo.user = " + demo.user);
        System.out.println("demo.userObjectProvider = "  + demo.userObjectProvider.getObject());

        System.out.println("demo.usersObjectFactory = " + demo.usersObjectFactory.getObject());

        demo.userObjectProvider.forEach(System.out::println);

        applicationContext.close();
    }
}
