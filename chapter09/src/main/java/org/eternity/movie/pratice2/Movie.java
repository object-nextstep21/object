package org.eternity.movie.pratice2;

import org.eternity.money.Money;
import org.eternity.movie.pratice2.locator.ServiceLocator;
import org.eternity.movie.pratice2.pricing.AmountDiscountPolicy;
import org.eternity.movie.pratice2.pricing.SequenceCondition;

import java.time.Duration;

public class Movie {
    private String title;
    private Duration runningTime;
    private Money fee;
    private DiscountPolicy discountPolicy;

    public Movie(String title, Duration runningTime, Money fee) {
        this(title, runningTime, fee, ServiceLocator.discountPolicy());
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

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", runningTime=" + runningTime +
                ", fee=" + fee +
                ", discountPolicy=" + discountPolicy +
                '}';
    }
}

