package com.birdshel.Uciana.AI;

import com.birdshel.Uciana.AI.AIObjects.SystemObjectAttackAction;
import com.birdshel.Uciana.Colonies.BombingTarget;
import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Elements.Battle.Invasion;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.GameProperties;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Planets.SystemObject;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponentID;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponents;
import com.birdshel.Uciana.Ships.ShipComponents.Weapon;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class PlanetAttack {
    private SystemObjectAttackAction action;
    private final int attackerID;
    private TreeMap<ShipComponentID, Integer> availableBombs;
    private boolean colonyDestroyed;
    private final int defenderID;
    private Invasion invasion;
    private final int orbit;
    private boolean outpostDestroyed;
    private int structureArmor;
    private int structureHitPoints;
    private final int systemID;
    private final SystemObject systemObject;
    private boolean invaded = false;
    private int populationLosses = 0;
    private int buildingLosses = 0;
    private int militaryLosses = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.AI.PlanetAttack$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1348a;
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[BombingTarget.values().length];
            b = iArr;
            try {
                iArr[BombingTarget.POPULATION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[BombingTarget.BUILDING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[BombingTarget.MILITARY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[SystemObjectAttackAction.values().length];
            f1348a = iArr2;
            try {
                iArr2[SystemObjectAttackAction.BOMBARD.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1348a[SystemObjectAttackAction.INVADE.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1348a[SystemObjectAttackAction.DESTROY.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1348a[SystemObjectAttackAction.NO_ACTION.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1348a[SystemObjectAttackAction.BOMB.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public PlanetAttack(int i, int i2, int i3) {
        this.attackerID = i;
        this.systemID = i2;
        this.orbit = i3;
        this.availableBombs = new TreeMap<>();
        if (GameData.fleets.isFleetInSystem(i, i2)) {
            this.availableBombs = GameData.fleets.getFleetInSystem(i, i2).getBombs();
        }
        SystemObject systemObject = GameData.galaxy.getSystemObject(i2, i3);
        this.systemObject = systemObject;
        this.defenderID = systemObject.getOccupier();
        this.colonyDestroyed = false;
        this.outpostDestroyed = false;
    }

    private void bombColony(Colony colony, Weapon weapon, int i, BombingTarget bombingTarget) {
        if (bombingTarget == BombingTarget.MISS) {
            return;
        }
        int i2 = this.structureHitPoints;
        int i3 = i2 - i;
        this.structureHitPoints = i3;
        if (i3 > 0) {
            return;
        }
        int i4 = i - i2;
        this.structureHitPoints = this.structureArmor;
        int i5 = AnonymousClass1.b[bombingTarget.ordinal()];
        if (i5 == 1) {
            colony.populationBombed();
            this.populationLosses++;
        } else if (i5 == 2) {
            colony.buildingBombed();
            this.buildingLosses++;
        } else if (i5 == 3) {
            colony.militaryBombed();
            this.militaryLosses++;
        }
        if (colony.isAlive()) {
            bombColony(colony, weapon, i4, getTargetHit(colony, weapon, false));
        }
    }

    private void bombPlanet(ShipComponentID shipComponentID) {
        int intValue = this.availableBombs.get(shipComponentID).intValue() - 1;
        if (intValue == 0) {
            this.availableBombs.remove(shipComponentID);
        } else {
            this.availableBombs.put(shipComponentID, Integer.valueOf(intValue));
        }
        calculateDamage(shipComponentID);
    }

    private void bombardPlanet() {
        while (getBombCount() != 0 && this.systemObject.hasColony()) {
            bombPlanet(this.availableBombs.entrySet().iterator().next().getKey());
        }
    }

    private String calculateDamage(ShipComponentID shipComponentID) {
        Colony colony = this.systemObject.getColony();
        Weapon weapon = (Weapon) ShipComponents.get(shipComponentID);
        int damage = weapon.getDamage(this.attackerID);
        if (colony.isShielded()) {
            damage -= (int) (damage * colony.getShieldingStrength());
        }
        BombingTarget targetHit = getTargetHit(colony, weapon, true);
        bombColony(colony, weapon, damage, targetHit);
        if (!colony.isAlive()) {
            this.colonyDestroyed = true;
            GameData.colonies.removeColony(colony);
        }
        if (targetHit == BombingTarget.MISS) {
            return !colony.isAlive() ? "" : "Miss";
        }
        return Integer.toString(damage);
    }

    private SystemObjectAttackAction getAction() {
        if (!this.systemObject.isOccupied()) {
            return SystemObjectAttackAction.NO_ACTION;
        }
        boolean z = GameProperties.isNonNormalEmpire(this.attackerID) || GameData.empires.get(this.attackerID).isAtWar(this.systemObject.getOccupier());
        if (this.systemObject.hasOutpost() && z) {
            return SystemObjectAttackAction.DESTROY;
        }
        if (this.systemObject.hasColony()) {
            Fleet fleetInSystem = GameData.fleets.getFleetInSystem(this.attackerID, this.systemID);
            if (z) {
                if (fleetInSystem.hasTransportShip()) {
                    return SystemObjectAttackAction.INVADE;
                }
                if (!fleetInSystem.getBombs().isEmpty()) {
                    return SystemObjectAttackAction.BOMBARD;
                }
            }
        }
        return SystemObjectAttackAction.NO_ACTION;
    }

    private int getBombCount() {
        int i = 0;
        for (Map.Entry<ShipComponentID, Integer> entry : this.availableBombs.entrySet()) {
            i += entry.getValue().intValue();
        }
        return i;
    }

    private BombingTarget getTargetHit(Colony colony, Weapon weapon, boolean z) {
        ArrayList<BombingTarget> arrayList = new ArrayList();
        if (z) {
            arrayList.add(BombingTarget.MISS);
        }
        if (colony.getPopulation() > 0) {
            arrayList.add(BombingTarget.POPULATION);
        }
        if (colony.getInfDivisions() != 0) {
            arrayList.add(BombingTarget.MILITARY);
        }
        if (weapon.getID() != ShipComponentID.BIO_BOMB && !colony.getBuildings().isEmpty()) {
            arrayList.add(BombingTarget.BUILDING);
        }
        if (arrayList.isEmpty()) {
            arrayList.add(BombingTarget.NONE);
        }
        int size = arrayList.size();
        int i = 0;
        int[] iArr = size != 2 ? size != 3 ? size != 4 ? new int[]{100} : new int[]{25, 25, 25, 25} : new int[]{34, 33, 33} : new int[]{50, 50};
        HashMap hashMap = new HashMap();
        for (BombingTarget bombingTarget : arrayList) {
            hashMap.put(bombingTarget, Integer.valueOf(iArr[i]));
            i++;
        }
        return (BombingTarget) Functions.getItemByPercent(hashMap);
    }

    private void invadePlanet() {
        this.invaded = true;
        Invasion invasion = new Invasion(this.systemObject.getColony(), this.attackerID);
        this.invasion = invasion;
        invasion.check();
        while (!this.invasion.isDone()) {
            if (Functions.random.nextInt(this.invasion.getDefenderStrength()) >= Functions.random.nextInt(this.invasion.getAttackerStrength())) {
                this.invasion.removeAttacker();
            } else {
                this.invasion.removeDefender();
                this.militaryLosses++;
            }
            this.invasion.check();
        }
    }

    public boolean attack() {
        this.action = getAction();
        if (!GameData.fleets.isFleetInSystem(this.attackerID, this.systemID)) {
            this.action = SystemObjectAttackAction.NO_ACTION;
        }
        int i = AnonymousClass1.f1348a[this.action.ordinal()];
        if (i == 1) {
            this.structureArmor = (int) (GameData.empires.get(this.systemObject.getOccupier()).getTech().getArmorMultiplier() * 50.0f);
            bombardPlanet();
            return true;
        } else if (i == 2) {
            invadePlanet();
            return true;
        } else if (i != 3) {
            return false;
        } else {
            if (GameData.outposts.isOutpost(this.systemID, this.orbit)) {
                GameData.outposts.removeOutpost(this.systemObject.getOutpost());
            }
            this.outpostDestroyed = true;
            return true;
        }
    }

    public String getActionString() {
        int i = AnonymousClass1.f1348a[this.action.ordinal()];
        if (i != 1) {
            if (i == 2) {
                if (this.invasion.hasPlanetBeenTaken()) {
                    return GameData.activity.getString(R.string.planet_attack_invaded);
                }
                return GameData.activity.getString(R.string.planet_attack_repelled_invasion);
            } else if (i != 5) {
                return this.outpostDestroyed ? GameData.activity.getString(R.string.planet_attack_destroyed) : "";
            }
        }
        if (!this.colonyDestroyed && !this.outpostDestroyed) {
            return GameData.activity.getString(R.string.planet_attack_bombed);
        }
        return GameData.activity.getString(R.string.planet_attack_destroyed);
    }

    public int getBuildingLosses() {
        return this.buildingLosses;
    }

    public int getDefenderID() {
        return this.defenderID;
    }

    public int getMilitaryLosses() {
        return this.militaryLosses;
    }

    public int getOrbit() {
        return this.orbit;
    }

    public int getPopulationLosses() {
        return this.populationLosses;
    }

    public int getSystemID() {
        return this.systemID;
    }

    public boolean hasColonyBeenDestroyed() {
        return this.colonyDestroyed;
    }

    public boolean hasColonyBeenTaken() {
        return this.invaded && this.invasion.hasPlanetBeenTaken();
    }

    public boolean hasOutpostBeenDestroyed() {
        return this.outpostDestroyed;
    }
}
