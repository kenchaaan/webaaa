package com.com.com.webaaa.domain.repository;

import com.com.com.webaaa.domain.model.User;

import java.util.List;

public interface RestDao {
    User selectOne(String userId);

    List<User> selectMany();

    int createUser(User user);

    int updateUser(User user);

    int deleteUser(String userId);
}
