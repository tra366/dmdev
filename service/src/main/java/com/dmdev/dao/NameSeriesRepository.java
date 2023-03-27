package com.dmdev.dao;

import com.dmdev.entity.NameSeries;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class NameSeriesRepository extends RepositoryBase<Integer, NameSeries> {

    public NameSeriesRepository(EntityManager entityManger) {
        super(NameSeries.class, entityManger);
    }
}
