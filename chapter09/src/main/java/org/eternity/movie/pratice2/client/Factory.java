package org.eternity.movie.pratice2.client;

import org.eternity.money.Money;
import org.eternity.movie.pratice2.Movie;
import org.eternity.movie.pratice2.pricing.AmountDiscountPolicy;
import org.eternity.movie.pratice2.pricing.SequenceCondition;

import java.time.Duration;

public class Factory {
    public Movie createAvatarMovie(){
        return new Movie(
                "아바타",
                Duration.ofMinutes(120),
                Money.wons(10000),
                new AmountDiscountPolicy(
                        Money.wons(800),
                        new SequenceCondition(1)
                )
        );
    }
}
