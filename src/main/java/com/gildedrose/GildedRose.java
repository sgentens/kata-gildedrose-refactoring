package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (!isAgedBrie(items[i]) && !isBackstagePasses(items[i])) {
                if (items[i].quality > 0 && !isSulfuras(items[i])) {
                    items[i].quality = items[i].quality - 1;
                }
            } else if (items[i].quality < 50) {
                items[i].quality = items[i].quality + 1;

                if (isBackstagePasses(items[i]) && items[i].quality < 50) {
                    if (items[i].sellIn < 11) {
                        items[i].quality = items[i].quality + 1;
                    }

                    if (items[i].sellIn < 6) {
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }

            if (!isSulfuras(items[i])) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (items[i].sellIn < 0) {
                if (!isAgedBrie(items[i])) {
                    if (!isBackstagePasses(items[i])) {
                        if (items[i].quality > 0 && !isSulfuras(items[i])) {
                            items[i].quality = items[i].quality - 1;
                        }
                    } else {
                        items[i].quality = 0;
                    }
                } else if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;
                }
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
