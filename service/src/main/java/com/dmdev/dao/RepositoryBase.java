package com.dmdev.dao;

import com.dmdev.entity.BaseEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class RepositoryBase<K extends Serializable, E extends BaseEntity<K>> implements Repository<K, E> {

    private final Class<E> clazz;
    @Getter
    private final EntityManager entityManger;

    @Override
    public E save(E entity) {
        entityManger.persist(entity);
        return entity;
    }

    @Override
    public void delete(E entity) {
        entityManger.remove(entity);
        entityManger.flush();
    }

    @Override
    public void update(E entity) {
        entityManger.merge(entity);
        entityManger.flush();
    }


    @Override
    public Optional<E> findbyId(K id, Map<String, Object> properties) {
        return Optional.ofNullable(entityManger.find(clazz, id, properties));
    }

    @Override
    public List<E> findAll() {
        CriteriaQuery criteriaQuery = entityManger.getCriteriaBuilder().createQuery(clazz);
        criteriaQuery.from(clazz);
        return entityManger.createQuery(criteriaQuery)
                .getResultList();
    }
}
