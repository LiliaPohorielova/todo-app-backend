package ua.nure.todo.web.dto.request.priority;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ua.nure.todo.web.dto.request.RequestDto;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PriorityRequestDto extends RequestDto {

    private String title;
    private String color;
}
