package pro.appwork.open_university.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StudentDto {
    private String name;
    private String lastName;
    private String middleName;
    private String email;
    private String group;
    private String password;
    private String repeatPassword;
}
