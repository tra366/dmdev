package com.dmdev.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"avt", "chart", "series"})
@EqualsAndHashCode(exclude = {"avt", "chart", "series"})
@Builder
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"avt_id", "chart_id", "series_id"})})
public class Av implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "avt_id")
    private Avt avt;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "chart_id")
    private Chart chart;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "series_id")
    private Series series;

    private String value;

    public void setAvt(Avt avt) {
        this.avt = avt;
        this.avt.getAves().add(this);
    }

    public void setChart(Chart chart) {
        this.chart = chart;
        this.chart.getAves().add(this);
    }

    public void setSeries(Series series) {
        this.series = series;
        this.series.getAves().add(this);
    }
}
