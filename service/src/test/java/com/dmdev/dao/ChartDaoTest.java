package com.dmdev.dao;

import com.dmdev.GetEntity;
import com.dmdev.dto.ChartDto;
import com.dmdev.entity.Chart;
import com.dmdev.entity.ObjectBuilding;
import com.dmdev.entity.PeriodReport;
import com.dmdev.entity.TypeBuilding;
import com.dmdev.entity.TypeReport;
import com.dmdev.entity.User;
import com.dmdev.integration.IntegrationTestBase;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

class ChartDaoTest extends IntegrationTestBase {

    @Test
    void save() {
        User user = GetEntity.getUser("UUser1");
        Chart chart = GetEntity.getChart("myFirstChart");
        getSourceRepository().save(chart.getSource());
        getUserRepository().save(user);
        chart.setOwner(user);
        getChartRepository().save(chart);
        getSession().clear();

        Chart actualChart = getSession().get(Chart.class, chart.getId());

        assertThat(actualChart).isNotNull();
    }

    @Test
    void update() {
        User user = GetEntity.getUser("UUser1");
        Chart chart = GetEntity.getChart("mySecondChart");
        getSourceRepository().save(chart.getSource());
        getUserRepository().save(user);
        chart.setOwner(user);
        getChartRepository().save(chart);
        User user1 = GetEntity.getUser("UUser2");
        getUserRepository().save(user1);
        chart.setOwner(user1);
        getChartRepository().update(chart);
        getSession().clear();

        Chart actualChart = getSession().get(Chart.class, chart.getId());

        assertThat(actualChart.getOwner().getUsername()).isEqualTo("UUser2");
    }

    @Test
    void findById() {
        User user = GetEntity.getUser("UUser3");
        Chart chart = GetEntity.getChart("myThirdChart");
        getSourceRepository().save(chart.getSource());
        getUserRepository().save(user);
        chart.setOwner(user);
        getChartRepository().save(chart);
        getSession().clear();

        Optional<Chart> findChart = getChartRepository().findById(chart.getId());

        assertThat(findChart).isNotNull();
    }

    @Test
    void delete() {
        User user = GetEntity.getUser("UUser4");
        Chart chart = GetEntity.getChart("myFourthChart");
        getSourceRepository().save(chart.getSource());
        getUserRepository().save(user);
        chart.setOwner(user);
        getChartRepository().save(chart);
        getChartRepository().delete(chart);
        getSession().clear();

        Chart actualChart = getSession().get(Chart.class, chart.getId());

        assertThat(actualChart).isNull();
    }

    @Test
    void getAll() {
        List<Chart> results = getChartRepository().getAll();

        assertThat(results).hasSize(3);

        List<String> chartNames = results.stream().map(Chart::getName).collect(toList());
        assertThat(chartNames).containsExactlyInAnyOrder("PlanFactGF", "DynamicBuilding", "Swap");
    }

    @Test
    void getByUsername() {
        List<Chart> results = getChartRepository().getByUsername("PPetrov");

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getOwner().getUsername()).isEqualTo("PPetrov");
    }

    @Test
    void getByNameSeries() {
        List<Chart> results = getChartRepository().getByNameSeries("Plan");

        assertThat(results).hasSize(2);

        List<String> charts = results.stream().map(Chart::getName).collect(toList());
        assertThat(charts).containsExactlyInAnyOrder("PlanFactGF", "Swap");
    }

    @Test
    void getByParams() {
        ChartDto chartDto = ChartDto.builder()
                .objectBuilding(ObjectBuilding.GF)
                .periodReport(PeriodReport.WEEK)
                .typeBuilding(TypeBuilding.GF_RT_CS)
                .typeReport(TypeReport.STATUS)
                .name("queryTest")
                .build();

        List<Chart> results = getChartRepository().getByParams(chartDto);

        assertThat(results).hasSize(1);

        List<String> charts = results.stream().map(Chart::getName).collect(toList());
        assertThat(charts).containsExactlyInAnyOrder("PlanFactGF");
    }
}