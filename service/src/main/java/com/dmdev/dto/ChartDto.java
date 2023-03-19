package com.dmdev.dto;

import com.dmdev.entity.ObjectBuilding;
import com.dmdev.entity.PeriodReport;
import com.dmdev.entity.Source;
import com.dmdev.entity.TypeBuilding;
import com.dmdev.entity.TypeReport;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ChartDto {
    String name;
    TypeReport typeReport;
    PeriodReport periodReport;
    ObjectBuilding objectBuilding;
    TypeBuilding typeBuilding;
    Source source;
}
