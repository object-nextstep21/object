package org.eternity.pratice.movie;

import org.eternity.money.Money;
import org.eternity.pratice.condition.DiscountCondition;

import java.time.Duration;

public class PercentDiscountMovie extends Movie {

    private double percent;

    public PercentDiscountMovie(String title, Duration runningTime,
                                Money fee, double Percent,
                                DiscountCondition... discountConditions) {
        super(title, runningTime, fee, discountConditions);
        this.percent = percent;
    }

    @Override
    protected Money calculateDiscountAmount() {
        return getFee().times(percent);
    }
}
