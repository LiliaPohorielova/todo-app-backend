package ua.nure.todo.facade.priority;

import ua.nure.todo.facade.BaseFacade;
import ua.nure.todo.web.dto.request.priority.PriorityRequestDto;
import ua.nure.todo.web.dto.response.priority.PriorityResponseDto;

import java.util.List;

public interface PriorityFacade extends BaseFacade<PriorityRequestDto, PriorityResponseDto> {

    List<PriorityResponseDto> findByTitle(String text);
}
