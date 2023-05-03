package com.birdshel.Uciana.Technology;

import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.GameProperties;
import com.birdshel.Uciana.R;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public enum TechCategory {
    NONE(R.string.tech_category_none),
    ENGINEERING(R.string.tech_category_engineering),
    PHYSICS(R.string.tech_category_physics),
    CHEMISTRY(R.string.tech_category_chemistry),
    ENERGY(R.string.tech_category_energy);
    
    private final int name;

    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Technology.TechCategory$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1574a;

        static {
            int[] iArr = new int[TechCategory.values().length];
            f1574a = iArr;
            try {
                iArr[TechCategory.ENGINEERING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1574a[TechCategory.PHYSICS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1574a[TechCategory.CHEMISTRY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1574a[TechCategory.ENERGY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1574a[TechCategory.NONE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    TechCategory(int i) {
        this.name = i;
    }

    private TechID[] getChemistryList() {
        TechID techID = TechID.BLANK;
        return new TechID[]{TechID.CHEMISTRY1, TechID.VANADIUM_ARMOR, TechID.CLONING_CENTER, TechID.COZIURIUM, techID, TechID.CHEMISTRY2, TechID.AUTOMATED_FARM, TechID.ANTIMATTER_TORPEDO, TechID.ANTIMATTER_BOMB, techID, TechID.CHEMISTRY3, TechID.DETRUTIUM_ARMOR, TechID.BIO_BOMB, TechID.SOIL_ENRICHMENT, techID, TechID.CHEMISTRY4, TechID.TERRAFORMING, TechID.LESCITE_DETECTION, TechID.HARDENED_ALLOY, techID, TechID.CHEMISTRY5, TechID.THETRIUM_ARMOR, TechID.GENETICALLY_ENGINEERED_SUPER_FOOD, TechID.QUANTUM_TORPEDO, techID, TechID.CHEMISTRY6, TechID.HEIGHTENED_INTELLIGENCE, TechID.CRYSTALIUM_ARMOR, TechID.NOVA_BOMB, techID, TechID.CHEMISTRY7, TechID.ADVANCED_CHEMICAL_MINING, TechID.TRANSPHASIC_TORPEDO, TechID.DIMENSIONAL_ENERGY_BOMB, techID, TechID.CHEMISTRY8, TechID.NEUTRONIUM_ARMOR};
    }

    private TechID[] getEnergyList() {
        TechID techID = TechID.BLANK;
        return new TechID[]{TechID.ENERGY1, TechID.FUSION_REACTOR, TechID.FUSION_REACTOR_PLANT, TechID.WARP_DRIVE, techID, TechID.ENERGY2, TechID.TRANSWARP_DRIVE, TechID.FORCE_SHIELDS, TechID.SPACIAL_CHARGE, techID, TechID.ENERGY3, TechID.QUANTUM_GENERATOR, TechID.QUANTUM_GENERATOR_PLANT, TechID.DEFLECTOR_SHIELDS, techID, TechID.ENERGY4, TechID.SLIPSTREAM_DRIVE, TechID.PERSONAL_SHIELD, TechID.SHIELD_CAPACITOR, techID, TechID.ENERGY5, TechID.ANTIMATTER_REACTOR, TechID.ANTIMATTER_REACTOR_PLANT, TechID.SUBSPACE_CHARGE, techID, TechID.ENERGY6, TechID.HYPER_DRIVE, TechID.PHASED_SHIELDS, TechID.PLANETARY_SHIELD, techID, TechID.ENERGY7, TechID.ZERO_POINT_ENERGY, TechID.ZERO_POINT_ENERGY_PLANT, TechID.DIMENSIONAL_CHARGE, techID, TechID.ENERGY8, TechID.FOLD_DRIVE, TechID.MULTIPHASIC_SHIELDS, TechID.HOLOGRAPHIC_CENTER};
    }

    private TechID[] getEngineeringList() {
        TechID techID = TechID.BLANK;
        return new TechID[]{TechID.ENGINEERING1, TechID.ASTEROID_MINING_OUTPOST, TechID.ADAPTIVE_OPTICS_TARGETING, TechID.STAR_BASE, techID, TechID.ENGINEERING2, TechID.AUTOMATED_FACTORY, TechID.MOON_BASE, TechID.QUANTUM_TARGETING, techID, TechID.ENGINEERING3, TechID.EXTENDED_HULL, TechID.POWERED_ARMOR, TechID.SCIENCE_LAB, techID, TechID.ENGINEERING4, TechID.GALACTIC_STOCK_EXCHANGE, TechID.GALACTIC_INTERNET, TechID.PHASED_TARGETING, techID, TechID.ENGINEERING5, TechID.DREADNOUGHT, TechID.POINT_DEFENSE_SYSTEM, TechID.VIRTUAL_REALITY_SYSTEM, techID, TechID.ENGINEERING6, TechID.MULTI_PHASED_TARGETING, TechID.SPACE_ELEVATOR, techID, TechID.ENGINEERING7, TechID.NANO_FABRICATION_PLANT, TechID.NANO_SCALE_ARMOR};
    }

    private TechID[] getPhysicsList() {
        TechID techID = TechID.BLANK;
        return new TechID[]{TechID.PHYSICS1, TechID.ION_ENGINES, TechID.SUBSPACE_COMMUNICATION, TechID.LASER_RIFLE, techID, TechID.PHYSICS2, TechID.TACHYON_SCANNER, TechID.PULSE_RIFLE, TechID.DISRUPTOR_BEAM, techID, TechID.PHYSICS3, TechID.HYPERSPACE_COMMUNICATION, TechID.IMPULSE_ENGINES, TechID.QUANTUM_SUPER_COMPUTER, techID, TechID.PHYSICS4, TechID.ANTIMATTER_DETECTION, TechID.PHASED_ENGINES, TechID.POLARON_BEAM, techID, TechID.PHYSICS5, TechID.PHASED_COMMUNICATION, TechID.GRAVITY_GENERATOR, TechID.PHASOR_RIFLE, techID, TechID.PHYSICS6, TechID.PHASOR_BEAM, TechID.DIMENSIONAL_ENGINES, TechID.GRAVITY_DAMPER, techID, TechID.PHYSICS7, TechID.QUANTUM_COMMUNICATION, TechID.DISRUPTOR_RIFLE, TechID.FOOD_REPLICATORS};
    }

    public String getName() {
        return GameData.activity.getString(this.name);
    }

    public int getTechCost(int i) {
        int i2 = i - 1;
        int i3 = AnonymousClass1.f1574a[ordinal()];
        if (i3 != 1) {
            if (i3 != 2) {
                if (i3 != 3) {
                    if (i3 != 4) {
                        if (i3 == 5) {
                            return 0;
                        }
                        throw new AssertionError("Unknown TechCategory when getting tech cost");
                    }
                    return GameProperties.ENERGY_RESEARCH_COST[GameData.getTechCostVersion()][i2];
                }
                return GameProperties.CHEMISTRY_RESEARCH_COST[GameData.getTechCostVersion()][i2];
            }
            return GameProperties.PHYSICS_RESEARCH_COST[GameData.getTechCostVersion()][i2];
        }
        return GameProperties.ENGINEERING_RESEARCH_COST[GameData.getTechCostVersion()][i2];
    }

    public TechID[] getTechLevelList() {
        int i = AnonymousClass1.f1574a[ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        if (i == 5) {
                            return new TechID[0];
                        }
                        throw new AssertionError("Unknown TechCategory when getting tech level list");
                    }
                    return getEnergyList();
                }
                return getChemistryList();
            }
            return getPhysicsList();
        }
        return getEngineeringList();
    }
}
