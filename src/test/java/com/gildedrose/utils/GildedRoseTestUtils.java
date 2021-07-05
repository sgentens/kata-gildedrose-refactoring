package com.gildedrose.utils;

import com.gildedrose.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Steven Gentens
 * @since 0.0.1
 */
public class GildedRoseTestUtils {

    public static void verifySellInAndQuality(Item item, int expectedSellIn, int expectedQuality) {
        assertEquals(expectedSellIn, item.sellIn);
        assertEquals(expectedQuality, item.quality);
    }
}
