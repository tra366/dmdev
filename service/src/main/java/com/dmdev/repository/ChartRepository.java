package com.dmdev.repository;

import com.dmdev.dto.ChartReadDto;
import com.dmdev.entity.Chart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChartRepository extends JpaRepository<Chart, Integer>,
        FilterChartRepository {

    List<Chart> findByUsername(String username);

    List<Chart> findByNameSeries(String nameSeries);

    List<Chart> findByParams(ChartReadDto chartDto);

}
