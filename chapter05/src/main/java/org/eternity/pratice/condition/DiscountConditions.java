package org.eternity.pratice.condition;

import org.eternity.pratice.Screening;

import java.util.Arrays;
import java.util.List;

public class DiscountConditions {

    private List<DiscountCondition> conditions;

    public DiscountConditions(DiscountCondition... conditions) {
        this(Arrays.asList(conditions));
    }

    public DiscountConditions(List<DiscountCondition> conditions) {
        this.conditions = conditions;
    }

    public boolean isSatisfiedBy(Screening screening) {
        return conditions.stream().anyMatch(condition -> condition.isSatisfiedBy(screening));
    }
}
