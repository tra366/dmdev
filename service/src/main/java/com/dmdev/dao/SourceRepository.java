package com.dmdev.dao;

import com.dmdev.entity.Source;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class SourceRepository extends RepositoryBase<Integer, Source> {

    public SourceRepository(EntityManager entityManger) {
        super(Source.class, entityManger);
    }
}
