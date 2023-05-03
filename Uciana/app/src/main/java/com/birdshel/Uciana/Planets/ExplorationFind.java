package com.birdshel.Uciana.Planets;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Colonies.Outpost;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.Ships.Ship;
import com.birdshel.Uciana.Ships.ShipType;
import com.birdshel.Uciana.Technology.Tech;
import com.birdshel.Uciana.Technology.TechID;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import org.andengine.entity.Entity;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public enum ExplorationFind {
    NOTHING(new Builder().i(R.string.exploration_find_nothing).f(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Planets.ExplorationFind.1
    })),
    SMUGGLER_CACHE(new Builder().i(R.string.exploration_find_smuggler_cache).f(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Planets.ExplorationFind.2
        {
            put("1-getString", Integer.valueOf((int) R.string.exploration_find_effect_found));
            put("2-reward", "credits");
            put("3-icon", InfoIconEnum.CREDITS);
        }
    }).h(100.0f).g(200.0f)),
    TECH_DISCOVERY(new Builder().i(R.string.exploration_find_tech_discovery).f(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Planets.ExplorationFind.3
        {
            put("1-reward", "techName");
        }
    })),
    ANOMALY(new Builder().i(R.string.exploration_find_anomaly).f(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Planets.ExplorationFind.4
        {
            put("1-string", "+");
            put("2-reward", "researchPoints");
            put("3-icon", InfoIconEnum.SCIENCE);
            put("4-getString", Integer.valueOf((int) R.string.exploration_find_effect_to));
            put("5-reward", "techName");
        }
    }).h(100.0f).g(200.0f)),
    CRASHED_SHIP(new Builder().i(R.string.exploration_find_crashed_ship).f(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Planets.ExplorationFind.5
        {
            put("1-string", "+");
            put("2-reward", "credits");
            put("3-icon", InfoIconEnum.CREDITS);
            put("4-string", " +");
            put("5-reward", "researchPoints");
            put("6-icon", InfoIconEnum.SCIENCE);
        }
    }).h(50.0f).g(100.0f)),
    LOST_COLONY(new Builder().i(R.string.exploration_find_lost_colony).f(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Planets.ExplorationFind.6
        {
            put("1-getDescription", Integer.valueOf((int) R.string.exploration_find_effect_colony));
        }
    })),
    FLEET_MAINTENANCE_DEVICE(new Builder().i(R.string.exploration_find_fleet_device).f(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Planets.ExplorationFind.7
        {
            put("1-string", "+");
            put("2-reward", "commandPoints");
            put("3-icon", InfoIconEnum.COMMAND_POINTS);
        }
    }).j(1.0f)),
    STRANDED_POPULATION(new Builder().i(R.string.exploration_find_population).f(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Planets.ExplorationFind.8
        {
            put("1-string", "+20m");
            put("2-icon", InfoIconEnum.POPULATION);
            put("3-string", "to ");
            put("4-reward", "colonyName");
        }
    }).j(20.0f)),
    ABANDONED_SHIP(new Builder().i(R.string.exploration_find_ship).f(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Planets.ExplorationFind.9
        {
            put("1-icon", InfoIconEnum.DESTROYER_ICON);
            put("2-getString", Integer.valueOf((int) R.string.exploration_find_effect_ship));
        }
    })),
    FRIENDLY_RACE(new Builder().i(R.string.exploration_find_friendly_race).f(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Planets.ExplorationFind.10
        {
            put("1-getDescription", Integer.valueOf((int) R.string.exploration_find_effect_colony));
        }
    })),
    ABANDONED_OUTPOST(new Builder().i(R.string.exploration_find_outpost).f(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Planets.ExplorationFind.11
        {
            put("1-getDescription", Integer.valueOf((int) R.string.exploration_find_effect_outpost));
        }
    })),
    ANCIENT_EXPERIMENT(new Builder().i(R.string.exploration_find_ancient_experiment).f(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Planets.ExplorationFind.12
        {
            put("1-getDescription", Integer.valueOf((int) R.string.exploration_find_effect_ancient_experiment));
        }
    })),
    ANCIENT_TRAP(new Builder().i(R.string.exploration_find_ancient_trap).f(new HashMap<String, Object>() { // from class: com.birdshel.Uciana.Planets.ExplorationFind.13
        {
            put("1-getDescription", Integer.valueOf((int) R.string.exploration_find_effect_ancient_trap));
        }
    }));
    
    private final Map<String, Object> description;
    private int displayWidth;
    private final float max;
    private final float min;
    private final int name;
    private final Map<String, Object> reward = new HashMap();
    private final float value;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Planets.ExplorationFind$14  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass14 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1392a;

        static {
            int[] iArr = new int[ExplorationFind.values().length];
            f1392a = iArr;
            try {
                iArr[ExplorationFind.SMUGGLER_CACHE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1392a[ExplorationFind.TECH_DISCOVERY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1392a[ExplorationFind.ANOMALY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1392a[ExplorationFind.CRASHED_SHIP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1392a[ExplorationFind.FLEET_MAINTENANCE_DEVICE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1392a[ExplorationFind.STRANDED_POPULATION.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1392a[ExplorationFind.LOST_COLONY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1392a[ExplorationFind.FRIENDLY_RACE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1392a[ExplorationFind.ABANDONED_OUTPOST.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f1392a[ExplorationFind.ABANDONED_SHIP.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f1392a[ExplorationFind.ANCIENT_EXPERIMENT.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f1392a[ExplorationFind.ANCIENT_TRAP.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    /* compiled from: MyApplication */
    /* loaded from: classes.dex */
    private static class Builder {
        private Map<String, Object> description;
        private int name;
        private float min = 0.0f;
        private float max = 0.0f;
        private float value = 0.0f;

        Builder f(Map<String, Object> map) {
            this.description = map;
            return this;
        }

        Builder g(float f2) {
            this.max = f2;
            return this;
        }

        Builder h(float f2) {
            this.min = f2;
            return this;
        }

        Builder i(int i) {
            this.name = i;
            return this;
        }

        Builder j(float f2) {
            this.value = f2;
            return this;
        }
    }

    ExplorationFind(Builder builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.min = builder.min;
        this.max = builder.max;
        this.value = builder.value;
    }

    private void addAbandonedOutpost(int i, int i2, int i3) {
        GameData.outposts.add(new Outpost(GameData.galaxy.getSystemObject(i2, i3), i));
    }

    private void addAbandonedShip(int i, int i2) {
        Empire empire = GameData.empires.get(i);
        Ship shipDesign = empire.getShipDesignAI().getShipDesign(ShipType.DESTROYER);
        shipDesign.setID(empire.getNextShipID());
        if (GameData.fleets.isFleetInSystem(i, i2)) {
            GameData.fleets.getFleetInSystem(i, i2).addShip(shipDesign);
            return;
        }
        Fleet fleet = new Fleet(i, i2);
        GameData.fleets.add(fleet);
        fleet.addShip(shipDesign);
    }

    private void addCopyOfShip(Ship ship, int i) {
        Empire empire = GameData.empires.get(ship.getEmpireID());
        Ship createClone = ship.createClone(empire.getNextShipID());
        createClone.setID(empire.getNextShipID());
        if (GameData.fleets.isFleetInSystem(ship.getEmpireID(), i)) {
            GameData.fleets.getFleetInSystem(ship.getEmpireID(), i).addShip(createClone);
            return;
        }
        Fleet fleet = new Fleet(ship.getEmpireID(), i);
        GameData.fleets.add(fleet);
        fleet.addShip(createClone);
    }

    private void addFleetMaintenanceDevice(int i) {
        int i2 = (int) this.value;
        this.reward.put("commandPoints", Integer.toString(i2));
        GameData.empires.get(i).increaseBaseCommandPoints(i2);
    }

    private void addLostColony(int i, int i2, int i3) {
        GameData.colonies.colonize(i, (Planet) GameData.galaxy.getSystemObject(i2, i3));
    }

    private void addStrandedPopulationDiscovery(int i) {
        List<Colony> colonies = GameData.empires.get(i).getColonies();
        addStrandedPopulationDiscovery(colonies.get(Functions.random.nextInt(colonies.size())));
    }

    private void destroyShip(Fleet fleet, Ship ship) {
        fleet.removeShip(ship.getID());
        if (fleet.isEmpty()) {
            GameData.fleets.remove(fleet);
        }
    }

    private void getAnomalyDiscovery(int i) {
        Tech randomTech = getRandomTech(i);
        int nextInt = Functions.random.nextInt((int) (this.max - this.min)) + ((int) this.min);
        this.reward.put("researchPoints", Integer.toString(nextInt));
        this.reward.put("techName", randomTech.getShortName());
        if (randomTech.addResearch(nextInt)) {
            GameData.empires.get(i).checkForUpgrade(randomTech.getID());
            GameData.events.addTechEvent(i, randomTech.getID().ordinal(), 0);
        }
    }

    private void getCrashedShipDiscovery(int i) {
        int nextInt = Functions.random.nextInt((int) (this.max - this.min));
        float f2 = this.min;
        int i2 = nextInt + ((int) f2);
        int nextInt2 = Functions.random.nextInt((int) (this.max - f2)) + ((int) this.min);
        this.reward.put("credits", Integer.toString(i2));
        this.reward.put("researchPoints", Integer.toString(nextInt2));
        Empire empire = GameData.empires.get(i);
        empire.addRemoveCredits(i2);
        Tech currentTech = empire.getCurrentTech();
        if (currentTech.getID() == TechID.NONE) {
            currentTech = getRandomTech(i);
        }
        if (currentTech.addResearch(nextInt2)) {
            GameData.empires.get(i).checkForUpgrade(currentTech.getID());
            GameData.events.addTechEvent(i, currentTech.getID().ordinal(), 0);
        }
    }

    public static ExplorationFind getExplorationFind(Climate climate, List<ResourceID> list) {
        if (list.contains(ResourceID.ADVANCED_RUINS)) {
            return TECH_DISCOVERY;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(NOTHING, 40);
        hashMap.put(ABANDONED_SHIP, 5);
        hashMap.put(ANOMALY, 10);
        hashMap.put(CRASHED_SHIP, 10);
        hashMap.put(FLEET_MAINTENANCE_DEVICE, 5);
        hashMap.put(ABANDONED_OUTPOST, 5);
        hashMap.put(ANCIENT_EXPERIMENT, 5);
        hashMap.put(ANCIENT_TRAP, 5);
        if (climate.getFoodPerFarmer() > 0.0f) {
            hashMap.put(LOST_COLONY, 5);
            hashMap.put(STRANDED_POPULATION, 5);
            hashMap.put(SMUGGLER_CACHE, 5);
        } else {
            hashMap.put(SMUGGLER_CACHE, 15);
        }
        return (ExplorationFind) Functions.getItemByPercent(hashMap);
    }

    private Tech getRandomTech(int i) {
        Empire empire = GameData.empires.get(i);
        List<TechID> availableTechsToResearch = empire.getTech().getAvailableTechsToResearch();
        if (availableTechsToResearch.isEmpty()) {
            return empire.getTech().getTech(TechID.NONE);
        }
        return empire.getTech().getTech(availableTechsToResearch.get(Functions.random.nextInt(availableTechsToResearch.size())));
    }

    private void getSmugglerCache(int i) {
        int nextInt = Functions.random.nextInt((int) (this.max - this.min)) + ((int) this.min);
        this.reward.put("credits", Integer.toString(nextInt));
        GameData.empires.get(i).addRemoveCredits(nextInt);
    }

    private void getTechDiscovery(int i) {
        Tech randomTech = getRandomTech(i);
        this.reward.put("techName", randomTech.getShortName());
        randomTech.addResearch(randomTech.getResearchCost());
        GameData.empires.get(i).checkForUpgrade(randomTech.getID());
        if (GameData.empires.get(i).isHuman()) {
            GameData.events.addTechEvent(i, randomTech.getID().ordinal(), 3);
        }
    }

    public void addFindBonusToEmpire(int i, int i2, int i3, Fleet fleet, Ship ship) {
        this.reward.clear();
        switch (AnonymousClass14.f1392a[ordinal()]) {
            case 1:
                getSmugglerCache(i);
                break;
            case 2:
                getTechDiscovery(i);
                break;
            case 3:
                getAnomalyDiscovery(i);
                break;
            case 4:
                getCrashedShipDiscovery(i);
                break;
            case 5:
                addFleetMaintenanceDevice(i);
                break;
            case 6:
                addStrandedPopulationDiscovery(i);
                break;
            case 7:
            case 8:
                addLostColony(i, i2, i3);
                break;
            case 9:
                addAbandonedOutpost(i, i2, i3);
                break;
            case 10:
                addAbandonedShip(i, i2);
                break;
            case 11:
                addCopyOfShip(ship, i2);
                break;
            case 12:
                destroyShip(fleet, ship);
                break;
        }
        if (GameData.empires.get(i).isHuman()) {
            Game.gameAchievements.checkForExplorationAchievements(this);
        }
    }

    public Map<String, Object> getDescription() {
        return this.description;
    }

    public Entity getDisplay(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        Entity entity = new Entity();
        float f2 = 0.0f;
        for (String str : new TreeSet(this.description.keySet())) {
            if (str.endsWith(TypedValues.Custom.S_STRING)) {
                float f3 = f2;
                Text text = new Text(f3, 8.0f, game.fonts.smallInfoFont, (String) this.description.get(str), new TextOptions(HorizontalAlign.CENTER), vertexBufferObjectManager);
                f2 += text.getWidthScaled() + 5.0f;
                entity.attachChild(text);
            } else if (str.endsWith("reward")) {
                String str2 = (String) this.reward.get((String) this.description.get(str));
                Text text2 = new Text(f2, 8.0f, game.fonts.smallInfoFont, str2, new TextOptions(HorizontalAlign.CENTER), vertexBufferObjectManager);
                f2 += text2.getWidthScaled() + 5.0f;
                entity.attachChild(text2);
            } else if (str.endsWith("icon")) {
                TiledSprite tiledSprite = new TiledSprite(f2, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
                tiledSprite.setCurrentTileIndex(((InfoIconEnum) this.description.get(str)).ordinal());
                entity.attachChild(tiledSprite);
                f2 += tiledSprite.getWidthScaled() + 5.0f;
            } else if (str.endsWith("getString")) {
                float f4 = f2;
                Text text3 = new Text(f4, 8.0f, game.fonts.smallInfoFont, GameData.activity.getString(((Integer) this.description.get(str)).intValue()), new TextOptions(HorizontalAlign.CENTER), vertexBufferObjectManager);
                f2 += text3.getWidthScaled() + 5.0f;
                entity.attachChild(text3);
            } else if (str.endsWith("getDescription")) {
                float f5 = f2;
                Text text4 = new Text(f5, 8.0f, game.fonts.smallInfoFont, GameData.activity.getString(((Integer) this.description.get(str)).intValue()), new TextOptions(AutoWrap.WORDS, 230.0f, HorizontalAlign.CENTER), vertexBufferObjectManager);
                f2 += text4.getWidthScaled() + 5.0f;
                entity.attachChild(text4);
            }
        }
        this.displayWidth = ((int) f2) - 5;
        return entity;
    }

    public int getDisplayWidth() {
        return this.displayWidth;
    }

    public String getName() {
        return GameData.activity.getString(this.name);
    }

    public boolean isNewColony() {
        return this == LOST_COLONY || this == FRIENDLY_RACE;
    }

    public boolean isNewOutpost() {
        return this == ABANDONED_OUTPOST;
    }

    public void addStrandedPopulationDiscovery(Colony colony) {
        this.reward.put("colonyName", colony.getName());
        colony.addPopulation((int) this.value);
    }
}
