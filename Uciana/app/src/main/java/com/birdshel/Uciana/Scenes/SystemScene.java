package com.birdshel.Uciana.Scenes;

import com.birdshel.Uciana.Colonies.Buildings.BuildingID;
import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Elements.PlanetInfo;
import com.birdshel.Uciana.Events.Event;
import com.birdshel.Uciana.Events.EventType;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Messages.MiningTechNeededMessage;
import com.birdshel.Uciana.Messages.PlanetDiscoveryMessage;
import com.birdshel.Uciana.Messages.PlanetExplorationMessage;
import com.birdshel.Uciana.Messages.ScoutsAllUsedMessage;
import com.birdshel.Uciana.Messages.SelectAttackMessage;
import com.birdshel.Uciana.Messages.Tutorials.SystemViewTutorial;
import com.birdshel.Uciana.Messages.UnableToRenameMessage;
import com.birdshel.Uciana.Messages.WormholeMessage;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Overlays.TroopUnloadOverlay;
import com.birdshel.Uciana.Overlays.TroopUnloadSelectOverlay;
import com.birdshel.Uciana.Planets.AsteroidBelt;
import com.birdshel.Uciana.Planets.AsteroidBeltSprite;
import com.birdshel.Uciana.Planets.GasGiant;
import com.birdshel.Uciana.Planets.IonStorm;
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
import com.birdshel.Uciana.Resources.Options;
import com.birdshel.Uciana.Resources.TutorialID;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.Ships.Ship;
import com.birdshel.Uciana.StarSystems.Nebulas;
import com.birdshel.Uciana.StarSystems.StarSystem;
import com.birdshel.Uciana.StarSystems.SunSprite;
import com.birdshel.Uciana.StarSystems.WormholeObject;
import com.birdshel.Uciana.Technology.TechID;
import java.util.ArrayList;
import java.util.List;
import org.andengine.entity.Entity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.color.Color;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class SystemScene extends ExtendedScene {
    public static int ORBIT_SIZE = 213;
    private static final int SUN_SPACE = 165;
    private TiledSprite attackButton;
    private VertexBufferObjectManager bufferManager;
    private TiledSprite comet;
    private TiledSprite eventsButton;
    private Sprite fleetPress;
    private TiledSprite galaxyButton;
    private TiledSprite ionFlash1;
    private TiledSprite ionFlash2;
    private TiledSprite ionFlash3;
    private TiledSprite ionFlash4;
    private Entity nebulaBackground;
    private Nebulas nebulas;
    private TiledSprite renameIcon;
    private ExtendedScene screen;
    public StarSystem starSystem;
    private SunSprite sunSprite;
    private Text systemName;
    private Text systemWarning;
    private TroopUnloadOverlay troopUnloadOverlay;
    private TroopUnloadSelectOverlay troopUnloadSelectOverlay;
    private TiledSprite unloadTroopsButton;
    private TiledSprite unloadTroopsIcon;
    private final List<PlanetSprite> planetSprites = new ArrayList();
    private final List<Entity> ionStorms = new ArrayList();
    private final List<Entity> asteroidBelts = new ArrayList();
    private final List<Entity> planetResources = new ArrayList();
    private final List<Entity> exploredTexts = new ArrayList();
    private final List<Entity> planetInfoIcons = new ArrayList();
    private final List<Entity> actionButtons = new ArrayList();
    private final List<Entity> colonizeButtonIcons = new ArrayList();
    private final List<Entity> outpostButtonIcons = new ArrayList();
    private final List<Entity> exploreButtonIcons = new ArrayList();
    private final List<Entity> colonizeActionButtons = new ArrayList();
    private final List<Entity> outpostActionButtons = new ArrayList();
    private final List<Entity> exploreActionButtons = new ArrayList();
    private final List<Entity> colonizeActionButtonIcons = new ArrayList();
    private final List<Entity> outpostActionButtonIcons = new ArrayList();
    private final List<Entity> exploreActionButtonIcons = new ArrayList();
    private final List<Entity> populationBars = new ArrayList();
    private final List<Entity> emptyPopulationBars = new ArrayList();
    private final List<Entity> populationStrings = new ArrayList();
    private final List<Entity> empireBars = new ArrayList();
    private final List<Entity> productionTexts = new ArrayList();
    private final List<Entity> productionTurns = new ArrayList();
    private final List<Entity> capitalIcons = new ArrayList();
    private final List<Entity> fleets = new ArrayList();
    private final List<Entity> shipCounts = new ArrayList();
    private final List<Integer> empireIDsForFleets = new ArrayList();
    private final List<Entity> outposts = new ArrayList();
    private final List<Entity> starBases = new ArrayList();
    private final List<Entity> turret1s = new ArrayList();
    private final List<Entity> turret2s = new ArrayList();
    private final List<Entity> shipYards = new ArrayList();
    private final List<Entity> bonusIcons = new ArrayList();
    private final List<Entity> textBonuses = new ArrayList();
    private final TiledSprite[] wormholeBottomLayers = new TiledSprite[4];
    private final TiledSprite[] wormholeTopLayers = new TiledSprite[4];

    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Scenes.SystemScene$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a */
        static final /* synthetic */ int[] f1478a;

        static {
            int[] iArr = new int[SystemObjectType.values().length];
            f1478a = iArr;
            try {
                iArr[SystemObjectType.PLANET.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1478a[SystemObjectType.GAS_GIANT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1478a[SystemObjectType.ASTEROID_BELT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public SystemScene(Game game) {
        this.B = game;
        this.screen = game.galaxyScene;
    }

    private void actionButtonPressed(Entity entity, int i) {
        if (entity.getAlpha() == 1.0f) {
            checkActionButton(i);
            this.B.sounds.playButtonPressSound();
            Game game = this.B;
            game.vibrate(game.BUTTON_VIBRATE);
        } else if (!this.exploreActionButtonIcons.get(i).isVisible() && !this.exploreButtonIcons.get(i).isVisible()) {
            if (this.outpostButtonIcons.get(i).isVisible() || this.outpostActionButtonIcons.get(i).isVisible()) {
                showMessage(new MiningTechNeededMessage());
            }
        } else {
            showMessage(new ScoutsAllUsedMessage());
        }
    }

    private void asteroidBeltPressed(int i) {
        loadPlanet(i);
        this.B.sounds.playSystemObjectPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void attackButtonPressed() {
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        Game game2 = this.B;
        game2.events.addSelectAttackEvent(game2.getCurrentPlayer(), this.starSystem.getID());
        showMessage(new SelectAttackMessage());
        this.attackButton.setAlpha(0.4f);
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [boolean, int] */
    private void checkActionButton(int i) {
        ?? isVisible = this.exploreButtonIcons.get(i).isVisible();
        int i2 = isVisible;
        if (this.outpostButtonIcons.get(i).isVisible()) {
            i2 = isVisible + 1;
        }
        int i3 = i2;
        if (this.colonizeButtonIcons.get(i).isVisible()) {
            i3 = i2 + 1;
        }
        if (i3 > 1) {
            if (!this.colonizeActionButtons.get(i).isVisible() && !this.outpostActionButtons.get(i).isVisible() && !this.exploreActionButtons.get(i).isVisible()) {
                setSingleActionButtons(i);
            } else {
                hideActionButtons();
            }
        } else if (this.exploreButtonIcons.get(i).isVisible()) {
            explorePlanet(i);
        } else if (this.outpostButtonIcons.get(i).isVisible()) {
            Game game = this.B;
            game.outposts.add(game.getCurrentPlayer(), this.starSystem.getID(), i, this);
        } else if (this.colonizeButtonIcons.get(i).isVisible()) {
            Game game2 = this.B;
            game2.colonies.add(game2.getCurrentPlayer(), this.starSystem.getID(), i, this);
        }
    }

    private void checkActionDown(Point point) {
        checkFleetPress(point);
    }

    private void checkActionMove(Point point) {
        this.fleetPress.setVisible(false);
        checkFleetPress(point);
    }

    private void checkActionUp(Point point) {
        this.fleetPress.setVisible(false);
        int i = 0;
        for (Entity entity : this.actionButtons) {
            if (A(entity, point, 0.0f, 0.0f)) {
                actionButtonPressed(entity, i);
                return;
            }
            i++;
        }
        int i2 = 0;
        for (Entity entity2 : this.colonizeActionButtons) {
            if (isClicked(entity2, point)) {
                colonizeActionButtonPressed(i2);
                return;
            }
            i2++;
        }
        int i3 = 0;
        for (Entity entity3 : this.outpostActionButtons) {
            if (isClicked(entity3, point)) {
                outpostActionButtonPressed(i3);
                return;
            }
            i3++;
        }
        int i4 = 0;
        for (Entity entity4 : this.exploreActionButtons) {
            if (A(entity4, point, 0.0f, 0.0f)) {
                exploreActionButtonPressed(entity4, i4);
                return;
            }
            i4++;
        }
        if (isClicked(this.galaxyButton, point)) {
            galaxyButtonPressed();
        } else if (isClicked(this.eventsButton, point)) {
            eventsButtonPressed();
        } else if (isClicked(this.attackButton, point)) {
            attackButtonPressed();
        } else if (isClicked(this.unloadTroopsButton, point)) {
            unloadTroopsButtonPressed();
        } else if (!y(this.systemName, point) && !x(this.renameIcon, point, 10.0f, 10.0f)) {
            for (TiledSprite tiledSprite : this.wormholeBottomLayers) {
                if (isClicked(tiledSprite, point)) {
                    wormholePressed();
                    return;
                }
            }
            for (PlanetSprite planetSprite : this.planetSprites) {
                if (planetSprite.getPlanetSprite().isVisible() && point.getX() > planetSprite.getX() && point.getX() < planetSprite.getX() + planetSprite.getWidthScaled() && point.getY() > planetSprite.getY() && point.getY() < planetSprite.getY() + planetSprite.getWidthScaled()) {
                    planetPressed(this.planetSprites.indexOf(planetSprite));
                }
            }
            for (Entity entity5 : this.asteroidBelts) {
                if (((AsteroidBeltSprite) entity5).isPressed(point)) {
                    asteroidBeltPressed(this.asteroidBelts.indexOf(entity5));
                }
            }
            for (Entity entity6 : this.fleets) {
                if (isClicked(entity6, point)) {
                    fleetPressed(this.fleets.indexOf(entity6));
                }
            }
        } else {
            renameSystemPressed();
        }
    }

    private void checkFleetPress(Point point) {
        for (Entity entity : this.fleets) {
            if (isClicked(entity, point)) {
                this.fleetPress.setVisible(true);
                this.fleetPress.setX(entity.getX() - 10.0f);
                this.fleetPress.setY(entity.getY() - 10.0f);
            }
        }
    }

    private void checkForSystemEvents() {
        Game game = this.B;
        List<Event> eventsForSystem = game.events.getEventsForSystem(game.getCurrentPlayer(), this.starSystem.getID());
        if (eventsForSystem.isEmpty()) {
            setEventsButton();
        } else {
            showEvent(eventsForSystem);
        }
    }

    private void colonizeActionButtonPressed(int i) {
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        Game game2 = this.B;
        game2.colonies.add(game2.getCurrentPlayer(), this.starSystem.getID(), i, this);
    }

    private void createActionButtons(int i) {
        float f2 = (ORBIT_SIZE * i) + 180;
        TiledTextureRegion tiledTextureRegion = this.B.graphics.buttonsTexture;
        VertexBufferObjectManager vertexBufferObjectManager = this.bufferManager;
        ButtonsEnum buttonsEnum = ButtonsEnum.BLANK_BLUE;
        TiledSprite H = H(f2, 570.0f, tiledTextureRegion, vertexBufferObjectManager, buttonsEnum.ordinal(), false);
        this.actionButtons.add(H);
        s(H);
        float f3 = (ORBIT_SIZE * i) + 180 + 37;
        TiledTextureRegion tiledTextureRegion2 = this.B.graphics.infoIconsTexture;
        VertexBufferObjectManager vertexBufferObjectManager2 = this.bufferManager;
        InfoIconEnum infoIconEnum = InfoIconEnum.COLONY_ICON;
        TiledSprite H2 = H(f3, 592.0f, tiledTextureRegion2, vertexBufferObjectManager2, infoIconEnum.ordinal(), false);
        H2.setSize(40.0f, 40.0f);
        this.colonizeButtonIcons.add(H2);
        float f4 = (ORBIT_SIZE * i) + 180 + 37;
        TiledTextureRegion tiledTextureRegion3 = this.B.graphics.infoIconsTexture;
        VertexBufferObjectManager vertexBufferObjectManager3 = this.bufferManager;
        InfoIconEnum infoIconEnum2 = InfoIconEnum.WORKER_ICON;
        TiledSprite H3 = H(f4, 592.0f, tiledTextureRegion3, vertexBufferObjectManager3, infoIconEnum2.ordinal(), false);
        H3.setSize(40.0f, 40.0f);
        this.outpostButtonIcons.add(H3);
        float f5 = (ORBIT_SIZE * i) + 180 + 37;
        TiledTextureRegion tiledTextureRegion4 = this.B.graphics.infoIconsTexture;
        VertexBufferObjectManager vertexBufferObjectManager4 = this.bufferManager;
        InfoIconEnum infoIconEnum3 = InfoIconEnum.SCOUT_ICON;
        TiledSprite H4 = H(f5, 592.0f, tiledTextureRegion4, vertexBufferObjectManager4, infoIconEnum3.ordinal(), false);
        H4.setSize(40.0f, 40.0f);
        this.exploreButtonIcons.add(H4);
        TiledSprite H5 = H((ORBIT_SIZE * i) + 180, 0.0f, this.B.graphics.buttonsTexture, this.bufferManager, buttonsEnum.ordinal(), false);
        this.colonizeActionButtons.add(H5);
        s(H5);
        TiledSprite H6 = H((ORBIT_SIZE * i) + 180, 0.0f, this.B.graphics.buttonsTexture, this.bufferManager, buttonsEnum.ordinal(), false);
        this.outpostActionButtons.add(H6);
        s(H6);
        TiledSprite H7 = H((ORBIT_SIZE * i) + 180, 0.0f, this.B.graphics.buttonsTexture, this.bufferManager, buttonsEnum.ordinal(), false);
        this.exploreActionButtons.add(H7);
        s(H7);
        TiledSprite H8 = H((ORBIT_SIZE * i) + 180 + 37, 0.0f, this.B.graphics.infoIconsTexture, this.bufferManager, infoIconEnum.ordinal(), false);
        H8.setSize(40.0f, 40.0f);
        this.colonizeActionButtonIcons.add(H8);
        TiledSprite H9 = H((ORBIT_SIZE * i) + 180 + 37, 0.0f, this.B.graphics.infoIconsTexture, this.bufferManager, infoIconEnum2.ordinal(), false);
        H9.setSize(40.0f, 40.0f);
        this.outpostActionButtonIcons.add(H9);
        TiledSprite H10 = H((i * ORBIT_SIZE) + 180 + 37, 0.0f, this.B.graphics.infoIconsTexture, this.bufferManager, infoIconEnum3.ordinal(), false);
        H10.setSize(40.0f, 40.0f);
        this.exploreActionButtonIcons.add(H10);
    }

    private void createAsteroidBelt(int i) {
        AsteroidBeltSprite asteroidBeltSprite = new AsteroidBeltSprite(this.B, this.bufferManager);
        asteroidBeltSprite.setX(i * ORBIT_SIZE);
        this.asteroidBelts.add(asteroidBeltSprite);
        attachChild(asteroidBeltSprite);
    }

    private void createButtons() {
        TiledSprite H = H(getWidth() - 120.0f, 0.0f, this.B.graphics.buttonsTexture, this.bufferManager, ButtonsEnum.GALAXY.ordinal(), true);
        this.galaxyButton = H;
        s(H);
        TiledSprite H2 = H(getWidth() - 240.0f, 0.0f, this.B.graphics.buttonsTexture, this.bufferManager, ButtonsEnum.EVENTS.ordinal(), false);
        this.eventsButton = H2;
        s(H2);
        TiledSprite H3 = H(0.0f, 0.0f, this.B.graphics.buttonsTexture, this.bufferManager, ButtonsEnum.ATTACK.ordinal(), false);
        this.attackButton = H3;
        s(H3);
        this.unloadTroopsButton = H(0.0f, 0.0f, this.B.graphics.buttonsTexture, this.bufferManager, ButtonsEnum.BLANK.ordinal(), false);
        this.unloadTroopsIcon = H(0.0f, 0.0f, this.B.graphics.gameIconsTexture, this.bufferManager, GameIconEnum.UNLOAD.ordinal(), false);
        s(this.unloadTroopsButton);
    }

    private void createComet() {
        TiledSprite J = J(0.0f, 0.0f, this.B.graphics.cometsTexture, this.bufferManager, false);
        this.comet = J;
        J.setBlendFunction(IShape.BLENDFUNCTION_SOURCE_DEFAULT, 771);
        this.comet.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(new AlphaModifier(0.5f, 0.85f, 1.0f), new AlphaModifier(0.5f, 1.0f, 0.85f))));
    }

    private void createEmpireBar() {
        TiledSprite J = J(0.0f, 530.0f, this.B.graphics.empireColors, this.bufferManager, false);
        J.setHeight(10.0f);
        this.empireBars.add(J);
        this.capitalIcons.add(H(0.0f, 518.0f, this.B.graphics.infoIconsTexture, this.bufferManager, InfoIconEnum.CAPITAL.ordinal(), false));
    }

    private void createExploredElements() {
        this.exploredTexts.add(G(0.0f, 410.0f, this.B.fonts.smallInfoFont, this.D, this.E, this.bufferManager, false));
    }

    private void createFleetIcons() {
        Sprite E = E(0.0f, 0.0f, this.B.graphics.selectedFleetTexture, this.bufferManager, false);
        this.fleetPress = E;
        E.setSize(80.0f, 80.0f);
        for (int i = 0; i < 6; i++) {
            TiledSprite H = H((i * 75) + 70, 660.0f, this.B.graphics.shipsTextures[0], this.bufferManager, 0, false);
            H.setSize(60.0f, 60.0f);
            this.fleets.add(H);
            this.empireIDsForFleets.add(-1);
            this.shipCounts.add(F(0.0f, 680.0f, this.B.fonts.smallInfoFont, this.D, this.E, this.bufferManager));
        }
    }

    private void createIonStorm() {
        TiledSprite J = J(0.0f, 0.0f, this.B.graphics.ionCloudsTexture, this.bufferManager, false);
        J.setRotationCenter(J.getWidthScaled() / 2.0f, J.getHeightScaled() / 2.0f);
        this.ionStorms.add(J);
        TiledSprite J2 = J(0.0f, 0.0f, this.B.graphics.ionCloudsTexture, this.bufferManager, false);
        J2.setRotationCenter(J2.getWidthScaled() / 2.0f, J2.getHeightScaled() / 2.0f);
        this.ionStorms.add(J2);
        TiledSprite J3 = J(0.0f, 0.0f, this.B.graphics.ionFlashesTexture, this.bufferManager, false);
        this.ionFlash1 = J3;
        startIonFlashEffect(J3, 3.0f);
        TiledSprite J4 = J(0.0f, 0.0f, this.B.graphics.ionFlashesTexture, this.bufferManager, false);
        this.ionFlash2 = J4;
        startIonFlashEffect(J4, 3.5f);
        TiledSprite J5 = J(0.0f, 0.0f, this.B.graphics.ionFlashesTexture, this.bufferManager, false);
        this.ionFlash3 = J5;
        startIonFlashEffect(J5, 4.0f);
        TiledSprite J6 = J(0.0f, 0.0f, this.B.graphics.ionFlashesTexture, this.bufferManager, false);
        this.ionFlash4 = J6;
        startIonFlashEffect(J6, 4.5f);
    }

    private void createNebula() {
        this.nebulas = this.B.nebulas;
        Entity entity = new Entity();
        this.nebulaBackground = entity;
        attachChild(entity);
    }

    private void createOutpostIcons(int i) {
        TiledSprite H = H((i * ORBIT_SIZE) + 135 + 30, 365.0f, this.B.graphics.gameIconsTexture, this.bufferManager, GameIconEnum.OUTPOST.ordinal(), false);
        H.setSize(35.0f, 35.0f);
        H.setZIndex(2);
        this.outposts.add(H);
    }

    private void createOverlays() {
        this.H = new MessageOverlay(this.B, this.bufferManager);
        this.troopUnloadSelectOverlay = new TroopUnloadSelectOverlay(this.B, this.bufferManager);
        this.troopUnloadOverlay = new TroopUnloadOverlay(this.B, this.bufferManager);
    }

    private void createPlanetInfoIcons() {
        PlanetInfo planetInfo = new PlanetInfo(this.B, this.bufferManager);
        planetInfo.setScaleCenter(0.0f, 0.0f);
        planetInfo.setScale(0.7f);
        attachChild(planetInfo);
        this.planetInfoIcons.add(planetInfo);
        this.textBonuses.add(G(0.0f, 460.0f, this.B.fonts.smallFont, "####", this.E, this.bufferManager, false));
        TiledSprite H = H(0.0f, 450.0f, this.B.graphics.infoIconsTexture, this.bufferManager, InfoIconEnum.SCIENCE.ordinal(), false);
        H.setSize(35.0f, 35.0f);
        this.bonusIcons.add(H);
    }

    private void createPopulationBar() {
        this.populationBars.add(E(0.0f, 0.0f, this.B.graphics.popTexture, this.bufferManager, false));
        this.emptyPopulationBars.add(E(0.0f, 0.0f, this.B.graphics.popEmptyTexture, this.bufferManager, false));
        this.populationStrings.add(G(0.0f, 0.0f, this.B.fonts.smallInfoFont, this.D, this.E, this.bufferManager, false));
    }

    private void createProductionSummary() {
        Font font = this.B.fonts.smallInfoFont;
        CharSequence charSequence = this.D;
        AutoWrap autoWrap = AutoWrap.WORDS;
        HorizontalAlign horizontalAlign = HorizontalAlign.CENTER;
        this.productionTexts.add(F(0.0f, 550.0f, font, charSequence, new TextOptions(autoWrap, ORBIT_SIZE - 10, horizontalAlign), this.bufferManager));
        this.productionTurns.add(F(0.0f, 573.0f, this.B.fonts.smallInfoFont, this.D, new TextOptions(autoWrap, ORBIT_SIZE - 10, horizontalAlign), this.bufferManager));
    }

    private void createResourceIcons() {
        for (int i = 0; i < 4; i++) {
            TiledSprite J = J(0.0f, 410.0f, this.B.graphics.resourceIconsTexture, this.bufferManager, false);
            J.setSize(40.0f, 40.0f);
            this.planetResources.add(J);
        }
    }

    private void createShipYardIcons(int i) {
        int i2 = ORBIT_SIZE;
        TiledSprite H = H((i * i2) + 135 + ((i2 - 35) - 30), 255.0f, this.B.graphics.gameIconsTexture, this.bufferManager, GameIconEnum.SHIP_YARDS.ordinal(), false);
        H.setSize(35.0f, 35.0f);
        H.setZIndex(2);
        this.shipYards.add(H);
    }

    private void createStarBaseIcons(int i) {
        int i2 = ORBIT_SIZE;
        TiledSprite H = H((i * i2) + 135 + ((i2 - 35) - 30), 365.0f, this.B.graphics.gameIconsTexture, this.bufferManager, GameIconEnum.STAR_BASE.ordinal(), false);
        H.setSize(35.0f, 35.0f);
        H.setZIndex(2);
        this.starBases.add(H);
    }

    private void createSun() {
        SunSprite sunSprite = new SunSprite(this.B, this.bufferManager, true);
        this.sunSprite = sunSprite;
        attachChild(sunSprite);
    }

    private void createSystemInfo() {
        this.systemName = F(0.0f, 0.0f, this.B.fonts.infoFont, this.D, this.E, this.bufferManager);
        this.renameIcon = H(0.0f, 0.0f, this.B.graphics.infoIconsTexture, this.bufferManager, InfoIconEnum.RENAME.ordinal(), false);
        Text G = G(0.0f, 50.0f, this.B.fonts.infoFont, this.D, this.E, this.bufferManager, false);
        this.systemWarning = G;
        G.setColor(Color.RED);
        v(this.systemWarning);
    }

    private void createSystemObjects() {
        for (int i = 0; i < 5; i++) {
            createAsteroidBelt(i);
            createEmpireBar();
            createProductionSummary();
            createOutpostIcons(i);
            createStarBaseIcons(i);
            createTurretIcons(i);
            createShipYardIcons(i);
            createResourceIcons();
            createExploredElements();
            createPlanetInfoIcons();
            createPopulationBar();
            createActionButtons(i);
        }
    }

    private void createTurretIcons(int i) {
        int i2 = ORBIT_SIZE;
        TiledTextureRegion tiledTextureRegion = this.B.graphics.gameIconsTexture;
        VertexBufferObjectManager vertexBufferObjectManager = this.bufferManager;
        GameIconEnum gameIconEnum = GameIconEnum.TORPEDO_TURRET;
        TiledSprite H = H((i * i2) + 135 + ((i2 - 35) - 30) + 10, 340.0f, tiledTextureRegion, vertexBufferObjectManager, gameIconEnum.ordinal(), false);
        H.setSize(20.0f, 20.0f);
        H.setZIndex(2);
        this.turret1s.add(H);
        int i3 = ORBIT_SIZE;
        TiledSprite H2 = H((i * i3) + 135 + (((i3 - 35) - 30) - 20), 380.0f, this.B.graphics.gameIconsTexture, this.bufferManager, gameIconEnum.ordinal(), false);
        H2.setSize(20.0f, 20.0f);
        H2.setZIndex(2);
        this.turret2s.add(H2);
    }

    private void createWormholes() {
        for (int i = 0; i < 4; i++) {
            TiledSprite J = J(585.0f, 240.0f, this.B.graphics.gameIconsTexture, this.bufferManager, true);
            J.setCurrentTileIndex(GameIconEnum.BOTTOM_WORMHOLE_LAYER.ordinal());
            this.wormholeBottomLayers[i] = J;
            TiledSprite J2 = J(585.0f, 240.0f, this.B.graphics.gameIconsTexture, this.bufferManager, true);
            J2.setAlpha(0.9f);
            J2.setCurrentTileIndex(GameIconEnum.TOP_WORMHOLE_LAYER.ordinal());
            this.wormholeTopLayers[i] = J2;
        }
    }

    private void eventsButtonPressed() {
        changeScene(this.B.eventsScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void exploreActionButtonPressed(Entity entity, int i) {
        if (entity.getAlpha() == 1.0f) {
            explorePlanet(i);
            this.B.sounds.playButtonPressSound();
            Game game = this.B;
            game.vibrate(game.BUTTON_VIBRATE);
            return;
        }
        showMessage(new ScoutsAllUsedMessage());
    }

    private void explorePlanet(int i) {
        Game game = this.B;
        Fleet fleetInSystem = game.fleets.getFleetInSystem(game.getCurrentPlayer(), this.starSystem.getID());
        Ship scoutUsed = fleetInSystem.setScoutUsed();
        Planet planet = (Planet) this.starSystem.getSystemObject(i);
        planet.getExplorationFind().addFindBonusToEmpire(fleetInSystem.empireID, this.starSystem.getID(), i, fleetInSystem, scoutUsed);
        showMessage(new PlanetExplorationMessage(planet, this.B.getActivity().getString(R.string.exploration_explorers), planet.isUnexplored()));
        planet.beenExplored(fleetInSystem.empireID);
        loadSystem(this.starSystem.getID());
    }

    private void fleetPressed(int i) {
        loadFleet(i);
        this.B.sounds.playFleetPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void galaxyButtonPressed() {
        changeScene(this.B.galaxyScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void hideActionButtons() {
        C(this.colonizeActionButtons);
        C(this.outpostActionButtons);
        C(this.exploreActionButtons);
        C(this.colonizeActionButtonIcons);
        C(this.outpostActionButtonIcons);
        C(this.exploreActionButtonIcons);
    }

    public /* synthetic */ void lambda$releasePoolElements$0(ExtendedScene extendedScene, Object obj) {
        this.nebulaBackground.detachChild(this.nebulas);
        for (PlanetSprite planetSprite : this.planetSprites) {
            detachChild(planetSprite);
            this.B.planetSpritePool.push(planetSprite);
        }
        this.planetSprites.clear();
        extendedScene.getPoolElements();
        M(extendedScene, obj);
        this.B.setCurrentScene(extendedScene);
    }

    private void loadFleet(int i) {
        this.B.getCurrentEmpire().setSelectedFleetID(this.B.fleets.getFleetInSystem(this.empireIDsForFleets.get(i).intValue(), this.starSystem.getID()).id);
        changeScene(this.B.galaxyScene);
    }

    private void loadPlanet(int i) {
        changeScene(this.B.planetScene, Integer.valueOf(i));
    }

    private void loadStarSystem(int i) {
        this.B.getActivity().setLocale();
        this.starSystem = this.B.galaxy.getStarSystem(i);
        setSystemBackground();
        setSystemOrbits();
        setSystemInfo();
        setFleetIcons(i);
        showShortCuts();
        checkForSystemEvents();
        setActionButtons();
        Options options = Game.options;
        TutorialID tutorialID = TutorialID.SYSTEM;
        if (options.shouldTutorialBeShown(tutorialID)) {
            showMessage(new SystemViewTutorial());
            Game.options.markSeen(tutorialID);
        }
    }

    private void outpostActionButtonPressed(int i) {
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        Game game2 = this.B;
        game2.outposts.add(game2.getCurrentPlayer(), this.starSystem.getID(), i, this);
    }

    private void planetPressed(int i) {
        loadPlanet(i);
        this.B.sounds.playSystemObjectPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void renameSystemPressed() {
        boolean z = false;
        boolean z2 = false;
        for (SystemObject systemObject : this.starSystem.getSystemObjects()) {
            if (systemObject.isOccupied()) {
                if (systemObject.getOccupier() == this.B.getCurrentPlayer()) {
                    z = true;
                } else {
                    z2 = true;
                }
            }
        }
        if (z && !z2) {
            this.B.keyboardOverlay.setOverlay("renameSystem", this.B.getActivity().getString(R.string.system_rename_message, new Object[]{this.starSystem.getName()}));
            setChildScene(this.B.keyboardOverlay, false, false, true);
        } else {
            showMessage(new UnableToRenameMessage(z));
        }
        this.B.sounds.playSystemObjectPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void setActionButtonForAsteroidBelt(AsteroidBelt asteroidBelt, boolean z) {
        if (asteroidBelt.hasOutpost() || !z) {
            return;
        }
        float f2 = this.B.getCurrentEmpire().hasTech(TechID.ASTEROID_MINING_OUTPOST) ? 1.0f : 0.4f;
        int orbit = asteroidBelt.getOrbit();
        this.actionButtons.get(orbit).setVisible(true);
        this.actionButtons.get(orbit).setAlpha(f2);
        this.outpostButtonIcons.get(orbit).setVisible(true);
        this.outpostButtonIcons.get(orbit).setX(this.actionButtons.get(orbit).getX() + 40.0f);
        this.outpostButtonIcons.get(orbit).setAlpha(f2);
    }

    private void setActionButtonForGasGiant(GasGiant gasGiant, boolean z) {
        if (gasGiant.hasOutpost() || !z) {
            return;
        }
        int orbit = gasGiant.getOrbit();
        this.actionButtons.get(orbit).setVisible(true);
        this.actionButtons.get(orbit).setAlpha(1.0f);
        this.outpostButtonIcons.get(orbit).setVisible(true);
        this.outpostButtonIcons.get(orbit).setAlpha(1.0f);
        this.outpostButtonIcons.get(orbit).setX(this.actionButtons.get(orbit).getX() + 40.0f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v1, types: [int] */
    /* JADX WARN: Type inference failed for: r11v26 */
    /* JADX WARN: Type inference failed for: r11v27 */
    private void setActionButtonsForPlanet(Planet planet, boolean z, boolean z2, boolean z3) {
        ?? r11 = z3;
        if (planet.hasBeenExploredByEmpire(this.B.getCurrentPlayer())) {
            r11 = 0;
        }
        z2 = (planet.hasColony() || planet.hasOutpost()) ? false : false;
        if (planet.hasColony() || (planet.hasOutpost() && planet.getOutpost().getEmpireID() != this.B.getCurrentPlayer())) {
            z = false;
        }
        int i = z2 ? r11 + 1 : r11;
        if (z) {
            i++;
        }
        if (i == 0) {
            return;
        }
        int i2 = 10;
        if (i == 1) {
            i2 = 40;
        } else if (i == 2) {
            i2 = 20;
        }
        int orbit = planet.getOrbit();
        this.actionButtons.get(orbit).setVisible(true);
        this.actionButtons.get(orbit).setAlpha(1.0f);
        if (r11 != 0) {
            this.exploreButtonIcons.get(orbit).setVisible(true);
            this.exploreButtonIcons.get(orbit).setX(this.actionButtons.get(orbit).getX() + i2);
            i2 = i == 3 ? i2 + 30 : i2 + 40;
            this.exploreButtonIcons.get(orbit).setAlpha(1.0f);
            if (i == 1) {
                Game game = this.B;
                if (!game.fleets.getFleetInSystem(game.getCurrentPlayer(), this.starSystem.getID()).hasScoutShipAvailable()) {
                    this.exploreButtonIcons.get(orbit).setAlpha(0.4f);
                    this.actionButtons.get(orbit).setAlpha(0.4f);
                }
            }
        }
        if (z2) {
            this.outpostButtonIcons.get(orbit).setVisible(true);
            this.outpostButtonIcons.get(orbit).setAlpha(1.0f);
            this.outpostButtonIcons.get(orbit).setX(this.actionButtons.get(orbit).getX() + i2);
            i2 = i == 3 ? i2 + 30 : i2 + 40;
        }
        if (z) {
            this.colonizeButtonIcons.get(orbit).setVisible(true);
            this.colonizeButtonIcons.get(orbit).setX(this.actionButtons.get(orbit).getX() + i2);
        }
    }

    private void setAsteroidBelt(AsteroidBelt asteroidBelt, int i) {
        AsteroidBeltSprite asteroidBeltSprite = (AsteroidBeltSprite) this.asteroidBelts.get(i);
        asteroidBeltSprite.set(asteroidBelt);
        asteroidBeltSprite.setVisible(true);
        Text text = (Text) this.textBonuses.get(i);
        text.setVisible(true);
        text.setText("+" + asteroidBelt.getMineralRichness().getAsteroidBonusPercent() + "%");
        text.setX(((float) (((ORBIT_SIZE * i) + 135) + 106)) - ((text.getWidthScaled() + 35.0f) / 2.0f));
        TiledSprite tiledSprite = (TiledSprite) this.bonusIcons.get(i);
        tiledSprite.setVisible(true);
        tiledSprite.setCurrentTileIndex(InfoIconEnum.PRODUCTION.ordinal());
        tiledSprite.setX(text.getX() + text.getWidthScaled());
        if (asteroidBelt.hasOutpost()) {
            Text text2 = (Text) this.populationStrings.get(i);
            text2.setVisible(true);
            text2.setText(this.B.getActivity().getString(R.string.system_mining_outpost));
            text2.setPosition((((ORBIT_SIZE * i) + 135) + 106) - (text2.getWidth() / 2.0f), 493.0f);
            TiledSprite tiledSprite2 = (TiledSprite) this.outposts.get(i);
            tiledSprite2.setSize(45.0f, 45.0f);
            tiledSprite2.setCurrentTileIndex(GameIconEnum.MINING_STATION.ordinal());
        }
    }

    private void setColony(Planet planet, Text text) {
        int orbit = planet.getOrbit();
        Colony colony = this.B.colonies.getColony(planet.getSystemID(), orbit);
        Sprite sprite = (Sprite) this.populationBars.get(orbit);
        sprite.setVisible(true);
        sprite.setPosition((ORBIT_SIZE * orbit) + SUN_SPACE, 485.0f);
        sprite.setSize(153.0f, 34.0f);
        float population = colony.getPopulation() / planet.getMaxPopulation();
        if (population != 1.0f) {
            float f2 = population * 151.0f;
            Sprite sprite2 = (Sprite) this.emptyPopulationBars.get(orbit);
            sprite2.setPosition((ORBIT_SIZE * orbit) + 166 + f2, 491.0f);
            sprite2.setSize(151.0f - f2, 21.0f);
            sprite2.setVisible(true);
        }
        text.setText(this.B.getActivity().getString(R.string.system_colony_pop, new Object[]{Integer.valueOf(colony.getPopulation()), Integer.valueOf(planet.getMaxPopulation())}));
        if (colony.hasBuilding(BuildingID.SPACE_DOCK)) {
            TiledSprite tiledSprite = (TiledSprite) this.outposts.get(orbit);
            tiledSprite.setSize(35.0f, 35.0f);
            tiledSprite.setCurrentTileIndex(GameIconEnum.OUTPOST.ordinal());
            tiledSprite.setVisible(true);
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
        if (colony.getEmpireID() == this.B.getCurrentPlayer()) {
            Text text2 = (Text) this.productionTexts.get(orbit);
            text2.setVisible(true);
            text2.setText(planet.getColony().getManufacturing().getCurrentItem().getName());
            int i = ORBIT_SIZE;
            text2.setX((((orbit * i) + 135) + (i / 2.0f)) - (text2.getWidthScaled() / 2.0f));
            Text text3 = (Text) this.productionTurns.get(orbit);
            text3.setVisible(true);
            text3.setText(colony.getTurnsLeftOnProductionString());
            int i2 = ORBIT_SIZE;
            text3.setX((((orbit * i2) + 135) + (i2 / 2.0f)) - (text3.getWidthScaled() / 2.0f));
            text3.setY(text2.getY() + text2.getHeightScaled() + 5.0f);
        }
    }

    private void setComet() {
        if (this.starSystem.isCometVisible()) {
            this.comet.setVisible(true);
            this.comet.setCurrentTileIndex(this.starSystem.getCometType());
            this.comet.setPosition(this.starSystem.getCometPosition().getX(), this.starSystem.getCometPosition().getY());
        } else if (Functions.percent(33)) {
            Point point = new Point(Functions.random.nextInt(1000) + 300, Functions.random.nextInt(620));
            int nextInt = Functions.random.nextInt(2);
            this.starSystem.setCometVisibility(true);
            this.starSystem.setCometPosition(point);
            this.starSystem.setCometType(nextInt);
            this.comet.setVisible(true);
            this.comet.setCurrentTileIndex(nextInt);
            this.comet.setPosition(point.getX(), point.getY());
        } else {
            this.comet.setVisible(false);
        }
    }

    private void setEmpireBar(SystemObject systemObject, int i) {
        TiledSprite tiledSprite = (TiledSprite) this.empireBars.get(i);
        if (systemObject.hasColony()) {
            tiledSprite.setX((ORBIT_SIZE * i) + SUN_SPACE);
            tiledSprite.setCurrentTileIndex(systemObject.getColony().getEmpireID());
            tiledSprite.setWidth(153.0f);
            tiledSprite.setVisible(true);
            if (systemObject.getColony().hasBuilding(BuildingID.CAPITAL)) {
                this.capitalIcons.get(i).setVisible(true);
                this.capitalIcons.get(i).setX((ORBIT_SIZE * i) + SUN_SPACE + 61.5f);
            }
        } else if (systemObject.hasOutpost()) {
            tiledSprite.setX((i * ORBIT_SIZE) + SUN_SPACE + 38);
            tiledSprite.setCurrentTileIndex(systemObject.getOutpost().getEmpireID());
            tiledSprite.setWidth(76.0f);
            tiledSprite.setVisible(true);
        }
    }

    private void setEventsButton() {
        this.eventsButton.setVisible(false);
        if (this.screen instanceof EventsScene) {
            Game game = this.B;
            if (game.events.getEvents(game.getCurrentPlayer()).size() > 1) {
                this.eventsButton.setVisible(true);
            }
        }
    }

    private void setGasGiant(GasGiant gasGiant, int i) {
        this.planetSprites.get(i).setGasGiant(gasGiant, gasGiant.getScale(this.B.systemScene), Moon.getScale(this.B.systemScene));
        Text text = (Text) this.textBonuses.get(i);
        text.setVisible(true);
        text.setText("+" + gasGiant.getSciencePercentBonus() + "%");
        text.setX(((float) (((ORBIT_SIZE * i) + 135) + 106)) - ((text.getWidthScaled() + 35.0f) / 2.0f));
        TiledSprite tiledSprite = (TiledSprite) this.bonusIcons.get(i);
        tiledSprite.setVisible(true);
        tiledSprite.setCurrentTileIndex(InfoIconEnum.SCIENCE.ordinal());
        tiledSprite.setX(text.getX() + text.getWidthScaled());
        if (gasGiant.hasOutpost()) {
            Text text2 = (Text) this.populationStrings.get(i);
            text2.setVisible(true);
            text2.setText(this.B.getActivity().getString(R.string.system_science_station));
            text2.setPosition((((ORBIT_SIZE * i) + 135) + 106) - (text2.getWidth() / 2.0f), 493.0f);
            TiledSprite tiledSprite2 = (TiledSprite) this.outposts.get(i);
            tiledSprite2.setSize(45.0f, 45.0f);
            tiledSprite2.setCurrentTileIndex(GameIconEnum.SCIENCE_STATION.ordinal());
        }
    }

    private void setIonStorm() {
        IonStorm ionStorm = this.starSystem.getIonStorm();
        if (ionStorm.isVisible()) {
            for (int i = 0; i < 2; i++) {
                TiledSprite tiledSprite = (TiledSprite) this.ionStorms.get(i);
                tiledSprite.setVisible(true);
                tiledSprite.setCurrentTileIndex(ionStorm.getLayer(i));
                tiledSprite.setPosition(ionStorm.getPosition().getX() + ionStorm.getPositionOfCloud(i).getX(), ionStorm.getPosition().getY() + ionStorm.getPositionOfCloud(i).getY());
                tiledSprite.setAlpha(ionStorm.getAlpha(i));
                tiledSprite.setRotation(ionStorm.getRotation(i));
            }
            int widthScaled = (int) (((TiledSprite) this.ionStorms.get(0)).getWidthScaled() - this.ionFlash1.getWidthScaled());
            float x = ionStorm.getPosition().getX() + ionStorm.getPositionOfCloud(0).getX();
            float y = ionStorm.getPosition().getY() + ionStorm.getPositionOfCloud(0).getY();
            this.ionFlash1.setX(Functions.random.nextInt(widthScaled) + x);
            this.ionFlash1.setY(Functions.random.nextInt(widthScaled) + y);
            this.ionFlash1.setCurrentTileIndex(Functions.random.nextInt(6));
            this.ionFlash1.setVisible(true);
            this.ionFlash2.setX(x + Functions.random.nextInt(widthScaled));
            this.ionFlash2.setY(y + Functions.random.nextInt(widthScaled));
            this.ionFlash2.setCurrentTileIndex(Functions.random.nextInt(6));
            this.ionFlash2.setVisible(true);
            float x2 = ionStorm.getPosition().getX() + ionStorm.getPositionOfCloud(1).getX();
            float y2 = ionStorm.getPosition().getY() + ionStorm.getPositionOfCloud(1).getY();
            this.ionFlash3.setX(Functions.random.nextInt(widthScaled) + x2);
            this.ionFlash3.setY(Functions.random.nextInt(widthScaled) + y2);
            this.ionFlash3.setCurrentTileIndex(Functions.random.nextInt(6));
            this.ionFlash3.setVisible(true);
            this.ionFlash4.setX(x2 + Functions.random.nextInt(widthScaled));
            this.ionFlash4.setY(y2 + Functions.random.nextInt(widthScaled));
            this.ionFlash4.setCurrentTileIndex(Functions.random.nextInt(6));
            this.ionFlash4.setVisible(true);
        }
    }

    private void setNebula() {
        this.nebulas.set(this.starSystem.getID());
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
                tiledSprite.setX(((((ORBIT_SIZE * i) + 135) + 106) - (size / 2.0f)) + (i2 * 40));
                i2++;
            }
        } else {
            Text text = (Text) this.exploredTexts.get(i);
            text.setVisible(true);
            if (planet.isUnexplored()) {
                text.setColor(Color.WHITE);
                text.setText(this.B.getActivity().getString(R.string.system_unexplored));
            } else {
                text.setColor(new Color(0.6f, 0.6f, 0.6f));
                text.setText(this.B.getActivity().getString(R.string.system_unknown));
            }
            text.setX((((ORBIT_SIZE * i) + 135) + 106) - (text.getWidthScaled() / 2.0f));
        }
        PlanetInfo planetInfo = (PlanetInfo) this.planetInfoIcons.get(i);
        planetInfo.set(planet);
        planetInfo.setPosition((ORBIT_SIZE * i) + 135 + 36, 450.0f);
        planetInfo.setVisible(true);
        Text text2 = (Text) this.populationStrings.get(i);
        if (planet.hasColony()) {
            setColony(planet, text2);
        } else {
            if (planet.hasOutpost()) {
                TiledSprite tiledSprite2 = (TiledSprite) this.outposts.get(i);
                tiledSprite2.setSize(35.0f, 35.0f);
                tiledSprite2.setCurrentTileIndex(GameIconEnum.OUTPOST.ordinal());
            }
            text2.setText(this.B.getActivity().getString(R.string.system_planet_max_pop, new Object[]{Integer.valueOf(planet.getMaxPopulation())}));
        }
        text2.setVisible(true);
        text2.setPosition((((i * ORBIT_SIZE) + 135) + 106) - (text2.getWidth() / 2.0f), 493.0f);
    }

    private void setSingleActionButtons(int i) {
        hideActionButtons();
        Game game = this.B;
        Fleet fleetInSystem = game.fleets.getFleetInSystem(game.getCurrentPlayer(), this.starSystem.getID());
        float y = this.actionButtons.get(i).getY() - 86.0f;
        if (this.colonizeButtonIcons.get(i).isVisible()) {
            this.colonizeActionButtons.get(i).setVisible(true);
            this.colonizeActionButtons.get(i).setY(y);
            this.colonizeActionButtonIcons.get(i).setVisible(true);
            this.colonizeActionButtonIcons.get(i).setY(y + 23.0f);
            y -= 86.0f;
        }
        if (this.outpostButtonIcons.get(i).isVisible()) {
            this.outpostActionButtons.get(i).setVisible(true);
            this.outpostActionButtons.get(i).setY(y);
            this.outpostActionButtonIcons.get(i).setVisible(true);
            this.outpostActionButtonIcons.get(i).setY(y + 23.0f);
            y -= 86.0f;
        }
        if (this.exploreButtonIcons.get(i).isVisible()) {
            this.exploreActionButtons.get(i).setVisible(true);
            this.exploreActionButtons.get(i).setY(y);
            this.exploreActionButtonIcons.get(i).setVisible(true);
            this.exploreActionButtonIcons.get(i).setY(y + 23.0f);
            float f2 = fleetInSystem.hasScoutShipAvailable() ? 1.0f : 0.4f;
            this.exploreActionButtonIcons.get(i).setAlpha(f2);
            this.exploreActionButtons.get(i).setAlpha(f2);
        }
    }

    private void setSpritesInvisible() {
        C(this.planetInfoIcons);
        C(this.asteroidBelts);
        C(this.ionStorms);
        C(this.populationStrings);
        C(this.empireBars);
        C(this.productionTexts);
        C(this.productionTurns);
        C(this.capitalIcons);
        C(this.populationBars);
        C(this.emptyPopulationBars);
        C(this.outposts);
        C(this.starBases);
        C(this.turret1s);
        C(this.turret2s);
        C(this.shipYards);
        C(this.actionButtons);
        C(this.colonizeButtonIcons);
        C(this.outpostButtonIcons);
        C(this.exploreButtonIcons);
        hideActionButtons();
        C(this.fleets);
        C(this.shipCounts);
        C(this.planetResources);
        C(this.exploredTexts);
        C(this.textBonuses);
        C(this.bonusIcons);
        this.systemWarning.setVisible(false);
        this.ionFlash1.setVisible(false);
        this.ionFlash2.setVisible(false);
        this.ionFlash3.setVisible(false);
        this.ionFlash4.setVisible(false);
        this.eventsButton.setVisible(false);
        this.attackButton.setVisible(false);
        this.attackButton.setAlpha(1.0f);
        this.comet.setVisible(false);
        this.renameIcon.setVisible(false);
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
        setNebula();
        this.sunSprite.set(this.starSystem.getStarType());
        setComet();
        setIonStorm();
        setWormholes();
    }

    private void setSystemInfo() {
        boolean z = false;
        this.systemName.setText(this.B.getActivity().getString(R.string.system_name, new Object[]{this.starSystem.getName()}));
        this.systemName.setPosition((getWidth() / 2.0f) - (this.systemName.getWidth() / 2.0f), 10.0f);
        this.renameIcon.setPosition(this.systemName.getX() + this.systemName.getWidthScaled() + 10.0f, this.systemName.getY());
        boolean z2 = false;
        for (SystemObject systemObject : this.starSystem.getSystemObjects()) {
            if (systemObject.isOccupied()) {
                if (systemObject.getOccupier() == this.B.getCurrentPlayer()) {
                    z = true;
                } else {
                    z2 = true;
                }
            }
        }
        if (z && !z2) {
            this.renameIcon.setVisible(true);
        }
        if (this.B.getCurrentEmpire().isSystemBlockaded(this.starSystem.getID())) {
            this.systemWarning.setVisible(true);
            this.systemWarning.setText(this.B.getActivity().getString(R.string.system_blockaded));
            this.systemWarning.setX((getWidth() / 2.0f) - (this.systemWarning.getWidthScaled() / 2.0f));
        }
    }

    private void setSystemOrbits() {
        for (SystemObject systemObject : this.starSystem.getSystemObjects()) {
            int orbit = systemObject.getOrbit();
            int i = AnonymousClass1.f1478a[systemObject.getSystemObjectType().ordinal()];
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

    private void setWormholes() {
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

    private void showEvent(List<Event> list) {
        if (list.get(0).getEventType() == EventType.SYSTEM_DISCOVERY) {
            showSystemDiscoveryEvent();
        }
        this.B.events.removeEvent(list.get(0));
    }

    private void showShortCuts() {
        for (Entity entity : this.actionButtons) {
            entity.setVisible(false);
        }
        for (Entity entity2 : this.colonizeButtonIcons) {
            entity2.setVisible(false);
        }
        for (Entity entity3 : this.outpostButtonIcons) {
            entity3.setVisible(false);
        }
        for (Entity entity4 : this.exploreButtonIcons) {
            entity4.setVisible(false);
        }
        Game game = this.B;
        if (game.fleets.isFleetInSystem(game.getCurrentPlayer(), this.starSystem.getID())) {
            Game game2 = this.B;
            Fleet fleetInSystem = game2.fleets.getFleetInSystem(game2.getCurrentPlayer(), this.starSystem.getID());
            boolean hasColonyShip = fleetInSystem.hasColonyShip();
            boolean hasOutpostShip = fleetInSystem.hasOutpostShip();
            boolean hasScoutShip = fleetInSystem.hasScoutShip();
            if (hasColonyShip || hasOutpostShip || hasScoutShip) {
                for (SystemObject systemObject : this.starSystem.getSystemObjects()) {
                    int i = AnonymousClass1.f1478a[systemObject.getSystemObjectType().ordinal()];
                    if (i == 1) {
                        setActionButtonsForPlanet((Planet) systemObject, hasColonyShip, hasOutpostShip, hasScoutShip);
                    } else if (i == 2) {
                        setActionButtonForGasGiant((GasGiant) systemObject, hasOutpostShip);
                    } else if (i == 3) {
                        setActionButtonForAsteroidBelt((AsteroidBelt) systemObject, hasOutpostShip);
                    }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:63:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void showSystemDiscoveryEvent() {
        boolean z;
        int i;
        ArrayList arrayList = new ArrayList();
        if (this.starSystem.hasWormholes()) {
            boolean z2 = false;
            for (Integer num : this.B.galaxy.getWormholes(this.starSystem.getID())) {
                if (this.B.getCurrentEmpire().isDiscoveredSystem(num.intValue())) {
                    z2 = true;
                }
            }
            if (!z2) {
                arrayList.add(new WormholeMessage(true));
                z = true;
                for (i = 0; i < 5; i++) {
                    if (this.starSystem.isOrbitSpecial(i)) {
                        arrayList.add(new PlanetDiscoveryMessage((Planet) this.starSystem.getSystemObject(i), true));
                        z = true;
                    }
                }
                if (z) {
                    return;
                }
                this.H.setOverlay(arrayList);
                setChildScene(this.H, false, false, true);
                return;
            }
        }
        z = false;
        while (i < 5) {
        }
        if (z) {
        }
    }

    private void startIonFlashEffect(TiledSprite tiledSprite, float f2) {
        tiledSprite.setBlendFunction(IShape.BLENDFUNCTION_SOURCE_DEFAULT, 771);
        tiledSprite.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(new AlphaModifier(0.25f, 0.0f, 1.0f), new AlphaModifier(0.25f, 1.0f, 0.0f), new AlphaModifier(f2, 0.0f, 0.0f))));
    }

    private void unloadTroopsButtonPressed() {
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        Game game2 = this.B;
        if (game2.colonies.getColonies(game2.getCurrentPlayer(), this.starSystem.getID()).size() > 1) {
            showSelectUnloadTroopsOverlay();
            return;
        }
        int i = 0;
        int i2 = 0;
        for (SystemObject systemObject : this.starSystem.getSystemObjects()) {
            if (systemObject.hasColony() && systemObject.getOccupier() == this.B.getCurrentPlayer()) {
                i = i2;
            }
            i2++;
        }
        showUnloadTroopsOverlay(i);
    }

    private void wormholePressed() {
        showMessage(new WormholeMessage(false));
        this.B.sounds.playSystemObjectPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void B(Point point) {
    }

    public void L(int i) {
        this.screen = this.B.eventsScene;
        setSpritesInvisible();
        Game game = this.B;
        if (game.events.getEvents(game.getCurrentPlayer()).size() > 0) {
            this.eventsButton.setVisible(true);
        }
        loadStarSystem(i);
    }

    protected void M(ExtendedScene extendedScene, Object obj) {
        if (extendedScene instanceof PlanetScene) {
            this.B.planetScene.loadPlanet(this.starSystem.getID(), ((Integer) obj).intValue(), this.B.systemScene);
        } else if (extendedScene instanceof GalaxyScene) {
            this.B.galaxyScene.setStarsAndNebulas();
        } else if (extendedScene instanceof EventsScene) {
            Game game = this.B;
            game.eventsScene.set(game.getCurrentPlayer());
        }
    }

    @Override // org.andengine.entity.scene.Scene
    public void back() {
        if (hasChildScene()) {
            this.B.keyboardOverlay.back();
            this.H.back();
        } else if (t()) {
        } else {
            changeScene(this.B.galaxyScene);
        }
    }

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
        this.bufferManager = vertexBufferObjectManager;
        createNebula();
        createIonStorm();
        createSun();
        createWormholes();
        createSystemObjects();
        createComet();
        createButtons();
        createSystemInfo();
        createFleetIcons();
        createOverlays();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void getPoolElements() {
        if (this.nebulas.hasParent()) {
            this.nebulas.detachSelf();
        }
        this.nebulaBackground.attachChild(this.nebulas);
        for (int i = 0; i < 5; i++) {
            PlanetSprite planetSprite = this.B.planetSpritePool.get();
            planetSprite.setMoonRange(ORBIT_SIZE, 180);
            planetSprite.setPosition((ORBIT_SIZE * i) + 135 + 106, 330.0f);
            planetSprite.setZIndex(1);
            attachChild(planetSprite);
            this.planetSprites.add(planetSprite);
        }
        sortChildren();
    }

    public void loadSystem(int i) {
        this.screen = this.B.galaxyScene;
        setSpritesInvisible();
        loadStarSystem(i);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void refresh() {
        loadSystem(this.starSystem.getID());
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void releasePoolElements(final ExtendedScene extendedScene, final Object obj) {
        this.B.getEngine().runOnUpdateThread(new Runnable() { // from class: com.birdshel.Uciana.Scenes.k0
            {
                SystemScene.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                SystemScene.this.lambda$releasePoolElements$0(extendedScene, obj);
            }
        });
    }

    public void setActionButtons() {
        int id = this.starSystem.getID();
        Empire currentEmpire = this.B.getCurrentEmpire();
        this.attackButton.setVisible(currentEmpire.canAttack(id));
        if (this.B.events.hasSelectAttackEvent(currentEmpire.id, id)) {
            this.attackButton.setAlpha(0.4f);
        }
        this.unloadTroopsButton.setVisible(false);
        this.unloadTroopsIcon.setVisible(false);
        Game game = this.B;
        if (game.fleets.isFleetInSystem(game.getCurrentPlayer(), this.starSystem.getID())) {
            Game game2 = this.B;
            if (game2.fleets.getFleetInSystem(game2.getCurrentPlayer(), this.starSystem.getID()).getTroopCount() > 0) {
                Game game3 = this.B;
                if (game3.colonies.hasColonyInSystem(game3.getCurrentPlayer(), this.starSystem.getID())) {
                    this.unloadTroopsButton.setVisible(true);
                    this.unloadTroopsIcon.setVisible(true);
                }
            }
        }
        this.attackButton.setX((getWidth() / 2.0f) - 60.0f);
        this.unloadTroopsButton.setX((getWidth() / 2.0f) - 60.0f);
        if (this.attackButton.isVisible() && this.unloadTroopsButton.isVisible()) {
            this.attackButton.setX((getWidth() / 2.0f) - 120.0f);
            this.unloadTroopsButton.setX(getWidth() / 2.0f);
        }
        float f2 = currentEmpire.isSystemBlockaded(this.starSystem.getID()) ? 86 : 50;
        this.attackButton.setY(f2);
        this.unloadTroopsButton.setY(f2);
        this.unloadTroopsIcon.setX(this.unloadTroopsButton.getX() + 10.0f);
        this.unloadTroopsIcon.setY(this.unloadTroopsButton.getY() - 7.0f);
    }

    public void setFleetIcons(int i) {
        C(this.fleets);
        C(this.shipCounts);
        List<Fleet> fleetsInSystem = this.B.fleets.getFleetsInSystem(i);
        float f2 = 70.0f;
        for (Fleet fleet : fleetsInSystem) {
            int indexOf = fleetsInSystem.indexOf(fleet);
            if (fleet.isVisible(this.B.getCurrentEmpire())) {
                Text text = (Text) this.shipCounts.get(indexOf);
                text.setX(f2);
                text.setText(Integer.toString(fleet.getSize()));
                text.setVisible(true);
                TiledSprite tiledSprite = (TiledSprite) this.fleets.get(indexOf);
                tiledSprite.setTiledTextureRegion((ITiledTextureRegion) this.B.graphics.shipsTextures[fleet.empireID]);
                tiledSprite.setCurrentTileIndex(fleet.getIcon());
                tiledSprite.setX(text.getX() + text.getWidthScaled() + 5.0f);
                tiledSprite.setVisible(true);
                this.empireIDsForFleets.set(indexOf, Integer.valueOf(fleet.empireID));
                f2 = tiledSprite.getX() + tiledSprite.getWidthScaled() + 25.0f;
            }
        }
    }

    public void showSelectUnloadTroopsOverlay() {
        ArrayList arrayList = new ArrayList();
        for (PlanetSprite planetSprite : this.planetSprites) {
            arrayList.add(planetSprite.getMoonSprite());
        }
        this.troopUnloadSelectOverlay.setOverlay(this.B.getCurrentPlayer(), this.starSystem.getID(), arrayList);
        setChildScene(this.troopUnloadSelectOverlay, false, false, true);
    }

    public void showUnloadTroopsOverlay(int i) {
        this.troopUnloadOverlay.setOverlay(this.starSystem.getID(), i);
        setChildScene(this.troopUnloadOverlay, false, false, true);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void switched() {
        showShortCuts();
        super.switched();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void update() {
        if (this.comet.isVisible()) {
            if (this.comet.getX() > -100.0f) {
                TiledSprite tiledSprite = this.comet;
                tiledSprite.setX(tiledSprite.getX() - 0.2f);
                this.starSystem.setCometPosition(new Point(this.comet.getX(), this.comet.getY()));
                return;
            }
            this.comet.setVisible(false);
        }
    }
}
