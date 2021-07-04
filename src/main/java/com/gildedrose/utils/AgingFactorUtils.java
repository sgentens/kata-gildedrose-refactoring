package com.gildedrose.utils;

/**
 * @author Steven Gentens
 * @since 0.0.1
 */
public class AgingFactorUtils {

    /**
     * Calculates the quality degradation that should be applied on top of a given quality degradation based on a given factor.
     *
     * @param qualityDegradation degradation that has already been applied
     * @param agingFactor        that should be applied to the degradation
     * @return the remaining quality degradation that should be applied after taking the factor into account
     */
    public static int calculateNewItemQuality(int qualityDegradation, int agingFactor) {
        if (agingFactor == 0) {
            return (qualityDegradation * -1);
        }
        int agingFactorWithoutAppliedDegradation = agingFactor > 0 ? agingFactor - 1 : agingFactor + 1;
        return agingFactorWithoutAppliedDegradation * qualityDegradation;
    }
}
