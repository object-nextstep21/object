package org.eternity.pratice.movie;

import org.eternity.money.Money;
import org.eternity.pratice.Screening;
import org.eternity.pratice.condition.DiscountCondition;
import org.eternity.pratice.constant.MovieType;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public abstract class Movie {
    private String title;
    private Duration runningTime;
    private Money fee;
    private List<DiscountCondition> discountConditions;

    public Movie(String title, Duration runningTime, Money fee, DiscountCondition... discountConditions) {
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        this.discountConditions = Arrays.asList(discountConditions);
    }

    public Money calculateMoviFee(Screening screening) {
        if(isDiscountable(screening)){
            return fee.minus(calculateDiscountAmount());
        }

        return fee;
    }

    private boolean isDiscountable(Screening screening) {
        return this.discountConditions.stream().anyMatch(coundition -> coundition.isSatisfiedBy(screening));
    }

    /**
     * 서브클래스에서만 사용해야 하므로 protected
     */
    protected Money getFee() {
        return fee;
    }

    abstract protected Money calculateDiscountAmount();

}
