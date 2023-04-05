package ua.nure.todo.facade.task.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.nure.todo.entity.Task;
import ua.nure.todo.exception.EntityNotFoundException;
import ua.nure.todo.facade.task.TaskFacade;
import ua.nure.todo.service.task.TaskService;
import ua.nure.todo.web.dto.request.task.TaskRequestDto;
import ua.nure.todo.web.dto.response.task.TaskResponseDto;

import java.util.List;

@Slf4j
@Service
public class TaskFacadeImpl implements TaskFacade {

    private final TaskService taskService;

    public TaskFacadeImpl(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public TaskResponseDto create(TaskRequestDto taskRequestDto) {
        log.info("Method create() was started with taskRequestDto: {}", taskRequestDto);
        Task task = new Task();
        task.setTitle(taskRequestDto.getTitle());
        task.setPriority(taskRequestDto.getPriority());
        task.setCategory(taskRequestDto.getCategory());
        task.setCompleted(taskRequestDto.getCompleted());
        task.setDate(taskRequestDto.getDate());
        taskService.create(task);
        TaskResponseDto responseDto = new TaskResponseDto(task);
        log.info("Method create() was finished with taskResponseDto: {}", responseDto);
        return responseDto;
    }

    @Override
    public TaskResponseDto update(TaskRequestDto taskRequestDto, Long id) {
        log.info("Method update() by id = {} was started with taskRequestDto: {}", id, taskRequestDto);
        if (taskService.findById(id).isPresent()) {
            Task task = taskService.findById(id).get();
            task.setTitle(taskRequestDto.getTitle());
            task.setPriority(taskRequestDto.getPriority());
            task.setCategory(taskRequestDto.getCategory());
            task.setCompleted(taskRequestDto.getCompleted());
            task.setDate(taskRequestDto.getDate());
            taskService.create(task);
            TaskResponseDto responseDto = new TaskResponseDto(task);
            log.info("Method update() was finished with taskResponseDto: {}", responseDto);
            return responseDto;
        }
        log.error("Task with id = {} not found", id);
        throw new EntityNotFoundException("Task not found");
    }

    @Override
    public void delete(Long id) {
        log.info("Method delete() by id = {} was started", id);
        taskService.delete(id);
        log.info("Method delete() by id = {} was finished", id);
    }

    @Override
    public TaskResponseDto findById(Long id) {
        log.info("Method findById() by id = {} was started", id);
        if (taskService.findById(id).isPresent()) {
            Task task = taskService.findById(id).get();
            TaskResponseDto responseDto = new TaskResponseDto(task);
            log.info("Method findById() was finished with taskResponseDto: {}", responseDto);
            return responseDto;
        }
        log.error("Task with id = {} not found", id);
        throw new EntityNotFoundException("Task not found");
    }

    @Override
    public List<TaskResponseDto> findAll() {
        log.info("Method findAll() was started");
        List<Task> all = taskService.findAll();
        List<TaskResponseDto> responses = all.stream().map(TaskResponseDto::new).toList();
        log.info("Method findAll() was finished with number of responses: {}", responses.size());
        return responses;
    }

    @Override
    public Page<Task> findByParams(String text, Integer completed, Long priorityId,
        Long categoryId, PageRequest paging) {
        log.info("Method findByParams() was started with text = {}, completed = {},"
            + "priorityId = {}, categoryId = {}, {}", text, completed, priorityId,
            categoryId, paging);
        Page<Task> taskPage = taskService.findByParams(text, completed, priorityId, categoryId, paging);
        log.info("Method findByParams() was finished with: {}", taskPage);
        return taskPage;
    }
}
