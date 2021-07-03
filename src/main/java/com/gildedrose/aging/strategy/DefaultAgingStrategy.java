package com.gildedrose.aging.strategy;

import com.gildedrose.Item;

import static com.gildedrose.utils.ItemUtils.MIN_QUALITY;
import static com.gildedrose.utils.ItemUtils.isAfterSellDate;

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
        if (item.quality > MIN_QUALITY) {
            item.quality = item.quality - 1;
        }

        item.sellIn = item.sellIn - 1;

        if (isAfterSellDate(item)) {
            item.quality = item.quality > MIN_QUALITY ? item.quality - 1 : MIN_QUALITY;
        }
    }
}