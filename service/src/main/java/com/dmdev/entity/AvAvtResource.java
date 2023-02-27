package com.dmdev.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class AvAvtResource {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "avt_id")
    private Avt avt;
    private Integer resourceId;

    //org.hibernate.AnnotationException: Use of @OneToMany or @ManyToMany targeting an unmapped class: com.dmdev.entity.Avt.avAvtResources[com.dmdev.entity.AvAvtResource]
    //через @Embedded AvAvtResource я определяю UNIQUE(avt_id, resource_id), в то же время на unmapping класс нельзя ставить  аннотацию @ManyToOne
    /*public void setAvt(Avt avt) {
        this.avt = avt;
        this.avt.getAvAvtResources().add(this);
    }*/
}
