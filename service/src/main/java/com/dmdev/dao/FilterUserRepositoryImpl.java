package com.dmdev.dao;

import com.dmdev.entity.Chart_;
import com.dmdev.entity.TypeBuilding;
import com.dmdev.entity.User;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private final EntityManager entityManager;

    public List<User> findByTypeBuilding(TypeBuilding typeBuilding) {
        var cb = entityManager.getCriteriaBuilder();

        var criteria = cb.createQuery(User.class);
        var user = criteria.from(User.class);
        var charts = user.join("charts");

        criteria.select(user).where(
                cb.equal(charts.get(Chart_.TYPE_BUILDING), typeBuilding)
        );

        return entityManager.createQuery(criteria)
                .getResultList();
    }
}
