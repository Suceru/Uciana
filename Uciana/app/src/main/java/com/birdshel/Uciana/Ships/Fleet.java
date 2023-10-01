package com.birdshel.Uciana.Ships;

import com.birdshel.Uciana.Events.EventType;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.GameProperties;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponent;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponentID;
import com.birdshel.Uciana.Ships.ShipComponents.Weapon;
import com.birdshel.Uciana.Ships.ShipComponents.WeaponType;
import com.birdshel.Uciana.StarSystems.Blackhole;
import com.birdshel.Uciana.StarSystems.SpaceRift;
import com.birdshel.Uciana.StarSystems.StarSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Fleet {
    private static final List<ShipComponentID> bombSortList;
    private Object data;
    public final int empireID;
    public final String id;
    private boolean inOrbit;
    private boolean isMoving;
    private int originSystemID;
    private Point position;
    private List<Ship> ships;
    private int systemID;
    private boolean wormholeTravel;

    static {
        ArrayList arrayList = new ArrayList();
        bombSortList = arrayList;
        arrayList.add(ShipComponentID.NUCLUEAR_BOMB);
        arrayList.add(ShipComponentID.ANTIMATTER_BOMB);
        arrayList.add(ShipComponentID.NOVA_BOMB);
        arrayList.add(ShipComponentID.DIMENSIONAL_ENERGY_BOMB);
        arrayList.add(ShipComponentID.BIO_BOMB);
    }

    public Fleet(int i, int i2) {
        this.ships = new ArrayList();
        this.wormholeTravel = false;
        this.empireID = i;
        this.systemID = i2;
        this.originSystemID = i2;
        this.inOrbit = true;
        Point position = GameData.galaxy.getStarSystem(i2).getPosition();
        this.position = new Point(position.getX(), position.getY());
        this.id = UUID.randomUUID().toString();
    }

    private void arriveAtDestination(Point point) {
        this.position.setX(point.getX());
        this.position.setY(point.getY());
        this.isMoving = false;
        this.inOrbit = true;
        int i = this.systemID;
        this.originSystemID = i;
        GameData.fleets.checkForChanceToAttack(i);
        if (GameProperties.isNonNormalEmpire(this.empireID)) {
            return;
        }
        Empire empire = GameData.empires.get(this.empireID);
        if (!empire.isDiscoveredSystem(this.systemID)) {
            empire.addToDiscoveredList(this.systemID);
            GameData.events.addSystemEvent(EventType.SYSTEM_DISCOVERY, this.empireID, this.systemID);
        }
        if (GameData.empires.get(this.empireID).isHuman() && hasColonyShip() && GameData.galaxy.getColonizablePlanetsCount(this.systemID, this.empireID) != 0) {
            GameData.fleets.b(this.empireID, this.systemID);
        }
    }

    private boolean containsShipType(ShipType[] shipTypeArr) {
        for (Ship ship : this.ships) {
            if (Arrays.asList(shipTypeArr).contains(ship.getShipType())) {
                return true;
            }
        }
        return false;
    }

    private int getDistanceToSystem(int i) {
        return this.position.getDistance(GameData.galaxy.getStarSystem(i).getPosition()) / GameData.galaxy.getDistanceConst();
    }

    private int getIndex(String str) {
        for (int i = 0; i < this.ships.size(); i++) {
            if (this.ships.get(i).getID().equals(str)) {
                return i;
            }
        }
        throw new AssertionError("Error getting a ship");
    }

    private Point getNextJump(Point point, Point point2) {
        int distance = point.getDistance(point2);
        int speed = getSpeed();
        for (Blackhole blackhole : GameData.galaxy.getBlackholes()) {
            if (blackhole.isAffectedByTimeDilation(point)) {
                speed /= 2;
            }
        }
        for (SpaceRift spaceRift : GameData.galaxy.getSpaceRifts()) {
            if (spaceRift.checkInRangeOf(point)) {
                speed = (int) (speed * 1.5f);
            }
        }
        if (speed > distance) {
            return new Point(point2.getX(), point2.getY());
        }
        double atan2 = (float) Math.atan2(this.position.getY() - point2.getY(), this.position.getX() - point2.getX());
        float f2 = speed;
        return new Point(point.getX() - (((float) Math.cos(atan2)) * f2), point.getY() - (((float) Math.sin(atan2)) * f2));
    }

    private int getShipTypeCount(ShipType[] shipTypeArr, boolean z) {
        int[] countOfEachType = getCountOfEachType(z);
        int i = 0;
        for (ShipType shipType : shipTypeArr) {
            if (countOfEachType[shipType.id] != 0) {
                i++;
            }
        }
        return i;
    }

    private int getSpeed() {
        int i = this.empireID;
        if (i == 9) {
            return GameData.galaxy.getDistanceConst() * 3;
        }
        if (i == 8) {
            return GameData.galaxy.getDistanceConst() * 6;
        }
        return GameData.empires.get(i).getTech().getEngineSpeed() * GameData.galaxy.getDistanceConst();
    }

    private int getTurnsTo(Point point) {
        Point point2 = new Point(this.position.getX(), this.position.getY());
        int i = 0;
        while (!point.equals(point2)) {
            point2 = getNextJump(point2, point);
            i++;
        }
        return i;
    }

    private boolean hasNonCombatShips() {
        return containsShipType(new ShipType[]{ShipType.SCOUT, ShipType.COLONY, ShipType.CONSTRUCTION, ShipType.TRANSPORT});
    }

    private boolean inRange(int i, int i2) {
        return getDistanceToSystem(i) <= i2;
    }

    private boolean inRangeOfEmpire(Empire empire, int i) {
        for (Integer num : empire.getSystemIDs()) {
            if (inRange(num.intValue(), i)) {
                return true;
            }
        }
        return false;
    }

    public static /* synthetic */ int lambda$getBombs$1(ShipComponentID shipComponentID, ShipComponentID shipComponentID2) {
        List<ShipComponentID> list = bombSortList;
        if (list.indexOf(shipComponentID) == list.indexOf(shipComponentID2)) {
            return 0;
        }
        return list.indexOf(shipComponentID) > list.indexOf(shipComponentID2) ? 1 : -1;
    }

    public static /* synthetic */ int lambda$sort$0(Ship ship, Ship ship2) {
        return ship2.getShipType().id - ship.getShipType().id;
    }

    public void addShip(Ship ship) {
        this.ships.add(ship);
    }

    public void c(int i) {
        checkForChanceToAttack(i, -1);
    }

    public boolean canCommunicate() {
        if (!this.isMoving || this.inOrbit || GameProperties.isNonNormalEmpire(this.empireID)) {
            return true;
        }
        Empire empire = GameData.empires.get(this.empireID);
        return inRangeOfEmpire(empire, empire.getTech().getShipCommunicationRange());
    }

    public void checkForChanceToAttack(int i, int i2) {
        if (hasCombatShips()) {
            for (Fleet fleet : GameData.fleets.getFleetsInSystem(i)) {
                int i3 = fleet.empireID;
                int i4 = this.empireID;
                if (i3 != i4 && (GameProperties.isNonNormalEmpire(i4) || !GameData.empires.get(this.empireID).isAutoSelectAttackHidden(fleet.empireID) || fleet.empireID == i2)) {
                    GameData.events.addSelectAttackEvent(this.empireID, i);
                    return;
                }
            }
            StarSystem starSystem = GameData.galaxy.getStarSystem(i);
            for (int i5 = 0; i5 < 5; i5++) {
                if (starSystem.isOccupied(i5)) {
                    int occupier = starSystem.getOccupier(i5);
                    int i6 = this.empireID;
                    if (occupier != i6) {
                        if (!GameProperties.isNonNormalEmpire(i6)) {
                            Empire empire = GameData.empires.get(this.empireID);
                            int occupier2 = starSystem.getOccupier(i5);
                            if (empire.isAutoSelectAttackHidden(occupier2) && occupier2 != i2) {
                            }
                        }
                        GameData.events.addSelectAttackEvent(this.empireID, i);
                        return;
                    }
                    continue;
                }
            }
        }
    }

    public void d() {
        if (this.isMoving) {
            return;
        }
        for (Ship ship : this.ships) {
            ship.c();
        }
        if (GameProperties.isNonNormalEmpire(this.empireID)) {
            for (Ship ship2 : this.ships) {
                ship2.d();
            }
        } else if (GameData.empires.get(this.empireID).getFriendlyStarSystems().contains(Integer.valueOf(this.systemID))) {
            for (Ship ship3 : this.ships) {
                ship3.d();
            }
        }
    }

    public void e() {
        for (Ship ship : this.ships) {
            ship.f();
        }
    }

    public int getAutoCount(ShipType shipType) {
        int i = 0;
        for (Ship ship : getShips()) {
            if (ship.getShipType() == shipType && ship.isAutoOn()) {
                i++;
            }
        }
        return i;
    }

    public TreeMap<ShipComponentID, Integer> getBombs() {
        TreeMap<ShipComponentID, Integer> treeMap = new TreeMap<>(new Comparator() { // from class: com.birdshel.Uciana.Ships.b
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int lambda$getBombs$1;
                lambda$getBombs$1 = Fleet.lambda$getBombs$1((ShipComponentID) obj, (ShipComponentID) obj2);
                return lambda$getBombs$1;
            }
        });
        for (Ship ship : getShips()) {
            for (ShipComponent shipComponent : ship.getShipComponents()) {
                if ((shipComponent instanceof Weapon) && ((Weapon) shipComponent).getType() == WeaponType.BOMB) {
                    if (treeMap.containsKey(shipComponent.getID())) {
                        treeMap.put(shipComponent.getID(), Integer.valueOf(treeMap.get(shipComponent.getID()).intValue() + shipComponent.getComponentCount()));
                    } else {
                        treeMap.put(shipComponent.getID(), Integer.valueOf(shipComponent.getComponentCount()));
                    }
                }
            }
        }
        return treeMap;
    }

    public int getCombatShipTypeCount(boolean z) {
        return getShipTypeCount(new ShipType[]{ShipType.CRUISER, ShipType.DREADNOUGHT, ShipType.BATTLESHIP, ShipType.DESTROYER}, z);
    }

    public List<Ship> getCombatShips() {
        ArrayList arrayList = new ArrayList();
        for (Ship ship : this.ships) {
            if (ship.isCombatShip()) {
                arrayList.add(ship);
            }
        }
        return arrayList;
    }

    public int getCombatStrength() {
        int i = 0;
        for (Ship ship : this.ships) {
            i += ship.getCombatStrength();
        }
        return i;
    }

    public int[] getCountOfEachType(boolean z) {
        ArrayList arrayList = new ArrayList();
        for (Ship ship : this.ships) {
            arrayList.add(ship.getID());
        }
        return getCountOfEachType(arrayList, z);
    }

    public Object getData() {
        Object obj = this.data;
        if (obj == null) {
            return 1;
        }
        return obj;
    }

    public String getDestinationText() {
        String string = GameData.activity.getString(R.string.galaxy_select_unknown);
        if (GameData.getCurrentEmpire().isDiscoveredSystem(getSystemID())) {
            string = GameData.galaxy.getStarSystem(getSystemID()).getName();
        }
        return isMoving() ? getETA() == 1 ? GameData.activity.getString(R.string.fleet_destination_in_route_turn, new Object[]{string}) : GameData.activity.getString(R.string.fleet_destination_in_route_turns, new Object[]{string, Integer.valueOf(getETA())}) : GameData.activity.getString(R.string.fleet_destination_in_orbit, new Object[]{string});
    }

    public int getDirection() {
        return (!this.isMoving || GameData.galaxy.getStarSystem(this.systemID).getPosition().getX() >= this.position.getX()) ? 1 : 0;
    }

    public int getDistanceOfClosestColonyOrOutpost() {
        int i = 1000;
        for (Integer num : GameData.empires.get(this.empireID).getSystemIDs()) {
            int distanceToSystem = getDistanceToSystem(num.intValue());
            if (distanceToSystem < i) {
                i = distanceToSystem;
            }
        }
        return i;
    }

    public int getETA() {
        if (this.wormholeTravel) {
            return 1;
        }
        return getTurnsTo(GameData.galaxy.getStarSystem(this.systemID).getPosition());
    }

    public int getHullDesignForShipType(ShipType shipType) {
        if (shipType.isCombatShip()) {
            int[] iArr = {0, 0, 0, 0, 0, 0};
            for (Ship ship : this.ships) {
                if (ship.getShipType() == shipType) {
                    int hullDesign = ship.getHullDesign();
                    iArr[hullDesign] = iArr[hullDesign] + 1;
                }
            }
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < 6; i3++) {
                if (iArr[i3] > i2) {
                    i2 = iArr[i3];
                    i = i3;
                }
            }
            return i;
        }
        return 0;
    }

    public int getIcon() {
        Object[] largestShipTypeAndDesign = getLargestShipTypeAndDesign();
        return ((ShipType) largestShipTypeAndDesign[0]).getIcon(this.empireID, ((Integer) largestShipTypeAndDesign[1]).intValue());
    }

    public ShipType getLargestShip() {
        ShipType shipType = this.ships.get(0).getShipType();
        for (Ship ship : this.ships) {
            if (ship.getShipType().id > shipType.id) {
                shipType = ship.getShipType();
            }
        }
        return shipType;
    }

    public Object[] getLargestShipTypeAndDesign() {
        int[] iArr = {0, 0, 0, 0, 0, 0};
        ShipType shipType = this.ships.get(0).getShipType();
        for (Ship ship : this.ships) {
            if (ship.getShipType().id == shipType.id) {
                int i = ship.f1550d;
                iArr[i] = iArr[i] + 1;
            }
            if (ship.getShipType().id > shipType.id) {
                shipType = ship.getShipType();
                iArr[0] = 0;
                iArr[1] = 0;
                iArr[2] = 0;
                iArr[3] = 0;
                iArr[4] = 0;
                iArr[5] = 0;
                int i2 = ship.f1550d;
                iArr[i2] = iArr[i2] + 1;
            }
        }
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < 6; i6++) {
            int i7 = iArr[i6];
            if (i7 > i4) {
                i3 = i5;
                i4 = i7;
            }
            i5++;
        }
        return new Object[]{shipType, Integer.valueOf(i3)};
    }

    public int getNonCombatShipTypeCount() {
        return getShipTypeCount(new ShipType[]{ShipType.CONSTRUCTION, ShipType.COLONY, ShipType.TRANSPORT, ShipType.SCOUT}, true);
    }

    public List<Ship> getNonCombatShips() {
        ArrayList arrayList = new ArrayList();
        for (Ship ship : this.ships) {
            if (!ship.isCombatShip()) {
                arrayList.add(ship);
            }
        }
        return arrayList;
    }

    public int getOriginSystem() {
        return this.originSystemID;
    }

    public Point getPosition() {
        return this.position;
    }

    public int getScrapValue(List<String> list) {
        Empire empire = GameData.empires.get(this.empireID);
        int i = 0;
        if (this.inOrbit && empire.getSystemIDs().contains(Integer.valueOf(getSystemID()))) {
            for (String str : list) {
                i += getShip(str).b();
            }
            return i;
        }
        return 0;
    }

    public Ship getShip(String str) {
        return this.ships.get(getIndex(str));
    }

    public List<String> getShipIDs() {
        ArrayList arrayList = new ArrayList();
        for (Ship ship : getShips()) {
            arrayList.add(ship.getID());
        }
        return arrayList;
    }

    public List<Ship> getShips() {
        return this.ships;
    }

    public int getSize() {
        return this.ships.size();
    }

    public int getSystemID() {
        return this.systemID;
    }

    public int getTroopCount() {
        int i = getCountOfEachType(true)[ShipType.TRANSPORT.id] * 5;
        for (Ship ship : this.ships) {
            if (ship.isCombatShip()) {
                for (ShipComponent shipComponent : ship.getShipComponents()) {
                    if (shipComponent.getID() == ShipComponentID.TROOP_PODS) {
                        i += 5;
                    }
                }
            }
        }
        return i;
    }

    public int getTurnsToSystem(int i) {
        StarSystem starSystem = GameData.galaxy.getStarSystem(this.originSystemID);
        if (!this.isMoving) {
            starSystem = GameData.galaxy.getStarSystem(this.systemID);
        }
        int turnsTo = getTurnsTo(GameData.galaxy.getStarSystem(i).getPosition());
        if ((!this.isMoving || isPreparingToMove()) && starSystem.hasWormholes()) {
            for (Point point : GameData.galaxy.getWormholes()) {
                if (point.getX() == starSystem.getID() && point.getY() == i) {
                    return 1;
                }
                if (point.getY() == starSystem.getID() && point.getX() == i) {
                    return 1;
                }
            }
        }
        return turnsTo;
    }

    public List<Ship> getUnretreatedShips() {
        ArrayList arrayList = new ArrayList();
        for (Ship ship : this.ships) {
            if (!ship.hasRetreated()) {
                arrayList.add(ship);
            }
        }
        return arrayList;
    }

    public boolean hasColonyShip() {
        return containsShipType(new ShipType[]{ShipType.COLONY});
    }

    public boolean hasCombatShips() {
        return containsShipType(new ShipType[]{ShipType.DESTROYER, ShipType.CRUISER, ShipType.BATTLESHIP, ShipType.DREADNOUGHT});
    }

    public boolean hasNonRetreatedCombatShips() {
        for (Ship ship : getCombatShips()) {
            if (!ship.hasRetreated()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasNonRetreatedShips() {
        if (hasNonCombatShips()) {
            return true;
        }
        return hasNonRetreatedCombatShips();
    }

    public boolean hasOutpostShip() {
        return containsShipType(new ShipType[]{ShipType.CONSTRUCTION});
    }

    public boolean hasScoutShip() {
        for (Ship ship : this.ships) {
            if (ship.a()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasScoutShipAvailable() {
        for (Ship ship : this.ships) {
            if (ship.a() && !ship.hasBeenUsed()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasShip(String str) {
        for (Ship ship : this.ships) {
            if (ship.getID().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasTransportShip() {
        return containsShipType(new ShipType[]{ShipType.TRANSPORT});
    }

    public boolean inOrbit() {
        return this.inOrbit;
    }

    public boolean isEmpty() {
        return this.ships.isEmpty();
    }

    public boolean isMoving() {
        return this.isMoving;
    }

    public boolean isPreparingToMove() {
        return this.inOrbit && this.isMoving;
    }

    public boolean isVisible(Empire empire) {
        if (this.empireID == 8 && this.inOrbit && empire.isDiscoveredSystem(this.systemID)) {
            return true;
        }
        if (this.empireID == 9 && GameData.getCurrentEmpire().getSystemIDs().contains(Integer.valueOf(this.systemID))) {
            return true;
        }
        for (Fleet fleet : empire.getFleets()) {
            if (this.position.getDistance(fleet.position) / GameData.galaxy.getDistanceConst() < empire.getTech().getShipScanningRange()) {
                return true;
            }
        }
        return inRangeOfEmpire(empire, empire.getTech().getColonyScanningRange());
    }

    public void move(int i) {
        if (!isMoving()) {
            this.inOrbit = true;
            this.originSystemID = this.systemID;
        }
        boolean z = this.inOrbit;
        if (z && i == this.originSystemID) {
            this.isMoving = false;
            this.systemID = i;
            GameData.fleets.c(this.empireID, i);
            return;
        }
        this.wormholeTravel = false;
        if (z) {
            for (Point point : GameData.galaxy.getWormholes()) {
                if (point.getX() == this.originSystemID || point.getY() == this.originSystemID) {
                    float f2 = i;
                    if (point.getX() == f2 || point.getY() == f2) {
                        this.wormholeTravel = true;
                    }
                }
            }
        }
        this.isMoving = true;
        this.systemID = i;
    }

    public void removeShip(String str) {
        if (hasShip(str)) {
            int index = getIndex(str);
            this.ships.remove(this.ships.get(index));
        }
    }

    public void resetStatus() {
        for (Ship ship : this.ships) {
            ship.e();
        }
    }

    public void scrap(String str) {
        Ship ship = getShip(str);
        Empire empire = GameData.empires.get(this.empireID);
        if (this.inOrbit && empire.getSystemIDs().contains(Integer.valueOf(getSystemID()))) {
            GameData.empires.get(this.empireID).addRemoveCredits(ship.b());
        }
        removeShip(str);
    }

    public void set(boolean z, boolean z2) {
        this.isMoving = z;
        this.inOrbit = z2;
    }

    public void setData(Object obj) {
        this.data = obj;
    }

    public void setMoving() {
        this.inOrbit = false;
    }

    public void setPosition(Point point) {
        this.position = point;
    }

    public Ship setScoutUsed() {
        for (Ship ship : this.ships) {
            if (ship.a() && !ship.hasBeenUsed()) {
                ship.setUsed();
                return ship;
            }
        }
        return null;
    }

    public void sort() {
        Collections.sort(getShips(), new Comparator() { // from class: com.birdshel.Uciana.Ships.a
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int lambda$sort$0;
                lambda$sort$0 = Fleet.lambda$sort$0((Ship) obj, (Ship) obj2);
                return lambda$sort$0;
            }
        });
    }

    public void update() {
        if (!isMoving()) {
            this.inOrbit = true;
            return;
        }
        Point position = GameData.galaxy.getStarSystem(this.systemID).getPosition();
        if (this.wormholeTravel) {
            if (!GameProperties.isNonNormalEmpire(this.empireID) && GameData.empires.get(this.empireID).isHuman()) {
                Game.gameAchievements.wormholeTravel();
            }
            arriveAtDestination(position);
            this.wormholeTravel = false;
            return;
        }
        this.inOrbit = false;
        Point nextJump = getNextJump(this.position, position);
        this.position = nextJump;
        if (nextJump.equals(position)) {
            arriveAtDestination(position);
        }
    }

    public boolean wormholeTravel() {
        return this.wormholeTravel;
    }

    public Ship getShip(ShipType shipType) {
        for (Ship ship : this.ships) {
            if (ship.getShipType() == shipType) {
                return ship;
            }
        }
        return null;
    }

    public int[] getCountOfEachType(List<String> list, boolean z) {
        int[] iArr = {0, 0, 0, 0, 0, 0, 0, 0};
        for (Ship ship : this.ships) {
            if (z || !ship.hasRetreated()) {
                if (list.contains(ship.getID())) {
                    int i = ship.getShipType().id;
                    iArr[i] = iArr[i] + 1;
                }
            }
        }
        return iArr;
    }

    public Fleet(String str, Point point, int i, int i2, int i3, boolean z, boolean z2, boolean z3, List<Ship> list) {
        this.ships = new ArrayList();
        this.wormholeTravel = false;
        this.id = str;
        this.position = point;
        this.empireID = i;
        this.systemID = i2;
        this.originSystemID = i3;
        this.isMoving = z;
        this.wormholeTravel = z2;
        this.inOrbit = z3;
        this.ships = list;
    }
}
