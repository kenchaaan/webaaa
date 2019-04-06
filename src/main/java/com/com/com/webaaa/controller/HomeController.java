package com.com.com.webaaa.controller;

import com.com.com.webaaa.domain.model.SignupForm;
import com.com.com.webaaa.domain.model.User;
import com.com.com.webaaa.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    private Map<String, String> radioMarriage;

    private Map<String, String> initRadioMarriage() {
        Map<String, String> radio = new HashMap<>();
        radio.put("marriaged", "true");
        radio.put("unmarriaged", "false");
        return radio;
    }

    @GetMapping("/home")
    public String getHome(Model model) {
        model.addAttribute("contents", "login/home :: home_contents");
        return "login/homeLayout";
    }

    @GetMapping("/userList")
    public String getUserList(Model model) {
        List<User> userList = userService.selectMany();
        int count = userService.count();
        model.addAttribute("contents", "login/userList :: userList_contents");
        model.addAttribute("userList", userList);
        model.addAttribute("userListCount", count);
        return "login/homeLayout";
    }

    @GetMapping("/userDetail/{id:.+}")
    public String getUserDetail(@ModelAttribute SignupForm form, Model model, @PathVariable("id") String userId) {
        System.out.println("userId : " + userId);
        model.addAttribute("contents", "login/userDetail :: userDetail_contents");
        radioMarriage = initRadioMarriage();
        model.addAttribute("radioMarriage", radioMarriage);
        if (userId != null && userId.length() > 0) {
            User user = userService.selectOne(userId);
            form.setUserId(user.getUserId());
            form.setPassword(user.getPassword());
            form.setUserName(user.getUserName());
            form.setAge(user.getAge());
            form.setBirthday(user.getBirthday());
            form.setMarriage(user.isMarriage());
        }
        model.addAttribute("signupForm", form);
        return "login/homeLayout";
    }

    @PostMapping(path = "/userDetail", params = "update")
    public String updateUserDetail(@ModelAttribute SignupForm form, Model model) {
        User user = new User();
        user.setUserId(form.getUserId());
        user.setUserName(form.getUserName());
        user.setPassword(form.getPassword());
        user.setBirthday(form.getBirthday());
        user.setAge(form.getAge());
        user.setMarriage(form.isMarriage());
        boolean result = userService.updateOne(user);
        String message = result ? "Updated" : "Failed to update";
        model.addAttribute("result", message);
        return getUserDetail(form, model, user.getUserId());
    }

    @PostMapping(path = "/userDetail", params = "delete")
    public String deleteUser(@ModelAttribute SignupForm form, Model model) {
        boolean result = userService.deleteOne(form.getUserId());
        String message = result ? "Deleted" : "Failed to delete";
        model.addAttribute("result", message);
        return getUserList(model);
    }

    @GetMapping("/userList/csv")
    public ResponseEntity<byte[]> getUserListCsv(Model model) {
        userService.outputCsv();
        byte[] bytes = null;
        try {
            bytes = userService.getFile("sample.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "text/csv; charset=UTF-8");
        httpHeaders.setContentDispositionFormData("filename", "sample.csv");
        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/admin")
    public String getAdmin(Model model) {
        model.addAttribute("contents", "login/admin :: admin_contents");
        return "login/homeLayout";
    }

    @PostMapping("/logout")
    public String postLogout() {
        return "redirect:/login";
    }
}
