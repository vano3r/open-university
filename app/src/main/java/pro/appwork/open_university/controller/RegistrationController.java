package pro.appwork.open_university.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pro.appwork.open_university.model.dto.StudentDto;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    @GetMapping("/student-registration/{token}")
    public String studentRegistrationPage(@PathVariable String token, Model model) {
        if ("error".equals(token)) {
            model.addAttribute("error", "Ссылка недействительна!");
            return "student-registration-error-page";
        }

        List<String> groups = List.of("ИВТ-15.04", "М.ПИН.РИС-19.04");
        StudentDto student = new StudentDto();
        student.setEmail("test@example.com");
        model.addAttribute("student", student);
        model.addAttribute("groups", groups);

        return "student-registration-page";
    }

    @PostMapping("/student-registration")
    public String studentRegistration(@ModelAttribute StudentDto student) {

        System.out.println(student);

        return "login-page";
    }
}
