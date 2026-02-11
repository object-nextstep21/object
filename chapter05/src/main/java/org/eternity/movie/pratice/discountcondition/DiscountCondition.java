package org.eternity.movie.pratice.discountcondition;

import org.eternity.movie.pratice.Screening;

public interface DiscountCondition {

    boolean isMovieDiscountable(Screening screening);

}
