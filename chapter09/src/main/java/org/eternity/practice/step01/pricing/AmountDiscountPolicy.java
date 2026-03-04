package org.eternity.practice.step01.pricing;

import org.eternity.money.Money;
import org.eternity.practice.step01.DiscountCondition;
import org.eternity.practice.step01.DiscountPolicy;
import org.eternity.practice.step01.Screening;

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
