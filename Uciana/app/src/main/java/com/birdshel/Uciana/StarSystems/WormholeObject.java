package com.birdshel.Uciana.StarSystems;

import com.birdshel.Uciana.GameProperties;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class WormholeObject {
    private final float bottomSpinSpeed;
    private final Point position;
    private final int size;
    private final float topSpinSpeed;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WormholeObject(Point point) {
        this.position = point;
        this.size = Functions.getRandom(70, (int) GameProperties.WORMHOLE_MAX_SIZE);
        this.bottomSpinSpeed = Functions.getRandom(1.75f, 3.5f);
        this.topSpinSpeed = Functions.getRandom(1.75f, 3.5f);
    }

    public float getBottomSpinSpeed() {
        return this.bottomSpinSpeed;
    }

    public Point getPosition() {
        return this.position;
    }

    public int getSize() {
        return this.size;
    }

    public float getTopSpinSpeed() {
        return this.topSpinSpeed;
    }

    public WormholeObject(Point point, int i, float f2, float f3) {
        this.position = point;
        this.size = i;
        this.bottomSpinSpeed = f2;
        this.topSpinSpeed = f3;
    }
}
