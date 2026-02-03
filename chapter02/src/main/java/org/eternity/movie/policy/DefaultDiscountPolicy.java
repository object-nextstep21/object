package org.eternity.movie.policy;

import org.eternity.money.Money;
import org.eternity.movie.condition.DiscountCondition;
import org.eternity.movie.Screening;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DefaultDiscountPolicy implements DiscountPolicy {
    private List<DiscountCondition> conditions = new ArrayList<>();

    public DefaultDiscountPolicy(DiscountCondition... conditions) {
        this.conditions = Arrays.asList(conditions);
    }

    @Override
    public Money calculateDiscountAmount(Screening screening) {
        return conditions.stream()
                .filter(condition -> condition.isSatisfiedBy(screening))
                .findFirst()
                .map(condition -> getDiscountAmount(screening))
                .orElse(Money.ZERO);
    }

    abstract protected Money getDiscountAmount(Screening Screening);
}
