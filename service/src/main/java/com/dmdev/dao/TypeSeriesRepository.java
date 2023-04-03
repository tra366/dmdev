package com.dmdev.dao;

import com.dmdev.entity.TypeSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeSeriesRepository extends JpaRepository<TypeSeries, Integer> {
}
