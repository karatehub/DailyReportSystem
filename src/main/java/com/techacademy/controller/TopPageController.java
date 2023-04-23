package com.techacademy.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.entity.Employee;
import com.techacademy.service.ReportService;
import com.techacademy.service.UserDetail;

@Controller
public class TopPageController {
    private final ReportService service;

    public TopPageController(ReportService service) {
        this.service = service;
    }
    /** ログイン画面を表示 */
    @GetMapping("/")
    public String getToppage(Model model, Employee employee, @AuthenticationPrincipal UserDetail userDetail) {
     // 全件検索結果をModelに登録
        employee = userDetail.getUser();
        model.addAttribute("reportlist", service.getEmployeeReportList(employee));
        // toppage.htmlに画面遷移
        return "toppage";
    }
}