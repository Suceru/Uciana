package com.birdshel.Uciana.AI.Managers;

import com.birdshel.Uciana.AI.AIObjects.EmpireThreatState;
import com.birdshel.Uciana.AI.AIObjects.FleetSize;
import com.birdshel.Uciana.AI.AIObjects.ShipBuildPriority;
import com.birdshel.Uciana.Colonies.Buildings.BuildingID;
import com.birdshel.Uciana.Colonies.Buildings.Buildings;
import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Planets.SystemObject;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.Ships.Ship;
import com.birdshel.Uciana.Ships.ShipType;
import com.birdshel.Uciana.Ships.StarBase;
import com.birdshel.Uciana.Ships.TorpedoTurret;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class MilitaryAI {
    private final Empire empire;
    private List<Integer> enemySystems;
    private int fleetStagingSystemID;
    private boolean areAttacking = false;
    private final int MIN_NUMBER_OF_COMBAT_DEFENDERS_PER_SYSTEM = 1;
    private int targetSystemID = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.AI.Managers.MilitaryAI$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1344a;
        static final /* synthetic */ int[] b;

        /* renamed from: c  reason: collision with root package name */
        static final /* synthetic */ int[] f1345c;

        static {
            int[] iArr = new int[FleetSize.values().length];
            f1345c = iArr;
            try {
                iArr[FleetSize.TINY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1345c[FleetSize.SMALL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1345c[FleetSize.MEDIUM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1345c[FleetSize.LARGE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1345c[FleetSize.HUGE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1345c[FleetSize.MAXED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            int[] iArr2 = new int[EmpireThreatState.values().length];
            b = iArr2;
            try {
                iArr2[EmpireThreatState.NO_CONTACT.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                b[EmpireThreatState.PEACE.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                b[EmpireThreatState.WAR.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
            int[] iArr3 = new int[BuildingID.values().length];
            f1344a = iArr3;
            try {
                iArr3[BuildingID.STAR_BASE.ordinal()] = 1;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f1344a[BuildingID.TORPEDO_TURRET.ordinal()] = 2;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    public MilitaryAI(Empire empire) {
        this.empire = empire;
        this.fleetStagingSystemID = empire.getHomeSystem();
    }

    private void checkToSeeIfAttackFleetIsAttacking() {
        int i = this.targetSystemID;
        if (i == -1) {
            this.areAttacking = false;
        }
        if (!this.enemySystems.contains(Integer.valueOf(i))) {
            this.targetSystemID = -1;
            this.areAttacking = false;
        }
        for (Fleet fleet : GameData.fleets.getFleetsByEmpire(this.empire.id)) {
            if (fleet.getSystemID() == this.targetSystemID) {
                return;
            }
        }
        this.areAttacking = false;
    }

    private Fleet createNewFleetFromFleet(Fleet fleet) {
        Fleet fleet2 = new Fleet(this.empire.id, fleet.getOriginSystem());
        GameData.fleets.add(fleet2);
        fleet2.setPosition(new Point(fleet.getPosition().getX(), fleet.getPosition().getY()));
        fleet2.set(fleet.isMoving(), fleet.inOrbit());
        return fleet2;
    }

    private void figureOutTargetSystem() {
        if (this.areAttacking) {
            return;
        }
        if (this.enemySystems.isEmpty()) {
            this.targetSystemID = -1;
            return;
        }
        List<Integer> list = this.enemySystems;
        this.targetSystemID = list.get(Functions.random.nextInt(list.size())).intValue();
    }

    private void getEnemySystemsInRange() {
        this.enemySystems = new ArrayList();
        int fuelCellRange = this.empire.getTech().getFuelCellRange();
        List<Integer> friendlyStarSystems = this.empire.getFriendlyStarSystems();
        for (Empire empire : GameData.empires.getEmpires()) {
            int i = empire.id;
            Empire empire2 = this.empire;
            if (i != empire2.id && empire2.isAtWar(i)) {
                for (Colony colony : empire.getColonies()) {
                    Iterator<Integer> it = friendlyStarSystems.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (GameData.galaxy.getDistance(colony.getSystemID(), it.next().intValue()) <= fuelCellRange) {
                                this.enemySystems.add(Integer.valueOf(colony.getSystemID()));
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    private int getHighestCombatStrengthInSystem(int i) {
        int combatStrength;
        int combatStrength2;
        int i2 = 0;
        int i3 = 0;
        for (SystemObject systemObject : GameData.galaxy.getStarSystem(i).getSystemObjects()) {
            if (systemObject.isPlanet() && systemObject.hasColony()) {
                Colony colony = systemObject.getColony();
                if (this.empire.isAtWar(colony.getEmpireID())) {
                    int i4 = 0;
                    for (String str : colony.getBuildings()) {
                        int i5 = AnonymousClass1.f1344a[Buildings.getBuilding(str).getID().ordinal()];
                        if (i5 == 1) {
                            combatStrength2 = new StarBase(colony.getEmpireID()).getCombatStrength();
                        } else if (i5 == 2) {
                            combatStrength2 = new TorpedoTurret(colony.getEmpireID(), "").getCombatStrength() * 2;
                        }
                        i4 += combatStrength2;
                    }
                    if (i4 > i3) {
                        i3 = i4;
                    }
                }
            }
        }
        for (Fleet fleet : GameData.fleets.getFleetsInSystem(i)) {
            if (this.empire.isAtWar(fleet.empireID) && (combatStrength = fleet.getCombatStrength()) > i2) {
                i2 = combatStrength;
            }
        }
        return i3 + i2;
    }

    private List<Integer> getSystemsThatNeedShips() {
        ArrayList arrayList = new ArrayList();
        for (Integer num : this.empire.getSystemIDs()) {
            int intValue = num.intValue();
            if (GameData.fleets.getWarShipCountInOrComingToSystem(this.empire.id, intValue) < 1) {
                arrayList.add(Integer.valueOf(intValue));
            }
        }
        return arrayList;
    }

    private void moveCombatShipToSystem(Fleet fleet, int i) {
        if (fleet.getSystemID() == i) {
            return;
        }
        if (fleet.getSize() == 1) {
            fleet.move(i);
            return;
        }
        Fleet createNewFleetFromFleet = createNewFleetFromFleet(fleet);
        Iterator<Ship> it = fleet.getShips().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Ship next = it.next();
            if (next.isCombatShip()) {
                createNewFleetFromFleet.getShips().add(next);
                fleet.removeShip(next.getID());
                break;
            }
        }
        createNewFleetFromFleet.move(i);
    }

    private void moveTransportsToSystem(Fleet fleet, int i) {
        if (fleet.getSystemID() == i) {
            return;
        }
        Fleet createNewFleetFromFleet = createNewFleetFromFleet(fleet);
        ArrayList<String> arrayList = new ArrayList();
        for (Ship ship : fleet.getShips()) {
            if (ship.getShipType() == ShipType.TRANSPORT) {
                createNewFleetFromFleet.getShips().add(ship);
                arrayList.add(ship.getID());
            }
        }
        for (String str : arrayList) {
            fleet.removeShip(str);
        }
        if (fleet.isEmpty()) {
            GameData.fleets.remove(fleet);
        }
        createNewFleetFromFleet.move(i);
    }

    private ShipBuildPriority noContactBuildPriority(FleetSize fleetSize) {
        int i = AnonymousClass1.f1345c[fleetSize.ordinal()];
        if (i != 1 && i != 2 && i != 3 && i != 4 && i != 5) {
            return ShipBuildPriority.MAINTENANCE;
        }
        return ShipBuildPriority.BUILDINGS_FIRST;
    }

    private ShipBuildPriority peaceBuildPriority(FleetSize fleetSize) {
        int i = AnonymousClass1.f1345c[fleetSize.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        if (i != 5) {
                            return ShipBuildPriority.MAINTENANCE;
                        }
                        return ShipBuildPriority.BUILDINGS_FIRST;
                    }
                    return ShipBuildPriority.RARELY;
                }
                return ShipBuildPriority.NOT_CRITICAL;
            }
            return ShipBuildPriority.AVAILABLE;
        }
        return ShipBuildPriority.IMMEDIATELY;
    }

    private void setStagingSystem() {
        if (this.empire.getSystemIDs().contains(Integer.valueOf(this.fleetStagingSystemID))) {
            return;
        }
        this.fleetStagingSystemID = this.empire.getSystemIDs().get(0).intValue();
    }

    private ShipBuildPriority warBuildPriority(FleetSize fleetSize) {
        int i = AnonymousClass1.f1345c[fleetSize.ordinal()];
        if (i == 1 || i == 2) {
            return ShipBuildPriority.EMERGENCY;
        }
        if (i != 3) {
            if (i != 4 && i != 5) {
                return ShipBuildPriority.MAINTENANCE;
            }
            return ShipBuildPriority.AVAILABLE;
        }
        return ShipBuildPriority.IMMEDIATELY;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ShipBuildPriority a(EmpireThreatState empireThreatState, int i, int i2) {
        FleetSize fleetSize = FleetSize.getFleetSize(i, i2);
        int i3 = AnonymousClass1.b[empireThreatState.ordinal()];
        if (i3 != 1) {
            if (i3 != 2) {
                if (i3 != 3) {
                    return ShipBuildPriority.AVAILABLE;
                }
                return warBuildPriority(fleetSize);
            }
            return peaceBuildPriority(fleetSize);
        }
        return noContactBuildPriority(fleetSize);
    }

    public void manage() {
        setStagingSystem();
        getEnemySystemsInRange();
        checkToSeeIfAttackFleetIsAttacking();
        figureOutTargetSystem();
        List<Integer> systemsThatNeedShips = getSystemsThatNeedShips();
        Collections.shuffle(systemsThatNeedShips);
        for (Integer num : this.empire.getSystemIDs()) {
            int intValue = num.intValue();
            if (GameData.fleets.isFleetInSystem(this.empire.id, intValue)) {
                Fleet fleetInSystem = GameData.fleets.getFleetInSystem(this.empire.id, intValue);
                if (fleetInSystem.hasTransportShip()) {
                    moveTransportsToSystem(fleetInSystem, this.fleetStagingSystemID);
                }
                if (fleetInSystem.getCombatShips().size() > 1) {
                    if (systemsThatNeedShips.isEmpty()) {
                        moveCombatShipToSystem(fleetInSystem, this.fleetStagingSystemID);
                    } else {
                        int intValue2 = systemsThatNeedShips.get(0).intValue();
                        systemsThatNeedShips.remove(0);
                        moveCombatShipToSystem(fleetInSystem, intValue2);
                    }
                }
            }
        }
        int i = this.targetSystemID;
        if (i == -1) {
            return;
        }
        int highestCombatStrengthInSystem = getHighestCombatStrengthInSystem(i);
        if (GameData.fleets.isFleetInSystem(this.empire.id, this.fleetStagingSystemID)) {
            Fleet fleetInSystem2 = GameData.fleets.getFleetInSystem(this.empire.id, this.fleetStagingSystemID);
            if (fleetInSystem2.getCombatStrength() >= highestCombatStrengthInSystem) {
                fleetInSystem2.move(this.targetSystemID);
            }
        }
    }
}
