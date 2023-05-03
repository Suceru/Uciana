package com.birdshel.Uciana.AI.FleetTasks;

import com.birdshel.Uciana.Colonies.Outpost;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Math.SystemOrbit;
import com.birdshel.Uciana.Math.TaskDoerScore;
import com.birdshel.Uciana.Planets.AsteroidBelt;
import com.birdshel.Uciana.Planets.ExplorationFind;
import com.birdshel.Uciana.Planets.GasGiant;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.SystemObject;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.Ships.Ship;
import com.birdshel.Uciana.Technology.TechID;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BuildOutpostTasks {
    private static List<TaskDoerScore> outpostDoerScores;

    BuildOutpostTasks() {
    }

    public static void a(Empire empire, Map<String, String> map, List<SystemOrbit> list) {
        outpostDoerScores = new ArrayList();
        boolean hasTech = empire.hasTech(TechID.ASTEROID_MINING_OUTPOST);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            Ship ship = GameData.fleets.get(entry.getValue()).getShip(entry.getKey());
            for (SystemOrbit systemOrbit : list) {
                int i = 0;
                SystemObject systemObject = GameData.galaxy.getSystemObject(systemOrbit.systemID, systemOrbit.orbit);
                if (systemObject.isAsteroidBelt()) {
                    if (hasTech) {
                        AsteroidBelt asteroidBelt = (AsteroidBelt) systemObject;
                        int score = asteroidBelt.getScore();
                        i = GameData.colonies.hasColonyInSystem(ship.getEmpireID(), asteroidBelt.getSystemID()) ? score + 15 : score;
                    }
                }
                if (systemObject.isGasGiant()) {
                    GasGiant gasGiant = (GasGiant) systemObject;
                    i = gasGiant.getScore();
                    if (GameData.colonies.hasColonyInSystem(ship.getEmpireID(), gasGiant.getSystemID())) {
                        i += 15;
                    }
                }
                outpostDoerScores.add(new TaskDoerScore(systemOrbit.systemID, systemOrbit.orbit, ship.getID(), i));
            }
        }
    }

    public static void b(Empire empire) {
        List<TaskDoerScore> e2 = FleetTaskAI.e(outpostDoerScores);
        outpostDoerScores = e2;
        executeBuildOutpostTasks(empire, e2);
    }

    private static void buildOutpost(int i, SystemObject systemObject, Fleet fleet, Ship ship) {
        if (systemObject.hasOutpost()) {
            return;
        }
        if (systemObject.isPlanet()) {
            Planet planet = (Planet) systemObject;
            ExplorationFind explorationFind = planet.getExplorationFind();
            if (!planet.hasBeenExploredByEmpire(i)) {
                explorationFind.addFindBonusToEmpire(i, planet.getSystemID(), planet.getOrbit(), fleet, ship);
                planet.beenExplored(i);
            }
            if (explorationFind.isNewOutpost()) {
                return;
            }
        }
        GameData.outposts.add(new Outpost(systemObject, i));
        GameData.fleets.removeOutpostShip(systemObject.getSystemID(), i);
    }

    private static void executeBuildOutpostTasks(Empire empire, List<TaskDoerScore> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (TaskDoerScore taskDoerScore : list) {
            if (!arrayList.contains(taskDoerScore.shipID) && !arrayList2.contains(FleetTaskAI.c(taskDoerScore.systemID, taskDoerScore.orbit)) && !GameData.fleets.doesShipNoLongerExists(taskDoerScore.shipID)) {
                arrayList.add(taskDoerScore.shipID);
                arrayList2.add(FleetTaskAI.c(taskDoerScore.systemID, taskDoerScore.orbit));
                Fleet usingShipID = GameData.fleets.getUsingShipID(empire.id, taskDoerScore.shipID);
                if (!usingShipID.isMoving()) {
                    int systemID = usingShipID.getSystemID();
                    int i = taskDoerScore.systemID;
                    if (systemID == i) {
                        buildOutpost(empire.id, GameData.galaxy.getSystemObject(i, taskDoerScore.orbit), usingShipID, usingShipID.getShip(taskDoerScore.shipID));
                    }
                }
                Ship ship = usingShipID.getShip(taskDoerScore.shipID);
                FleetTaskAI.b(usingShipID, ship, taskDoerScore);
                FleetTaskAI.d(usingShipID, ship);
            }
        }
    }
}
