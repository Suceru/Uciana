package com.birdshel.Uciana.Events;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public enum EventType {
    SYSTEM_DISCOVERY,
    TECH_BREAKTHROUGH,
    EMPIRE_CONTACT,
    TERRAFORMING,
    EMPIRE_DESTROYED,
    COLONY_DESTROYED,
    CAPITAL_DESTROYED,
    SELECT_ATTACK,
    OUTPOST_DESTROYED,
    COLONY_INVADED,
    DIPLOMATIC,
    BUILDING_SCRAP,
    EXPLORATION,
    COLONY_STARVED_OFF,
    ASCENDED_ENCOUNTER,
    AI_PROPOSE_TREATIES,
    RANDOM_EVENT;

    public static EventType getEventType(int i) {
        return values()[i];
    }
}
