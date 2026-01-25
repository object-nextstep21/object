package org.eternity.movie.pratice;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.eternity.money.Money;

public class Screening {
    private Movie movie;
    private int sequence;
    private LocalDateTime whenScreened;

    public Screening(Movie movie, int sequence, LocalDateTime whenScreened) {
        this.movie = movie;
        this.sequence = sequence;
        this.whenScreened = whenScreened;
    }

    public LocalDateTime getStartTime() {
        return whenScreened;
    }

    public boolean isSameDay(DayOfWeek dayOfWeek) {
        return whenScreened.getDayOfWeek().equals(dayOfWeek);
    }

    public boolean isAfterStartTime(LocalTime startTime) {
        return !startTime.isAfter(whenScreened.toLocalTime());
    }

    public boolean isBeforeEndTime(LocalTime endTime) {
        return !endTime.isBefore(whenScreened.toLocalTime());
    }

    public boolean isSequence(int sequence) {
        return this.sequence == sequence;
    }

    public Money getMovieFee() {
        return movie.getFee();
    }

    public Reservation reserve(Customer customer, int audience) {
        return new Reservation(customer, this, calculateFee(audience), audience);
    }

    private Money calculateFee(int audience) {
        return movie.calculateMovieFee(this).times(audience);
    }

    // 분기처리 방식 (절차적 프로그래밍)
//    private Money calculateFee(int audience) {
//        if (movie.getDiscountPolicy() == new AmountDiscountPolicy()) {
//
//        } else if (movie.getDiscountPolicy() == new PercentDiscountPolicy()) {
//
//        } else if (movie.getDiscountPolicy() == new NoneDiscountPolicy()) {
//
//        }
//    }

}
