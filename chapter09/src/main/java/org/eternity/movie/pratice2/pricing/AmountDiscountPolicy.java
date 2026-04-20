package org.eternity.movie.pratice2.pricing;

import org.eternity.money.Money;
import org.eternity.movie.pratice2.DiscountCondition;
import org.eternity.movie.pratice2.DiscountPolicy;
import org.eternity.movie.pratice2.Screening;


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
