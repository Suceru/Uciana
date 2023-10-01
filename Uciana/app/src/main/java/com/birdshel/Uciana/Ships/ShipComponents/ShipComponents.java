package com.birdshel.Uciana.Ships.ShipComponents;

import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ComponentIconEnum;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Technology.TechID;

import org.andengine.util.adt.color.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import org.andengine.util.adt.color.Color;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ShipComponents {
    private static final Map<ShipComponentID, ShipComponent> shipComponents = new HashMap();

    public static ShipComponent get(ShipComponentID shipComponentID) {
        ShipComponent shipComponent = shipComponents.get(shipComponentID);
        if (shipComponent instanceof Armor) {
            return new Armor((Armor) shipComponent);
        }
        if (shipComponent instanceof Shield) {
            return new Shield((Shield) shipComponent);
        }
        if (shipComponent instanceof TargetingComputer) {
            return new TargetingComputer((TargetingComputer) shipComponent);
        }
        if (shipComponent instanceof SublightEngine) {
            return new SublightEngine((SublightEngine) shipComponent);
        }
        if (shipComponent instanceof Weapon) {
            return new Weapon((Weapon) shipComponent);
        }
        return new SpecialComponent((SpecialComponent) shipComponent);
    }

    public static List<Armor> getArmors() {
        ArrayList arrayList = new ArrayList();
        Map<ShipComponentID, ShipComponent> map = shipComponents;
        arrayList.add((Armor) map.get(ShipComponentID.NEUTRONIUM_ARMOR));
        arrayList.add((Armor) map.get(ShipComponentID.CRYSTALIUM_ARMOR));
        arrayList.add((Armor) map.get(ShipComponentID.THETRIUM_ARMOR));
        arrayList.add((Armor) map.get(ShipComponentID.DETRUTIUM_ARMOR));
        arrayList.add((Armor) map.get(ShipComponentID.VANADIUM_ARMOR));
        arrayList.add((Armor) map.get(ShipComponentID.TITIANIUM_ARMOR));
        return arrayList;
    }

    public static List<Shield> getShields() {
        ArrayList arrayList = new ArrayList();
        Map<ShipComponentID, ShipComponent> map = shipComponents;
        arrayList.add((Shield) map.get(ShipComponentID.MULTIPHASIC_SHIELDS));
        arrayList.add((Shield) map.get(ShipComponentID.PHASED_SHIELDS));
        arrayList.add((Shield) map.get(ShipComponentID.DEFLECTOR_SHIELDS));
        arrayList.add((Shield) map.get(ShipComponentID.FORCE_SHIELDS));
        arrayList.add((Shield) map.get(ShipComponentID.NO_SHIELDS));
        return arrayList;
    }

    public static List<SpecialComponent> getSpecialComponents() {
        ArrayList arrayList = new ArrayList();
        Map<ShipComponentID, ShipComponent> map = shipComponents;
        arrayList.add((SpecialComponent) map.get(ShipComponentID.SCOUTING));
        arrayList.add((SpecialComponent) map.get(ShipComponentID.EXTENDED_HULL));
        arrayList.add((SpecialComponent) map.get(ShipComponentID.HARDENED_ALLOY));
        arrayList.add((SpecialComponent) map.get(ShipComponentID.SHIELD_CAPACITOR));
        arrayList.add((SpecialComponent) map.get(ShipComponentID.SCANNER));
        arrayList.add((SpecialComponent) map.get(ShipComponentID.TROOP_PODS));
        return arrayList;
    }

    public static List<SublightEngine> getSublightEngines() {
        ArrayList arrayList = new ArrayList();
        Map<ShipComponentID, ShipComponent> map = shipComponents;
        arrayList.add((SublightEngine) map.get(ShipComponentID.DIMENSIONAL_ENGINES));
        arrayList.add((SublightEngine) map.get(ShipComponentID.PHASED_ENGINES));
        arrayList.add((SublightEngine) map.get(ShipComponentID.IMPULSE_ENGINES));
        arrayList.add((SublightEngine) map.get(ShipComponentID.ION_ENGINES));
        arrayList.add((SublightEngine) map.get(ShipComponentID.STANDARD_CHEMICAL_ENGINES));
        return arrayList;
    }

    public static List<TargetingComputer> getTargetingComputers() {
        ArrayList arrayList = new ArrayList();
        Map<ShipComponentID, ShipComponent> map = shipComponents;
        arrayList.add((TargetingComputer) map.get(ShipComponentID.MULTI_PHASED_TARGETING));
        arrayList.add((TargetingComputer) map.get(ShipComponentID.PHASED_TARGETING));
        arrayList.add((TargetingComputer) map.get(ShipComponentID.QUANTUM_TARGETING));
        arrayList.add((TargetingComputer) map.get(ShipComponentID.ADAPTIVE_OPTICS_TARGETING));
        arrayList.add((TargetingComputer) map.get(ShipComponentID.OPTICS_TARGETING));
        arrayList.add((TargetingComputer) map.get(ShipComponentID.NO_TARGETING));
        return arrayList;
    }

    public static List<Weapon> getWeapons() {
        ArrayList arrayList = new ArrayList();
        Map<ShipComponentID, ShipComponent> map = shipComponents;
        arrayList.add((Weapon) map.get(ShipComponentID.LAZER));
        arrayList.add((Weapon) map.get(ShipComponentID.DISRUPTOR));
        arrayList.add((Weapon) map.get(ShipComponentID.POLARON_BEAM));
        arrayList.add((Weapon) map.get(ShipComponentID.PHASOR));
        arrayList.add((Weapon) map.get(ShipComponentID.NUCLUEAR_BOMB));
        arrayList.add((Weapon) map.get(ShipComponentID.ANTIMATTER_BOMB));
        arrayList.add((Weapon) map.get(ShipComponentID.NOVA_BOMB));
        arrayList.add((Weapon) map.get(ShipComponentID.DIMENSIONAL_ENERGY_BOMB));
        arrayList.add((Weapon) map.get(ShipComponentID.BIO_BOMB));
        arrayList.add((Weapon) map.get(ShipComponentID.TORPEDO));
        arrayList.add((Weapon) map.get(ShipComponentID.ANTIMATTER_TORPEDO));
        arrayList.add((Weapon) map.get(ShipComponentID.QUANTUM_TORPEDO));
        arrayList.add((Weapon) map.get(ShipComponentID.TRANSPHASIC_TORPEDO));
        arrayList.add((Weapon) map.get(ShipComponentID.SPACIAL_CHARGE));
        arrayList.add((Weapon) map.get(ShipComponentID.SUBSPACE_CHARGE));
        arrayList.add((Weapon) map.get(ShipComponentID.DIMENSIONAL_CHARGE));
        return arrayList;
    }

    public static void set() {
        setArmors();
        setShields();
        setTargetingComputers();
        setSublightEngines();
        setWeapons();
        setSpecialComponents();
    }

    private static void setArmors() {
        Map<ShipComponentID, ShipComponent> map = shipComponents;
        ShipComponentID shipComponentID = ShipComponentID.NEUTRONIUM_ARMOR;
        map.put(shipComponentID, new Armor.Builder().id(shipComponentID).name(R.string.armor_neutronium).armorMultiplier(6.0f).requiredTechID(TechID.NEUTRONIUM_ARMOR).iconIndex(ComponentIconEnum.NEUTRONIUM_ARMOR.ordinal()).spaceRequired(15).productionCost(20).build());
        ShipComponentID shipComponentID2 = ShipComponentID.CRYSTALIUM_ARMOR;
        map.put(shipComponentID2, new Armor.Builder().id(shipComponentID2).name(R.string.armor_crystalium).armorMultiplier(5.0f).requiredTechID(TechID.CRYSTALIUM_ARMOR).iconIndex(ComponentIconEnum.CRYSTALIUM_ARMOR.ordinal()).spaceRequired(15).productionCost(18).build());
        ShipComponentID shipComponentID3 = ShipComponentID.THETRIUM_ARMOR;
        map.put(shipComponentID3, new Armor.Builder().id(shipComponentID3).name(R.string.armor_thetrium).armorMultiplier(4.0f).requiredTechID(TechID.THETRIUM_ARMOR).iconIndex(ComponentIconEnum.THETRIUM_ARMOR.ordinal()).spaceRequired(10).productionCost(18).build());
        ShipComponentID shipComponentID4 = ShipComponentID.DETRUTIUM_ARMOR;
        map.put(shipComponentID4, new Armor.Builder().id(shipComponentID4).name(R.string.armor_detrutium).armorMultiplier(3.0f).requiredTechID(TechID.DETRUTIUM_ARMOR).iconIndex(ComponentIconEnum.DETRUTIUM_ARMOR.ordinal()).spaceRequired(10).productionCost(15).build());
        ShipComponentID shipComponentID5 = ShipComponentID.VANADIUM_ARMOR;
        map.put(shipComponentID5, new Armor.Builder().id(shipComponentID5).name(R.string.armor_vanadium).armorMultiplier(2.0f).requiredTechID(TechID.VANADIUM_ARMOR).iconIndex(ComponentIconEnum.VANADIUM_ARMOR.ordinal()).spaceRequired(10).productionCost(12).build());
        ShipComponentID shipComponentID6 = ShipComponentID.TITIANIUM_ARMOR;
        map.put(shipComponentID6, new Armor.Builder().id(shipComponentID6).name(R.string.armor_titanium).armorMultiplier(1.0f).requiredTechID(TechID.NONE).iconIndex(ComponentIconEnum.TITIANIUM_ARMOR.ordinal()).spaceRequired(10).productionCost(10).build());
    }

    private static void setShields() {
        Map<ShipComponentID, ShipComponent> map = shipComponents;
        ShipComponentID shipComponentID = ShipComponentID.MULTIPHASIC_SHIELDS;
        map.put(shipComponentID, new Shield.Builder().id(shipComponentID).name(R.string.shield_multi_phased).strengthMultiplier(35).absorption(7).rechargeRate(0.3f).requiredTechID(TechID.MULTIPHASIC_SHIELDS).iconIndex(ComponentIconEnum.MULTIPHASIC_SHIELDS.ordinal()).spaceRequired(15).productionCost(18).build());
        ShipComponentID shipComponentID2 = ShipComponentID.PHASED_SHIELDS;
        map.put(shipComponentID2, new Shield.Builder().id(shipComponentID2).name(R.string.shield_phased).strengthMultiplier(25).absorption(5).rechargeRate(0.3f).requiredTechID(TechID.PHASED_SHIELDS).iconIndex(ComponentIconEnum.PHASED_SHIELDS.ordinal()).spaceRequired(12).productionCost(15).build());
        ShipComponentID shipComponentID3 = ShipComponentID.DEFLECTOR_SHIELDS;
        map.put(shipComponentID3, new Shield.Builder().id(shipComponentID3).name(R.string.shield_deflector).strengthMultiplier(15).absorption(3).rechargeRate(0.25f).requiredTechID(TechID.DEFLECTOR_SHIELDS).iconIndex(ComponentIconEnum.DEFLECTOR_SHIELDS.ordinal()).spaceRequired(10).productionCost(10).build());
        ShipComponentID shipComponentID4 = ShipComponentID.FORCE_SHIELDS;
        map.put(shipComponentID4, new Shield.Builder().id(shipComponentID4).name(R.string.shield_force).strengthMultiplier(5).absorption(1).rechargeRate(0.2f).requiredTechID(TechID.FORCE_SHIELDS).iconIndex(ComponentIconEnum.FORCE_SHIELDS.ordinal()).spaceRequired(10).productionCost(8).build());
        ShipComponentID shipComponentID5 = ShipComponentID.NO_SHIELDS;
        map.put(shipComponentID5, new Shield.Builder().id(shipComponentID5).name(R.string.shield_no).strengthMultiplier(0).absorption(0).rechargeRate(0.0f).requiredTechID(TechID.NONE).iconIndex(ComponentIconEnum.NO_SHIELDS.ordinal()).spaceRequired(0).productionCost(0).build());
    }

    private static void setSpecialComponents() {
        Map<ShipComponentID, ShipComponent> map = shipComponents;
        ShipComponentID shipComponentID = ShipComponentID.SCOUTING;
        SpecialComponent.Builder iconIndex = new SpecialComponent.Builder().id(shipComponentID).name(R.string.special_component_scouting).description(R.string.special_component_scouting_desc).iconIndex(ComponentIconEnum.SCOUTING.ordinal());
        TechID techID = TechID.NONE;
        map.put(shipComponentID, iconIndex.requiredTechID(techID).spaceRequired(10).productionCost(10).allowMoreThenOne(false).showOnShipControl(false).build());
        ShipComponentID shipComponentID2 = ShipComponentID.EXTENDED_HULL;
        map.put(shipComponentID2, new SpecialComponent.Builder().id(shipComponentID2).name(R.string.special_component_hull).description(R.string.special_component_hull_desc).iconIndex(ComponentIconEnum.EXTENDED_HULL.ordinal()).requiredTechID(TechID.EXTENDED_HULL).spaceRequired(0).productionCost(0).effectValue(0.5f).allowMoreThenOne(false).showOnShipControl(false).build());
        ShipComponentID shipComponentID3 = ShipComponentID.HARDENED_ALLOY;
        map.put(shipComponentID3, new SpecialComponent.Builder().id(shipComponentID3).name(R.string.special_component_alloy).description(R.string.special_component_alloy_desc).iconIndex(ComponentIconEnum.HARDENED_ALLOY.ordinal()).requiredTechID(TechID.HARDENED_ALLOY).spaceRequired(20).productionCost(20).effectValue(0.25f).allowMoreThenOne(false).showOnShipControl(false).build());
        ShipComponentID shipComponentID4 = ShipComponentID.SHIELD_CAPACITOR;
        map.put(shipComponentID4, new SpecialComponent.Builder().id(shipComponentID4).name(R.string.special_component_shield_capacitor).description(R.string.special_component_shield_capacitor_desc).iconIndex(ComponentIconEnum.SHIELD_CAPACITOR.ordinal()).requiredTechID(TechID.SHIELD_CAPACITOR).spaceRequired(15).productionCost(15).effectValue(0.1f).allowMoreThenOne(false).showOnShipControl(false).build());
        ShipComponentID shipComponentID5 = ShipComponentID.SCANNER;
        map.put(shipComponentID5, new SpecialComponent.Builder().id(shipComponentID5).name(R.string.special_component_scanner).description(R.string.special_component_scanner_desc).shortName(R.string.special_component_scanner_short).iconIndex(ComponentIconEnum.SCANNER.ordinal()).requiredTechID(techID).spaceRequired(10).productionCost(10).allowMoreThenOne(false).showOnShipControl(true).build());
        ShipComponentID shipComponentID6 = ShipComponentID.TROOP_PODS;
        map.put(shipComponentID6, new SpecialComponent.Builder().id(shipComponentID6).name(R.string.special_component_troop_pods).description(R.string.special_component_troop_pods_desc).iconIndex(ComponentIconEnum.TROOP_PODS.ordinal()).requiredTechID(techID).spaceRequired(50).productionCost(75).allowMoreThenOne(false).showOnShipControl(false).build());
    }

    private static void setSublightEngines() {
        Map<ShipComponentID, ShipComponent> map = shipComponents;
        ShipComponentID shipComponentID = ShipComponentID.DIMENSIONAL_ENGINES;
        map.put(shipComponentID, new SublightEngine.Builder().id(shipComponentID).name(R.string.engine_dimensional).combatSpeed(6).requiredTechID(TechID.DIMENSIONAL_ENGINES).iconIndex(ComponentIconEnum.DIMENSIONAL_ENGINES.ordinal()).spaceRequired(15).productionCost(18).build());
        ShipComponentID shipComponentID2 = ShipComponentID.PHASED_ENGINES;
        map.put(shipComponentID2, new SublightEngine.Builder().id(shipComponentID2).name(R.string.engine_phased).combatSpeed(5).requiredTechID(TechID.PHASED_ENGINES).iconIndex(ComponentIconEnum.PHASED_ENGINES.ordinal()).spaceRequired(15).productionCost(15).build());
        ShipComponentID shipComponentID3 = ShipComponentID.IMPULSE_ENGINES;
        map.put(shipComponentID3, new SublightEngine.Builder().id(shipComponentID3).name(R.string.engine_impulse).combatSpeed(4).requiredTechID(TechID.IMPULSE_ENGINES).iconIndex(ComponentIconEnum.IMPULSE_ENGINES.ordinal()).spaceRequired(12).productionCost(15).build());
        ShipComponentID shipComponentID4 = ShipComponentID.ION_ENGINES;
        map.put(shipComponentID4, new SublightEngine.Builder().id(shipComponentID4).name(R.string.engine_ion).combatSpeed(3).requiredTechID(TechID.ION_ENGINES).iconIndex(ComponentIconEnum.ION_ENGINES.ordinal()).spaceRequired(10).productionCost(12).build());
        ShipComponentID shipComponentID5 = ShipComponentID.STANDARD_CHEMICAL_ENGINES;
        SublightEngine.Builder combatSpeed = new SublightEngine.Builder().id(shipComponentID5).name(R.string.engine_chemical).combatSpeed(2);
        TechID techID = TechID.NONE;
        SublightEngine.Builder requiredTechID = combatSpeed.requiredTechID(techID);
        ComponentIconEnum componentIconEnum = ComponentIconEnum.STANDARD_CHEMICAL_ENGINES;
        map.put(shipComponentID5, requiredTechID.iconIndex(componentIconEnum.ordinal()).spaceRequired(10).productionCost(10).build());
        ShipComponentID shipComponentID6 = ShipComponentID.NO_SUBLIGHT_ENGINES;
        map.put(shipComponentID6, new SublightEngine.Builder().id(shipComponentID6).name(R.string.engine_none).combatSpeed(0).requiredTechID(techID).iconIndex(componentIconEnum.ordinal()).spaceRequired(0).productionCost(0).build());
    }

    private static void setTargetingComputers() {
        Map<ShipComponentID, ShipComponent> map = shipComponents;
        ShipComponentID shipComponentID = ShipComponentID.MULTI_PHASED_TARGETING;
        map.put(shipComponentID, new TargetingComputer.Builder().id(shipComponentID).name(R.string.targeting_multi_phased).targetingBonus(115).damageBonus(16).requiredTechID(TechID.MULTI_PHASED_TARGETING).iconIndex(ComponentIconEnum.MULTI_PHASED_TARGETING.ordinal()).spaceRequired(0).productionCost(20).build());
        ShipComponentID shipComponentID2 = ShipComponentID.PHASED_TARGETING;
        map.put(shipComponentID2, new TargetingComputer.Builder().id(shipComponentID2).name(R.string.targeting_phased).targetingBonus(85).damageBonus(12).requiredTechID(TechID.PHASED_TARGETING).iconIndex(ComponentIconEnum.PHASED_TARGETING.ordinal()).spaceRequired(0).productionCost(18).build());
        ShipComponentID shipComponentID3 = ShipComponentID.QUANTUM_TARGETING;
        map.put(shipComponentID3, new TargetingComputer.Builder().id(shipComponentID3).name(R.string.targeting_quantum).targetingBonus(65).damageBonus(8).requiredTechID(TechID.QUANTUM_TARGETING).iconIndex(ComponentIconEnum.QUANTUM_TARGETING.ordinal()).spaceRequired(0).productionCost(15).build());
        ShipComponentID shipComponentID4 = ShipComponentID.ADAPTIVE_OPTICS_TARGETING;
        map.put(shipComponentID4, new TargetingComputer.Builder().id(shipComponentID4).name(R.string.targeting_adaptive).targetingBonus(45).damageBonus(5).requiredTechID(TechID.ADAPTIVE_OPTICS_TARGETING).iconIndex(ComponentIconEnum.ADAPTIVE_OPTICS_TARGETING.ordinal()).spaceRequired(0).productionCost(12).build());
        ShipComponentID shipComponentID5 = ShipComponentID.OPTICS_TARGETING;
        TargetingComputer.Builder damageBonus = new TargetingComputer.Builder().id(shipComponentID5).name(R.string.targeting_optical).targetingBonus(25).damageBonus(2);
        TechID techID = TechID.NONE;
        map.put(shipComponentID5, damageBonus.requiredTechID(techID).iconIndex(ComponentIconEnum.OPTICS_TARGETING.ordinal()).spaceRequired(0).productionCost(10).build());
        ShipComponentID shipComponentID6 = ShipComponentID.NO_TARGETING;
        map.put(shipComponentID6, new TargetingComputer.Builder().id(shipComponentID6).name(R.string.targeting_no).targetingBonus(0).damageBonus(0).requiredTechID(techID).iconIndex(ComponentIconEnum.NO_TARGETING.ordinal()).spaceRequired(0).productionCost(0).build());
    }

    private static void setWeapons() {
        Map<ShipComponentID, ShipComponent> map = shipComponents;
        ShipComponentID shipComponentID = ShipComponentID.LAZER;
        Weapon.Builder id = new Weapon.Builder().id(shipComponentID);
        WeaponType weaponType = WeaponType.BEAM;
        Weapon.Builder iconIndex = id.type(weaponType).minDamage(4).maxDamage(8).name(R.string.weapon_beam_laser).description(R.string.weapon_beam_laser_desc).shortName(R.string.weapon_beam_laser_short).iconIndex(ComponentIconEnum.LAZER.ordinal());
        TechID techID = TechID.NONE;
        map.put(shipComponentID, iconIndex.requiredTechID(techID).spaceRequired(10).productionCost(10).soundEffectIndex(0).weaponColor(Color.RED).build());
        ShipComponentID shipComponentID2 = ShipComponentID.DISRUPTOR;
        map.put(shipComponentID2, new Weapon.Builder().id(shipComponentID2).type(weaponType).minDamage(8).maxDamage(16).name(R.string.weapon_beam_disruptor).description(R.string.weapon_beam_disruptor_desc).shortName(R.string.weapon_beam_disruptor_short).iconIndex(ComponentIconEnum.DISRUPTOR.ordinal()).requiredTechID(TechID.DISRUPTOR_BEAM).spaceRequired(12).productionCost(12).soundEffectIndex(1).weaponColor(Color.GREEN).build());
        ShipComponentID shipComponentID3 = ShipComponentID.POLARON_BEAM;
        map.put(shipComponentID3, new Weapon.Builder().id(shipComponentID3).type(weaponType).minDamage(16).maxDamage(32).name(R.string.weapon_beam_polaron_short).description(R.string.weapon_beam_polaron_desc).shortName(R.string.weapon_beam_polaron_short).iconIndex(ComponentIconEnum.POLARON_BEAM.ordinal()).requiredTechID(TechID.POLARON_BEAM).spaceRequired(15).productionCost(15).soundEffectIndex(2).weaponColor(Color.BLUE).build());
        ShipComponentID shipComponentID4 = ShipComponentID.PHASOR;
        map.put(shipComponentID4, new Weapon.Builder().id(shipComponentID4).type(weaponType).minDamage(32).maxDamage(64).name(R.string.weapon_beam_phasor).description(R.string.weapon_beam_phasor_desc).shortName(R.string.weapon_beam_phasor_short).iconIndex(ComponentIconEnum.PHASOR.ordinal()).requiredTechID(TechID.PHASOR_BEAM).spaceRequired(18).productionCost(18).soundEffectIndex(3).weaponColor(new Color(0.98f, 0.65f, 0.05f)).build());
        ShipComponentID shipComponentID5 = ShipComponentID.NUCLUEAR_BOMB;
        Weapon.Builder id2 = new Weapon.Builder().id(shipComponentID5);
        WeaponType weaponType2 = WeaponType.BOMB;
        map.put(shipComponentID5, id2.type(weaponType2).minDamage(25).maxDamage(35).name(R.string.weapon_bomb_nuclear).description(R.string.weapon_bomb_nuclear_desc).shortName(R.string.weapon_bomb_nuclear_short).iconIndex(ComponentIconEnum.NUCLUEAR_BOMB.ordinal()).requiredTechID(techID).spaceRequired(8).productionCost(8).soundEffectIndex(0).blastSize(50).build());
        ShipComponentID shipComponentID6 = ShipComponentID.ANTIMATTER_BOMB;
        map.put(shipComponentID6, new Weapon.Builder().id(shipComponentID6).type(weaponType2).minDamage(50).maxDamage(75).name(R.string.weapon_bomb_antimatter).description(R.string.weapon_bomb_antimatter_desc).shortName(R.string.weapon_bomb_antimatter_short).iconIndex(ComponentIconEnum.ANTIMATTER_BOMB.ordinal()).requiredTechID(TechID.ANTIMATTER_BOMB).spaceRequired(10).productionCost(10).soundEffectIndex(0).blastSize(65).build());
        ShipComponentID shipComponentID7 = ShipComponentID.NOVA_BOMB;
        map.put(shipComponentID7, new Weapon.Builder().id(shipComponentID7).type(weaponType2).minDamage(100).maxDamage(WeaponStats.NOVA_BOMB_MAX_DAMAGE).name(R.string.weapon_bomb_nova).description(R.string.weapon_bomb_nova_desc).shortName(R.string.weapon_bomb_nova_short).iconIndex(ComponentIconEnum.NOVA_BOMB.ordinal()).requiredTechID(TechID.NOVA_BOMB).spaceRequired(12).productionCost(12).soundEffectIndex(0).blastSize(80).build());
        ShipComponentID shipComponentID8 = ShipComponentID.DIMENSIONAL_ENERGY_BOMB;
        map.put(shipComponentID8, new Weapon.Builder().id(shipComponentID8).type(weaponType2).minDamage(WeaponStats.DIMENSIONAL_ENERGY_BOMB_MIN_DAMAGE).maxDamage(300).name(R.string.weapon_bomb_dimensional).description(R.string.weapon_bomb_dimensional_desc).shortName(R.string.weapon_bomb_dimensional_short).iconIndex(ComponentIconEnum.DIMENSIONAL_ENERGY_BOMB.ordinal()).requiredTechID(TechID.DIMENSIONAL_ENERGY_BOMB).spaceRequired(15).productionCost(15).soundEffectIndex(0).blastSize(95).build());
        ShipComponentID shipComponentID9 = ShipComponentID.BIO_BOMB;
        map.put(shipComponentID9, new Weapon.Builder().id(shipComponentID9).type(weaponType2).minDamage(75).maxDamage(100).name(R.string.weapon_bomb_bio).description(R.string.weapon_bomb_bio_desc).shortName(R.string.weapon_bomb_bio_short).iconIndex(ComponentIconEnum.BIO_BOMB.ordinal()).requiredTechID(TechID.BIO_BOMB).spaceRequired(10).productionCost(15).soundEffectIndex(0).blastSize(65).build());
        ShipComponentID shipComponentID10 = ShipComponentID.TORPEDO;
        Weapon.Builder id3 = new Weapon.Builder().id(shipComponentID10);
        WeaponType weaponType3 = WeaponType.TORPEDO;
        map.put(shipComponentID10, id3.type(weaponType3).minDamage(5).maxDamage(10).speed(WeaponStats.SPACIAL_CHARGE_SPEED).name(R.string.weapon_torp_torpedo).description(R.string.weapon_torp_torpedo_desc).shortName(R.string.weapon_torp_torpedo_short).weaponColor(new Color(0.195f, 0.97f, 0.1f)).iconIndex(ComponentIconEnum.TORPEDO.ordinal()).requiredTechID(techID).spaceRequired(15).productionCost(10).soundEffectIndex(4).build());
        ShipComponentID shipComponentID11 = ShipComponentID.ANTIMATTER_TORPEDO;
        map.put(shipComponentID11, new Weapon.Builder().id(shipComponentID11).type(weaponType3).minDamage(12).maxDamage(20).speed(WeaponStats.ANTIMATTER_TORPEDO_SPEED).name(R.string.weapon_torp_antimatter).description(R.string.weapon_torp_antimatter_desc).shortName(R.string.weapon_torp_antimatter_short).weaponColor(new Color(0.945f, 0.175f, 0.0f)).iconIndex(ComponentIconEnum.ANTIMATTER_TORPEDO.ordinal()).requiredTechID(TechID.ANTIMATTER_TORPEDO).spaceRequired(15).productionCost(15).soundEffectIndex(5).build());
        ShipComponentID shipComponentID12 = ShipComponentID.QUANTUM_TORPEDO;
        map.put(shipComponentID12, new Weapon.Builder().id(shipComponentID12).type(weaponType3).minDamage(25).maxDamage(36).speed(300).name(R.string.weapon_torp_quantum).description(R.string.weapon_torp_quantum_desc).shortName(R.string.weapon_torp_quantum_short).weaponColor(new Color(0.23f, 0.687f, 1.0f)).iconIndex(ComponentIconEnum.QUANTUM_TORPEDO.ordinal()).requiredTechID(TechID.QUANTUM_TORPEDO).spaceRequired(20).productionCost(15).soundEffectIndex(6).build());
        ShipComponentID shipComponentID13 = ShipComponentID.TRANSPHASIC_TORPEDO;
        map.put(shipComponentID13, new Weapon.Builder().id(shipComponentID13).type(weaponType3).minDamage(45).maxDamage(65).speed(WeaponStats.TRANSPHASIC_TORPEDO_SPEED).name(R.string.weapon_torp_transphasic).description(R.string.weapon_torp_transphasic_desc).shortName(R.string.weapon_torp_transphasic_short).weaponColor(new Color(1.0f, 0.785f, 0.1f)).iconIndex(ComponentIconEnum.TRANSPHASIC_TORPEDO.ordinal()).requiredTechID(TechID.TRANSPHASIC_TORPEDO).spaceRequired(20).productionCost(20).soundEffectIndex(7).build());
        ShipComponentID shipComponentID14 = ShipComponentID.SPACIAL_CHARGE;
        Weapon.Builder id4 = new Weapon.Builder().id(shipComponentID14);
        WeaponType weaponType4 = WeaponType.SPACIAL_CHARGE;
        map.put(shipComponentID14, id4.type(weaponType4).minDamage(8).maxDamage(12).speed(WeaponStats.SPACIAL_CHARGE_SPEED).name(R.string.weapon_charge_spacial).description(R.string.weapon_charge_spacial_desc).shortName(R.string.weapon_charge_spacial_short).weaponColor(new Color(0.945f, 0.175f, 0.0f)).iconIndex(ComponentIconEnum.SPACIAL_CHARGE.ordinal()).requiredTechID(TechID.SPACIAL_CHARGE).spaceRequired(15).productionCost(10).soundEffectIndex(8).shockwaveIndex(GameIconEnum.SPACIAL_CHARGE.ordinal()).build());
        ShipComponentID shipComponentID15 = ShipComponentID.SUBSPACE_CHARGE;
        map.put(shipComponentID15, new Weapon.Builder().id(shipComponentID15).type(weaponType4).minDamage(20).maxDamage(30).speed(WeaponStats.SUBSPACE_CHARGE_SPEED).name(R.string.weapon_charge_subspace).description(R.string.weapon_charge_subspace_desc).shortName(R.string.weapon_charge_subspace_short).weaponColor(new Color(0.945f, 0.175f, 0.0f)).iconIndex(ComponentIconEnum.SUBSPACE_CHARGE.ordinal()).requiredTechID(TechID.SUBSPACE_CHARGE).spaceRequired(18).productionCost(12).soundEffectIndex(9).shockwaveIndex(GameIconEnum.SUBSPACE_CHARGE.ordinal()).build());
        ShipComponentID shipComponentID16 = ShipComponentID.DIMENSIONAL_CHARGE;
        map.put(shipComponentID16, new Weapon.Builder().id(shipComponentID16).type(weaponType4).minDamage(35).maxDamage(45).speed(WeaponStats.DIMENSIONAL_CHARGE_SPEED).name(R.string.weapon_charge_dimensional).description(R.string.weapon_charge_dimensional_desc).shortName(R.string.weapon_charge_dimensional_short).weaponColor(new Color(0.945f, 0.175f, 0.0f)).iconIndex(ComponentIconEnum.DIMENSIONAL_CHARGE.ordinal()).requiredTechID(TechID.DIMENSIONAL_CHARGE).spaceRequired(20).productionCost(15).soundEffectIndex(10).shockwaveIndex(GameIconEnum.DIMENSIONAL_CHARGE.ordinal()).build());
    }
}
