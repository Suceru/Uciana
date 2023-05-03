package com.birdshel.Uciana.Colonies.Buildings;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public enum BuildingID {
    GRAVITY_DAMPER,
    GRAVITY_GENERATOR,
    MOON_BASE,
    SHIP_YARDS,
    CAPITAL,
    FUSION_POWERPLANT,
    INFANTRY_BARRACKS,
    SOIL_ENRICHMENT,
    AUTOMATIC_FARM,
    NOT_USED,
    TERRAFORMING,
    TRADEGOODS,
    NONE,
    SCIENCE_LAB,
    PLANETARY_SHIELD,
    NUCLEAR_POWERPLANT,
    QUANTUM_GENERATOR,
    ANTIMATTER_REACTOR,
    ZERO_POINT_ENERGY_GENERATOR,
    SPACE_DOCK,
    AUTOMATED_FACTORY,
    GALACTIC_STOCK_EXCHANGE,
    POINT_DEFENSE_SYSTEM,
    STAR_BASE,
    SPY_NETWORK_RED,
    SPY_NETWORK_GREEN,
    SPY_NETWORK_BLUE,
    SPY_NETWORK_ORANGE,
    SPY_NETWORK_YELLOW,
    SPY_NETWORK_PURPLE,
    GALACTIC_INTERNET,
    VIRTUAL_REALITY_SYSTEM,
    SPACE_ELEVATOR,
    QUANTUM_SUPER_COMPUTER,
    FOOD_REPLICATORS,
    HOLOGRAPHIC_CENTER,
    CLONING_CENTER,
    TORPEDO_TURRET,
    NANO_FABRICATION_PLANT,
    SPY_NETWORK_CYAN;

    public static BuildingID getBuildingID(String str) {
        return values()[Integer.parseInt(str)];
    }

    public static int getEmpireIDForSpyNetwork(BuildingID buildingID) {
        if (buildingID == SPY_NETWORK_CYAN) {
            return 6;
        }
        return buildingID.ordinal() - SPY_NETWORK_RED.ordinal();
    }

    @Override // java.lang.Enum
    public String toString() {
        return Integer.toString(ordinal());
    }
}
