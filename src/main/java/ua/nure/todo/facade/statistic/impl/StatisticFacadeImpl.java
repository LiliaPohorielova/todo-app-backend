package ua.nure.todo.facade.statistic.impl;

import org.springframework.stereotype.Service;
import ua.nure.todo.entity.Statistic;
import ua.nure.todo.exception.EntityNotFoundException;
import ua.nure.todo.facade.statistic.StatisticFacade;
import ua.nure.todo.service.statistic.StatisticService;
import ua.nure.todo.web.dto.response.statistic.StatisticResponseDto;

@Service
public class StatisticFacadeImpl implements StatisticFacade {

    private final StatisticService statisticService;

    public StatisticFacadeImpl(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @Override
    public StatisticResponseDto findById(Long id) {
        if (statisticService.findById(id).isPresent()) {
            Statistic statistic = statisticService.findById(id).get();
            return new StatisticResponseDto(statistic);
        }
        throw new EntityNotFoundException("Statistic not found");
    }
}
