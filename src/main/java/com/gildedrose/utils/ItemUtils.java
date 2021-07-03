package com.gildedrose.utils;

import com.gildedrose.Item;

/**
 * Utility class for {@link com.gildedrose.Item} operations.
 *
 * @author Steven Gentens
 * @since 0.0.1
 */
public class ItemUtils {

    public static boolean isAfterSellDate(Item item){
        return item.sellIn < 0;
    }
}
