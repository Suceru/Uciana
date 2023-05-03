package com.birdshel.Uciana.Technology;

import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.R;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public enum TechType {
    NONE(R.string.tech_type_na),
    POWER_CORE(R.string.tech_type_power_core),
    FASTER_THEN_LIGHT_DRIVE(R.string.tech_type_ftl),
    COLONY_SCANNER(R.string.tech_type_colony_scanner),
    SHIP_SCANNER(R.string.tech_type_ship_scanner),
    SHIP_COMMUNICATION(R.string.tech_type_ship_communication),
    PLANET_ENHANCEMENT(R.string.tech_type_planet_enhancement),
    BUILDING(R.string.tech_type_building),
    TROOP_IMPROVEMENT(R.string.tech_type_troop_improvement),
    SHIP_SHIELD(R.string.tech_type_ship_shield),
    SHIP_ARMOR(R.string.tech_type_ship_armor),
    SHIP_TARGETING_COMPUTER(R.string.tech_type_ship_targeting_computer),
    SHIP_SUBLIGHT_ENGINE(R.string.tech_type_ship_sublight_engine),
    SHIP_SPECIAL_COMPONENT(R.string.tech_type_ship_special_component),
    SHIP_WEAPON(R.string.tech_type_ship_weapon),
    ABILITY(R.string.tech_type_ability),
    RESOURCE(R.string.tech_type_resource),
    PERK(R.string.tech_type_perk);
    
    private final int name;

    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Technology.TechType$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1575a;

        static {
            int[] iArr = new int[TechType.values().length];
            f1575a = iArr;
            try {
                iArr[TechType.SHIP_SHIELD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1575a[TechType.SHIP_ARMOR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1575a[TechType.SHIP_TARGETING_COMPUTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1575a[TechType.SHIP_SUBLIGHT_ENGINE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1575a[TechType.SHIP_SPECIAL_COMPONENT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1575a[TechType.SHIP_WEAPON.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    TechType(int i) {
        this.name = i;
    }

    public String getName() {
        return GameData.activity.getString(this.name);
    }

    public boolean isShipComponent() {
        switch (AnonymousClass1.f1575a[ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return true;
            default:
                return false;
        }
    }
}
