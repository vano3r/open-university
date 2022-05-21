package pro.appwork.open_university;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import pro.appwork.open_university.model.entity.*;
import pro.appwork.open_university.model.enums.UserRole;
import pro.appwork.open_university.model.enums.UserState;
import pro.appwork.open_university.repository.*;

import java.util.List;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public CommandLineRunner CommandLineRunnerBean(StudentRepository studentRepository,
                                                   GroupRepository groupRepository,
                                                   PasswordEncoder passwordEncoder,
                                                   TeacherRepository teacherRepository,
                                                   CourseRepository courseRepository,
                                                   CourseGroupRepository courseGroupRepository,
                                                   LessonRepository lessonRepository,
                                                   TaskRepository taskRepository,
                                                   TaskLessonRepository taskLessonRepository,
                                                   SolutionRepository solutionRepository) {
        return (args) -> {

            Course course1 = Course.builder().stage(1).build();
            Course course2 = Course.builder().stage(2).build();
            Course course3 = Course.builder().stage(3).build();
            Course course4 = Course.builder().stage(4).build();

            courseRepository.saveAll(List.of(course1, course2, course3, course4));

            Group group1 = Group.builder().name("ИВТ-1504").build();
            Group group2 = Group.builder().name("М.ПИН.РИС-1904").build();

            groupRepository.saveAll(List.of(group1, group2));

            CourseGroup courseGroup1 = CourseGroup.builder()
                    .course(course1)
                    .group(group1)
                    .semester(1)
                    .build();

            CourseGroup courseGroup2 = CourseGroup.builder()
                    .course(course2)
                    .group(group1)
                    .semester(3)
                    .build();

            CourseGroup courseGroup3 = CourseGroup.builder()
                    .course(course1)
                    .group(group2)
                    .semester(1)
                    .build();

            courseGroupRepository.saveAll(List.of(courseGroup1, courseGroup2, courseGroup3));

            Teacher teacher = Teacher.builder()
                    .firstName("Сергей")
                    .lastName("Кузнецов")
                    .middleName("Евгеньевич")
                    .email("teacher@mail.ru")
                    .password(passwordEncoder.encode("teacher"))
                    .role(UserRole.TEACHER)
                    .state(UserState.ACTIVE)
                    .build();

            teacherRepository.save(teacher);

            Lesson lesson1 = Lesson.builder().course(courseGroup1).name("ИАТ").teacher(teacher).build();
            Lesson lesson2 = Lesson.builder().course(courseGroup2).name("ИИС").teacher(teacher).build();
            Lesson lesson3 = Lesson.builder().course(courseGroup3).name("ТПР").teacher(teacher).build();

            lessonRepository.saveAll(List.of(lesson1, lesson2, lesson3));


            Task task1 = Task.builder().name("ВКР").build();
            Task task2 = Task.builder().name("Лабораторная работа").build();
            Task task3 = Task.builder().name("КР").build();

            taskRepository.saveAll(List.of(task1, task2, task3));

            TaskLesson taskLesson1 = TaskLesson.builder().task(task1).lesson(lesson1).filePath("file").build();
            TaskLesson taskLesson2 = TaskLesson.builder().task(task2).lesson(lesson1).filePath("file").build();
            TaskLesson taskLesson3 = TaskLesson.builder().task(task3).lesson(lesson1).filePath("file").build();
            TaskLesson taskLesson4 = TaskLesson.builder().task(task2).lesson(lesson2).filePath("file").build();
            TaskLesson taskLesson5 = TaskLesson.builder().task(task3).lesson(lesson3).filePath("file").build();
            TaskLesson taskLesson6 = TaskLesson.builder().task(task2).lesson(lesson3).filePath("file").build();

            taskLessonRepository.saveAll(
                    List.of(taskLesson1, taskLesson2, taskLesson3, taskLesson4, taskLesson5, taskLesson6)
            );

            Student student1 = Student.builder()
                    .firstName("Иван")
                    .lastName("Иванов")
                    .middleName("Иванович")
                    .email("student1@mail.ru")
                    .group(group1)
                    .password(passwordEncoder.encode("student"))
                    .role(UserRole.STUDENT)
                    .state(UserState.ACTIVE)
                    .build();

            Student student2 = Student.builder()
                    .firstName("Степан")
                    .lastName("Звонарь")
                    .email("student2@mail.ru")
                    .group(group1)
                    .password(passwordEncoder.encode("student"))
                    .role(UserRole.STUDENT)
                    .state(UserState.ACTIVE)
                    .build();

            Student student3 = Student.builder()
                    .firstName("Михаил")
                    .lastName("Шурупов")
                    .middleName("Алексеевич")
                    .email("student3@mail.ru")
                    .group(group2)
                    .password(passwordEncoder.encode("student"))
                    .role(UserRole.STUDENT)
                    .state(UserState.ACTIVE)
                    .build();

            studentRepository.saveAll(List.of(student1, student2, student3));


            Solution solution1 = Solution.builder().task(taskLesson1).student(student1).filePath("file").version(1).build();
            Solution solution2 = Solution.builder().task(taskLesson2).student(student1).filePath("file").version(1).build();
            Solution solution3 = Solution.builder().task(taskLesson3).student(student2).filePath("file").version(1).build();
            Solution solution4 = Solution.builder().task(taskLesson3).student(student3).filePath("file").version(1).build();
            Solution solution5 = Solution.builder().task(taskLesson3).student(student3).filePath("file").version(2).build();

            solutionRepository.saveAll(List.of(solution1, solution2, solution3, solution4, solution5));
        };
    }
}
