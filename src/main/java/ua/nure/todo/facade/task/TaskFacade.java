package ua.nure.todo.facade.task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ua.nure.todo.entity.Task;
import ua.nure.todo.facade.BaseFacade;
import ua.nure.todo.web.dto.request.task.TaskRequestDto;
import ua.nure.todo.web.dto.response.task.TaskResponseDto;

public interface TaskFacade extends BaseFacade<TaskRequestDto, TaskResponseDto> {

    Page<Task> findByParams(String text, Integer completed, Long priorityId, Long categoryId, PageRequest paging);
}
