package org.eternity.movie.pratice;

import org.eternity.money.Money;
import org.eternity.movie.pratice.pricing.AmountDiscountPolicy;
import org.eternity.movie.pratice.pricing.SequenceCondition;

import java.time.Duration;

public class Movie {
    private String title;
    private Duration runningTime;
    private Money fee;
    private DiscountPolicy discountPolicy;

    public Movie(String title, Duration runningTime, Money fee) {
        this(title, runningTime, fee, new AmountDiscountPolicy(
                        Money.wons(800),
                        new SequenceCondition(1)
                )
        );
    }

    public Movie(String title, Duration runningTime, Money fee, DiscountPolicy discountPolicy) {
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        this.discountPolicy = discountPolicy;
    }

    public Money getFee() {
        return fee;
    }

    public Money calculateMovieFee(Screening screening) {
        return fee.minus(discountPolicy.calculateDiscountAmount(screening));
    }
}

