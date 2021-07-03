package com.gildedrose.aging.strategy;

import com.gildedrose.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Steven Gentens
 * @since 0.0.1
 */
public class AgedBrieAgingStrategyTest {

    private AgedBrieAgingStrategy strategy = new AgedBrieAgingStrategy();

    @Test
    @DisplayName("Applies if name contains 'aged brie' - case insensitive")
    void checkIsApplicable() {
        assertTrue(strategy.isApplicable(item("Aged Brie")));
        assertTrue(strategy.isApplicable(item("Conjured Aged Brie")));
        assertTrue(strategy.isApplicable(item("AgeD BriE")));
        assertTrue(strategy.isApplicable(item("aged Brie")));
        assertTrue(strategy.isApplicable(item("aged brie")));
        assertFalse(strategy.isApplicable(item("agedbrie")));
        assertFalse(strategy.isApplicable(item("aged conjured brie")));
    }

    @DisplayName("Maximum quality is 50")
    @ParameterizedTest
    @CsvSource({
        "15,49",
        "15,50",
        "0,49",
        "0,50",
        "-5,49",
        "-5,50",
    })
    void maximumQuality(int sellIn, int initialQuality) {
        Item item = new Item("aged brie", sellIn, initialQuality);
        strategy.accept(item);
        assertEquals(sellIn - 1, item.sellIn);
        assertTrue(item.quality <= 50);
    }

    @DisplayName("Increases in quality as it gets older")
    @ParameterizedTest
    @CsvSource({
        "15, 20",
        "5, 20",
        "0, 20",
        "-5, 20",
    })
    void increasesInQualityTheOlderItGets(int sellIn, int initialQuality) {
        Item item = new Item("aged brie", sellIn, initialQuality);
        strategy.accept(item);
        assertEquals(sellIn - 1, item.sellIn);
        assertTrue(initialQuality < item.quality);
    }

    private Item item(String name) {
        return new Item(name, 5, 5);
    }
}
