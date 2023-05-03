package com.birdshel.Uciana.Planets;

import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.R;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public enum MineralRichness {
    VERY_POOR(new Builder().h(1).f(R.string.mineral_richness_very_poor).e(-10).g(29)),
    POOR(new Builder().h(2).f(R.string.mineral_richness_poor).e(-5).g(30)),
    ABUNDANT(new Builder().h(3).f(R.string.mineral_richness_abundant).e(5).g(31)),
    RICH(new Builder().h(5).f(R.string.mineral_richness_rich).e(10).g(32)),
    VERY_RICH(new Builder().h(7).f(R.string.mineral_richness_very_rich).e(20).g(33)),
    ULTRA_RICH(new Builder().h(8).f(R.string.mineral_richness_ultra_rich).e(25).g(34));
    
    private final int calculationValue;
    private final int displayName;
    private final int infoIconIndex;
    private final int productionPerWorker;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: MyApplication */
    /* loaded from: classes.dex */
    public static class Builder {
        private int calculationValue;
        private int displayName;
        private int infoIconIndex;
        private int productionPerWorker;

        Builder e(int i) {
            this.calculationValue = i;
            return this;
        }

        Builder f(int i) {
            this.displayName = i;
            return this;
        }

        Builder g(int i) {
            this.infoIconIndex = i;
            return this;
        }

        Builder h(int i) {
            this.productionPerWorker = i;
            return this;
        }
    }

    MineralRichness(Builder builder) {
        this.productionPerWorker = builder.productionPerWorker;
        this.displayName = builder.displayName;
        this.calculationValue = builder.calculationValue;
        this.infoIconIndex = builder.infoIconIndex;
    }

    public int getAsteroidBonusPercent() {
        return (ordinal() + 1) * 5;
    }

    public int getCalculationValue() {
        return this.calculationValue;
    }

    public String getDisplayName() {
        return GameData.activity.getString(this.displayName);
    }

    public int getInfoIconIndex() {
        return this.infoIconIndex;
    }

    public MineralRichness getNext() {
        return values()[ordinal() + 1];
    }

    public MineralRichness getPrevious() {
        return values()[ordinal() - 1];
    }

    public float getProductionPerWorker() {
        return this.productionPerWorker;
    }
}
