package org.eternity.movie.condition;

import org.eternity.movie.Screening;
import org.eternity.movie.Period;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class PeriodCondition implements DiscountCondition {
    private DayOfWeek dayOfWeek;
    private Period period;

    public PeriodCondition(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this(dayOfWeek, new Period(startTime, endTime));
    }

    public PeriodCondition(DayOfWeek dayOfWeek, Period period) {
        this.dayOfWeek = dayOfWeek;
        this.period = period;
    }

    public boolean isSatisfiedBy(Screening screening) {
        return screening.matchesDayOfWeek(dayOfWeek) &&
                period.containsTime(screening.getStartTime().toLocalTime());
    }
}
