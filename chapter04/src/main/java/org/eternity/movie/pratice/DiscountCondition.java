package org.eternity.movie.pratice;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DiscountCondition {

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

    public void setType(DiscountConditionType type) {
        this.type = type;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
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

    public void setSequence(int sequence) {
        this.sequence = sequence;
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
