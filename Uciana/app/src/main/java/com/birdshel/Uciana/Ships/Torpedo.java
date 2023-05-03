package com.birdshel.Uciana.Ships;

import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponentID;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Torpedo extends MovingWeapon {
    private boolean shouldSelfDestruct;
    private final String targetID;

    public Torpedo(String str, ShipComponentID shipComponentID, String str2, Point point, int i, int i2) {
        this.f1549c = str;
        this.p = shipComponentID;
        this.targetID = str2;
        this.q = point;
        this.r = i;
        this.o = i2;
        this.shouldSelfDestruct = false;
    }

    public int getPayloadCount() {
        return this.r;
    }

    public String getTargetID() {
        return this.targetID;
    }

    public ShipComponentID getTorpedoType() {
        return this.p;
    }

    @Override // com.birdshel.Uciana.Ships.Ship
    public void reset() {
    }

    public void setSelfDestruct() {
        this.shouldSelfDestruct = true;
    }

    public boolean shouldSelfDestruct() {
        return this.shouldSelfDestruct;
    }
}
