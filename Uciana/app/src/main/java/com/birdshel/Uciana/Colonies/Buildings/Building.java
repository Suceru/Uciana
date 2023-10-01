package com.birdshel.Uciana.Colonies.Buildings;

import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Colonies.ManufacturingType;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Planets.Gravity;
import com.birdshel.Uciana.Technology.TechID;

import java.util.HashMap;
import java.util.Map;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Building {
    private final BuildingType buildingType;
    private final boolean canBeSold;
    private final Map<String, Object> costInfo;
    private final Map<BuildingEffectType, Float> effects;
    private final BuildingID id;
    private final int imageIndex;
    private final Map<String, Object> info;
    private final int maintenanceCost;
    private final String name;
    private final int powerRequirement;
    private final int productionCost;
    private final TechID requiredTech;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Colonies.Buildings.Building$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1353a;
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[BuildingID.values().length];
            b = iArr;
            try {
                iArr[BuildingID.GRAVITY_DAMPER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[BuildingID.GRAVITY_GENERATOR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[BuildingType.values().length];
            f1353a = iArr2;
            try {
                iArr2[BuildingType.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1353a[BuildingType.TERRAFORMING.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1353a[BuildingType.TRADEGOODS.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1353a[BuildingType.GRAVITY.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1353a[BuildingType.POPULATION.ordinal()] = 5;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1353a[BuildingType.FOOD.ordinal()] = 6;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    /* compiled from: MyApplication */
    /* loaded from: classes.dex */
    public static class Builder {
        private BuildingType buildingType;
        private BuildingID id;
        private int imageIndex;
        private int maintenanceCost;
        private String name;
        private int productionCost;
        private boolean canBeSold = true;
        private TechID requiredTech = TechID.NONE;
        private int powerRequirement = 0;
        private Map<BuildingEffectType, Float> effects = new HashMap();
        private Map<String, Object> info = new HashMap();
        private Map<String, Object> costInfo = new HashMap();

        public Building build() {
            return new Building(this);
        }

        public Builder id(BuildingID buildingID) {
            this.id = buildingID;
            return this;
        }

        public Builder imageIndex(int i) {
            this.imageIndex = i;
            return this;
        }

        public Builder info(Map<String, Object> map) {
            this.info = map;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder m(BuildingType buildingType) {
            this.buildingType = buildingType;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder n() {
            this.canBeSold = false;
            return this;
        }

        public Builder name(String str) {
            this.name = str;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder o(Map<String, Object> map) {
            this.costInfo = map;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder p(Map<BuildingEffectType, Float> map) {
            this.effects = map;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder q(int i) {
            this.maintenanceCost = i;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder r(int i) {
            this.powerRequirement = i;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder s(int i) {
            this.productionCost = i;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder t(TechID techID) {
            this.requiredTech = techID;
            return this;
        }
    }

    public Building(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.productionCost = builder.productionCost;
        this.buildingType = builder.buildingType;
        this.maintenanceCost = builder.maintenanceCost;
        this.imageIndex = builder.imageIndex;
        this.canBeSold = builder.canBeSold;
        this.requiredTech = builder.requiredTech;
        this.powerRequirement = builder.powerRequirement;
        this.info = builder.info;
        this.costInfo = builder.costInfo;
        this.effects = builder.effects;
    }

    private boolean checkBuildingCanBeBuilt(Colony colony) {
        if (this.id == BuildingID.CAPITAL) {
            return checkCapitalBuildingCanBeBuilt(colony);
        }
        return true;
    }

    private boolean checkCapitalBuildingCanBeBuilt(Colony colony) {
        if (GameData.getCurrentEmpire().hasCapital()) {
            return false;
        }
        boolean z = true;
        for (Colony colony2 : GameData.getCurrentEmpire().getColonies()) {
            if (colony2.getManufacturing().getType() == ManufacturingType.BUILDINGS && (colony2.getSystemID() != colony.getSystemID() || colony2.getOrbit() != colony.getOrbit())) {
                if (colony2.getManufacturing().getBuildingID().equals(BuildingID.CAPITAL.toString())) {
                    z = false;
                }
            }
        }
        return z;
    }

    private boolean checkFoodBuildingCanBeBuilt(Colony colony) {
        return this.id != BuildingID.SOIL_ENRICHMENT || colony.getPlanet().getClimate().getFoodPerFarmer() > 0.0f;
    }

    private boolean checkGravityBuildingsCanBeBuilt(Colony colony) {
        int i = AnonymousClass1.b[this.id.ordinal()];
        return i != 1 ? i == 2 && colony.getPlanet().getGravity() == Gravity.LOW : colony.getPlanet().getGravity() == Gravity.HIGH;
    }

    private boolean checkPopulationBuildingsCanBeBuilt(Colony colony) {
        if (this.id == BuildingID.MOON_BASE) {
            return colony.getPlanet().hasMoon();
        }
        return true;
    }

    private boolean checkTerraformingCanBeBuilt(Colony colony) {
        return !colony.getPlanet().isTerraformed();
    }

    public boolean canBeBuilt(Colony colony) {
        if (colony.hasBuilding(getID())) {
            return false;
        }
        switch (AnonymousClass1.f1353a[this.buildingType.ordinal()]) {
            case 1:
                return false;
            case 2:
                return checkTerraformingCanBeBuilt(colony);
            case 3:
                return true;
            case 4:
                return checkGravityBuildingsCanBeBuilt(colony);
            case 5:
                return checkPopulationBuildingsCanBeBuilt(colony);
            case 6:
                return checkFoodBuildingCanBeBuilt(colony);
            default:
                return checkBuildingCanBeBuilt(colony);
        }
    }

    public boolean canBeSold() {
        return this.canBeSold;
    }

    public BuildingType getBuildingType() {
        return this.buildingType;
    }

    public Map<String, Object> getCostInfo() {
        return this.costInfo;
    }

    public Map<BuildingEffectType, Float> getEffects() {
        return this.effects;
    }

    public BuildingID getID() {
        return this.id;
    }

    public int getImageIndex() {
        return this.imageIndex;
    }

    public Map<String, Object> getInfo() {
        return this.info;
    }

    public int getMaintenanceCost() {
        return this.maintenanceCost;
    }

    public String getName() {
        return this.name;
    }

    public int getPowerRequirement() {
        return this.powerRequirement;
    }

    public int getProductionCost() {
        return this.productionCost;
    }

    public TechID getRequiredTech() {
        return this.requiredTech;
    }

    public int getSellValue() {
        return this.productionCost / 2;
    }

    public String getStringID() {
        return Integer.toString(this.id.ordinal());
    }
}
