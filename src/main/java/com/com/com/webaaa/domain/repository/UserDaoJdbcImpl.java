package com.com.com.webaaa.domain.repository;

import com.com.com.webaaa.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoJdbcImpl implements UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public int count() throws DataAccessException {
        int rowNumber = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM m_user", Integer.class);
        return rowNumber;
    }

    @Override
    public int insertOne(User user) throws DataAccessException {
        String password = passwordEncoder.encode(user.getPassword());
        int rowNumber = jdbcTemplate.update("INSERT INTO m_user(" +
                "user_id, password, user_name, birthday, age, marriage, role) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?)",
                user.getUserId(),
                password,
                user.getUserName(),
                user.getBirthday(),
                user.getAge(),
                user.isMarriage(),
                user.getRole()
                );
        return rowNumber;
    }

    @Override
    public User selectOne(String userId) throws DataAccessException {
        Map<String, Object> map = jdbcTemplate.queryForMap("SELECT * FROM m_user " +
                        "where user_id = ?",
                userId);
        User user = new User();
        user.setUserId((String) map.get("user_id"));
        user.setPassword((String) map.get("password"));
        user.setUserName((String) map.get("user_name"));
        user.setAge((Integer) map.get("age"));
        user.setBirthday((Date) map.get("birthday"));
        user.setRole((String) map.get("role"));
        user.setMarriage((Boolean) map.get("marriage"));
        return user;
    }

    @Override
    public List<User> selectMany() throws DataAccessException {
        List<Map<String, Object>> tmpList = jdbcTemplate.queryForList("SELECT * FROM m_user");
        List<User> userList = new ArrayList<>();
        for (Map<String, Object> map : tmpList) {
            User user = new User();
            user.setUserId((String)map.get("user_id"));
            user.setPassword((String) map.get("password"));
            user.setUserName((String) map.get("user_name"));
            user.setAge((Integer) map.get("age"));
            user.setBirthday((Date) map.get("birthday"));
            user.setMarriage((Boolean) map.get("marriage"));
            user.setRole((String)map.get("role"));
            userList.add(user);
        }
        return userList;
    }

    @Override
    public int updateOne(User user) throws DataAccessException {
        String password = passwordEncoder.encode(user.getPassword());
        int rowNumber = jdbcTemplate.update("UPDATE m_user " +
                "SET " +
                "password = ?, " +
                "user_name = ?, " +
                "birthday = ?, " +
                "age = ?, " +
                "marriage = ? " +
                "where user_id = ?",
                password,
                user.getUserName(),
                user.getBirthday(),
                user.getAge(),
                user.isMarriage(),
                user.getUserId());
        return rowNumber;
    }

    @Override
    public int deleteOne(String userId) throws DataAccessException {
        int rowNumber = jdbcTemplate.update("DELETE FROM m_user " +
                        "WHERE user_id = ?",
                userId);
        return rowNumber;
    }

    @Override
    public void userCsvOut() throws DataAccessException {
        String sql = "SELECT * FROM m_user";
        UserRowCallBackHandler handler = new UserRowCallBackHandler();
        jdbcTemplate.query(sql, handler);
    }
}
