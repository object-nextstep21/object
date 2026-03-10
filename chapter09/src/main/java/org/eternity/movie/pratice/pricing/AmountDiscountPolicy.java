package org.eternity.movie.pratice.pricing;

import org.eternity.money.Money;
import org.eternity.movie.pratice.DiscountCondition;
import org.eternity.movie.pratice.DiscountPolicy;
import org.eternity.movie.pratice.Screening;


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
