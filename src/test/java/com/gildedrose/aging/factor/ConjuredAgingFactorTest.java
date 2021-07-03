package com.gildedrose.aging.factor;

import com.gildedrose.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Steven Gentens
 * @since 0.0.1
 */
public class ConjuredAgingFactorTest {

    private AgingFactor factor = new ConjuredAgingFactor();

    @Test
    @DisplayName("Applies for conjured items")
    void checkIsApplicable() {
        assertTrue(factor.isApplicable(item("Conjured Sulfuras")));
        assertTrue(factor.isApplicable(item(" ConJuReD SuLfURaS")));
        assertTrue(factor.isApplicable(item("conjured stuff")));
        assertTrue(factor.isApplicable(item("      conjured sulfuras")));
        assertFalse(factor.isApplicable(item("sul conjured furas")));
        assertFalse(factor.isApplicable(item("aged conjured brie")));
    }

    @Test
    @DisplayName("Conjured items degrade twice as fast - aging factor of 2")
    void agingFactor() {
        assertEquals(2, factor.getAgingFactor());
    }

    private Item item(String name) {
        return new Item(name, 5, 5);
    }
}
