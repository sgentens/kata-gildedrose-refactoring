package com.gildedrose.proxy;

import com.gildedrose.Item;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author Steven Gentens
 * @since 0.0.1
 */
public enum ItemType {

    SULFURAS(TypeCheck::isSulfuras, SulfurasItemProxy::new),
    BACKSTAGE_PASS(TypeCheck::isBackstagePasses, BackstagePassesItemProxy::new),
    AGED_BRIE(TypeCheck::isAgedBrie, AgedBrieItemProxy::new),
    GENERAL((item) -> true, GeneralItemProxy::new);

    private final Function<Item, Boolean> typeCheck;
    private final Function<Item, ItemProxy> mapper;


    ItemType(Function<Item, Boolean> typeCheck, Function<Item, ItemProxy> mapper) {
        this.typeCheck = typeCheck;
        this.mapper = mapper;
    }

    boolean isItemType(Item item) {
        return typeCheck.apply(item);
    }

    Optional<ItemProxy> mapToItemProxy(Item item) {
        if (isItemType(item)) {
            return Optional.of(mapper.apply(item));
        }
        return Optional.empty();
    }

    public static ItemProxy convertToItemProxy(Item item) {
        return Arrays.stream(values())
            .map(it -> it.mapToItemProxy(item))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .findFirst()
            .orElseThrow(() -> new ItemNotConvertibleToItemProxyException(item));
    }

    static class TypeCheck {
        static boolean isAgedBrie(Item item) {
            return StringUtils.containsIgnoreCase(getTrimmedName(item), "aged brie");
        }

        static boolean isBackstagePasses(Item item) {
            return StringUtils.containsIgnoreCase(getTrimmedName(item), "backstage pass");
        }

        static boolean isSulfuras(Item item) {
            return StringUtils.containsIgnoreCase(getTrimmedName(item), "sulfuras");
        }

        static String getTrimmedName(Item item) {
            return StringUtils.trim(item.name);
        }
    }
}
