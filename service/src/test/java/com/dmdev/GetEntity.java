package com.dmdev;

import com.dmdev.entity.Chart;
import com.dmdev.entity.ChartInfo;
import com.dmdev.entity.Matrix;
import com.dmdev.entity.NameSeries;
import com.dmdev.entity.Object;
import com.dmdev.entity.PeriodReport;
import com.dmdev.entity.Role;
import com.dmdev.entity.Series;
import com.dmdev.entity.SeriesInfo;
import com.dmdev.entity.Source;
import com.dmdev.entity.TypeBuilding;
import com.dmdev.entity.TypeReport;
import com.dmdev.entity.TypeSeries;
import com.dmdev.entity.User;

public class GetEntity {

    public static User getUser() {
        return getUser("IIvanov");
    }

    public static User getUser(String username ) {
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

    public static TypeReport getTypeReport(String typeReportName) {
        return TypeReport.builder()
                .name(typeReportName)
                .build();
    }

    public static PeriodReport getPeriodReport(String periodReportName) {
        return PeriodReport.builder()
                .name(periodReportName)
                .build();
    }

    public static Object getObject(String objectName) {
        return Object.builder()
                .name(objectName)
                .build();
    }

    public static TypeBuilding getTypeBuilding(String typeBuildingName) {
        return TypeBuilding.builder()
                .name(typeBuildingName)
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
                .chartInfo(ChartInfo.builder()
                        .typeReport(getTypeReport(chartName+"Динамика"))
                        .periodReport(getPeriodReport(chartName+"Квартал"))
                        .object(getObject(chartName+"БС"))
                        .typeBuilding(getTypeBuilding(chartName+"GreenFiled"))
                        .source(getSource(chartName+"sourceBd"))
                        .build()
                )
                .myguid("myguid1")
                .actuality(true)
                .build();
    }

    public static Series getSeries(String seriesName) {
        return Series.builder()
                .name(seriesName)
                .seriesInfo(SeriesInfo.builder()
                        .chart(getChart(seriesName+"ForChart"))
                        .typeSeries(getTypeSeries(seriesName+"Bar"))
                        .nameSeries(getNameSeries(seriesName+"nameSeriesTest"))
                        .periodReport(getPeriodReport(seriesName+"Квартал"))
                        .source(getSource(seriesName+"sourceBd"))
                        .build()
                )
                .myguid("myguid1")
                .actuality(true)
                .build();
    }

    public static Matrix getMatrix(String matrixName) {
        return Matrix.builder()
                .chartInfo(ChartInfo.builder()
                        .typeReport(getTypeReport(matrixName+"Динамика"))
                        .periodReport(getPeriodReport(matrixName+"Квартал"))
                        .object(getObject(matrixName+"БС"))
                        .typeBuilding(getTypeBuilding(matrixName+"GreenFiled"))
                        .source(getSource(matrixName+"sourceBd"))
                        .build()
                )
                .nameSeries(getNameSeries(matrixName+"nameSeriesTest"))
                .sqlQuery("select column_test from table_test")
                .build();
    }
}
