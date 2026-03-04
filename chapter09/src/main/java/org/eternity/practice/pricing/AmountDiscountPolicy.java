package org.eternity.practice.pricing;

import org.eternity.money.Money;
import org.eternity.practice.DiscountCondition;
import org.eternity.practice.DiscountPolicy;
import org.eternity.practice.Screening;

public class AmountDiscountPolicy extends DiscountPolicy {
    private Money discountAmount;

    public AmountDiscountPolicy(Money discountAmount, DiscountCondition... conditions) {
        super(conditions);
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return discountAmount;
    }
}
