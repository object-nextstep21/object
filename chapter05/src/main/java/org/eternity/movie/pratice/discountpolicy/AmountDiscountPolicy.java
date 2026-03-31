package org.eternity.movie.pratice.discountpolicy;

import org.eternity.money.Money;
import org.eternity.movie.pratice.Screening;
import org.eternity.movie.pratice.discountcondition.DiscountCondition;

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
