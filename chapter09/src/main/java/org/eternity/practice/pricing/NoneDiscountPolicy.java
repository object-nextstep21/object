package org.eternity.practice.pricing;

import org.eternity.money.Money;
import org.eternity.practice.DiscountPolicy;
import org.eternity.practice.Screening;

public class NoneDiscountPolicy extends DiscountPolicy {
    @Override
    protected Money getDiscountAmount(Screening screening) {
        return Money.ZERO;
    }
}
