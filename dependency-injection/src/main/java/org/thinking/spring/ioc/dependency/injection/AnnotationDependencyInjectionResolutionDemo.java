package org.thinking.spring.ioc.dependency.injection;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.thinking.spring.ioc.dependency.injection.annotation.InjectedUser;
import org.thinking.spring.ioc.dependency.injection.annotation.MyAutowired;

import java.lang.annotation.Annotation;
import java.util.*;

import static org.springframework.context.annotation.AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME;

@Configuration
public class AnnotationDependencyInjectionResolutionDemo {

    @Autowired
    private User user;

    @Autowired
    private Map<String, User> users;

    @MyAutowired
    private Optional<User> userOptional;

    @InjectedUser
    private User myInjectedUser;

//    @Bean(name = AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
//    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
//        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
//
//        Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<>(Arrays.asList(Autowired.class, InjectedUser.class));
//        beanPostProcessor.setAutowiredAnnotationTypes(autowiredAnnotationTypes);
//        return beanPostProcessor;
//    }

    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE - 3)
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        beanPostProcessor.setAutowiredAnnotationType(InjectedUser.class);
        return beanPostProcessor;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册Configuration Class 配置类
        applicationContext.register(AnnotationDependencyInjectionResolutionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();

        AnnotationDependencyInjectionResolutionDemo demo = applicationContext.getBean(AnnotationDependencyInjectionResolutionDemo.class);

        System.out.println("demo.user = " + demo.user);

        System.out.println("demo.users + " +demo.users);

        System.out.println("demo.userOptional = " + demo.userOptional);

        System.out.println("demo.myInjectedUser = " + demo.myInjectedUser);

        applicationContext.close();
    }
}
