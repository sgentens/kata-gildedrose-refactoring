package com.gildedrose.proxy;

import com.gildedrose.Item;

/**
 * @author Steven Gentens
 * @since 0.0.1
 */
public class AgedBrieItemProxy extends ItemProxy {

    public AgedBrieItemProxy(Item item) {
        super(item);
    }

    @Override
    int calculateNextQualityDegradation() {
        if (isAfterSellDate()) {
            return 2;
        }
        return 1;
    }
}
