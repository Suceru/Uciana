package com.birdshel.Uciana.Planets;

import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class EmptyOrbit extends SystemObject {

    /* compiled from: MyApplication */
    /* loaded from: classes.dex */
    public static class Builder extends SystemObject.Builder {
        public Builder(int i, int i2) {
            this.b = SystemObjectType.NONE;
            this.f1401c = i;
            this.f1402d = i2;
        }

        @Override // com.birdshel.Uciana.Planets.SystemObject.Builder
        public /* bridge */ /* synthetic */ SystemObject.Builder exploredValue(int i) {
            return super.exploredValue(i);
        }

        @Override // com.birdshel.Uciana.Planets.SystemObject.Builder
        public /* bridge */ /* synthetic */ SystemObject.Builder imageIndex(int i) {
            return super.imageIndex(i);
        }

        @Override // com.birdshel.Uciana.Planets.SystemObject.Builder
        public /* bridge */ /* synthetic */ SystemObject.Builder name(String str) {
            return super.name(str);
        }

        @Override // com.birdshel.Uciana.Planets.SystemObject.Builder
        public /* bridge */ /* synthetic */ SystemObject.Builder orbit(int i) {
            return super.orbit(i);
        }

        @Override // com.birdshel.Uciana.Planets.SystemObject.Builder
        public /* bridge */ /* synthetic */ SystemObject.Builder resources(List list) {
            return super.resources(list);
        }

        @Override // com.birdshel.Uciana.Planets.SystemObject.Builder
        public /* bridge */ /* synthetic */ SystemObject.Builder systemID(int i) {
            return super.systemID(i);
        }

        @Override // com.birdshel.Uciana.Planets.SystemObject.Builder
        public EmptyOrbit build() {
            return new EmptyOrbit(this);
        }
    }

    private EmptyOrbit(Builder builder) {
        super(builder);
    }
}
