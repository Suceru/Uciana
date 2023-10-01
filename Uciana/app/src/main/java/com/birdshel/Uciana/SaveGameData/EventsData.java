package com.birdshel.Uciana.SaveGameData;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.birdshel.Uciana.Colonies.Buildings.BuildingID;
import com.birdshel.Uciana.Events.Event;
import com.birdshel.Uciana.Events.EventDataFields;
import com.birdshel.Uciana.Events.EventType;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Messages.DiplomaticType;
import com.birdshel.Uciana.Players.Treaty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
class EventsData {
    private static final String EVENTS = "events";
    private static final String EVENT_DATA = "event_data";
    private SQLiteDatabase db;
    private final Game game;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.SaveGameData.EventsData$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1420a;
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[EventDataFields.values().length];
            b = iArr;
            try {
                iArr[EventDataFields.SYSTEM_ID.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[EventDataFields.ORBIT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[EventDataFields.EMPIRE_ID.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[EventDataFields.TECH_ID.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[EventDataFields.TECH_FROM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                b[EventDataFields.DIPLOMATIC_TYPE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                b[EventDataFields.BUILDING_ID.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                b[EventDataFields.WHY.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                b[EventDataFields.TREATY.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            int[] iArr2 = new int[EventType.values().length];
            f1420a = iArr2;
            try {
                iArr2[EventType.EMPIRE_CONTACT.ordinal()] = 1;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f1420a[EventType.EMPIRE_DESTROYED.ordinal()] = 2;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f1420a[EventType.SYSTEM_DISCOVERY.ordinal()] = 3;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f1420a[EventType.SELECT_ATTACK.ordinal()] = 4;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f1420a[EventType.TERRAFORMING.ordinal()] = 5;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f1420a[EventType.COLONY_DESTROYED.ordinal()] = 6;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f1420a[EventType.OUTPOST_DESTROYED.ordinal()] = 7;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f1420a[EventType.CAPITAL_DESTROYED.ordinal()] = 8;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f1420a[EventType.COLONY_INVADED.ordinal()] = 9;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                f1420a[EventType.TECH_BREAKTHROUGH.ordinal()] = 10;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                f1420a[EventType.DIPLOMATIC.ordinal()] = 11;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                f1420a[EventType.BUILDING_SCRAP.ordinal()] = 12;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                f1420a[EventType.AI_PROPOSE_TREATIES.ordinal()] = 13;
            } catch (NoSuchFieldError unused22) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EventsData(Game game) {
        this.game = game;
    }

    private Map<EventDataFields, Object> getEventData(int i) {
        HashMap hashMap = new HashMap();
        Cursor query = this.db.query(EVENT_DATA, new String[]{"key", "value"}, "eventID = ?", new String[]{Integer.toString(i)}, null, null, null);
        int columnIndex = query.getColumnIndex("key");
        int columnIndex2 = query.getColumnIndex("value");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            EventDataFields eventDataField = EventDataFields.getEventDataField(query.getInt(columnIndex));
            Integer num = null;
            switch (AnonymousClass1.b[eventDataField.ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                    num = Integer.valueOf(Integer.parseInt(query.getString(columnIndex2)));
                    break;
            }
            hashMap.put(eventDataField, num);
            query.moveToNext();
        }
        query.close();
        return hashMap;
    }

    private void loadEvents() {
        Cursor query = this.db.query(EVENTS, new String[]{"eventID", "empireID", "previewed", "eventType"}, null, null, null, null, null);
        int columnIndex = query.getColumnIndex("eventID");
        int columnIndex2 = query.getColumnIndex("empireID");
        int columnIndex3 = query.getColumnIndex("previewed");
        int columnIndex4 = query.getColumnIndex("eventType");
        ArrayList arrayList = new ArrayList();
        query.moveToFirst();
        while (!query.isAfterLast()) {
            int i = query.getInt(columnIndex);
            int i2 = query.getInt(columnIndex2);
            boolean z = query.getInt(columnIndex3) != 0;
            EventType eventType = EventType.getEventType(query.getInt(columnIndex4));
            Map<EventDataFields, Object> eventData = getEventData(i);
            switch (AnonymousClass1.f1420a[eventType.ordinal()]) {
                case 1:
                    this.game.events.addEmpireEvent(eventType, i2, ((Integer) eventData.get(EventDataFields.EMPIRE_ID)).intValue(), z);
                    break;
                case 2:
                    int intValue = ((Integer) eventData.get(EventDataFields.EMPIRE_ID)).intValue();
                    if (!arrayList.contains(Integer.valueOf(intValue))) {
                        arrayList.add(Integer.valueOf(intValue));
                        this.game.events.addEmpireDestroyedEvent(intValue, ((Integer) eventData.get(EventDataFields.WHY)).intValue());
                        break;
                    } else {
                        break;
                    }
                case 3:
                case 4:
                    this.game.events.addSystemEvent(eventType, i2, ((Integer) eventData.get(EventDataFields.SYSTEM_ID)).intValue(), z);
                    break;
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                    this.game.events.addPlanetEvent(eventType, i2, ((Integer) eventData.get(EventDataFields.SYSTEM_ID)).intValue(), ((Integer) eventData.get(EventDataFields.ORBIT)).intValue());
                    break;
                case 10:
                    this.game.events.addTechEvent(i2, ((Integer) eventData.get(EventDataFields.TECH_ID)).intValue(), ((Integer) eventData.get(EventDataFields.TECH_FROM)).intValue());
                    break;
                case 11:
                    int intValue2 = ((Integer) eventData.get(EventDataFields.DIPLOMATIC_TYPE)).intValue();
                    int intValue3 = ((Integer) eventData.get(EventDataFields.EMPIRE_ID)).intValue();
                    if (intValue2 == DiplomaticType.BREAK_TREATY.ordinal()) {
                        this.game.events.addDiplomaticBreakTreatyEvent(intValue2, i2, intValue3, Treaty.values()[((Integer) eventData.get(EventDataFields.TREATY)).intValue()]);
                        break;
                    } else {
                        this.game.events.addDiplomaticEvent(intValue2, i2, intValue3);
                        break;
                    }
                case 12:
                    this.game.events.addBuildingScrapEvent(i2, ((Integer) eventData.get(EventDataFields.SYSTEM_ID)).intValue(), ((Integer) eventData.get(EventDataFields.ORBIT)).intValue(), BuildingID.values()[((Integer) eventData.get(EventDataFields.BUILDING_ID)).intValue()]);
                    break;
                case 13:
                    this.game.events.addAIProposeTreatyEvent(i2, ((Integer) eventData.get(EventDataFields.EMPIRE_ID)).intValue(), ((Integer) eventData.get(EventDataFields.TREATY)).intValue());
                    break;
            }
            query.moveToNext();
        }
        query.close();
    }

    private void saveEvents() {
        this.db.beginTransaction();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Event event : this.game.events.getEvents()) {
            if (event.getEventType() != EventType.EXPLORATION) {
                if (event.getEventType() == EventType.EMPIRE_DESTROYED) {
                    if (!arrayList.contains(Integer.valueOf(event.getEmpireID()))) {
                        arrayList.add(Integer.valueOf(event.getEmpireID()));
                    }
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put("eventID", Integer.valueOf(i));
                contentValues.put("empireID", Integer.valueOf(event.getEmpireID()));
                contentValues.put("previewed", Integer.valueOf(event.hasBeenPreviewed() ? 1 : 0));
                contentValues.put("eventType", Integer.valueOf(event.getEventType().ordinal()));
                this.db.insert(EVENTS, null, contentValues);
                for (Map.Entry<EventDataFields, Object> entry : event.getEventData().entrySet()) {
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.put("eventID", Integer.valueOf(i));
                    contentValues2.put("key", Integer.valueOf(entry.getKey().ordinal()));
                    contentValues2.put("value", entry.getValue().toString());
                    this.db.insert(EVENT_DATA, null, contentValues2);
                }
                i++;
            }
        }
        this.db.setTransactionSuccessful();
        this.db.endTransaction();
    }

    public void load(SQLiteDatabase sQLiteDatabase) {
        this.db = sQLiteDatabase;
        this.game.events.clear();
        loadEvents();
    }

    public void save(SQLiteDatabase sQLiteDatabase) {
        this.db = sQLiteDatabase;
        saveEvents();
    }
}
