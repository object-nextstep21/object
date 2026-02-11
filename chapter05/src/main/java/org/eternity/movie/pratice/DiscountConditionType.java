package org.eternity.movie.pratice;

public enum DiscountConditionType {
    SEQUENCE("순번조건"),
    PERIOD("기간 조건"),
    DAY_OF_WEEK("요일 조건");

    DiscountConditionType(String name) {
        this.name = name;
    }

    private final String name;

    public boolean isPeriod() {
        return this == PERIOD;
    }

    public boolean isSequence() {
        return this == SEQUENCE;
    }

    public boolean isDayOfWeek() {
        return this == DAY_OF_WEEK;
    }
}

