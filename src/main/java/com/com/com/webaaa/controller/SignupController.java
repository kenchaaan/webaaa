package com.com.com.webaaa.controller;

import com.com.com.webaaa.domain.model.SignupForm;
import com.com.com.webaaa.domain.model.User;
import com.com.com.webaaa.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SignupController {

    private Map<String, String> radioMarriage;

    @Autowired
    UserService userService;

    private Map<String, String> initRadioMarriage() {
        Map<String, String> radio = new HashMap<>();
        radio.put("marriaged", "true");
        radio.put("unmarriaged", "false");
        return radio;
    }

    @GetMapping("/signup")
    public String getSignup(@ModelAttribute SignupForm signupForm, Model model) {
        radioMarriage = initRadioMarriage();
        model.addAttribute("radioMarriage", radioMarriage);
        return "login/signup";
    }

    @PostMapping("/signup")
    public String postSignup(@ModelAttribute @Validated SignupForm signupForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return getSignup(signupForm, model);
        }
        System.out.println(signupForm);
        User user = new User();
        user.setUserId(signupForm.getUserId());
        user.setPassword(signupForm.getPassword());
        user.setUserName(signupForm.getUserName());
        user.setBirthday(signupForm.getBirthday());
        user.setAge(signupForm.getAge());
        user.setMarriage(signupForm.isMarriage());
        user.setRole("ROLE_GENERAL");
        boolean result = userService.insert(user);

        if (result) {
            System.out.println("completed");
        } else {
            System.out.println("faled");
        }

        return "redirect:login";
    }
}
