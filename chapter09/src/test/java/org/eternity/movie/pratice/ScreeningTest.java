package org.eternity.movie.pratice;

import org.eternity.money.Money;
import org.eternity.movie.pratice.client.Client;
import org.eternity.movie.pratice.client.Factory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class ScreeningTest {

    @Test
    @DisplayName("new 생성자로 직접 생성")
    void newConstructor() {
        Movie movie = new Movie(
                "아바타",
                Duration.ofMinutes(120),
                Money.wons(8000)
        );

        Screening screening = new Screening(movie, 1, LocalDateTime.of(2026, 02, 21, 21, 39, 0));

        Money expectedFee = movie.calculateMovieFee(screening);

        assertThat(expectedFee).isEqualTo(Money.wons(7200));
    }

    @Test
    @DisplayName("Client로 직접 생성")
    void newConstructorClient() {
        Client client = new Client();
        Money fee = client.getAvatarFeeToClient();

        assertThat(fee).isEqualTo(Money.wons(10000));
    }

    @Test
    @DisplayName("Fatory로 생성")
    void newConstructorFactory() {
        Client client = new Client(new Factory());
        Money fee = client.getAvatarFee();
        assertThat(fee).isEqualTo(Money.wons(10000));
    }


}