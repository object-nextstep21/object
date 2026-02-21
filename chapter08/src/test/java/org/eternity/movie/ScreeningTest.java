package org.eternity.movie;


import org.eternity.money.Money;
import org.eternity.movie.pricing.AmountDiscountPolicy;
import org.eternity.movie.pricing.SequenceCondition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class ScreeningTest {

    @Test
    @DisplayName("new 생성자로 직접 생성")
    void newConstructor() {
        Movie movie = new Movie(
                "아바타",
                Duration.ofMinutes(120),
                Money.wons(8000)
                );

        Screening screening = new Screening(movie, 1, LocalDateTime.of(2026, 02, 21, 21, 39, 0));

        Money expectedFee = movie.calculateMovieFee(screening);

        assertThat(expectedFee).isEqualTo(Money.wons(7200));
    }

    @Test
    @DisplayName("setter 메서드로 할인 정책 주입")
    void setterMethod() {
        Movie movie = new Movie(
                "아바타",
                Duration.ofMinutes(120),
                Money.wons(8000)
        );

        movie.changeDiscountPolicy(new AmountDiscountPolicy(Money.wons(1000), new SequenceCondition(1)));

        Screening screening = new Screening(movie, 1, LocalDateTime.of(2026, 02, 21, 21, 39, 0));

        Money expectedFee = movie.calculateMovieFee(screening);

        assertThat(expectedFee).isEqualTo(Money.wons(7000));
    }



}