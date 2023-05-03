package com.birdshel.Uciana.RandomEvents;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public enum RandomEventRarity {
    COMMON(5),
    UNCOMMON(4),
    RARE(3),
    VERY_RARE(2),
    ULTRA_RARE(1);
    
    private int occurrence;

    RandomEventRarity(int i) {
        this.occurrence = i;
    }

    public int getOccurrence() {
        return this.occurrence;
    }
}
