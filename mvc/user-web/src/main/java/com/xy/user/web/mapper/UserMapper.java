package com.xy.user.web.mapper;

import com.xy.user.web.domain.User;

import java.util.Collection;

public interface UserMapper {

    boolean save(User user);

    boolean deleteById(Long userId);

    boolean update(User user);

    User getById(Long userId);

    User getByEmailAndPassword(String userName, String password);

    Collection<User> getAll();
}
