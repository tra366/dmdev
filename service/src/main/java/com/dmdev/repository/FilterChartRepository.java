package com.dmdev.repository;

import com.dmdev.dto.ChartDto;
import com.dmdev.entity.Chart;

import java.util.List;

public interface FilterChartRepository {

    List<Chart> findByParams(ChartDto chartDto);

    List<Chart> findByNameSeries(String nameSeries);

    List<Chart> findByUsername(String username);
}
