package com.birdshel.Uciana.AI;

import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public enum Personality {
    RUTHLESS(new Builder().d(new Point(20.0f, 35.0f)).f(30).e(20)),
    ERRATIC(new Builder().d(new Point(20.0f, 45.0f)).f(20).e(20)),
    AGGRESSIVE(new Builder().d(new Point(40.0f, 45.0f)).f(25).e(20)),
    PACIFISTIC(new Builder().d(new Point(10.0f, 20.0f)).f(10).e(20)),
    HONORABLE(new Builder().d(new Point(20.0f, 30.0f)).f(15).e(20)),
    XENOPHOBIC(new Builder().d(new Point(30.0f, 35.0f)).f(20).e(20));
    
    private final Point minGoToWarRange;
    private final int minTurnsAfterPeaceToGoToWar;
    private final int minTurnsAskForPeace;

    /* compiled from: MyApplication */
    /* loaded from: classes.dex */
    private static class Builder {
        private Point minGoToWarRange;
        private int minTurnsAfterPeaceToGoToWar;
        private int minTurnsAskForPeace;

        Builder d(Point point) {
            this.minGoToWarRange = point;
            return this;
        }

        Builder e(int i) {
            this.minTurnsAfterPeaceToGoToWar = i;
            return this;
        }

        Builder f(int i) {
            this.minTurnsAskForPeace = i;
            return this;
        }
    }

    Personality(Builder builder) {
        this.minGoToWarRange = builder.minGoToWarRange;
        this.minTurnsAskForPeace = builder.minTurnsAskForPeace;
        this.minTurnsAfterPeaceToGoToWar = builder.minTurnsAfterPeaceToGoToWar;
    }

    public int getMinGoToWar() {
        return Functions.random.nextInt((int) this.minGoToWarRange.getY()) + ((int) this.minGoToWarRange.getX());
    }

    public int getMinTurnsAfterPeaceToGoToWar() {
        return this.minTurnsAfterPeaceToGoToWar;
    }

    public int getMinTurnsAskForPeace() {
        return this.minTurnsAskForPeace;
    }
}
