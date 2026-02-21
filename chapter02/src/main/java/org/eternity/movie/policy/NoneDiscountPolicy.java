package org.eternity.movie.policy;

import org.eternity.money.Money;
import org.eternity.movie.Screening;

public class NoneDiscountPolicy implements DiscountPolicy {
    @Override
    public Money calculateDiscountAmount(Screening screening) {
        return Money.ZERO;
    }
}
