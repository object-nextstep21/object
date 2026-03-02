package org.eternity.movie.pratice.pricing;


import org.eternity.movie.pratice.DiscountCondition;
import org.eternity.movie.pratice.Screening;

public class SequenceCondition implements DiscountCondition {
    private int sequence;

    public SequenceCondition(int sequence) {
        this.sequence = sequence;
    }

    public boolean isSatisfiedBy(Screening screening) {
        return screening.isSequence(sequence);
    }
}
