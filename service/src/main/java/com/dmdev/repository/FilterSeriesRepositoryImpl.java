package com.dmdev.repository;

import com.dmdev.entity.Series;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import java.util.List;

import static com.dmdev.entity.QChart.chart;
import static com.dmdev.entity.QSeries.series;

@RequiredArgsConstructor
public class FilterSeriesRepositoryImpl implements FilterSeriesRepository {

    private final EntityManager entityManager;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Series> findByNameSeries(List<String> nameSeries) {
        var predicate = QPredicate.builder()
                .add(nameSeries, series.nameSeries.name::in)
                .buildOr();

        return new JPAQuery<Series>(entityManager)
                .select(series)
                .from(series)
                .join(series.chart, chart)
                .where(predicate)
                .fetch();
    }

    @Override
    public List<Series> findByChartName(String chartName) {
        return new JPAQuery<Series>(entityManager)
                .select(series)
                .from(series)
                .join(series.chart, chart)
                .where(chart.name.eq(chartName))
                .fetch();
    }
}
