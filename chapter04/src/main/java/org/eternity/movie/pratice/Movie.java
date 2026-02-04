package org.eternity.movie.pratice;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.eternity.money.Money;

public class Movie {

    private String title;
    private Duration runningTime;
    private Money fee;
    private List<DiscountCondition> discountConditions;

    private MovieType movieType;
    private Money discountAmount;
    private Double discountPercent;

    public Movie(String title, Duration runningTime, Money fee, Money discountAmount, DiscountCondition... discountConditions) {
        this(title, runningTime, fee, MovieType.AMOUNT_DISCOUNT, discountAmount, null, discountConditions);
    }

    public Movie(String title, Duration runningTime, Money fee, Double discountPercent, DiscountCondition... discountConditions) {
        this(title, runningTime, fee, MovieType.PERCENT_DISCOUNT, null, discountPercent, discountConditions);
    }

    public Movie(String title, Duration runningTime, Money fee, DiscountCondition... discountConditions) {
        this(title, runningTime, fee, MovieType.NONE_DISCOUNT, null, null, discountConditions);
    }

    public Movie(String title, Duration runningTime, Money fee, MovieType movieType, Money discountAmount, Double discountPercent, DiscountCondition... discountConditions) {
        this(title, runningTime, fee, movieType, discountAmount, discountPercent, Arrays.asList(discountConditions));
    }

    public Movie(String title, Duration runningTime, Money fee, MovieType movieType, Money discountAmount, Double discountPercent, List<DiscountCondition> discountConditions) {
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        this.discountConditions = discountConditions;
        this.movieType = movieType;
        this.discountAmount = discountAmount;
        this.discountPercent = discountPercent;
    }

    public Money calculateMovieFee() {
        if (movieType.isAmountDiscount()) {
            return fee.minus(discountAmount);
        }

        if (movieType.isPercentDiscount()) {
            return fee.minus(fee.times(discountPercent));
        }

        return fee;
    }

    public Money getFee() {
        return fee;
    }

    public List<DiscountCondition> getDiscountConditions() {
        return discountConditions;
    }
}
