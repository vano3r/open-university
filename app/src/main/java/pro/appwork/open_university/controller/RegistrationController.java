package pro.appwork.open_university.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pro.appwork.open_university.model.dto.StudentDto;
import pro.appwork.open_university.model.entity.RegistrationToken;
import pro.appwork.open_university.service.RegistrationTokenRepository;
import pro.appwork.open_university.service.GroupService;
import pro.appwork.open_university.service.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {
    private final RegistrationTokenRepository emailTokenService;
    private final UserService userService;
    private final GroupService groupService;

    @GetMapping("/student/{token}")
    public String studentRegistrationPage(@PathVariable String token, Model model) {
        RegistrationToken emailToken = emailTokenService.getByToken(token);

        StudentDto student = new StudentDto();
        student.setEmail(emailToken.getEmail());
        student.setGroup(groupService.getGroupById(emailToken.getGroup().getId()));

        model.addAttribute("token", emailToken.getToken());
        model.addAttribute("student", student);

        return "student-registration-page";
    }

    @PostMapping("/student")
    public String studentRegistration(@ModelAttribute StudentDto student, @RequestParam("token") String token) {

//        CustomUser newUser = new CustomUser(
//                null,
//                student.getFirstName(),
//                student.getLastName(),
//                student.getMiddleName(),
//                student.getEmail(),
//                student.getPassword(),
//                UserRole.STUDENT,
//                UserState.ACTIVE,
//                student.getGroup(),
//                null,
//                null
//        );

//        userService.createNew(newUser);

        emailTokenService.removeToken(token);

        return "login-page";
    }
}
