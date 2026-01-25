package org.eternity.movie.pratice.discountcondition;

import java.time.DayOfWeek;
import java.time.LocalTime;
import org.eternity.movie.pratice.Screening;

public class PeriodCondition implements DiscountCondition {
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    public PeriodCondition(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean isSatisfiedBy(Screening screening) {
        return screening.isSameDay(dayOfWeek) &&
            screening.isAfterStartTime(startTime) &&
            screening.isBeforeEndTime(endTime);
    }
}
