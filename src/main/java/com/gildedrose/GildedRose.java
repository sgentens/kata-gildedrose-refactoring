package com.gildedrose;

import com.gildedrose.modification.quality.DefaultQualityModificationStrategy;
import com.gildedrose.modification.quality.QualityModificationStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class GildedRose {
    Item[] items;
    List<QualityModificationStrategy> qualityModificationStrategies = new ArrayList<>();

    public GildedRose(Item[] items) {
        this.items = items;
        qualityModificationStrategies = Arrays.asList(
            new DefaultQualityModificationStrategy()
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
