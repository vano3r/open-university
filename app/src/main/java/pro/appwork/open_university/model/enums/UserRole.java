package pro.appwork.open_university.model.enums;

public enum UserRole {
    STUDENT("Студент"),
    TEACHER("Преподаватель"),
    ADMIN("Администратор");

    private final String description;

    UserRole(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
