package com.birdshel.Uciana.Scenes;

import com.birdshel.Uciana.Colonies.Buildings.BuildingID;
import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Colonies.Outpost;
import com.birdshel.Uciana.Elements.EmpireButton;
import com.birdshel.Uciana.Elements.PlanetInfo;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameProperties;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Planets.AsteroidBelt;
import com.birdshel.Uciana.Planets.AsteroidBeltSprite;
import com.birdshel.Uciana.Planets.GasGiant;
import com.birdshel.Uciana.Planets.Moon;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.PlanetSprite;
import com.birdshel.Uciana.Planets.ResourceID;
import com.birdshel.Uciana.Planets.Resources;
import com.birdshel.Uciana.Planets.SystemObject;
import com.birdshel.Uciana.Planets.SystemObjectType;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.StarSystems.Nebulas;
import com.birdshel.Uciana.StarSystems.StarSystem;
import com.birdshel.Uciana.StarSystems.SunSprite;
import com.birdshel.Uciana.StarSystems.WormholeObject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.andengine.entity.Entity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class SelectAttackScene extends ExtendedScene {
    private static final int ORBIT_SIZE = 213;
    private static final int SUN_SPACE = 165;
    private TiledSprite attackingFleet;
    private Text attackingFleetCount;
    private TiledSprite closeButton;
    private TiledSprite colonyBackground;
    private TiledSprite comet;
    private Text displayText;
    private TiledSprite empireBanner;
    private int empireID;
    private int fleetsWithoutWorlds;
    private Entity nebulaBackground;
    private Nebulas nebulas;
    private int planetsToAttack;
    private Sprite press;
    private StarSystem starSystem;
    private SunSprite sunSprite;
    private Text toggleText;
    private final List<Entity> planetBackgrounds = new ArrayList();
    private final List<PlanetSprite> planetSprites = new ArrayList();
    private final List<Entity> asteroidBelts = new ArrayList();
    private final List<Entity> ionStorms = new ArrayList();
    private final List<Entity> planetResources = new ArrayList();
    private final List<Entity> exploredTexts = new ArrayList();
    private final List<Entity> planetInfoIcons = new ArrayList();
    private final List<Entity> populationBars = new ArrayList();
    private final List<Entity> emptyPopulationBars = new ArrayList();
    private final List<Entity> populationStrings = new ArrayList();
    private final List<Entity> empireBars = new ArrayList();
    private final List<Entity> fleetsInOrbit = new ArrayList();
    private final List<Entity> fleetBackgrounds = new ArrayList();
    private final List<Entity> fleets = new ArrayList();
    private final List<Entity> shipCounts = new ArrayList();
    private final List<Entity> outposts = new ArrayList();
    private final List<Entity> starBases = new ArrayList();
    private final List<Entity> turret1s = new ArrayList();
    private final List<Entity> turret2s = new ArrayList();
    private final List<Entity> shipYards = new ArrayList();
    private final List<Entity> empireButtons = new ArrayList();
    private final List<Entity> capitalIcons = new ArrayList();
    private final TiledSprite[] wormholeBottomLayers = new TiledSprite[4];
    private final TiledSprite[] wormholeTopLayers = new TiledSprite[4];

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Scenes.SelectAttackScene$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1476a;

        static {
            int[] iArr = new int[SystemObjectType.values().length];
            f1476a = iArr;
            try {
                iArr[SystemObjectType.PLANET.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1476a[SystemObjectType.GAS_GIANT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1476a[SystemObjectType.ASTEROID_BELT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public SelectAttackScene(Game game) {
        this.B = game;
    }

    private void attackingFleetPressed() {
        this.B.shipSelectOverlay.setOverlay(this.B.fleets.getFleetInSystem(this.empireID, this.starSystem.getID()).id);
        setChildScene(this.B.shipSelectOverlay, false, false, true);
        this.B.sounds.playFleetPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void checkActionDown(Point point) {
        checkPresses(point);
    }

    private void checkActionMove(Point point) {
        this.press.setVisible(false);
        checkPresses(point);
    }

    private void checkActionUp(Point point) {
        int i = 0;
        this.press.setVisible(false);
        if (isClicked(this.closeButton, point)) {
            closeButtonPressed();
        }
        for (Entity entity : this.planetBackgrounds) {
            if (isClicked(entity, point)) {
                colonyPressed(i);
                return;
            }
            i++;
        }
        Iterator<Entity> it = this.fleetBackgrounds.iterator();
        while (it.hasNext()) {
            TiledSprite tiledSprite = (TiledSprite) it.next();
            if (isClicked(tiledSprite, point)) {
                fleetPressed(tiledSprite);
                return;
            }
        }
        for (Entity entity2 : this.empireButtons) {
            if (isClicked(entity2, point)) {
                empireButtonPressed(entity2);
            }
        }
        if (isClicked(this.attackingFleet, point)) {
            attackingFleetPressed();
        }
    }

    private void checkPresses(Point point) {
        Iterator<Entity> it = this.planetBackgrounds.iterator();
        while (it.hasNext()) {
            TiledSprite tiledSprite = (TiledSprite) it.next();
            if (isClicked(tiledSprite, point)) {
                this.press.setVisible(true);
                this.press.setPosition(tiledSprite.getX(), tiledSprite.getY());
                this.press.setSize(tiledSprite.getWidth(), tiledSprite.getHeight());
            }
        }
        Iterator<Entity> it2 = this.fleetBackgrounds.iterator();
        while (it2.hasNext()) {
            TiledSprite tiledSprite2 = (TiledSprite) it2.next();
            if (isClicked(tiledSprite2, point)) {
                this.press.setVisible(true);
                this.press.setPosition(tiledSprite2.getX(), tiledSprite2.getY());
                this.press.setSize(tiledSprite2.getWidth(), tiledSprite2.getHeight());
            }
        }
    }

    private void closeButtonPressed() {
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        this.B.beginTurn();
    }

    private void colonyPressed(int i) {
        int empireID;
        if (this.B.colonies.isColony(this.starSystem.getID(), i)) {
            empireID = this.B.colonies.getColony(this.starSystem.getID(), i).getEmpireID();
        } else {
            empireID = this.B.outposts.getOutpost(this.starSystem.getID(), i).getEmpireID();
        }
        changeScene(this.B.attackScene, new AttackSceneData(2, this.empireID, empireID, this.starSystem.getID(), i, true));
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void empireButtonPressed(Entity entity) {
        int empireID = ((EmpireButton) entity).getEmpireID();
        Empire empire = this.B.empires.get(this.empireID);
        empire.setHideAutoSelectAttack(empireID, !empire.isAutoSelectAttackHidden(empireID));
        setEmpireButtons();
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void fleetPressed(TiledSprite tiledSprite) {
        changeScene(this.B.attackScene, new AttackSceneData(1, this.empireID, tiledSprite.getCurrentTileIndex(), this.starSystem.getID(), true));
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$releasePoolElements$0(ExtendedScene extendedScene, Object obj) {
        this.nebulaBackground.detachChild(this.nebulas);
        for (PlanetSprite planetSprite : this.planetSprites) {
            detachChild(planetSprite);
            this.B.planetSpritePool.push(planetSprite);
        }
        this.planetSprites.clear();
        extendedScene.getPoolElements();
        L(extendedScene, obj);
        this.B.setCurrentScene(extendedScene);
    }

    private void setAsteroidBelt(AsteroidBelt asteroidBelt, int i) {
        AsteroidBeltSprite asteroidBeltSprite = (AsteroidBeltSprite) this.asteroidBelts.get(i);
        asteroidBeltSprite.set(asteroidBelt);
        asteroidBeltSprite.setVisible(true);
        PlanetInfo planetInfo = (PlanetInfo) this.planetInfoIcons.get(i);
        planetInfo.set(asteroidBelt);
        int i2 = (i * ORBIT_SIZE) + 135;
        planetInfo.setPosition(i2 + 25, 450.0f);
        planetInfo.setVisible(true);
        if (asteroidBelt.hasOutpost()) {
            setOutpost(asteroidBelt);
            Text text = (Text) this.populationStrings.get(i);
            text.setVisible(true);
            text.setText(this.B.getActivity().getString(R.string.select_attack_mining_outpost));
            text.setPosition((i2 + 106) - (text.getWidth() / 2.0f), 493.0f);
        }
    }

    private void setAttackingFleet() {
        Fleet fleetInSystem = this.B.fleets.getFleetInSystem(this.empireID, this.starSystem.getID());
        this.attackingFleet.setTiledTextureRegion((ITiledTextureRegion) this.B.graphics.shipsTextures[fleetInSystem.empireID]);
        this.attackingFleet.setCurrentTileIndex(fleetInSystem.getIcon());
        this.attackingFleetCount.setText(Integer.toString(fleetInSystem.getSize()));
    }

    private void setColony(Planet planet, Text text) {
        int orbit = planet.getOrbit();
        Colony colony = this.B.colonies.getColony(planet.getSystemID(), orbit);
        if (colony.getEmpireID() != this.empireID) {
            this.planetsToAttack++;
            TiledSprite tiledSprite = (TiledSprite) this.planetBackgrounds.get(orbit);
            tiledSprite.setVisible(true);
            tiledSprite.setCurrentTileIndex(colony.getEmpireID());
            if (this.B.fleets.isFleetInSystem(colony.getEmpireID(), colony.getSystemID())) {
                Fleet fleetInSystem = this.B.fleets.getFleetInSystem(colony.getEmpireID(), colony.getSystemID());
                TiledSprite tiledSprite2 = (TiledSprite) this.fleetsInOrbit.get(orbit);
                tiledSprite2.setTiledTextureRegion((ITiledTextureRegion) this.B.graphics.shipsTextures[fleetInSystem.empireID]);
                tiledSprite2.setCurrentTileIndex(fleetInSystem.getIcon());
                tiledSprite2.setVisible(true);
            }
        }
        Sprite sprite = (Sprite) this.populationBars.get(orbit);
        sprite.setVisible(true);
        int i = orbit * ORBIT_SIZE;
        sprite.setPosition(i + SUN_SPACE, 485.0f);
        sprite.setSize(153.0f, 34.0f);
        float population = colony.getPopulation() / planet.getMaxPopulation();
        if (population != 1.0f) {
            float f2 = population * 151.0f;
            Sprite sprite2 = (Sprite) this.emptyPopulationBars.get(orbit);
            sprite2.setPosition(i + 166 + f2, 491.0f);
            sprite2.setSize(151.0f - f2, 21.0f);
            sprite2.setVisible(true);
        }
        text.setText((colony.getPopulation() + "m") + " / " + (planet.getMaxPopulation() + "m"));
        if (colony.hasBuilding(BuildingID.SPACE_DOCK)) {
            this.outposts.get(planet.getOrbit()).setVisible(true);
        }
        if (colony.hasBuilding(BuildingID.STAR_BASE)) {
            this.starBases.get(orbit).setVisible(true);
        }
        if (colony.hasBuilding(BuildingID.TORPEDO_TURRET)) {
            this.turret1s.get(orbit).setVisible(true);
            this.turret2s.get(orbit).setVisible(true);
        }
        if (colony.hasBuilding(BuildingID.SHIP_YARDS)) {
            this.shipYards.get(orbit).setVisible(true);
        }
    }

    private void setEmpireBar(SystemObject systemObject, int i) {
        TiledSprite tiledSprite = (TiledSprite) this.empireBars.get(i);
        if (systemObject.hasColony()) {
            float f2 = (i * ORBIT_SIZE) + SUN_SPACE;
            tiledSprite.setX(f2);
            tiledSprite.setCurrentTileIndex(systemObject.getColony().getEmpireID());
            tiledSprite.setWidth(153.0f);
            tiledSprite.setVisible(true);
            if (systemObject.getColony().hasBuilding(BuildingID.CAPITAL)) {
                this.capitalIcons.get(i).setVisible(true);
                this.capitalIcons.get(i).setX(f2 + 61.5f);
            }
        } else if (systemObject.hasOutpost()) {
            tiledSprite.setX((i * ORBIT_SIZE) + SUN_SPACE + 38);
            tiledSprite.setCurrentTileIndex(systemObject.getOutpost().getEmpireID());
            tiledSprite.setWidth(76.0f);
            tiledSprite.setVisible(true);
        }
    }

    private void setEmpireButtons() {
        int occupier;
        C(this.empireButtons);
        HashSet<Integer> hashSet = new HashSet();
        for (Fleet fleet : this.B.fleets.getFleetsInSystem(this.starSystem.getID())) {
            int i = fleet.empireID;
            if (i != this.empireID) {
                hashSet.add(Integer.valueOf(i));
            }
        }
        for (SystemObject systemObject : this.starSystem.getSystemObjects()) {
            if (systemObject.isOccupied() && (occupier = systemObject.getOccupier()) != this.empireID) {
                hashSet.add(Integer.valueOf(occupier));
            }
        }
        int i2 = 0;
        for (Integer num : hashSet) {
            if (!GameProperties.isNonNormalEmpire(num.intValue())) {
                EmpireButton empireButton = (EmpireButton) this.empireButtons.get(i2);
                empireButton.setVisible(true);
                empireButton.set(num.intValue());
                empireButton.setX((getWidth() - 600.0f) + (i2 * 120));
                empireButton.setAlpha(1.0f);
                if (this.B.empires.get(this.empireID).isAutoSelectAttackHidden(num.intValue())) {
                    empireButton.setAlpha(0.45f);
                }
                i2++;
            }
        }
        this.toggleText.setVisible(i2 != 0);
    }

    private void setGasGiant(GasGiant gasGiant, int i) {
        this.planetSprites.get(i).setGasGiant(gasGiant, gasGiant.getScale(this.B.systemScene), Moon.getScale(this.B.systemScene));
        if (gasGiant.hasOutpost()) {
            setOutpost(gasGiant);
            Text text = (Text) this.populationStrings.get(i);
            text.setVisible(true);
            text.setText(this.B.getActivity().getString(R.string.select_attack_science_station));
            text.setPosition((((i * ORBIT_SIZE) + 135) + 106) - (text.getWidth() / 2.0f), 493.0f);
        }
    }

    private void setHeader() {
        String string;
        this.colonyBackground.setCurrentTileIndex(this.empireID);
        this.empireBanner.setCurrentTileIndex(GameIconEnum.getEmpireBanner(this.empireID));
        if (this.planetsToAttack != 0) {
            if (this.fleetsWithoutWorlds != 0) {
                string = this.B.getActivity().getString(R.string.select_attack_header_fleet_or_planet, new Object[]{this.starSystem.getName()});
            } else {
                string = this.B.getActivity().getString(R.string.select_attack_header_planet, new Object[]{this.starSystem.getName()});
            }
        } else {
            string = this.B.getActivity().getString(R.string.select_attack_header_fleet, new Object[]{this.starSystem.getName()});
        }
        this.displayText.setText(string);
        Text text = this.displayText;
        text.setY(43.0f - (text.getHeightScaled() / 2.0f));
    }

    private void setOutpost(SystemObject systemObject) {
        Outpost outpost = this.B.outposts.getOutpost(this.starSystem.getID(), systemObject.getOrbit());
        if (outpost.getEmpireID() != this.empireID) {
            this.planetsToAttack++;
            TiledSprite tiledSprite = (TiledSprite) this.planetBackgrounds.get(systemObject.getOrbit());
            tiledSprite.setVisible(true);
            tiledSprite.setCurrentTileIndex(outpost.getEmpireID());
            if (this.B.fleets.isFleetInSystem(outpost.getEmpireID(), outpost.getSystemID())) {
                Fleet fleetInSystem = this.B.fleets.getFleetInSystem(outpost.getEmpireID(), outpost.getSystemID());
                TiledSprite tiledSprite2 = (TiledSprite) this.fleetsInOrbit.get(systemObject.getOrbit());
                tiledSprite2.setTiledTextureRegion((ITiledTextureRegion) this.B.graphics.shipsTextures[fleetInSystem.empireID]);
                tiledSprite2.setCurrentTileIndex(fleetInSystem.getIcon());
                tiledSprite2.setVisible(true);
            }
        }
    }

    private void setPlanet(Planet planet, int i) {
        this.planetSprites.get(i).setPlanet(planet, planet.getScale(this.B.systemScene), Moon.getScale(this.B.systemScene));
        if (planet.hasBeenExploredByEmpire(this.B.getCurrentPlayer())) {
            List<ResourceID> visibleResources = planet.getVisibleResources(this.B.getCurrentPlayer());
            int size = visibleResources.size() * 40;
            int i2 = 0;
            for (ResourceID resourceID : visibleResources) {
                TiledSprite tiledSprite = (TiledSprite) this.planetResources.get((i * 4) + i2);
                if (Resources.get(resourceID).isVisible(this.B.getCurrentPlayer())) {
                    tiledSprite.setVisible(true);
                }
                tiledSprite.setCurrentTileIndex(Resources.get(resourceID).getImageIndex());
                tiledSprite.setX(((((i * ORBIT_SIZE) + 135) + 106) - (size / 2.0f)) + (i2 * 40));
                i2++;
            }
        } else {
            Text text = (Text) this.exploredTexts.get(i);
            text.setVisible(true);
            if (planet.isUnexplored()) {
                text.setColor(Color.WHITE);
                text.setText(this.B.getActivity().getString(R.string.select_attack_unexplored));
            } else {
                text.setColor(new Color(0.6f, 0.6f, 0.6f));
                text.setText(this.B.getActivity().getString(R.string.select_attack_unknown));
            }
            text.setX((((i * ORBIT_SIZE) + 135) + 106) - (text.getWidthScaled() / 2.0f));
        }
        PlanetInfo planetInfo = (PlanetInfo) this.planetInfoIcons.get(i);
        planetInfo.set(planet);
        int i3 = (i * ORBIT_SIZE) + 135;
        planetInfo.setPosition(i3 + 36, 450.0f);
        planetInfo.setVisible(true);
        Text text2 = (Text) this.populationStrings.get(i);
        if (planet.hasColony()) {
            setColony(planet, text2);
        } else if (planet.hasOutpost()) {
            setOutpost(planet);
            text2.setText(this.B.getActivity().getString(R.string.select_attack_max_pop, new Object[]{Integer.valueOf(planet.getMaxPopulation())}));
        } else {
            text2.setText(this.B.getActivity().getString(R.string.select_attack_max_pop, new Object[]{Integer.valueOf(planet.getMaxPopulation())}));
        }
        text2.setVisible(true);
        text2.setPosition((i3 + 106) - (text2.getWidth() / 2.0f), 493.0f);
    }

    private void setRemainingFleets() {
        ArrayList<Fleet> arrayList = new ArrayList();
        for (Fleet fleet : this.B.fleets.getFleetsInSystem(this.starSystem.getID())) {
            int i = fleet.empireID;
            if (i != this.empireID && (GameProperties.isNonNormalEmpire(i) || !this.B.empires.get(fleet.empireID).getSystemIDs().contains(Integer.valueOf(this.starSystem.getID())))) {
                arrayList.add(fleet);
            }
        }
        int i2 = 0;
        for (Fleet fleet2 : arrayList) {
            TiledSprite tiledSprite = (TiledSprite) this.fleetBackgrounds.get(i2);
            tiledSprite.setVisible(true);
            tiledSprite.setCurrentTileIndex(fleet2.empireID);
            tiledSprite.setX((i2 * 125) + 45);
            TiledSprite tiledSprite2 = (TiledSprite) this.fleets.get(i2);
            tiledSprite2.setVisible(true);
            tiledSprite2.setTiledTextureRegion((ITiledTextureRegion) this.B.graphics.shipsTextures[fleet2.empireID]);
            tiledSprite2.setCurrentTileIndex(fleet2.getIcon());
            tiledSprite2.setX(tiledSprite.getX() + 12.0f);
            i2++;
        }
        this.fleetsWithoutWorlds = arrayList.size();
    }

    private void setSpritesInvisible() {
        C(this.asteroidBelts);
        C(this.ionStorms);
        C(this.planetInfoIcons);
        C(this.populationStrings);
        C(this.empireBars);
        C(this.capitalIcons);
        C(this.populationBars);
        C(this.emptyPopulationBars);
        C(this.outposts);
        C(this.starBases);
        C(this.turret1s);
        C(this.turret2s);
        C(this.shipYards);
        C(this.planetResources);
        C(this.exploredTexts);
        C(this.planetBackgrounds);
        C(this.fleetsInOrbit);
        C(this.fleetBackgrounds);
        C(this.fleets);
        C(this.shipCounts);
        C(this.empireButtons);
        this.press.setVisible(false);
        for (PlanetSprite planetSprite : this.planetSprites) {
            planetSprite.setSpritesInvisible();
        }
        for (TiledSprite tiledSprite : this.wormholeBottomLayers) {
            tiledSprite.setVisible(false);
        }
        for (TiledSprite tiledSprite2 : this.wormholeTopLayers) {
            tiledSprite2.setVisible(false);
        }
    }

    private void setSystemBackground() {
        this.sunSprite.set(this.starSystem.getStarType());
        this.nebulas.set(this.starSystem.getID());
        this.comet.setCurrentTileIndex(Functions.random.nextInt(2));
        this.comet.setX(Functions.random.nextInt(((int) getWidth()) - 280) + 300);
        this.comet.setY(Functions.random.nextInt(620));
        this.comet.setVisible(Functions.percent(50));
        int i = 0;
        for (WormholeObject wormholeObject : this.starSystem.getWormholeObjects()) {
            this.wormholeBottomLayers[i].setVisible(true);
            this.wormholeBottomLayers[i].setPosition(wormholeObject.getPosition().getX(), wormholeObject.getPosition().getY());
            this.wormholeBottomLayers[i].setSize(wormholeObject.getSize(), wormholeObject.getSize());
            TiledSprite[] tiledSpriteArr = this.wormholeBottomLayers;
            tiledSpriteArr[i].setRotationCenter(tiledSpriteArr[i].getWidthScaled() / 2.0f, this.wormholeBottomLayers[i].getHeightScaled() / 2.0f);
            this.wormholeBottomLayers[i].clearEntityModifiers();
            this.wormholeBottomLayers[i].registerEntityModifier(new LoopEntityModifier(new RotationModifier(wormholeObject.getBottomSpinSpeed(), 360.0f, 0.0f)));
            this.wormholeTopLayers[i].setVisible(true);
            this.wormholeTopLayers[i].setPosition(wormholeObject.getPosition().getX(), wormholeObject.getPosition().getY());
            this.wormholeTopLayers[i].setSize(wormholeObject.getSize(), wormholeObject.getSize());
            TiledSprite[] tiledSpriteArr2 = this.wormholeTopLayers;
            tiledSpriteArr2[i].setRotationCenter(tiledSpriteArr2[i].getWidthScaled() / 2.0f, this.wormholeTopLayers[i].getHeightScaled() / 2.0f);
            this.wormholeTopLayers[i].clearEntityModifiers();
            this.wormholeTopLayers[i].registerEntityModifier(new LoopEntityModifier(new RotationModifier(wormholeObject.getTopSpinSpeed(), 0.0f, 360.0f)));
            i++;
            if (i == 4) {
                i--;
            }
        }
    }

    private void setSystemOrbits() {
        for (SystemObject systemObject : this.starSystem.getSystemObjects()) {
            int orbit = systemObject.getOrbit();
            int i = AnonymousClass1.f1476a[systemObject.getSystemObjectType().ordinal()];
            if (i == 1) {
                setPlanet((Planet) systemObject, orbit);
            } else if (i == 2) {
                setGasGiant((GasGiant) systemObject, orbit);
            } else if (i == 3) {
                setAsteroidBelt((AsteroidBelt) systemObject, orbit);
            }
            setEmpireBar(systemObject, orbit);
            if (systemObject.hasOutpost()) {
                this.outposts.get(orbit).setVisible(true);
            }
        }
    }

    private void startCometEffect() {
        this.comet.setBlendFunction(IShape.BLENDFUNCTION_SOURCE_DEFAULT, 771);
        this.comet.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(new AlphaModifier(0.5f, 0.85f, 1.0f), new AlphaModifier(0.5f, 1.0f, 0.85f))));
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void B(Point point) {
    }

    protected void L(ExtendedScene extendedScene, Object obj) {
        if (extendedScene instanceof GalaxyScene) {
            this.B.galaxyScene.setStarsAndNebulas();
        } else if (extendedScene instanceof SelectAttackScene) {
            Point point = (Point) obj;
            this.B.selectAttackScene.set((int) point.getX(), (int) point.getY());
        } else if (extendedScene instanceof TurnScene) {
            this.B.turnScene.set();
        } else if (extendedScene instanceof AttackScene) {
            AttackSceneData attackSceneData = (AttackSceneData) obj;
            int empireID = attackSceneData.getEmpireID();
            int targetID = attackSceneData.getTargetID();
            int systemID = attackSceneData.getSystemID();
            int orbit = attackSceneData.getOrbit();
            boolean isAttacker = attackSceneData.isAttacker();
            int type = attackSceneData.getType();
            if (type == 0) {
                this.B.attackScene.N(empireID, targetID, systemID, orbit);
            } else if (type == 1) {
                this.B.attackScene.set(isAttacker, empireID, targetID, systemID);
            } else if (type != 2) {
            } else {
                this.B.attackScene.set(isAttacker, empireID, targetID, systemID, orbit);
            }
        }
    }

    @Override // org.andengine.entity.scene.Scene
    public void back() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void checkInput(int i, Point point) {
        super.checkInput(i, point);
        if (i == 0) {
            checkActionDown(point);
        } else if (i == 1) {
            checkActionUp(point);
        } else if (i != 2) {
        } else {
            checkActionMove(point);
        }
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void createScene(VertexBufferObjectManager vertexBufferObjectManager) {
        super.createScene(vertexBufferObjectManager);
        this.nebulas = this.B.nebulas;
        Entity entity = new Entity();
        this.nebulaBackground = entity;
        attachChild(entity);
        SunSprite sunSprite = new SunSprite(this.B, vertexBufferObjectManager, true);
        this.sunSprite = sunSprite;
        attachChild(sunSprite);
        int i = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            TiledSprite J = J(585.0f, 240.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, true);
            J.setCurrentTileIndex(GameIconEnum.BOTTOM_WORMHOLE_LAYER.ordinal());
            this.wormholeBottomLayers[i2] = J;
            TiledSprite J2 = J(585.0f, 240.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, true);
            J2.setAlpha(0.9f);
            J2.setCurrentTileIndex(GameIconEnum.TOP_WORMHOLE_LAYER.ordinal());
            this.wormholeTopLayers[i2] = J2;
        }
        this.press = E(0.0f, 0.0f, this.B.graphics.selectColonyTexture, vertexBufferObjectManager, false);
        int i3 = 0;
        while (i3 < 5) {
            int i4 = i3 * ORBIT_SIZE;
            int i5 = i4 + 135;
            TiledSprite J3 = J(i5, 220.0f, this.B.graphics.empireColors, vertexBufferObjectManager, false);
            J3.setSize(210.0f, 330.0f);
            J3.setZIndex(i);
            J3.setAlpha(0.6f);
            this.planetBackgrounds.add(J3);
            AsteroidBeltSprite asteroidBeltSprite = new AsteroidBeltSprite(this.B, vertexBufferObjectManager);
            asteroidBeltSprite.setX(i4);
            this.asteroidBelts.add(asteroidBeltSprite);
            attachChild(asteroidBeltSprite);
            this.ionStorms.add(J(0.0f, 0.0f, this.B.graphics.ionCloudsTexture, vertexBufferObjectManager, false));
            this.ionStorms.add(J(0.0f, 0.0f, this.B.graphics.ionCloudsTexture, vertexBufferObjectManager, false));
            TiledSprite J4 = J(0.0f, 0.0f, this.B.graphics.empireColors, vertexBufferObjectManager, false);
            J4.setHeight(10.0f);
            J4.setY(530.0f);
            this.empireBars.add(J4);
            this.capitalIcons.add(H(0.0f, 518.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.CAPITAL.ordinal(), false));
            TiledSprite H = H(0.0f, 0.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, GameIconEnum.OUTPOST.ordinal(), false);
            float f2 = i5 + 30;
            H.setPosition(f2, 365.0f);
            H.setZIndex(2);
            H.setSize(35.0f, 35.0f);
            this.outposts.add(H);
            float f3 = i5 + 148;
            TiledSprite H2 = H(f3, 365.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, GameIconEnum.STAR_BASE.ordinal(), false);
            H2.setSize(35.0f, 35.0f);
            H2.setZIndex(2);
            this.starBases.add(H2);
            ITiledTextureRegion iTiledTextureRegion = this.B.graphics.gameIconsTexture;
            GameIconEnum gameIconEnum = GameIconEnum.TORPEDO_TURRET;
            TiledSprite H3 = H(i5 + 158, 340.0f, iTiledTextureRegion, vertexBufferObjectManager, gameIconEnum.ordinal(), false);
            H3.setSize(20.0f, 20.0f);
            H3.setZIndex(2);
            this.turret1s.add(H3);
            TiledSprite H4 = H(i5 + 128, 380.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, gameIconEnum.ordinal(), false);
            H4.setSize(20.0f, 20.0f);
            H4.setZIndex(2);
            this.turret2s.add(H4);
            TiledSprite H5 = H(f3, 255.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, GameIconEnum.SHIP_YARDS.ordinal(), false);
            H5.setSize(35.0f, 35.0f);
            H5.setZIndex(2);
            this.shipYards.add(H5);
            for (int i6 = 0; i6 < 4; i6++) {
                TiledSprite J5 = J(0.0f, 410.0f, this.B.graphics.resourceIconsTexture, vertexBufferObjectManager, false);
                J5.setSize(40.0f, 40.0f);
                this.planetResources.add(J5);
            }
            this.exploredTexts.add(G(0.0f, 410.0f, this.B.fonts.smallInfoFont, this.D, this.E, vertexBufferObjectManager, false));
            PlanetInfo planetInfo = new PlanetInfo(this.B, vertexBufferObjectManager);
            planetInfo.setScaleCenter(0.0f, 0.0f);
            planetInfo.setScale(0.7f);
            attachChild(planetInfo);
            this.planetInfoIcons.add(planetInfo);
            this.populationBars.add(E(0.0f, 0.0f, this.B.graphics.popTexture, vertexBufferObjectManager, false));
            this.emptyPopulationBars.add(E(0.0f, 0.0f, this.B.graphics.popEmptyTexture, vertexBufferObjectManager, false));
            this.populationStrings.add(G(0.0f, 0.0f, this.B.fonts.smallInfoFont, this.D, this.E, vertexBufferObjectManager, false));
            TiledSprite H6 = H(f2, 240.0f, this.B.graphics.shipsTextures[0], vertexBufferObjectManager, 0, false);
            H6.setSize(60.0f, 60.0f);
            H6.setZIndex(5);
            this.fleetsInOrbit.add(H6);
            i3++;
            i = 0;
        }
        this.comet = J(0.0f, 0.0f, this.B.graphics.cometsTexture, vertexBufferObjectManager, false);
        startCometEffect();
        TiledSprite J6 = J(0.0f, 0.0f, this.B.graphics.empireColors, vertexBufferObjectManager, true);
        this.colonyBackground = J6;
        J6.setAlpha(0.6f);
        this.colonyBackground.setSize(getWidth(), 86.0f);
        this.empireBanner = J(0.0f, -7.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, true);
        this.displayText = F(120.0f, 20.0f, this.B.fonts.smallFont, this.D, this.E, vertexBufferObjectManager);
        TiledSprite J7 = J(125.0f, 106.0f, this.B.graphics.shipsTextures[0], vertexBufferObjectManager, true);
        this.attackingFleet = J7;
        J7.setSize(75.0f, 75.0f);
        this.attackingFleetCount = G(185.0f, 166.0f, this.B.fonts.smallFont, this.D, this.E, vertexBufferObjectManager, true);
        for (int i7 = 0; i7 < 7; i7++) {
            TiledSprite J8 = J(0.0f, 600.0f, this.B.graphics.empireColors, vertexBufferObjectManager, false);
            J8.setSize(100.0f, 100.0f);
            J8.setAlpha(0.6f);
            this.fleetBackgrounds.add(J8);
            TiledSprite J9 = J(0.0f, 612.0f, this.B.graphics.shipsTextures[0], vertexBufferObjectManager, false);
            J9.setSize(75.0f, 75.0f);
            this.fleets.add(J9);
        }
        TiledSprite H7 = H(getWidth() - 120.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.CLOSE.ordinal(), true);
        this.closeButton = H7;
        s(H7);
        float width = getWidth() - 590.0f;
        Game game = this.B;
        Text F = F(width, 600.0f, game.fonts.smallFont, game.getActivity().getString(R.string.select_attack_scene_toggle), this.E, vertexBufferObjectManager);
        this.toggleText = F;
        if ((F.getWidthScaled() + getWidth()) - 590.0f > getWidth()) {
            this.toggleText.setX(getWidth() - this.toggleText.getWidthScaled());
        }
        for (int i8 = 0; i8 < 7; i8++) {
            TiledSprite empireButton = new EmpireButton(this.B, vertexBufferObjectManager);
            attachChild(empireButton);
            empireButton.setPosition(0.0f, 617.0f);
            empireButton.setVisible(false);
            s(empireButton);
            this.empireButtons.add(empireButton);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void getPoolElements() {
        if (this.nebulas.hasParent()) {
            this.nebulas.detachSelf();
        }
        this.nebulaBackground.attachChild(this.nebulas);
        for (int i = 0; i < 5; i++) {
            PlanetSprite planetSprite = this.B.planetSpritePool.get();
            planetSprite.setMoonRange(ORBIT_SIZE, 180);
            planetSprite.setPosition((i * ORBIT_SIZE) + 135 + 106, 330.0f);
            planetSprite.setZIndex(1);
            attachChild(planetSprite);
            this.planetSprites.add(planetSprite);
        }
        sortChildren();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void refresh() {
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void releasePoolElements(final ExtendedScene extendedScene, final Object obj) {
        this.B.getEngine().runOnUpdateThread(new Runnable() { // from class: com.birdshel.Uciana.Scenes.h0
            @Override // java.lang.Runnable
            public final void run() {
                SelectAttackScene.this.lambda$releasePoolElements$0(extendedScene, obj);
            }
        });
    }

    public void set(int i, int i2) {
        if (!this.B.fleets.isFleetInSystem(i, i2)) {
            this.B.beginTurn();
            return;
        }
        this.starSystem = this.B.galaxy.getStarSystem(i2);
        this.empireID = i;
        this.planetsToAttack = 0;
        setSpritesInvisible();
        setSystemBackground();
        setSystemOrbits();
        setAttackingFleet();
        setRemainingFleets();
        setHeader();
        setEmpireButtons();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void switched() {
        this.B.shipSelectOverlay.back();
        super.switched();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void update() {
        if (this.comet.getX() > -100.0f) {
            TiledSprite tiledSprite = this.comet;
            tiledSprite.setX(tiledSprite.getX() - 0.2f);
        }
    }
}
