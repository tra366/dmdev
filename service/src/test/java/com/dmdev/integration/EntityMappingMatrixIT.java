package com.dmdev.integration;

import com.dmdev.GetEntity;
import com.dmdev.entity.Matrix;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EntityMappingMatrixIT extends IntegrationTestBase {

    private Session session = getSession();

    @Test
    void addMatrix() {
        Matrix newMatrix = GetEntity.getMatrix("newMatrix");
        session.save(newMatrix.getNameSeries());
        session.save(newMatrix.getSource());
        session.save(newMatrix);
        session.clear();

        Matrix actualMatrix = session.get(Matrix.class, newMatrix.getId());

        assertThat(actualMatrix.getId()).isNotNull();
    }

    @Test
    void getMatrix() {
        Matrix newMatrix = GetEntity.getMatrix("newMatrix1");
        session.save(newMatrix.getNameSeries());
        session.save(newMatrix.getSource());
        session.save(newMatrix);
        session.clear();

        Matrix actualMatrix = session.get(Matrix.class, newMatrix.getId());

        assertThat(actualMatrix).isEqualTo(newMatrix);
    }

    @Test
    void updateMatrix() {
        Matrix newMatrix = GetEntity.getMatrix("newMatrix2");
        session.save(newMatrix.getNameSeries());
        session.save(newMatrix.getSource());
        session.save(newMatrix);
        newMatrix.setSqlQuery("newSqlQuery");
        session.update(newMatrix);
        session.flush();
        session.clear();

        Matrix actualMatrix = session.get(Matrix.class, newMatrix.getId());

        assertThat(actualMatrix.getSqlQuery()).isEqualTo("newSqlQuery");
    }

    @Test
    void deleteMatrix() {
        Matrix newMatrix = GetEntity.getMatrix("newMatrix3");
        session.save(newMatrix.getNameSeries());
        session.save(newMatrix.getSource());
        session.save(newMatrix);
        session.delete(newMatrix);
        session.flush();
        session.clear();

        Matrix actualMatrix = session.get(Matrix.class, newMatrix.getId());

        assertThat(actualMatrix).isNull();
    }
}
