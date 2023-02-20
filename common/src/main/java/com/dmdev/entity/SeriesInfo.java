package com.dmdev.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class SeriesInfo {

    private Integer chartId;
    private Integer typeSeriesId;
    private Integer nameSeriesId;
    private Integer periodReportId;
    private Integer sourceId;
}
