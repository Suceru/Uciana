package com.birdshel.Uciana.Planets;

import android.util.SparseArray;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.R;
import java.util.HashMap;
import java.util.Map;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public enum Climate {
    SUPER_ACIDIC(new Builder().w(2).r("Super Acidic").t(R.string.climate_super_acidic).v(0.0f).A(0.2f).z(0.6f).y(2.25f).B(1).s(15).x(0).q(-15).u().D()),
    CORROSIVE(new Builder().w(3).r("Corrosive").t(R.string.climate_corrosive).v(0.0f).A(0.2f).z(0.75f).y(1.75f).s(15).F(new HashMap<Object, Integer>() { // from class: com.birdshel.Uciana.Planets.Climate.1
        {
            put("Metallic", 35);
            put("Super Acidic", 30);
            put("Corrosive", 25);
            put("Methane", 10);
        }
    }).x(1).q(-15)),
    METALLIC(new Builder().w(4).r("Metallic").t(R.string.climate_metallic).v(0.0f).A(0.5f).z(1.0f).s(5).x(2).q(15).u().D()),
    RADIATED(new Builder().w(5).r("Radiated").t(R.string.climate_radiated).v(0.0f).A(0.25f).z(0.8f).y(1.5f).s(15).F(new HashMap<Object, Integer>() { // from class: com.birdshel.Uciana.Planets.Climate.2
        {
            put("Barren", 35);
            put("Desert", 35);
            put("Tundra", 15);
            put("Arid", 15);
        }
    }).x(3).q(-10)),
    VOLCANIC(new Builder().w(6).r("Volcanic").t(R.string.climate_volcanic).v(0.0f).A(0.2f).z(0.8f).y(1.6f).s(15).B(1).x(4).q(-10).u().D()),
    BARREN(new Builder().w(7).r("Barren").t(R.string.climate_barren).v(0.0f).A(0.25f).z(0.85f).y(1.35f).s(10).F(new HashMap<Object, Integer>() { // from class: com.birdshel.Uciana.Planets.Climate.3
        {
            put("Desert", 30);
            put("Tundra", 30);
            put("Ice", 25);
            put("Arid", 10);
            put("Plains", 5);
        }
    }).x(5).q(-5)),
    DESERT(new Builder().w(8).r("Desert").t(R.string.climate_desert).v(1.0f).A(0.35f).z(0.9f).y(1.2f).s(10).F(new HashMap<Object, Integer>() { // from class: com.birdshel.Uciana.Planets.Climate.4
        {
            put("Arid", 40);
            put("Plains", 25);
            put("Tundra", 25);
            put("Jungle", 5);
            put("Terran", 5);
        }
    }).x(6).q(0)),
    ICE(new Builder().w(9).r("Ice").t(R.string.climate_ice).v(1.0f).A(0.35f).z(0.9f).y(1.2f).s(10).F(new HashMap<Object, Integer>() { // from class: com.birdshel.Uciana.Planets.Climate.5
        {
            put("Ocean", 35);
            put("Tundra", 20);
            put("Aphotic Ocean", 20);
            put("Swamp", 15);
            put("Methane", 5);
            put("Tropical Ocean", 5);
        }
    }).x(7).q(0)),
    METHANE(new Builder().w(10).r("Methane").t(R.string.climate_methane).v(0.0f).A(0.35f).z(0.9f).y(1.2f).s(10).B(1).x(8).q(0).u().D()),
    TUNDRA(new Builder().w(11).r("Tundra").t(R.string.climate_tundra).v(1.0f).A(0.4f).z(0.95f).y(1.1f).s(10).F(new HashMap<Object, Integer>() { // from class: com.birdshel.Uciana.Planets.Climate.6
        {
            put("Aphotic Ocean", 25);
            put("Swamp", 25);
            put("Ocean", 25);
            put("Jungle", 15);
            put("Methane", 10);
        }
    }).x(9).q(5)),
    ARID(new Builder().w(12).r("Arid").t(R.string.climate_arid).v(1.0f).A(0.65f).z(1.0f).y(1.1f).s(10).F(new HashMap<Object, Integer>() { // from class: com.birdshel.Uciana.Planets.Climate.7
        {
            put("Plains", 35);
            put("Terran", 20);
            put("Stagnant Terran", 20);
            put("Jungle", 15);
            put("Boreal", 10);
        }
    }).x(10).q(5)),
    PLAINS(new Builder().w(13).r("Plains").t(R.string.climate_plains).v(2.0f).A(0.7f).z(1.05f).y(1.05f).x(11).q(10).u().D()),
    APHOTIC_OCEAN(new Builder().w(14).r("Aphotic Ocean").t(R.string.climate_aphotic_ocean).v(2.0f).A(0.45f).z(1.0f).y(1.1f).x(12).q(10).u().D()),
    OCEAN(new Builder().w(15).r("Ocean").t(R.string.climate_ocean).v(3.0f).A(0.45f).z(1.05f).F(new HashMap<Object, Integer>() { // from class: com.birdshel.Uciana.Planets.Climate.8
        {
            put("Terran", 25);
            put("Garden", 8);
            put("Gaia", 5);
            put("Sentient", 2);
            put("Tropical Ocean", 60);
        }
    }).x(13).q(19)),
    TROPICAL_OCEAN(new Builder().w(16).r("Tropical Ocean").t(R.string.climate_tropical_ocean).v(4.0f).A(0.55f).z(1.15f).x(14).q(25).u().D()),
    BOG(new Builder().w(17).r("Bog").t(R.string.climate_bog).v(1.0f).A(0.55f).z(0.9f).y(1.1f).s(10).x(15).q(10).u().D()),
    SWAMP(new Builder().w(18).r("Swamp").t(R.string.climate_swamp).v(2.0f).A(0.55f).z(0.95f).y(1.1f).s(10).F(new HashMap<Object, Integer>() { // from class: com.birdshel.Uciana.Planets.Climate.9
        {
            put("Bog", 35);
            put("Jungle", 20);
            put("Stagnant Terran", 20);
            put("Plague", 15);
            put("Terran", 10);
        }
    }).x(16).q(10)),
    PLAGUE(new Builder().w(19).r("Plague").t(R.string.climate_plague).v(1.0f).A(0.6f).z(0.5f).y(1.1f).s(15).B(1).x(17).q(10).u().D()),
    JUNGLE(new Builder().w(20).r("Jungle").t(R.string.climate_jungle).v(2.0f).A(0.6f).z(0.95f).y(1.1f).s(10).F(new HashMap<Object, Integer>() { // from class: com.birdshel.Uciana.Planets.Climate.10
        {
            put("Boreal", 35);
            put("Plague", 30);
            put("Terran", 15);
            put("Stagnant Terran", 15);
            put("Gaia", 5);
        }
    }).x(18).q(15)),
    BOREAL(new Builder().w(21).r("Boreal").t(R.string.climate_boreal).v(3.0f).A(0.65f).z(1.05f).x(19).q(15).u().D()),
    STAGNANT(new Builder().w(22).r("Stagnant Terran").t(R.string.climate_stagnant_terran).v(3.0f).A(0.8f).z(0.8f).y(1.1f).x(20).q(15).u().D()),
    TERRAN(new Builder().w(23).r("Terran").t(R.string.climate_terran).v(3.0f).A(0.8f).z(1.1f).F(new HashMap<Object, Integer>() { // from class: com.birdshel.Uciana.Planets.Climate.11
        {
            put("Garden", 65);
            put("Gaia", 25);
            put("Sentient", 10);
        }
    }).x(21).q(20)),
    GARDEN(new Builder().w(24).r("Garden").t(R.string.climate_garden).v(4.0f).A(0.9f).z(1.1f).F(new HashMap<Object, Integer>() { // from class: com.birdshel.Uciana.Planets.Climate.12
        {
            put("Gaia", 100);
        }
    }).x(22).q(21).u()),
    GAIA(new Builder().w(25).r("Gaia").t(R.string.climate_gaia).v(4.0f).A(1.0f).z(1.15f).F(new HashMap<Object, Integer>() { // from class: com.birdshel.Uciana.Planets.Climate.13
        {
            put("Sentient", 100);
        }
    }).x(23).q(25).u().D()),
    SENTIENT(new Builder().w(26).r("Sentient").t(R.string.climate_sentient).v(5.0f).A(1.2f).z(1.25f).x(24).q(26).u().D()),
    GAS_GIANT(new Builder().w(0).r("Gas Giant").t(R.string.climate_gas_giant).v(0.0f).A(0.0f).z(0.0f).B(1).x(25).q(0)),
    MOLTEN(new Builder().w(1).r("Molten").t(R.string.climate_molten).v(0.0f).A(0.1f).z(0.75f).y(2.0f).s(15).F(new HashMap<Object, Integer>() { // from class: com.birdshel.Uciana.Planets.Climate.14
        {
            put("Barren", 20);
            put("Volcanic", 26);
            put("Molten", 25);
            put("Radiated", 24);
            put("Metallic", 5);
        }
    }).x(38).q(-15)),
    BROKEN(new Builder().w(27).r("Broken World").t(R.string.climate_broken_world).v(1.0f).A(0.5f).z(0.9f).y(1.1f).B(4).s(15).x(39).q(5).u().E().D().C(R.string.planet_discovery_broken)),
    RED_HOMEWORLD(new Builder().w(28).r("Terran").t(R.string.climate_terran).v(3.0f).A(0.8f).z(1.1f).x(21).q(21).u().E().D().C(R.string.planet_discovery_red_homeworld)),
    GREEN_HOMEWORLD(new Builder().w(29).r("Terran").t(R.string.climate_terran).v(3.0f).A(0.8f).z(1.1f).x(21).q(21).u().E().D().C(R.string.planet_discovery_green_homeworld)),
    BLUE_HOMEWORLD(new Builder().w(30).r("Terran").t(R.string.climate_terran).v(3.0f).A(0.8f).z(1.1f).x(21).q(21).u().E().D().C(R.string.planet_discovery_blue_homeworld)),
    ORANGE_HOMEWORLD(new Builder().w(31).r("Terran").t(R.string.climate_terran).v(3.0f).A(0.8f).z(1.1f).x(21).q(21).u().E().D().C(R.string.planet_discovery_orange_homeworld)),
    YELLOW_HOMEWORLD(new Builder().w(32).r("Terran").t(R.string.climate_terran).v(3.0f).A(0.8f).z(1.1f).x(21).q(21).u().E().D().C(R.string.planet_discovery_yellow_homeworld)),
    PURPLE_HOMEWORLD(new Builder().w(33).r("Terran").t(R.string.climate_terran).v(3.0f).A(0.8f).z(1.1f).x(21).q(21).u().E().D().C(R.string.planet_discovery_purple_homeworld)),
    RING(new Builder().w(34).r("Gem World").t(R.string.climate_gem_world).v(0.0f).A(0.65f).z(1.1f).B(2).x(40).q(24).u().E().D().C(R.string.planet_discovery_ring)),
    POLLUTED(new Builder().w(35).r("Polluted World").t(R.string.climate_polluted_world).v(1.0f).A(0.5f).z(0.9f).y(1.5f).s(10).B(2).x(41).q(5).u().E().D().C(R.string.planet_discovery_polluted)),
    CYAN_HOMEWORLD(new Builder().w(36).r("Terran").t(R.string.climate_terran).v(3.0f).A(0.8f).z(1.1f).x(21).q(21).u().E().D().C(R.string.planet_discovery_cyan_homeworld));
    
    private static final SparseArray<Climate> lookup = new SparseArray<>();
    private static final Map<String, Climate> terraformNameLookup;
    private final int calculationValue;
    private final String climateName;
    private final int defenceBonus;
    private final int descriptionID;
    private final int displayNameID;
    private final float foodPerFarmer;
    private final int id;
    private final int infoIconIndex;
    private final boolean isSpecial;
    private final float maintenanceCostModifier;
    private final float populationGrowthModifier;
    private final float populationLimitModifier;
    private final boolean rareClimate;
    private final int researchBonus;
    private final Map<Object, Integer> terraformedPercents;
    private final boolean useImageIndex;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: MyApplication */
    /* loaded from: classes.dex */
    public static class Builder {
        private int calculationValue;
        private String climateName;
        private int displayNameID;
        private float foodPerFarmer;
        private int id;
        private int infoIconIndex;
        private float populationGrowthModifier;
        private float populationLimitModifier;
        private boolean useImageIndex = true;
        private boolean isSpecial = false;
        private int researchBonus = 0;
        private int defenceBonus = 5;
        private float maintenanceCostModifier = 1.0f;
        private Map<Object, Integer> terraformedPercents = new HashMap();
        private boolean rareClimate = false;
        private int descriptionID = -1;

        Builder() {
        }

        Builder A(float f2) {
            this.populationLimitModifier = f2;
            return this;
        }

        Builder B(int i) {
            this.researchBonus = i;
            return this;
        }

        Builder C(int i) {
            this.descriptionID = i;
            return this;
        }

        Builder D() {
            this.rareClimate = true;
            return this;
        }

        Builder E() {
            this.isSpecial = true;
            return this;
        }

        Builder F(Map<Object, Integer> map) {
            this.terraformedPercents = map;
            return this;
        }

        Builder q(int i) {
            this.calculationValue = i;
            return this;
        }

        Builder r(String str) {
            this.climateName = str;
            return this;
        }

        Builder s(int i) {
            this.defenceBonus = i;
            return this;
        }

        Builder t(int i) {
            this.displayNameID = i;
            return this;
        }

        Builder u() {
            this.useImageIndex = false;
            return this;
        }

        Builder v(float f2) {
            this.foodPerFarmer = f2;
            return this;
        }

        Builder w(int i) {
            this.id = i;
            return this;
        }

        Builder x(int i) {
            this.infoIconIndex = i;
            return this;
        }

        Builder y(float f2) {
            this.maintenanceCostModifier = f2;
            return this;
        }

        Builder z(float f2) {
            this.populationGrowthModifier = f2;
            return this;
        }
    }

    static {
        Climate[] values;
        Climate[] values2;
        for (Climate climate : values()) {
            lookup.put(climate.id, climate);
        }
        terraformNameLookup = new HashMap();
        for (Climate climate2 : values()) {
            if (!climate2.isSpecial()) {
                terraformNameLookup.put(climate2.climateName, climate2);
            }
        }
    }

    Climate(Builder builder) {
        this.id = builder.id;
        this.climateName = builder.climateName;
        this.foodPerFarmer = builder.foodPerFarmer;
        this.populationLimitModifier = builder.populationLimitModifier;
        this.populationGrowthModifier = builder.populationGrowthModifier;
        this.maintenanceCostModifier = builder.maintenanceCostModifier;
        this.researchBonus = builder.researchBonus;
        this.defenceBonus = builder.defenceBonus;
        this.terraformedPercents = builder.terraformedPercents;
        this.infoIconIndex = builder.infoIconIndex;
        this.isSpecial = builder.isSpecial;
        this.calculationValue = builder.calculationValue;
        this.useImageIndex = builder.useImageIndex;
        this.displayNameID = builder.displayNameID;
        this.rareClimate = builder.rareClimate;
        this.descriptionID = builder.descriptionID;
    }

    public static Climate getClimate(int i) {
        return lookup.get(i);
    }

    public int getCalculationValue() {
        return this.calculationValue;
    }

    public int getDefenceBonus() {
        return this.defenceBonus;
    }

    public String getDescription() {
        int i = this.descriptionID;
        return i == -1 ? "" : GameData.activity.getString(i);
    }

    public String getDisplayName() {
        return GameData.activity.getString(this.displayNameID);
    }

    public float getFoodPerFarmer() {
        return this.foodPerFarmer;
    }

    public int getID() {
        return this.id;
    }

    public int getImageIndex(int i) {
        if (this.useImageIndex) {
            return i;
        }
        return 0;
    }

    public int getInfoIconIndex() {
        return this.infoIconIndex;
    }

    public float getMaintenanceCostModifier() {
        return this.maintenanceCostModifier;
    }

    public float getPopulationGrowthModifier() {
        return this.populationGrowthModifier;
    }

    public float getPopulationLimitModifier() {
        return this.populationLimitModifier;
    }

    public int getResearchBonus() {
        return this.researchBonus;
    }

    public Climate getTerraformedClimate() {
        return terraformNameLookup.get((String) Functions.getItemByPercent(this.terraformedPercents));
    }

    public boolean isRareClimate() {
        return this.rareClimate;
    }

    public boolean isSpecial() {
        return this.isSpecial;
    }
}
