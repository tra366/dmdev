package com.dmdev.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


import javax.persistence.Embedded;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

public class Chart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer ownerId;
    @Embedded
    private ChartInfo chartInfo;
    private Integer avId;
    private String myguid;
    private Boolean actuality;
}