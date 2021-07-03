package com.gildedrose.aging.strategy;

import com.gildedrose.Item;
import com.gildedrose.utils.ItemUtils;

/**
 * Strategy that applies to legendary items. Legendary items are never modified.
 *
 * @author Steven Gentens
 * @since 0.0.1
 */
public class LegendaryAgingStrategy implements AgingStrategy {
    @Override
    public boolean isApplicable(Item item) {
        return ItemUtils.isLegendary(item);
    }

    @Override
    public void accept(Item item) {
    }
}
