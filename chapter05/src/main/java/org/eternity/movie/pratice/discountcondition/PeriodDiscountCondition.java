package org.eternity.movie.pratice.discountcondition;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.eternity.movie.pratice.Screening;

public class PeriodDiscountCondition implements DiscountCondition {

    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    public PeriodDiscountCondition(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public boolean isMovieDiscountable(Screening screening) {
        return isSamePeriod(screening.getWhenScreened());
    }

    private boolean isSamePeriod(LocalDateTime whenScreened) {
        return isSameTime(whenScreened) && isSameDay(whenScreened);
    }

    private boolean isSameTime(LocalDateTime whenScreened) {
        return !startTime.isAfter(whenScreened.toLocalTime()) &&
            !endTime.isBefore(whenScreened.toLocalTime());
    }

    private boolean isSameDay(LocalDateTime whenScreened) {
        return dayOfWeek.equals(whenScreened.getDayOfWeek());
    }
}
