//package com.seohan.HR_APP.controller.APIcontroller;
//
//import com.seohan.HR_APP.dto.LoginRequestDTO;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//
//@RestController
//public class AuthController {
//
//    @PostMapping("/api/login")
//    public String login(@RequestBody LoginRequestDTO loginReq, HttpServletRequest req) {
//        // 사용자 인증 수행, 성공 시 세션에 사용자 정보 저장
//        HttpSession session = req.getSession();
//        session.setAttribute("loginCompanyCode", loginReq.getCompanyCode());
//        return "로그인 성공";
//    }
//
//    @PostMapping("/api/logout")
//    public String logout(HttpServletRequest req) {
//        // 세션 제거하여 로그아웃
//        HttpSession session = req.getSession();
//        session.invalidate();
//        return "로그아웃 성공";
//    }
//}
//
