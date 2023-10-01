package com.birdshel.Uciana.Planets;

import androidx.constraintlayout.core.motion.utils.TypedValues;

import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Ships.ShipComponents.WeaponStats;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class IonStorm {
    private float[] alpha;
    private final boolean isVisible;
    private int[] layers;
    private final Point position;
    private Point[] positions;
    private float[] rotations;
    private int systemID;

    public IonStorm(int i) {
        this.layers = new int[2];
        this.rotations = new float[2];
        this.positions = new Point[2];
        this.alpha = new float[2];
        this.systemID = i;
        this.position = new Point(Functions.random.nextInt(TypedValues.Custom.TYPE_INT) + WeaponStats.DIMENSIONAL_ENERGY_BOMB_MIN_DAMAGE, Functions.random.nextInt(550));
        this.isVisible = Functions.percent(40);
        for (int i2 = 0; i2 < 2; i2++) {
            this.layers[i2] = Functions.random.nextInt(4);
            this.rotations[i2] = 1.0f;
            this.positions[i2] = new Point(Functions.random.nextInt(63), Functions.random.nextInt(WeaponStats.NOVA_BOMB_MAX_DAMAGE));
            this.alpha[i2] = (Functions.random.nextFloat() * 0.3f) + 0.5f;
        }
    }

    public float getAlpha(int i) {
        return this.alpha[i];
    }

    public int getLayer(int i) {
        return this.layers[i];
    }

    public Point getPosition() {
        return this.position;
    }

    public Point getPositionOfCloud(int i) {
        return this.positions[i];
    }

    public float getRotation(int i) {
        return this.rotations[i];
    }

    public int getSystemID() {
        return this.systemID;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    public IonStorm(Point point) {
        this.layers = new int[2];
        this.rotations = new float[2];
        this.positions = new Point[2];
        this.alpha = new float[2];
        this.position = point;
        this.isVisible = true;
        for (int i = 0; i < 2; i++) {
            this.layers[i] = Functions.random.nextInt(4);
            this.rotations[i] = 1.0f;
            this.positions[i] = new Point(Functions.random.nextInt(25), Functions.random.nextInt(25));
            this.alpha[i] = (Functions.random.nextFloat() * 0.3f) + 0.5f;
        }
    }

    public IonStorm(int i, Point point, boolean z, int[] iArr, float[] fArr, Point[] pointArr, float[] fArr2) {
        this.layers = new int[2];
        this.rotations = new float[2];
        this.positions = new Point[2];
        this.alpha = new float[2];
        this.systemID = i;
        this.position = point;
        this.isVisible = z;
        this.layers = iArr;
        this.rotations = fArr;
        this.positions = pointArr;
        this.alpha = fArr2;
    }
}
