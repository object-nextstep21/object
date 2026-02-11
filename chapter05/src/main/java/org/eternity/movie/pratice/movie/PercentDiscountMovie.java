package org.eternity.movie.pratice.movie;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.eternity.money.Money;
import org.eternity.movie.pratice.DiscountCondition;

public class PercentDiscountMovie extends Movie {

    private Double discountPercent;

    public PercentDiscountMovie(String title, Duration runningTime, Money fee, Double discountPercent, DiscountCondition... discountConditions) {
        this(title, runningTime, fee, discountPercent, Arrays.asList(discountConditions));
    }

    public PercentDiscountMovie(String title, Duration runningTime, Money fee, Double discountPercent, List<DiscountCondition> discountConditions) {
        super(title, runningTime, fee, discountConditions);
        this.discountPercent = discountPercent;
    }

    @Override
    public Money calculateDiscountAmount() {
        return fee.minus(fee.times(discountPercent));
    }
}
