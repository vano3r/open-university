package pro.appwork.open_university.model.enums;

public enum RoleEnum {
    STUDENT("Студент"),
    TEACHER("Преподаватель"),
    ADMIN("Администратор");

    private final String description;

    RoleEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
