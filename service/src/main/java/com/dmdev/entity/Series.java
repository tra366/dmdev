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
    @Column(nullable = false)
    private String name;
    @Embedded
    private SeriesInfo seriesInfo;
    @Column(nullable = false)
    private String myguid;
    private Integer sortOrder;
    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean visible;
    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean actuality;
}
