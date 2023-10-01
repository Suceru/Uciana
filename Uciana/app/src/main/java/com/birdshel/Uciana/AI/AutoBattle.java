package com.birdshel.Uciana.AI;

import com.birdshel.Uciana.AI.Managers.BattleAI;
import com.birdshel.Uciana.Colonies.Buildings.BuildingID;
import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Elements.Battle.BattleCallBack;
import com.birdshel.Uciana.Elements.Battle.BattleGrid;
import com.birdshel.Uciana.Elements.Battle.BattleLosses;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameProperties;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Planets.SystemObject;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.Ships.Ship;
import com.birdshel.Uciana.Ships.ShipComponents.Weapon;
import com.birdshel.Uciana.Ships.SpacialCharge;
import com.birdshel.Uciana.Ships.Torpedo;
import com.birdshel.Uciana.Utility.Log;

import java.util.ArrayList;

public class AutoBattle implements BattleCallBack {
    private BattleAI attackerBattleAI;
    private int attackerID;
    private BattleGrid battleGrid;
    private BattleAI defenderBattleAI;
    private int defenderID;
    private final Game game;
    private int orbit;
    private final boolean showResult;
    private int systemID;
    private final BattleLosses attackerLosses = new BattleLosses();
    private final BattleLosses defenderLosses = new BattleLosses();

    public AutoBattle(Game game, boolean z) {
        this.game = game;
        this.showResult = z;
    }

    private void AIMoveDone(Ship ship) {
        if (ship.hasBeenDestroyed()) {
            return;
        }
        if (ship.getEmpireID() == this.attackerID) {
            this.attackerBattleAI.moveDone(ship);
        } else {
            this.defenderBattleAI.moveDone(ship);
        }
    }

    private void AIMoveShip(Ship ship) {
        if (ship.getEmpireID() == this.attackerID) {
            this.attackerBattleAI.moveShip(ship);
        } else {
            this.defenderBattleAI.moveShip(ship);
        }
    }

    private void beamAttack(Weapon weapon, Ship ship, Ship ship2) {
        Point battleLocation;
        int hexDistance = Functions.getHexDistance(ship.getBattleLocation(), ship2.getBattleLocation());
        Log.message("AutoBattle", ship.getID() + " attacks " + ship2.getID() + " at " + battleLocation.toString());
        if (Functions.percent(this.battleGrid.getChanceToHit(ship, ship2))) {
            float f2 = Functions.percent(25) ? 0.5f : 0.0f;
            while (weapon.getAvailableCount() != 0 && ship2.isAlive()) {
                float weaponDamage = ship.getWeaponDamage(weapon, hexDistance);
                ship2.takeDamage((int) (weaponDamage + (f2 * weaponDamage)));
            }
            this.battleGrid.checkShipStatusNeedsToBeAdded(ship2);
            if (ship2.isAlive()) {
                return;
            }
            shipDestroyed(ship2);
            return;
        }
        weapon.setAllWeaponsUsed();
    }

    private void checkShockwaveHits(Ship ship, Point point) {
        for (Point point2 : this.battleGrid.getNodesInRange(point, 1)) {
            if (this.battleGrid.hasShip(point2)) {
                Ship ship2 = this.battleGrid.getShip(point2);
                int coreBreachDamage = ship.getCoreBreachDamage();
                ship2.takeDamage(coreBreachDamage);
                Log.message("AutoBattle", "Shockwave from " + ship.getID() + " has hit " + ship2.getID() + " for " + coreBreachDamage);
                this.battleGrid.checkShipStatusNeedsToBeAdded(ship2);
                if (!ship2.isAlive() && !ship2.hasBeenDestroyed()) {
                    shipDestroyed(ship2);
                }
            }
        }
    }

