package org.eternity.movie.pratice;

import java.time.LocalDateTime;
import org.eternity.money.Money;
import org.eternity.movie.pratice.movie.Movie;

public class Screening {

    private Movie movie;
    private int sequence;
    private LocalDateTime whenScreened;

    public Screening(Movie movie, int sequence, LocalDateTime whenScreened) {
        this.movie = movie;
        this.sequence = sequence;
        this.whenScreened = whenScreened;
    }

    public int getSequence() {
        return sequence;
    }

    public LocalDateTime getWhenScreened() {
        return whenScreened;
    }

    public Movie getMovie() {
        return movie;
    }

    public Money calculateMovieFee() {
        return movie.calculateMovieFee(this);
    }

}
