package com.gildedrose.proxy;

import com.gildedrose.Item;

/**
 * @author Steven Gentens
 * @since 0.0.1
 */
public class AgedBrieItemProxy extends ItemProxy {

    AgedBrieItemProxy(Item item) {
        super(item);
    }

    @Override
    int calculateNextQualityDegradation() {
        return 0;
    }
}
