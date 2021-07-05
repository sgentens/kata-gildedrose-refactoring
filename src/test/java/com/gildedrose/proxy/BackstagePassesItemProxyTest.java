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
public class BackstagePassesItemProxyTest {

    @ParameterizedTest
    @CsvSource({
        "20,50,50",
        "12,50,50",
        "8,50,50",
        "3,50,50",
        "20,27,28",
        "12,27,29",
        "8,27,29",
        "3,27,30",
        "20,0,1",
        "16,0,1",
        "15,0,2",
        "12,0,2",
        "8,0,2",
        "5,0,3",
        "3,0,3",
        "0,50,0",
        "0,0,0",
        "-1,0,0",
    })
    void increasesInQualityAsItMovesCloserToSellDate(int sellIn, int quality, int expectedQuality) {
        Item item = new Item("test", sellIn, quality);
        ItemProxy proxy = new BackstagePassesItemProxy(item);
        proxy.incrementSellInAndUpdateQuality();
        verifySellInAndQuality(item, 4, expectedQuality);
    }

    @Test
    void sellInZeroMeansTheItemWasNotSoldToday() {
        Item item = new Item("test", 0, 10);
        ItemProxy proxy = new BackstagePassesItemProxy(item);
        proxy.incrementSellInAndUpdateQuality();
        verifySellInAndQuality(item, -1, 0);
    }

    @ParameterizedTest
    @CsvSource({
        "50,0",
        "27,0",
        "10,0",
        "3,0",
        "0,0",
    })
    void qualityIsZeroAfterSellDate(int quality, int expectedQuality) {
        Item item = new Item("test", -5, quality);
        ItemProxy proxy = new BackstagePassesItemProxy(item);
        proxy.incrementSellInAndUpdateQuality();
        verifySellInAndQuality(item, -6, expectedQuality);
    }

    @Test
    void nextDay() {
        Item item = new Item("test", 5, 10);
        ItemProxy proxy = new BackstagePassesItemProxy(item);
        proxy.nextDay();
        verifySellInAndQuality(item, 4, 10);
    }

    @Test
    void updateQuality() {
        Item item = new Item("test", 5, 10);
        ItemProxy proxy = new BackstagePassesItemProxy(item);
        proxy.updateQuality();
        verifySellInAndQuality(item, 5, 11);
    }

    @Test
    void increasesQualityByOneFifteenDaysBeforeSellDate() {
        Item item = new Item("test", 15, 10);
        ItemProxy proxy = new BackstagePassesItemProxy(item);
        assertEquals(1, proxy.calculateNextQualityDegradation());
    }

    @Test
    void increasesQualityByTwoTenDaysBeforeSellDate() {
        Item item = new Item("test", 10, 10);
        ItemProxy proxy = new BackstagePassesItemProxy(item);
        assertEquals(1, proxy.calculateNextQualityDegradation());
    }

    @Test
    void increasesQualityByThreeFiveDaysBeforeSellDate() {
        Item item = new Item("test", 5, 10);
        ItemProxy proxy = new BackstagePassesItemProxy(item);
        assertEquals(1, proxy.calculateNextQualityDegradation());
    }
}
