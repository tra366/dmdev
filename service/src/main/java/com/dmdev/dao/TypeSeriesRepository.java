package com.dmdev.dao;

import com.dmdev.entity.TypeSeries;

import javax.persistence.EntityManager;

public class TypeSeriesRepository extends RepositoryBase<Integer, TypeSeries> {

    public TypeSeriesRepository(EntityManager entityManger) {
        super(TypeSeries.class, entityManger);
    }
}
