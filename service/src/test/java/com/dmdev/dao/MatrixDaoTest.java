package com.dmdev.dao;

import com.dmdev.GetEntity;
import com.dmdev.entity.Matrix;
import com.dmdev.integration.IntegrationTestBase;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MatrixDaoTest extends IntegrationTestBase {

    @Test
    void save() {
        Matrix newMatrix = GetEntity.getMatrix("newMatrix");
        getNameSeriesRepository().save(newMatrix.getNameSeries());
        getSourceRepository().save(newMatrix.getSource());
        getMatrixRepository().save(newMatrix);
        getSession().clear();

        Matrix actualMatrix = getSession().get(Matrix.class, newMatrix.getId());

        assertThat(actualMatrix.getId()).isNotNull();
    }

    @Test
    void findById() {
        Matrix newMatrix = GetEntity.getMatrix("newMatrix");
        getNameSeriesRepository().save(newMatrix.getNameSeries());
        getSourceRepository().save(newMatrix.getSource());
        getMatrixRepository().save(newMatrix);
        getSession().clear();

        Optional<Matrix> actualMatrix = getMatrixRepository().findById(newMatrix.getId());

        assertThat(actualMatrix).isNotNull();
    }

    @Test
    void update() {
        Matrix newMatrix = GetEntity.getMatrix("newMatrix2");
        getNameSeriesRepository().save(newMatrix.getNameSeries());
        getSourceRepository().save(newMatrix.getSource());
        getMatrixRepository().save(newMatrix);
        newMatrix.setSqlQuery("newSqlQuery");
        getMatrixRepository().update(newMatrix);
        getSession().clear();

        Matrix actualMatrix = getSession().get(Matrix.class, newMatrix.getId());

        assertThat(actualMatrix.getSqlQuery()).isEqualTo("newSqlQuery");
    }

    @Test
    void delete() {
        Matrix newMatrix = GetEntity.getMatrix("newMatrix3");
        getNameSeriesRepository().save(newMatrix.getNameSeries());
        getSourceRepository().save(newMatrix.getSource());
        getMatrixRepository().save(newMatrix);
        getMatrixRepository().delete(newMatrix);
        getSession().clear();

        Matrix actualMatrix = getSession().get(Matrix.class, newMatrix.getId());

        assertThat(actualMatrix).isNull();
    }
}
