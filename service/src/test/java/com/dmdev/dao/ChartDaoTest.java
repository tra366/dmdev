package com.dmdev.dao;

import com.dmdev.entity.Chart;
import com.dmdev.entity.ObjectBuilding;
import com.dmdev.entity.PeriodReport;
import com.dmdev.entity.TypeBuilding;
import com.dmdev.entity.TypeReport;
import com.dmdev.entity.User;
import com.dmdev.integration.IntegrationTestBase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class ChartDaoTest extends IntegrationTestBase {

    @Test
    void getAll() {
        List<Chart> results = chartDao.getAll(session);

        assertThat(results).hasSize(3);

        List<String> chartNames = results.stream().map(Chart::getName).collect(toList());
        assertThat(chartNames).containsExactlyInAnyOrder("PlanFactGF", "DynamicBuilding", "Swap");
    }

    @Test
    void getByUsername() {
        List<Chart> results = chartDao.getByUsername(session, "PPetrov");

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getOwner().getUsername()).isEqualTo("PPetrov");
    }

    @Test
    void getByNameSeries() {
        List<Chart> results = chartDao.getByNameSeries(session, "Plan");

        assertThat(results).hasSize(2);

        List<String> charts = results.stream().map(Chart::getName).collect(toList());
        assertThat(charts).containsExactlyInAnyOrder("PlanFactGF", "Swap");
    }

    @Test
    void getByParams() {
        List<Chart> results = chartDao.getByParams(session, "STATUS", "WEEK", "GF", "GF_RT_CS");

        assertThat(results).hasSize(1);

        List<String> charts = results.stream().map(Chart::getName).collect(toList());
        assertThat(charts).containsExactlyInAnyOrder("PlanFactGF");
    }
}