package com.gildedrose.aging;

import com.gildedrose.Item;
import org.apache.commons.lang3.StringUtils;

import static com.gildedrose.utils.ItemUtils.isAfterSellDate;

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
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }

        item.sellIn = item.sellIn - 1;

        if (isAfterSellDate(item) && item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }
}
