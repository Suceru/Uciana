package com.birdshel.Uciana.Colonies.Buildings;

import com.birdshel.Uciana.Colonies.Buildings.Building;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Technology.TechID;
import java.util.HashMap;
import java.util.Map;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Buildings {
    private static final Map<BuildingID, Building> buildings = new HashMap();

    private static void addAntimatterReactor() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.ANTIMATTER_REACTOR;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_antimatter_reactor)).s(600).m(BuildingType.POWER).q(10).imageIndex(GameIconEnum.POWER_BUILDING.ordinal()).t(TechID.ANTIMATTER_REACTOR_PLANT).p(new HashMap<BuildingEffectType, Float>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.56
            {
                put(BuildingEffectType.POWER, Float.valueOf(20.0f));
            }
        }).info(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.55
            {
                put("1-string", "+20");
                put("2-icon", InfoIconEnum.POWER);
            }
        }).o(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.54
            {
                put("1-cost-string", "-10");
                put("2-icon", InfoIconEnum.CREDITS);
            }
        }).build());
    }

    private static void addAutomatedFactory() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.AUTOMATED_FACTORY;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_automated_factory)).s(600).m(BuildingType.PRODUCTION).q(20).imageIndex(GameIconEnum.PRODUCTION_BUILDING.ordinal()).t(TechID.AUTOMATED_FACTORY).r(10).p(new HashMap<BuildingEffectType, Float>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.41
            {
                put(BuildingEffectType.PRODUCTION, Float.valueOf(10.0f));
                put(BuildingEffectType.PRODUCTION_PER_WORKER, Float.valueOf(1.0f));
            }
        }).info(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.40
            {
                put("1-string", "+10");
                InfoIconEnum infoIconEnum = InfoIconEnum.PRODUCTION;
                put("2-icon", infoIconEnum);
                put("3-string", " +1");
                put("4-icon", infoIconEnum);
                put("5-string", GameData.activity.getString(R.string.building_effect_per_worker));
            }
        }).o(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.39
            {
                put("1-string", "-10");
                put("2-icon", InfoIconEnum.POWER);
                put("3-cost-string", "-20");
                put("4-icon", InfoIconEnum.CREDITS);
            }
        }).build());
    }

    private static void addAutomaticFarm() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.AUTOMATIC_FARM;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_automatic_farm)).s(500).m(BuildingType.FOOD).q(10).imageIndex(GameIconEnum.FOOD_BUILDING.ordinal()).t(TechID.AUTOMATED_FARM).r(5).p(new HashMap<BuildingEffectType, Float>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.67
            {
                put(BuildingEffectType.FOOD, Float.valueOf(25.0f));
            }
        }).info(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.66
            {
                put("1-string", "+25");
                put("2-icon", InfoIconEnum.FOOD);
            }
        }).o(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.65
            {
                put("1-string", "-5");
                put("2-icon", InfoIconEnum.POWER);
                put("3-cost-string", "-10");
                put("4-icon", InfoIconEnum.CREDITS);
            }
        }).build());
    }

    private static void addCapital() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.CAPITAL;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_capital)).s(600).m(BuildingType.HAPPINESS).q(0).imageIndex(GameIconEnum.CAPITAL.ordinal()).n().p(new HashMap<BuildingEffectType, Float>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.30
            {
                put(BuildingEffectType.COMMAND_POINTS, Float.valueOf(6.0f));
            }
        }).info(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.29
            {
                put("1-string", "+25%");
                put("2-icon", InfoIconEnum.HAPPINESS);
                put("3-string", GameData.activity.getString(R.string.building_effect_empire_wide));
                put("4-string", "  +6");
                put("5-icon", InfoIconEnum.COMMAND_POINTS);
            }
        }).build());
    }

    private static void addCloningCenter() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.CLONING_CENTER;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_cloning_center)).s(500).m(BuildingType.POPULATION).q(20).imageIndex(GameIconEnum.POPULATION_BUILDING.ordinal()).t(TechID.CLONING_CENTER).r(5).p(new HashMap<BuildingEffectType, Float>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.25
            {
                put(BuildingEffectType.POPULATION_GROWTH, Float.valueOf(1.0f));
            }
        }).info(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.24
            {
                put("1-string", "+1m");
                put("2-icon", InfoIconEnum.POPULATION);
                put("3-string", GameData.activity.getString(R.string.building_effect_per));
                put("4-icon", InfoIconEnum.TURN);
            }
        }).o(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.23
            {
                put("1-string", "-5");
                put("2-icon", InfoIconEnum.POWER);
                put("3-cost-string", "-20");
                put("4-icon", InfoIconEnum.CREDITS);
            }
        }).build());
    }

    private static void addFoodReplicators() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.FOOD_REPLICATORS;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_food_replicators)).s(1000).m(BuildingType.FOOD).q(30).imageIndex(GameIconEnum.FOOD_BUILDING.ordinal()).t(TechID.FOOD_REPLICATORS).r(10).p(new HashMap<BuildingEffectType, Float>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.70
            {
                put(BuildingEffectType.FOOD, Float.valueOf(50.0f));
            }
        }).info(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.69
            {
                put("1-string", "+50");
                put("2-icon", InfoIconEnum.FOOD);
            }
        }).o(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.68
            {
                put("1-string", "-10");
                put("2-icon", InfoIconEnum.POWER);
                put("3-cost-string", "-30");
                put("4-icon", InfoIconEnum.CREDITS);
            }
        }).build());
    }

    private static void addFusionPowerPlanet() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.FUSION_POWERPLANT;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_fusion_power)).s(600).m(BuildingType.POWER).q(10).imageIndex(GameIconEnum.POWER_BUILDING.ordinal()).t(TechID.FUSION_REACTOR_PLANT).p(new HashMap<BuildingEffectType, Float>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.50
            {
                put(BuildingEffectType.POWER, Float.valueOf(15.0f));
            }
        }).info(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.49
            {
                put("1-string", "+15");
                put("2-icon", InfoIconEnum.POWER);
            }
        }).o(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.48
            {
                put("1-cost-string", "-10");
                put("2-icon", InfoIconEnum.CREDITS);
            }
        }).build());
    }

    private static void addGalacticInternet() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.GALACTIC_INTERNET;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_galactic_internet)).s(600).m(BuildingType.HAPPINESS).q(10).imageIndex(GameIconEnum.HAPPINESS_BUILDING.ordinal()).t(TechID.GALACTIC_INTERNET).r(5).p(new HashMap<BuildingEffectType, Float>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.13
            {
                put(BuildingEffectType.HAPPINESS, Float.valueOf(0.1f));
            }
        }).info(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.12
            {
                put("1-string", "+10%");
                put("2-icon", InfoIconEnum.HAPPINESS);
            }
        }).o(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.11
            {
                put("1-string", "-5");
                put("2-icon", InfoIconEnum.POWER);
                put("3-cost-string", "-10");
                put("4-icon", InfoIconEnum.CREDITS);
            }
        }).build());
    }

    private static void addGalacticStockExchange() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.GALACTIC_STOCK_EXCHANGE;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_galactic_stock_exchange)).s(600).m(BuildingType.CREDITS).q(10).imageIndex(GameIconEnum.MONEY_BUILDING.ordinal()).t(TechID.GALACTIC_STOCK_EXCHANGE).r(5).p(new HashMap<BuildingEffectType, Float>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.7
            {
                put(BuildingEffectType.CREDITS_INCREASE_PERCENT, Float.valueOf(0.1f));
            }
        }).info(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.6
            {
                put("1-string", "+10%");
                put("2-icon", InfoIconEnum.CREDITS);
            }
        }).o(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.5
            {
                put("1-string", "-5");
                put("2-icon", InfoIconEnum.POWER);
                put("3-cost-string", "-10");
                put("4-icon", InfoIconEnum.CREDITS);
            }
        }).build());
    }

    private static void addGravityDamper() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.GRAVITY_DAMPER;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_gravity_damper)).s(500).m(BuildingType.GRAVITY).q(10).imageIndex(GameIconEnum.GRAVITY_BUILDING.ordinal()).t(TechID.GRAVITY_DAMPER).r(5).info(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.2
            {
                put("1-string", GameData.activity.getString(R.string.building_effect_normalize_gravity));
            }
        }).o(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.1
            {
                put("1-string", "-5");
                put("2-icon", InfoIconEnum.POWER);
                put("3-cost-string", "-10");
                put("4-icon", InfoIconEnum.CREDITS);
            }
        }).build());
    }

    private static void addGravityGenerator() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.GRAVITY_GENERATOR;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_gravity_generator)).s(500).m(BuildingType.GRAVITY).q(20).imageIndex(GameIconEnum.GRAVITY_BUILDING.ordinal()).t(TechID.GRAVITY_GENERATOR).r(5).info(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.4
            {
                put("1-string", GameData.activity.getString(R.string.building_effect_normalize_gravity));
            }
        }).o(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.3
            {
                put("1-string", "-5");
                put("2-icon", InfoIconEnum.POWER);
                put("3-cost-string", "-20");
                put("4-icon", InfoIconEnum.CREDITS);
            }
        }).build());
    }

    private static void addHolographicCenter() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.HOLOGRAPHIC_CENTER;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_holographic_center)).s(1200).m(BuildingType.HAPPINESS).q(30).imageIndex(GameIconEnum.HAPPINESS_BUILDING.ordinal()).t(TechID.HOLOGRAPHIC_CENTER).r(5).p(new HashMap<BuildingEffectType, Float>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.19
            {
                put(BuildingEffectType.HAPPINESS, Float.valueOf(0.1f));
            }
        }).info(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.18
            {
                put("1-string", "+10%");
                put("2-icon", InfoIconEnum.HAPPINESS);
            }
        }).o(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.17
            {
                put("1-string", "-5");
                put("2-icon", InfoIconEnum.POWER);
                put("3-cost-string", "-30");
                put("4-icon", InfoIconEnum.CREDITS);
            }
        }).build());
    }

    private static void addInfantryBarracks() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.INFANTRY_BARRACKS;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_infantry_barracks)).s(500).m(BuildingType.GROUND_TROOPS).q(0).imageIndex(GameIconEnum.DEFENCE_BUILDING.ordinal()).p(new HashMap<BuildingEffectType, Float>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.62
            {
                put(BuildingEffectType.DEFENSE, Float.valueOf(5.0f));
            }
        }).info(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.61
            {
                put("1-string", "+5");
                put("2-icon", InfoIconEnum.DEFENSE);
                put("3-string", "  +1");
                put("4-icon", InfoIconEnum.INFANTRY);
                put("5-string", GameData.activity.getString(R.string.building_effect_every) + " 5");
                put("6-icon", InfoIconEnum.TURN);
            }
        }).o(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.60
            {
                put("1-cost-string", "-10");
                put("2-icon", InfoIconEnum.CREDITS);
            }
        }).build());
    }

    private static void addMoonBase() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.MOON_BASE;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_moon_base)).s(300).m(BuildingType.POPULATION).q(20).imageIndex(GameIconEnum.POPULATION_BUILDING.ordinal()).t(TechID.MOON_BASE).p(new HashMap<BuildingEffectType, Float>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.22
            {
                put(BuildingEffectType.POPULATION, Float.valueOf(20.0f));
            }
        }).info(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.21
            {
                put("1-string", "+20m pop");
            }
        }).o(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.20
            {
                put("1-cost-string", "-20");
                put("2-icon", InfoIconEnum.CREDITS);
            }
        }).build());
    }

    private static void addNanoFabricationPlant() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.NANO_FABRICATION_PLANT;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_nano_fabrication_plant)).s(600).m(BuildingType.PRODUCTION).q(20).imageIndex(GameIconEnum.PRODUCTION_BUILDING.ordinal()).t(TechID.NANO_FABRICATION_PLANT).r(10).p(new HashMap<BuildingEffectType, Float>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.44
            {
                put(BuildingEffectType.PRODUCTION, Float.valueOf(20.0f));
                put(BuildingEffectType.PRODUCTION_PER_WORKER, Float.valueOf(1.0f));
            }
        }).info(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.43
            {
                put("1-string", "+20");
                InfoIconEnum infoIconEnum = InfoIconEnum.PRODUCTION;
                put("2-icon", infoIconEnum);
                put("3-string", " +1");
                put("4-icon", infoIconEnum);
                put("5-string", GameData.activity.getString(R.string.building_effect_per_worker));
            }
        }).o(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.42
            {
                put("1-string", "-10");
                put("2-icon", InfoIconEnum.POWER);
                put("3-cost-string", "-20");
                put("4-icon", InfoIconEnum.CREDITS);
            }
        }).build());
    }

    private static void addNone() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.NONE;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_none)).s(0).m(BuildingType.NONE).q(0).imageIndex(GameIconEnum.NONE.ordinal()).n().build());
    }

    private static void addNuclearPowerPlanet() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.NUCLEAR_POWERPLANT;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_nuclear_power)).s(600).m(BuildingType.POWER).q(10).imageIndex(GameIconEnum.POWER_BUILDING.ordinal()).p(new HashMap<BuildingEffectType, Float>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.47
            {
                put(BuildingEffectType.POWER, Float.valueOf(15.0f));
            }
        }).info(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.46
            {
                put("1-string", "+15");
                put("2-icon", InfoIconEnum.POWER);
            }
        }).o(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.45
            {
                put("1-cost-string", "-10");
                put("2-icon", InfoIconEnum.CREDITS);
            }
        }).build());
    }

    private static void addPlanetaryShield() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.PLANETARY_SHIELD;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_planetary_shield)).s(400).m(BuildingType.DEFENSE).q(50).imageIndex(GameIconEnum.DEFENCE_BUILDING.ordinal()).t(TechID.PLANETARY_SHIELD).r(5).p(new HashMap<BuildingEffectType, Float>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.88
            {
                put(BuildingEffectType.PLANET_SHIELDING, Float.valueOf(0.25f));
            }
        }).info(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.87
            {
                put("1-string", "+25%");
                put("2-icon", InfoIconEnum.BOMB_SHIELDING);
                put("3-string", GameData.activity.getString(R.string.building_effect_stops_bio));
            }
        }).o(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.86
            {
                put("1-string", "-5");
                put("2-icon", InfoIconEnum.POWER);
                put("3-cost-string", "-50");
                put("4-icon", InfoIconEnum.CREDITS);
            }
        }).build());
    }

    private static void addPointDefenseSystem() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.POINT_DEFENSE_SYSTEM;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_point_defense_system)).s(400).m(BuildingType.DEFENSE).q(50).imageIndex(GameIconEnum.DEFENCE_BUILDING.ordinal()).t(TechID.POINT_DEFENSE_SYSTEM).r(5).p(new HashMap<BuildingEffectType, Float>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.91
            {
                put(BuildingEffectType.PLANET_SHIELDING, Float.valueOf(0.25f));
            }
        }).info(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.90
            {
                put("1-string", "+25%");
                put("2-icon", InfoIconEnum.BOMB_SHIELDING);
            }
        }).o(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.89
            {
                put("1-string", "-5");
                put("2-icon", InfoIconEnum.POWER);
                put("3-cost-string", "-50");
                put("4-icon", InfoIconEnum.CREDITS);
            }
        }).build());
    }

    private static void addQuantumGenerator() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.QUANTUM_GENERATOR;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_quantum_generator)).s(600).m(BuildingType.POWER).q(10).imageIndex(GameIconEnum.POWER_BUILDING.ordinal()).t(TechID.QUANTUM_GENERATOR_PLANT).p(new HashMap<BuildingEffectType, Float>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.53
            {
                put(BuildingEffectType.POWER, Float.valueOf(20.0f));
            }
        }).info(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.52
            {
                put("1-string", "+20");
                put("2-icon", InfoIconEnum.POWER);
            }
        }).o(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.51
            {
                put("1-cost-string", "-10");
                put("2-icon", InfoIconEnum.CREDITS);
            }
        }).build());
    }

    private static void addQuantumSuperComputer() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.QUANTUM_SUPER_COMPUTER;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_quantum_super_computer)).s(600).m(BuildingType.SCIENCE).q(30).imageIndex(GameIconEnum.SCIENCE_BUILDING.ordinal()).t(TechID.QUANTUM_SUPER_COMPUTER).r(5).p(new HashMap<BuildingEffectType, Float>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.85
            {
                put(BuildingEffectType.SCIENCE_PER_SCIENTIST, Float.valueOf(1.0f));
                put(BuildingEffectType.SCIENCE, Float.valueOf(10.0f));
            }
        }).info(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.84
            {
                put("1-string", "+10");
                InfoIconEnum infoIconEnum = InfoIconEnum.SCIENCE;
                put("2-icon", infoIconEnum);
                put("3-string", " +1");
                put("4-icon", infoIconEnum);
                put("5-string", GameData.activity.getString(R.string.building_effect_per_scientist));
            }
        }).o(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.83
            {
                put("1-string", "-5");
                put("2-icon", InfoIconEnum.POWER);
                put("3-cost-string", "-30");
                put("4-icon", InfoIconEnum.CREDITS);
            }
        }).build());
    }

    private static void addScienceLab() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.SCIENCE_LAB;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_science_lab)).s(400).m(BuildingType.SCIENCE).q(30).imageIndex(GameIconEnum.SCIENCE_BUILDING.ordinal()).t(TechID.SCIENCE_LAB).r(5).p(new HashMap<BuildingEffectType, Float>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.82
            {
                put(BuildingEffectType.SCIENCE_PER_SCIENTIST, Float.valueOf(1.0f));
            }
        }).info(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.81
            {
                put("1-string", "+1");
                put("2-icon", InfoIconEnum.SCIENCE);
                put("3-string", GameData.activity.getString(R.string.building_effect_per_scientist));
            }
        }).o(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.80
            {
                put("1-string", "-5");
                put("2-icon", InfoIconEnum.POWER);
                put("3-cost-string", "-30");
                put("4-icon", InfoIconEnum.CREDITS);
            }
        }).build());
    }

    private static void addShipYards() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.SHIP_YARDS;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_ship_yards)).s(600).m(BuildingType.SHIP_YARD).q(10).imageIndex(GameIconEnum.SHIP_YARDS.ordinal()).p(new HashMap<BuildingEffectType, Float>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.28
            {
                put(BuildingEffectType.COMMAND_POINTS, Float.valueOf(3.0f));
            }
        }).info(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.27
            {
                put("1-string", GameData.activity.getString(R.string.building_effect_builds));
                put("2-icon", InfoIconEnum.BATTLESHIP_ICON);
                put("3-icon", InfoIconEnum.DREADNOUGHT_ICON);
                put("4-string", "  +3");
                put("5-icon", InfoIconEnum.COMMAND_POINTS);
            }
        }).o(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.26
            {
                put("1-cost-string", "-10");
                put("2-icon", InfoIconEnum.CREDITS);
            }
        }).build());
    }

    private static void addSoilEnrichment() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.SOIL_ENRICHMENT;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_soil_enrichment)).s(500).m(BuildingType.FOOD).q(0).imageIndex(GameIconEnum.TERRAFORMING.ordinal()).t(TechID.SOIL_ENRICHMENT).n().p(new HashMap<BuildingEffectType, Float>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.64
            {
                put(BuildingEffectType.FOOD_PER_FARMER, Float.valueOf(1.0f));
            }
        }).info(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.63
            {
                put("1-string", "+1");
                put("2-icon", InfoIconEnum.FOOD);
                put("3-string", GameData.activity.getString(R.string.building_effect_per_farmer));
            }
        }).build());
    }

    private static void addSpaceDock() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.SPACE_DOCK;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_space_dock)).s(600).m(BuildingType.FLEET_MAINTENANCE).q(20).imageIndex(GameIconEnum.OUTPOST.ordinal()).p(new HashMap<BuildingEffectType, Float>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.33
            {
                put(BuildingEffectType.COMMAND_POINTS, Float.valueOf(4.0f));
            }
        }).info(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.32
            {
                put("1-string", "+4");
                put("2-icon", InfoIconEnum.COMMAND_POINTS);
            }
        }).o(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.31
            {
                put("1-cost-string", "-20");
                put("2-icon", InfoIconEnum.CREDITS);
            }
        }).build());
    }

    private static void addSpaceElevator() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.SPACE_ELEVATOR;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_space_elevator)).s(1000).m(BuildingType.CREDITS).q(10).imageIndex(GameIconEnum.MONEY_BUILDING.ordinal()).t(TechID.SPACE_ELEVATOR).r(5).p(new HashMap<BuildingEffectType, Float>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.10
            {
                put(BuildingEffectType.CREDITS_INCREASE_PERCENT, Float.valueOf(0.1f));
            }
        }).info(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.9
            {
                put("1-string", "+10%");
                put("2-icon", InfoIconEnum.CREDITS);
            }
        }).o(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.8
            {
                put("1-string", "-5");
                put("2-icon", InfoIconEnum.POWER);
                put("3-cost-string", "-10");
                put("4-icon", InfoIconEnum.CREDITS);
            }
        }).build());
    }

    private static void addSpyNetworkForBlue() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.SPY_NETWORK_BLUE;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_spy_network)).s(600).m(BuildingType.SPY_NETWORK).q(0).imageIndex(GameIconEnum.SPY.ordinal()).n().info(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.75
            {
                put("1-string", GameData.activity.getString(R.string.building_effect_spy_network));
                put("2-banner", 2);
            }
        }).build());
    }

    private static void addSpyNetworkForCyan() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.SPY_NETWORK_CYAN;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_spy_network)).s(600).m(BuildingType.SPY_NETWORK).q(0).imageIndex(GameIconEnum.SPY.ordinal()).n().info(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.79
            {
                put("1-string", GameData.activity.getString(R.string.building_effect_spy_network));
                put("2-banner", 6);
            }
        }).build());
    }

    private static void addSpyNetworkForGreen() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.SPY_NETWORK_GREEN;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_spy_network)).s(600).m(BuildingType.SPY_NETWORK).q(0).imageIndex(GameIconEnum.SPY.ordinal()).n().info(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.74
            {
                put("1-string", GameData.activity.getString(R.string.building_effect_spy_network));
                put("2-banner", 1);
            }
        }).build());
    }

    private static void addSpyNetworkForOrange() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.SPY_NETWORK_ORANGE;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_spy_network)).s(600).m(BuildingType.SPY_NETWORK).q(0).imageIndex(GameIconEnum.SPY.ordinal()).n().info(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.76
            {
                put("1-string", GameData.activity.getString(R.string.building_effect_spy_network));
                put("2-banner", 3);
            }
        }).build());
    }

    private static void addSpyNetworkForPurple() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.SPY_NETWORK_PURPLE;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_spy_network)).s(600).m(BuildingType.SPY_NETWORK).q(0).imageIndex(GameIconEnum.SPY.ordinal()).n().info(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.78
            {
                put("1-string", GameData.activity.getString(R.string.building_effect_spy_network));
                put("2-banner", 5);
            }
        }).build());
    }

    private static void addSpyNetworkForRed() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.SPY_NETWORK_RED;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_spy_network)).s(600).m(BuildingType.SPY_NETWORK).q(0).imageIndex(GameIconEnum.SPY.ordinal()).n().info(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.73
            {
                put("1-string", GameData.activity.getString(R.string.building_effect_spy_network));
                put("2-banner", 0);
            }
        }).build());
    }

    private static void addSpyNetworkForYellow() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.SPY_NETWORK_YELLOW;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_spy_network)).s(600).m(BuildingType.SPY_NETWORK).q(0).imageIndex(GameIconEnum.SPY.ordinal()).n().info(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.77
            {
                put("1-string", GameData.activity.getString(R.string.building_effect_spy_network));
                put("2-banner", 4);
            }
        }).build());
    }

    private static void addStarBase() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.STAR_BASE;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_star_base)).s(1200).m(BuildingType.FLEET_DEFENSE).q(40).imageIndex(GameIconEnum.STAR_BASE.ordinal()).t(TechID.STAR_BASE).p(new HashMap<BuildingEffectType, Float>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.36
            {
                put(BuildingEffectType.COMMAND_POINTS, Float.valueOf(2.0f));
            }
        }).info(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.35
            {
                put("1-icon", InfoIconEnum.DEFENSE);
                put("2-string", GameData.activity.getString(R.string.building_effect_colony));
                put("3-string", "  +2");
                put("4-icon", InfoIconEnum.COMMAND_POINTS);
            }
        }).o(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.34
            {
                put("1-cost-string", "-40");
                put("2-icon", InfoIconEnum.CREDITS);
            }
        }).build());
    }

    private static void addTerraforming() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.TERRAFORMING;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_terraforming)).s(1500).m(BuildingType.TERRAFORMING).q(0).imageIndex(GameIconEnum.TERRAFORMING.ordinal()).n().t(TechID.TERRAFORMING).info(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.71
            {
                put("1-string", GameData.activity.getString(R.string.building_effect_terraforming));
            }
        }).build());
    }

    private static void addTorpedoTurret() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.TORPEDO_TURRET;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_torpedo_turrets)).s(400).m(BuildingType.FLEET_DEFENSE).q(20).imageIndex(GameIconEnum.TORPEDO_TURRET.ordinal()).info(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.38
            {
                put("1-icon", InfoIconEnum.DEFENSE);
                put("2-string", GameData.activity.getString(R.string.building_effect_colony));
            }
        }).o(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.37
            {
                put("1-cost-string", "-20");
                put("2-icon", InfoIconEnum.CREDITS);
            }
        }).build());
    }

    private static void addTradeGoods() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.TRADEGOODS;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_tradegoods)).s(0).m(BuildingType.TRADEGOODS).q(0).imageIndex(GameIconEnum.TRADEGOODS.ordinal()).n().info(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.72
            {
                put("1-string", "+0.5");
                put("2-icon", InfoIconEnum.CREDITS);
                put("3-string", GameData.activity.getString(R.string.building_effect_per));
                put("4-icon", InfoIconEnum.PRODUCTION);
                put("5-string", GameData.activity.getString(R.string.building_effect_per));
                put("6-icon", InfoIconEnum.TURN);
            }
        }).build());
    }

    private static void addVirtualRealitySystem() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.VIRTUAL_REALITY_SYSTEM;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_virtual_reality_system)).s(1000).m(BuildingType.HAPPINESS).q(10).imageIndex(GameIconEnum.HAPPINESS_BUILDING.ordinal()).t(TechID.VIRTUAL_REALITY_SYSTEM).r(5).p(new HashMap<BuildingEffectType, Float>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.16
            {
                put(BuildingEffectType.HAPPINESS, Float.valueOf(0.1f));
            }
        }).info(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.15
            {
                put("1-string", "+10%");
                put("2-icon", InfoIconEnum.HAPPINESS);
            }
        }).o(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.14
            {
                put("1-string", "-5");
                put("2-icon", InfoIconEnum.POWER);
                put("3-cost-string", "-10");
                put("4-icon", InfoIconEnum.CREDITS);
            }
        }).build());
    }

    private static void addZeroPointEnergy() {
        Map<BuildingID, Building> map = buildings;
        BuildingID buildingID = BuildingID.ZERO_POINT_ENERGY_GENERATOR;
        map.put(buildingID, new Building.Builder().id(buildingID).name(GameData.activity.getString(R.string.building_zero_point_energy)).s(600).m(BuildingType.POWER).q(10).imageIndex(GameIconEnum.POWER_BUILDING.ordinal()).t(TechID.ZERO_POINT_ENERGY_PLANT).p(new HashMap<BuildingEffectType, Float>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.59
            {
                put(BuildingEffectType.POWER, Float.valueOf(25.0f));
            }
        }).info(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.58
            {
                put("1-string", "+25");
                put("2-icon", InfoIconEnum.POWER);
            }
        }).o(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Colonies.Buildings.Buildings.57
            {
                put("1-cost-string", "-10");
                put("2-icon", InfoIconEnum.CREDITS);
            }
        }).build());
    }

    public static Building getBuilding(String str) {
        return buildings.get(BuildingID.values()[Integer.parseInt(str)]);
    }

    public static Map<BuildingID, Building> getBuildings() {
        return buildings;
    }

    public static void set() {
        buildings.clear();
        addCapital();
        addShipYards();
        addSpaceDock();
        addStarBase();
        addTorpedoTurret();
        addMoonBase();
        addCloningCenter();
        addNuclearPowerPlanet();
        addFusionPowerPlanet();
        addQuantumGenerator();
        addAntimatterReactor();
        addZeroPointEnergy();
        addAutomaticFarm();
        addFoodReplicators();
        addAutomatedFactory();
        addNanoFabricationPlant();
        addScienceLab();
        addQuantumSuperComputer();
        addGravityDamper();
        addGravityGenerator();
        addGalacticStockExchange();
        addSpaceElevator();
        addGalacticInternet();
        addVirtualRealitySystem();
        addHolographicCenter();
        addPlanetaryShield();
        addInfantryBarracks();
        addPointDefenseSystem();
        addSoilEnrichment();
        addTerraforming();
        addTradeGoods();
        addNone();
        addSpyNetworkForRed();
        addSpyNetworkForGreen();
        addSpyNetworkForBlue();
        addSpyNetworkForOrange();
        addSpyNetworkForYellow();
        addSpyNetworkForPurple();
        addSpyNetworkForCyan();
    }

    public static Building getBuilding(BuildingID buildingID) {
        return buildings.get(buildingID);
    }
}
