package pro.appwork.open_university.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pro.appwork.open_university.model.dto.InviteDto;
import pro.appwork.open_university.model.dto.RegistrationDto;
import pro.appwork.open_university.model.entity.Group;
import pro.appwork.open_university.model.enums.UserRole;
import pro.appwork.open_university.service.GroupService;
import pro.appwork.open_university.service.RegistrationService;

import javax.annotation.security.PermitAll;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {
    private final RegistrationService registrationService;
    private final GroupService groupService;


    @GetMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN', 'TEACHER')")
    public String viewInvitePage(Model model) {
        model.addAttribute("inviteDto", new InviteDto());

        return "invite-page";
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN', 'TEACHER')")
    public String invite(@ModelAttribute InviteDto dto) {
        registrationService.sendInvite(
                dto.getEmail(),
                dto.getRole() == null ? UserRole.STUDENT : dto.getRole(),
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
    public List<UserRole> getAllRole() {
        return List.of(UserRole.STUDENT, UserRole.TEACHER);
    }
}
