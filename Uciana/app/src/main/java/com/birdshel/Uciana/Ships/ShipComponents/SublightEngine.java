package com.birdshel.Uciana.Ships.ShipComponents;

import com.birdshel.Uciana.Technology.TechID;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class SublightEngine extends ShipComponent {
    private final int combatSpeed;

    /* compiled from: MyApplication */
    /* loaded from: classes.dex */
    public static class Builder extends ShipComponent.Builder {
        int j;

        public Builder combatSpeed(int i) {
            this.j = i;
            return this;
        }

        @Override // com.birdshel.Uciana.Ships.ShipComponents.ShipComponent.Builder
        public SublightEngine build() {
            return new SublightEngine(this);
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

    public SublightEngine(Builder builder) {
        super(builder);
        this.combatSpeed = builder.j;
    }

    public int getCombatSpeed() {
        return this.combatSpeed;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SublightEngine(SublightEngine sublightEngine) {
        super(sublightEngine);
        this.combatSpeed = sublightEngine.combatSpeed;
    }
}
