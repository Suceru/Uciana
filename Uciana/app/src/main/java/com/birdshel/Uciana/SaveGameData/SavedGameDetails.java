package com.birdshel.Uciana.SaveGameData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.constraintlayout.motion.widget.Key;
import com.birdshel.Uciana.BuildConfig;
import com.birdshel.Uciana.Difficulty;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Scenes.GalaxyScene;
import com.birdshel.Uciana.Ships.FleetIconData;
import com.birdshel.Uciana.StarSystems.Blackhole;
import com.birdshel.Uciana.StarSystems.GalaxySize;
import com.birdshel.Uciana.StarSystems.Nebula;
import com.birdshel.Uciana.StarSystems.NebulaType;
import com.birdshel.Uciana.StarSystems.SpaceRift;
import com.birdshel.Uciana.StarSystems.Star;
import com.birdshel.Uciana.StarSystems.StarType;
import com.birdshel.Uciana.StarSystems.SystemNameDisplay;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.andengine.util.level.constants.LevelConstants;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class SavedGameDetails {
    private static final String FLEET_ICONS = "fleet_icons";
    private static final String SYSTEM_NAME_DISPLAYS = "system_name_displays";
    private final Context context;
    private final Game game;

    public SavedGameDetails(Game game, Context context) {
        this.game = game;
        this.context = context;
    }

    private List<Blackhole> getBlackHoles(SQLiteDatabase sQLiteDatabase) {
        ArrayList arrayList = new ArrayList();
        Cursor query = sQLiteDatabase.query("blackholes", new String[]{"id", "x", "y", "size", "spinDirection", "spinSpeed"}, null, null, null, null, null);
        int columnIndex = query.getColumnIndex("id");
        int columnIndex2 = query.getColumnIndex("x");
        int columnIndex3 = query.getColumnIndex("y");
        int columnIndex4 = query.getColumnIndex("size");
        int columnIndex5 = query.getColumnIndex("spinDirection");
        int columnIndex6 = query.getColumnIndex("spinSpeed");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            arrayList.add(new Blackhole(query.getInt(columnIndex), new Point(query.getInt(columnIndex2), query.getInt(columnIndex3)), query.getInt(columnIndex4), query.getInt(columnIndex5) != 0, query.getInt(columnIndex6)));
            query.moveToNext();
        }
        query.close();
        return arrayList;
    }

    private String getDateAndTime() {
        return new SimpleDateFormat("MMM dd yyyy   h:mm:ss a", Locale.US).format(new Date());
    }

    private Difficulty getDifficulty(SQLiteDatabase sQLiteDatabase) {
        Cursor query = sQLiteDatabase.query("details", new String[]{"difficulty"}, null, null, null, null, null);
        int columnIndex = query.getColumnIndex("difficulty");
        query.moveToFirst();
        Difficulty difficulty = Difficulty.values()[query.getInt(columnIndex)];
        query.close();
        return difficulty;
    }

    private List<Integer> getDiscoveredSystemsList(SQLiteDatabase sQLiteDatabase, int i) {
        ArrayList arrayList = new ArrayList();
        Cursor query = sQLiteDatabase.query("discovered_systems", new String[]{"systemID"}, "empireID = ?", new String[]{Integer.toString(i)}, null, null, null);
        int columnIndex = query.getColumnIndex("systemID");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            arrayList.add(Integer.valueOf(query.getInt(columnIndex)));
            query.moveToNext();
        }
        query.close();
        return arrayList;
    }

    private List<FleetIconData> getFleetIcons(SQLiteDatabase sQLiteDatabase) {
        ArrayList arrayList = new ArrayList();
        Cursor query = sQLiteDatabase.query(FLEET_ICONS, new String[]{"x", "y", "imageIndex", Key.ROTATION}, null, null, null, null, null);
        int columnIndex = query.getColumnIndex("imageIndex");
        int columnIndex2 = query.getColumnIndex("x");
        int columnIndex3 = query.getColumnIndex("y");
        int columnIndex4 = query.getColumnIndex(Key.ROTATION);
        query.moveToFirst();
        while (!query.isAfterLast()) {
            arrayList.add(new FleetIconData(new Point(query.getInt(columnIndex2), query.getInt(columnIndex3)), query.getInt(columnIndex), query.getInt(columnIndex4)));
            query.moveToNext();
        }
        query.close();
        return arrayList;
    }

    private GalaxySize getGalaxySize(SQLiteDatabase sQLiteDatabase) {
        Cursor query = sQLiteDatabase.query("details", new String[]{"galaxySize"}, null, null, null, null, null);
        int columnIndex = query.getColumnIndex("galaxySize");
        query.moveToFirst();
        GalaxySize galaxySize = GalaxySize.values()[query.getInt(columnIndex)];
        query.close();
        return galaxySize;
    }

    private int getSaveBannerID(SQLiteDatabase sQLiteDatabase, int i) {
        try {
            Cursor query = sQLiteDatabase.query("details", new String[]{"bannerID"}, null, null, null, null, null);
            int columnIndex = query.getColumnIndex("bannerID");
            query.moveToFirst();
            int i2 = query.getInt(columnIndex);
            query.close();
            return i2;
        } catch (Exception unused) {
            return i;
        }
    }

    private int getSaveCurrentPlayer(SQLiteDatabase sQLiteDatabase) {
        Cursor query = sQLiteDatabase.query("details", new String[]{"currentPlayer"}, null, null, null, null, null);
        int columnIndex = query.getColumnIndex("currentPlayer");
        query.moveToFirst();
        int i = query.getInt(columnIndex);
        query.close();
        return i;
    }

    private long getSaveTimePlayed(SQLiteDatabase sQLiteDatabase) {
        Cursor query = sQLiteDatabase.query("details", new String[]{"timePlayed"}, null, null, null, null, null);
        int columnIndex = query.getColumnIndex("timePlayed");
        query.moveToFirst();
        long j = query.getLong(columnIndex);
        query.close();
        return j;
    }

    private String getSaveTimeStamp(SQLiteDatabase sQLiteDatabase) {
        Cursor query = sQLiteDatabase.query("details", new String[]{"timeStamp"}, null, null, null, null, null);
        int columnIndex = query.getColumnIndex("timeStamp");
        query.moveToFirst();
        String string = query.getString(columnIndex);
        query.close();
        return string;
    }

    private String getSaveTurns(SQLiteDatabase sQLiteDatabase) {
        Cursor query = sQLiteDatabase.query("details", new String[]{"turn"}, null, null, null, null, null);
        int columnIndex = query.getColumnIndex("turn");
        query.moveToFirst();
        String num = Integer.toString(query.getInt(columnIndex));
        query.close();
        return num;
    }

    private List<Integer> getShipStyles(SQLiteDatabase sQLiteDatabase) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 7; i++) {
            arrayList.add(Integer.valueOf(i));
        }
        try {
            Cursor query = sQLiteDatabase.query("empires", new String[]{"id", "shipStyleID"}, null, null, null, null, null);
            int columnIndex = query.getColumnIndex("id");
            int columnIndex2 = query.getColumnIndex("shipStyleID");
            query.moveToFirst();
            while (!query.isAfterLast()) {
                arrayList.add(query.getInt(columnIndex), Integer.valueOf(query.getInt(columnIndex2)));
                query.moveToNext();
            }
            query.close();
        } catch (Exception unused) {
        }
        return arrayList;
    }

    private List<SpaceRift> getSpaceRifts(SQLiteDatabase sQLiteDatabase) {
        ArrayList arrayList = new ArrayList();
        Cursor query = sQLiteDatabase.query("spacerifts", new String[]{"id", "x", "y", "size", "spinDirection", "spinSpeed"}, null, null, null, null, null);
        int columnIndex = query.getColumnIndex("id");
        int columnIndex2 = query.getColumnIndex("x");
        int columnIndex3 = query.getColumnIndex("y");
        int columnIndex4 = query.getColumnIndex("size");
        int columnIndex5 = query.getColumnIndex("spinDirection");
        int columnIndex6 = query.getColumnIndex("spinSpeed");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            arrayList.add(new SpaceRift(query.getInt(columnIndex), new Point(query.getInt(columnIndex2), query.getInt(columnIndex3)), query.getInt(columnIndex4), query.getInt(columnIndex5) != 0, query.getInt(columnIndex6)));
            query.moveToNext();
        }
        query.close();
        return arrayList;
    }

    private List<Star> getStars(SQLiteDatabase sQLiteDatabase) {
        ArrayList arrayList = new ArrayList();
        Cursor query = sQLiteDatabase.query("starsystems", new String[]{"x", "y", "starType", "starShape", "starSize"}, null, null, null, null, null);
        int columnIndex = query.getColumnIndex("x");
        int columnIndex2 = query.getColumnIndex("y");
        int columnIndex3 = query.getColumnIndex("starType");
        int columnIndex4 = query.getColumnIndex("starShape");
        int columnIndex5 = query.getColumnIndex("starSize");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            arrayList.add(new Star(new Point(query.getInt(columnIndex), query.getInt(columnIndex2)), StarType.values()[query.getInt(columnIndex3)], query.getInt(columnIndex4), query.getFloat(columnIndex5)));
            query.moveToNext();
        }
        query.close();
        return arrayList;
    }

    private List<SystemNameDisplay> getSystemNameDisplays(SQLiteDatabase sQLiteDatabase) {
        ArrayList arrayList = new ArrayList();
        Cursor query = sQLiteDatabase.query(SYSTEM_NAME_DISPLAYS, new String[]{"x", "y", LevelConstants.TAG_LEVEL_ATTRIBUTE_NAME, "occupier1", "occupier2", "occupier3", "occupier4", "occupier5"}, null, null, null, null, null);
        int columnIndex = query.getColumnIndex("x");
        int columnIndex2 = query.getColumnIndex("y");
        int columnIndex3 = query.getColumnIndex(LevelConstants.TAG_LEVEL_ATTRIBUTE_NAME);
        query.moveToFirst();
        while (!query.isAfterLast()) {
            ArrayList arrayList2 = new ArrayList();
            for (int i = 1; i < 7; i++) {
                if (!query.isNull(query.getColumnIndex("occupier" + i))) {
                    arrayList2.add(Integer.valueOf(query.getInt(query.getColumnIndex("occupier" + i))));
                }
            }
            arrayList.add(new SystemNameDisplay(query.getInt(columnIndex), query.getInt(columnIndex2), query.getString(columnIndex3), arrayList2));
            query.moveToNext();
        }
        query.close();
        return arrayList;
    }

    private List<Point> getWormHoles(SQLiteDatabase sQLiteDatabase) {
        ArrayList arrayList = new ArrayList();
        Cursor query = sQLiteDatabase.query("wormholes", new String[]{"system1", "system2"}, null, null, null, null, null);
        int columnIndex = query.getColumnIndex("system1");
        int columnIndex2 = query.getColumnIndex("system2");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            arrayList.add(new Point(query.getInt(columnIndex), query.getInt(columnIndex2)));
            query.moveToNext();
        }
        query.close();
        return arrayList;
    }

    private void saveDetails(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.beginTransaction();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", (Integer) 0);
        contentValues.put("galaxySize", Integer.valueOf(this.game.galaxy.getSize().ordinal()));
        contentValues.put("turn", Integer.valueOf(GameData.turn));
        contentValues.put("currentPlayer", Integer.valueOf(this.game.getCurrentPlayer()));
        contentValues.put("bannerID", Integer.valueOf(this.game.getCurrentEmpire().getBannerID()));
        contentValues.put("playerStatus0", Integer.valueOf(this.game.getPlayerStatus(0)));
        contentValues.put("playerStatus1", Integer.valueOf(this.game.getPlayerStatus(1)));
        contentValues.put("playerStatus2", Integer.valueOf(this.game.getPlayerStatus(2)));
        contentValues.put("playerStatus3", Integer.valueOf(this.game.getPlayerStatus(3)));
        contentValues.put("playerStatus4", Integer.valueOf(this.game.getPlayerStatus(4)));
        contentValues.put("playerStatus5", Integer.valueOf(this.game.getPlayerStatus(5)));
        contentValues.put("timeStamp", getDateAndTime());
        contentValues.put("techCostVersion", Integer.valueOf(GameData.getTechCostVersion()));
        contentValues.put("difficulty", Integer.valueOf(Game.getDifficulty().ordinal()));
        contentValues.put("timePlayed", Long.valueOf(this.game.getTimePlayed()));
        contentValues.put("gameVersion", Integer.valueOf((int) BuildConfig.VERSION_CODE));
        sQLiteDatabase.insert("details", null, contentValues);
        sQLiteDatabase.setTransactionSuccessful();
        sQLiteDatabase.endTransaction();
    }

    private void saveFleetIcons(SQLiteDatabase sQLiteDatabase) {
        if (this.game.getCurrentPlayer() == -1) {
            return;
        }
        sQLiteDatabase.beginTransaction();
        for (GalaxyScene.FleetIconData fleetIconData : this.game.galaxyScene.getFleetDetails()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("x", Float.valueOf(fleetIconData.getX()));
            contentValues.put("y", Float.valueOf(fleetIconData.getY()));
            contentValues.put("imageIndex", Integer.valueOf(fleetIconData.getImageIndex()));
            contentValues.put(Key.ROTATION, Float.valueOf(fleetIconData.getRotation()));
            sQLiteDatabase.insert(FLEET_ICONS, null, contentValues);
        }
        sQLiteDatabase.setTransactionSuccessful();
        sQLiteDatabase.endTransaction();
    }

    private void saveSystemNameDisplays(SQLiteDatabase sQLiteDatabase) {
        if (this.game.getCurrentPlayer() == -1) {
            return;
        }
        sQLiteDatabase.beginTransaction();
        for (SystemNameDisplay systemNameDisplay : this.game.galaxyScene.getVisibleSystems()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("x", Float.valueOf(systemNameDisplay.getX()));
            contentValues.put("y", Float.valueOf(systemNameDisplay.getY()));
            contentValues.put(LevelConstants.TAG_LEVEL_ATTRIBUTE_NAME, systemNameDisplay.getName());
            int i = 1;
            for (Integer num : systemNameDisplay.getOccupiers()) {
                contentValues.put("occupier" + i, num);
                i++;
            }
            sQLiteDatabase.insert(SYSTEM_NAME_DISPLAYS, null, contentValues);
        }
        sQLiteDatabase.setTransactionSuccessful();
        sQLiteDatabase.endTransaction();
    }

    public boolean doesSaveExists(int i) {
        return this.context.getDatabasePath("uciana" + i).exists();
    }

    public List<Nebula> getNebulas(int i) {
        return getNebulas(this.context.openOrCreateDatabase("uciana" + i, 0, null));
    }

    public PreviewData getPreviewData(int i) {
        return getPreviewData(this.context.openOrCreateDatabase("uciana" + i, 0, null));
    }

    public void load(SQLiteDatabase sQLiteDatabase) {
        Cursor query = sQLiteDatabase.query("details", new String[]{"turn", "galaxySize", "currentPlayer", "playerStatus0", "playerStatus1", "playerStatus2", "playerStatus3", "playerStatus4", "playerStatus5", "techCostVersion", "difficulty", "timePlayed"}, null, null, null, null, null);
        int columnIndex = query.getColumnIndex("turn");
        int columnIndex2 = query.getColumnIndex("galaxySize");
        int columnIndex3 = query.getColumnIndex("currentPlayer");
        int columnIndex4 = query.getColumnIndex("playerStatus0");
        int columnIndex5 = query.getColumnIndex("playerStatus1");
        int columnIndex6 = query.getColumnIndex("playerStatus2");
        int columnIndex7 = query.getColumnIndex("playerStatus3");
        int columnIndex8 = query.getColumnIndex("playerStatus4");
        int columnIndex9 = query.getColumnIndex("playerStatus5");
        int columnIndex10 = query.getColumnIndex("techCostVersion");
        int columnIndex11 = query.getColumnIndex("difficulty");
        int columnIndex12 = query.getColumnIndex("timePlayed");
        query.moveToFirst();
        GameData.turn = query.getInt(columnIndex);
        this.game.setCurrentPlayer(query.getInt(columnIndex3));
        this.game.galaxy.setSize(GalaxySize.values()[query.getInt(columnIndex2)]);
        this.game.setPlayerTurnStatus(0, query.getInt(columnIndex4));
        this.game.setPlayerTurnStatus(1, query.getInt(columnIndex5));
        this.game.setPlayerTurnStatus(2, query.getInt(columnIndex6));
        this.game.setPlayerTurnStatus(3, query.getInt(columnIndex7));
        this.game.setPlayerTurnStatus(4, query.getInt(columnIndex8));
        this.game.setPlayerTurnStatus(5, query.getInt(columnIndex9));
        GameData.setTechCostVersion(query.getInt(columnIndex10));
        Game.setDifficulty(Difficulty.values()[query.getInt(columnIndex11)]);
        this.game.elapsedTime = query.getLong(columnIndex12);
        query.close();
    }

    public void save(SQLiteDatabase sQLiteDatabase) {
        saveDetails(sQLiteDatabase);
        saveFleetIcons(sQLiteDatabase);
        saveSystemNameDisplays(sQLiteDatabase);
    }

    public List<Nebula> getNebulas(SQLiteDatabase sQLiteDatabase) {
        ArrayList arrayList = new ArrayList();
        Cursor query = sQLiteDatabase.query("nebulas", new String[]{"id", "x", "y", "size", "type", Key.ROTATION}, null, null, null, null, null);
        int columnIndex = query.getColumnIndex("x");
        int columnIndex2 = query.getColumnIndex("y");
        int columnIndex3 = query.getColumnIndex("size");
        int columnIndex4 = query.getColumnIndex("type");
        int columnIndex5 = query.getColumnIndex(Key.ROTATION);
        query.moveToFirst();
        while (!query.isAfterLast()) {
            arrayList.add(new Nebula(new Point(query.getInt(columnIndex), query.getInt(columnIndex2)), query.getFloat(columnIndex3), NebulaType.values()[query.getInt(columnIndex4)], query.getFloat(columnIndex5)));
            query.moveToNext();
        }
        query.close();
        sQLiteDatabase.close();
        return arrayList;
    }

    public PreviewData getPreviewData(SQLiteDatabase sQLiteDatabase) {
        PreviewData previewData = new PreviewData();
        previewData.difficulty = getDifficulty(sQLiteDatabase);
        previewData.saveTimeStamp = getSaveTimeStamp(sQLiteDatabase);
        previewData.turns = getSaveTurns(sQLiteDatabase);
        previewData.playTime = getSaveTimePlayed(sQLiteDatabase);
        int saveCurrentPlayer = getSaveCurrentPlayer(sQLiteDatabase);
        previewData.currentPlayer = saveCurrentPlayer;
        previewData.bannerID = getSaveBannerID(sQLiteDatabase, saveCurrentPlayer);
        previewData.galaxySize = getGalaxySize(sQLiteDatabase);
        previewData.stars = getStars(sQLiteDatabase);
        previewData.blackholes = getBlackHoles(sQLiteDatabase);
        previewData.spaceRifts = getSpaceRifts(sQLiteDatabase);
        previewData.discoveredLocations = getDiscoveredSystemsList(sQLiteDatabase, previewData.currentPlayer);
        previewData.wormholes = getWormHoles(sQLiteDatabase);
        previewData.systemNameDisplays = getSystemNameDisplays(sQLiteDatabase);
        previewData.fleetIcons = getFleetIcons(sQLiteDatabase);
        previewData.shipStyles = getShipStyles(sQLiteDatabase);
        sQLiteDatabase.close();
        return previewData;
    }
}
