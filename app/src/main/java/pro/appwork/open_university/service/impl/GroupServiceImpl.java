package pro.appwork.open_university.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.appwork.open_university.model.entity.Group;
import pro.appwork.open_university.repository.GroupRepository;
import pro.appwork.open_university.service.GroupService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;

    @Override
    public List<Group> getAll() {
        return groupRepository.findAll();
    }
}