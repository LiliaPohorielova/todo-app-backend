package ua.nure.todo.web.dto.request.statistic;

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
public class StatisticRequestDto extends RequestDto {

    private Long completedTotal;
    private Long uncompletedTotal;
}
