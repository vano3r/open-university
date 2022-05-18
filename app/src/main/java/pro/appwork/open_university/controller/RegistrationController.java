package pro.appwork.open_university.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pro.appwork.open_university.model.dto.StudentDto;
import pro.appwork.open_university.model.entity.CustomUser;
import pro.appwork.open_university.model.enums.UserRole;
import pro.appwork.open_university.model.enums.UserState;
import pro.appwork.open_university.service.EmailTokenService;
import pro.appwork.open_university.service.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {
    private final EmailTokenService emailTokenService;
    private final UserService userService;

    @GetMapping("/student/{token}")
    public String studentRegistrationPage(@PathVariable String token, Model model) {
        if ("error".equals(token)) {
            model.addAttribute("error", "Ссылка недействительна!");
            return "student-registration-error-page";
        }
        List<String> groups = List.of("ИВТ-15.04", "М.ПИН.РИС-19.04");
        StudentDto student = new StudentDto();
        String email = emailTokenService.getByToken(token).getEmail();
        student.setEmail(email);
        model.addAttribute("student", student);
        model.addAttribute("groups", groups);

        return "student-registration-page";
    }

    @PostMapping("/student")
    public String studentRegistration(@ModelAttribute StudentDto student) {

        CustomUser newUser = new CustomUser(
                null,
                student.getFirstName(),
                student.getLastName(),
                student.getMiddleName(),
                student.getEmail(),
                student.getPassword(),
                UserRole.STUDENT,
                UserState.ACTIVE,
                null,
                null,
                null
        );
        userService.createNew(newUser);

        return "login-page";
    }
}
