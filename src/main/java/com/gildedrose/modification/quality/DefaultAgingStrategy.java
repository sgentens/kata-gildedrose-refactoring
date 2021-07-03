package com.gildedrose.modification.quality;

import com.gildedrose.Item;

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
        if (!isBackstagePasses(item)) {
            if (item.quality > 0 && !isSulfuras(item)) {
                item.quality = item.quality - 1;
            }
        } else if (item.quality < 50) {
            item.quality = item.quality + 1;
            if (isBackstagePasses(item)) {
                handleBackstagePasses(item);
            }
        }

        if (!isSulfuras(item)) {
            item.sellIn = item.sellIn - 1;
        }

        if (isAfterSellDate(item)) {
            if (isBackstagePasses(item)) {
                item.quality = 0;
            } else if (!isSulfuras(item)) {
                item.quality = item.quality > 0 ? item.quality - 1 : 0;
            }
        }
    }

    private void handleBackstagePasses(Item item) {
        if (item.sellIn < 11) {
            item.quality = item.quality + 1;
        }

        if (item.sellIn < 6) {
            item.quality = item.quality + 1;
        }
    }

    private boolean isAgedBrie(Item item) {
        return "Aged Brie".equals(item.name);
    }

    private boolean isBackstagePasses(Item item) {
        return "Backstage passes to a TAFKAL80ETC concert".equals(item.name);
    }

    private boolean isSulfuras(Item item) {
        return "Sulfuras, Hand of Ragnaros".equals(item.name);
    }
}
