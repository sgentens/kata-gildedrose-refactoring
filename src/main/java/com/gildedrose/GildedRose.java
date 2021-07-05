package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (!isSulfuras(item)) {
                item.sellIn = item.sellIn - 1;

                if (!isAgedBrie(item) && !isBackstagePasses(item)) {
                    updateQualityGeneralItem(item);
                } else if (item.quality < 50) {
                    updateQualityAgedBrieOrBackstagePass(item);
                }
            }
        }
    }

    private void updateQualityAgedBrieOrBackstagePass(Item item) {
        updateAgedBrie(item);
        updateBackstagePasses(item);
    }

    private void updateAgedBrie(Item item) {
        if (isAgedBrie(item)) {
            item.quality = item.quality + 1;
            if (isAfterSellDate(item)) {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            }
        }
    }

    private void updateBackstagePasses(Item item) {
        if (isBackstagePasses(item)) {
            item.quality = item.quality + 1;
            if (item.sellIn < 11) {
                item.quality = item.quality + 1;
            }

            if (item.sellIn < 6) {
                item.quality = item.quality + 1;
            }
            if (isAfterSellDate(item)) {
                item.quality = 0;
            }
        }
    }

    private void updateQualityGeneralItem(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
        if (isAfterSellDate(item)) {
            item.quality = item.quality > 0 ? item.quality - 1 : 0;
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
