package com.techacademy.entity;


import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.validation.Valid;

import org.hibernate.annotations.Where;
import org.springframework.transaction.annotation.Transactional;

import lombok.Data;

@Data
@Entity
@Table(name = "employee")
@Where(clause = "delete_flag = 0")
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
    private Integer deleteFlag;
    /** 登録日時 **/
    private LocalDateTime createdAt;
    /** 更新日時 **/
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    @Valid
    private Authentication authentication;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Report> reports;

    @PreRemove
    @Transactional
    private void preRemove() {

        if (authentication!=null) {
            authentication.setEmployee(null);
        }
    }
}