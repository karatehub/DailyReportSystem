package com.techacademy.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techacademy.entity.Authentication;
import com.techacademy.entity.Employee;
import com.techacademy.repository.EmployeeRepository;

@Service

public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public EmployeeService(EmployeeRepository repository) {
        this.employeeRepository = repository;
    }

    /** 全件を検索して返す */
    public List<Employee> getEmployeeList() {
        // リポジトリのfindAllメソッドを呼び出す
        return employeeRepository.findAll();
    }

    /** Employeeを１件検索して返す */
    public Employee getEmployee(Integer id) {
        return employeeRepository.findById(id).get();
    }

    /** Employeeの登録を行う　*/
    @Transactional
    public Employee saveEmployee(Employee employee) {
        Authentication authentication = employee.getAuthentication();
        employee.setDeleteFlag(0);
        employee.setCreatedAt(LocalDateTime.now());

        authentication.setEmployee(employee);
        authentication.setPassword(passwordEncoder.encode(authentication.getPassword()));
        return employeeRepository.save(employee);
    }

    /** Employeeの編集を行う　*/
    @Transactional
    public Employee updateEmployee(Employee employee) {
        Employee updateemployee = employeeRepository.findById(employee.getId()).get();
        Authentication authentication = employee.getAuthentication();
        updateemployee.setName(employee.getName());
        updateemployee.setUpdatedAt(LocalDateTime.now());
        authentication.setPassword(employee.getAuthentication().getPassword());
        authentication.setRole(employee.getAuthentication().getRole());

        authentication.setPassword(passwordEncoder.encode(authentication.getPassword()));
        return employeeRepository.save(updateemployee);
    }


    /** Employeeの削除を行う */
    @Transactional
    public Employee deleteEmployee(Employee employee) {
        Employee deleteemployee = employeeRepository.findById(employee.getId()).get();
        deleteemployee.setDeleteFlag(1);
        deleteemployee.setUpdatedAt(LocalDateTime.now());
        //authentication.setPassword(passwordEncoder.encode(authentication.getPassword()));
        return employeeRepository.save(deleteemployee);
    }

}
