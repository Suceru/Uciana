package com.birdshel.Uciana.Ships;

import android.util.SparseArray;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Resources.OptionID;
import com.birdshel.Uciana.Ships.ShipComponents.WeaponStats;

public enum ShipType {
    SCOUT(new Builder().z(2).w(0.32f).x(32.0f).p(26.0f).q(new int[]{R.string.scout_name_0, R.string.scout_name_1, R.string.scout_name_2, R.string.scout_name_3, R.string.scout_name_4, R.string.scout_name_5, R.string.scout_name_6, R.string.scout_name_7, R.string.scout_name_8, R.string.scout_name_9}).o(125).s(R.string.ship_description_scout)),
    DESTROYER(new Builder().z(4).w(0.49f).x(49.0f).p(40.0f).q(new int[]{R.string.destroyer_name_0, R.string.destroyer_name_1, R.string.destroyer_name_2, R.string.destroyer_name_3, R.string.destroyer_name_4, R.string.destroyer_name_5, R.string.destroyer_name_6, R.string.destroyer_name_7, R.string.destroyer_name_8, R.string.destroyer_name_9}).r(2).u().o(WeaponStats.SPACIAL_CHARGE_SPEED).n(125).v(2).y(2).t(4)),
    CRUISER(new Builder().z(5).w(0.66f).x(66.0f).p(53.0f).q(new int[]{R.string.cruiser_name_0, R.string.cruiser_name_1, R.string.cruiser_name_2, R.string.cruiser_name_3, R.string.cruiser_name_4, R.string.cruiser_name_5, R.string.cruiser_name_6, R.string.cruiser_name_7, R.string.cruiser_name_8, R.string.cruiser_name_9}).r(3).u().o(450).n(WeaponStats.DIMENSIONAL_ENERGY_BOMB_MIN_DAMAGE).v(3).y(3).t(3)),
    BATTLESHIP(new Builder().z(6).w(0.83f).x(83.0f).p(66.0f).q(new int[]{R.string.battleship_name_0, R.string.battleship_name_1, R.string.battleship_name_2, R.string.battleship_name_3, R.string.battleship_name_4, R.string.battleship_name_5, R.string.battleship_name_6, R.string.battleship_name_7, R.string.battleship_name_8, R.string.battleship_name_9}).r(4).u().o(650).n(300).v(4).y(4).t(2)),
    DREADNOUGHT(new Builder().z(7).w(1.0f).x(100.0f).p(80.0f).q(new int[]{R.string.dreadnought_name_0, R.string.dreadnought_name_1, R.string.dreadnought_name_2, R.string.dreadnought_name_3, R.string.dreadnought_name_4, R.string.dreadnought_name_5, R.string.dreadnought_name_6, R.string.dreadnought_name_7, R.string.dreadnought_name_8, R.string.dreadnought_name_9}).r(5).u().o(TypedValues.Custom.TYPE_INT).n(450).v(6).y(5).t(1)),
    COLONY(new Builder().z(3).w(0.83f).x(83.0f).p(66.0f).q(new int[]{R.string.colony_ship_name_0, R.string.colony_ship_name_1, R.string.colony_ship_name_2, R.string.colony_ship_name_3, R.string.colony_ship_name_4, R.string.colony_ship_name_5, R.string.colony_ship_name_6, R.string.colony_ship_name_7, R.string.colony_ship_name_8, R.string.colony_ship_name_9}).o(TypedValues.Cycle.TYPE_WAVE_PHASE).s(R.string.ship_description_colony)),
    CONSTRUCTION(new Builder().z(1).w(0.66f).x(66.0f).p(53.0f).q(new int[]{R.string.construction_ship_name_0, R.string.construction_ship_name_1, R.string.construction_ship_name_2, R.string.construction_ship_name_3, R.string.construction_ship_name_4, R.string.construction_ship_name_5, R.string.construction_ship_name_6, R.string.construction_ship_name_7, R.string.construction_ship_name_8, R.string.construction_ship_name_9}).o(WeaponStats.SUBSPACE_CHARGE_SPEED).s(R.string.ship_description_construction)),
    TRANSPORT(new Builder().z(0).w(0.66f).x(66.0f).p(53.0f).q(new int[]{R.string.transport_name_0, R.string.transport_name_1, R.string.transport_name_2, R.string.transport_name_3, R.string.transport_name_4, R.string.transport_name_5, R.string.transport_name_6, R.string.transport_name_7, R.string.transport_name_8, R.string.transport_name_9}).o(WeaponStats.DIMENSIONAL_ENERGY_BOMB_MIN_DAMAGE).s(R.string.ship_description_transport)),
    STAR_BASE(new Builder().z(8).w(1.0f).x(100.0f).p(100.0f).q(new int[]{R.string.station_starbase, R.string.station_starbase, R.string.station_starbase, R.string.station_starbase, R.string.station_starbase, R.string.station_starbase, R.string.station_starbase, R.string.station_starbase, R.string.station_starbase}).r(0).u().n(300).v(6).y(4).t(2)),
    TORPEDO_TURRET(new Builder().z(9).w(0.6f).x(60.0f).p(50.0f).q(new int[]{R.string.station_turret, R.string.station_turret, R.string.station_turret, R.string.station_turret, R.string.station_turret, R.string.station_turret, R.string.station_turret, R.string.station_turret, R.string.station_turret}).r(0).u().n(WeaponStats.NOVA_BOMB_MAX_DAMAGE).v(1).y(3).t(3));
    
