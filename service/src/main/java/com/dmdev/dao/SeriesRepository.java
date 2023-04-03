package com.dmdev.dao;

import com.dmdev.entity.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Integer>,
        FilterSeriesRepository {
    public List<Series> findAll();

    public List<Series> findByNameSeries(List<String> nameSeries);

    public List<Series> findByChartName(String chartName);

}
