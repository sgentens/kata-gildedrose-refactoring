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
public class AgedBrieItemProxyTest {

    @ParameterizedTest
    @CsvSource({
        "50,50",
        "27,28",
        "10,11",
        "3,4",
        "0,1",
    })
    void increasesInQualityAsItMovesCloserToSellDate(int quality, int expectedQuality) {
        Item item = new Item("test", 5, quality);
        ItemProxy proxy = new AgedBrieItemProxy(item);
        proxy.incrementSellInAndUpdateQuality();
        verifySellInAndQuality(item, 4, expectedQuality);
    }

    @Test
    void sellInZeroMeansTheItemWasNotSoldToday() {
        Item item = new Item("test", 0, 10);
        ItemProxy proxy = new AgedBrieItemProxy(item);
        proxy.incrementSellInAndUpdateQuality();
        verifySellInAndQuality(item, -1, 12);
    }

    @ParameterizedTest
    @CsvSource({
        "50,50",
        "27,29",
        "10,11",
        "3,5",
        "0,2",
    })
    void increasesInQualityTwiceAsFastAfterSellDate(int quality, int expectedQuality) {
        Item item = new Item("test", -5, quality);
        ItemProxy proxy = new AgedBrieItemProxy(item);
        proxy.incrementSellInAndUpdateQuality();
        verifySellInAndQuality(item, -6, expectedQuality);
    }

    @Test
    void nextDay() {
        Item item = new Item("test", 5, 10);
        ItemProxy proxy = new AgedBrieItemProxy(item);
        proxy.nextDay();
        verifySellInAndQuality(item, 4, 10);
    }

    @Test
    void updateQuality() {
        Item item = new Item("test", 5, 10);
        ItemProxy proxy = new AgedBrieItemProxy(item);
        proxy.updateQuality();
        verifySellInAndQuality(item, 5, 11);
    }

    @Test
    void increasesQualityByOneBeforeSellDate() {
        Item item = new Item("test", 5, 10);
        ItemProxy proxy = new AgedBrieItemProxy(item);
        assertEquals(1, proxy.calculateNextQualityDegradation());
    }

    @Test
    void increasesQualityByTwoAfterSellDate() {
        Item item = new Item("test", -5, 10);
        ItemProxy proxy = new AgedBrieItemProxy(item);
        assertEquals(2, proxy.calculateNextQualityDegradation());
    }
}
