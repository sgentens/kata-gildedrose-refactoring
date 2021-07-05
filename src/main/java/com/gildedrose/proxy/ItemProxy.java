package com.gildedrose.proxy;

import com.gildedrose.Item;

/**
 * Base class that acts as a proxy for operations on {@link Item}.
 *
 * @author Steven Gentens
 * @since 0.0.1
 */
public abstract class ItemProxy {

    static final int MAX_QUALITY = 50;
    static final int MIN_QUALITY = 0;

    private final Item item;

    ItemProxy(Item item) {
        this.item = item;
    }

    /**
     * Increments the sell date and then applies the quality degradation to the item.
     * Should the min/max quality be crossed, it gets reset to said boundary.
     */
    public void incrementSellInAndUpdateQuality() {
        nextDay();
        updateQuality();
        resetToBoundaryIfNecessary();
    }

    final int getSellIn() {
        return item.sellIn;
    }

    final boolean isAfterSellDate() {
        return item.sellIn < 0;
    }

    /**
     * Indicates the item was not sold and moves closer to the sale deadline.
     */
    void nextDay() {
        item.sellIn -= 1;
    }

    void updateQuality() {
        item.quality += calculateNextQualityDegradation();
    }

    /**
     * Calculates the quality degradation that will occur on this day iteration.
     */
    abstract int calculateNextQualityDegradation();

    private void resetToBoundaryIfNecessary() {
        if (item.quality > MAX_QUALITY) {
            item.quality = MAX_QUALITY;
        } else if (item.quality < MIN_QUALITY) {
            item.quality = MIN_QUALITY;
        }
    }
}
