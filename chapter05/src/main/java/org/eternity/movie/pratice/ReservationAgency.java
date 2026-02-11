package org.eternity.movie.pratice;

import org.eternity.movie.pratice.movie.Movie;

public class ReservationAgency {

    public Reservation reserve(Screening screening, Customer customer, int audienceCount) {
        Movie movie = screening.getMovie();
        // 할인 가능 여부 검증
        if (screening.isMovieDiscountable()) {
            // 최종 금액 계산
            return new Reservation(customer, screening, movie.calculateMovieFee(), audienceCount);
        }
        return new Reservation(customer, screening, movie.getFee(), audienceCount);
    }
}
