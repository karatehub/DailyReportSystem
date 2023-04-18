package com.techacademy.entity;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

import lombok.Data;

@Data
@Entity
@Table(name = "employee")
public class Employee {

    /** 主キー。自動生成 */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 名前。20桁。null不許可 */
    @Column(length = 20, nullable = false)
    private String name;

    /** 削除フラグ **/
    private Integer delete_flag;
    /** 登録日時 **/
    private LocalDateTime created_at;
    /** 更新日時 **/
    private LocalDateTime updated_at;

    @OneToOne(mappedBy="employee")
    private Authentication authentication;

    @PreRemove
    @Transactional
    private void preRemove() {

        if (authentication!=null) {
            authentication.setEmployee(null);
        }
    }
}