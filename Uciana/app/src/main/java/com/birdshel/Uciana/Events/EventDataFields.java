package com.birdshel.Uciana.Events;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public enum EventDataFields {
    SYSTEM_ID,
    TECH_ID,
    EMPIRE_ID,
    ORBIT,
    DIPLOMATIC_TYPE,
    BUILDING_ID,
    TECH_FROM,
    UNEXPLORED,
    WHY,
    TREATY,
    RANDOM_EVENT_TYPE,
    RANDOM_EVENT_DATA1,
    RANDOM_EVENT_DATA2,
    RANDOM_EVENT_DATA3,
    RANDOM_EVENT_MESSAGE_TYPE;

    public static EventDataFields getEventDataField(int i) {
        return values()[i];
    }
}
