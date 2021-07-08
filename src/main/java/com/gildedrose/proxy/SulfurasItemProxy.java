package com.gildedrose.proxy;

import com.gildedrose.Item;

/**
 * @author Steven Gentens
 * @since 0.0.1
 */
final class SulfurasItemProxy extends ItemProxy {

    SulfurasItemProxy(Item item) {
        super(item);
    }

    @Override
    public void incrementSellInAndUpdateQuality() {
        // never needs to be sold or degrades in quality
    }

    @Override
    void incrementSellIn() {
        // never sold
    }

    @Override
    int calculateNextQualityDegradation() {
        // never degrades in quality
        return 0;
    }
}
