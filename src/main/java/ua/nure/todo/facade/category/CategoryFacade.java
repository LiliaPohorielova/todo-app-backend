package ua.nure.todo.facade.category;

import ua.nure.todo.facade.BaseFacade;
import ua.nure.todo.web.dto.request.category.CategoryRequestDto;
import ua.nure.todo.web.dto.response.category.CategoryResponseDto;

import java.util.List;

public interface CategoryFacade extends BaseFacade<CategoryRequestDto, CategoryResponseDto> {

    List<CategoryResponseDto> findByTitle(String text);
    List<CategoryResponseDto> findAllByOrderByTitleAsc();
}
