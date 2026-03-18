package org.eternity.pratice.composition;

import org.eternity.money.Money;
import org.eternity.pratice.composition.additionalreatepolicy.RateDiscountablePolicy;
import org.eternity.pratice.composition.additionalreatepolicy.TaxablePolicy;
import org.eternity.pratice.composition.basicratepolicy.NightlyDiscountPolicy;
import org.eternity.pratice.composition.basicratepolicy.RegularPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 과제 2: 핸드폰 과금 시스템을 합성 + 데코레이터로 리팩터링
 *
 * 목표 구조:
 *
 *   [RatePolicy] ← 인터페이스
 *       ├── BasicRatePolicy (추상 클래스) ← 기본 정책
 *       │     ├── RegularPolicy        ← 일반 요금제
 *       │     └── NightlyDiscountPolicy ← 심야 할인 요금제
 *       │
 *       └── AdditionalRatePolicy (추상 클래스) ← 부가 정책 (다른 RatePolicy를 감싼다)
 *             ├── TaxablePolicy          ← 세금 부과
 *             └── RateDiscountablePolicy ← 기본 요금 할인
 *
 *   [Phone] ← 더 이상 abstract가 아님. RatePolicy를 생성자로 주입받아 합성한다.
 *            call(Call)로 통화 기록 추가, calculateFee()로 요금 계산
 *
 * 핵심:
 * - Phone은 RatePolicy 인터페이스에만 의존한다 (구체 클래스를 모른다)
 * - 부가 정책은 다른 RatePolicy를 감싸는 데코레이터 형태이다
 * - 런타임에 객체 조합만으로 새로운 요금제를 만들 수 있다
 * - TaxableRegularPhone, RateDiscountableNightlyDiscountPhone 같은 클래스가 전부 필요 없어진다
 */
class PhoneBillingTest {

    // 테스트용 상수
    private static final Money REGULAR_AMOUNT = Money.wons(10);      // 10초당 10원
    private static final Duration REGULAR_SECONDS = Duration.ofSeconds(10);

    private static final Money NIGHTLY_AMOUNT = Money.wons(5);       // 심야: 10초당 5원
    private static final Money REGULAR_NIGHTLY_AMOUNT = Money.wons(10); // 주간: 10초당 10원
    private static final Duration NIGHTLY_SECONDS = Duration.ofSeconds(10);

    private static final double TAX_RATE = 0.1;                      // 세금 10%
    private static final Money DISCOUNT_AMOUNT = Money.wons(5);      // 할인 5원

    // 60초 통화 (주간: 오전 10시)
    private static final Call DAYTIME_CALL = new Call(
            LocalDateTime.of(2024, 1, 1, 10, 0, 0),
            LocalDateTime.of(2024, 1, 1, 10, 1, 0)
    );

    // 60초 통화 (심야: 오후 11시)
    private static final Call NIGHTLY_CALL = new Call(
            LocalDateTime.of(2024, 1, 1, 23, 0, 0),
            LocalDateTime.of(2024, 1, 1, 23, 1, 0)
    );

    @Nested
    @DisplayName("기본 정책 단독 사용")
    class BasicPolicyTest {

        @Test
        @DisplayName("일반 요금제: 60초 통화 → 10원 * (60/10) = 60원")
        void regularPolicy() {
            // Phone이 RatePolicy를 생성자로 주입받는다 (합성)
            Phone phone = new Phone(new RegularPolicy(REGULAR_AMOUNT, REGULAR_SECONDS));
            phone.call(DAYTIME_CALL);

            assertEquals(Money.wons(60), phone.calculateFee());
        }

        @Test
        @DisplayName("심야 할인 요금제 - 주간 통화: 10원 * (60/10) = 60원")
        void nightlyDiscountPolicy_daytime() {
            Phone phone = new Phone(
                    new NightlyDiscountPolicy(NIGHTLY_AMOUNT, REGULAR_NIGHTLY_AMOUNT, NIGHTLY_SECONDS)
            );
            phone.call(DAYTIME_CALL);

            assertEquals(Money.wons(60), phone.calculateFee());
        }

        @Test
        @DisplayName("심야 할인 요금제 - 심야 통화: 5원 * (60/10) = 30원")
        void nightlyDiscountPolicy_nightly() {
            Phone phone = new Phone(
                    new NightlyDiscountPolicy(NIGHTLY_AMOUNT, REGULAR_NIGHTLY_AMOUNT, NIGHTLY_SECONDS)
            );
            phone.call(NIGHTLY_CALL);

            assertEquals(Money.wons(30), phone.calculateFee());
        }
    }

    @Nested
    @DisplayName("부가 정책 단일 적용")
    class SingleAdditionalPolicyTest {

        @Test
        @DisplayName("일반 요금제 + 세금: 60원 + 60*0.1 = 66원")
        void regularWithTax() {
            // 데코레이터: TaxablePolicy가 RegularPolicy를 감싼다
            Phone phone = new Phone(
                    new TaxablePolicy(TAX_RATE,
                            new RegularPolicy(REGULAR_AMOUNT, REGULAR_SECONDS))
            );
            phone.call(DAYTIME_CALL);

            assertEquals(Money.wons(66), phone.calculateFee());
        }

