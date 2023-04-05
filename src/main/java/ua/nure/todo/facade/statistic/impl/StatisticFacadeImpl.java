package ua.nure.todo.facade.statistic.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.nure.todo.entity.Statistic;
import ua.nure.todo.exception.EntityNotFoundException;
import ua.nure.todo.facade.statistic.StatisticFacade;
import ua.nure.todo.service.statistic.StatisticService;
import ua.nure.todo.web.dto.response.statistic.StatisticResponseDto;

@Slf4j
@Service
public class StatisticFacadeImpl implements StatisticFacade {

    private final StatisticService statisticService;

    public StatisticFacadeImpl(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @Override
    public StatisticResponseDto findById(Long id) {
        log.info("Method findById() by id = {} was started", id);
        if (statisticService.findById(id).isPresent()) {
            Statistic statistic = statisticService.findById(id).get();
            StatisticResponseDto responseDto = new StatisticResponseDto(statistic);
            log.info("Method findById() was finished with statisticResponseDto: {}", responseDto);
            return responseDto;
        }
        log.error("Statistic with id = {} not found", id);
        throw new EntityNotFoundException("Statistic not found");
    }
}
