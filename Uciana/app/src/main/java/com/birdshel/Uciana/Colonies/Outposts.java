package com.birdshel.Uciana.Colonies;

import com.birdshel.Uciana.Events.EventType;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Messages.PlanetExplorationMessage;
import com.birdshel.Uciana.Planets.ExplorationFind;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.SystemObject;
import com.birdshel.Uciana.Planets.SystemObjectType;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Scenes.ExtendedScene;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.Ships.ShipType;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Outposts {
    private final Game game;
    private List<Outpost> outposts = new ArrayList();
    private final Stack<Outpost> outpostsToRemove = new Stack<>();

    public Outposts(Game game) {
        this.game = game;
    }

    private int getCountOfOutpostsByType(int i, SystemObjectType systemObjectType) {
        int i2 = 0;
        for (Outpost outpost : this.outposts) {
            if (outpost.getEmpireID() == i && this.game.galaxy.getSystemObject(outpost.getSystemID(), outpost.getOrbit()).getSystemObjectType() == systemObjectType) {
                i2++;
            }
        }
        return i2;
    }

    public void a(Outpost outpost) {
        this.outposts.remove(outpost);
    }

    public void add(Outpost outpost) {
        this.outposts.add(outpost);
    }

    public void clear() {
        this.outposts = new ArrayList();
    }

    public int getCountOfMiningStations(int i) {
        return getCountOfOutpostsByType(i, SystemObjectType.ASTEROID_BELT);
    }

    public int getCountOfOutpostAroundPlanets(int i) {
        return getCountOfOutpostsByType(i, SystemObjectType.PLANET);
    }

    public int getCountOfResearchStations(int i) {
        return getCountOfOutpostsByType(i, SystemObjectType.GAS_GIANT);
    }

    public int getID(int i, int i2) {
        int i3 = 0;
        for (Outpost outpost : this.outposts) {
            if (outpost.getSystemID() == i && outpost.getOrbit() == i2) {
                return i3;
            }
            i3++;
        }
        return -2;
    }

    public Outpost getOutpost(int i, int i2) {
        for (Outpost outpost : this.outposts) {
            if (outpost.getSystemID() == i && outpost.getOrbit() == i2) {
                return outpost;
            }
        }
        throw new AssertionError("Requested outpost at system " + i + " in orbit " + i2 + " does not exists");
    }

    public List<Outpost> getOutposts(int i) {
        ArrayList arrayList = new ArrayList();
        for (Outpost outpost : this.outposts) {
            if (outpost.getEmpireID() == i) {
                arrayList.add(outpost);
            }
        }
        return arrayList;
    }

    public boolean hasOutpostInSystem(int i, int i2) {
        for (Outpost outpost : this.outposts) {
            if (outpost.getSystemID() == i2 && outpost.getEmpireID() == i) {
                return true;
            }
        }
        return false;
    }

    public boolean isOutpost(int i, int i2) {
        for (Outpost outpost : this.outposts) {
            if (outpost.getSystemID() == i && outpost.getOrbit() == i2) {
                return true;
            }
        }
        return false;
    }

    public void processTurn() {
        while (!this.outpostsToRemove.isEmpty()) {
            removeOutpost(this.outpostsToRemove.pop());
        }
    }

    public void remove(int i) {
        this.outposts.remove(i);
    }

    public void removeOutpost(Outpost outpost) {
        this.game.events.addPlanetEvent(EventType.OUTPOST_DESTROYED, outpost.getEmpireID(), outpost.getSystemID(), outpost.getOrbit());
        this.outposts.remove(outpost);
    }

    public void transferOutposts(int i, int i2, int i3) {
        for (Outpost outpost : getOutposts(i2)) {
            if (outpost.getSystemID() == i) {
                outpost.a(i3);
            }
        }
    }

    public void add(int i, int i2, int i3, ExtendedScene extendedScene) {
        SystemObject systemObject = this.game.galaxy.getSystemObject(i2, i3);
        if (this.game.empires.get(i).isHuman()) {
            Game.gameAchievements.checkForOutpostAchievements(systemObject);
        }
        if (!systemObject.hasBeenExploredByEmpire(i) && systemObject.isPlanet()) {
            Planet planet = (Planet) systemObject;
            ExplorationFind explorationFind = planet.getExplorationFind();
            Fleet fleetInSystem = this.game.fleets.getFleetInSystem(i, i2);
            explorationFind.addFindBonusToEmpire(i, systemObject.getSystemID(), systemObject.getOrbit(), fleetInSystem, fleetInSystem.getShip(ShipType.CONSTRUCTION));
            extendedScene.showMessage(new PlanetExplorationMessage(planet, this.game.getActivity().getString(R.string.exploration_engineers), planet.isUnexplored()));
            systemObject.beenExplored(i);
            if (explorationFind.isNewOutpost() || explorationFind == ExplorationFind.ANCIENT_TRAP) {
                extendedScene.refresh();
                return;
            }
        }
        this.game.outposts.add(new Outpost(systemObject, i));
        this.game.fleets.removeOutpostShip(i2, i);
        extendedScene.refresh();
    }

    public void remove(Outpost outpost) {
        this.outpostsToRemove.push(outpost);
    }

    public List<Outpost> getOutposts() {
        return this.outposts;
    }

    public void removeOutpost(int i, int i2) {
        for (Outpost outpost : this.outposts) {
            if (outpost.getSystemID() == i && outpost.getOrbit() == i2) {
                this.outposts.remove(outpost);
                return;
            }
        }
    }
}
