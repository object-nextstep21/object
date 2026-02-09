package org.eternity.pratice;

import org.eternity.money.Money;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class Movie {
    private String title;
    private Duration runningTime;
    private Money fee;
    private List<DiscountCondition> discountConditions;

    private MovieType movieType;
    private Money discountAmount;
    private double discountPercent;

    public Money calculateDiscountedFee() {
        switch(this.movieType) {
            case AMOUNT_DISCOUNT:
                return calculateAmountDiscountedFee();
            case PERCENT_DISCOUNT:
                return calculatePercentDiscountedFee();
            case NONE_DISCOUNT:
                return calculateNoneDiscountedFee();
        }

        throw new IllegalArgumentException();
    }

    private Money calculateAmountDiscountedFee() {
        return this.discountAmount;
    }

    private Money calculatePercentDiscountedFee() {
        return this.fee.times(this.discountPercent);
    }

    private Money calculateNoneDiscountedFee() {
        return this.fee;
    }


    public MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    public Money getFee() {
        return fee;
    }

    public void setFee(Money fee) {
        this.fee = fee;
    }

    public List<DiscountCondition> getDiscountConditions() {
        return Collections.unmodifiableList(discountConditions);
    }

    public void setDiscountConditions(
            List<DiscountCondition> discountConditions) {
        this.discountConditions = discountConditions;
    }

    public Money getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Money discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }
}
