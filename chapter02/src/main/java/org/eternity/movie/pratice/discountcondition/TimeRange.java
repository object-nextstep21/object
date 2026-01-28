package org.eternity.movie.pratice.discountcondition;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class TimeRange {

    private LocalTime startTime;
    private LocalTime endTime;

    public TimeRange(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean contains(LocalDateTime whenScreened) {
        return !startTime.isAfter(whenScreened.toLocalTime()) && !endTime.isBefore(whenScreened.toLocalTime());
    }
}