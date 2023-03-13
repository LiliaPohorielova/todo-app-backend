package ua.nure.todo.service.priority.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ua.nure.todo.entity.Priority;
import ua.nure.todo.exception.EntityNotFoundException;
import ua.nure.todo.repository.priority.PriorityRepository;
import ua.nure.todo.service.priority.PriorityService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PriorityServiceImpl implements PriorityService {
    
    private final PriorityRepository priorityRepository;

    public PriorityServiceImpl(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @Override
    public void create(Priority entity) {
        priorityRepository.save(entity);
    }

    @Override
    public void update(Priority entity) {
        checkExist(entity.getId());
        priorityRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        checkExist(id);
        priorityRepository.deleteById(id);
    }

    @Override
    public Optional<Priority> findById(Long id) {
        return priorityRepository.findById(id);
    }

    @Override
    public List<Priority> findAll() {
        return priorityRepository.findAll();
    }

    @Override
    public List<Priority> findByTitle(String text){
        return priorityRepository.findByTitle(text);
    }

    private void checkExist(Long id) {
        if (!priorityRepository.existsById(id)) {
            throw new EntityNotFoundException("Entity not found");
        }
    }
}
