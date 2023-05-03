package com.birdshel.Uciana.Math;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class TaskDoerScore {
    public final int orbit;
    public final int score;
    public final String shipID;
    public final int systemID;

    public TaskDoerScore(int i, String str, int i2) {
        this.systemID = i;
        this.shipID = str;
        this.score = i2;
        this.orbit = -1;
    }

    public TaskDoerScore(int i, int i2, String str, int i3) {
        this.systemID = i;
        this.orbit = i2;
        this.shipID = str;
        this.score = i3;
    }
}
