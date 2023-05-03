package com.birdshel.Uciana.Elements.Battle;

import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Ships.Ship;
import com.birdshel.Uciana.Ships.ShipComponents.Weapon;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public interface BattleCallBack {
    void attackShip(Point point, Weapon weapon);

    void moveShip(Point point);

    void selectNextShip();

    void selfDestruct(Ship ship);

    void shipDone();

    void shipRetreat(Ship ship);

    void shipWait();
}
