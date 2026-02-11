package org.eternity.movie.pratice.movie;

import java.time.Duration;
import java.util.List;
import org.eternity.money.Money;
import org.eternity.movie.pratice.DiscountCondition;
import org.eternity.movie.pratice.Screening;

public abstract class Movie {

    private String title;
    private Duration runningTime;
    protected Money fee;
    private List<DiscountCondition> discountConditions;

    public Movie(String title, Duration runningTime, Money fee, List<DiscountCondition> discountConditions) {
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        this.discountConditions = discountConditions;
    }

    public Money calculateMovieFee() {
        return calculateDiscountAmount();
    }

    public Money getFee() {
        return fee;
    }

    public List<DiscountCondition> getDiscountConditions() {
        return discountConditions;
    }

    public boolean isMovieDiscountable(Screening screening) {
        if (discountConditions == null || discountConditions.isEmpty()) {
            return false;
        }
        return discountConditions.stream()
            .anyMatch(discountCondition -> discountCondition.isMovieDiscountable(screening));
    }

    abstract protected Money calculateDiscountAmount();
}
