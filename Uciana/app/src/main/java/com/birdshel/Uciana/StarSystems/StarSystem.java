package com.birdshel.Uciana.StarSystems;

import androidx.core.view.PointerIconCompat;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Planets.AsteroidBelt;
import com.birdshel.Uciana.Planets.EmptyOrbit;
import com.birdshel.Uciana.Planets.GasGiant;
import com.birdshel.Uciana.Planets.IonStorm;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.SystemObject;
import com.birdshel.Uciana.Planets.SystemObjectType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class StarSystem {
    private Point cometPosition;
    private int cometType;
    private final int id;
    private final IonStorm ionStorm;
    private boolean isCometVisible = false;
    private String name;
    private final List<Integer> nebulaIDs;
    private final Point position;
    private final int starShape;
    private final float starSize;
    private final StarType starType;
    private final List<SystemObject> systemObjects;
    private int wormholeCount;
    private final List<WormholeObject> wormholeObjects;

    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.StarSystems.StarSystem$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a */
        static final /* synthetic */ int[] f1571a;

        static {
            int[] iArr = new int[SystemObjectType.values().length];
            f1571a = iArr;
            try {
                iArr[SystemObjectType.PLANET.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1571a[SystemObjectType.GAS_GIANT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1571a[SystemObjectType.ASTEROID_BELT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* compiled from: MyApplication */
    /* loaded from: classes.dex */
    public static class Builder {
        private int id;
        private IonStorm ionStorm;
        private String name;
        private List<Integer> nebulaIDs;
        private Point position;
        private int starShape;
        private float starSize;
        private StarType starType;
        private List<SystemObject> systemObjects;
        private int wormholeCount;
        private List<WormholeObject> wormholeObjects;

        public StarSystem buildNewSystem() {
            HashMap hashMap = new HashMap();
            hashMap.put(StarType.BLUE, 10);
            hashMap.put(StarType.WHITE, 10);
            hashMap.put(StarType.YELLOW, 27);
            hashMap.put(StarType.ORANGE, 20);
            hashMap.put(StarType.RED, 28);
            hashMap.put(StarType.BROWN, 5);
            this.starType = (StarType) Functions.getItemByPercent(hashMap);
            HashMap hashMap2 = new HashMap();
            hashMap2.put(0, 34);
            hashMap2.put(1, 33);
            hashMap2.put(2, 33);
            this.starShape = ((Integer) Functions.getItemByPercent(hashMap2)).intValue();
            this.starSize = (Functions.random.nextInt(50) + 75.0f) / 100.0f;
            this.systemObjects = new ArrayList();
            for (int i = 0; i < 5; i++) {
                if (Functions.percent(this.starType.getSystemObjectPercent())) {
                    int i2 = AnonymousClass1.f1571a[SystemObjectType.getSystemObjectType().ordinal()];
                    if (i2 == 1) {
                        this.systemObjects.add(new Planet.Builder().systemID(this.id).orbit(i).buildNewWorld(this.starType));
                    } else if (i2 == 2) {
                        this.systemObjects.add(new GasGiant.Builder().systemID(this.id).orbit(i).buildNew());
                    } else if (i2 == 3) {
                        this.systemObjects.add(new AsteroidBelt.Builder().systemID(this.id).orbit(i).buildNew(this.starType));
                    }
                } else {
                    this.systemObjects.add(new EmptyOrbit.Builder(this.id, i).build());
                }
            }
            this.ionStorm = new IonStorm(this.id);
            this.wormholeObjects = new ArrayList();
            this.wormholeCount = 0;
            return new StarSystem(this);
        }

        public Builder id(int i) {
            this.id = i;
            return this;
        }

        public Builder ionStorm(IonStorm ionStorm) {
            this.ionStorm = ionStorm;
            return this;
        }

        public StarSystem loadSystem() {
            return new StarSystem(this);
        }

        public Builder name(String str) {
            this.name = str;
            return this;
        }

        public Builder nebulaIDs(List<Integer> list) {
            this.nebulaIDs = list;
            return this;
        }

        public Builder position(Point point) {
            this.position = point;
            return this;
        }

        public Builder starShape(int i) {
            this.starShape = i;
            return this;
        }

        public Builder starSize(float f2) {
            this.starSize = f2;
            return this;
        }

        public Builder starType(StarType starType) {
            this.starType = starType;
            return this;
        }

        public Builder systemObjects(List<SystemObject> list) {
            this.systemObjects = list;
            return this;
        }

        public Builder wormholeCount(int i) {
            this.wormholeCount = i;
            return this;
        }

        public Builder wormholeObjects(List<WormholeObject> list) {
            this.wormholeObjects = list;
            return this;
        }
    }

    public StarSystem(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.position = builder.position;
        this.starType = builder.starType;
        this.starShape = builder.starShape;
        this.starSize = builder.starSize;
        this.nebulaIDs = builder.nebulaIDs;
        this.systemObjects = builder.systemObjects;
        this.wormholeCount = builder.wormholeCount;
        this.ionStorm = builder.ionStorm;
        this.wormholeObjects = builder.wormholeObjects;
    }

    private boolean checkWormholePosition(Point point) {
        for (WormholeObject wormholeObject : this.wormholeObjects) {
            if (point.getX() > wormholeObject.getPosition().getX() && point.getX() < wormholeObject.getPosition().getX() + 100.0f) {
                return true;
            }
        }
        return false;
    }

    public void a() {
        this.wormholeCount++;
        Point point = new Point(0.0f, Functions.random.nextInt(85) + 40);
        do {
            point.setX(Functions.random.nextInt(PointerIconCompat.TYPE_GRAB) + 160);
        } while (checkWormholePosition(point));
        this.wormholeObjects.add(new WormholeObject(point));
    }

    public void b() {
        this.wormholeCount--;
        this.wormholeObjects.remove(0);
    }

    public int getAsteroidProductionBonus(int i) {
        int i2 = 0;
        for (SystemObject systemObject : this.systemObjects) {
            if (systemObject.isAsteroidBelt() && systemObject.hasOutpost() && systemObject.getOutpost().getEmpireID() == i) {
                i2 += ((AsteroidBelt) systemObject).getMineralRichness().getAsteroidBonusPercent();
            }
        }
        return i2;
    }

    public Point getCometPosition() {
        return this.cometPosition;
    }

    public int getCometType() {
        return this.cometType;
    }

    public int getID() {
        return this.id;
    }

    public IonStorm getIonStorm() {
        return this.ionStorm;
    }

    public String getName() {
        return this.name;
    }

    public List<Integer> getNebulaIDs() {
        return this.nebulaIDs;
    }

    public int getOccupier(int i) {
        SystemObject systemObject = getSystemObject(i);
        if (systemObject.hasColony()) {
            return systemObject.getColony().getEmpireID();
        }
        if (systemObject.hasOutpost()) {
            return systemObject.getOutpost().getEmpireID();
        }
        return -1;
    }

    public Point getPosition() {
        return this.position;
    }

    public int getSciencePercentBonus(int i) {
        int i2 = 0;
        for (SystemObject systemObject : this.systemObjects) {
            if (systemObject.isGasGiant() && systemObject.hasOutpost() && systemObject.getOutpost().getEmpireID() == i) {
                i2 += ((GasGiant) systemObject).getSciencePercentBonus();
            }
        }
        return i2;
    }

    public int getStarShape() {
        return this.starShape;
    }

    public float getStarSize() {
        return this.starSize * GameData.galaxy.getSize().getSizeModifier();
    }

    public StarType getStarType() {
        return this.starType;
    }

    public SystemObject getSystemObject(int i) {
        return this.systemObjects.get(i);
    }

    public List<SystemObject> getSystemObjects() {
        return this.systemObjects;
    }

    public float getUnmodifiedStarSize() {
        return this.starSize;
    }

    public int getWormholeCount() {
        return this.wormholeCount;
    }

    public List<WormholeObject> getWormholeObjects() {
        return this.wormholeObjects;
    }

    public boolean hasWormholes() {
        return this.wormholeCount > 0;
    }

    public boolean isCometVisible() {
        return this.isCometVisible;
    }

    public boolean isOccupied(int i) {
        SystemObject systemObject = getSystemObject(i);
        return systemObject.hasColony() || systemObject.hasOutpost();
    }

    public boolean isOrbitSpecial(int i) {
        if (getSystemObject(i).getSystemObjectType() == SystemObjectType.PLANET) {
            return ((Planet) getSystemObject(i)).getClimate().isSpecial();
        }
        return false;
    }

    public void setCometPosition(Point point) {
        this.cometPosition = point;
    }

    public void setCometType(int i) {
        this.cometType = i;
    }

    public void setCometVisibility(boolean z) {
        this.isCometVisible = z;
    }

    public void setName(String str) {
        this.name = str;
    }
}
