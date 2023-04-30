package com.dmdev.dto;

import com.dmdev.entity.ObjectBuilding;
import com.dmdev.entity.PeriodReport;
import com.dmdev.entity.TypeBuilding;
import com.dmdev.entity.TypeReport;
import com.dmdev.validation.group.CreateAction;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class ChartCreateEditDto {
    String name;
    Integer ownerId;
    TypeReport typeReport;
    PeriodReport periodReport;
    ObjectBuilding objectBuilding;
    TypeBuilding typeBuilding;
    Integer sourceId;
    @NotBlank(groups = CreateAction.class)
    String myguid;
    @NotBlank(groups = CreateAction.class)
    String actuality;
}
