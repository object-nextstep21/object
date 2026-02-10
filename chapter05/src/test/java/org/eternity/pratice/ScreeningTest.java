package org.eternity.pratice;


import org.eternity.money.Money;
import org.eternity.pratice.condition.SequenceCondition;
import org.eternity.pratice.movie.AmountDiscountMovie;
import org.eternity.pratice.movie.Movie;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class ScreeningTest {

    @Test
    public void 정상_생성() {

        Movie movie = new AmountDiscountMovie(
                "토토로",
                Duration.ofMinutes(150),
                Money.wons(8_000),
                Money.wons(1_000),
                new SequenceCondition(1)
        );

        Screening screening = new Screening(movie, 1, LocalDateTime.of(2026, 2, 10, 22, 0));
        Customer customer = new Customer("찬한", "chan");
        Reservation reserve = screening.reserve(customer, 1);

        Money expectedFee = movie.calculateMoviFee(screening);

        assertEquals(expectedFee, reserve.getFee());
    }


}