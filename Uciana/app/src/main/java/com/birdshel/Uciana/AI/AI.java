package com.birdshel.Uciana.AI;

import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Ships.Fleet;

import java.util.ArrayList;
import java.util.List;

public class AI {
    public static List<Integer> getRoute(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(i3));
        if (i2 == i3) {
            return arrayList;
        }
        int distance = GameData.galaxy.getDistance(i2, i3);
        for (Point point : GameData.galaxy.getKnownWormholes(i)) {
            int x = (int) point.getX();
            int y = (int) point.getY();
            if (GameData.galaxy.getDistance(i2, (int) point.getX()) > GameData.galaxy.getDistance(i2, (int) point.getY())) {
                x = (int) point.getY();
                y = (int) point.getX();
            }
            int distance2 = GameData.galaxy.getDistance(i2, x);
            if (distance2 < distance) {
                int distance3 = distance2 + 1 + GameData.galaxy.getDistance(y, i3);
                if (distance3 < distance) {
                    arrayList.clear();
                    arrayList.add(Integer.valueOf(x));
                    arrayList.add(Integer.valueOf(y));
                    arrayList.add(Integer.valueOf(i3));
                    distance = distance3;
                }
            }
        }
        if (((Integer) arrayList.get(0)).intValue() == i2) {
            arrayList.remove(0);
        }
        return arrayList;
    }

    public static void moveFleet(Fleet fleet, int i, List<String> list) {
        if (fleet.getSize() == list.size()) {
            fleet.move(i);
            return;
        }
        Fleet fleet2 = new Fleet(GameData.empires.get(fleet.empireID).id, fleet.getSystemID());
        GameData.fleets.add(fleet2);
        fleet2.setPosition(new Point(fleet.getPosition().getX(), fleet.getPosition().getY()));
        fleet2.set(fleet.isMoving(), fleet.inOrbit());
        for (String str : list) {
            fleet2.getShips().add(fleet.getShip(str));
            fleet.removeShip(str);
        }
        fleet2.move(i);
    }
}
