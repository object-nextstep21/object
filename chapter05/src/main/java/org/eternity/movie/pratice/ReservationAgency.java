package org.eternity.movie.pratice;

public class ReservationAgency {

    public Reservation reserve(Screening screening, Customer customer, int audienceCount) {
        return new Reservation(customer, screening, screening.calculateMovieFee(), audienceCount);
    }
}
