package com.birdshel.Uciana.StarSystems;

import com.birdshel.Uciana.Math.Point;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Nebula {
    private final NebulaType nebulaType;
    private final Point position;
    private final float rotation;
    private final float sizeModifier;

    public Nebula(Point point, float f2, NebulaType nebulaType, float f3) {
        this.position = point;
        this.sizeModifier = f2;
        this.nebulaType = nebulaType;
        this.rotation = f3;
    }

    public NebulaType getNebulaType() {
        return this.nebulaType;
    }

    public Point getPosition() {
        return this.position;
    }

    public float getRotation() {
        return this.rotation;
    }

    public int getSize() {
        return (int) (this.sizeModifier * 300.0f);
    }

    public float getSizeModifier() {
        return this.sizeModifier;
    }
}
