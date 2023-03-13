package com.dmdev.dao;

import com.dmdev.entity.NameSeries;

import javax.persistence.EntityManager;

public class NameSeriesRepository extends RepositoryBase<Integer, NameSeries> {

    public NameSeriesRepository(EntityManager entityManger) {
        super(NameSeries.class, entityManger);
    }
}
