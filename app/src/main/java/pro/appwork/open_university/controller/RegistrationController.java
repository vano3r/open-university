package pro.appwork.open_university.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pro.appwork.open_university.model.dto.StudentDto;
import pro.appwork.open_university.model.entity.CustomUser;
import pro.appwork.open_university.model.entity.EmailToken;
import pro.appwork.open_university.model.enums.UserRole;
import pro.appwork.open_university.model.enums.UserState;
import pro.appwork.open_university.service.EmailTokenService;
import pro.appwork.open_university.service.GroupService;
import pro.appwork.open_university.service.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {
    private final EmailTokenService emailTokenService;
    private final UserService userService;
    private final GroupService groupService;

    @GetMapping("/student/{token}")
    public String studentRegistrationPage(@PathVariable String token, Model model) {
        EmailToken emailToken = emailTokenService.getByToken(token);

        StudentDto student = new StudentDto();
        student.email(emailToken.getEmail());
        student.group(groupService.getGroupById(emailToken.getGroupId()).name());

        model.addAttribute("student", student);

        return "student-registration-page";
    }

    @PostMapping("/student")
    public String studentRegistration(@ModelAttribute StudentDto student) {

        CustomUser newUser = new CustomUser(
                null,
                student.firstName(),
                student.lastName(),
                student.middleName(),
                student.email(),
                student.password(),
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
