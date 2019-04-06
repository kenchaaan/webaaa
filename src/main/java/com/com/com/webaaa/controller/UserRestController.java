package com.com.com.webaaa.controller;

import com.com.com.webaaa.domain.model.User;
import com.com.com.webaaa.domain.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    RestService restService;

    @GetMapping("/api/users/{id:.+}")
    public User getUserOne(@PathVariable("id") String userId) {
        return restService.selectOne(userId);
    }

    @GetMapping("/api/users")
    public List<User> getUsers() {
        return restService.selectMany();
    }
}
