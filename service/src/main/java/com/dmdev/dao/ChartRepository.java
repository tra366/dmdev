package com.dmdev.dao;

import com.dmdev.dto.ChartDto;
import com.dmdev.entity.Chart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChartRepository extends JpaRepository<Chart, Integer>,
        FilterChartRepository {

    public List<Chart> findAll();

    public List<Chart> findByUsername(String username);

    public List<Chart> findByNameSeries(String nameSeries);

    public List<Chart> findByParams(ChartDto chartDto);

}
