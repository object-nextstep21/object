package org.eternity.movie.condition;

import org.eternity.movie.Screening;

public interface DiscountCondition {
    boolean isSatisfiedBy(Screening screening);
}
