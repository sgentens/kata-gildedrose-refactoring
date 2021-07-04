package com.gildedrose.aging.factor;

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
public class ConjuredAgingFactorTest {

    private AgingFactor factor = new ConjuredAgingFactor();

    @Test
    @DisplayName("Applies for conjured items")
    void isApplicable() {
        assertTrue(factor.isApplicable(item("Conjured Sulfuras")));
        assertTrue(factor.isApplicable(item(" ConJuReD SuLfURaS")));
        assertTrue(factor.isApplicable(item("conjured stuff")));
        assertTrue(factor.isApplicable(item("      conjured sulfuras")));
        assertFalse(factor.isApplicable(item("sul conjured furas")));
        assertFalse(factor.isApplicable(item("aged conjured brie")));
    }

    @DisplayName("Conjured items degrade twice as fast - aging factor of 2")
    @ParameterizedTest
    @CsvSource({
        "20,-5,15",
        "20,5,25",
        "20,-10,10",
        "20,10,30"
    })
    void applyAgingFactor(int initialQuality, int appliedQualityDegradation, int expectedQuality) {
        Item item = new Item("some item", 5, initialQuality);
        factor.accept(item, appliedQualityDegradation);
        assertEquals(item.quality, expectedQuality);
    }

    @DisplayName("Degradation cannot cross min / max item quality boundaries.")
    @ParameterizedTest
    @CsvSource({
        "2,-5,0",
        "48,5,50",
        "5,-10,0",
        "45,10,50"
    })
    void minMaxQualityBoundaries(int initialQuality, int appliedQualityDegradation, int expectedQuality) {
        Item item = new Item("some item", 5, initialQuality);
        factor.accept(item, appliedQualityDegradation);
        assertEquals(item.quality, expectedQuality);
    }

    private Item item(String name) {
        return new Item(name, 5, 5);
    }
}
