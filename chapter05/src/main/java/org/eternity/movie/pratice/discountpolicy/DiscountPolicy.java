package org.eternity.movie.pratice.discountpolicy;

import java.util.Arrays;
import java.util.List;
import org.eternity.money.Money;
import org.eternity.movie.pratice.Screening;
import org.eternity.movie.pratice.discountcondition.DiscountCondition;

public abstract class DiscountPolicy {

    private List<DiscountCondition> discountConditions;

    public DiscountPolicy(DiscountCondition... discountConditions) {
        this(Arrays.asList(discountConditions));
    }

    public DiscountPolicy(List<DiscountCondition> discountConditions) {
        this.discountConditions = discountConditions;
    }

    public Money calculateMovieFee(Screening screening) {
        return discountConditions.stream()
            .filter(discountCondition -> discountCondition.isMovieDiscountable(screening))
            .findFirst()
            .map(discountCondition -> getDiscountAmount(screening))
            .orElse(Money.ZERO);
    }

    abstract protected Money getDiscountAmount(Screening screening);

}