    private void doBattle() {
        while (true) {
            if (!this.battleGrid.isShipListEmpty()) {
                if (this.battleGrid.isBattleDone()) {
                    endBattle();
                    return;
                }
                Ship nextShip = this.battleGrid.getNextShip();
                Log.message("AutoBattle", "New ship selected: " + nextShip.getID());
                if (nextShip instanceof Torpedo) {
                    moveTorpedo((Torpedo) nextShip);
                } else if (nextShip instanceof SpacialCharge) {
                    moveSpacialCharge((SpacialCharge) nextShip);
                } else {
                    selectShip(nextShip);
                }
            } else if (this.battleGrid.isBattleDone() || this.battleGrid.getTurns() == 100) {
                break;
            } else {
                this.battleGrid.endTurn();
            }
        }
        endBattle();
    }

    private void endBattle() {
        Log.message("AutoBattle", "Battle Ended");
        this.battleGrid.sendRetreatingShipsToNearestSystem();
        if (this.showResult) {
            showResult();
        }
    }

    private void moveSpacialCharge(SpacialCharge spacialCharge) {
        Log.message("AutoBattle", "Move charge: " + spacialCharge.getID());
        if (this.battleGrid.isLineOfSightBlocked(spacialCharge, spacialCharge.getPosition().copy(), this.battleGrid.moveCharge(spacialCharge))) {
            this.battleGrid.removeSpacialCharge(spacialCharge.getID());
        } else {
            spacialChargeMoveDone(spacialCharge);
        }
    }

    private void moveTorpedo(Torpedo torpedo) {
        if (torpedo.shouldSelfDestruct()) {
            Log.message("BattleScene", "Removing Torpedo");
            this.battleGrid.removeTorpedo(torpedo.getID());
            return;
        }
        Point copy = torpedo.getPosition().copy();
        Log.message("AutoBattle", "Move torpedo: " + torpedo.getID());
        if (this.battleGrid.isLineOfSightBlocked(torpedo, copy, this.battleGrid.moveTorpedo(torpedo))) {
            this.battleGrid.removeTorpedo(torpedo.getID());
        } else {
            torpedoMoveDone(torpedo);
        }
    }

    private void placeShips(Fleet fleet, Point[] pointArr) {
        int i = this.orbit;
        int i2 = 0;
        if (i != -1 && this.game.colonies.isColony(this.systemID, i)) {
            Colony colony = this.game.colonies.getColony(this.systemID, this.orbit);
            if (colony.getEmpireID() == fleet.empireID) {
                if (colony.hasBuilding(BuildingID.STAR_BASE)) {
                    int x = (int) pointArr[0].getX();
                    int y = (int) pointArr[0].getY();
                    this.battleGrid.setObject(x, y, "starbase");
                    this.battleGrid.getStarBase().setBattleLocation(new Point(x, y));
                    i2 = 1;
                }
                if (colony.hasBuilding(BuildingID.TORPEDO_TURRET)) {
                    BattleGrid battleGrid = this.battleGrid;
                    Point point = GameProperties.BATTLE_TURRET_1_POSITION;
                    battleGrid.setObject((int) point.getX(), (int) point.getY(), "turret-1");
                    this.battleGrid.getTurret("turret-1").setBattleLocation(new Point(point.getX(), point.getY()));
                    BattleGrid battleGrid2 = this.battleGrid;
                    Point point2 = GameProperties.BATTLE_TURRET_2_POSITION;
                    battleGrid2.setObject((int) point2.getX(), (int) point2.getY(), "turret-2");
                    this.battleGrid.getTurret("turret-2").setBattleLocation(new Point(point2.getX(), point2.getY()));
                }
            }
        }
        for (Ship ship : fleet.getCombatShips()) {
            int x2 = (int) pointArr[i2].getX();
            int y2 = (int) pointArr[i2].getY();
            this.battleGrid.setObject(x2, y2, ship.getID());
            ship.setBattleLocation(new Point(x2, y2));
            i2++;
            if (i2 == 21) {
                return;
            }
        }
    }

    private void retreatShip(Ship ship) {
        this.battleGrid.shipRetreated(ship);
    }

