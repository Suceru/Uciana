package com.birdshel.Uciana.Planets;

import com.birdshel.Uciana.Math.Functions;

import java.util.HashMap;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public enum SystemObjectType {
    PLANET,
    ASTEROID_BELT,
    GAS_GIANT,
    NONE;

    public static SystemObjectType getSystemObjectType() {
        HashMap hashMap = new HashMap();
        hashMap.put(PLANET, 68);
        hashMap.put(GAS_GIANT, 16);
        hashMap.put(ASTEROID_BELT, 16);
        return (SystemObjectType) Functions.getItemByPercent(hashMap);
    }
}
