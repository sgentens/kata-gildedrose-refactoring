package com.gildedrose.type;

import com.gildedrose.Item;

/**
 * @author Steven Gentens
 * @since 0.0.1
 */
public class ItemNotConvertibleToItemProxyException extends RuntimeException {

    ItemNotConvertibleToItemProxyException(Item item) {
        super("Unable to convert item '" + item.name + "' to an ItemProxy. No matching ItemType.");
    }
}
