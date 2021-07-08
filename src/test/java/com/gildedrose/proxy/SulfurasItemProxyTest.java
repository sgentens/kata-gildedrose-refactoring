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
public class SulfurasItemProxyTest {

    @ParameterizedTest
    @CsvSource({
        "40,40",
        "50,50",
        "48,48",
        "10,10",
        "10,10",
        "0,0",
        "3,3"
    })
    void neverIncrementSellInOrUpdateQuality(int quality, int expectedQuality) {
        Item item = new Item("test", 5, quality);
        ItemProxy proxy = new SulfurasItemProxy(item);
        proxy.incrementSellInAndUpdateQuality();
        verifySellInAndQuality(item, 5, expectedQuality);
    }


    @Test
    void neverIncrementsNextDay() {
        Item item = new Item("test", 5, 10);
        ItemProxy proxy = new SulfurasItemProxy(item);
        proxy.nextDay();
        verifySellInAndQuality(item, 5, 10);
    }

    @Test
    void neverUpdateQuality() {
        Item item = new Item("test", 5, 10);
        ItemProxy proxy = new SulfurasItemProxy(item);
        proxy.updateQuality();
        verifySellInAndQuality(item, 5, 10);
    }

    @Test
    void neverChangesQuality() {
        Item item = new Item("test", 5, 10);
        ItemProxy proxy = new SulfurasItemProxy(item);
        assertEquals(0, proxy.calculateNextQualityDegradation());
    }

    @ParameterizedTest
    @CsvSource({
        " conjured test,20,50",
        " CoNJuReD test,5,10",
        " \tCoNJuReD test,-3,10",
        "Conjured test,-10,1",
    })
    void conjuredVersionDoesNotUpdateSellDateNorChangesQuality(String itemName, int sellIn, int quality) {
        Item item = new Item(itemName, sellIn, quality);
        ItemProxy proxy = new SulfurasItemProxy(item);
        proxy.incrementSellInAndUpdateQuality();
        verifySellInAndQuality(item, sellIn, quality);
    }
}
