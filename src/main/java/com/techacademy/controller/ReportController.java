package com.techacademy.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.entity.Employee;
import com.techacademy.entity.Report;
import com.techacademy.service.ReportService;
import com.techacademy.service.UserDetail;

@Controller
@RequestMapping("report")
public class ReportController {
    private final ReportService service;

    public ReportController(ReportService service) {
        this.service = service;
    }

    /** Report一覧画面を表示 */
    @GetMapping("/list")
    public String getList(Model model) {
        // 全件検索結果をModelに登録
        model.addAttribute("reportlist", service.getReportList());
        // employee/list.htmlに画面遷移
        return "report/list";
    }

    /** Report登録画面を表示 */
    @GetMapping("/register")
    public String getRegister(@ModelAttribute Report report,@AuthenticationPrincipal UserDetail userDetail) {
        report.setEmployee(userDetail.getUser());
        // Report登録画面に遷移
        return "report/register";
    }

    /** Report登録処理 */
    @PostMapping("/register")
    public String postRegister(Report report,@AuthenticationPrincipal UserDetail userDetail) {
        report.setEmployee(userDetail.getUser());
        // Report登録
         service.saveReport(report);
        // 一覧画面にリダイレクト
        return "redirect:/report/list";
    }

    /** Report詳細画面表示 */
    @GetMapping("/detail/{id}")
    public String getReport(@PathVariable Integer id, Model model, Report report, @AuthenticationPrincipal UserDetail userDetail) {
        report.setEmployee(userDetail.getUser());
        Report report2 = service.getReport(id);
        // Modelに登録
        model.addAttribute("report", report);
        model.addAttribute("report2", report2);
        // report/detail.htmlに画面遷移
        return "report/detail";
    }

    /** Report編集画面を表示 */
    @GetMapping("/update/{id}")
    public String reportUser(@PathVariable Integer id, Model model) {
        // Modelに登録
        model.addAttribute("report", service.getReport(id));
        // Report編集画面に遷移
        return "report/update";
    }

    /** Report編集処理 */
    @PostMapping("/update/{id}")
    public String postReport(Report report) {
        // Report編集
        service.updateReport(report);

        // 一覧画面にリダイレクト
        return "redirect:/report/list";
    }



}
