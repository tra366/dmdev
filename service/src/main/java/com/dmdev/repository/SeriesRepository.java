package com.dmdev.repository;

import com.dmdev.entity.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Integer>,
        FilterSeriesRepository {

    List<Series> findByNameSeries(List<String> nameSeries);

    List<Series> findByChartName(String chartName);
}
