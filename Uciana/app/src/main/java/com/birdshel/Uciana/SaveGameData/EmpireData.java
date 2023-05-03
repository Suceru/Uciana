package com.birdshel.Uciana.SaveGameData;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import com.birdshel.Uciana.AI.Personality;
import com.birdshel.Uciana.Colonies.Buildings.BuildingID;
import com.birdshel.Uciana.Colonies.SortType;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Players.BuildLists;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.Players.EmpireType;
import com.birdshel.Uciana.Players.Migrants;
import com.birdshel.Uciana.Players.PlayerSettings;
import com.birdshel.Uciana.Players.PlayerSettingsEnum;
import com.birdshel.Uciana.Players.RaceAttribute;
import com.birdshel.Uciana.Players.Treaties;
import com.birdshel.Uciana.Players.Treaty;
import com.birdshel.Uciana.Ships.Ship;
import com.birdshel.Uciana.Ships.ShipType;
import com.birdshel.Uciana.Technology.Tech;
import com.birdshel.Uciana.Technology.TechID;
import com.birdshel.Uciana.Technology.TechType;
import com.birdshel.Uciana.Technology.Technology;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.andengine.util.level.constants.LevelConstants;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class EmpireData {
    private static final String ALLOWED_RANDOM_TECH = "allowed_random_tech";
    private static final String BUILD_LIST = "build_list";
    private static final String BUILD_LIST_ITEMS = "build_list_items";
    private static final String DESIGN_VERSIONS = "design_versions";
    private static final String DISPOSITION = "disposition";
    private static final String EMPIRE_EVENTS = "empire_events";
    private static final String EMPIRE_STATS = "empire_stats";
    private static final String HIDE_AI_PROPOSALS = "hide_ai_proposals";
    private static final String HIDE_AUTO_SELECT_ATTACK = "hide_auto_select_attack";
    private static final String KNOWN_EMPIRES = "known_empires";
    private static final String MIGRANTS = "migrants";
    private static final String PLAYER_SETTINGS = "player_settings";
    private static final String RACE_ATTRIBUTES = "race_attributes";
    private static final String RELATION_VALUES = "relation_values";
    private static final String TECH = "tech";
    private static final String TECHNOLOGIES = "technologies";
    private static final String TREATIES = "treaties";
    private static final String TREATIES_START_DATES = "treaties_start_dates";
    private static final String TURNS_TO_ASK_AGAIN = "turns_to_ask_again";
    private SQLiteDatabase db;
    private final Game game;

    public EmpireData(Game game) {
        this.game = game;
    }

    private List<RaceAttribute> getAttributes(int i) {
        ArrayList arrayList = new ArrayList();
        Cursor query = this.db.query(RACE_ATTRIBUTES, new String[]{"attributeID"}, "empireID = ?", new String[]{Integer.toString(i)}, null, null, null);
        int columnIndex = query.getColumnIndex("attributeID");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            arrayList.add(RaceAttribute.values()[query.getInt(columnIndex)]);
            query.moveToNext();
        }
        query.close();
        return arrayList;
    }

    private BuildLists getBuildLists(int i) {
        BuildLists buildLists = new BuildLists();
        Cursor query = this.db.query(BUILD_LIST, new String[]{"buildListID, name"}, "empireID = ?", new String[]{Integer.toString(i)}, null, null, null);
        int columnIndex = query.getColumnIndex("buildListID");
        int columnIndex2 = query.getColumnIndex(LevelConstants.TAG_LEVEL_ATTRIBUTE_NAME);
        query.moveToFirst();
        while (!query.isAfterLast()) {
            buildLists.setName(query.getInt(columnIndex), query.getString(columnIndex2));
            query.moveToNext();
        }
        query.close();
        Cursor query2 = this.db.query(BUILD_LIST_ITEMS, new String[]{"buildListID, buildOrder, buildingID"}, "empireID = ?", new String[]{Integer.toString(i)}, null, null, null);
        int columnIndex3 = query2.getColumnIndex("buildListID");
        int columnIndex4 = query2.getColumnIndex("buildOrder");
        int columnIndex5 = query2.getColumnIndex("buildingID");
        query2.moveToFirst();
        while (!query2.isAfterLast()) {
            buildLists.getItems(query2.getInt(columnIndex3)).put(Integer.valueOf(query2.getInt(columnIndex4)), BuildingID.values()[query2.getInt(columnIndex5)]);
            query2.moveToNext();
        }
        query2.close();
        return buildLists;
    }

    private List<Integer> getDiscoveredSystems(int i) {
        ArrayList arrayList = new ArrayList();
        Cursor query = this.db.query("discovered_systems", new String[]{"systemID"}, "empireID = ?", new String[]{Integer.toString(i)}, null, null, null);
        int columnIndex = query.getColumnIndex("systemID");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            arrayList.add(Integer.valueOf(query.getInt(columnIndex)));
            query.moveToNext();
        }
        query.close();
        return arrayList;
    }

    private SparseIntArray getDisposition(int i) {
        SparseIntArray sparseIntArray = new SparseIntArray();
        Cursor query = this.db.query(DISPOSITION, new String[]{"otherEmpireID, value"}, "empireID = ?", new String[]{Integer.toString(i)}, null, null, null);
        int columnIndex = query.getColumnIndex("otherEmpireID");
        int columnIndex2 = query.getColumnIndex("value");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            sparseIntArray.put(query.getInt(columnIndex), query.getInt(columnIndex2));
            query.moveToNext();
        }
        query.close();
        return sparseIntArray;
    }

    private Map<String, Integer> getEvents(int i) {
        HashMap hashMap = new HashMap();
        Cursor query = this.db.query(EMPIRE_EVENTS, new String[]{"key, value"}, "empireID = ?", new String[]{Integer.toString(i)}, null, null, null);
        int columnIndex = query.getColumnIndex("key");
        int columnIndex2 = query.getColumnIndex("value");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            hashMap.put(query.getString(columnIndex), Integer.valueOf(query.getInt(columnIndex2)));
            query.moveToNext();
        }
        query.close();
        return hashMap;
    }

    private SparseBooleanArray getHideAIProposals(int i) {
        SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
        Cursor query = this.db.query(HIDE_AI_PROPOSALS, new String[]{"otherEmpireID", "hide"}, "empireID = ?", new String[]{Integer.toString(i)}, null, null, null);
        int columnIndex = query.getColumnIndex("otherEmpireID");
        int columnIndex2 = query.getColumnIndex("hide");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            sparseBooleanArray.put(query.getInt(columnIndex), query.getInt(columnIndex2) == 1);
            query.moveToNext();
        }
        query.close();
        return sparseBooleanArray;
    }

    private SparseBooleanArray getHideAutoSelectAttack(int i) {
        SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
        Cursor query = this.db.query(HIDE_AUTO_SELECT_ATTACK, new String[]{"otherEmpireID", "hide"}, "empireID = ?", new String[]{Integer.toString(i)}, null, null, null);
        int columnIndex = query.getColumnIndex("otherEmpireID");
        int columnIndex2 = query.getColumnIndex("hide");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            sparseBooleanArray.put(query.getInt(columnIndex), query.getInt(columnIndex2) == 1);
            query.moveToNext();
        }
        query.close();
        return sparseBooleanArray;
    }

    private SparseIntArray getHistoryForType(int i, int i2) {
        SparseIntArray sparseIntArray = new SparseIntArray();
        Cursor query = this.db.query(EMPIRE_STATS, new String[]{"turn, amount"}, "empireID = ? and type = ?", new String[]{Integer.toString(i), Integer.toString(i2)}, null, null, null);
        int columnIndex = query.getColumnIndex("turn");
        int columnIndex2 = query.getColumnIndex("amount");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            sparseIntArray.put(query.getInt(columnIndex), query.getInt(columnIndex2));
            query.moveToNext();
        }
        query.close();
        return sparseIntArray;
    }

    private List<Integer> getKnownEmpires(int i) {
        ArrayList arrayList = new ArrayList();
        Cursor query = this.db.query(KNOWN_EMPIRES, new String[]{"knownEmpire"}, "empireID = ?", new String[]{Integer.toString(i)}, null, null, null);
        int columnIndex = query.getColumnIndex("knownEmpire");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            arrayList.add(Integer.valueOf(query.getInt(columnIndex)));
            query.moveToNext();
        }
        query.close();
        return arrayList;
    }

    private List<Migrants> getMigrants(int i) {
        ArrayList arrayList = new ArrayList();
        Cursor query = this.db.query(MIGRANTS, new String[]{"systemID, orbit, populationCount, turns"}, "empireID = ?", new String[]{Integer.toString(i)}, null, null, null);
        int columnIndex = query.getColumnIndex("systemID");
        int columnIndex2 = query.getColumnIndex("orbit");
        int columnIndex3 = query.getColumnIndex("populationCount");
        int columnIndex4 = query.getColumnIndex("turns");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            arrayList.add(new Migrants(query.getInt(columnIndex), query.getInt(columnIndex2), query.getInt(columnIndex3), query.getInt(columnIndex4)));
            query.moveToNext();
        }
        query.close();
        return arrayList;
    }

    private PlayerSettings getPlayerSettings(int i) {
        PlayerSettings playerSettings = new PlayerSettings();
        Cursor query = this.db.query(PLAYER_SETTINGS, new String[]{"setting, value"}, "empireID = ?", new String[]{Integer.toString(i)}, null, null, null);
        int columnIndex = query.getColumnIndex("setting");
        int columnIndex2 = query.getColumnIndex("value");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            int i2 = query.getInt(columnIndex);
            int i3 = query.getInt(columnIndex2);
            if (i2 == PlayerSettingsEnum.ZOOM_LEVEL.ordinal()) {
                playerSettings.setZoomLevel(i3);
            } else if (i2 == PlayerSettingsEnum.GALAXY_X.ordinal()) {
                playerSettings.setGalaxyX(i3);
            } else if (i2 == PlayerSettingsEnum.GALAXY_Y.ordinal()) {
                playerSettings.setGalaxyY(i3);
            }
            query.moveToNext();
        }
        query.close();
        return playerSettings;
    }

    private SparseIntArray getRelationValues(int i) {
        SparseIntArray sparseIntArray = new SparseIntArray();
        Cursor query = this.db.query(RELATION_VALUES, new String[]{"otherEmpireID, value"}, "empireID = ?", new String[]{Integer.toString(i)}, null, null, null);
        int columnIndex = query.getColumnIndex("otherEmpireID");
        int columnIndex2 = query.getColumnIndex("value");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            sparseIntArray.put(query.getInt(columnIndex), query.getInt(columnIndex2));
            query.moveToNext();
        }
        query.close();
        return sparseIntArray;
    }

    private Map<ShipType, Integer> getShipDesignVersions(int i) {
        HashMap hashMap = new HashMap();
        Cursor query = this.db.query(DESIGN_VERSIONS, new String[]{"shipType, version"}, "empireID = ?", new String[]{Integer.toString(i)}, null, null, null);
        int columnIndex = query.getColumnIndex("shipType");
        int columnIndex2 = query.getColumnIndex("version");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            hashMap.put(ShipType.getShipType(query.getInt(columnIndex)), Integer.valueOf(query.getInt(columnIndex2)));
            query.moveToNext();
        }
        query.close();
        return hashMap;
    }

    private List<Map<Treaty, Integer>> getStartDates(int i) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < 7; i2++) {
            arrayList.add(new HashMap());
        }
        Cursor query = this.db.query(TREATIES_START_DATES, new String[]{"contactID", "treatyID", "StartTurn"}, "empireID = ?", new String[]{Integer.toString(i)}, null, null, null);
        int columnIndex = query.getColumnIndex("contactID");
        int columnIndex2 = query.getColumnIndex("treatyID");
        int columnIndex3 = query.getColumnIndex("StartTurn");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            ((Map) arrayList.get(query.getInt(columnIndex))).put(Treaty.getTreaty(query.getInt(columnIndex2)), Integer.valueOf(query.getInt(columnIndex3)));
            query.moveToNext();
        }
        query.close();
        return arrayList;
    }

    private Technology getTech(int i, EmpireType empireType) {
        HashMap hashMap = new HashMap();
        Cursor query = this.db.query(TECH, new String[]{"techID", "value"}, "empireID = ?", new String[]{Integer.toString(i)}, null, null, "techID ASC");
        int columnIndex = query.getColumnIndex("techID");
        int columnIndex2 = query.getColumnIndex("value");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            hashMap.put(TechType.values()[query.getInt(columnIndex)], Integer.valueOf(query.getInt(columnIndex2)));
            query.moveToNext();
        }
        query.close();
        SparseIntArray sparseIntArray = new SparseIntArray();
        Cursor query2 = this.db.query(TECHNOLOGIES, new String[]{"techID", "research"}, "empireID = ?", new String[]{Integer.toString(i)}, null, null, "techID ASC");
        int columnIndex3 = query2.getColumnIndex("techID");
        int columnIndex4 = query2.getColumnIndex("research");
        query2.moveToFirst();
        while (!query2.isAfterLast()) {
            sparseIntArray.put(query2.getInt(columnIndex3), query2.getInt(columnIndex4));
            query2.moveToNext();
        }
        query2.close();
        Cursor query3 = this.db.query(ALLOWED_RANDOM_TECH, new String[]{"techID"}, "empireID = ?", new String[]{Integer.toString(i)}, null, null, null);
        int columnIndex5 = query3.getColumnIndex("techID");
        ArrayList arrayList = new ArrayList();
        query3.moveToFirst();
        while (!query3.isAfterLast()) {
            arrayList.add(TechID.values()[query3.getInt(columnIndex5)]);
            query3.moveToNext();
        }
        query3.close();
        Technology technology = new Technology(hashMap, sparseIntArray, i, empireType);
        technology.setAllowedTechsForRandomProgression(arrayList);
        return technology;
    }

    private Treaties getTreaties(int i) {
        Treaties treaties = new Treaties(i);
        Cursor query = this.db.query(TREATIES, new String[]{"contactID", TREATIES}, "empireID = ?", new String[]{Integer.toString(i)}, null, null, null);
        int columnIndex = query.getColumnIndex("contactID");
        int columnIndex2 = query.getColumnIndex(TREATIES);
        query.moveToFirst();
        while (!query.isAfterLast()) {
            treaties.setTreatiesValue(query.getInt(columnIndex), query.getInt(columnIndex2));
            query.moveToNext();
        }
        query.close();
        treaties.setStartDates(getStartDates(i));
        return treaties;
    }

    private int[] getTurnsToAsk(int i) {
        int[] iArr = new int[7];
        for (int i2 = 0; i2 < 7; i2++) {
            iArr[i2] = 0;
        }
        Cursor query = this.db.query(TURNS_TO_ASK_AGAIN, new String[]{"otherEmpireID, turns"}, "empireID = ?", new String[]{Integer.toString(i)}, null, null, null);
        int columnIndex = query.getColumnIndex("otherEmpireID");
        int columnIndex2 = query.getColumnIndex("turns");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            iArr[query.getInt(columnIndex)] = query.getInt(columnIndex2);
            query.moveToNext();
        }
        query.close();
        return iArr;
    }

    private void loadEmpires() {
        Cursor query = this.db.query("empires", new String[]{"id", LevelConstants.TAG_LEVEL_ATTRIBUTE_NAME, "homeSystem", "homeWorld", "type", "sortBy", "credits", "taxrate", "currentTechID", "baseCommandPoints", "shipCount", "spyNetworks", "personality", "bannerID", "raceID", "shipStyleID"}, null, null, null, null, null);
        int columnIndex = query.getColumnIndex("id");
        int columnIndex2 = query.getColumnIndex(LevelConstants.TAG_LEVEL_ATTRIBUTE_NAME);
        int columnIndex3 = query.getColumnIndex("homeSystem");
        int columnIndex4 = query.getColumnIndex("homeWorld");
        int columnIndex5 = query.getColumnIndex("type");
        int columnIndex6 = query.getColumnIndex("sortBy");
        int columnIndex7 = query.getColumnIndex("credits");
        int columnIndex8 = query.getColumnIndex("taxrate");
        int columnIndex9 = query.getColumnIndex("currentTechID");
        int columnIndex10 = query.getColumnIndex("baseCommandPoints");
        int columnIndex11 = query.getColumnIndex("shipCount");
        int columnIndex12 = query.getColumnIndex("spyNetworks");
        int columnIndex13 = query.getColumnIndex("personality");
        int columnIndex14 = query.getColumnIndex("bannerID");
        int columnIndex15 = query.getColumnIndex("raceID");
        int columnIndex16 = query.getColumnIndex("shipStyleID");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            int i = columnIndex16;
            int i2 = query.getInt(columnIndex);
            int i3 = columnIndex;
            String string = query.getString(columnIndex2);
            int i4 = columnIndex2;
            int i5 = query.getInt(columnIndex3);
            int i6 = columnIndex3;
            int i7 = query.getInt(columnIndex4);
            int i8 = columnIndex4;
            EmpireType empireType = EmpireType.getEmpireType(query.getInt(columnIndex5));
            int i9 = columnIndex5;
            SortType sortType = SortType.values()[query.getInt(columnIndex6)];
            int i10 = columnIndex6;
            int i11 = query.getInt(columnIndex7);
            int i12 = columnIndex7;
            float f2 = query.getFloat(columnIndex8);
            int i13 = columnIndex8;
            int i14 = query.getInt(columnIndex9);
            int i15 = columnIndex9;
            int i16 = query.getInt(columnIndex10);
            int i17 = columnIndex10;
            int i18 = query.getInt(columnIndex11);
            int i19 = columnIndex11;
            int i20 = query.getInt(columnIndex12);
            int i21 = columnIndex12;
            Treaties treaties = getTreaties(i2);
            int i22 = columnIndex13;
            Personality personality = Personality.values()[query.getInt(columnIndex13)];
            int i23 = query.getInt(columnIndex14);
            int i24 = columnIndex14;
            int i25 = columnIndex15;
            int i26 = query.getInt(i25);
            int i27 = query.getInt(i);
            FleetsData a2 = this.game.getDatabase().a();
            Cursor cursor = query;
            SQLiteDatabase sQLiteDatabase = this.db;
            Empire loadEmpire = new Empire.Builder().id(i2).name(string).type(empireType).homeSystemID(i5).homeWorldOrbit(i7).discoveredSystems(getDiscoveredSystems(i2)).technology(getTech(i2, empireType)).sortBy(sortType).credits(i11).taxRate(f2).knownEmpires(getKnownEmpires(i2)).shipDesigns(a2.getShips(sQLiteDatabase, "shipDesignEmpire" + i2, i2)).hideAutoShowSelectAttack(getHideAutoSelectAttack(i2)).hideAIProposals(getHideAIProposals(i2)).migrants(getMigrants(i2)).designVersions(getShipDesignVersions(i2)).relationValuesAI(getRelationValues(i2)).dispositionAI(getDisposition(i2)).shipCount(i18).baseCommandPoints(i16).spyNetworks(i20).treaties(treaties).personality(personality).events(getEvents(i2)).attributes(getAttributes(i2)).buildLists(getBuildLists(i2)).populationHistory(getHistoryForType(i2, 0)).colonyCountHistory(getHistoryForType(i2, 1)).systemsCountHistory(getHistoryForType(i2, 2)).commandPointUsageHistory(getHistoryForType(i2, 3)).foodHistory(getHistoryForType(i2, 4)).productionHistory(getHistoryForType(i2, 5)).scienceHistory(getHistoryForType(i2, 6)).techHistory(getHistoryForType(i2, 7)).creditsPerTurnHistory(getHistoryForType(i2, 8)).bannerID(i23).raceID(i26).shipStyleID(i27).loadEmpire(i14);
            this.game.empires.add(loadEmpire);
            loadEmpire.getDiplomaticAI().setTurnsToAsk(getTurnsToAsk(i2));
            if (empireType == EmpireType.HUMAN) {
                this.game.playerSettings.put(Integer.valueOf(i2), getPlayerSettings(i2));
            }
            cursor.moveToNext();
            columnIndex = i3;
            columnIndex2 = i4;
            columnIndex3 = i6;
            columnIndex6 = i10;
            columnIndex4 = i8;
            columnIndex7 = i12;
            columnIndex5 = i9;
            columnIndex8 = i13;
            columnIndex9 = i15;
            columnIndex10 = i17;
            columnIndex11 = i19;
            columnIndex12 = i21;
            columnIndex14 = i24;
            columnIndex13 = i22;
            columnIndex15 = i25;
            columnIndex16 = i;
            query = cursor;
        }
        query.close();
    }

    private void saveEmpires() {
        Iterator<Empire> it;
        this.db.beginTransaction();
        Iterator<Empire> it2 = this.game.empires.getEmpires().iterator();
        while (it2.hasNext()) {
            Empire next = it2.next();
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", Integer.valueOf(next.id));
            contentValues.put(LevelConstants.TAG_LEVEL_ATTRIBUTE_NAME, next.getName());
            contentValues.put("type", Integer.valueOf(next.getType().getValue()));
            contentValues.put("homeSystem", Integer.valueOf(next.getHomeSystem()));
            contentValues.put("homeWorld", Integer.valueOf(next.getHomeWorldOrbit()));
            contentValues.put("sortBy", Integer.valueOf(next.getSortBy().ordinal()));
            contentValues.put("credits", Integer.valueOf(next.getCredits()));
            contentValues.put("taxrate", Float.valueOf(next.getTaxRate()));
            contentValues.put("currentTechID", Integer.valueOf(next.getTech().getCurrentTech().getID().ordinal()));
            contentValues.put("baseCommandPoints", Integer.valueOf(next.getBaseCommandPoints()));
            contentValues.put("spyNetworks", Integer.valueOf(next.getSpyNetworkValue()));
            contentValues.put("shipCount", Integer.valueOf(next.getShipCount()));
            contentValues.put("personality", Integer.valueOf(next.getPersonality().ordinal()));
            contentValues.put("bannerID", Integer.valueOf(next.getBannerID()));
            contentValues.put("raceID", Integer.valueOf(next.getRaceID()));
            contentValues.put("shipStyleID", Integer.valueOf(next.getShipStyleID()));
            String str = null;
            this.db.insert("empires", null, contentValues);
            for (Integer num : next.getDiscoveredSystems()) {
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("empireID", Integer.valueOf(next.id));
                contentValues2.put("systemID", num);
                this.db.insert("discovered_systems", null, contentValues2);
            }
            for (Integer num2 : next.getKnownEmpires()) {
                ContentValues contentValues3 = new ContentValues();
                contentValues3.put("empireID", Integer.valueOf(next.id));
                contentValues3.put("knownEmpire", num2);
                this.db.insert(KNOWN_EMPIRES, null, contentValues3);
            }
            int[] treatiesValues = next.getTreatiesValues();
            List<Map<Treaty, Integer>> startDates = next.getTreaties().getStartDates();
            int i = 0;
            while (i < 7) {
                ContentValues contentValues4 = new ContentValues();
                contentValues4.put("empireID", Integer.valueOf(next.id));
                contentValues4.put("contactID", Integer.valueOf(i));
                contentValues4.put(TREATIES, Integer.valueOf(treatiesValues[i]));
                this.db.insert(TREATIES, str, contentValues4);
                for (Map.Entry<Treaty, Integer> entry : startDates.get(i).entrySet()) {
                    ContentValues contentValues5 = new ContentValues();
                    contentValues5.put("empireID", Integer.valueOf(next.id));
                    contentValues5.put("contactID", Integer.valueOf(i));
                    contentValues5.put("treatyID", Integer.valueOf(entry.getKey().getID()));
                    contentValues5.put("StartTurn", entry.getValue());
                    this.db.insert(TREATIES_START_DATES, null, contentValues5);
                }
                i++;
                str = null;
            }
            for (int i2 = 0; i2 < 7; i2++) {
                ContentValues contentValues6 = new ContentValues();
                contentValues6.put("empireID", Integer.valueOf(next.id));
                contentValues6.put("otherEmpireID", Integer.valueOf(i2));
                contentValues6.put("value", Integer.valueOf(next.getRelationValue(i2)));
                this.db.insert(RELATION_VALUES, null, contentValues6);
            }
            for (int i3 = 0; i3 < 7; i3++) {
                ContentValues contentValues7 = new ContentValues();
                contentValues7.put("empireID", Integer.valueOf(next.id));
                contentValues7.put("otherEmpireID", Integer.valueOf(i3));
                contentValues7.put("value", Integer.valueOf(next.getDisposition(i3)));
                this.db.insert(DISPOSITION, null, contentValues7);
            }
            for (int i4 = 0; i4 < 7; i4++) {
                ContentValues contentValues8 = new ContentValues();
                contentValues8.put("empireID", Integer.valueOf(next.id));
                contentValues8.put("otherEmpireID", Integer.valueOf(i4));
                contentValues8.put("hide", Integer.valueOf(next.isAutoSelectAttackHidden(i4) ? 1 : 0));
                this.db.insert(HIDE_AUTO_SELECT_ATTACK, null, contentValues8);
            }
            for (int i5 = 0; i5 < 7; i5++) {
                ContentValues contentValues9 = new ContentValues();
                contentValues9.put("empireID", Integer.valueOf(next.id));
                contentValues9.put("otherEmpireID", Integer.valueOf(i5));
                contentValues9.put("hide", Integer.valueOf(next.isAIProposalsHidden(i5) ? 1 : 0));
                this.db.insert(HIDE_AI_PROPOSALS, null, contentValues9);
            }
            for (Map.Entry<TechType, Integer> entry2 : next.getTech().getTechValues().entrySet()) {
                ContentValues contentValues10 = new ContentValues();
                contentValues10.put("empireID", Integer.valueOf(next.id));
                contentValues10.put("techID", Integer.valueOf(entry2.getKey().ordinal()));
                contentValues10.put("value", entry2.getValue());
                this.db.insert(TECH, null, contentValues10);
            }
            TechID[] values = TechID.values();
            int length = values.length;
            int i6 = 0;
            while (i6 < length) {
                TechID techID = values[i6];
                Tech tech = next.getTech().getTech(techID);
                if (tech.getCurrentResearch() > 0) {
                    ContentValues contentValues11 = new ContentValues();
                    it = it2;
                    contentValues11.put("empireID", Integer.valueOf(next.id));
                    contentValues11.put("techID", Integer.valueOf(techID.ordinal()));
                    contentValues11.put("research", Integer.valueOf(tech.getCurrentResearch()));
                    this.db.insert(TECHNOLOGIES, null, contentValues11);
                } else {
                    it = it2;
                }
                i6++;
                it2 = it;
            }
            Iterator<Empire> it3 = it2;
            for (Ship ship : next.getShipDesigns()) {
                FleetsData a2 = this.game.getDatabase().a();
                SQLiteDatabase sQLiteDatabase = this.db;
                a2.a(sQLiteDatabase, "shipDesignEmpire" + next.id, ship);
            }
            ShipType[] shipTypeArr = {ShipType.DESTROYER, ShipType.CRUISER, ShipType.BATTLESHIP, ShipType.DREADNOUGHT};
            int i7 = 0;
            for (int i8 = 4; i7 < i8; i8 = 4) {
                ShipType shipType = shipTypeArr[i7];
                ContentValues contentValues12 = new ContentValues();
                contentValues12.put("empireID", Integer.valueOf(next.id));
                contentValues12.put("shipType", Integer.valueOf(shipType.id));
                contentValues12.put("version", Integer.valueOf(next.getDesignVersion(shipType)));
                this.db.insert(DESIGN_VERSIONS, null, contentValues12);
                i7++;
            }
            for (Migrants migrants : next.getMigrants()) {
                ContentValues contentValues13 = new ContentValues();
                contentValues13.put("empireID", Integer.valueOf(next.id));
                contentValues13.put("systemID", Integer.valueOf(migrants.getSystemID()));
                contentValues13.put("orbit", Integer.valueOf(migrants.getOrbit()));
                contentValues13.put("populationCount", Integer.valueOf(migrants.getPopulationCount()));
                contentValues13.put("turns", Integer.valueOf(migrants.getTurns()));
                this.db.insert(MIGRANTS, null, contentValues13);
            }
            for (Map.Entry<String, Integer> entry3 : next.getEvents().entrySet()) {
                ContentValues contentValues14 = new ContentValues();
                contentValues14.put("empireID", Integer.valueOf(next.id));
                contentValues14.put("key", entry3.getKey());
                contentValues14.put("value", entry3.getValue());
                this.db.insert(EMPIRE_EVENTS, null, contentValues14);
            }
            int[] turnsToAsk = next.getDiplomaticAI().getTurnsToAsk();
            int length2 = turnsToAsk.length;
            int i9 = 0;
            int i10 = 0;
            while (i9 < length2) {
                int i11 = turnsToAsk[i9];
                ContentValues contentValues15 = new ContentValues();
                contentValues15.put("empireID", Integer.valueOf(next.id));
                contentValues15.put("otherEmpireID", Integer.valueOf(i10));
                contentValues15.put("turns", Integer.valueOf(i11));
                this.db.insert(TURNS_TO_ASK_AGAIN, null, contentValues15);
                i10++;
                i9++;
                turnsToAsk = turnsToAsk;
            }
            for (RaceAttribute raceAttribute : next.getRaceAttributes()) {
                ContentValues contentValues16 = new ContentValues();
                contentValues16.put("empireID", Integer.valueOf(next.id));
                contentValues16.put("attributeID", Integer.valueOf(raceAttribute.ordinal()));
                this.db.insert(RACE_ATTRIBUTES, null, contentValues16);
            }
            for (int i12 = 0; i12 < 3; i12++) {
                if (!next.getBuildLists().isBlank(i12)) {
                    ContentValues contentValues17 = new ContentValues();
                    contentValues17.put("empireID", Integer.valueOf(next.id));
                    contentValues17.put("buildListID", Integer.valueOf(i12));
                    contentValues17.put(LevelConstants.TAG_LEVEL_ATTRIBUTE_NAME, next.getBuildLists().getName(i12));
                    this.db.insert(BUILD_LIST, null, contentValues17);
                    for (Map.Entry<Integer, BuildingID> entry4 : next.getBuildLists().getItems(i12).entrySet()) {
                        ContentValues contentValues18 = new ContentValues();
                        contentValues18.put("empireID", Integer.valueOf(next.id));
                        contentValues18.put("buildListID", Integer.valueOf(i12));
                        contentValues18.put("buildOrder", entry4.getKey());
                        contentValues18.put("buildingID", Integer.valueOf(entry4.getValue().ordinal()));
                        this.db.insert(BUILD_LIST_ITEMS, null, contentValues18);
                    }
                }
            }
            for (TechID techID2 : next.getTech().getAllowedTechsForRandomProgression()) {
                ContentValues contentValues19 = new ContentValues();
                contentValues19.put("empireID", Integer.valueOf(next.id));
                contentValues19.put("techID", Integer.valueOf(techID2.ordinal()));
                this.db.insert(ALLOWED_RANDOM_TECH, null, contentValues19);
            }
            SparseArray<SparseIntArray> stats = next.getStats();
            int[] iArr = {0, 1, 2, 3, 4, 5, 6, 7, 8};
            for (int i13 = 0; i13 < 9; i13++) {
                int i14 = iArr[i13];
                SparseIntArray sparseIntArray = stats.get(i14);
                for (int i15 = 0; i15 < sparseIntArray.size(); i15++) {
                    int keyAt = sparseIntArray.keyAt(i15);
                    int i16 = sparseIntArray.get(keyAt);
                    ContentValues contentValues20 = new ContentValues();
                    contentValues20.put("empireID", Integer.valueOf(next.id));
                    contentValues20.put("turn", Integer.valueOf(keyAt));
                    contentValues20.put("type", Integer.valueOf(i14));
                    contentValues20.put("amount", Integer.valueOf(i16));
                    this.db.insert(EMPIRE_STATS, null, contentValues20);
                }
            }
            if (next.isHuman()) {
                PlayerSettings playerSettings = this.game.playerSettings.get(Integer.valueOf(next.id));
                ContentValues contentValues21 = new ContentValues();
                contentValues21.put("empireID", Integer.valueOf(next.id));
                contentValues21.put("setting", Integer.valueOf(PlayerSettingsEnum.ZOOM_LEVEL.ordinal()));
                contentValues21.put("value", Integer.valueOf(playerSettings != null ? playerSettings.getZoomLevel() : 0));
                this.db.insert(PLAYER_SETTINGS, null, contentValues21);
                ContentValues contentValues22 = new ContentValues();
                contentValues22.put("empireID", Integer.valueOf(next.id));
                contentValues22.put("setting", Integer.valueOf(PlayerSettingsEnum.GALAXY_X.ordinal()));
                contentValues22.put("value", Integer.valueOf(playerSettings != null ? playerSettings.getGalaxyX() : 0));
                this.db.insert(PLAYER_SETTINGS, null, contentValues22);
                ContentValues contentValues23 = new ContentValues();
                contentValues23.put("empireID", Integer.valueOf(next.id));
                contentValues23.put("setting", Integer.valueOf(PlayerSettingsEnum.GALAXY_Y.ordinal()));
                contentValues23.put("value", Integer.valueOf(playerSettings != null ? playerSettings.getGalaxyY() : 0));
                this.db.insert(PLAYER_SETTINGS, null, contentValues23);
            }
            it2 = it3;
        }
        this.db.setTransactionSuccessful();
        this.db.endTransaction();
    }

    public void load(SQLiteDatabase sQLiteDatabase) {
        this.db = sQLiteDatabase;
        this.game.empires.clear();
        loadEmpires();
    }

    public void save(SQLiteDatabase sQLiteDatabase) {
        this.db = sQLiteDatabase;
        saveEmpires();
    }
}
