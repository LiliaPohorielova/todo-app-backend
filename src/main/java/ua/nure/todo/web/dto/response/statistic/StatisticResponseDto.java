package ua.nure.todo.web.dto.response.statistic;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.nure.todo.entity.Statistic;
import ua.nure.todo.web.dto.response.ResponseDto;

@Getter
@Setter
@NoArgsConstructor
public class StatisticResponseDto extends ResponseDto {

    private Long id;
    private Long completedTotal;
    private Long uncompletedTotal;

    public StatisticResponseDto(Statistic statistic) {
        if (statistic != null) {
            this.id = statistic.getId();
            this.completedTotal = statistic.getCompletedTotal();
            this.uncompletedTotal = statistic.getUncompletedTotal();
        }
    }
}