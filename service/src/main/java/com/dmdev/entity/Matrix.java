package com.dmdev.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Embedded;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "nameSeries")
@Builder
@Entity
public class Matrix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private ChartInfo chartInfo;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private NameSeries nameSeries;
    private String sqlQuery;

    public void setNameSeries(NameSeries nameSeries) {
        this.nameSeries = nameSeries;
        this.nameSeries.getMatrixs().add(this);
    }
}
