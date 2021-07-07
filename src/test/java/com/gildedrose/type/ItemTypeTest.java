package com.gildedrose.type;

import com.gildedrose.Item;
import com.gildedrose.proxy.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Steven Gentens
 * @since 0.0.1
 */
public class ItemTypeTest {

    @ParameterizedTest
    @ValueSource(strings = {
        "sulfuras",
        "Sulfuras",
        "Sulfuras, Hand of Ragnaros",
        " SulFuRaS",
        "\tSulFuRaS",
    })
    void convertsSulfurasToSulfurasItemProxy(String itemName) {
        createItemProxyAndValidateItemProxyType(itemName, SulfurasItemProxy.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "aged brie",
        "Aged Brie (1990)",
        "Aged Brie",
        " AgeD BrIE",
        "\taged brie",
    })
    void convertsAgedBrieToAgedBrieItemProxy(String itemName) {
        createItemProxyAndValidateItemProxyType(itemName, AgedBrieItemProxy.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "backstage pass",
        "Backstage passes to a TAFKAL80ETC concert",
        "Backstage Pass",
        " BacKStAGe PaSSeS",
        "\tbackstage pass",
    })
    void convertsBackstagePassesToBackstagePassesItemProxy(String itemName) {
        createItemProxyAndValidateItemProxyType(itemName, BackstagePassesItemProxy.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "+5 Dexterity Vest",
        "some item",
        "Elixir of the Mongoose",
        "\tConjured Mana Cake",
    })
    void convertsItemToGeneralItemProxy(String itemName) {
        createItemProxyAndValidateItemProxyType(itemName, GeneralItemProxy.class);
    }

    private void createItemProxyAndValidateItemProxyType(String itemName, Class<? extends ItemProxy> toMatch) {
        ItemProxy itemProxy = createItemProxy(itemName);
        assertTrue(toMatch.isInstance(itemProxy));
    }

    private ItemProxy createItemProxy(String itemName) {
        Item item = new Item(itemName, 5, 5);
        return ItemType.convertToItemProxy(item);
    }

}
