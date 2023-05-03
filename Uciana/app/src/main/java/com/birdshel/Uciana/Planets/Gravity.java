package com.birdshel.Uciana.Planets;

import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.R;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public enum Gravity {
    HIGH(new Builder().h(0.5f).f(R.string.gravity_high).e(-10).g(35)),
    LOW(new Builder().h(0.75f).f(R.string.gravity_low).e(-5).g(36)),
    NORMAL(new Builder().h(1.0f).f(R.string.gravity_normal).e(5).g(37));
    
    private final int calculationValue;
    private final int displayName;
    private final int infoIconIndex;
    private final float modifier;

    /* compiled from: MyApplication */
    /* loaded from: classes.dex */
    private static class Builder {
        private int calculationValue;
        private int displayName;
        private int infoIconIndex;
        private float modifier;

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

        Builder h(float f2) {
            this.modifier = f2;
            return this;
        }
    }

    Gravity(Builder builder) {
        this.modifier = builder.modifier;
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

    public float getModifier() {
        return this.modifier;
    }
}
