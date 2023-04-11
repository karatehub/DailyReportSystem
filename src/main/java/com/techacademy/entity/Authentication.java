package com.techacademy.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Authentication")
public class Authentication {

    public static enum role{
        一般, 管理者
    }

    /** 社員番号 **/
    @Id
    @Column(length = 20, nullable = false)
    private String code;


    /** パスワード **/
    @Column(length = 255)
    private String password;

    /** 権限 **/
    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private String role;

    /** 従業員のテーブルのID **/
    @Column(name = "table_id")
    @JoinColumn(name = "table_id", referencedColumnName = "id")
    private Integer employee_id;

}