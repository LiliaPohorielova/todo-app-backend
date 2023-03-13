package ua.nure.todo.service.category.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ua.nure.todo.entity.Category;
import ua.nure.todo.exception.EntityNotFoundException;
import ua.nure.todo.repository.category.CategoryRepository;
import ua.nure.todo.service.category.CategoryService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void create(Category entity) {
        categoryRepository.save(entity);
    }

    @Override
    public void update(Category entity) {
        checkExist(entity.getId());
        categoryRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        checkExist(id);
        categoryRepository.deleteById(id);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findByTitle(String text) {
        return categoryRepository.findByTitle(text);
    }

    @Override
    public List<Category> findAllByOrderByTitleAsc() {
        return categoryRepository.findAllByOrderByTitleAsc();
    }

    private void checkExist(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Entity not found");
        }
    }
}
