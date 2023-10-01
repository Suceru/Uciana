package com.birdshel.Uciana.Ships;

import android.util.SparseArray;

import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.GameProperties;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.Players.EmpireEventKeys;
import com.birdshel.Uciana.StarSystems.StarSystem;
import com.birdshel.Uciana.Utility.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Fleets {
    private List<Fleet> fleets = new ArrayList();
    private final List<Set<Integer>> colonyShipsArrivedLocations = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Ships.Fleets$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1547a;

        static {
            int[] iArr = new int[ShipSort.values().length];
            f1547a = iArr;
            try {
                iArr[ShipSort.A_TO_Z.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1547a[ShipSort.Z_TO_A.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1547a[ShipSort.SHIPS_HIGHEST_TO_LOWEST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1547a[ShipSort.SHIPS_LOWEST_TO_HIGHEST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public Fleets() {
        for (int i = 0; i < 7; i++) {
            this.colonyShipsArrivedLocations.add(i, new HashSet());
        }
    }

    private void checkForContact(SparseArray<List<Integer>> sparseArray, int i) {
        List<Fleet> fleetsInSystem = getFleetsInSystem(i);
        for (Fleet fleet : fleetsInSystem) {
            int i2 = fleet.empireID;
            if (!GameProperties.isNonNormalEmpire(i2)) {
                Empire empire = GameData.empires.get(i2);
                for (Fleet fleet2 : fleetsInSystem) {
                    int i3 = fleet2.empireID;
                    if (i2 != i3) {
                        if (i3 == 8) {
                            if (fleet2.getShips().get(0).getID().startsWith("AD")) {
                                Map<String, Integer> events = empire.getEvents();
                                EmpireEventKeys empireEventKeys = EmpireEventKeys.ASCENDED_ENCOUNTER;
                                if (!events.containsKey(empireEventKeys.getName())) {
                                    empire.addEvent(empireEventKeys.getName(), 1);
                                    GameData.events.addAscendedEvent(empire.id, i);
                                }
                            }
                        } else if (i3 != 9) {
                            empire.addContact(i3);
                        }
                    }
                }
                for (Empire empire2 : GameData.empires.getEmpires()) {
                    int i4 = empire2.id;
                    if (i2 != i4 && sparseArray.get(i4).contains(Integer.valueOf(i))) {
                        empire.addContact(empire2.id);
                        empire2.addContact(i2);
                    }
                }
            }
        }
    }

    private int fleetsInSystemCount(int i, int i2) {
        int i3 = 0;
        for (Fleet fleet : this.fleets) {
            if (fleet.empireID == i && fleet.getSystemID() == i2) {
                i3++;
            }
        }
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$sort$0(ShipSort shipSort, Fleet fleet, Fleet fleet2) {
        int size;
        int size2;
        String name = GameData.galaxy.getStarSystem(fleet.getSystemID()).getName();
        String name2 = GameData.galaxy.getStarSystem(fleet2.getSystemID()).getName();
        int i = AnonymousClass1.f1547a[shipSort.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    size = fleet2.getSize();
                    size2 = fleet.getSize();
                } else if (i != 4) {
                    return 0;
                } else {
                    size = fleet.getSize();
                    size2 = fleet2.getSize();
                }
                return size - size2;
            }
            return name2.compareToIgnoreCase(name);
        }
        return name.compareToIgnoreCase(name2);
    }

    private void removeShipType(ShipType shipType, int i, int i2) {
        Fleet fleetInSystem = getFleetInSystem(i2, i);
        Iterator<Ship> it = fleetInSystem.getShips().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Ship next = it.next();
            if (next.getShipType() == shipType) {
                fleetInSystem.getShips().remove(next);
                break;
            }
        }
        if (fleetInSystem.getShips().isEmpty()) {
            remove(fleetInSystem);
        }
    }

    public void add(Fleet fleet) {
        this.fleets.add(fleet);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(int i, int i2) {
        this.colonyShipsArrivedLocations.get(i).add(Integer.valueOf(i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(int i, int i2) {
        if (fleetsInSystemCount(i, i2) < 2) {
            return;
        }
        Fleet fleet = new Fleet(i, i2);
        for (Fleet fleet2 : getFleetsInSystem(i, i2)) {
            for (Ship ship : fleet2.getShips()) {
                fleet.addShip(ship);
            }
            remove(fleet2);
        }
        if (fleet.getShips().size() != 0) {
            add(fleet);
        }
    }

    public void checkForChanceToAttack(int i) {
        for (Fleet fleet : getFleetsInSystem(i)) {
            fleet.c(i);
        }
    }

    public void clear() {
        this.fleets = new ArrayList();
        for (Set<Integer> set : this.colonyShipsArrivedLocations) {
            set.clear();
        }
    }

    public boolean doesShipNoLongerExists(String str) {
        for (Fleet fleet : this.fleets) {
            if (fleet.hasShip(str)) {
                return false;
            }
        }
        return true;
    }

    public Fleet get(String str) {
        for (Fleet fleet : this.fleets) {
            if (fleet.id.equals(str)) {
                fleet.sort();
                return fleet;
            }
        }
        throw new AssertionError("Fleet with id of " + str + " does not exists");
    }

    public Set<Integer> getColonyShipArrivedLocations(int i) {
        return this.colonyShipsArrivedLocations.get(i);
    }

    public int getCommandPointsUsed(int i) {
        int i2 = 0;
        for (Fleet fleet : this.fleets) {
            if (fleet.empireID == i) {
                for (Ship ship : fleet.getShips()) {
                    i2 += ship.getShipType().getCommandPointCost();
                }
            }
        }
        return i2;
    }

    public int[] getCountOfEachType(int i) {
        int[] iArr = {0, 0, 0, 0, 0, 0, 0, 0};
        for (Fleet fleet : getFleetsByEmpire(i)) {
            for (Ship ship : fleet.getShips()) {
                int i2 = ship.getShipType().id;
                iArr[i2] = iArr[i2] + 1;
            }
        }
        return iArr;
    }

    public Fleet getFleetInSystem(int i, int i2) {
        for (Fleet fleet : this.fleets) {
            if (fleet.empireID == i && fleet.getSystemID() == i2 && !fleet.isMoving()) {
                return fleet;
            }
        }
        throw new AssertionError("Requested fleet at system " + i2 + " for empire " + i + " does not exists");
    }

    public List<Fleet> getFleets() {
        return this.fleets;
    }

    public List<Fleet> getFleetsByEmpire(int i) {
        ArrayList arrayList = new ArrayList();
        for (Fleet fleet : this.fleets) {
            if (fleet.empireID == i) {
                arrayList.add(fleet);
            }
        }
        return arrayList;
    }

    public List<Fleet> getFleetsInSystem(int i) {
        ArrayList arrayList = new ArrayList();
        for (Fleet fleet : this.fleets) {
            if (fleet.getSystemID() == i && !fleet.isMoving()) {
                arrayList.add(fleet);
            }
        }
        return arrayList;
    }

    public List<Fleet> getList() {
        return this.fleets;
    }

    public List<Fleet> getMovingFleets() {
        ArrayList arrayList = new ArrayList();
        for (Fleet fleet : this.fleets) {
            if (fleet.isMoving()) {
                arrayList.add(fleet);
            }
        }
        return arrayList;
    }

    public int getTotalShipCount(int i) {
        int i2 = 0;
        for (Fleet fleet : getFleetsByEmpire(i)) {
            i2 += fleet.getSize();
        }
        return i2;
    }

    public Fleet getUsingShipID(int i, String str) {
        for (Fleet fleet : this.fleets) {
            if (fleet.empireID == i && fleet.hasShip(str)) {
                return fleet;
            }
        }
        throw new AssertionError("Ship with id of " + str + " does not exists");
    }

    public int getWarShipCountInOrComingToSystem(int i, int i2) {
        int i3 = 0;
        for (Fleet fleet : this.fleets) {
            if (fleet.empireID == i && fleet.getSystemID() == i2) {
                i3 += fleet.getCombatShips().size();
            }
        }
        return i3;
    }

    public boolean has(String str) {
        for (Fleet fleet : this.fleets) {
            if (fleet.id.equals(str)) {
                fleet.sort();
                return true;
            }
        }
        return false;
    }

    public boolean isFleetInSystem(int i, int i2) {
        for (Fleet fleet : this.fleets) {
            if (fleet.empireID == i && fleet.getSystemID() == i2 && !fleet.isMoving()) {
                return true;
            }
        }
        return false;
    }

    public boolean isStillAFleet(String str) {
        for (Fleet fleet : this.fleets) {
            if (fleet.id.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public void processTurn() {
        int i;
        Iterator<Fleet> it = this.fleets.iterator();
        while (it.hasNext()) {
            if (it.next().isEmpty()) {
                it.remove();
            }
        }
        for (Set<Integer> set : this.colonyShipsArrivedLocations) {
            set.clear();
        }
        for (Fleet fleet : this.fleets) {
            fleet.update();
            fleet.e();
            fleet.d();
            fleet.resetStatus();
        }
        SparseArray<List<Integer>> sparseArray = new SparseArray<>();
        for (Empire empire : GameData.empires.getEmpires()) {
            sparseArray.put(empire.id, empire.getSystemIDs());
        }
        Iterator<StarSystem> it2 = GameData.galaxy.getStarSystems().iterator();
        while (true) {
            i = 0;
            if (!it2.hasNext()) {
                break;
            }
            StarSystem next = it2.next();
            while (i <= 9) {
                c(i, next.getID());
                i++;
            }
            checkForContact(sparseArray, next.getID());
        }
        for (Set<Integer> set2 : this.colonyShipsArrivedLocations) {
            Iterator<Integer> it3 = set2.iterator();
            while (it3.hasNext()) {
                Log.message("Colony Ship Arrived", "Colony ship for the " + GameData.empires.get(i).getName() + " has arrived at " + GameData.galaxy.getStarSystem(it3.next().intValue()).getName());
            }
            i++;
        }
    }

    public void remove(Fleet fleet) {
        this.fleets.remove(fleet);
    }

    public void removeColonyShip(int i, int i2) {
        removeShipType(ShipType.COLONY, i, i2);
    }

    public void removeColonyShipArrived(int i, int i2) {
        this.colonyShipsArrivedLocations.get(i).remove(Integer.valueOf(i2));
    }

    public void removeOutpostShip(int i, int i2) {
        removeShipType(ShipType.CONSTRUCTION, i, i2);
    }

    public void removeTransportShip(int i, int i2) {
        removeShipType(ShipType.TRANSPORT, i, i2);
    }

    public List<Fleet> sort(int i, final ShipSort shipSort) {
        List<Fleet> fleetsByEmpire = getFleetsByEmpire(i);
        Collections.sort(fleetsByEmpire, new Comparator() { // from class: com.birdshel.Uciana.Ships.c
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int lambda$sort$0;
                lambda$sort$0 = Fleets.lambda$sort$0(ShipSort.this, (Fleet) obj, (Fleet) obj2);
                return lambda$sort$0;
            }
        });
        return fleetsByEmpire;
    }

    private List<Fleet> getFleetsInSystem(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        for (Fleet fleet : this.fleets) {
            if (fleet.empireID == i && fleet.getSystemID() == i2 && !fleet.isMoving()) {
                arrayList.add(fleet);
            }
        }
        return arrayList;
    }
}
