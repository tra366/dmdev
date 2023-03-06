package com.dmdev.integration;

import com.dmdev.GetEntity;
import com.dmdev.entity.Chart;
import com.dmdev.entity.Series;
import com.dmdev.entity.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EntityMappingSeriesIT extends IntegrationTestBase {

    @Test
    void addSeries() {
        User user = GetEntity.getUser("UUser20");
        Series newSeries = GetEntity.getSeries("newSeries");
        session.save(newSeries.getChart().getSource());
        user.addChart(newSeries.getChart());
        session.save(user);
        session.save(newSeries.getSource());
        session.save(newSeries.getNameSeries());
        session.save(newSeries.getTypeSeries());
        session.save(newSeries);
        session.clear();

        Series actualSeries = session.get(Series.class, newSeries.getId());

        assertThat(actualSeries.getId()).isNotNull();
    }

    @Test
    void getSeries() {
        User user = GetEntity.getUser("UUser21");
        Series newSeries = GetEntity.getSeries("newSeries1");
        session.save(newSeries.getChart().getSource());
        user.addChart(newSeries.getChart());
        session.save(user);
        session.save(newSeries.getSource());
        session.save(newSeries.getNameSeries());
        session.save(newSeries.getTypeSeries());
        session.save(newSeries);
        session.clear();

        Series actualSeries = session.get(Series.class, newSeries.getId());

        assertThat(actualSeries).isEqualTo(newSeries);
    }

    @Test
    void updateSeries() {
        User user = GetEntity.getUser("UUser22");
        Series newSeries = GetEntity.getSeries("newSeries2");
        session.save(newSeries.getChart().getSource());
        user.addChart(newSeries.getChart());
        session.save(user);
        session.save(newSeries.getSource());
        session.save(newSeries.getNameSeries());
        session.save(newSeries.getTypeSeries());
        session.save(newSeries);
        newSeries.setName("newSeries_Renamed");
        session.update(newSeries);
        session.flush();
        session.clear();

        Series actualSeries = session.get(Series.class, newSeries.getId());

        assertThat(actualSeries.getName()).isEqualTo("newSeries_Renamed");
    }

    @Test
    void deleteSeries() {
        User user = GetEntity.getUser("UUser23");
        Series newSeries = GetEntity.getSeries("newSeries3");
        session.save(newSeries.getChart().getSource());
        user.addChart(newSeries.getChart());
        session.save(user);
        session.save(newSeries.getSource());
        session.save(newSeries.getNameSeries());
        session.save(newSeries.getTypeSeries());
        session.save(newSeries);
        session.delete(newSeries);
        session.flush();
        session.clear();

        Series actualSeries = session.get(Series.class, newSeries.getId());

        assertThat(actualSeries).isNull();
    }

}
