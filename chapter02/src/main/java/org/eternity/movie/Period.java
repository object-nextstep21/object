package org.eternity.movie;

import java.time.LocalTime;

public class Period {

    private LocalTime startTime;
    private LocalTime endTime;

    public Period(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean containsTime(LocalTime time) {
        return this.startTime.compareTo(time) <= 0 && this.endTime.compareTo(time) >= 0;
    }
}
