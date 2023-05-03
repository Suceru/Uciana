package com.birdshel.Uciana.AI.Managers;

import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.motion.utils.a;
import com.birdshel.Uciana.Elements.Battle.BattleCallBack;
import com.birdshel.Uciana.Elements.Battle.BattleGrid;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Ships.Ship;
import com.birdshel.Uciana.Ships.ShipComponents.Weapon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class BattleAI {
    private BattleCallBack battleCallBack;
    private BattleGrid battleGrid;

    private boolean hasTargets(Ship ship) {
        for (Ship ship2 : this.battleGrid.getEnemyShips(ship.getEmpireID())) {
            if (this.battleGrid.hasLineOfSight(ship.getBattleLocation(), ship2.getBattleLocation())) {
                return true;
            }
        }
        return false;
    }

    private void moveTowardsEnemyShips(Ship ship, List<Ship> list, List<Point> list2) {
        Point battleLocation = list.get(0).getBattleLocation();
        Point battleLocation2 = ship.getBattleLocation();
        int hexDistance = Functions.getHexDistance(battleLocation2, battleLocation);
        for (Ship ship2 : list) {
            int hexDistance2 = Functions.getHexDistance(battleLocation2, ship2.getBattleLocation());
            if (hexDistance2 < hexDistance) {
                battleLocation = ship2.getBattleLocation();
                hexDistance = hexDistance2;
            }
        }
        ArrayList arrayList = new ArrayList();
        int i = 1000;
        for (Point point : list2) {
            int hexDistance3 = Functions.getHexDistance(battleLocation, point);
            if (hexDistance3 < i) {
                arrayList.clear();
                arrayList.add(point);
                i = hexDistance3;
            } else if (hexDistance3 == i) {
                arrayList.add(point);
            }
        }
        this.battleCallBack.moveShip((Point) arrayList.get(Functions.random.nextInt(arrayList.size())));
    }

    private boolean shouldSelfDestruct(Ship ship) {
        boolean z;
        Iterator<Ship> it = this.battleGrid.getShips().iterator();
        while (true) {
            if (it.hasNext()) {
                if (it.next().getEmpireID() != ship.getEmpireID()) {
                    z = false;
                    break;
                }
            } else {
                z = true;
                break;
            }
        }
        if (z) {
            return false;
        }
        if (ship.canMove() || this.battleGrid.getShips(ship.getEmpireID()).size() != 1 || hasTargets(ship)) {
            if (ship.getArmorHitPoints() < ship.getMaxArmorHitPoints() * 0.2f) {
                int i = 0;
                int i2 = 0;
                for (Map.Entry<String, Integer> entry : this.battleGrid.getShipsNearHex(ship.getBattleLocation()).entrySet()) {
                    if (ship.getEmpireID() == entry.getValue().intValue()) {
                        i2++;
                    } else {
                        i++;
                    }
                }
                return i != 0 && i >= i2;
            }
            return false;
        }
        return true;
    }

    public void moveDone(Ship ship) {
        List<Ship> enemyShips = this.battleGrid.getEnemyShips(ship.getEmpireID());
        final Point battleLocation = ship.getBattleLocation();
        Collections.sort(enemyShips, new Comparator<Ship>(this) { // from class: com.birdshel.Uciana.AI.Managers.BattleAI.1
            @Override // java.util.Comparator
            @RequiresApi(api = 19)
            public int compare(Ship ship2, Ship ship3) {
                int i;
                int i2 = 1000;
                try {
                    i = Functions.getHexDistance(battleLocation, ship2.getBattleLocation());
                } catch (Exception unused) {
                    i = 1000;
                }
                try {
                    i2 = Functions.getHexDistance(battleLocation, ship3.getBattleLocation());
                } catch (Exception unused2) {
                }
                if (Build.VERSION.SDK_INT >= 19) {
                    return a.a(i, i2);
                }
                if (i < i2) {
                    return -1;
                }
                return i > i2 ? 1 : 0;
            }
        });
        for (Weapon weapon : ship.getShipToShipWeapons()) {
            for (Ship ship2 : enemyShips) {
                if (weapon.hasUnfiredWeapons() && this.battleGrid.hasLineOfSight(ship.getBattleLocation(), ship2.getBattleLocation())) {
                    this.battleCallBack.attackShip(ship2.getBattleLocation(), weapon);
                    return;
                }
            }
        }
        if (shouldSelfDestruct(ship)) {
            this.battleCallBack.selfDestruct(ship);
        } else {
            this.battleCallBack.shipDone();
        }
    }

    public void moveShip(Ship ship) {
        List<Point> possibleMoves = this.battleGrid.getPossibleMoves();
        if (possibleMoves.isEmpty()) {
            moveDone(ship);
            return;
        }
        List<Ship> enemyShips = this.battleGrid.getEnemyShips(ship.getEmpireID());
        if (enemyShips.isEmpty()) {
            moveDone(ship);
        } else {
            moveTowardsEnemyShips(ship, enemyShips, possibleMoves);
        }
    }

    public void set(BattleGrid battleGrid, BattleCallBack battleCallBack) {
        this.battleGrid = battleGrid;
        this.battleCallBack = battleCallBack;
    }
}
