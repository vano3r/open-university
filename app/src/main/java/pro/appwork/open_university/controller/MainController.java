package pro.appwork.open_university.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.appwork.open_university.model.entity.CustomUser;
import pro.appwork.open_university.model.enums.UserRole;
import pro.appwork.open_university.security.CustomUserDetails;

@Controller
@RequestMapping("/")
public class MainController {
    @GetMapping
    public String viewMainPage(Authentication authentication) {
        CustomUser user = ((CustomUserDetails) authentication.getPrincipal()).user();
        if (user.getRole().equals(UserRole.STUDENT)) {
            return "redirect:/student";
        } else {
            return "redirect:/teacher/groups";
        }
    }
}
