package com.gildedrose.proxy;

import com.gildedrose.Item;

/**
 * @author Steven Gentens
 * @since 0.0.1
 */
final class GeneralItemProxy extends ItemProxy {

    GeneralItemProxy(Item item) {
        super(item);
    }

    @Override
    int calculateNextQualityDegradation() {
        if (isAfterSellDate()) {
            return -2;
        }
        return -1;
    }
}
