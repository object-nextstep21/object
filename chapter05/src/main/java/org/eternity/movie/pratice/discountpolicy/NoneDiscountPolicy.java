package org.eternity.movie.pratice.discountpolicy;

import org.eternity.money.Money;
import org.eternity.movie.pratice.Screening;

public class NoneDiscountPolicy extends DiscountPolicy {

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return Money.ZERO;
    }

}
