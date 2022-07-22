package com.geek.base.concurrent.Immutable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author G.E.E.K.
 * @create 2022-07-01 16:24
 */
public class ImmutableExample {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> unmodifiableMap = Collections.unmodifiableMap(map);
        unmodifiableMap.put("a", 1);
    }
}

