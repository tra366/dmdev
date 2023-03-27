package com.dmdev.dao;

import com.dmdev.entity.TypeSeries;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class TypeSeriesRepository extends RepositoryBase<Integer, TypeSeries> {

    public TypeSeriesRepository(EntityManager entityManger) {
        super(TypeSeries.class, entityManger);
    }
}
