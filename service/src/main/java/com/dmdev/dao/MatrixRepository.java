package com.dmdev.dao;

import com.dmdev.entity.Matrix;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class MatrixRepository extends RepositoryBase<Integer, Matrix> {

    public MatrixRepository(EntityManager entityManger) {
        super(Matrix.class, entityManger);
    }
}
