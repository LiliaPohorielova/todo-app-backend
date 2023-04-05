package ua.nure.todo.web.dto.response.priority;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ua.nure.todo.entity.Priority;
import ua.nure.todo.web.dto.response.ResponseDto;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PriorityResponseDto extends ResponseDto {

    private Long id;
    private String title;
    private String color;

    public PriorityResponseDto(Priority priority) {
        if (priority != null) {
            this.id = priority.getId();
            this.title = priority.getTitle();
            this.color = priority.getColor();
        }
    }
}
