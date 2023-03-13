package com.dmdev.dao;

import com.dmdev.entity.Av;

import javax.persistence.EntityManager;

public class AvRepository extends RepositoryBase<Integer, Av> {

    public AvRepository(EntityManager entityManager) {
        super(Av.class, entityManager);
    }
}
