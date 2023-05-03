package com.birdshel.Uciana.Ships;

import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponent;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponentID;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponents;
import com.birdshel.Uciana.Ships.ShipComponents.SpecialComponent;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class StarBase extends Ship {
    public StarBase(int i) {
        this.f1549c = "starbase";
        ShipType shipType = ShipType.STAR_BASE;
        this.f1548a = shipType;
        this.b = shipType.getString(i);
        this.o = i;
        Ship shipDesign = GameData.empires.get(i).getShipDesignAI().getShipDesign(shipType);
        this.i = shipDesign.getArmor();
        this.j = shipDesign.getShield();
        this.l = shipDesign.getSublightEngine();
        this.k = shipDesign.getTargetingComputer();
        this.m = shipDesign.getShipComponents();
        this.f1552f = (int) (this.f1548a.getSizeClass() * 100 * this.i.getArmorMultiplier());
        for (ShipComponent shipComponent : this.m) {
            ShipComponentID id = shipComponent.getID();
            ShipComponentID shipComponentID = ShipComponentID.HARDENED_ALLOY;
            if (id == shipComponentID) {
                int i2 = this.f1552f;
                this.f1552f = (int) (i2 + (i2 * ((SpecialComponent) ShipComponents.get(shipComponentID)).getEffectValue()));
            }
        }
        this.f1551e = this.f1552f;
        int strengthMultiplier = this.j.getStrengthMultiplier() * this.f1548a.getSizeClass();
        this.h = strengthMultiplier;
        this.g = strengthMultiplier;
    }
}
