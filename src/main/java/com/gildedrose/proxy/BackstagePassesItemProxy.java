package com.gildedrose.proxy;

import com.gildedrose.Item;

/**
 * @author Steven Gentens
 * @since 0.0.1
 */
public class BackstagePassesItemProxy extends ItemProxy {

    public BackstagePassesItemProxy(Item item) {
        super(item);
    }

    @Override
    int calculateNextQualityDegradation() {
        return 0;
    }
}
