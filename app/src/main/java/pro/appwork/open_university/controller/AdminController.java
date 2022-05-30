package pro.appwork.open_university.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pro.appwork.open_university.model.entity.Group;
import pro.appwork.open_university.service.GroupService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private final GroupService groupService;

    @GetMapping("/groups")
    public String viewGroupsPage(Model model) {
        model.addAttribute("groups", groupService.getAll());
        return "admin/groups-page";
    }

    @PostMapping("/groups/add")
    public String deleteGroup(@RequestParam String name) {
        Group group = Group.builder().name(name).build();
        groupService.addGroup(group);
        return "redirect:/admin/groups";
    }

    @PostMapping("/groups/delete/{id}")
    public String deleteGroup(@PathVariable Long id) {
        groupService.delete(id);
        return "redirect:/admin/groups";
    }
}
