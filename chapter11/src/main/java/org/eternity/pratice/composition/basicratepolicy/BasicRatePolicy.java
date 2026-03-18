package org.eternity.pratice.composition.basicratepolicy;

import org.eternity.money.Money;
import org.eternity.pratice.composition.Call;
import org.eternity.pratice.composition.Phone;
import org.eternity.pratice.composition.RatePolicy;

public abstract class BasicRatePolicy implements RatePolicy {
    @Override
    public Money calculateFee(Phone phone) {
        Money result = Money.ZERO;

        for (Call call: phone.getCalls()) {
            result = result.plus(calculateCallFee(call));
        }
        return result;
    }

    protected abstract Money calculateCallFee(Call call);
}
