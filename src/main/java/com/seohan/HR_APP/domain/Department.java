package com.seohan.HR_APP.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.usertype.UserType;

import java.util.Date;

@Getter
@Entity
@Table(name = "HR_DEPARTMENT_TB")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class Department {

    @Id
    @Column(name = "department_change_id")
    private Long departmentChangeId; //auto import 아님,
    //바뀐날짜+채번

    @Column(name = "department_code")
    private String departmentCode; // 부서 코드

    @Column(name = "department_name")
    private String departmentName; // 부서명

    @Column(name = "department_english_name")
    private String departmentEnglishName; // 부서 영어명

    @Column(name = "change_date")
    private Date changeDate;// 개편날짜(생성날짜)

    @Column(name = "upper_department_code")
    private String upperDepartmentCode; // 상위부서 코드

    @Column(name = "is_leaf_node")
    private String isLeafNode; // 상위부서 코드
    @Builder
    public Department(Long departmentChangeId, String departmentCode, String departmentName, String departmentEnglishName, Date changeDate, String upperDepartmentCode, String isLeafNode) {
        this.departmentChangeId = departmentChangeId;
        this.departmentCode = departmentCode;
        this.departmentName = departmentName;
        this.departmentEnglishName = departmentEnglishName;
        this.changeDate = changeDate;
        this.upperDepartmentCode = upperDepartmentCode;
        this.isLeafNode = isLeafNode;
    }

    //부서 개편기능은 만들지 않음
}
