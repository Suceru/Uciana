package com.birdshel.Uciana.Planets;

import com.birdshel.Uciana.Colonies.Buildings.Building;
import com.birdshel.Uciana.Colonies.Buildings.BuildingEffectType;
import com.birdshel.Uciana.Colonies.Buildings.BuildingID;
import com.birdshel.Uciana.Colonies.Buildings.Buildings;
import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Players.EmpireType;
import com.birdshel.Uciana.Players.RaceAttributeType;
import com.birdshel.Uciana.Scenes.BuildingsScene;
import com.birdshel.Uciana.Scenes.ColoniesScene;
import com.birdshel.Uciana.Scenes.ExtendedScene;
import com.birdshel.Uciana.Scenes.FleetsScene;
import com.birdshel.Uciana.Scenes.GalaxyScene;
import com.birdshel.Uciana.Scenes.PlanetScene;
import com.birdshel.Uciana.Scenes.SystemScene;
import com.birdshel.Uciana.StarSystems.StarType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Planet extends SystemObject {
    private final int cityLightIndex;
    private Climate climate;
    private final ExplorationFind explorationFind;
    private final Gravity gravity;
    private final boolean hasMoon;
    private final boolean hasRing;
    private boolean isTerraformed;
    private MineralRichness mineralRichness;
    private final Moon moon;
    private final int planetValue;
    private final int ringImageIndex;
    private final PlanetSize size;
    private Climate terraformedClimate;

    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Planets.Planet$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a */
        static final /* synthetic */ int[] f1393a;
        static final /* synthetic */ int[] b;

        /* renamed from: c */
        static final /* synthetic */ int[] f1394c;

        static {
            int[] iArr = new int[MineralRichness.values().length];
            f1394c = iArr;
            try {
                iArr[MineralRichness.VERY_POOR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1394c[MineralRichness.POOR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1394c[MineralRichness.ABUNDANT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1394c[MineralRichness.RICH.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1394c[MineralRichness.VERY_RICH.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[PlanetSize.values().length];
            b = iArr2;
            try {
                iArr2[PlanetSize.SMALL.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                b[PlanetSize.MED.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                b[PlanetSize.LARGE.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                b[PlanetSize.EXTRA_LARGE.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            int[] iArr3 = new int[Climate.values().length];
            f1393a = iArr3;
            try {
                iArr3[Climate.GAS_GIANT.ordinal()] = 1;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f1393a[Climate.BROKEN.ordinal()] = 2;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f1393a[Climate.RING.ordinal()] = 3;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f1393a[Climate.POLLUTED.ordinal()] = 4;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f1393a[Climate.METALLIC.ordinal()] = 5;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f1393a[Climate.PLAGUE.ordinal()] = 6;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f1393a[Climate.SUPER_ACIDIC.ordinal()] = 7;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f1393a[Climate.BOG.ordinal()] = 8;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f1393a[Climate.METHANE.ordinal()] = 9;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                f1393a[Climate.BOREAL.ordinal()] = 10;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                f1393a[Climate.TROPICAL_OCEAN.ordinal()] = 11;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                f1393a[Climate.SENTIENT.ordinal()] = 12;
            } catch (NoSuchFieldError unused21) {
            }
        }
    }

    /* compiled from: MyApplication */
    /* loaded from: classes.dex */
    public static class Builder extends SystemObject.Builder {
        private int cityLightIndex;
        private Climate climate;
        private ExplorationFind explorationFind;
        private Gravity gravity;
        private boolean hasMoon;
        private boolean hasRing;
        private boolean isTerraformed;
        private MineralRichness mineralRichness;
        private Moon moon;
        private int planetValue;
        private int ringImageIndex;
        private PlanetSize size;
        private Climate terraformedClimate;

        public Builder() {
            this.b = SystemObjectType.PLANET;
            this.cityLightIndex = Functions.random.nextInt(3);
        }

        private void calculatePlanetValue() {
            this.planetValue = 0;
            int calculationValue = 0 + this.size.getCalculationValue();
            this.planetValue = calculationValue;
            int calculationValue2 = calculationValue + this.climate.getCalculationValue();
            this.planetValue = calculationValue2;
            int calculationValue3 = calculationValue2 + this.mineralRichness.getCalculationValue();
            this.planetValue = calculationValue3;
            this.planetValue = calculationValue3 + this.gravity.getCalculationValue();
        }

        private Gravity getLargePlanetGravity() {
            int i = AnonymousClass1.f1394c[this.mineralRichness.ordinal()];
            if (i == 1 || i == 2 || i == 3) {
                return Gravity.NORMAL;
            }
            if (i != 4 && i != 5) {
                throw new AssertionError("Unknown MineralRichness when getting large planet gravity");
            }
            if (Functions.percent(25)) {
                return Gravity.HIGH;
            }
            return Gravity.NORMAL;
        }

        private Gravity getMedPlanetGravity() {
            int i = AnonymousClass1.f1394c[this.mineralRichness.ordinal()];
            if (i == 1 || i == 2) {
                if (Functions.percent(25)) {
                    return Gravity.LOW;
                }
                return Gravity.NORMAL;
            } else if (i != 3 && i != 4 && i != 5) {
                throw new AssertionError("Unknown MineralRichness when getting med planet gravity");
            } else {
                return Gravity.NORMAL;
            }
        }

        private Gravity getPlanetGravity() {
            int i = AnonymousClass1.b[this.size.ordinal()];
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            return Gravity.NORMAL;
                        }
                        return getVeryLargePlanetGravity();
                    }
                    return getLargePlanetGravity();
                }
                return getMedPlanetGravity();
            }
            return getSmallPlanetGravity();
        }

        private PlanetSize getPlanetSize() {
            HashMap hashMap = new HashMap();
            hashMap.put(PlanetSize.SMALL, 20);
            hashMap.put(PlanetSize.MED, 35);
            hashMap.put(PlanetSize.LARGE, 30);
            hashMap.put(PlanetSize.EXTRA_LARGE, 15);
            return (PlanetSize) Functions.getItemByPercent(hashMap);
        }

        private Gravity getSmallPlanetGravity() {
            int i = AnonymousClass1.f1394c[this.mineralRichness.ordinal()];
            if (i == 1) {
                if (Functions.percent(50)) {
                    return Gravity.LOW;
                }
                return Gravity.NORMAL;
            } else if (i == 2 || i == 3) {
                if (Functions.percent(25)) {
                    return Gravity.LOW;
                }
                return Gravity.NORMAL;
            } else if (i != 4 && i != 5) {
                throw new AssertionError("Unknown Gravity when getting small planet gravity");
            } else {
                return Gravity.NORMAL;
            }
        }

        private Gravity getVeryLargePlanetGravity() {
            int i = AnonymousClass1.f1394c[this.mineralRichness.ordinal()];
            if (i == 1 || i == 2 || i == 3 || i == 4) {
                if (Functions.percent(25)) {
                    return Gravity.HIGH;
                }
                return Gravity.NORMAL;
            } else if (i == 5) {
                if (Functions.percent(50)) {
                    return Gravity.HIGH;
                }
                return Gravity.NORMAL;
            } else {
                throw new AssertionError("Unknown MineralRichness when getting very large planet gravity");
            }
        }

        public Planet buildHomeworld(EmpireType empireType) {
            this.isTerraformed = false;
            this.f1404f = Functions.random.nextInt(3);
            this.ringImageIndex = Functions.random.nextInt(2);
            this.cityLightIndex = Functions.random.nextInt(3);
            this.size = PlanetSize.LARGE;
            this.mineralRichness = MineralRichness.ABUNDANT;
            this.gravity = Gravity.NORMAL;
            this.explorationFind = ExplorationFind.FRIENDLY_RACE;
            if (empireType == EmpireType.NONE) {
                this.g = 0;
                this.f1403e = Resources.getResourcesForPlanet(this.climate, this.terraformedClimate);
            } else {
                this.g = SystemObject.EXPLORED_BY_ALL;
            }
            calculatePlanetValue();
            return new Planet(this);
        }

        public Planet buildNewWorld(StarType starType) {
            this.isTerraformed = false;
            this.f1404f = Functions.random.nextInt(3);
            this.ringImageIndex = Functions.random.nextInt(2);
            this.cityLightIndex = Functions.random.nextInt(3);
            this.moon = new Moon();
            Climate climate = starType.getClimate();
            this.climate = climate;
            this.terraformedClimate = climate.getTerraformedClimate();
            this.size = getPlanetSize();
            this.mineralRichness = starType.getMineralRichness();
            this.gravity = getPlanetGravity();
            this.hasRing = this.size.hasRing();
            this.hasMoon = this.size.hasMoon();
            List<ResourceID> resourcesForPlanet = Resources.getResourcesForPlanet(this.climate, this.terraformedClimate);
            this.f1403e = resourcesForPlanet;
            this.explorationFind = ExplorationFind.getExplorationFind(this.climate, resourcesForPlanet);
            this.g = 0;
            calculatePlanetValue();
            return new Planet(this);
        }

        public Planet buildRandomPlanet() {
            this.f1401c = -1;
            this.f1402d = -1;
            this.isTerraformed = true;
            this.f1404f = Functions.random.nextInt(3);
            this.ringImageIndex = Functions.random.nextInt(2);
            this.cityLightIndex = Functions.random.nextInt(3);
            this.moon = new Moon();
            Climate climate = Climate.values()[Functions.random.nextInt(Climate.values().length)];
            this.climate = climate;
            this.terraformedClimate = climate;
            this.size = PlanetSize.values()[Functions.random.nextInt(PlanetSize.values().length - 2) + 1];
            int i = AnonymousClass1.f1393a[this.climate.ordinal()];
            if (i != 1) {
                if (i == 2) {
                    this.size = PlanetSize.SMALL;
                } else if (i == 3) {
                    this.size = PlanetSize.SMALL;
                } else if (i == 4) {
                    this.size = PlanetSize.LARGE;
                }
            } else if (Functions.percent(50)) {
                this.size = PlanetSize.EXTRA_LARGE;
            } else {
                this.size = PlanetSize.HUGE;
            }
            this.mineralRichness = MineralRichness.ABUNDANT;
            this.gravity = Gravity.NORMAL;
            this.hasMoon = Functions.percent(50);
            this.hasRing = false;
            if (!this.climate.isSpecial()) {
                this.hasRing = Functions.percent(50);
            }
            this.g = 0;
            return new Planet(this);
        }

        public Builder cityLightIndex(int i) {
            this.cityLightIndex = i;
            return this;
        }

        public Builder climate(Climate climate) {
            this.climate = climate;
            return this;
        }

        public Builder explorationFind(ExplorationFind explorationFind) {
            this.explorationFind = explorationFind;
            return this;
        }

        public Builder gravity(Gravity gravity) {
            this.gravity = gravity;
            return this;
        }

        public Builder hasMoon(boolean z) {
            this.hasMoon = z;
            return this;
        }

        public Builder hasRing(boolean z) {
            this.hasRing = z;
            return this;
        }

        public Builder isTerraformed(boolean z) {
            this.isTerraformed = z;
            return this;
        }

        public Builder mineralRichness(MineralRichness mineralRichness) {
            this.mineralRichness = mineralRichness;
            return this;
        }

        public Builder moon(Moon moon) {
            this.moon = moon;
            return this;
        }

        @Override // com.birdshel.Uciana.Planets.SystemObject.Builder
        public /* bridge */ /* synthetic */ SystemObject.Builder name(String str) {
            return super.name(str);
        }

        @Override // com.birdshel.Uciana.Planets.SystemObject.Builder
        public /* bridge */ /* synthetic */ SystemObject.Builder resources(List list) {
            return resources((List<ResourceID>) list);
        }

        public Builder ringImageIndex(int i) {
            this.ringImageIndex = i;
            return this;
        }

        public Builder size(PlanetSize planetSize) {
            this.size = planetSize;
            return this;
        }

        public Builder terraformedClimate(Climate climate) {
            this.terraformedClimate = climate;
            return this;
        }

        @Override // com.birdshel.Uciana.Planets.SystemObject.Builder
        public Planet build() {
            calculatePlanetValue();
            return new Planet(this);
        }

        @Override // com.birdshel.Uciana.Planets.SystemObject.Builder
        public Builder exploredValue(int i) {
            super.exploredValue(i);
            return this;
        }

        @Override // com.birdshel.Uciana.Planets.SystemObject.Builder
        public Builder imageIndex(int i) {
            super.imageIndex(i);
            return this;
        }

        @Override // com.birdshel.Uciana.Planets.SystemObject.Builder
        public Builder orbit(int i) {
            super.orbit(i);
            return this;
        }

        @Override // com.birdshel.Uciana.Planets.SystemObject.Builder
        public Builder resources(List<ResourceID> list) {
            super.resources(list);
            return this;
        }

        @Override // com.birdshel.Uciana.Planets.SystemObject.Builder
        public Builder systemID(int i) {
            super.systemID(i);
            return this;
        }
    }

    public Planet(Builder builder) {
        super(builder);
        this.ringImageIndex = builder.ringImageIndex;
        this.cityLightIndex = builder.cityLightIndex;
        this.moon = builder.moon;
        this.climate = builder.climate;
        this.terraformedClimate = builder.terraformedClimate;
        this.size = builder.size;
        this.mineralRichness = builder.mineralRichness;
        this.gravity = builder.gravity;
        this.isTerraformed = builder.isTerraformed;
        this.hasRing = builder.hasRing;
        this.hasMoon = builder.hasMoon;
        this.f1397c = builder.f1403e;
        this.f1399e = builder.g;
        this.planetValue = builder.planetValue;
        this.explorationFind = builder.explorationFind;
    }

    private float checkForPopulationGrowthModifiers(float f2) {
        float attributeTypeBonus;
        if (hasColony()) {
            attributeTypeBonus = GameData.empires.get(getColony().getEmpireID()).getAttributeTypeBonus(RaceAttributeType.BIRTHRATE_INCREASE);
        } else if (GameData.getCurrentPlayer() == -1) {
            return f2;
        } else {
            attributeTypeBonus = GameData.empires.get(GameData.getCurrentPlayer()).getAttributeTypeBonus(RaceAttributeType.BIRTHRATE_INCREASE);
        }
        return f2 + attributeTypeBonus;
    }

    private int checkModifiersOnPopulation(int i) {
        if (!hasColony()) {
            return GameData.getCurrentPlayer() != -1 ? (int) (i * (GameData.empires.get(GameData.getCurrentPlayer()).getAttributeTypeBonus(RaceAttributeType.MAX_POPULATION_INCREASE) + 1.0f)) : i;
        }
        int attributeTypeBonus = (int) (i * (GameData.empires.get(getColony().getEmpireID()).getAttributeTypeBonus(RaceAttributeType.MAX_POPULATION_INCREASE) + 1.0f));
        for (String str : getColony().getBuildings()) {
            Building building = Buildings.getBuilding(str);
            Map<BuildingEffectType, Float> effects = building.getEffects();
            BuildingEffectType buildingEffectType = BuildingEffectType.POPULATION;
            if (effects.containsKey(buildingEffectType)) {
                attributeTypeBonus = (int) (attributeTypeBonus + building.getEffects().get(buildingEffectType).floatValue());
            }
        }
        return attributeTypeBonus;
    }

    public void decreaseMineralRichness() {
        MineralRichness mineralRichness = this.mineralRichness;
        if (mineralRichness != MineralRichness.VERY_POOR) {
            this.mineralRichness = mineralRichness.getPrevious();
        }
    }

    public int getCityLightIndex() {
        return this.cityLightIndex;
    }

    public Climate getClimate() {
        if (this.isTerraformed) {
            return this.terraformedClimate;
        }
        return this.climate;
    }

    public float getClimateCostModifier() {
        return getClimate().getMaintenanceCostModifier();
    }

    public float getClimateCostModifierPreTerraforming() {
        return getPreTerraformedClimate().getMaintenanceCostModifier();
    }

    public int getDefenceBonus() {
        return this.climate.getDefenceBonus();
    }

    public ExplorationFind getExplorationFind() {
        if (this.f1399e != 0) {
            return ExplorationFind.NOTHING;
        }
        return this.explorationFind;
    }

    public ExplorationFind getExplorationFindNoCheck() {
        return this.explorationFind;
    }

    public float getFoodPerFarmer(int i) {
        float foodPerFarmer = getClimate().getFoodPerFarmer();
        if (hasBeenExploredByEmpire(i)) {
            for (ResourceID resourceID : getVisibleResources(i)) {
                foodPerFarmer += Resources.get(resourceID).getValue(ResourceType.FOOD_PER_FARMER);
            }
        }
        if (foodPerFarmer != 0.0f) {
            foodPerFarmer += GameData.empires.get(i).getFoodPerks();
        }
        return foodPerFarmer * getGravity().getModifier();
    }

    public Gravity getGravity() {
        if (hasAdjustedGravity()) {
            return Gravity.NORMAL;
        }
        return this.gravity;
    }

    public float getMaintenanceCostModifier() {
        return getClimate().getMaintenanceCostModifier();
    }

    public int getMaxPopulation() {
        float populationLimitModifier = getClimate().getPopulationLimitModifier();
        if (this.isTerraformed) {
            populationLimitModifier += 0.1f;
        }
        return checkModifiersOnPopulation((int) (this.size.getPopulationModifier() * 60 * populationLimitModifier));
    }

    public int getMaxPopulationPreTerraforming() {
        return checkModifiersOnPopulation((int) (this.size.getPopulationModifier() * 60 * getPreTerraformedClimate().getPopulationLimitModifier()));
    }

    public MineralRichness getMineralRichness() {
        return this.mineralRichness;
    }

    public Moon getMoon() {
        return this.moon;
    }

    public int getNaturalPower(int i) {
        int i2 = 0;
        for (ResourceID resourceID : this.f1397c) {
            if (Resources.get(resourceID).isVisible(i)) {
                i2 = (int) (i2 + Resources.get(resourceID).getValue(ResourceType.POWER));
            }
        }
        return i2;
    }

    public int getPlanetValue() {
        return this.planetValue;
    }

    public float getPopulationGrowthModifier() {
        return checkForPopulationGrowthModifiers(getClimate().getPopulationGrowthModifier());
    }

    public float getPopulationGrowthModifierPreTerraforming() {
        return checkForPopulationGrowthModifiers(getPreTerraformedClimate().getPopulationGrowthModifier());
    }

    public Climate getPreTerraformedClimate() {
        return this.climate;
    }

    public float getProductionPerWorker(int i) {
        float productionPerWorker = this.mineralRichness.getProductionPerWorker();
        if (hasBeenExploredByEmpire(i)) {
            for (ResourceID resourceID : getVisibleResources(i)) {
                productionPerWorker += Resources.get(resourceID).getValue(ResourceType.PRODUCTION_PER_WORKER);
            }
        }
        return (productionPerWorker + GameData.empires.get(i).getProductionPerks()) * getGravity().getModifier();
    }

    public int getRingImageIndex() {
        return this.ringImageIndex;
    }

    public float getScale(ExtendedScene extendedScene) {
        if (extendedScene instanceof PlanetScene) {
            return this.size.getPlanetValue();
        }
        if (extendedScene instanceof SystemScene) {
            return this.size.getSystemValue();
        }
        if (extendedScene instanceof GalaxyScene) {
            return this.size.getPreviewValue();
        }
        if (extendedScene instanceof ColoniesScene) {
            return 0.4f;
        }
        if (extendedScene instanceof BuildingsScene) {
            return 0.35f;
        }
        return extendedScene instanceof FleetsScene ? 0.2f : 1.0f;
    }

    public float getSciencePerScientist(int i) {
        float researchBonus = getClimate().getResearchBonus() + 2;
        if (hasBeenExploredByEmpire(i)) {
            for (ResourceID resourceID : getVisibleResources(i)) {
                researchBonus += Resources.get(resourceID).getValue(ResourceType.SCIENCE_PER_SCIENTIST);
            }
        }
        return (researchBonus + GameData.empires.get(i).getSciencePerks()) * getGravity().getModifier();
    }

    public float getSciencePerScientistPreTerraforming() {
        return (getPreTerraformedClimate().getResearchBonus() + 2) * getGravity().getModifier();
    }

    public PlanetSize getSize() {
        return this.size;
    }

    public Climate getTerraformedClimate() {
        return this.terraformedClimate;
    }

    public Gravity getUnmodifiedGravity() {
        return this.gravity;
    }

    public boolean hasAdjustedGravity() {
        Gravity gravity = this.gravity;
        if ((gravity == Gravity.LOW || gravity == Gravity.HIGH) && GameData.colonies.isColony(this.f1396a, this.b)) {
            Colony colony = GameData.colonies.getColony(this.f1396a, this.b);
            if (colony.getPowerLevel() < 0) {
                return false;
            }
            return colony.hasBuilding(BuildingID.GRAVITY_GENERATOR) || colony.hasBuilding(BuildingID.GRAVITY_DAMPER);
        }
        return false;
    }

    public boolean hasMoon() {
        return this.hasMoon;
    }

    public boolean hasRing() {
        return this.hasRing;
    }

    public void improveMineralRichness() {
        MineralRichness mineralRichness = this.mineralRichness;
        if (mineralRichness != MineralRichness.ULTRA_RICH) {
            this.mineralRichness = mineralRichness.getNext();
        }
    }

    public boolean isTerraformed() {
        return this.isTerraformed;
    }

    public void setClimateChange(Climate climate) {
        this.terraformedClimate = climate;
    }

    public void terraform(int i) {
        this.isTerraformed = true;
        switch (AnonymousClass1.f1393a[this.terraformedClimate.ordinal()]) {
            case 5:
                improveMineralRichness();
                break;
            case 6:
                this.f1397c.add(ResourceID.BIO_TOXIN);
                break;
            case 7:
                this.f1397c.add(ResourceID.ACID);
                break;
            case 8:
                this.f1397c.add(ResourceID.WHISKEY);
                break;
            case 9:
                this.f1397c.add(ResourceID.METALLIC_METHANE);
                break;
            case 10:
                this.f1397c.add(ResourceID.EXOTIC_WOOD);
                break;
            case 11:
            case 12:
                this.f1397c.add(ResourceID.RESORT);
                break;
        }
        if (getFoodPerFarmer(i) == 0.0f && hasColony()) {
            Colony colony = getColony();
            int farmersPercent = colony.getFarmersPercent();
            colony.setFarmersPercent(0);
            colony.setWorkersPercent(colony.getWorkersPercent() + farmersPercent);
        }
    }

    public void setClimateChange(Climate climate, Climate climate2) {
        this.climate = climate;
        this.terraformedClimate = climate2;
    }
}
