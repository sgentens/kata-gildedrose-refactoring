package com.gildedrose.aging.strategy;

import com.gildedrose.Item;
import com.gildedrose.utils.ItemUtils;

import static com.gildedrose.utils.ItemUtils.MIN_QUALITY;

/**
 * Default modification strategy which executes if no other strategy is applicable.
 *
 * @author Steven Gentens
 * @since 0.0.1
 */
public class DefaultAgingStrategy implements AgingStrategy {
    @Override
    public boolean isApplicable(Item item) {
        return true;
    }

    @Override
    public void accept(Item item) {
        int increment = 0;
        if (item.quality > MIN_QUALITY) {
            increment -= 1;
        }

        item.sellIn = item.sellIn - 1;

        if (ItemUtils.isAfterSellDate(item)) {
            increment -= 1;
        }

        item.quality = Math.max(0, item.quality + increment);
    }
}
