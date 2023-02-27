package com.dmdev.integration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import com.dmdev.entity.*;
import com.dmdev.GetEntity;

public class EntityMappingIT extends IntegrationTestBase {

    @Test
    void addUser() {
        User newUser = GetEntity.getUser();
        session.save(newUser);
        session.clear();

        User actualUser = session.get(User.class, newUser.getId());

        assertThat(actualUser.getId()).isNotNull();
    }

    @Test
    void getUser() {
        User newUser = GetEntity.getUser("UUser");
        session.save(newUser);
        session.clear();

        User actualUser = session.get(User.class, newUser.getId());

        assertThat(actualUser).isEqualTo(newUser);
    }

    @Test
    void updateUser() {
        User newUser = GetEntity.getUser("APetrov");
        session.save(newUser);
        newUser.setLastName("NewAPetrov");
        session.flush();
        session.clear();

        User actualUser = session.get(User.class, newUser.getId());

        assertThat(actualUser.getLastName()).isEqualTo("NewAPetrov");
    }

    @Test
    void deleteUser() {
        User newUser = GetEntity.getUser("VSidorov");
        session.save(newUser);
        session.delete(newUser);
        session.flush();
        session.clear();

        User actualUser = session.get(User.class, newUser.getId());

        assertThat(actualUser).isNull();
    }

    @Test
    void addChartToNewUser() {
        User user = GetEntity.getUser("UUser1");
        Chart chart = GetEntity.getChart("myFirstChart");
        user.addChart(chart);
        session.save(chart.getChartInfo().getObject());
        session.save(chart.getChartInfo().getSource());
        session.save(chart.getChartInfo().getPeriodReport());
        session.save(chart.getChartInfo().getTypeBuilding());
        session.save(chart.getChartInfo().getTypeReport());
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
        user.addChart(chart);
        session.save(chart.getChartInfo().getObject());
        session.save(chart.getChartInfo().getSource());
        session.save(chart.getChartInfo().getPeriodReport());
        session.save(chart.getChartInfo().getTypeBuilding());
        session.save(chart.getChartInfo().getTypeReport());
        session.save(user);
        session.clear();

        Chart actualChart = session.get(Chart.class, chart.getId());

        assertThat(actualChart).isEqualTo(chart);
    }

