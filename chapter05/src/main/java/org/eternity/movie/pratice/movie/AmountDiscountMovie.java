package org.eternity.movie.pratice.movie;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.eternity.money.Money;
import org.eternity.movie.pratice.DiscountCondition;

public class AmountDiscountMovie extends Movie {

    private Money discountAmount;

    public AmountDiscountMovie(String title, Duration runningTime, Money fee, Money discountAmount, DiscountCondition... discountConditions) {
        this(title, runningTime, fee, discountAmount, Arrays.asList(discountConditions));
    }

    public AmountDiscountMovie(String title, Duration runningTime, Money fee, Money discountAmount, List<DiscountCondition> discountConditions) {
        super(title, runningTime, fee, discountConditions);
        this.discountAmount = discountAmount;
    }

    @Override
    public Money calculateDiscountAmount() {
        return fee.minus(discountAmount);
    }
}
