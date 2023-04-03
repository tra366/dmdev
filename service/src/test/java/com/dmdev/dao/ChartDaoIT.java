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
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
class ChartDaoIT extends IntegrationTestBase {

    private final UserRepository userRepository;
    private final SourceRepository sourceRepository;
    private final ChartRepository chartRepository;
    private final EntityManager entityManager;

    @Test
    void save() {
        User user = GetEntity.getUser("UUser1");
        Chart chart = GetEntity.getChart("myFirstChart");
        sourceRepository.save(chart.getSource());
        userRepository.save(user);
        chart.setOwner(user);
        chartRepository.save(chart);
        entityManager.clear();

        Chart actualChart = entityManager.find(Chart.class, chart.getId());

        assertThat(actualChart).isNotNull();
    }

    @Test
    void update() {
        User user = GetEntity.getUser("UUser1");
        Chart chart = GetEntity.getChart("mySecondChart");
        sourceRepository.save(chart.getSource());
        userRepository.save(user);
        chart.setOwner(user);
        chartRepository.save(chart);
        User user1 = GetEntity.getUser("UUser2");
        userRepository.save(user1);
        chart.setOwner(user1);
        chartRepository.save(chart);
        entityManager.clear();

        Chart actualChart = entityManager.find(Chart.class, chart.getId());

        assertThat(actualChart.getOwner().getUsername()).isEqualTo("UUser2");
    }

    @Test
    void findById() {
        User user = GetEntity.getUser("UUser3");
        Chart chart = GetEntity.getChart("myThirdChart");
        sourceRepository.save(chart.getSource());
        userRepository.save(user);
        chart.setOwner(user);
        chartRepository.save(chart);
        entityManager.clear();

        Optional<Chart> findChart = chartRepository.findById(chart.getId());

        assertThat(findChart).isNotNull();
    }

    @Test
    void delete() {
        User user = GetEntity.getUser("UUser4");
        Chart chart = GetEntity.getChart("myFourthChart");
        sourceRepository.save(chart.getSource());
        userRepository.save(user);
        chart.setOwner(user);
        chartRepository.save(chart);
        chartRepository.delete(chart);
        entityManager.clear();

        Chart actualChart = entityManager.find(Chart.class, chart.getId());

        assertThat(actualChart).isNull();
    }

    @Test
    void getAll() {
        List<Chart> results = chartRepository.findAll();

        assertThat(results).hasSize(3);

        List<String> chartNames = results.stream().map(Chart::getName).collect(toList());
        assertThat(chartNames).containsExactlyInAnyOrder("PlanFactGF", "DynamicBuilding", "Swap");
    }

    @Test
    void getByUsername() {
        List<Chart> results = chartRepository.findByUsername("PPetrov");

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getOwner().getUsername()).isEqualTo("PPetrov");
    }

    @Test
    void getByNameSeries() {
        List<Chart> results = chartRepository.findByNameSeries("Plan");

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

        List<Chart> results = chartRepository.findByParams(chartDto);

        assertThat(results).hasSize(1);

        List<String> charts = results.stream().map(Chart::getName).collect(toList());
        assertThat(charts).containsExactlyInAnyOrder("PlanFactGF");
    }
}