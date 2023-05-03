package com.birdshel.Uciana.StarSystems;

import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class SpaceRift extends GalacticObject {
    /* JADX INFO: Access modifiers changed from: package-private */
    public SpaceRift(int i, Point point) {
        super(i, point, 0.0f, true, 0.0f);
        c(Functions.getRandom(40, 60));
        b(Functions.percent(50));
        d(Functions.getRandom(15.0f, 40.0f));
    }

    private int geAffectedRange() {
        return GameData.galaxy.getDistanceConst() * 7;
    }

    public boolean checkInRangeOf(Point point) {
        return point.getDistance(a()) < geAffectedRange();
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

    @Override // com.birdshel.Uciana.StarSystems.GalacticObject
    public /* bridge */ /* synthetic */ float getSize(GalaxySize galaxySize) {
        return super.getSize(galaxySize);
    }

    public SpaceRift(int i, Point point, float f2, boolean z, float f3) {
        super(i, point, f2, z, f3);
    }
}
