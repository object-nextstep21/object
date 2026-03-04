package org.eternity.practice.step01.client;

import java.time.Duration;
import org.eternity.money.Money;
import org.eternity.practice.step01.Movie;
import org.eternity.practice.step01.pricing.AmountDiscountPolicy;
import org.eternity.practice.step01.pricing.SequenceCondition;

public class Client {
    public Money getAvatarFee() {
        Movie avatar = new Movie("아바타",
                Duration.ofMinutes(120),
                Money.wons(10000),
                new AmountDiscountPolicy(
                    Money.wons(800),
                    new SequenceCondition(1),
                    new SequenceCondition(10)));
        return avatar.getFee();
    }
}
