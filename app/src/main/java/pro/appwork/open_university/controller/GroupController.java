package pro.appwork.open_university.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pro.appwork.open_university.service.GroupService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/groups")
@PreAuthorize("hasAuthority('ADMIN')")
public class GroupController {
    private final GroupService groupService;

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("groupList", groupService.getAll());

        return "group-list";

    }

    @PostMapping("/create")
    public String create(HttpServletRequest request,
                         @RequestParam String name) {

        String referer = Optional.of(request.getHeader("Referer"))
                .orElse("/");

        groupService.createByName(name);

        return "redirect:" + referer;
    }

    @PostMapping("/delete/{id}")
    public String delete(HttpServletRequest request,
                         @PathVariable Long id) {

        String referer = Optional.of(request.getHeader("Referer"))
                .orElse("/");

        groupService.deleteById(id);

        return "redirect:" + referer;
    }

    @PostMapping("/update/{id}")
    public String update(HttpServletRequest request,
                         @PathVariable Long id,
                         @RequestParam String name) {

        String referer = Optional.of(request.getHeader("Referer"))
                .orElse("/");

        groupService.updateById(id, name);

        return "redirect:" + referer;
    }
}
