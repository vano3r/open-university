package pro.appwork.open_university.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Semester {
    FIRST(1, "Первый семестр"),
    SECOND(1, "Второй семестер"),
    THIRD(2, "Третий семестер"),
    FOURTH(2, "Четвертый семестер"),
    FIFTH(3, "Пятый семестер"),
    SIXTH(3, "Шестой семестер"),
    SEVENTH(4, "Седьмой семестер"),
    EIGHTH(4, "Восьмой семестер");

    private final Integer course;
    private final String description;
}