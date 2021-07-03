package com.gildedrose;

import com.gildedrose.modification.quality.AgedBrieAgingStrategy;
import com.gildedrose.modification.quality.DefaultAgingStrategy;
import com.gildedrose.modification.quality.AgingStrategy;

import java.util.Arrays;
import java.util.List;

class GildedRose {
    Item[] items;
    List<AgingStrategy> qualityModificationStrategies;

    public GildedRose(Item[] items) {
        this.items = items;
        qualityModificationStrategies = Arrays.asList(
            new AgedBrieAgingStrategy(),
            new DefaultAgingStrategy()
        );
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];

            qualityModificationStrategies.stream()
                .filter(strategy -> strategy.isApplicable(item))
                .findFirst()
                .ifPresent(strategy -> strategy.accept(item));

        }
    }
}
