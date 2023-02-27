package com.dmdev.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class PeriodReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    private String name;
    private Integer sort_order;

    //org.hibernate.AnnotationException: Use of @OneToMany or @ManyToMany targeting an unmapped class: com.dmdev.entity.Object.chartinfos[com.dmdev.entity.ChartInfo]
 /*   @Builder.Default
    @OneToMany(mappedBy = "periodReport", cascade = CascadeType.ALL)
    private Set<ChartInfo> chartinfos = new HashSet<>();
    @Builder.Default
    @OneToMany(mappedBy = "periodReport", cascade = CascadeType.ALL)
    private Set<SeriesInfo> seriesinfos = new HashSet<>();*/
}

