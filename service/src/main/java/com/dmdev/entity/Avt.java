package com.dmdev.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
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

public class Avt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Embedded
    @Column(unique = true)
    private AvtResourceTypeIdName avtResourceTypeIdName;
    @Column(nullable = false)
    private String type;

    //org.hibernate.AnnotationException: Use of @OneToMany or @ManyToMany targeting an unmapped class: com.dmdev.entity.Avt.avAvtResources[com.dmdev.entity.AvAvtResource]
    //через @Embedded AvAvtResource я определяю UNIQUE(avt_id, resource_id), в то же время на unmapping класс нельзя ставить  аннотацию @ManyToOne

  /*  @Builder.Default
    @OneToMany(mappedBy = "avt", cascade = CascadeType.ALL)
    private Set<AvAvtResource> avAvtResources = new HashSet<>();
*/

}
