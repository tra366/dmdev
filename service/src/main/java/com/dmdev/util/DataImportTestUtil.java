package com.dmdev.util;

import com.dmdev.GetEntity;
import com.dmdev.entity.Chart;
import com.dmdev.entity.Matrix;
import com.dmdev.entity.NameSeries;
import com.dmdev.entity.ObjectBuilding;
import com.dmdev.entity.PeriodReport;
import com.dmdev.entity.Series;
import com.dmdev.entity.Source;
import com.dmdev.entity.TypeBuilding;
import com.dmdev.entity.TypeReport;
import com.dmdev.entity.TypeSeries;
import com.dmdev.entity.User;
import lombok.experimental.UtilityClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@UtilityClass
public class DataImportTestUtil {

    public void importData(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            User petrov = GetEntity.getUser("PPetrov");
            session.save(petrov);
            User ivanov = GetEntity.getUser("IIvanov");
            session.save(ivanov);
            User sidorov = GetEntity.getUser("SSidorov");
            session.save(sidorov);

            TypeSeries bar = GetEntity.getTypeSeries("Bar");
            session.save(bar);
            TypeSeries line = GetEntity.getTypeSeries("Line");
            session.save(line);
            TypeSeries area = GetEntity.getTypeSeries("Area");
            session.save(area);

            NameSeries plan = GetEntity.getNameSeries("Plan");
            session.save(plan);
            NameSeries fact = GetEntity.getNameSeries("Fact");
            session.save(fact);
            NameSeries planSmr = GetEntity.getNameSeries("PlanSmr");
            session.save(planSmr);
            NameSeries factSmr = GetEntity.getNameSeries("FactSmr");
            session.save(factSmr);

            Source kedr = GetEntity.getSource("Kedr");
            session.save(kedr);
            Source nioss = GetEntity.getSource("Nioss");
            session.save(nioss);

            Chart planFactGF = Chart.builder()
                    .name("PlanFactGF")
                    .owner(petrov)
                    .typeReport(TypeReport.STATUS)
                    .periodReport(PeriodReport.WEEK)
                    .objectBuilding(ObjectBuilding.GF)
                    .typeBuilding(TypeBuilding.GF_RT_CS)
                    .source(kedr)
                    .myguid("myguid1")
                    .actuality(true)
                    .build();
            session.save(planFactGF);

            Chart statusBuilding = Chart.builder()
                    .name("DynamicBuilding")
                    .owner(ivanov)
                    .typeReport(TypeReport.DYNAMIC)
                    .periodReport(PeriodReport.YEAR)
                    .objectBuilding(ObjectBuilding.BS)
                    .typeBuilding(TypeBuilding.GF_RT_CS)
                    .source(nioss)
                    .myguid("myguid1")
                    .actuality(true)
                    .build();
            session.save(statusBuilding);

            Chart swap = Chart.builder()
                    .name("Swap")
                    .owner(sidorov)
                    .typeReport(TypeReport.STATUS)
                    .periodReport(PeriodReport.HALFYEAR)
                    .objectBuilding(ObjectBuilding.KPE_GF)
                    .typeBuilding(TypeBuilding.SWAP)
                    .source(nioss)
                    .myguid("myguid1")
                    .actuality(true)
                    .build();
            session.save(swap);

            Series planSeries = saveSeries(session, "PlanSeries", planFactGF, bar, plan, PeriodReport.WEEK, kedr);
            Series factSeries = saveSeries(session, "FactSeries", planFactGF, bar, fact, PeriodReport.WEEK, kedr);
            Series planSmrSeries = saveSeries(session, "PlanSmrSeries", statusBuilding, area, planSmr, PeriodReport.YEAR, nioss);
            Series factSmrSeries = saveSeries(session, "FactSmrSeries", statusBuilding, area, factSmr, PeriodReport.YEAR, nioss);
            Series planSwap = saveSeries(session, "PlanSwap", swap, bar, plan, PeriodReport.HALFYEAR, nioss);
            Series factSwap = saveSeries(session, "FactSwap", swap, bar, fact, PeriodReport.HALFYEAR, nioss);

            Matrix planMatrix = saveMatrix(session, TypeReport.STATUS, PeriodReport.WEEK, ObjectBuilding.GF, TypeBuilding.GF_RT_CS, kedr, plan);
            Matrix factMatrix = saveMatrix(session, TypeReport.STATUS, PeriodReport.WEEK, ObjectBuilding.GF, TypeBuilding.GF_RT_CS, kedr, fact);
            Matrix planSmrSeriesMatrix = saveMatrix(session, TypeReport.DYNAMIC, PeriodReport.YEAR, ObjectBuilding.BS, TypeBuilding.GF_RT_CS, nioss, planSmr);
            Matrix factSmrSeriesMatrix = saveMatrix(session, TypeReport.DYNAMIC, PeriodReport.YEAR, ObjectBuilding.BS, TypeBuilding.GF_RT_CS, nioss, factSmr);
            Matrix planSwapMatrix = saveMatrix(session, TypeReport.STATUS, PeriodReport.HALFYEAR, ObjectBuilding.KPE_GF, TypeBuilding.SWAP, nioss, plan);
            Matrix factSwapMatrix = saveMatrix(session, TypeReport.STATUS, PeriodReport.HALFYEAR, ObjectBuilding.KPE_GF, TypeBuilding.SWAP, nioss, fact);

            session.getTransaction().commit();
        }


    }

    private Series saveSeries(Session session, String name, Chart chart, TypeSeries typeSeries, NameSeries nameSeries, PeriodReport periodReport,
                              Source source) {
        Series series = Series.builder()
                .name(name)
                .chart(chart)
                .typeSeries(typeSeries)
                .nameSeries(nameSeries)
                .periodReport(periodReport)
                .source(source)
                .myguid("myguid1")
                .actuality(true)
                .build();
        session.save(series);
        return series;
    }

    private Matrix saveMatrix(Session session, TypeReport typereport, PeriodReport periodReport, ObjectBuilding objectBuilding, TypeBuilding typeBuilding, Source source,
                              NameSeries nameSeries) {
        Matrix matrix = Matrix.builder()
                .typeReport(typereport)
                .periodReport(periodReport)
                .objectBuilding(objectBuilding)
                .typeBuilding(typeBuilding)
                .source(source)
                .nameSeries(nameSeries)
                .sqlQuery("select column_test from table_test")
                .build();
        session.save(matrix);
        return matrix;

    }
}
