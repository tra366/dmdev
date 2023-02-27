package com.dmdev.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class SeriesInfo {

    @ManyToOne(fetch = FetchType.LAZY)
    private Chart chart;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TypeSeries typeSeries;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private NameSeries nameSeries;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PeriodReport periodReport;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Source source;

    //org.hibernate.AnnotationException: Use of @OneToMany or @ManyToMany targeting an unmapped class: com.dmdev.entity.Chart.seriesinfos[com.dmdev.entity.SeriesInfo]
/*    public void setChart(Chart chart) {
        this.chart = chart;
        this.chart.getSeriesinfos().add(this);
    }

    public void setTypeSeries(TypeSeries typeSeries) {
        this.typeSeries = typeSeries;
        this.typeSeries.getSeriesinfos().add(this);
    }

    public void setNameSeries(NameSeries nameSeries) {
        this.nameSeries = nameSeries;
        this.nameSeries.getSeriesinfos().add(this);
    }

    public void setPeriodReport(PeriodReport periodReport) {
        this.periodReport = periodReport;
        this.periodReport.getSeriesinfos().add(this);
    }

    public void setSource(Source source) {
        this.source = source;
        this.source.getSeriesinfos().add(this);
    }*/
}