    private void selectShip(Ship ship) {
        if (this.battleGrid.isShipRetreating()) {
            retreatShip(ship);
        } else if (this.battleGrid.isShipWaitingToRetreat()) {
            this.battleGrid.moveShipFromWaitingToRetreat();
            shipDone();
        } else {
            int movementLeft = ship.getMovementLeft();
            this.battleGrid.clearMoves();
            if (ship.canMove()) {
                for (Point point : this.battleGrid.getNodesInRange(movementLeft)) {
                    if (!this.battleGrid.hasShip(point)) {
                        this.battleGrid.setMoveHex((int) point.getX(), (int) point.getY(), true);
                    }
                }
            }
            AIMoveShip(ship);
        }
    }

    private void shipCoreBreach(Ship ship) {
        Log.message("AutoBattle", "Ship core breach: " + ship.getID());
        Point battleLocation = ship.getBattleLocation();
        checkShockwaveHits(ship, battleLocation);
        for (String str : this.battleGrid.checkTorpedoesInRangeOfShockwave(battleLocation)) {
            this.battleGrid.removeTorpedo(str);
        }
    }

    private void shipDestroyed(Ship ship) {
        Log.message("AutoBattle", "Ship destroyed: " + ship.getID());
        ship.setDestroyed();
        if (ship.getEmpireID() == this.attackerID) {
            this.attackerLosses.addLoss(ship);
        } else {
            this.defenderLosses.addLoss(ship);
        }
        if (Functions.percent(25)) {
            shipCoreBreach(ship);
        }
        this.battleGrid.shipDestroyed(ship);
    }

    private void shipHitWithSpacialCharge(SpacialCharge spacialCharge, Ship ship) {
        int damage = spacialCharge.getDamage();
        ship.takeDamage(damage);
        Log.message("AutoBattle", "Shockwave from " + spacialCharge.getID() + " has hit " + ship.getID() + " for " + damage);
        this.battleGrid.checkShipStatusNeedsToBeAdded(ship);
        if (ship.isAlive() || ship.hasBeenDestroyed()) {
            return;
        }
        shipDestroyed(ship);
    }

    private void showResult() {
        int i;
        int i2;
        int i3;
        boolean z = false;
        boolean z2 = this.game.fleets.isFleetInSystem(this.attackerID, this.systemID) && this.game.fleets.getFleetInSystem(this.attackerID, this.systemID).hasCombatShips();
        boolean z3 = this.game.fleets.isFleetInSystem(this.defenderID, this.systemID) && this.game.fleets.getFleetInSystem(this.defenderID, this.systemID).hasCombatShips();
        int i4 = this.orbit;
        if (i4 != -1 && !z3) {
            SystemObject systemObject = this.game.galaxy.getSystemObject(this.systemID, i4);
            if (systemObject.hasColony()) {
                z3 = systemObject.getColony().hasDefences();
            }
        }
        if (z2 && !z3 && (GameProperties.isNonNormalEmpire(this.attackerID) || ((i3 = this.attackerID) >= 0 && i3 < 6 && this.game.empires.get(i3).isAI()))) {
            z = true;
        }
        if (z && this.game.fleets.isFleetInSystem(this.defenderID, this.systemID)) {
            Fleet fleetInSystem = this.game.fleets.getFleetInSystem(this.defenderID, this.systemID);
            ArrayList<String> arrayList = new ArrayList();
            for (Ship ship : fleetInSystem.getNonCombatShips()) {
                this.defenderLosses.addLoss(ship);
                arrayList.add(ship.getID());
            }
            for (String str : arrayList) {
                fleetInSystem.removeShip(str);
            }
            if (fleetInSystem.isEmpty()) {
                this.game.fleets.remove(fleetInSystem);
            }
        }
        if (z2 && !z3) {
            i2 = this.attackerID;
        } else if (z3 && !z2) {
            i2 = this.defenderID;
        } else {
            i = -1;
            Log.message("AutoBattle", "WinnerID: " + i);
            this.game.attackScene.showAutoResult(i, this.attackerID, this.defenderID, this.attackerLosses, this.defenderLosses);
        }
        i = i2;
        Log.message("AutoBattle", "WinnerID: " + i);
        this.game.attackScene.showAutoResult(i, this.attackerID, this.defenderID, this.attackerLosses, this.defenderLosses);
    }

