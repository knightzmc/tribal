package me.bristermitten.tribal.util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class RandomUtils {
    private static final Random random = new Random();

    @SafeVarargs
    public static <T extends Enum<T>> T randomEnum(Class<? extends T> enm, T... exclude) {
        return randomEnum(enm, () -> null, exclude);
    }

    @SafeVarargs
    public static <T extends Enum<T>> T randomEnum(Class<? extends T> enm, Supplier<T> ifFull, T... exclude) {
        T[] tEnum = enm.getEnumConstants();
        if (tEnum.length == exclude.length) return ifFull.get();
        List<T> excluded = Arrays.asList(exclude);
        T random;
        while (excluded.contains(random = randomEnum(enm))) {
        }
        return random;
    }

    private static <T extends Enum<T>> T randomEnum(Class<? extends T> enm) {
        T[] tEnum = enm.getEnumConstants();
        return tEnum[random.nextInt(tEnum.length)];
    }
}
