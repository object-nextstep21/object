package org.eternity.movie.policy;

import org.eternity.money.Money;
import org.eternity.movie.condition.DiscountCondition;
import org.eternity.movie.Screening;

public class AmountDiscountPolicy extends DefaultDiscountPolicy {
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
