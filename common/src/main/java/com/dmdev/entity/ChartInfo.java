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
public class ChartInfo {

    private Integer typeReportId;
    private Integer periodReportId;
    private Integer objectId;
    private Integer typeBuildingId;
    private Integer sourceId;
 }
