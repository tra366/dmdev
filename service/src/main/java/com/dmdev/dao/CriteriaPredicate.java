package com.dmdev.dao;

import org.apache.commons.lang3.ObjectUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CriteriaPredicate {

    private final List<Predicate> predicates = new ArrayList<>();

    public static CriteriaPredicate builder() {
        return new CriteriaPredicate();
    }

    public <T> CriteriaPredicate add(T object, Function<T, Predicate> function) {
        if (ObjectUtils.isNotEmpty(object)) {
            predicates.add(function.apply(object));
        }
        return this;
    }

    public List<Predicate> build() {
        return predicates;
    }

}
