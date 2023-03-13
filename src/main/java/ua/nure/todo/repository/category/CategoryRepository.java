package ua.nure.todo.repository.category;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.nure.todo.entity.Category;
import ua.nure.todo.repository.BaseRepository;

import java.util.List;

@Repository
public interface CategoryRepository extends BaseRepository<Category> {

    // Поиск с помощью JPQL -> подключить ElasticSearch
    // Если title пустой - показываем все значения
    // lower - приводим все буквы к нижнему регистру
    @Query( "SELECT c FROM Category c WHERE " +
            "(:title is null or :title='' or lower(c.title) like lower(concat('%',:title,'%'))) " +
            "ORDER BY c.title ASC")
    List<Category> findByTitle(@Param("title") String title);

    List<Category> findAllByOrderByTitleAsc();
}
