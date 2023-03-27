package com.dmdev.dao;

import com.dmdev.entity.Chart_;
import com.dmdev.entity.TypeBuilding;
import com.dmdev.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserRepository extends RepositoryBase<Integer, User> {

    public UserRepository(EntityManager entityManger) {
        super(User.class, entityManger);
    }

    public List<User> getAll() {
        return findAll();
    }

    public List<User> getByUsername(String username) {
        var cb = getEntityManger().getCriteriaBuilder();

        var criteria = cb.createQuery(User.class);
        var user = criteria.from(User.class);

        criteria.select(user).where(
                cb.equal(user.get("username"), username)
        );

        return getEntityManger().createQuery(criteria)
                .getResultList();
    }

    public List<User> getByTypeBuilding(TypeBuilding typeBuilding) {
        var cb = getEntityManger().getCriteriaBuilder();

        var criteria = cb.createQuery(User.class);
        var user = criteria.from(User.class);
        var charts = user.join("charts");

        criteria.select(user).where(
                cb.equal(charts.get(Chart_.TYPE_BUILDING), typeBuilding)
        );

        return getEntityManger().createQuery(criteria)
                .getResultList();
    }
}
