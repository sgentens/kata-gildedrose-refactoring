package com.gildedrose;

import com.gildedrose.proxy.AgedBrieItemProxy;
import com.gildedrose.proxy.BackstagePassesItemProxy;
import com.gildedrose.proxy.GeneralItemProxy;
import com.gildedrose.proxy.SulfurasItemProxy;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (isSulfuras(item)) {
                new SulfurasItemProxy(item).incrementSellInAndUpdateQuality();
            } else if (isAgedBrie(item)) {
                new AgedBrieItemProxy(item).incrementSellInAndUpdateQuality();
            } else if (isBackstagePasses(item)) {
                new BackstagePassesItemProxy(item).incrementSellInAndUpdateQuality();
            } else {
                new GeneralItemProxy(item).incrementSellInAndUpdateQuality();
            }
        }
    }

    private boolean isAgedBrie(Item item) {
        return "Aged Brie".equals(item.name);
    }

    private boolean isBackstagePasses(Item item) {
        return "Backstage passes to a TAFKAL80ETC concert".equals(item.name);
    }

    private boolean isSulfuras(Item item) {
        return "Sulfuras, Hand of Ragnaros".equals(item.name);
    }
}
