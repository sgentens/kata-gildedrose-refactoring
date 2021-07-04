package com.gildedrose.aging.strategy;

import com.gildedrose.Item;
import com.gildedrose.utils.ItemUtils;
import org.apache.commons.lang3.StringUtils;

import static com.gildedrose.utils.ItemUtils.MAX_QUALITY;

/**
 * Strategy that handles Aged Brie. Aged Brie gets more valuable over time.
 *
 * @author Steven Gentens
 * @since 0.0.1
 */
public class AgedBrieAgingStrategy implements AgingStrategy {

    @Override
    public boolean isApplicable(Item item) {
        return StringUtils.containsIgnoreCase(item.name, "aged brie");
    }

    @Override
    public void accept(Item item) {
        item.sellIn = item.sellIn - 1;

        int increment = 1;
        if (ItemUtils.isAfterSellDate(item)) {
            increment += 1;
        }

        item.quality = Math.min(MAX_QUALITY, item.quality + increment);
    }
}
