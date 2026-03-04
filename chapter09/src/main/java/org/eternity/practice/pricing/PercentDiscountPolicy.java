package org.eternity.practice.pricing;

import org.eternity.money.Money;
import org.eternity.practice.DiscountCondition;
import org.eternity.practice.DiscountPolicy;
import org.eternity.practice.Screening;

public class PercentDiscountPolicy extends DiscountPolicy {
    private double percent;

    public PercentDiscountPolicy(double percent, DiscountCondition... conditions) {
        super(conditions);
        this.percent = percent;
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return screening.getMovieFee().times(percent);
    }
}
