package com.birdshel.Uciana.Events;

import java.util.Map;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Event {
    private final int empireID;
    private final Map<EventDataFields, Object> eventData;
    private final EventType eventType;
    private boolean previewed;

    public Event(int i, EventType eventType, Map<EventDataFields, Object> map) {
        this.empireID = i;
        this.eventType = eventType;
        this.eventData = map;
        this.previewed = false;
    }

    public void beenPreviewed() {
        this.previewed = true;
    }

    public int getEmpireID() {
        return this.empireID;
    }

    public Map<EventDataFields, Object> getEventData() {
        return this.eventData;
    }

    public EventType getEventType() {
        return this.eventType;
    }

    public boolean hasBeenPreviewed() {
        return this.previewed;
    }

    public Object getEventData(EventDataFields eventDataFields) {
        return this.eventData.get(eventDataFields);
    }

    public Event(int i, EventType eventType, Map<EventDataFields, Object> map, boolean z) {
        this.empireID = i;
        this.eventType = eventType;
        this.eventData = map;
        this.previewed = z;
    }
}
