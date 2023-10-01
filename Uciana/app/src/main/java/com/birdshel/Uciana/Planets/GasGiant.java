package com.birdshel.Uciana.Planets;

import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Scenes.ExtendedScene;
import com.birdshel.Uciana.Scenes.FleetsScene;
import com.birdshel.Uciana.Scenes.GalaxyScene;
import com.birdshel.Uciana.Scenes.PlanetScene;
import com.birdshel.Uciana.Scenes.SystemScene;

import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class GasGiant extends SystemObject {
    private final boolean hasMoon;
    private final boolean hasRing;
    private final Moon moon;
    private final int ringImageIndex;
    private final PlanetSize size;

    /* compiled from: MyApplication */
    /* loaded from: classes.dex */
    public static class Builder extends SystemObject.Builder {
        private boolean hasMoon;
        private boolean hasRing;
        private Moon moon;
        private int ringImageIndex;
        private PlanetSize size;

        public Builder() {
            this.b = SystemObjectType.GAS_GIANT;
        }

        public GasGiant buildNew() {
            this.f1404f = Functions.random.nextInt(3);
            this.ringImageIndex = Functions.random.nextInt(2);
            this.size = PlanetSize.HUGE;
            if (Functions.percent(33)) {
                this.size = PlanetSize.EXTRA_LARGE;
            }
            this.hasRing = this.size.hasRing();
            this.hasMoon = this.size.hasMoon();
            this.moon = new Moon();
            this.g = 0;
            return new GasGiant(this);
        }

        public Builder hasMoon(boolean z) {
            this.hasMoon = z;
            return this;
        }

        public Builder hasRing(boolean z) {
            this.hasRing = z;
            return this;
        }

        public Builder moon(Moon moon) {
            this.moon = moon;
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

        public Builder ringImageIndex(int i) {
            this.ringImageIndex = i;
            return this;
        }

        public Builder size(PlanetSize planetSize) {
            this.size = planetSize;
            return this;
        }

        @Override // com.birdshel.Uciana.Planets.SystemObject.Builder
        public GasGiant build() {
            return new GasGiant(this);
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

    public GasGiant(Builder builder) {
        super(builder);
        this.ringImageIndex = builder.ringImageIndex;
        this.size = builder.size;
        this.hasRing = builder.hasRing;
        this.hasMoon = builder.hasMoon;
        this.moon = builder.moon;
    }

    public Climate getClimate() {
        return Climate.GAS_GIANT;
    }

    public Moon getMoon() {
        return this.moon;
    }

    public int getRingImageIndex() {
        return this.ringImageIndex;
    }

    public float getScale(ExtendedScene extendedScene) {
        if (extendedScene instanceof PlanetScene) {
            return this.size.getPlanetValue();
        }
        if (extendedScene instanceof SystemScene) {
            return this.size.getSystemValue();
        }
        if (extendedScene instanceof GalaxyScene) {
            return this.size.getPreviewValue();
        }
        return extendedScene instanceof FleetsScene ? 0.2f : 1.0f;
    }

    public int getSciencePercentBonus() {
        int i = this.size == PlanetSize.EXTRA_LARGE ? 3 : 5;
        int i2 = this.f1398d * 3;
        return i + i2 + (this.hasRing ? 2 : 0) + (this.hasMoon ? 2 : 0);
    }

    public int getScore() {
        return getSciencePercentBonus();
    }

    public PlanetSize getSize() {
        return this.size;
    }

    public boolean hasMoon() {
        return this.hasMoon;
    }

    public boolean hasRing() {
        return this.hasRing;
    }
}
