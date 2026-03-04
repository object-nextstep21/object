package org.eternity.practice.step01.pricing;

import org.eternity.money.Money;
import org.eternity.practice.step01.DiscountCondition;
import org.eternity.practice.step01.DiscountPolicy;
import org.eternity.practice.step01.Screening;

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
