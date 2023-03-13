package com.dmdev.dao;

import com.dmdev.dto.ChartDto;
import com.dmdev.entity.Chart;
import com.dmdev.entity.Chart_;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import javax.persistence.criteria.Predicate;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChartDao {
    private static final ChartDao INSTANCE = new ChartDao();

    public static ChartDao getInstance() {
        return INSTANCE;
    }

    public List<Chart> getAll(Session session) {

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

    public List<Chart> getByParams(Session session, ChartDto chartDto) {
        var cb = session.getCriteriaBuilder();

        var criteria = cb.createQuery(Chart.class);
        var chart = criteria.from(Chart.class);

        var citeriaPredicates = CriteriaPredicate.builder()
                .add(chartDto.typeReport, it -> cb.equal(chart.get(Chart_.typeReport), it))
                .add(chartDto.periodReport, it -> cb.equal(chart.get(Chart_.periodReport), it))
                .add(chartDto.objectBuilding, it -> cb.equal(chart.get(Chart_.objectBuilding), it))
                .add(chartDto.typeBuilding, it -> cb.equal(chart.get(Chart_.typeBuilding), it))
                .build();

/*        List<Predicate> predicates = new ArrayList<>();
        if (chartDto.typeReport != null) {
            predicates.add(cb.equal(chart.get("typeReport"), chartDto.typeReport));
        }
        if (chartDto.periodReport != null) {
            predicates.add(cb.equal(chart.get("periodReport"), chartDto.periodReport));
        }
        if (chartDto.objectBuilding != null) {
            predicates.add(cb.equal(chart.get("objectBuilding"), chartDto.objectBuilding));
        }
        if (chartDto.typeBuilding != null) {
            predicates.add(cb.equal(chart.get("typeBuilding"), chartDto.typeBuilding));
        }

        criteria.select(chart).where(
                predicates.toArray(Predicate[]::new)
        );*/

        criteria.select(chart).where(
                citeriaPredicates.toArray(Predicate[]::new)
        );

        return session.createQuery(criteria)
                .list();
    }
}
