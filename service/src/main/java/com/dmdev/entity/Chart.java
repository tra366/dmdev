package com.dmdev.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


import javax.persistence.Embedded;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "owner")
@Builder
@Entity
public class Chart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;
    @Embedded
    private ChartInfo chartInfo;
    @Column(nullable = false)
    private String myguid;
    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean actuality;

    //org.hibernate.AnnotationException: Use of @OneToMany or @ManyToMany targeting an unmapped class: com.dmdev.entity.Chart.seriesinfos[com.dmdev.entity.SeriesInfo]
/*    @Builder.Default
    @OneToMany(mappedBy = "chart", cascade = CascadeType.ALL)
    private Set<SeriesInfo> seriesinfos = new HashSet<>();*/

}