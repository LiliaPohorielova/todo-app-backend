package ua.nure.todo.facade.statistic;

import ua.nure.todo.web.dto.response.statistic.StatisticResponseDto;

public interface StatisticFacade {

    StatisticResponseDto findById(Long id);
}
