package org.eternity.movie.pratice.client;

import org.eternity.money.Money;
import org.eternity.movie.pratice.Movie;
import org.eternity.movie.pratice.pricing.AmountDiscountPolicy;
import org.eternity.movie.pratice.pricing.SequenceCondition;

import java.time.Duration;

public class Client {
    public Money getAvatarFee() {
        Movie avatar = new Movie(
                "아바타",
                Duration.ofMinutes(120),
                Money.wons(10000),
                new AmountDiscountPolicy(
                        Money.wons(800),
                        new SequenceCondition(1)
                )
        );

        return avatar.getFee();
    }
}
