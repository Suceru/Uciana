package com.birdshel.Uciana.Ships.ShipComponents;

import com.birdshel.Uciana.Technology.TechID;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class TargetingComputer extends ShipComponent {
    private final int damageBonus;
    private final int targetingBonus;

    /* compiled from: MyApplication */
    /* loaded from: classes.dex */
    public static class Builder extends ShipComponent.Builder {
        int j;
        int k;

        public Builder damageBonus(int i) {
            this.k = i;
            return this;
        }

        public Builder targetingBonus(int i) {
            this.j = i;
            return this;
        }

        @Override // com.birdshel.Uciana.Ships.ShipComponents.ShipComponent.Builder
        public TargetingComputer build() {
            return new TargetingComputer(this);
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

    public TargetingComputer(Builder builder) {
        super(builder);
        this.targetingBonus = builder.j;
        this.damageBonus = builder.k;
    }

    public int getDamageBonus() {
        return this.damageBonus;
    }

    public int getTargetingBonus() {
        return this.targetingBonus;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TargetingComputer(TargetingComputer targetingComputer) {
        super(targetingComputer);
        this.targetingBonus = targetingComputer.targetingBonus;
        this.damageBonus = targetingComputer.damageBonus;
    }
}
