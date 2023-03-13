package ua.nure.todo.repository.statistic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nure.todo.entity.Statistic;

@Repository
public interface StatisticRepository extends JpaRepository<Statistic, Long> { }
