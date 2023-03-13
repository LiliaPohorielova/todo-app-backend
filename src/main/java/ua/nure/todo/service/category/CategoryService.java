package ua.nure.todo.service.category;

import ua.nure.todo.entity.Category;
import ua.nure.todo.service.BaseCrudService;

import java.util.List;

public interface CategoryService extends BaseCrudService<Category> {

    List<Category> findByTitle(String text);
    List<Category> findAllByOrderByTitleAsc();
}