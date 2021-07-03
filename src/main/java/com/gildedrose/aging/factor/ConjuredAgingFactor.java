package com.gildedrose.aging.factor;

import com.gildedrose.Item;
import com.gildedrose.utils.ItemUtils;

/**
 * Factor by which conjured items degrade in quality.
 *
 * @author Steven Gentens
 * @since 0.0.1
 */
public class ConjuredAgingFactor implements AgingFactor {
    @Override
    public boolean isApplicable(Item item) {
        return ItemUtils.isConjured(item);
    }

    @Override
    public int getAgingFactor() {
        return 2;
    }
}
