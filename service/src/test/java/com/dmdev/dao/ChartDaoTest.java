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

public class ChartDaoTest extends IntegrationTestBase {

    @Test
    void save() {
        ChartRepository chartRepository = new ChartRepository(getSession());
        SourceRepository sourceRepository = new SourceRepository(getSession());
        UserRepository userRepository = new UserRepository(getSession());
        User user = GetEntity.getUser("UUser1");
        Chart chart = GetEntity.getChart("myFirstChart");
        sourceRepository.save(chart.getSource());
        userRepository.save(user);
        chart.setOwner(user);
        chartRepository.save(chart);
        getSession().clear();

        Chart actualChart = getSession().get(Chart.class, chart.getId());

        assertThat(actualChart).isNotNull();
    }

    @Test
    void update() {
        ChartRepository chartRepository = new ChartRepository(getSession());
        SourceRepository sourceRepository = new SourceRepository(getSession());
        UserRepository userRepository = new UserRepository(getSession());
        User user = GetEntity.getUser("UUser1");
        Chart chart = GetEntity.getChart("mySecondChart");
        sourceRepository.save(chart.getSource());
        userRepository.save(user);
        chart.setOwner(user);
        chartRepository.save(chart);
        User user1 = GetEntity.getUser("UUser2");
        userRepository.save(user1);
        chart.setOwner(user1);
        chartRepository.update(chart);
        getSession().flush();
        getSession().clear();

        Chart actualChart = getSession().get(Chart.class, chart.getId());

        assertThat(actualChart.getOwner().getUsername()).isEqualTo("UUser2");
    }

    @Test
    void findById() {
        ChartRepository chartRepository = new ChartRepository(getSession());
        SourceRepository sourceRepository = new SourceRepository(getSession());
        UserRepository userRepository = new UserRepository(getSession());
        User user = GetEntity.getUser("UUser3");
        Chart chart = GetEntity.getChart("myThirdChart");
        sourceRepository.save(chart.getSource());
        userRepository.save(user);
        chart.setOwner(user);
        chartRepository.save(chart);
        getSession().clear();

        Optional<Chart> findChart = chartRepository.findById(chart.getId());

        assertThat(findChart).isNotNull();
    }

    @Test
    void delete() {
        ChartRepository chartRepository = new ChartRepository(getSession());
        SourceRepository sourceRepository = new SourceRepository(getSession());
        UserRepository userRepository = new UserRepository(getSession());
        User user = GetEntity.getUser("UUser4");
        Chart chart = GetEntity.getChart("myFourthChart");
        sourceRepository.save(chart.getSource());
        userRepository.save(user);
        chart.setOwner(user);
        chartRepository.save(chart);
        chartRepository.delete(chart);
        getSession().clear();

        Chart actualChart = getSession().get(Chart.class, chart.getId());

        assertThat(actualChart).isNull();
    }

    @Test
    void getAll() {
        ChartRepository chartRepository = new ChartRepository(getSession());

        List<Chart> results = chartRepository.getAll();

        assertThat(results).hasSize(3);

        List<String> chartNames = results.stream().map(Chart::getName).collect(toList());
        assertThat(chartNames).containsExactlyInAnyOrder("PlanFactGF", "DynamicBuilding", "Swap");
    }

    @Test
    void getByUsername() {
        ChartRepository chartRepository = new ChartRepository(getSession());

        List<Chart> results = chartRepository.getByUsername("PPetrov");

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getOwner().getUsername()).isEqualTo("PPetrov");
    }

    @Test
    void getByNameSeries() {
        ChartRepository chartRepository = new ChartRepository(getSession());

        List<Chart> results = chartRepository.getByNameSeries("Plan");

        assertThat(results).hasSize(2);

        List<String> charts = results.stream().map(Chart::getName).collect(toList());
        assertThat(charts).containsExactlyInAnyOrder("PlanFactGF", "Swap");
    }

    @Test
    void getByParams() {
        ChartRepository chartRepository = new ChartRepository(getSession());

        ChartDto chartDto = ChartDto.builder()
                .objectBuilding(ObjectBuilding.GF)
                .periodReport(PeriodReport.WEEK)
                .typeBuilding(TypeBuilding.GF_RT_CS)
                .typeReport(TypeReport.STATUS)
                .name("queryTest")
                .build();

        List<Chart> results = chartRepository.getByParams(chartDto);

        assertThat(results).hasSize(1);

        List<String> charts = results.stream().map(Chart::getName).collect(toList());
        assertThat(charts).containsExactlyInAnyOrder("PlanFactGF");
    }
}