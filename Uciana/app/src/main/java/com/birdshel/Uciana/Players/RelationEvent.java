package com.birdshel.Uciana.Players;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public enum RelationEvent {
    WAR(-100),
    PEACE(50),
    SMALL_GIFT(10),
    MED_GIFT(25),
    LARGE_GIFT(20),
    BREAK_TREATY(-20),
    BREAK_TREATY_OTHERS(-10),
    BREAK_TREATY_ALLIANCE(-30),
    BREAK_TREATY_ALLIANCE_OTHERS(-15),
    BREAK_TREATY_NON_AGGRESSION(-20),
    BREAK_TREATY_NON_AGGRESSION_OTHERS(-10),
    SURPRISE_ATTACK_ALLY(-40),
    SURPRISE_ATTACK_WITH_NON_AGGRESSION(-25),
    SURPRISE_ATTACK(-10),
    NEW_TREATY(20);
    

    /* renamed from: a  reason: collision with root package name */
    int f1406a;

    RelationEvent(int i) {
        this.f1406a = i;
    }

    public int getValue() {
        return this.f1406a;
    }
}
