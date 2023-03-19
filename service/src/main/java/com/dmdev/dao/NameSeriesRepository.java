package com.dmdev.dao;

import com.dmdev.entity.NameSeries;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class NameSeriesRepository extends RepositoryBase<Integer, NameSeries> {

    public NameSeriesRepository(EntityManager entityManger) {
        super(NameSeries.class, entityManger);
    }
}
