package com.birdshel.Uciana.Players;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;

import com.birdshel.Uciana.AI.FleetTasks.AutoFleetTaskAI;
import com.birdshel.Uciana.AI.FleetTasks.FleetTaskAI;
import com.birdshel.Uciana.AI.Managers.AttackAI;
import com.birdshel.Uciana.AI.Managers.BattleAI;
import com.birdshel.Uciana.AI.Managers.ColonyProductionAI;
import com.birdshel.Uciana.AI.Managers.DiplomaticAI;
import com.birdshel.Uciana.AI.Managers.EconomicAI;
import com.birdshel.Uciana.AI.Managers.ManageColoniesAI;
import com.birdshel.Uciana.AI.Managers.MilitaryAI;
import com.birdshel.Uciana.AI.Managers.ResearchAI;
import com.birdshel.Uciana.AI.Managers.ShipDesignAI;
import com.birdshel.Uciana.AI.Personality;
import com.birdshel.Uciana.Colonies.Buildings.Building;
import com.birdshel.Uciana.Colonies.Buildings.BuildingID;
import com.birdshel.Uciana.Colonies.Buildings.BuildingType;
import com.birdshel.Uciana.Colonies.Buildings.Buildings;
import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Colonies.ColonyFoodNeeded;
import com.birdshel.Uciana.Colonies.ManufacturingType;
import com.birdshel.Uciana.Colonies.Outpost;
import com.birdshel.Uciana.Colonies.ProductionItem;
import com.birdshel.Uciana.Colonies.SortType;
import com.birdshel.Uciana.Events.Event;
import com.birdshel.Uciana.Events.EventDataFields;
import com.birdshel.Uciana.Events.EventType;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.GameProperties;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Messages.DiplomaticType;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.ResourceID;
import com.birdshel.Uciana.Planets.SystemObject;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.Ships.Ship;
import com.birdshel.Uciana.Ships.ShipComponents.Armor;
import com.birdshel.Uciana.Ships.ShipComponents.Shield;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponent;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponentID;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponents;
import com.birdshel.Uciana.Ships.ShipComponents.SpecialComponent;
import com.birdshel.Uciana.Ships.ShipComponents.SublightEngine;
import com.birdshel.Uciana.Ships.ShipComponents.TargetingComputer;
import com.birdshel.Uciana.Ships.ShipComponents.Weapon;
import com.birdshel.Uciana.Ships.ShipComponents.WeaponType;
import com.birdshel.Uciana.Ships.ShipSort;
import com.birdshel.Uciana.Ships.ShipType;
import com.birdshel.Uciana.StarSystems.StarSystem;
import com.birdshel.Uciana.Technology.Tech;
import com.birdshel.Uciana.Technology.TechCategory;
import com.birdshel.Uciana.Technology.TechID;
import com.birdshel.Uciana.Technology.Technology;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Empire {
    public static final int STAT_COLONIES_TYPE = 1;
    public static final int STAT_COMMAND_POINTS_TYPE = 3;
    public static final int STAT_CREDITS_PER_TURN_TYPE = 8;
    public static final int STAT_FOOD_TYPE = 4;
    public static final int STAT_POP_TYPE = 0;
    public static final int STAT_PRODUCTION_TYPE = 5;
    public static final int STAT_SCIENCE_TYPE = 6;
    public static final int STAT_SYSTEMS_TYPE = 2;
    public static final int STAT_TECH_TYPE = 7;
    private final AttackAI attackAI;
    private final List<RaceAttribute> attributes;
    private int averageDevelopmentScore;
    private final int bannerID;
    private int baseCommandPoints;
    private final BattleAI battleAI;
    private final BuildLists buildLists;
    private final SparseIntArray coloniesCountHistory;
    private final ColonyProductionAI colonyProductionAI;
    private final SparseIntArray commandPointUsageHistory;
    private int credits;
    private final SparseIntArray creditsPerTurnHistory;
    private final Map<ShipType, Integer> designVersions;
    private final DiplomaticAI diplomaticAI;
    private final List<Integer> discoveredSystems;
    private final SparseIntArray dispositionAI;
    private final EconomicAI economicAI;
    private final Map<String, Integer> events;
    private final SparseIntArray foodHistory;
    private boolean hasCapital;
    private final SparseBooleanArray hideAIProposals;
    private final SparseBooleanArray hideAutoShowSelectAttack;
    private final int homeSystemID;
    private final int homeWorldOrbit;
    public final int id;
    private boolean isAlive;
    private final List<Integer> knownEmpires;
    private final ManageColoniesAI manageColoniesAI;
    private final List<Migrants> migrants;
    private final MilitaryAI militaryAI;
    private final String name;
    private final Personality personality;
    private final SparseIntArray populationHistory;
    private final SparseIntArray productionHistory;
    private final int raceID;
    private final SparseIntArray relationValuesAI;
    private final ResearchAI researchAI;
    private final SparseIntArray scienceHistory;
    private String selectedFleetID;
    private int shipCount;
    private final ShipDesignAI shipDesignAI;
    private final List<Ship> shipDesigns;
    private ShipSort shipSort;
    private final int shipStyleID;
    private SortType sortBy;
    private int spyNetworks;
    private final SparseIntArray systemsCountHistory;
    private float taxRate;
    private final SparseIntArray techHistory;
    private final Map<TechCategory, Float> techListYs;
    private final Technology technology;
    private final Treaties treaties;
    private final EmpireType type;

    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Players.Empire$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a */
        static final /* synthetic */ int[] f1405a;
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[ShipComponentID.values().length];
            b = iArr;
            try {
                iArr[ShipComponentID.TITIANIUM_ARMOR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[ShipComponentID.NUCLUEAR_BOMB.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[ShipComponentID.TORPEDO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[ShipComponentID.STANDARD_CHEMICAL_ENGINES.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[ShipComponentID.LAZER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                b[ShipComponentID.OPTICS_TARGETING.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                b[ShipComponentID.SCOUTING.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                b[ShipComponentID.SCANNER.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            int[] iArr2 = new int[EmpireType.values().length];
            f1405a = iArr2;
            try {
                iArr2[EmpireType.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f1405a[EmpireType.AI.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f1405a[EmpireType.HUMAN.ordinal()] = 3;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    /* compiled from: MyApplication */
    /* loaded from: classes.dex */
    public static class Builder {
        private List<RaceAttribute> attributes;
        private int bannerID;
        private BuildLists buildLists;
        private Map<String, Integer> events;
        private int homeSystemID;
        private int homeWorldOrbit;
        private String name;
        private Personality personality;
        private int raceID;
        private int shipCount;
        private int shipStyleID;
        private int spyNetworks;
        private Technology technology;
        private EmpireType type;
        private List<Integer> discoveredSystems = new ArrayList();
        private List<Integer> knownEmpires = new ArrayList();
        private SparseBooleanArray hideAutoShowSelectAttack = new SparseBooleanArray();
        private SparseBooleanArray hideAIProposals = new SparseBooleanArray();
        private List<Ship> shipDesigns = new ArrayList();
        private List<Migrants> migrants = new ArrayList();
        private Map<ShipType, Integer> designVersions = new HashMap();
        private SparseIntArray relationValuesAI = new SparseIntArray();
        private SparseIntArray dispositionAI = new SparseIntArray();
        private SparseIntArray populationHistory = new SparseIntArray();
        private SparseIntArray colonyCountHistory = new SparseIntArray();
        private SparseIntArray systemsCountHistory = new SparseIntArray();
        private SparseIntArray commandPointUsageHistory = new SparseIntArray();
        private SparseIntArray foodHistory = new SparseIntArray();
        private SparseIntArray productionHistory = new SparseIntArray();
        private SparseIntArray scienceHistory = new SparseIntArray();
        private SparseIntArray techHistory = new SparseIntArray();
        private SparseIntArray creditsPerTurnHistory = new SparseIntArray();
        private final String selectedFleetID = "none";
        private int credits = 100;
        private float taxRate = 0.0f;
        private SortType sortBy = SortType.OLDEST_TO_NEWEST;
        private final ShipSort shipSort = ShipSort.A_TO_Z;
        private int baseCommandPoints = 0;
        private int id;
        private Treaties treaties = new Treaties(this.id);

        public Builder attributes(List<RaceAttribute> list) {
            this.attributes = list;
            return this;
        }

        public Builder bannerID(int i) {
            this.bannerID = i;
            return this;
        }

        public Builder baseCommandPoints(int i) {
            this.baseCommandPoints = i;
            return this;
        }

        public Builder buildLists(BuildLists buildLists) {
            this.buildLists = buildLists;
            return this;
        }

        public Builder colonyCountHistory(SparseIntArray sparseIntArray) {
            this.colonyCountHistory = sparseIntArray;
            return this;
        }

        public Builder commandPointUsageHistory(SparseIntArray sparseIntArray) {
            this.commandPointUsageHistory = sparseIntArray;
            return this;
        }

        public Builder credits(int i) {
            this.credits = i;
            return this;
        }

        public Builder creditsPerTurnHistory(SparseIntArray sparseIntArray) {
            this.creditsPerTurnHistory = sparseIntArray;
            return this;
        }

        public Builder designVersions(Map<ShipType, Integer> map) {
            this.designVersions = map;
            return this;
        }

        public Builder discoveredSystems(List<Integer> list) {
            this.discoveredSystems = list;
            return this;
        }

        public Builder dispositionAI(SparseIntArray sparseIntArray) {
            this.dispositionAI = sparseIntArray;
            return this;
        }

        public Builder events(Map<String, Integer> map) {
            this.events = map;
            return this;
        }

        public Builder foodHistory(SparseIntArray sparseIntArray) {
            this.foodHistory = sparseIntArray;
            return this;
        }

        public Builder hideAIProposals(SparseBooleanArray sparseBooleanArray) {
            this.hideAIProposals = sparseBooleanArray;
            return this;
        }

        public Builder hideAutoShowSelectAttack(SparseBooleanArray sparseBooleanArray) {
            this.hideAutoShowSelectAttack = sparseBooleanArray;
            return this;
        }

        public Builder homeSystemID(int i) {
            this.homeSystemID = i;
            return this;
        }

        public Builder homeWorldOrbit(int i) {
            this.homeWorldOrbit = i;
            return this;
        }

        public Builder id(int i) {
            this.id = i;
            return this;
        }

        public Builder knownEmpires(List<Integer> list) {
            this.knownEmpires = list;
            return this;
        }

        public Empire loadEmpire(int i) {
            this.technology.setTech(TechID.getTechID(i));
            return new Empire(this);
        }

        public Builder migrants(List<Migrants> list) {
            this.migrants = list;
            return this;
        }

        public Builder name(String str) {
            this.name = str;
            return this;
        }

        public Empire newEmpire() {
            this.discoveredSystems.add(Integer.valueOf(this.homeSystemID));
            this.name = GameData.empires.getDefaultName(this.raceID);
            for (int i = 0; i < 7; i++) {
                this.hideAutoShowSelectAttack.put(i, false);
                this.hideAIProposals.put(i, false);
            }
            this.personality = Personality.values()[Functions.random.nextInt(Personality.values().length)];
            for (int i2 = 0; i2 < 7; i2++) {
                int nextInt = Functions.random.nextInt(3);
                this.dispositionAI.put(i2, nextInt);
                this.relationValuesAI.put(i2, nextInt != 0 ? nextInt != 1 ? 60 : 50 : 40);
            }
            this.technology = new Technology(this.id, this.type);
            this.designVersions.put(ShipType.DESTROYER, 1);
            this.designVersions.put(ShipType.CRUISER, 1);
            this.designVersions.put(ShipType.BATTLESHIP, 1);
            this.designVersions.put(ShipType.DREADNOUGHT, 1);
            this.shipCount = 0;
            this.spyNetworks = 0;
            this.events = new HashMap();
            this.buildLists = new BuildLists();
            return new Empire(this);
        }

        public Builder personality(Personality personality) {
            this.personality = personality;
            return this;
        }

        public Builder populationHistory(SparseIntArray sparseIntArray) {
            this.populationHistory = sparseIntArray;
            return this;
        }

        public Builder productionHistory(SparseIntArray sparseIntArray) {
            this.productionHistory = sparseIntArray;
            return this;
        }

        public Builder raceID(int i) {
            this.raceID = i;
            return this;
        }

        public Builder relationValuesAI(SparseIntArray sparseIntArray) {
            this.relationValuesAI = sparseIntArray;
            return this;
        }

        public Builder scienceHistory(SparseIntArray sparseIntArray) {
            this.scienceHistory = sparseIntArray;
            return this;
        }

        public Builder shipCount(int i) {
            this.shipCount = i;
            return this;
        }

        public Builder shipDesigns(List<Ship> list) {
            this.shipDesigns = list;
            return this;
        }

        public Builder shipStyleID(int i) {
            this.shipStyleID = i;
            return this;
        }

        public Builder sortBy(SortType sortType) {
            this.sortBy = sortType;
            return this;
        }

        public Builder spyNetworks(int i) {
            this.spyNetworks = i;
            return this;
        }

        public Builder systemsCountHistory(SparseIntArray sparseIntArray) {
            this.systemsCountHistory = sparseIntArray;
            return this;
        }

        public Builder taxRate(float f2) {
            this.taxRate = f2;
            return this;
        }

        public Builder techHistory(SparseIntArray sparseIntArray) {
            this.techHistory = sparseIntArray;
            return this;
        }

        public Builder technology(Technology technology) {
            this.technology = technology;
            return this;
        }

        public Builder treaties(Treaties treaties) {
            this.treaties = treaties;
            return this;
        }

        public Builder type(EmpireType empireType) {
            this.type = empireType;
            return this;
        }
    }

    /* compiled from: MyApplication */
    /* loaded from: classes.dex */
    public static class FinancialInfo {
        public int buildingsMaintenanceCosts;
        public int commandPointCost;
        public int extraFoodSale;
        public int importCosts;
        public int industrialTaxes;
        public int populationTaxes;
        public int revenueFromResources;
        public int tradegoods;
        public int treatiesExpense;
        public int treatiesRevenue;
    }

    public Empire(Builder builder) {
        HashMap hashMap = new HashMap();
        this.techListYs = hashMap;
        int i = builder.id;
        this.id = i;
        this.discoveredSystems = builder.discoveredSystems;
        this.type = builder.type;
        this.homeSystemID = builder.homeSystemID;
        this.homeWorldOrbit = builder.homeWorldOrbit;
        this.technology = builder.technology;
        this.sortBy = builder.sortBy;
        this.shipSort = builder.shipSort;
        this.credits = builder.credits;
        this.taxRate = builder.taxRate;
        this.knownEmpires = builder.knownEmpires;
        this.name = builder.name;
        this.hideAutoShowSelectAttack = builder.hideAutoShowSelectAttack;
        this.hideAIProposals = builder.hideAIProposals;
        this.selectedFleetID = builder.selectedFleetID;
        this.shipDesigns = builder.shipDesigns;
        this.migrants = builder.migrants;
        this.designVersions = builder.designVersions;
        this.relationValuesAI = builder.relationValuesAI;
        this.dispositionAI = builder.dispositionAI;
        this.shipCount = builder.shipCount;
        this.baseCommandPoints = builder.baseCommandPoints;
        this.spyNetworks = builder.spyNetworks;
        this.treaties = builder.treaties;
        this.personality = builder.personality;
        this.events = builder.events;
        this.attributes = builder.attributes;
        this.buildLists = builder.buildLists;
        this.populationHistory = builder.populationHistory;
        this.coloniesCountHistory = builder.colonyCountHistory;
        this.systemsCountHistory = builder.systemsCountHistory;
        this.commandPointUsageHistory = builder.commandPointUsageHistory;
        this.foodHistory = builder.foodHistory;
        this.productionHistory = builder.productionHistory;
        this.scienceHistory = builder.scienceHistory;
        this.techHistory = builder.techHistory;
        this.creditsPerTurnHistory = builder.creditsPerTurnHistory;
        this.bannerID = builder.bannerID;
        this.shipStyleID = builder.shipStyleID;
        this.raceID = builder.raceID;
        this.researchAI = new ResearchAI(this);
        this.manageColoniesAI = new ManageColoniesAI(this);
        this.colonyProductionAI = new ColonyProductionAI(this);
        this.militaryAI = new MilitaryAI(this);
        this.battleAI = new BattleAI();
        this.diplomaticAI = new DiplomaticAI(this);
        this.economicAI = new EconomicAI(this);
        this.attackAI = new AttackAI(i);
        this.shipDesignAI = new ShipDesignAI(this);
        TechCategory techCategory = TechCategory.ENGINEERING;
        Float valueOf = Float.valueOf(166.0f);
        hashMap.put(techCategory, valueOf);
        hashMap.put(TechCategory.PHYSICS, valueOf);
        hashMap.put(TechCategory.CHEMISTRY, valueOf);
        hashMap.put(TechCategory.ENERGY, valueOf);
    }

    private void checkForAttackOpportunities() {
        for (Fleet fleet : getFleets()) {
            if (canAttack(fleet.getSystemID())) {
                GameData.events.addSelectAttackEvent(fleet.empireID, fleet.getSystemID());
            }
        }
    }

    private void clearImportedFood() {
        for (Colony colony : getColonies()) {
            colony.clearImportedFood();
        }
    }

    private int getCapitalSystemID() {
        for (Colony colony : getColonies()) {
            if (colony.hasBuilding(BuildingID.CAPITAL)) {
                return colony.getSystemID();
            }
        }
        return -1;
    }

    private List<TradeItem> getCommonTradeDemandItems(int i) {
        ArrayList arrayList = new ArrayList();
        Empire empire = GameData.empires.get(i);
        if (this.credits >= 10) {
            arrayList.add(new TradeItem(TradeType.CREDITS, TradeItemIDs.CREDITS.getID(), GameData.activity.getString(R.string.empire_trade_item_credits), false));
        }
        arrayList.addAll(getTechTradeItems(empire));
        return arrayList;
    }

    private int getExtraFoodPerTurn() {
        int i = 0;
        for (Colony colony : getColonies()) {
            if (colony.getNetFoodPerTurn() > 0 && !isSystemBlockaded(colony.getSystemID())) {
                i += colony.getNetFoodPerTurn();
            }
        }
        return i;
    }

    private int getLevelsAhead(ShipComponent shipComponent) {
        Tech tech = this.technology.getTech(shipComponent.getRequiredTech());
        int currentTechLevel = this.technology.getCurrentTechLevel(getTechCategoryForComponent(shipComponent)) - tech.getLevel();
        if (currentTechLevel > 5) {
            return 5;
        }
        return currentTechLevel;
    }

    private List<TradeItem> getSystemTradeItems(Empire empire) {
        ArrayList arrayList = new ArrayList();
        int capitalSystemID = getCapitalSystemID();
        if (capitalSystemID != -1) {
            ArrayList<StarSystem> arrayList2 = new ArrayList();
            for (Integer num : getSystemIDs()) {
                arrayList2.add(GameData.galaxy.getStarSystem(num.intValue()));
            }
            Collections.sort(arrayList2, new Comparator() { // from class: com.birdshel.Uciana.Players.a
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    int lambda$getSystemTradeItems$0;
                    lambda$getSystemTradeItems$0 = Empire.lambda$getSystemTradeItems$0((StarSystem) obj, (StarSystem) obj2);
                    return lambda$getSystemTradeItems$0;
                }
            });
            for (StarSystem starSystem : arrayList2) {
                if (starSystem.getID() != capitalSystemID && empire.isDiscoveredSystem(starSystem.getID())) {
                    TradeType tradeType = TradeType.SYSTEM;
                    arrayList.add(new TradeItem(tradeType, TradeItemIDs.SYSTEM.getID() + starSystem.getID(), starSystem.getName(), false));
                }
            }
        }
        return arrayList;
    }

    private TechCategory getTechCategoryForComponent(ShipComponent shipComponent) {
        Tech tech = this.technology.getTech(shipComponent.getRequiredTech());
        switch (AnonymousClass1.b[shipComponent.getID().ordinal()]) {
            case 1:
            case 2:
            case 3:
                return TechCategory.CHEMISTRY;
            case 4:
            case 5:
                return TechCategory.PHYSICS;
            case 6:
            case 7:
                return TechCategory.ENGINEERING;
            case 8:
                return TechCategory.ENERGY;
            default:
                return tech.getCategory();
        }
    }

    private List<TradeItem> getTechTradeItems(Empire empire) {
        ArrayList arrayList = new ArrayList();
        for (Tech tech : this.technology.getFinishedTechsSorted()) {
            if (!empire.hasTech(tech.getID())) {
                arrayList.add(new TradeItem(TradeType.TECH, TradeItemIDs.TECH.getID() + tech.getID().ordinal(), tech.getShortName(), false));
            }
        }
        return arrayList;
    }

    private int getTotalFoodProducedPerTurn() {
        int i = 0;
        for (Colony colony : getColonies()) {
            i += colony.getFoodPerTurn();
        }
        return i;
    }

    private int getTotalProductionPerTurn() {
        int i = 0;
        for (Colony colony : getColonies()) {
            i += colony.getProductionPerTurn();
        }
        return i;
    }

    public static /* synthetic */ int lambda$getSystemTradeItems$0(StarSystem starSystem, StarSystem starSystem2) {
        return starSystem.getName().compareToIgnoreCase(starSystem2.getName());
    }

    private void scrapBuilding() {
        boolean z;
        ArrayList arrayList = new ArrayList();
        Iterator<Colony> it = getColonies().iterator();
        while (true) {
            z = true;
            if (!it.hasNext()) {
                break;
            }
            Colony next = it.next();
            if (next.hasBuildingThatCanBeScrapped(true)) {
                arrayList.add(next);
            }
        }
        if (arrayList.isEmpty()) {
            for (Colony colony : getColonies()) {
                if (colony.hasBuildingThatCanBeScrapped(false)) {
                    arrayList.add(colony);
                }
            }
            z = false;
        }
        if (arrayList.isEmpty()) {
            return;
        }
        Colony colony2 = (Colony) arrayList.get(Functions.random.nextInt(arrayList.size()));
        List<BuildingID> buildingsThatCanBeScrapped = colony2.getBuildingsThatCanBeScrapped(z);
        colony2.scrapBuilding(buildingsThatCanBeScrapped.get(Functions.random.nextInt(buildingsThatCanBeScrapped.size())));
    }

    private void scrapShip() {
        List<Fleet> fleets = getFleets();
        Fleet fleet = fleets.get(Functions.random.nextInt(fleets.size()));
        fleet.removeShip(fleet.getShips().get(Functions.random.nextInt(fleet.getShips().size())).getID());
        if (fleet.isEmpty()) {
            GameData.fleets.remove(fleet);
        }
    }

    private void updateStats() {
        this.populationHistory.put(GameData.turn, getTotalPopulation());
        this.coloniesCountHistory.put(GameData.turn, getColonies().size());
        this.systemsCountHistory.put(GameData.turn, getSystemIDs().size());
        this.commandPointUsageHistory.put(GameData.turn, GameData.fleets.getCommandPointsUsed(this.id));
        this.foodHistory.put(GameData.turn, getTotalFoodProducedPerTurn());
        this.productionHistory.put(GameData.turn, getTotalProductionPerTurn());
        this.scienceHistory.put(GameData.turn, getResearchPoints());
        this.techHistory.put(GameData.turn, this.technology.getTechValue());
        this.creditsPerTurnHistory.put(GameData.turn, getCreditsPerTurn());
    }

    public void addContact(int i) {
        if (this.knownEmpires.contains(Integer.valueOf(i)) || i >= 7) {
            return;
        }
        this.knownEmpires.add(Integer.valueOf(i));
        GameData.events.addEmpireEvent(EventType.EMPIRE_CONTACT, this.id, i, false);
    }

    public void addEvent(String str, int i) {
        this.events.put(str, Integer.valueOf(i));
    }

    public void addMigrants(int i, int i2, int i3, int i4) {
        for (Migrants migrants : this.migrants) {
            if (migrants.getSystemID() == i && migrants.getOrbit() == i2 && migrants.getTurns() == i4) {
                migrants.a(i3);
                return;
            }
        }
        this.migrants.add(new Migrants(i, i2, i3, i4));
    }

    public void addRemoveCredits(int i) {
        this.credits += i;
    }

    public void addShipDesign(Ship ship) {
        this.shipDesigns.add(ship);
    }

    public void addSpyNetwork(int i) {
        if (hasSpyNetwork(i)) {
            return;
        }
        this.spyNetworks += (int) Math.pow(2.0d, i + 1);
    }

    public void addToDiscoveredList(int i) {
        if (this.discoveredSystems.contains(Integer.valueOf(i))) {
            return;
        }
        this.discoveredSystems.add(Integer.valueOf(i));
        if (isHuman()) {
            Game.gameAchievements.checkForSystemDiscoveryAchievements(i);
        }
    }

    public boolean areAllies(int i) {
        return this.treaties.hasTreaty(i, Treaty.ALLIANCE);
    }

    public int b() {
        int i = 0;
        for (Colony colony : getColonies()) {
            i += colony.getRevenue();
        }
        return i;
    }

    public int c() {
        int i = 0;
        for (Colony colony : getColonies()) {
            i += colony.getSciencePerTurn();
        }
        return i;
    }

    public void calculateAverageDevelopmentScore() {
        List<Colony> colonies = getColonies();
        int i = 0;
        for (Colony colony : colonies) {
            i += colony.getDevelopmentScore();
        }
        this.averageDevelopmentScore = i / colonies.size();
    }

    public boolean canAttack(int i) {
        boolean z;
        if (GameData.fleets.isFleetInSystem(this.id, i)) {
            Fleet fleetInSystem = GameData.fleets.getFleetInSystem(this.id, i);
            if (fleetInSystem.hasCombatShips()) {
                Iterator<Ship> it = fleetInSystem.getCombatShips().iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (!it.next().hasRetreated()) {
                            z = true;
                            break;
                        }
                    } else {
                        z = false;
                        break;
                    }
                }
                if (z) {
                    if (GameData.fleets.getFleetsInSystem(i).size() > 1) {
                        return true;
                    }
                    StarSystem starSystem = GameData.galaxy.getStarSystem(i);
                    for (int i2 = 0; i2 < 5; i2++) {
                        if (starSystem.isOccupied(i2) && starSystem.getOccupier(i2) != this.id) {
                            return true;
                        }
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public void capitalBuilt() {
        this.hasCapital = true;
    }

    public void capitalDestroyed() {
        this.hasCapital = false;
    }

    public void checkForCapital() {
        this.hasCapital = false;
        for (Colony colony : getColonies()) {
            if (colony.hasBuilding(BuildingID.CAPITAL)) {
                this.hasCapital = true;
                return;
            }
        }
    }

    public void checkForUpgrade(TechID techID) {
        if (isAI()) {
            if (techID == TechID.DREADNOUGHT) {
                this.shipDesigns.add(this.shipDesignAI.getShipDesign(ShipType.DREADNOUGHT));
            }
            if (this.technology.getTech(techID).isShipComponent()) {
                this.shipDesigns.set(0, this.shipDesignAI.getShipDesign(ShipType.DESTROYER));
                this.shipDesigns.set(1, this.shipDesignAI.getShipDesign(ShipType.CRUISER));
                this.shipDesigns.set(2, this.shipDesignAI.getShipDesign(ShipType.BATTLESHIP));
                if (this.shipDesigns.size() > 3) {
                    this.shipDesigns.set(3, this.shipDesignAI.getShipDesign(ShipType.DREADNOUGHT));
                }
            }
        }
    }

    public void d() {
        redistributeFood();
        this.technology.addResearch(getResearchPoints());
        addRemoveCredits(getCreditsPerTurn());
        if (this.credits < -500) {
            if (getAvailableCommandPoints() < 0) {
                scrapShip();
            } else {
                scrapBuilding();
            }
        }
        Iterator<Migrants> it = this.migrants.iterator();
        while (it.hasNext()) {
            if (it.next().update(this.id)) {
                it.remove();
            }
        }
        updateStats();
    }

    public void declareWar(int i) {
        declareWar(i, true);
    }

    public void destroyed(int i) {
        ArrayList<String> arrayList = new ArrayList();
        for (Fleet fleet : GameData.fleets.getFleetsByEmpire(this.id)) {
            arrayList.add(fleet.id);
        }
        for (String str : arrayList) {
            GameData.fleets.remove(GameData.fleets.get(str));
        }
        for (Outpost outpost : GameData.outposts.getOutposts(this.id)) {
            GameData.outposts.remove(outpost);
        }
        for (int i2 = 0; i2 < 7; i2++) {
            this.treaties.setTreatiesValue(i2, 0);
            GameData.empires.get(i2).treaties.setTreatiesValue(this.id, 0);
        }
        if (this.isAlive) {
            this.isAlive = false;
            GameData.events.addEmpireDestroyedEvent(this.id, i);
        }
    }

    public void doAiTasks() {
        calculateAverageDevelopmentScore();
        this.diplomaticAI.updateRelations();
        this.diplomaticAI.checkForRelationChanges();
        FleetTaskAI.execute(this.id);
        this.militaryAI.manage();
        checkForAttackOpportunities();
        this.researchAI.manage();
        this.manageColoniesAI.manage();
        this.colonyProductionAI.manage();
        this.economicAI.manage();
    }

    public void doAutoTasks() {
        AutoFleetTaskAI.execute(this.id);
    }

    public List<Integer> getAlliancesWith() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 7; i++) {
            if (this.treaties.hasTreaty(i, Treaty.ALLIANCE)) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        return arrayList;
    }

    public List<Integer> getAtWarWith() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 7; i++) {
            if (this.treaties.hasTreaty(i, Treaty.DECLARATION_OF_WAR)) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        return arrayList;
    }

    public AttackAI getAttackAI() {
        return this.attackAI;
    }

    public float getAttributeTypeBonus(RaceAttributeType raceAttributeType) {
        float f2 = 0.0f;
        for (RaceAttribute raceAttribute : this.attributes) {
            for (Map.Entry<RaceAttributeType, Float> entry : raceAttribute.getAttributeTypes().entrySet()) {
                if (entry.getKey() == raceAttributeType) {
                    f2 += entry.getValue().floatValue();
                }
            }
        }
        return f2;
    }

    public List<Armor> getAvailableArmor() {
        ArrayList arrayList = new ArrayList();
        for (Armor armor : ShipComponents.getArmors()) {
            if (hasTech(armor.getRequiredTech())) {
                arrayList.add(armor);
            }
        }
        return arrayList;
    }

    public int getAvailableCommandPoints() {
        return getCommandPoints() - GameData.fleets.getCommandPointsUsed(this.id);
    }

    public List<Shield> getAvailableShields() {
        ArrayList arrayList = new ArrayList();
        for (Shield shield : ShipComponents.getShields()) {
            if (hasTech(shield.getRequiredTech())) {
                arrayList.add(shield);
            }
        }
        return arrayList;
    }

    public List<SpecialComponent> getAvailableSpecialComponents() {
        ArrayList arrayList = new ArrayList();
        for (SpecialComponent specialComponent : ShipComponents.getSpecialComponents()) {
            if (hasTech(specialComponent.getRequiredTech())) {
                arrayList.add(specialComponent);
            }
        }
        return arrayList;
    }

    public List<SublightEngine> getAvailableSublightEngines() {
        ArrayList arrayList = new ArrayList();
        for (SublightEngine sublightEngine : ShipComponents.getSublightEngines()) {
            if (hasTech(sublightEngine.getRequiredTech())) {
                arrayList.add(sublightEngine);
            }
        }
        return arrayList;
    }

    public List<TargetingComputer> getAvailableTargetingComputers() {
        ArrayList arrayList = new ArrayList();
        for (TargetingComputer targetingComputer : ShipComponents.getTargetingComputers()) {
            if (hasTech(targetingComputer.getRequiredTech())) {
                arrayList.add(targetingComputer);
            }
        }
        return arrayList;
    }

    public List<Weapon> getAvailableWeapons() {
        ArrayList arrayList = new ArrayList();
        for (Weapon weapon : ShipComponents.getWeapons()) {
            if (hasTech(ShipComponents.get(weapon.getID()).getRequiredTech())) {
                arrayList.add(weapon);
            }
        }
        return arrayList;
    }

    public int getAverageDevelopmentScore() {
        return this.averageDevelopmentScore;
    }

    public int getBannerID() {
        return this.bannerID;
    }

    public int getBaseCommandPoints() {
        return this.baseCommandPoints;
    }

    public BattleAI getBattleAI() {
        return this.battleAI;
    }

    public BuildLists getBuildLists() {
        return this.buildLists;
    }

    public List<Building> getBuildingList() {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<BuildingID, Building> entry : Buildings.getBuildings().entrySet()) {
            Building value = entry.getValue();
            if (value.getBuildingType() == BuildingType.SPY_NETWORK) {
                int empireIDForSpyNetwork = BuildingID.getEmpireIDForSpyNetwork(value.getID());
                if (empireIDForSpyNetwork != this.id && GameData.empires.get(empireIDForSpyNetwork).isAlive() && this.knownEmpires.contains(Integer.valueOf(empireIDForSpyNetwork)) && !hasSpyNetwork(empireIDForSpyNetwork)) {
                    arrayList.add(value);
                }
            } else if (value.getRequiredTech() == TechID.NONE) {
                arrayList.add(value);
            } else {
                if (this.technology.getTech(value.getRequiredTech()).isDone()) {
                    arrayList.add(value);
                }
            }
        }
        return arrayList;
    }

    public List<Colony> getColonies() {
        return GameData.colonies.getColonies(this.id);
    }

    public SparseIntArray getColoniesCountHistory() {
        return this.coloniesCountHistory;
    }

    public SparseIntArray getCommandPointUsageHistory() {
        return this.commandPointUsageHistory;
    }

    public int getCommandPoints() {
        int baseCommandPoints = getBaseCommandPoints();
        int attributeTypeBonus = (int) getAttributeTypeBonus(RaceAttributeType.COMMAND_POINTS_PER_COLONY);
        for (Colony colony : getColonies()) {
            baseCommandPoints = baseCommandPoints + colony.getCommandPoints() + 2 + attributeTypeBonus;
        }
        return baseCommandPoints;
    }

    public int getCommandPointsInProduction() {
        int i = 0;
        for (Colony colony : getColonies()) {
            ProductionItem currentItem = colony.getManufacturing().getCurrentItem();
            if (currentItem.getType() == ManufacturingType.SHIP) {
                i += colony.getManufacturing().getQueuedShipDesigns().get(currentItem.getID()).getShipType().getCommandPointCost();
            }
        }
        return i;
    }

    public int getComponentCostAfterMiniaturization(ShipComponent shipComponent) {
        int productionCost = shipComponent.getProductionCost();
        if (productionCost == 0) {
            return 0;
        }
        return (int) (productionCost * GameProperties.MINIATURIZATION_COST[getLevelsAhead(shipComponent)]);
    }

    public int getComponentSpaceAfterMiniaturization(ShipComponent shipComponent) {
        int spaceRequired = shipComponent.getSpaceRequired();
        char c2 = 0;
        if (spaceRequired == 0) {
            return 0;
        }
        if (shipComponent instanceof Weapon) {
            Weapon weapon = (Weapon) shipComponent;
            if (weapon.getType() == WeaponType.BEAM || weapon.getType() == WeaponType.BOMB) {
                c2 = 1;
            }
        }
        return (int) (spaceRequired * GameProperties.MINIATURIZATION_SPACE[c2][getLevelsAhead(shipComponent)]);
    }

    public int getCostOfImportedFood() {
        return Math.round(getTotalImportedFoodPerTurn() * 0.5f);
    }

    public int getCredits() {
        return this.credits;
    }

    public int getCreditsPerTurn() {
        int i = 0;
        for (Colony colony : getColonies()) {
            i += colony.getCreditsPerTurn();
        }
        int netFoodPerTurn = getNetFoodPerTurn();
        if (netFoodPerTurn > 0) {
            i += netFoodPerTurn / 2;
        }
        int costOfImportedFood = i - getCostOfImportedFood();
        int availableCommandPoints = getAvailableCommandPoints();
        if (availableCommandPoints < 0) {
            costOfImportedFood += availableCommandPoints * 50;
        }
        return costOfImportedFood + this.treaties.b();
    }

    public SparseIntArray getCreditsPerTurnHistory() {
        return this.creditsPerTurnHistory;
    }

    public Tech getCurrentTech() {
        return this.technology.getCurrentTech();
    }

    public List<TradeItem> getDemandItems(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(getCommonTradeDemandItems(i));
        return arrayList;
    }

    public int getDesignVersion(ShipType shipType) {
        return this.designVersions.get(shipType).intValue();
    }

    public DiplomaticAI getDiplomaticAI() {
        return this.diplomaticAI;
    }

    public List<Integer> getDiscoveredSystems() {
        return this.discoveredSystems;
    }

    public int getDisposition(int i) {
        int i2 = this.dispositionAI.get(i);
        if (GameData.gameSettings.isAlwaysAtWar()) {
            return 0;
        }
        return i2;
    }

    public int getEmpireSetting(String str) {
        if (this.events.containsKey(str)) {
            return this.events.get(str).intValue();
        }
        return 0;
    }

    public Map<String, Integer> getEvents() {
        return this.events;
    }

    public FinancialInfo getFinancialDetails() {
        FinancialInfo financialInfo = new FinancialInfo();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        for (Colony colony : getColonies()) {
            float incomePercentIncrease = colony.getIncomePercentIncrease();
            i += colony.getRevenueFromTradegoods(incomePercentIncrease);
            i2 += colony.getRevenueFromPopulationTaxes(incomePercentIncrease);
            i3 += colony.getRevenueFromIndustrialTaxes(incomePercentIncrease);
            i5 += colony.getExpenseFromBuildings();
            i4 += colony.getRevenueFromResources(incomePercentIncrease);
        }
        financialInfo.tradegoods = i;
        financialInfo.populationTaxes = i2;
        financialInfo.industrialTaxes = i3;
        financialInfo.revenueFromResources = i4;
        financialInfo.buildingsMaintenanceCosts = i5;
        financialInfo.treatiesRevenue = this.treaties.d();
        financialInfo.treatiesExpense = this.treaties.a();
        financialInfo.importCosts = getCostOfImportedFood();
        int netFoodPerTurn = getNetFoodPerTurn();
        if (netFoodPerTurn > 0) {
            financialInfo.extraFoodSale = netFoodPerTurn / 2;
        } else {
            financialInfo.extraFoodSale = 0;
        }
        int availableCommandPoints = getAvailableCommandPoints();
        if (availableCommandPoints < 0) {
            financialInfo.commandPointCost = availableCommandPoints * 50;
        } else {
            financialInfo.commandPointCost = 0;
        }
        return financialInfo;
    }

    public List<Fleet> getFleets() {
        return GameData.fleets.getFleetsByEmpire(this.id);
    }

    public SparseIntArray getFoodHistory() {
        return this.foodHistory;
    }

    public int getFoodPerks() {
        return (int) ((hasTech(TechID.GENETICALLY_ENGINEERED_SUPER_FOOD) ? 1.0f : 0.0f) + getAttributeTypeBonus(RaceAttributeType.FOOD_PER_FARMER));
    }

    public List<Integer> getFriendlyStarSystems() {
        List<Integer> systemIDs = getSystemIDs();
        for (Integer num : getKnownEmpires()) {
            int intValue = num.intValue();
            if (intValue < 7) {
                Empire empire = GameData.empires.get(intValue);
                if (empire.areAllies(this.id)) {
                    systemIDs.addAll(empire.getSystemIDs());
                }
            }
        }
        return systemIDs;
    }

    public int getGroundCombatStrength() {
        int troopStrength = this.technology.getTroopStrength() + 10;
        Technology technology = this.technology;
        TechID techID = TechID.PERSONAL_SHIELD;
        if (technology.hasTech(techID)) {
            troopStrength += this.technology.getTech(techID).getValue();
        }
        Technology technology2 = this.technology;
        TechID techID2 = TechID.POWERED_ARMOR;
        if (technology2.hasTech(techID2)) {
            troopStrength += this.technology.getTech(techID2).getValue();
        }
        Technology technology3 = this.technology;
        TechID techID3 = TechID.NANO_SCALE_ARMOR;
        if (technology3.hasTech(techID3)) {
            troopStrength += this.technology.getTech(techID3).getValue();
        }
        return (int) (troopStrength + getAttributeTypeBonus(RaceAttributeType.GROUND_COMBAT));
    }

    public float getHappiness() {
        return (!this.hasCapital ? 0.75f : 1.0f) + getAttributeTypeBonus(RaceAttributeType.HAPPINESS);
    }

    public int getHomeSystem() {
        return this.homeSystemID;
    }

    public int getHomeWorldOrbit() {
        return this.homeWorldOrbit;
    }

    public List<Integer> getKnownEmpires() {
        return this.knownEmpires;
    }

    public void getMapFromEmpire(int i) {
        List<Integer> discoveredSystems = GameData.empires.get(i).getDiscoveredSystems();
        for (Integer num : discoveredSystems) {
            addToDiscoveredList(num.intValue());
        }
        for (StarSystem starSystem : GameData.galaxy.getStarSystems()) {
            if (discoveredSystems.contains(Integer.valueOf(starSystem.getID()))) {
                for (SystemObject systemObject : starSystem.getSystemObjects()) {
                    if (systemObject.hasBeenExploredByEmpire(i) && !systemObject.hasBeenExploredByEmpire(this.id)) {
                        systemObject.beenExplored(this.id);
                    }
                }
            }
        }
    }

    public List<Migrants> getMigrants() {
        return this.migrants;
    }

    public List<Migrants> getMigrantsForPlanet(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        for (Migrants migrants : this.migrants) {
            if (migrants.getSystemID() == i && migrants.getOrbit() == i2) {
                arrayList.add(migrants);
            }
        }
        return arrayList;
    }

    public MilitaryAI getMilitaryAI() {
        return this.militaryAI;
    }

    public String getName() {
        return this.name;
    }

    public int getNetFoodNonBlockadedPerTurn() {
        int i = 0;
        for (Colony colony : getColonies()) {
            if (!isSystemBlockaded(colony.getSystemID())) {
                i += colony.getNetFoodPerTurn();
            }
        }
        return i;
    }

    public int getNetFoodPerTurn() {
        int netFoodPerTurn;
        int i = 0;
        for (Colony colony : getColonies()) {
            if (!isSystemBlockaded(colony.getSystemID())) {
                netFoodPerTurn = colony.getNetFoodPerTurn();
            } else if (colony.getNetFoodPerTurn() < 0) {
                netFoodPerTurn = colony.getNetFoodPerTurn();
            }
            i += netFoodPerTurn;
        }
        return i;
    }

    public String getNextShipID() {
        this.shipCount++;
        return GameData.empires.a(this.id) + this.shipCount;
    }

    public List<Outpost> getOutposts() {
        return GameData.outposts.getOutposts(this.id);
    }

    public int getPercentOfGalaxyExplored() {
        int i = 0;
        int i2 = 0;
        for (StarSystem starSystem : GameData.galaxy.getStarSystems()) {
            for (SystemObject systemObject : starSystem.getSystemObjects()) {
                if (systemObject.isPlanet() && !((Planet) systemObject).getClimate().isSpecial()) {
                    i2++;
                    if (systemObject.hasBeenExploredByEmpire(this.id)) {
                        i++;
                    }
                }
            }
        }
        return (i * 100) / i2;
    }

    public Personality getPersonality() {
        return this.personality;
    }

    public SparseIntArray getPopulationHistory() {
        return this.populationHistory;
    }

    public int getPopulationInTransit() {
        int i = 0;
        for (Migrants migrants : this.migrants) {
            i += migrants.getPopulationCount();
        }
        return i;
    }

    public SparseIntArray getProductionHistory() {
        return this.productionHistory;
    }

    public int getProductionPerks() {
        return (int) ((hasTech(TechID.ADVANCED_CHEMICAL_MINING) ? 1.0f : 0.0f) + getAttributeTypeBonus(RaceAttributeType.PRODUCTION_PER_WORKER));
    }

    public List<RaceAttribute> getRaceAttributes() {
        return this.attributes;
    }

    public int getRaceID() {
        return this.raceID;
    }

    public int getRelationValue(int i) {
        return this.relationValuesAI.get(i);
    }

    public ResearchAI getResearchAI() {
        return this.researchAI;
    }

    public int getResearchPoints() {
        return c() + this.treaties.c();
    }

    public Map<ResourceID, Integer> getResources() {
        HashMap hashMap = new HashMap();
        for (Colony colony : getColonies()) {
            for (ResourceID resourceID : colony.getResources()) {
                if (hashMap.containsKey(resourceID)) {
                    hashMap.put(resourceID, Integer.valueOf(((Integer) hashMap.get(resourceID)).intValue() + 1));
                } else {
                    hashMap.put(resourceID, 1);
                }
            }
        }
        return hashMap;
    }

    public SparseIntArray getScienceHistory() {
        return this.scienceHistory;
    }

    public int getSciencePerks() {
        return (int) ((hasTech(TechID.HEIGHTENED_INTELLIGENCE) ? 1.0f : 0.0f) + getAttributeTypeBonus(RaceAttributeType.SCIENCE_PER_SCIENTIST));
    }

    public String getSelectedFleetID() {
        if (!GameData.fleets.isStillAFleet(this.selectedFleetID)) {
            this.selectedFleetID = "none";
        }
        return this.selectedFleetID;
    }

    public List<Ship> getShipBuildList() {
        ArrayList arrayList = new ArrayList();
        Ship.Builder builder = new Ship.Builder();
        arrayList.add(builder.id("Scout-" + UUID.randomUUID().toString()).shipType(ShipType.SCOUT).empireID(this.id).buildNonCombatShip());
        Ship.Builder builder2 = new Ship.Builder();
        arrayList.add(builder2.id("Outpost-" + UUID.randomUUID().toString()).shipType(ShipType.CONSTRUCTION).empireID(this.id).buildNonCombatShip());
        Ship.Builder builder3 = new Ship.Builder();
        arrayList.add(builder3.id("Colony-" + UUID.randomUUID().toString()).shipType(ShipType.COLONY).empireID(this.id).buildNonCombatShip());
        Ship.Builder builder4 = new Ship.Builder();
        arrayList.add(builder4.id("Transport-" + UUID.randomUUID().toString()).shipType(ShipType.TRANSPORT).empireID(this.id).buildNonCombatShip());
        arrayList.addAll(this.shipDesigns);
        return arrayList;
    }

    public int getShipCount() {
        return this.shipCount;
    }

    public ShipDesignAI getShipDesignAI() {
        return this.shipDesignAI;
    }

    public List<Ship> getShipDesigns() {
        return this.shipDesigns;
    }

    public ShipSort getShipSort() {
        return this.shipSort;
    }

    public int getShipStyleID() {
        return this.shipStyleID;
    }

    public SortType getSortBy() {
        return this.sortBy;
    }

    public int getSpyNetworkValue() {
        return this.spyNetworks;
    }

    public SparseArray<SparseIntArray> getStats() {
        SparseArray<SparseIntArray> sparseArray = new SparseArray<>();
        sparseArray.put(0, this.populationHistory);
        sparseArray.put(1, this.coloniesCountHistory);
        sparseArray.put(2, this.systemsCountHistory);
        sparseArray.put(3, this.commandPointUsageHistory);
        sparseArray.put(4, this.foodHistory);
        sparseArray.put(5, this.productionHistory);
        sparseArray.put(6, this.scienceHistory);
        sparseArray.put(7, this.techHistory);
        sparseArray.put(8, this.creditsPerTurnHistory);
        return sparseArray;
    }

    public List<Integer> getSystemIDs() {
        HashSet hashSet = new HashSet();
        for (Colony colony : getColonies()) {
            hashSet.add(Integer.valueOf(colony.getPlanet().getSystemID()));
        }
        for (Outpost outpost : getOutposts()) {
            hashSet.add(Integer.valueOf(outpost.getSystemID()));
        }
        return new ArrayList(hashSet);
    }

    public SparseIntArray getSystemsCountHistory() {
        return this.systemsCountHistory;
    }

    public float getTaxRate() {
        return this.taxRate;
    }

    public Technology getTech() {
        return this.technology;
    }

    public SparseIntArray getTechHistory() {
        return this.techHistory;
    }

    public float getTechListY(TechCategory techCategory) {
        Float f2 = this.techListYs.get(techCategory);
        if (f2 != null) {
            return f2.floatValue();
        }
        return 0.0f;
    }

    public String getTechTurnsLeftString() {
        return getCurrentTech().getTechTurnsLeftString(getResearchPoints());
    }

    public int getTotalImportedFoodPerTurn() {
        int i = 0;
        for (Colony colony : getColonies()) {
            i += colony.getImportedFood();
        }
        return i;
    }

    public int getTotalPopulation() {
        int i = 0;
        for (Colony colony : getColonies()) {
            i += colony.getPopulation();
        }
        return i + getPopulationInTransit();
    }

    public List<TradeItem> getTradeItems(int i) {
        ArrayList arrayList = new ArrayList();
        if (isAtWar(i)) {
            TradeType tradeType = TradeType.TREATY;
            String id = TradeItemIDs.PEACE_TREATY.getID();
            Treaty treaty = Treaty.PEACE_TREATY;
            arrayList.add(new TradeItem(tradeType, id, treaty.getName(), treaty.requiresBoth()));
        } else {
            Treaties treaties = this.treaties;
            Treaty treaty2 = Treaty.NON_AGGRESSION_PACT;
            if (!treaties.hasTreaty(i, treaty2)) {
                arrayList.add(new TradeItem(TradeType.TREATY, TradeItemIDs.NON_AGGRESSION_PACT.getID(), treaty2.getName(), treaty2.requiresBoth()));
            } else if (this.treaties.hasTreaty(i, treaty2) && !areAllies(i)) {
                TradeType tradeType2 = TradeType.TREATY;
                String id2 = TradeItemIDs.ALLIANCE.getID();
                Treaty treaty3 = Treaty.ALLIANCE;
                arrayList.add(new TradeItem(tradeType2, id2, treaty3.getName(), treaty3.requiresBoth()));
            }
        }
        if (!isAtWar(i)) {
            Treaties treaties2 = this.treaties;
            Treaty treaty4 = Treaty.TRADE;
            if (!treaties2.hasTreaty(i, treaty4)) {
                arrayList.add(new TradeItem(TradeType.TREATY, TradeItemIDs.TRADE.getID(), treaty4.getName(), treaty4.requiresBoth()));
            }
            Treaties treaties3 = this.treaties;
            Treaty treaty5 = Treaty.RESEARCH;
            if (!treaties3.hasTreaty(i, treaty5)) {
                arrayList.add(new TradeItem(TradeType.TREATY, TradeItemIDs.RESEARCH.getID(), treaty5.getName(), treaty5.requiresBoth()));
            }
        }
        arrayList.add(new TradeItem(TradeType.MAPS, TradeItemIDs.SHARE_MAPS.getID(), GameData.activity.getString(R.string.empire_trade_item_exploration_data), false));
        arrayList.addAll(getCommonTradeDemandItems(i));
        return arrayList;
    }

    public Treaties getTreaties() {
        return this.treaties;
    }

    public int[] getTreatiesValues() {
        return this.treaties.e();
    }

    public EmpireType getType() {
        return this.type;
    }

    public boolean hasAllianceWith(int i) {
        return this.treaties.hasTreaty(i, Treaty.ALLIANCE);
    }

    public boolean hasAttributeType(RaceAttributeType raceAttributeType) {
        for (RaceAttribute raceAttribute : this.attributes) {
            if (raceAttribute.getAttributeTypes().containsKey(raceAttributeType)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCapital() {
        return this.hasCapital;
    }

    public boolean hasItsFallenMessage() {
        List<Event> events = GameData.events.getEvents(this.id, EventType.EMPIRE_DESTROYED);
        if (events.isEmpty()) {
            return false;
        }
        for (Event event : events) {
            if (((Integer) event.getEventData(EventDataFields.EMPIRE_ID)).intValue() == this.id) {
                return true;
            }
        }
        return false;
    }

    public boolean hasSpyNetwork(int i) {
        int pow = (int) Math.pow(2.0d, i + 1);
        return (this.spyNetworks & pow) == pow;
    }

    public boolean hasTech(TechID techID) {
        if (techID == TechID.NONE) {
            return true;
        }
        return this.technology.hasTech(techID);
    }

    public void increaseBaseCommandPoints(int i) {
        this.baseCommandPoints += i;
    }

    public boolean isAI() {
        return this.type == EmpireType.AI;
    }

    public boolean isAIProposalsHidden(int i) {
        return this.hideAIProposals.get(i, false);
    }

    public boolean isAlive() {
        if (this.isAlive) {
            EmpireType empireType = this.type;
            if (empireType == EmpireType.AI) {
                if (getColonies().isEmpty()) {
                    destroyed(0);
                    return false;
                }
            } else if (empireType == EmpireType.HUMAN) {
                if (getColonies().isEmpty()) {
                    destroyed(0);
                    return false;
                } else if (getTotalPopulation() == 0) {
                    destroyed(1);
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean isAtWar() {
        for (int i = 0; i < 7; i++) {
            if (this.treaties.hasTreaty(i, Treaty.DECLARATION_OF_WAR)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAutoSelectAttackHidden(int i) {
        return this.hideAutoShowSelectAttack.get(i);
    }

    public boolean isDiscoveredSystem(int i) {
        return this.discoveredSystems.contains(Integer.valueOf(i));
    }

    public boolean isEmpireKnown(int i) {
        return this.knownEmpires.contains(Integer.valueOf(i));
    }

    public boolean isHuman() {
        return this.type == EmpireType.HUMAN;
    }

    public boolean isSystemBlockaded(int i) {
        if (getSystemIDs().contains(Integer.valueOf(i))) {
            for (Fleet fleet : GameData.fleets.getFleetsInSystem(i)) {
                int i2 = fleet.empireID;
                if (i2 != this.id) {
                    if (GameProperties.isNonNormalEmpire(i2)) {
                        return true;
                    }
                    if (isAtWar(fleet.empireID) && fleet.hasCombatShips()) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    public boolean isType(EmpireType empireType) {
        return this.type == empireType;
    }

    public void manageProduction(Colony colony) {
        this.colonyProductionAI.manageProduction(colony);
    }

    public void redistributeFood() {
        clearImportedFood();
        int extraFoodPerTurn = getExtraFoodPerTurn();
        if (extraFoodPerTurn < 0) {
            return;
        }
        ArrayList<ColonyFoodNeeded> arrayList = new ArrayList();
        int i = 0;
        for (Colony colony : getColonies()) {
            if (!isSystemBlockaded(colony.getSystemID()) && colony.getTotalFoodPerTurn() < 0) {
                arrayList.add(new ColonyFoodNeeded(colony));
                i += Math.abs(colony.getTotalFoodPerTurn());
            }
        }
        if (extraFoodPerTurn >= i) {
            for (ColonyFoodNeeded colonyFoodNeeded : arrayList) {
                Colony colony2 = colonyFoodNeeded.colony;
                colony2.importFood(Math.abs(colony2.getTotalFoodPerTurn()));
            }
            return;
        }
        for (int i2 = 0; extraFoodPerTurn > 0 && i2 < 25; i2++) {
            ArrayList arrayList2 = new ArrayList();
            int size = extraFoodPerTurn > arrayList.size() ? extraFoodPerTurn / arrayList.size() : 1;
            for (ColonyFoodNeeded colonyFoodNeeded2 : arrayList) {
                int i3 = colonyFoodNeeded2.foodNeeded;
                if (i3 <= size) {
                    colonyFoodNeeded2.colony.importFood(i3);
                    extraFoodPerTurn -= colonyFoodNeeded2.foodNeeded;
                    colonyFoodNeeded2.foodNeeded = 0;
                    arrayList2.add(colonyFoodNeeded2);
                    continue;
                } else {
                    colonyFoodNeeded2.colony.importFood(size);
                    extraFoodPerTurn -= size;
                    colonyFoodNeeded2.foodNeeded -= size;
                    continue;
                }
                if (extraFoodPerTurn <= 0) {
                    return;
                }
            }
            arrayList.removeAll(arrayList2);
            if (arrayList.isEmpty()) {
                return;
            }
        }
    }

    public void removeShipDesign(int i) {
        this.shipDesigns.remove(i);
    }

    public void replaceShipDesign(int i, Ship ship) {
        this.shipDesigns.set(i, ship);
    }

    public void setDefaultShipDesigns() {
        this.shipDesigns.add(this.shipDesignAI.getShipDesign(ShipType.DESTROYER, 0));
        this.shipDesigns.add(this.shipDesignAI.getShipDesign(ShipType.CRUISER, 0));
        this.shipDesigns.add(this.shipDesignAI.getShipDesign(ShipType.BATTLESHIP, 0));
    }

    public void setEmpireSetting(String str, int i) {
        this.events.put(str, Integer.valueOf(i));
    }

    public void setHideAIProposals(int i, boolean z) {
        this.hideAIProposals.put(i, z);
    }

    public void setHideAutoSelectAttack(int i, boolean z) {
        this.hideAutoShowSelectAttack.put(i, z);
    }

    public void setIsAlive() {
        int i = AnonymousClass1.f1405a[this.type.ordinal()];
        boolean z = false;
        if (i == 1) {
            this.isAlive = false;
        } else if (i == 2) {
            this.isAlive = !getColonies().isEmpty();
        } else if (i != 3) {
        } else {
            if (!getColonies().isEmpty() && getTotalPopulation() != 0) {
                z = true;
            }
            this.isAlive = z;
        }
    }

    public void setSelectedFleetID(String str) {
        this.selectedFleetID = str;
    }

    public void setShipSort(ShipSort shipSort) {
        this.shipSort = shipSort;
    }

    public void setSortBy(SortType sortType) {
        this.sortBy = sortType;
    }

    public void setTaxRate(float f2) {
        this.taxRate = f2;
    }

    public void setTech(TechID techID) {
        this.technology.setCurrentTech(techID);
    }

    public void setTechListY(TechCategory techCategory, float f2) {
        this.techListYs.put(techCategory, Float.valueOf(f2));
    }

    public void updateRelationValue(int i, RelationEvent relationEvent) {
        updateRelationValue(i, relationEvent.getValue());
    }

    public boolean willHaveCreditTrouble() {
        return this.credits + getCreditsPerTurn() < -500;
    }

    public void declareWar(int i, boolean z) {
        Empire empire = GameData.empires.get(i);
        this.treaties.declareWar(i);
        empire.treaties.declareWar(this.id);
        setHideAutoSelectAttack(i, false);
        empire.setHideAutoSelectAttack(this.id, false);
        GameData.events.removePeacefulDiplomaticEvents(i, this.id);
        if (z) {
            GameData.events.addDiplomaticEvent(DiplomaticType.DECLARED_WAR.ordinal(), i, this.id);
        }
        if (empire.isAI() && z) {
            empire.updateRelationValue(this.id, RelationEvent.WAR);
            GameData.events.addDiplomaticEvent(DiplomaticType.RESPONSE_TO_WAR.ordinal(), this.id, i);
        }
    }

    public boolean isAtWar(int i) {
        if (GameProperties.isNonNormalEmpire(i)) {
            return true;
        }
        return this.treaties.hasTreaty(i, Treaty.DECLARATION_OF_WAR);
    }

    public void updateRelationValue(int i, int i2) {
        int i3 = this.relationValuesAI.get(i) + i2;
        if (i3 < 0) {
            i3 = 0;
        } else if (i3 > 100) {
            i3 = 100;
        }
        if (isAtWar(i) && i3 > 50) {
            i3 = 50;
        }
        this.relationValuesAI.put(i, i3);
    }
}
