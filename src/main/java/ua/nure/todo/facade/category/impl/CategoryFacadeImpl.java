package ua.nure.todo.facade.category.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.nure.todo.entity.Category;
import ua.nure.todo.exception.EntityNotFoundException;
import ua.nure.todo.facade.category.CategoryFacade;
import ua.nure.todo.service.category.CategoryService;
import ua.nure.todo.web.dto.request.category.CategoryRequestDto;
import ua.nure.todo.web.dto.response.category.CategoryResponseDto;

import java.util.List;

@Slf4j
@Service
public class CategoryFacadeImpl implements CategoryFacade {

    private final CategoryService categoryService;

    public CategoryFacadeImpl(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public CategoryResponseDto create(CategoryRequestDto categoryRequestDto) {
        log.info("Method create() was started with categoryRequestDto: {}", categoryRequestDto);
        Category category = new Category();
        category.setTitle(categoryRequestDto.getTitle());
        category.setCompletedCount(categoryRequestDto.getCompletedCount());
        category.setUncompletedCount(categoryRequestDto.getUncompletedCount());
        categoryService.create(category);
        CategoryResponseDto responseDto = new CategoryResponseDto(category);
        log.info("Method create() was finished with categoryResponseDto: {}", responseDto);
        return responseDto;
    }

    @Override
    public CategoryResponseDto update(CategoryRequestDto categoryRequestDto, Long id) {
        log.info("Method update() by id = {} was started with categoryRequestDto: {}", id, categoryRequestDto);
        if (categoryService.findById(id).isPresent()) {
            Category category = categoryService.findById(id).get();
            category.setTitle(categoryRequestDto.getTitle());
            category.setCompletedCount(categoryRequestDto.getCompletedCount());
            category.setUncompletedCount(categoryRequestDto.getUncompletedCount());
            categoryService.create(category);
            CategoryResponseDto responseDto = new CategoryResponseDto(category);
            log.info("Method update() was finished with categoryResponseDto: {}", responseDto);
            return responseDto;
        }
        log.error("Category with id = {} not found", id);
        throw new EntityNotFoundException("Category not found");
    }

    @Override
    public void delete(Long id) {
        log.info("Method delete() by id = {} was started", id);
        categoryService.delete(id);
        log.info("Method delete() by id = {} was finished", id);
    }

    @Override
    public CategoryResponseDto findById(Long id) {
        log.info("Method findById() by id = {} was started", id);
        if (categoryService.findById(id).isPresent()) {
            Category category = categoryService.findById(id).get();
            CategoryResponseDto responseDto = new CategoryResponseDto(category);
            log.info("Method findById() was finished with categoryResponseDto: {}", responseDto);
            return responseDto;
        }
        log.error("Category with id = {} not found", id);
        throw new EntityNotFoundException("Category not found");
    }

    @Override
    public List<CategoryResponseDto> findAll() {
        log.info("Method findAll() was started");
        List<Category> all = categoryService.findAll();
        List<CategoryResponseDto> responses = all.stream().map(CategoryResponseDto::new).toList();
        log.info("Method findAll() was finished with number of responses: {}", responses.size());
        return responses;
    }

    @Override
    public List<CategoryResponseDto> findByTitle(String text) {
        log.info("Method findByTitle() by title = {} was started", text);
        List<Category> all = categoryService.findByTitle(text);
        List<CategoryResponseDto> responses = all.stream().map(CategoryResponseDto::new).toList();
        log.info("Method findByTitle() was finished with number of responses: {}", responses.size());
        return responses;
    }

    @Override
    public List<CategoryResponseDto> findAllByOrderByTitleAsc() {
        log.info("Method findAllByOrderByTitleAsc() was started");
        List<Category> all = categoryService.findAllByOrderByTitleAsc();
        List<CategoryResponseDto> responses = all.stream().map(CategoryResponseDto::new).toList();
        log.info("Method findAllByOrderByTitleAsc() was finished with number of responses: {}", responses.size());
        return responses;
    }
}
