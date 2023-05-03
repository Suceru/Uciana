package com.birdshel.Uciana.AI.FleetTasks;

import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Math.SystemOrbit;
import com.birdshel.Uciana.Planets.SystemObject;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.Ships.Ship;
import com.birdshel.Uciana.Ships.ShipType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class AutoFleetTaskAI {
    private static Empire empire;
    private static List<SystemOrbit> explorationTasks;
    private static Map<String, String> scoutShips;
    private static List<Integer> scoutingTasks;

    private static void checkSystem(int index) {
        for (SystemObject systemObject : GameData.galaxy.getStarSystem(index).getSystemObjects()) {
            if (!systemObject.isNothing() && systemObject.isPlanet() && !systemObject.hasBeenExploredByEmpire(empire.id)) {
                explorationTasks.add(new SystemOrbit(systemObject.getSystemID(), systemObject.getOrbit()));
            }
        }
    }

    public static void execute(int i) {
        empire = GameData.empires.get(i);
        scoutShips = new HashMap();
        scoutingTasks = new ArrayList();
        explorationTasks = new ArrayList();
        fillScoutingShips();
        if (scoutShips.isEmpty()) {
            return;
        }
        fillScoutTasks();
        ScoutExploreTasks.a(scoutShips, scoutingTasks, explorationTasks);
        ScoutExploreTasks.b(empire);
    }

    private static void fillScoutTasks() {
        int fuelCellRange = empire.getTech().getFuelCellRange();
        List<Integer> friendlyStarSystems = empire.getFriendlyStarSystems();
        HashSet<Integer> hashSet = new HashSet(friendlyStarSystems);
        for (Integer num : friendlyStarSystems) {
            int intValue = num.intValue();
            if (empire.isDiscoveredSystem(intValue)) {
                hashSet.addAll(GameData.galaxy.getWormholes(intValue));
            }
            List<Integer> starsInRange = GameData.galaxy.getStarsInRange(intValue, fuelCellRange);
            hashSet.addAll(starsInRange);
            for (Integer num2 : starsInRange) {
                int intValue2 = num2.intValue();
                if (empire.isDiscoveredSystem(intValue2)) {
                    hashSet.addAll(GameData.galaxy.getWormholes(intValue2));
                }
            }
        }
        for (Integer num3 : hashSet) {
            int intValue3 = num3.intValue();
            if (empire.isDiscoveredSystem(intValue3)) {
                checkSystem(intValue3);
            } else {
                scoutingTasks.add(Integer.valueOf(intValue3));
            }
        }
    }

    private static void fillScoutingShips() {
        for (Fleet fleet : GameData.fleets.getFleetsByEmpire(empire.id)) {
            for (Ship ship : fleet.getShips()) {
                if (ship.getShipType() == ShipType.SCOUT && ship.isAutoOn()) {
                    scoutShips.put(ship.getID(), fleet.id);
                }
            }
        }
    }
}
