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
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class AvtResourceTypeIdName {

    @Column(nullable = false)
    private Integer resourceTypeId;
    @Column(nullable = false)
    private String name;

}
