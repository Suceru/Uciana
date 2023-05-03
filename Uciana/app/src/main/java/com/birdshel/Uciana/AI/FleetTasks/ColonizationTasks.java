package com.birdshel.Uciana.AI.FleetTasks;

import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Math.SystemOrbit;
import com.birdshel.Uciana.Math.TaskDoerScore;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.Ships.Ship;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ColonizationTasks {
    private static List<TaskDoerScore> colonizationDoerScores;

    ColonizationTasks() {
    }

    public static void a(Map<String, String> map, List<SystemOrbit> list) {
        colonizationDoerScores = new ArrayList();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            Fleet fleet = GameData.fleets.get(entry.getValue());
            Ship ship = fleet.getShip(entry.getKey());
            for (SystemOrbit systemOrbit : list) {
                boolean z = true;
                Iterator<Fleet> it = GameData.fleets.getFleetsInSystem(systemOrbit.systemID).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Fleet next = it.next();
                    if (GameData.empires.get(fleet.empireID).isAtWar(next.empireID) && next.hasCombatShips()) {
                        z = false;
                        break;
                    }
                }
                if (z) {
                    int planetValue = GameData.galaxy.getPlanetValue(systemOrbit.systemID, systemOrbit.orbit);
                    int turnsToSystem = fleet.getTurnsToSystem(systemOrbit.systemID);
                    if (turnsToSystem != 0) {
                        planetValue -= turnsToSystem * 4;
                    }
                    colonizationDoerScores.add(new TaskDoerScore(systemOrbit.systemID, systemOrbit.orbit, ship.getID(), planetValue));
                }
            }
        }
    }

    public static void b(Empire empire) {
        List<TaskDoerScore> e2 = FleetTaskAI.e(colonizationDoerScores);
        colonizationDoerScores = e2;
        executeColonizationTasks(empire, e2);
    }

    private static void colonizeWorld(int i, Planet planet) {
        if (planet.hasColony()) {
            return;
        }
        GameData.colonies.add(i, planet.getSystemID(), planet.getOrbit(), null);
    }

    private static void executeColonizationTasks(Empire empire, List<TaskDoerScore> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (TaskDoerScore taskDoerScore : list) {
            if (GameData.turn > 8 || taskDoerScore.score >= 30 || empire.getDiscoveredSystems().size() >= 3) {
                if (!arrayList.contains(taskDoerScore.shipID) && !arrayList2.contains(FleetTaskAI.c(taskDoerScore.systemID, taskDoerScore.orbit)) && !GameData.fleets.doesShipNoLongerExists(taskDoerScore.shipID)) {
                    arrayList.add(taskDoerScore.shipID);
                    arrayList2.add(FleetTaskAI.c(taskDoerScore.systemID, taskDoerScore.orbit));
                    Fleet usingShipID = GameData.fleets.getUsingShipID(empire.id, taskDoerScore.shipID);
                    if (!usingShipID.isMoving()) {
                        int systemID = usingShipID.getSystemID();
                        int i = taskDoerScore.systemID;
                        if (systemID == i) {
                            colonizeWorld(empire.id, (Planet) GameData.galaxy.getSystemObject(i, taskDoerScore.orbit));
                        }
                    }
                    Ship ship = usingShipID.getShip(taskDoerScore.shipID);
                    FleetTaskAI.b(usingShipID, ship, taskDoerScore);
                    FleetTaskAI.d(usingShipID, ship);
                }
            }
        }
    }
}
