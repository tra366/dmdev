package com.dmdev.dao;

import com.dmdev.entity.Source;

import javax.persistence.EntityManager;

public class SourceRepository extends RepositoryBase<Integer, Source> {

    public SourceRepository(EntityManager entityManger) {
        super(Source.class, entityManger);
    }
}
