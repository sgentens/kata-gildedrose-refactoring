package com.gildedrose.modification.quality;

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
public class LegendaryAgingStrategyTest {

    private AgingStrategy strategy = new LegendaryAgingStrategy();

    @Test
    @DisplayName("Applies for legendary items")
    void checkIsApplicable() {
        assertTrue(strategy.isApplicable(item("Sulfuras")));
        assertTrue(strategy.isApplicable(item("SuLfURaS")));
        assertTrue(strategy.isApplicable(item("SULFURAS")));
        assertTrue(strategy.isApplicable(item("sulfuras")));
        assertFalse(strategy.isApplicable(item("sul furas")));
        assertFalse(strategy.isApplicable(item("jommeke")));
        assertFalse(strategy.isApplicable(item("aged conjured brie")));
    }

    @DisplayName("Is never sold or updates quality")
    @ParameterizedTest
    @CsvSource({
        "15, 20",
        "5, 20",
        "0, 20",
        "-5, 20",
    })
    void isNeverModified(int sellIn, int initialQuality) {
        Item item = new Item("sulfuras", sellIn, initialQuality);
        strategy.accept(item);
        assertEquals(sellIn, item.sellIn);
        assertEquals(initialQuality, item.quality);
    }

    private Item item(String name) {
        return new Item(name, 5, 5);
    }
}
