package com.birdshel.Uciana.AI.AIObjects;

public class AttackTarget {
    private final AttackTargetType attackTargetType;
    private int empireID;
    private int orbit;
    private int systemID;
    private int value;

    public AttackTarget(int systemID, int orbit, int empireID, int value) {
        this.attackTargetType = AttackTargetType.SYSTEM_OBJECT;
        this.systemID = systemID;
        this.orbit = orbit;
        this.empireID = empireID;
        this.value = value;
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

    public AttackTarget(int systemID, int empireID, int value) {
        this.attackTargetType = AttackTargetType.FLEET;
        this.systemID = systemID;
        this.empireID = empireID;
        this.value = value;
    }

    public AttackTarget() {
        this.attackTargetType = AttackTargetType.NONE;
    }
}
