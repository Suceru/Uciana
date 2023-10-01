package com.birdshel.Uciana.Colonies;

import android.util.SparseArray;

import com.birdshel.Uciana.Colonies.Buildings.BuildingID;
import com.birdshel.Uciana.Events.EventType;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Messages.PlanetExplorationMessage;
import com.birdshel.Uciana.Planets.ExplorationFind;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Scenes.ExtendedScene;
import com.birdshel.Uciana.Scenes.PlanetScene;
import com.birdshel.Uciana.Scenes.SystemScene;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.Ships.ShipType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Colonies {
    private SparseArray<Colony> colonies = new SparseArray<>();
    private final Stack<Colony> coloniesToRemove = new Stack<>();
    private final Game game;

    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Colonies.Colonies$2 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass2 {

        /* renamed from: a */
        static final /* synthetic */ int[] f1355a;
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[FilterType.values().length];
            b = iArr;
            try {
                iArr[FilterType.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[FilterType.DEVELOPED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[FilterType.SAME_SYSTEM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[SortType.values().length];
            f1355a = iArr2;
            try {
                iArr2[SortType.A_TO_Z.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1355a[SortType.Z_TO_A.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1355a[SortType.POPULATION_HIGHEST_TO_LOWEST.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1355a[SortType.POPULATION_LOWEST_TO_HIGHEST.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1355a[SortType.FOOD_HIGHEST_TO_LOWEST.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1355a[SortType.FOOD_LOWEST_TO_HIGHEST.ordinal()] = 6;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f1355a[SortType.PRODUCTION_HIGHEST_TO_LOWEST.ordinal()] = 7;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f1355a[SortType.PRODUCTION_LOWEST_TO_HIGHEST.ordinal()] = 8;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f1355a[SortType.SCIENCE_HIGHEST_TO_LOWEST.ordinal()] = 9;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f1355a[SortType.SCIENCE_LOWEST_TO_HIGHEST.ordinal()] = 10;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f1355a[SortType.CREDITS_HIGHEST_TO_LOWEST.ordinal()] = 11;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f1355a[SortType.CREDITS_LOWEST_TO_HIGHEST.ordinal()] = 12;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f1355a[SortType.ALERTS.ordinal()] = 13;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f1355a[SortType.FOOD_PER_FARMER_HIGHEST_TO_LOWEST.ordinal()] = 14;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f1355a[SortType.PRODUCTION_PER_WORKER_HIGHEST_TO_LOWEST.ordinal()] = 15;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                f1355a[SortType.SCIENCE_PER_SCIENTISTS_HIGHEST_TO_LOWEST.ordinal()] = 16;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                f1355a[SortType.BUILDINGS_HIGHEST_TO_LOWEST.ordinal()] = 17;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                f1355a[SortType.BUILDINGS_LOWEST_TO_HIGHEST.ordinal()] = 18;
            } catch (NoSuchFieldError unused21) {
            }
        }
    }

    public Colonies(Game game) {
        this.game = game;
    }

    public void add(Colony colony) {
        this.colonies.put(Functions.hashKeyFromPair((short) colony.getSystemID(), (short) colony.getOrbit()), colony);
        this.game.empires.get(colony.getEmpireID()).redistributeFood();
    }

    public void addFromSave(Colony colony) {
        this.colonies.put(Functions.hashKeyFromPair((short) colony.getSystemID(), (short) colony.getOrbit()), colony);
    }

    public void clear() {
        this.colonies = new SparseArray<>();
    }

    public void colonize(int i, Planet planet) {
        Colony newColony = new Colony.Builder().planet(planet).empireID(i).newColony(10);
        this.game.colonies.add(newColony);
        if (this.game.outposts.isOutpost(planet.getSystemID(), planet.getOrbit())) {
            Outposts outposts = this.game.outposts;
            outposts.a(outposts.getOutpost(planet.getSystemID(), planet.getOrbit()));
            newColony.addBuilding(BuildingID.SPACE_DOCK);
        }
        this.game.empires.get(i).redistributeFood();
        if (this.game.empires.get(i).isHuman()) {
            Game.gameAchievements.checkForColonizingAchievements(planet);
        }
    }

    public List<Colony> filter(List<Colony> list, FilterType filterType, Object obj) {
        ArrayList arrayList = new ArrayList();
        int i = AnonymousClass2.b[filterType.ordinal()];
        if (i != 1) {
            if (i == 2) {
                int averageDevelopmentScore = this.game.empires.get(((Integer) obj).intValue()).getAverageDevelopmentScore();
                for (Colony colony : list) {
                    if (colony.getDevelopmentScore() >= averageDevelopmentScore) {
                        arrayList.add(colony);
                    }
                }
            } else if (i == 3) {
                for (Colony colony2 : list) {
                    if (colony2.getSystemID() == ((Integer) obj).intValue()) {
                        arrayList.add(colony2);
                    }
                }
            }
            return arrayList;
        }
        return list;
    }

    public List<Colony> getColonies() {
        return Functions.asList(this.colonies);
    }

    public List<Colony> getColoniesNotInEmpire(int i) {
        Empire empire = this.game.empires.get(i);
        ArrayList arrayList = new ArrayList();
        for (Colony colony : getColonies()) {
            if (colony.getEmpireID() != i && empire.isDiscoveredSystem(colony.getSystemID())) {
                arrayList.add(colony);
            }
        }
        return arrayList;
    }

    public Colony getColony(int i, int i2) {
        return this.colonies.get(Functions.hashKeyFromPair((short) i, (short) i2));
    }

    public boolean hasColonyInSystem(int i, int i2) {
        for (int i3 = 0; i3 < this.colonies.size(); i3++) {
            Colony valueAt = this.colonies.valueAt(i3);
            if (valueAt.getSystemID() == i2 && valueAt.getEmpireID() == i) {
                return true;
            }
        }
        return false;
    }

    public boolean isColony(int i, int i2) {
        return this.colonies.indexOfKey(Functions.hashKeyFromPair((short) i, (short) i2)) > -1;
    }

    public void processTurn() {
        while (!this.coloniesToRemove.isEmpty()) {
            removeColony(this.coloniesToRemove.pop());
        }
        for (Colony colony : getColonies()) {
            colony.b();
        }
    }

    public void remove(Colony colony) {
        this.coloniesToRemove.push(colony);
    }

    public void removeColony(Colony colony) {
        int empireID = colony.getEmpireID();
        if (colony.hasBuilding(BuildingID.CAPITAL)) {
            this.game.empires.get(empireID).capitalDestroyed();
            this.game.events.addPlanetEvent(EventType.CAPITAL_DESTROYED, empireID, colony.getSystemID(), colony.getOrbit());
        } else {
            this.game.events.addPlanetEvent(EventType.COLONY_DESTROYED, empireID, colony.getSystemID(), colony.getOrbit());
        }
        this.colonies.remove(Functions.hashKeyFromPair((short) colony.getSystemID(), (short) colony.getOrbit()));
    }

    public List<Colony> sort(int i, SortType sortType) {
        return sort(getColonies(i), sortType);
    }

    public void transferColonies(int i, int i2, int i3) {
        for (Colony colony : getColonies(i2)) {
            if (colony.getSystemID() == i) {
                colony.c(i3, true);
                colony.getManufacturing().a();
                colony.getManufacturing().setEmpireID(i3);
            }
        }
    }

    public void transferColony(int i, int i2, int i3, boolean z) {
        Colony colony = getColony(i, i2);
        colony.c(i3, z);
        colony.getManufacturing().a();
        colony.getManufacturing().setEmpireID(i3);
        colony.removeBuilding(BuildingID.CAPITAL);
        if (this.game.empires.get(i3).isHuman()) {
            Game.gameAchievements.checkForColonyTakeOverAchievements(colony, z);
        }
    }

    public List<Colony> getColonies(int i) {
        ArrayList arrayList = new ArrayList();
        for (Colony colony : getColonies()) {
            if (colony.getEmpireID() == i) {
                arrayList.add(colony);
            }
        }
        return arrayList;
    }

    public List<Colony> sort(List<Colony> list, final SortType sortType) {
        if (list.isEmpty()) {
            return list;
        }
        if (sortType == SortType.NEWEST_TO_OLDEST) {
            Collections.reverse(list);
            return list;
        }
        final int empireID = list.get(0).getEmpireID();
        Collections.sort(list, new Comparator<Colony>(this) { // from class: com.birdshel.Uciana.Colonies.Colonies.1
            @Override // java.util.Comparator
            public int compare(Colony colony, Colony colony2) {
                int population;
                int population2;
                float foodPerFarmer;
                switch (AnonymousClass2.f1355a[sortType.ordinal()]) {
                    case 1:
                        return colony.getName().compareToIgnoreCase(colony2.getName());
                    case 2:
                        return colony2.getName().compareToIgnoreCase(colony.getName());
                    case 3:
                        population = colony2.getPopulation();
                        population2 = colony.getPopulation();
                        break;
                    case 4:
                        return colony.getPopulation() - colony2.getPopulation();
                    case 5:
                        population = colony2.getFoodPerTurn();
                        population2 = colony.getFoodPerTurn();
                        break;
                    case 6:
                        return colony.getFoodPerTurn() - colony2.getFoodPerTurn();
                    case 7:
                        population = colony2.getProductionPerTurn();
                        population2 = colony.getProductionPerTurn();
                        break;
                    case 8:
                        return colony.getProductionPerTurn() - colony2.getProductionPerTurn();
                    case 9:
                        population = colony2.getSciencePerTurn();
                        population2 = colony.getSciencePerTurn();
                        break;
                    case 10:
                        return colony.getSciencePerTurn() - colony2.getSciencePerTurn();
                    case 11:
                        population = colony2.getCreditsPerTurn();
                        population2 = colony.getCreditsPerTurn();
                        break;
                    case 12:
                        return colony.getCreditsPerTurn() - colony2.getCreditsPerTurn();
                    case 13:
                        if (colony.isStarving() || colony.isBlockaded() || colony.isLowPower() || colony.getPopulation() == 0) {
                            return -1;
                        }
                        return (colony2.isStarving() || colony2.isBlockaded() || colony2.isLowPower() || colony2.getPopulation() == 0) ? 1 : 0;
                    case 14:
                        population = (int) colony2.getPlanet().getFoodPerFarmer(empireID);
                        foodPerFarmer = colony.getPlanet().getFoodPerFarmer(empireID);
                        population2 = (int) foodPerFarmer;
                        break;
                    case 15:
                        population = (int) colony2.getPlanet().getProductionPerWorker(empireID);
                        foodPerFarmer = colony.getPlanet().getProductionPerWorker(empireID);
                        population2 = (int) foodPerFarmer;
                        break;
                    case 16:
                        population = (int) colony2.getPlanet().getSciencePerScientist(empireID);
                        foodPerFarmer = colony.getPlanet().getSciencePerScientist(empireID);
                        population2 = (int) foodPerFarmer;
                        break;
                    case 17:
                        population = colony2.getBuildings().size();
                        population2 = colony.getBuildings().size();
                        break;
                    case 18:
                        return colony.getBuildings().size() - colony2.getBuildings().size();
                    default:
                        return 0;
                }
                return population - population2;
            }
        });
        return list;
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0090 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0091  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void add(int i, int i2, int i3, ExtendedScene extendedScene) {
        boolean z;
        Planet planet = (Planet) this.game.galaxy.getSystemObject(i2, i3);
        ExplorationFind explorationFind = planet.getExplorationFind();
        if (!planet.hasBeenExploredByEmpire(i)) {
            if (explorationFind != ExplorationFind.STRANDED_POPULATION) {
                Fleet fleetInSystem = this.game.fleets.getFleetInSystem(i, i2);
                explorationFind.addFindBonusToEmpire(i, i2, i3, fleetInSystem, fleetInSystem.getShip(ShipType.COLONY));
                if (extendedScene != null) {
                    Game game = this.game;
                    game.planetScene.showMessage(new PlanetExplorationMessage(planet, game.getActivity().getString(R.string.exploration_colonists), planet.isUnexplored()));
                }
                planet.beenExplored(i);
            } else {
                z = true;
                if (!explorationFind.isNewColony() && explorationFind != ExplorationFind.ANCIENT_TRAP) {
                    this.game.fleets.removeColonyShip(i2, i);
                    colonize(i, planet);
                }
                if (z) {
                    explorationFind.addStrandedPopulationDiscovery(this.game.colonies.getColony(i2, i3));
                    if (extendedScene != null) {
                        Game game2 = this.game;
                        game2.planetScene.showMessage(new PlanetExplorationMessage(planet, game2.getActivity().getString(R.string.exploration_colonists), planet.isUnexplored()));
                    }
                    planet.beenExplored(i);
                }
                if (extendedScene != null) {
                    return;
                }
                if (this.game.getCurrentScene() instanceof PlanetScene) {
                    this.game.planetScene.refresh();
                    return;
                } else if (extendedScene instanceof SystemScene) {
                    extendedScene.changeScene(this.game.planetScene, Integer.valueOf(planet.getOrbit()));
                    return;
                } else {
                    this.game.planetScene.loadPlanet(planet.getSystemID(), planet.getOrbit(), this.game.planetScene);
                    extendedScene.changeScene(this.game.planetScene);
                    return;
                }
            }
        }
        z = false;
        if (!explorationFind.isNewColony()) {
            this.game.fleets.removeColonyShip(i2, i);
            colonize(i, planet);
        }
        if (z) {
        }
        if (extendedScene != null) {
        }
    }

    public List<Colony> getColonies(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        for (Colony colony : getColonies()) {
            if (colony.getPlanet().getSystemID() == i2 && colony.getEmpireID() == i) {
                arrayList.add(colony);
            }
        }
        return arrayList;
    }
}
