package com.dmdev.integration;

import com.dmdev.GetEntity;
import com.dmdev.entity.Chart;
import com.dmdev.entity.User;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EntityMappingChartIT extends IntegrationTestBase {

    private Session session = getSession();

    @Test
    void addChartToNewUser() {
        User user = GetEntity.getUser("UUser1");
        Chart chart = GetEntity.getChart("myFirstChart");
        session.save(chart.getSource());
        user.addChart(chart);
        session.save(user);
        session.clear();

        Chart actualChart = session.get(Chart.class, chart.getId());

        assertThat(actualChart).isNotNull();
        assertThat(actualChart.getOwner().getUsername()).isEqualTo("UUser1");
    }

    @Test
    void getChart() {
        User user = GetEntity.getUser("UUser5");
        Chart chart = GetEntity.getChart("myFirstChart1");
        session.save(chart.getSource());
        user.addChart(chart);
        session.save(user);
        session.clear();

        Chart actualChart = session.get(Chart.class, chart.getId());

        assertThat(actualChart).isEqualTo(chart);
    }

    @Test
    void updateChart() {
        User user = GetEntity.getUser("UUser2");
        Chart chart = GetEntity.getChart("myFirstChart2");
        session.save(chart.getSource());
        user.addChart(chart);
        session.save(user);

        User user1 = GetEntity.getUser("UUser3");
        user1.addChart(chart);
        session.update(chart);
        session.save(user1);
        session.flush();
        session.clear();

        Chart actualChart = session.get(Chart.class, chart.getId());

        assertThat(actualChart.getOwner().getUsername()).isEqualTo("UUser3");
    }

    @Test
    void deleteChart() {
        User user = GetEntity.getUser("UUser4");
        Chart chart = GetEntity.getChart("myFirstChart3");
        session.save(chart.getSource());
        user.addChart(chart);
        session.save(user);
        //  Boolean removed = user.getCharts().remove(chart); Почему не срабатывает ID объекта одинаковые
        user.getCharts().clear();
        session.delete(chart);
        session.flush();
        session.clear();

        Chart actualChart = session.get(Chart.class, chart.getId());

        assertThat(actualChart).isNull();
    }

}