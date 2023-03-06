package com.dmdev.dao;

import com.dmdev.entity.Chart;
import com.dmdev.entity.ObjectBuilding;
import com.dmdev.entity.PeriodReport;
import com.dmdev.entity.TypeBuilding;
import com.dmdev.entity.TypeReport;
import com.dmdev.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChartDao {

    private static final ChartDao INSTANCE = new ChartDao();
    public static ChartDao getInstance() {
        return INSTANCE;
    }

    public List<Chart> getAll(Session session){

        var cb = session.getCriteriaBuilder();
        var criteria = cb.createQuery(Chart.class);
        var chart = criteria.from(Chart.class);

        criteria.select(chart);

        return session.createQuery(criteria)
                .list();
    }

    public List<Chart> getByUsername(Session session, String username) {
        var cb = session.getCriteriaBuilder();

        var criteria = cb.createQuery(Chart.class);
        var chart = criteria.from(Chart.class);
        var user = chart.join("owner");

        criteria.select(chart).where(
                cb.equal(user.get("username"), username)
        );

        return session.createQuery(criteria)
                .list();
    }

    public List<Chart> getByNameSeries(Session session, String nameSeries) {
        var cb = session.getCriteriaBuilder();

        var criteria = cb.createQuery(Chart.class);
        var chart = criteria.from(Chart.class);
        var serieses = chart.join("serieses");

        criteria.select(chart).where(
                cb.equal(serieses.get("nameSeries").get("name"), nameSeries)
        );

        return session.createQuery(criteria)
                .list();
    }

    public List<Chart> getByParams(Session session, String typeReport, String periodReport, String objectBuilding, String typeBuilding) {
        var cb = session.getCriteriaBuilder();

        var criteria = cb.createQuery(Chart.class);
        var chart = criteria.from(Chart.class);
        List<Predicate> predicates = new ArrayList<>();
        if (typeReport != null) {
            predicates.add(cb.equal(chart.get("typeReport"), TypeReport.valueOf(typeReport)));
        }
        if (periodReport != null) {
            predicates.add(cb.equal(chart.get("periodReport"), PeriodReport.valueOf(periodReport)));
        }
        if (objectBuilding != null) {
            predicates.add(cb.equal(chart.get("objectBuilding"), ObjectBuilding.valueOf(objectBuilding)));
        }
        if (typeBuilding != null) {
            predicates.add(cb.equal(chart.get("typeBuilding"), TypeBuilding.valueOf(typeBuilding)));
        }

        criteria.select(chart).where(
                predicates.toArray(Predicate[]::new)
        );


        return session.createQuery(criteria)
                .list();
    }
}
