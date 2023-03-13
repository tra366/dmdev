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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "aves")
@EqualsAndHashCode(exclude = "aves")
@Builder
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"resource", "name"}))
public class Avt implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Resource resource;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    @Builder.Default
    @OneToMany(mappedBy = "avt", cascade = CascadeType.ALL)
    private List<Av> aves = new ArrayList<>();
}
