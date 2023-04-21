package com.techacademy.controller;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techacademy.entity.Employee;
import com.techacademy.service.EmployeeService;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    /** Employee一覧画面を表示 */
    @GetMapping("/list")
    public String getList(Model model) {
        // 全件検索結果をModelに登録
        model.addAttribute("employeelist", service.getEmployeeList());
        // employee/list.htmlに画面遷移
        return "employee/list";
    }

      /** Employee登録画面を表示 */
    @GetMapping("/register")
    public String getRegister(@ModelAttribute Employee employee) {
        // Employee登録画面に遷移
        return "employee/register";
    }

    /** Employee登録処理 */
    @PostMapping("/register")
    public String postRegister(Employee employee) {

        // Employee登録
         service.saveEmployee(employee);
        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }

    /** Employee詳細画面表示 */
    @GetMapping("/detail/{code}")
    public String getEmployee(@PathVariable Integer code, Model model) {

        Employee employee = service.getEmployee(code);
        // Modelに登録
        model.addAttribute("employee", employee);
        // country/detail.htmlに画面遷移
        return "employee/detail";
    }

    /** Employee編集画面を表示 */
    @GetMapping("/update/{id}")
    public String employeeUser(@PathVariable Integer id, Model model) {
        // Modelに登録
        model.addAttribute("employee", service.getEmployee(id));
        // Employee編集画面に遷移
        return "employee/update";
    }

    /** Employee編集処理 */
    @PostMapping("/update/{id}")
    public String postEmployee(Employee employee) {
        // Employee登録
        service.saveEmployee(employee);

        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }

    /** Employee削除処理 */
    @GetMapping("/delete/{id}")
    public String deleteRun(Employee employee) {
        // Employeeを削除
        service.deleteEmployee(employee);

        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }
}
