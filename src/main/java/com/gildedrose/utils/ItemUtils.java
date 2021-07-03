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

    public static final int MAX_QUALITY = 50;
    public static final int MIN_QUALITY = 0;

    public static boolean isAfterSellDate(Item item) {
        return item.sellIn < 0;
    }

    /**
     * Defines whether an item is legendary. Currently, we only know of one legendary item called Sulfuras.
     */
    public static boolean isLegendary(Item item) {
        // Hopefully we can someday come to an agreement with the goblin, to add a type indicator to items.
        return StringUtils.containsIgnoreCase(item.name, "sulfuras");
    }

    /**
     * Defines whether an item is conjured.
     */
    public static boolean isConjured(Item item) {
        // Hopefully we can someday come to an agreement with the goblin, to add a type indicator to items.
        String itemNameTrimmed = StringUtils.trim(item.name);
        return StringUtils.startsWithIgnoreCase(itemNameTrimmed, "conjured");
    }
}
