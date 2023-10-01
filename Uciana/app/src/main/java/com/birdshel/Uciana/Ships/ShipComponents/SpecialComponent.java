package com.birdshel.Uciana.Ships.ShipComponents;

import com.birdshel.Uciana.Technology.TechID;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class SpecialComponent extends ShipComponent {
    private final boolean allowMoreThenOne;
    private final float effectValue;
    private final boolean showOnShipControl;

    /* compiled from: MyApplication */
    /* loaded from: classes.dex */
    public static class Builder extends ShipComponent.Builder {
        boolean j;
        boolean k;
        float l;

        public Builder allowMoreThenOne(boolean z) {
            this.k = z;
            return this;
        }

        public Builder effectValue(float f2) {
            this.l = f2;
            return this;
        }

        public Builder showOnShipControl(boolean z) {
            this.j = z;
            return this;
        }

        @Override // com.birdshel.Uciana.Ships.ShipComponents.ShipComponent.Builder
        public SpecialComponent build() {
            return new SpecialComponent(this);
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
        public Builder productionCost(int i) {
            super.productionCost(i);
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

    public SpecialComponent(Builder builder) {
        super(builder);
        this.showOnShipControl = builder.j;
        this.allowMoreThenOne = builder.k;
        this.effectValue = builder.l;
    }

    public boolean allowMoreThenOne() {
        return this.allowMoreThenOne;
    }

    public float getEffectValue() {
        return this.effectValue;
    }

    public boolean showOnShipControl() {
        return this.showOnShipControl;
    }

    public SpecialComponent(SpecialComponent specialComponent) {
        super(specialComponent);
        this.showOnShipControl = specialComponent.showOnShipControl;
        this.allowMoreThenOne = specialComponent.allowMoreThenOne;
        this.effectValue = specialComponent.effectValue;
    }
}
