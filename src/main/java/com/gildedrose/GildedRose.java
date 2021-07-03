package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
            if (!isAgedBrie(item) && !isBackstagePasses(item)) {
                if (item.quality > 0 && !isSulfuras(item)) {
                    item.quality = item.quality - 1;
                }
            } else if (item.quality < 50) {
                item.quality = item.quality + 1;
                if (isBackstagePasses(item)) {
                    handleBackstagePasses(item);
                }
            }

            if (!isSulfuras(item)) {
                item.sellIn = item.sellIn - 1;
            }

            if (isAfterSellDate(item)) {
                if (isAgedBrie(item) && item.quality < 50) {
                    item.quality = item.quality + 1;
                } else if (isBackstagePasses(item)) {
                    item.quality = 0;
                } else if (!isSulfuras(item)) {
                    item.quality = item.quality > 0 ? item.quality - 1 : 0;
                }
            }
        }
    }

    private void handleBackstagePasses(Item item) {
        if (item.sellIn < 11) {
            item.quality = item.quality + 1;
        }

        if (item.sellIn < 6) {
            item.quality = item.quality + 1;
        }
    }

    private boolean isAfterSellDate(Item item) {
        return item.sellIn < 0;
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
