package com.birdshel.Uciana.SaveGameData;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.GameSettingsEnum;
import com.birdshel.Uciana.RandomEvents.RandomEventData;
import com.birdshel.Uciana.RandomEvents.RandomEventType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class GameSettingsData {
    private static final String RANDOM_EVENTS = "random_events";
    private SQLiteDatabase db;

    private void loadGameSettingsData() {
        GameData.gameSettings.setSettings(new HashMap());
        Cursor query = this.db.query("game_settings", new String[]{"id", "value"}, null, null, null, null, null);
        int columnIndex = query.getColumnIndex("id");
        int columnIndex2 = query.getColumnIndex("value");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            GameData.gameSettings.setSetting(GameSettingsEnum.values()[query.getInt(columnIndex)], Integer.valueOf(query.getInt(columnIndex2)));
            query.moveToNext();
        }
        query.close();
    }

    private void loadRandomEventData() {
        Cursor query = this.db.query(RANDOM_EVENTS, new String[]{"type", "data1", "data2", "data3"}, null, null, null, null, null);
        int columnIndex = query.getColumnIndex("type");
        int columnIndex2 = query.getColumnIndex("data1");
        int columnIndex3 = query.getColumnIndex("data2");
        int columnIndex4 = query.getColumnIndex("data3");
        ArrayList arrayList = new ArrayList();
        query.moveToFirst();
        while (!query.isAfterLast()) {
            arrayList.add(new RandomEventData(RandomEventType.values()[query.getInt(columnIndex)], query.getInt(columnIndex2), query.getInt(columnIndex3), query.getInt(columnIndex4)));
            query.moveToNext();
        }
        query.close();
        GameData.randomEvents.setHistory(arrayList);
    }

    private void saveGameSettingsData() {
        GameSettingsEnum[] values;
        Map<GameSettingsEnum, Integer> settings = GameData.gameSettings.getSettings();
        this.db.beginTransaction();
        for (GameSettingsEnum gameSettingsEnum : GameSettingsEnum.values()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", Integer.valueOf(gameSettingsEnum.ordinal()));
            contentValues.put("value", settings.get(gameSettingsEnum));
            this.db.insert("game_settings", null, contentValues);
        }
        this.db.setTransactionSuccessful();
        this.db.endTransaction();
    }

    private void saveRandomEventData() {
        this.db.beginTransaction();
        for (RandomEventData randomEventData : GameData.randomEvents.getHistory()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("type", Integer.valueOf(randomEventData.getType().ordinal()));
            contentValues.put("data1", Integer.valueOf(randomEventData.getData1()));
            contentValues.put("data2", Integer.valueOf(randomEventData.getData2()));
            contentValues.put("data3", Integer.valueOf(randomEventData.getData3()));
            this.db.insert(RANDOM_EVENTS, null, contentValues);
        }
        this.db.setTransactionSuccessful();
        this.db.endTransaction();
    }

    public void load(SQLiteDatabase sQLiteDatabase) {
        this.db = sQLiteDatabase;
        loadGameSettingsData();
        loadRandomEventData();
    }

    public void save(SQLiteDatabase sQLiteDatabase) {
        this.db = sQLiteDatabase;
        saveGameSettingsData();
        saveRandomEventData();
    }
}
