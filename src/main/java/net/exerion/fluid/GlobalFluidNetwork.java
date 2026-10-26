/*
 * Decompiled with CFR 0.152.
 */
package net.exerion.fluid;

import java.util.HashMap;
import java.util.Map;

public class GlobalFluidNetwork {
    private static final Map<String, Integer> FLUID_NETWORK = new HashMap<String, Integer>();

    public static int injectLava(String frequency, int amount) {
        if (amount <= 0) {
            return 0;
        }
        int capacity = 100000;
        int current = FLUID_NETWORK.getOrDefault(frequency, 0);
        int toInject = Math.min(amount, capacity - current);
        if (toInject > 0) {
            FLUID_NETWORK.put(frequency, current + toInject);
        }
        return toInject;
    }

    public static int extractLava(String frequency, int maxExtract) {
        if (maxExtract <= 0) {
            return 0;
        }
        int current = FLUID_NETWORK.getOrDefault(frequency, 0);
        int toExtract = Math.min(maxExtract, current);
        if (toExtract > 0) {
            FLUID_NETWORK.put(frequency, current - toExtract);
        }
        return toExtract;
    }

    public static int getLavaAmount(String frequency) {
        return FLUID_NETWORK.getOrDefault(frequency, 0);
    }
}

