package com.birdshel.Uciana.Scenes;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.birdshel.Uciana.Colonies.Buildings.BuildingID;
import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Colonies.ManufacturingType;
import com.birdshel.Uciana.Controls.FoodProductionScienceControl;
import com.birdshel.Uciana.Elements.EmpireInfo;
import com.birdshel.Uciana.Elements.PlanetInfo;
import com.birdshel.Uciana.Elements.ProductionPercentBar;
import com.birdshel.Uciana.Events.Event;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Messages.ColonyInfo.PopulationMessage;
import com.birdshel.Uciana.Messages.ColonyInfo.ResistanceInfoMessage;
import com.birdshel.Uciana.Messages.MiningTechNeededMessage;
import com.birdshel.Uciana.Messages.PlanetDiscoveryMessage;
import com.birdshel.Uciana.Messages.PlanetExplorationMessage;
import com.birdshel.Uciana.Messages.PlanetInfo.ClimateInfoMessage;
import com.birdshel.Uciana.Messages.PlanetInfo.GravityInfoMessage;
import com.birdshel.Uciana.Messages.PlanetInfo.PlanetSizeInfoMessage;
import com.birdshel.Uciana.Messages.PlanetInfo.RichnessInfoMessage;
import com.birdshel.Uciana.Messages.ResourcesMessage;
import com.birdshel.Uciana.Messages.ScoutsAllUsedMessage;
import com.birdshel.Uciana.Messages.TerraformedMessage;
import com.birdshel.Uciana.Messages.Tutorials.ColonyViewTutorial;
import com.birdshel.Uciana.Messages.WarningMessage;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Overlays.MigrantListOverlay;
import com.birdshel.Uciana.Planets.AsteroidBelt;
import com.birdshel.Uciana.Planets.GasGiant;
import com.birdshel.Uciana.Planets.Moon;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.PlanetSprite;
import com.birdshel.Uciana.Planets.ResourceID;
import com.birdshel.Uciana.Planets.Resources;
import com.birdshel.Uciana.Planets.SystemObject;
import com.birdshel.Uciana.Planets.SystemObjectType;
import com.birdshel.Uciana.Players.Migrants;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Resources.Options;
import com.birdshel.Uciana.Resources.TutorialID;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.Ships.ShipComponents.WeaponStats;
import com.birdshel.Uciana.Ships.ShipType;
import com.birdshel.Uciana.StarSystems.Nebulas;
import com.birdshel.Uciana.StarSystems.StarSystem;
import com.birdshel.Uciana.StarSystems.SunSprite;
import com.birdshel.Uciana.Technology.TechID;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.andengine.entity.Entity;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.color.Color;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class PlanetScene extends ExtendedScene {
    private TiledSprite asteroidBeltSprite;
    private Sprite blackedColonyBackground;
    private TiledSprite blockadeWarning;
    private VertexBufferObjectManager bufferManager;
    private TiledSprite buildingProductionIcon;
    private TiledSprite buildingsButton;
    private Text buildingsCount;
    private Sprite buyNowBackground;
    private TiledSprite buyNowButton;
    private Sprite buyNowPress;
    private TiledSprite capitalIcon;
    private int coloniesListIndex;
    private TiledSprite colonizeButton;
    private TiledSprite colonizeButtonIcon;
    private Text colonizeText;
    private Colony colony;
    private TiledSprite colonyBackground;
    private TiledSprite creditsIcon;
    private Text creditsPerTurn;
    private final DecimalFormat decimalFormat;
    private Sprite defenseBackground;
    private Text defenseBonus;
    private TiledSprite defenseBonusIcon;
    private Text defenseBonusString;
    private TiledSprite defenseIcon;
    private TiledSprite empireBanner;
    private TiledSprite empireBannerBackground;
    private EmpireInfo empireInfo;
    private TiledSprite emptyColonyWarning;
    private TiledSprite eventsButton;
    private TiledSprite exploreButton;
    private TiledSprite exploreButtonIcon;
    private Text exploreText;
    private Text exploredText;
    private TiledSprite foodPerFarmerIcon;
    private Text foodPerFarmerString;
    private FoodProductionScienceControl fpsPercentControl;
    private TiledSprite galaxyButton;
    private Text gravityString;
    private Text happiness;
    private TiledSprite happinessIcon;
    private Text importedFoodPerTurn;
    private TiledSprite importedFoodPerTurnIcon;
    private Text infDivisionCount;
    private TiledSprite infIcon;
    private TiledSprite infoIcon;
    private TiledSprite listButton;
    private TiledSprite lowPowerWarning;
    private TiledSprite maintenanceIcon;
    private Text maintenanceString;
    private MigrantListOverlay migrantListOverlay;
    private TiledSprite migrantTurnIcon;
    private TiledSprite migrants;
    private TiledSprite movePeopleButton;
    private TiledSprite movePeopleButtonIcon;
    private TiledSprite movePeopleWarningIcon;
    private Entity nebulaBackground;
    private Nebulas nebulas;
    private Text netFoodPerTurn;
    private TiledSprite netFoodPerTurnIcon;
    private TiledSprite nextButton;
    private TiledSprite outpost;
    private Sprite outpostBackground;
    private Sprite outpostBackground2;
    private TiledSprite outpostButton;
    private TiledSprite outpostButtonIcon;
    private Text outpostText;
    private Text perMillion;
    private PlanetInfo planetInfo;
    private Text planetName;
    private PlanetSprite planetSprite;
    private TiledSprite popGrowthIcon;
    private Text popGrowthString;
    private TiledSprite popIcon;
    private Sprite populationBar;
    private Sprite populationBarEmpty;
    private Text populationPercentString;
    private Text populationString;
    private Text power;
    private TiledSprite powerIcon;
    private TiledSprite previousButton;
    private Sprite productionBackground;
    private Text productionBonusString;
    private Text productionName;
    private TiledSprite productionPerWorkerIcon;
    private Text productionPerWorkerString;
    private ProductionPercentBar productionPercentBar;
    private Sprite productionPress;
    private Text productionTurnsLeft;
    private TiledSprite renameIcon;
    private TiledSprite repeatIcon;
    private TiledSprite resistanceIcon;
    private TiledSprite resistancePercent;
    private Text resourcesText;
    private Text richnessString;
    private TiledSprite sciencePerScientistIcon;
    private Text sciencePerScientistString;
    private TiledSprite shipProductionIcon;
    private TiledSprite shipTypeProductionIcon;
    private TiledSprite shipYards;
    private Text sizeClimateString;
    private TiledSprite starBase;
    private StarSystem starSystem;
    private TiledSprite starvingWarning;
    private SunSprite sunSprite;
    private Sprite surface;
    private TiledSprite systemButton;
    private SystemObject systemObject;
    private Text turnsToMigrants;
    private TiledSprite turret1;
    private TiledSprite turret2;
    private boolean updateColony;
    private long updateTime;
    private ExtendedScene whereFrom;
    private final List<Entity> asteroids = new ArrayList();
    private final List<Entity> resourceIcons = new ArrayList();
    private List<Colony> colonies = new ArrayList();
    private List<Planet> planets = new ArrayList();

    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Scenes.PlanetScene$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a */
        static final /* synthetic */ int[] f1468a;

        static {
            int[] iArr = new int[SystemObjectType.values().length];
            f1468a = iArr;
            try {
                iArr[SystemObjectType.PLANET.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1468a[SystemObjectType.GAS_GIANT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1468a[SystemObjectType.ASTEROID_BELT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public PlanetScene(Game game) {
        DecimalFormat decimalFormat = new DecimalFormat();
        this.decimalFormat = decimalFormat;
        this.updateColony = false;
        this.B = game;
        decimalFormat.setMaximumFractionDigits(1);
    }

    private void buildingsButtonPressed() {
        changeScene(this.B.buildingsScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void buyNowPressed() {
        Game game = this.B;
        game.confirmOverlay.setOverlay(this.colony, game.planetScene, "buy");
        setChildScene(this.B.confirmOverlay, false, false, true);
        this.B.sounds.playBoxPressSound();
        Game game2 = this.B;
        game2.vibrate(game2.BUTTON_VIBRATE);
    }

    private void checkActionDown(Point point) {
        checkPressed(point);
    }

    private void checkActionMove(Point point) {
        this.productionPress.setVisible(false);
        this.buyNowPress.setVisible(false);
        checkPressed(point);
    }

    private void checkActionUp(Point point) {
        this.productionPress.setVisible(false);
        this.buyNowPress.setVisible(false);
        if (isClicked(this.galaxyButton, point)) {
            galaxyButtonPressed();
        }
        if (isClicked(this.systemButton, point)) {
            systemButtonPressed();
        }
        if (isClicked(this.listButton, point)) {
            listButtonPressed();
        }
        if (isClicked(this.previousButton, point)) {
            previousButtonPressed();
        }
        if (isClicked(this.nextButton, point)) {
            nextButtonPressed();
        }
        if (isClicked(this.eventsButton, point)) {
            eventsButtonPressed();
        }
        if (A(this.outpostButton, point, 0.0f, 0.0f)) {
            outpostButtonPressed();
        } else if (isClicked(this.colonizeButton, point)) {
            colonizeButtonPressed();
        } else if (A(this.exploreButton, point, 0.0f, 0.0f)) {
            exploreButtonPressed();
        } else {
            if (isClicked(this.buildingsButton, point)) {
                buildingsButtonPressed();
            }
            if (isClicked(this.buyNowBackground, point) && this.buyNowButton.isVisible()) {
                buyNowPressed();
            }
            if (isClicked(this.productionBackground, point)) {
                productionPressed();
            }
            if (isClicked(this.populationBar, point) && this.colony.getEmpireID() == this.B.getCurrentPlayer()) {
                populationInfoPressed();
                return;
            }
            if (isClicked(this.migrants, point) || y(this.turnsToMigrants, point) || isClicked(this.migrantTurnIcon, point)) {
                migrantInfoPressed();
            }
            for (Entity entity : this.resourceIcons) {
                if (isClicked(entity, point)) {
                    resourcesPressed();
                }
            }
            if (isClicked(this.creditsIcon, point) || y(this.creditsPerTurn, point)) {
                moneyInfoPressed();
            }
            if (isClicked(this.netFoodPerTurnIcon, point) || y(this.netFoodPerTurn, point) || isClicked(this.importedFoodPerTurnIcon, point) || y(this.importedFoodPerTurn, point)) {
                foodInfoPressed();
            }
            if (isClicked(this.happinessIcon, point) || y(this.happiness, point)) {
                happinessInfoPressed();
            }
            if (isClicked(this.powerIcon, point) || y(this.power, point)) {
                powerInfoPressed();
            }
            if (x(this.infoIcon, point, 10.0f, 10.0f)) {
                specialInfoPressed();
            }
            if (isClicked(this.movePeopleButton, point)) {
                movePeopleButtonPressed();
            }
            if (isClicked(this.resistanceIcon, point) || isClicked(this.resistancePercent, point)) {
                resistancePressed();
            }
            if (isClicked(this.starvingWarning, point)) {
                warningPressed(this.starvingWarning);
            }
            if (isClicked(this.blockadeWarning, point)) {
                warningPressed(this.blockadeWarning);
            }
            if (isClicked(this.lowPowerWarning, point)) {
                warningPressed(this.lowPowerWarning);
            }
            if (isClicked(this.emptyColonyWarning, point)) {
                warningPressed(this.emptyColonyWarning);
            }
            if (isPressed(point, this.planetInfo.getSizeSprite(), new Point(this.planetInfo.getX(), this.planetInfo.getY()))) {
                planetSizePressed();
            }
            if (isPressed(point, this.planetInfo.getClimateSprite(), new Point(this.planetInfo.getX(), this.planetInfo.getY()))) {
                climatePressed();
            }
            if (isPressed(point, this.planetInfo.getRichnessSprite(), new Point(this.planetInfo.getX(), this.planetInfo.getY()))) {
                richnessPressed();
            }
            if (isPressed(point, this.planetInfo.getGravitySprite(), new Point(this.planetInfo.getX(), this.planetInfo.getY()))) {
                gravityPressed();
            }
            if (this.systemObject.hasColony() && this.B.getCurrentPlayer() == this.systemObject.getOccupier()) {
                if (y(this.planetName, point) || isClicked(this.renameIcon, point)) {
                    planetNamePressed();
                }
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [boolean, int] */
    private void checkForWarnings() {
        this.starvingWarning.setVisible(false);
        this.blockadeWarning.setVisible(false);
        this.lowPowerWarning.setVisible(false);
        this.emptyColonyWarning.setVisible(false);
        ?? isBlockaded = this.colony.isBlockaded();
        int i = isBlockaded;
        if (this.colony.isStarving()) {
            i = isBlockaded + 1;
        }
        int i2 = i;
        if (this.colony.isLowPower()) {
            i2 = i + 1;
        }
        int i3 = i2;
        if (this.colony.getPopulation() == 0) {
            i3 = i2 + 1;
        }
        float f2 = 360 - (i3 * 20);
        if (this.colony.isStarving()) {
            this.starvingWarning.setVisible(true);
            this.starvingWarning.setX(f2);
            f2 += 40.0f;
        }
        if (this.colony.isBlockaded()) {
            this.blockadeWarning.setVisible(true);
            this.blockadeWarning.setX(f2);
            f2 += 40.0f;
        }
        if (this.colony.isLowPower()) {
            this.lowPowerWarning.setVisible(true);
            this.lowPowerWarning.setX(f2);
            f2 += 40.0f;
        }
        if (this.colony.getPopulation() == 0) {
            this.emptyColonyWarning.setVisible(true);
            this.emptyColonyWarning.setX(f2);
        }
    }

    private void checkPressed(Point point) {
        if (isClicked(this.productionBackground, point)) {
            this.productionPress.setVisible(true);
        }
        if (isClicked(this.buyNowBackground, point) && this.buyNowButton.isVisible()) {
            this.buyNowPress.setVisible(true);
        }
    }

    private void climatePressed() {
        Planet planet = (Planet) this.systemObject;
        showMessage(new ClimateInfoMessage(planet.getClimate(), planet.isTerraformed()));
        this.B.sounds.playSystemObjectPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void colonizeButtonPressed() {
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        this.whereFrom = this;
        Game game2 = this.B;
        game2.colonies.add(game2.getCurrentPlayer(), this.systemObject.getSystemID(), this.systemObject.getOrbit(), this);
    }

    private void createAsteroidBelt() {
        Object obj;
        this.asteroidBeltSprite = J(0.0f, 0.0f, this.B.graphics.asteroidBeltsTexture, this.bufferManager, false);
        int i = 0;
        for (int i2 = 0; i2 < 10; i2++) {
            TiledSprite H = H(0.0f, 0.0f, this.B.graphics.gameIconsTexture, this.bufferManager, GameIconEnum.ASTEROID1.ordinal() + i, false);
            i++;
            if (i == 7) {
                i = 0;
            }
            H.setScale((Functions.random.nextFloat() * 0.099999994f) + 0.25f);
            float nextFloat = (Functions.random.nextFloat() * 10.0f) + 15.0f;
            int nextInt = Functions.random.nextInt(60) - 65;
            float f2 = nextInt - 100;
            float f3 = nextInt + WeaponStats.NOVA_BOMB_MAX_DAMAGE;
            float f4 = nextInt + 600;
            float f5 = nextInt + 450;
            MoveModifier moveModifier = new MoveModifier(nextFloat, f2, f3, f4, f5);
            float f6 = nextInt + 300;
            MoveModifier moveModifier2 = new MoveModifier(nextFloat, f3, f6, f5, f6);
            MoveModifier moveModifier3 = new MoveModifier(nextFloat, f6, f5, f6, f3);
            float f7 = nextInt + 560;
            MoveModifier moveModifier4 = new MoveModifier(nextFloat, f5, f7, f3, f2);
            MoveModifier moveModifier5 = new MoveModifier(nextFloat, f7, f5, f2, f3);
            MoveModifier moveModifier6 = new MoveModifier(nextFloat, f5, f6, f3, f6);
            MoveModifier moveModifier7 = new MoveModifier(nextFloat, f6, f3, f6, f5);
            MoveModifier moveModifier8 = new MoveModifier(nextFloat, f3, f2, f5, f4);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SequenceEntityModifier(moveModifier, moveModifier2, moveModifier3, moveModifier4, moveModifier5, moveModifier6, moveModifier7, moveModifier8));
            arrayList.add(new SequenceEntityModifier(moveModifier2, moveModifier3, moveModifier4, moveModifier5, moveModifier6, moveModifier7, moveModifier8, moveModifier));
            arrayList.add(new SequenceEntityModifier(moveModifier3, moveModifier4, moveModifier5, moveModifier6, moveModifier7, moveModifier8, moveModifier, moveModifier2));
            arrayList.add(new SequenceEntityModifier(moveModifier4, moveModifier5, moveModifier6, moveModifier7, moveModifier8, moveModifier, moveModifier2, moveModifier3));
            arrayList.add(new SequenceEntityModifier(moveModifier5, moveModifier6, moveModifier7, moveModifier8, moveModifier, moveModifier2, moveModifier3, moveModifier4));
            arrayList.add(new SequenceEntityModifier(moveModifier6, moveModifier7, moveModifier8, moveModifier, moveModifier2, moveModifier3, moveModifier4, moveModifier5));
            arrayList.add(new SequenceEntityModifier(moveModifier7, moveModifier8, moveModifier, moveModifier2, moveModifier3, moveModifier4, moveModifier5, moveModifier6));
            arrayList.add(new SequenceEntityModifier(moveModifier8, moveModifier, moveModifier2, moveModifier3, moveModifier4, moveModifier5, moveModifier6, moveModifier7));
            if (i2 >= arrayList.size()) {
                obj = arrayList.get(Functions.random.nextInt(arrayList.size()));
            } else {
                obj = arrayList.get(i2);
            }
            H.registerEntityModifier(new LoopEntityModifier((SequenceEntityModifier) obj));
            this.asteroids.add(H);
        }
    }

    private void createButtons() {
        this.G = H(0.0f, 0.0f, this.B.graphics.buttonsTexture, this.bufferManager, ButtonsEnum.PRESSED.ordinal(), false);
        TiledSprite H = H(getWidth() - 120.0f, 0.0f, this.B.graphics.buttonsTexture, this.bufferManager, ButtonsEnum.GALAXY.ordinal(), true);
        this.galaxyButton = H;
        s(H);
        TiledSprite H2 = H(getWidth() - 240.0f, 0.0f, this.B.graphics.buttonsTexture, this.bufferManager, ButtonsEnum.SYSTEM.ordinal(), true);
        this.systemButton = H2;
        s(H2);
        TiledSprite H3 = H(getWidth() - 360.0f, 0.0f, this.B.graphics.buttonsTexture, this.bufferManager, ButtonsEnum.EVENTS.ordinal(), true);
        this.eventsButton = H3;
        s(H3);
        TiledSprite H4 = H(getWidth() - 360.0f, 0.0f, this.B.graphics.buttonsTexture, this.bufferManager, ButtonsEnum.COLONIES.ordinal(), true);
        this.listButton = H4;
        s(H4);
        TiledSprite H5 = H(0.0f, 0.0f, this.B.graphics.buttonsTexture, this.bufferManager, ButtonsEnum.PREVIOUS.ordinal(), true);
        this.previousButton = H5;
        s(H5);
        TiledSprite H6 = H(115.0f, 0.0f, this.B.graphics.buttonsTexture, this.bufferManager, ButtonsEnum.NEXT.ordinal(), true);
        this.nextButton = H6;
        s(H6);
        TiledSprite H7 = H(getWidth() - 120.0f, 142.0f, this.B.graphics.buttonsTexture, this.bufferManager, ButtonsEnum.BUILDINGS.ordinal(), true);
        this.buildingsButton = H7;
        s(H7);
        this.buildingsCount = F(0.0f, 0.0f, this.B.fonts.smallInfoFont, "####", this.E, this.bufferManager);
        ITiledTextureRegion iTiledTextureRegion = this.B.graphics.buttonsTexture;
        VertexBufferObjectManager vertexBufferObjectManager = this.bufferManager;
        ButtonsEnum buttonsEnum = ButtonsEnum.BLANK_BLUE;
        this.outpostButton = H(580.0f, 600.0f, iTiledTextureRegion, vertexBufferObjectManager, buttonsEnum.ordinal(), false);
        TiledSprite H8 = H(580.0f, 620.0f, this.B.graphics.infoIconsTexture, this.bufferManager, InfoIconEnum.WORKER_ICON.ordinal(), false);
        this.outpostButtonIcon = H8;
        H8.setSize(45.0f, 45.0f);
        s(this.outpostButton);
        Game game = this.B;
        this.outpostText = G(0.0f, 686.0f, game.fonts.infoFont, game.getActivity().getString(R.string.planet_construct_outpost), this.E, this.bufferManager, false);
        this.colonizeButton = H(580.0f, 600.0f, this.B.graphics.buttonsTexture, this.bufferManager, buttonsEnum.ordinal(), false);
        TiledSprite H9 = H(580.0f, 620.0f, this.B.graphics.infoIconsTexture, this.bufferManager, InfoIconEnum.COLONY_ICON.ordinal(), false);
        this.colonizeButtonIcon = H9;
        H9.setSize(45.0f, 45.0f);
        s(this.colonizeButton);
        Game game2 = this.B;
        this.colonizeText = G(0.0f, 686.0f, game2.fonts.infoFont, game2.getActivity().getString(R.string.planet_colonize_planet), this.E, this.bufferManager, false);
        this.exploreButton = H(580.0f, 600.0f, this.B.graphics.buttonsTexture, this.bufferManager, buttonsEnum.ordinal(), false);
        TiledSprite H10 = H(580.0f, 620.0f, this.B.graphics.infoIconsTexture, this.bufferManager, InfoIconEnum.SCOUT_ICON.ordinal(), false);
        this.exploreButtonIcon = H10;
        H10.setSize(45.0f, 45.0f);
        s(this.exploreButton);
        Game game3 = this.B;
        this.exploreText = G(0.0f, 686.0f, game3.fonts.infoFont, game3.getActivity().getString(R.string.planet_explore_planet), this.E, this.bufferManager, false);
        TiledSprite H11 = H(getWidth() - 735.0f, 634.0f, this.B.graphics.buttonsTexture, this.bufferManager, ButtonsEnum.BLANK.ordinal(), false);
        this.movePeopleButton = H11;
        s(H11);
        this.movePeopleButtonIcon = H(getWidth() - 725.0f, 627.0f, this.B.graphics.gameIconsTexture, this.bufferManager, GameIconEnum.MOVE_PEOPLE.ordinal(), false);
        TiledSprite H12 = H(getWidth() - 735.0f, 634.0f, this.B.graphics.infoIconsTexture, this.bufferManager, InfoIconEnum.BLOCKADE.ordinal(), false);
        this.movePeopleWarningIcon = H12;
        blinkSprite(H12);
    }

    private void createColony() {
        TiledSprite J = J(getWidth() - 615.0f, 145.0f, this.B.graphics.empireColors, this.bufferManager, false);
        this.colonyBackground = J;
        J.setAlpha(0.6f);
        this.colonyBackground.setSize(615.0f, 575.0f);
        Sprite E = E(getWidth() - 615.0f, 145.0f, this.B.graphics.blackenedBackgroundTexture, this.bufferManager, false);
        this.blackedColonyBackground = E;
        E.setSize(615.0f, 575.0f);
        this.blackedColonyBackground.setAlpha(0.6f);
        Sprite E2 = E(getWidth() - 615.0f, 410.0f, this.B.graphics.selectColonyTexture, this.bufferManager, false);
        this.productionPress = E2;
        E2.setSize(495.0f, 100.0f);
        Sprite E3 = E(getWidth() - 120.0f, 410.0f, this.B.graphics.selectColonyTexture, this.bufferManager, false);
        this.buyNowPress = E3;
        E3.setSize(120.0f, 100.0f);
        Sprite E4 = E(getWidth() - 615.0f, 410.0f, this.B.graphics.productionButton, this.bufferManager, false);
        this.productionBackground = E4;
        E4.setSize(495.0f, 100.0f);
        Sprite E5 = E(getWidth() - 120.0f, 410.0f, this.B.graphics.productionButton, this.bufferManager, false);
        this.buyNowBackground = E5;
        E5.setSize(120.0f, 100.0f);
        Sprite E6 = E(getWidth() - 562.0f, 225.0f, this.B.graphics.planetSurfaceTexture, this.bufferManager, false);
        this.surface = E6;
        E6.setSize(510.0f, 135.0f);
    }

    private void createColonyStats() {
        this.creditsPerTurn = F(0.0f, 370.0f, this.B.fonts.infoFont, this.D, this.E, this.bufferManager);
        this.creditsIcon = H(0.0f, 370.0f, this.B.graphics.infoIconsTexture, this.bufferManager, InfoIconEnum.CREDITS.ordinal(), false);
        this.netFoodPerTurn = F(0.0f, 370.0f, this.B.fonts.infoFont, this.D, this.E, this.bufferManager);
        this.netFoodPerTurnIcon = H(0.0f, 370.0f, this.B.graphics.infoIconsTexture, this.bufferManager, InfoIconEnum.FOOD.ordinal(), false);
        this.importedFoodPerTurn = F(0.0f, 370.0f, this.B.fonts.infoFont, this.D, this.E, this.bufferManager);
        this.importedFoodPerTurnIcon = H(0.0f, 370.0f, this.B.graphics.infoIconsTexture, this.bufferManager, InfoIconEnum.IMPORTED_FOOD.ordinal(), false);
        this.happiness = F(0.0f, 370.0f, this.B.fonts.infoFont, this.D, this.E, this.bufferManager);
        this.happinessIcon = H(0.0f, 370.0f, this.B.graphics.infoIconsTexture, this.bufferManager, InfoIconEnum.HAPPINESS.ordinal(), false);
        this.power = F(0.0f, 370.0f, this.B.fonts.infoFont, this.D, this.E, this.bufferManager);
        this.powerIcon = H(0.0f, 370.0f, this.B.graphics.infoIconsTexture, this.bufferManager, InfoIconEnum.POWER.ordinal(), false);
    }

    private void createFoodProductionScienceSlider() {
        FoodProductionScienceControl foodProductionScienceControl = new FoodProductionScienceControl(this.B, this.bufferManager);
        this.fpsPercentControl = foodProductionScienceControl;
        foodProductionScienceControl.setPosition(getWidth() - 587.0f, 520.0f);
        attachChild(this.fpsPercentControl);
    }

    private void createIncomingMigrantsInfo() {
        this.migrants = H(0.0f, 545.0f, this.B.graphics.gameIconsTexture, this.bufferManager, GameIconEnum.INCOMING_POPULATION.ordinal(), false);
        this.turnsToMigrants = G(0.0f, 585.0f, this.B.fonts.smallFont, this.D, this.E, this.bufferManager, false);
        this.migrantTurnIcon = H(0.0f, 580.0f, this.B.graphics.infoIconsTexture, this.bufferManager, InfoIconEnum.TURN.ordinal(), false);
    }

    private void createModifierStats() {
        this.popGrowthString = F(0.0f, 480.0f, this.B.fonts.smallInfoFont, this.D, this.E, this.bufferManager);
        this.popGrowthIcon = H(0.0f, 480.0f, this.B.graphics.infoIconsTexture, this.bufferManager, InfoIconEnum.POP_GROWTH.ordinal(), true);
        this.defenseBonusString = F(0.0f, 520.0f, this.B.fonts.smallInfoFont, this.D, this.E, this.bufferManager);
        this.defenseBonusIcon = H(0.0f, 520.0f, this.B.graphics.infoIconsTexture, this.bufferManager, InfoIconEnum.DEFENSE.ordinal(), true);
        this.maintenanceString = F(0.0f, 560.0f, this.B.fonts.smallInfoFont, this.D, this.E, this.bufferManager);
        this.maintenanceIcon = H(0.0f, 560.0f, this.B.graphics.infoIconsTexture, this.bufferManager, InfoIconEnum.MAINTENANCE_COST.ordinal(), true);
    }

    private void createNebula() {
        this.nebulas = this.B.nebulas;
        Entity entity = new Entity();
        this.nebulaBackground = entity;
        attachChild(entity);
    }

    private void createOutpost() {
        Sprite E = E(0.0f, 470.0f, this.B.graphics.blackenedBackgroundTexture, this.bufferManager, false);
        this.outpostBackground2 = E;
        E.setSize(0.0f, 80.0f);
        Sprite E2 = E(0.0f, 470.0f, this.B.graphics.colonyBackgroundTexture, this.bufferManager, false);
        this.outpostBackground = E2;
        E2.setSize(0.0f, 80.0f);
        this.outpost = H(560.0f, 225.0f, this.B.graphics.gameIconsTexture, this.bufferManager, GameIconEnum.OUTPOST.ordinal(), false);
    }

    private void createOverlays() {
        this.migrantListOverlay = new MigrantListOverlay(this.B, this.bufferManager, false);
        this.H = new MessageOverlay(this.B, this.bufferManager);
    }

    private void createPlanetDefenseInfo() {
        Sprite E = E(getWidth() - 615.0f, 680.0f, this.B.graphics.blackenedBackgroundTexture, this.bufferManager, false);
        this.defenseBackground = E;
        E.setSize(615.0f, 40.0f);
        this.defenseBackground.setAlpha(0.6f);
        this.defenseBonus = F(getWidth() - 610.0f, 688.0f, this.B.fonts.infoFont, this.D, this.E, this.bufferManager);
        this.defenseIcon = H(getWidth() - 610.0f, 685.0f, this.B.graphics.infoIconsTexture, this.bufferManager, InfoIconEnum.DEFENSE.ordinal(), false);
        this.infDivisionCount = F(getWidth() - 510.0f, 688.0f, this.B.fonts.infoFont, this.D, this.E, this.bufferManager);
        this.infIcon = H(getWidth() - 510.0f, 685.0f, this.B.graphics.infoIconsTexture, this.bufferManager, InfoIconEnum.INFANTRY.ordinal(), false);
    }

    private void createPlanetInfo() {
        PlanetInfo planetInfo = new PlanetInfo(this.B, this.bufferManager);
        this.planetInfo = planetInfo;
        attachChild(planetInfo);
        this.exploredText = G(0.0f, 0.0f, this.B.fonts.infoFont, this.D, this.E, this.bufferManager, false);
        for (int i = 0; i < 4; i++) {
            TiledSprite I = I(0.0f, 0.0f, this.B.graphics.resourceIconsTexture, this.bufferManager, 0, false, 0.9f);
            I.setSize(50.0f, 50.0f);
            this.resourceIcons.add(I);
        }
        this.foodPerFarmerIcon = H(0.0f, 0.0f, this.B.graphics.infoIconsTexture, this.bufferManager, InfoIconEnum.FOOD.ordinal(), false);
        this.productionPerWorkerIcon = H(0.0f, 0.0f, this.B.graphics.infoIconsTexture, this.bufferManager, InfoIconEnum.PRODUCTION.ordinal(), false);
        this.sciencePerScientistIcon = H(0.0f, 0.0f, this.B.graphics.infoIconsTexture, this.bufferManager, InfoIconEnum.SCIENCE.ordinal(), false);
    }

    private void createPopulationBar() {
        this.populationBar = E(0.0f, 630.0f, this.B.graphics.popTexture, this.bufferManager, false);
        this.populationBarEmpty = E(0.0f, 630.0f, this.B.graphics.popEmptyTexture, this.bufferManager, false);
        this.populationPercentString = F(0.0f, 0.0f, this.B.fonts.infoFont, this.D, this.E, this.bufferManager);
        this.popIcon = H(0.0f, 630.0f, this.B.graphics.infoIconsTexture, this.bufferManager, InfoIconEnum.POPULATION.ordinal(), false);
    }

    private void createProductionInfo() {
        this.shipProductionIcon = H(getWidth() - 615.0f, 410.0f, this.B.graphics.shipsTextures[0], this.bufferManager, 0, false);
        this.shipTypeProductionIcon = J(getWidth() - 615.0f, 410.0f, this.B.graphics.infoIconsTexture, this.bufferManager, false);
        this.buildingProductionIcon = H(getWidth() - 615.0f, 410.0f, this.B.graphics.gameIconsTexture, this.bufferManager, 0, false);
        this.buyNowButton = H(getWidth() - 110.0f, 410.0f, this.B.graphics.gameIconsTexture, this.bufferManager, GameIconEnum.BUY_PRODUCTION.ordinal(), false);
        Text G = G(getWidth() - 515.0f, 421.0f, this.B.fonts.infoFont, this.D, this.E, this.bufferManager, false);
        this.productionName = G;
        G.setScale(0.75f);
        Text G2 = G(getWidth() - 515.0f, 469.0f, this.B.fonts.infoFont, this.D, this.E, this.bufferManager, false);
        this.productionTurnsLeft = G2;
        G2.setScale(0.75f);
        this.repeatIcon = H(getWidth() - 160.0f, 420.0f, this.B.graphics.infoIconsTexture, this.bufferManager, InfoIconEnum.REPEAT.ordinal(), false);
        ProductionPercentBar productionPercentBar = new ProductionPercentBar(this.B, this.bufferManager, 380.0f);
        this.productionPercentBar = productionPercentBar;
        productionPercentBar.setPosition(getWidth() - 510.0f, 455.0f);
        attachChild(this.productionPercentBar);
    }

    private void createResistanceIndicator() {
        this.resistanceIcon = H(0.0f, 0.0f, this.B.graphics.infoIconsTexture, this.bufferManager, InfoIconEnum.RESISTANCE.ordinal(), false);
        TiledSprite H = H(0.0f, 0.0f, this.B.graphics.empireColors, this.bufferManager, 0, false);
        this.resistancePercent = H;
        H.setWidth(10.0f);
    }

    private void createShipYards() {
        TiledSprite H = H(545.0f, 225.0f, this.B.graphics.gameIconsTexture, this.bufferManager, GameIconEnum.SHIP_YARDS.ordinal(), false);
        this.shipYards = H;
        H.setSize(100.0f, 100.0f);
    }

    private void createStarBase() {
        TiledSprite H = H(545.0f, 450.0f, this.B.graphics.gameIconsTexture, this.bufferManager, GameIconEnum.STAR_BASE.ordinal(), false);
        this.starBase = H;
        H.setSize(100.0f, 100.0f);
    }

    private void createSun() {
        SunSprite sunSprite = new SunSprite(this.B, this.bufferManager, false);
        this.sunSprite = sunSprite;
        attachChild(sunSprite);
    }

    private void createSystemObjectName() {
        TiledSprite J = J(getWidth() - 690.0f, 390.0f, this.B.graphics.empireColors, this.bufferManager, false);
        this.empireBannerBackground = J;
        J.setSize(100.0f, 75.0f);
        this.empireBanner = H(getWidth() - 615.0f, 142.0f, this.B.graphics.gameIconsTexture, this.bufferManager, 0, false);
        TiledSprite H = H(getWidth() - 705.0f, 90.0f, this.B.graphics.infoIconsTexture, this.bufferManager, InfoIconEnum.CAPITAL.ordinal(), false);
        this.capitalIcon = H;
        H.setSize(50.0f, 50.0f);
        this.renameIcon = H(0.0f, 0.0f, this.B.graphics.infoIconsTexture, this.bufferManager, InfoIconEnum.RENAME.ordinal(), false);
        TiledSprite H2 = H(getWidth() - 660.0f, 50.0f, this.B.graphics.infoIconsTexture, this.bufferManager, InfoIconEnum.INFO.ordinal(), false);
        this.infoIcon = H2;
        H2.setSize(40.0f, 40.0f);
    }

    private void createSystemObjectText() {
        this.planetName = F(0.0f, 0.0f, this.B.fonts.infoFont, this.D, this.E, this.bufferManager);
        this.populationString = F(0.0f, 0.0f, this.B.fonts.infoFont, this.D, this.E, this.bufferManager);
        this.foodPerFarmerString = F(0.0f, 360.0f, this.B.fonts.infoFont, this.D, this.E, this.bufferManager);
        this.productionPerWorkerString = F(0.0f, 400.0f, this.B.fonts.infoFont, this.D, this.E, this.bufferManager);
        this.sciencePerScientistString = F(0.0f, 440.0f, this.B.fonts.infoFont, this.D, this.E, this.bufferManager);
        Game game = this.B;
        this.perMillion = F(0.0f, 0.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.planet_per_1m), this.E, this.bufferManager);
        this.sizeClimateString = G(getWidth() - 520.0f, 270.0f, this.B.fonts.infoFont, this.D, this.E, this.bufferManager, false);
        this.richnessString = G(getWidth() - 520.0f, 310.0f, this.B.fonts.infoFont, this.D, this.E, this.bufferManager, false);
        this.gravityString = G(getWidth() - 520.0f, 350.0f, this.B.fonts.infoFont, this.D, this.E, this.bufferManager, false);
        this.resourcesText = G(getWidth() - 520.0f, 555.0f, this.B.fonts.smallInfoFont, this.D, this.E, this.bufferManager, false);
        this.productionBonusString = G(425.0f, 485.0f, this.B.fonts.smallFont, this.D, new TextOptions(AutoWrap.WORDS, 1200.0f, HorizontalAlign.CENTER), this.bufferManager, false);
    }

    private void createTorpedoTurrets() {
        TiledTextureRegion tiledTextureRegion = this.B.graphics.gameIconsTexture;
        VertexBufferObjectManager vertexBufferObjectManager = this.bufferManager;
        GameIconEnum gameIconEnum = GameIconEnum.TORPEDO_TURRET;
        TiledSprite H = H(565.0f, 395.0f, tiledTextureRegion, vertexBufferObjectManager, gameIconEnum.ordinal(), false);
        this.turret1 = H;
        H.setSize(50.0f, 50.0f);
        TiledSprite H2 = H(525.0f, 525.0f, this.B.graphics.gameIconsTexture, this.bufferManager, gameIconEnum.ordinal(), false);
        this.turret2 = H2;
        H2.setSize(50.0f, 50.0f);
    }

    private void createWarningIcons() {
        TiledSprite H = H(0.0f, 100.0f, this.B.graphics.infoIconsTexture, this.bufferManager, InfoIconEnum.STARVING.ordinal(), true);
        this.starvingWarning = H;
        H.setSize(40.0f, 40.0f);
        blinkSprite(this.starvingWarning);
        TiledSprite H2 = H(0.0f, 100.0f, this.B.graphics.infoIconsTexture, this.bufferManager, InfoIconEnum.BLOCKADE.ordinal(), true);
        this.blockadeWarning = H2;
        H2.setSize(40.0f, 40.0f);
        blinkSprite(this.blockadeWarning);
        TiledSprite H3 = H(0.0f, 100.0f, this.B.graphics.infoIconsTexture, this.bufferManager, InfoIconEnum.POWER_WARNING.ordinal(), true);
        this.lowPowerWarning = H3;
        H3.setSize(40.0f, 40.0f);
        blinkSprite(this.lowPowerWarning);
        TiledSprite H4 = H(0.0f, 100.0f, this.B.graphics.infoIconsTexture, this.bufferManager, InfoIconEnum.EMPTY_COLONY_WARNING.ordinal(), true);
        this.emptyColonyWarning = H4;
        H4.setSize(40.0f, 40.0f);
        blinkSprite(this.emptyColonyWarning);
    }

    private void eventsButtonPressed() {
        changeScene(this.B.eventsScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void exploreButtonPressed() {
        if (this.exploreButton.getAlpha() == 1.0f) {
            this.B.sounds.playButtonPressSound();
            Game game = this.B;
            game.vibrate(game.BUTTON_VIBRATE);
            Game game2 = this.B;
            Fleet fleetInSystem = game2.fleets.getFleetInSystem(game2.getCurrentPlayer(), this.systemObject.getSystemID());
            ((Planet) this.systemObject).getExplorationFind().addFindBonusToEmpire(fleetInSystem.empireID, this.systemObject.getSystemID(), this.systemObject.getOrbit(), fleetInSystem, fleetInSystem.setScoutUsed());
            showExploreMessage(this.B.getActivity().getString(R.string.exploration_explorers));
            this.systemObject.beenExplored(fleetInSystem.empireID);
            refresh();
            return;
        }
        showMessage(new ScoutsAllUsedMessage());
    }

    private void foodInfoPressed() {
    }

    private void galaxyButtonPressed() {
        changeScene(this.B.galaxyScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void gravityPressed() {
        Planet planet = (Planet) this.systemObject;
        showMessage(new GravityInfoMessage(planet.getGravity(), planet.hasAdjustedGravity()));
        this.B.sounds.playSystemObjectPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void happinessInfoPressed() {
    }

    private boolean isPressed(Point point, Sprite sprite, Point point2) {
        return sprite.isVisible() && point.getX() > point2.getX() + sprite.getX() && point.getX() < (point2.getX() + sprite.getX()) + sprite.getWidthScaled() && point.getY() > point2.getY() + sprite.getY() && point.getY() < (point2.getY() + sprite.getY()) + sprite.getHeightScaled();
    }

    public /* synthetic */ void lambda$releasePoolElements$0(ExtendedScene extendedScene, Object obj) {
        this.nebulaBackground.detachChild(this.nebulas);
        this.nebulaBackground.detachChild(this.planetSprite);
        this.B.planetSpritePool.push(this.planetSprite);
        extendedScene.getPoolElements();
        N(extendedScene, obj);
        this.B.setCurrentScene(extendedScene);
    }

    private void listButtonPressed() {
        changeScene(this.B.coloniesScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void loadColony() {
        Planet planet = (Planet) this.systemObject;
        this.colony = this.B.colonies.getColony(planet.getSystemID(), planet.getOrbit());
        this.colonyBackground.setVisible(true);
        this.colonyBackground.setCurrentTileIndex(this.colony.getEmpireID());
        this.colonyBackground.setHeight(300.0f);
        this.blackedColonyBackground.setVisible(true);
        this.blackedColonyBackground.setHeight(300.0f);
        Game game = this.B;
        ITextureRegion surfaceTexture = game.graphics.setSurfaceTexture("Surfaces/" + planet.getClimate().getID() + ".png", game.getActivity());
        this.surface.setVisible(true);
        this.surface.setTiledTextureRegion(surfaceTexture);
        setPlanetInfoForColony(planet);
        this.empireBanner.setVisible(true);
        this.empireBanner.setPosition(getWidth() - 615.0f, 132.0f);
        this.empireBanner.setCurrentTileIndex(GameIconEnum.getEmpireBanner(this.colony.getEmpireID()));
        this.planetName.setText(this.colony.getName());
        this.planetName.setPosition((getWidth() - (this.colony.getResistanceCount() != 0 ? 358 : 308)) - (this.planetName.getWidth() / 2.0f), 175.0f);
        if (this.colony.hasBuilding(BuildingID.CAPITAL)) {
            this.capitalIcon.setVisible(true);
            this.capitalIcon.setPosition((this.planetName.getX() - this.capitalIcon.getWidthScaled()) + 4.0f, this.planetName.getY() - 13.0f);
        }
        this.renameIcon.setVisible(this.colony.getEmpireID() == this.B.getCurrentPlayer());
        this.renameIcon.setPosition(this.planetName.getX() + this.planetName.getWidthScaled() + 10.0f, this.planetName.getY());
        if (this.colony.hasBuilding(BuildingID.SPACE_DOCK)) {
            this.outpost.setVisible(true);
            this.outpost.setCurrentTileIndex(GameIconEnum.OUTPOST.ordinal());
            this.outpost.setSize(100.0f, 100.0f);
            this.outpost.setX(30.0f);
        }
        if (this.colony.hasBuilding(BuildingID.STAR_BASE)) {
            this.starBase.setVisible(true);
        }
        if (this.colony.hasBuilding(BuildingID.TORPEDO_TURRET)) {
            this.turret1.setVisible(true);
            this.turret2.setVisible(true);
        }
        if (this.colony.hasBuilding(BuildingID.SHIP_YARDS)) {
            this.shipYards.setVisible(true);
        }
        if (this.colony.getEmpireID() == this.B.getCurrentPlayer()) {
            setColonyForCurrentPlayer();
        }
        Game game2 = this.B;
        if (game2.fleets.isFleetInSystem(game2.getCurrentPlayer(), this.systemObject.getSystemID())) {
            setExploreButton();
        }
    }

    private void migrantInfoPressed() {
        this.migrantListOverlay.setOverlay(this.systemObject.getSystemID(), this.systemObject.getOrbit());
        setChildScene(this.migrantListOverlay, false, false, true);
        this.B.sounds.playSystemObjectPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void moneyInfoPressed() {
    }

    private void movePeopleButtonPressed() {
        changeScene(this.B.movePopulationScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void nextButtonPressed() {
        if (this.planets.isEmpty()) {
            L(this.coloniesListIndex + 1, this.colonies);
        } else {
            M(this.coloniesListIndex + 1, this.planets);
        }
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void outpostButtonPressed() {
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        if (this.outpostButton.getAlpha() == 1.0f) {
            Game game2 = this.B;
            game2.outposts.add(game2.getCurrentPlayer(), this.systemObject.getSystemID(), this.systemObject.getOrbit(), this);
            return;
        }
        showMessage(new MiningTechNeededMessage());
    }

    private void planetNamePressed() {
        this.B.sounds.playSystemObjectPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
        this.B.keyboardOverlay.setOverlay("renameColony", this.B.getActivity().getString(R.string.planet_change_name), this.colony.getName());
        setChildScene(this.B.keyboardOverlay, false, false, true);
    }

    private void planetSizePressed() {
        showMessage(new PlanetSizeInfoMessage(((Planet) this.systemObject).getSize()));
        this.B.sounds.playSystemObjectPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void populationInfoPressed() {
        showMessage(new PopulationMessage(this.colony));
        this.B.sounds.playSystemObjectPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void powerInfoPressed() {
    }

    private void previousButtonPressed() {
        if (this.planets.isEmpty()) {
            L(this.coloniesListIndex - 1, this.colonies);
        } else {
            M(this.coloniesListIndex - 1, this.planets);
        }
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void productionPressed() {
        changeScene(this.B.productionScene);
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void resistancePressed() {
        showMessage(new ResistanceInfoMessage(this.colony.getResistanceCount() * 10));
        this.B.sounds.playSystemObjectPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void resourcesPressed() {
        showMessage(new ResourcesMessage(this.systemObject.getVisibleResources(this.B.getCurrentPlayer())));
        this.B.sounds.playSystemObjectPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void richnessPressed() {
        SystemObject systemObject = this.systemObject;
        if (systemObject instanceof Planet) {
            showMessage(new RichnessInfoMessage((Planet) systemObject));
        } else {
            showMessage(new RichnessInfoMessage((AsteroidBelt) systemObject));
        }
        this.B.sounds.playSystemObjectPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    /* JADX WARN: Type inference failed for: r4v2, types: [boolean, int] */
    private void setActionButtons() {
        Game game = this.B;
        if (game.fleets.isFleetInSystem(game.getCurrentPlayer(), this.systemObject.getSystemID())) {
            Game game2 = this.B;
            Fleet fleetInSystem = game2.fleets.getFleetInSystem(game2.getCurrentPlayer(), this.systemObject.getSystemID());
            if (this.systemObject.isPlanet() && fleetInSystem.hasColonyShip()) {
                if (this.systemObject.hasOutpost()) {
                    if (this.systemObject.getOccupier() == this.B.getCurrentPlayer()) {
                        this.colonizeButton.setVisible(true);
                        this.colonizeButtonIcon.setVisible(true);
                        this.colonizeText.setVisible(true);
                    }
                } else {
                    this.colonizeButton.setVisible(true);
                    this.colonizeButtonIcon.setVisible(true);
                    this.colonizeText.setVisible(true);
                }
            }
            if (!this.systemObject.isOccupied() && fleetInSystem.hasOutpostShip()) {
                this.outpostButton.setAlpha(1.0f);
                this.outpostButtonIcon.setAlpha(1.0f);
                this.outpostText.setAlpha(1.0f);
                int i = AnonymousClass1.f1468a[this.systemObject.getSystemObjectType().ordinal()];
                if (i == 1 || i == 2) {
                    this.outpostButton.setVisible(true);
                    this.outpostButtonIcon.setVisible(true);
                    this.outpostText.setVisible(true);
                } else if (i == 3) {
                    this.outpostButton.setVisible(true);
                    this.outpostButtonIcon.setVisible(true);
                    this.outpostText.setVisible(true);
                    if (this.systemObject.getSystemObjectType() == SystemObjectType.ASTEROID_BELT && !this.B.getCurrentEmpire().hasTech(TechID.ASTEROID_MINING_OUTPOST)) {
                        this.outpostButton.setAlpha(0.4f);
                        this.outpostButtonIcon.setAlpha(0.4f);
                        this.outpostText.setAlpha(0.4f);
                    }
                }
            }
            if (this.systemObject.isPlanet() && !this.systemObject.hasBeenExploredByEmpire(this.B.getCurrentPlayer()) && fleetInSystem.hasScoutShip()) {
                float f2 = fleetInSystem.hasScoutShipAvailable() ? 1.0f : 0.4f;
                this.exploreButton.setVisible(true);
                this.exploreButtonIcon.setVisible(true);
                this.exploreText.setVisible(true);
                this.exploreButton.setAlpha(f2);
                this.exploreButtonIcon.setAlpha(f2);
                this.exploreText.setAlpha(f2);
            }
            int width = ((int) getWidth()) / 2;
            int width2 = ((int) getWidth()) / 2;
            int width3 = ((int) getWidth()) / 2;
            ?? isVisible = this.outpostButton.isVisible();
            int i2 = isVisible;
            if (this.colonizeButton.isVisible()) {
                i2 = isVisible + 1;
            }
            int i3 = i2;
            if (this.exploreButton.isVisible()) {
                i3 = i2 + 1;
            }
            if (i3 == 2) {
                if (this.exploreButton.isVisible() && this.outpostButton.isVisible()) {
                    width = ((int) getWidth()) - 460;
                } else if (this.exploreButton.isVisible() && this.colonizeButton.isVisible()) {
                    width2 = ((int) getWidth()) - 460;
                } else if (this.outpostButton.isVisible() && this.colonizeButton.isVisible()) {
                    width2 = ((int) getWidth()) - 460;
                    width = 440;
                }
                width3 = 440;
            } else if (i3 == 3) {
                width = ((int) getWidth()) / 2;
                width2 = ((int) getWidth()) - 240;
                width3 = 240;
            }
            TiledSprite tiledSprite = this.outpostButton;
            float f3 = width;
            tiledSprite.setX(f3 - (tiledSprite.getWidthScaled() / 2.0f));
            this.outpostButtonIcon.setX((f3 - (this.outpostButton.getWidthScaled() / 2.0f)) + 37.0f);
            Text text = this.outpostText;
            text.setX(f3 - (text.getWidthScaled() / 2.0f));
            TiledSprite tiledSprite2 = this.colonizeButton;
            float f4 = width2;
            tiledSprite2.setX(f4 - (tiledSprite2.getWidthScaled() / 2.0f));
            this.colonizeButtonIcon.setX((f4 - (this.colonizeButton.getWidthScaled() / 2.0f)) + 37.0f);
            Text text2 = this.colonizeText;
            text2.setX(f4 - (text2.getWidthScaled() / 2.0f));
            TiledSprite tiledSprite3 = this.exploreButton;
            float f5 = width3;
            tiledSprite3.setX(f5 - (tiledSprite3.getWidthScaled() / 2.0f));
            this.exploreButtonIcon.setX((f5 - (this.exploreButton.getWidthScaled() / 2.0f)) + 37.0f);
            Text text3 = this.exploreText;
            text3.setX(f5 - (text3.getWidthScaled() / 2.0f));
        }
    }

    private void setAsteroidBelt() {
        AsteroidBelt asteroidBelt = (AsteroidBelt) this.systemObject;
        this.asteroidBeltSprite.setPosition(400.0f, -120.0f);
        this.asteroidBeltSprite.setCurrentTileIndex(asteroidBelt.getImageIndex());
        this.asteroidBeltSprite.setScale(2.0f);
        this.asteroidBeltSprite.setRotationCenter(0.0f, 0.0f);
        this.asteroidBeltSprite.setRotation(45.0f);
        this.asteroidBeltSprite.setVisible(true);
        for (Entity entity : this.asteroids) {
            entity.setVisible(true);
        }
        this.planetInfo.set(asteroidBelt);
        this.planetInfo.setPosition((getWidth() / 2.0f) - 125.0f, 400.0f);
        int asteroidBonusPercent = asteroidBelt.getMineralRichness().getAsteroidBonusPercent();
        String string = this.B.getActivity().getString(R.string.planet_asteroid_outpost_to_be, new Object[]{Integer.valueOf(asteroidBonusPercent)});
        if (asteroidBelt.hasOutpost()) {
            this.outpost.setVisible(true);
            this.outpost.setCurrentTileIndex(GameIconEnum.MINING_STATION.ordinal());
            this.outpost.setSize(120.0f, 120.0f);
            this.outpost.setX(560.0f);
            this.empireBannerBackground.setVisible(true);
            this.empireBannerBackground.setCurrentTileIndex(asteroidBelt.getOutpost().getEmpireID());
            this.empireBannerBackground.setPosition((getWidth() / 2.0f) - 50.0f, 110.0f);
            this.empireBanner.setVisible(true);
            this.empireBanner.setPosition((getWidth() / 2.0f) - 50.0f, 100.0f);
            this.empireBanner.setCurrentTileIndex(GameIconEnum.getEmpireBanner(asteroidBelt.getOutpost().getEmpireID()));
            this.outpostBackground.setVisible(true);
            if (asteroidBelt.getOutpost().getEmpireID() == this.B.getCurrentPlayer()) {
                string = this.B.getActivity().getString(R.string.planet_asteroid_outpost_yours, new Object[]{Integer.valueOf(asteroidBonusPercent)});
            } else {
                string = this.B.getActivity().getString(R.string.planet_asteroid_outpost_theirs, new Object[]{Integer.valueOf(asteroidBonusPercent)});
            }
        }
        this.productionBonusString.setVisible(true);
        this.productionBonusString.setText(string);
        this.productionBonusString.setX((getWidth() / 2.0f) - (this.productionBonusString.getWidthScaled() / 2.0f));
        if (asteroidBelt.hasOutpost()) {
            this.outpostBackground.setX(this.productionBonusString.getX());
            this.outpostBackground.setWidth(this.productionBonusString.getWidthScaled());
        }
        setActionButtons();
    }

    private void setColonyForCurrentPlayer() {
        this.blackedColonyBackground.setHeight(getWidth() - 705.0f);
        this.colonyBackground.setHeight(getWidth() - 705.0f);
        this.productionBackground.setVisible(true);
        this.buyNowBackground.setVisible(true);
        this.buildingsButton.setVisible(true);
        this.buildingsCount.setVisible(true);
        this.movePeopleButton.setVisible(true);
        this.movePeopleButtonIcon.setVisible(true);
        this.creditsPerTurn.setVisible(true);
        this.creditsIcon.setVisible(true);
        this.happiness.setVisible(true);
        this.happinessIcon.setVisible(true);
        this.power.setVisible(true);
        this.powerIcon.setVisible(true);
        updateColonyStats();
        this.defenseBackground.setVisible(true);
        this.defenseIcon.setVisible(true);
        this.defenseBonus.setText(Integer.toString(this.B.empires.get(this.colony.getEmpireID()).getGroundCombatStrength() + this.colony.getDefenseBonus()));
        this.defenseBonus.setVisible(true);
        this.defenseIcon.setX(this.defenseBonus.getX() + this.defenseBonus.getWidthScaled() + 5.0f);
        if (this.colony.hasBuilding(BuildingID.INFANTRY_BARRACKS) || this.colony.getInfDivisions() > 0) {
            this.infDivisionCount.setVisible(true);
            this.infIcon.setVisible(true);
            this.infDivisionCount.setText(Integer.toString(this.colony.getInfDivisions()));
            this.infIcon.setX(this.infDivisionCount.getX() + this.infDivisionCount.getWidthScaled() + 10.0f);
        }
        updateProduction();
        this.productionPercentBar.set(this.colony);
        this.fpsPercentControl.set(this.colony);
        checkForWarnings();
        List<Migrants> migrantsForPlanet = this.B.getCurrentEmpire().getMigrantsForPlanet(this.colony.getSystemID(), this.colony.getOrbit());
        if (!migrantsForPlanet.isEmpty()) {
            int turns = migrantsForPlanet.get(0).getTurns();
            for (Migrants migrants : migrantsForPlanet) {
                if (migrants.getTurns() < turns) {
                    turns = migrants.getTurns();
                }
            }
            this.migrants.setVisible(true);
            this.migrantTurnIcon.setVisible(true);
            this.turnsToMigrants.setVisible(true);
            this.turnsToMigrants.setText(Integer.toString(turns));
            float widthScaled = 295.0f - this.turnsToMigrants.getWidthScaled();
            this.migrants.setX(widthScaled);
            float f2 = widthScaled + 90.0f;
            this.turnsToMigrants.setX(f2);
            this.migrantTurnIcon.setX(f2 + this.turnsToMigrants.getWidthScaled() + 5.0f);
        }
        this.empireInfo.setVisible(true);
        this.empireInfo.update();
        this.movePeopleWarningIcon.setVisible(this.colony.isBlockaded());
        if (shouldMovePeopleButtonBeDisabled()) {
            this.movePeopleButton.setAlpha(0.4f);
            this.movePeopleButtonIcon.setAlpha(0.4f);
        } else {
            this.movePeopleButton.setAlpha(1.0f);
            this.movePeopleButtonIcon.setAlpha(1.0f);
        }
        if (!this.colony.isAllPopulationSupportive()) {
            this.resistanceIcon.setVisible(true);
            this.resistanceIcon.setX(this.renameIcon.getX() + this.renameIcon.getWidthScaled() + 10.0f);
            this.resistanceIcon.setY(this.planetName.getY() - 5.0f);
            this.resistancePercent.setVisible(true);
            this.resistancePercent.setX((this.resistanceIcon.getX() + this.resistanceIcon.getWidthScaled()) - 2.0f);
            this.resistancePercent.setY(this.resistanceIcon.getY() + (30 - (this.colony.getResistanceCount() * 3)));
            this.resistancePercent.setHeight(this.colony.getResistanceCount() * 3);
        }
        this.buildingsCount.setText(Integer.toString(this.colony.getBuildings().size()));
        this.buildingsCount.setX(((this.buildingsButton.getX() + this.buildingsButton.getWidthScaled()) - 20.0f) - this.buildingsCount.getWidthScaled());
        this.buildingsCount.setY(this.buildingsButton.getY() + 20.0f);
    }

    private void setExploreButton() {
        Game game = this.B;
        Fleet fleetInSystem = game.fleets.getFleetInSystem(game.getCurrentPlayer(), this.systemObject.getSystemID());
        if (this.systemObject.isPlanet() && !this.systemObject.hasBeenExploredByEmpire(this.B.getCurrentPlayer()) && fleetInSystem.hasScoutShip()) {
            this.exploreButton.setVisible(true);
            this.exploreButtonIcon.setVisible(true);
            this.exploreText.setVisible(true);
            TiledSprite tiledSprite = this.exploreButton;
            tiledSprite.setX(972.0f - (tiledSprite.getWidthScaled() / 2.0f));
            this.exploreButtonIcon.setX((972.0f - (this.exploreButton.getWidthScaled() / 2.0f)) + 37.0f);
            Text text = this.exploreText;
            text.setX(972.0f - (text.getWidthScaled() / 2.0f));
            float f2 = fleetInSystem.hasScoutShipAvailable() ? 1.0f : 0.4f;
            this.exploreButton.setAlpha(f2);
            this.exploreButtonIcon.setAlpha(f2);
            this.exploreText.setAlpha(f2);
        }
    }

    private float setFarmProdScienceStats(float f2, float f3) {
        Planet planet = (Planet) this.systemObject;
        this.foodPerFarmerString.setPosition(f2, f3);
        this.foodPerFarmerString.setVisible(true);
        String format = this.decimalFormat.format(planet.getFoodPerFarmer(this.B.getCurrentPlayer()));
        if (planet.hasColony()) {
            format = this.decimalFormat.format(planet.getColony().getFoodPerFarmer());
        }
        this.foodPerFarmerString.setText(format);
        float f4 = f3 - 3.0f;
        this.foodPerFarmerIcon.setPosition(this.foodPerFarmerString.getWidth() + f2 + 5.0f, f4);
        this.foodPerFarmerIcon.setVisible(true);
        this.productionPerWorkerString.setPosition(this.foodPerFarmerIcon.getX() + this.foodPerFarmerIcon.getWidthScaled() + 10.0f, f3);
        this.productionPerWorkerString.setVisible(true);
        String format2 = this.decimalFormat.format(planet.getProductionPerWorker(this.B.getCurrentPlayer()));
        if (planet.hasColony()) {
            format2 = this.decimalFormat.format(planet.getColony().getProductionPerWorker());
        }
        this.productionPerWorkerString.setText(format2);
        this.productionPerWorkerIcon.setPosition(this.productionPerWorkerString.getX() + this.productionPerWorkerString.getWidth() + 8.0f, f4);
        this.productionPerWorkerIcon.setVisible(true);
        this.sciencePerScientistString.setPosition(this.productionPerWorkerIcon.getX() + this.productionPerWorkerIcon.getWidthScaled() + 10.0f, f3);
        this.sciencePerScientistString.setVisible(true);
        String format3 = this.decimalFormat.format(planet.getSciencePerScientist(this.B.getCurrentPlayer()));
        if (planet.hasColony()) {
            format3 = this.decimalFormat.format(planet.getColony().getSciencePerScientist());
        }
        this.sciencePerScientistString.setText(format3);
        this.sciencePerScientistIcon.setPosition(this.sciencePerScientistString.getX() + this.sciencePerScientistString.getWidth() + 5.0f, f4);
        this.sciencePerScientistIcon.setVisible(true);
        this.perMillion.setPosition(this.sciencePerScientistIcon.getX() + this.sciencePerScientistIcon.getWidthScaled() + 10.0f, f3 + 2.0f);
        this.perMillion.setVisible(true);
        return (this.perMillion.getX() + this.perMillion.getWidthScaled()) - f2;
    }

    private void setGasGiant() {
        GasGiant gasGiant = (GasGiant) this.systemObject;
        this.planetSprite.setGasGiant(gasGiant, gasGiant.getScale(this.B.planetScene), Moon.getScale(this.B.planetScene), true);
        this.planetInfo.set();
        this.sizeClimateString.setText(this.B.getActivity().getString(R.string.planet_size_climate, new Object[]{gasGiant.getSize().getDisplayName(), gasGiant.getClimate().getDisplayName()}));
        this.sizeClimateString.setVisible(true);
        String string = this.B.getActivity().getString(R.string.planet_gas_giant_outpost_to_be, new Object[]{Integer.valueOf(gasGiant.getSciencePercentBonus())});
        if (gasGiant.hasOutpost()) {
            this.outpost.setVisible(true);
            this.outpost.setCurrentTileIndex(GameIconEnum.SCIENCE_STATION.ordinal());
            this.outpost.setSize(120.0f, 120.0f);
            this.outpost.setX(560.0f);
            this.empireBannerBackground.setVisible(true);
            this.empireBannerBackground.setCurrentTileIndex(gasGiant.getOutpost().getEmpireID());
            this.empireBannerBackground.setPosition((getWidth() / 2.0f) - 50.0f, 110.0f);
            this.empireBanner.setVisible(true);
            this.empireBanner.setPosition((getWidth() / 2.0f) - 50.0f, 100.0f);
            this.empireBanner.setCurrentTileIndex(GameIconEnum.getEmpireBanner(gasGiant.getOutpost().getEmpireID()));
            this.outpostBackground.setVisible(true);
            if (gasGiant.getOutpost().getEmpireID() == this.B.getCurrentPlayer()) {
                string = this.B.getActivity().getString(R.string.planet_gas_giant_outpost_yours, new Object[]{Integer.valueOf(gasGiant.getSciencePercentBonus())});
            } else {
                string = this.B.getActivity().getString(R.string.planet_gas_giant_outpost_theirs, new Object[]{Integer.valueOf(gasGiant.getSciencePercentBonus())});
            }
        }
        this.productionBonusString.setVisible(true);
        this.productionBonusString.setText(string);
        this.productionBonusString.setX((getWidth() / 2.0f) - (this.productionBonusString.getWidthScaled() / 2.0f));
        this.outpostBackground2.setVisible(true);
        this.outpostBackground2.setX(this.productionBonusString.getX());
        this.outpostBackground2.setWidth(this.productionBonusString.getWidthScaled());
        if (gasGiant.hasOutpost()) {
            this.outpostBackground.setX(this.productionBonusString.getX());
            this.outpostBackground.setWidth(this.productionBonusString.getWidthScaled());
        }
        setActionButtons();
    }

    private void setPlanet() {
        Planet planet = (Planet) this.systemObject;
        this.planetSprite.setPlanet(planet, planet.getScale(this.B.planetScene), Moon.getScale(this.B.planetScene), true);
        setResourcesIcons(getWidth() - 520.0f, 160.0f);
        setPlanetInfoIcons(getWidth() - 520.0f, 210.0f);
        this.sizeClimateString.setText(this.B.getActivity().getString(R.string.planet_size_climate, new Object[]{planet.getSize().getDisplayName(), planet.getClimate().getDisplayName()}));
        this.sizeClimateString.setVisible(true);
        this.richnessString.setText(this.B.getActivity().getString(R.string.planet_mineral_richness, new Object[]{planet.getMineralRichness().getDisplayName()}));
        this.richnessString.setVisible(true);
        this.gravityString.setText(this.B.getActivity().getString(R.string.planet_gravity, new Object[]{planet.getGravity().getDisplayName()}));
        this.gravityString.setVisible(true);
        setFarmProdScienceStats(getWidth() - 520.0f, 430.0f);
        this.populationString.setVisible(true);
        this.populationString.setText(this.B.getActivity().getString(R.string.planet_max_pop, new Object[]{Integer.valueOf(planet.getMaxPopulation())}));
        this.populationString.setPosition(getWidth() - 520.0f, 470.0f);
        setPlanetStats(getWidth() - 520.0f);
        if (planet.hasColony()) {
            loadColony();
            return;
        }
        if (planet.hasOutpost()) {
            this.outpost.setVisible(true);
            this.outpost.setCurrentTileIndex(GameIconEnum.OUTPOST.ordinal());
            this.outpost.setSize(100.0f, 100.0f);
            this.outpost.setX(560.0f);
            this.empireBannerBackground.setVisible(true);
            this.empireBannerBackground.setCurrentTileIndex(planet.getOutpost().getEmpireID());
            this.empireBannerBackground.setPosition(getWidth() - 690.0f, 100.0f);
            this.empireBanner.setVisible(true);
            this.empireBanner.setPosition(getWidth() - 690.0f, 90.0f);
            this.empireBanner.setCurrentTileIndex(GameIconEnum.getEmpireBanner(planet.getOutpost().getEmpireID()));
        }
        setActionButtons();
    }

    private void setPlanetInfoForColony(Planet planet) {
        setPopulationBar();
        setResourcesIcons(getWidth() - 615.0f, 40.0f);
        setPlanetInfoIcons(getWidth() - 615.0f, 90.0f);
        float farmProdScienceStats = setFarmProdScienceStats(880.0f, 112.0f);
        if (this.colony.getEmpireID() == this.B.getCurrentPlayer()) {
            setFarmProdScienceStats(getWidth() - farmProdScienceStats, 112.0f);
        } else {
            this.foodPerFarmerString.setVisible(false);
            this.foodPerFarmerIcon.setVisible(false);
            this.productionPerWorkerString.setVisible(false);
            this.productionPerWorkerIcon.setVisible(false);
            this.sciencePerScientistString.setVisible(false);
            this.sciencePerScientistIcon.setVisible(false);
            this.perMillion.setVisible(false);
        }
        this.sizeClimateString.setVisible(false);
        this.richnessString.setVisible(false);
        this.gravityString.setVisible(false);
        this.resourcesText.setVisible(false);
        int round = Math.round(planet.getPopulationGrowthModifier() * 100.0f);
        Text text = this.popGrowthString;
        text.setText("+" + round + "%");
        this.popGrowthString.setVisible(true);
        this.popGrowthString.setPosition(50.0f, 690.0f);
        this.popGrowthIcon.setVisible(true);
        this.popGrowthIcon.setPosition(10.0f, 680.0f);
        Text text2 = this.defenseBonusString;
        text2.setText("+" + planet.getDefenceBonus());
        this.defenseBonusString.setVisible(true);
        this.defenseBonusString.setPosition(50.0f, 660.0f);
        this.defenseBonusIcon.setVisible(true);
        this.defenseBonusIcon.setPosition(10.0f, 650.0f);
        if (planet.getClimateCostModifier() > 1.0f) {
            int round2 = Math.round((planet.getClimateCostModifier() - 1.0f) * 100.0f);
            Text text3 = this.maintenanceString;
            text3.setText("+" + round2 + "%");
            this.maintenanceString.setVisible(true);
            this.maintenanceString.setPosition(50.0f, 630.0f);
            this.maintenanceIcon.setVisible(true);
            this.maintenanceIcon.setPosition(10.0f, 620.0f);
        }
    }

    private void setPlanetInfoIcons(float f2, float f3) {
        Planet planet = (Planet) this.systemObject;
        this.planetInfo.set(planet);
        this.planetInfo.setPosition(f2, f3);
        if (planet.getClimate().isSpecial()) {
            this.infoIcon.setVisible(true);
            this.infoIcon.setPosition((getWidth() / 2.0f) - 20.0f, 50.0f);
            if (planet.hasColony()) {
                if (planet.getColony().getEmpireID() == this.B.getCurrentPlayer()) {
                    this.infoIcon.setPosition(getWidth() - 405.0f, 90.0f);
                } else {
                    this.infoIcon.setPosition((getWidth() / 2.0f) - 20.0f, 670.0f);
                }
            }
        }
    }

    private void setPlanetStats(float f2) {
        Planet planet = (Planet) this.systemObject;
        this.popGrowthIcon.setVisible(true);
        this.popGrowthIcon.setPosition(f2, 510.0f);
        int round = Math.round(planet.getPopulationGrowthModifier() * 100.0f);
        Text text = this.popGrowthString;
        text.setText(round + "%");
        this.popGrowthString.setVisible(true);
        this.popGrowthString.setPosition(f2 + this.popGrowthIcon.getWidthScaled() + 5.0f, 520.0f);
        this.defenseBonusIcon.setVisible(true);
        this.defenseBonusIcon.setPosition(this.popGrowthString.getX() + this.popGrowthString.getWidthScaled() + 30.0f, 510.0f);
        Text text2 = this.defenseBonusString;
        text2.setText("+" + planet.getDefenceBonus());
        this.defenseBonusString.setVisible(true);
        this.defenseBonusString.setPosition(this.defenseBonusIcon.getX() + this.defenseBonusIcon.getWidthScaled() + 5.0f, 520.0f);
        if (planet.getClimateCostModifier() > 1.0f) {
            int round2 = Math.round((planet.getClimateCostModifier() - 1.0f) * 100.0f);
            this.maintenanceIcon.setVisible(true);
            this.maintenanceIcon.setPosition(this.defenseBonusString.getX() + this.defenseBonusString.getWidthScaled() + 30.0f, 510.0f);
            Text text3 = this.maintenanceString;
            text3.setText("+" + round2 + "%");
            this.maintenanceString.setVisible(true);
            this.maintenanceString.setPosition(this.maintenanceIcon.getX() + this.maintenanceIcon.getWidthScaled() + 5.0f, 520.0f);
        }
    }

    private void setPopulationBar() {
        Planet planet = (Planet) this.systemObject;
        int maxPopulation = planet.getMaxPopulation() + 60;
        float f2 = ((360 - maxPopulation) / 2.0f) + 180.0f;
        this.populationBar.setVisible(true);
        this.populationBar.setPosition(f2, 605.0f);
        float f3 = maxPopulation;
        this.populationBar.setSize(f3, 75.0f);
        int population = this.colony.getPopulation();
        int maxPopulation2 = planet.getMaxPopulation();
        float f4 = population / maxPopulation2;
        int populationGrowth = this.colony.getPopulationGrowth();
        this.populationString.setVisible(true);
        String str = population + "m / " + maxPopulation2 + "m ";
        if (this.colony.getEmpireID() == this.B.getCurrentPlayer()) {
            if (populationGrowth >= 0) {
                str = str + "+";
            }
            str = str + populationGrowth + "k";
        }
        this.populationString.setText(str);
        this.populationString.setPosition((this.planetSprite.getX() + (this.planetSprite.getWidthScaled() / 2.0f)) - (this.populationString.getWidth() / 2.0f), 685.0f);
        this.populationPercentString.setVisible(true);
        String num = Integer.toString((int) (100.0f * f4));
        this.populationPercentString.setText(num + "%");
        this.populationPercentString.setPosition((this.planetSprite.getX() + (this.planetSprite.getWidthScaled() / 2.0f)) - (this.populationPercentString.getWidth() / 2.0f), 630.0f);
        if (f4 != 1.0f) {
            float f5 = f4 * f3;
            this.populationBarEmpty.setPosition(f2 + f5, 619.0f);
            this.populationBarEmpty.setSize((f3 - f5) - 2.0f, 45.0f);
            this.populationBarEmpty.setVisible(true);
        } else {
            this.populationBarEmpty.setVisible(false);
        }
        this.popIcon.setVisible(true);
        this.popIcon.setX((this.populationBar.getX() - this.popIcon.getWidthScaled()) - 5.0f);
    }

    private void setResourcesIcons(float f2, float f3) {
        if (this.systemObject.hasBeenExploredByEmpire(this.B.getCurrentPlayer())) {
            List<ResourceID> visibleResources = this.systemObject.getVisibleResources(this.B.getCurrentPlayer());
            String string = this.B.getActivity().getString(R.string.planet_has);
            if (visibleResources.size() != 0) {
                this.resourcesText.setVisible(true);
            }
            int i = 0;
            for (ResourceID resourceID : visibleResources) {
                if (resourceID != ResourceID.NONE) {
                    string = string.concat(Resources.get(resourceID).getName());
                    if (visibleResources.size() - 1 != i) {
                        string = string + ", ";
                    }
                    TiledSprite tiledSprite = (TiledSprite) this.resourceIcons.get(i);
                    tiledSprite.setVisible(true);
                    tiledSprite.setCurrentTileIndex(Resources.get(resourceID).getImageIndex());
                    tiledSprite.setPosition((i * 50) + f2, f3);
                    i++;
                    if (i == 4) {
                        return;
                    }
                }
            }
            this.resourcesText.setText(string);
            return;
        }
        this.exploredText.setVisible(true);
        this.exploredText.setPosition(f2, f3);
        if (this.systemObject.isUnexplored()) {
            this.exploredText.setColor(Color.WHITE);
            this.exploredText.setText(this.B.getActivity().getString(R.string.planet_unexplored));
            return;
        }
        this.exploredText.setColor(new Color(0.6f, 0.6f, 0.6f));
        this.exploredText.setText(this.B.getActivity().getString(R.string.planet_unknown));
    }

    private void setSpritesInvisible() {
        int size;
        this.productionPercentBar.hide();
        C(this.asteroids);
        this.colonyBackground.setVisible(false);
        this.surface.setVisible(false);
        this.productionBackground.setVisible(false);
        this.buyNowBackground.setVisible(false);
        this.blackedColonyBackground.setVisible(false);
        this.asteroidBeltSprite.setVisible(false);
        this.resourcesText.setVisible(false);
        this.populationString.setVisible(false);
        this.foodPerFarmerIcon.setVisible(false);
        this.foodPerFarmerString.setVisible(false);
        this.productionPerWorkerIcon.setVisible(false);
        this.productionPerWorkerString.setVisible(false);
        this.sciencePerScientistIcon.setVisible(false);
        this.sciencePerScientistString.setVisible(false);
        this.perMillion.setVisible(false);
        this.popGrowthString.setVisible(false);
        this.popGrowthIcon.setVisible(false);
        this.defenseBonusString.setVisible(false);
        this.defenseBonusIcon.setVisible(false);
        this.maintenanceString.setVisible(false);
        this.maintenanceIcon.setVisible(false);
        this.buildingsButton.setVisible(false);
        this.buildingsCount.setVisible(false);
        this.movePeopleButton.setVisible(false);
        this.movePeopleButtonIcon.setVisible(false);
        this.creditsPerTurn.setVisible(false);
        this.creditsIcon.setVisible(false);
        this.netFoodPerTurn.setVisible(false);
        this.netFoodPerTurnIcon.setVisible(false);
        this.importedFoodPerTurn.setVisible(false);
        this.importedFoodPerTurnIcon.setVisible(false);
        this.happiness.setVisible(false);
        this.happinessIcon.setVisible(false);
        this.power.setVisible(false);
        this.powerIcon.setVisible(false);
        this.colonizeButton.setVisible(false);
        this.colonizeButtonIcon.setVisible(false);
        this.colonizeText.setVisible(false);
        this.outpostButton.setVisible(false);
        this.outpostButtonIcon.setVisible(false);
        this.outpostText.setVisible(false);
        this.exploreButton.setVisible(false);
        this.exploreButtonIcon.setVisible(false);
        this.exploreText.setVisible(false);
        this.sizeClimateString.setVisible(false);
        this.richnessString.setVisible(false);
        this.gravityString.setVisible(false);
        this.productionBonusString.setVisible(false);
        this.outpostBackground.setVisible(false);
        this.outpostBackground2.setVisible(false);
        this.outpost.setVisible(false);
        this.starBase.setVisible(false);
        this.turret1.setVisible(false);
        this.turret2.setVisible(false);
        this.shipYards.setVisible(false);
        this.starvingWarning.setVisible(false);
        this.blockadeWarning.setVisible(false);
        this.emptyColonyWarning.setVisible(false);
        this.lowPowerWarning.setVisible(false);
        this.defenseBackground.setVisible(false);
        this.empireBannerBackground.setVisible(false);
        this.infDivisionCount.setVisible(false);
        this.infIcon.setVisible(false);
        this.defenseIcon.setVisible(false);
        this.defenseBonus.setVisible(false);
        this.infoIcon.setVisible(false);
        C(this.resourceIcons);
        this.migrants.setVisible(false);
        this.turnsToMigrants.setVisible(false);
        this.migrantTurnIcon.setVisible(false);
        this.exploredText.setVisible(false);
        this.resistanceIcon.setVisible(false);
        this.resistancePercent.setVisible(false);
        this.listButton.setVisible(false);
        this.previousButton.setVisible(false);
        this.nextButton.setVisible(false);
        this.eventsButton.setVisible(false);
        if (this.whereFrom instanceof ColoniesScene) {
            this.listButton.setVisible(true);
            this.previousButton.setVisible(true);
            this.nextButton.setVisible(true);
            this.previousButton.setAlpha(1.0f);
            this.nextButton.setAlpha(1.0f);
            if (this.coloniesListIndex == 0) {
                this.previousButton.setAlpha(0.4f);
            }
            if (this.colonies.isEmpty()) {
                size = this.planets.size();
            } else {
                size = this.colonies.size();
            }
            if (size - 1 == this.coloniesListIndex) {
                this.nextButton.setAlpha(0.4f);
            }
        }
        if (this.whereFrom instanceof EventsScene) {
            Game game = this.B;
            if (game.events.getEvents(game.getCurrentPlayer()).size() > 1) {
                this.eventsButton.setVisible(true);
            }
        }
        this.fpsPercentControl.hideControl();
        this.empireBanner.setVisible(false);
        this.capitalIcon.setVisible(false);
        this.populationBar.setVisible(false);
        this.populationBarEmpty.setVisible(false);
        this.populationPercentString.setVisible(false);
        this.popIcon.setVisible(false);
        this.shipProductionIcon.setVisible(false);
        this.shipTypeProductionIcon.setVisible(false);
        this.buildingProductionIcon.setVisible(false);
        this.buyNowButton.setVisible(false);
        this.productionName.setVisible(false);
        this.repeatIcon.setVisible(false);
        this.productionTurnsLeft.setVisible(false);
        this.empireInfo.setVisible(false);
    }

    private void setSystemBackground(StarSystem starSystem) {
        this.nebulas.set(starSystem.getID());
        this.sunSprite.set(starSystem.getStarType());
    }

    private boolean shouldMovePeopleButtonBeDisabled() {
        Game game = this.B;
        return game.colonies.getColonies(game.getCurrentPlayer()).size() == 1 || this.colony.getPopulation() < 2 || this.colony.isBlockaded();
    }

    private void showExploreMessage(String str) {
        Planet planet = (Planet) this.systemObject;
        showMessage(new PlanetExplorationMessage(planet, str, planet.isUnexplored()));
    }

    private void specialInfoPressed() {
        showMessage(new PlanetDiscoveryMessage((Planet) this.systemObject, false));
        this.B.sounds.playSystemObjectPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void systemButtonPressed() {
        changeScene(this.B.systemScene, Integer.valueOf(this.systemObject.getSystemID()));
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void updateColonyStats() {
        float x;
        float widthScaled;
        String num = Integer.toString(this.colony.getCreditsPerTurn());
        if (this.colony.getCreditsPerTurn() >= 0) {
            num = "+" + num;
        }
        this.creditsPerTurn.setText(num);
        this.creditsPerTurn.setX(0.0f);
        this.creditsIcon.setX(0.0f + this.creditsPerTurn.getWidth() + 5.0f);
        int totalFoodPerTurn = this.colony.getTotalFoodPerTurn();
        int importedFood = this.colony.getImportedFood();
        String num2 = Integer.toString(totalFoodPerTurn);
        if (this.colony.getTotalFoodPerTurn() >= 0) {
            num2 = "+" + num2;
        }
        this.netFoodPerTurn.setText(num2);
        this.importedFoodPerTurn.setText(Integer.toString(importedFood));
        float x2 = this.creditsIcon.getX() + this.creditsIcon.getWidthScaled() + 20.0f;
        this.importedFoodPerTurn.setVisible(false);
        this.importedFoodPerTurnIcon.setVisible(false);
        this.netFoodPerTurn.setVisible(false);
        this.netFoodPerTurnIcon.setVisible(false);
        if (importedFood > 0) {
            this.importedFoodPerTurn.setVisible(true);
            this.importedFoodPerTurnIcon.setVisible(true);
            this.importedFoodPerTurn.setX(x2);
            this.importedFoodPerTurnIcon.setX(x2 + this.importedFoodPerTurn.getWidthScaled() + 7.0f);
            x = this.importedFoodPerTurnIcon.getX();
            widthScaled = this.importedFoodPerTurnIcon.getWidthScaled();
        } else {
            this.netFoodPerTurn.setVisible(true);
            this.netFoodPerTurnIcon.setVisible(true);
            this.netFoodPerTurn.setX(x2);
            this.netFoodPerTurnIcon.setX(x2 + this.netFoodPerTurn.getWidthScaled() + 3.0f);
            x = this.netFoodPerTurnIcon.getX();
            widthScaled = this.netFoodPerTurnIcon.getWidthScaled();
        }
        float f2 = x + widthScaled + 20.0f;
        this.happiness.setText(Integer.toString(Math.round(this.colony.getHappiness() * 100.0f)) + "%");
        this.happiness.setX(f2);
        this.happinessIcon.setX(f2 + this.happiness.getWidthScaled() + 5.0f);
        String num3 = Integer.toString(this.colony.getPowerLevel());
        if (this.colony.getPowerLevel() >= 0) {
            num3 = "+" + num3;
        }
        this.power.setText(num3);
        float x3 = this.happinessIcon.getX() + this.happinessIcon.getWidthScaled() + 20.0f;
        this.power.setX(x3);
        this.powerIcon.setX(x3 + this.power.getWidthScaled() + 3.0f);
        float width = (getWidth() - 308.0f) - ((this.powerIcon.getX() + this.powerIcon.getWidthScaled()) / 2.0f);
        this.creditsPerTurn.setX(width);
        this.creditsIcon.setX(width + this.creditsPerTurn.getWidth() + 5.0f);
        float x4 = this.creditsIcon.getX() + this.creditsIcon.getWidthScaled() + 20.0f;
        if (this.netFoodPerTurn.isVisible()) {
            this.netFoodPerTurn.setX(x4);
            this.netFoodPerTurnIcon.setX(x4 + this.netFoodPerTurn.getWidthScaled() + 3.0f);
            x4 = this.netFoodPerTurnIcon.getX() + this.netFoodPerTurnIcon.getWidthScaled() + 20.0f;
        }
        if (this.importedFoodPerTurn.isVisible()) {
            this.importedFoodPerTurn.setX(x4);
            this.importedFoodPerTurnIcon.setX(x4 + this.importedFoodPerTurn.getWidthScaled() + 7.0f);
            x4 = this.importedFoodPerTurnIcon.getX() + this.importedFoodPerTurnIcon.getWidthScaled() + 20.0f;
        }
        this.happiness.setX(x4);
        this.happinessIcon.setX(x4 + this.happiness.getWidthScaled() + 5.0f);
        float x5 = this.happinessIcon.getX() + this.happinessIcon.getWidthScaled() + 20.0f;
        this.power.setX(x5);
        this.powerIcon.setX(x5 + this.power.getWidthScaled() + 3.0f);
    }

    private void updateProduction() {
        ManufacturingType type = this.colony.getManufacturing().getType();
        this.productionName.setVisible(true);
        this.productionTurnsLeft.setVisible(true);
        this.buyNowButton.setVisible(true);
        if (this.colony.getManufacturing().getCostToFinish() > this.B.getCurrentEmpire().getCredits()) {
            this.buyNowButton.setAlpha(0.4f);
        } else {
            this.buyNowButton.setAlpha(1.0f);
        }
        if (this.colony.getManufacturing().getCostToFinish() == 0 || this.colony.getManufacturing().showGreyProgressBar()) {
            this.buyNowButton.setVisible(false);
        }
        this.productionName.setText(this.colony.getNameOfProduction());
        this.productionName.setX((getWidth() - 320.0f) - (this.productionName.getWidth() / 2.0f));
        this.productionTurnsLeft.setText(this.colony.getTurnsLeftOnProductionString());
        this.productionTurnsLeft.setX((getWidth() - 320.0f) - (this.productionTurnsLeft.getWidth() / 2.0f));
        this.shipProductionIcon.setVisible(false);
        this.buildingProductionIcon.setVisible(false);
        if (type == ManufacturingType.SHIP) {
            ShipType shipType = this.colony.getManufacturing().getShipType();
            this.shipProductionIcon.setVisible(true);
            this.shipProductionIcon.setPosition(getWidth() - 615.0f, 410.0f);
            this.shipProductionIcon.setTiledTextureRegion((ITiledTextureRegion) this.B.graphics.shipsTextures[this.colony.getEmpireID()]);
            this.shipProductionIcon.setCurrentTileIndex(this.colony.getManufacturing().getShipIconIndex());
            float selectScreenSize = this.colony.getManufacturing().getShipType().getSelectScreenSize();
            this.shipProductionIcon.setSize(selectScreenSize, selectScreenSize);
            TiledSprite tiledSprite = this.shipProductionIcon;
            float f2 = 50.0f - (selectScreenSize / 2.0f);
            tiledSprite.setPosition(tiledSprite.getX() + f2, this.shipProductionIcon.getY() + f2);
            this.shipTypeProductionIcon.setVisible(true);
            this.shipTypeProductionIcon.setCurrentTileIndex(InfoIconEnum.getShipIcon(shipType));
        } else {
            this.buildingProductionIcon.setCurrentTileIndex(this.colony.getManufacturing().getImageIndex());
            this.buildingProductionIcon.setVisible(true);
        }
        if (this.colony.getManufacturing().getCurrentItem().isRepeatOn()) {
            this.repeatIcon.setVisible(true);
        }
    }

    private void warningPressed(TiledSprite tiledSprite) {
        showMessage(new WarningMessage(tiledSprite.getCurrentTileIndex()));
        this.B.sounds.playSystemObjectPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void B(Point point) {
    }

    public void L(int i, List<Colony> list) {
        this.coloniesListIndex = i;
        this.colonies = list;
        this.planets.clear();
        loadPlanet(list.get(i).getSystemID(), list.get(i).getOrbit(), this.B.coloniesScene);
    }

    public void M(int i, List<Planet> list) {
        this.coloniesListIndex = i;
        this.planets = list;
        this.colonies.clear();
        loadPlanet(list.get(i).getSystemID(), list.get(i).getOrbit(), this.B.coloniesScene);
    }

    protected void N(ExtendedScene extendedScene, Object obj) {
        if (extendedScene instanceof ProductionScene) {
            if (this.listButton.isVisible()) {
                this.B.productionScene.set(this.coloniesListIndex, this.colonies);
            } else {
                Game game = this.B;
                game.productionScene.set(this.colony, game.planetScene);
            }
        }
        if (extendedScene instanceof ColoniesScene) {
            this.B.coloniesScene.L();
        } else if (extendedScene instanceof MovePopulationScene) {
            this.B.movePopulationScene.set(this.systemObject.getSystemID(), this.systemObject.getOrbit());
        } else if (extendedScene instanceof BuildingsScene) {
            this.B.buildingsScene.set(this.colony);
        } else if (extendedScene instanceof GalaxyScene) {
            this.B.galaxyScene.setStarsAndNebulas();
        } else if (extendedScene instanceof SystemScene) {
            this.B.systemScene.loadSystem(((Integer) obj).intValue());
        } else if (extendedScene instanceof EventsScene) {
            Game game2 = this.B;
            game2.eventsScene.set(game2.getCurrentPlayer());
        }
    }

    @Override // org.andengine.entity.scene.Scene
    public void back() {
        if (t()) {
            return;
        }
        if (hasChildScene()) {
            this.H.back();
        } else {
            changeScene(this.B.systemScene, Integer.valueOf(this.systemObject.getSystemID()));
        }
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void checkInput(int i, Point point) {
        if (this.fpsPercentControl.checkInputOnControl(i, point)) {
            return;
        }
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
        createSun();
        createAsteroidBelt();
        createColony();
        createOutpost();
        createStarBase();
        createTorpedoTurrets();
        createShipYards();
        createPlanetInfo();
        createSystemObjectName();
        EmpireInfo empireInfo = new EmpireInfo(this.B, vertexBufferObjectManager, 620, this);
        this.empireInfo = empireInfo;
        attachChild(empireInfo);
        createButtons();
        createSystemObjectText();
        createModifierStats();
        createColonyStats();
        createPopulationBar();
        createFoodProductionScienceSlider();
        createProductionInfo();
        createPlanetDefenseInfo();
        createWarningIcons();
        createIncomingMigrantsInfo();
        createResistanceIndicator();
        createOverlays();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void getPoolElements() {
        if (this.nebulas.hasParent()) {
            this.nebulas.detachSelf();
        }
        this.nebulaBackground.attachChild(this.nebulas);
        PlanetSprite planetSprite = this.B.planetSpritePool.get();
        this.planetSprite = planetSprite;
        planetSprite.setPosition(360.0f, 390.0f);
        this.planetSprite.setMoonRange(665, TypedValues.Motion.TYPE_ANIMATE_RELATIVE_TO);
        this.nebulaBackground.attachChild(this.planetSprite);
        this.planetSprite.setSpritesInvisible();
    }

    public SystemObject getSystemObject() {
        return this.systemObject;
    }

    public void loadPlanet(int i, int i2, ExtendedScene extendedScene) {
        this.whereFrom = extendedScene;
        setSpritesInvisible();
        StarSystem starSystem = this.B.galaxy.getStarSystem(i);
        this.starSystem = starSystem;
        this.systemObject = starSystem.getSystemObject(i2);
        setSystemBackground(this.starSystem);
        Text text = this.planetName;
        text.setText(this.starSystem.getName() + " " + (this.systemObject.getOrbit() + 1));
        this.planetName.setPosition((getWidth() / 2.0f) - (this.planetName.getWidth() / 2.0f), 10.0f);
        this.renameIcon.setVisible(false);
        this.blockadeWarning.setVisible(false);
        this.starvingWarning.setVisible(false);
        this.emptyColonyWarning.setVisible(false);
        this.lowPowerWarning.setVisible(false);
        int i3 = AnonymousClass1.f1468a[this.systemObject.getSystemObjectType().ordinal()];
        if (i3 == 1) {
            setPlanet();
        } else if (i3 == 2) {
            setGasGiant();
        } else if (i3 == 3) {
            setAsteroidBelt();
        }
        Game game = this.B;
        List<Event> eventsForPlanet = game.events.getEventsForPlanet(game.getCurrentPlayer(), this.systemObject.getSystemID(), this.systemObject.getOrbit());
        if (eventsForPlanet.size() != 0) {
            this.B.events.removeEvent(eventsForPlanet.get(0));
            showMessage(new TerraformedMessage((Planet) this.systemObject));
        }
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void refresh() {
        loadPlanet(this.starSystem.getID(), this.systemObject.getOrbit(), this.whereFrom);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void releasePoolElements(final ExtendedScene extendedScene, final Object obj) {
        this.B.getEngine().runOnUpdateThread(new Runnable() { // from class: com.birdshel.Uciana.Scenes.b0
            {
                PlanetScene.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                PlanetScene.this.lambda$releasePoolElements$0(extendedScene, obj);
            }
        });
    }

    public void setColonyName(String str) {
        if (str.equals(this.colony.getPlanet().getName())) {
            this.colony.setName("");
        } else {
            this.colony.setName(str);
        }
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void switched() {
        if (this.systemObject.hasColony() && this.colony.getEmpireID() == this.B.getCurrentPlayer()) {
            this.productionPercentBar.update();
            updateProduction();
            if (!hasChildScene()) {
                Options options = Game.options;
                TutorialID tutorialID = TutorialID.COLONY;
                if (options.shouldTutorialBeShown(tutorialID)) {
                    showMessage(new ColonyViewTutorial());
                    Game.options.markSeen(tutorialID);
                }
            }
        }
        super.switched();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void update() {
        if (!this.updateColony || System.currentTimeMillis() - this.updateTime <= 100) {
            return;
        }
        this.updateColony = false;
        updateColonyInfo();
    }

    public void updateColony() {
        if (this.updateColony) {
            return;
        }
        this.updateColony = true;
        this.updateTime = System.currentTimeMillis();
    }

    public void updateColonyInfo() {
        updateProduction();
        updateColonyStats();
        this.productionPercentBar.update();
    }

    public void updatePopulationInfo() {
        this.B.getCurrentEmpire().redistributeFood();
        updateColonyStats();
        setPopulationBar();
        checkForWarnings();
        this.empireInfo.update();
    }
}
