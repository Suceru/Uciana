package com.birdshel.Uciana.SaveGameData;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.constraintlayout.motion.widget.Key;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Planets.AsteroidBelt;
import com.birdshel.Uciana.Planets.Climate;
import com.birdshel.Uciana.Planets.EmptyOrbit;
import com.birdshel.Uciana.Planets.ExplorationFind;
import com.birdshel.Uciana.Planets.GasGiant;
import com.birdshel.Uciana.Planets.Gravity;
import com.birdshel.Uciana.Planets.IonStorm;
import com.birdshel.Uciana.Planets.MineralRichness;
import com.birdshel.Uciana.Planets.Moon;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.PlanetSize;
import com.birdshel.Uciana.Planets.ResourceID;
import com.birdshel.Uciana.Planets.SystemObject;
import com.birdshel.Uciana.Planets.SystemObjectType;
import com.birdshel.Uciana.StarSystems.Blackhole;
import com.birdshel.Uciana.StarSystems.Nebula;
import com.birdshel.Uciana.StarSystems.SpaceRift;
import com.birdshel.Uciana.StarSystems.StarSystem;
import com.birdshel.Uciana.StarSystems.StarType;
import com.birdshel.Uciana.StarSystems.WormholeObject;

import org.andengine.util.level.constants.LevelConstants;

import java.util.ArrayList;
import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
class GalaxyData {
    private static final String ASTEROID_BELTS = "asteroid_belts";
    private static final String GAS_GIANTS = "gas_giants";
    private static final String ION_STORMS = "ion_storms";
    private static final String PLANETS = "planets";
    private static final String RESOURCES = "resources";
    private static final String SYSTEM_NEBULAS = "system_nebulas";
    private static final String SYSTEM_OBJECTS = "system_objects";
    private static final String WORMHOLE_OBJECTS = "wormhole_objects";
    private SQLiteDatabase db;
    private final Game game;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.SaveGameData.GalaxyData$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1421a;

