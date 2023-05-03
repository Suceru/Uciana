package com.birdshel.Uciana.StarSystems;

import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Math.Point;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class GalacticObject {
    private boolean hasAltSpinDirection;
    private final int id;
    private final Point position;
    private float size;
    private float spinSpeed;

    public GalacticObject(int i, Point point, float f2, boolean z, float f3) {
        this.id = i;
        this.position = point;
        this.size = f2;
        this.hasAltSpinDirection = z;
        this.spinSpeed = f3;
    }

    public Point a() {
        return new Point(this.position.getX() + (getSize() / 2.0f), this.position.getY() + (getSize() / 2.0f));
    }

    public void b(boolean z) {
        this.hasAltSpinDirection = z;
    }

    public void c(int i) {
        this.size = i;
    }

    public void d(float f2) {
        this.spinSpeed = f2;
    }

    public int getID() {
        return this.id;
    }

    public Point getPosition() {
        return this.position;
    }

    public float getSize() {
        return this.size * GameData.galaxy.getSize().getSizeModifier();
    }

    public float getSpinSpeed() {
        return this.spinSpeed;
    }

    public float getUnmodifiedSize() {
        return this.size;
    }

    public boolean hasAltSpinDirection() {
        return this.hasAltSpinDirection;
    }

    public float getSize(GalaxySize galaxySize) {
        return this.size * galaxySize.getSizeModifier();
    }
}
