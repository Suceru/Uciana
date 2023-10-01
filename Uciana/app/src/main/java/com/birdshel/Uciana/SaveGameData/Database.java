package com.birdshel.Uciana.SaveGameData;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.RandomEvents.RandomEventType;
import com.birdshel.Uciana.Utility.Log;

import java.io.File;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Database {
    private static final int DATABASE_VERSION = 17;
    private final ColoniesData coloniesData;
    private final Context context;
    private final EmpireData empireData;
    private final EventsData eventsData;
    private final FleetsData fleetsData;
    private final GalaxyData galaxyData;
    private final Game game;
    private final GameSettingsData gameSettingsData = new GameSettingsData();
    private final OutpostsData outpostsData;
    private final SavedGameDetails savedGameDetails;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: MyApplication */
    /* loaded from: classes.dex */
    public static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context, String str) {
            super(context, str, (SQLiteDatabase.CursorFactory) null, 17);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void createDatabase(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("create table details (id integer primary key, galaxySize integer not null, turn integer not null, currentPlayer integer not null, bannerID integer not null , playerStatus0 integer not null, playerStatus1 integer not null, playerStatus2 integer not null, playerStatus3 integer not null, playerStatus4 integer not null, playerStatus5 integer not null, timePlayed integer not null, difficulty real not null, techCostVersion integer not null, timeStamp text not null, gameVersion integer not null);");
            sQLiteDatabase.execSQL("create table fleet_icons (_id integer primary key autoincrement, x integer not null, y integer not null, imageIndex integer not null, rotation integer not null);");
            sQLiteDatabase.execSQL("create table system_name_displays (_id integer primary key autoincrement, x integer not null, y integer not null, name text not null, occupier1 integer, occupier2 integer, occupier3 integer, occupier4 integer, occupier5 integer);");
            sQLiteDatabase.execSQL("create table nebulas (id integer primary key, x integer not null, y integer not null, size real not null, type integer not null, rotation real not null);");
            sQLiteDatabase.execSQL("create table starsystems (id integer primary key, name text not null, x integer not null, y integer not null, starType integer not null, starShape integer not null, starSize real not null,wormholeCount integer not null);");
            sQLiteDatabase.execSQL("create table wormholes (id integer primary key, system1 integer not null, system2 integer not null);");
            sQLiteDatabase.execSQL("create table blackholes (id integer primary key, x integer not null, y integer not null, size real not null, spinDirection integer not null, spinSpeed real not null);");
            sQLiteDatabase.execSQL("create table spacerifts (id integer primary key, x integer not null, y integer not null, size real not null, spinDirection integer not null, spinSpeed real not null);");
            sQLiteDatabase.execSQL("create table system_nebulas (_id integer primary key autoincrement, nebulaID integer not null, systemID integer not null);");
            sQLiteDatabase.execSQL("create table system_objects (_id integer primary key autoincrement, systemID integer not null, orbit integer not null, objectType integer not null, exploredValue integer not null);");
            sQLiteDatabase.execSQL("create table planets (_id integer primary key autoincrement, systemID integer not null, orbit integer not null, imageIndex integer not null, ringIndex integer not null, cityLightsIndex integer not null, moonIndex integer not null, moonSize real not null, climate integer not null, terraformedClimate integer not null, planetSize integer not null, richness integer not null, gravity integer not null, isTerraformed integer not null, explorationFind integer not null, hasRing integer not null, hasMoon integer not null);");
            sQLiteDatabase.execSQL("create table gas_giants (_id integer primary key autoincrement, systemID integer not null, orbit integer not null, imageIndex integer not null, ringIndex integer not null, moonIndex integer not null, moonSize real not null, planetSize integer not null, hasRing integer not null, hasMoon integer not null);");
            sQLiteDatabase.execSQL("create table asteroid_belts (_id integer primary key autoincrement, systemID integer not null, orbit integer not null, imageIndex integer not null, richness integer not null);");
            sQLiteDatabase.execSQL("create table ion_storms (_id integer primary key autoincrement, systemID integer not null, x integer not null, y integer not null, isVisible integer not null, imageIndex1 integer not null, imageIndex2 integer not null, x1 integer not null, y1 integer not null, x2 integer not null, y2 integer not null, alpha1 real not null, alpha2 real not null, rotation1 real not null, rotation2 real not null);");
            sQLiteDatabase.execSQL("create table resources (_id integer primary key autoincrement, resourceID integer not null, systemID integer not null, orbit integer not null);");
            sQLiteDatabase.execSQL("create table wormhole_objects (_id integer primary key autoincrement, systemID integer not null, x integer not null, y integer not null, size integer not null, bottomSpinSpeed real not null, topSpinSpeed real not null);");
            sQLiteDatabase.execSQL("create table empires (id integer primary key, name text, homeSystem integer, homeWorld integer, type integer not null, sortBy integer not null, credits integer not null,taxrate real not null,shipCount integer not null, baseCommandPoints integer not null, spyNetworks integer not null, personality integer not null, currentTechID integer not null, bannerID integer not null, raceID integer not null, shipStyleID integer not null );");
            sQLiteDatabase.execSQL("create table known_empires (_id integer primary key autoincrement, empireID integer not null, knownEmpire integer not null );");
            sQLiteDatabase.execSQL("create table treaties (_id integer primary key autoincrement, empireID integer not null, contactID integer not null, treaties integer not null );");
            sQLiteDatabase.execSQL("create table treaties_start_dates (_id integer primary key autoincrement, empireID integer not null, contactID integer not null, treatyID integer not null, StartTurn integer not null );");
            sQLiteDatabase.execSQL("create table hide_auto_select_attack (_id integer primary key autoincrement, empireID integer not null, otherEmpireID integer not null, hide integer not null );");
            sQLiteDatabase.execSQL("create table hide_ai_proposals (_id integer primary key autoincrement, empireID integer not null, otherEmpireID integer not null, hide integer not null );");
            sQLiteDatabase.execSQL("create table tech (_id integer primary key autoincrement, empireID integer not null, techID integer not null, value integer not null);");
            sQLiteDatabase.execSQL("create table technologies (_id integer primary key autoincrement, empireID integer not null, techID integer not null,research integer not null);");
            sQLiteDatabase.execSQL("create table discovered_systems (_id integer primary key autoincrement, empireID integer not null, systemID integer not null);");
            sQLiteDatabase.execSQL("create table migrants (_id integer primary key autoincrement, empireID integer not null, systemID integer not null, orbit integer not null, populationCount integer not null, turns integer not null);");
            sQLiteDatabase.execSQL("create table design_versions (_id integer primary key autoincrement, empireID integer not null, shipType integer not null, version integer not null);");
            sQLiteDatabase.execSQL("create table relation_values (_id integer primary key autoincrement, empireID integer not null, otherEmpireID integer not null, value integer not null);");
            sQLiteDatabase.execSQL("create table disposition (_id integer primary key autoincrement, empireID integer not null, otherEmpireID integer not null, value integer not null);");
            sQLiteDatabase.execSQL("create table empire_events (_id integer primary key autoincrement, empireID integer not null, key text not null, value integer not null);");
            sQLiteDatabase.execSQL("create table turns_to_ask_again (_id integer primary key autoincrement, empireID integer not null, otherEmpireID integer not null, turns integer not null);");
            sQLiteDatabase.execSQL("create table race_attributes (_id integer primary key autoincrement, empireID integer not null, attributeID integer not null);");
            sQLiteDatabase.execSQL("create table empire_stats (_id integer primary key autoincrement, empireID integer not null, turn integer not null, type integer not null, amount integer not null);");
            sQLiteDatabase.execSQL("create table player_settings (_id integer primary key autoincrement, empireID integer not null, setting integer not null, value integer not null);");
            sQLiteDatabase.execSQL("create table build_list (_id integer primary key autoincrement, empireID integer not null, buildListID integer not null, name text not null);");
            sQLiteDatabase.execSQL("create table build_list_items (_id integer primary key autoincrement, empireID integer not null, buildListID integer not null, buildOrder integer not null, buildingID integer not null);");
            sQLiteDatabase.execSQL("create table allowed_random_tech (_id integer primary key autoincrement, empireID integer not null, techID integer not null);");
            sQLiteDatabase.execSQL("create table colonies (_id integer primary key autoincrement, empireID integer not null, population integer not null, systemID integer not null, orbit integer not null, farmingPercent integer not null, workerPercent integer not null, scientistPercent integer not null, finishedProductionPoints integer not null, manufacturingType integer not null, manufacturingInfo integer not null, manufacturingIsRepeatOn integer not null, hasSoldABuildingThisTurn integer not null, infDivisions integer not null, turnFounded integer not null, name text, autoBuild integer not null);");
            sQLiteDatabase.execSQL("create table production_queues (_id integer primary key autoincrement, systemID integer not null, orbit integer not null, id text not null, type integer not null, name text not null, isRepeatOn integer not null, requiredProduction integer not null);");
            sQLiteDatabase.execSQL("create table buildings (_id integer primary key autoincrement, buildingID integer not null, systemID integer not null, orbit integer not null);");
            sQLiteDatabase.execSQL("create table building_progress (_id integer primary key autoincrement, systemID integer not null, orbit integer not null, buildingID integer not null, progress integer not null);");
            sQLiteDatabase.execSQL("create table population_percent (_id integer primary key autoincrement, empireID integer not null, systemID integer not null, orbit integer not null);");
            sQLiteDatabase.execSQL("create table fleets (_id integer primary key autoincrement, id text not null, x integer not null, y integer not null, empireID integer not null, systemID integer not null, originID integer not null, isMoving integer not null, wormholeTravel integer not null, inOrbit integer not null);");
            sQLiteDatabase.execSQL("create table ships (_id integer primary key autoincrement, id text not null, fleetID text not null, name text not null, type integer not null,designNumber integer, productionCost integer, armorHitPoints integer, armor integer, shield integer, targetingComputer integer, sublightEngine integer, isAuto integer, shipDesignIndex integer, beenUsed integer);");
            sQLiteDatabase.execSQL("create table ship_components (_id integer primary key autoincrement, shipID text not null, component integer, componentCount integer);");
            sQLiteDatabase.execSQL("create table outposts (_id integer primary key autoincrement, empireID integer not null, systemID integer not null, orbit integer not null);");
            sQLiteDatabase.execSQL("create table events (_id integer primary key autoincrement, eventID integer not null, empireID integer not null, previewed integer not null, eventType integer not null);");
            sQLiteDatabase.execSQL("create table event_data (_id integer primary key autoincrement, eventID integer not null, key integer not null, value text not null);");
            sQLiteDatabase.execSQL("create table game_settings (_id integer primary key autoincrement, id integer not null, value integer);");
            sQLiteDatabase.execSQL("create table random_events (_id integer primary key autoincrement, type integer not null, data1 integer, data2 integer, data3 integer);");
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            createDatabase(sQLiteDatabase);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }
    }

    public Database(Context context, Game game) {
        this.context = context;
        this.game = game;
        this.savedGameDetails = new SavedGameDetails(game, context);
        this.galaxyData = new GalaxyData(game);
        this.empireData = new EmpireData(game);
        this.coloniesData = new ColoniesData(game);
        this.fleetsData = new FleetsData(game);
        this.eventsData = new EventsData(game);
        this.outpostsData = new OutpostsData(game);
    }

    private void loadFromDB(File file) {
        if (file.exists()) {
            SQLiteDatabase openOrCreateDatabase = this.context.openOrCreateDatabase(file.getAbsolutePath(), 0, null);
            if (openOrCreateDatabase.getVersion() < 17) {
                upgradeDatabase(openOrCreateDatabase, openOrCreateDatabase.getVersion());
            }
            this.savedGameDetails.load(openOrCreateDatabase);
            this.galaxyData.load(openOrCreateDatabase);
            this.empireData.load(openOrCreateDatabase);
            this.coloniesData.load(openOrCreateDatabase);
            this.fleetsData.load(openOrCreateDatabase);
            this.eventsData.load(openOrCreateDatabase);
            this.outpostsData.load(openOrCreateDatabase);
            this.gameSettingsData.load(openOrCreateDatabase);
            openOrCreateDatabase.close();
            for (Empire empire : this.game.empires.getEmpires()) {
                empire.setIsAlive();
                if (empire.isAlive()) {
                    empire.checkForCapital();
                    empire.calculateAverageDevelopmentScore();
                    empire.redistributeFood();
                }
            }
            Game game = this.game;
            game.randomEvents.setRandomEvent(game.gameSettings.currentRandomEventType(), this.game.gameSettings.currentRandomEventData1(), this.game.gameSettings.currentRandomEventData2(), this.game.gameSettings.currentRandomEventData3());
            Game game2 = this.game;
            game2.randomEvents.setCurrentRandomEventStartTurn(game2.gameSettings.currentRandomEventStartTurn());
            this.game.galaxyScene.setGalaxyState(0);
            if (this.game.gameSettings.currentRandomEventType() == RandomEventType.HYPER_SPACE_FLUCTUATIONS) {
                this.game.galaxyScene.setGalaxyState(1);
            }
        }
    }

    private void saveToDB(SQLiteDatabase sQLiteDatabase) {
        this.savedGameDetails.save(sQLiteDatabase);
        this.galaxyData.save(sQLiteDatabase);
        this.empireData.save(sQLiteDatabase);
        this.coloniesData.save(sQLiteDatabase);
        this.fleetsData.save(sQLiteDatabase);
        this.eventsData.save(sQLiteDatabase);
        this.outpostsData.save(sQLiteDatabase);
        this.gameSettingsData.save(sQLiteDatabase);
        sQLiteDatabase.close();
    }

    private void upgradeDatabase(SQLiteDatabase sQLiteDatabase, int i) {
        switch (i) {
            case 1:
                sQLiteDatabase.execSQL("ALTER TABLE colonies ADD COLUMN turnFounded INTEGER DEFAULT 1");
            case 2:
                sQLiteDatabase.execSQL("create table empire_events (_id integer primary key autoincrement, empireID integer not null, key text not null, value integer not null);");
            case 3:
                sQLiteDatabase.execSQL("create table turns_to_ask_again (_id integer primary key autoincrement, empireID integer not null, otherEmpireID integer not null, turns integer not null);");
            case 4:
                sQLiteDatabase.execSQL("create table race_attributes (_id integer primary key autoincrement, empireID integer not null, attributeID integer not null);");
            case 5:
                sQLiteDatabase.execSQL("create table hide_ai_proposals (_id integer primary key autoincrement, empireID integer not null, otherEmpireID integer not null, hide integer not null );");
            case 6:
                sQLiteDatabase.execSQL("ALTER TABLE details ADD COLUMN techCostVersion INTEGER DEFAULT 0");
            case 7:
                sQLiteDatabase.execSQL("create table empire_stats (_id integer primary key autoincrement, empireID integer not null, turn integer not null, type integer not null, amount integer not null);");
            case 8:
                sQLiteDatabase.execSQL("ALTER TABLE production_queues ADD COLUMN isRepeatOn INTEGER DEFAULT 0");
                sQLiteDatabase.execSQL("ALTER TABLE colonies ADD COLUMN manufacturingIsRepeatOn INTEGER DEFAULT 0");
            case 9:
                sQLiteDatabase.execSQL("create table player_settings (_id integer primary key autoincrement, empireID integer not null, setting integer not null, value integer not null);");
            case 10:
                sQLiteDatabase.execSQL("ALTER TABLE ships ADD COLUMN shipDesignIndex INTEGER DEFAULT 0");
            case 11:
                sQLiteDatabase.execSQL("ALTER TABLE colonies ADD COLUMN name TEXT");
            case 12:
                sQLiteDatabase.execSQL("create table game_settings (_id integer primary key autoincrement, id integer not null, value integer);");
                sQLiteDatabase.execSQL("INSERT INTO game_settings (id, value) VALUES (0, 0)");
                sQLiteDatabase.execSQL("INSERT INTO game_settings (id, value) VALUES (1, 0)");
                sQLiteDatabase.execSQL("INSERT INTO game_settings (id, value) VALUES (2, 0)");
                sQLiteDatabase.execSQL("INSERT INTO game_settings (id, value) VALUES (3, 0)");
                sQLiteDatabase.execSQL("INSERT INTO game_settings (id, value) VALUES (4, 0)");
                sQLiteDatabase.execSQL("INSERT INTO game_settings (id, value) VALUES (5, -1)");
                sQLiteDatabase.execSQL("INSERT INTO game_settings (id, value) VALUES (6, -1)");
                sQLiteDatabase.execSQL("INSERT INTO game_settings (id, value) VALUES (7, -1)");
                sQLiteDatabase.execSQL("INSERT INTO game_settings (id, value) VALUES (8, -1)");
                sQLiteDatabase.execSQL("INSERT INTO game_settings (id, value) VALUES (9, -1)");
                sQLiteDatabase.execSQL("INSERT INTO game_settings (id, value) VALUES (10, -1)");
                sQLiteDatabase.execSQL("create table random_events (_id integer primary key autoincrement, type integer not null, data1 integer, data2 integer, data3 integer);");
            case 13:
                sQLiteDatabase.execSQL("create table build_list (_id integer primary key autoincrement, empireID integer not null, buildListID integer not null, name text not null);");
                sQLiteDatabase.execSQL("create table build_list_items (_id integer primary key autoincrement, empireID integer not null, buildListID integer not null, buildOrder integer not null, buildingID integer not null);");
            case 14:
                sQLiteDatabase.execSQL("ALTER TABLE details ADD COLUMN gameVersion INTEGER DEFAULT 0");
            case 15:
                sQLiteDatabase.execSQL("create table allowed_random_tech (_id integer primary key autoincrement, empireID integer not null, techID integer not null);");
            case 16:
                sQLiteDatabase.execSQL("UPDATE fleets set empireID = 8 where empireID = 6;");
                sQLiteDatabase.execSQL("UPDATE fleets set empireID = 9 where empireID = 7;");
                sQLiteDatabase.execSQL("INSERT INTO empires (id, name, homeSystem, homeWorld, type, sortBy, credits, taxrate, shipCount, baseCommandPoints, spyNetworks, personality, currentTechID) VALUES (6, '', -1, -1, 2, 0, 0, 0, 0, 0, 0, 0, 0);");
                sQLiteDatabase.execSQL("ALTER TABLE empires ADD COLUMN bannerID INTEGER DEFAULT 0;");
                sQLiteDatabase.execSQL("ALTER TABLE empires ADD COLUMN raceID INTEGER DEFAULT 0;");
                sQLiteDatabase.execSQL("ALTER TABLE empires ADD COLUMN shipStyleID INTEGER DEFAULT 0;");
                sQLiteDatabase.execSQL("UPDATE empires set bannerID = id, raceID = id, shipStyleID = id;");
                break;
        }
        sQLiteDatabase.setVersion(17);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FleetsData a() {
        return this.fleetsData;
    }

    public void deleteSave(int i) {
        String str = "uciana" + i;
        if (this.context.getDatabasePath(str).exists()) {
            this.context.deleteDatabase(str);
        }
    }

    public SavedGameDetails getSavedGameDetails() {
        return this.savedGameDetails;
    }

    public void load(int i) {
        loadFromDB(this.context.getDatabasePath("uciana" + i));
    }

    public void loadFromStorage() {
        File file = new File(Environment.getExternalStorageDirectory() + "/uciana");
        loadFromDB(new File(file.getAbsolutePath() + File.separator + "save"));
    }

    public void save(int i) {
        saveToDB(new DatabaseHelper(this.context, "uciana_temp").getWritableDatabase());
        File databasePath = this.game.getActivity().getDatabasePath("uciana_temp");
        if (databasePath.renameTo(new File(databasePath.getParentFile(), "uciana" + i))) {
            return;
        }
        Log.message("Save", "Failed to rename the temp save to the save slot");
    }

    public void saveToStorage() {
        File file;
        new File(Environment.getExternalStorageDirectory() + "/uciana").mkdirs();
        String str = file.getAbsolutePath() + File.separator + "save";
        if (new File(str).exists()) {
            this.context.deleteDatabase(str);
        }
        SQLiteDatabase openOrCreateDatabase = this.context.openOrCreateDatabase(str, 0, null);
        DatabaseHelper.createDatabase(openOrCreateDatabase);
        saveToDB(openOrCreateDatabase);
    }
}
