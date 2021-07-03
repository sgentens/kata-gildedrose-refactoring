package com.gildedrose.aging;

import com.gildedrose.Item;
import org.apache.commons.lang3.StringUtils;

import static com.gildedrose.utils.ItemUtils.isAfterSellDate;

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
        if (item.quality < 50) {
            item.quality = item.quality + 1;

            if (item.sellIn < 11) {
                item.quality = item.quality + 1;
            }

            if (item.sellIn < 6) {
                item.quality = item.quality + 1;
            }
        }

        item.sellIn = item.sellIn - 1;

        if (isAfterSellDate(item)) {
            item.quality = 0;
        }
    }
}
