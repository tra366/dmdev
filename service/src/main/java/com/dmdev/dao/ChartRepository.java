package com.dmdev.dao;

import com.dmdev.dto.ChartDto;
import com.dmdev.entity.Chart;
import com.dmdev.entity.Chart_;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import java.util.List;

public class ChartRepository extends RepositoryBase<Integer, Chart> {

    public ChartRepository(EntityManager entityManger) {
        super(Chart.class, entityManger);
    }

    public List<Chart> getAll() {

        var cb = getEntityManger().getCriteriaBuilder();
        var criteria = cb.createQuery(Chart.class);
        var chart = criteria.from(Chart.class);

        criteria.select(chart);

        return getEntityManger().createQuery(criteria)
                .getResultList();
    }

    public List<Chart> getByUsername(String username) {
        var cb = getEntityManger().getCriteriaBuilder();

        var criteria = cb.createQuery(Chart.class);
        var chart = criteria.from(Chart.class);
        var user = chart.join("owner");

        criteria.select(chart).where(
                cb.equal(user.get("username"), username)
        );

        return getEntityManger().createQuery(criteria)
                .getResultList();
    }

    public List<Chart> getByNameSeries(String nameSeries) {
        var cb = getEntityManger().getCriteriaBuilder();

        var criteria = cb.createQuery(Chart.class);
        var chart = criteria.from(Chart.class);
        var serieses = chart.join("serieses");

        criteria.select(chart).where(
                cb.equal(serieses.get("nameSeries").get("name"), nameSeries)
        );

        return getEntityManger().createQuery(criteria)
                .getResultList();
    }

    public List<Chart> getByParams(ChartDto chartDto) {
        var cb = getEntityManger().getCriteriaBuilder();

        var criteria = cb.createQuery(Chart.class);
        var chart = criteria.from(Chart.class);

        var citeriaPredicates = CriteriaPredicate.builder()
                .add(chartDto.typeReport, it -> cb.equal(chart.get(Chart_.typeReport), it))
                .add(chartDto.periodReport, it -> cb.equal(chart.get(Chart_.periodReport), it))
                .add(chartDto.objectBuilding, it -> cb.equal(chart.get(Chart_.objectBuilding), it))
                .add(chartDto.typeBuilding, it -> cb.equal(chart.get(Chart_.typeBuilding), it))
                .build();

        criteria.select(chart).where(
                citeriaPredicates.toArray(Predicate[]::new)
        );

        return getEntityManger().createQuery(criteria)
                .getResultList();
    }

}
