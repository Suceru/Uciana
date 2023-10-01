package com.birdshel.Uciana.SaveGameData;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.Ships.Ship;
import com.birdshel.Uciana.Ships.ShipComponents.Armor;
import com.birdshel.Uciana.Ships.ShipComponents.Shield;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponent;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponentID;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponents;
import com.birdshel.Uciana.Ships.ShipComponents.SublightEngine;
import com.birdshel.Uciana.Ships.ShipComponents.TargetingComputer;
import com.birdshel.Uciana.Ships.ShipType;

import org.andengine.util.level.constants.LevelConstants;

import java.util.ArrayList;
import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
class FleetsData {
    private static final String FLEETS = "fleets";
    public static final String SHIPS = "ships";
    private static final String SHIP_COMPONENTS = "ship_components";
    private SQLiteDatabase db;
    private final Game game;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FleetsData(Game game) {
        this.game = game;
    }

    private List<ShipComponent> getShipComponents(SQLiteDatabase sQLiteDatabase, String str) {
        ArrayList arrayList = new ArrayList();
        Cursor query = sQLiteDatabase.query(SHIP_COMPONENTS, new String[]{"component", "componentCount"}, "shipID = ?", new String[]{str}, null, null, null);
        int columnIndex = query.getColumnIndex("component");
        int columnIndex2 = query.getColumnIndex("componentCount");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            ShipComponent shipComponent = ShipComponents.get(ShipComponentID.values()[query.getInt(columnIndex)]);
            shipComponent.setComponentCount(query.getInt(columnIndex2));
            arrayList.add(shipComponent);
            query.moveToNext();
        }
        query.close();
        return arrayList;
    }

    private void loadFleets() {
        Cursor query = this.db.query(FLEETS, new String[]{"id", "x", "y", "empireID", "systemID", "originID", "isMoving", "wormholeTravel", "inOrbit"}, null, null, null, null, null);
        int columnIndex = query.getColumnIndex("id");
        int columnIndex2 = query.getColumnIndex("x");
        int columnIndex3 = query.getColumnIndex("y");
        int columnIndex4 = query.getColumnIndex("empireID");
        int columnIndex5 = query.getColumnIndex("systemID");
        int columnIndex6 = query.getColumnIndex("originID");
        int columnIndex7 = query.getColumnIndex("isMoving");
        int columnIndex8 = query.getColumnIndex("wormholeTravel");
        int columnIndex9 = query.getColumnIndex("inOrbit");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            String string = query.getString(columnIndex);
            Point point = new Point(query.getInt(columnIndex2), query.getInt(columnIndex3));
            int i = query.getInt(columnIndex4);
            this.game.fleets.add(new Fleet(string, point, i, query.getInt(columnIndex5), query.getInt(columnIndex6), query.getInt(columnIndex7) != 0, query.getInt(columnIndex8) != 0, query.getInt(columnIndex9) != 0, getShips(this.db, string, i)));
            query.moveToNext();
        }
        query.close();
    }

    private void saveFleets() {
        this.db.beginTransaction();
        for (Fleet fleet : this.game.fleets.getFleets()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", fleet.id);
            contentValues.put("x", Float.valueOf(fleet.getPosition().getX()));
            contentValues.put("y", Float.valueOf(fleet.getPosition().getY()));
            contentValues.put("empireID", Integer.valueOf(fleet.empireID));
            contentValues.put("systemID", Integer.valueOf(fleet.getSystemID()));
            contentValues.put("originID", Integer.valueOf(fleet.getOriginSystem()));
            contentValues.put("isMoving", Integer.valueOf(fleet.isMoving() ? 1 : 0));
            contentValues.put("wormholeTravel", Integer.valueOf(fleet.wormholeTravel() ? 1 : 0));
            contentValues.put("inOrbit", Integer.valueOf(fleet.inOrbit() ? 1 : 0));
            this.db.insert(FLEETS, null, contentValues);
            for (Ship ship : fleet.getShips()) {
                a(this.db, fleet.id, ship);
            }
        }
        this.db.setTransactionSuccessful();
        this.db.endTransaction();
    }

    private void saveShipComponents(SQLiteDatabase sQLiteDatabase, Ship ship) {
        for (ShipComponent shipComponent : ship.getShipComponents()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("shipID", ship.getID());
            contentValues.put("component", Integer.valueOf(shipComponent.getID().ordinal()));
            contentValues.put("componentCount", Integer.valueOf(shipComponent.getComponentCount()));
            sQLiteDatabase.insert(SHIP_COMPONENTS, null, contentValues);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(SQLiteDatabase sQLiteDatabase, String str, Ship ship) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", ship.getID());
        contentValues.put(LevelConstants.TAG_LEVEL_ATTRIBUTE_NAME, ship.getName());
        contentValues.put("fleetID", str);
        contentValues.put("type", Integer.valueOf(ship.getShipType().ordinal()));
        contentValues.put("beenUsed", Boolean.valueOf(ship.hasBeenUsed()));
        contentValues.put("isAuto", Boolean.valueOf(ship.isAutoOn()));
        contentValues.put("shipDesignIndex", Integer.valueOf(ship.getHullDesign()));
        if (ship.getShipType().isCombatShip()) {
            contentValues.put("designNumber", Integer.valueOf(ship.getDesignNumber()));
            contentValues.put("productionCost", Integer.valueOf(ship.getProductionCost()));
            contentValues.put("armorHitPoints", Integer.valueOf(ship.getArmorHitPoints()));
            contentValues.put("armor", Integer.valueOf(ship.getArmor().getID().ordinal()));
            contentValues.put("shield", Integer.valueOf(ship.getShield().getID().ordinal()));
            contentValues.put("targetingComputer", Integer.valueOf(ship.getTargetingComputer().getID().ordinal()));
            contentValues.put("sublightEngine", Integer.valueOf(ship.getSublightEngine().getID().ordinal()));
            saveShipComponents(sQLiteDatabase, ship);
        }
        sQLiteDatabase.insert(SHIPS, null, contentValues);
    }

    public List<Ship> getShips(SQLiteDatabase sQLiteDatabase, String str, int i) {
        int i2;
        boolean z;
        int i3;
        int i4;
        boolean z2;
        int i5;
        int i6;
        Cursor cursor;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        ArrayList arrayList;
        ArrayList arrayList2 = new ArrayList();
        Cursor query = sQLiteDatabase.query(SHIPS, new String[]{"id", LevelConstants.TAG_LEVEL_ATTRIBUTE_NAME, "type", "designNumber", "productionCost", "armorHitPoints", "armor", "shield", "targetingComputer", "sublightEngine", "isAuto", "beenUsed", "shipDesignIndex"}, "fleetID = ?", new String[]{str}, null, null, null);
        int columnIndex = query.getColumnIndex("id");
        int columnIndex2 = query.getColumnIndex(LevelConstants.TAG_LEVEL_ATTRIBUTE_NAME);
        int columnIndex3 = query.getColumnIndex("type");
        int columnIndex4 = query.getColumnIndex("designNumber");
        int columnIndex5 = query.getColumnIndex("productionCost");
        int columnIndex6 = query.getColumnIndex("armorHitPoints");
        int columnIndex7 = query.getColumnIndex("armor");
        int columnIndex8 = query.getColumnIndex("shield");
        int columnIndex9 = query.getColumnIndex("targetingComputer");
        int columnIndex10 = query.getColumnIndex("sublightEngine");
        int columnIndex11 = query.getColumnIndex("isAuto");
        int columnIndex12 = query.getColumnIndex("beenUsed");
        int columnIndex13 = query.getColumnIndex("shipDesignIndex");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            String string = query.getString(columnIndex);
            String string2 = query.getString(columnIndex2);
            int i14 = columnIndex;
            ShipType shipType = ShipType.values()[query.getInt(columnIndex3)];
            if (query.getInt(columnIndex11) != 0) {
                i2 = columnIndex11;
                z = true;
            } else {
                i2 = columnIndex11;
                z = false;
            }
            if (query.getInt(columnIndex12) != 0) {
                i3 = columnIndex2;
                i4 = columnIndex3;
                z2 = true;
            } else {
                i3 = columnIndex2;
                i4 = columnIndex3;
                z2 = false;
            }
            int i15 = query.getInt(columnIndex13);
            if (shipType.isCombatShip()) {
                i12 = columnIndex12;
                int i16 = query.getInt(columnIndex4);
                i7 = columnIndex4;
                int i17 = query.getInt(columnIndex5);
                i5 = columnIndex5;
                int i18 = query.getInt(columnIndex6);
                i8 = columnIndex6;
                Armor armor = (Armor) ShipComponents.get(ShipComponentID.values()[query.getInt(columnIndex7)]);
                i9 = columnIndex7;
                Shield shield = (Shield) ShipComponents.get(ShipComponentID.values()[query.getInt(columnIndex8)]);
                i10 = columnIndex8;
                TargetingComputer targetingComputer = (TargetingComputer) ShipComponents.get(ShipComponentID.values()[query.getInt(columnIndex9)]);
                i6 = columnIndex10;
                SublightEngine sublightEngine = (SublightEngine) ShipComponents.get(ShipComponentID.values()[query.getInt(columnIndex10)]);
                cursor = query;
                i11 = columnIndex9;
                i13 = columnIndex13;
                arrayList = arrayList2;
                arrayList.add(new Ship.Builder().id(string).name(string2).shipType(shipType).empireID(i).designNumber(i16).productionCost(i17).armorHitPoints(i18).armor(armor).shield(shield).targetingComputer(targetingComputer).sublightEngine(sublightEngine).shipComponents(getShipComponents(sQLiteDatabase, string)).isAuto(z).beenUsed(z2).hullDesign(i15).loadCombatShip());
            } else {
                i5 = columnIndex5;
                i6 = columnIndex10;
                cursor = query;
                i7 = columnIndex4;
                i8 = columnIndex6;
                i9 = columnIndex7;
                i10 = columnIndex8;
                i11 = columnIndex9;
                i12 = columnIndex12;
                i13 = columnIndex13;
                arrayList = arrayList2;
                arrayList.add(new Ship.Builder().id(string).name(string2).shipType(shipType).empireID(i).isAuto(z).beenUsed(z2).loadNonCombatShip());
            }
            cursor.moveToNext();
            columnIndex = i14;
            arrayList2 = arrayList;
            columnIndex11 = i2;
            columnIndex2 = i3;
            columnIndex3 = i4;
            columnIndex12 = i12;
            columnIndex4 = i7;
            columnIndex5 = i5;
            columnIndex9 = i11;
            columnIndex6 = i8;
            columnIndex7 = i9;
            columnIndex8 = i10;
            columnIndex10 = i6;
            columnIndex13 = i13;
            query = cursor;
        }
        ArrayList arrayList3 = arrayList2;
        query.close();
        return arrayList3;
    }

    public void load(SQLiteDatabase sQLiteDatabase) {
        this.db = sQLiteDatabase;
        this.game.fleets.clear();
        loadFleets();
    }

    public void save(SQLiteDatabase sQLiteDatabase) {
        this.db = sQLiteDatabase;
        saveFleets();
    }
}
