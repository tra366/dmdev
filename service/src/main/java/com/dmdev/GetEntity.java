package com.dmdev;

import com.dmdev.entity.Chart;
import com.dmdev.entity.Matrix;
import com.dmdev.entity.NameSeries;
import com.dmdev.entity.ObjectBuilding;
import com.dmdev.entity.PeriodReport;
import com.dmdev.entity.Role;
import com.dmdev.entity.Series;
import com.dmdev.entity.Source;
import com.dmdev.entity.TypeBuilding;
import com.dmdev.entity.TypeReport;
import com.dmdev.entity.TypeSeries;
import com.dmdev.entity.User;

public class GetEntity {

    public static User getUser() {
        return getUser("IIvanov");
    }

    public static User getUser(String username) {
        return User.builder()
                .username(username)
                .firstName("Ivan")
                .lastName("Ivanov")
                .role(Role.BUILDER)
                .password("123")
                .build();
    }

    public static TypeSeries getTypeSeries(String typeSeries) {
        return TypeSeries.builder()
                .name(typeSeries)
                .build();
    }

    public static NameSeries getNameSeries(String nameSeries) {
        return NameSeries.builder()
                .name(nameSeries)
                .build();
    }

    public static Source getSource(String sourceName) {
        return Source.builder()
                .name(sourceName)
                .build();
    }

    public static Chart getChart(String chartName) {
        return Chart.builder()
                .name(chartName)
                .typeReport(TypeReport.DYNAMIC)
                .periodReport(PeriodReport.WEEK)
                .objectBuilding(ObjectBuilding.GF)
                .typeBuilding(TypeBuilding.GF_RT_CS)
                .source(getSource(chartName + "sourceBd"))
                .myguid("myguid1")
                .actuality(true)
                .build();
    }

    public static Series getSeries(String seriesName) {
        return Series.builder()
                .name(seriesName)
                .chart(getChart(seriesName + "ForChart"))
                .typeSeries(getTypeSeries(seriesName + "Bar"))
                .nameSeries(getNameSeries(seriesName + "nameSeriesTest"))
                .periodReport(PeriodReport.WEEK)
                .source(getSource(seriesName + "sourceBd"))
                .myguid("myguid1")
                .actuality(true)
                .build();
    }

    public static Matrix getMatrix(String matrixName) {
        return Matrix.builder()
                .typeReport(TypeReport.DYNAMIC)
                .periodReport(PeriodReport.WEEK)
                .objectBuilding(ObjectBuilding.GF)
                .typeBuilding(TypeBuilding.GF_RT_CS)
                .source(getSource(matrixName + "sourceBd"))
                .nameSeries(getNameSeries(matrixName + "nameSeriesTest"))
                .sqlQuery("select column_test from table_test")
                .build();
    }
}
