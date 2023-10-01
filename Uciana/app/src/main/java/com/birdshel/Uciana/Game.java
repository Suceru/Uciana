package com.birdshel.Uciana;

import android.os.AsyncTask;
import android.os.Vibrator;
import android.util.SparseArray;

import com.birdshel.Uciana.AI.AIAttack;
import com.birdshel.Uciana.AI.AscendedAttack;
import com.birdshel.Uciana.AI.MonsterAttack;
import com.birdshel.Uciana.Achievements.GameAchievements;
import com.birdshel.Uciana.Colonies.Buildings.BuildingID;
import com.birdshel.Uciana.Colonies.Buildings.Buildings;
import com.birdshel.Uciana.Colonies.Colonies;
import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Colonies.Outposts;
import com.birdshel.Uciana.Elements.Pools.PlanetSpritePool;
import com.birdshel.Uciana.Elements.Pools.ShipSpriteBattlePool;
import com.birdshel.Uciana.Elements.Pools.ShipSpritePool;
import com.birdshel.Uciana.Elements.Pools.StarSpritePool;
import com.birdshel.Uciana.Events.Event;
import com.birdshel.Uciana.Events.EventDataFields;
import com.birdshel.Uciana.Events.EventType;
import com.birdshel.Uciana.Events.Events;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Overlays.ConfirmOverlay;
import com.birdshel.Uciana.Overlays.KeyboardOverlay;
import com.birdshel.Uciana.Overlays.ShipDetailOverlay;
import com.birdshel.Uciana.Overlays.ShipSelectOverlay;
import com.birdshel.Uciana.Planets.Climate;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.Resources;
import com.birdshel.Uciana.Planets.SystemObject;
import com.birdshel.Uciana.Players.CreateEmpireInfo;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.Players.EmpireType;
import com.birdshel.Uciana.Players.Empires;
import com.birdshel.Uciana.Players.PlayerSettings;
import com.birdshel.Uciana.Players.RaceAttribute;
import com.birdshel.Uciana.RandomEvents.RandomEventData;
import com.birdshel.Uciana.RandomEvents.RandomEventType;
import com.birdshel.Uciana.RandomEvents.RandomEvents;
import com.birdshel.Uciana.Resources.Fonts;
import com.birdshel.Uciana.Resources.GameMusic;
import com.birdshel.Uciana.Resources.Graphics;
import com.birdshel.Uciana.Resources.ModValues;
import com.birdshel.Uciana.Resources.OptionID;
import com.birdshel.Uciana.Resources.Options;
import com.birdshel.Uciana.Resources.Sounds;
import com.birdshel.Uciana.Resources.SupportedLocales;
import com.birdshel.Uciana.SaveGameData.Database;
import com.birdshel.Uciana.SaveGameData.SavedGameDetails;
import com.birdshel.Uciana.Scenes.AttackScene;
import com.birdshel.Uciana.Scenes.BattleScene;
import com.birdshel.Uciana.Scenes.BuildListScene;
import com.birdshel.Uciana.Scenes.BuildingsScene;
import com.birdshel.Uciana.Scenes.ColoniesScene;
import com.birdshel.Uciana.Scenes.CustomizeAttributesScene;
import com.birdshel.Uciana.Scenes.EmpireInfoScene;
import com.birdshel.Uciana.Scenes.EmpireScene;
import com.birdshel.Uciana.Scenes.EventsScene;
import com.birdshel.Uciana.Scenes.ExtendedScene;
import com.birdshel.Uciana.Scenes.FleetsScene;
import com.birdshel.Uciana.Scenes.GalaxyScene;
import com.birdshel.Uciana.Scenes.LoadSaveScene;
import com.birdshel.Uciana.Scenes.MenuScene;
import com.birdshel.Uciana.Scenes.MovePopulationScene;
import com.birdshel.Uciana.Scenes.OptionsScene;
import com.birdshel.Uciana.Scenes.PlanetScene;
import com.birdshel.Uciana.Scenes.PlayerCreationScene;
import com.birdshel.Uciana.Scenes.ProductionScene;
import com.birdshel.Uciana.Scenes.RaceDiscussScene;
import com.birdshel.Uciana.Scenes.RaceScene;
import com.birdshel.Uciana.Scenes.RacesScene;
import com.birdshel.Uciana.Scenes.SelectAttackScene;
import com.birdshel.Uciana.Scenes.SetupScene;
import com.birdshel.Uciana.Scenes.ShipDesignScene;
import com.birdshel.Uciana.Scenes.StatsScene;
import com.birdshel.Uciana.Scenes.SystemScene;
import com.birdshel.Uciana.Scenes.TechScene;
import com.birdshel.Uciana.Scenes.TurnScene;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.Ships.Fleets;
import com.birdshel.Uciana.Ships.Ship;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponentID;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponents;
import com.birdshel.Uciana.Ships.ShipComponents.Weapon;
import com.birdshel.Uciana.Ships.ShipType;
import com.birdshel.Uciana.StarSystems.Galaxy;
import com.birdshel.Uciana.StarSystems.GalaxySize;
import com.birdshel.Uciana.StarSystems.Nebulas;
import com.birdshel.Uciana.StarSystems.StarSystem;
import com.birdshel.Uciana.Technology.TechID;
import com.birdshel.Uciana.Utility.Log;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Game {
    private static Difficulty difficulty = Difficulty.NORMAL;
    public static GameAchievements gameAchievements;
    public static ModValues modValues;
    public static Options options;
    public long[] BUTTON_VIBRATE;
    public long[] SLIDER_VIBRATE;
    private final Uciana activity;
    private int ascendedID;
    public AttackScene attackScene;
    private boolean autoSaved;
    public BattleScene battleScene;
    public BuildListScene buildListScene;
    public BuildingsScene buildingsScene;
    public Camera camera;
    public Colonies colonies;
    public ColoniesScene coloniesScene;
    public ConfirmOverlay confirmOverlay;
    private int currentPlayer;
    private ExtendedScene currentScene;
    public CustomizeAttributesScene customizeAttributesScene;
    private Database database;
    public long elapsedTime;
    public EmpireInfoScene empireInfoScene;
    public EmpireScene empireScene;
    public Empires empires;
    private Engine engine;
    public Events events;
    public EventsScene eventsScene;
    private TextureRegion explorerTextureRegion;
    public Fleets fleets;
    public FleetsScene fleetsScene;
    public Fonts fonts;
    public Galaxy galaxy;
    public GalaxyScene galaxyScene;
    private boolean gameEnded;
    public GameSettings gameSettings;
    public Graphics graphics;
    private final List<Integer> homeSystems;
    public KeyboardOverlay keyboardOverlay;
    public LoadSaveScene loadSaveScene;
    private TextureRegion loadingTextureRegion;
    private final SupportedLocales locale;
    private TextureRegion logoTextureRegion;
    public MenuScene menuScene;
    public MovePopulationScene movePopulationScene;
    public GameMusic music;
    public Nebulas nebulas;
    public OptionsScene optionsScene;
    public Outposts outposts;
    public PlanetScene planetScene;
    public PlanetSpritePool planetSpritePool;
    public PlayerCreationScene playerCreationScene;
    public Map<Integer, PlayerSettings> playerSettings;
    private final int[] playerTurnStatus;
    public ProductionScene productionScene;
    public RaceDiscussScene raceDiscussScene;
    public RaceScene raceScene;
    public RacesScene racesScene;
    private RandomEventData randomEventData;
    public RandomEvents randomEvents;
    public SelectAttackScene selectAttackScene;
    private List<Event> selectAttacks;
    public SetupScene setupScene;
    public ShipDesignScene shipDesignScene;
    public ShipDetailOverlay shipDetailOverlay;
    public ShipSelectOverlay shipSelectOverlay;
    public ShipSpriteBattlePool shipSpriteBattlePool;
    public ShipSpritePool shipSpritePool;
    public Sounds sounds;
    private TextureRegion splashBackgroundTextureRegion;
    public StarSpritePool starSpritePool;
    public long startTime;
    public StatsScene statsScene;
    private TextureRegion surfaceTextureRegion;
    public SystemScene systemScene;
    public TechScene techScene;
    private TextureRegion titleTextureRegion;
    public TurnScene turnScene;
    private final int width;

    public Game(Uciana uciana, Engine engine, Camera camera, int i, SupportedLocales supportedLocales) {
        this.galaxy = new Galaxy(this);
        this.colonies = new Colonies(this);
        this.empires = new Empires(this);
        this.fleets = new Fleets();
        this.outposts = new Outposts(this);
        this.events = new Events();
        this.gameSettings = new GameSettings();
        this.randomEvents = new RandomEvents(this);
        this.currentPlayer = -1;
        this.playerTurnStatus = new int[7];
        this.elapsedTime = 0L;
        this.ascendedID = 0;
        this.autoSaved = true;
        this.playerSettings = new HashMap();
        this.homeSystems = new ArrayList();
        this.selectAttacks = new ArrayList();
        this.BUTTON_VIBRATE = new long[]{0, 10};
        this.SLIDER_VIBRATE = new long[]{0, 5, 100};
        this.activity = uciana;
        this.engine = engine;
        this.camera = camera;
        this.width = i;
        this.locale = supportedLocales;
        GameData.activity = uciana;
        options = new Options(uciana);
        modValues = new ModValues(uciana);
        gameAchievements = new GameAchievements(this, uciana);
        this.startTime = System.currentTimeMillis();
        this.database = new Database(uciana.getApplicationContext(), this);
        initialize();
    }

    private void addColonyToHomeworld(int i, Planet planet) {
        Colony.Builder empireID = new Colony.Builder().planet(planet).empireID(i);
        Empires empires = this.empires;
        Colony newColony = empireID.name(empires.getHomeworldName(empires.get(i).getRaceID())).newColony(70);
        newColony.addBuilding(BuildingID.CAPITAL);
        newColony.addBuilding(BuildingID.SHIP_YARDS);
        newColony.addBuilding(BuildingID.NUCLEAR_POWERPLANT);
        newColony.addBuilding(BuildingID.INFANTRY_BARRACKS);
        newColony.addBuilding(BuildingID.TORPEDO_TURRET);
        newColony.setFarmersPercent(45);
        newColony.setWorkersPercent(30);
        newColony.setScientistPercent(25);
        this.colonies.add(newColony);
        newColony.setInfDivisions(newColony.getInfantryCapacity() / 2);
        this.empires.get(i).checkForCapital();
    }

    private void addStartingFleet(int i, int i2) {
        Empire empire = this.empires.get(i);
        Fleet fleet = new Fleet(i, i2);
        fleet.addShip(new Ship.Builder().id(empire.getNextShipID()).shipType(ShipType.SCOUT).empireID(i).buildNonCombatShip());
        fleet.addShip(new Ship.Builder().id(empire.getNextShipID()).shipType(ShipType.COLONY).empireID(i).buildNonCombatShip());
        this.fleets.add(fleet);
    }

    private void aiSelectAttack(int i, int i2) {
        new AIAttack(this, i, i2).execute();
    }

    private void autoSaveGame() {
        if (((int) options.getOption(OptionID.AUTOSAVE, 2.0f)) != 0) {
            Options options2 = options;
            OptionID optionID = OptionID.LAST_AUTOSAVE_SLOT;
            if (options2.getOption(optionID, 1.0f) == 1.0f) {
                options.setOption(optionID, 0.0f);
                save(0);
                return;
            }
            options.setOption(optionID, 1.0f);
            save(4);
        }
    }

    private void checkToSeeIfFleetIsStillInRange(Fleet fleet, List<Integer> list) {
        if (fleet.isMoving() || list.isEmpty()) {
            return;
        }
        int fuelCellRange = this.empires.get(fleet.empireID).getTech().getFuelCellRange();
        ArrayList arrayList = new ArrayList();
        if (this.galaxy.hasWormholes(fleet.getSystemID())) {
            arrayList.addAll(this.galaxy.getWormholeSystemEndpoints(fleet.getSystemID()));
        } else {
            arrayList.add(Integer.valueOf(fleet.getSystemID()));
        }
        if (this.galaxy.isFleetStillInRange(arrayList, list, fuelCellRange)) {
            return;
        }
        fleet.move(this.galaxy.getClosestEmpireSystem(fleet.getSystemID(), list));
        fleet.setMoving();
    }

    private Planet createHomeworld(int i, int i2, EmpireType empireType) {
        this.galaxy.getStarSystem(i2).setName(this.empires.getHomeSystemName(i));
        return (Planet) this.galaxy.getStarSystem(i2).getSystemObject(this.empires.createHomeworld(i, i2, empireType));
    }

    private void doRandomEvent() {
        RandomEventType currentRandomEvent = this.randomEvents.getCurrentRandomEvent();
        RandomEventType randomEventType = RandomEventType.NONE;
        if (currentRandomEvent == randomEventType) {
            this.randomEvents.setNextToBeCurrentRandomEvent();
        }
        if (!this.randomEvents.canCurrentRandomEventStillHappen()) {
            this.randomEvents.setRandomEvent(randomEventType, -1, -1, -1);
            return;
        }
        this.randomEventData = new RandomEventData(this.randomEvents.getCurrentRandomEvent(), this.randomEvents.getCurrentRandomEventData1(), this.randomEvents.getCurrentRandomEventData2(), this.randomEvents.getCurrentRandomEventData3());
        int executeCurrentRandomEvent = this.randomEvents.executeCurrentRandomEvent();
        if (executeCurrentRandomEvent != -1) {
            this.events.addRandomEvent(this.randomEventData, executeCurrentRandomEvent);
        }
    }

    private void doTurn() {
        boolean z;
        Iterator<Empire> it = this.empires.getEmpires().iterator();
        while (true) {
            z = true;
            if (!it.hasNext()) {
                break;
            }
            Empire next = it.next();
            if (next.getEmpireSetting("auto_research") == 1 && next.getCurrentTech().getID() == TechID.NONE) {
                next.getResearchAI().manage();
            }
        }
        SparseArray<List<Integer>> friendlySystems = getFriendlySystems();
        for (Fleet fleet : this.fleets.getFleets()) {
            fleet.resetStatus();
            if (!GameProperties.isNonNormalEmpire(fleet.empireID)) {
                checkToSeeIfFleetIsStillInRange(fleet, friendlySystems.get(fleet.empireID));
            }
        }
        int option = (int) options.getOption(OptionID.AUTOSAVE, 2.0f);
        if (option == 2 ? GameData.turn % 10 != 0 : option == 3 ? GameData.turn % 5 != 0 : option != 4) {
            z = false;
        }
        if (z) {
            try {
                Options options2 = options;
                OptionID optionID = OptionID.LAST_AUTOSAVE_SLOT;
                if (options2.getOption(optionID, 1.0f) == 1.0f) {
                    options.setOption(optionID, 0.0f);
                    save(0);
                } else {
                    options.setOption(optionID, 1.0f);
                    save(4);
                }
            } catch (Exception unused) {
                Log.message("Autosave", "Autosave Failed");
            }
        }
        if (options.isOn(OptionID.DEBUG)) {
            setCurrentPlayer(this.currentPlayer);
            setCurrentScene(this.galaxyScene);
            return;
        }
        figureOutPlayerScreen();
    }

    private void figureOutPlayerScreen() {
        if (getHumanPlayerCount() <= 1 && !GameData.showTurnScene) {
            this.currentPlayer = getHumanPlayers().get(0).intValue();
            ExtendedScene currentScene = getCurrentScene();
            GalaxyScene galaxyScene = this.galaxyScene;
            if (currentScene != galaxyScene) {
                getCurrentScene().changeScene(this.galaxyScene);
                return;
            } else {
                galaxyScene.refresh();
                return;
            }
        }
        GameData.showTurnScene = false;
        this.currentPlayer = -1;
        getCurrentScene().changeScene(this.turnScene);
    }

    private int getAscendedID() {
        int i = this.ascendedID + 1;
        this.ascendedID = i;
        return i;
    }

    public static Difficulty getDifficulty() {
        return difficulty;
    }

    private SparseArray<List<Integer>> getFriendlySystems() {
        SparseArray<List<Integer>> sparseArray = new SparseArray<>();
        for (Empire empire : this.empires.getEmpires()) {
            sparseArray.put(empire.id, empire.getFriendlyStarSystems());
        }
        return sparseArray;
    }

    public static float getProductionDifficultyModifier() {
        return difficulty.getProductionModifier();
    }

    private int getRandomHomeSystem() {
        for (StarSystem starSystem : this.galaxy.getStarSystems()) {
            if (!this.homeSystems.contains(Integer.valueOf(starSystem.getID()))) {
                for (StarSystem starSystem2 : this.galaxy.getStarSystems()) {
                    if (starSystem2.getID() != starSystem.getID() && this.galaxy.getDistance(starSystem.getID(), starSystem2.getID()) <= 10) {
                        this.homeSystems.add(Integer.valueOf(starSystem.getID()));
                        return starSystem.getID();
                    }
                }
                continue;
            }
        }
        for (StarSystem starSystem3 : this.galaxy.getStarSystems()) {
            if (!this.homeSystems.contains(Integer.valueOf(starSystem3.getID()))) {
                this.homeSystems.add(Integer.valueOf(starSystem3.getID()));
                return starSystem3.getID();
            }
        }
        return -1;
    }

    public static float getResearchDifficultyModifier() {
        return difficulty.getResearchCostModifier();
    }

    private void initialize() {
        this.menuScene = new MenuScene(this);
        this.galaxyScene = new GalaxyScene(this);
        this.systemScene = new SystemScene(this);
        this.planetScene = new PlanetScene(this);
        this.coloniesScene = new ColoniesScene(this);
        this.setupScene = new SetupScene(this);
        this.techScene = new TechScene(this);
        this.empireScene = new EmpireScene(this);
        this.racesScene = new RacesScene(this);
        this.loadSaveScene = new LoadSaveScene(this);
        this.productionScene = new ProductionScene(this);
        this.optionsScene = new OptionsScene(this);
        this.eventsScene = new EventsScene(this);
        this.playerCreationScene = new PlayerCreationScene(this);
        this.fleetsScene = new FleetsScene(this);
        this.buildingsScene = new BuildingsScene(this);
        this.turnScene = new TurnScene(this);
        this.selectAttackScene = new SelectAttackScene(this);
        this.attackScene = new AttackScene(this);
        this.shipDesignScene = new ShipDesignScene(this);
        this.movePopulationScene = new MovePopulationScene(this);
        this.battleScene = new BattleScene(this);
        this.raceScene = new RaceScene(this);
        this.empireInfoScene = new EmpireInfoScene(this);
        this.customizeAttributesScene = new CustomizeAttributesScene(this);
        this.statsScene = new StatsScene(this);
        this.buildListScene = new BuildListScene(this);
        this.raceDiscussScene = new RaceDiscussScene(this);
        GameData.galaxy = this.galaxy;
        GameData.colonies = this.colonies;
        GameData.empires = this.empires;
        GameData.fleets = this.fleets;
        GameData.outposts = this.outposts;
        GameData.events = this.events;
        GameData.gameSettings = this.gameSettings;
        GameData.randomEvents = this.randomEvents;
        this.currentScene = this.menuScene;
    }

    public /* synthetic */ void lambda$processTurn$0() {
        Iterator<Fleet> it = this.fleets.getFleetsByEmpire(8).iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Fleet next = it.next();
            int size = GameData.fleets.getFleetsInSystem(next.getSystemID()).size() - 1;
            for (int i = 0; i < size; i++) {
                GameData.events.addSelectAttackEvent(next.empireID, next.getSystemID());
            }
        }
        ArrayList<Fleet> arrayList = new ArrayList();
        for (Fleet fleet : this.fleets.getFleetsByEmpire(9)) {
            if (fleet.inOrbit()) {
                boolean z = GameData.fleets.getFleetsInSystem(fleet.getSystemID()).size() - 1 > 0;
                for (SystemObject systemObject : this.galaxy.getStarSystem(fleet.getSystemID()).getSystemObjects()) {
                    if (systemObject.isOccupied()) {
                        z = true;
                    }
                }
                if (z && ((Integer) fleet.getData()).intValue() < 2) {
                    GameData.events.addSelectAttackEvent(fleet.empireID, fleet.getSystemID());
                    fleet.setData(Integer.valueOf(((Integer) fleet.getData()).intValue() + 1));
                } else {
                    arrayList.add(fleet);
                }
            }
        }
        for (Fleet fleet2 : arrayList) {
            this.fleets.remove(fleet2);
        }
        for (Empire empire : this.empires.getEmpires()) {
            if (empire.isAlive()) {
                if (empire.isType(EmpireType.AI)) {
                    empire.doAiTasks();
                } else if (empire.isType(EmpireType.HUMAN)) {
                    empire.doAutoTasks();
                }
            }
        }
        this.colonies.processTurn();
        this.outposts.processTurn();
        this.fleets.processTurn();
        this.empires.processTurn();
        GameData.turn++;
        for (int i2 = 0; i2 < 7; i2++) {
            this.playerTurnStatus[i2] = 0;
        }
        this.selectAttacks = this.events.getEvents(EventType.SELECT_ATTACK);
        if (this.gameSettings.isRandomEventsOn() && (this.randomEvents.getCurrentRandomEvent() != RandomEventType.NONE || this.randomEvents.getNextRandomEventTurn() <= GameData.turn)) {
            doRandomEvent();
        }
        beginTurn();
    }

    private void processTurn() {
        AsyncTask.execute(new Runnable() { // from class: com.birdshel.Uciana.a
            {
                Game.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                Game.this.lambda$processTurn$0();
            }
        });
    }

    private void selectAttack() {
        Event event = this.selectAttacks.get(0);
        this.selectAttacks.remove(0);
        this.events.removeEvent(event);
        int intValue = ((Integer) event.getEventData(EventDataFields.SYSTEM_ID)).intValue();
        int empireID = event.getEmpireID();
        if (empireID == 8) {
            if (AscendedAttack.canAttack(intValue)) {
                new AscendedAttack(this, intValue).execute();
            } else {
                beginTurn();
            }
        } else if (empireID == 9) {
            if (MonsterAttack.canAttack(intValue)) {
                new MonsterAttack(this, intValue).execute();
            } else {
                beginTurn();
            }
        } else if (this.empires.get(empireID).canAttack(intValue)) {
            if (this.empires.get(empireID).isHuman()) {
                showSelectAttack(empireID, intValue);
            } else {
                aiSelectAttack(empireID, intValue);
            }
        } else {
            beginTurn();
        }
    }

    public static void setDifficulty(Difficulty difficulty2) {
        difficulty = difficulty2;
    }

    private void setupPlayers(CreateEmpireInfo[] createEmpireInfoArr) {
        int randomHomeSystem;
        List<RaceAttribute> list;
        int i = 0;
        for (CreateEmpireInfo createEmpireInfo : createEmpireInfoArr) {
            if (createEmpireInfo.getEmpireType() != EmpireType.NONE) {
                i++;
            }
        }
        List<Integer> startingSectors = this.galaxy.getStartingSectors(i);
        int i2 = 0;
        for (int i3 = 0; i3 < 7; i3++) {
            if (options.getOption(OptionID.STARTING_POSITIONS, 0.0f) == 0.0f) {
                if (createEmpireInfoArr[i3].getEmpireType() != EmpireType.NONE) {
                    randomHomeSystem = this.galaxy.getCenterStarInSector(startingSectors.get(i2).intValue(), this.homeSystems);
                    if (randomHomeSystem == -1) {
                        randomHomeSystem = getRandomHomeSystem();
                    }
                    this.homeSystems.add(Integer.valueOf(randomHomeSystem));
                    i2++;
                } else {
                    randomHomeSystem = getRandomHomeSystem();
                }
            } else {
                randomHomeSystem = getRandomHomeSystem();
            }
            Planet createHomeworld = createHomeworld(createEmpireInfoArr[i3].getRaceID(), randomHomeSystem, createEmpireInfoArr[i3].getEmpireType());
            if (createEmpireInfoArr[i3].getEmpireType() == EmpireType.HUMAN) {
                list = createEmpireInfoArr[i3].getRaceAttributes();
            } else {
                Stack stack = new Stack();
                stack.addAll(Arrays.asList(RaceAttribute.values()));
                Collections.shuffle(stack);
                ArrayList arrayList = new ArrayList();
                arrayList.add((RaceAttribute) stack.pop());
                arrayList.add((RaceAttribute) stack.pop());
                list = arrayList;
            }
            Empire newEmpire = new Empire.Builder().id(i3).type(createEmpireInfoArr[i3].getEmpireType()).homeSystemID(createHomeworld.getSystemID()).homeWorldOrbit(createHomeworld.getOrbit()).attributes(list).bannerID(createEmpireInfoArr[i3].getBannerID()).shipStyleID(createEmpireInfoArr[i3].getShipStyleID()).raceID(createEmpireInfoArr[i3].getRaceID()).newEmpire();
            this.empires.add(newEmpire);
            newEmpire.setDefaultShipDesigns();
            if (createEmpireInfoArr[i3].getEmpireType() != EmpireType.NONE) {
                addColonyToHomeworld(i3, createHomeworld);
                addStartingFleet(i3, createHomeworld.getSystemID());
            } else {
                Fleet fleet = new Fleet(8, createHomeworld.getSystemID());
                ArrayList arrayList2 = new ArrayList();
                Weapon weapon = (Weapon) ShipComponents.get(ShipComponentID.DISRUPTOR);
                weapon.setComponentCount(8);
                arrayList2.add(weapon);
                Weapon weapon2 = (Weapon) ShipComponents.get(ShipComponentID.ANTIMATTER_TORPEDO);
                weapon2.setComponentCount(5);
                arrayList2.add(weapon2);
                Weapon weapon3 = (Weapon) ShipComponents.get(ShipComponentID.SUBSPACE_CHARGE);
                weapon3.setComponentCount(5);
                arrayList2.add(weapon3);
                Ship.Builder id = new Ship.Builder().id("AD-" + getAscendedID());
                ShipType shipType = ShipType.BATTLESHIP;
                fleet.addShip(id.shipType(shipType).name(shipType.getString(8)).empireID(8).designNumber(0).productionCost(0).armor(ShipComponents.getArmors().get(2)).shield(ShipComponents.getShields().get(2)).targetingComputer(ShipComponents.getTargetingComputers().get(2)).sublightEngine(ShipComponents.getSublightEngines().get(2)).shipComponents(arrayList2).buildCombatShip());
                this.fleets.add(fleet);
            }
            newEmpire.setIsAlive();
        }
        if (getHumanPlayerCount() > 1) {
            this.currentPlayer = -1;
            return;
        }
        for (int i4 = 0; i4 < 7; i4++) {
            if (createEmpireInfoArr[i4].getEmpireType() == EmpireType.HUMAN) {
                this.currentPlayer = i4;
                return;
            }
        }
    }

    private void showSelectAttack(int i, int i2) {
        getCurrentScene().changeScene(this.selectAttackScene, new Point(i, i2));
    }

    public void b() {
        System.gc();
        this.shipSpritePool = new ShipSpritePool(this, this.activity.getVertexBufferObjectManager());
        this.planetSpritePool = new PlanetSpritePool(this, this.activity.getVertexBufferObjectManager());
        this.shipSpriteBattlePool = new ShipSpriteBattlePool(this, this.activity.getVertexBufferObjectManager());
        this.starSpritePool = new StarSpritePool(this, this.activity.getVertexBufferObjectManager());
        this.nebulas = new Nebulas(this, this.activity.getVertexBufferObjectManager());
        this.keyboardOverlay = new KeyboardOverlay(this, this.activity.getVertexBufferObjectManager());
        this.shipSelectOverlay = new ShipSelectOverlay(this, this.activity.getVertexBufferObjectManager());
        this.shipDetailOverlay = new ShipDetailOverlay(this, this.activity.getVertexBufferObjectManager());
        this.confirmOverlay = new ConfirmOverlay(this, this.activity.getVertexBufferObjectManager());
        this.menuScene.createScene(this.activity.getVertexBufferObjectManager());
        this.galaxyScene.createScene(this.activity.getVertexBufferObjectManager());
        this.systemScene.createScene(this.activity.getVertexBufferObjectManager());
        this.planetScene.createScene(this.activity.getVertexBufferObjectManager());
        this.coloniesScene.createScene(this.activity.getVertexBufferObjectManager());
        this.setupScene.createScene(this.activity.getVertexBufferObjectManager());
        this.techScene.createScene(this.activity.getVertexBufferObjectManager());
        this.empireScene.createScene(this.activity.getVertexBufferObjectManager());
        this.racesScene.createScene(this.activity.getVertexBufferObjectManager());
        this.loadSaveScene.createScene(this.activity.getVertexBufferObjectManager());
        this.productionScene.createScene(this.activity.getVertexBufferObjectManager());
        this.optionsScene.createScene(this.activity.getVertexBufferObjectManager());
        this.eventsScene.createScene(this.activity.getVertexBufferObjectManager());
        this.playerCreationScene.createScene(this.activity.getVertexBufferObjectManager());
        this.fleetsScene.createScene(this.activity.getVertexBufferObjectManager());
        this.buildingsScene.createScene(this.activity.getVertexBufferObjectManager());
        this.turnScene.createScene(this.activity.getVertexBufferObjectManager());
        this.selectAttackScene.createScene(this.activity.getVertexBufferObjectManager());
        this.attackScene.createScene(this.activity.getVertexBufferObjectManager());
        this.shipDesignScene.createScene(this.activity.getVertexBufferObjectManager());
        this.movePopulationScene.createScene(this.activity.getVertexBufferObjectManager());
        this.battleScene.createScene(this.activity.getVertexBufferObjectManager());
        this.raceScene.createScene(this.activity.getVertexBufferObjectManager());
        this.empireInfoScene.createScene(this.activity.getVertexBufferObjectManager());
        this.customizeAttributesScene.createScene(this.activity.getVertexBufferObjectManager());
        this.statsScene.createScene(this.activity.getVertexBufferObjectManager());
        this.buildListScene.createScene(this.activity.getVertexBufferObjectManager());
        this.raceDiscussScene.createScene(this.activity.getVertexBufferObjectManager());
    }

    public boolean back() {
        ExtendedScene extendedScene = this.currentScene;
        if (extendedScene == this.menuScene) {
            return true;
        }
        if (extendedScene == null) {
            exit();
            return false;
        }
        extendedScene.back();
        return false;
    }

    public void beginTurn() {
        if (this.selectAttacks.isEmpty()) {
            doTurn();
        } else {
            selectAttack();
        }
    }

    public Scene c() {
        Sprite sprite;
        Scene scene = new Scene();
        Sprite sprite2 = new Sprite(0.0f, 0.0f, this.splashBackgroundTextureRegion, this.activity.getVertexBufferObjectManager());
        sprite2.setAlpha(0.8f);
        sprite2.setWidth(this.width);
        sprite2.setHeight(160.0f);
        scene.attachChild(sprite2);
        Sprite sprite3 = new Sprite(0.0f, 560.0f, this.splashBackgroundTextureRegion, this.activity.getVertexBufferObjectManager());
        sprite3.setAlpha(0.8f);
        sprite3.setWidth(this.width);
        sprite3.setHeight(160.0f);
        scene.attachChild(sprite3);
        if (this.width == 1280) {
            sprite = new Sprite(0.0f, 160.0f, this.surfaceTextureRegion, this.activity.getVertexBufferObjectManager());
            sprite.setSize(1280.0f, 400.0f);
        } else {
            sprite = new Sprite(0.0f, 100.0f, this.surfaceTextureRegion, this.activity.getVertexBufferObjectManager());
            sprite.setSize(1480.0f, 460.0f);
        }
        scene.attachChild(sprite);
        Sprite sprite4 = new Sprite(0.0f, 560.0f, this.titleTextureRegion, this.activity.getVertexBufferObjectManager());
        sprite4.setX((this.width / 2.0f) - sprite4.getWidthScaled());
        sprite4.setScaleCenter(0.0f, 0.0f);
        sprite4.setScale(2.0f);
        scene.attachChild(sprite4);
        Sprite sprite5 = new Sprite(740.0f, 60.0f, this.explorerTextureRegion, this.activity.getVertexBufferObjectManager());
        sprite5.setScaleCenter(0.0f, 0.0f);
        sprite5.setScale(2.0f);
        scene.attachChild(sprite5);
        Sprite sprite6 = new Sprite(0.0f, 665.0f, this.loadingTextureRegion, this.activity.getVertexBufferObjectManager());
        sprite6.setScale(0.75f);
        sprite6.setAlpha(0.75f);
        if (this.width == 1280) {
            sprite6.setX((1280.0f - sprite6.getWidthScaled()) - 30.0f);
        } else {
            sprite6.setX((1480.0f - sprite6.getWidthScaled()) - 50.0f);
        }
        scene.attachChild(sprite6);
        Sprite sprite7 = new Sprite(-20.0f, 550.0f, this.logoTextureRegion, this.activity.getVertexBufferObjectManager());
        if (this.width == 1480) {
            sprite7.setX(0.0f);
        }
        sprite7.setScale(0.75f);
        scene.attachChild(sprite7);
        return scene;
    }

    public void d() {
        this.sounds = new Sounds(this.activity);
        this.fonts = new Fonts(this.activity);
        this.graphics = new Graphics(this.activity);
        this.music = new GameMusic(this);
    }

    public boolean didGameEnd() {
        return this.gameEnded;
    }

    public boolean doesSaveExists(int i) {
        return getSavedGameDetails().doesSaveExists(i);
    }

    public void e() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx_hd/");
        BitmapTextureAtlas bitmapTextureAtlas = new BitmapTextureAtlas(this.activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
        String num = Integer.toString(Functions.random.nextInt(Climate.values().length - 2) + 1);
        this.surfaceTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, this.activity, "Surfaces/" + num + ".png", 0, 0);
        Uciana uciana = this.activity;
        this.explorerTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, uciana, "Explorers/" + Functions.random.nextInt(7) + ".png", 0, 257);
        this.titleTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, this.activity, "Title.png", 751, 257);
        this.splashBackgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, this.activity, "Backgrounds/SplashBackground.png", 0, 800);
        this.logoTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, this.activity, "Logo.png", 0, 600);
        this.loadingTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, this.activity, "Loading/Loading-" + getLocale().getLetters() + ".png", 0, 948);
        bitmapTextureAtlas.load();
    }

    public void exit() {
        this.autoSaved = true;
        if (!this.galaxy.getStarSystems().isEmpty() && !this.gameEnded) {
            autoSaveGame();
            this.elapsedTime += System.currentTimeMillis() - this.startTime;
        }
        this.activity.finish();
        System.exit(0);
    }

    public void f() {
        if (!this.galaxy.getStarSystems().isEmpty() && !this.gameEnded && !this.autoSaved) {
            autoSaveGame();
            this.elapsedTime += System.currentTimeMillis() - this.startTime;
        }
        GameMusic gameMusic = this.music;
        if (gameMusic != null) {
            gameMusic.pause();
        }
    }

    public void g() {
        this.autoSaved = false;
        this.startTime = System.currentTimeMillis();
        GameMusic gameMusic = this.music;
        if (gameMusic != null) {
            gameMusic.resume();
        }
    }

    public Uciana getActivity() {
        return this.activity;
    }

    public Empire getCurrentEmpire() {
        return this.empires.get(this.currentPlayer);
    }

    public int getCurrentPlayer() {
        return this.currentPlayer;
    }

    public ExtendedScene getCurrentScene() {
        return this.currentScene;
    }

    public Database getDatabase() {
        return this.database;
    }

    public Engine getEngine() {
        return this.engine;
    }

    public int getHumanPlayerCount() {
        return getHumanPlayers().size();
    }

    public List<Integer> getHumanPlayers() {
        ArrayList arrayList = new ArrayList();
        for (Empire empire : this.empires.getEmpires()) {
            if (empire.isHuman() && (empire.isAlive() || empire.hasItsFallenMessage())) {
                arrayList.add(Integer.valueOf(empire.id));
            }
        }
        return arrayList;
    }

    public SupportedLocales getLocale() {
        return this.locale;
    }

    public int getPlayerStatus(int i) {
        return this.playerTurnStatus[i];
    }

    public RandomEventData getRandomEventData() {
        return this.randomEventData;
    }

    public SavedGameDetails getSavedGameDetails() {
        return this.database.getSavedGameDetails();
    }

    public long getTimePlayed() {
        return this.elapsedTime + (System.currentTimeMillis() - this.startTime);
    }

    public int getWidth() {
        return this.width;
    }

    public void load(int i) {
        this.database.load(i);
    }

    public void loadFromStorage() {
        this.database.loadFromStorage();
    }

    public void newGame(GalaxySize galaxySize, int i, CreateEmpireInfo[] createEmpireInfoArr, Difficulty difficulty2, Map<GameSettingsEnum, Integer> map) {
        int i2;
        this.gameSettings.setSettings(map);
        GameData.galaxy = this.galaxy;
        GameData.colonies = this.colonies;
        GameData.empires = this.empires;
        GameData.fleets = this.fleets;
        GameData.outposts = this.outposts;
        GameData.events = this.events;
        GameData.gameSettings = this.gameSettings;
        GameData.randomEvents = this.randomEvents;
        GameData.setTechCostVersion(1);
        this.empires.clear();
        this.colonies.clear();
        this.outposts.clear();
        this.fleets.clear();
        this.events.clear();
        this.homeSystems.clear();
        this.playerSettings.clear();
        this.galaxyScene.setGalaxyState(0);
        ArrayList arrayList = new ArrayList();
        int i3 = 0;
        int i4 = 0;
        for (CreateEmpireInfo createEmpireInfo : createEmpireInfoArr) {
            if (createEmpireInfo.getEmpireType() == EmpireType.HUMAN) {
                i3++;
                this.playerSettings.put(Integer.valueOf(i4), new PlayerSettings());
            } else {
                arrayList.add(Integer.valueOf(i4));
            }
            i4++;
        }
        if (i3 < i) {
            for (int i5 = 0; i5 < i - i3; i5++) {
                Integer num = (Integer) arrayList.get(Functions.random.nextInt(arrayList.size()));
                arrayList.remove(num);
                createEmpireInfoArr[num.intValue()].setEmpireType(EmpireType.AI);
            }
        }
        for (int i6 = 0; i6 < 7; i6++) {
            if (createEmpireInfoArr[i6].getEmpireType() != EmpireType.NONE) {
                this.graphics.setShipIconsTextures(i6, createEmpireInfoArr[i6].getShipStyleID(), this.activity);
            }
        }
        setDifficulty(difficulty2);
        this.galaxy.setupGalaxy(galaxySize);
        setupPlayers(createEmpireInfoArr);
        GameData.turn = 1;
        GameData.f1367a = getCurrentPlayer();
        if (GameData.gameSettings.isAlwaysAtWar()) {
            for (Empire empire : this.empires.getEmpires()) {
                if (!empire.isType(EmpireType.NONE)) {
                    for (Empire empire2 : this.empires.getEmpires()) {
                        if (!empire2.isType(EmpireType.NONE) && (i2 = empire2.id) != empire.id) {
                            empire.declareWar(i2, false);
                        }
                    }
                }
            }
        }
        this.randomEvents.clear();
        this.startTime = System.currentTimeMillis();
        this.elapsedTime = 0L;
        for (int i7 = 0; i7 < 7; i7++) {
            this.playerTurnStatus[i7] = 0;
        }
        this.techScene.resetLists();
        if (getHumanPlayerCount() == 1) {
            this.galaxyScene.setZoom(0);
            getCurrentScene().changeScene(this.galaxyScene);
            return;
        }
        getCurrentScene().changeScene(this.turnScene);
    }

    public void save(int i) {
        this.database.save(i);
    }

    public void saveToStorage() {
        this.database.saveToStorage();
    }

    public void setCurrentPlayer(int i) {
        this.currentPlayer = i;
        GameData.f1367a = i;
    }

    public void setCurrentScene(ExtendedScene extendedScene) {
        this.currentScene = extendedScene;
        extendedScene.switched();
        this.engine.setScene(extendedScene);
    }

    public void setCurrentSceneNoSwitch(ExtendedScene extendedScene) {
        this.currentScene = extendedScene;
        this.engine.setScene(extendedScene);
    }

    public void setGameEnded(boolean z) {
        this.gameEnded = z;
    }

    public void setPlayerTurnStatus(int i, int i2) {
        this.playerTurnStatus[i] = i2;
    }

    public void turnDone() {
        int[] iArr = this.playerTurnStatus;
        iArr[this.currentPlayer] = 1;
        int i = 0;
        for (int i2 : iArr) {
            if (i2 == 1) {
                i++;
            }
        }
        if (i >= getHumanPlayerCount()) {
            processTurn();
        } else {
            figureOutPlayerScreen();
        }
    }

    public void vibrate(long[] jArr) {
        vibrate(jArr, -1);
    }

    public void vibrate(long[] jArr, int i) {
        Vibrator vibrator;
        if (!options.isOn(OptionID.VIBRATE, 1) || (vibrator = (Vibrator) getActivity().getSystemService("vibrator")) == null) {
            return;
        }
        vibrator.vibrate(jArr, i);
    }

    public Game(Uciana uciana) {
        this.galaxy = new Galaxy(this);
        this.colonies = new Colonies(this);
        this.empires = new Empires(this);
        this.fleets = new Fleets();
        this.outposts = new Outposts(this);
        this.events = new Events();
        this.gameSettings = new GameSettings();
        this.randomEvents = new RandomEvents(this);
        this.currentPlayer = -1;
        this.playerTurnStatus = new int[7];
        this.elapsedTime = 0L;
        this.ascendedID = 0;
        this.autoSaved = true;
        this.playerSettings = new HashMap();
        this.homeSystems = new ArrayList();
        this.selectAttacks = new ArrayList();
        this.BUTTON_VIBRATE = new long[]{0, 10};
        this.SLIDER_VIBRATE = new long[]{0, 5, 100};
        this.activity = uciana;
        this.width = 1280;
        this.locale = SupportedLocales.ENGLISH;
        GameData.activity = uciana;
        options = new Options(uciana);
        modValues = new ModValues(uciana);
        initialize();
        this.fonts = new Fonts(uciana);
        this.graphics = new Graphics(uciana);
        this.galaxyScene.createScene(new VertexBufferObjectManager());
        Resources.set();
        Buildings.set();
        ShipComponents.set();
    }
}
