package com.dmdev.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@NamedEntityGraph(
        name = "withSeries",
        attributeNodes = {
                @NamedAttributeNode(value = "serieses")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"owner", "aves", "serieses", "source"})
@EqualsAndHashCode(exclude = {"owner", "aves", "serieses", "source"})
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

    @Column(nullable = false)
    private String myguid;

    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean actuality;

    @Builder.Default
    @OneToMany(mappedBy = "chart", cascade = CascadeType.ALL)
    private List<Av> aves = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "chart", cascade = CascadeType.ALL)
    private List<Series> serieses = new ArrayList<>();

    public void setSource(Source source) {
        this.source = source;
        this.source.getCharts().add(this);
    }

}