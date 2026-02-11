package org.eternity.movie.policy;

import org.eternity.money.Money;
import org.eternity.movie.Screening;

public interface DiscountPolicy {
    Money calculateDiscountAmount(Screening screening);
}
