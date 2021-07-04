package com.gildedrose;

import com.gildedrose.aging.factor.AgingFactor;
import com.gildedrose.aging.factor.ConjuredAgingFactor;
import com.gildedrose.aging.strategy.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class GildedRose {

    Item[] items;

    private List<AgingStrategy> agingStrategies;
    private List<AgingFactor> agingFactors;

    public GildedRose(Item[] items) {
        this.items = items;
        agingStrategies = Arrays.asList(
            new AgedBrieAgingStrategy(),
            new LegendaryAgingStrategy(),
            new BackstagePassesAgingStrategy(),
            new DefaultAgingStrategy()
        );
        agingFactors = Collections.singletonList(
            new ConjuredAgingFactor()
        );
    }

    public void updateQuality() {
        for (Item item : items) {
            int initialQuality = item.quality;

            applyAgingStrategy(item);

            int modifiedQuality = item.quality;
            int qualityDegradation = modifiedQuality - initialQuality;

            applyAgingFactor(item, qualityDegradation);
        }
    }

    /**
     * Applies the first {@link AgingStrategy} available that is applicable to the item.
     */
    private void applyAgingStrategy(Item item) {
        agingStrategies.stream()
            .filter(strategy -> strategy.isApplicable(item))
            .findFirst()
            .ifPresent(strategy -> strategy.accept(item));
    }

    /**
     * Applies the first {@link AgingFactor} available that is applicable to the item.
     */
    private void applyAgingFactor(Item item, int qualityDegradation) {
        agingFactors.stream()
            .filter(factor -> factor.isApplicable(item))
            .findFirst()
            .ifPresent(factor -> factor.accept(item, qualityDegradation));
    }
}
