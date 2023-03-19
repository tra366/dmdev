package com.dmdev.dao;

import com.dmdev.entity.Series;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

import static com.dmdev.entity.QChart.chart;
import static com.dmdev.entity.QSeries.series;

@Component
public class SeriesRepository extends RepositoryBase<Integer, Series> {

    public SeriesRepository(EntityManager entityManger) {
        super(Series.class, entityManger);
    }

    public List<Series> getAll() {
        return findAll();
    }

    public List<Series> getByChartName(String chartName) {
        return new JPAQuery<Series>(getEntityManger())
                .select(series)
                .from(series)
                .join(series.chart, chart)
                .where(chart.name.eq(chartName))
                .fetch();
    }

    public List<Series> getByNameSeries(List<String> nameSeries) {
        var predicate = QPredicate.builder()
                .add(nameSeries, series.nameSeries.name::in)
                .buildOr();


        return new JPAQuery<Series>(getEntityManger())
                .select(series)
                .from(series)
                .join(series.chart, chart)
                .where(predicate)
                .fetch();
    }
}
