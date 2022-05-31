package pro.appwork.open_university.model.dto;

import lombok.Data;
import pro.appwork.open_university.model.entity.Group;
import pro.appwork.open_university.model.enums.RoleEnum;

@Data
public class RegistrationDto {
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private RoleEnum role;
    private Group group;
    private String password;
    private String repeatPassword;
}
