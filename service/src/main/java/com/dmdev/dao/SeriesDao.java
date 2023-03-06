package com.dmdev.dao;

import com.dmdev.entity.Series;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.Session;

import java.util.List;

import static com.dmdev.entity.QChart.chart;
import static com.dmdev.entity.QSeries.series;

public class SeriesDao {

    private static final SeriesDao INSTANCE = new SeriesDao();

    public static SeriesDao getInstance() {
        return INSTANCE;
    }

    public List<Series> getAll(Session session) {

        return new JPAQuery<Series>(session)
                .select(series)
                .from(series)
                .fetch();
    }

    public List<Series> getByChartName(Session session, String chartName) {

        return new JPAQuery<Series>(session)
                .select(series)
                .from(series)
                .join(series.chart, chart)
                .where(chart.name.eq(chartName))
                .fetch();
    }

    public List<Series> getByNameSeries(Session session, String nameSeries1, String nameSeries2) {

        var predicate = QPredicate.builder()
                .add(nameSeries1, series.nameSeries.name::eq)
                .add(nameSeries2, series.nameSeries.name::eq)
                .buildOr();


        return new JPAQuery<Series>(session)
                .select(series)
                .from(series)
                .join(series.chart, chart)
                .where(predicate)
                .fetch();
    }
}
