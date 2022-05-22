package pro.appwork.open_university.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pro.appwork.open_university.model.dto.InviteDto;
import pro.appwork.open_university.model.dto.RegistrationDto;
import pro.appwork.open_university.model.entity.Group;
import pro.appwork.open_university.model.enums.UserRole;
import pro.appwork.open_university.service.GroupService;
import pro.appwork.open_university.service.RegistrationService;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {
    private final RegistrationService registrationService;
    private final GroupService groupService;


    @GetMapping
    public String viewInvitePage(Model model) {
        model.addAttribute("inviteDto", new InviteDto());

        return "invite-page";
    }


    @GetMapping("/{token}")
    public String viewRegistrationPage(@PathVariable String token, Model model) {
        if (!registrationService.isValidToken(token)) {
            return "redirect:/login";
        }

        model.addAllAttributes(Map.of(
                "registrationDto", new RegistrationDto(),
                "token", token
        ));

        return "registration-page";
    }

    @PostMapping
    public String invite(@ModelAttribute InviteDto dto) {
        String token = registrationService.generateToken(dto.getEmail(), dto.getRole(), dto.getGroup());
        System.out.println(token);

        return "redirect:/registration";
    }


    @PostMapping("/{token}")
    public String registration(@PathVariable String token, @ModelAttribute RegistrationDto dto) {

        return "redirect:/login";
    }

    @ModelAttribute("allGroup")
    public List<Group> getAllGroup() {
        return groupService.getAll();
    }

    @ModelAttribute("allRole")
    public List<UserRole> getAllRole() {
        return List.of(UserRole.STUDENT, UserRole.TEACHER);
    }
}
