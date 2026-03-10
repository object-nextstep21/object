package org.eternity.pratice;

import org.eternity.money.Money;

import java.time.Duration;

public class NightlyDiscountPhone extends Phone {

    private static final int LATE_NIGHT_HOUR = 22;

    private Money nightlyAmount;

    public NightlyDiscountPhone(Money nightlyAmount, Money regularAmount, Duration seconds) {
        super(regularAmount, seconds);
        this.nightlyAmount = nightlyAmount;
    }

    public Money calculateFee() {
        Money result = Money.ZERO;

        for(Call call : getCalls()) {
            if (call.getFrom().getHour() >= LATE_NIGHT_HOUR) {
                result = result.plus(nightlyAmount.times(call.getDuration().getSeconds() / getSeconds().getSeconds()));
            } else {
                result = result.plus(getAmount().times(call.getDuration().getSeconds() / getSeconds().getSeconds()));
            }
        }

        return result;
    }
}
