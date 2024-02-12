package com.seohan.HR_APP.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Entity
@Table(name = "HR_DEPARTMENT_TB")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class Department {

    @EmbeddedId
    private DepartmentId id; //복합 키

    @Column(name = "department_name")
    private String departmentName; // 부서명

    @Column(name = "department_english_name")
    private String departmentEnglishName; // 부서 영어명

    @Column(name = "change_date")
    private Date changeDate;// 개편날짜(생성날짜)

    /** 상위 부서 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "upper_department_change_id"),
            @JoinColumn(name = "upper_department_code")
    })
    private Department upperDepartment;

    /** 하위 부서 리스트 */
    @OneToMany(mappedBy = "upperDepartment")
    private List<Department> subDepartmentList = new ArrayList<>();

    @Column(name = "is_leaf_node")
    private Boolean isLeafNode; // 리프노드 유무

    // Employee 엔티티와의 관계 매핑
    @OneToMany(mappedBy = "department")
    private List<Employee> employees = new ArrayList<>();

    // 복합 키 클래스
    @Embeddable
    @NoArgsConstructor
    @Getter
    public static class DepartmentId implements Serializable {

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "department_change_id")
        private String departmentChangeId; //날짜+채번 202402110001

        @Column(name = "department_code")
        private String departmentCode;
        public DepartmentId(String departmentChangeId, String departmentCode) {
            this.departmentChangeId = departmentChangeId;
            this.departmentCode = departmentCode;
        }
    }

    public Department(DepartmentId departmentId, String departmentName, String departmentEnglishName, Date changeDate, Department upperDepartment, Boolean isLeafNode) {
        this.id = departmentId;
        this.departmentName = departmentName;
        this.departmentEnglishName = departmentEnglishName;
        this.changeDate = changeDate;
        this.upperDepartment = upperDepartment;
        this.isLeafNode = isLeafNode;
    }

    //부서 기능은 만들지 않음
}
