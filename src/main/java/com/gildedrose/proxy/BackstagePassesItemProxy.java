package com.gildedrose.proxy;

import com.gildedrose.Item;

/**
 * @author Steven Gentens
 * @since 0.0.1
 */
public final class BackstagePassesItemProxy extends ItemProxy {

    public BackstagePassesItemProxy(Item item) {
        super(item);
    }

    @Override
    int calculateNextQualityDegradation() {
        if (isAfterSellDate()) {
            // degrade more than the max so that we get reset to 0;
            return -1 * (MAX_QUALITY + 1);
        }
        if (getSellIn() < 5) {
            return 3;
        }
        if (getSellIn() < 10) {
            return 2;
        }
        return 1;
    }
}
