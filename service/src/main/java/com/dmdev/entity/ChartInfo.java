package com.dmdev.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"typeReport", "periodReport", "object", "typeBuilding", "source"})
@Builder
@Embeddable
public class ChartInfo {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TypeReport typeReport;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PeriodReport periodReport;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Object object;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TypeBuilding typeBuilding;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Source source;

    //org.hibernate.AnnotationException: Use of @OneToMany or @ManyToMany targeting an unmapped class: com.dmdev.entity.Object.chartinfos[com.dmdev.entity.ChartInfo]
   /* public void setTypeReport(TypeReport typeReport) {
        this.typeReport = typeReport;
        this.typeReport.getChartinfos().add(this);
    }

    public void setPeriodReport(PeriodReport periodReport) {
        this.periodReport = periodReport;
        this.periodReport.getChartinfos().add(this);
    }

    public void setObject(Object object) {
        this.object = object;
        this.object.getChartinfos().add(this);
    }

    public void setTypeBuilding(TypeBuilding typeBuilding) {
        this.typeBuilding = typeBuilding;
        this.typeBuilding.getChartinfos().add(this);
    }

    public void setSource(Source source) {
        this.source = source;
        this.source.getChartinfos().add(this);
    }*/

}
