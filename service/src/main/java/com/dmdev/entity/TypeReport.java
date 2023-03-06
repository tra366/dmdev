package com.dmdev.entity;

public enum TypeReport {
    STATUS("Статусный"),
    DYNAMIC("Динамика");

    private String typeReportName;

    TypeReport(String typeReportName) {
        this.typeReportName = typeReportName;
    }

    @Override
    public String toString() {
        return typeReportName;
    }
}
