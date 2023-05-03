package com.birdshel.Uciana.Resources;

import com.birdshel.Uciana.Ships.ShipType;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public enum InfoIconEnum {
    FOOD,
    COMMAND_POINTS,
    CREDITS,
    SCIENCE,
    DISCOVERY,
    HAPPINESS,
    POWER,
    TAX_RATE,
    MAINTENANCE_COST,
    IMPORTED_FOOD,
    STARVING,
    BLOCKADE,
    WARNING,
    SCOUT_ICON,
    COLONY_ICON,
    WORKER_ICON,
    TRANSPORT_ICON,
    DESTROYER_ICON,
    CRUISER_ICON,
    BATTLESHIP_ICON,
    DREADNOUGHT_ICON,
    SHIP,
    PRODUCTION,
    FTL_DISABLED,
    SUBLIGHT_DISABLED,
    POP_GROWTH,
    DEFENSE,
    TURN,
    INFANTRY,
    ATTACK,
    TURKISH,
    SHIELD,
    POWER_WARNING,
    CAPITAL,
    BOMB_SHIELDING,
    POPULATION,
    BIO,
    STAR_BASE,
    INFO,
    ADJUSTED,
    RENAME,
    ARMOR,
    SPACE,
    EMPTY_COLONY_WARNING,
    TURRET_ICON,
    RESISTANCE,
    RED_EMPIRE,
    GREEN_EMPIRE,
    BLUE_EMPIRE,
    ORANGE_EMPIRE,
    YELLOW_EMPIRE,
    PURPLE_EMPIRE,
    CYAN_EMPIRE,
    OFF,
    ON,
    BEAM_WEAPON,
    ENGLISH,
    GERMAN,
    FRENCH,
    SPANISH,
    RUSSIAN,
    PORTUGUESE,
    COLONY_SHIP_ARRIVED,
    JAPANESE,
    POLISH,
    ALLIANCE,
    WAR,
    UNKNOWN_EMPIRE,
    REPEAT,
    ITALIAN,
    RANDOM_EVENT,
    NOT_ALLOWED,
    KOREAN,
    RANDOM,
    CUSTOM,
    LEFT_ARROW,
    EMPIRE_BACKGROUND;

    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Resources.InfoIconEnum$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1418a;

        static {
            int[] iArr = new int[ShipType.values().length];
            f1418a = iArr;
            try {
                iArr[ShipType.SCOUT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1418a[ShipType.COLONY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1418a[ShipType.CONSTRUCTION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1418a[ShipType.TRANSPORT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1418a[ShipType.DESTROYER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1418a[ShipType.CRUISER.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1418a[ShipType.BATTLESHIP.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1418a[ShipType.DREADNOUGHT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1418a[ShipType.STAR_BASE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f1418a[ShipType.TORPEDO_TURRET.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    public static int getEmpireIcon(int i) {
        return RED_EMPIRE.ordinal() + i;
    }

    public static int getLangIcon(String str) {
        if (str.equals(SupportedLocales.GERMAN.getLetters())) {
            return GERMAN.ordinal();
        }
        if (str.equals(SupportedLocales.RUSSIAN.getLetters())) {
            return RUSSIAN.ordinal();
        }
        if (str.equals(SupportedLocales.JAPANESE.getLetters())) {
            return JAPANESE.ordinal();
        }
        if (str.equals(SupportedLocales.SPANISH.getLetters())) {
            return SPANISH.ordinal();
        }
        if (str.equals(SupportedLocales.FRENCH.getLetters())) {
            return FRENCH.ordinal();
        }
        if (str.equals(SupportedLocales.POLISH.getLetters())) {
            return POLISH.ordinal();
        }
        if (str.equals(SupportedLocales.PORTUGUESE.getLetters())) {
            return PORTUGUESE.ordinal();
        }
        if (str.equals(SupportedLocales.ITALIAN.getLetters())) {
            return ITALIAN.ordinal();
        }
        if (str.equals(SupportedLocales.TURKISH.getLetters())) {
            return TURKISH.ordinal();
        }
        if (str.equals(SupportedLocales.KOREAN.getLetters())) {
            return KOREAN.ordinal();
        }
        return ENGLISH.ordinal();
    }

    public static int getShipIcon(ShipType shipType) {
        switch (AnonymousClass1.f1418a[shipType.ordinal()]) {
            case 1:
                return SCOUT_ICON.ordinal();
            case 2:
                return COLONY_ICON.ordinal();
            case 3:
                return WORKER_ICON.ordinal();
            case 4:
                return TRANSPORT_ICON.ordinal();
            case 5:
                return DESTROYER_ICON.ordinal();
            case 6:
                return CRUISER_ICON.ordinal();
            case 7:
                return BATTLESHIP_ICON.ordinal();
            case 8:
                return DREADNOUGHT_ICON.ordinal();
            case 9:
                return STAR_BASE.ordinal();
            case 10:
                return TURRET_ICON.ordinal();
            default:
                throw new AssertionError("Unknown ShipType when getting ship type icon");
        }
    }
}
