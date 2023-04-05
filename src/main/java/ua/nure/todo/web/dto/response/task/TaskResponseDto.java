package ua.nure.todo.web.dto.response.task;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ua.nure.todo.entity.Category;
import ua.nure.todo.entity.Priority;
import ua.nure.todo.entity.Task;
import ua.nure.todo.web.dto.response.ResponseDto;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TaskResponseDto extends ResponseDto {

    private Long id;
    private String title;
    private Integer completed;
    private Date date;
    private Priority priority;
    private Category category;

    public TaskResponseDto(Task task) {
        if(task != null) {
            this.id = task.getId();
            this.title = task.getTitle();
            this.completed = task.getCompleted();
            this.date = task.getDate();
            this.priority = task.getPriority();
            this.category = task.getCategory();
        }
    }
}
