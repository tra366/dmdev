package com.dmdev.dao;

import com.dmdev.entity.Series;

import java.util.List;

public interface FilterSeriesRepository {

    List<Series> findByNameSeries(List<String> nameSeries);

    List<Series> findByChartName(String chartName);
}