    private void spacialChargeAttack(Weapon weapon, Point point) {
        SpacialCharge spacialChargeFired = this.battleGrid.spacialChargeFired(weapon.getID(), this.battleGrid.getSelectedHex(), point, weapon.getComponentCount(), this.battleGrid.getSelectedShip().getEmpireID());
        weapon.setAllWeaponsUsed();
        this.battleGrid.moveCharge(spacialChargeFired);
        if (this.battleGrid.checkIfChargeReachedTarget(spacialChargeFired.getID())) {
            spacialChargeExplode(spacialChargeFired);
        }
    }

    private void spacialChargeDone() {
        Ship selectedShip = this.battleGrid.getSelectedShip();
        if (selectedShip instanceof SpacialCharge) {
            shipDone();
        } else {
            AIMoveDone(selectedShip);
        }
    }

    private void spacialChargeExplode(SpacialCharge spacialCharge) {
        if (this.battleGrid.hasShip(spacialCharge.getTargetPosition())) {
            Ship ship = this.battleGrid.getShip(spacialCharge.getTargetPosition());
            if (!ship.hasBeenDestroyed()) {
                shipHitWithSpacialCharge(spacialCharge, ship);
            }
        }
        for (Point point : this.battleGrid.getNodesInRange(spacialCharge.getTargetPosition(), 1)) {
            if (this.battleGrid.hasShip(point)) {
                Ship ship2 = this.battleGrid.getShip(point);
                if (!ship2.hasBeenDestroyed()) {
                    shipHitWithSpacialCharge(spacialCharge, ship2);
                }
            }
        }
        this.battleGrid.removeSpacialCharge(spacialCharge.getID());
    }

    private void spacialChargeMoveDone(SpacialCharge spacialCharge) {
        if (this.battleGrid.checkIfChargeReachedTarget(spacialCharge.getID())) {
            spacialChargeExplode(spacialCharge);
        } else {
            spacialChargeDone();
        }
    }

    private void torpedoAttack(Weapon weapon, Point point) {
        String str = this.battleGrid.torpedoFired(weapon.getID(), this.battleGrid.getSelectedHex(), this.battleGrid.getShip(point).getID(), weapon.getComponentCount(), this.battleGrid.getSelectedShip().getEmpireID());
        weapon.setAllWeaponsUsed();
        BattleGrid battleGrid = this.battleGrid;
        battleGrid.moveTorpedo(battleGrid.getTorpedo(str));
        if (this.battleGrid.checkTorpedoForHit(str)) {
            torpedoHitShip(this.battleGrid.getTorpedo(str));
        }
    }

    private void torpedoDone() {
        Ship selectedShip = this.battleGrid.getSelectedShip();
        if (selectedShip instanceof Torpedo) {
            shipDone();
        } else {
            AIMoveDone(selectedShip);
        }
    }

    private void torpedoHitShip(Torpedo torpedo) {
        this.battleGrid.getTorpedoDamage(torpedo.getID());
        this.battleGrid.removeTorpedo(torpedo.getID());
        Ship ship = this.battleGrid.getShip(torpedo.getTargetID());
        if (ship.isAlive()) {
            return;
        }
        shipDestroyed(ship);
    }

    private void torpedoMoveDone(Torpedo torpedo) {
        if (this.battleGrid.checkTorpedoForHit(torpedo.getID())) {
            torpedoHitShip(torpedo);
        } else {
            torpedoDone();
        }
    }

