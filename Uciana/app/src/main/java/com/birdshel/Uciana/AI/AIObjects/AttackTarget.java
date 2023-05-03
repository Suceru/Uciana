package com.birdshel.Uciana.AI.AIObjects;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class AttackTarget {
    private final AttackTargetType attackTargetType;
    private int empireID;
    private int orbit;
    private int systemID;
    private int value;

    public AttackTarget(int i, int i2, int i3, int i4) {
        this.attackTargetType = AttackTargetType.SYSTEM_OBJECT;
        this.systemID = i;
        this.orbit = i2;
        this.empireID = i3;
        this.value = i4;
    }

    public AttackTargetType getAttackTargetType() {
        return this.attackTargetType;
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

    public int getValue() {
        return this.value;
    }

    public boolean isFleet() {
        return this.attackTargetType == AttackTargetType.FLEET;
    }

    public boolean isNone() {
        return this.attackTargetType == AttackTargetType.NONE;
    }

    public boolean isSystemObject() {
        return this.attackTargetType == AttackTargetType.SYSTEM_OBJECT;
    }

    public AttackTarget(int i, int i2, int i3) {
        this.attackTargetType = AttackTargetType.FLEET;
        this.systemID = i;
        this.empireID = i2;
        this.value = i3;
    }

    public AttackTarget() {
        this.attackTargetType = AttackTargetType.NONE;
    }
}
