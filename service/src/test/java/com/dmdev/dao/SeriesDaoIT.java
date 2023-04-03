package com.dmdev.dao;

import com.dmdev.GetEntity;
import com.dmdev.entity.Series;
import com.dmdev.entity.User;
import com.dmdev.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
class SeriesDaoIT extends IntegrationTestBase {

    private final UserRepository userRepository;
    private final SourceRepository sourceRepository;
    private final ChartRepository chartRepository;
    private final NameSeriesRepository nameSeriesRepository;
    private final TypeSeriesRepository typeSeriesRepository;
    private final SeriesRepository seriesRepository;
    private final EntityManager entityManager;

    @Test
    void save() {
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
        entityManager.clear();

        Series actualSeries = entityManager.find(Series.class, newSeries.getId());

        assertThat(actualSeries.getId()).isNotNull();
    }

    @Test
    void findById() {
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
        entityManager.clear();

        Optional<Series> actualSeries = seriesRepository.findById(newSeries.getId());

        assertThat(actualSeries).isNotNull();
    }

    @Test
    void update() {
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
        seriesRepository.save(newSeries);
        entityManager.clear();

        Series actualSeries = entityManager.find(Series.class, newSeries.getId());

        assertThat(actualSeries.getName()).isEqualTo("newSeries_Renamed");
    }

    @Test
    void delete() {
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
        entityManager.clear();

        Series actualSeries = entityManager.find(Series.class, newSeries.getId());

        assertThat(actualSeries).isNull();
    }

    @Test
    void getAll() {
        List<Series> results = seriesRepository.findAll();

        assertThat(results).hasSize(6);

        List<String> seriesNames = results.stream().map(Series::getName).collect(toList());
        assertThat(seriesNames).containsExactlyInAnyOrder("PlanSeries", "FactSeries", "PlanSmrSeries", "FactSmrSeries", "PlanSwap", "FactSwap");
    }

    @Test
    void getByChartName() {
        List<Series> results = seriesRepository.findByChartName("DynamicBuilding");

        assertThat(results).hasSize(2);

        List<String> seriesNames = results.stream().map(Series::getName).collect(toList());
        assertThat(seriesNames).containsExactlyInAnyOrder("PlanSmrSeries", "FactSmrSeries");
    }

    @Test
    void getByNameSeries() {
        List<Series> results = seriesRepository.findByNameSeries(Arrays.asList("Plan", "PlanSmr"));

        assertThat(results).hasSize(3);

        List<String> seriesNames = results.stream().map(Series::getName).collect(toList());
        assertThat(seriesNames).containsExactlyInAnyOrder("PlanSeries", "PlanSmrSeries", "PlanSwap");
    }
}
