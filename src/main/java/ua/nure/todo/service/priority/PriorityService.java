package ua.nure.todo.service.priority;

import ua.nure.todo.entity.Priority;
import ua.nure.todo.service.BaseCrudService;

import java.util.List;

public interface PriorityService extends BaseCrudService<Priority> {

    List<Priority> findByTitle(String text);
}
