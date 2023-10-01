package com.birdshel.Uciana.Colonies;

import com.birdshel.Uciana.Colonies.Buildings.Building;
import com.birdshel.Uciana.Colonies.Buildings.BuildingID;
import com.birdshel.Uciana.Colonies.Buildings.BuildingType;
import com.birdshel.Uciana.Colonies.Buildings.Buildings;
import com.birdshel.Uciana.Events.EventType;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Resources.OptionID;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.Ships.Ship;
import com.birdshel.Uciana.Ships.ShipType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Manufacturing {
    private ProductionItem currentItem;
    private int empireID;
    private final int orbit;
    private int production;
    private final LinkedList<ProductionItem> queue;
    private final Map<String, Ship> queuedShipDesigns;
    private final int systemID;

    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Colonies.Manufacturing$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a */
        static final /* synthetic */ int[] f1357a;
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[BuildingType.values().length];
            b = iArr;
            try {
                iArr[BuildingType.TERRAFORMING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[BuildingType.TRADEGOODS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[BuildingType.SPY_NETWORK.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[ManufacturingType.values().length];
            f1357a = iArr2;
            try {
                iArr2[ManufacturingType.SHIP.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1357a[ManufacturingType.BUILDINGS.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1357a[ManufacturingType.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* compiled from: MyApplication */
    /* loaded from: classes.dex */
    public static class Builder {
        private int empireID;
        private boolean isRepeatOn;
        private int orbit;
        private int systemID;
        private String currentItemID = "0";
        private ManufacturingType currentItemType = ManufacturingType.NONE;
        private LinkedList<ProductionItem> queue = new LinkedList<>();
        private final Map<String, Ship> queuedShipDesigns = new HashMap();
        private int production = 0;

        public Manufacturing build() {
            return new Manufacturing(this, null);
        }

        public Builder currentItemID(String str) {
            this.currentItemID = str;
            return this;
        }

        public Builder currentItemIsRepeatOn(boolean z) {
            this.isRepeatOn = z;
            return this;
        }

        public Builder currentItemType(ManufacturingType manufacturingType) {
            this.currentItemType = manufacturingType;
            return this;
        }

        public Builder empireID(int i) {
            this.empireID = i;
            return this;
        }

        public Builder orbit(int i) {
            this.orbit = i;
            return this;
        }

        public Builder production(int i) {
            this.production = i;
            return this;
        }

        public Builder queue(LinkedList<ProductionItem> linkedList) {
            this.queue = linkedList;
            return this;
        }

        public Builder queuedShipDesigns(List<Ship> list) {
            for (Ship ship : list) {
                this.queuedShipDesigns.put(ship.getID(), ship);
            }
            return this;
        }

        public Builder systemID(int i) {
            this.systemID = i;
            return this;
        }
    }

    /* synthetic */ Manufacturing(Builder builder, AnonymousClass1 anonymousClass1) {
        this(builder);
    }

    private void addToQueuedShipDesigns(Ship ship) {
        this.queuedShipDesigns.put(ship.getID(), ship);
        ArrayList<String> arrayList = new ArrayList();
        for (Map.Entry<String, Ship> entry : this.queuedShipDesigns.entrySet()) {
            boolean z = false;
            if (!entry.getKey().equals(this.currentItem.getID())) {
                Iterator<ProductionItem> it = this.queue.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it.next().getID().equals(entry.getKey())) {
                            z = true;
                            break;
                        }
                    } else {
                        break;
                    }
                }
                if (!z) {
                    arrayList.add(entry.getKey());
                }
            }
        }
        for (String str : arrayList) {
            this.queuedShipDesigns.remove(str);
        }
    }

    private void done() {
        int i = AnonymousClass1.f1357a[this.currentItem.getType().ordinal()];
        if (i == 1) {
            shipDone();
        } else if (i == 2) {
            Building building = Buildings.getBuilding(this.currentItem.getID());
            int i2 = AnonymousClass1.b[building.getBuildingType().ordinal()];
            if (i2 == 1) {
                getColony().getPlanet().terraform(this.empireID);
                if (GameData.empires.get(this.empireID).isHuman()) {
                    Game.gameAchievements.checkForTerraformAchievements(getColony().getPlanet());
                }
                GameData.events.addPlanetEvent(EventType.TERRAFORMING, this.empireID, this.systemID, this.orbit);
                setNext();
            } else if (i2 != 2) {
                if (i2 != 3) {
                    getColony().a(this.currentItem.getID());
                    if (GameData.empires.get(this.empireID).isHuman()) {
                        Game.gameAchievements.checkForBuildingFinished(getColony(), this.currentItem.getID());
                    }
                    setNext();
                } else {
                    GameData.empires.get(this.empireID).addSpyNetwork(BuildingID.getEmpireIDForSpyNetwork(building.getID()));
                    setNext();
                }
            } else if (getColony().isAutoBuilding()) {
                GameData.empires.get(this.empireID).manageProduction(getColony());
            }
        }
        this.production = 0;
    }

    private Colony getColony() {
        return GameData.colonies.getColony(this.systemID, this.orbit);
    }

    private boolean isBuildingInQueue(String str) {
        Iterator<ProductionItem> it = this.queue.iterator();
        while (it.hasNext()) {
            if (it.next().getID().equals(str)) {
                return true;
            }
        }
        return false;
    }

    private void loadSetBuilding(Building building) {
        this.currentItem = new ProductionItem(ManufacturingType.BUILDINGS, building.getStringID(), building.getName(), building.getProductionCost());
    }

    private void loadSetShip(Ship ship, boolean z) {
        this.currentItem = new ProductionItem(ManufacturingType.SHIP, ship.getID(), ship.getName(), ship.getProductionCost(), z);
    }

    private void removeFromQueueOnID(String str) {
        Iterator<ProductionItem> it = this.queue.iterator();
        while (it.hasNext()) {
            ProductionItem next = it.next();
            if (next.getID().equals(str)) {
                this.queue.remove(next);
                return;
            }
        }
    }

    private void setNext() {
        if (this.queue.isEmpty()) {
            if (Game.options.isOn(OptionID.SET_TRADEGOODS) && GameData.empires.get(this.empireID).isHuman()) {
                setBuilding(Buildings.getBuilding(BuildingID.TRADEGOODS.toString()));
            } else {
                setNone();
            }
            if (getColony().isAutoBuilding()) {
                GameData.empires.get(this.empireID).manageProduction(getColony());
                return;
            }
            return;
        }
        this.currentItem = this.queue.poll();
    }

    private void setNone() {
        this.currentItem = new ProductionItem(ManufacturingType.NONE, "0", GameData.activity.getString(R.string.colony_production_none), 0);
    }

    private void shipDone() {
        Fleet fleet;
        if (GameData.fleets.isFleetInSystem(this.empireID, this.systemID)) {
            fleet = GameData.fleets.getFleetInSystem(this.empireID, this.systemID);
        } else {
            fleet = new Fleet(this.empireID, this.systemID);
            GameData.fleets.add(fleet);
        }
        Ship createClone = this.queuedShipDesigns.get(this.currentItem.getID()).createClone(GameData.empires.get(this.empireID).getNextShipID());
        if (this.currentItem.getName().startsWith("Refit")) {
            createClone.setID(this.currentItem.getID().substring(6));
            createClone.setName(this.currentItem.getName().substring(6));
        }
        fleet.addShip(createClone);
        if (!this.currentItem.isRepeatOn()) {
            this.queuedShipDesigns.remove(this.currentItem.getID());
            setNext();
        }
        GameData.fleets.checkForChanceToAttack(this.systemID);
    }

    public void a() {
        this.production = 0;
        this.queue.clear();
        setNext();
    }

    public void addBuildingToQueue(Building building) {
        String stringID = building.getStringID();
        if (this.currentItem.getType() == ManufacturingType.NONE) {
            setBuilding(building);
        } else if (isQueueFull() || getCurrentItem().getID().equals(stringID) || isBuildingInQueue(stringID)) {
        } else {
            this.queue.add(new ProductionItem(ManufacturingType.BUILDINGS, stringID, building.getName(), building.getProductionCost()));
        }
    }

    public void addRefitShipToQueue(Ship ship, Ship ship2) {
        String str = "refit-" + ship2.getID();
        String str2 = "Refit " + ship2.getName();
        Ship createClone = ship.createClone(str);
        if (this.currentItem.getType() == ManufacturingType.NONE) {
            setRefitShip(ship, ship2);
        } else if (isQueueFull()) {
        } else {
            int productionCost = createClone.getProductionCost();
            double productionCost2 = ship2.getProductionCost();
            Double.isNaN(productionCost2);
            int i = productionCost - ((int) (productionCost2 * 0.5d));
            if (i < 0) {
                i = 50;
            }
            this.queue.add(new ProductionItem(ManufacturingType.SHIP, str, str2, i));
            addToQueuedShipDesigns(createClone);
        }
    }

    public void addShipToQueue(Ship ship) {
        if (this.currentItem.getType() == ManufacturingType.NONE) {
            setShip(ship);
        } else if (isQueueFull()) {
        } else {
            Ship createClone = ship.createClone("Queue-" + UUID.randomUUID().toString());
            this.queue.add(new ProductionItem(ManufacturingType.SHIP, createClone.getID(), createClone.getName(), createClone.getProductionCost()));
            addToQueuedShipDesigns(createClone);
        }
    }

    public int b() {
        int requiredProduction = this.currentItem.getRequiredProduction() - this.production;
        if (requiredProduction < 0) {
            return 0;
        }
        return requiredProduction;
    }

    public float c() {
        return 0.5f;
    }

    public void d(int i) {
        this.production += i;
        int requiredProduction = this.currentItem.getRequiredProduction();
        if (GameData.empires.get(this.empireID).isAI()) {
            requiredProduction = (int) (requiredProduction * Game.getProductionDifficultyModifier());
        }
        if (this.production >= requiredProduction) {
            done();
        }
    }

    public void finishProject() {
        this.production += b();
    }

    public String getBuildingID() {
        return this.currentItem.getType() != ManufacturingType.BUILDINGS ? "-1" : this.currentItem.getID();
    }

    public ProductionItem getCopyOfCurrentItem() {
        return new ProductionItem(this.currentItem.getType(), this.currentItem.getID(), this.currentItem.getName(), this.currentItem.getRequiredProduction(), this.currentItem.isRepeatOn());
    }

    public int getCostToFinish() {
        return (int) (b() * ((((int) ((this.production / this.currentItem.getRequiredProduction()) * 100.0f)) * (-0.02f)) + 4.0f));
    }

    public ProductionItem getCurrentItem() {
        return this.currentItem;
    }

    public int getFinishedProduction() {
        return this.production;
    }

    public String getID() {
        return this.currentItem.getID();
    }

    public int getImageIndex() {
        if (this.currentItem.getType() == ManufacturingType.BUILDINGS) {
            return Buildings.getBuilding(getBuildingID()).getImageIndex();
        }
        return GameIconEnum.NONE.ordinal();
    }

    public String getName() {
        return this.currentItem.getName();
    }

    public int getPercentDone() {
        if (this.currentItem.getRequiredProduction() == 0) {
            return 0;
        }
        int requiredProduction = (int) ((this.production / this.currentItem.getRequiredProduction()) * 100.0f);
        if (requiredProduction > 100) {
            return 100;
        }
        return requiredProduction;
    }

    public LinkedList<ProductionItem> getQueue() {
        return this.queue;
    }

    public Map<String, Ship> getQueuedShipDesigns() {
        return this.queuedShipDesigns;
    }

    public int getRequiredProduction() {
        return this.currentItem.getRequiredProduction();
    }

    public int getShipIconIndex() {
        Ship ship = this.queuedShipDesigns.get(this.currentItem.getID());
        return ship.getShipType().getIcon(this.empireID, ship.getHullDesign());
    }

    public ShipType getShipType() {
        return this.queuedShipDesigns.get(this.currentItem.getID()).getShipType();
    }

    public ManufacturingType getType() {
        return this.currentItem.getType();
    }

    public boolean isQueueFull() {
        return this.queue.size() >= 5;
    }

    public void removeFromQueue(int i) {
        this.queue.remove(i);
    }

    public void setBuilding(Building building) {
        String stringID = building.getStringID();
        ProductionItem productionItem = new ProductionItem(ManufacturingType.BUILDINGS, stringID, building.getName(), building.getProductionCost());
        removeFromQueueOnID(stringID);
        this.currentItem = productionItem;
    }

    public void setCurrentItem(ProductionItem productionItem) {
        this.currentItem = productionItem;
    }

    public void setEmpireID(int i) {
        this.empireID = i;
    }

    public void setRefitShip(Ship ship, Ship ship2) {
        String str = "refit-" + ship2.getID();
        String str2 = "Refit " + ship2.getName();
        Ship createClone = ship.createClone(str);
        int productionCost = createClone.getProductionCost();
        double productionCost2 = ship2.getProductionCost();
        Double.isNaN(productionCost2);
        int i = productionCost - ((int) (productionCost2 * 0.5d));
        if (i < 0) {
            i = 50;
        }
        this.currentItem = new ProductionItem(ManufacturingType.SHIP, str, str2, i);
        addToQueuedShipDesigns(createClone);
    }

    public void setShip(Ship ship) {
        String name = ship.getName();
        Ship createClone = ship.createClone("Queue-" + UUID.randomUUID().toString());
        this.currentItem = new ProductionItem(ManufacturingType.SHIP, createClone.getID(), name, createClone.getProductionCost());
        addToQueuedShipDesigns(createClone);
    }

    public boolean showGreyProgressBar() {
        if (this.currentItem.getType() == ManufacturingType.NONE) {
            return true;
        }
        return this.currentItem.getType() == ManufacturingType.BUILDINGS && Buildings.getBuilding(this.currentItem.getID()).getBuildingType() == BuildingType.TRADEGOODS;
    }

    private Manufacturing(Builder builder) {
        this.queue = builder.queue;
        Map<String, Ship> map = builder.queuedShipDesigns;
        this.queuedShipDesigns = map;
        this.production = builder.production;
        this.empireID = builder.empireID;
        this.systemID = builder.systemID;
        this.orbit = builder.orbit;
        int i = AnonymousClass1.f1357a[builder.currentItemType.ordinal()];
        if (i == 1) {
            loadSetShip(map.get(builder.currentItemID), builder.isRepeatOn);
        } else if (i == 2) {
            loadSetBuilding(Buildings.getBuilding(builder.currentItemID));
        } else if (i != 3) {
        } else {
            setNone();
        }
    }
}
