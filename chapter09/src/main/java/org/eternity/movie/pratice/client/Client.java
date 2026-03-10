package org.eternity.movie.pratice.client;

import org.eternity.money.Money;
import org.eternity.movie.pratice.Movie;
import org.eternity.movie.pratice.pricing.AmountDiscountPolicy;
import org.eternity.movie.pratice.pricing.SequenceCondition;

import java.time.Duration;

public class Client {
    private Factory factory;

    public Client() {
    }

    public Client(Factory factory) {
        this.factory = factory;
    }

    /**
     * Client 내부에서 Movie 객체 생성
     */
    public Money getAvatarFeeToClient() {
        Movie avatar = new Movie(
                "아바타",
                Duration.ofMinutes(120),
                Money.wons(10000),
                new AmountDiscountPolicy(
                        Money.wons(800),
                        new SequenceCondition(1)
                )
        );
        return avatar.getFee();
    }

    /**
     * Factory 사용
     */
    public Money getAvatarFee() {
        Movie avatar = factory.createAvatarMovie();
        return avatar.getFee();
    }
}
