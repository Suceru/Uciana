package com.birdshel.Uciana.AI.Managers;

import android.util.SparseIntArray;

import com.birdshel.Uciana.AI.AIObjects.AttackTarget;
import com.birdshel.Uciana.Colonies.Buildings.BuildingID;
import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.GameProperties;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Planets.SystemObject;
import com.birdshel.Uciana.Planets.SystemObjectType;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.Ships.Ship;
import com.birdshel.Uciana.Ships.ShipType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class AttackAI {
    private final int empireID;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.AI.Managers.AttackAI$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1339a;

        static {
            int[] iArr = new int[ShipType.values().length];
            f1339a = iArr;
            try {
                iArr[ShipType.DESTROYER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1339a[ShipType.CRUISER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1339a[ShipType.BATTLESHIP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1339a[ShipType.DREADNOUGHT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public AttackAI(int i) {
        this.empireID = i;
    }

    private int figureOutCombatStrength(int i, int i2) {
        int i3 = 0;
        if (GameData.fleets.isFleetInSystem(i, i2)) {
            for (Ship ship : GameData.fleets.getFleetInSystem(i, i2).getShips()) {
                int i4 = AnonymousClass1.f1339a[ship.getShipType().ordinal()];
                if (i4 == 1) {
                    i3++;
                } else if (i4 == 2) {
                    i3 += 3;
                } else if (i4 == 3) {
                    i3 += 6;
                } else if (i4 == 4) {
                    i3 += 8;
                }
            }
        }
        return i3;
    }

    private List<AttackTarget> getAttackTargets(int i) {
        ArrayList arrayList = new ArrayList();
        for (SystemObject systemObject : GameData.galaxy.getStarSystem(i).getSystemObjects()) {
            if (systemObject.isOccupied() && systemObject.getOccupier() != this.empireID) {
                int systemObjectTargetValue = getSystemObjectTargetValue(systemObject);
                arrayList.add(new AttackTarget(i, systemObject.getOrbit(), systemObject.getOccupier(), systemObjectTargetValue));
            }
        }
        for (Fleet fleet : GameData.fleets.getFleetsInSystem(i)) {
            if (fleet.empireID != this.empireID) {
                boolean z = false;
                Iterator it = arrayList.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (((AttackTarget) it.next()).getEmpireID() == fleet.empireID) {
                            z = true;
                            break;
                        }
                    } else {
                        break;
                    }
                }
                if (!z) {
                    arrayList.add(new AttackTarget(i, fleet.empireID, getFleetTargetValue(fleet)));
                }
            }
        }
        return arrayList;
    }

    private AttackTarget getBestTargetToAttack(int i, List<AttackTarget> list) {
        SparseIntArray combatStrengthForTargets = getCombatStrengthForTargets(list);
        int keyAt = combatStrengthForTargets.keyAt(0);
        for (int i2 = 0; i2 < combatStrengthForTargets.size(); i2++) {
            int keyAt2 = combatStrengthForTargets.keyAt(i2);
            if (combatStrengthForTargets.get(keyAt) > combatStrengthForTargets.get(keyAt2)) {
                keyAt = keyAt2;
            }
        }
        if (i >= combatStrengthForTargets.get(keyAt)) {
            return list.get(keyAt);
        }
        return new AttackTarget();
    }

    private SparseIntArray getCombatStrengthForTargets(List<AttackTarget> list) {
        int figureOutCombatStrength;
        SparseIntArray sparseIntArray = new SparseIntArray();
        int i = 0;
        for (AttackTarget attackTarget : list) {
            if (attackTarget.isFleet()) {
                figureOutCombatStrength = figureOutCombatStrength(attackTarget.getEmpireID(), attackTarget.getSystemID());
            } else {
                figureOutCombatStrength = figureOutCombatStrength(attackTarget.getEmpireID(), attackTarget.getSystemID(), attackTarget.getOrbit());
            }
            sparseIntArray.put(i, figureOutCombatStrength);
            i++;
        }
        return sparseIntArray;
    }

    private int getFleetTargetValue(Fleet fleet) {
        int i = 10;
        if (GameProperties.isNonNormalEmpire(this.empireID)) {
            return 10;
        }
        Empire empire = GameData.empires.get(this.empireID);
        if (GameProperties.isNonNormalEmpire(fleet.empireID)) {
            return 10;
        }
        if (empire.isAtWar(fleet.empireID)) {
            for (SystemObject systemObject : GameData.galaxy.getStarSystem(fleet.getSystemID()).getSystemObjects()) {
                if (systemObject.isOccupied() && systemObject.getOccupier() == this.empireID && systemObject.getSystemObjectType() == SystemObjectType.PLANET) {
                    i += 10;
                }
            }
            return i;
        }
        return 5;
    }

    private int getSystemObjectTargetValue(SystemObject systemObject) {
        return (GameProperties.isNonNormalEmpire(this.empireID) || GameData.empires.get(this.empireID).isAtWar(systemObject.getOccupier())) ? 10 : 5;
    }

    public AttackTarget getAttackTarget(int i) {
        List<AttackTarget> attackTargets = getAttackTargets(i);
        ArrayList arrayList = new ArrayList();
        for (AttackTarget attackTarget : attackTargets) {
            if (GameProperties.isNonNormalEmpire(this.empireID)) {
                arrayList.add(attackTarget);
            } else {
                Empire empire = GameData.empires.get(this.empireID);
                if (GameProperties.isNonNormalEmpire(attackTarget.getEmpireID()) || empire.isAtWar(attackTarget.getEmpireID())) {
                    arrayList.add(attackTarget);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return new AttackTarget();
        }
        if (GameProperties.isNonNormalEmpire(this.empireID)) {
            return arrayList.get(Functions.random.nextInt(arrayList.size()));
        }
        return getBestTargetToAttack(figureOutCombatStrength(this.empireID, i), arrayList);
    }

    private int figureOutCombatStrength(int i, int i2, int i3) {
        int figureOutCombatStrength = figureOutCombatStrength(i, i2);
        if (GameData.colonies.isColony(i2, i3)) {
            Colony colony = GameData.colonies.getColony(i2, i3);
            if (colony.hasBuilding(BuildingID.STAR_BASE)) {
                figureOutCombatStrength += 6;
            }
            return colony.hasBuilding(BuildingID.TORPEDO_TURRET) ? figureOutCombatStrength + 4 : figureOutCombatStrength;
        }
        return figureOutCombatStrength;
    }
}
