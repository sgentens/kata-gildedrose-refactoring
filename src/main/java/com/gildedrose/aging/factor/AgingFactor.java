package com.gildedrose.aging.factor;

import com.gildedrose.Item;

/**
 * A factor by which the quality degradation of a given item should be multiplied. Multiple factors can apply at the same time.
 *
 * @author Steven Gentens
 * @since 0.0.1
 */
public interface AgingFactor {

    boolean isApplicable(Item item);

    int getAgingFactor();
}
