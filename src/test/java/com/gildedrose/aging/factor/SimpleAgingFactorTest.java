package com.gildedrose.aging.factor;

import com.gildedrose.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Steven Gentens
 * @since 0.0.1
 */
public class SimpleAgingFactorTest {

    @DisplayName("Conjured items degrade twice as fast - aging factor of 2")
    @ParameterizedTest
    @CsvSource({
        "2,20,-5,15",
        "2,20,5,25",
        "2,20,-10,10",
        "2,20,10,30",
        "3,20,-5,10",
        "3,20,5,30",
        "3,20,-10,0",
        "3,20,10,40"
    })
    void multipliesByGivenFactor(int factor, int initialQuality, int appliedQualityDegradation, int expectedQuality) {
        Item item = new Item("some item", 5, initialQuality);
        SimpleAgingFactor agingFactor = new AlwaysApplicableSimpleAgingFactor() {
            @Override
            int getAgingFactor() {
                return factor;
            }
        };
        agingFactor.accept(item, appliedQualityDegradation);
        assertEquals(item.quality, expectedQuality);
    }

    @DisplayName("Degradation cannot cross min / max item quality boundaries.")
    @ParameterizedTest
    @CsvSource({
        "2,2,-5,0",
        "2,48,5,50",
        "2,5,-10,0",
        "2,45,10,50",
        "3,8,-5,0",
        "3,42,5,50",
        "3,15,-10,0",
        "3,35,10,50"
    })
    void minMaxQualityBoundaries(int factor, int initialQuality, int appliedQualityDegradation, int expectedQuality) {
        Item item = new Item("some item", 5, initialQuality);
        SimpleAgingFactor agingFactor = new AlwaysApplicableSimpleAgingFactor() {
            @Override
            int getAgingFactor() {
                return factor;
            }
        };
        agingFactor.accept(item, appliedQualityDegradation);
        assertEquals(item.quality, expectedQuality);
    }

    static abstract class AlwaysApplicableSimpleAgingFactor extends SimpleAgingFactor {
        @Override
        public boolean isApplicable(Item item) {
            return true;
        }
    }
}
