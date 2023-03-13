package com.dmdev.dao;

import com.dmdev.entity.Chart_;
import com.dmdev.entity.TypeBuilding;
import com.dmdev.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao {

    private static final UserDao INSTANCE = new UserDao();

    public static UserDao getInstance() {
        return INSTANCE;
    }

    public List<User> getAll(Session session) {

        var cb = session.getCriteriaBuilder();
        var criteria = cb.createQuery(User.class);
        var user = criteria.from(User.class);

        criteria.select(user);

        return session.createQuery(criteria)
                .list();
    }

    public List<User> getByUsername(Session session, String username) {
        var cb = session.getCriteriaBuilder();

        var criteria = cb.createQuery(User.class);
        var user = criteria.from(User.class);

        criteria.select(user).where(
                cb.equal(user.get("username"), username)
        );

        return session.createQuery(criteria)
                .list();
    }

    public List<User> getByTypeBuilding(Session session, TypeBuilding typeBuilding) {
        var cb = session.getCriteriaBuilder();

        var criteria = cb.createQuery(User.class);
        var user = criteria.from(User.class);
        var charts = user.join("charts");

        criteria.select(user).where(
                cb.equal(charts.get(Chart_.TYPE_BUILDING), typeBuilding)
        );

        return session.createQuery(criteria)
                .list();
    }


}
