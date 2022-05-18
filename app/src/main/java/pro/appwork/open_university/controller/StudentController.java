package pro.appwork.open_university.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pro.appwork.open_university.service.EmailTokenService;
import pro.appwork.open_university.service.MailService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {
    private final MailService mailService;

    @GetMapping("")
    public String viewAddNewStudentPage() {
        return "add-new-student-page";
    }

    @PostMapping
    public String addNewStudent(@RequestParam String email) {
        mailService.send(email);
        return "add-new-student-page";
    }



    @GetMapping("/subjects")
    public String viewStudentSubjectsPage(Model model){
        return "student-subjects-page";
    }
}
