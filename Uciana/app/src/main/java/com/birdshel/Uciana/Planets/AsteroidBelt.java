package com.birdshel.Uciana.Planets;

import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Planets.SystemObject;
import com.birdshel.Uciana.StarSystems.StarType;
import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class AsteroidBelt extends SystemObject {
    private final MineralRichness mineralRichness;

    /* compiled from: MyApplication */
    /* loaded from: classes.dex */
    public static class Builder extends SystemObject.Builder {
        private MineralRichness mineralRichness;

        public Builder() {
            this.b = SystemObjectType.ASTEROID_BELT;
        }

        public AsteroidBelt buildNew(StarType starType) {
            this.f1404f = Functions.random.nextInt(3);
            this.mineralRichness = starType.getMineralRichness();
            this.g = 0;
            return new AsteroidBelt(this);
        }

        public Builder mineralRichness(MineralRichness mineralRichness) {
            this.mineralRichness = mineralRichness;
            return this;
        }

        @Override // com.birdshel.Uciana.Planets.SystemObject.Builder
        public /* bridge */ /* synthetic */ SystemObject.Builder name(String str) {
            return super.name(str);
        }

        @Override // com.birdshel.Uciana.Planets.SystemObject.Builder
        public /* bridge */ /* synthetic */ SystemObject.Builder resources(List list) {
            return resources((List<ResourceID>) list);
        }

        @Override // com.birdshel.Uciana.Planets.SystemObject.Builder
        public AsteroidBelt build() {
            return new AsteroidBelt(this);
        }

        @Override // com.birdshel.Uciana.Planets.SystemObject.Builder
        public Builder exploredValue(int i) {
            super.exploredValue(i);
            return this;
        }

        @Override // com.birdshel.Uciana.Planets.SystemObject.Builder
        public Builder imageIndex(int i) {
            super.imageIndex(i);
            return this;
        }

        @Override // com.birdshel.Uciana.Planets.SystemObject.Builder
        public Builder orbit(int i) {
            super.orbit(i);
            return this;
        }

        @Override // com.birdshel.Uciana.Planets.SystemObject.Builder
        public Builder resources(List<ResourceID> list) {
            super.resources(list);
            return this;
        }

        @Override // com.birdshel.Uciana.Planets.SystemObject.Builder
        public Builder systemID(int i) {
            super.systemID(i);
            return this;
        }
    }

    public AsteroidBelt(Builder builder) {
        super(builder);
        this.mineralRichness = builder.mineralRichness;
    }

    public MineralRichness getMineralRichness() {
        return this.mineralRichness;
    }

    public int getScore() {
        return this.mineralRichness.getCalculationValue();
    }
}
