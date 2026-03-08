package org.eternity.billing.pratice;

import org.eternity.money.Money;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NightlyDiscountPhoneTest {

    @Test
    void calculateFee() throws Exception {
        // 심야: 10원 / 10초, 주간: 20원 / 10초
        NightlyDiscountPhone phone = new NightlyDiscountPhone(
                Money.wons(10), Money.wons(20), Duration.ofSeconds(10));

        // calls 필드에 직접 접근하여 Call 추가 (프로덕션 코드 수정 없이 테스트하기 위함)
        java.lang.reflect.Field callsField = NightlyDiscountPhone.class.getDeclaredField("calls");
        callsField.setAccessible(true);
        List<Call> calls = (List<Call>) callsField.get(phone);

        // 심야 통화 (22:00 ~ )
        calls.add(new Call(LocalDateTime.of(2026, 1, 1, 22, 0, 0),
                LocalDateTime.of(2026, 1, 1, 22, 1, 0))); // 60s -> 6 * 10 = 60

        // 주간 통화 ( ~ 22:00)
        calls.add(new Call(LocalDateTime.of(2026, 1, 1, 10, 0, 0),
                LocalDateTime.of(2026, 1, 1, 10, 1, 0))); // 60s -> 6 * 20 = 120

        assertEquals(Money.wons(180), phone.calculateFee());
    }
}
