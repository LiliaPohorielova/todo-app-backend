package ua.nure.todo.web.dto.response.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ua.nure.todo.entity.Category;
import ua.nure.todo.web.dto.response.ResponseDto;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CategoryResponseDto extends ResponseDto {

    private Long id;
    private String title;
    private Long completedCount;
    private Long uncompletedCount;

    public CategoryResponseDto(Category category) {
        if (category != null) {
            this.id = category.getId();
            this.title = category.getTitle();
            this.completedCount = category.getCompletedCount();
            this.uncompletedCount = category.getUncompletedCount();
        }
    }
}
