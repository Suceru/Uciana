package com.birdshel.Uciana.Colonies;

import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Planets.SystemObject;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Outpost {
    private int empireID;
    private final SystemObject systemObject;

    public Outpost(SystemObject systemObject, int i) {
        this.systemObject = systemObject;
        this.empireID = i;
        GameData.fleets.checkForChanceToAttack(systemObject.getSystemID());
    }

    public void a(int i) {
        this.empireID = i;
    }

    public int getEmpireID() {
        return this.empireID;
    }

    public int getOrbit() {
        return this.systemObject.getOrbit();
    }

    public int getSystemID() {
        return this.systemObject.getSystemID();
    }

    public SystemObject getSystemObject() {
        return this.systemObject;
    }
}
