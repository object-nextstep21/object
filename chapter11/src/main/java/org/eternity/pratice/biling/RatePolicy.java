package org.eternity.pratice.biling;

import org.eternity.money.Money;

public interface RatePolicy {
    Money calculateFee(Phone phone);
}
