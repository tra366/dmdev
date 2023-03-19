package com.dmdev.dao;

import com.dmdev.dto.ChartDto;
import com.dmdev.entity.Chart;
import com.dmdev.entity.Chart_;
import com.dmdev.entity.NameSeries_;
import com.dmdev.entity.Series_;
import com.dmdev.entity.User_;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import java.util.List;

@Component
public class ChartRepository extends RepositoryBase<Integer, Chart> {

    public ChartRepository(EntityManager entityManger) {
        super(Chart.class, entityManger);
    }

    public List<Chart> getAll() {
        return findAll();
    }

    public List<Chart> getByUsername(String username) {
        var cb = getEntityManger().getCriteriaBuilder();

        var criteria = cb.createQuery(Chart.class);
        var chart = criteria.from(Chart.class);
        var user = chart.join(Chart_.OWNER);

        criteria.select(chart).where(
                cb.equal(user.get(User_.USERNAME), username)
        );

        return getEntityManger().createQuery(criteria)
                .getResultList();
    }

    public List<Chart> getByNameSeries(String nameSeries) {
        var cb = getEntityManger().getCriteriaBuilder();

        var criteria = cb.createQuery(Chart.class);
        var chart = criteria.from(Chart.class);
        var serieses = chart.join(Chart_.SERIESES);

        criteria.select(chart).where(
                cb.equal(serieses.get(Series_.NAME_SERIES).get(NameSeries_.NAME), nameSeries)
        );

        return getEntityManger().createQuery(criteria)
                .getResultList();
    }

    public List<Chart> getByParams(ChartDto chartDto) {
        var cb = getEntityManger().getCriteriaBuilder();

        var criteria = cb.createQuery(Chart.class);
        var chart = criteria.from(Chart.class);

        var citeriaPredicates = CriteriaPredicate.builder()
                .add(chartDto.getTypeReport(), it -> cb.equal(chart.get(Chart_.typeReport), it))
                .add(chartDto.getPeriodReport(), it -> cb.equal(chart.get(Chart_.periodReport), it))
                .add(chartDto.getObjectBuilding(), it -> cb.equal(chart.get(Chart_.objectBuilding), it))
                .add(chartDto.getTypeBuilding(), it -> cb.equal(chart.get(Chart_.typeBuilding), it))
                .build();

        criteria.select(chart).where(
                citeriaPredicates.toArray(Predicate[]::new)
        );

        return getEntityManger().createQuery(criteria)
                .getResultList();
    }

}
