package com.birdshel.Uciana.Technology;

import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Planets.ResourceID;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ComponentIconEnum;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Ships.ShipComponents.WeaponStats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Techs {
    private final Technology techLink;
    private final Map<TechID, Tech> technologies = new HashMap();

    public Techs(Technology technology) {
        this.techLink = technology;
        setOther();
        setTechLevels();
        setFasterThenLightTech();
        setCommunicationTech();
        setScanningTech();
        setFuelTech();
        setPlanetEnhancementTech();
        setBuildingsTech();
        setTroopTech();
        setShieldTech();
        setArmorTech();
        setTargetingTech();
        setSublightEngineTech();
        setAbilityTech();
        setWeaponTech();
        setSpecialComponentTech();
        setPerks();
    }

    private void setAbilityTech() {
        Map<TechID, Tech> map = this.technologies;
        TechID techID = TechID.ASTEROID_MINING_OUTPOST;
        Tech.Builder id = new Tech.Builder(this.techLink).id(techID);
        TechType techType = TechType.ABILITY;
        Tech.Builder shortName = id.type(techType).name(GameData.activity.getString(R.string.tech_mining_outpost)).shortName(GameData.activity.getString(R.string.tech_mining_outpost_short));
        TechCategory techCategory = TechCategory.ENGINEERING;
        map.put(techID, shortName.category(techCategory).level(1).description(GameData.activity.getString(R.string.tech_mining_outpost_description)).iconIndex(GameIconEnum.OUTPOST.ordinal()).build());
        Map<TechID, Tech> map2 = this.technologies;
        TechID techID2 = TechID.DREADNOUGHT;
        map2.put(techID2, new Tech.Builder(this.techLink).id(techID2).type(techType).name(GameData.activity.getString(R.string.tech_dreadnought)).shortName(GameData.activity.getString(R.string.tech_dreadnought_short)).category(techCategory).level(5).description(GameData.activity.getString(R.string.tech_dreadnought_description)).iconIndex(GameIconEnum.SHIPS.ordinal()).build());
        Map<TechID, Tech> map3 = this.technologies;
        TechID techID3 = TechID.COZIURIUM;
        Tech.Builder id2 = new Tech.Builder(this.techLink).id(techID3);
        TechType techType2 = TechType.RESOURCE;
        Tech.Builder shortName2 = id2.type(techType2).name(GameData.activity.getString(R.string.tech_coziurium_detection)).shortName(GameData.activity.getString(R.string.tech_coziurium_detection_short));
        TechCategory techCategory2 = TechCategory.CHEMISTRY;
        map3.put(techID3, shortName2.category(techCategory2).level(1).description(GameData.activity.getString(R.string.tech_coziurium_detection_description)).iconIndex(ResourceID.COZIURIUM.ordinal() - 1).build());
        Map<TechID, Tech> map4 = this.technologies;
        TechID techID4 = TechID.ANTIMATTER_DETECTION;
        map4.put(techID4, new Tech.Builder(this.techLink).id(techID4).type(techType2).name(GameData.activity.getString(R.string.tech_antimatter_detection)).shortName(GameData.activity.getString(R.string.tech_antimatter_detection_short)).category(TechCategory.PHYSICS).level(4).description(GameData.activity.getString(R.string.tech_antimatter_detection_description)).iconIndex(ResourceID.ANTIMATTER.ordinal() - 1).build());
        Map<TechID, Tech> map5 = this.technologies;
        TechID techID5 = TechID.LESCITE_DETECTION;
        map5.put(techID5, new Tech.Builder(this.techLink).id(techID5).type(techType2).name(GameData.activity.getString(R.string.tech_lescite_detection)).shortName(GameData.activity.getString(R.string.tech_lescite_detection_short)).category(techCategory2).level(4).description(GameData.activity.getString(R.string.tech_lescite_detection_description)).iconIndex(ResourceID.LESCITE.ordinal() - 1).build());
    }

    private void setArmorTech() {
        Map<TechID, Tech> map = this.technologies;
        TechID techID = TechID.VANADIUM_ARMOR;
        Tech.Builder id = new Tech.Builder(this.techLink).id(techID);
        TechType techType = TechType.SHIP_ARMOR;
        Tech.Builder shortName = id.type(techType).name(GameData.activity.getString(R.string.tech_vanadium_armor)).shortName(GameData.activity.getString(R.string.tech_vanadium_armor_short));
        TechCategory techCategory = TechCategory.CHEMISTRY;
        map.put(techID, shortName.category(techCategory).level(1).description(GameData.activity.getString(R.string.tech_vanadium_armor_description, new Object[]{2})).iconIndex(ComponentIconEnum.VANADIUM_ARMOR.ordinal()).build());
        Map<TechID, Tech> map2 = this.technologies;
        TechID techID2 = TechID.DETRUTIUM_ARMOR;
        map2.put(techID2, new Tech.Builder(this.techLink).id(techID2).type(techType).name(GameData.activity.getString(R.string.tech_detrutium_armor)).shortName(GameData.activity.getString(R.string.tech_detrutium_armor_short)).category(techCategory).level(3).description(GameData.activity.getString(R.string.tech_detrutium_armor_description, new Object[]{3})).iconIndex(ComponentIconEnum.DETRUTIUM_ARMOR.ordinal()).build());
        Map<TechID, Tech> map3 = this.technologies;
        TechID techID3 = TechID.THETRIUM_ARMOR;
        map3.put(techID3, new Tech.Builder(this.techLink).id(techID3).type(techType).name(GameData.activity.getString(R.string.tech_thetrium_armor)).shortName(GameData.activity.getString(R.string.tech_thetrium_armor_short)).category(techCategory).level(5).description(GameData.activity.getString(R.string.tech_thetrium_armor_description, new Object[]{4})).iconIndex(ComponentIconEnum.THETRIUM_ARMOR.ordinal()).build());
        Map<TechID, Tech> map4 = this.technologies;
        TechID techID4 = TechID.CRYSTALIUM_ARMOR;
        map4.put(techID4, new Tech.Builder(this.techLink).id(techID4).type(techType).name(GameData.activity.getString(R.string.tech_crystalium_armor)).shortName(GameData.activity.getString(R.string.tech_crystalium_armor_short)).category(techCategory).level(6).description(GameData.activity.getString(R.string.tech_crystalium_armor_description, new Object[]{5})).iconIndex(ComponentIconEnum.CRYSTALIUM_ARMOR.ordinal()).build());
        Map<TechID, Tech> map5 = this.technologies;
        TechID techID5 = TechID.NEUTRONIUM_ARMOR;
        map5.put(techID5, new Tech.Builder(this.techLink).id(techID5).type(techType).name(GameData.activity.getString(R.string.tech_neutronium_armor)).shortName(GameData.activity.getString(R.string.tech_neutronium_armor_short)).category(techCategory).level(8).description(GameData.activity.getString(R.string.tech_neutronium_armor_description, new Object[]{6})).iconIndex(ComponentIconEnum.NEUTRONIUM_ARMOR.ordinal()).build());
        Map<TechID, Tech> map6 = this.technologies;
        TechID techID6 = TechID.HARDENED_ALLOY;
        map6.put(techID6, new Tech.Builder(this.techLink).id(techID6).type(TechType.SHIP_SPECIAL_COMPONENT).name(GameData.activity.getString(R.string.tech_hardened_armor_alloy)).shortName(GameData.activity.getString(R.string.tech_hardened_armor_alloy_short)).category(techCategory).level(4).description(GameData.activity.getString(R.string.tech_hardened_armor_alloy_description, new Object[]{25})).iconIndex(ComponentIconEnum.HARDENED_ALLOY.ordinal()).build());
    }

    private void setBuildingsTech() {
        Map<TechID, Tech> map = this.technologies;
        TechID techID = TechID.AUTOMATED_FARM;
        Tech.Builder id = new Tech.Builder(this.techLink).id(techID);
        TechType techType = TechType.BUILDING;
        Tech.Builder shortName = id.type(techType).name(GameData.activity.getString(R.string.tech_automated_farm)).shortName(GameData.activity.getString(R.string.tech_automated_farm_short));
        TechCategory techCategory = TechCategory.CHEMISTRY;
        Tech.Builder description = shortName.category(techCategory).level(2).description(GameData.activity.getString(R.string.tech_automated_farm_description, new Object[]{25}));
        GameIconEnum gameIconEnum = GameIconEnum.FOOD_BUILDING;
        map.put(techID, description.iconIndex(gameIconEnum.ordinal()).build());
        Map<TechID, Tech> map2 = this.technologies;
        TechID techID2 = TechID.CLONING_CENTER;
        Tech.Builder description2 = new Tech.Builder(this.techLink).id(techID2).type(techType).name(GameData.activity.getString(R.string.tech_cloning_center)).shortName(GameData.activity.getString(R.string.tech_cloning_center_short)).category(techCategory).level(1).description(GameData.activity.getString(R.string.tech_cloning_center_description, new Object[]{1}));
        GameIconEnum gameIconEnum2 = GameIconEnum.POPULATION_BUILDING;
        map2.put(techID2, description2.iconIndex(gameIconEnum2.ordinal()).build());
        Map<TechID, Tech> map3 = this.technologies;
        TechID techID3 = TechID.GRAVITY_GENERATOR;
        Tech.Builder shortName2 = new Tech.Builder(this.techLink).id(techID3).type(techType).name(GameData.activity.getString(R.string.tech_gravity_generator)).shortName(GameData.activity.getString(R.string.tech_gravity_generator_short));
        TechCategory techCategory2 = TechCategory.PHYSICS;
        Tech.Builder description3 = shortName2.category(techCategory2).level(5).description(GameData.activity.getString(R.string.tech_gravity_generator_description));
        GameIconEnum gameIconEnum3 = GameIconEnum.GRAVITY_BUILDING;
        map3.put(techID3, description3.iconIndex(gameIconEnum3.ordinal()).build());
        Map<TechID, Tech> map4 = this.technologies;
        TechID techID4 = TechID.GRAVITY_DAMPER;
        map4.put(techID4, new Tech.Builder(this.techLink).id(techID4).type(techType).name(GameData.activity.getString(R.string.tech_gravity_damper)).shortName(GameData.activity.getString(R.string.tech_gravity_damper_short)).category(techCategory2).level(6).description(GameData.activity.getString(R.string.tech_gravity_damper_description)).iconIndex(gameIconEnum3.ordinal()).build());
        Map<TechID, Tech> map5 = this.technologies;
        TechID techID5 = TechID.PLANETARY_SHIELD;
        Tech.Builder shortName3 = new Tech.Builder(this.techLink).id(techID5).type(techType).name(GameData.activity.getString(R.string.tech_planetary_shield)).shortName(GameData.activity.getString(R.string.tech_planetary_shield_short));
        TechCategory techCategory3 = TechCategory.ENERGY;
        Tech.Builder description4 = shortName3.category(techCategory3).level(6).description(GameData.activity.getString(R.string.tech_planetary_shield_description, new Object[]{25}));
        GameIconEnum gameIconEnum4 = GameIconEnum.DEFENCE_BUILDING;
        map5.put(techID5, description4.iconIndex(gameIconEnum4.ordinal()).build());
        Map<TechID, Tech> map6 = this.technologies;
        TechID techID6 = TechID.SCIENCE_LAB;
        Tech.Builder shortName4 = new Tech.Builder(this.techLink).id(techID6).type(techType).name(GameData.activity.getString(R.string.tech_science_lab)).shortName(GameData.activity.getString(R.string.tech_science_lab_short));
        TechCategory techCategory4 = TechCategory.ENGINEERING;
        Tech.Builder description5 = shortName4.category(techCategory4).level(3).description(GameData.activity.getString(R.string.tech_science_lab_description, new Object[]{1}));
        GameIconEnum gameIconEnum5 = GameIconEnum.SCIENCE_BUILDING;
        map6.put(techID6, description5.iconIndex(gameIconEnum5.ordinal()).build());
        Map<TechID, Tech> map7 = this.technologies;
        TechID techID7 = TechID.MOON_BASE;
        map7.put(techID7, new Tech.Builder(this.techLink).id(techID7).type(techType).name(GameData.activity.getString(R.string.tech_moon_base)).shortName(GameData.activity.getString(R.string.tech_moon_base_short)).category(techCategory4).level(2).description(GameData.activity.getString(R.string.tech_moon_base_description, new Object[]{20})).iconIndex(gameIconEnum2.ordinal()).build());
        Map<TechID, Tech> map8 = this.technologies;
        TechID techID8 = TechID.AUTOMATED_FACTORY;
        Tech.Builder description6 = new Tech.Builder(this.techLink).id(techID8).type(techType).name(GameData.activity.getString(R.string.tech_automated_factory)).shortName(GameData.activity.getString(R.string.tech_automated_factory_short)).category(techCategory4).level(2).description(GameData.activity.getString(R.string.tech_automated_factory_description, new Object[]{1, 10}));
        GameIconEnum gameIconEnum6 = GameIconEnum.PRODUCTION_BUILDING;
        map8.put(techID8, description6.iconIndex(gameIconEnum6.ordinal()).build());
        Map<TechID, Tech> map9 = this.technologies;
        TechID techID9 = TechID.FUSION_REACTOR_PLANT;
        Tech.Builder description7 = new Tech.Builder(this.techLink).id(techID9).type(techType).name(GameData.activity.getString(R.string.tech_fusion_reactor_plant)).shortName(GameData.activity.getString(R.string.tech_fusion_reactor_plant_short)).category(techCategory3).level(1).description(GameData.activity.getString(R.string.tech_fusion_reactor_plant_description, new Object[]{15}));
        GameIconEnum gameIconEnum7 = GameIconEnum.POWER_BUILDING;
        map9.put(techID9, description7.iconIndex(gameIconEnum7.ordinal()).build());
        Map<TechID, Tech> map10 = this.technologies;
        TechID techID10 = TechID.QUANTUM_GENERATOR_PLANT;
        map10.put(techID10, new Tech.Builder(this.techLink).id(techID10).type(techType).name(GameData.activity.getString(R.string.tech_quantum_generator_plant)).shortName(GameData.activity.getString(R.string.tech_quantum_generator_plant_short)).category(techCategory3).level(3).description(GameData.activity.getString(R.string.tech_quantum_generator_plant_description, new Object[]{20})).iconIndex(gameIconEnum7.ordinal()).build());
        Map<TechID, Tech> map11 = this.technologies;
        TechID techID11 = TechID.ANTIMATTER_REACTOR_PLANT;
        map11.put(techID11, new Tech.Builder(this.techLink).id(techID11).type(techType).name(GameData.activity.getString(R.string.tech_antimatter_reactor_plant)).shortName(GameData.activity.getString(R.string.tech_antimatter_reactor_plant_short)).category(techCategory3).level(5).description(GameData.activity.getString(R.string.tech_antimatter_reactor_plant_description, new Object[]{20})).iconIndex(gameIconEnum7.ordinal()).build());
        Map<TechID, Tech> map12 = this.technologies;
        TechID techID12 = TechID.ZERO_POINT_ENERGY_PLANT;
        map12.put(techID12, new Tech.Builder(this.techLink).id(techID12).type(techType).name(GameData.activity.getString(R.string.tech_zero_point_energy_plant)).shortName(GameData.activity.getString(R.string.tech_zero_point_energy_plant_short)).category(techCategory3).level(7).description(GameData.activity.getString(R.string.tech_zero_point_energy_plant_description, new Object[]{25})).iconIndex(gameIconEnum7.ordinal()).build());
        Map<TechID, Tech> map13 = this.technologies;
        TechID techID13 = TechID.GALACTIC_STOCK_EXCHANGE;
        Tech.Builder description8 = new Tech.Builder(this.techLink).id(techID13).type(techType).name(GameData.activity.getString(R.string.tech_galactic_stock_exchange)).shortName(GameData.activity.getString(R.string.tech_galactic_stock_exchange_short)).category(techCategory4).level(4).description(GameData.activity.getString(R.string.tech_galactic_stock_exchange_description, new Object[]{10}));
        GameIconEnum gameIconEnum8 = GameIconEnum.MONEY_BUILDING;
        map13.put(techID13, description8.iconIndex(gameIconEnum8.ordinal()).build());
        Map<TechID, Tech> map14 = this.technologies;
        TechID techID14 = TechID.POINT_DEFENSE_SYSTEM;
        map14.put(techID14, new Tech.Builder(this.techLink).id(techID14).type(techType).name(GameData.activity.getString(R.string.tech_point_defense_system)).shortName(GameData.activity.getString(R.string.tech_point_defense_system_short)).category(techCategory4).level(5).description(GameData.activity.getString(R.string.tech_point_defense_system_description, new Object[]{25})).iconIndex(gameIconEnum4.ordinal()).build());
        Map<TechID, Tech> map15 = this.technologies;
        TechID techID15 = TechID.GALACTIC_INTERNET;
        Tech.Builder description9 = new Tech.Builder(this.techLink).id(techID15).type(techType).name(GameData.activity.getString(R.string.tech_galactic_internet)).shortName(GameData.activity.getString(R.string.tech_galactic_internet_short)).category(techCategory4).level(4).description(GameData.activity.getString(R.string.tech_galactic_internet_description, new Object[]{10}));
        GameIconEnum gameIconEnum9 = GameIconEnum.HAPPINESS_BUILDING;
        map15.put(techID15, description9.iconIndex(gameIconEnum9.ordinal()).build());
        Map<TechID, Tech> map16 = this.technologies;
        TechID techID16 = TechID.VIRTUAL_REALITY_SYSTEM;
        map16.put(techID16, new Tech.Builder(this.techLink).id(techID16).type(techType).name(GameData.activity.getString(R.string.tech_vr_system)).shortName(GameData.activity.getString(R.string.tech_vr_system_short)).category(techCategory4).level(5).description(GameData.activity.getString(R.string.tech_vr_system_description, new Object[]{10})).iconIndex(gameIconEnum9.ordinal()).build());
        Map<TechID, Tech> map17 = this.technologies;
        TechID techID17 = TechID.SPACE_ELEVATOR;
        map17.put(techID17, new Tech.Builder(this.techLink).id(techID17).type(techType).name(GameData.activity.getString(R.string.tech_space_elevator)).shortName(GameData.activity.getString(R.string.tech_space_elevator_short)).category(techCategory4).level(6).description(GameData.activity.getString(R.string.tech_space_elevator_description, new Object[]{10})).iconIndex(gameIconEnum8.ordinal()).build());
        Map<TechID, Tech> map18 = this.technologies;
        TechID techID18 = TechID.QUANTUM_SUPER_COMPUTER;
        map18.put(techID18, new Tech.Builder(this.techLink).id(techID18).type(techType).name(GameData.activity.getString(R.string.tech_quantum_computer)).shortName(GameData.activity.getString(R.string.tech_quantum_computer_short)).category(techCategory2).level(3).description(GameData.activity.getString(R.string.tech_quantum_computer_description, new Object[]{10, 1})).iconIndex(gameIconEnum5.ordinal()).build());
        Map<TechID, Tech> map19 = this.technologies;
        TechID techID19 = TechID.FOOD_REPLICATORS;
        map19.put(techID19, new Tech.Builder(this.techLink).id(techID19).type(techType).name(GameData.activity.getString(R.string.tech_food_replicators)).shortName(GameData.activity.getString(R.string.tech_food_replicators_short)).category(techCategory2).level(7).description(GameData.activity.getString(R.string.tech_food_replicators_description, new Object[]{50})).iconIndex(gameIconEnum.ordinal()).build());
        Map<TechID, Tech> map20 = this.technologies;
        TechID techID20 = TechID.HOLOGRAPHIC_CENTER;
        map20.put(techID20, new Tech.Builder(this.techLink).id(techID20).type(techType).name(GameData.activity.getString(R.string.tech_holographic_center)).shortName(GameData.activity.getString(R.string.tech_holographic_center_short)).category(techCategory3).level(8).description(GameData.activity.getString(R.string.tech_holographic_center_description, new Object[]{10})).iconIndex(gameIconEnum9.ordinal()).build());
        Map<TechID, Tech> map21 = this.technologies;
        TechID techID21 = TechID.STAR_BASE;
        map21.put(techID21, new Tech.Builder(this.techLink).id(techID21).type(techType).name(GameData.activity.getString(R.string.tech_star_base)).shortName(GameData.activity.getString(R.string.tech_star_base_short)).category(techCategory4).level(1).description(GameData.activity.getString(R.string.tech_star_base_description)).iconIndex(GameIconEnum.STAR_BASE.ordinal()).build());
        Map<TechID, Tech> map22 = this.technologies;
        TechID techID22 = TechID.NANO_FABRICATION_PLANT;
        map22.put(techID22, new Tech.Builder(this.techLink).id(techID22).type(techType).name(GameData.activity.getString(R.string.tech_nano_fabrication)).shortName(GameData.activity.getString(R.string.tech_nano_fabrication_short)).category(techCategory4).level(7).description(GameData.activity.getString(R.string.tech_nano_fabrication_description, new Object[]{1, 20})).iconIndex(gameIconEnum6.ordinal()).build());
    }

    private void setCommunicationTech() {
        Map<TechID, Tech> map = this.technologies;
        TechID techID = TechID.SUBSPACE_COMMUNICATION;
        Tech.Builder id = new Tech.Builder(this.techLink).id(techID);
        TechType techType = TechType.SHIP_COMMUNICATION;
        Tech.Builder shortName = id.type(techType).name(GameData.activity.getString(R.string.tech_subspace_communication)).shortName(GameData.activity.getString(R.string.tech_subspace_communication_short));
        TechCategory techCategory = TechCategory.PHYSICS;
        Tech.Builder value = shortName.category(techCategory).level(1).description(GameData.activity.getString(R.string.tech_subspace_communication_description, new Object[]{4})).value(4);
        GameIconEnum gameIconEnum = GameIconEnum.SCANNING;
        map.put(techID, value.iconIndex(gameIconEnum.ordinal()).build());
        Map<TechID, Tech> map2 = this.technologies;
        TechID techID2 = TechID.HYPERSPACE_COMMUNICATION;
        map2.put(techID2, new Tech.Builder(this.techLink).id(techID2).type(techType).name(GameData.activity.getString(R.string.tech_hyperspace_communication)).shortName(GameData.activity.getString(R.string.tech_hyperspace_communication_short)).category(techCategory).level(3).description(GameData.activity.getString(R.string.tech_hyperspace_communication_description, new Object[]{8})).value(8).iconIndex(gameIconEnum.ordinal()).build());
        Map<TechID, Tech> map3 = this.technologies;
        TechID techID3 = TechID.PHASED_COMMUNICATION;
        map3.put(techID3, new Tech.Builder(this.techLink).id(techID3).type(techType).name(GameData.activity.getString(R.string.tech_phased_communication)).shortName(GameData.activity.getString(R.string.tech_phased_communication_short)).category(techCategory).level(5).description(GameData.activity.getString(R.string.tech_phased_communication_description, new Object[]{16})).value(16).iconIndex(gameIconEnum.ordinal()).build());
        Map<TechID, Tech> map4 = this.technologies;
        TechID techID4 = TechID.QUANTUM_COMMUNICATION;
        map4.put(techID4, new Tech.Builder(this.techLink).id(techID4).type(techType).name(GameData.activity.getString(R.string.tech_quantum_communication)).shortName(GameData.activity.getString(R.string.tech_quantum_communication_short)).category(techCategory).level(7).description(GameData.activity.getString(R.string.tech_quantum_communication_description)).value(1000).iconIndex(gameIconEnum.ordinal()).build());
    }

    private void setFasterThenLightTech() {
        Map<TechID, Tech> map = this.technologies;
        TechID techID = TechID.WARP_DRIVE;
        Tech.Builder id = new Tech.Builder(this.techLink).id(techID);
        TechType techType = TechType.FASTER_THEN_LIGHT_DRIVE;
        Tech.Builder shortName = id.type(techType).name(GameData.activity.getString(R.string.tech_warp_drive)).shortName(GameData.activity.getString(R.string.tech_warp_drive_short));
        TechCategory techCategory = TechCategory.ENERGY;
        Tech.Builder value = shortName.category(techCategory).level(1).description(GameData.activity.getString(R.string.tech_warp_drive_description, new Object[]{4})).value(4);
        GameIconEnum gameIconEnum = GameIconEnum.SHIPS;
        map.put(techID, value.iconIndex(gameIconEnum.ordinal()).build());
        Map<TechID, Tech> map2 = this.technologies;
        TechID techID2 = TechID.TRANSWARP_DRIVE;
        map2.put(techID2, new Tech.Builder(this.techLink).id(techID2).type(techType).name(GameData.activity.getString(R.string.tech_transwarp_drive)).shortName(GameData.activity.getString(R.string.tech_transwarp_drive_short)).category(techCategory).level(2).description(GameData.activity.getString(R.string.tech_transwarp_description, new Object[]{5})).value(5).iconIndex(gameIconEnum.ordinal()).build());
        Map<TechID, Tech> map3 = this.technologies;
        TechID techID3 = TechID.SLIPSTREAM_DRIVE;
        map3.put(techID3, new Tech.Builder(this.techLink).id(techID3).type(techType).name(GameData.activity.getString(R.string.tech_slipstream_drive)).shortName(GameData.activity.getString(R.string.tech_slipstream_drive_short)).category(techCategory).level(4).description(GameData.activity.getString(R.string.tech_slipstream_drive_description, new Object[]{6})).value(6).iconIndex(gameIconEnum.ordinal()).build());
        Map<TechID, Tech> map4 = this.technologies;
        TechID techID4 = TechID.HYPER_DRIVE;
        map4.put(techID4, new Tech.Builder(this.techLink).id(techID4).type(techType).name(GameData.activity.getString(R.string.tech_hyper_drive)).shortName(GameData.activity.getString(R.string.tech_hyper_drive_short)).category(techCategory).level(6).description(GameData.activity.getString(R.string.tech_hyper_drive_description, new Object[]{7})).value(7).iconIndex(gameIconEnum.ordinal()).build());
        Map<TechID, Tech> map5 = this.technologies;
        TechID techID5 = TechID.FOLD_DRIVE;
        map5.put(techID5, new Tech.Builder(this.techLink).id(techID5).type(techType).name(GameData.activity.getString(R.string.tech_fold_drive)).shortName(GameData.activity.getString(R.string.tech_fold_drive_short)).category(techCategory).level(8).description(GameData.activity.getString(R.string.tech_fold_drive_description, new Object[]{8})).value(8).iconIndex(gameIconEnum.ordinal()).build());
    }

    private void setFuelTech() {
        Map<TechID, Tech> map = this.technologies;
        TechID techID = TechID.FUSION_REACTOR;
        Tech.Builder id = new Tech.Builder(this.techLink).id(techID);
        TechType techType = TechType.POWER_CORE;
        Tech.Builder shortName = id.type(techType).name(GameData.activity.getString(R.string.tech_fusion_reactor)).shortName(GameData.activity.getString(R.string.tech_fusion_reactor_short));
        TechCategory techCategory = TechCategory.ENERGY;
        Tech.Builder value = shortName.category(techCategory).level(1).description(GameData.activity.getString(R.string.tech_fusion_reactor_description, new Object[]{15})).value(15);
        GameIconEnum gameIconEnum = GameIconEnum.FUEL;
        map.put(techID, value.iconIndex(gameIconEnum.ordinal()).build());
        Map<TechID, Tech> map2 = this.technologies;
        TechID techID2 = TechID.QUANTUM_GENERATOR;
        map2.put(techID2, new Tech.Builder(this.techLink).id(techID2).type(techType).name(GameData.activity.getString(R.string.tech_quantum_generator)).shortName(GameData.activity.getString(R.string.tech_quantum_generator_short)).category(techCategory).level(3).description(GameData.activity.getString(R.string.tech_quantum_generator_description, new Object[]{20})).value(20).iconIndex(gameIconEnum.ordinal()).build());
        Map<TechID, Tech> map3 = this.technologies;
        TechID techID3 = TechID.ANTIMATTER_REACTOR;
        map3.put(techID3, new Tech.Builder(this.techLink).id(techID3).type(techType).name(GameData.activity.getString(R.string.tech_antimatter_reactor)).shortName(GameData.activity.getString(R.string.tech_antimatter_reactor_short)).category(techCategory).level(5).description(GameData.activity.getString(R.string.tech_antimatter_reactor_description, new Object[]{30})).value(30).iconIndex(gameIconEnum.ordinal()).build());
        Map<TechID, Tech> map4 = this.technologies;
        TechID techID4 = TechID.ZERO_POINT_ENERGY;
        map4.put(techID4, new Tech.Builder(this.techLink).id(techID4).type(techType).name(GameData.activity.getString(R.string.tech_zero_point_energy)).shortName(GameData.activity.getString(R.string.tech_zero_point_energy_short)).category(techCategory).level(7).description(GameData.activity.getString(R.string.tech_zero_point_energy_description)).value(1000).iconIndex(gameIconEnum.ordinal()).build());
    }

    private void setOther() {
        Map<TechID, Tech> map = this.technologies;
        TechID techID = TechID.NONE;
        Tech.Builder id = new Tech.Builder(this.techLink).id(techID);
        TechType techType = TechType.NONE;
        Tech.Builder name = id.type(techType).name(GameData.activity.getString(R.string.tech_none));
        TechCategory techCategory = TechCategory.NONE;
        map.put(techID, name.category(techCategory).level(0).description(GameData.activity.getString(R.string.tech_none_description)).build());
        Map<TechID, Tech> map2 = this.technologies;
        TechID techID2 = TechID.MINIATURIZATION;
        map2.put(techID2, new Tech.Builder(this.techLink).id(techID2).type(techType).name(GameData.activity.getString(R.string.tech_miniaturization)).category(techCategory).level(0).description(GameData.activity.getString(R.string.tech_miniaturization_description)).build());
    }

    private void setPerks() {
        Map<TechID, Tech> map = this.technologies;
        TechID techID = TechID.GENETICALLY_ENGINEERED_SUPER_FOOD;
        Tech.Builder id = new Tech.Builder(this.techLink).id(techID);
        TechType techType = TechType.PERK;
        Tech.Builder shortName = id.type(techType).name(GameData.activity.getString(R.string.tech_genetically_engineered_super_food)).shortName(GameData.activity.getString(R.string.tech_genetically_engineered_super_food_short));
        TechCategory techCategory = TechCategory.CHEMISTRY;
        map.put(techID, shortName.category(techCategory).level(5).description(GameData.activity.getString(R.string.tech_genetically_engineered_super_food_description)).iconIndex(16).build());
        Map<TechID, Tech> map2 = this.technologies;
        TechID techID2 = TechID.ADVANCED_CHEMICAL_MINING;
        map2.put(techID2, new Tech.Builder(this.techLink).id(techID2).type(techType).name(GameData.activity.getString(R.string.tech_advanced_chemical_mining)).shortName(GameData.activity.getString(R.string.tech_advanced_chemical_mining_short)).category(techCategory).level(7).description(GameData.activity.getString(R.string.tech_advanced_chemical_mining_description)).iconIndex(17).build());
        Map<TechID, Tech> map3 = this.technologies;
        TechID techID3 = TechID.HEIGHTENED_INTELLIGENCE;
        map3.put(techID3, new Tech.Builder(this.techLink).id(techID3).type(techType).name(GameData.activity.getString(R.string.tech_heightened_intelligence)).shortName(GameData.activity.getString(R.string.tech_heightened_intelligence_short)).category(techCategory).level(6).description(GameData.activity.getString(R.string.tech_heightened_intelligence_description)).iconIndex(18).build());
    }

    private void setPlanetEnhancementTech() {
        Map<TechID, Tech> map = this.technologies;
        TechID techID = TechID.SOIL_ENRICHMENT;
        Tech.Builder shortName = new Tech.Builder(this.techLink).id(techID).type(TechType.BUILDING).name(GameData.activity.getString(R.string.tech_soil_enrichment)).shortName(GameData.activity.getString(R.string.tech_soil_enrichment_short));
        TechCategory techCategory = TechCategory.CHEMISTRY;
        Tech.Builder description = shortName.category(techCategory).level(3).description(GameData.activity.getString(R.string.tech_soil_enrichment_description));
        GameIconEnum gameIconEnum = GameIconEnum.TERRAFORMING;
        map.put(techID, description.iconIndex(gameIconEnum.ordinal()).build());
        Map<TechID, Tech> map2 = this.technologies;
        TechID techID2 = TechID.TERRAFORMING;
        map2.put(techID2, new Tech.Builder(this.techLink).id(techID2).type(TechType.PLANET_ENHANCEMENT).name(GameData.activity.getString(R.string.tech_terraforming)).shortName(GameData.activity.getString(R.string.tech_terraforming_short)).category(techCategory).level(4).description(GameData.activity.getString(R.string.tech_terraforming_description)).iconIndex(gameIconEnum.ordinal()).build());
    }

    private void setScanningTech() {
        Map<TechID, Tech> map = this.technologies;
        TechID techID = TechID.TACHYON_SCANNER;
        map.put(techID, new Tech.Builder(this.techLink).id(techID).type(TechType.COLONY_SCANNER).name(GameData.activity.getString(R.string.tech_tachyon_scanning)).shortName(GameData.activity.getString(R.string.tech_tachyon_scanning_short)).category(TechCategory.PHYSICS).level(2).description(GameData.activity.getString(R.string.tech_tachyon_scanning_description, new Object[]{10})).value(10).iconIndex(GameIconEnum.SCANNING.ordinal()).build());
    }

    private void setShieldTech() {
        Map<TechID, Tech> map = this.technologies;
        TechID techID = TechID.FORCE_SHIELDS;
        Tech.Builder id = new Tech.Builder(this.techLink).id(techID);
        TechType techType = TechType.SHIP_SHIELD;
        Tech.Builder shortName = id.type(techType).name(GameData.activity.getString(R.string.tech_force_shields)).shortName(GameData.activity.getString(R.string.tech_force_shields_short));
        TechCategory techCategory = TechCategory.ENERGY;
        map.put(techID, shortName.category(techCategory).level(2).description(GameData.activity.getString(R.string.tech_force_shields_description, new Object[]{10, 25, 1, 20})).iconIndex(ComponentIconEnum.FORCE_SHIELDS.ordinal()).build());
        Map<TechID, Tech> map2 = this.technologies;
        TechID techID2 = TechID.DEFLECTOR_SHIELDS;
        map2.put(techID2, new Tech.Builder(this.techLink).id(techID2).type(techType).name(GameData.activity.getString(R.string.tech_deflector_shields)).shortName(GameData.activity.getString(R.string.tech_deflector_shields_short)).category(techCategory).level(3).description(GameData.activity.getString(R.string.tech_deflector_shields_description, new Object[]{30, 75, 3, 25})).iconIndex(ComponentIconEnum.DEFLECTOR_SHIELDS.ordinal()).build());
        Map<TechID, Tech> map3 = this.technologies;
        TechID techID3 = TechID.PHASED_SHIELDS;
        map3.put(techID3, new Tech.Builder(this.techLink).id(techID3).type(techType).name(GameData.activity.getString(R.string.tech_phased_shields)).shortName(GameData.activity.getString(R.string.tech_phased_shields_short)).category(techCategory).level(6).description(GameData.activity.getString(R.string.tech_phased_shields_description, new Object[]{50, 125, 5, 30})).iconIndex(ComponentIconEnum.PHASED_SHIELDS.ordinal()).build());
        Map<TechID, Tech> map4 = this.technologies;
        TechID techID4 = TechID.MULTIPHASIC_SHIELDS;
        map4.put(techID4, new Tech.Builder(this.techLink).id(techID4).type(techType).name(GameData.activity.getString(R.string.tech_multiphasic_shields)).shortName(GameData.activity.getString(R.string.tech_multiphasic_shields_short)).category(techCategory).level(8).description(GameData.activity.getString(R.string.tech_multiphasic_shields_description, new Object[]{70, 175, 7, 30})).iconIndex(ComponentIconEnum.MULTIPHASIC_SHIELDS.ordinal()).build());
        Map<TechID, Tech> map5 = this.technologies;
        TechID techID5 = TechID.SHIELD_CAPACITOR;
        map5.put(techID5, new Tech.Builder(this.techLink).id(techID5).type(TechType.SHIP_SPECIAL_COMPONENT).name(GameData.activity.getString(R.string.tech_shield_capacitor)).shortName(GameData.activity.getString(R.string.tech_shield_capacitor_short)).category(techCategory).level(4).description(GameData.activity.getString(R.string.tech_shield_capacitor_description, new Object[]{10})).iconIndex(ComponentIconEnum.SHIELD_CAPACITOR.ordinal()).build());
    }

    private void setSpecialComponentTech() {
        Map<TechID, Tech> map = this.technologies;
        TechID techID = TechID.EXTENDED_HULL;
        map.put(techID, new Tech.Builder(this.techLink).id(techID).type(TechType.SHIP_SPECIAL_COMPONENT).name(GameData.activity.getString(R.string.tech_extended_hull)).shortName(GameData.activity.getString(R.string.tech_extended_hull_short)).category(TechCategory.ENGINEERING).level(3).description(GameData.activity.getString(R.string.tech_extended_hull_description)).iconIndex(ComponentIconEnum.EXTENDED_HULL.ordinal()).build());
    }

    private void setSublightEngineTech() {
        Map<TechID, Tech> map = this.technologies;
        TechID techID = TechID.ION_ENGINES;
        Tech.Builder id = new Tech.Builder(this.techLink).id(techID);
        TechType techType = TechType.SHIP_SUBLIGHT_ENGINE;
        Tech.Builder shortName = id.type(techType).name(GameData.activity.getString(R.string.tech_ion_engines)).shortName(GameData.activity.getString(R.string.tech_ion_engines_short));
        TechCategory techCategory = TechCategory.PHYSICS;
        map.put(techID, shortName.category(techCategory).level(1).description(GameData.activity.getString(R.string.tech_ion_engines_description, new Object[]{3})).iconIndex(ComponentIconEnum.ION_ENGINES.ordinal()).build());
        Map<TechID, Tech> map2 = this.technologies;
        TechID techID2 = TechID.IMPULSE_ENGINES;
        map2.put(techID2, new Tech.Builder(this.techLink).id(techID2).type(techType).name(GameData.activity.getString(R.string.tech_impulse_engines)).shortName(GameData.activity.getString(R.string.tech_impulse_engines_short)).category(techCategory).level(3).description(GameData.activity.getString(R.string.tech_impulse_engines_description, new Object[]{4})).iconIndex(ComponentIconEnum.IMPULSE_ENGINES.ordinal()).build());
        Map<TechID, Tech> map3 = this.technologies;
        TechID techID3 = TechID.PHASED_ENGINES;
        map3.put(techID3, new Tech.Builder(this.techLink).id(techID3).type(techType).name(GameData.activity.getString(R.string.tech_phased_engines)).shortName(GameData.activity.getString(R.string.tech_phased_engines_short)).category(techCategory).level(4).description(GameData.activity.getString(R.string.tech_phased_engines_description, new Object[]{5})).iconIndex(ComponentIconEnum.PHASED_ENGINES.ordinal()).build());
        Map<TechID, Tech> map4 = this.technologies;
        TechID techID4 = TechID.DIMENSIONAL_ENGINES;
        map4.put(techID4, new Tech.Builder(this.techLink).id(techID4).type(techType).name(GameData.activity.getString(R.string.tech_dimensional_engines)).shortName(GameData.activity.getString(R.string.tech_dimensional_engines_short)).category(techCategory).level(6).description(GameData.activity.getString(R.string.tech_dimensional_engines_description, new Object[]{6})).iconIndex(ComponentIconEnum.DIMENSIONAL_ENGINES.ordinal()).build());
    }

    private void setTargetingTech() {
        Map<TechID, Tech> map = this.technologies;
        TechID techID = TechID.ADAPTIVE_OPTICS_TARGETING;
        Tech.Builder id = new Tech.Builder(this.techLink).id(techID);
        TechType techType = TechType.SHIP_TARGETING_COMPUTER;
        Tech.Builder shortName = id.type(techType).name(GameData.activity.getString(R.string.tech_adaptive_optical_targeting)).shortName(GameData.activity.getString(R.string.tech_adaptive_optical_targeting_short));
        TechCategory techCategory = TechCategory.ENGINEERING;
        map.put(techID, shortName.category(techCategory).level(1).description(GameData.activity.getString(R.string.tech_adaptive_optical_targeting_description, new Object[]{45, 5})).iconIndex(ComponentIconEnum.ADAPTIVE_OPTICS_TARGETING.ordinal()).build());
        Map<TechID, Tech> map2 = this.technologies;
        TechID techID2 = TechID.QUANTUM_TARGETING;
        map2.put(techID2, new Tech.Builder(this.techLink).id(techID2).type(techType).name(GameData.activity.getString(R.string.tech_quantum_targeting)).shortName(GameData.activity.getString(R.string.tech_quantum_targeting_short)).category(techCategory).level(2).description(GameData.activity.getString(R.string.tech_quantum_targeting_description, new Object[]{65, 8})).iconIndex(ComponentIconEnum.QUANTUM_TARGETING.ordinal()).build());
        Map<TechID, Tech> map3 = this.technologies;
        TechID techID3 = TechID.PHASED_TARGETING;
        map3.put(techID3, new Tech.Builder(this.techLink).id(techID3).type(techType).name(GameData.activity.getString(R.string.tech_phased_targeting)).shortName(GameData.activity.getString(R.string.tech_phased_targeting_short)).category(techCategory).level(4).description(GameData.activity.getString(R.string.tech_phased_targeting_description, new Object[]{85, 12})).iconIndex(ComponentIconEnum.PHASED_TARGETING.ordinal()).build());
        Map<TechID, Tech> map4 = this.technologies;
        TechID techID4 = TechID.MULTI_PHASED_TARGETING;
        map4.put(techID4, new Tech.Builder(this.techLink).id(techID4).type(techType).name(GameData.activity.getString(R.string.tech_multi_phased_targeting)).shortName(GameData.activity.getString(R.string.tech_multi_phased_targeting_short)).category(techCategory).level(6).description(GameData.activity.getString(R.string.tech_multi_phased_targeting_description, new Object[]{115, 16})).iconIndex(ComponentIconEnum.MULTI_PHASED_TARGETING.ordinal()).build());
    }

    private void setTechLevels() {
        Map<TechID, Tech> map = this.technologies;
        TechID techID = TechID.BLANK;
        Tech.Builder id = new Tech.Builder(this.techLink).id(techID);
        TechType techType = TechType.NONE;
        Tech.Builder name = id.type(techType).name("");
        TechCategory techCategory = TechCategory.ENGINEERING;
        map.put(techID, name.category(techCategory).level(1).build());
        Map<TechID, Tech> map2 = this.technologies;
        TechID techID2 = TechID.ENGINEERING1;
        map2.put(techID2, new Tech.Builder(this.techLink).id(techID2).type(techType).name(GameData.activity.getString(R.string.tech_level_1)).category(techCategory).level(1).build());
        Map<TechID, Tech> map3 = this.technologies;
        TechID techID3 = TechID.ENGINEERING2;
        map3.put(techID3, new Tech.Builder(this.techLink).id(techID3).type(techType).name(GameData.activity.getString(R.string.tech_level_2)).category(techCategory).level(2).build());
        Map<TechID, Tech> map4 = this.technologies;
        TechID techID4 = TechID.ENGINEERING3;
        map4.put(techID4, new Tech.Builder(this.techLink).id(techID4).type(techType).name(GameData.activity.getString(R.string.tech_level_3)).category(techCategory).level(3).build());
        Map<TechID, Tech> map5 = this.technologies;
        TechID techID5 = TechID.ENGINEERING4;
        map5.put(techID5, new Tech.Builder(this.techLink).id(techID5).type(techType).name(GameData.activity.getString(R.string.tech_level_4)).category(techCategory).level(4).build());
        Map<TechID, Tech> map6 = this.technologies;
        TechID techID6 = TechID.ENGINEERING5;
        map6.put(techID6, new Tech.Builder(this.techLink).id(techID6).type(techType).name(GameData.activity.getString(R.string.tech_level_5)).category(techCategory).level(5).build());
        Map<TechID, Tech> map7 = this.technologies;
        TechID techID7 = TechID.ENGINEERING6;
        map7.put(techID7, new Tech.Builder(this.techLink).id(techID7).type(techType).name(GameData.activity.getString(R.string.tech_level_6)).category(techCategory).level(6).build());
        Map<TechID, Tech> map8 = this.technologies;
        TechID techID8 = TechID.ENGINEERING7;
        map8.put(techID8, new Tech.Builder(this.techLink).id(techID8).type(techType).name(GameData.activity.getString(R.string.tech_level_7)).category(techCategory).level(7).build());
        Map<TechID, Tech> map9 = this.technologies;
        TechID techID9 = TechID.PHYSICS1;
        Tech.Builder name2 = new Tech.Builder(this.techLink).id(techID9).type(techType).name(GameData.activity.getString(R.string.tech_level_1));
        TechCategory techCategory2 = TechCategory.PHYSICS;
        map9.put(techID9, name2.category(techCategory2).level(1).build());
        Map<TechID, Tech> map10 = this.technologies;
        TechID techID10 = TechID.PHYSICS2;
        map10.put(techID10, new Tech.Builder(this.techLink).id(techID10).type(techType).name(GameData.activity.getString(R.string.tech_level_2)).category(techCategory2).level(2).build());
        Map<TechID, Tech> map11 = this.technologies;
        TechID techID11 = TechID.PHYSICS3;
        map11.put(techID11, new Tech.Builder(this.techLink).id(techID11).type(techType).name(GameData.activity.getString(R.string.tech_level_3)).category(techCategory2).level(3).build());
        Map<TechID, Tech> map12 = this.technologies;
        TechID techID12 = TechID.PHYSICS4;
        map12.put(techID12, new Tech.Builder(this.techLink).id(techID12).type(techType).name(GameData.activity.getString(R.string.tech_level_4)).category(techCategory2).level(4).build());
        Map<TechID, Tech> map13 = this.technologies;
        TechID techID13 = TechID.PHYSICS5;
        map13.put(techID13, new Tech.Builder(this.techLink).id(techID13).type(techType).name(GameData.activity.getString(R.string.tech_level_5)).category(techCategory2).level(5).build());
        Map<TechID, Tech> map14 = this.technologies;
        TechID techID14 = TechID.PHYSICS6;
        map14.put(techID14, new Tech.Builder(this.techLink).id(techID14).type(techType).name(GameData.activity.getString(R.string.tech_level_6)).category(techCategory2).level(6).build());
        Map<TechID, Tech> map15 = this.technologies;
        TechID techID15 = TechID.PHYSICS7;
        map15.put(techID15, new Tech.Builder(this.techLink).id(techID15).type(techType).name(GameData.activity.getString(R.string.tech_level_7)).category(techCategory2).level(7).build());
        Map<TechID, Tech> map16 = this.technologies;
        TechID techID16 = TechID.CHEMISTRY1;
        Tech.Builder name3 = new Tech.Builder(this.techLink).id(techID16).type(techType).name(GameData.activity.getString(R.string.tech_level_1));
        TechCategory techCategory3 = TechCategory.CHEMISTRY;
        map16.put(techID16, name3.category(techCategory3).level(1).build());
        Map<TechID, Tech> map17 = this.technologies;
        TechID techID17 = TechID.CHEMISTRY2;
        map17.put(techID17, new Tech.Builder(this.techLink).id(techID17).type(techType).name(GameData.activity.getString(R.string.tech_level_2)).category(techCategory3).level(2).build());
        Map<TechID, Tech> map18 = this.technologies;
        TechID techID18 = TechID.CHEMISTRY3;
        map18.put(techID18, new Tech.Builder(this.techLink).id(techID18).type(techType).name(GameData.activity.getString(R.string.tech_level_3)).category(techCategory3).level(3).build());
        Map<TechID, Tech> map19 = this.technologies;
        TechID techID19 = TechID.CHEMISTRY4;
        map19.put(techID19, new Tech.Builder(this.techLink).id(techID19).type(techType).name(GameData.activity.getString(R.string.tech_level_4)).category(techCategory3).level(4).build());
        Map<TechID, Tech> map20 = this.technologies;
        TechID techID20 = TechID.CHEMISTRY5;
        map20.put(techID20, new Tech.Builder(this.techLink).id(techID20).type(techType).name(GameData.activity.getString(R.string.tech_level_5)).category(techCategory3).level(5).build());
        Map<TechID, Tech> map21 = this.technologies;
        TechID techID21 = TechID.CHEMISTRY6;
        map21.put(techID21, new Tech.Builder(this.techLink).id(techID21).type(techType).name(GameData.activity.getString(R.string.tech_level_6)).category(techCategory3).level(6).build());
        Map<TechID, Tech> map22 = this.technologies;
        TechID techID22 = TechID.CHEMISTRY7;
        map22.put(techID22, new Tech.Builder(this.techLink).id(techID22).type(techType).name(GameData.activity.getString(R.string.tech_level_7)).category(techCategory3).level(7).build());
        Map<TechID, Tech> map23 = this.technologies;
        TechID techID23 = TechID.CHEMISTRY8;
        map23.put(techID23, new Tech.Builder(this.techLink).id(techID23).type(techType).name(GameData.activity.getString(R.string.tech_level_8)).category(techCategory3).level(8).build());
        Map<TechID, Tech> map24 = this.technologies;
        TechID techID24 = TechID.ENERGY1;
        Tech.Builder name4 = new Tech.Builder(this.techLink).id(techID24).type(techType).name(GameData.activity.getString(R.string.tech_level_1));
        TechCategory techCategory4 = TechCategory.ENERGY;
        map24.put(techID24, name4.category(techCategory4).level(1).build());
        Map<TechID, Tech> map25 = this.technologies;
        TechID techID25 = TechID.ENERGY2;
        map25.put(techID25, new Tech.Builder(this.techLink).id(techID25).type(techType).name(GameData.activity.getString(R.string.tech_level_2)).category(techCategory4).level(2).build());
        Map<TechID, Tech> map26 = this.technologies;
        TechID techID26 = TechID.ENERGY3;
        map26.put(techID26, new Tech.Builder(this.techLink).id(techID26).type(techType).name(GameData.activity.getString(R.string.tech_level_3)).category(techCategory4).level(3).build());
        Map<TechID, Tech> map27 = this.technologies;
        TechID techID27 = TechID.ENERGY4;
        map27.put(techID27, new Tech.Builder(this.techLink).id(techID27).type(techType).name(GameData.activity.getString(R.string.tech_level_4)).category(techCategory4).level(4).build());
        Map<TechID, Tech> map28 = this.technologies;
        TechID techID28 = TechID.ENERGY5;
        map28.put(techID28, new Tech.Builder(this.techLink).id(techID28).type(techType).name(GameData.activity.getString(R.string.tech_level_5)).category(techCategory4).level(5).build());
        Map<TechID, Tech> map29 = this.technologies;
        TechID techID29 = TechID.ENERGY6;
        map29.put(techID29, new Tech.Builder(this.techLink).id(techID29).type(techType).name(GameData.activity.getString(R.string.tech_level_6)).category(techCategory4).level(6).build());
        Map<TechID, Tech> map30 = this.technologies;
        TechID techID30 = TechID.ENERGY7;
        map30.put(techID30, new Tech.Builder(this.techLink).id(techID30).type(techType).name(GameData.activity.getString(R.string.tech_level_7)).category(techCategory4).level(7).build());
        Map<TechID, Tech> map31 = this.technologies;
        TechID techID31 = TechID.ENERGY8;
        map31.put(techID31, new Tech.Builder(this.techLink).id(techID31).type(techType).name(GameData.activity.getString(R.string.tech_level_8)).category(techCategory4).level(8).build());
    }

    private void setTroopTech() {
        Map<TechID, Tech> map = this.technologies;
        TechID techID = TechID.LASER_RIFLE;
        Tech.Builder id = new Tech.Builder(this.techLink).id(techID);
        TechType techType = TechType.TROOP_IMPROVEMENT;
        Tech.Builder shortName = id.type(techType).name(GameData.activity.getString(R.string.tech_laser_rifle)).shortName(GameData.activity.getString(R.string.tech_laser_rifle_short));
        TechCategory techCategory = TechCategory.PHYSICS;
        map.put(techID, shortName.category(techCategory).level(1).description(GameData.activity.getString(R.string.tech_laser_rifle_description, new Object[]{10})).value(10).iconIndex(0).build());
        Map<TechID, Tech> map2 = this.technologies;
        TechID techID2 = TechID.PULSE_RIFLE;
        map2.put(techID2, new Tech.Builder(this.techLink).id(techID2).type(techType).name(GameData.activity.getString(R.string.tech_pulse_rifle)).shortName(GameData.activity.getString(R.string.tech_pulse_rifle_short)).category(techCategory).level(2).description(GameData.activity.getString(R.string.tech_pulse_rifle_description, new Object[]{15})).value(15).iconIndex(0).build());
        Map<TechID, Tech> map3 = this.technologies;
        TechID techID3 = TechID.PHASOR_RIFLE;
        map3.put(techID3, new Tech.Builder(this.techLink).id(techID3).type(techType).name(GameData.activity.getString(R.string.tech_phasor_rifle)).shortName(GameData.activity.getString(R.string.tech_phasor_rifle_short)).category(techCategory).level(5).description(GameData.activity.getString(R.string.tech_phasor_rifle_description, new Object[]{20})).value(20).iconIndex(0).build());
        Map<TechID, Tech> map4 = this.technologies;
        TechID techID4 = TechID.DISRUPTOR_RIFLE;
        map4.put(techID4, new Tech.Builder(this.techLink).id(techID4).type(techType).name(GameData.activity.getString(R.string.tech_disruptor_rifle)).shortName(GameData.activity.getString(R.string.tech_disruptor_rifle_short)).category(techCategory).level(7).description(GameData.activity.getString(R.string.tech_disruptor_rifle_description, new Object[]{25})).value(25).iconIndex(0).build());
        Map<TechID, Tech> map5 = this.technologies;
        TechID techID5 = TechID.POWERED_ARMOR;
        Tech.Builder shortName2 = new Tech.Builder(this.techLink).id(techID5).type(techType).name(GameData.activity.getString(R.string.tech_powered_armor)).shortName(GameData.activity.getString(R.string.tech_powered_armor_short));
        TechCategory techCategory2 = TechCategory.ENGINEERING;
        map5.put(techID5, shortName2.category(techCategory2).level(3).description(GameData.activity.getString(R.string.tech_powered_armor_description, new Object[]{5})).value(5).iconIndex(0).build());
        Map<TechID, Tech> map6 = this.technologies;
        TechID techID6 = TechID.PERSONAL_SHIELD;
        map6.put(techID6, new Tech.Builder(this.techLink).id(techID6).type(techType).name(GameData.activity.getString(R.string.tech_personal_shield)).shortName(GameData.activity.getString(R.string.tech_personal_shield_short)).category(TechCategory.ENERGY).level(4).description(GameData.activity.getString(R.string.tech_personal_shield_description, new Object[]{5})).value(5).iconIndex(0).build());
        Map<TechID, Tech> map7 = this.technologies;
        TechID techID7 = TechID.NANO_SCALE_ARMOR;
        map7.put(techID7, new Tech.Builder(this.techLink).id(techID7).type(techType).name(GameData.activity.getString(R.string.tech_nano_scale_armor)).shortName(GameData.activity.getString(R.string.tech_nano_scale_armor_short)).category(techCategory2).level(7).description(GameData.activity.getString(R.string.tech_nano_scale_armor_description, new Object[]{5})).value(5).iconIndex(0).build());
    }

    private void setWeaponTech() {
        Map<TechID, Tech> map = this.technologies;
        TechID techID = TechID.DISRUPTOR_BEAM;
        Tech.Builder id = new Tech.Builder(this.techLink).id(techID);
        TechType techType = TechType.SHIP_WEAPON;
        Tech.Builder shortName = id.type(techType).name(GameData.activity.getString(R.string.tech_disruptor_beam)).shortName(GameData.activity.getString(R.string.tech_disruptor_beam_short));
        TechCategory techCategory = TechCategory.PHYSICS;
        map.put(techID, shortName.category(techCategory).level(2).description(GameData.activity.getString(R.string.tech_disruptor_beam_description, new Object[]{8, 16})).iconIndex(ComponentIconEnum.DISRUPTOR.ordinal()).build());
        Map<TechID, Tech> map2 = this.technologies;
        TechID techID2 = TechID.POLARON_BEAM;
        map2.put(techID2, new Tech.Builder(this.techLink).id(techID2).type(techType).name(GameData.activity.getString(R.string.tech_polaron_beam)).shortName(GameData.activity.getString(R.string.tech_polaron_beam_short)).category(techCategory).level(4).description(GameData.activity.getString(R.string.tech_polaron_beam_description, new Object[]{16, 32})).iconIndex(ComponentIconEnum.POLARON_BEAM.ordinal()).build());
        Map<TechID, Tech> map3 = this.technologies;
        TechID techID3 = TechID.PHASOR_BEAM;
        map3.put(techID3, new Tech.Builder(this.techLink).id(techID3).type(techType).name(GameData.activity.getString(R.string.tech_phasor_beam)).shortName(GameData.activity.getString(R.string.tech_phasor_beam_short)).category(techCategory).level(6).description(GameData.activity.getString(R.string.tech_phasor_beam_description, new Object[]{32, 64})).iconIndex(ComponentIconEnum.PHASOR.ordinal()).build());
        Map<TechID, Tech> map4 = this.technologies;
        TechID techID4 = TechID.ANTIMATTER_BOMB;
        Tech.Builder shortName2 = new Tech.Builder(this.techLink).id(techID4).type(techType).name(GameData.activity.getString(R.string.tech_antimatter_bomb)).shortName(GameData.activity.getString(R.string.tech_antimatter_bomb_short));
        TechCategory techCategory2 = TechCategory.CHEMISTRY;
        map4.put(techID4, shortName2.category(techCategory2).level(2).description(GameData.activity.getString(R.string.tech_antimatter_bomb_description, new Object[]{50, 75})).iconIndex(ComponentIconEnum.ANTIMATTER_BOMB.ordinal()).build());
        Map<TechID, Tech> map5 = this.technologies;
        TechID techID5 = TechID.NOVA_BOMB;
        map5.put(techID5, new Tech.Builder(this.techLink).id(techID5).type(techType).name(GameData.activity.getString(R.string.tech_nova_bomb)).shortName(GameData.activity.getString(R.string.tech_nova_bomb_short)).category(techCategory2).level(6).description(GameData.activity.getString(R.string.tech_nova_bomb_description, new Object[]{100, Integer.valueOf((int) WeaponStats.NOVA_BOMB_MAX_DAMAGE)})).iconIndex(ComponentIconEnum.NOVA_BOMB.ordinal()).build());
        Map<TechID, Tech> map6 = this.technologies;
        TechID techID6 = TechID.DIMENSIONAL_ENERGY_BOMB;
        map6.put(techID6, new Tech.Builder(this.techLink).id(techID6).type(techType).name(GameData.activity.getString(R.string.tech_dimensional_energy_bomb)).shortName(GameData.activity.getString(R.string.tech_dimensional_energy_bomb_short)).category(techCategory2).level(7).description(GameData.activity.getString(R.string.tech_dimensional_energy_bomb_description, new Object[]{Integer.valueOf((int) WeaponStats.DIMENSIONAL_ENERGY_BOMB_MIN_DAMAGE), 300})).iconIndex(ComponentIconEnum.DIMENSIONAL_ENERGY_BOMB.ordinal()).build());
        Map<TechID, Tech> map7 = this.technologies;
        TechID techID7 = TechID.BIO_BOMB;
        map7.put(techID7, new Tech.Builder(this.techLink).id(techID7).type(techType).name(GameData.activity.getString(R.string.tech_bio_weapon_bomb)).shortName(GameData.activity.getString(R.string.tech_bio_weapon_bomb_short)).category(techCategory2).level(3).description(GameData.activity.getString(R.string.tech_bio_weapon_bomb_description, new Object[]{75, 100})).iconIndex(ComponentIconEnum.BIO_BOMB.ordinal()).build());
        Map<TechID, Tech> map8 = this.technologies;
        TechID techID8 = TechID.ANTIMATTER_TORPEDO;
        map8.put(techID8, new Tech.Builder(this.techLink).id(techID8).type(techType).name(GameData.activity.getString(R.string.tech_antimatter_torpedo)).shortName(GameData.activity.getString(R.string.tech_antimatter_torpedo_short)).category(techCategory2).level(2).description(GameData.activity.getString(R.string.tech_antimatter_torpedo_description, new Object[]{Integer.valueOf((int) WeaponStats.ANTIMATTER_TORPEDO_SPEED), 12, 20})).iconIndex(ComponentIconEnum.ANTIMATTER_TORPEDO.ordinal()).build());
        Map<TechID, Tech> map9 = this.technologies;
        TechID techID9 = TechID.QUANTUM_TORPEDO;
        map9.put(techID9, new Tech.Builder(this.techLink).id(techID9).type(techType).name(GameData.activity.getString(R.string.tech_quantum_torpedo)).shortName(GameData.activity.getString(R.string.tech_quantum_torpedo_short)).category(techCategory2).level(5).description(GameData.activity.getString(R.string.tech_quantum_torpedo_description, new Object[]{300, 25, 36})).iconIndex(ComponentIconEnum.QUANTUM_TORPEDO.ordinal()).build());
        Map<TechID, Tech> map10 = this.technologies;
        TechID techID10 = TechID.TRANSPHASIC_TORPEDO;
        map10.put(techID10, new Tech.Builder(this.techLink).id(techID10).type(techType).name(GameData.activity.getString(R.string.tech_transphasic_torpedo)).shortName(GameData.activity.getString(R.string.tech_transphasic_torpedo_short)).category(techCategory2).level(7).description(GameData.activity.getString(R.string.tech_transphasic_torpedo_description, new Object[]{Integer.valueOf((int) WeaponStats.TRANSPHASIC_TORPEDO_SPEED), 45, 65})).iconIndex(ComponentIconEnum.TRANSPHASIC_TORPEDO.ordinal()).build());
        Map<TechID, Tech> map11 = this.technologies;
        TechID techID11 = TechID.SPACIAL_CHARGE;
        Tech.Builder shortName3 = new Tech.Builder(this.techLink).id(techID11).type(techType).name(GameData.activity.getString(R.string.tech_spacial_charge)).shortName(GameData.activity.getString(R.string.tech_spacial_charge_short));
        TechCategory techCategory3 = TechCategory.ENERGY;
        map11.put(techID11, shortName3.category(techCategory3).level(2).description(GameData.activity.getString(R.string.tech_spacial_charge_description, new Object[]{8, 12, Integer.valueOf((int) WeaponStats.SPACIAL_CHARGE_SPEED)})).iconIndex(ComponentIconEnum.SPACIAL_CHARGE.ordinal()).build());
        Map<TechID, Tech> map12 = this.technologies;
        TechID techID12 = TechID.SUBSPACE_CHARGE;
        map12.put(techID12, new Tech.Builder(this.techLink).id(techID12).type(techType).name(GameData.activity.getString(R.string.tech_subspace_charge)).shortName(GameData.activity.getString(R.string.tech_subspace_charge_short)).category(techCategory3).level(5).description(GameData.activity.getString(R.string.tech_subspace_charge_description, new Object[]{20, 30, Integer.valueOf((int) WeaponStats.SUBSPACE_CHARGE_SPEED)})).iconIndex(ComponentIconEnum.SUBSPACE_CHARGE.ordinal()).build());
        Map<TechID, Tech> map13 = this.technologies;
        TechID techID13 = TechID.DIMENSIONAL_CHARGE;
        map13.put(techID13, new Tech.Builder(this.techLink).id(techID13).type(techType).name(GameData.activity.getString(R.string.tech_dimensional_charge)).shortName(GameData.activity.getString(R.string.tech_dimensional_charge_short)).category(techCategory3).level(7).description(GameData.activity.getString(R.string.tech_dimensional_charge_description, new Object[]{35, 45, Integer.valueOf((int) WeaponStats.DIMENSIONAL_CHARGE_SPEED)})).iconIndex(ComponentIconEnum.DIMENSIONAL_CHARGE.ordinal()).build());
    }

    public Map<TechID, Tech> a() {
        return this.technologies;
    }

    public Tech get(TechID techID) {
        return this.technologies.get(techID);
    }

    public int getHighestTechLevel(TechCategory techCategory) {
        int i = 1;
        for (Tech tech : this.technologies.values()) {
            if (tech.getCategory() == techCategory && tech.getType() != TechType.NONE && tech.getLevel() > i) {
                i = tech.getLevel();
            }
        }
        return i;
    }

    public List<TechID> getTechsFromCategoryAndLevel(TechCategory techCategory, int i) {
        ArrayList arrayList = new ArrayList();
        for (Tech tech : this.technologies.values()) {
            if (tech.getCategory() == techCategory && tech.getLevel() == i && tech.getType() != TechType.NONE) {
                arrayList.add(tech.getID());
            }
        }
        return arrayList;
    }
}
