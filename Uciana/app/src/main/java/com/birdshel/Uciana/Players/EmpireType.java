package com.birdshel.Uciana.Players;

import android.util.SparseArray;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public enum EmpireType {
    HUMAN(0),
    AI(1),
    NONE(2);
    
    private static final SparseArray<EmpireType> lookup = new SparseArray<>();
    private final int value;

    static {
        EmpireType[] values;
        for (EmpireType empireType : values()) {
            lookup.put(empireType.getValue(), empireType);
        }
    }

    EmpireType(int i) {
        this.value = i;
    }

    public static EmpireType getEmpireType(int i) {
        return lookup.get(i);
    }

    public int getValue() {
        return this.value;
    }
}
