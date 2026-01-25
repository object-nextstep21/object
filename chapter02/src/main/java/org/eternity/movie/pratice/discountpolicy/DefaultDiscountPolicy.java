package org.eternity.movie.pratice.discountpolicy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.eternity.money.Money;
import org.eternity.movie.pratice.discountcondition.DiscountCondition;
import org.eternity.movie.pratice.Screening;

public abstract class DefaultDiscountPolicy implements DiscountPolicy {

    private List<DiscountCondition> conditions = new ArrayList<>();

    public DefaultDiscountPolicy(DiscountCondition... conditions) {
        this.conditions = Arrays.asList(conditions);
    }

    public Money calculateDiscountAmount(Screening screening) {
        return conditions.stream()
            .filter(condition -> condition.isSatisfiedBy(screening))
            .findAny()
            .map(condition -> getDiscountAmount(screening))
            .orElse(Money.ZERO);
    }

    abstract protected Money getDiscountAmount(Screening screening);
}
