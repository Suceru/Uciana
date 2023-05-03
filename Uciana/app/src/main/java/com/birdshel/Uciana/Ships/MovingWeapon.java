package com.birdshel.Uciana.Ships;

import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponentID;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponents;
import com.birdshel.Uciana.Ships.ShipComponents.Weapon;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class MovingWeapon extends Ship {
    private Point interceptionPosition;
    private int interceptionSide;
    protected ShipComponentID p;
    private boolean payloadDestroyed;
    protected Point q;
    protected int r;

    public Weapon getInfo() {
        return (Weapon) ShipComponents.get(this.p);
    }

    public Point getInterceptionPosition() {
        return this.interceptionPosition;
    }

    public int getInterceptionSide() {
        return this.interceptionSide;
    }

    public Point getPosition() {
        return this.q;
    }

    public boolean isPayloadDestroyed() {
        return this.payloadDestroyed;
    }

    public void setInterceptionPosition(Point point) {
        this.interceptionPosition = point;
    }

    public void setInterceptionSide(int i) {
        this.interceptionSide = i;
    }

    public void setPayloadDestroyed(boolean z) {
        this.payloadDestroyed = z;
    }

    public void setPosition(Point point) {
        this.q = point;
    }
}
