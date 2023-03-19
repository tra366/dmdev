package com.dmdev.dao;

import com.dmdev.entity.TypeSeries;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class TypeSeriesRepository extends RepositoryBase<Integer, TypeSeries> {

    public TypeSeriesRepository(EntityManager entityManger) {
        super(TypeSeries.class, entityManger);
    }
}
