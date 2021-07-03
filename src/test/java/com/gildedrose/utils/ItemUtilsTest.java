package com.gildedrose.utils;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Steven Gentens
 * @since 0.0.1
 */
public class ItemUtilsTest {

    @Test
    void isAfterSellDate() {
        assertFalse(ItemUtils.isAfterSellDate(new Item("test", 5, 20)));
        assertFalse(ItemUtils.isAfterSellDate(new Item("test", 0, 20)));
        assertTrue(ItemUtils.isAfterSellDate(new Item("test", -1, 20)));
        assertTrue(ItemUtils.isAfterSellDate(new Item("test", -5, 20)));
    }

    @Test
    void isLegendaryItem() {
        assertFalse(ItemUtils.isAfterSellDate(new Item("brie", 5, 20)));
        assertFalse(ItemUtils.isAfterSellDate(new Item("sword", 0, 20)));
        assertTrue(ItemUtils.isAfterSellDate(new Item("sulfuras, a legendary item", -1, 20)));
        assertTrue(ItemUtils.isAfterSellDate(new Item("Sulfuras, Hand of Ragnaros", -5, 20)));
        assertTrue(ItemUtils.isAfterSellDate(new Item("SulFuRAS", -5, 20)));
    }

    @Test
    void isConjuredItem() {
        assertTrue(ItemUtils.isConjured(new Item("Conjured Aged Brie", 5, 5)));
        assertTrue(ItemUtils.isConjured(new Item("conjured Aged Brie", 5, 5)));
        assertTrue(ItemUtils.isConjured(new Item("  Conjured Sulfuras", 5, 5)));
        assertTrue(ItemUtils.isConjured(new Item("   ConJuRed SuLfuRas", 5, 5)));
        assertTrue(ItemUtils.isConjured(new Item("CONJURED STUFF", 5, 5)));
        assertFalse(ItemUtils.isConjured(new Item("not conjured stuff", 5, 5)));
    }
}
