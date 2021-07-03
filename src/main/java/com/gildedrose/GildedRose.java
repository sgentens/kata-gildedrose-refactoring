package com.gildedrose;

import com.gildedrose.aging.factor.AgingFactor;
import com.gildedrose.aging.factor.ConjuredAgingFactor;
import com.gildedrose.aging.strategy.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.gildedrose.utils.ItemUtils.MAX_QUALITY;
import static com.gildedrose.utils.ItemUtils.MIN_QUALITY;

class GildedRose {

    Item[] items;
    List<AgingStrategy> agingStrategies;
    List<AgingFactor> agingFactors;

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

            List<AgingFactor> agingFactorsToApply = getAgingFactorsToApply(item);
            if (!agingFactorsToApply.isEmpty()) {
                applyAgingFactors(agingFactorsToApply, item, qualityDegradation);
            }

        }
    }

    private void applyAgingStrategy(Item item) {
        agingStrategies.stream()
            .filter(strategy -> strategy.isApplicable(item))
            .findFirst()
            .ifPresent(strategy -> strategy.accept(item));
    }

    private List<AgingFactor> getAgingFactorsToApply(Item item) {
        return agingFactors.stream()
            .filter(factor -> factor.isApplicable(item))
            .collect(Collectors.toList());
    }

    /**
     * Applies aging factors to the item if necessary.
     * The aging factor multiplies the quality degradation that is applied to the item.
     * If multiple aging factors should be applied, the sum of the factors is applied.
     *
     * @param agingFactorsToApply factors that should be applied on top of the existing degradation
     * @param item                to apply aging factors on
     * @param qualityDegradation  that has already been applied
     */
    private void applyAgingFactors(List<AgingFactor> agingFactorsToApply, Item item, int qualityDegradation) {
        int agingFactor = agingFactorsToApply.stream()
            .mapToInt(AgingFactor::getAgingFactor)
            .sum();

        if (qualityDegradation > 0) {
            item.quality = Math.min(MAX_QUALITY, multiplyQualityWithAgingFactor(item.quality, qualityDegradation, agingFactor));
        } else {
            item.quality = Math.max(MIN_QUALITY, multiplyQualityWithAgingFactor(item.quality, qualityDegradation, agingFactor));
        }
    }

    /**
     * Calculates the quality degradation that should be additionally applied to the item.
     *
     * @param itemQuality        current quality of the item
     * @param qualityDegradation degradation that has already been applied
     * @param agingFactor        that should be applied
     * @return the remaining quality degradation that should be applied on the item
     */
    protected static int multiplyQualityWithAgingFactor(int itemQuality, int qualityDegradation, int agingFactor) {
        if (agingFactor == 0) {
            return itemQuality + (qualityDegradation * -1);
        }

        int agingFactorWithoutAppliedDegradation = agingFactor > 0 ? agingFactor - 1 : agingFactor + 1;

        return itemQuality + (agingFactorWithoutAppliedDegradation * qualityDegradation);
    }
}
