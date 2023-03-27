package com.dmdev.dao;

import com.dmdev.entity.Source;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class SourceRepository extends RepositoryBase<Integer, Source> {

    public SourceRepository(EntityManager entityManger) {
        super(Source.class, entityManger);
    }
}
