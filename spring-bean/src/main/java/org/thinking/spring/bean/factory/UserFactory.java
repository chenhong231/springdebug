package org.thinking.spring.bean.factory;

import org.thinking.spring.ioc.overview.domain.User;

public interface UserFactory {

    default public User createUser() {
        return User.createUser();
    }
}
