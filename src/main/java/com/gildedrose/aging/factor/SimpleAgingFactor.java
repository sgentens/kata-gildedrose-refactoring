package com.gildedrose.aging.factor;

import com.gildedrose.Item;
import com.gildedrose.utils.AgingFactorUtils;

import static com.gildedrose.utils.ItemUtils.MAX_QUALITY;
import static com.gildedrose.utils.ItemUtils.MIN_QUALITY;

/**
 * Applies the aging factor based on a fixed factor to the item. If the remaining degradation that is applied
 * would cross an item quality boundary (e.g. min/max quality), the quality is set to said boundary.
 *
 * @author Steven Gentens
 * @since 0.0.1
 */
public abstract class SimpleAgingFactor implements AgingFactor {

    /**
     * The aging factor that should be applied when calculation the new quality degradation.
     */
    abstract int getAgingFactor();

    @Override
    public void accept(Item item, int qualityDegradation) {
        int degradationToApply = AgingFactorUtils.calculateNewItemQuality(qualityDegradation, getAgingFactor());
        if (qualityDegradation > 0) {
            item.quality = Math.min(MAX_QUALITY, item.quality + degradationToApply);
        } else {
            item.quality = Math.max(MIN_QUALITY, item.quality + degradationToApply);
        }
    }
}
