package com.dmdev.mapper;

import com.dmdev.dto.ChartReadDto;
import com.dmdev.dto.SourceReadDto;
import com.dmdev.dto.UserReadDto;
import com.dmdev.entity.Chart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ChartReadMapper implements Mapper<Chart, ChartReadDto> {

    private final UserReadMapper userReadMapper;
    private final SourceReadMapper sourceReadDto;

    @Override
    public ChartReadDto map(Chart object) {
        UserReadDto user = Optional.ofNullable(object.getOwner())
                .map(userReadMapper::map)
                .orElse(null);
        SourceReadDto source = Optional.ofNullable(object.getSource())
                .map(sourceReadDto::map)
                .orElse(null);
        ChartReadDto chartDto = ChartReadDto.builder()
                .name(object.getName())
                .owner(user)
                .typeReport(object.getTypeReport())
                .periodReport(object.getPeriodReport())
                .objectBuilding(object.getObjectBuilding())
                .typeBuilding(object.getTypeBuilding())
                .source(source)
                .build();
        return chartDto;
    }

}
