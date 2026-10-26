package net.exerion.fluid;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GlobalFluidNetwork {
    public static final int CAPACITY = 100_000;
    private static final Map<String, Integer> FLUID_NETWORK = new ConcurrentHashMap<>();

    public static synchronized int injectLava(String frequency, int amount) {
        if (amount <= 0) {
            return 0;
        }
        int current = FLUID_NETWORK.getOrDefault(frequency, 0);
        int toInject = Math.min(amount, CAPACITY - current);
        if (toInject > 0) {
            FLUID_NETWORK.put(frequency, current + toInject);
        }
        return toInject;
    }

    public static synchronized int extractLava(String frequency, int maxExtract) {
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

    public static void clear() {
        FLUID_NETWORK.clear();
    }
}

