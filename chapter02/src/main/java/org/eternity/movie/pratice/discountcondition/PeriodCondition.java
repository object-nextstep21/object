package org.eternity.movie.pratice.discountcondition;

import java.time.DayOfWeek;
import java.time.LocalTime;
import org.eternity.movie.pratice.Screening;

public class PeriodCondition implements DiscountCondition {
    private DayOfWeek dayOfWeek;
    private TimeRange timeRange;

    public PeriodCondition(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this(dayOfWeek, new TimeRange(startTime, endTime));
    }

    public PeriodCondition(DayOfWeek dayOfWeek, TimeRange timeRange) {
        this.dayOfWeek = dayOfWeek;
        this.timeRange = timeRange;
    }

    public boolean isSatisfiedBy(Screening screening) {
        return screening.isDuring(dayOfWeek, timeRange);
    }
}
