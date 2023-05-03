package com.birdshel.Uciana.AI.FleetTasks;

import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Math.SystemOrbit;
import com.birdshel.Uciana.Math.TaskDoerScore;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.Ships.Ship;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ScoutExploreTasks {
    private static List<TaskDoerScore> scoutingExplorationTaskDoerScores;

    ScoutExploreTasks() {
    }

    public static void a(Map<String, String> map, List<Integer> list, List<SystemOrbit> list2) {
        scoutingExplorationTaskDoerScores = new ArrayList();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            Fleet fleet = GameData.fleets.get(entry.getValue());
            Ship ship = fleet.getShip(entry.getKey());
            for (Integer num : list) {
                scoutingExplorationTaskDoerScores.add(new TaskDoerScore(num.intValue(), ship.getID(), getScoreForScoutingSystem(fleet, num.intValue())));
            }
            for (SystemOrbit systemOrbit : list2) {
                scoutingExplorationTaskDoerScores.add(new TaskDoerScore(systemOrbit.systemID, systemOrbit.orbit, ship.getID(), getScoreForExploringPlanet(fleet, systemOrbit.systemID, systemOrbit.orbit)));
            }
        }
    }

    public static void b(Empire empire) {
        List<TaskDoerScore> e2 = FleetTaskAI.e(scoutingExplorationTaskDoerScores);
        scoutingExplorationTaskDoerScores = e2;
        executeScoutExploreTasks(empire.id, e2);
    }

    private static void executeScoutExploreTasks(int i, List<TaskDoerScore> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (TaskDoerScore taskDoerScore : list) {
            if (arrayList.contains(taskDoerScore.shipID)) {
                if (!arrayList2.contains(taskDoerScore.shipID) && !GameData.fleets.doesShipNoLongerExists(taskDoerScore.shipID) && taskDoerScore.orbit != -1) {
                    Fleet usingShipID = GameData.fleets.getUsingShipID(i, taskDoerScore.shipID);
                    if (usingShipID.inOrbit() && taskDoerScore.systemID == usingShipID.getOriginSystem()) {
                        Ship ship = usingShipID.getShip(taskDoerScore.shipID);
                        arrayList2.add(ship.getID());
                        if (!ship.hasBeenUsed()) {
                            arrayList3.add(FleetTaskAI.c(taskDoerScore.systemID, taskDoerScore.orbit));
                            exploreWorld(usingShipID, ship, taskDoerScore.systemID, taskDoerScore.orbit);
                        }
                    }
                }
            } else if (!arrayList3.contains(FleetTaskAI.c(taskDoerScore.systemID, taskDoerScore.orbit))) {
                arrayList.add(taskDoerScore.shipID);
                arrayList3.add(FleetTaskAI.c(taskDoerScore.systemID, taskDoerScore.orbit));
                Fleet usingShipID2 = GameData.fleets.getUsingShipID(i, taskDoerScore.shipID);
                Ship ship2 = usingShipID2.getShip(taskDoerScore.shipID);
                FleetTaskAI.b(usingShipID2, ship2, taskDoerScore);
                if (!usingShipID2.isMoving()) {
                    int systemID = usingShipID2.getSystemID();
                    int i2 = taskDoerScore.systemID;
                    if (systemID == i2) {
                        int i3 = taskDoerScore.orbit;
                        if (i3 != -1) {
                            exploreWorld(usingShipID2, ship2, i2, i3);
                        }
                    }
                }
                FleetTaskAI.d(usingShipID2, ship2);
            }
        }
    }

    private static void exploreWorld(Fleet fleet, Ship ship, int i, int i2) {
        ship.setUsed();
        Planet planet = (Planet) GameData.galaxy.getSystemObject(i, i2);
        if (GameData.empires.get(ship.getEmpireID()).isHuman()) {
            GameData.events.addExplorationEvent(ship.getEmpireID(), planet.getSystemID(), planet.getOrbit(), planet.isUnexplored());
        }
        planet.getExplorationFind().addFindBonusToEmpire(ship.getEmpireID(), planet.getSystemID(), planet.getOrbit(), fleet, ship);
        planet.beenExplored(ship.getEmpireID());
    }

    private static int getScoreForExploringPlanet(Fleet fleet, int i, int i2) {
        int i3;
        if (((Planet) GameData.galaxy.getSystemObject(i, i2)).getFoodPerFarmer(fleet.empireID) > 0.0f) {
            i3 = 10;
            int turnsToSystem = fleet.getTurnsToSystem(i);
            if (turnsToSystem != 0) {
                i3 = 10 / turnsToSystem;
            }
        } else {
            i3 = 5;
        }
        if (GameData.turn <= 8) {
            return 0;
        }
        return i3;
    }

    private static int getScoreForScoutingSystem(Fleet fleet, int i) {
        int scoutValue = GameData.galaxy.getStarSystem(i).getStarType().getScoutValue();
        int turnsToSystem = fleet.getTurnsToSystem(i);
        return turnsToSystem != 0 ? scoutValue / turnsToSystem : scoutValue;
    }
}
