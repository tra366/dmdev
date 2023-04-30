package com.dmdev.mapper;

import com.dmdev.dto.ChartCreateEditDto;
import com.dmdev.entity.Chart;
import com.dmdev.entity.Source;
import com.dmdev.entity.User;
import com.dmdev.repository.SourceRepository;
import com.dmdev.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ChartCreateEditMapper implements Mapper<ChartCreateEditDto, Chart> {

    private final UserRepository userRepository;
    private final SourceRepository sourceRepository;

    @Override
    public Chart map(ChartCreateEditDto chartDto) {
        Chart chart = new Chart();
        return map(chartDto, chart);
    }

    @Override
    public Chart map(ChartCreateEditDto chartDto, Chart chart) {
        chart.setName(chartDto.getName());
        chart.setOwner(getOwner(chartDto.getOwnerId()));
        chart.setTypeReport(chartDto.getTypeReport());
        chart.setPeriodReport(chartDto.getPeriodReport());
        chart.setObjectBuilding(chartDto.getObjectBuilding());
        chart.setTypeBuilding(chartDto.getTypeBuilding());
        chart.setSource(getSource(chartDto.getSourceId()));

        return chart;
    }

    public User getOwner(Integer ownerId) {
        return Optional.ofNullable(ownerId)
                .flatMap(userRepository::findById)
                .orElse(null);
    }

    public Source getSource(Integer sourceId) {
        return Optional.ofNullable(sourceId)
                .flatMap(sourceRepository::findById)
                .orElse(null);
    }
}
