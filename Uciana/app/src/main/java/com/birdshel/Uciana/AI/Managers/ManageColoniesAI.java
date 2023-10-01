package com.birdshel.Uciana.AI.Managers;

import com.birdshel.Uciana.AI.AI;
import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Colonies.SortType;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.StarSystems.Blackhole;
import com.birdshel.Uciana.StarSystems.StarSystem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ManageColoniesAI {
    private final Empire empire;

    public ManageColoniesAI(Empire empire) {
        this.empire = empire;
    }

    private Point getNextJump(Point point, Point point2, Point point3) {
        int distance = point2.getDistance(point3);
        int engineSpeed = this.empire.getTech().getEngineSpeed() * GameData.galaxy.getDistanceConst();
        for (Blackhole blackhole : GameData.galaxy.getBlackholes()) {
            if (blackhole.isAffectedByTimeDilation(point2)) {
                engineSpeed /= 2;
            }
        }
        if (engineSpeed > distance) {
            return new Point(point3.getX(), point3.getY());
        }
        double atan2 = (float) Math.atan2(point.getY() - point3.getY(), point.getX() - point3.getX());
        float f2 = engineSpeed;
        return new Point(point2.getX() - (((float) Math.cos(atan2)) * f2), point2.getY() - (((float) Math.sin(atan2)) * f2));
    }

    private int getTurnsTo(Point point, Point point2) {
        Point point3 = new Point(point.getX(), point.getY());
        int i = 0;
        while (!point2.equals(point3)) {
            point3 = getNextJump(point, point3, point2);
            i++;
        }
        return i;
    }

    private int getTurnsToSystem(int i, int i2) {
        int i3 = 0;
        for (Integer num : AI.getRoute(this.empire.id, i, i2)) {
            i3 += getTurnsToSystemCalc(i, num.intValue());
            i = num.intValue();
        }
        return i3;
    }

    private int getTurnsToSystemCalc(int i, int i2) {
        StarSystem starSystem = GameData.galaxy.getStarSystem(i);
        int turnsTo = getTurnsTo(starSystem.getPosition(), GameData.galaxy.getStarSystem(i2).getPosition());
        if (starSystem.hasWormholes()) {
            for (Point point : GameData.galaxy.getWormholes()) {
                if (point.getX() == starSystem.getID() && point.getY() == i2) {
                    return 1;
                }
                if (point.getY() == starSystem.getID() && point.getX() == i2) {
                    return 1;
                }
            }
        }
        return turnsTo;
    }

    private void levelOutFarmersWorkersScientists() {
        for (Colony colony : this.empire.getColonies()) {
            int farmersPercent = 100 - colony.getFarmersPercent();
            if (colony.isBlockaded()) {
                colony.setWorkersPercent(farmersPercent);
                colony.setScientistPercent(0);
            } else {
                if (farmersPercent % 2 == 1) {
                    farmersPercent--;
                    colony.setWorkersPercent((farmersPercent / 2) + 1);
                } else {
                    colony.setWorkersPercent(farmersPercent / 2);
                }
                colony.setScientistPercent(farmersPercent / 2);
            }
        }
    }

    private void manageFoodProductionScience() {
        setAllFarmersToNone();
        setFarmers();
        setFarmersForBlockadedColonies();
        levelOutFarmersWorkersScientists();
    }

    private void managePopulation() {
        ArrayList<Colony> arrayList = new ArrayList();
        for (Colony colony : GameData.colonies.sort(this.empire.id, SortType.POPULATION_LOWEST_TO_HIGHEST)) {
            if (colony.getPopulation() > 0) {
                break;
            } else if (!colony.isBlockaded() && this.empire.getMigrantsForPlanet(colony.getSystemID(), colony.getOrbit()).isEmpty()) {
                arrayList.add(colony);
            }
        }
        List<Colony> sort = GameData.colonies.sort(this.empire.id, SortType.POPULATION_HIGHEST_TO_LOWEST);
        for (Colony colony2 : arrayList) {
            for (Colony colony3 : sort) {
                if (!colony3.isBlockaded() && colony3.getPopulation() >= 10) {
                    int turnsToSystem = getTurnsToSystem(colony2.getSystemID(), colony3.getSystemID());
                    if (turnsToSystem == 0) {
                        colony2.addPopulation(2);
                    } else {
                        this.empire.addMigrants(colony2.getSystemID(), colony2.getOrbit(), 2, turnsToSystem);
                    }
                }
            }
        }
    }

    private void setAllFarmersToNone() {
        for (Colony colony : this.empire.getColonies()) {
            colony.setFarmersPercent(0);
        }
    }

    private void setFarmers() {
        List<Colony> sort = GameData.colonies.sort(this.empire.id, SortType.FOOD_HIGHEST_TO_LOWEST);
        ArrayList arrayList = new ArrayList();
        for (Colony colony : sort) {
            if (colony.getFoodPerFarmer() != 0.0f && !colony.isBlockaded()) {
                arrayList.add(colony);
            }
        }
        if (setFarmingPercentForColony(arrayList, 3, true, 75)) {
            return;
        }
        setAllFarmersToNone();
        if (setFarmingPercentForColony(arrayList, 3, true, 80) || setFarmingPercentForColony(arrayList, 3, false, 50)) {
            return;
        }
        setAllFarmersToNone();
        if (setFarmingPercentForColony(arrayList, 3, true, 80) || setFarmingPercentForColony(arrayList, 3, false, 60)) {
            return;
        }
        setAllFarmersToNone();
        if (setFarmingPercentForColony(arrayList, 3, true, 80) || setFarmingPercentForColony(arrayList, 3, false, 65)) {
            return;
        }
        setAllFarmersToNone();
        if (setFarmingPercentForColony(arrayList, 3, true, 85) || setFarmingPercentForColony(arrayList, 3, false, 70) || setFarmingPercentForColony(arrayList, 2, false, 50)) {
            return;
        }
        setAllFarmersToNone();
        if (setFarmingPercentForColony(arrayList, 3, true, 85) || setFarmingPercentForColony(arrayList, 3, false, 75) || setFarmingPercentForColony(arrayList, 2, false, 60)) {
            return;
        }
        setAllFarmersToNone();
        if (setFarmingPercentForColony(arrayList, 3, true, 94) || setFarmingPercentForColony(arrayList, 3, false, 94) || setFarmingPercentForColony(arrayList, 2, false, 94)) {
            return;
        }
        setAllFarmersToNone();
        setFarmingPercentForColony(arrayList, 0, true, 100);
    }

    private void setFarmersForBlockadedColonies() {
        for (Colony colony : this.empire.getColonies()) {
            if (colony.isBlockaded() && colony.getFoodPerFarmer() != 0.0f) {
                for (int i = 0; i <= 100; i += 5) {
                    colony.setFarmersPercent(i);
                    if (colony.getNetFoodPerTurn() >= 0) {
                        return;
                    }
                }
                continue;
            }
        }
    }

    private boolean setFarmingPercentForColony(List<Colony> list, int i, boolean z, int i2) {
        Iterator<Colony> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                return false;
            }
            Colony next = it.next();
            if (!z ? next.getFoodPerFarmer() != ((float) i) : next.getFoodPerFarmer() <= ((float) i)) {
                for (int i3 = 0; i3 <= i2; i3 += 5) {
                    next.setFarmersPercent(i3);
                    if (this.empire.getNetFoodPerTurn() >= 0) {
                        return true;
                    }
                }
                continue;
            }
        }
    }

    public void manage() {
        manageFoodProductionScience();
        managePopulation();
    }
}
