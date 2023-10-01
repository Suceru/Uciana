package com.birdshel.Uciana.Ships.ShipComponents;

import com.birdshel.Uciana.Technology.TechID;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Armor extends ShipComponent {
    private final float armorMultiplier;

    /* compiled from: MyApplication */
    /* loaded from: classes.dex */
    public static class Builder extends ShipComponent.Builder {
        float j;

        public Builder armorMultiplier(float f2) {
            this.j = f2;
            return this;
        }

        @Override // com.birdshel.Uciana.Ships.ShipComponents.ShipComponent.Builder
        public Armor build() {
            return new Armor(this);
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

    public Armor(Builder builder) {
        super(builder);
        this.armorMultiplier = builder.j;
    }

    public float getArmorMultiplier() {
        return this.armorMultiplier;
    }

    public Armor(Armor armor) {
        super(armor);
        this.armorMultiplier = armor.armorMultiplier;
    }
}