        static {
            int[] iArr = new int[SystemObjectType.values().length];
            f1421a = iArr;
            try {
                iArr[SystemObjectType.PLANET.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1421a[SystemObjectType.GAS_GIANT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1421a[SystemObjectType.ASTEROID_BELT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1421a[SystemObjectType.NONE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GalaxyData(Game game) {
        this.game = game;
    }

    private AsteroidBelt getAsteroidBelt(int i, int i2, int i3) {
        Cursor query = this.db.query(ASTEROID_BELTS, new String[]{"imageIndex", "richness"}, "systemID = ? and orbit = ?", new String[]{Integer.toString(i), Integer.toString(i2)}, null, null, null);
        int columnIndex = query.getColumnIndex("imageIndex");
        int columnIndex2 = query.getColumnIndex("richness");
        query.moveToFirst();
        int i4 = query.getInt(columnIndex);
        MineralRichness mineralRichness = MineralRichness.values()[query.getInt(columnIndex2)];
        query.close();
        return new AsteroidBelt.Builder().systemID(i).orbit(i2).imageIndex(i4).mineralRichness(mineralRichness).resources(getResources(i, i2)).exploredValue(i3).build();
    }

    private GasGiant getGasGiant(int i, int i2, int i3) {
        Cursor query = this.db.query(GAS_GIANTS, new String[]{"imageIndex", "ringIndex", "moonIndex", "moonSize", "planetSize", "hasRing", "hasMoon"}, "systemID = ? and orbit = ?", new String[]{Integer.toString(i), Integer.toString(i2)}, null, null, null);
        int columnIndex = query.getColumnIndex("imageIndex");
        int columnIndex2 = query.getColumnIndex("ringIndex");
        int columnIndex3 = query.getColumnIndex("moonIndex");
        int columnIndex4 = query.getColumnIndex("moonSize");
        int columnIndex5 = query.getColumnIndex("planetSize");
        int columnIndex6 = query.getColumnIndex("hasRing");
        int columnIndex7 = query.getColumnIndex("hasMoon");
        query.moveToFirst();
        int i4 = query.getInt(columnIndex);
        int i5 = query.getInt(columnIndex2);
        int i6 = query.getInt(columnIndex3);
        float f2 = query.getFloat(columnIndex4);
        PlanetSize planetSize = PlanetSize.values()[query.getInt(columnIndex5)];
        boolean z = query.getInt(columnIndex6) != 0;
        boolean z2 = query.getInt(columnIndex7) != 0;
        Moon moon = new Moon(i6, f2);
        query.close();
        return new GasGiant.Builder().systemID(i).orbit(i2).imageIndex(i4).ringImageIndex(i5).moon(moon).size(planetSize).hasRing(z).hasMoon(z2).resources(getResources(i, i2)).exploredValue(i3).build();
    }

    private IonStorm getIonStorm(int i) {
        int[] iArr = new int[2];
        Point[] pointArr = new Point[2];
        float[] fArr = new float[2];
        float[] fArr2 = new float[2];
        Cursor query = this.db.query(ION_STORMS, new String[]{"x", "y", "isVisible", "imageIndex1", "imageIndex2", "x1", "y1", "x2", "y2", "alpha1", "alpha2", "rotation1", "rotation2"}, "systemID = ?", new String[]{Integer.toString(i)}, null, null, null);
        int columnIndex = query.getColumnIndex("x");
        int columnIndex2 = query.getColumnIndex("y");
        int columnIndex3 = query.getColumnIndex("isVisible");
        int columnIndex4 = query.getColumnIndex("imageIndex1");
        int columnIndex5 = query.getColumnIndex("imageIndex2");
        int columnIndex6 = query.getColumnIndex("x1");
        int columnIndex7 = query.getColumnIndex("y1");
        int columnIndex8 = query.getColumnIndex("x2");
        int columnIndex9 = query.getColumnIndex("y2");
        int columnIndex10 = query.getColumnIndex("alpha1");
        int columnIndex11 = query.getColumnIndex("alpha2");
        int columnIndex12 = query.getColumnIndex("rotation1");
        int columnIndex13 = query.getColumnIndex("rotation2");
        query.moveToFirst();
        Point point = new Point(query.getInt(columnIndex), query.getInt(columnIndex2));
        boolean z = query.getInt(columnIndex3) == 1;
        iArr[0] = query.getInt(columnIndex4);
        iArr[1] = query.getInt(columnIndex5);
        pointArr[0] = new Point(query.getInt(columnIndex6), query.getInt(columnIndex7));
        pointArr[1] = new Point(query.getInt(columnIndex8), query.getInt(columnIndex9));
        fArr[0] = query.getFloat(columnIndex10);
        fArr[1] = query.getFloat(columnIndex11);
        fArr2[0] = query.getFloat(columnIndex12);
        fArr2[1] = query.getFloat(columnIndex13);
        query.close();
        return new IonStorm(i, point, z, iArr, fArr2, pointArr, fArr);
    }

    private Planet getPlanet(int i, int i2, int i3) {
        Cursor query = this.db.query(PLANETS, new String[]{"imageIndex", "ringIndex", "cityLightsIndex", "moonIndex", "moonSize", "climate", "terraformedClimate", "planetSize", "richness", "gravity", "isTerraformed", "hasRing", "hasMoon", "explorationFind"}, "systemID = ? and orbit = ?", new String[]{Integer.toString(i), Integer.toString(i2)}, null, null, null);
        int columnIndex = query.getColumnIndex("imageIndex");
        int columnIndex2 = query.getColumnIndex("ringIndex");
        int columnIndex3 = query.getColumnIndex("cityLightsIndex");
        int columnIndex4 = query.getColumnIndex("moonIndex");
        int columnIndex5 = query.getColumnIndex("moonSize");
        int columnIndex6 = query.getColumnIndex("climate");
        int columnIndex7 = query.getColumnIndex("terraformedClimate");
        int columnIndex8 = query.getColumnIndex("planetSize");
        int columnIndex9 = query.getColumnIndex("richness");
        int columnIndex10 = query.getColumnIndex("gravity");
        int columnIndex11 = query.getColumnIndex("isTerraformed");
        int columnIndex12 = query.getColumnIndex("hasRing");
        int columnIndex13 = query.getColumnIndex("hasMoon");
        int columnIndex14 = query.getColumnIndex("explorationFind");
        query.moveToFirst();
        int i4 = query.getInt(columnIndex);
        int i5 = query.getInt(columnIndex2);
        int i6 = query.getInt(columnIndex3);
        int i7 = query.getInt(columnIndex4);
        float f2 = query.getFloat(columnIndex5);
        Climate climate = Climate.values()[query.getInt(columnIndex6)];
        Climate climate2 = Climate.values()[query.getInt(columnIndex7)];
        PlanetSize planetSize = PlanetSize.values()[query.getInt(columnIndex8)];
        MineralRichness mineralRichness = MineralRichness.values()[query.getInt(columnIndex9)];
        Gravity gravity = Gravity.values()[query.getInt(columnIndex10)];
        boolean z = query.getInt(columnIndex11) != 0;
        boolean z2 = query.getInt(columnIndex12) != 0;
        boolean z3 = query.getInt(columnIndex13) != 0;
        Moon moon = new Moon(i7, f2);
        ExplorationFind explorationFind = ExplorationFind.values()[query.getInt(columnIndex14)];
        query.close();
        return new Planet.Builder().systemID(i).orbit(i2).imageIndex(i4).ringImageIndex(i5).cityLightIndex(i6).moon(moon).climate(climate).terraformedClimate(climate2).size(planetSize).mineralRichness(mineralRichness).gravity(gravity).isTerraformed(z).hasRing(z2).hasMoon(z3).resources(getResources(i, i2)).exploredValue(i3).explorationFind(explorationFind).build();
    }

    private List<ResourceID> getResources(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        Cursor query = this.db.query(RESOURCES, new String[]{"resourceID"}, "systemID = ? and orbit = ?", new String[]{Integer.toString(i), Integer.toString(i2)}, null, null, null);
        int columnIndex = query.getColumnIndex("resourceID");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            arrayList.add(ResourceID.values()[query.getInt(columnIndex)]);
            query.moveToNext();
        }
        query.close();
        return arrayList;
    }

    private List<Integer> getSystemNebulaList(int i) {
        ArrayList arrayList = new ArrayList();
        Cursor query = this.db.query(SYSTEM_NEBULAS, new String[]{"nebulaID"}, "systemID = ?", new String[]{Integer.toString(i)}, null, null, null);
        int columnIndex = query.getColumnIndex("nebulaID");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            arrayList.add(Integer.valueOf(query.getInt(columnIndex)));
            query.moveToNext();
        }
        query.close();
        return arrayList;
    }

    private List<SystemObject> getSystemObjects(int i) {
        ArrayList arrayList = new ArrayList();
        Cursor query = this.db.query(SYSTEM_OBJECTS, new String[]{"orbit", "objectType", "exploredValue"}, "systemID = ?", new String[]{Integer.toString(i)}, null, null, "orbit ASC");
        int columnIndex = query.getColumnIndex("orbit");
        int columnIndex2 = query.getColumnIndex("objectType");
        int columnIndex3 = query.getColumnIndex("exploredValue");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            int i2 = query.getInt(columnIndex);
            SystemObjectType systemObjectType = SystemObjectType.values()[query.getInt(columnIndex2)];
            int i3 = query.getInt(columnIndex3);
            int i4 = AnonymousClass1.f1421a[systemObjectType.ordinal()];
            if (i4 == 1) {
                arrayList.add(getPlanet(i, i2, i3));
            } else if (i4 == 2) {
                arrayList.add(getGasGiant(i, i2, i3));
            } else if (i4 == 3) {
                arrayList.add(getAsteroidBelt(i, i2, i3));
            } else if (i4 == 4) {
                arrayList.add(new EmptyOrbit.Builder(i, i2).build());
            }
            query.moveToNext();
        }
        query.close();
        return arrayList;
    }

    private List<WormholeObject> getWormholeObjects(int i) {
        ArrayList arrayList = new ArrayList();
        Cursor query = this.db.query(WORMHOLE_OBJECTS, new String[]{"x", "y", "size", "bottomSpinSpeed", "topSpinSpeed"}, "systemID = ?", new String[]{Integer.toString(i)}, null, null, null);
        int columnIndex = query.getColumnIndex("x");
        int columnIndex2 = query.getColumnIndex("y");
        int columnIndex3 = query.getColumnIndex("size");
        int columnIndex4 = query.getColumnIndex("bottomSpinSpeed");
        int columnIndex5 = query.getColumnIndex("topSpinSpeed");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            arrayList.add(new WormholeObject(new Point(query.getInt(columnIndex), query.getInt(columnIndex2)), query.getInt(columnIndex3), query.getFloat(columnIndex4), query.getFloat(columnIndex5)));
            query.moveToNext();
        }
        query.close();
        return arrayList;
    }

    private void loadBlackholes() {
        Cursor query = this.db.query("blackholes", new String[]{"id", "x", "y", "size", "spinDirection", "spinSpeed"}, null, null, null, null, null);
        int columnIndex = query.getColumnIndex("id");
        int columnIndex2 = query.getColumnIndex("x");
        int columnIndex3 = query.getColumnIndex("y");
        int columnIndex4 = query.getColumnIndex("size");
        int columnIndex5 = query.getColumnIndex("spinDirection");
        int columnIndex6 = query.getColumnIndex("spinSpeed");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            this.game.galaxy.addBlackhole(query.getInt(columnIndex), new Point(query.getInt(columnIndex2), query.getInt(columnIndex3)), query.getInt(columnIndex4), query.getInt(columnIndex5) != 0, query.getInt(columnIndex6));
            query.moveToNext();
        }
        query.close();
    }

    private void loadNebulas() {
        Cursor query = this.db.query("nebulas", new String[]{"id", "x", "y", "size", "type", Key.ROTATION}, null, null, null, null, null);
        int columnIndex = query.getColumnIndex("x");
        int columnIndex2 = query.getColumnIndex("y");
        int columnIndex3 = query.getColumnIndex("size");
        int columnIndex4 = query.getColumnIndex("type");
        int columnIndex5 = query.getColumnIndex(Key.ROTATION);
        query.moveToFirst();
        while (!query.isAfterLast()) {
            this.game.galaxy.addNebula(new Point(query.getInt(columnIndex), query.getInt(columnIndex2)), query.getFloat(columnIndex3), query.getInt(columnIndex4), query.getFloat(columnIndex5));
            query.moveToNext();
        }
        query.close();
    }

    private void loadSpaceRifts() {
        Cursor query = this.db.query("spacerifts", new String[]{"id", "x", "y", "size", "spinDirection", "spinSpeed"}, null, null, null, null, null);
        int columnIndex = query.getColumnIndex("id");
        int columnIndex2 = query.getColumnIndex("x");
        int columnIndex3 = query.getColumnIndex("y");
        int columnIndex4 = query.getColumnIndex("size");
        int columnIndex5 = query.getColumnIndex("spinDirection");
        int columnIndex6 = query.getColumnIndex("spinSpeed");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            this.game.galaxy.addSpaceRift(query.getInt(columnIndex), new Point(query.getInt(columnIndex2), query.getInt(columnIndex3)), query.getInt(columnIndex4), query.getInt(columnIndex5) != 0, query.getInt(columnIndex6));
            query.moveToNext();
        }
        query.close();
    }

