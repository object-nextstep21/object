package org.eternity.pratice;

import org.eternity.money.Money;
import org.eternity.pratice.condition.DiscountCondition;
import org.eternity.pratice.constant.MovieType;

import java.time.Duration;
import java.util.List;

public class Movie {
    private String title;
    private Duration runningTime;
    private Money fee;
    private List<DiscountCondition> discountConditions;

    private MovieType movieType;
    private Money discountAmount;
    private double discountPercent;

    public Money calculateMoviFee(Screening screening) {
        if(isDiscountable(screening)){
            return fee.minus(calculateDiscountAmount());
        }

        return fee;
    }

    private boolean isDiscountable(Screening screening) {
        return this.discountConditions.stream().anyMatch(coundition -> coundition.isSatisfiedBy(screening));
    }

    public Money calculateDiscountAmount() {
        switch(this.movieType) {
            case AMOUNT_DISCOUNT:
                return calculateAmountDiscountAmount();
            case PERCENT_DISCOUNT:
                return calculatePercentDiscountAmount();
            case NONE_DISCOUNT:
                return calculateNoneDiscountAmount();
        }

        throw new IllegalArgumentException();
    }

    private Money calculateAmountDiscountAmount() {
        return this.discountAmount;
    }

    private Money calculatePercentDiscountAmount() {
        return this.fee.times(this.discountPercent);
    }

    private Money calculateNoneDiscountAmount() {
        return Money.ZERO;
    }

}
