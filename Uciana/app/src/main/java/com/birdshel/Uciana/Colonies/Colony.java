package com.birdshel.Uciana.Colonies;

import com.birdshel.Uciana.AI.Tasks.NoTask;
import com.birdshel.Uciana.AI.Tasks.Task;
import com.birdshel.Uciana.Colonies.Buildings.Building;
import com.birdshel.Uciana.Colonies.Buildings.BuildingEffectType;
import com.birdshel.Uciana.Colonies.Buildings.BuildingID;
import com.birdshel.Uciana.Colonies.Buildings.BuildingType;
import com.birdshel.Uciana.Colonies.Buildings.Buildings;
import com.birdshel.Uciana.Events.EventType;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.ResourceID;
import com.birdshel.Uciana.Planets.ResourceType;
import com.birdshel.Uciana.Planets.Resources;
import com.birdshel.Uciana.Players.RaceAttributeType;
import com.birdshel.Uciana.R;
import com.google.android.gms.games.GamesStatusCodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Colony {
    private boolean autoBuild;
    private final Map<BuildingID, Integer> buildingProgress;
    private final List<String> buildings;
    private int empireID;
    private int farmersPercent;
    private boolean hasSoldABuildingThisTurn;
    private int importedFood;
    private int infDivisions;
    private final Manufacturing manufacturing;
    private String name;
    private final Planet planet;
    private int population;
    private final Queue<Integer> populationGroups;
    private int scientistPercent;
    private Task task;
    private final int turnFounded;
    private int workersPercent;

    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Colonies.Colony$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a */
        static final /* synthetic */ int[] f1356a;

        static {
            int[] iArr = new int[BuildingID.values().length];
            f1356a = iArr;
            try {
                iArr[BuildingID.INFANTRY_BARRACKS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* compiled from: MyApplication */
    /* loaded from: classes.dex */
    public static class Builder {
        private boolean autoBuild;
        private int empireID;
        private int farmersPercent;
        private boolean hasSoldABuildingThisTurn;
        private int infDivisions;
        private Manufacturing manufacturing;
        private String name;
        private Planet planet;
        private int population;
        private int scientistPercent;
        private Task task;
        private int turnFounded;
        private int workersPercent;
        private List<String> buildings = new ArrayList();
        private Map<BuildingID, Integer> buildingProgress = new HashMap();
        private Queue<Integer> populationGroups = new LinkedList();
        private final int importedFood = 0;

        public Builder autoBuild(boolean z) {
            this.autoBuild = z;
            return this;
        }

        public Builder buildingProgress(Map<BuildingID, Integer> map) {
            this.buildingProgress = map;
            return this;
        }

        public Builder buildings(List<String> list) {
            this.buildings = list;
            return this;
        }

        public Builder empireID(int i) {
            this.empireID = i;
            return this;
        }

        public Builder farmersPercent(int i) {
            this.farmersPercent = i;
            return this;
        }

        public Builder hasSoldABuildingThisTurn(boolean z) {
            this.hasSoldABuildingThisTurn = z;
            return this;
        }

        public Builder infDivisions(int i) {
            this.infDivisions = i;
            return this;
        }

        public Colony loadColony() {
            if (this.farmersPercent > 100) {
                this.farmersPercent = 100;
                this.workersPercent = 0;
                this.scientistPercent = 0;
            }
            return new Colony(this);
        }

        public Builder manufacturing(Manufacturing manufacturing) {
            this.manufacturing = manufacturing;
            return this;
        }

        public Builder name(String str) {
            if (str != null) {
                this.name = str;
            } else {
                this.name = "";
            }
            return this;
        }

        public Colony newColony(int i) {
            this.population = i * 1000;
            this.farmersPercent = 35;
            this.workersPercent = 35;
            this.scientistPercent = 30;
            if (this.planet.getFoodPerFarmer(this.empireID) == 0.0f) {
                this.farmersPercent = 0;
                this.workersPercent = 65;
                this.scientistPercent = 35;
            }
            this.manufacturing = new Manufacturing.Builder().empireID(this.empireID).systemID(this.planet.getSystemID()).orbit(this.planet.getOrbit()).build();
            this.task = new NoTask();
            this.autoBuild = false;
            this.hasSoldABuildingThisTurn = false;
            this.infDivisions = (int) GameData.empires.get(this.empireID).getAttributeTypeBonus(RaceAttributeType.STARTING_TROOPS_FOR_COLONY);
            this.buildingProgress = new HashMap();
            for (int i2 = 0; i2 < 10; i2++) {
                this.populationGroups.add(Integer.valueOf(this.empireID));
            }
            this.turnFounded = GameData.turn;
            GameData.fleets.checkForChanceToAttack(this.planet.getSystemID());
            return new Colony(this);
        }

        public Builder planet(Planet planet) {
            this.planet = planet;
            return this;
        }

        public Builder population(int i) {
            this.population = i;
            return this;
        }

        public Builder populationGroups(Queue<Integer> queue) {
            this.populationGroups = queue;
            return this;
        }

        public Builder scientistPercent(int i) {
            this.scientistPercent = i;
            return this;
        }

        public Builder task(Task task) {
            this.task = task;
            return this;
        }

        public Builder turnFounded(int i) {
            this.turnFounded = i;
            return this;
        }

        public Builder workerPercent(int i) {
            this.workersPercent = i;
            return this;
        }
    }

    public Colony(Builder builder) {
        this.empireID = builder.empireID;
        this.population = builder.population;
        this.planet = builder.planet;
        this.manufacturing = builder.manufacturing;
        this.buildings = builder.buildings;
        this.hasSoldABuildingThisTurn = builder.hasSoldABuildingThisTurn;
        this.infDivisions = builder.infDivisions;
        this.buildingProgress = builder.buildingProgress;
        this.populationGroups = builder.populationGroups;
        this.farmersPercent = builder.farmersPercent;
        this.workersPercent = builder.workersPercent;
        this.scientistPercent = builder.scientistPercent;
        this.importedFood = builder.importedFood;
        this.task = builder.task;
        this.autoBuild = builder.autoBuild;
        this.turnFounded = builder.turnFounded;
        this.name = builder.name;
    }

    private float getBuildingEffect(BuildingEffectType buildingEffectType) {
        float f2 = 0.0f;
        for (String str : this.buildings) {
            Building building = Buildings.getBuilding(str);
            Map<BuildingEffectType, Float> effects = building.getEffects();
            if (effects.containsKey(buildingEffectType)) {
                float floatValue = effects.get(buildingEffectType).floatValue();
                if (isLowPower() && building.getPowerRequirement() > 0) {
                    floatValue *= 0.5f;
                }
                f2 += floatValue;
            }
        }
        return f2;
    }

    private float getEmpireWideBonus(ResourceType resourceType) {
        float f2 = 0.0f;
        if (isBlockaded()) {
            for (ResourceID resourceID : this.planet.getVisibleResources(this.empireID)) {
                f2 += Resources.get(resourceID).getValue(resourceType);
            }
        } else {
            for (Map.Entry<ResourceID, Integer> entry : GameData.empires.get(this.empireID).getResources().entrySet()) {
                f2 += Resources.get(entry.getKey()).getValue(resourceType) * entry.getValue().intValue();
            }
        }
        return f2;
    }

    private int getExpense() {
        return getExpenseFromBuildings() + 0;
    }

    private int getFoodFromBuildings() {
        return (int) (getBuildingEffect(BuildingEffectType.FOOD) * this.planet.getGravity().getModifier());
    }

    private int getFoodFromResources() {
        int i = 0;
        for (ResourceID resourceID : this.planet.getResources(this.empireID, ResourceType.FOOD)) {
            i = (int) (i + Resources.get(resourceID).getValue(ResourceType.FOOD));
        }
        return (int) (i * this.planet.getGravity().getModifier());
    }

    private float getOccupationPenalty() {
        float f2 = 0.0f;
        if (isAllPopulationSupportive()) {
            return 0.0f;
        }
        for (int i = 0; i < 10 - getPopulationGroupSupport(); i++) {
            f2 -= 0.1f;
        }
        return f2;
    }

    private int getPopulationGroupSupport() {
        int i = 0;
        for (Integer num : this.populationGroups) {
            if (num.intValue() == this.empireID) {
                i++;
            }
        }
        return i;
    }

    private float getProductionBeforeTaxes() {
        float population = (getPopulation() * this.workersPercent * 0.01f * getProductionPerWorker()) + getProductionFromBuildings();
        if (!isBlockaded()) {
            population += (GameData.galaxy.getStarSystem(this.planet.getSystemID()).getAsteroidProductionBonus(this.empireID) / 100.0f) * population;
        }
        return (population * getHappiness()) + getProductionFromResources();
    }

    private int getProductionFromBuildings() {
        return (int) (getBuildingEffect(BuildingEffectType.PRODUCTION) * this.planet.getGravity().getModifier());
    }

    private int getProductionFromResources() {
        int i = 0;
        for (ResourceID resourceID : this.planet.getResources(this.empireID, ResourceType.PRODUCTION)) {
            i = (int) (i + Resources.get(resourceID).getValue(ResourceType.PRODUCTION));
        }
        return (int) (i * this.planet.getGravity().getModifier());
    }

    private int getRequiredPower() {
        int i = 0;
        for (String str : getBuildings()) {
            i += Buildings.getBuilding(str).getPowerRequirement();
        }
        return i;
    }

    private int getScienceFromBuildings() {
        return (int) (getBuildingEffect(BuildingEffectType.SCIENCE) * this.planet.getGravity().getModifier());
    }

    private void removeBuildingForCredits(BuildingID buildingID) {
        GameData.empires.get(this.empireID).addRemoveCredits(Buildings.getBuilding(buildingID).getSellValue());
        removeBuilding(buildingID);
    }

    private void updateMilitary() {
        if (this.infDivisions < getInfantryCapacity()) {
            for (String str : getBuildings()) {
                Building building = Buildings.getBuilding(str);
                if (building.getBuildingType() == BuildingType.GROUND_TROOPS && AnonymousClass1.f1356a[building.getID().ordinal()] == 1) {
                    Map<BuildingID, Integer> map = this.buildingProgress;
                    BuildingID buildingID = BuildingID.INFANTRY_BARRACKS;
                    int i = 0;
                    if (!map.containsKey(buildingID)) {
                        this.buildingProgress.put(buildingID, 0);
                    }
                    int intValue = this.buildingProgress.get(buildingID).intValue() + 1;
                    if (intValue >= 5) {
                        this.infDivisions++;
                    } else {
                        i = intValue;
                    }
                    this.buildingProgress.put(buildingID, Integer.valueOf(i));
                }
            }
        }
    }

    private void updatePopulation() {
        int populationGrowth = getPopulationGrowth();
        boolean z = getPopulation() != 0;
        this.population += populationGrowth;
        if (z && getPopulation() == 0) {
            this.population = 0;
            GameData.events.addPlanetEvent(EventType.COLONY_STARVED_OFF, this.empireID, getSystemID(), getOrbit());
        }
    }

    private void updatePopulationGroups() {
        if (isAllPopulationSupportive()) {
            return;
        }
        for (Integer num : this.populationGroups) {
            if (num.intValue() != this.empireID) {
                this.populationGroups.remove();
                this.populationGroups.add(Integer.valueOf(this.empireID));
                if (GameData.empires.get(this.empireID).hasAttributeType(RaceAttributeType.ASSIMILATION_RATE_BONUS)) {
                    this.populationGroups.remove();
                    this.populationGroups.add(Integer.valueOf(this.empireID));
                    return;
                }
                return;
            }
        }
    }

    private void updateProduction() {
        this.manufacturing.d(getProductionPerTurn());
    }

    public void a(String str) {
        if (str.equals(BuildingID.CAPITAL.toString())) {
            GameData.empires.get(this.empireID).capitalBuilt();
        }
        this.buildings.add(str);
    }

    public void addBuilding(BuildingID buildingID) {
        this.buildings.add(buildingID.toString());
    }

    public void addPopulation(int i) {
        this.population += i * 1000;
        if (isFull()) {
            this.population = this.planet.getMaxPopulation() * 1000;
        }
    }

    public void b() {
        updatePopulation();
        updateProduction();
        this.hasSoldABuildingThisTurn = false;
        updateMilitary();
        updatePopulationGroups();
        if (GameData.empires.get(this.empireID).isHuman()) {
            Game.gameAchievements.checkForEndOfTurnAchievements(this);
        }
    }

    public void buildingBombed() {
        if (this.buildings.size() > 0) {
            BuildingID buildingID = BuildingID.getBuildingID(this.buildings.get(Functions.random.nextInt(this.buildings.size())));
            removeBuilding(buildingID);
            if (buildingID == BuildingID.CAPITAL) {
                GameData.empires.get(this.empireID).capitalDestroyed();
                GameData.events.addPlanetEvent(EventType.CAPITAL_DESTROYED, this.empireID, getSystemID(), getOrbit());
            }
        }
    }

    public void c(int i, boolean z) {
        this.empireID = i;
        if (getPopulation() == 0 || z) {
            this.populationGroups.clear();
            for (int i2 = 0; i2 < 10; i2++) {
                this.populationGroups.add(Integer.valueOf(i));
            }
        }
        this.farmersPercent = 35;
        this.workersPercent = 35;
        this.scientistPercent = 30;
        if (this.planet.getFoodPerFarmer(i) == 0.0f) {
            this.farmersPercent = 0;
            this.workersPercent = 65;
            this.scientistPercent = 35;
        }
    }

    public void clearImportedFood() {
        this.importedFood = 0;
    }

    public List<Building> getAvailableBuildingList() {
        ArrayList arrayList = new ArrayList();
        for (Building building : GameData.empires.get(this.empireID).getBuildingList()) {
            if (building.canBeBuilt(this)) {
                arrayList.add(building);
            }
        }
        return arrayList;
    }

    public Map<BuildingID, Integer> getBuildingProgress() {
        return this.buildingProgress;
    }

    public List<String> getBuildings() {
        return this.buildings;
    }

    public List<BuildingID> getBuildingsThatCanBeScrapped(boolean z) {
        ArrayList arrayList = new ArrayList();
        for (String str : this.buildings) {
            Building building = Buildings.getBuilding(str);
            if (building.canBeSold()) {
                if (z) {
                    if (building.getMaintenanceCost() > 0) {
                        arrayList.add(building.getID());
                    }
                } else {
                    arrayList.add(building.getID());
                }
            }
        }
        return arrayList;
    }

    public int getColonyValue() {
        return this.planet.getPlanetValue() + (getPopulation() / 10) + 0 + this.buildings.size();
    }

    public int getCommandPoints() {
        return (int) getBuildingEffect(BuildingEffectType.COMMAND_POINTS);
    }

    public int getCreditsPerTurn() {
        return getRevenue() - getExpense();
    }

    public int getDefenseBonus() {
        return (int) (((int) getBuildingEffect(BuildingEffectType.DEFENSE)) + this.planet.getDefenceBonus() + GameData.empires.get(this.empireID).getAttributeTypeBonus(RaceAttributeType.DEFENDING_GROUND_COMBAT));
    }

    public int getDevelopmentScore() {
        return getPopulation() + (this.buildings.size() * 5);
    }

    public int getEmpireID() {
        return this.empireID;
    }

    public int getExpenseFromBuildings() {
        int i = 0;
        for (String str : this.buildings) {
            i += Buildings.getBuilding(str).getMaintenanceCost();
        }
        return (int) (i * this.planet.getMaintenanceCostModifier());
    }

    public int getFarmersPercent() {
        return this.farmersPercent;
    }

    public float getFoodPerFarmer() {
        return this.planet.getFoodPerFarmer(this.empireID) + (getBuildingEffect(BuildingEffectType.FOOD_PER_FARMER) * this.planet.getGravity().getModifier());
    }

    public int getFoodPerTurn() {
        return Math.round((getPopulation() * this.farmersPercent * 0.01f * getFoodPerFarmer() * getHappiness()) + getFoodFromBuildings() + getFoodFromResources());
    }

    public float getHappiness() {
        float happiness = GameData.empires.get(this.empireID).getHappiness() + getBuildingEffect(BuildingEffectType.HAPPINESS);
        for (ResourceID resourceID : this.planet.getResources(this.empireID, ResourceType.HAPPINESS)) {
            happiness += Resources.get(resourceID).getValue(ResourceType.HAPPINESS);
        }
        float empireWideBonus = happiness + getEmpireWideBonus(ResourceType.HAPPINESS_EMPIRE_WIDE) + getOccupationPenalty();
        if (empireWideBonus < 0.0f) {
            return 0.0f;
        }
        return empireWideBonus;
    }

    public int getImportedFood() {
        return this.importedFood;
    }

    public float getIncomePercentIncrease() {
        float buildingEffect = getBuildingEffect(BuildingEffectType.CREDITS_INCREASE_PERCENT) + 0.0f;
        for (ResourceID resourceID : this.planet.getResources(this.empireID, ResourceType.CREDITS_PERCENT)) {
            buildingEffect += Resources.get(resourceID).getValue(ResourceType.CREDITS_PERCENT);
        }
        return buildingEffect + GameData.empires.get(this.empireID).getAttributeTypeBonus(RaceAttributeType.CREDITS_INCREASE);
    }

    public int getInfDivisions() {
        return this.infDivisions;
    }

    public int getInfantryCapacity() {
        return this.planet.getMaxPopulation() / 10;
    }

    public Manufacturing getManufacturing() {
        return this.manufacturing;
    }

    public String getName() {
        String str = this.name;
        if (str != null && !str.equals("")) {
            return this.name;
        }
        return this.planet.getName();
    }

    public String getNameOfProduction() {
        return this.manufacturing.getName();
    }

    public int getNetFoodPerTurn() {
        return getFoodPerTurn() - getPopulation();
    }

    public int getOrbit() {
        return this.planet.getOrbit();
    }

    public Planet getPlanet() {
        return this.planet;
    }

    public int getPopulation() {
        return this.population / 1000;
    }

    public Queue<Integer> getPopulationGroups() {
        return this.populationGroups;
    }

    public int getPopulationGrowth() {
        int pow = ((int) ((((int) Math.pow((int) ((((getPopulation() * GamesStatusCodes.STATUS_SNAPSHOT_NOT_FOUND) * (this.planet.getMaxPopulation() - getPopulation())) / this.planet.getMaxPopulation()) * this.planet.getPopulationGrowthModifier()), 0.5d)) * 2) + (getBuildingEffect(BuildingEffectType.POPULATION_GROWTH) * 1000.0f))) - (getTotalFoodPerTurn() < 0 ? (getTotalFoodPerTurn() * (-1)) * 500 : 0);
        return this.population + pow > this.planet.getMaxPopulation() * 1000 ? (this.planet.getMaxPopulation() * 1000) - this.population : pow;
    }

    public int getPowerLevel() {
        int i = 5;
        for (String str : this.buildings) {
            Map<BuildingEffectType, Float> effects = Buildings.getBuilding(str).getEffects();
            BuildingEffectType buildingEffectType = BuildingEffectType.POWER;
            if (effects.containsKey(buildingEffectType)) {
                i = (int) (i + effects.get(buildingEffectType).floatValue());
            }
        }
        return ((int) ((i + this.planet.getNaturalPower(this.empireID)) + getEmpireWideBonus(ResourceType.POWER_EMPIRE_WIDE))) - getRequiredPower();
    }

    public int getProductionPerTurn() {
        float productionBeforeTaxes = getProductionBeforeTaxes();
        return Math.round(productionBeforeTaxes - (GameData.empires.get(this.empireID).getTaxRate() * productionBeforeTaxes));
    }

    public float getProductionPerWorker() {
        return this.planet.getProductionPerWorker(this.empireID) + getEmpireWideBonus(ResourceType.PRODUCTION_PER_WORKER_EMPIRE_WIDE) + getBuildingEffect(BuildingEffectType.PRODUCTION_PER_WORKER);
    }

    public LinkedList<ProductionItem> getQueue() {
        return this.manufacturing.getQueue();
    }

    public int getRealPopulation() {
        return this.population;
    }

    public int getResistanceCount() {
        return 10 - getPopulationGroupSupport();
    }

    public List<ResourceID> getResources() {
        return this.planet.getVisibleResources(this.empireID);
    }

    public int getRevenue() {
        float incomePercentIncrease = getIncomePercentIncrease();
        return getRevenueFromTradegoods(incomePercentIncrease) + 0 + getRevenueFromPopulationTaxes(incomePercentIncrease) + getRevenueFromIndustrialTaxes(incomePercentIncrease) + getRevenueFromResources(incomePercentIncrease);
    }

    public int getRevenueFromIndustrialTaxes(float f2) {
        int round = Math.round(getProductionBeforeTaxes() * GameData.empires.get(this.empireID).getTaxRate() * 0.5f);
        return round + ((int) (round * f2));
    }

    public int getRevenueFromPopulationTaxes(float f2) {
        int round = Math.round(getPopulation() * 0.7f);
        return round + ((int) (round * f2));
    }

    public int getRevenueFromResources(float f2) {
        int i = 0;
        for (ResourceID resourceID : this.planet.getResources(this.empireID, ResourceType.CREDITS)) {
            i = (int) (i + Resources.get(resourceID).getValue(ResourceType.CREDITS));
        }
        return i + ((int) (i * f2));
    }

    public int getRevenueFromTradegoods(float f2) {
        int i = 0;
        if (this.manufacturing.getType() == ManufacturingType.BUILDINGS && Buildings.getBuilding(this.manufacturing.getBuildingID()).getBuildingType() == BuildingType.TRADEGOODS) {
            i = 0 + ((int) (this.manufacturing.c() * getProductionPerTurn()));
        }
        return i + ((int) (i * f2));
    }

    public float getSciencePerScientist() {
        float researchBonus = this.planet.getClimate().getResearchBonus() + 2;
        for (ResourceID resourceID : this.planet.getResources()) {
            researchBonus += Resources.get(resourceID).getValue(ResourceType.SCIENCE_PER_SCIENTIST);
        }
        float f2 = 0.0f;
        for (String str : this.buildings) {
            Building building = Buildings.getBuilding(str);
            Map<BuildingEffectType, Float> effects = building.getEffects();
            BuildingEffectType buildingEffectType = BuildingEffectType.SCIENCE_PER_SCIENTIST;
            if (effects.containsKey(buildingEffectType)) {
                f2 += building.getEffects().get(buildingEffectType).floatValue();
            }
        }
        if (isLowPower()) {
            f2 *= 0.5f;
        }
        return (researchBonus + f2 + GameData.empires.get(this.empireID).getSciencePerks()) * this.planet.getGravity().getModifier();
    }

    public int getSciencePerTurn() {
        float population = ((getPopulation() * this.scientistPercent * 0.01f * getSciencePerScientist()) + getScienceFromBuildings()) * getHappiness();
        if (!isBlockaded()) {
            population *= (GameData.galaxy.getStarSystem(this.planet.getSystemID()).getSciencePercentBonus(this.empireID) * 0.01f) + 1.0f;
        }
        return Math.round(population);
    }

    public int getScientistPercent() {
        return this.scientistPercent;
    }

    public float getShieldingStrength() {
        return getBuildingEffect(BuildingEffectType.PLANET_SHIELDING);
    }

    public int getSystemID() {
        return this.planet.getSystemID();
    }

    public Task getTask() {
        return this.task;
    }

    public int getTotalFoodPerTurn() {
        return getNetFoodPerTurn() + this.importedFood;
    }

    public int getTurnFounded() {
        return this.turnFounded;
    }

    public String getTurnsLeftOnProduction() {
        if (this.manufacturing.getType() == ManufacturingType.BUILDINGS) {
            if (Buildings.getBuilding(this.manufacturing.getBuildingID()).getBuildingType() == BuildingType.TRADEGOODS) {
                return GameData.activity.getString(R.string.colony_production_ongoing);
            }
        } else if (getManufacturing().getType() == ManufacturingType.NONE) {
            return GameData.activity.getString(R.string.colony_production_select);
        }
        if (getProductionPerTurn() > 0) {
            double b = this.manufacturing.b();
            double productionPerTurn = getProductionPerTurn();
            Double.isNaN(b);
            Double.isNaN(productionPerTurn);
            return Integer.toString((int) Math.ceil(b / productionPerTurn));
        }
        return GameData.activity.getString(R.string.colony_production_never);
    }

    public String getTurnsLeftOnProductionString() {
        if (this.manufacturing.getType() == ManufacturingType.BUILDINGS) {
            if (Buildings.getBuilding(this.manufacturing.getBuildingID()).getBuildingType() == BuildingType.TRADEGOODS) {
                return GameData.activity.getString(R.string.colony_production_ongoing);
            }
        } else if (getManufacturing().getType() == ManufacturingType.NONE) {
            return GameData.activity.getString(R.string.colony_production_select);
        }
        if (getProductionPerTurn() > 0) {
            double b = this.manufacturing.b();
            double productionPerTurn = getProductionPerTurn();
            Double.isNaN(b);
            Double.isNaN(productionPerTurn);
            int ceil = (int) Math.ceil(b / productionPerTurn);
            return ceil <= 1 ? GameData.activity.getString(R.string.colony_production_turn) : GameData.activity.getString(R.string.colony_production_turns, new Object[]{Integer.valueOf(ceil)});
        }
        return GameData.activity.getString(R.string.colony_production_never);
    }

    public int getWorkersPercent() {
        return this.workersPercent;
    }

    public boolean hasBuilding(BuildingID buildingID) {
        return this.buildings.contains(buildingID.toString());
    }

    public boolean hasBuildingThatCanBeScrapped(boolean z) {
        for (String str : this.buildings) {
            Building building = Buildings.getBuilding(str);
            if (building.canBeSold() && (!z || building.getMaintenanceCost() > 0)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasDefences() {
        for (String str : this.buildings) {
            if (Buildings.getBuilding(str).getBuildingType() == BuildingType.FLEET_DEFENSE) {
                return true;
            }
        }
        return false;
    }

    public boolean hasSoldABuildingThisTurn() {
        return this.hasSoldABuildingThisTurn;
    }

    public void importFood(int i) {
        this.importedFood += i;
    }

    public void infKilledOff() {
        this.infDivisions--;
    }

    public void infLanded(int i) {
        int i2 = this.infDivisions + i;
        this.infDivisions = i2;
        if (i2 > getInfantryCapacity()) {
            this.infDivisions = getInfantryCapacity();
        }
    }

    public boolean isAlive() {
        return getPopulation() > 0 || this.buildings.size() > 0 || getInfDivisions() > 0;
    }

    public boolean isAllPopulationSupportive() {
        for (Integer num : this.populationGroups) {
            if (num.intValue() != this.empireID) {
                return false;
            }
        }
        return true;
    }

    public boolean isAutoBuilding() {
        return this.autoBuild;
    }

    public boolean isBlockaded() {
        return GameData.empires.get(this.empireID).isSystemBlockaded(getSystemID());
    }

    public boolean isDeveloped() {
        return getDevelopmentScore() >= GameData.empires.get(this.empireID).getAverageDevelopmentScore();
    }

    public boolean isFull() {
        return this.population >= this.planet.getMaxPopulation() * 1000;
    }

    public boolean isLowPower() {
        return getPowerLevel() < 0;
    }

    public boolean isShielded() {
        return hasBuilding(BuildingID.PLANETARY_SHIELD) && !isLowPower();
    }

    public boolean isStarving() {
        return getTotalFoodPerTurn() < 0;
    }

    public void militaryBombed() {
        int i = this.infDivisions;
        if (i != 0) {
            this.infDivisions = i - 1;
        }
    }

    public boolean needsProductionSet() {
        return getManufacturing().getType() == ManufacturingType.NONE;
    }

    public void populationBombed() {
        int i = this.population - 10000;
        this.population = i;
        if (i < 0) {
            this.population = 0;
        }
    }

    public void removeBuilding(BuildingID buildingID) {
        if (hasBuilding(buildingID)) {
            this.buildings.remove(buildingID.toString());
            if (getPopulation() > this.planet.getMaxPopulation()) {
                this.population = this.planet.getMaxPopulation() * 1000;
            }
        }
    }

    public void removePopulation(int i) {
        int i2 = this.population - (i * 1000);
        this.population = i2;
        if (i2 < 0) {
            this.population = 0;
        }
    }

    public void scrapBuilding(BuildingID buildingID) {
        removeBuildingForCredits(buildingID);
        GameData.events.addBuildingScrapEvent(this.empireID, getSystemID(), getOrbit(), buildingID);
    }

    public void sellBuilding(BuildingID buildingID) {
        removeBuildingForCredits(buildingID);
        this.hasSoldABuildingThisTurn = true;
    }

    public void setAutoBuild(boolean z) {
        this.autoBuild = z;
    }

    public void setFarmersPercent(int i) {
        this.farmersPercent = i;
    }

    public void setInfDivisions(int i) {
        this.infDivisions = i;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setPopulationToRevolt() {
        this.populationGroups.clear();
        for (int i = 0; i < 10; i++) {
            this.populationGroups.add(-1);
        }
    }

    public void setScientistPercent(int i) {
        this.scientistPercent = i;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void setWorkersPercent(int i) {
        this.workersPercent = i;
    }
}
