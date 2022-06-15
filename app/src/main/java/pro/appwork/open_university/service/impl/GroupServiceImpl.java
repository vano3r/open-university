package pro.appwork.open_university.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.appwork.open_university.model.entity.Group;
import pro.appwork.open_university.model.enums.AcademicDegreeEnum;
import pro.appwork.open_university.model.enums.GroupStatusEnum;
import pro.appwork.open_university.repository.GroupRepository;
import pro.appwork.open_university.service.GroupService;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;

    @Override
    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    @Override
    public Group getById(Long id) {
        return groupRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Group %d not found".formatted(id))
        );
    }

    @Override
    public void create(String name, Integer year, AcademicDegreeEnum degree) {
        LocalDate startDate = LocalDate.of(year, 9, 1);

        Group newGroup = Group.builder()
                .name(name)
                .learningStartDate(startDate)
                .learningEndDate(startDate.plusYears(degree.getNumberYears()))
                .academicDegree(degree)
                .status(GroupStatusEnum.ACTIVE)
                .build();

        groupRepository.save(newGroup);
    }

    @Override
    public void deleteById(Long id) {
        Group group = groupRepository.findById(id).orElseThrow(
                EntityNotFoundException::new
        );

        groupRepository.delete(group);
    }

    @Override
    public void updateById(Long id, String name, Integer year,
                           AcademicDegreeEnum degree, GroupStatusEnum status) {

        Group group = groupRepository.findById(id).orElseThrow(
                EntityNotFoundException::new
        );

        LocalDate startDate = LocalDate.of(year, 9, 1);

        Group newGroup = group.toBuilder()
                .name(name)
                .learningStartDate(startDate)
                .learningEndDate(startDate.plusYears(degree.getNumberYears()))
                .academicDegree(degree)
                .status(status)
                .build();

        groupRepository.save(newGroup);
    }
}
