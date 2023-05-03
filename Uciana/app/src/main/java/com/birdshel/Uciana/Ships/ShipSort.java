package com.birdshel.Uciana.Ships;

import android.util.SparseArray;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public enum ShipSort {
    A_TO_Z(2),
    Z_TO_A(3),
    SHIPS_HIGHEST_TO_LOWEST(14),
    SHIPS_LOWEST_TO_HIGHEST(15);
    
    private static final SparseArray<ShipSort> lookup = new SparseArray<>();
    private final int value;

    static {
        ShipSort[] values;
        for (ShipSort shipSort : values()) {
            lookup.put(shipSort.getValue(), shipSort);
        }
    }

    ShipSort(int i) {
        this.value = i;
    }

    public static ShipSort getShipShort(int i) {
        return lookup.get(i);
    }

    public int getValue() {
        return this.value;
    }
}
