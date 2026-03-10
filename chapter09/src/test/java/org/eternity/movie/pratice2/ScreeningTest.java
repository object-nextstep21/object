package org.eternity.movie.pratice2;

import org.eternity.money.Money;
import org.eternity.movie.pratice2.locator.ServiceLocator;
import org.eternity.movie.pratice2.pricing.AmountDiscountPolicy;
import org.eternity.movie.pratice2.pricing.SequenceCondition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

class ScreeningTest {

    @Test
    @DisplayName("ServiceLocator로 생성")
    void serviceLocatorConstruct() {
        ServiceLocator.provide(new AmountDiscountPolicy(
                        Money.wons(10000),
                        new SequenceCondition(1)
        ));

        Movie movie = new Movie(
                "아바타",
                Duration.ofMinutes(120),
                Money.wons(10000)
        );

    }

}