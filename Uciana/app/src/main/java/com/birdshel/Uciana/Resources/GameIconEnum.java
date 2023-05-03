package com.birdshel.Uciana.Resources;

import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.StarSystems.GalaxySize;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public enum GameIconEnum {
    MOVE,
    CLOSE,
    TERRAFORMING,
    WAR,
    TRADEGOODS,
    BUY_PRODUCTION,
    SYSTEM_CONTROL,
    OUTPOST,
    SHIP_YARDS,
    SCIENCE_STATION,
    RED_EMPIRE_BANNER,
    GREEN_EMPIRE_BANNER,
    BLUE_EMPIRE_BANNER,
    ORANGE_EMPIRE_BANNER,
    YELLOW_EMPIRE_BANNER,
    PURPLE_EMPIRE_BANNER,
    SYSTEM_SEARCH,
    SPACE_RIFT,
    BUILDINGS,
    SHIPS,
    ADD,
    UNLOAD,
    BOTTOM_WORMHOLE_LAYER,
    TOP_WORMHOLE_LAYER,
    UP_ARROW,
    DOWN_ARROW,
    SCANNING,
    FUEL,
    STAR_BASE,
    THRUSTER,
    CAPITAL,
    GRAVITY_BUILDING,
    POWER_BUILDING,
    DEFENCE_BUILDING,
    HAPPINESS_BUILDING,
    POPULATION_BUILDING,
    FOOD_BUILDING,
    PRODUCTION_BUILDING,
    SCIENCE_BUILDING,
    MONEY_BUILDING,
    ASTEROID1,
    ASTEROID2,
    ASTEROID3,
    ASTEROID4,
    ASTEROID5,
    ASTEROID6,
    ASTEROID7,
    CYAN_EMPIRE_BANNER,
    MOVE_PEOPLE,
    INCOMING_POPULATION,
    SPY,
    MINUS,
    PHYSICS,
    POPULATION,
    PEACE,
    HEX_GRID,
    MOVE_HEX_GRID,
    SELECTED_HEX_GRID,
    CHEMISTRY,
    TRADE_TREATY,
    NON_AGGRESSION_TREATY,
    TRIBUTE_TREATY,
    MINING_STATION,
    ATTACK_HEX_GRID,
    ASCENDED_EMPIRE_BANNER,
    SMALL_GALAXY,
    MED_GALAXY,
    LARGE_GALAXY,
    ALLIANCE,
    RESEARCH_TREATY,
    NONE,
    SHOCK_WAVE,
    DEBRIS,
    GOOGLE_PLAY_LOGIN,
    ACHIEVEMENTS,
    TORPEDO_TURRET,
    SPECIAL_HEX_GRID,
    SPACIAL_CHARGE,
    SUBSPACE_CHARGE,
    DIMENSIONAL_CHARGE;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Resources.GameIconEnum$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a */
        static final /* synthetic */ int[] f1412a;

        static {
            int[] iArr = new int[GalaxySize.values().length];
            f1412a = iArr;
            try {
                iArr[GalaxySize.SMALL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1412a[GalaxySize.MEDIUM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1412a[GalaxySize.LARGE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static int getEmpireBanner(int i) {
        if (i == 8) {
            return ASCENDED_EMPIRE_BANNER.ordinal();
        }
        if (i == 9) {
            return SPACE_RIFT.ordinal();
        }
        int bannerID = GameData.empires.get(i).getBannerID();
        return bannerID == 6 ? CYAN_EMPIRE_BANNER.ordinal() : bannerID + 10;
    }

    public static int getEmpireBannerNonEmpireLookup(int i) {
        if (i == 8) {
            return ASCENDED_EMPIRE_BANNER.ordinal();
        }
        if (i == 9) {
            return SPACE_RIFT.ordinal();
        }
        return i == 6 ? CYAN_EMPIRE_BANNER.ordinal() : i + 10;
    }

    public static int getGalaxySizeIcon(GalaxySize galaxySize) {
        int i = AnonymousClass1.f1412a[galaxySize.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    return SMALL_GALAXY.ordinal();
                }
                return LARGE_GALAXY.ordinal();
            }
            return MED_GALAXY.ordinal();
        }
        return SMALL_GALAXY.ordinal();
    }
}