    @Override // com.birdshel.Uciana.Elements.Battle.BattleCallBack
    public void attackShip(Point point, Weapon weapon) {
        Ship selectedShip = this.battleGrid.getSelectedShip();
        Ship ship = this.battleGrid.getShip(point);
        while (weapon.getAvailableCount() != 0 && ship.isAlive()) {
            switch (weapon.getType()){
                case NONE:
                    break;
                case BEAM:
                    beamAttack(weapon, selectedShip, ship);
                    break;
                case BOMB:
                    break;
                case TORPEDO:
                    torpedoAttack(weapon, point);
                    break;
                case SPACIAL_CHARGE:
                    spacialChargeAttack(weapon, point);
                    break;
            }

        }
        AIMoveDone(selectedShip);
    }
/*    *//* compiled from: MyApplication *//*
    *//* renamed from: com.birdshel.Uciana.AI.AutoBattle$1  reason: invalid class name *//*
    *//* loaded from: classes.dex *//*
    static *//* synthetic *//* class AnonymousClass1 {

        *//* renamed from: a  reason: collision with root package name *//*
        static final *//* synthetic *//* int[] f1336a;

        static {
            int[] iArr = new int[WeaponType.values().length];
            f1336a = iArr;
            try {
                iArr[WeaponType.BEAM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1336a[WeaponType.TORPEDO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1336a[WeaponType.SPACIAL_CHARGE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
    int i = AnonymousClass1.f1336a[weapon.getType().ordinal()];
            if (i == 1) {
        beamAttack(weapon, selectedShip, ship);
    } else if (i == 2) {
        torpedoAttack(weapon, point);
    } else if (i == 3) {
        spacialChargeAttack(weapon, point);
    }*/
    @Override // com.birdshel.Uciana.Elements.Battle.BattleCallBack
    public void moveShip(Point point) {
        Log.message("AutoBattle", "Move ship: " + this.battleGrid.getSelectedShip().getID());
        Ship selectedShip = this.battleGrid.getSelectedShip();
        this.battleGrid.shipMoved(selectedShip, point);
        selectedShip.setBattleLocation(new Point(point.getX(), point.getY()));
        selectedShip.movementUsed(Functions.getHexDistance(this.battleGrid.getSelectedX(), this.battleGrid.getSelectedY(), (int) point.getX(), (int) point.getY()));
        AIMoveDone(selectedShip);
    }

    @Override // com.birdshel.Uciana.Elements.Battle.BattleCallBack
    public void selectNextShip() {
    }

    @Override // com.birdshel.Uciana.Elements.Battle.BattleCallBack
    public void selfDestruct(Ship ship) {
        Log.message("AutoBattle", "Ship self destructed: " + ship.getID());
        if (ship.getEmpireID() == this.attackerID) {
            this.attackerLosses.addLoss(ship);
        } else {
            this.defenderLosses.addLoss(ship);
        }
        ship.setDestroyed();
        shipCoreBreach(ship);
        this.battleGrid.shipDestroyed(ship);
    }

    public void set(int i, int i2, int i3) {
        set(i, -1, i2, i3);
    }

    @Override // com.birdshel.Uciana.Elements.Battle.BattleCallBack
    public void shipDone() {
        this.battleGrid.addDoneShip();
    }

    @Override // com.birdshel.Uciana.Elements.Battle.BattleCallBack
    public void shipRetreat(Ship ship) {
        this.battleGrid.addRetreatingShip(ship);
        shipDone();
    }

    @Override // com.birdshel.Uciana.Elements.Battle.BattleCallBack
    public void shipWait() {
        this.battleGrid.addWaitingShip();
    }

    public void set(int i, int i2, int i3, int i4) {
        this.systemID = i;
        this.orbit = i2;
        this.attackerID = i3;
        this.defenderID = i4;
        this.battleGrid = new BattleGrid(this.game, i, i2, i3, i4);
        if (GameProperties.isNonNormalEmpire(i3)) {
            this.attackerBattleAI = new BattleAI();
        } else {
            this.attackerBattleAI = this.game.empires.get(i3).getBattleAI();
        }
        this.attackerBattleAI.set(this.battleGrid, this);
        if (GameProperties.isNonNormalEmpire(i4)) {
            this.defenderBattleAI = new BattleAI();
        } else {
            this.defenderBattleAI = this.game.empires.get(i4).getBattleAI();
        }
        this.defenderBattleAI.set(this.battleGrid, this);
        placeShips(this.battleGrid.getAttackingFleet(), GameProperties.STARTING_ATTACK_POSITIONS);
        placeShips(this.battleGrid.getDefendingFleet(), GameProperties.STARTING_DEFENSE_POSITIONS);
        Log.message("AutoBattle", "Battle Started at systemID: " + i);
        doBattle();
    }
}
