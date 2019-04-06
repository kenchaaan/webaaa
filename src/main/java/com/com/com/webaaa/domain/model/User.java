package com.com.com.webaaa.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "m_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String userId;
    private String password;
    private String userName;
    private Date birthday;
    private int age;
    private boolean marriage;
    private String role;

    public User(String userId) {
        this.userId = userId;
    }

    public User() {

    }
}
