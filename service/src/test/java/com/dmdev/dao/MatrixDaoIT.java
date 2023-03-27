package com.dmdev.dao;

import com.dmdev.GetEntity;
import com.dmdev.entity.Matrix;
import com.dmdev.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
class MatrixDaoIT extends IntegrationTestBase {

    private final NameSeriesRepository nameSeriesRepository;
    private final SourceRepository sourceRepository;
    private final MatrixRepository matrixRepository;
    private final EntityManager entityManager;

    @Test
    void save() {
        Matrix newMatrix = GetEntity.getMatrix("newMatrix");
        nameSeriesRepository.save(newMatrix.getNameSeries());
        sourceRepository.save(newMatrix.getSource());
        matrixRepository.save(newMatrix);
        entityManager.clear();

        Matrix actualMatrix = entityManager.find(Matrix.class, newMatrix.getId());

        assertThat(actualMatrix.getId()).isNotNull();
    }

    @Test
    void findById() {
        Matrix newMatrix = GetEntity.getMatrix("newMatrix");
        nameSeriesRepository.save(newMatrix.getNameSeries());
        sourceRepository.save(newMatrix.getSource());
        matrixRepository.save(newMatrix);
        entityManager.clear();

        Optional<Matrix> actualMatrix = matrixRepository.findById(newMatrix.getId());

        assertThat(actualMatrix).isNotNull();
    }

    @Test
    void update() {
        Matrix newMatrix = GetEntity.getMatrix("newMatrix2");
        nameSeriesRepository.save(newMatrix.getNameSeries());
        sourceRepository.save(newMatrix.getSource());
        matrixRepository.save(newMatrix);
        newMatrix.setSqlQuery("newSqlQuery");
        matrixRepository.update(newMatrix);
        entityManager.clear();

        Matrix actualMatrix = entityManager.find(Matrix.class, newMatrix.getId());

        assertThat(actualMatrix.getSqlQuery()).isEqualTo("newSqlQuery");
    }

    @Test
    void delete() {
        Matrix newMatrix = GetEntity.getMatrix("newMatrix3");
        nameSeriesRepository.save(newMatrix.getNameSeries());
        sourceRepository.save(newMatrix.getSource());
        matrixRepository.save(newMatrix);
        matrixRepository.delete(newMatrix);
        entityManager.clear();

        Matrix actualMatrix = entityManager.find(Matrix.class, newMatrix.getId());

        assertThat(actualMatrix).isNull();
    }
}
