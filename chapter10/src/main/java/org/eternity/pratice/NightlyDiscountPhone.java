package org.eternity.pratice;

import org.eternity.money.Money;

import java.time.Duration;

public class NightlyDiscountPhone extends Phone {

    private static final int LATE_NIGHT_HOUR = 22;

    private Money nightlyAmount;

    public NightlyDiscountPhone(Money nightlyAmount, Money regularAmount, Duration seconds, double texRet) {
        super(regularAmount, seconds, texRet);
        this.nightlyAmount = nightlyAmount;
    }

}
