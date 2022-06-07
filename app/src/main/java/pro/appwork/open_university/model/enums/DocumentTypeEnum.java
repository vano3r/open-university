package pro.appwork.open_university.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DocumentTypeEnum {
    BACHELOR("Бакалавры"),
    MASTER("Магистры"),
    POSTGRADUATE("Аспиранты"),
    GENERAL("Общие"),
    PPC("ППС");

    private final String description;

    public static DocumentTypeEnum lowerCaseOf(String lowerCaseName) {
        return DocumentTypeEnum.valueOf(lowerCaseName.toUpperCase());
    }

    public String getLowerCaseName() {
        return name().toLowerCase();
    }
}
