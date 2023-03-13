package com.dmdev.dao;

import com.dmdev.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Collections.emptyMap;

public interface Repository<K extends Serializable, E extends BaseEntity<K>> {

    E save(E entity);

    void delete(E entity);

    void update(E entity);

    default Optional<E> findById(K id) {
        return findbyId(id, emptyMap());
    }

    Optional<E> findbyId(K id, Map<String, Object> properties);

    List<E> findAll();
}
