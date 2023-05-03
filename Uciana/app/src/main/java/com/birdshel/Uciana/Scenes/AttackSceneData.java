package com.birdshel.Uciana.Scenes;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class AttackSceneData {
    public static final int ATTACK_ONLY_FLEET = 1;
    public static final int ATTACK_PLANET = 2;
    public static final int BOMBING_SUMMARY = 0;
    public static final int RETURNING = 3;
    private final int empireID;
    private final boolean isAttacker;
    private final int orbit;
    private final int systemID;
    private final int targetID;
    private final int type;

    public AttackSceneData(int i, int i2, int i3, int i4, int i5, boolean z) {
        this.type = i;
        this.empireID = i2;
        this.targetID = i3;
        this.systemID = i4;
        this.orbit = i5;
        this.isAttacker = z;
    }

    public int getEmpireID() {
        return this.empireID;
    }

    public int getOrbit() {
        return this.orbit;
    }

    public int getSystemID() {
        return this.systemID;
    }

    public int getTargetID() {
        return this.targetID;
    }

    public int getType() {
        return this.type;
    }

    public boolean isAttacker() {
        return this.isAttacker;
    }

    public AttackSceneData(int i, int i2, int i3, int i4, boolean z) {
        this.type = i;
        this.empireID = i2;
        this.targetID = i3;
        this.systemID = i4;
        this.orbit = -1;
        this.isAttacker = z;
    }

    public AttackSceneData() {
        this.type = 3;
        this.empireID = -1;
        this.targetID = -1;
        this.systemID = -1;
        this.orbit = -1;
        this.isAttacker = true;
    }
}
