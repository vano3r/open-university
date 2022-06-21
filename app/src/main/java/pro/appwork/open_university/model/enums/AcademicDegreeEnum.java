package pro.appwork.open_university.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AcademicDegreeEnum {
    BACHELOR("Бакалавриат", 4),
    MASTER("Магистратура", 2);

    private final String description;
    private final Integer numberYears;
}
