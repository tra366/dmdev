package com.dmdev.dao;

import com.dmdev.entity.NameSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NameSeriesRepository extends JpaRepository<NameSeries, Integer> {
}
