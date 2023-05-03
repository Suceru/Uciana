package com.birdshel.Uciana.Elements.Battle;

import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Events.EventType;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.Ships.Ship;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponent;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponentID;
import com.birdshel.Uciana.Technology.Tech;
import com.birdshel.Uciana.Technology.TechID;
import java.util.ArrayList;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Invasion {
    private final int attackerID;
    private final int attackerStrength;
    private int attackingInfantryCount;
    private final Colony colony;
    private final int defenderID;
    private final int defenderStrength;
    private int defendingInfantryCount;
    private final Fleet fleet;
    private boolean hasPlanetBeenTaken = false;
    private int attackingTroops = 0;
    private boolean done = false;
    private boolean showExploredMessage = false;

    public Invasion(Colony colony, int i) {
        this.colony = colony;
        Fleet fleetInSystem = GameData.fleets.getFleetInSystem(i, colony.getSystemID());
        this.fleet = fleetInSystem;
        this.attackerID = i;
        int empireID = colony.getEmpireID();
        this.defenderID = empireID;
        this.attackingInfantryCount = fleetInSystem.getTroopCount();
        this.defendingInfantryCount = colony.getInfDivisions();
        this.attackerStrength = GameData.empires.get(i).getGroundCombatStrength();
        this.defenderStrength = GameData.empires.get(empireID).getGroundCombatStrength() + colony.getDefenseBonus();
    }

    private void planetTaken(int i) {
        this.hasPlanetBeenTaken = true;
        GameData.events.addPlanetEvent(EventType.COLONY_INVADED, this.colony.getEmpireID(), this.colony.getSystemID(), this.colony.getOrbit());
        if (!this.colony.getPlanet().hasBeenExploredByEmpire(this.fleet.empireID)) {
            this.showExploredMessage = true;
            this.colony.getPlanet().beenExplored(this.fleet.empireID);
        }
        GameData.colonies.transferColony(this.colony.getSystemID(), this.colony.getOrbit(), this.fleet.empireID, false);
        this.colony.infLanded(i);
        if (!GameData.empires.get(this.attackerID).isHuman() || GameData.empires.get(this.defenderID).isAlive()) {
            return;
        }
        Game.gameAchievements.killOffAnEmpire();
    }

    private void unloadTransport() {
        if (this.fleet.hasTransportShip()) {
            GameData.fleets.removeTransportShip(this.colony.getSystemID(), this.attackerID);
            this.attackingTroops = 5;
            return;
        }
        for (Ship ship : this.fleet.getShips()) {
            if (ship.isCombatShip()) {
                for (ShipComponent shipComponent : ship.getShipComponents()) {
                    ShipComponentID id = shipComponent.getID();
                    ShipComponentID shipComponentID = ShipComponentID.TROOP_PODS;
                    if (id == shipComponentID) {
                        ship.removeShipComponent(shipComponentID);
                        this.attackingTroops = 5;
                        return;
                    }
                }
                continue;
            }
        }
    }

    public void check() {
        if (this.attackingTroops == 0) {
            unloadTransport();
        }
        if (this.attackingTroops == 0) {
            this.done = true;
        } else if (this.colony.getInfDivisions() == 0) {
            planetTaken(this.attackingTroops);
            this.done = true;
        }
    }

    public boolean didInvasionForceRecoverTech() {
        return Functions.percent(50);
    }

    public int getAttackerID() {
        return this.attackerID;
    }

    public int getAttackerStrength() {
        return this.attackerStrength;
    }

    public Colony getColony() {
        return this.colony;
    }

    public int getCountOfAttackingInfantry() {
        return this.attackingInfantryCount;
    }

    public int getDefenderID() {
        return this.defenderID;
    }

    public int getDefenderStrength() {
        return this.defenderStrength;
    }

    public int getDefendingInfantryCount() {
        return this.defendingInfantryCount;
    }

    public Fleet getFleet() {
        return this.fleet;
    }

    public Planet getPlanet() {
        return this.colony.getPlanet();
    }

    public TechID getTechFromDefender() {
        ArrayList arrayList = new ArrayList();
        Empire empire = GameData.empires.get(this.attackerID);
        for (TechID techID : GameData.empires.get(this.defenderID).getTech().getFinishedTechs()) {
            if (!empire.hasTech(techID)) {
                arrayList.add(techID);
            }
        }
        if (arrayList.isEmpty()) {
            return TechID.NONE;
        }
        TechID techID2 = (TechID) arrayList.get(Functions.random.nextInt(arrayList.size()));
        Tech tech = empire.getTech().getTech(techID2);
        tech.addResearch(tech.getResearchCost());
        GameData.empires.get(this.attackerID).checkForUpgrade(techID2);
        GameData.events.addTechEvent(this.attackerID, tech.getID().ordinal(), 2);
        return techID2;
    }

    public boolean hasPlanetBeenTaken() {
        return this.hasPlanetBeenTaken;
    }

    public boolean isDone() {
        return this.done;
    }

    public void removeAttacker() {
        this.attackingTroops--;
        this.attackingInfantryCount--;
    }

    public void removeDefender() {
        this.colony.infKilledOff();
        this.defendingInfantryCount--;
    }

    public boolean showExploredMessage() {
        return this.showExploredMessage;
    }
}
