package com.gildedrose.aging.strategy;

import com.gildedrose.Item;
import com.gildedrose.utils.ItemUtils;
import org.apache.commons.lang3.StringUtils;

import static com.gildedrose.utils.ItemUtils.MAX_QUALITY;
import static com.gildedrose.utils.ItemUtils.MIN_QUALITY;

/**
 * Strategy that applies to backstage passes. Backstage passes get more valuable over time,
 * but are utterly useless when the concert has passed.
 *
 * @author Steven Gentens
 * @since 0.0.1
 */
public class BackstagePassesAgingStrategy implements AgingStrategy {

    @Override
    public boolean isApplicable(Item item) {
        return StringUtils.containsIgnoreCase(item.name, "backstage pass");
    }

    @Override
    public void accept(Item item) {
        int increment = 1;
        if (item.sellIn < 11) {
            increment += 1;
        }

        if (item.sellIn < 6) {
            increment += 1;
        }

        item.quality = Math.min(MAX_QUALITY, item.quality + increment);

        item.sellIn = item.sellIn - 1;

        if (ItemUtils.isAfterSellDate(item)) {
            item.quality = MIN_QUALITY;
        }
    }
}
