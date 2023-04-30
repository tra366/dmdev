package com.dmdev.mapper;

import com.dmdev.dto.SourceReadDto;
import com.dmdev.entity.Source;
import org.springframework.stereotype.Component;

@Component
public class SourceReadMapper implements Mapper<Source, SourceReadDto> {

    @Override
    public SourceReadDto map(Source source) {
        return new SourceReadDto(
                source.getId(),
                source.getName()
        );
    }

}
