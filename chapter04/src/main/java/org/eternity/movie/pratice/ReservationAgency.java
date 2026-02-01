package org.eternity.movie.pratice;

import org.eternity.money.Money;

public class ReservationAgency {

    public Reservation reserve(Screening screening, Customer customer, int audienceCount) {
        Movie movie = screening.getMovie();

        // 할인 가능 여부 검증
        boolean discountable = false;
        for (DiscountCondition condition : movie.getDiscountConditions()) {
            if (condition.getType() == DiscountConditionType.PERIOD) {
                discountable = screening.getWhenScreened().getDayOfWeek().equals(condition.getDayOfWeek()) &&
                    condition.getStartTime().compareTo(screening.getWhenScreened().toLocalTime()) <= 0 &&
                    condition.getEndTime().compareTo(screening.getWhenScreened().toLocalTime()) >= 0;
            } else if (condition.getType() == DiscountConditionType.SEQUENCE) {
                discountable = condition.getSequence() == screening.getSequence();
            } else if (condition.getType() == DiscountConditionType.DAY_OF_WEEK) {
                discountable = screening.getWhenScreened().getDayOfWeek().equals(condition.getDayOfWeek());
            } else {
                throw new IllegalArgumentException("Unknown DiscountConditionType");
            }

            if (discountable) {
                break;
            }
        }

        // 최종 금액 계산
        Money fee;
        if (discountable) {
            Money discountAmount = Money.ZERO;
            switch (movie.getMovieType()) {
                case AMOUNT_DISCOUNT:
                    discountAmount = movie.getDiscountAmount();
                    break;
                case PERCENT_DISCOUNT:
                    discountAmount = movie.getFee().times(movie.getDiscountPercent());
                    break;
                case NONE_DISCOUNT:
                    discountAmount = Money.ZERO;
                    break;
            }
            fee = movie.getFee().minus(discountAmount);
        } else {
            fee = movie.getFee();
        }
        return new Reservation(customer, screening, fee, audienceCount);
    }
}
