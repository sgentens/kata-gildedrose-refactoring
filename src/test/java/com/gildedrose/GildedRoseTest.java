package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    public static final String CONJURED = "Conjured";

    public static final String DEXTERITY_VEST = "+5 Dexterity Vest";
    public static final String AGED_BRIE = "Aged Brie";
    public static final String ELIXIR_MONGOOSE = "Elixir of the Mongoose";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";

    @ParameterizedTest
    @CsvSource({
        DEXTERITY_VEST + ", 15, 20, 19",
        DEXTERITY_VEST + ", 5, 20, 19",
        DEXTERITY_VEST + ", 0, 20, 18",
        DEXTERITY_VEST + ", -5, 20, 18",
        AGED_BRIE + ", 15, 20, 21",
        AGED_BRIE + ", 5, 20, 21",
        AGED_BRIE + ", 0, 20, 22",
        AGED_BRIE + ", -5, 20, 22",
        ELIXIR_MONGOOSE + ", 15, 20, 19",
        ELIXIR_MONGOOSE + ", 5, 20, 19",
        ELIXIR_MONGOOSE + ", 0, 20, 18",
        ELIXIR_MONGOOSE + ", -5, 20, 18",
        BACKSTAGE_PASSES + ", 15, 20, 21",
        BACKSTAGE_PASSES + ", 10, 20, 22",
        BACKSTAGE_PASSES + ", 5, 20, 23",
        BACKSTAGE_PASSES + ", 0, 20, 0",
        BACKSTAGE_PASSES + ", -5, 20, 0",
        BACKSTAGE_PASSES + ", 10, 49, 50",
        BACKSTAGE_PASSES + ", 5, 49, 50",
    })
    @DisplayName("increment 1 day")
    void incrementOneDay(String name, int sellInDays, int initialQuality, int expectedQuality) {
        Item item = new Item(name, sellInDays, initialQuality);
        incrementSingleQualityUpdateForItems(item);
        assertEquals(sellInDays - 1, item.sellIn);
        assertEquals(expectedQuality, item.quality);
    }

    @ParameterizedTest
    @ValueSource(ints = {15, 5, 0, -5})
    @DisplayName("Sulfuras is never sold or changes quality")
    void sulfurasIncrementByOneDay(int sellInDays) {
        Item item = new Item(SULFURAS, sellInDays, 80);
        incrementSingleQualityUpdateForItems(item);
        assertEquals(sellInDays, item.sellIn);
        assertEquals(80, item.quality);
    }

    @ParameterizedTest
    @CsvSource({
        DEXTERITY_VEST + ", 15, 0, 0",
        DEXTERITY_VEST + ", 15, -4, 0",
        AGED_BRIE + ", 15, -5, 0",
        AGED_BRIE + ", 15, 0, 1",
        ELIXIR_MONGOOSE + ", 15, 0, 0",
        ELIXIR_MONGOOSE + ", 15, -4, 0",
        BACKSTAGE_PASSES + ", 15, -5, 0",
        BACKSTAGE_PASSES + ", 15, 0, 1",
    })
    @DisplayName("Quality of an item is never negative")
    void qualityNeverNegative(String name, int sellInDays, int initialQuality, int expectedQuality) {
        Item item = new Item(name, sellInDays, initialQuality);
        incrementSingleQualityUpdateForItems(item);
        assertEquals(expectedQuality, item.quality);
    }

    @ParameterizedTest
    @CsvSource({
        DEXTERITY_VEST + ", 15, 50, 49",
        DEXTERITY_VEST + ", 15, 55, 50",
        AGED_BRIE + ", 15, 50, 50",
        AGED_BRIE + ", 15, 55, 55",
        ELIXIR_MONGOOSE + ", 15, 50, 49",
        ELIXIR_MONGOOSE + ", 15, 55, 50",
        BACKSTAGE_PASSES + ", 15, 50, 50",
        BACKSTAGE_PASSES + ", 15, 55, 55",
    })
    @DisplayName("Quality is never above 50")
    void qualityNeverMoreThanFifty(String name, int sellInDays, int initialQuality, int expectedQuality) {
        Item item = new Item(name, sellInDays, initialQuality);
        incrementSingleQualityUpdateForItems(item);
        assertEquals(expectedQuality, item.quality);
    }

//    @ParameterizedTest
//    @CsvSource({
//        CONJURED + " " + DEXTERITY_VEST + ", 15, 20, 18",
//        CONJURED + " " + DEXTERITY_VEST + ", 5, 20, 18",
//        CONJURED + " " + DEXTERITY_VEST + ", 0, 20, 16",
//        CONJURED + " " + DEXTERITY_VEST + ", -5, 20, 16",
//        CONJURED + " " + AGED_BRIE + ", 15, 20, 22",
//        CONJURED + " " + AGED_BRIE + ", 5, 20, 22",
//        CONJURED + " " + AGED_BRIE + ", 0, 20, 24",
//        CONJURED + " " + AGED_BRIE + ", -5, 20, 24",
//        CONJURED + " " + ELIXIR_MONGOOSE + ", 15, 20, 18",
//        CONJURED + " " + ELIXIR_MONGOOSE + ", 5, 20, 18",
//        CONJURED + " " + ELIXIR_MONGOOSE + ", 0, 20, 16",
//        CONJURED + " " + ELIXIR_MONGOOSE + ", -5, 20, 16",
//        CONJURED + " " + BACKSTAGE_PASSES + ", 15, 20, 22",
//        CONJURED + " " + BACKSTAGE_PASSES + ", 10, 20, 24",
//        CONJURED + " " + BACKSTAGE_PASSES + ", 5, 20, 26",
//        CONJURED + " " + BACKSTAGE_PASSES + ", 0, 20, 0",
//        CONJURED + " " + BACKSTAGE_PASSES + ", -5, 20, 0",
//        "'" + CONJURED + " " + SULFURAS + "', -5, 20, 20",
//    })
//    @DisplayName("Conjured items degrade twice as fast - increment 1 day")
//    void conjuredItems(String name, int sellInDays, int initialQuality, int expectedQuality) {
//        Item item = new Item(name, sellInDays, initialQuality);
//        incrementSingleQualityUpdateForItems(item);
//        assertEquals(expectedQuality, item.quality);
//    }

    @Test
    @DisplayName("updateQuality applies to all items - increment 1 day")
    void incrementAppliesToAllItems() {
        Item item1 = new Item("item", 5, 20);
//        Item item2 = new Item("Conjured backstage passes", 15, 20);
        Item item3 = new Item("Aged Brie", 20, 20);
        incrementSingleQualityUpdateForItems(item1, /*item2,*/ item3);
        assertEquals(19, item1.quality);
//        assertEquals(22, item2.quality);
        assertEquals(21, item3.quality);
    }

    private void incrementSingleQualityUpdateForItems(Item... items) {
        GildedRose app = new GildedRose(items);
        app.updateQuality();
    }
}
