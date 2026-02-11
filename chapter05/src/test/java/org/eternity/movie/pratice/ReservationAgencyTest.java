package org.eternity.movie.pratice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.eternity.money.Money;
import org.eternity.movie.pratice.discountcondition.DayOfWeekDiscountCondition;
import org.eternity.movie.pratice.discountcondition.DiscountCondition;
import org.eternity.movie.pratice.discountcondition.PeriodDiscountCondition;
import org.eternity.movie.pratice.discountcondition.SequenceDiscountCondition;
import org.eternity.movie.pratice.discountpolicy.AmountDiscountPolicy;
import org.eternity.movie.pratice.discountpolicy.NoneDiscountPolicy;
import org.eternity.movie.pratice.discountpolicy.PercentDiscountPolicy;
import org.eternity.movie.pratice.movie.Movie;
import org.junit.jupiter.api.Test;

class ReservationAgencyTest {

    public static final String TITLE_PERCENT_DISCOUNT = "PercentDiscount";
    public static final String TITLE_NONE_DISCOUNT = "NoneDiscount";
    public static final String TITLE_NO_DISCOUNT = "NoDiscount";
    private final ReservationAgency agency = new ReservationAgency();

    @Test
    void periodCondition_appliesAmountDiscount() {
        DiscountCondition condition = new PeriodDiscountCondition(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(12, 0));

        Movie movie = new Movie(
            "AmountDiscount",
            Duration.ofMinutes(120),
            Money.wons(10_000),
            new AmountDiscountPolicy(Money.wons(1_000), condition)
        );

        Screening screening = screening(movie, 1, LocalDateTime.of(2026, 2, 2, 10, 30));

        Reservation reservation = agency.reserve(screening, new Customer("user", "id"), 1);

        assertEquals(Money.wons(9_000), reservation.getFee());
    }

    @Test
    void sequenceCondition_appliesPercentDiscount() {
        DiscountCondition condition = new SequenceDiscountCondition(2);

        Movie movie = new Movie(
            TITLE_PERCENT_DISCOUNT,
            Duration.ofMinutes(120),
            Money.wons(10_000),
            new PercentDiscountPolicy(0.1, condition)
        );

        Screening screening = screening(movie, 2, LocalDateTime.of(2026, 2, 4, 14, 0));

        Reservation reservation = agency.reserve(screening, new Customer("user", "id"), 1);

        assertEquals(Money.wons(9_000), reservation.getFee());
    }

    @Test
    void periodCondition_notMatched_usesBaseFee() {
        DiscountCondition condition = new PeriodDiscountCondition(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(12, 0));

        Movie movie = new Movie(
            "AmountDiscountWhenConditionMatched",
            Duration.ofMinutes(120),
            Money.wons(10_000),
            new AmountDiscountPolicy(Money.wons(1_000), condition)
        );

        Screening screening = screening(movie, 1, LocalDateTime.of(2026, 2, 3, 10, 30));

        Reservation reservation = agency.reserve(screening, new Customer("user", "id"), 1);

        assertEquals(Money.wons(10_000), reservation.getFee());
    }

    @Test
    void sequenceCondition_notMatched_usesBaseFee() {
        DiscountCondition condition = new SequenceDiscountCondition(2);

        Movie movie = new Movie(
            "PercentDiscountWhenConditionMatched",
            Duration.ofMinutes(120),
            Money.wons(10_000),
            new PercentDiscountPolicy(0.1, condition)
        );

        Screening screening = screening(movie, 1, LocalDateTime.of(2026, 2, 4, 14, 0));

        Reservation reservation = agency.reserve(screening, new Customer("user", "id"), 1);

        assertEquals(Money.wons(10_000), reservation.getFee());
    }

    @Test
    void dayOfWeekCondition_appliesNoneDiscount() {
        DiscountCondition condition = new DayOfWeekDiscountCondition(DayOfWeek.WEDNESDAY);

        Movie movie = new Movie(
            TITLE_NONE_DISCOUNT,
            Duration.ofMinutes(120),
            Money.wons(10_000),
            new NoneDiscountPolicy()
        );

        Screening screening = screening(movie, 1, LocalDateTime.of(2026, 2, 4, 11, 0));

        Reservation reservation = agency.reserve(screening, new Customer("user", "id"), 1);

        assertEquals(Money.wons(10_000), reservation.getFee());
    }

    @Test
    void noConditionMatched_usesBaseFee() {
        Movie movie = new Movie(
            TITLE_NO_DISCOUNT,
            Duration.ofMinutes(120),
            Money.wons(10_000),
            new NoneDiscountPolicy()
        );

        Screening screening = screening(movie, 1, LocalDateTime.of(2026, 2, 4, 11, 0));

        Reservation reservation = agency.reserve(screening, new Customer("user", "id"), 1);

        assertEquals(Money.wons(10_000), reservation.getFee());
    }

    private Screening screening(Movie movie, int sequence, LocalDateTime whenScreened) {
        return new Screening(movie, sequence, whenScreened);
    }
}
