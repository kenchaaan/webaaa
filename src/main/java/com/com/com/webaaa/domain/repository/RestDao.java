package com.com.com.webaaa.domain.repository;

import com.com.com.webaaa.domain.model.User;

import java.util.List;

public interface RestDao {
    public User selectOne(String userId);

    public List<User> selectMany();

    public int createUser(User user);

    public int updateUser(User user);

    public int deleteUser(String userId);
}
