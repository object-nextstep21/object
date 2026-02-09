package org.eternity.pratice;

import org.eternity.money.Money;

import java.time.LocalDateTime;

public class Screening {
    private Movie movie;
    private int sequence;
    private LocalDateTime whenScreened;

    public Reservation createReservation(Customer customer, int audienceCount) {
        boolean discountable = checkDiscountable();
        Money fee = calculateFee(discountable, audienceCount);
        return new Reservation(customer, this, fee, audienceCount);
    }

    private boolean checkDiscountable() {
        return this.movie.getDiscountConditions().stream()
                .anyMatch(condition -> condition.isDiscountable(this));
    }

    private Money calculateFee(boolean discountable, int audienceCount) {
        if (discountable) {
            return this.movie.getFee()
                    .minus(this.movie.calculateDiscountedFee())
                    .times(audienceCount);
        }

        return this.movie.getFee();
    }


    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalDateTime getWhenScreened() {
        return whenScreened;
    }

    public void setWhenScreened(LocalDateTime whenScreened) {
        this.whenScreened = whenScreened;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

}
