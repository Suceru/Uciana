package com.birdshel.Uciana.Ships;

import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponentID;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class SpacialCharge extends MovingWeapon {
    private final Point targetPosition;

    public SpacialCharge(String str, ShipComponentID shipComponentID, Point point, Point point2, int i, int i2) {
        this.f1549c = str;
        this.p = shipComponentID;
        this.targetPosition = point;
        this.q = point2;
        this.r = i;
        this.o = i2;
    }

    public ShipComponentID getChargeType() {
        return this.p;
    }

    public int getDamage() {
        int i = 0;
        for (int i2 = 0; i2 < this.r; i2++) {
            i += getInfo().getDamage();
        }
        return i;
    }

    public int getShockwaveIndex() {
        return getInfo().getShockwaveIndex();
    }

    public Point getTargetPosition() {
        return this.targetPosition;
    }

    @Override // com.birdshel.Uciana.Ships.Ship
    public void reset() {
    }
}
