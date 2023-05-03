package com.birdshel.Uciana.StarSystems;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public enum NebulaType {
    GREEN,
    YELLOW,
    WHITE,
    ORANGE,
    RED,
    DARK_GREEN;

    public static NebulaType getRandom() {
        NebulaType[] values = values();
        double random = Math.random();
        double length = values().length;
        Double.isNaN(length);
        return values[(int) (random * length)];
    }
}
