package com.birdshel.Uciana.AI.FleetTasks;

import com.birdshel.Uciana.AI.AI;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Math.SystemOrbit;
import com.birdshel.Uciana.Math.TaskDoerScore;
import com.birdshel.Uciana.Planets.SystemObject;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.Ships.Ship;
import com.birdshel.Uciana.Ships.ShipType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class FleetTaskAI {
    private static List<SystemOrbit> buildOutpostTasks;
    private static List<SystemOrbit> colonizationTasks;
    private static Map<String, String> colonyShips;
    private static Map<String, String> combatShips;
    private static Empire empire;
    private static List<SystemOrbit> explorationTasks;
    private static Map<String, String> outpostShips;
    private static Map<String, String> scoutShips;
    private static List<Integer> scoutingTasks;
    private static Map<String, String> transportShips;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.AI.FleetTasks.FleetTaskAI$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1337a;

        static {
            int[] iArr = new int[ShipType.values().length];
            f1337a = iArr;
            try {
                iArr[ShipType.COLONY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1337a[ShipType.CONSTRUCTION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1337a[ShipType.SCOUT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1337a[ShipType.TRANSPORT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(Fleet fleet, Ship ship, TaskDoerScore taskDoerScore) {
        boolean z;
        if (ship.getAiTask().systemID != taskDoerScore.systemID) {
            ship.setRoute(AI.getRoute(ship.getEmpireID(), fleet.getOriginSystem(), taskDoerScore.systemID));
            z = true;
        } else {
            z = false;
        }
        int i = ship.getAiTask().orbit;
        int i2 = taskDoerScore.orbit;
        if (i == i2 ? z : true) {
            ship.setAiTask(new SystemOrbit(taskDoerScore.systemID, i2));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String c(int i, int i2) {
        return i + "-" + i2;
    }

    private static void checkSystem(int i) {
        if (GameData.fleets.isFleetInSystem(8, i)) {
            return;
        }
        for (SystemObject systemObject : GameData.galaxy.getStarSystem(i).getSystemObjects()) {
            if (!systemObject.isNothing()) {
                if (systemObject.isPlanet()) {
                    if (!systemObject.isOccupied() || (systemObject.isOccupied() && systemObject.getOccupier() == empire.id && systemObject.hasOutpost())) {
                        colonizationTasks.add(new SystemOrbit(systemObject.getSystemID(), systemObject.getOrbit()));
                    }
                    if (!systemObject.hasBeenExploredByEmpire(empire.id)) {
                        explorationTasks.add(new SystemOrbit(systemObject.getSystemID(), systemObject.getOrbit()));
                    }
                } else {
                    buildOutpostTasks.add(new SystemOrbit(systemObject.getSystemID(), systemObject.getOrbit()));
                }
            }
        }
    }

    private static void clearOutData() {
        scoutingTasks = new ArrayList();
        explorationTasks = new ArrayList();
        colonizationTasks = new ArrayList();
        buildOutpostTasks = new ArrayList();
        scoutShips = new HashMap();
        colonyShips = new HashMap();
        outpostShips = new HashMap();
        transportShips = new HashMap();
        combatShips = new HashMap();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void d(Fleet fleet, Ship ship) {
        if (fleet.inOrbit()) {
            int intValue = ship.getRoute().get(0).intValue();
            if (fleet.getSystemID() == ship.getRoute().get(0).intValue()) {
                if (ship.getRoute().size() == 1) {
                    return;
                }
                intValue = ship.getRoute().get(1).intValue();
                ship.getRoute().remove(0);
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(ship.getID());
            AI.moveFleet(fleet, intValue, arrayList);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<TaskDoerScore> e(List<TaskDoerScore> list) {
        Collections.sort(list, new Comparator() { // from class: com.birdshel.Uciana.AI.FleetTasks.a
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int lambda$prioritize$0;
                lambda$prioritize$0 = FleetTaskAI.lambda$prioritize$0((TaskDoerScore) obj, (TaskDoerScore) obj2);
                return lambda$prioritize$0;
            }
        });
        return list;
    }

    public static void execute(int i) {
        empire = GameData.empires.get(i);
        clearOutData();
        fillNonCombatShipTasks();
        fillShips();
        ScoutExploreTasks.a(scoutShips, scoutingTasks, explorationTasks);
        ColonizationTasks.a(colonyShips, colonizationTasks);
        BuildOutpostTasks.a(empire, outpostShips, buildOutpostTasks);
        ScoutExploreTasks.b(empire);
        ColonizationTasks.b(empire);
        BuildOutpostTasks.b(empire);
    }

    private static void fillNonCombatShipTasks() {
        int fuelCellRange = empire.getTech().getFuelCellRange();
        List<Integer> friendlyStarSystems = empire.getFriendlyStarSystems();
        HashSet<Integer> hashSet = new HashSet(friendlyStarSystems);
        for (Integer num : friendlyStarSystems) {
            int intValue = num.intValue();
            if (empire.isDiscoveredSystem(intValue)) {
                hashSet.addAll(GameData.galaxy.getWormholes(intValue));
            }
            List<Integer> starsInRange = GameData.galaxy.getStarsInRange(intValue, fuelCellRange);
            hashSet.addAll(starsInRange);
            for (Integer num2 : starsInRange) {
                int intValue2 = num2.intValue();
                if (empire.isDiscoveredSystem(intValue2)) {
                    hashSet.addAll(GameData.galaxy.getWormholes(intValue2));
                }
            }
        }
        for (Integer num3 : hashSet) {
            int intValue3 = num3.intValue();
            if (empire.isDiscoveredSystem(intValue3)) {
                checkSystem(intValue3);
            } else {
                scoutingTasks.add(Integer.valueOf(intValue3));
            }
        }
    }

    private static void fillShips() {
        for (Fleet fleet : GameData.fleets.getFleetsByEmpire(empire.id)) {
            for (Ship ship : fleet.getShips()) {
                int i = AnonymousClass1.f1337a[ship.getShipType().ordinal()];
                if (i == 1) {
                    colonyShips.put(ship.getID(), fleet.id);
                } else if (i == 2) {
                    outpostShips.put(ship.getID(), fleet.id);
                } else if (i == 3) {
                    scoutShips.put(ship.getID(), fleet.id);
                } else if (i != 4) {
                    combatShips.put(ship.getID(), fleet.id);
                } else {
                    transportShips.put(ship.getID(), fleet.id);
                }
            }
        }
    }

    public static int getBuildOutpostTasksCount() {
        List<SystemOrbit> list = buildOutpostTasks;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public static int getColonizationTasksCount() {
        List<SystemOrbit> list = colonizationTasks;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$prioritize$0(TaskDoerScore taskDoerScore, TaskDoerScore taskDoerScore2) {
        return taskDoerScore2.score - taskDoerScore.score;
    }
}

