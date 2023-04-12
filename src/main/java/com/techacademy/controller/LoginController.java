package com.techacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    /** ログイン画面を表示 */
    @RequestMapping("employee")
    @GetMapping("/login")
    public String getLogin() {
        // login.htmlに画面遷移
        return "login";
    }
}