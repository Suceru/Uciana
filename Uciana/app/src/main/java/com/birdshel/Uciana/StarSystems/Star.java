package com.birdshel.Uciana.StarSystems;

import com.birdshel.Uciana.Math.Point;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Star {
    private final Point position;
    private final int shape;
    private final float size;
    private final StarType type;

    public Star(Point point, StarType starType, int i, float f2) {
        this.position = point;
        this.type = starType;
        this.shape = i;
        this.size = f2;
    }

    public Point getPosition() {
        return this.position;
    }

    public int getShape() {
        return this.shape;
    }

    public float getSize() {
        return this.size;
    }

    public StarType getType() {
        return this.type;
    }
}
