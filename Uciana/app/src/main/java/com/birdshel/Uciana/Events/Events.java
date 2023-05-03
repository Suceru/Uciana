package com.birdshel.Uciana.Events;

import com.birdshel.Uciana.Colonies.Buildings.BuildingID;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Messages.DiplomaticType;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.Players.EmpireType;
import com.birdshel.Uciana.Players.Treaty;
import com.birdshel.Uciana.RandomEvents.RandomEventData;
import com.birdshel.Uciana.Technology.TechID;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Events {
    private final List<Event> events = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Events.Events$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a */
        static final /* synthetic */ int[] f1366a;

        static {
            int[] iArr = new int[EventType.values().length];
            f1366a = iArr;
            try {
                iArr[EventType.SYSTEM_DISCOVERY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1366a[EventType.TECH_BREAKTHROUGH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1366a[EventType.EMPIRE_DESTROYED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1366a[EventType.DIPLOMATIC.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1366a[EventType.EXPLORATION.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1366a[EventType.EMPIRE_CONTACT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1366a[EventType.COLONY_STARVED_OFF.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1366a[EventType.ASCENDED_ENCOUNTER.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1366a[EventType.AI_PROPOSE_TREATIES.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f1366a[EventType.RANDOM_EVENT.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    private void addEvent(Event event) {
        if (event.getEventType() == EventType.SELECT_ATTACK || !GameData.empires.get(event.getEmpireID()).isType(EmpireType.AI)) {
            this.events.add(event);
        }
    }

    public void addAIProposeTreatyEvent(int i, int i2, int i3) {
        if (GameData.empires.get(i).isAIProposalsHidden(i2)) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(EventDataFields.EMPIRE_ID, Integer.valueOf(i2));
        hashMap.put(EventDataFields.TREATY, Integer.valueOf(i3));
        addEvent(new Event(i, EventType.AI_PROPOSE_TREATIES, hashMap));
    }

    public void addAscendedEvent(int i, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put(EventDataFields.SYSTEM_ID, Integer.valueOf(i2));
        addEvent(new Event(i, EventType.ASCENDED_ENCOUNTER, hashMap));
    }

    public void addBuildingScrapEvent(int i, int i2, int i3, BuildingID buildingID) {
        HashMap hashMap = new HashMap();
        hashMap.put(EventDataFields.SYSTEM_ID, Integer.valueOf(i2));
        hashMap.put(EventDataFields.ORBIT, Integer.valueOf(i3));
        hashMap.put(EventDataFields.BUILDING_ID, Integer.valueOf(buildingID.ordinal()));
        addEvent(new Event(i, EventType.BUILDING_SCRAP, hashMap));
    }

    public void addDiplomaticBreakTreatyEvent(int i, int i2, int i3, Treaty treaty) {
        HashMap hashMap = new HashMap();
        hashMap.put(EventDataFields.DIPLOMATIC_TYPE, Integer.valueOf(i));
        hashMap.put(EventDataFields.EMPIRE_ID, Integer.valueOf(i3));
        hashMap.put(EventDataFields.TREATY, Integer.valueOf(treaty.ordinal()));
        addEvent(new Event(i2, EventType.DIPLOMATIC, hashMap));
    }

    public void addDiplomaticEvent(int i, int i2, int i3) {
        HashMap hashMap = new HashMap();
        hashMap.put(EventDataFields.DIPLOMATIC_TYPE, Integer.valueOf(i));
        hashMap.put(EventDataFields.EMPIRE_ID, Integer.valueOf(i3));
        addEvent(new Event(i2, EventType.DIPLOMATIC, hashMap));
    }

    public void addEmpireDestroyedEvent(int i, int i2) {
        for (Empire empire : GameData.empires.getEmpires()) {
            if (!empire.isAI()) {
                if (empire.getEmpireSetting("seen_destroyed_message_" + i) == 0) {
                    HashMap hashMap = new HashMap();
                    hashMap.put(EventDataFields.EMPIRE_ID, Integer.valueOf(i));
                    hashMap.put(EventDataFields.WHY, Integer.valueOf(i2));
                    addEvent(new Event(empire.id, EventType.EMPIRE_DESTROYED, hashMap, false));
                }
            }
        }
    }

    public void addEmpireEvent(EventType eventType, int i, int i2, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(EventDataFields.EMPIRE_ID, Integer.valueOf(i2));
        addEvent(new Event(i, eventType, hashMap, z));
    }

    public void addExplorationEvent(int i, int i2, int i3, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(EventDataFields.SYSTEM_ID, Integer.valueOf(i2));
        hashMap.put(EventDataFields.ORBIT, Integer.valueOf(i3));
        hashMap.put(EventDataFields.UNEXPLORED, Boolean.valueOf(z));
        addEvent(new Event(i, EventType.EXPLORATION, hashMap));
    }

    public void addPlanetEvent(EventType eventType, int i, int i2, int i3) {
        HashMap hashMap = new HashMap();
        hashMap.put(EventDataFields.SYSTEM_ID, Integer.valueOf(i2));
        hashMap.put(EventDataFields.ORBIT, Integer.valueOf(i3));
        addEvent(new Event(i, eventType, hashMap));
    }

    public void addRandomEvent(RandomEventData randomEventData, int i) {
        for (Empire empire : GameData.empires.getEmpires()) {
            if (empire.isHuman()) {
                HashMap hashMap = new HashMap();
                hashMap.put(EventDataFields.RANDOM_EVENT_TYPE, Integer.valueOf(randomEventData.getType().ordinal()));
                hashMap.put(EventDataFields.RANDOM_EVENT_DATA1, Integer.valueOf(randomEventData.getData1()));
                hashMap.put(EventDataFields.RANDOM_EVENT_DATA2, Integer.valueOf(randomEventData.getData2()));
                hashMap.put(EventDataFields.RANDOM_EVENT_DATA3, Integer.valueOf(randomEventData.getData3()));
                hashMap.put(EventDataFields.RANDOM_EVENT_MESSAGE_TYPE, Integer.valueOf(i));
                addEvent(new Event(empire.id, EventType.RANDOM_EVENT, hashMap));
            }
        }
    }

    public void addSelectAttackEvent(int i, int i2) {
        List<Event> events = getEvents(i, EventType.SELECT_ATTACK);
        if (i != 8) {
            for (Event event : events) {
                if (((Integer) event.getEventData().get(EventDataFields.SYSTEM_ID)).intValue() == i2) {
                    return;
                }
            }
        }
        HashMap hashMap = new HashMap();
        hashMap.put(EventDataFields.SYSTEM_ID, Integer.valueOf(i2));
        this.events.add(new Event(i, EventType.SELECT_ATTACK, hashMap));
    }

    public void addSystemEvent(EventType eventType, int i, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put(EventDataFields.SYSTEM_ID, Integer.valueOf(i2));
        addEvent(new Event(i, eventType, hashMap));
    }

    public void addTechEvent(int i, int i2, int i3) {
        if (i2 == TechID.NONE.ordinal()) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(EventDataFields.TECH_ID, Integer.valueOf(i2));
        hashMap.put(EventDataFields.TECH_FROM, Integer.valueOf(i3));
        addEvent(new Event(i, EventType.TECH_BREAKTHROUGH, hashMap));
    }

    public void clear() {
        this.events.clear();
    }

    public void clearEventsForEmpire(int i) {
        for (Event event : getEvents(i)) {
            if (event.getEventType() != EventType.SELECT_ATTACK) {
                this.events.remove(event);
            }
        }
    }

    public int getCount(int i) {
        int i2 = 0;
        for (Event event : this.events) {
            if (event.getEmpireID() == i && event.getEventType() != EventType.SELECT_ATTACK && event.getEventType() != EventType.TECH_BREAKTHROUGH && event.getEventType() != EventType.DIPLOMATIC && event.getEventType() != EventType.RANDOM_EVENT) {
                i2++;
            }
        }
        return i2;
    }

    public List<Event> getEvents() {
        return this.events;
    }

    public List<Event> getEventsForEventsScene(int i) {
        ArrayList arrayList = new ArrayList();
        for (Event event : this.events) {
            if (event.getEmpireID() == i && event.getEventType() != EventType.SELECT_ATTACK) {
                arrayList.add(event);
            }
        }
        return arrayList;
    }

    public List<Event> getEventsForPlanet(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        for (Event event : this.events) {
            if (event.getEmpireID() == i && event.getEventType() == EventType.TERRAFORMING && ((Integer) event.getEventData(EventDataFields.SYSTEM_ID)).intValue() == i2 && ((Integer) event.getEventData(EventDataFields.ORBIT)).intValue() == i3) {
                arrayList.add(event);
            }
        }
        return arrayList;
    }

    public List<Event> getEventsForSystem(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        for (Event event : this.events) {
            if (event.getEmpireID() == i && event.getEventType() == EventType.SYSTEM_DISCOVERY && ((Integer) event.getEventData(EventDataFields.SYSTEM_ID)).intValue() == i2) {
                arrayList.add(event);
            }
        }
        return arrayList;
    }

    public List<Event> getStartOfTurnEvents(int i) {
        ArrayList arrayList = new ArrayList();
        for (Event event : this.events) {
            if (event.getEmpireID() == i && !event.hasBeenPreviewed()) {
                switch (AnonymousClass1.f1366a[event.getEventType().ordinal()]) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                        arrayList.add(event);
                        continue;
                }
            }
        }
        return arrayList;
    }

    public boolean hasSelectAttackEvent(int i, int i2) {
        for (Event event : this.events) {
            if (event.getEmpireID() == i && event.getEventType() == EventType.SELECT_ATTACK && ((Integer) event.getEventData(EventDataFields.SYSTEM_ID)).intValue() == i2) {
                return true;
            }
        }
        return false;
    }

    public void removeEvent(Event event) {
        this.events.remove(event);
    }

    public void removePeacefulDiplomaticEvents(int i, int i2) {
        DiplomaticType diplomaticType;
        ArrayList<Event> arrayList = new ArrayList();
        for (Event event : this.events) {
            if (event.getEmpireID() == i) {
                if (event.getEventType() == EventType.AI_PROPOSE_TREATIES && ((Integer) event.getEventData(EventDataFields.EMPIRE_ID)).intValue() == i2) {
                    arrayList.add(event);
                }
                if (event.getEventType() == EventType.DIPLOMATIC && (diplomaticType = DiplomaticType.values()[((Integer) event.getEventData(EventDataFields.DIPLOMATIC_TYPE)).intValue()]) != DiplomaticType.DECLARED_WAR && diplomaticType != DiplomaticType.RESPONSE_TO_WAR && ((Integer) event.getEventData(EventDataFields.EMPIRE_ID)).intValue() == i2) {
                    arrayList.add(event);
                }
            }
        }
        for (Event event2 : arrayList) {
            this.events.remove(event2);
        }
    }

    public List<Event> getEvents(int i) {
        ArrayList arrayList = new ArrayList();
        for (Event event : this.events) {
            if (event.getEmpireID() == i) {
                arrayList.add(event);
            }
        }
        return arrayList;
    }

    public void addSystemEvent(EventType eventType, int i, int i2, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(EventDataFields.SYSTEM_ID, Integer.valueOf(i2));
        addEvent(new Event(i, eventType, hashMap, z));
    }

    public List<Event> getEvents(int i, EventType eventType) {
        ArrayList arrayList = new ArrayList();
        for (Event event : this.events) {
            if (event.getEmpireID() == i && event.getEventType() == eventType) {
                arrayList.add(event);
            }
        }
        return arrayList;
    }

    public List<Event> getEvents(EventType eventType) {
        ArrayList arrayList = new ArrayList();
        for (Event event : this.events) {
            if (event.getEventType() == eventType) {
                arrayList.add(event);
            }
        }
        return arrayList;
    }
}
