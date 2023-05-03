package com.birdshel.Uciana.Elements.Battle;

import com.birdshel.Uciana.Colonies.Buildings.BuildingID;
import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Elements.BattleScene.Grid;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.GameProperties;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.Ships.MovingWeapon;
import com.birdshel.Uciana.Ships.Ship;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponentID;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponents;
import com.birdshel.Uciana.Ships.ShipComponents.Weapon;
import com.birdshel.Uciana.Ships.ShipStatus;
import com.birdshel.Uciana.Ships.SpacialCharge;
import com.birdshel.Uciana.Ships.StarBase;
import com.birdshel.Uciana.Ships.Torpedo;
import com.birdshel.Uciana.Ships.TorpedoTurret;
import com.birdshel.Uciana.Utility.Log;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class BattleGrid {
    private final Fleet attackingFleet;
    private final int currentSystem;
    private final Fleet defendingFleet;
    private final Game game;
    private final int orbit;
    private Ship selectedShip;
    private StarBase starBase;
    private final int systemID;
    private TorpedoTurret turret1;
    private TorpedoTurret turret2;
    private final Grid grid = new Grid();
    private final Hex[][] hexGrid = (Hex[][]) Array.newInstance(Hex.class, 15, 7);
    private final Stack<Ship> shipsWaiting = new Stack<>();
    private final Stack<Ship> shipsDone = new Stack<>();
    private final Set<String> shipsRetreating = new HashSet();
    private final Set<String> shipsWaitingToRetreat = new HashSet();
    private final Map<String, Torpedo> torpedoes = new HashMap();
    private final Map<String, SpacialCharge> charges = new HashMap();
    private List<Point> asteroidLocations = new ArrayList();
    private final List<Point> ionStormLocations = new ArrayList();
    private final List<Ship> ships = new ArrayList();
    private int torpedoID = 0;
    private int chargeID = 0;
    private int turn = 0;

    /* JADX WARN: Code restructure failed: missing block: B:39:0x015a, code lost:
        if (r0 == 21) goto L41;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public BattleGrid(Game game, int i, int i2, int i3, int i4) {
        this.game = game;
        this.systemID = i;
        this.orbit = i2;
        int i5 = 0;
        for (int i6 = 0; i6 < 7; i6++) {
            for (int i7 = 0; i7 < 15; i7++) {
                this.hexGrid[i7][i6] = new Hex();
            }
        }
        Fleet fleetInSystem = GameData.fleets.getFleetInSystem(i3, i);
        this.attackingFleet = fleetInSystem;
        if (GameData.fleets.isFleetInSystem(i4, i)) {
            this.defendingFleet = GameData.fleets.getFleetInSystem(i4, i);
        } else {
            this.defendingFleet = new Fleet(i4, i);
        }
        int i8 = 0;
        for (Ship ship : fleetInSystem.getCombatShips()) {
            this.shipsWaiting.add(ship);
            this.ships.add(ship);
            i8++;
            if (i8 == 21) {
                break;
            }
        }
        if (i2 != -1 && game.colonies.isColony(i, i2)) {
            Colony colony = game.colonies.getColony(i, i2);
            if (colony.getEmpireID() == i4 && colony.hasBuilding(BuildingID.STAR_BASE)) {
                StarBase starBase = new StarBase(i4);
                this.starBase = starBase;
                this.shipsWaiting.add(starBase);
                this.ships.add(this.starBase);
                i5 = 1;
            } else {
                this.starBase = null;
            }
            if (colony.getEmpireID() == i4 && colony.hasBuilding(BuildingID.TORPEDO_TURRET)) {
                TorpedoTurret torpedoTurret = new TorpedoTurret(i4, "turret-1");
                this.turret1 = torpedoTurret;
                this.shipsWaiting.add(torpedoTurret);
                this.ships.add(this.turret1);
                TorpedoTurret torpedoTurret2 = new TorpedoTurret(i4, "turret-2");
                this.turret2 = torpedoTurret2;
                this.shipsWaiting.add(torpedoTurret2);
                this.ships.add(this.turret2);
            } else {
                this.turret1 = null;
                this.turret2 = null;
            }
        }
        for (Ship ship2 : this.defendingFleet.getCombatShips()) {
            this.shipsWaiting.add(ship2);
            this.ships.add(ship2);
            i5++;
        }
        try {
            Collections.sort(this.shipsWaiting);
        } catch (Exception unused) {
            Log.message("BattleGrid", "Crash sorting waiting ships");
        }
        Iterator<Ship> it = this.shipsWaiting.iterator();
        while (it.hasNext()) {
            it.next().reset();
        }
        this.currentSystem = this.attackingFleet.getSystemID();
        setupAsteroids();
        setupIonStorms();
    }

    private boolean canEmpireStillFight(int i) {
        return (getShips(i).isEmpty() && this.shipsWaitingToRetreat.isEmpty() && this.shipsRetreating.isEmpty()) ? false : true;
    }

    private boolean checkForIonStormInPath(Point point, Point point2) {
        Point position = this.grid.getPosition((int) point.getX(), (int) point.getY());
        position.setX(position.getX() + 50.0f);
        position.setY(position.getY() + 50.0f);
        Point position2 = this.grid.getPosition((int) point2.getX(), (int) point2.getY());
        position2.setX(position2.getX() + 50.0f);
        position2.setY(position2.getY() + 50.0f);
        for (Point point3 : getIonStormLocations()) {
            Point position3 = this.grid.getPosition((int) point3.getX(), (int) point3.getY());
            position3.setX(position3.getX() + 10.0f);
            position3.setY(position3.getY() + 10.0f);
            if (Functions.doesLineInterceptSquare(position, position2, position3, 80)) {
                return true;
            }
        }
        return false;
    }

    private void checkForTorpedoToSelfDestruct(String str, Ship ship) {
        if (ship instanceof Torpedo) {
            Torpedo torpedo = (Torpedo) ship;
            if (torpedo.getTargetID().equals(str)) {
                torpedo.setSelfDestruct();
            }
        }
    }

    private Point getNextPosition(Point point, Point point2, int i) {
        if (i > point.getDistance(point2)) {
            return new Point(point2.getX(), point2.getY());
        }
        double atan2 = (float) Math.atan2(point.getY() - point2.getY(), point.getX() - point2.getX());
        float f2 = i;
        return new Point(point.getX() - (((float) Math.cos(atan2)) * f2), point.getY() - (((float) Math.sin(atan2)) * f2));
    }

    private List<SpacialCharge> getSpacialCharges() {
        ArrayList arrayList = new ArrayList();
        Iterator<Ship> it = this.shipsWaiting.iterator();
        while (it.hasNext()) {
            Ship next = it.next();
            if (next instanceof SpacialCharge) {
                arrayList.add((SpacialCharge) next);
            }
        }
        Iterator<Ship> it2 = this.shipsDone.iterator();
        while (it2.hasNext()) {
            Ship next2 = it2.next();
            if (next2 instanceof SpacialCharge) {
                arrayList.add((SpacialCharge) next2);
            }
        }
        Ship ship = this.selectedShip;
        if (ship != null && (ship instanceof SpacialCharge)) {
            arrayList.add((SpacialCharge) ship);
        }
        return arrayList;
    }

    private List<Torpedo> getTorpedoes() {
        ArrayList arrayList = new ArrayList();
        Iterator<Ship> it = this.shipsWaiting.iterator();
        while (it.hasNext()) {
            Ship next = it.next();
            if (next instanceof Torpedo) {
                arrayList.add((Torpedo) next);
            }
        }
        Iterator<Ship> it2 = this.shipsDone.iterator();
        while (it2.hasNext()) {
            Ship next2 = it2.next();
            if (next2 instanceof Torpedo) {
                arrayList.add((Torpedo) next2);
            }
        }
        Ship ship = this.selectedShip;
        if (ship != null && (ship instanceof Torpedo)) {
            arrayList.add((Torpedo) ship);
        }
        return arrayList;
    }

    private void removeNode(int i, int i2) {
        this.grid.removeNode(i, i2);
    }

    private void removeShipFromGame(Ship ship) {
        if (this.attackingFleet.hasShip(ship.getID())) {
            this.attackingFleet.removeShip(ship.getID());
            if (this.attackingFleet.isEmpty()) {
                GameData.fleets.remove(this.attackingFleet);
            }
        } else if (ship instanceof StarBase) {
            this.starBase = null;
            this.game.colonies.getColony(this.systemID, this.orbit).removeBuilding(BuildingID.STAR_BASE);
        } else if (ship instanceof TorpedoTurret) {
            if (ship.getID().equals("turret-1")) {
                this.turret1 = null;
            } else {
                this.turret2 = null;
            }
            if (this.turret1 == null && this.turret2 == null) {
                this.game.colonies.getColony(this.systemID, this.orbit).removeBuilding(BuildingID.TORPEDO_TURRET);
            }
        } else {
            this.defendingFleet.removeShip(ship.getID());
            if (this.defendingFleet.isEmpty()) {
                GameData.fleets.remove(this.defendingFleet);
            }
        }
    }

    private void removeShipFromHexGrid(String str) {
        for (int i = 0; i < 7; i++) {
            for (int i2 = 0; i2 < 15; i2++) {
                if (str.equals(this.hexGrid[i2][i].getObject())) {
                    this.hexGrid[i2][i].e();
                }
            }
        }
    }

    private void retreatShip(Ship ship) {
        ship.addStatus(ShipStatus.RETREAT);
        if (this.shipsWaiting.contains(ship)) {
            this.shipsRetreating.add(ship.getID());
            this.shipsWaiting.remove(ship);
            this.shipsDone.add(ship);
            return;
        }
        this.shipsWaitingToRetreat.add(ship.getID());
    }

    private void retreatShipsThatRetreated(Fleet fleet) {
        if (GameProperties.isNonNormalEmpire(fleet.empireID)) {
            fleet.resetStatus();
            return;
        }
        Empire empire = this.game.empires.get(fleet.empireID);
        int closestEmpireSystem = this.game.galaxy.getClosestEmpireSystem(this.currentSystem, empire.getFriendlyStarSystems());
        if (this.currentSystem == closestEmpireSystem) {
            fleet.resetStatus();
            return;
        }
        ArrayList<Ship> arrayList = new ArrayList();
        for (Ship ship : fleet.getShips()) {
            if (ship.hasRetreated()) {
                arrayList.add(ship);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        if (arrayList.size() == fleet.getSize()) {
            fleet.move(closestEmpireSystem);
            fleet.set(true, false);
            return;
        }
        Fleet fleet2 = new Fleet(empire.id, fleet.getOriginSystem());
        this.game.fleets.add(fleet2);
        fleet2.setPosition(new Point(fleet.getPosition().getX(), fleet.getPosition().getY()));
        fleet2.getShips().addAll(arrayList);
        for (Ship ship2 : arrayList) {
            fleet.removeShip(ship2.getID());
        }
        fleet2.move(closestEmpireSystem);
        fleet2.set(true, false);
    }

    private void setTorpedoesToSelfDestruct(String str) {
        checkForTorpedoToSelfDestruct(str, this.selectedShip);
        Iterator<Ship> it = this.shipsDone.iterator();
        while (it.hasNext()) {
            checkForTorpedoToSelfDestruct(str, it.next());
        }
        Iterator<Ship> it2 = this.shipsWaiting.iterator();
        while (it2.hasNext()) {
            checkForTorpedoToSelfDestruct(str, it2.next());
        }
    }

    private void setupAsteroids() {
        this.asteroidLocations = new ArrayList();
        for (int i = 0; i < Functions.random.nextInt(10); i++) {
            int nextInt = Functions.random.nextInt(8) + 3;
            int nextInt2 = Functions.random.nextInt(7);
            this.asteroidLocations.add(new Point(nextInt, nextInt2));
            removeNode(nextInt, nextInt2);
        }
    }

    private void setupIonStorms() {
        this.ionStormLocations.clear();
        for (int i = 0; i < Functions.random.nextInt(7); i++) {
            this.ionStormLocations.add(new Point(Functions.random.nextInt(15), Functions.random.nextInt(7)));
        }
    }

    public void addDoneShip() {
        Ship ship = this.selectedShip;
        if (!(ship instanceof Torpedo) && !(ship instanceof SpacialCharge)) {
            if (this.ships.contains(ship)) {
                this.shipsDone.add(this.selectedShip);
                return;
            }
            return;
        }
        this.shipsDone.add(ship);
    }

    public void addRetreatingShip(Ship ship) {
        this.shipsRetreating.add(ship.getID());
    }

    public void addWaitingShip() {
        this.shipsWaiting.add(0, getSelectedShip());
    }

    public boolean canAttack(Point point) {
        return this.hexGrid[(int) point.getX()][(int) point.getY()].a();
    }

    public boolean canMove(Point point) {
        return this.hexGrid[(int) point.getX()][(int) point.getY()].b();
    }

    public boolean canSpecial(Point point) {
        return this.hexGrid[(int) point.getX()][(int) point.getY()].c();
    }

    public boolean checkIfChargeReachedTarget(String str) {
        SpacialCharge spacialCharge = this.charges.get(str);
        Point position = this.grid.getPosition((int) spacialCharge.getTargetPosition().getX(), (int) spacialCharge.getTargetPosition().getY());
        position.setX(position.getX() + 30.0f);
        position.setY(position.getY() + 30.0f);
        return spacialCharge.getPosition().equals(position);
    }

    public ShipStatus checkShipStatusNeedsToBeAdded(Ship ship) {
        if (isStation(ship)) {
            return ShipStatus.NONE;
        }
        if (ship.getShieldHitPoints() > 0) {
            return ShipStatus.NONE;
        }
        if (ship.getArmorPercent() > 50) {
            return ShipStatus.NONE;
        }
        if (!ship.canGoFTL() && !ship.canMove()) {
            return ShipStatus.NONE;
        }
        if (Functions.percent((int) ((100 - (ship.getArmorPercent() * 2)) * 0.01f * 80.0f))) {
            ArrayList arrayList = new ArrayList();
            if (ship.canMove()) {
                arrayList.add(ShipStatus.SUBLIGHT_DISABLED);
            }
            if (ship.canGoFTL()) {
                arrayList.add(ShipStatus.FTL_DISABLED);
            }
            if (arrayList.isEmpty()) {
                return ShipStatus.NONE;
            }
            ShipStatus shipStatus = (ShipStatus) arrayList.get(Functions.random.nextInt(arrayList.size()));
            ship.addStatus(shipStatus);
            if (shipStatus == ShipStatus.FTL_DISABLED && ship.hasRetreated()) {
                ship.removeStatus(ShipStatus.RETREAT);
                if (this.shipsWaitingToRetreat.contains(ship.getID())) {
                    this.shipsWaitingToRetreat.remove(ship.getID());
                    this.shipsDone.add(ship);
                }
                if (this.shipsRetreating.contains(ship.getID())) {
                    this.shipsRetreating.remove(ship.getID());
                    this.shipsDone.add(ship);
                }
            }
            return shipStatus;
        }
        return ShipStatus.NONE;
    }

    public List<String> checkSpacialChargesInRangeOfShockwave(Point point) {
        ArrayList arrayList = new ArrayList();
        Point position = this.grid.getPosition((int) point.getX(), (int) point.getY());
        position.setX(position.getX() + 50.0f);
        position.setY(position.getY() + 50.0f);
        for (Map.Entry<String, SpacialCharge> entry : this.charges.entrySet()) {
            if (position.getDistance(entry.getValue().getPosition()) < 125 && Functions.percent(50)) {
                arrayList.add(entry.getKey());
            }
        }
        return arrayList;
    }

    public boolean checkTorpedoForHit(String str) {
        Torpedo torpedo = this.torpedoes.get(str);
        if (hasShip(torpedo.getTargetID())) {
            Point battleLocation = getShip(torpedo.getTargetID()).getBattleLocation();
            Point position = this.grid.getPosition((int) battleLocation.getX(), (int) battleLocation.getY());
            position.setX(position.getX() + 30.0f);
            position.setY(position.getY() + 30.0f);
            return torpedo.getPosition().equals(position);
        }
        return false;
    }

    public List<String> checkTorpedoesInRangeOfShockwave(Point point) {
        ArrayList arrayList = new ArrayList();
        Point position = this.grid.getPosition((int) point.getX(), (int) point.getY());
        position.setX(position.getX() + 50.0f);
        position.setY(position.getY() + 50.0f);
        for (Map.Entry<String, Torpedo> entry : this.torpedoes.entrySet()) {
            if (position.getDistance(entry.getValue().getPosition()) < 125 && Functions.percent(50)) {
                arrayList.add(entry.getKey());
            }
        }
        return arrayList;
    }

    public void clear() {
        for (int i = 0; i < 7; i++) {
            for (int i2 = 0; i2 < 15; i2++) {
                this.hexGrid[i2][i].e();
            }
        }
    }

    public void clearAttacks() {
        for (int i = 0; i < 7; i++) {
            for (int i2 = 0; i2 < 15; i2++) {
                this.hexGrid[i2][i].setAttack(false);
            }
        }
    }

    public void clearMoves() {
        for (int i = 0; i < 7; i++) {
            for (int i2 = 0; i2 < 15; i2++) {
                this.hexGrid[i2][i].setMove(false);
            }
        }
    }

    public void clearShipFromLists(Ship ship) {
        if (ship == null) {
            return;
        }
        Ship ship2 = this.selectedShip;
        if (ship2 != null && ship2.getID().equals(ship.getID())) {
            this.selectedShip = null;
        }
        this.ships.remove(ship);
        this.shipsWaiting.remove(ship);
        this.shipsWaitingToRetreat.remove(ship.getID());
        this.shipsRetreating.remove(ship.getID());
        this.shipsDone.remove(ship);
    }

    public void endTurn() {
        Log.message("BattleGrid", "End Turn");
        this.turn++;
        Iterator<Ship> it = this.shipsDone.iterator();
        while (it.hasNext()) {
            it.next().reset();
        }
        while (!this.shipsDone.isEmpty()) {
            this.shipsWaiting.add(this.shipsDone.pop());
        }
    }

    public List<Point> getAsteroidLocations() {
        return this.asteroidLocations;
    }

    public Fleet getAttackingFleet() {
        return this.attackingFleet;
    }

    public int getChanceToHit(Ship ship, Ship ship2) {
        float pow = ((int) (100.0d / (Math.pow(2.0d, (-(((ship.getTargetAccuracyBonus() + 25) - (Functions.getHexDistance(ship.getBattleLocation(), ship2.getBattleLocation()) * 15)) - ship2.getEvasionBonus())) / 16.0f) + 1.0d))) + 25.0f;
        if (pow > 100.0f) {
            pow = 100.0f;
        }
        if (pow < 50.0f) {
            pow += 15.0f;
        }
        if (checkForIonStormInPath(ship.getBattleLocation(), ship2.getBattleLocation())) {
            pow /= 2.0f;
        }
        return (int) pow;
    }

    public Fleet getDefendingFleet() {
        return this.defendingFleet;
    }

    public List<Ship> getEnemyShips(int i) {
        ArrayList arrayList = new ArrayList();
        for (Ship ship : this.ships) {
            if (ship.getEmpireID() != i) {
                arrayList.add(ship);
            }
        }
        return arrayList;
    }

    public Point getIntersectionPoint(MovingWeapon movingWeapon, Point point, Point point2) {
        return Functions.getIntersectionPoint(point, point2, movingWeapon.getInterceptionPosition(), 80, movingWeapon.getInterceptionSide());
    }

    public List<Point> getIonStormLocations() {
        return this.ionStormLocations;
    }

    public Ship getNextShip() {
        Ship pop = this.shipsWaiting.pop();
        this.selectedShip = pop;
        return pop;
    }

    public Set<Point> getNodesInRange(int i) {
        return this.grid.getNodesInRange(this.selectedShip.getBattleLocation(), i);
    }

    public String getObject(Point point) {
        return this.hexGrid[(int) point.getX()][(int) point.getY()].getObject();
    }

    public Point getPositionOfHex(Point point) {
        return this.grid.getPosition((int) point.getX(), (int) point.getY());
    }

    public List<Point> getPossibleMoves() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 7; i++) {
            for (int i2 = 0; i2 < 15; i2++) {
                if (this.hexGrid[i2][i].b()) {
                    arrayList.add(new Point(i2, i));
                }
            }
        }
        return arrayList;
    }

    public Point getSelectedHex() {
        return this.selectedShip.getBattleLocation();
    }

    public Ship getSelectedShip() {
        return this.selectedShip;
    }

    public int getSelectedX() {
        return (int) this.selectedShip.getBattleLocation().getX();
    }

    public int getSelectedY() {
        return (int) this.selectedShip.getBattleLocation().getY();
    }

    public Ship getShip(Point point) {
        return getShip(getObject(point));
    }

    public List<Ship> getShips() {
        return this.ships;
    }

    public Map<String, Integer> getShipsNearHex(Point point) {
        HashMap hashMap = new HashMap();
        for (Point point2 : this.grid.getNodesInRange(point, 1)) {
            if (this.hexGrid[(int) point2.getX()][(int) point2.getY()].d()) {
                Ship ship = getShip(point2);
                hashMap.put(ship.getID(), Integer.valueOf(ship.getEmpireID()));
            }
        }
        return hashMap;
    }

    public SpacialCharge getSpacialCharge(String str) {
        return this.charges.get(str);
    }

    public StarBase getStarBase() {
        return this.starBase;
    }

    public Torpedo getTorpedo(String str) {
        return this.torpedoes.get(str);
    }

    public Map<String, Object> getTorpedoDamage(String str) {
        HashMap hashMap = new HashMap();
        Torpedo torpedo = this.torpedoes.get(str);
        Ship ship = getShip(torpedo.getTargetID());
        Weapon weapon = (Weapon) ShipComponents.get(torpedo.getTorpedoType());
        boolean percent = Functions.percent(25);
        float f2 = percent ? 0.5f : 0.0f;
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < torpedo.getPayloadCount(); i3++) {
            float damage = weapon.getDamage();
            Point takeDamage = ship.takeDamage((int) (damage + (f2 * damage)));
            i = (int) (i + takeDamage.getX());
            i2 = (int) (i2 + takeDamage.getY());
            if (!ship.isAlive()) {
                break;
            }
        }
        ShipStatus checkShipStatusNeedsToBeAdded = checkShipStatusNeedsToBeAdded(ship);
        if (checkShipStatusNeedsToBeAdded != ShipStatus.NONE) {
            hashMap.put("status", checkShipStatusNeedsToBeAdded);
        }
        hashMap.put("damage", (percent ? this.game.getActivity().getString(R.string.battle_critical) + "\n" : "") + Integer.toString(i + i2));
        hashMap.put("shieldDamage", Integer.valueOf(i));
        hashMap.put("armorDamage", Integer.valueOf(i2));
        return hashMap;
    }

    public int getTurns() {
        return this.turn;
    }

    public TorpedoTurret getTurret(String str) {
        str.hashCode();
        if (str.equals("turret-1")) {
            return this.turret1;
        }
        if (str.equals("turret-2")) {
            return this.turret2;
        }
        throw new AssertionError("Invalid turret id");
    }

    public boolean hasLineOfSight(Point point, Point point2) {
        Point position = this.grid.getPosition((int) point.getX(), (int) point.getY());
        position.setX(position.getX() + 50.0f);
        position.setY(position.getY() + 50.0f);
        Point position2 = this.grid.getPosition((int) point2.getX(), (int) point2.getY());
        position2.setX(position2.getX() + 50.0f);
        position2.setY(position2.getY() + 50.0f);
        for (Point point3 : getAsteroidLocations()) {
            Point position3 = this.grid.getPosition((int) point3.getX(), (int) point3.getY());
            position3.setX(position3.getX() + 10.0f);
            position3.setY(position3.getY() + 10.0f);
            if (Functions.doesLineInterceptSquare(position, position2, position3, 80)) {
                return false;
            }
        }
        return true;
    }

    public boolean hasShip(Point point) {
        return hasShip(this.hexGrid[(int) point.getX()][(int) point.getY()].getObject());
    }

    public boolean hasStarbase() {
        return this.starBase != null;
    }

    public boolean isBattleDone() {
        int i;
        boolean canEmpireStillFight = canEmpireStillFight(getAttackingFleet().empireID);
        boolean canEmpireStillFight2 = canEmpireStillFight(getDefendingFleet().empireID);
        if (!canEmpireStillFight2 && (i = this.orbit) != -1 && this.game.colonies.isColony(this.systemID, i)) {
            canEmpireStillFight2 = this.game.colonies.getColony(this.systemID, this.orbit).hasDefences();
        }
        if (canEmpireStillFight && canEmpireStillFight2) {
            return false;
        }
        boolean z = false;
        boolean z2 = false;
        for (Torpedo torpedo : getTorpedoes()) {
            if (torpedo.getEmpireID() == this.attackingFleet.empireID) {
                z2 = true;
            } else {
                z = true;
            }
            if (canEmpireStillFight && z) {
                return false;
            }
            if (canEmpireStillFight2 && z2) {
                return false;
            }
        }
        boolean z3 = false;
        boolean z4 = false;
        for (SpacialCharge spacialCharge : getSpacialCharges()) {
            if (spacialCharge.getEmpireID() == this.attackingFleet.empireID) {
                z4 = true;
            } else {
                z3 = true;
            }
            if (canEmpireStillFight && z3) {
                return false;
            }
            if (canEmpireStillFight2 && z4) {
                return false;
            }
        }
        return true;
    }

    public boolean isLineOfSightBlocked(MovingWeapon movingWeapon, Point point, Point point2) {
        for (Point point3 : getAsteroidLocations()) {
            Point position = this.grid.getPosition((int) point3.getX(), (int) point3.getY());
            position.setX(position.getX() + 10.0f);
            position.setY(position.getY() + 10.0f);
            if (Functions.doesLineInterceptSquare(point, point2, position, 80)) {
                movingWeapon.setInterceptionPosition(position);
                movingWeapon.setInterceptionSide(Functions.intersectionSide);
                return true;
            }
        }
        return false;
    }

    public boolean isShipAlive(String str) {
        Ship ship = this.selectedShip;
        if (ship == null || !ship.getID().equals(str)) {
            if (str.equals("starbase")) {
                Iterator<Ship> it = this.shipsWaiting.iterator();
                while (it.hasNext()) {
                    if (it.next() instanceof StarBase) {
                        return true;
                    }
                }
                Iterator<Ship> it2 = this.shipsDone.iterator();
                while (it2.hasNext()) {
                    if (it2.next() instanceof StarBase) {
                        return true;
                    }
                }
                return false;
            } else if (!str.startsWith("turret")) {
                return this.attackingFleet.hasShip(str) || this.defendingFleet.hasShip(str);
            } else {
                Iterator<Ship> it3 = this.shipsWaiting.iterator();
                while (it3.hasNext()) {
                    Ship next = it3.next();
                    if ((next instanceof TorpedoTurret) && next.getID().equals(str)) {
                        return true;
                    }
                }
                Iterator<Ship> it4 = this.shipsDone.iterator();
                while (it4.hasNext()) {
                    Ship next2 = it4.next();
                    if ((next2 instanceof TorpedoTurret) && next2.getID().equals(str)) {
                        return true;
                    }
                }
                return false;
            }
        }
        return true;
    }

    public boolean isShipListEmpty() {
        return this.shipsWaiting.isEmpty();
    }

    public boolean isShipRetreating() {
        return this.shipsRetreating.contains(this.selectedShip.getID());
    }

    public boolean isShipWaitingToRetreat() {
        return this.shipsWaitingToRetreat.contains(this.selectedShip.getID());
    }

    public boolean isStation(Ship ship) {
        return (ship instanceof StarBase) || (ship instanceof TorpedoTurret);
    }

    public Point moveCharge(SpacialCharge spacialCharge) {
        Point position = this.grid.getPosition((int) spacialCharge.getTargetPosition().getX(), (int) spacialCharge.getTargetPosition().getY());
        position.setX(position.getX() + 30.0f);
        position.setY(position.getY() + 30.0f);
        Point nextPosition = getNextPosition(spacialCharge.getPosition(), position, spacialCharge.getInfo().getSpeed());
        spacialCharge.setPosition(nextPosition);
        return nextPosition;
    }

    public void moveShipFromWaitingToRetreat() {
        this.shipsWaitingToRetreat.remove(this.selectedShip.getID());
        this.shipsRetreating.add(this.selectedShip.getID());
    }

    public Point moveTorpedo(Torpedo torpedo) {
        Point position = torpedo.getPosition();
        if (hasShip(torpedo.getTargetID())) {
            Point battleLocation = getShip(torpedo.getTargetID()).getBattleLocation();
            position = this.grid.getPosition((int) battleLocation.getX(), (int) battleLocation.getY());
        }
        position.setX(position.getX() + 30.0f);
        position.setY(position.getY() + 30.0f);
        Point nextPosition = getNextPosition(torpedo.getPosition(), position, torpedo.getInfo().getSpeed());
        torpedo.setPosition(nextPosition);
        return nextPosition;
    }

    public void removeSpacialCharge(String str) {
        Log.message("BattleGrid", "Remove charge: " + str);
        this.shipsWaiting.remove(this.charges.get(str));
        this.shipsDone.remove(this.charges.get(str));
        this.charges.remove(str);
    }

    public void removeTorpedo(String str) {
        Log.message("BattleGrid", "Remove torpedo: " + str);
        this.shipsWaiting.remove(this.torpedoes.get(str));
        this.shipsDone.remove(this.torpedoes.get(str));
        this.torpedoes.remove(str);
    }

    public void retreatShips(int i) {
        for (Ship ship : this.ships) {
            if (!isStation(ship) && !(ship instanceof Torpedo) && ship.canGoFTL() && ship.getEmpireID() == i && !ship.getStatuses().contains(ShipStatus.RETREAT)) {
                retreatShip(ship);
            }
        }
    }

    public void sendRetreatingShipsToNearestSystem() {
        retreatShipsThatRetreated(getAttackingFleet());
        retreatShipsThatRetreated(getDefendingFleet());
    }

    public void setAttackHex(int i, int i2, boolean z) {
        this.hexGrid[i][i2].setAttack(z);
    }

    public void setMoveHex(int i, int i2, boolean z) {
        this.hexGrid[i][i2].setMove(z);
    }

    public void setObject(int i, int i2, String str) {
        this.hexGrid[i][i2].setObject(str);
    }

    public void setSelectedShip(Ship ship) {
        this.selectedShip = ship;
    }

    public void setSpecialHex(int i, int i2, boolean z) {
        this.hexGrid[i][i2].setSpecial(z);
    }

    public void shipDestroyed(Ship ship) {
        int i;
        if (ship.getEmpireID() == 8) {
            boolean z = false;
            int i2 = this.attackingFleet.empireID;
            if (i2 == 8 ? !((i = this.defendingFleet.empireID) == 8 || !this.game.empires.get(i).isHuman()) : this.game.empires.get(i2).isHuman()) {
                z = true;
            }
            if (z) {
                Game.gameAchievements.ascendedShipDestroyed();
            }
        }
        removeShipFromHexGrid(ship.getID());
        clearShipFromLists(ship);
        removeShipFromGame(ship);
        setTorpedoesToSelfDestruct(ship.getID());
    }

    public void shipMoved(Ship ship, Point point) {
        removeShipFromHexGrid(ship.getID());
        ship.setBattleLocation(new Point(point.getX(), point.getY()));
        this.hexGrid[(int) point.getX()][(int) point.getY()].setObject(ship.getID());
    }

    public void shipRetreated(Ship ship) {
        ship.retreatedFromBattle();
        removeShipFromHexGrid(ship.getID());
        clearShipFromLists(ship);
        setTorpedoesToSelfDestruct(ship.getID());
    }

    public SpacialCharge spacialChargeFired(ShipComponentID shipComponentID, Point point, Point point2, int i, int i2) {
        String str = "charge-" + this.chargeID;
        this.chargeID++;
        Point position = this.grid.getPosition((int) point.getX(), (int) point.getY());
        position.setX(position.getX() + 30.0f);
        position.setY(position.getY() + 30.0f);
        SpacialCharge spacialCharge = new SpacialCharge(str, shipComponentID, point2, position, i, i2);
        this.charges.put(str, spacialCharge);
        this.shipsDone.add(spacialCharge);
        Log.message("BattleGrid", "Charge: " + str + " fired at position " + point2.toString());
        return spacialCharge;
    }

    public String torpedoFired(ShipComponentID shipComponentID, Point point, String str, int i, int i2) {
        String str2 = "torpedo-" + this.torpedoID;
        this.torpedoID++;
        Point position = this.grid.getPosition((int) point.getX(), (int) point.getY());
        position.setX(position.getX() + 30.0f);
        position.setY(position.getY() + 30.0f);
        Torpedo torpedo = new Torpedo(str2, shipComponentID, str, position, i, i2);
        this.torpedoes.put(str2, torpedo);
        this.shipsDone.add(torpedo);
        Log.message("BattleGrid", "Torpedo: " + str2 + " fired at " + str);
        return str2;
    }

    public Set<Point> getNodesInRange(Point point, int i) {
        return this.grid.getNodesInRange(point, i);
    }

    public List<Ship> getShips(int i) {
        ArrayList arrayList = new ArrayList();
        for (Ship ship : this.ships) {
            if (ship.getEmpireID() == i) {
                arrayList.add(ship);
            }
        }
        return arrayList;
    }

    public Ship getShip(String str) {
        for (Ship ship : this.ships) {
            if (str.equals(ship.getID())) {
                return ship;
            }
        }
        throw new AssertionError("Ship ID: " + str + " was not in the ships list");
    }

    public boolean hasShip(String str) {
        for (Ship ship : this.ships) {
            if (ship.getID().equals(str)) {
                return true;
            }
        }
        return false;
    }
}
