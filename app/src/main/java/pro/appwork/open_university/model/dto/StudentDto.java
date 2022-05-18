package pro.appwork.open_university.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pro.appwork.open_university.model.entity.Group;

@Getter
@Setter
@ToString
public class StudentDto {
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private Group group;
    private String password;
    private String repeatPassword;
}