    private static final SparseArray<ShipType> lookup = new SparseArray<>();
    private final int baseAvailableSpace;
    private final int baseProductionCost;
    private final float battleScreenSize;
    private final int[] classNames;
    private final int commandPointCost;
    private final int description;
    public final int id;
    private final int initiative;
    private final boolean isCombatShip;
    private final int numberOfComponents;
    private final float scale;
    private final float selectScreenSize;
    private final int sizeClass;

    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Ships.ShipType$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a */
        static final /* synthetic */ int[] f1566a;

        static {
            int[] iArr = new int[ShipType.values().length];
            f1566a = iArr;
            try {
                iArr[ShipType.TORPEDO_TURRET.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1566a[ShipType.STAR_BASE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1566a[ShipType.DESTROYER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1566a[ShipType.CRUISER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1566a[ShipType.BATTLESHIP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1566a[ShipType.DREADNOUGHT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1566a[ShipType.SCOUT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1566a[ShipType.COLONY.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1566a[ShipType.CONSTRUCTION.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f1566a[ShipType.TRANSPORT.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: MyApplication */
    /* loaded from: classes.dex */
    public static class Builder {
        private int baseProductionCost;
        private float battleScreenSize;
        private int[] classNames;
        private int description;
        private int initiative;
        private float scale;
        private float selectScreenSize;
        private int sizeClass;
        private int value;
        private int baseAvailableSpace = 0;
        private boolean isCombatShip = false;
        private int numberOfComponents = 0;
        private int commandPointCost = 1;

        Builder n(int i) {
            this.baseAvailableSpace = i;
            return this;
        }

        Builder o(int i) {
            this.baseProductionCost = i;
            return this;
        }

        Builder p(float f2) {
            this.battleScreenSize = f2;
            return this;
        }

        Builder q(int[] iArr) {
            this.classNames = iArr;
            return this;
        }

        Builder r(int i) {
            this.commandPointCost = i;
            return this;
        }

        Builder s(int i) {
            this.description = i;
            return this;
        }

        Builder t(int i) {
            this.initiative = i;
            return this;
        }

        Builder u() {
            this.isCombatShip = true;
            return this;
        }

        Builder v(int i) {
            this.numberOfComponents = i;
            return this;
        }

        Builder w(float f2) {
            this.scale = f2;
            return this;
        }

        Builder x(float f2) {
            this.selectScreenSize = f2;
            return this;
        }

        Builder y(int i) {
            this.sizeClass = i;
            return this;
        }

        Builder z(int i) {
            this.value = i;
            return this;
        }
    }

    static {
        ShipType[] values;
        for (ShipType shipType : values()) {
            lookup.put(shipType.id, shipType);
        }
    }

    ShipType(Builder builder) {
        this.id = builder.value;
        this.scale = builder.scale;
        this.selectScreenSize = builder.selectScreenSize;
        this.battleScreenSize = builder.battleScreenSize;
        this.classNames = builder.classNames;
        this.commandPointCost = builder.commandPointCost;
        this.isCombatShip = builder.isCombatShip;
        this.baseProductionCost = builder.baseProductionCost;
        this.baseAvailableSpace = builder.baseAvailableSpace;
        this.numberOfComponents = builder.numberOfComponents;
        this.description = builder.description;
        this.sizeClass = builder.sizeClass;
        this.initiative = builder.initiative;
    }

    public static ShipType getShipType(int i) {
        return lookup.get(i);
    }

    public static ShipType[] getShipTypesForShips() {
        return new ShipType[]{SCOUT, COLONY, CONSTRUCTION, TRANSPORT, DESTROYER, CRUISER, BATTLESHIP, DREADNOUGHT};
    }

    public int getBaseAvailableSpace() {
        return this.baseAvailableSpace;
    }

    public int getBaseProductionCost() {
        return this.baseProductionCost;
    }

    public float getBattleScreenSize() {
        return this.battleScreenSize;
    }

    public int getCommandPointCost() {
        return this.commandPointCost;
    }

    public String getDescription() {
        return GameData.activity.getString(this.description);
    }

    public int getIcon(int i) {
        return getIcon(i, 0);
    }

    public int getInitiative() {
        return this.initiative;
    }

    public int getNumberOfComponents() {
        return this.numberOfComponents;
    }

    public float getScale() {
        return this.scale;
    }

    public float getSelectScreenSize() {
        return this.selectScreenSize;
    }

    public int getSizeClass() {
        return this.sizeClass;
    }

    public String getString(int i) {
        if (Game.options.isOn(OptionID.MODDING) && i < 7 && !isStation() && Game.modValues.getShipName(i, this) != null) {
            return Game.modValues.getShipName(i, this);
        }
        return GameData.activity.getString(this.classNames[i]);
    }

    public boolean isCombatShip() {
        return this.isCombatShip;
    }

    public boolean isStation() {
        return this == STAR_BASE || this == TORPEDO_TURRET;
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x00a2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int getIcon(int i, int i2) {
        if (i == 8 || i == 9) {
            return 0;
        }
        switch (AnonymousClass1.f1566a[ordinal()]) {
            case 1:
                return GameIconEnum.TORPEDO_TURRET.ordinal();
            case 2:
                return GameIconEnum.STAR_BASE.ordinal();
            case 3:
                if (i2 != 0) {
                    if (i2 != 1) {
                        if (i2 != 2) {
                            if (i2 != 3) {
                                if (i2 == 4) {
                                    return ordinal() + 3;
                                }
                                if (i2 == 0) {
                                    if (i2 != 1) {
                                        if (i2 != 2) {
                                            if (i2 != 3) {
                                                if (i2 == 4) {
                                                    return ordinal() + 2;
                                                }
                                                if (i2 == 0) {
                                                    if (i2 != 1) {
                                                        if (i2 != 2) {
                                                            if (i2 != 3) {
                                                                if (i2 == 4) {
                                                                    return ordinal() + 1;
                                                                }
                                                                if (i2 == 0) {
                                                                    if (i2 != 1) {
                                                                        if (i2 != 2) {
                                                                            if (i2 != 3) {
                                                                                if (i2 != 4) {
                                                                                    return 5;
                                                                                }
                                                                                return ordinal();
                                                                            }
                                                                            return ordinal() - 4;
                                                                        }
                                                                        return ordinal() - 3;
                                                                    }
                                                                    return ordinal() - 2;
                                                                }
                                                                return ordinal() - 1;
                                                            }
                                                            return ordinal() - 3;
                                                        }
                                                        return ordinal() - 2;
                                                    }
                                                    return ordinal();
                                                }
                                                return ordinal() - 1;
                                            }
                                            return ordinal() + 1;
                                        }
                                        return ordinal() - 2;
                                    }
                                    return ordinal();
                                }
                                return ordinal() - 1;
                            }
                            return ordinal() + 2;
                        }
                        return ordinal() + 1;
                    }
                    return ordinal();
                }
                return ordinal() - 1;
            case 4:
                if (i2 == 0) {
                }
                break;
            case 5:
                if (i2 == 0) {
                }
                break;
            case 6:
                if (i2 == 0) {
                }
                break;
            case 7:
                return 5;
            case 8:
                return 6;
            case 9:
                return 7;
            case 10:
                return 8;
            default:
                throw new AssertionError("Invalid ship type");
        }
    }
}
