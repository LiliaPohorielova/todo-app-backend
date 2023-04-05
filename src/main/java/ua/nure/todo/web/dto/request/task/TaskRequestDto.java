package ua.nure.todo.web.dto.request.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ua.nure.todo.entity.Category;
import ua.nure.todo.entity.Priority;
import ua.nure.todo.web.dto.request.RequestDto;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDto extends RequestDto {

    private String title;
    private Integer completed;
    private Date date;
    private Priority priority;
    private Category category;
}
