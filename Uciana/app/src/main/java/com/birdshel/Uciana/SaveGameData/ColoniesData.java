package com.birdshel.Uciana.SaveGameData;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.birdshel.Uciana.AI.Tasks.NoTask;
import com.birdshel.Uciana.Colonies.Buildings.BuildingID;
import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Colonies.Manufacturing;
import com.birdshel.Uciana.Colonies.ManufacturingType;
import com.birdshel.Uciana.Colonies.ProductionItem;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Ships.Ship;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import org.andengine.util.level.constants.LevelConstants;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
class ColoniesData {
    private static final String BUILDINGS = "buildings";
    private static final String BUILDING_PROGRESS = "building_progress";
    private static final String POPULATION_GROUPS = "population_percent";
    private SQLiteDatabase db;
    private final Game game;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.SaveGameData.ColoniesData$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1419a;

        static {
            int[] iArr = new int[ManufacturingType.values().length];
            f1419a = iArr;
            try {
                iArr[ManufacturingType.SHIP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1419a[ManufacturingType.BUILDINGS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ColoniesData(Game game) {
        this.game = game;
    }

    private Map<BuildingID, Integer> getBuildingProgress(int i, int i2) {
        HashMap hashMap = new HashMap();
        Cursor query = this.db.query(BUILDING_PROGRESS, new String[]{"buildingID", "progress"}, "systemID = ? and orbit = ?", new String[]{Integer.toString(i), Integer.toString(i2)}, null, null, null);
        int columnIndex = query.getColumnIndex("buildingID");
        int columnIndex2 = query.getColumnIndex("progress");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            hashMap.put(BuildingID.values()[Integer.parseInt(query.getString(columnIndex))], Integer.valueOf(query.getInt(columnIndex2)));
            query.moveToNext();
        }
        query.close();
        return hashMap;
    }

    private List<String> getBuildings(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        Cursor query = this.db.query(BUILDINGS, new String[]{"buildingID"}, "systemID = ? and orbit = ?", new String[]{Integer.toString(i), Integer.toString(i2)}, null, null, null);
        int columnIndex = query.getColumnIndex("buildingID");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            arrayList.add(query.getString(columnIndex));
            query.moveToNext();
        }
        query.close();
        return arrayList;
    }

    private Queue<Integer> getPopulationPercent(int i, int i2, int i3) {
        LinkedList linkedList = new LinkedList();
        Cursor query = this.db.query(POPULATION_GROUPS, new String[]{"empireID"}, "systemID = ? and orbit = ?", new String[]{Integer.toString(i2), Integer.toString(i3)}, null, null, null);
        if (query.getCount() > 0) {
            int columnIndex = query.getColumnIndex("empireID");
            query.moveToFirst();
            while (!query.isAfterLast()) {
                linkedList.add(Integer.valueOf(query.getInt(columnIndex)));
                query.moveToNext();
            }
            query.close();
        } else {
            for (int i4 = 0; i4 < 10; i4++) {
                linkedList.add(Integer.valueOf(i));
            }
        }
        return linkedList;
    }

    private LinkedList<ProductionItem> getProductionQueue(int i, int i2) {
        LinkedList<ProductionItem> linkedList = new LinkedList<>();
        Cursor query = this.db.query("production_queues", new String[]{"id", "type", LevelConstants.TAG_LEVEL_ATTRIBUTE_NAME, "requiredProduction", "isRepeatOn"}, "systemID = ? and orbit = ?", new String[]{Integer.toString(i), Integer.toString(i2)}, null, null, null);
        int columnIndex = query.getColumnIndex("id");
        int columnIndex2 = query.getColumnIndex("type");
        int columnIndex3 = query.getColumnIndex(LevelConstants.TAG_LEVEL_ATTRIBUTE_NAME);
        int columnIndex4 = query.getColumnIndex("requiredProduction");
        int columnIndex5 = query.getColumnIndex("isRepeatOn");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            linkedList.add(new ProductionItem(ManufacturingType.values()[query.getInt(columnIndex2)], query.getString(columnIndex), query.getString(columnIndex3), query.getInt(columnIndex4), query.getInt(columnIndex5) == 1));
            query.moveToNext();
        }
        query.close();
        return linkedList;
    }

    private void loadColonies() {
        int i;
        Cursor query = this.db.query("colonies", new String[]{"empireID", "population", "systemID", "orbit", "farmingPercent", "workerPercent", "scientistPercent", "finishedProductionPoints", "manufacturingType", "manufacturingInfo", "hasSoldABuildingThisTurn", "infDivisions", "autoBuild", "turnFounded", "manufacturingIsRepeatOn", LevelConstants.TAG_LEVEL_ATTRIBUTE_NAME}, null, null, null, null, null);
        int columnIndex = query.getColumnIndex("empireID");
        int columnIndex2 = query.getColumnIndex("population");
        int columnIndex3 = query.getColumnIndex("systemID");
        int columnIndex4 = query.getColumnIndex("orbit");
        int columnIndex5 = query.getColumnIndex("farmingPercent");
        int columnIndex6 = query.getColumnIndex("workerPercent");
        int columnIndex7 = query.getColumnIndex("scientistPercent");
        int columnIndex8 = query.getColumnIndex("finishedProductionPoints");
        int columnIndex9 = query.getColumnIndex("manufacturingType");
        int columnIndex10 = query.getColumnIndex("manufacturingInfo");
        int columnIndex11 = query.getColumnIndex("hasSoldABuildingThisTurn");
        int columnIndex12 = query.getColumnIndex("infDivisions");
        int columnIndex13 = query.getColumnIndex("autoBuild");
        int columnIndex14 = query.getColumnIndex("turnFounded");
        int columnIndex15 = query.getColumnIndex("manufacturingIsRepeatOn");
        int columnIndex16 = query.getColumnIndex(LevelConstants.TAG_LEVEL_ATTRIBUTE_NAME);
        query.moveToFirst();
        while (!query.isAfterLast()) {
            int i2 = columnIndex16;
            int i3 = query.getInt(columnIndex);
            int i4 = columnIndex;
            int i5 = query.getInt(columnIndex2);
            int i6 = columnIndex2;
            int i7 = query.getInt(columnIndex3);
            int i8 = columnIndex3;
            int i9 = query.getInt(columnIndex4);
            int i10 = columnIndex4;
            int i11 = query.getInt(columnIndex5);
            int i12 = columnIndex5;
            int i13 = query.getInt(columnIndex6);
            int i14 = columnIndex6;
            int i15 = query.getInt(columnIndex7);
            int i16 = columnIndex7;
            int i17 = query.getInt(columnIndex8);
            int i18 = query.getInt(columnIndex9);
            int i19 = columnIndex8;
            String string = query.getString(columnIndex10);
            int i20 = columnIndex9;
            int i21 = columnIndex10;
            boolean z = true;
            int i22 = columnIndex11;
            boolean z2 = query.getInt(columnIndex11) == 1;
            int i23 = query.getInt(columnIndex12);
            int i24 = columnIndex12;
            int i25 = columnIndex13;
            boolean z3 = query.getInt(columnIndex13) == 1;
            int i26 = query.getInt(columnIndex14);
            int i27 = columnIndex14;
            int i28 = columnIndex15;
            if (query.getInt(i28) == 1) {
                i = i2;
            } else {
                i = i2;
                z = false;
            }
            String string2 = query.getString(i);
            int i29 = i;
            StringBuilder sb = new StringBuilder();
            Cursor cursor = query;
            sb.append("queue");
            sb.append(i7);
            sb.append("-");
            sb.append(i9);
            this.game.colonies.addFromSave(new Colony.Builder().planet((Planet) this.game.galaxy.getStarSystem(i7).getSystemObject(i9)).empireID(i3).population(i5).farmersPercent(i11).workerPercent(i13).scientistPercent(i15).buildings(getBuildings(i7, i9)).hasSoldABuildingThisTurn(z2).infDivisions(i23).buildingProgress(getBuildingProgress(i7, i9)).autoBuild(z3).populationGroups(getPopulationPercent(i3, i7, i9)).manufacturing(new Manufacturing.Builder().currentItemID(string).currentItemType(ManufacturingType.values()[i18]).currentItemIsRepeatOn(z).queue(getProductionQueue(i7, i9)).queuedShipDesigns(this.game.getDatabase().a().getShips(this.db, sb.toString(), i3)).production(i17).empireID(i3).systemID(i7).orbit(i9).build()).turnFounded(i26).name(string2).task(new NoTask()).loadColony());
            cursor.moveToNext();
            columnIndex11 = i22;
            columnIndex = i4;
            columnIndex2 = i6;
            columnIndex3 = i8;
            columnIndex4 = i10;
            columnIndex5 = i12;
            columnIndex6 = i14;
            columnIndex7 = i16;
            columnIndex8 = i19;
            columnIndex9 = i20;
            columnIndex10 = i21;
            columnIndex12 = i24;
            columnIndex13 = i25;
            columnIndex14 = i27;
            columnIndex15 = i28;
            columnIndex16 = i29;
            query = cursor;
        }
        query.close();
    }

    private void saveColonies() {
        this.db.beginTransaction();
        for (Colony colony : this.game.colonies.getColonies()) {
            Manufacturing manufacturing = colony.getManufacturing();
            ContentValues contentValues = new ContentValues();
            contentValues.put("empireID", Integer.valueOf(colony.getEmpireID()));
            contentValues.put("population", Integer.valueOf(colony.getRealPopulation()));
            contentValues.put("systemID", Integer.valueOf(colony.getPlanet().getSystemID()));
            contentValues.put("orbit", Integer.valueOf(colony.getPlanet().getOrbit()));
            contentValues.put("turnFounded", Integer.valueOf(colony.getTurnFounded()));
            contentValues.put("farmingPercent", Integer.valueOf(colony.getFarmersPercent()));
            contentValues.put("workerPercent", Integer.valueOf(colony.getWorkersPercent()));
            contentValues.put("scientistPercent", Integer.valueOf(colony.getScientistPercent()));
            contentValues.put("finishedProductionPoints", Integer.valueOf(manufacturing.getFinishedProduction()));
            contentValues.put("manufacturingType", Integer.valueOf(manufacturing.getType().ordinal()));
            int i = AnonymousClass1.f1419a[manufacturing.getType().ordinal()];
            if (i == 1) {
                contentValues.put("manufacturingInfo", manufacturing.getCurrentItem().getID());
            } else if (i != 2) {
                contentValues.put("manufacturingInfo", (Integer) 0);
            } else {
                contentValues.put("manufacturingInfo", manufacturing.getBuildingID());
            }
            contentValues.put("manufacturingIsRepeatOn", Integer.valueOf(manufacturing.getCurrentItem().isRepeatOn() ? 1 : 0));
            contentValues.put("hasSoldABuildingThisTurn", Integer.valueOf(colony.hasSoldABuildingThisTurn() ? 1 : 0));
            contentValues.put("infDivisions", Integer.valueOf(colony.getInfDivisions()));
            contentValues.put("autoBuild", Integer.valueOf(colony.isAutoBuilding() ? 1 : 0));
            contentValues.put(LevelConstants.TAG_LEVEL_ATTRIBUTE_NAME, colony.getName().equals(colony.getPlanet().getName()) ? "" : colony.getName());
            this.db.insert("colonies", null, contentValues);
            Iterator<ProductionItem> it = colony.getQueue().iterator();
            while (it.hasNext()) {
                ProductionItem next = it.next();
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("systemID", Integer.valueOf(colony.getSystemID()));
                contentValues2.put("orbit", Integer.valueOf(colony.getOrbit()));
                contentValues2.put("id", next.getID());
                contentValues2.put("type", Integer.valueOf(next.getType().getValue()));
                contentValues2.put(LevelConstants.TAG_LEVEL_ATTRIBUTE_NAME, next.getName());
                contentValues2.put("requiredProduction", Integer.valueOf(next.getRequiredProduction()));
                contentValues2.put("isRepeatOn", Integer.valueOf(next.isRepeatOn() ? 1 : 0));
                this.db.insert("production_queues", null, contentValues2);
            }
            String str = "queue" + colony.getSystemID() + "-" + colony.getOrbit();
            for (Map.Entry<String, Ship> entry : colony.getManufacturing().getQueuedShipDesigns().entrySet()) {
                this.game.getDatabase().a().a(this.db, str, entry.getValue());
            }
            for (String str2 : colony.getBuildings()) {
                ContentValues contentValues3 = new ContentValues();
                contentValues3.put("buildingID", str2);
                contentValues3.put("systemID", Integer.valueOf(colony.getSystemID()));
                contentValues3.put("orbit", Integer.valueOf(colony.getOrbit()));
                this.db.insert(BUILDINGS, null, contentValues3);
            }
            for (Map.Entry<BuildingID, Integer> entry2 : colony.getBuildingProgress().entrySet()) {
                ContentValues contentValues4 = new ContentValues();
                contentValues4.put("systemID", Integer.valueOf(colony.getSystemID()));
                contentValues4.put("orbit", Integer.valueOf(colony.getOrbit()));
                contentValues4.put("buildingID", entry2.getKey().toString());
                contentValues4.put("progress", entry2.getValue());
                this.db.insert(BUILDING_PROGRESS, null, contentValues4);
            }
            if (!colony.isAllPopulationSupportive()) {
                for (Integer num : colony.getPopulationGroups()) {
                    int intValue = num.intValue();
                    ContentValues contentValues5 = new ContentValues();
                    contentValues5.put("systemID", Integer.valueOf(colony.getSystemID()));
                    contentValues5.put("orbit", Integer.valueOf(colony.getOrbit()));
                    contentValues5.put("empireID", Integer.valueOf(intValue));
                    this.db.insert(POPULATION_GROUPS, null, contentValues5);
                }
            }
        }
        this.db.setTransactionSuccessful();
        this.db.endTransaction();
    }

    public void load(SQLiteDatabase sQLiteDatabase) {
        this.db = sQLiteDatabase;
        this.game.colonies.clear();
        loadColonies();
    }

    public void save(SQLiteDatabase sQLiteDatabase) {
        this.db = sQLiteDatabase;
        saveColonies();
    }
}
