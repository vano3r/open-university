package pro.appwork.open_university.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pro.appwork.open_university.model.dto.InviteDto;
import pro.appwork.open_university.model.dto.RegistrationDto;
import pro.appwork.open_university.model.entity.Group;
import pro.appwork.open_university.model.enums.RoleEnum;
import pro.appwork.open_university.security.annotation.IsTeacher;
import pro.appwork.open_university.service.GroupService;
import pro.appwork.open_university.service.RegistrationService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {
    private final RegistrationService registrationService;
    private final GroupService groupService;


    @IsTeacher
    @GetMapping
    public String viewInvitePage(Model model) {
        model.addAttribute("inviteDto", new InviteDto());

        return "invite-page";
    }

    @IsTeacher
    @PostMapping
    public String invite(HttpServletRequest request,
                         @ModelAttribute InviteDto dto) {
        registrationService.sendInvite(
                request,
                dto.getEmail(),
                dto.getRole() == null ? RoleEnum.STUDENT : dto.getRole(),
                dto.getGroup()
        );

        return "redirect:/registration";
    }

    @GetMapping("/{token}")
    public String viewRegistrationPage(@PathVariable String token, Model model) {
        var validToken = registrationService.getValidToken(token);
        if (validToken.isEmpty()) {
            return "redirect:/login";
        }

        model.addAttribute("registrationDto", new RegistrationDto());
        model.addAttribute("validToken", validToken.get());

        return "registration-page";
    }

    @PostMapping("/{token}")
    public String registration(@ModelAttribute RegistrationDto dto, @PathVariable String token) {
        var validToken = registrationService.getValidToken(token);
        if (validToken.isEmpty()) {
            return "redirect:/login";
        }

        registrationService.registrationUser(dto);

        return "redirect:/login";
    }

    @ModelAttribute("allGroup")
    public List<Group> getAllGroup() {
        return groupService.getAll();
    }

    @ModelAttribute("allRole")
    public List<RoleEnum> getAllRole() {
        return List.of(RoleEnum.STUDENT, RoleEnum.TEACHER);
    }
}
