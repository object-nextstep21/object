package org.eternity.movie.pratice;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DiscountCondition {

    public DiscountCondition(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this(DiscountConditionType.PERIOD, 0, dayOfWeek, startTime, endTime);
    }

    public DiscountCondition(LocalTime startTime, LocalTime endTime) {
        this(DiscountConditionType.PERIOD, 0, null, startTime, endTime);
    }

    public DiscountCondition(DayOfWeek dayOfWeek) {
        this(DiscountConditionType.DAY_OF_WEEK, 0, dayOfWeek, null, null);
    }

    public DiscountCondition(int sequence) {
        this(DiscountConditionType.SEQUENCE, sequence, null, null, null);
    }

    public DiscountCondition(DiscountConditionType type, int sequence, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this.type = type;
        this.sequence = sequence;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    private DiscountConditionType type;

    private int sequence;

    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    public DiscountConditionType getType() {
        return type;
    }

    public boolean isPeriod() {
        return type.isPeriod();
    }

    public boolean isSequence() {
        return type.isSequence();
    }

    public boolean isDayOfWeek() {
        return type.isDayOfWeek();
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public int getSequence() {
        return sequence;
    }

    public boolean isSameSequence(int sequence) {
        return this.sequence == sequence;
    }

    public boolean isSameDayOfTheWeek(LocalDateTime whenScreened) {
        return dayOfWeek.equals(whenScreened.getDayOfWeek());
    }

    public boolean isSamePeriod(LocalDateTime whenScreened) {
        return isSameTime(whenScreened) && isSameDay(whenScreened);
    }

    private boolean isSameTime(LocalDateTime whenScreened) {
        return !startTime.isAfter(whenScreened.toLocalTime()) &&
            !endTime.isBefore(whenScreened.toLocalTime());
    }

    private boolean isSameDay(LocalDateTime whenScreened) {
        return dayOfWeek.equals(whenScreened.getDayOfWeek());
    }
}
