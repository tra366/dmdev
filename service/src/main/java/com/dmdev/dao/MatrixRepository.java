package com.dmdev.dao;

import com.dmdev.entity.Matrix;

import javax.persistence.EntityManager;

public class MatrixRepository extends RepositoryBase<Integer, Matrix> {

    public MatrixRepository(EntityManager entityManger) {
        super(Matrix.class, entityManger);
    }
}
