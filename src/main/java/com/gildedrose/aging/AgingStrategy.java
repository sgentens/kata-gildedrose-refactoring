package com.gildedrose.aging;

import com.gildedrose.Item;

import java.util.function.Consumer;

/**
 * Strategy that determines how an item ages.
 * Only one strategy should ever apply to a given item.
 *
 * @author Steven Gentens
 * @since 0.0.1
 */
public interface AgingStrategy extends Consumer<Item> {

    boolean isApplicable(Item item);
}
