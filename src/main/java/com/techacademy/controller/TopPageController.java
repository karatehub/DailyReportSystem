package com.techacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TopPageController {
    /** ログイン画面を表示 */
    @GetMapping("/")
    public String getToppage() {
        // toppage.htmlに画面遷移
        return "toppage";
    }
}