        @Test
        @DisplayName("일반 요금제 + 요금 할인: 60원 - 5원 = 55원")
        void regularWithRateDiscount() {
            Phone phone = new Phone(
                    new RateDiscountablePolicy(DISCOUNT_AMOUNT,
                            new RegularPolicy(REGULAR_AMOUNT, REGULAR_SECONDS))
            );
            phone.call(DAYTIME_CALL);

            assertEquals(Money.wons(55), phone.calculateFee());
        }

        @Test
        @DisplayName("심야 할인 요금제(심야) + 세금: 30원 + 30*0.1 = 33원")
        void nightlyWithTax() {
            Phone phone = new Phone(
                    new TaxablePolicy(TAX_RATE,
                            new NightlyDiscountPolicy(NIGHTLY_AMOUNT, REGULAR_NIGHTLY_AMOUNT, NIGHTLY_SECONDS))
            );
            phone.call(NIGHTLY_CALL);

            assertEquals(Money.wons(33), phone.calculateFee());
        }
    }

    @Nested
    @DisplayName("부가 정책 다중 조합 - 순서에 따라 결과가 달라진다!")
    class CombinedPolicyTest {

        @Test
        @DisplayName("세금 먼저, 할인 나중: (60 + 60*0.1) - 5 = 66 - 5 = 61원")
        void taxThenDiscount() {
            // 바깥이 나중에 적용된다: 할인(세금(기본))
            Phone phone = new Phone(
                    new RateDiscountablePolicy(DISCOUNT_AMOUNT,
                            new TaxablePolicy(TAX_RATE,
                                    new RegularPolicy(REGULAR_AMOUNT, REGULAR_SECONDS)))
            );
            phone.call(DAYTIME_CALL);

            assertEquals(Money.wons(61), phone.calculateFee());
        }

        @Test
        @DisplayName("할인 먼저, 세금 나중: (60 - 5) + (60-5)*0.1 = 55 + 5.5 = 60.5원")
        void discountThenTax() {
            // 바깥이 나중에 적용된다: 세금(할인(기본))
            Phone phone = new Phone(
                    new TaxablePolicy(TAX_RATE,
                            new RateDiscountablePolicy(DISCOUNT_AMOUNT,
                                    new RegularPolicy(REGULAR_AMOUNT, REGULAR_SECONDS)))
            );
            phone.call(DAYTIME_CALL);

            assertEquals(Money.wons(60.5), phone.calculateFee());
        }

        @Test
        @DisplayName("같은 부가 정책을 여러 번 적용할 수도 있다: 세금(세금(기본))")
        void doubleTax() {
            // 세금 10%를 두 번 적용
            Phone phone = new Phone(
                    new TaxablePolicy(TAX_RATE,
                            new TaxablePolicy(TAX_RATE,
                                    new RegularPolicy(REGULAR_AMOUNT, REGULAR_SECONDS)))
            );
            phone.call(DAYTIME_CALL);

            // 60 → 66 → 66 + 66*0.1 = 72.6
            assertEquals(Money.wons(72.6), phone.calculateFee());
        }
    }

    @Nested
    @DisplayName("여러 통화 누적 계산")
    class MultipleCallsTest {

        @Test
        @DisplayName("일반 요금제로 주간 60초 + 심야 60초 = 120원")
        void multipleCalls() {
            Phone phone = new Phone(new RegularPolicy(REGULAR_AMOUNT, REGULAR_SECONDS));
            phone.call(DAYTIME_CALL);
            phone.call(NIGHTLY_CALL);

            // 일반 요금제는 시간대 구분 없음: 60 + 60 = 120
            assertEquals(Money.wons(120), phone.calculateFee());
        }

        @Test
        @DisplayName("심야 할인 요금제로 주간 60초 + 심야 60초 = 60 + 30 = 90원")
        void multipleCallsWithNightlyDiscount() {
            Phone phone = new Phone(
                    new NightlyDiscountPolicy(NIGHTLY_AMOUNT, REGULAR_NIGHTLY_AMOUNT, NIGHTLY_SECONDS)
            );
            phone.call(DAYTIME_CALL);
            phone.call(NIGHTLY_CALL);

            assertEquals(Money.wons(90), phone.calculateFee());
        }

        @Test
        @DisplayName("세금 포함 심야 할인: (60 + 30) + (60+30)*0.1 = 90 + 9 = 99원")
        void multipleCallsWithNightlyDiscountAndTax() {
            Phone phone = new Phone(
                    new TaxablePolicy(TAX_RATE,
                            new NightlyDiscountPolicy(NIGHTLY_AMOUNT, REGULAR_NIGHTLY_AMOUNT, NIGHTLY_SECONDS))
            );
            phone.call(DAYTIME_CALL);
            phone.call(NIGHTLY_CALL);

            assertEquals(Money.wons(99), phone.calculateFee());
        }
    }
}
