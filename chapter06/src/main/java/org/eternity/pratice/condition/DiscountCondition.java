package org.eternity.pratice.condition;

import org.eternity.pratice.Screening;

public interface DiscountCondition {

    boolean isSatisfiedBy(Screening screening);

}
