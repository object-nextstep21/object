package org.eternity.movie.pratice.movie;

import java.time.Duration;
import org.eternity.money.Money;
import org.eternity.movie.pratice.Screening;
import org.eternity.movie.pratice.discountpolicy.DiscountPolicy;

public class Movie {

    private String title;
    private Duration runningTime;
    protected Money fee;
    private DiscountPolicy discountPolicy;

    public Movie(String title, Duration runningTime, Money fee, DiscountPolicy discountPolicy) {
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        this.discountPolicy = discountPolicy;
    }

    public Money calculateMovieFee(Screening screening) {
        return fee.minus(discountPolicy.calculateMovieFee(screening));
    }

    public Money getFee() {
        return fee;
    }
}
