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

    /** Employee詳細画面を表示 */
    @GetMapping(value = {"/detail","/detail/{code}/"})
    public String getEmployee(@PathVariable(name = "code", required = false) String code, Model model) {
        // codeが指定されていたら検索結果、無ければ空のクラスを設定
        Employee employee = code != null ? service.getEmployee(code) : new Employee();
        // Modelに登録
        model.addAttribute("employee", employee);
        // employee/detail.htmlに画面移動
        return "employee/detail";
    }

    /** Employee登録画面を表示 */
    @GetMapping("/register")
    public String getRegister(@ModelAttribute Employee employee) {
        // Employee登録画面に遷移
        return "employee/register";
    }

    /** Employee登録処理 */
    @PostMapping("/register")
    public String postRegister(@Validated Employee employee, BindingResult res, Model model) {
        if(res.hasErrors()) {
            // エラーあり
            return getRegister(employee);
        }
        // Employee登録
        service.saveEmployee(employee);
        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }

    /** Employee編集画面を表示 */
    @GetMapping("/update/{id}/")
    public String editEmployee(@PathVariable(name = "id", required = false) Integer id, Model model, Employee employee) {

        if (id != null) {
         // Modelに登録
            model.addAttribute("employee", service.getEmployee("id"));
        }else {

            model.addAttribute("employee", employee);
        }

        // Employee編集画面に遷移
        return "employee/update";
    }

    /** Employee編集処理 */
    @PostMapping("/update/{id}/")
    public String postEmployee(@Validated Employee employee, BindingResult res, Model model, Integer id) {
        if(res.hasErrors()) {
            // エラーあり

            return editEmployee(null, model, employee);
        }
        // Employee登録
        service.saveEmployee(employee);
        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }

    /** Employee削除処理 */
    @PostMapping(path="update", params="deleteRun")
    public String deleteRun(@RequestParam(name="idck") Set<String> idck, Model model) {
        // Employeeを一括削除
        service.deleteUser(idck);
        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }

}
