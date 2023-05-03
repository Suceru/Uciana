package com.birdshel.Uciana.Players;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public enum EmpireEventKeys {
    ASCENDED_ENCOUNTER("ascended_encounter");
    
    private final String name;

    EmpireEventKeys(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }
}
