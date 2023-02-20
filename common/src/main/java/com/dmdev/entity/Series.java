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

public class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    @Embedded
    private SeriesInfo seriesInfo;
    private Integer avId;
    private Integer sortOrder;
    private Boolean visible;
    private Boolean actuality;
}