    private void loadStarSystems() {
        Cursor query = this.db.query("starsystems", new String[]{"id", LevelConstants.TAG_LEVEL_ATTRIBUTE_NAME, "x", "y", "starType", "starShape", "starSize", "wormholeCount"}, null, null, null, null, null);
        int columnIndex = query.getColumnIndex("id");
        int columnIndex2 = query.getColumnIndex(LevelConstants.TAG_LEVEL_ATTRIBUTE_NAME);
        int columnIndex3 = query.getColumnIndex("x");
        int columnIndex4 = query.getColumnIndex("y");
        int columnIndex5 = query.getColumnIndex("starType");
        int columnIndex6 = query.getColumnIndex("starShape");
        int columnIndex7 = query.getColumnIndex("starSize");
        int columnIndex8 = query.getColumnIndex("wormholeCount");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            int i = query.getInt(columnIndex);
            String string = query.getString(columnIndex2);
            Point point = new Point(query.getInt(columnIndex3), query.getInt(columnIndex4));
            int i2 = query.getInt(columnIndex5);
            int i3 = query.getInt(columnIndex6);
            float f2 = query.getFloat(columnIndex7);
            int i4 = columnIndex7;
            int i5 = columnIndex;
            this.game.galaxy.addStarSystem(new StarSystem.Builder().id(i).name(string).position(point).nebulaIDs(getSystemNebulaList(i)).systemObjects(getSystemObjects(i)).starType(StarType.values()[i2]).starShape(i3).starSize(f2).wormholeCount(query.getInt(columnIndex8)).ionStorm(getIonStorm(i)).wormholeObjects(getWormholeObjects(i)).loadSystem());
            query.moveToNext();
            columnIndex7 = i4;
            columnIndex = i5;
            columnIndex2 = columnIndex2;
            columnIndex3 = columnIndex3;
        }
        query.close();
    }

    private void loadWormHoles() {
        Cursor query = this.db.query("wormholes", new String[]{"system1", "system2"}, null, null, null, null, null);
        int columnIndex = query.getColumnIndex("system1");
        int columnIndex2 = query.getColumnIndex("system2");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            this.game.galaxy.setWormhole(query.getInt(columnIndex), query.getInt(columnIndex2));
            query.moveToNext();
        }
        query.close();
    }

    private void saveAsteroidBelt(AsteroidBelt asteroidBelt) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("systemID", Integer.valueOf(asteroidBelt.getSystemID()));
        contentValues.put("orbit", Integer.valueOf(asteroidBelt.getOrbit()));
        contentValues.put("imageIndex", Integer.valueOf(asteroidBelt.getImageIndex()));
        contentValues.put("richness", Integer.valueOf(asteroidBelt.getMineralRichness().ordinal()));
        this.db.insert(ASTEROID_BELTS, null, contentValues);
    }

    private void saveBlackholes() {
        this.db.beginTransaction();
        for (Blackhole blackhole : this.game.galaxy.getBlackholes()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", Integer.valueOf(blackhole.getID()));
            contentValues.put("x", Float.valueOf(blackhole.getPosition().getX()));
            contentValues.put("y", Float.valueOf(blackhole.getPosition().getY()));
            contentValues.put("size", Float.valueOf(blackhole.getUnmodifiedSize()));
            contentValues.put("spinDirection", Integer.valueOf(blackhole.hasAltSpinDirection() ? 1 : 0));
            contentValues.put("spinSpeed", Float.valueOf(blackhole.getSpinSpeed()));
            this.db.insert("blackholes", null, contentValues);
        }
        this.db.setTransactionSuccessful();
        this.db.endTransaction();
    }

    private void saveGasGiant(GasGiant gasGiant) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("systemID", Integer.valueOf(gasGiant.getSystemID()));
        contentValues.put("orbit", Integer.valueOf(gasGiant.getOrbit()));
        contentValues.put("imageIndex", Integer.valueOf(gasGiant.getImageIndex()));
        contentValues.put("ringIndex", Integer.valueOf(gasGiant.getRingImageIndex()));
        contentValues.put("moonIndex", Integer.valueOf(gasGiant.getMoon().getImageIndex()));
        contentValues.put("moonSize", Float.valueOf(gasGiant.getMoon().getSize()));
        contentValues.put("planetSize", Integer.valueOf(gasGiant.getSize().ordinal()));
        contentValues.put("hasRing", Integer.valueOf(gasGiant.hasRing() ? 1 : 0));
        contentValues.put("hasMoon", Integer.valueOf(gasGiant.hasMoon() ? 1 : 0));
        this.db.insert(GAS_GIANTS, null, contentValues);
    }

    private void saveIonStorm(IonStorm ionStorm) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("systemID", Integer.valueOf(ionStorm.getSystemID()));
        contentValues.put("isVisible", Integer.valueOf(ionStorm.isVisible() ? 1 : 0));
        contentValues.put("x", Float.valueOf(ionStorm.getPosition().getX()));
        contentValues.put("y", Float.valueOf(ionStorm.getPosition().getX()));
        contentValues.put("imageIndex1", Integer.valueOf(ionStorm.getLayer(0)));
        contentValues.put("imageIndex2", Integer.valueOf(ionStorm.getLayer(1)));
        contentValues.put("x1", Float.valueOf(ionStorm.getPositionOfCloud(0).getX()));
        contentValues.put("y1", Float.valueOf(ionStorm.getPositionOfCloud(0).getY()));
        contentValues.put("x2", Float.valueOf(ionStorm.getPositionOfCloud(1).getX()));
        contentValues.put("y2", Float.valueOf(ionStorm.getPositionOfCloud(1).getY()));
        contentValues.put("alpha1", Float.valueOf(ionStorm.getAlpha(0)));
        contentValues.put("alpha2", Float.valueOf(ionStorm.getAlpha(1)));
        contentValues.put("rotation1", Float.valueOf(ionStorm.getRotation(0)));
        contentValues.put("rotation2", Float.valueOf(ionStorm.getRotation(1)));
        this.db.insert(ION_STORMS, null, contentValues);
    }

    private void saveNebulas() {
        this.db.beginTransaction();
        int i = 0;
        for (Nebula nebula : this.game.galaxy.getNebulas()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", Integer.valueOf(i));
            contentValues.put("x", Float.valueOf(nebula.getPosition().getX()));
            contentValues.put("y", Float.valueOf(nebula.getPosition().getY()));
            contentValues.put("size", Float.valueOf(nebula.getSizeModifier()));
            contentValues.put("type", Integer.valueOf(nebula.getNebulaType().ordinal()));
            contentValues.put(Key.ROTATION, Float.valueOf(nebula.getRotation()));
            this.db.insert("nebulas", null, contentValues);
            i++;
        }
        this.db.setTransactionSuccessful();
        this.db.endTransaction();
    }

    private void savePlanet(Planet planet) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("systemID", Integer.valueOf(planet.getSystemID()));
        contentValues.put("orbit", Integer.valueOf(planet.getOrbit()));
        contentValues.put("imageIndex", Integer.valueOf(planet.getImageIndex()));
        contentValues.put("ringIndex", Integer.valueOf(planet.getRingImageIndex()));
        contentValues.put("cityLightsIndex", Integer.valueOf(planet.getCityLightIndex()));
        contentValues.put("moonIndex", Integer.valueOf(planet.getMoon().getImageIndex()));
        contentValues.put("moonSize", Float.valueOf(planet.getMoon().getSize()));
        contentValues.put("climate", Integer.valueOf(planet.getPreTerraformedClimate().ordinal()));
        contentValues.put("terraformedClimate", Integer.valueOf(planet.getTerraformedClimate().ordinal()));
        contentValues.put("planetSize", Integer.valueOf(planet.getSize().ordinal()));
        contentValues.put("richness", Integer.valueOf(planet.getMineralRichness().ordinal()));
        contentValues.put("gravity", Integer.valueOf(planet.getUnmodifiedGravity().ordinal()));
        contentValues.put("isTerraformed", Integer.valueOf(planet.isTerraformed() ? 1 : 0));
        contentValues.put("explorationFind", Integer.valueOf(planet.getExplorationFind().ordinal()));
        contentValues.put("hasRing", Integer.valueOf(planet.hasRing() ? 1 : 0));
        contentValues.put("hasMoon", Integer.valueOf(planet.hasMoon() ? 1 : 0));
        this.db.insert(PLANETS, null, contentValues);
    }

    private void saveSpaceRifts() {
        this.db.beginTransaction();
        for (SpaceRift spaceRift : this.game.galaxy.getSpaceRifts()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", Integer.valueOf(spaceRift.getID()));
            contentValues.put("x", Float.valueOf(spaceRift.getPosition().getX()));
            contentValues.put("y", Float.valueOf(spaceRift.getPosition().getY()));
            contentValues.put("size", Float.valueOf(spaceRift.getUnmodifiedSize()));
            contentValues.put("spinDirection", Integer.valueOf(spaceRift.hasAltSpinDirection() ? 1 : 0));
            contentValues.put("spinSpeed", Float.valueOf(spaceRift.getSpinSpeed()));
            this.db.insert("spacerifts", null, contentValues);
        }
        this.db.setTransactionSuccessful();
        this.db.endTransaction();
    }

    private void saveStarSystems() {
        this.db.beginTransaction();
        for (StarSystem starSystem : this.game.galaxy.getStarSystems()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", Integer.valueOf(starSystem.getID()));
            contentValues.put(LevelConstants.TAG_LEVEL_ATTRIBUTE_NAME, starSystem.getName());
            contentValues.put("x", Float.valueOf(starSystem.getPosition().getX()));
            contentValues.put("y", Float.valueOf(starSystem.getPosition().getY()));
            contentValues.put("starType", Integer.valueOf(starSystem.getStarType().ordinal()));
            contentValues.put("starShape", Integer.valueOf(starSystem.getStarShape()));
            contentValues.put("starSize", Float.valueOf(starSystem.getUnmodifiedStarSize()));
            contentValues.put("wormholeCount", Integer.valueOf(starSystem.getWormholeCount()));
            this.db.insert("starsystems", null, contentValues);
            for (Integer num : starSystem.getNebulaIDs()) {
                int intValue = num.intValue();
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("nebulaID", Integer.valueOf(intValue));
                contentValues2.put("systemID", Integer.valueOf(starSystem.getID()));
                this.db.insert(SYSTEM_NEBULAS, null, contentValues2);
            }
            for (SystemObject systemObject : starSystem.getSystemObjects()) {
                ContentValues contentValues3 = new ContentValues();
                contentValues3.put("systemID", Integer.valueOf(starSystem.getID()));
                contentValues3.put("orbit", Integer.valueOf(systemObject.getOrbit()));
                contentValues3.put("objectType", Integer.valueOf(systemObject.getSystemObjectType().ordinal()));
                contentValues3.put("exploredValue", Integer.valueOf(systemObject.getExploredValue()));
                this.db.insert(SYSTEM_OBJECTS, null, contentValues3);
                for (ResourceID resourceID : systemObject.getResources()) {
                    ContentValues contentValues4 = new ContentValues();
                    contentValues4.put("resourceID", Integer.valueOf(resourceID.ordinal()));
                    contentValues4.put("systemID", Integer.valueOf(systemObject.getSystemID()));
                    contentValues4.put("orbit", Integer.valueOf(systemObject.getOrbit()));
                    this.db.insert(RESOURCES, null, contentValues4);
                }
                int i = AnonymousClass1.f1421a[systemObject.getSystemObjectType().ordinal()];
                if (i == 1) {
                    savePlanet((Planet) systemObject);
                } else if (i == 2) {
                    saveGasGiant((GasGiant) systemObject);
                } else if (i == 3) {
                    saveAsteroidBelt((AsteroidBelt) systemObject);
                }
            }
            saveIonStorm(starSystem.getIonStorm());
            for (WormholeObject wormholeObject : starSystem.getWormholeObjects()) {
                ContentValues contentValues5 = new ContentValues();
                contentValues5.put("systemID", Integer.valueOf(starSystem.getID()));
                contentValues5.put("x", Float.valueOf(wormholeObject.getPosition().getX()));
                contentValues5.put("y", Float.valueOf(wormholeObject.getPosition().getY()));
                contentValues5.put("size", Integer.valueOf(wormholeObject.getSize()));
                contentValues5.put("bottomSpinSpeed", Float.valueOf(wormholeObject.getBottomSpinSpeed()));
                contentValues5.put("topSpinSpeed", Float.valueOf(wormholeObject.getTopSpinSpeed()));
                this.db.insert(WORMHOLE_OBJECTS, null, contentValues5);
            }
        }
        this.db.setTransactionSuccessful();
        this.db.endTransaction();
    }

    private void saveWormholes() {
        this.db.beginTransaction();
        int i = 0;
        for (Point point : this.game.galaxy.getWormholes()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", Integer.valueOf(i));
            contentValues.put("system1", Float.valueOf(point.getX()));
            contentValues.put("system2", Float.valueOf(point.getY()));
            this.db.insert("wormholes", null, contentValues);
            i++;
        }
        this.db.setTransactionSuccessful();
        this.db.endTransaction();
    }

    public void load(SQLiteDatabase sQLiteDatabase) {
        this.db = sQLiteDatabase;
        this.game.galaxy.clear();
        loadNebulas();
        loadStarSystems();
        loadWormHoles();
        loadBlackholes();
        loadSpaceRifts();
        this.game.galaxy.setDistanceMatrix();
    }

    public void save(SQLiteDatabase sQLiteDatabase) {
        this.db = sQLiteDatabase;
        saveNebulas();
        saveStarSystems();
        saveWormholes();
        saveBlackholes();
        saveSpaceRifts();
    }
}
