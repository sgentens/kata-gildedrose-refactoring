package com.gildedrose.proxy;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.gildedrose.utils.GildedRoseTestUtils.verifySellInAndQuality;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Steven Gentens
 * @since 0.0.1
 */
public class ItemProxyTest {

    @ParameterizedTest
    @CsvSource({
        "1,40,41",
        "1,50,50",
        "5,48,50",
        "-1,10,9",
        "-1,10,9",
        "-1,0,0",
        "-5,3,0"
    })
    void incrementSellInAndUpdateQuality(int fixedDegradation, int quality, int expectedQuality) {
        Item item = new Item("test", 5, quality);
        ItemProxy proxy = new ItemProxy(item) {
            @Override
            int calculateNextQualityDegradation() {
                return fixedDegradation;
            }
        };
        proxy.incrementSellInAndUpdateQuality();
        verifySellInAndQuality(item, 4, expectedQuality);
    }


    @Test
    void nextDay() {
        Item item = new Item("test", 5, 10);
        ItemProxy proxy = new FixedDegradationItemProxy(item);
        proxy.nextDay();
        verifySellInAndQuality(item, 4, 10);
    }

    @Test
    void updateQuality() {
        Item item = new Item("test", 5, 10);
        ItemProxy proxy = new FixedDegradationItemProxy(item);
        proxy.updateQuality();
        verifySellInAndQuality(item, 5, 9);
    }

    @Test
    void calculateNextQualityDegradation() {
        Item item = new Item("test", 5, 10);
        ItemProxy proxy = new FixedDegradationItemProxy(item);
        assertEquals(-1, proxy.calculateNextQualityDegradation());
    }


    static class FixedDegradationItemProxy extends ItemProxy {

        FixedDegradationItemProxy(Item item) {
            super(item);
        }

        @Override
        int calculateNextQualityDegradation() {
            return -1;
        }
    }
}
