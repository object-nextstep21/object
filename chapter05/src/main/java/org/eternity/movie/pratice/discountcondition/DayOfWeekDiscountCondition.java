package org.eternity.movie.pratice.discountcondition;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import org.eternity.movie.pratice.Screening;

public class DayOfWeekDiscountCondition implements DiscountCondition {

    private DayOfWeek dayOfWeek;

    public DayOfWeekDiscountCondition(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public boolean isMovieDiscountable(Screening screening) {
        return isSameDayOfTheWeek(screening.getWhenScreened());
    }

    private boolean isSameDayOfTheWeek(LocalDateTime whenScreened) {
        return dayOfWeek.equals(whenScreened.getDayOfWeek());
    }
}
