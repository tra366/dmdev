package com.dmdev.dao;

import com.dmdev.entity.Chart;
import com.dmdev.entity.Series;
import com.dmdev.integration.IntegrationTestBase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class SeriesDaoTest extends IntegrationTestBase {

    @Test
    void getAll() {
        List<Series> results = seriesDao.getAll(session);

        assertThat(results).hasSize(6);

        List<String> seriesNames = results.stream().map(Series::getName).collect(toList());
        assertThat(seriesNames).containsExactlyInAnyOrder("PlanSeries", "FactSeries", "PlanSmrSeries", "FactSmrSeries", "PlanSwap", "FactSwap");
    }

    @Test
    void getByChartName() {
        List<Series> results = seriesDao.getByChartName(session, "DynamicBuilding");

        assertThat(results).hasSize(2);

        List<String> seriesNames = results.stream().map(Series::getName).collect(toList());
        assertThat(seriesNames).containsExactlyInAnyOrder("PlanSmrSeries","FactSmrSeries");
    }

    @Test
    void getByNameSeries() {
        List<Series> results = seriesDao.getByNameSeries(session, "Plan", "PlanSmr");

        assertThat(results).hasSize(3);

        List<String> seriesNames = results.stream().map(Series::getName).collect(toList());
        assertThat(seriesNames).containsExactlyInAnyOrder("PlanSeries", "PlanSmrSeries","PlanSwap");
    }
}
