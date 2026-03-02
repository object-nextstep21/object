package org.eternity.movie.pratice2.pricing;


import org.eternity.movie.pratice2.DiscountCondition;
import org.eternity.movie.pratice2.Screening;

public class SequenceCondition implements DiscountCondition {
    private int sequence;

    public SequenceCondition(int sequence) {
        this.sequence = sequence;
    }

    public boolean isSatisfiedBy(Screening screening) {
        return screening.isSequence(sequence);
    }
}