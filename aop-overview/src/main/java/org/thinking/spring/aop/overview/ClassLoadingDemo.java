package org.thinking.spring.aop.overview;

public class ClassLoadingDemo {

    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(classLoader);

        while (true) {
            ClassLoader parentClassLoader = classLoader.getParent();
            if(parentClassLoader == null) {
                break;
            }
            System.out.println(parentClassLoader);
            classLoader = parentClassLoader;
        }
    }
}
