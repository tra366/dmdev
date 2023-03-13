package com.dmdev.dao;

import com.dmdev.entity.Avt;

import javax.persistence.EntityManager;

public class AvtRepository extends RepositoryBase<Integer, Avt> {

    public AvtRepository(EntityManager entityManager) {
        super(Avt.class, entityManager);
    }
}
