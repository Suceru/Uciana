package com.birdshel.Uciana.Colonies;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public enum ManufacturingType {
    BUILDINGS(0),
    SHIP(1),
    NONE(2);
    
    private final int value;

    ManufacturingType(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
