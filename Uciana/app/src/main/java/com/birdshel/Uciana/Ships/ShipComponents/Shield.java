package com.birdshel.Uciana.Ships.ShipComponents;

import com.birdshel.Uciana.Ships.ShipComponents.ShipComponent;
import com.birdshel.Uciana.Technology.TechID;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Shield extends ShipComponent {
    private final int absorption;
    private final float rechargeRate;
    private final int strengthMultiplier;

    /* compiled from: MyApplication */
    /* loaded from: classes.dex */
    public static class Builder extends ShipComponent.Builder {
        int j;
        int k;
        float l;

        public Builder absorption(int i) {
            this.k = i;
            return this;
        }

        public Builder rechargeRate(float f2) {
            this.l = f2;
            return this;
        }

        public Builder strengthMultiplier(int i) {
            this.j = i;
            return this;
        }

        @Override // com.birdshel.Uciana.Ships.ShipComponents.ShipComponent.Builder
        public Shield build() {
            return new Shield(this);
        }

        @Override // com.birdshel.Uciana.Ships.ShipComponents.ShipComponent.Builder
        public Builder count(int i) {
            super.count(i);
            return this;
        }

        @Override // com.birdshel.Uciana.Ships.ShipComponents.ShipComponent.Builder
        public Builder description(int i) {
            super.description(i);
            return this;
        }

        @Override // com.birdshel.Uciana.Ships.ShipComponents.ShipComponent.Builder
        public Builder iconIndex(int i) {
            super.iconIndex(i);
            return this;
        }

        @Override // com.birdshel.Uciana.Ships.ShipComponents.ShipComponent.Builder
        public Builder id(ShipComponentID shipComponentID) {
            super.id(shipComponentID);
            return this;
        }

        @Override // com.birdshel.Uciana.Ships.ShipComponents.ShipComponent.Builder
        public Builder name(int i) {
            super.name(i);
            return this;
        }

        @Override // com.birdshel.Uciana.Ships.ShipComponents.ShipComponent.Builder
        public Builder requiredTechID(TechID techID) {
            super.requiredTechID(techID);
            return this;
        }

        @Override // com.birdshel.Uciana.Ships.ShipComponents.ShipComponent.Builder
        public Builder shortName(int i) {
            super.shortName(i);
            return this;
        }

        @Override // com.birdshel.Uciana.Ships.ShipComponents.ShipComponent.Builder
        public Builder spaceRequired(int i) {
            super.spaceRequired(i);
            return this;
        }
    }

    public Shield(Builder builder) {
        super(builder);
        this.strengthMultiplier = builder.j;
        this.absorption = builder.k;
        this.rechargeRate = builder.l;
    }

    public int getAbsorption() {
        return this.absorption;
    }

    public float getRechargeRate() {
        return this.rechargeRate;
    }

    public int getStrengthMultiplier() {
        return this.strengthMultiplier;
    }

    public Shield(Shield shield) {
        super(shield);
        this.strengthMultiplier = shield.strengthMultiplier;
        this.absorption = shield.absorption;
        this.rechargeRate = shield.rechargeRate;
    }
}