    @Test
    void updateChart() {
        User user = GetEntity.getUser("UUser2");
        Chart chart = GetEntity.getChart("myFirstChart2");
        user.addChart(chart);
        session.save(chart.getChartInfo().getObject());
        session.save(chart.getChartInfo().getSource());
        session.save(chart.getChartInfo().getPeriodReport());
        session.save(chart.getChartInfo().getTypeBuilding());
        session.save(chart.getChartInfo().getTypeReport());
        session.save(user);
        User user1 = GetEntity.getUser("UUser3");
        user1.addChart(chart);
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
        session.save(chart.getChartInfo().getObject());
        session.save(chart.getChartInfo().getSource());
        session.save(chart.getChartInfo().getPeriodReport());
        session.save(chart.getChartInfo().getTypeBuilding());
        session.save(chart.getChartInfo().getTypeReport());
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

    @Test
    void addSeries() {
        User user = GetEntity.getUser("UUser20");
        Series newSeries = GetEntity.getSeries("newSeries");
        session.save(newSeries.getSeriesInfo().getNameSeries());
        session.save(newSeries.getSeriesInfo().getSource());
        session.save(newSeries.getSeriesInfo().getPeriodReport());
        session.save(newSeries.getSeriesInfo().getTypeSeries());
        session.save(newSeries.getSeriesInfo().getChart().getChartInfo().getObject());
        session.save(newSeries.getSeriesInfo().getChart().getChartInfo().getSource());
        session.save(newSeries.getSeriesInfo().getChart().getChartInfo().getPeriodReport());
        session.save(newSeries.getSeriesInfo().getChart().getChartInfo().getTypeBuilding());
        session.save(newSeries.getSeriesInfo().getChart().getChartInfo().getTypeReport());
        user.addChart(newSeries.getSeriesInfo().getChart());
        session.save(user);
        session.save(newSeries);
        session.clear();

        Series actualSeries = session.get(Series.class, newSeries.getId());

        assertThat(actualSeries.getId()).isNotNull();
    }

    @Test
    void getSeries() {
        User user = GetEntity.getUser("UUser21");
        Series newSeries = GetEntity.getSeries("newSeries1");
        session.save(newSeries.getSeriesInfo().getNameSeries());
        session.save(newSeries.getSeriesInfo().getSource());
        session.save(newSeries.getSeriesInfo().getPeriodReport());
        session.save(newSeries.getSeriesInfo().getTypeSeries());
        session.save(newSeries.getSeriesInfo().getChart().getChartInfo().getObject());
        session.save(newSeries.getSeriesInfo().getChart().getChartInfo().getSource());
        session.save(newSeries.getSeriesInfo().getChart().getChartInfo().getPeriodReport());
        session.save(newSeries.getSeriesInfo().getChart().getChartInfo().getTypeBuilding());
        session.save(newSeries.getSeriesInfo().getChart().getChartInfo().getTypeReport());
        user.addChart(newSeries.getSeriesInfo().getChart());
        session.save(user);
        session.save(newSeries);
        session.clear();

        Series actualSeries = session.get(Series.class, newSeries.getId());

        assertThat(actualSeries).isEqualTo(newSeries);
    }

    @Test
    void updateSeries() {
        User user = GetEntity.getUser("UUser22");
        Series newSeries = GetEntity.getSeries("newSeries2");
        session.save(newSeries.getSeriesInfo().getNameSeries());
        session.save(newSeries.getSeriesInfo().getSource());
        session.save(newSeries.getSeriesInfo().getPeriodReport());
        session.save(newSeries.getSeriesInfo().getTypeSeries());
        session.save(newSeries.getSeriesInfo().getChart().getChartInfo().getObject());
        session.save(newSeries.getSeriesInfo().getChart().getChartInfo().getSource());
        session.save(newSeries.getSeriesInfo().getChart().getChartInfo().getPeriodReport());
        session.save(newSeries.getSeriesInfo().getChart().getChartInfo().getTypeBuilding());
        session.save(newSeries.getSeriesInfo().getChart().getChartInfo().getTypeReport());
        user.addChart(newSeries.getSeriesInfo().getChart());
        session.save(user);
        session.save(newSeries);
        newSeries.setName("newSeries_Renamed");
        session.flush();
        session.clear();

        Series actualSeries = session.get(Series.class, newSeries.getId());

        assertThat(actualSeries.getName()).isEqualTo("newSeries_Renamed");
    }

    @Test
    void deleteSeries() {
        User user = GetEntity.getUser("UUser23");
        Series newSeries = GetEntity.getSeries("newSeries3");
        session.save(newSeries.getSeriesInfo().getNameSeries());
        session.save(newSeries.getSeriesInfo().getSource());
        session.save(newSeries.getSeriesInfo().getPeriodReport());
        session.save(newSeries.getSeriesInfo().getTypeSeries());
        session.save(newSeries.getSeriesInfo().getChart().getChartInfo().getObject());
        session.save(newSeries.getSeriesInfo().getChart().getChartInfo().getSource());
        session.save(newSeries.getSeriesInfo().getChart().getChartInfo().getPeriodReport());
        session.save(newSeries.getSeriesInfo().getChart().getChartInfo().getTypeBuilding());
        session.save(newSeries.getSeriesInfo().getChart().getChartInfo().getTypeReport());
        user.addChart(newSeries.getSeriesInfo().getChart());
        session.save(user);
        session.save(newSeries);
        session.delete(newSeries);
        session.flush();
        session.clear();

        Series actualSeries = session.get(Series.class, newSeries.getId());

        assertThat(actualSeries).isNull();
    }

    @Test
    void addMatrix() {
        Matrix newMatrix = GetEntity.getMatrix("newMatrix");
        session.save(newMatrix.getChartInfo().getObject());
        session.save(newMatrix.getChartInfo().getSource());
        session.save(newMatrix.getChartInfo().getPeriodReport());
        session.save(newMatrix.getChartInfo().getTypeBuilding());
        session.save(newMatrix.getChartInfo().getTypeReport());
        session.save(newMatrix.getNameSeries());
        session.save(newMatrix);
        session.clear();

        Matrix actualMatrix = session.get(Matrix.class, newMatrix.getId());

        assertThat(actualMatrix.getId()).isNotNull();
    }

    @Test
    void getMatrix() {
        Matrix newMatrix = GetEntity.getMatrix("newMatrix1");
        session.save(newMatrix.getChartInfo().getObject());
        session.save(newMatrix.getChartInfo().getSource());
        session.save(newMatrix.getChartInfo().getPeriodReport());
        session.save(newMatrix.getChartInfo().getTypeBuilding());
        session.save(newMatrix.getChartInfo().getTypeReport());
        session.save(newMatrix.getNameSeries());
        session.save(newMatrix);
        session.clear();

        Matrix actualMatrix = session.get(Matrix.class, newMatrix.getId());

        assertThat(actualMatrix).isEqualTo(newMatrix);
    }

    @Test
    void updateMatrix() {
        Matrix newMatrix = GetEntity.getMatrix("newMatrix2");
        session.save(newMatrix.getChartInfo().getObject());
        session.save(newMatrix.getChartInfo().getSource());
        session.save(newMatrix.getChartInfo().getPeriodReport());
        session.save(newMatrix.getChartInfo().getTypeBuilding());
        session.save(newMatrix.getChartInfo().getTypeReport());
        session.save(newMatrix.getNameSeries());
        session.save(newMatrix);
        newMatrix.setSqlQuery("newSqlQuery");
        session.flush();
        session.clear();

        Matrix actualMatrix = session.get(Matrix.class, newMatrix.getId());

        assertThat(actualMatrix.getSqlQuery()).isEqualTo("newSqlQuery");
    }

    @Test
    void deleteMatrix() {
        Matrix newMatrix = GetEntity.getMatrix("newMatrix3");
        session.save(newMatrix.getChartInfo().getObject());
        session.save(newMatrix.getChartInfo().getSource());
        session.save(newMatrix.getChartInfo().getPeriodReport());
        session.save(newMatrix.getChartInfo().getTypeBuilding());
        session.save(newMatrix.getChartInfo().getTypeReport());
        session.save(newMatrix.getNameSeries());
        session.save(newMatrix);
        session.delete(newMatrix);
        session.flush();
        session.clear();

        Matrix actualMatrix = session.get(Matrix.class, newMatrix.getId());

        assertThat(actualMatrix).isNull();
    }
}