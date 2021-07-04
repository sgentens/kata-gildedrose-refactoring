package com.gildedrose.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Steven Gentens
 * @since 0.0.1
 */
public class AgingFactorUtilsTest {


    @Test
    @DisplayName("An aging factor of 0 reverts the applied degradation")
    void multiplyAgingFactorZero() {
        assertEquals(-5, AgingFactorUtils.calculateNewItemQuality(5, 0));
        assertEquals(5, AgingFactorUtils.calculateNewItemQuality(-5, 0));
    }

    @Test
    @DisplayName("A positive aging factor applies its factor minus the already applied degradation")
    void multiplyAgingFactorPositive() {
        assertEquals(5, AgingFactorUtils.calculateNewItemQuality(5, 2));
        assertEquals(-5, AgingFactorUtils.calculateNewItemQuality(-5, 2));
    }

    @Test
    @DisplayName("A negative aging factor applies its factor plus the already applied degradation")
    void multiplyAgingFactorNegative() {
        assertEquals(-5, AgingFactorUtils.calculateNewItemQuality(5, -2));
        assertEquals(5, AgingFactorUtils.calculateNewItemQuality(-5, -2));
    }
}
