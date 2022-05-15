package pro.appwork.open_university.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pro.appwork.open_university.security.CustomUserDetails;

@Controller
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/")
    public String viewIndexPage(Model model) {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal()
                instanceof CustomUserDetails userDetails) {
            model.addAttribute("user", userDetails.user());
        }
        return "index-page";
    }
}
