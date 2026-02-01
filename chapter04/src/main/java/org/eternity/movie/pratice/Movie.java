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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(Duration runningTime) {
        this.runningTime = runningTime;
    }

    public Money getFee() {
        return fee;
    }

    public void setFee(Money fee) {
        this.fee = fee;
    }

    public List<DiscountCondition> getDiscountConditions() {
        return discountConditions;
    }

    public void setDiscountConditions(List<DiscountCondition> discountConditions) {
        this.discountConditions = discountConditions;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
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
