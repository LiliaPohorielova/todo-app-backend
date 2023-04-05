package ua.nure.todo.facade.priority.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.nure.todo.entity.Priority;
import ua.nure.todo.exception.EntityNotFoundException;
import ua.nure.todo.facade.priority.PriorityFacade;
import ua.nure.todo.service.priority.PriorityService;
import ua.nure.todo.web.dto.request.priority.PriorityRequestDto;
import ua.nure.todo.web.dto.response.priority.PriorityResponseDto;

import java.util.List;

@Slf4j
@Service
public class PriorityFacadeImpl implements PriorityFacade {

    private final PriorityService priorityService;

    public PriorityFacadeImpl(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    @Override
    public PriorityResponseDto create(PriorityRequestDto priorityRequestDto) {
        log.info("Method create() was started with priorityRequestDto: {}", priorityRequestDto);
        Priority priority = new Priority();
        priority.setTitle(priorityRequestDto.getTitle());
        priority.setColor(priorityRequestDto.getColor());
        priorityService.create(priority);
        PriorityResponseDto responseDto = new PriorityResponseDto(priority);
        log.info("Method create() was finished with priorityResponseDto: {}", responseDto);
        return responseDto;
    }

    @Override
    public PriorityResponseDto update(PriorityRequestDto priorityRequestDto, Long id) {
        log.info("Method update() by id = {} was started with priorityRequestDto: {}", id, priorityRequestDto);
        if (priorityService.findById(id).isPresent()) {
            Priority priority = priorityService.findById(id).get();
            priority.setTitle(priorityRequestDto.getTitle());
            priority.setColor(priorityRequestDto.getColor());
            priorityService.create(priority);
            PriorityResponseDto responseDto = new PriorityResponseDto(priority);
            log.info("Method update() was finished with priorityResponseDto: {}", responseDto);
            return responseDto;
        }
        log.error("Priority with id = {} not found", id);
        throw new EntityNotFoundException("Priority not found");
    }

    @Override
    public void delete(Long id) {
        log.info("Method delete() by id = {} was started", id);
        priorityService.delete(id);
        log.info("Method delete() by id = {} was finished", id);
    }

    @Override
    public PriorityResponseDto findById(Long id) {
        log.info("Method findById() by id = {} was started", id);
        if (priorityService.findById(id).isPresent()) {
            Priority priority = priorityService.findById(id).get();
            PriorityResponseDto responseDto = new PriorityResponseDto(priority);
            log.info("Method findById() was finished with priorityResponseDto: {}", responseDto);
            return responseDto;
        }
        log.error("Priority with id = {} not found", id);
        throw new EntityNotFoundException("Priority not found");
    }

    @Override
    public List<PriorityResponseDto> findAll() {
        log.info("Method findAll() was started");
        List<Priority> all = priorityService.findAll();
        List<PriorityResponseDto> responses = all.stream().map(PriorityResponseDto::new).toList();
        log.info("Method findAll() was finished with number of responses: {}", responses.size());
        return responses;
    }

    @Override
    public List<PriorityResponseDto> findByTitle(String text) {
        log.info("Method findByTitle() by title = {} was started", text);
        List<Priority> all = priorityService.findByTitle(text);
        List<PriorityResponseDto> responses = all.stream().map(PriorityResponseDto::new).toList();
        log.info("Method findByTitle() was finished with number of responses: {}", responses.size());
        return responses;
    }
}
