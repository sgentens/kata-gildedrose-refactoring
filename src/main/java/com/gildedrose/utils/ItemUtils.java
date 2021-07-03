package com.gildedrose.utils;

import com.gildedrose.Item;
import org.apache.commons.lang3.StringUtils;

/**
 * Utility class for {@link com.gildedrose.Item} operations.
 *
 * @author Steven Gentens
 * @since 0.0.1
 */
public class ItemUtils {

    public static boolean isAfterSellDate(Item item) {
        return item.sellIn < 0;
    }

    /**
     * Returns whether an item is legendary. Currently, we only know of one legendary item called Sulfuras.
     */
    public static boolean isLegendary(Item item) {
        // Hopefully we can someday come to an agreement with the goblin, to add a type indicator to items.
        return StringUtils.containsIgnoreCase(item.name, "sulfuras");
    }
}
