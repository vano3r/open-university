package pro.appwork.open_university.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String viewLoginPage() {
        return "login-page";
    }
}