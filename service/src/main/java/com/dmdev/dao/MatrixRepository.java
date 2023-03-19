package com.dmdev.dao;

import com.dmdev.entity.Matrix;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class MatrixRepository extends RepositoryBase<Integer, Matrix> {

    public MatrixRepository(EntityManager entityManger) {
        super(Matrix.class, entityManger);
    }
}
