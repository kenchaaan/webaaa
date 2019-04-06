package com.com.com.webaaa.domain.repository;

import com.com.com.webaaa.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class RestDaoJdbcImpl implements RestDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public User selectOne(String userId) {
        String sql = "SELECT * FROM m_user where user_id = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbc.queryForObject(sql, rowMapper, userId);
    }

    @Override
    public List<User> selectMany() {
        return null;
    }

    @Override
    public int createUser(User user) {
        return 0;
    }

    @Override
    public int updateUser(User user) {
        return 0;
    }

    @Override
    public int deleteUser(String userId) {
        return 0;
    }
}
