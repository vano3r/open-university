package pro.appwork.open_university.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.appwork.open_university.model.entity.TaskType;
import pro.appwork.open_university.repository.TaskTypeRepository;
import pro.appwork.open_university.service.TaskTypeService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskTypeServiceImpl implements TaskTypeService {
    private final TaskTypeRepository typeRepository;

    @Override
    public List<TaskType> getAll() {
        return typeRepository.findAll();
    }

    @Override
    public TaskType getById(Long id) {
        return typeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void createByName(String name) {
        TaskType newType = TaskType.builder().name(name).build();

        typeRepository.save(newType);
    }

    @Override
    public void deleteById(Long id) {
        TaskType type = typeRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        typeRepository.delete(type);
    }

    @Override
    public void updateById(Long id, String name) {
        TaskType type = typeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        TaskType newType = type.toBuilder().name(name).build();

        typeRepository.save(newType);
    }
}
