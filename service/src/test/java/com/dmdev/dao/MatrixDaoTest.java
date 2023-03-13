package com.dmdev.dao;

import com.dmdev.GetEntity;
import com.dmdev.entity.Matrix;
import com.dmdev.integration.IntegrationTestBase;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class MatrixDaoTest extends IntegrationTestBase {

    @Test
    void save() {
        MatrixRepository matrixRepository = new MatrixRepository(getSession());
        SourceRepository sourceRepository = new SourceRepository(getSession());
        NameSeriesRepository nameSeriesRepository = new NameSeriesRepository((getSession()));
        Matrix newMatrix = GetEntity.getMatrix("newMatrix");
        nameSeriesRepository.save(newMatrix.getNameSeries());
        sourceRepository.save(newMatrix.getSource());
        matrixRepository.save(newMatrix);
        getSession().clear();

        Matrix actualMatrix = getSession().get(Matrix.class, newMatrix.getId());

        assertThat(actualMatrix.getId()).isNotNull();
    }

    @Test
    void findById() {
        MatrixRepository matrixRepository = new MatrixRepository(getSession());
        SourceRepository sourceRepository = new SourceRepository(getSession());
        NameSeriesRepository nameSeriesRepository = new NameSeriesRepository((getSession()));
        Matrix newMatrix = GetEntity.getMatrix("newMatrix");
        nameSeriesRepository.save(newMatrix.getNameSeries());
        sourceRepository.save(newMatrix.getSource());
        matrixRepository.save(newMatrix);
        getSession().clear();

        Optional<Matrix> actualMatrix = matrixRepository.findById(newMatrix.getId());

        assertThat(actualMatrix).isNotNull();
    }

    @Test
    void update() {
        MatrixRepository matrixRepository = new MatrixRepository(getSession());
        SourceRepository sourceRepository = new SourceRepository(getSession());
        NameSeriesRepository nameSeriesRepository = new NameSeriesRepository((getSession()));
        Matrix newMatrix = GetEntity.getMatrix("newMatrix2");
        nameSeriesRepository.save(newMatrix.getNameSeries());
        sourceRepository.save(newMatrix.getSource());
        matrixRepository.save(newMatrix);
        newMatrix.setSqlQuery("newSqlQuery");
        matrixRepository.update(newMatrix);
        getSession().flush();
        getSession().clear();

        Matrix actualMatrix = getSession().get(Matrix.class, newMatrix.getId());

        assertThat(actualMatrix.getSqlQuery()).isEqualTo("newSqlQuery");
    }

    @Test
    void delete() {
        MatrixRepository matrixRepository = new MatrixRepository(getSession());
        SourceRepository sourceRepository = new SourceRepository(getSession());
        NameSeriesRepository nameSeriesRepository = new NameSeriesRepository((getSession()));
        Matrix newMatrix = GetEntity.getMatrix("newMatrix3");
        nameSeriesRepository.save(newMatrix.getNameSeries());
        sourceRepository.save(newMatrix.getSource());
        matrixRepository.save(newMatrix);
        matrixRepository.delete(newMatrix);
        getSession().clear();

        Matrix actualMatrix = getSession().get(Matrix.class, newMatrix.getId());

        assertThat(actualMatrix).isNull();
    }
}
