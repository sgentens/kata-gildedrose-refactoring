package com.gildedrose.modification.quality;

import com.gildedrose.Item;

import java.util.function.Consumer;

/**
 * Strategy that determines how the quality of an item is modified.
 * Only one strategy should ever apply to a given item.
 *
 * @author Steven Gentens
 * @since 0.0.1
 */
public interface QualityModificationStrategy extends Consumer<Item> {
    boolean isApplicable(Item item);
}
