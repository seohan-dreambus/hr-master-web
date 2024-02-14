package com.seohan.HR_APP.repository;

import ch.qos.logback.classic.encoder.JsonEncoder;
import com.seohan.HR_APP.domain.enumType.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JdbcTemplateDepartmentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    public Long getNextSequenceValue(String sequenceName) {
//        // 네이티브 쿼리를 사용하여 시퀀스 값을 가져옵니다.
//        return jdbcTemplate.queryForObject("SELECT NEXTVAL(" + sequenceName + ")", Long.class);
//    }
    public void insertEmployee(String companyId, String encodedPassword, String name, String englishName, String personalNumber, String phoneNumber, boolean isMilitary, NationalityType nationality, String homeAddress, String detailAddress, EmploymentType employmentType, Date joiningDate, ResignationType resignationType, WorkLocationType workLocation, PositionType position, PositionLankType positionLank, ShiftType shift){
        String sql = "INSERT INTO hr_db.hr_info_tb" +
                "(companyId, password, name, english_name, personal_number, phone_number, is_military, nationality, home_address, detail_address, employment_type, joining_date, resignation_type, work_location, position, position_rank, shift)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                companyId,
                encodedPassword,
                name,
                englishName,
                personalNumber,
                phoneNumber,
                isMilitary,
                nationality,
                homeAddress,
                detailAddress,
                employmentType,
                joiningDate,
                resignationType,
                workLocation,
                position,
                positionLank,
                shift);
    }


    public void insertUpperDepartment(String departmentChangeId, String departmentCode, String englishName, String departmentName){
        String sql = "INSERT INTO hr_db.hr_department_tb" +
                "(is_leaf_node, change_date, department_change_id, department_code, department_english_name, department_name, upper_department_change_id, upper_department_code)\n" +
                "VALUES(false, NOW(), ?, ? , ?, ?, NULL, NULL);";
        jdbcTemplate.update(sql, departmentChangeId, departmentCode, englishName, departmentName);
    }

    public void insertSubDepartment(String upperId, String upperCode, String departmentChangeId, String departmentCode, String englishName, String departmentName){
        String sql = "INSERT INTO hr_db.hr_department_tb" +
                "(is_leaf_node, change_date, department_change_id, department_code, department_english_name, department_name, upper_department_change_id, upper_department_code)\n" +
                "VALUES(true, NOW(), ?, ? , ?, ?, ?, ?);";
        jdbcTemplate.update(sql, departmentChangeId, departmentCode, englishName, departmentName, upperId, upperCode);
    }
}
