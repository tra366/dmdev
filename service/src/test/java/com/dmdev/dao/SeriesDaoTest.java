package com.dmdev.dao;

import com.dmdev.GetEntity;
import com.dmdev.entity.Series;
import com.dmdev.entity.User;
import com.dmdev.integration.IntegrationTestBase;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

class SeriesDaoTest extends IntegrationTestBase {
    @Test
    void save() {
        User user = GetEntity.getUser("UUser1");
        Series newSeries = GetEntity.getSeries("newSeries");
        getSourceRepository().save(newSeries.getChart().getSource());
        getSourceRepository().save(newSeries.getSource());
        getNameSeriesRepository().save(newSeries.getNameSeries());
        getTypeSeriesRepository().save(newSeries.getTypeSeries());
        getUserRepository().save(user);
        newSeries.getChart().setOwner(user);
        getChartRepository().save(newSeries.getChart());
        getSeriesRepository().save(newSeries);
        getSession().clear();

        Series actualSeries = getSession().get(Series.class, newSeries.getId());

        assertThat(actualSeries.getId()).isNotNull();
    }

    @Test
    void findById() {
        User user = GetEntity.getUser("UUser1");
        Series newSeries = GetEntity.getSeries("newSeries");
        getSourceRepository().save(newSeries.getChart().getSource());
        getSourceRepository().save(newSeries.getSource());
        getNameSeriesRepository().save(newSeries.getNameSeries());
        getTypeSeriesRepository().save(newSeries.getTypeSeries());
        getUserRepository().save(user);
        newSeries.getChart().setOwner(user);
        getChartRepository().save(newSeries.getChart());
        getSeriesRepository().save(newSeries);
        getSession().clear();

        Optional<Series> actualSeries = getSeriesRepository().findById(newSeries.getId());

        assertThat(actualSeries).isNotNull();
    }

    @Test
    void update() {
        User user = GetEntity.getUser("UUser1");
        Series newSeries = GetEntity.getSeries("newSeries");
        getSourceRepository().save(newSeries.getChart().getSource());
        getSourceRepository().save(newSeries.getSource());
        getNameSeriesRepository().save(newSeries.getNameSeries());
        getTypeSeriesRepository().save(newSeries.getTypeSeries());
        getUserRepository().save(user);
        newSeries.getChart().setOwner(user);
        getChartRepository().save(newSeries.getChart());
        getSeriesRepository().save(newSeries);
        newSeries.setName("newSeries_Renamed");
        getSeriesRepository().update(newSeries);
        getSession().clear();

        Series actualSeries = getSession().get(Series.class, newSeries.getId());

        assertThat(actualSeries.getName()).isEqualTo("newSeries_Renamed");
    }

    @Test
    void delete() {
        User user = GetEntity.getUser("UUser1");
        Series newSeries = GetEntity.getSeries("newSeries");
        getSourceRepository().save(newSeries.getChart().getSource());
        getSourceRepository().save(newSeries.getSource());
        getNameSeriesRepository().save(newSeries.getNameSeries());
        getTypeSeriesRepository().save(newSeries.getTypeSeries());
        getUserRepository().save(user);
        newSeries.getChart().setOwner(user);
        getChartRepository().save(newSeries.getChart());
        getSeriesRepository().save(newSeries);
        newSeries.setName("newSeries_Renamed");
        getSeriesRepository().delete(newSeries);
        getSession().clear();

        Series actualSeries = getSession().get(Series.class, newSeries.getId());

        assertThat(actualSeries).isNull();
    }

    @Test
    void getAll() {
        List<Series> results = getSeriesRepository().getAll();

        assertThat(results).hasSize(6);

        List<String> seriesNames = results.stream().map(Series::getName).collect(toList());
        assertThat(seriesNames).containsExactlyInAnyOrder("PlanSeries", "FactSeries", "PlanSmrSeries", "FactSmrSeries", "PlanSwap", "FactSwap");
    }

    @Test
    void getByChartName() {
        List<Series> results = getSeriesRepository().getByChartName("DynamicBuilding");

        assertThat(results).hasSize(2);

        List<String> seriesNames = results.stream().map(Series::getName).collect(toList());
        assertThat(seriesNames).containsExactlyInAnyOrder("PlanSmrSeries", "FactSmrSeries");
    }

    @Test
    void getByNameSeries() {
        List<Series> results = getSeriesRepository().getByNameSeries(Arrays.asList("Plan", "PlanSmr"));

        assertThat(results).hasSize(3);

        List<String> seriesNames = results.stream().map(Series::getName).collect(toList());
        assertThat(seriesNames).containsExactlyInAnyOrder("PlanSeries", "PlanSmrSeries", "PlanSwap");
    }
}
