package org.eternity.pratice.composition.additionalreatepolicy;

import org.eternity.money.Money;
import org.eternity.pratice.composition.RatePolicy;

public class RateDiscountablePolicy extends AdditionalRatePolicy{
    private Money discountAmount;

    public RateDiscountablePolicy(Money discountAmount, RatePolicy next) {
        super(next);
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money afterCalculated(Money fee) {
        return fee.minus(discountAmount);
    }
}
