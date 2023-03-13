package ua.nure.todo.service.statistic;

import ua.nure.todo.entity.Statistic;

import java.util.Optional;

public interface StatisticService {

     Optional<Statistic> findById(Long id);
}