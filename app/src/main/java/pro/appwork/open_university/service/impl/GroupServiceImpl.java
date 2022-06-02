package pro.appwork.open_university.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.appwork.open_university.model.entity.Group;
import pro.appwork.open_university.repository.GroupRepository;
import pro.appwork.open_university.service.GroupService;

import javax.persistence.EntityNotFoundException;
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
    public void createByName(String name) {
        Group newGroup = Group.builder().name(name).build();

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
    public void updateById(Long id, String name) {
        Group group = groupRepository.findById(id).orElseThrow(
                EntityNotFoundException::new
        );
        Group newGroup = group.toBuilder().name(name).build();

        groupRepository.save(newGroup);
    }
}
