package com.dmdev.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"chart", "typeSeries", "nameSeries", "source", "aves"})
@EqualsAndHashCode(exclude = {"chart", "typeSeries", "nameSeries", "source", "aves"})
@Builder
@Entity
public class Series implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Chart chart;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TypeSeries typeSeries;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private NameSeries nameSeries;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PeriodReport periodReport;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Source source;

    @Column(nullable = false)
    private String myguid;

    private Integer sortOrder;

    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean visible;

    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean actuality;

    @Builder.Default
    @OneToMany(mappedBy = "series", cascade = CascadeType.ALL)
    private List<Av> aves = new ArrayList<>();

    public void setChart(Chart chart) {
        this.chart = chart;
        this.chart.getSerieses().add(this);
    }

    public void setTypeSeries(TypeSeries typeSeries) {
        this.typeSeries = typeSeries;
        this.typeSeries.getSerieses().add(this);
    }

    public void setNameSeries(NameSeries nameSeries) {
        this.nameSeries = nameSeries;
        this.nameSeries.getSerieses().add(this);
    }

    public void setSource(Source source) {
        this.source = source;
        this.source.getSerieses().add(this);
    }
}
