package com.techacademy.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techacademy.entity.Authentication;
import com.techacademy.entity.Employee;
import com.techacademy.repository.EmployeeRepository;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    //@Autowired
    //private PasswordEncoder passwordEncoder;

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
        //authentication.setPassword(passwordEncoder.encode(authentication.getPassword()));
        return employeeRepository.save(employee);
    }

    /** Employeeの編集を行う　*/
    @Transactional
    public Employee updateEmployee(Employee employee) {
        Authentication authentication = employee.getAuthentication();
        employee.setDeleteFlag(0);

        employee.setUpdatedAt(LocalDateTime.now());
        authentication.setEmployee(employee);
        //authentication.setPassword(passwordEncoder.encode(authentication.getPassword()));
        return employeeRepository.save(employee);
    }


    /** Employeeの削除を行う */
    @Transactional
    public Employee deleteEmployee(Employee employee) {
        Authentication authentication = employee.getAuthentication();
        employee.setDeleteFlag(1);
        employee.setCreatedAt(LocalDateTime.now());
        employee.setUpdatedAt(LocalDateTime.now());
        authentication.setEmployee(employee);
        //authentication.setPassword(passwordEncoder.encode(authentication.getPassword()));
        return employeeRepository.save(employee);
    }

}
