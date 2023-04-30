package com.dmdev.mapper;

import com.dmdev.dto.SourceReadDto;
import com.dmdev.entity.Series;
import org.springframework.stereotype.Component;

@Component
public class SeriesReadMapper implements Mapper<Series, SourceReadDto> {

    @Override
    public SourceReadDto map(Series user) {
        return new SourceReadDto(
                user.getId(),
                user.getName()
        );
    }

}
