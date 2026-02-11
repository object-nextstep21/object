package org.eternity.movie.pratice;

public enum MovieType {
    AMOUNT_DISCOUNT("금액 할인 정책"),    // 금액 할인 정책
    PERCENT_DISCOUNT("비율 할인 정책"),   // 비율 할인 정책
    NONE_DISCOUNT("미적용");       // 미적용

    private final String name;

    MovieType(String name) {
        this.name = name;
    }

    public boolean isAmountDiscount() {
        return this == AMOUNT_DISCOUNT;
    }

    public boolean isPercentDiscount() {
        return this == PERCENT_DISCOUNT;
    }

    public boolean isNoneDiscount() {
        return this == NONE_DISCOUNT;
    }
}
