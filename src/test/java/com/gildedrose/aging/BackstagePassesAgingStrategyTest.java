package com.gildedrose.aging;

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
public class BackstagePassesAgingStrategyTest {

    private AgingStrategy strategy = new BackstagePassesAgingStrategy();

    @Test
    @DisplayName("Applies for backstage passes")
    void checkIsApplicable() {
        assertTrue(strategy.isApplicable(item("backstage pass")));
        assertTrue(strategy.isApplicable(item("Backstage pass")));
        assertTrue(strategy.isApplicable(item("Backstage Pass")));
        assertTrue(strategy.isApplicable(item("BacKstaGe PasS")));
        assertTrue(strategy.isApplicable(item("BACKSTAGE PASSES")));
        assertFalse(strategy.isApplicable(item("back stage pass")));
        assertFalse(strategy.isApplicable(item("backstagepass")));
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
        Item item = new Item("backstage pass", sellIn, initialQuality);
        strategy.accept(item);
        assertEquals(sellIn - 1, item.sellIn);
        assertTrue(item.quality <= 50);
    }

    @Test
    void increaseInQualityFiveDaysBeforeConcert() {
        Item item = new Item("backstage pass", 5, 5);
        strategy.accept(item);
        assertEquals(4, item.sellIn);
        assertEquals(8, item.quality);
    }

    @Test
    void increaseInQualityTenDaysBeforeConcert() {
        Item item = new Item("backstage pass", 10, 5);
        strategy.accept(item);
        assertEquals(9, item.sellIn);
        assertEquals(7, item.quality);
    }

    @Test
    void increaseInQualityBeforeConcert() {
        Item item = new Item("backstage pass", 15, 5);
        strategy.accept(item);
        assertEquals(14, item.sellIn);
        assertEquals(6, item.quality);
    }

    @Test
    void worthlessAfterConcert() {
        Item item = new Item("backstage pass", 0, 5);
        strategy.accept(item);
        assertEquals(-1, item.sellIn);
        assertEquals(0, item.quality);
    }

    private Item item(String name) {
        return new Item(name, 5, 5);
    }
}
