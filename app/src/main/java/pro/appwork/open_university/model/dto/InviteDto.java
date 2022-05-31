package pro.appwork.open_university.model.dto;

import lombok.Data;
import pro.appwork.open_university.model.entity.Group;
import pro.appwork.open_university.model.enums.RoleEnum;

@Data
public class InviteDto {
    private String email;
    private RoleEnum role;
    private Group group;
}
