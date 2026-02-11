package org.eternity.movie.pratice.movie;

import java.time.Duration;
import org.eternity.money.Money;

public class NoneDiscountMovie extends Movie {

    public NoneDiscountMovie(String title, Duration runningTime, Money fee) {
        super(title, runningTime, fee, null);
    }

    @Override
    public Money calculateDiscountAmount() {
        return fee;
    }

}
