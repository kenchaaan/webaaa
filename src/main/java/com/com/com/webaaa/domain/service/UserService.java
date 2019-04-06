package com.com.com.webaaa.domain.service;

import com.com.com.webaaa.domain.model.User;
import com.com.com.webaaa.domain.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public boolean insert(User user) {
        int rowNumber = userDao.insertOne(user);
        boolean result = false;
        if (rowNumber > 0) {
            result = true;
        }
        return result;
    }

    public int count() {
        return userDao.count();
    }

    public List<User> selectMany() {
        return userDao.selectMany();
    }

    public User selectOne(String userId) {
        return userDao.selectOne(userId);
    }

    public boolean updateOne(User user) {
        int rowNumber = userDao.updateOne(user);
        return rowNumber > 0;
    }

    public boolean deleteOne(String userId) {
        int rowNumber = userDao.deleteOne(userId);
        return rowNumber > 0;
    }

    public void outputCsv() {
        userDao.userCsvOut();
    }

    public byte[] getFile(String fileName) throws IOException {
        FileSystem fs = FileSystems.getDefault();
        Path p = fs.getPath(fileName);
        byte[] bytes = Files.readAllBytes(p);
        return bytes;
    }
}
