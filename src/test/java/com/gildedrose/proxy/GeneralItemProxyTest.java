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
public class GeneralItemProxyTest {

    @ParameterizedTest
    @CsvSource({
        "50,49",
        "27,26",
        "10,9",
        "3,2",
        "0,0",
    })
    void degradesAsItMovesCloserToSellDate(int quality, int expectedQuality) {
        Item item = new Item("test", 5, quality);
        ItemProxy proxy = new GeneralItemProxy(item);
        proxy.incrementSellInAndUpdateQuality();
        verifySellInAndQuality(item, 4, expectedQuality);
    }

    @Test
    void sellInZeroMeansTheItemWasNotSoldToday() {
        Item item = new Item("test", 0, 10);
        ItemProxy proxy = new GeneralItemProxy(item);
        proxy.incrementSellInAndUpdateQuality();
        verifySellInAndQuality(item, -1, 8);
    }

    @ParameterizedTest
    @CsvSource({
        "50,48",
        "27,25",
        "10,8",
        "3,1",
        "0,0",
    })
    void degradesTwiceAsFastAfterSellDate(int quality, int expectedQuality) {
        Item item = new Item("test", -5, quality);
        ItemProxy proxy = new GeneralItemProxy(item);
        proxy.incrementSellInAndUpdateQuality();
        verifySellInAndQuality(item, -6, expectedQuality);
    }

    @ParameterizedTest
    @CsvSource({
        " conjured test,20,50,48",
        " conjured test,3,10,8",
        " CoNJuReD test,-5,10,6",
        " \tCoNJuReD test,-10,10,6",
        "Conjured test,-10,1,0",
    })
    void conjuredVersionIncreasesInQualityTwiceAsFast(String itemName, int sellIn, int quality, int expectedQuality) {
        Item item = new Item(itemName, sellIn, quality);
        ItemProxy proxy = new GeneralItemProxy(item);
        proxy.incrementSellInAndUpdateQuality();
        verifySellInAndQuality(item, sellIn - 1, expectedQuality);
    }

    @Test
    void nextDay() {
        Item item = new Item("test", 5, 10);
        ItemProxy proxy = new GeneralItemProxy(item);
        proxy.incrementSellIn();
        verifySellInAndQuality(item, 4, 10);
    }

    @Test
    void updateQuality() {
        Item item = new Item("test", 5, 10);
        ItemProxy proxy = new GeneralItemProxy(item);
        proxy.updateQuality();
        verifySellInAndQuality(item, 5, 9);
    }

    @Test
    void reducesQualityByOneBeforeSellDate() {
        Item item = new Item("test", 5, 10);
        ItemProxy proxy = new GeneralItemProxy(item);
        assertEquals(-1, proxy.calculateNextQualityDegradation());
    }

    @Test
    void reducesQualityByTwoAfterSellDate() {
        Item item = new Item("test", -5, 10);
        ItemProxy proxy = new GeneralItemProxy(item);
        assertEquals(-2, proxy.calculateNextQualityDegradation());
    }
}
