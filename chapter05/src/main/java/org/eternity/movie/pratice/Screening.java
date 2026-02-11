package org.eternity.movie.pratice;

import java.time.LocalDateTime;

public class Screening {

    private Movie movie;
    private int sequence;
    private LocalDateTime whenScreened;

    public Screening(Movie movie, int sequence, LocalDateTime whenScreened) {
        this.movie = movie;
        this.sequence = sequence;
        this.whenScreened = whenScreened;
    }

    public Movie getMovie() {
        return movie;
    }

    public boolean isMovieDiscountable() {
        for (DiscountCondition condition : movie.getDiscountConditions()) {
            if (condition.isPeriod()) {
                return isPeriodDiscountable(condition);
            }
            if (condition.isSequence()) {
                return isSequenceDiscountable(condition);
            }
            if (condition.isDayOfWeek()) {
                return isDayOfWeekDiscountable(condition);
            }
        }
        return false;
    }

    private boolean isPeriodDiscountable(DiscountCondition condition) {
        return condition.isSamePeriod(whenScreened);
    }

    private boolean isSequenceDiscountable(DiscountCondition condition) {
        return condition.isSameSequence(sequence);
    }

    private boolean isDayOfWeekDiscountable(DiscountCondition condition) {
        return condition.isSameDayOfTheWeek(whenScreened);
    }

}
