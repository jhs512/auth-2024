package com.ll.auth2024.domain.home.home.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Tag(name = "HomeController", description = "홈 컨트롤러")
public class HomeController {
    @GetMapping("/")
    @ResponseBody
    public String showMain() {
        return "백엔드 서버가 정상 작동 중입니다.";
    }
}
