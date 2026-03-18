package org.eternity.pratice.composition;

import org.eternity.money.Money;

public interface RatePolicy {
    Money calculateFee(Phone phone);
}
