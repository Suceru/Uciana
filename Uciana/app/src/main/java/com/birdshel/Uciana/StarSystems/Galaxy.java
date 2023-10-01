package com.birdshel.Uciana.StarSystems;

import android.app.Activity;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Planets.Climate;
import com.birdshel.Uciana.Planets.ExplorationFind;
import com.birdshel.Uciana.Planets.Gravity;
import com.birdshel.Uciana.Planets.MineralRichness;
import com.birdshel.Uciana.Planets.Moon;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.PlanetSize;
import com.birdshel.Uciana.Planets.ResourceID;
import com.birdshel.Uciana.Planets.Resources;
import com.birdshel.Uciana.Planets.SystemObject;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.OptionID;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Galaxy {
    private static final int BLACKHOLE = 1;
    private static final int SPACE_RIFT = 2;
    private static final int STAR = 0;
    private final Game game;
    private GalaxySize size;
    private final String[] starNames = new String[60];
    private final List<StarSystem> starSystems = new ArrayList();
    private final List<Nebula> nebulas = new ArrayList();
    private final List<Point> wormholes = new ArrayList();
    private final List<Blackhole> blackholes = new ArrayList();
    private final List<SpaceRift> spaceRifts = new ArrayList();
    private final int[][] distance = (int[][]) Array.newInstance(int.class, 60, 60);
    private final List<List<Integer>> starsInSector = new ArrayList();

    public Galaxy(Game game) {
        this.game = game;
    }

    private void addStarToASector(StarSystem starSystem) {
        int minX = (getSize().getMinX() + getSize().getMaxX()) / 3;
        if (starSystem.getPosition().getY() < (getSize().getMinY() + getSize().getMaxY()) / 2.0f) {
            float f2 = minX;
            if (starSystem.getPosition().getX() < f2) {
                this.starsInSector.get(0).add(Integer.valueOf(starSystem.getID()));
                return;
            } else if (starSystem.getPosition().getX() >= f2 && starSystem.getPosition().getX() < minX * 2) {
                this.starsInSector.get(1).add(Integer.valueOf(starSystem.getID()));
                return;
            } else {
                this.starsInSector.get(2).add(Integer.valueOf(starSystem.getID()));
                return;
            }
        }
        float f3 = minX;
        if (starSystem.getPosition().getX() < f3) {
            this.starsInSector.get(3).add(Integer.valueOf(starSystem.getID()));
        } else if (starSystem.getPosition().getX() >= f3 && starSystem.getPosition().getX() < minX * 2) {
            this.starsInSector.get(4).add(Integer.valueOf(starSystem.getID()));
        } else {
            this.starsInSector.get(5).add(Integer.valueOf(starSystem.getID()));
        }
    }

    private List<Integer> checkForNebulas(Point point) {
        ArrayList arrayList = new ArrayList();
        Point point2 = new Point(point.getX() + 15.0f, point.getY() + 15.0f);
        int i = 0;
        for (Nebula nebula : this.nebulas) {
            if (point2.getX() > nebula.getPosition().getX() && point2.getY() > nebula.getPosition().getY() && point2.getX() < nebula.getPosition().getX() + nebula.getSize() && point2.getY() < nebula.getPosition().getY() + nebula.getSize()) {
                arrayList.add(Integer.valueOf(i));
            }
            i++;
        }
        return arrayList;
    }

    private boolean checkPosition(Point point) {
        float distanceCheckModifier = this.size.getDistanceCheckModifier() * 80.0f;
        Iterator<StarSystem> it = this.starSystems.iterator();
        while (it.hasNext()) {
            if (point.getDistance(it.next().getPosition()) < distanceCheckModifier) {
                return false;
            }
        }
        Iterator<Blackhole> it2 = this.blackholes.iterator();
        while (it2.hasNext()) {
            if (point.getDistance(it2.next().getPosition()) < distanceCheckModifier) {
                return false;
            }
        }
        Iterator<SpaceRift> it3 = this.spaceRifts.iterator();
        while (it3.hasNext()) {
            if (point.getDistance(it3.next().getPosition()) < distanceCheckModifier) {
                return false;
            }
        }
        return true;
    }

    private List<Integer> getEmpireCluster(int i, List<Integer> list, int i2) {
        Stack stack = new Stack();
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        stack.push(Integer.valueOf(i));
        while (!stack.isEmpty()) {
            int intValue = ((Integer) stack.pop()).intValue();
            for (Integer num : list) {
                int intValue2 = num.intValue();
                if (getDistance(intValue2, intValue) <= i2 * 2) {
                    if (hashSet.add(Integer.valueOf(intValue2))) {
                        stack.push(Integer.valueOf(intValue2));
                    }
                    hashSet2.add(Integer.valueOf(intValue2));
                }
            }
        }
        return new ArrayList(hashSet2);
    }

    private Point getNonSpecialLocation() {
        for (int i = 0; i < 100; i++) {
            int nextInt = Functions.random.nextInt(this.starSystems.size());
            int nextInt2 = Functions.random.nextInt(5);
            SystemObject systemObject = this.starSystems.get(nextInt).getSystemObject(nextInt2);
            if (!systemObject.isPlanet() || !((Planet) systemObject).getClimate().isSpecial()) {
                return new Point(nextInt, nextInt2);
            }
        }
        return new Point(-1.0f, -1.0f);
    }

    private String getSystemName(int i, String str) {
        return Game.modValues.getSystemName(i) != null ? Game.modValues.getSystemName(i) : str;
    }

    public static /* synthetic */ int lambda$sortPlanets$0(Planet planet, Planet planet2) {
        return planet.getName().compareToIgnoreCase(planet2.getName());
    }

    private void setBrokenWorld() {
        Point nonSpecialLocation = getNonSpecialLocation();
        if (nonSpecialLocation.getX() == -1.0f) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(ResourceID.ADVANCED_RUINS);
        Planet.Builder orbit = new Planet.Builder().systemID((int) nonSpecialLocation.getX()).orbit((int) nonSpecialLocation.getY());
        Climate climate = Climate.BROKEN;
        this.starSystems.get((int) nonSpecialLocation.getX()).getSystemObjects().set((int) nonSpecialLocation.getY(), orbit.climate(climate).size(PlanetSize.SMALL).terraformedClimate(climate).mineralRichness(MineralRichness.POOR).gravity(Gravity.HIGH).isTerraformed(true).hasRing(false).hasMoon(false).moon(new Moon()).resources((List<ResourceID>) arrayList).explorationFind(ExplorationFind.TECH_DISCOVERY).exploredValue(0).build());
    }

    private void setPerfectWorld() {
        Point nonSpecialLocation = getNonSpecialLocation();
        if (nonSpecialLocation.getX() == -1.0f) {
            return;
        }
        Climate climate = Climate.GAIA;
        Climate climate2 = Climate.SENTIENT;
        List<ResourceID> resourcesForPlanet = Resources.getResourcesForPlanet(climate, climate2);
        this.starSystems.get((int) nonSpecialLocation.getX()).getSystemObjects().set((int) nonSpecialLocation.getY(), new Planet.Builder().systemID((int) nonSpecialLocation.getX()).orbit((int) nonSpecialLocation.getY()).climate(climate).size(PlanetSize.EXTRA_LARGE).terraformedClimate(climate2).mineralRichness(MineralRichness.VERY_RICH).gravity(Gravity.NORMAL).isTerraformed(false).hasRing(Functions.percent(50)).hasMoon(Functions.percent(50)).moon(new Moon()).resources(resourcesForPlanet).explorationFind(ExplorationFind.getExplorationFind(climate, resourcesForPlanet)).exploredValue(0).build());
    }

    private void setPollutedWorld() {
        Point nonSpecialLocation = getNonSpecialLocation();
        if (nonSpecialLocation.getX() == -1.0f) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(ResourceID.ADVANCED_RUINS);
        Planet.Builder orbit = new Planet.Builder().systemID((int) nonSpecialLocation.getX()).orbit((int) nonSpecialLocation.getY());
        Climate climate = Climate.POLLUTED;
        this.starSystems.get((int) nonSpecialLocation.getX()).getSystemObjects().set((int) nonSpecialLocation.getY(), orbit.climate(climate).size(PlanetSize.LARGE).terraformedClimate(climate).mineralRichness(MineralRichness.VERY_POOR).gravity(Gravity.NORMAL).isTerraformed(true).hasRing(false).hasMoon(false).moon(new Moon()).resources((List<ResourceID>) arrayList).explorationFind(ExplorationFind.TECH_DISCOVERY).exploredValue(0).build());
    }

    private void setRingWorld() {
        Point nonSpecialLocation = getNonSpecialLocation();
        if (nonSpecialLocation.getX() == -1.0f) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(ResourceID.GOLD);
        arrayList.add(ResourceID.PLATINUM);
        arrayList.add(ResourceID.SILVER);
        arrayList.add(ResourceID.COZIURIUM);
        Planet.Builder orbit = new Planet.Builder().systemID((int) nonSpecialLocation.getX()).orbit((int) nonSpecialLocation.getY());
        Climate climate = Climate.RING;
        this.starSystems.get((int) nonSpecialLocation.getX()).getSystemObjects().set((int) nonSpecialLocation.getY(), orbit.climate(climate).size(PlanetSize.SMALL).terraformedClimate(climate).mineralRichness(MineralRichness.RICH).gravity(Gravity.NORMAL).isTerraformed(true).hasRing(false).hasMoon(false).moon(new Moon()).resources((List<ResourceID>) arrayList).explorationFind(ExplorationFind.getExplorationFind(climate, arrayList)).exploredValue(0).build());
    }

    private void setStarNames() {
        Activity activity = GameData.activity;
        this.starNames[0] = getSystemName(0, activity.getString(R.string.system_name_alpha));
        this.starNames[1] = getSystemName(1, activity.getString(R.string.system_name_aquarius));
        this.starNames[2] = getSystemName(2, activity.getString(R.string.system_name_aquila));
        this.starNames[3] = getSystemName(3, activity.getString(R.string.system_name_aries));
        this.starNames[4] = getSystemName(4, activity.getString(R.string.system_name_auriga));
        this.starNames[5] = getSystemName(5, activity.getString(R.string.system_name_austrini));
        this.starNames[6] = getSystemName(6, activity.getString(R.string.system_name_beta));
        this.starNames[7] = getSystemName(7, activity.getString(R.string.system_name_caelum));
        this.starNames[8] = getSystemName(8, activity.getString(R.string.system_name_carina));
        this.starNames[9] = getSystemName(9, activity.getString(R.string.system_name_cassiopeia));
        this.starNames[10] = getSystemName(10, activity.getString(R.string.system_name_castor));
        this.starNames[11] = getSystemName(11, activity.getString(R.string.system_name_centauri));
        this.starNames[12] = getSystemName(12, activity.getString(R.string.system_name_centaurus));
        this.starNames[13] = getSystemName(13, activity.getString(R.string.system_name_ceres));
        this.starNames[14] = getSystemName(14, activity.getString(R.string.system_name_ceti));
        this.starNames[15] = getSystemName(15, activity.getString(R.string.system_name_cetus));
        this.starNames[16] = getSystemName(16, activity.getString(R.string.system_name_collinder));
        this.starNames[17] = getSystemName(17, activity.getString(R.string.system_name_corvus));
        this.starNames[18] = getSystemName(18, activity.getString(R.string.system_name_cygnus));
        this.starNames[19] = getSystemName(19, activity.getString(R.string.system_name_delta));
        this.starNames[20] = getSystemName(20, activity.getString(R.string.system_name_denebola));
        this.starNames[21] = getSystemName(21, activity.getString(R.string.system_name_epsilon));
        this.starNames[22] = getSystemName(22, activity.getString(R.string.system_name_eridani));
        this.starNames[23] = getSystemName(23, activity.getString(R.string.system_name_eridanus));
        this.starNames[24] = getSystemName(24, activity.getString(R.string.system_name_eris));
        this.starNames[25] = getSystemName(25, activity.getString(R.string.system_name_gamma));
        this.starNames[26] = getSystemName(26, activity.getString(R.string.system_name_gemini));
        this.starNames[27] = getSystemName(27, activity.getString(R.string.system_name_gliese));
        this.starNames[28] = getSystemName(28, activity.getString(R.string.system_name_haro));
        this.starNames[29] = getSystemName(29, activity.getString(R.string.system_name_herculis));
        this.starNames[30] = getSystemName(30, activity.getString(R.string.system_name_hydri));
        this.starNames[31] = getSystemName(31, activity.getString(R.string.system_name_iota));
        this.starNames[32] = getSystemName(32, activity.getString(R.string.system_name_omega));
        this.starNames[33] = getSystemName(33, activity.getString(R.string.system_name_lambda));
        this.starNames[34] = getSystemName(34, activity.getString(R.string.system_name_luyten));
        this.starNames[35] = getSystemName(35, activity.getString(R.string.system_name_lynga));
        this.starNames[36] = getSystemName(36, activity.getString(R.string.system_name_mensae));
        this.starNames[37] = getSystemName(37, activity.getString(R.string.system_name_norma));
        this.starNames[38] = getSystemName(38, activity.getString(R.string.system_name_octans));
        this.starNames[39] = getSystemName(39, activity.getString(R.string.system_name_omicron));
        this.starNames[40] = getSystemName(40, activity.getString(R.string.system_name_trion));
        this.starNames[41] = getSystemName(41, activity.getString(R.string.system_name_orionis));
        this.starNames[42] = getSystemName(42, activity.getString(R.string.system_name_pegasi));
        this.starNames[43] = getSystemName(43, activity.getString(R.string.system_name_perseus));
        this.starNames[44] = getSystemName(44, activity.getString(R.string.system_name_piscis));
        this.starNames[45] = getSystemName(45, activity.getString(R.string.system_name_piscium));
        this.starNames[46] = getSystemName(46, activity.getString(R.string.system_name_pleiades));
        this.starNames[47] = getSystemName(47, activity.getString(R.string.system_name_pollux));
        this.starNames[48] = getSystemName(48, activity.getString(R.string.system_name_reticuli));
        this.starNames[49] = getSystemName(49, activity.getString(R.string.system_name_sagittae));
        this.starNames[50] = getSystemName(50, activity.getString(R.string.system_name_scutum));
        this.starNames[51] = getSystemName(51, activity.getString(R.string.system_name_serpentis));
        this.starNames[52] = getSystemName(52, activity.getString(R.string.system_name_sigma));
        this.starNames[53] = getSystemName(53, activity.getString(R.string.system_name_sirius));
        this.starNames[54] = getSystemName(54, activity.getString(R.string.system_name_terzan));
        this.starNames[55] = getSystemName(55, activity.getString(R.string.system_name_trianguli));
        this.starNames[56] = getSystemName(56, activity.getString(R.string.system_name_ursae));
        this.starNames[57] = getSystemName(57, activity.getString(R.string.system_name_zeta));
        this.starNames[58] = getSystemName(58, activity.getString(R.string.system_name_gruis));
        this.starNames[59] = getSystemName(59, activity.getString(R.string.system_name_arcturus));
    }

    private void setUniqueWorlds() {
        setBrokenWorld();
        setRingWorld();
        setPollutedWorld();
    }

    private void setupNebula() {
        int nextInt = Functions.random.nextInt(6) + 2;
        for (int i = 0; i < nextInt; i++) {
            int nextInt2 = Functions.random.nextInt(1100);
            this.nebulas.add(new Nebula(new Point(nextInt2, Functions.random.nextInt(600)), Functions.random.nextFloat() + 0.5f, NebulaType.getRandom(), Functions.random.nextInt(4) * 90));
        }
    }

    private void setupStars() {
        boolean z;
        int i;
        setStarNames();
        int option = (int) Game.options.getOption(OptionID.BLACKHOLES_LEVEL, 10.0f);
        int round = Math.round(this.size.getAverageNumberOfStars() * (option / 100.0f)) + this.size.ordinal() + 1;
        int option2 = (int) Game.options.getOption(OptionID.SPACE_RIFTS_LEVEL, 10.0f);
        int round2 = Math.round(this.size.getAverageNumberOfStars() * (option2 / 100.0f)) + this.size.ordinal() + 1;
        HashMap hashMap = new HashMap();
        hashMap.put(0, Integer.valueOf((100 - option) - option2));
        hashMap.put(1, Integer.valueOf(option));
        hashMap.put(2, Integer.valueOf(option2));
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < 50; i7++) {
            int i8 = 0;
            while (true) {
                if (i8 >= 500) {
                    z = false;
                    break;
                }
                Point point = new Point(Functions.random.nextInt(this.size.getMaxX() - this.size.getMinX()) + this.size.getMinX(), Functions.random.nextInt(this.size.getMaxY() - this.size.getMinY()) + this.size.getMinY());
                if (checkPosition(point)) {
                    int intValue = ((Integer) Functions.getItemByPercent(hashMap)).intValue();
                    if (intValue == 1) {
                        if (i2 >= round) {
                            intValue = 0;
                        } else {
                            i2++;
                        }
                        i = 2;
                    } else {
                        i = 2;
                        if (intValue == 2) {
                            if (i3 >= round2) {
                                intValue = 0;
                            } else {
                                i3++;
                            }
                        }
                    }
                    if (intValue == 0) {
                        StarSystem buildNewSystem = new StarSystem.Builder().id(i4).name(this.starNames[i7]).position(point).nebulaIDs(checkForNebulas(point)).buildNewSystem();
                        this.starSystems.add(buildNewSystem);
                        addStarToASector(buildNewSystem);
                        i4++;
                    } else if (intValue == 1) {
                        this.blackholes.add(new Blackhole(i5, point));
                        i5++;
                    } else if (intValue == i) {
                        this.spaceRifts.add(new SpaceRift(i6, point));
                        i6++;
                    }
                    z = true;
                } else {
                    i8++;
                }
            }
            if (!z) {
                return;
            }
        }
    }

    private void setupWormholes() {
        int wormholeCountNormal;
        int option = (int) Game.options.getOption(OptionID.WORMHOLES_LEVEL, 1.0f);
        if (option == 1) {
            wormholeCountNormal = this.size.getWormholeCountNormal();
        } else if (option == 2) {
            wormholeCountNormal = this.size.getWormholeCountHigh();
        } else if (option != 3) {
            wormholeCountNormal = option != 4 ? 0 : Functions.getRandom(0, this.size.getMaxRandomWormholes());
        } else {
            wormholeCountNormal = this.size.getWormholeCountLow();
        }
        for (int i = 0; i < wormholeCountNormal; i++) {
            addWormhole(getNewWormholeEndpoints());
        }
    }

    public void addBlackhole(int i, Point point, float f2, boolean z, float f3) {
        this.blackholes.add(new Blackhole(i, point, f2, z, f3));
    }

    public void addNebula(Point point, float f2, int i, float f3) {
        this.nebulas.add(new Nebula(point, f2, NebulaType.values()[i], f3));
    }

    public void addSpaceRift(int i, Point point, float f2, boolean z, float f3) {
        this.spaceRifts.add(new SpaceRift(i, point, f2, z, f3));
    }

    public void addStarSystem(StarSystem starSystem) {
        this.starSystems.add(starSystem);
    }

    public void addWormhole(Point point) {
        this.wormholes.add(point);
        this.starSystems.get((int) point.getX()).a();
        this.starSystems.get((int) point.getY()).a();
    }

    public void clear() {
        this.nebulas.clear();
        this.starSystems.clear();
        this.wormholes.clear();
        this.blackholes.clear();
        this.spaceRifts.clear();
        this.starsInSector.clear();
        this.starsInSector.add(new ArrayList());
        this.starsInSector.add(new ArrayList());
        this.starsInSector.add(new ArrayList());
        this.starsInSector.add(new ArrayList());
        this.starsInSector.add(new ArrayList());
        this.starsInSector.add(new ArrayList());
        this.starsInSector.add(new ArrayList());
    }

    public Blackhole getBlackhole(int i) {
        return this.blackholes.get(i);
    }

    public List<Blackhole> getBlackholes() {
        return this.blackholes;
    }

    public int getCenterStarInSector(int i, List<Integer> list) {
        int i2;
        if (this.starsInSector.get(i).isEmpty()) {
            return -1;
        }
        int minX = (getSize().getMinX() + getSize().getMaxX()) / 3;
        if (i == 0 || i == 3) {
            i2 = minX / 2;
        } else if (i == 1 || i == 4) {
            i2 = minX + (minX / 2);
        } else if (i == 6) {
            i2 = (getSize().getMinX() + getSize().getMaxX()) / 2;
        } else {
            i2 = (minX / 2) + (minX * 2);
        }
        int minY = (getSize().getMinY() + getSize().getMaxY()) / 2;
        if (i != 6) {
            if (i < 3) {
                minY -= minY / 2;
            } else {
                minY += minY / 2;
            }
        }
        Point point = new Point(i2, minY);
        StarSystem starSystem = null;
        for (Integer num : this.starsInSector.get(i)) {
            int intValue = num.intValue();
            if (!list.contains(Integer.valueOf(intValue))) {
                StarSystem starSystem2 = getStarSystem(intValue);
                if (starSystem == null) {
                    starSystem = starSystem2;
                }
                if (point.getDistance(starSystem2.getPosition()) < point.getDistance(starSystem.getPosition())) {
                    starSystem = starSystem2;
                }
            }
        }
        if (starSystem == null) {
            return -1;
        }
        return starSystem.getID();
    }

    public int getClosestEmpireSystem(int i, List<Integer> list) {
        if (list.contains(Integer.valueOf(i)) || list.isEmpty()) {
            return i;
        }
        int intValue = list.get(0).intValue();
        if (GameData.galaxy.hasWormholes(i)) {
            for (Integer num : GameData.galaxy.getWormholeSystemEndpoints(i)) {
                if (num.intValue() != i && list.contains(num)) {
                    return num.intValue();
                }
            }
        }
        for (Integer num2 : list) {
            int intValue2 = num2.intValue();
            if (intValue2 == i) {
                return i;
            }
            if (getDistance(i, intValue2) < getDistance(i, intValue)) {
                intValue = intValue2;
            }
        }
        return intValue;
    }

    public int getClosestSystem(Point point) {
        int id = this.starSystems.get(0).getID();
        int distance = point.getDistance(this.starSystems.get(0).getPosition());
        for (StarSystem starSystem : this.starSystems) {
            int distance2 = point.getDistance(starSystem.getPosition());
            if (distance2 < distance) {
                id = starSystem.getID();
                distance = distance2;
            }
        }
        return id;
    }

    public int getColonizablePlanetsCount(int i, int i2) {
        int i3 = 0;
        if (GameData.empires.get(i2).isDiscoveredSystem(i)) {
            for (SystemObject systemObject : this.starSystems.get(i).getSystemObjects()) {
                if (systemObject.isPlanet() && !systemObject.hasColony()) {
                    i3++;
                }
            }
            return i3;
        }
        return 0;
    }

    public int getDistance(int i, int i2) {
        return this.distance[i][i2];
    }

    public int getDistanceConst() {
        return (int) (10.0f / this.size.getDistanceModifier());
    }

    public List<Planet> getKnownUninhabitedPlanets(int i) {
        ArrayList arrayList = new ArrayList();
        Empire empire = this.game.empires.get(i);
        for (StarSystem starSystem : this.starSystems) {
            if (empire.isDiscoveredSystem(starSystem.getID())) {
                for (SystemObject systemObject : starSystem.getSystemObjects()) {
                    if (systemObject.isPlanet() && !systemObject.isOccupied()) {
                        arrayList.add((Planet) systemObject);
                    }
                }
            }
        }
        return arrayList;
    }

    public List<Point> getKnownWormholes(int i) {
        ArrayList arrayList = new ArrayList();
        List<Integer> discoveredSystems = GameData.empires.get(i).getDiscoveredSystems();
        for (Point point : this.wormholes) {
            if (discoveredSystems.contains(Integer.valueOf((int) point.getX())) || discoveredSystems.contains(Integer.valueOf((int) point.getY()))) {
                arrayList.add(point);
            }
        }
        return arrayList;
    }

    public List<Nebula> getNebulas() {
        return this.nebulas;
    }

    public Point getNewWormholeEndpoints() {
        float nextInt;
        float nextInt2;
        boolean z;
        do {
            nextInt = Functions.random.nextInt(this.starSystems.size());
            nextInt2 = Functions.random.nextInt(this.starSystems.size());
            z = nextInt != nextInt2;
            z = (getWormholes((int) nextInt).size() == 4 || getWormholes((int) nextInt2).size() == 4) ? false : false;
            for (Point point : getWormholes()) {
                if (point.getX() == nextInt || point.getX() == nextInt2) {
                    if (point.getY() == nextInt || point.getY() == nextInt2) {
                        z = false;
                    }
                }
            }
        } while (!z);
        return new Point(nextInt, nextInt2);
    }

    public int getPlanetValue(int i, int i2) {
        return ((Planet) getSystemObject(i, i2)).getPlanetValue();
    }

    public int getPossibleOutpostCount(int i, int i2) {
        int i3 = 0;
        if (GameData.empires.get(i2).isDiscoveredSystem(i)) {
            for (SystemObject systemObject : this.starSystems.get(i).getSystemObjects()) {
                if (!systemObject.isOccupied() && (systemObject.isAsteroidBelt() || systemObject.isGasGiant())) {
                    i3++;
                }
            }
            return i3;
        }
        return 0;
    }

    public GalaxySize getSize() {
        return this.size;
    }

    public List<SpaceRift> getSpaceRifts() {
        return this.spaceRifts;
    }

    public int getStarCount() {
        return this.starSystems.size();
    }

    public StarSystem getStarSystem(int i) {
        return this.starSystems.get(i);
    }

    public List<StarSystem> getStarSystems() {
        return this.starSystems;
    }

    public List<Integer> getStarsInRange(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < this.starSystems.size(); i3++) {
            if (getDistance(i, i3) <= i2) {
                arrayList.add(Integer.valueOf(i3));
            }
        }
        return arrayList;
    }

    public List<Integer> getStartingSectors(int i) {
        ArrayList arrayList = new ArrayList();
        switch (i) {
            case 2:
                if (Functions.percent(50)) {
                    arrayList.add(0);
                    arrayList.add(5);
                    break;
                } else {
                    arrayList.add(2);
                    arrayList.add(3);
                    break;
                }
            case 3:
                if (Functions.percent(50)) {
                    arrayList.add(0);
                    arrayList.add(2);
                    arrayList.add(4);
                    break;
                } else {
                    arrayList.add(1);
                    arrayList.add(3);
                    arrayList.add(5);
                    break;
                }
            case 4:
                arrayList.add(0);
                arrayList.add(2);
                arrayList.add(3);
                arrayList.add(5);
                break;
            case 5:
                arrayList.add(0);
                arrayList.add(2);
                arrayList.add(3);
                arrayList.add(5);
                if (Functions.percent(50)) {
                    arrayList.add(1);
                    break;
                } else {
                    arrayList.add(4);
                    break;
                }
            case 6:
                arrayList.add(0);
                arrayList.add(1);
                arrayList.add(2);
                arrayList.add(3);
                arrayList.add(4);
                arrayList.add(5);
                break;
            case 7:
                arrayList.add(0);
                arrayList.add(1);
                arrayList.add(2);
                arrayList.add(3);
                arrayList.add(4);
                arrayList.add(5);
                arrayList.add(6);
                break;
        }
        Collections.shuffle(arrayList);
        return arrayList;
    }

    public SystemObject getSystemObject(int i, int i2) {
        return getStarSystem(i).getSystemObject(i2);
    }

    public List<Integer> getSystemsInRange(int i, int i2, int i3) {
        HashSet hashSet = new HashSet();
        hashSet.add(Integer.valueOf(i));
        List<Integer> friendlyStarSystems = GameData.empires.get(i2).getFriendlyStarSystems();
        int closestEmpireSystem = getClosestEmpireSystem(i, friendlyStarSystems);
        if (getDistance(i, closestEmpireSystem) <= i3) {
            for (Integer num : getEmpireCluster(closestEmpireSystem, friendlyStarSystems, i3)) {
                hashSet.addAll(getStarsInRange(num.intValue(), i3));
            }
        }
        if (hasWormholes(i)) {
            int i4 = -1;
            int i5 = -1;
            for (Integer num2 : friendlyStarSystems) {
                int distance = getDistance(i, num2.intValue());
                if (distance <= i3) {
                    if (i4 == -1) {
                        i4 = num2.intValue();
                    } else if (distance < i5) {
                        i4 = num2.intValue();
                    }
                    i5 = distance;
                }
            }
            if (i4 != -1) {
                for (Integer num3 : getEmpireCluster(i4, friendlyStarSystems, i3)) {
                    hashSet.addAll(getStarsInRange(num3.intValue(), i3));
                }
            }
            for (Integer num4 : getWormholeSystemEndpoints(i)) {
                int intValue = num4.intValue();
                if (getDistance(intValue, getClosestEmpireSystem(intValue, friendlyStarSystems)) < i3) {
                    hashSet.addAll(getWormholes(i));
                }
            }
        } else {
            for (Integer num5 : getEmpireCluster(getClosestEmpireSystem(i, friendlyStarSystems), friendlyStarSystems, i3)) {
                hashSet.addAll(getStarsInRange(num5.intValue(), i3));
            }
        }
        return new ArrayList(hashSet);
    }

    public int getUnexploredPlanetsCount(int i, int i2) {
        int i3 = 0;
        if (GameData.empires.get(i2).isDiscoveredSystem(i)) {
            for (SystemObject systemObject : this.starSystems.get(i).getSystemObjects()) {
                if (systemObject.isPlanet() && systemObject.isUnexplored()) {
                    i3++;
                }
            }
            return i3;
        }
        return 0;
    }

    public List<Integer> getWormholeSystemEndpoints(int i) {
        ArrayList arrayList = new ArrayList();
        LinkedList linkedList = new LinkedList(getWormholes(i));
        arrayList.add(Integer.valueOf(i));
        while (!linkedList.isEmpty()) {
            int intValue = ((Integer) linkedList.remove()).intValue();
            if (!arrayList.contains(Integer.valueOf(intValue))) {
                arrayList.add(Integer.valueOf(intValue));
                linkedList.addAll(getWormholes(intValue));
            }
        }
        return arrayList;
    }

    public List<Point> getWormholes() {
        return this.wormholes;
    }

    public boolean hasWormholes(int i) {
        return getWormholes(i).size() != 0;
    }

    public boolean isFleetStillInRange(List<Integer> list, List<Integer> list2, int i) {
        for (Integer num : list) {
            int intValue = num.intValue();
            if (getDistance(intValue, getClosestEmpireSystem(intValue, list2)) <= i) {
                return true;
            }
        }
        return false;
    }

    public void removeWormhole(Point point) {
        this.wormholes.remove(point);
        this.starSystems.get((int) point.getX()).b();
        this.starSystems.get((int) point.getY()).b();
    }

    public void setDistanceMatrix() {
        for (StarSystem starSystem : this.starSystems) {
            for (StarSystem starSystem2 : this.starSystems) {
                int id = starSystem.getID();
                this.distance[id][starSystem2.getID()] = starSystem.getPosition().getDistance(starSystem2.getPosition()) / getDistanceConst();
            }
        }
    }

    public void setSize(GalaxySize galaxySize) {
        this.size = galaxySize;
    }

    public void setWormhole(int i, int i2) {
        this.wormholes.add(new Point(i, i2));
    }

    public void setupGalaxy(GalaxySize galaxySize) {
        this.size = galaxySize;
        clear();
        setupNebula();
        setupStars();
        setupWormholes();
        setDistanceMatrix();
        setUniqueWorlds();
        if (Functions.percent(20)) {
            setPerfectWorld();
        }
    }

    public List<Planet> sortPlanets(List<Planet> list) {
        Collections.sort(list, new Comparator() { // from class: com.birdshel.Uciana.StarSystems.a
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int lambda$sortPlanets$0;
                lambda$sortPlanets$0 = Galaxy.lambda$sortPlanets$0((Planet) obj, (Planet) obj2);
                return lambda$sortPlanets$0;
            }
        });
        return list;
    }

    public List<Integer> getWormholes(int i) {
        ArrayList arrayList = new ArrayList();
        for (Point point : this.wormholes) {
            float f2 = i;
            if (point.getX() == f2) {
                arrayList.add(Integer.valueOf((int) point.getY()));
            }
            if (point.getY() == f2) {
                arrayList.add(Integer.valueOf((int) point.getX()));
            }
        }
        return arrayList;
    }
}
