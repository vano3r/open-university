package pro.appwork.open_university.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GroupStatusEnum {
    ACTIVE("Активная группа"),
    ARCHIVE("В архиве");

    private final String description;
}
