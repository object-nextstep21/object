package org.eternity.billing.pratice;

import org.eternity.money.Money;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NightlyDiscountPhoneTest {

    @Test
    void calculateFee() {
        // 심야: 10원 / 10초, 주간: 20원 / 10초, 세율: 0.1
        NightlyDiscountPhone phone = new NightlyDiscountPhone(
                Money.wons(10), Money.wons(20), Duration.ofSeconds(10), 0.1);

        // 심야 통화 (22:00 ~ )
        phone.call(new Call(LocalDateTime.of(2026, 1, 1, 22, 0, 0),
                LocalDateTime.of(2026, 1, 1, 22, 1, 0))); // 60s -> 6 * 10 = 60

        // 주간 통화 ( ~ 22:00)
        phone.call(new Call(LocalDateTime.of(2026, 1, 1, 10, 0, 0),
                LocalDateTime.of(2026, 1, 1, 10, 1, 0))); // 60s -> 6 * 20 = 120

        // 1. super.calculateFee() -> (120+120) * 1.1 = 264원 (모두 regularAmount 적용 후 세금)
        // 2. nightlyFee (할인액 누적)
        //    첫 번째 심야 통화 (60초) 할인액: (20 - 10) * (60 / 10) = 10 * 6 = 60
        // 3. result(264) - nightlyFee(60) = 204원
        assertEquals(Money.wons(204), phone.calculateFee());
    }
}
