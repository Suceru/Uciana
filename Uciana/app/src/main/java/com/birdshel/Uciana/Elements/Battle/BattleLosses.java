package com.birdshel.Uciana.Elements.Battle;

import com.birdshel.Uciana.Ships.Ship;
import com.birdshel.Uciana.Ships.ShipType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class BattleLosses {
    private final Map<ShipType, Integer> count = new HashMap();
    private final Map<ShipType, Set<Integer>> hullDesigns = new HashMap();

    public void addLoss(Ship ship) {
        Set<Integer> hashSet;
        ShipType shipType = ship.getShipType();
        Map<ShipType, Integer> map = this.count;
        map.put(shipType, Integer.valueOf(map.containsKey(shipType) ? 1 + this.count.get(shipType).intValue() : 1));
        if (this.hullDesigns.containsKey(shipType)) {
            hashSet = this.hullDesigns.get(shipType);
        } else {
            hashSet = new HashSet<>();
        }
        hashSet.add(Integer.valueOf(ship.getHullDesign()));
        this.hullDesigns.put(shipType, hashSet);
    }

    public List<Integer> getHullDesignsForShipType(ShipType shipType) {
        if (this.hullDesigns.containsKey(shipType)) {
            return new ArrayList(this.hullDesigns.get(shipType));
        }
        return new ArrayList();
    }

    public Map<ShipType, Integer> getShipTypeAndCount() {
        return this.count;
    }

    public boolean isEmpty() {
        return this.count.isEmpty();
    }

    public int numberOfShipTypes() {
        return this.count.size();
    }
}
