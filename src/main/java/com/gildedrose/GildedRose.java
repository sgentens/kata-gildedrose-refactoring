package com.gildedrose;

import com.gildedrose.type.ItemType;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            ItemType.convertToItemProxy(item).incrementSellInAndUpdateQuality();
        }
    }
}
