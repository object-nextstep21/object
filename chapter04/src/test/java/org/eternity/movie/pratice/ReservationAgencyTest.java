package org.eternity.movie.pratice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.eternity.money.Money;
import org.junit.jupiter.api.Test;

class ReservationAgencyTest {

    private final ReservationAgency agency = new ReservationAgency();

    @Test
    void periodCondition_appliesAmountDiscount() {
        DiscountCondition condition = new DiscountCondition();
        condition.setType(DiscountConditionType.PERIOD);
        condition.setDayOfWeek(DayOfWeek.MONDAY);
        condition.setStartTime(LocalTime.of(10, 0));
        condition.setEndTime(LocalTime.of(12, 0));

        Movie movie = new Movie(
            "AmountDiscount",
            Duration.ofMinutes(120),
            Money.wons(10_000),
            Money.wons(1_000),
            condition
        );

        Screening screening = screening(movie, 1, LocalDateTime.of(2026, 2, 2, 10, 30));

        Reservation reservation = agency.reserve(screening, new Customer("user", "id"), 1);

        assertEquals(Money.wons(9_000), reservation.getFee());
    }

    @Test
    void sequenceCondition_appliesPercentDiscount() {
        DiscountCondition condition = new DiscountCondition();
        condition.setType(DiscountConditionType.SEQUENCE);
        condition.setSequence(2);

        Movie movie = new Movie(
            "PercentDiscount",
            Duration.ofMinutes(120),
            Money.wons(10_000),
            0.1,
            condition
        );

        Screening screening = screening(movie, 2, LocalDateTime.of(2026, 2, 4, 14, 0));

        Reservation reservation = agency.reserve(screening, new Customer("user", "id"), 1);

        assertEquals(Money.wons(9_000), reservation.getFee());
    }

    @Test
    void dayOfWeekCondition_appliesNoneDiscount() {
        DiscountCondition condition = new DiscountCondition();
        condition.setType(DiscountConditionType.DAY_OF_WEEK);
        condition.setDayOfWeek(DayOfWeek.WEDNESDAY);

        Movie movie = new Movie(
            "NoneDiscount",
            Duration.ofMinutes(120),
            Money.wons(10_000),
            condition
        );

        Screening screening = screening(movie, 1, LocalDateTime.of(2026, 2, 4, 11, 0));

        Reservation reservation = agency.reserve(screening, new Customer("user", "id"), 1);

        assertEquals(Money.wons(10_000), reservation.getFee());
    }

    @Test
    void noConditionMatched_usesBaseFee() {
        DiscountCondition condition = new DiscountCondition();
        condition.setType(DiscountConditionType.SEQUENCE);
        condition.setSequence(5);

        Movie movie = new Movie(
            "NoDiscount",
            Duration.ofMinutes(120),
            Money.wons(10_000),
            Money.wons(1_000),
            condition
        );

        Screening screening = screening(movie, 1, LocalDateTime.of(2026, 2, 4, 11, 0));

        Reservation reservation = agency.reserve(screening, new Customer("user", "id"), 1);

        assertEquals(Money.wons(10_000), reservation.getFee());
    }

    private Screening screening(Movie movie, int sequence, LocalDateTime whenScreened) {
        return new Screening(movie, sequence, whenScreened);
    }
}
