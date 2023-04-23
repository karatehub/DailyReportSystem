package com.techacademy.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techacademy.entity.Authentication;
import com.techacademy.entity.Employee;
import com.techacademy.entity.Report;
import com.techacademy.repository.ReportRepository;

@Service
public class ReportService {
    private final ReportRepository reportRepository;

    public ReportService(ReportRepository repository) {
        this.reportRepository = repository;
    }

    /** ログインした従業員のみ返す */
    public List<Report> getEmployeeReportList(Employee employee) {
        // リポジトリのfindAllメソッドを呼び出す
        return reportRepository.findByEmployee(employee);
    }

    /** 全件を検索して返す */
    public List<Report> getReportList() {
        // リポジトリのfindAllメソッドを呼び出す
        return reportRepository.findAll();
    }

    /** Reportを１件検索して返す */
    public Report getReport(Integer id) {
        return reportRepository.findById(id).get();
    }

    /** Reportの登録を行う　*/
    @Transactional
    public Report saveReport(Report report) {
        Employee employee = report.getEmployee();
        report.setCreatedAt(LocalDateTime.now());

        employee.setDeleteFlag(0);

        return reportRepository.save(report);
    }

    /** Reportの編集を行う　*/
    @Transactional
    public Report updateReport(Report report) {
        Report updatereport = reportRepository.findById(report.getId()).get();
        updatereport.setReportDate(report.getReportDate());
        updatereport.setTitle(report.getTitle());
        updatereport.setContent(report.getContent());
        updatereport.setUpdatedAt(LocalDateTime.now());

        return reportRepository.save(updatereport);
    }
}
