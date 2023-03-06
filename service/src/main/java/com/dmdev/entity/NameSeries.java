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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"matrixs", "serieses"})
@EqualsAndHashCode(exclude = {"matrixs", "serieses"})
@Builder
@Entity
public class NameSeries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "nameSeries", cascade = CascadeType.ALL)
    private List<Series> serieses= new ArrayList<>();

    @OneToMany(mappedBy = "nameSeries", cascade = CascadeType.ALL)
    private List<Matrix> matrixs = new ArrayList<>();
}
