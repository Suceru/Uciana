package com.birdshel.Uciana.Planets;

import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.R;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public enum PlanetSize {
    SMALL(new Builder().r(0.325f).o(1.1f).q(0.2f).p(2).m(10).l(5).k(R.string.planet_size_small).j(-5).n(25)),
    MED(new Builder().r(0.45f).o(1.45f).q(0.3f).p(3).m(20).l(25).k(R.string.planet_size_medium).j(0).n(26)),
    LARGE(new Builder().r(0.575f).o(1.8f).q(0.4f).p(4).m(30).l(45).k(R.string.planet_size_large).j(5).n(27)),
    EXTRA_LARGE(new Builder().r(0.7f).o(2.1f).q(0.5f).p(5).m(45).l(55).k(R.string.planet_size_extra_large).j(10).n(28)),
    HUGE(new Builder().r(0.85f).o(2.3f).q(0.6f).p(6).m(50).l(55).k(R.string.planet_size_huge).j(15).n(29));
    
    private final int calculationValue;
    private final int displayName;
    private final int hasMoonPercent;
    private final int hasRingPercent;
    private final int infoIconIndex;
    private final float planetScale;
    private final int populationModifier;
    private final float previewScale;
    private final float systemScale;

    /* compiled from: MyApplication */
    /* loaded from: classes.dex */
    private static class Builder {
        private int calculationValue;
        private int displayName;
        private int hasMoonPercent;
        private int hasRingPercent;
        private int infoIconIndex;
        private float planetScale;
        private int populationModifier;
        private float previewScale;
        private float systemScale;

        Builder j(int i) {
            this.calculationValue = i;
            return this;
        }

        Builder k(int i) {
            this.displayName = i;
            return this;
        }

        Builder l(int i) {
            this.hasMoonPercent = i;
            return this;
        }

        Builder m(int i) {
            this.hasRingPercent = i;
            return this;
        }

        Builder n(int i) {
            this.infoIconIndex = i;
            return this;
        }

        Builder o(float f2) {
            this.planetScale = f2;
            return this;
        }

        Builder p(int i) {
            this.populationModifier = i;
            return this;
        }

        Builder q(float f2) {
            this.previewScale = f2;
            return this;
        }

        Builder r(float f2) {
            this.systemScale = f2;
            return this;
        }
    }

    PlanetSize(Builder builder) {
        this.systemScale = builder.systemScale;
        this.planetScale = builder.planetScale;
        this.previewScale = builder.previewScale;
        this.populationModifier = builder.populationModifier;
        this.hasRingPercent = builder.hasRingPercent;
        this.hasMoonPercent = builder.hasMoonPercent;
        this.displayName = builder.displayName;
        this.calculationValue = builder.calculationValue;
        this.infoIconIndex = builder.infoIconIndex;
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

    public float getPlanetValue() {
        return this.planetScale;
    }

    public int getPopulationModifier() {
        return this.populationModifier;
    }

    public float getPreviewValue() {
        return this.previewScale;
    }

    public float getSystemValue() {
        return this.systemScale;
    }

    public boolean hasMoon() {
        return Functions.percent(this.hasMoonPercent);
    }

    public boolean hasRing() {
        return Functions.percent(this.hasRingPercent);
    }
}
