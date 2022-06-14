package pro.appwork.open_university.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum Semester {
    FIRST("Первый курс", "Первый семестр"),
    SECOND("Первый курс", "Второй семестер"),
    THIRD("Второй курс", "Третий семестер"),
    FOURTH("Второй курс", "Четвертый семестер"),
    FIFTH("Третий курс", "Пятый семестер"),
    SIXTH("Третий курс", "Шестой семестер"),
    SEVENTH("Четвертый курс", "Седьмой семестер"),
    EIGHTH("Четвертый курс", "Восьмой семестер");

    private final String course;
    private final String description;

    public static List<Semester> getAll() {
        return List.of(Semester.FIRST,
                Semester.SECOND,
                Semester.THIRD,
                Semester.FOURTH,
                Semester.FIFTH,
                Semester.SIXTH,
                Semester.SEVENTH,
                Semester.EIGHTH);
    }
}