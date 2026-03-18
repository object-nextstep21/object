package org.eternity.pratice.composition.additionalreatepolicy;

import org.eternity.money.Money;
import org.eternity.pratice.composition.Phone;
import org.eternity.pratice.composition.RatePolicy;

public abstract class AdditionalRatePolicy implements RatePolicy {
    private RatePolicy next;

    public AdditionalRatePolicy(RatePolicy next) {
        this.next = next;
    }

    @Override
    public Money calculateFee(Phone phone) {
        Money fee = next.calculateFee(phone);
        return afterCalculated(fee);
    }

    abstract protected Money afterCalculated(Money fee);
}
