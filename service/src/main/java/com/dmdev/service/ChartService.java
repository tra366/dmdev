package com.dmdev.service;

import com.dmdev.dto.ChartCreateEditDto;
import com.dmdev.dto.ChartReadDto;
import com.dmdev.mapper.ChartCreateEditMapper;
import com.dmdev.mapper.ChartReadMapper;
import com.dmdev.repository.ChartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChartService{

    private final ChartRepository chartRepository;
    private final ChartReadMapper chartReadMapper;
    private final ChartCreateEditMapper chartCreateEditMapper;

    public List<ChartReadDto> findAll() {
        return chartRepository.findAll().stream()
                .map(chartReadMapper::map)
                .toList();
    }

    public Optional<ChartReadDto> findById(Integer id) {
        return chartRepository.findById(id)
                .map(chartReadMapper::map);
    }

    @Transactional
    public ChartReadDto create(ChartCreateEditDto userDto) {
        return Optional.of(userDto)
                .map(chartCreateEditMapper::map)
                .map(chartRepository::save)
                .map(chartReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<ChartReadDto> update(Integer id, ChartCreateEditDto chartDto) {
        return chartRepository.findById(id)
                .map(entity -> chartCreateEditMapper.map(chartDto, entity))
                .map(chartRepository::saveAndFlush)
                .map(chartReadMapper::map);
    }

    @Transactional
    public Boolean delete(Integer id) {
        return chartRepository.findById(id)
                .map(entity -> {
                    chartRepository.delete(entity);
                    chartRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
