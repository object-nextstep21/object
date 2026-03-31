package org.eternity.movie.pratice.discountcondition;

import org.eternity.movie.pratice.Screening;

public class SequenceDiscountCondition implements DiscountCondition {

    private int sequence;

    public SequenceDiscountCondition(int sequence) {
        this.sequence = sequence;
    }

    @Override
    public boolean isMovieDiscountable(Screening screening) {
        return isSameSequence(screening.getSequence());
    }

    private boolean isSameSequence(int sequence) {
        return this.sequence == sequence;
    }

}
