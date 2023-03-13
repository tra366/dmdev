package com.dmdev.dao;

import com.dmdev.entity.Series;
import com.querydsl.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import java.util.List;

import static com.dmdev.entity.QChart.chart;
import static com.dmdev.entity.QSeries.series;

public class SeriesRepository extends RepositoryBase<Integer, Series> {

    public SeriesRepository(EntityManager entityManger) {
        super(Series.class, entityManger);
    }

    public List<Series> getAll() {

        return new JPAQuery<Series>(getEntityManger())
                .select(series)
                .from(series)
                .fetch();
    }

    public List<Series> getByChartName(String chartName) {

        return new JPAQuery<Series>(getEntityManger())
                .select(series)
                .from(series)
                .join(series.chart, chart)
                .where(chart.name.eq(chartName))
                .fetch();
    }

    public List<Series> getByNameSeries(String nameSeries1, String nameSeries2) {

        var predicate = QPredicate.builder()
                .add(nameSeries1, series.nameSeries.name::eq)
                .add(nameSeries2, series.nameSeries.name::eq)
                .buildOr();


        return new JPAQuery<Series>(getEntityManger())
                .select(series)
                .from(series)
                .join(series.chart, chart)
                .where(predicate)
                .fetch();
    }
}
