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
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "matrixs")
@EqualsAndHashCode(exclude = "matrixs")
@Builder
@Entity

public class NameSeries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    private String name;

    //org.hibernate.AnnotationException: Use of @OneToMany or @ManyToMany targeting an unmapped class: com.dmdev.entity.NameSeries.seriesinfos[com.dmdev.entity.SeriesInfo]
 /*   @Builder.Default
    @OneToMany(mappedBy = "nameSeries", cascade = CascadeType.ALL)
    private Set<SeriesInfo> seriesinfos = new HashSet<>();
*/
    @OneToMany(mappedBy = "nameSeries", cascade = CascadeType.ALL)
    private Set<Matrix> matrixs = new HashSet<>();
}
