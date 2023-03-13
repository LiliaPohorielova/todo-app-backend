package ua.nure.todo.service.task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ua.nure.todo.entity.Task;
import ua.nure.todo.service.BaseCrudService;

public interface TaskService extends BaseCrudService<Task> {

    Page<Task> findByParams(String text, Integer completed, Long priorityId, Long categoryId, PageRequest paging);
}
