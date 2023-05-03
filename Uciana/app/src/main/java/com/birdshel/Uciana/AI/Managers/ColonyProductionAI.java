package com.birdshel.Uciana.AI.Managers;

import com.birdshel.Uciana.AI.AIObjects.EmpireThreatState;
import com.birdshel.Uciana.AI.AIObjects.ShipBuildPriority;
import com.birdshel.Uciana.Colonies.Buildings.Building;
import com.birdshel.Uciana.Colonies.Buildings.BuildingID;
import com.birdshel.Uciana.Colonies.Buildings.Buildings;
import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Colonies.ManufacturingType;
import com.birdshel.Uciana.Colonies.ProductionItem;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.Ships.Ship;
import com.birdshel.Uciana.Ships.ShipType;
import com.birdshel.Uciana.Technology.TechID;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ColonyProductionAI {
    private final List<Ship> battleshipDesigns;
    private final List<Ship> cruiserDesigns;
    private final List<Ship> destroyerDesigns;
    private final List<Ship> dreadnoughtDesigns;
    private final Empire empire;
    private final List<BuildingID> requiredBuildings;
    private int turnsTillNewColonyShip;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.AI.Managers.ColonyProductionAI$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1341a;

        static {
            int[] iArr = new int[ShipType.values().length];
            f1341a = iArr;
            try {
                iArr[ShipType.DESTROYER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1341a[ShipType.CRUISER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1341a[ShipType.BATTLESHIP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1341a[ShipType.DREADNOUGHT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public ColonyProductionAI(Empire empire) {
        ArrayList arrayList = new ArrayList();
        this.requiredBuildings = arrayList;
        this.destroyerDesigns = new ArrayList();
        this.cruiserDesigns = new ArrayList();
        this.battleshipDesigns = new ArrayList();
        this.dreadnoughtDesigns = new ArrayList();
        this.turnsTillNewColonyShip = 0;
        this.empire = empire;
        arrayList.add(BuildingID.AUTOMATED_FACTORY);
        arrayList.add(BuildingID.CLONING_CENTER);
        arrayList.add(BuildingID.INFANTRY_BARRACKS);
    }

    private void buildCapital() {
        Colony randomDevelopedColony = getRandomDevelopedColony();
        Building building = Buildings.getBuilding(BuildingID.CAPITAL);
        if (building.canBeBuilt(randomDevelopedColony)) {
            randomDevelopedColony.getManufacturing().setBuilding(building);
        }
    }

    private void checkToSeeIfColonyShipsNeeded() {
        if (GameData.fleets.getCountOfEachType(this.empire.id)[ShipType.COLONY.id] != 0) {
            return;
        }
        for (Colony colony : this.empire.getColonies()) {
            if (colony.getManufacturing().getType() == ManufacturingType.SHIP && colony.getManufacturing().getShipType() == ShipType.COLONY) {
                return;
            }
        }
        int i = this.turnsTillNewColonyShip - 1;
        this.turnsTillNewColonyShip = i;
        if (i < 0) {
            this.turnsTillNewColonyShip = Game.getDifficulty().getTurnsBetweenColonyShips();
            Colony randomDevelopedColony = getRandomDevelopedColony();
            if (!randomDevelopedColony.isDeveloped() || randomDevelopedColony.isBlockaded()) {
                return;
            }
            randomDevelopedColony.getManufacturing().setShip(new Ship.Builder().id("AI-Colony-" + UUID.randomUUID().toString()).shipType(ShipType.COLONY).empireID(this.empire.id).buildNonCombatShip());
        }
    }

    private void checkToSeeIfOutpostShipIsNeeded() {
        if (GameData.fleets.getCountOfEachType(this.empire.id)[ShipType.CONSTRUCTION.id] != 0) {
            return;
        }
        for (Colony colony : this.empire.getColonies()) {
            if (colony.getManufacturing().getType() == ManufacturingType.SHIP && colony.getManufacturing().getShipType() == ShipType.CONSTRUCTION) {
                return;
            }
        }
        Colony randomDevelopedColony = getRandomDevelopedColony();
        if (!randomDevelopedColony.isDeveloped() || randomDevelopedColony.isBlockaded()) {
            return;
        }
        randomDevelopedColony.getManufacturing().setShip(new Ship.Builder().id("AI-OUTPOST-" + UUID.randomUUID().toString()).shipType(ShipType.CONSTRUCTION).empireID(this.empire.id).buildNonCombatShip());
    }

    private Colony getRandomDevelopedColony() {
        ArrayList<Colony> arrayList = new ArrayList(this.empire.getColonies());
        Collections.shuffle(arrayList);
        for (Colony colony : arrayList) {
            if (colony.isDeveloped()) {
                return colony;
            }
        }
        return (Colony) arrayList.get(0);
    }

    private int manageAIProduction(Colony colony, EmpireThreatState empireThreatState, int i, int i2) {
        ShipBuildPriority a2 = this.empire.getMilitaryAI().a(empireThreatState, i, i2);
        if (a2 == ShipBuildPriority.EMERGENCY) {
            return i - setEmergencyShipProduction(colony);
        }
        ShipBuildPriority shipBuildPriority = ShipBuildPriority.IMMEDIATELY;
        if (a2 == shipBuildPriority && colony.isDeveloped()) {
            return i - setImmediateShipProduction(colony);
        }
        ProductionItem currentItem = colony.getManufacturing().getCurrentItem();
        if (currentItem.getType() == ManufacturingType.NONE || currentItem.getID().equals(BuildingID.TRADEGOODS.toString())) {
            if (a2 != ShipBuildPriority.AVAILABLE && a2 != shipBuildPriority) {
                if (a2 == ShipBuildPriority.RARELY) {
                    if (Functions.percent(10)) {
                        return i - setShipProduction(colony);
                    }
                } else if (a2 == ShipBuildPriority.NOT_CRITICAL && Functions.percent(25)) {
                    return i - setShipProduction(colony);
                }
                for (BuildingID buildingID : this.requiredBuildings) {
                    if (!colony.hasBuilding(buildingID) && this.empire.getTech().hasTech(Buildings.getBuilding(buildingID).getRequiredTech())) {
                        colony.getManufacturing().setBuilding(Buildings.getBuilding(buildingID));
                        return i;
                    }
                }
                List<Building> availableBuildingList = colony.getAvailableBuildingList();
                Building building = availableBuildingList.get(availableBuildingList.size() > 1 ? Functions.random.nextInt(availableBuildingList.size()) : 0);
                if (a2 == ShipBuildPriority.BUILDINGS_FIRST && building.getID() == BuildingID.TRADEGOODS) {
                    return i - setShipProduction(colony);
                }
                colony.getManufacturing().setBuilding(building);
                return i;
            }
            return i - setShipProduction(colony);
        }
        return i;
    }

    private int setEmergencyShipProduction(Colony colony) {
        Ship ship;
        setShipDesigns();
        if (colony.isDeveloped()) {
            if (colony.hasBuilding(BuildingID.SHIP_YARDS)) {
                List<Ship> list = this.battleshipDesigns;
                ship = list.get(Functions.random.nextInt(list.size()));
            } else {
                List<Ship> list2 = this.cruiserDesigns;
                ship = list2.get(Functions.random.nextInt(list2.size()));
            }
        } else {
            List<Ship> list3 = this.destroyerDesigns;
            ship = list3.get(Functions.random.nextInt(list3.size()));
        }
        colony.getManufacturing().setShip(ship);
        return ship.getShipType().getCommandPointCost();
    }

    private int setImmediateShipProduction(Colony colony) {
        Ship ship;
        setShipDesigns();
        if (colony.hasBuilding(BuildingID.SHIP_YARDS)) {
            if (this.empire.hasTech(TechID.DREADNOUGHT)) {
                if (Functions.percent(50)) {
                    List<Ship> list = this.dreadnoughtDesigns;
                    ship = list.get(Functions.random.nextInt(list.size()));
                } else {
                    List<Ship> list2 = this.battleshipDesigns;
                    ship = list2.get(Functions.random.nextInt(list2.size()));
                }
            } else {
                List<Ship> list3 = this.battleshipDesigns;
                ship = list3.get(Functions.random.nextInt(list3.size()));
            }
        } else {
            List<Ship> list4 = this.cruiserDesigns;
            ship = list4.get(Functions.random.nextInt(list4.size()));
        }
        colony.getManufacturing().setShip(ship);
        return ship.getShipType().getCommandPointCost();
    }

    private void setShipDesigns() {
        for (Ship ship : this.empire.getShipDesigns()) {
            int i = AnonymousClass1.f1341a[ship.getShipType().ordinal()];
            if (i == 1) {
                this.destroyerDesigns.add(ship);
            } else if (i == 2) {
                this.cruiserDesigns.add(ship);
            } else if (i == 3) {
                this.battleshipDesigns.add(ship);
            } else if (i == 4) {
                this.dreadnoughtDesigns.add(ship);
            }
        }
    }

    private int setShipProduction(Colony colony) {
        Ship ship;
        setShipDesigns();
        int[] countOfEachType = GameData.fleets.getCountOfEachType(this.empire.id);
        ShipType shipType = ShipType.TRANSPORT;
        int i = countOfEachType[shipType.id];
        if (colony.isDeveloped()) {
            if (i < 3) {
                Ship.Builder builder = new Ship.Builder();
                ship = builder.id("Transport-" + UUID.randomUUID().toString()).shipType(shipType).empireID(this.empire.id).buildNonCombatShip();
            } else if (colony.hasBuilding(BuildingID.SHIP_YARDS)) {
                if (this.empire.hasTech(TechID.DREADNOUGHT)) {
                    if (Functions.percent(50)) {
                        List<Ship> list = this.dreadnoughtDesigns;
                        ship = list.get(Functions.random.nextInt(list.size()));
                    } else {
                        List<Ship> list2 = this.battleshipDesigns;
                        ship = list2.get(Functions.random.nextInt(list2.size()));
                    }
                } else {
                    List<Ship> list3 = this.battleshipDesigns;
                    ship = list3.get(Functions.random.nextInt(list3.size()));
                }
            } else {
                List<Ship> list4 = this.cruiserDesigns;
                ship = list4.get(Functions.random.nextInt(list4.size()));
            }
        } else if (i < 3) {
            Ship.Builder builder2 = new Ship.Builder();
            ship = builder2.id("Transport-" + UUID.randomUUID().toString()).shipType(shipType).empireID(this.empire.id).buildNonCombatShip();
        } else {
            List<Ship> list5 = this.destroyerDesigns;
            ship = list5.get(Functions.random.nextInt(list5.size()));
        }
        colony.getManufacturing().setShip(ship);
        return ship.getShipType().getCommandPointCost();
    }

    public void manage() {
        EmpireThreatState state = EmpireThreatState.getState(this.empire);
        int availableCommandPoints = this.empire.getAvailableCommandPoints() - this.empire.getCommandPointsInProduction();
        int commandPoints = this.empire.getCommandPoints();
        ArrayList<Colony> arrayList = new ArrayList(this.empire.getColonies());
        Collections.shuffle(arrayList);
        for (Colony colony : arrayList) {
            colony.getTask().update();
            availableCommandPoints = manageAIProduction(colony, state, availableCommandPoints, commandPoints);
        }
        checkToSeeIfOutpostShipIsNeeded();
        checkToSeeIfColonyShipsNeeded();
        if (this.empire.hasCapital()) {
            return;
        }
        buildCapital();
    }

    public void manageProduction(Colony colony) {
        ProductionItem currentItem = colony.getManufacturing().getCurrentItem();
        if (currentItem.getType() == ManufacturingType.NONE || currentItem.getID().equals(BuildingID.TRADEGOODS.toString())) {
            for (BuildingID buildingID : this.requiredBuildings) {
                if (!colony.hasBuilding(buildingID) && this.empire.hasTech(Buildings.getBuilding(buildingID).getRequiredTech())) {
                    colony.getManufacturing().setBuilding(Buildings.getBuilding(buildingID));
                    return;
                }
            }
            List<Building> availableBuildingList = colony.getAvailableBuildingList();
            colony.getManufacturing().setBuilding(availableBuildingList.get(availableBuildingList.size() > 1 ? Functions.random.nextInt(availableBuildingList.size()) : 0));
        }
    }
}
