package org.thinking.spring.ioc.dependency.injection;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thinking.spring.ioc.dependency.injection.annotation.UserGroup;

import java.util.Collection;

@Configuration
public class QualifierAnnotationDependencyInjectionDemo {

    @Autowired
    private User user;

    @Autowired
    @Qualifier("user")
    private User namedUser;

    @Autowired
    private Collection<User> allUsers;

    @Autowired
    @Qualifier
    private Collection<User> qualifiedUsers;

    @Autowired
    @UserGroup
    private Collection<User> groupedUsers;

    @Bean
    @Qualifier
    public User user1() {
        return createUser(7L);
    }

    @Bean
    @Qualifier
    public User user2() {
        return createUser(8L);
    }

    @Bean
    @UserGroup
    public User user3() {
        return createUser(9L);
    }

    @Bean
    @UserGroup
    public User user4() {
        return createUser(10L);
    }

    private static User createUser(Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册Configuration Class 配置类
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();

        QualifierAnnotationDependencyInjectionDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);

        System.out.println("demo.user = " + demo.user);
        System.out.println("demo.namedUser = " + demo.namedUser);

        System.out.println("demo.allUsers = " + demo.allUsers);
        System.out.println("demo.qualifiedUsers = " + demo.qualifiedUsers);

        System.out.println("demo.groupedUsers = " + demo.groupedUsers);

        applicationContext.close();
    }
}
