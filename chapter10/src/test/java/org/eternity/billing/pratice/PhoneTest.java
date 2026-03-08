package org.eternity.billing.pratice;

import org.eternity.money.Money;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PhoneTest {

    @Test
    void calculateFee() {
        Phone phone = new Phone(Money.wons(5), Duration.ofSeconds(10), 0.1);
        phone.call(new Call(LocalDateTime.of(2026, 1, 1, 10, 0, 0),
                LocalDateTime.of(2026, 1, 1, 10, 1, 0))); // 60s -> 6 * 5 = 30

        // 30원 + 세금(30 * 0.1) = 33원
        assertEquals(Money.wons(33), phone.calculateFee());
    }
}
