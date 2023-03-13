package ua.nure.todo.service.statistic.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.todo.entity.Statistic;
import ua.nure.todo.exception.EntityNotFoundException;
import ua.nure.todo.repository.statistic.StatisticRepository;
import ua.nure.todo.service.statistic.StatisticService;

import java.util.Optional;

@Service
@Transactional
public class StatisticServiceImpl implements StatisticService {

    private final StatisticRepository statisticRepository;

    public StatisticServiceImpl(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    @Override
    public Optional<Statistic> findById(Long id) {
        if (statisticRepository.existsById(id))
            return statisticRepository.findById(id);
        else
            throw new EntityNotFoundException("Statistic not found");
    }
}
