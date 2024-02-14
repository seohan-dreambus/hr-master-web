package com.seohan.HR_APP.controller.APIcontroller;

import com.seohan.HR_APP.SessionConst;
import com.seohan.HR_APP.domain.Employee;
import com.seohan.HR_APP.dto.LoginRequestDTO;
import com.seohan.HR_APP.dto.ResponseDTO;
import com.seohan.HR_APP.service.EmployeeService;
import jakarta.security.auth.message.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class AuthController {

    private final EmployeeService employeeService;
    @PostMapping("/api/login")
    public ResponseDTO<?> login(@RequestBody LoginRequestDTO loginDTO, HttpServletRequest req) throws AuthException {
        // 사용자 인증 수행
        employeeService.login(loginDTO);

        // 성공 시 세션에 사용자 정보 저장
        HttpSession session = req.getSession();
        session.setAttribute(SessionConst.LOGIN_EMPLOYEE, loginDTO.getCompanyId());
        return new ResponseDTO<>(null, "로그인 성공");
    }

    @PostMapping("/api/logout")
    public ResponseDTO<?> logout(HttpServletRequest req) {
        // 세션 제거하여 로그아웃
        HttpSession session = req.getSession();
        session.invalidate();
        return new ResponseDTO<>(null, "로그아웃 성공");
    }
}

