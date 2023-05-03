package com.birdshel.Uciana.Ships;

import com.birdshel.Uciana.GameData;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class TorpedoTurret extends Ship {
    public TorpedoTurret(int i, String str) {
        this.f1549c = str;
        ShipType shipType = ShipType.TORPEDO_TURRET;
        this.f1548a = shipType;
        this.b = shipType.getString(i);
        this.o = i;
        Ship shipDesign = GameData.empires.get(i).getShipDesignAI().getShipDesign(shipType);
        this.i = shipDesign.getArmor();
        this.j = shipDesign.getShield();
        this.l = shipDesign.getSublightEngine();
        this.k = shipDesign.getTargetingComputer();
        this.m = shipDesign.getShipComponents();
        int sizeClass = (int) (this.f1548a.getSizeClass() * 100 * this.i.getArmorMultiplier());
        this.f1552f = sizeClass;
        this.f1551e = sizeClass;
        int strengthMultiplier = this.j.getStrengthMultiplier() * this.f1548a.getSizeClass();
        this.h = strengthMultiplier;
        this.g = strengthMultiplier;
    }
}
