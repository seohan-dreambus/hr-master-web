package com.seohan.HR_APP.controller.WEBController;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class WebController {
    /** 로그인 화면 html 연결 */
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/")
    public String firstPage(){
        return "first";
    }

    @GetMapping("/main")
    public String mainPage() { return "main"; }
}
