package com.com.com.webaaa.domain.repository;

import com.com.com.webaaa.domain.model.User;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UserDao {

    public int count() throws DataAccessException;
    public int insertOne(User user) throws DataAccessException;
    public User selectOne(String userId) throws DataAccessException;
    public List<User> selectMany() throws DataAccessException;
    public int updateOne(User user) throws DataAccessException;
    public int deleteOne(String userId) throws DataAccessException;
    public void userCsvOut() throws DataAccessException;
}
