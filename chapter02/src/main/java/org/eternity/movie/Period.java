package org.eternity.movie;

import java.time.LocalTime;

public class Period {

    private LocalTime startTime;
    private LocalTime endTime;

    public Period(LocalTime startTime, LocalTime endTime) {
        validateTime(startTime, endTime);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    private void validateTime(LocalTime startTime, LocalTime endTime) {
        if(startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("종료 시간이 시작 시간보다 빠를 수 없습니다.");
        }
    }

    public boolean containsTime(LocalTime time) {
        return this.startTime.compareTo(time) <= 0 && this.endTime.compareTo(time) >= 0;
    }
}
