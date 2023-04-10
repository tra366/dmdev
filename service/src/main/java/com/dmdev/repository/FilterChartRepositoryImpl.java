package com.dmdev.repository;

import com.dmdev.dto.ChartDto;
import com.dmdev.entity.Chart;
import com.dmdev.entity.Chart_;
import com.dmdev.entity.NameSeries_;
import com.dmdev.entity.Series_;
import com.dmdev.entity.User_;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import java.util.List;

@RequiredArgsConstructor
public class FilterChartRepositoryImpl implements FilterChartRepository {

    private final EntityManager entityManager;

    @Override
    public List<Chart> findByParams(ChartDto chartDto) {
        var cb = entityManager.getCriteriaBuilder();

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

        return entityManager.createQuery(criteria)
                .getResultList();
    }

    @Override
    public List<Chart> findByUsername(String username) {
        var cb = entityManager.getCriteriaBuilder();

        var criteria = cb.createQuery(Chart.class);
        var chart = criteria.from(Chart.class);
        var user = chart.join(Chart_.OWNER);

        criteria.select(chart).where(
                cb.equal(user.get(User_.USERNAME), username)
        );

        return entityManager.createQuery(criteria)
                .getResultList();
    }

    @Override
    public List<Chart> findByNameSeries(String nameSeries) {
        var cb = entityManager.getCriteriaBuilder();

        var criteria = cb.createQuery(Chart.class);
        var chart = criteria.from(Chart.class);
        var serieses = chart.join(Chart_.SERIESES);

        criteria.select(chart).where(
                cb.equal(serieses.get(Series_.NAME_SERIES).get(NameSeries_.NAME), nameSeries)
        );

        return entityManager.createQuery(criteria)
                .getResultList();
    }
}
