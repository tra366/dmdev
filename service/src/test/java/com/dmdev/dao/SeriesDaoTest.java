package com.dmdev.dao;

import com.dmdev.GetEntity;
import com.dmdev.entity.Series;
import com.dmdev.entity.User;
import com.dmdev.integration.IntegrationTestBase;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class SeriesDaoTest extends IntegrationTestBase {
    @Test
    void save() {
        SeriesRepository seriesRepository = new SeriesRepository(getSession());
        SourceRepository sourceRepository = new SourceRepository(getSession());
        NameSeriesRepository nameSeriesRepository = new NameSeriesRepository((getSession()));
        TypeSeriesRepository typeSeriesRepository = new TypeSeriesRepository((getSession()));
        ChartRepository chartRepository = new ChartRepository(getSession());
        UserRepository userRepository = new UserRepository(getSession());
        User user = GetEntity.getUser("UUser1");
        Series newSeries = GetEntity.getSeries("newSeries");
        sourceRepository.save(newSeries.getChart().getSource());
        sourceRepository.save(newSeries.getSource());
        nameSeriesRepository.save(newSeries.getNameSeries());
        typeSeriesRepository.save(newSeries.getTypeSeries());
        userRepository.save(user);
        newSeries.getChart().setOwner(user);
        chartRepository.save(newSeries.getChart());
        seriesRepository.save(newSeries);
        getSession().clear();

        Series actualSeries = getSession().get(Series.class, newSeries.getId());

        assertThat(actualSeries.getId()).isNotNull();
    }

    @Test
    void findById() {
        SeriesRepository seriesRepository = new SeriesRepository(getSession());
        SourceRepository sourceRepository = new SourceRepository(getSession());
        NameSeriesRepository nameSeriesRepository = new NameSeriesRepository((getSession()));
        TypeSeriesRepository typeSeriesRepository = new TypeSeriesRepository((getSession()));
        ChartRepository chartRepository = new ChartRepository(getSession());
        UserRepository userRepository = new UserRepository(getSession());
        User user = GetEntity.getUser("UUser1");
        Series newSeries = GetEntity.getSeries("newSeries");
        sourceRepository.save(newSeries.getChart().getSource());
        sourceRepository.save(newSeries.getSource());
        nameSeriesRepository.save(newSeries.getNameSeries());
        typeSeriesRepository.save(newSeries.getTypeSeries());
        userRepository.save(user);
        newSeries.getChart().setOwner(user);
        chartRepository.save(newSeries.getChart());
        seriesRepository.save(newSeries);
        getSession().clear();

        Optional<Series> actualSeries = seriesRepository.findById(newSeries.getId());

        assertThat(actualSeries).isNotNull();
    }

    @Test
    void update() {
        SeriesRepository seriesRepository = new SeriesRepository(getSession());
        SourceRepository sourceRepository = new SourceRepository(getSession());
        NameSeriesRepository nameSeriesRepository = new NameSeriesRepository((getSession()));
        TypeSeriesRepository typeSeriesRepository = new TypeSeriesRepository((getSession()));
        ChartRepository chartRepository = new ChartRepository(getSession());
        UserRepository userRepository = new UserRepository(getSession());
        User user = GetEntity.getUser("UUser1");
        Series newSeries = GetEntity.getSeries("newSeries");
        sourceRepository.save(newSeries.getChart().getSource());
        sourceRepository.save(newSeries.getSource());
        nameSeriesRepository.save(newSeries.getNameSeries());
        typeSeriesRepository.save(newSeries.getTypeSeries());
        userRepository.save(user);
        newSeries.getChart().setOwner(user);
        chartRepository.save(newSeries.getChart());
        seriesRepository.save(newSeries);
        newSeries.setName("newSeries_Renamed");
        seriesRepository.update(newSeries);
        getSession().flush();
        getSession().clear();

        Series actualSeries = getSession().get(Series.class, newSeries.getId());

        assertThat(actualSeries.getName()).isEqualTo("newSeries_Renamed");
    }

    @Test
    void delete() {
        SeriesRepository seriesRepository = new SeriesRepository(getSession());
        SourceRepository sourceRepository = new SourceRepository(getSession());
        NameSeriesRepository nameSeriesRepository = new NameSeriesRepository((getSession()));
        TypeSeriesRepository typeSeriesRepository = new TypeSeriesRepository((getSession()));
        ChartRepository chartRepository = new ChartRepository(getSession());
        UserRepository userRepository = new UserRepository(getSession());
        User user = GetEntity.getUser("UUser1");
        Series newSeries = GetEntity.getSeries("newSeries");
        sourceRepository.save(newSeries.getChart().getSource());
        sourceRepository.save(newSeries.getSource());
        nameSeriesRepository.save(newSeries.getNameSeries());
        typeSeriesRepository.save(newSeries.getTypeSeries());
        userRepository.save(user);
        newSeries.getChart().setOwner(user);
        chartRepository.save(newSeries.getChart());
        seriesRepository.save(newSeries);
        newSeries.setName("newSeries_Renamed");
        seriesRepository.delete(newSeries);
        getSession().clear();

        Series actualSeries = getSession().get(Series.class, newSeries.getId());

        assertThat(actualSeries).isNull();
    }

    @Test
    void getAll() {
        SeriesRepository seriesRepository = new SeriesRepository(getSession());
        List<Series> results = seriesRepository.getAll();

        assertThat(results).hasSize(6);

        List<String> seriesNames = results.stream().map(Series::getName).collect(toList());
        assertThat(seriesNames).containsExactlyInAnyOrder("PlanSeries", "FactSeries", "PlanSmrSeries", "FactSmrSeries", "PlanSwap", "FactSwap");
    }

    @Test
    void getByChartName() {
        SeriesRepository seriesRepository = new SeriesRepository(getSession());
        List<Series> results = seriesRepository.getByChartName("DynamicBuilding");

        assertThat(results).hasSize(2);

        List<String> seriesNames = results.stream().map(Series::getName).collect(toList());
        assertThat(seriesNames).containsExactlyInAnyOrder("PlanSmrSeries", "FactSmrSeries");
    }

    @Test
    void getByNameSeries() {
        SeriesRepository seriesRepository = new SeriesRepository(getSession());
        List<Series> results = seriesRepository.getByNameSeries("Plan", "PlanSmr");

        assertThat(results).hasSize(3);

        List<String> seriesNames = results.stream().map(Series::getName).collect(toList());
        assertThat(seriesNames).containsExactlyInAnyOrder("PlanSeries", "PlanSmrSeries", "PlanSwap");
    }
}
