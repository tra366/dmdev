package com.dmdev.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"nameSeries", "source"})
@EqualsAndHashCode(exclude = {"nameSeries", "source"})
@Builder
@Entity
public class Matrix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeReport typeReport;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PeriodReport periodReport;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ObjectBuilding objectBuilding;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeBuilding typeBuilding;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Source source;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private NameSeries nameSeries;

    private String sqlQuery;

    public void setNameSeries(NameSeries nameSeries) {
        this.nameSeries = nameSeries;
        this.nameSeries.getMatrixs().add(this);
    }

    public void setSource(Source source) {
        this.source = source;
        this.source.getMatrixs().add(this);
    }
}
