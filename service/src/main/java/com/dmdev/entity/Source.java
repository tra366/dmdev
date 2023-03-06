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
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"charts", "serieses", "matrixs"})
@EqualsAndHashCode(exclude = {"charts", "serieses", "matrixs"})
@Builder
@Entity
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "source", cascade = CascadeType.ALL)
    private List<Chart> charts = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "source", cascade = CascadeType.ALL)
    private List<Series> serieses = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "source", cascade = CascadeType.ALL)
    private List<Matrix> matrixs = new ArrayList<>();

}
