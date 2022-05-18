package pro.appwork.open_university.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pro.appwork.open_university.service.GroupService;
import pro.appwork.open_university.service.MailService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {
    private final MailService mailService;
    private final GroupService groupService;

    @GetMapping()
    public String viewAddNewStudentPage(Model model) {
        model.addAttribute("groups", groupService.findAll());
        return "add-new-student-page";
    }

    @PostMapping
    public String addNewStudent(@RequestParam String email, @RequestParam Long groupId, Model model) {
        mailService.send(email, groupId);
        model.addAttribute("groups", groupService.findAll());
        return "add-new-student-page";
    }
}
