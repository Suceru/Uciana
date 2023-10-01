package com.birdshel.Uciana.StarSystems;

import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.R;

import java.util.HashMap;
import java.util.Map;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public enum GalaxySize {
    SMALL(new Builder().D(2.0f).s(0.5f).z(25).A(27).x(1050).y(590).B(65).C(35).t(15).F(new HashMap<Object, Integer>() { // from class: com.birdshel.Uciana.StarSystems.GalaxySize.3
        {
            put(0, 35);
            put(1, 40);
            put(2, 25);
        }
    }).G(new HashMap<Object, Integer>() { // from class: com.birdshel.Uciana.StarSystems.GalaxySize.2
        {
            put(1, 30);
            put(2, 35);
            put(3, 35);
        }
    }).E(new HashMap<Object, Integer>() { // from class: com.birdshel.Uciana.StarSystems.GalaxySize.1
        {
            put(2, 20);
            put(3, 30);
            put(4, 30);
            put(5, 20);
        }
    }).w(5).r(20).H(new float[]{1.0f, 1.15f, 1.3f}).v(1).u(R.string.galaxy_size_small)),
    MEDIUM(new Builder().D(1.5f).s(0.75f).z(25).A(27).x(1060).y(615).B(45).C(32).t(12).F(new HashMap<Object, Integer>() { // from class: com.birdshel.Uciana.StarSystems.GalaxySize.6
        {
            put(0, 20);
            put(1, 20);
            put(2, 25);
            put(3, 25);
            put(4, 10);
        }
    }).G(new HashMap<Object, Integer>() { // from class: com.birdshel.Uciana.StarSystems.GalaxySize.5
        {
            put(1, 15);
            put(2, 20);
            put(3, 25);
            put(4, 20);
            put(5, 20);
        }
    }).E(new HashMap<Object, Integer>() { // from class: com.birdshel.Uciana.StarSystems.GalaxySize.4
        {
            put(3, 20);
            put(4, 20);
            put(5, 20);
            put(6, 20);
            put(7, 20);
        }
    }).w(7).r(30).H(new float[]{1.0f, 1.2f, 1.4f}).v(2).u(R.string.galaxy_size_medium)),
    LARGE(new Builder().D(1.35f).s(0.8f).z(25).A(27).x(1070).y(620).B(42).C(30).t(12).F(new HashMap<Object, Integer>() { // from class: com.birdshel.Uciana.StarSystems.GalaxySize.9
        {
            put(0, 15);
            put(1, 25);
            put(2, 20);
            put(3, 25);
            put(4, 15);
        }
    }).G(new HashMap<Object, Integer>() { // from class: com.birdshel.Uciana.StarSystems.GalaxySize.8
        {
            put(2, 15);
            put(3, 25);
            put(4, 20);
            put(5, 25);
            put(6, 15);
        }
    }).E(new HashMap<Object, Integer>() { // from class: com.birdshel.Uciana.StarSystems.GalaxySize.7
        {
            put(4, 15);
            put(5, 25);
            put(6, 20);
            put(7, 25);
            put(8, 15);
        }
    }).w(8).r(40).H(new float[]{1.0f, 1.25f, 1.5f}).v(2).u(R.string.galaxy_size_large)),
    EXTRA_LARGE(new Builder().D(1.0f).s(1.0f).z(25).A(27).x(1100).y(640).B(35).C(25).t(10).F(new HashMap<Object, Integer>() { // from class: com.birdshel.Uciana.StarSystems.GalaxySize.12
        {
            put(0, 10);
            put(1, 15);
            put(2, 25);
            put(3, 25);
            put(4, 15);
            put(5, 10);
        }
    }).G(new HashMap<Object, Integer>() { // from class: com.birdshel.Uciana.StarSystems.GalaxySize.11
        {
            put(3, 15);
            put(4, 25);
            put(5, 20);
            put(6, 25);
            put(7, 15);
        }
    }).E(new HashMap<Object, Integer>() { // from class: com.birdshel.Uciana.StarSystems.GalaxySize.10
        {
            put(5, 10);
            put(6, 15);
            put(7, 25);
            put(8, 25);
            put(9, 15);
            put(10, 10);
        }
    }).w(10).r(50).H(new float[]{1.0f, 1.3f, 1.6f}).v(3).u(R.string.galaxy_size_extra_large));
    
    private final int averageNumberOfStars;
    private final float distanceModifier;
    private final int inOrbitPerPlayer;
    private final int inOrbitX;
    private final int labelID;
    private final int maxNumberOfNewWormholes;
    private final int maxRandomWormholes;
    private final int maxX;
    private final int maxY;
    private final int minX;
    private final int minY;
    private final int shipSize;
    private final float sizeModifier;
    private final Map<Object, Integer> wormholeCountPercentsHigh;
    private final Map<Object, Integer> wormholeCountPercentsLow;
    private final Map<Object, Integer> wormholeCountPercentsNormal;
    private final float[] zoomLevels;

    /* compiled from: MyApplication */
    /* loaded from: classes.dex */
    private static class Builder {
        private int averageNumberOfStars;
        private float distanceModifier;
        private int inOrbitPerPlayer;
        private int inOrbitX;
        private int labelID;
        private int maxNumberOfNewWormholes;
        private int maxRandomWormholes;
        private int maxX;
        private int maxY;
        private int minX;
        private int minY;
        private int shipSize;
        private float sizeModifier;
        private Map<Object, Integer> wormholeCountPercentsHigh;
        private Map<Object, Integer> wormholeCountPercentsLow;
        private Map<Object, Integer> wormholeCountPercentsNormal;
        private float[] zoomLevels;

        Builder() {
        }

        Builder A(int i) {
            this.minY = i;
            return this;
        }

        Builder B(int i) {
            this.inOrbitX = i;
            return this;
        }

        Builder C(int i) {
            this.shipSize = i;
            return this;
        }

        Builder D(float f2) {
            this.sizeModifier = f2;
            return this;
        }

        Builder E(Map<Object, Integer> map) {
            this.wormholeCountPercentsHigh = map;
            return this;
        }

        Builder F(Map<Object, Integer> map) {
            this.wormholeCountPercentsLow = map;
            return this;
        }

        Builder G(Map<Object, Integer> map) {
            this.wormholeCountPercentsNormal = map;
            return this;
        }

        Builder H(float[] fArr) {
            this.zoomLevels = fArr;
            return this;
        }

        Builder r(int i) {
            this.averageNumberOfStars = i;
            return this;
        }

        Builder s(float f2) {
            this.distanceModifier = f2;
            return this;
        }

        Builder t(int i) {
            this.inOrbitPerPlayer = i;
            return this;
        }

        Builder u(int i) {
            this.labelID = i;
            return this;
        }

        Builder v(int i) {
            this.maxNumberOfNewWormholes = i;
            return this;
        }

        Builder w(int i) {
            this.maxRandomWormholes = i;
            return this;
        }

        Builder x(int i) {
            this.maxX = i;
            return this;
        }

        Builder y(int i) {
            this.maxY = i;
            return this;
        }

        Builder z(int i) {
            this.minX = i;
            return this;
        }
    }

    GalaxySize(Builder builder) {
        this.sizeModifier = builder.sizeModifier;
        this.distanceModifier = builder.distanceModifier;
        this.minX = builder.minX;
        this.minY = builder.minY;
        this.maxX = builder.maxX;
        this.maxY = builder.maxY;
        this.inOrbitX = builder.inOrbitX;
        this.shipSize = builder.shipSize;
        this.inOrbitPerPlayer = builder.inOrbitPerPlayer;
        this.wormholeCountPercentsLow = builder.wormholeCountPercentsLow;
        this.wormholeCountPercentsNormal = builder.wormholeCountPercentsNormal;
        this.wormholeCountPercentsHigh = builder.wormholeCountPercentsHigh;
        this.maxRandomWormholes = builder.maxRandomWormholes;
        this.averageNumberOfStars = builder.averageNumberOfStars;
        this.zoomLevels = builder.zoomLevels;
        this.maxNumberOfNewWormholes = builder.maxNumberOfNewWormholes;
        this.labelID = builder.labelID;
    }

    public int getAverageNumberOfStars() {
        return this.averageNumberOfStars;
    }

    public float getDistanceCheckModifier() {
        return this.sizeModifier;
    }

    public float getDistanceModifier() {
        return this.distanceModifier;
    }

    public int getInOrbitPerPlayer() {
        return this.inOrbitPerPlayer;
    }

    public int getInOrbitX() {
        return this.inOrbitX;
    }

    public int getLabelID() {
        return this.labelID;
    }

    public int getMaxNumberOfNewWormholes() {
        return this.maxNumberOfNewWormholes;
    }

    public int getMaxRandomWormholes() {
        return this.maxRandomWormholes;
    }

    public int getMaxX() {
        return this.maxX;
    }

    public int getMaxY() {
        return this.maxY;
    }

    public int getMinX() {
        return this.minX;
    }

    public int getMinY() {
        return this.minY;
    }

    public float getShipSize() {
        return this.shipSize;
    }

    public float getSizeModifier() {
        return this.sizeModifier;
    }

    public int getWormholeCountHigh() {
        return ((Integer) Functions.getItemByPercent(this.wormholeCountPercentsHigh)).intValue();
    }

    public int getWormholeCountLow() {
        return ((Integer) Functions.getItemByPercent(this.wormholeCountPercentsLow)).intValue();
    }

    public int getWormholeCountNormal() {
        return ((Integer) Functions.getItemByPercent(this.wormholeCountPercentsNormal)).intValue();
    }

    public float getZoomLevel(int i) {
        return this.zoomLevels[i];
    }
}
