package com.birdshel.Uciana.RandomEvents;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum NONE uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:444)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:391)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:320)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:258)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* compiled from: MyApplication */
/* loaded from: classes.dex */
public final class RandomEventType {
    private static final /* synthetic */ RandomEventType[] $VALUES;
    public static final RandomEventType COLONY_REVOLT;
    public static final RandomEventType COMPUTER_VIRUS;
    public static final RandomEventType CREDITS_DONATION;
    public static final RandomEventType HYPER_SPACE_FLUCTUATIONS;
    public static final RandomEventType IMPROVED_CLIMATE;
    public static final RandomEventType MINERAL_DEPOSITS_DEPLETED;
    public static final RandomEventType NEW_DEPOSITS_FOUND;
    public static final RandomEventType NONE;
    public static final RandomEventType RESEARCH_BREAKTHROUGH;
    public static final RandomEventType SPACE_MONSTER;
    public static final RandomEventType STOLEN_CREDITS;
    public static final RandomEventType WORMHOLE_COLLAPSE;
    public static final RandomEventType WORMHOLE_CREATION;
    public static final RandomEventType WORSENED_CLIMATE;
    private final RandomEventRarity rarity;

    static {
        RandomEventRarity randomEventRarity = RandomEventRarity.COMMON;
        RandomEventType randomEventType = new RandomEventType("NONE", 0, randomEventRarity);
        NONE = randomEventType;
        RandomEventRarity randomEventRarity2 = RandomEventRarity.VERY_RARE;
        RandomEventType randomEventType2 = new RandomEventType("WORMHOLE_CREATION", 1, randomEventRarity2);
        WORMHOLE_CREATION = randomEventType2;
        RandomEventRarity randomEventRarity3 = RandomEventRarity.RARE;
        RandomEventType randomEventType3 = new RandomEventType("IMPROVED_CLIMATE", 2, randomEventRarity3);
        IMPROVED_CLIMATE = randomEventType3;
        RandomEventType randomEventType4 = new RandomEventType("WORSENED_CLIMATE", 3, randomEventRarity2);
        WORSENED_CLIMATE = randomEventType4;
        RandomEventType randomEventType5 = new RandomEventType("COMPUTER_VIRUS", 4, randomEventRarity);
        COMPUTER_VIRUS = randomEventType5;
        RandomEventRarity randomEventRarity4 = RandomEventRarity.UNCOMMON;
        RandomEventType randomEventType6 = new RandomEventType("RESEARCH_BREAKTHROUGH", 5, randomEventRarity4);
        RESEARCH_BREAKTHROUGH = randomEventType6;
        RandomEventType randomEventType7 = new RandomEventType("NEW_DEPOSITS_FOUND", 6, randomEventRarity3);
        NEW_DEPOSITS_FOUND = randomEventType7;
        RandomEventType randomEventType8 = new RandomEventType("MINERAL_DEPOSITS_DEPLETED", 7, randomEventRarity2);
        MINERAL_DEPOSITS_DEPLETED = randomEventType8;
        RandomEventType randomEventType9 = new RandomEventType("STOLEN_CREDITS", 8, randomEventRarity4);
        STOLEN_CREDITS = randomEventType9;
        RandomEventType randomEventType10 = new RandomEventType("CREDITS_DONATION", 9, randomEventRarity);
        CREDITS_DONATION = randomEventType10;
        RandomEventType randomEventType11 = new RandomEventType("COLONY_REVOLT", 10, randomEventRarity2);
        COLONY_REVOLT = randomEventType11;
        RandomEventType randomEventType12 = new RandomEventType("HYPER_SPACE_FLUCTUATIONS", 11, randomEventRarity2);
        HYPER_SPACE_FLUCTUATIONS = randomEventType12;
        RandomEventType randomEventType13 = new RandomEventType("SPACE_MONSTER", 12, randomEventRarity3);
        SPACE_MONSTER = randomEventType13;
        RandomEventType randomEventType14 = new RandomEventType("WORMHOLE_COLLAPSE", 13, RandomEventRarity.ULTRA_RARE);
        WORMHOLE_COLLAPSE = randomEventType14;
        $VALUES = new RandomEventType[]{randomEventType, randomEventType2, randomEventType3, randomEventType4, randomEventType5, randomEventType6, randomEventType7, randomEventType8, randomEventType9, randomEventType10, randomEventType11, randomEventType12, randomEventType13, randomEventType14};
    }

    private RandomEventType(String str, int i, RandomEventRarity randomEventRarity) {
        this.rarity = randomEventRarity;
    }

    public static RandomEventType valueOf(String str) {
        return (RandomEventType) Enum.valueOf(RandomEventType.class, str);
    }

    public static RandomEventType[] values() {
        return (RandomEventType[]) $VALUES.clone();
    }

    public RandomEventRarity getRarity() {
        return this.rarity;
    }
}
