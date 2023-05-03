package com.birdshel.Uciana.StarSystems;

import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Blackhole extends GalacticObject {
    public Blackhole(int i, Point point) {
        super(i, point, 0.0f, true, 0.0f);
        c(Functions.getRandom(75, 110));
        b(Functions.percent(50));
        d(Functions.getRandom(1.5f, 4.5f));
    }

    private int getDilationRange() {
        return GameData.galaxy.getDistanceConst() * 7;
    }

    @Override // com.birdshel.Uciana.StarSystems.GalacticObject
    public /* bridge */ /* synthetic */ int getID() {
        return super.getID();
    }

    @Override // com.birdshel.Uciana.StarSystems.GalacticObject
    public /* bridge */ /* synthetic */ Point getPosition() {
        return super.getPosition();
    }

    @Override // com.birdshel.Uciana.StarSystems.GalacticObject
    public /* bridge */ /* synthetic */ float getSize() {
        return super.getSize();
    }

    @Override // com.birdshel.Uciana.StarSystems.GalacticObject
    public /* bridge */ /* synthetic */ float getSpinSpeed() {
        return super.getSpinSpeed();
    }

    @Override // com.birdshel.Uciana.StarSystems.GalacticObject
    public /* bridge */ /* synthetic */ float getUnmodifiedSize() {
        return super.getUnmodifiedSize();
    }

    @Override // com.birdshel.Uciana.StarSystems.GalacticObject
    public /* bridge */ /* synthetic */ boolean hasAltSpinDirection() {
        return super.hasAltSpinDirection();
    }

    public boolean isAffectedByTimeDilation(Point point) {
        return point.getDistance(a()) < getDilationRange();
    }

    @Override // com.birdshel.Uciana.StarSystems.GalacticObject
    public /* bridge */ /* synthetic */ float getSize(GalaxySize galaxySize) {
        return super.getSize(galaxySize);
    }

    public Blackhole(int i, Point point, float f2, boolean z, float f3) {
        super(i, point, f2, z, f3);
    }
}
