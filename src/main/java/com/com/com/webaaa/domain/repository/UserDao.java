package com.com.com.webaaa.domain.repository;

import com.com.com.webaaa.domain.model.User;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UserDao {
    int count() throws DataAccessException;
    int insertOne(User user) throws DataAccessException;
    User selectOne(String userId) throws DataAccessException;
    List<User> selectMany() throws DataAccessException;
    int updateOne(User user) throws DataAccessException;
    int deleteOne(String userId) throws DataAccessException;
    void userCsvOut() throws DataAccessException;
}
