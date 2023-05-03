package com.birdshel.Uciana.Scenes;

import android.util.SparseIntArray;
import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Controls.FleetControl;
import com.birdshel.Uciana.Elements.EmpireButton;
import com.birdshel.Uciana.Elements.EmpireInfo;
import com.birdshel.Uciana.Events.Event;
import com.birdshel.Uciana.Events.EventDataFields;
import com.birdshel.Uciana.Events.EventType;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Messages.AIProposeTreatyMessage;
import com.birdshel.Uciana.Messages.AscendedEncounterMessage;
import com.birdshel.Uciana.Messages.DiplomaticMessage;
import com.birdshel.Uciana.Messages.DiplomaticType;
import com.birdshel.Uciana.Messages.EmpireDestroyedMessage;
import com.birdshel.Uciana.Messages.GalaxyMessages.BlackholeMessage;
import com.birdshel.Uciana.Messages.GalaxyMessages.NoDirectTravelMessage;
import com.birdshel.Uciana.Messages.GalaxyMessages.OutOfComRangeMessage;
import com.birdshel.Uciana.Messages.GalaxyMessages.OutOfRangeMessage;
import com.birdshel.Uciana.Messages.GalaxyMessages.SpaceRiftMessage;
import com.birdshel.Uciana.Messages.GalaxyMessages.UnexploredMessage;
import com.birdshel.Uciana.Messages.LowCreditsMessage;
import com.birdshel.Uciana.Messages.PlanetExplorationMessage;
import com.birdshel.Uciana.Messages.RandomEventMessage;
import com.birdshel.Uciana.Messages.StarvedMessaged;
import com.birdshel.Uciana.Messages.SystemExploredMessage;
import com.birdshel.Uciana.Messages.TechDiscoveryMessage;
import com.birdshel.Uciana.Messages.Tutorials.ColonyShipArrivedTutorial;
import com.birdshel.Uciana.Messages.Tutorials.FleetMaintenanceTutorial;
import com.birdshel.Uciana.Messages.Tutorials.GalaxyViewButtonsTutorial;
import com.birdshel.Uciana.Messages.Tutorials.GalaxyViewTutorial;
import com.birdshel.Uciana.Messages.Tutorials.SystemPeekTutorial;
import com.birdshel.Uciana.Overlays.GalaxySelectOverlay;
import com.birdshel.Uciana.Overlays.GameVictoryOverlay;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Overlays.SystemPreviewOverlay;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Players.Empire;
import com.birdshel.Uciana.Players.EmpireType;
import com.birdshel.Uciana.Players.Empires;
import com.birdshel.Uciana.Players.PlayerSettings;
import com.birdshel.Uciana.Players.Treaty;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.RandomEvents.RandomEventType;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Resources.OptionID;
import com.birdshel.Uciana.Resources.Options;
import com.birdshel.Uciana.Resources.SupportedLocales;
import com.birdshel.Uciana.Resources.TutorialID;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.Ships.Ship;
import com.birdshel.Uciana.Ships.ShipSprite;
import com.birdshel.Uciana.Ships.ShipType;
import com.birdshel.Uciana.StarSystems.Blackhole;
import com.birdshel.Uciana.StarSystems.Nebulas;
import com.birdshel.Uciana.StarSystems.SpaceRift;
import com.birdshel.Uciana.StarSystems.StarSystem;
import com.birdshel.Uciana.StarSystems.SystemNameDisplay;
import com.birdshel.Uciana.Technology.TechID;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.andengine.entity.Entity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.primitive.Line;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class GalaxyScene extends ExtendedScene {
    public static final int GALAXY_STATE_FLUX = 1;
    public static final int GALAXY_STATE_NORMAL = 0;
    private VertexBufferObjectManager bufferManager;
    private TiledSprite coloniesButton;
    private TiledSprite colonyWarningIcon;
    private EmpireButton empireButton;
    public EmpireInfo empireInfo;
    private TiledSprite empireWarningIcon;
    private Text eventCountText;
    private TiledSprite eventsButton;
    public FleetControl fleetControl;
    private Point fleetControlPressedPoint;
    private int fleetIndex;
    private TiledSprite fleetsButton;
    private Entity galaxyEntity;
    private GalaxySelectOverlay galaxySelectOverlay;
    private Text galaxyStateText;
    private GameVictoryOverlay gameVictoryOverlay;
    private TiledSprite horizontalScrollBar;
    private float lastX;
    private float lastY;
    private TiledSprite menuButton;
    private Entity nebulaBackground;
    private Nebulas nebulas;
    private float oldFleetControlX;
    private float oldFleetControlY;
    private float pressedX;
    private float pressedY;
    private TiledSprite racesButton;
    private TiledSprite scienceButton;
    private TiledSprite searchIcon;
    private Sprite selectedFleet;
    private Text shipCount;
    private TiledSprite shipCountIcon;
    private SystemPreviewOverlay systemPreviewOverlay;
    private TiledSprite turnButton;
    private Text turnCountText;
    private TiledSprite verticalScrollBar;
    private int xOffset;
    private TiledSprite zoomButton;
    private Text zoomPercentText;
    private final List<StarSystem> selectedStarSystem = new ArrayList();
    private final List<Fleet> selectedFleets = new ArrayList();
    public List<AnimatedSprite> stars = new ArrayList();
    public List<TiledSprite> outOfRangeStars = new ArrayList();
    public List<Sprite> blackholes = new ArrayList();
    public List<TiledSprite> spaceRifts = new ArrayList();
    private final List<Text> starNames = new ArrayList();
    private final List<Text> turnsToList = new ArrayList();
    private final List<TiledSprite> turnsToIconList = new ArrayList();
    private final List<TiledSprite> colonyShipArrivedIcons = new ArrayList();
    private final List<TiledSprite[]> systemNameBackground = new ArrayList();
    private final List<TiledSprite[]> systemControlBackground = new ArrayList();
    private final List<Line> wormholes = new ArrayList();
    private final List<Text> exploreCounts = new ArrayList();
    private final List<TiledSprite> exploreIcons = new ArrayList();
    private final List<Text> colonizeCounts = new ArrayList();
    private final List<TiledSprite> colonizeIcons = new ArrayList();
    private final List<Text> buildOutpostCounts = new ArrayList();
    private final List<TiledSprite> buildOutpostIcons = new ArrayList();
    private final List<Color> starNameColors = new ArrayList();
    private final List<ShipSprite> fleetIcons = new ArrayList();
    private final List<FleetIconData> fleetDetails = new ArrayList();
    private final List<Line> fleetLines = new ArrayList();
    private final List<Text> etas = new ArrayList();
    private final List<String> fleetIDs = new ArrayList();
    private String selectedFleetID = "";
    public List<String> selectedShipIDs = new ArrayList();
    private final Entity buttons = new Entity();
    private boolean fleetControlPressed = false;
    private boolean fleetControlMoved = false;
    private boolean dontShowEvents = true;
    private int galaxyState = 0;
    private float zoom = 1.0f;
    private int zoomLevel = 0;
    private boolean isScroll = false;
    private boolean isPreviewOn = false;

    /* compiled from: MyApplication */
    /* renamed from: com.birdshel.Uciana.Scenes.GalaxyScene$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a */
        static final /* synthetic */ int[] f1465a;
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[EventType.values().length];
            b = iArr;
            try {
                iArr[EventType.SYSTEM_DISCOVERY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[EventType.EMPIRE_CONTACT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[EventType.TERRAFORMING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[EventType.EMPIRE_DESTROYED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[EventType.COLONY_DESTROYED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                b[EventType.OUTPOST_DESTROYED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                b[EventType.CAPITAL_DESTROYED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                b[EventType.COLONY_INVADED.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                b[EventType.BUILDING_SCRAP.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                b[EventType.TECH_BREAKTHROUGH.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                b[EventType.DIPLOMATIC.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                b[EventType.EXPLORATION.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                b[EventType.COLONY_STARVED_OFF.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                b[EventType.ASCENDED_ENCOUNTER.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                b[EventType.AI_PROPOSE_TREATIES.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                b[EventType.RANDOM_EVENT.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            int[] iArr2 = new int[ShipType.values().length];
            f1465a = iArr2;
            try {
                iArr2[ShipType.TORPEDO_TURRET.ordinal()] = 1;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f1465a[ShipType.STAR_BASE.ordinal()] = 2;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                f1465a[ShipType.TRANSPORT.ordinal()] = 3;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                f1465a[ShipType.SCOUT.ordinal()] = 4;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                f1465a[ShipType.DREADNOUGHT.ordinal()] = 5;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                f1465a[ShipType.BATTLESHIP.ordinal()] = 6;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                f1465a[ShipType.CRUISER.ordinal()] = 7;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                f1465a[ShipType.DESTROYER.ordinal()] = 8;
            } catch (NoSuchFieldError unused24) {
            }
        }
    }

    /* compiled from: MyApplication */
    /* loaded from: classes.dex */
    public static class FleetIconData {
        private final int imageIndex;
        private final float rotation;
        private final float x;
        private final float y;

        FleetIconData(float f2, float f3, Fleet fleet, int i, float f4) {
            this.x = f2;
            this.y = f3;
            this.imageIndex = getIcon(fleet, i);
            this.rotation = f4;
        }

        /* JADX WARN: Removed duplicated region for block: B:112:0x0038  */
        /* JADX WARN: Removed duplicated region for block: B:117:0x0042  */
        /* JADX WARN: Removed duplicated region for block: B:122:0x004c  */
        /* JADX WARN: Removed duplicated region for block: B:135:0x0076  */
        /* JADX WARN: Removed duplicated region for block: B:145:0x009e  */
        /* JADX WARN: Removed duplicated region for block: B:155:0x00c5  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private int getIcon(Fleet fleet, int i) {
            int i2 = fleet.empireID;
            if (i2 == 8) {
                return 54;
            }
            if (i2 == 9) {
                return 55;
            }
            ShipType largestShip = fleet.getLargestShip();
            int i3 = i2 * 8;
            if (i2 == 6) {
                i3 = 56;
            }
            switch (AnonymousClass1.f1465a[largestShip.ordinal()]) {
                case 1:
                    return GameIconEnum.TORPEDO_TURRET.ordinal();
                case 2:
                    return GameIconEnum.STAR_BASE.ordinal();
                case 3:
                    if (i2 == 6) {
                        return 64;
                    }
                    return i2 + 48;
                case 4:
                    return i3 + 5;
                case 5:
                    if (i != 0) {
                        if (i != 1) {
                            if (i != 2) {
                                if (i != 3) {
                                    if (i == 4) {
                                        return ((i3 + largestShip.ordinal()) - 1) + 3;
                                    }
                                    if (i == 0) {
                                        if (i != 1) {
                                            if (i != 2) {
                                                if (i != 3) {
                                                    if (i == 4) {
                                                        return ((i3 + largestShip.ordinal()) - 1) + 4;
                                                    }
                                                    if (i == 0) {
                                                        if (i != 1) {
                                                            if (i != 2) {
                                                                if (i != 3) {
                                                                    if (i == 4) {
                                                                        return ((i3 + largestShip.ordinal()) - 1) + 5;
                                                                    }
                                                                    if (i == 0) {
                                                                        if (i != 1) {
                                                                            if (i != 2) {
                                                                                if (i == 3) {
                                                                                    return ((i3 + largestShip.ordinal()) - 1) + 3;
                                                                                }
                                                                                if (i == 4) {
                                                                                    return ((i3 + largestShip.ordinal()) - 1) + 6;
                                                                                }
                                                                                return i3 + largestShip.ordinal() + 1;
                                                                            }
                                                                            return ((i3 + largestShip.ordinal()) - 1) + 2;
                                                                        }
                                                                        return ((i3 + largestShip.ordinal()) - 1) + 1;
                                                                    }
                                                                    return (i3 + largestShip.ordinal()) - 1;
                                                                }
                                                                return ((i3 + largestShip.ordinal()) - 1) + 2;
                                                            }
                                                            return ((i3 + largestShip.ordinal()) - 1) - 1;
                                                        }
                                                        return ((i3 + largestShip.ordinal()) - 1) + 1;
                                                    }
                                                    return (i3 + largestShip.ordinal()) - 1;
                                                }
                                                return ((i3 + largestShip.ordinal()) - 1) - 2;
                                            }
                                            return ((i3 + largestShip.ordinal()) - 1) - 1;
                                        }
                                        return ((i3 + largestShip.ordinal()) - 1) + 1;
                                    }
                                    return (i3 + largestShip.ordinal()) - 1;
                                }
                                return ((i3 + largestShip.ordinal()) - 1) - 3;
                            }
                            return ((i3 + largestShip.ordinal()) - 1) - 2;
                        }
                        return ((i3 + largestShip.ordinal()) - 1) - 1;
                    }
                    return (i3 + largestShip.ordinal()) - 1;
                case 6:
                    if (i == 0) {
                    }
                    break;
                case 7:
                    if (i == 0) {
                    }
                    break;
                case 8:
                    if (i == 0) {
                    }
                    break;
                default:
                    return i3 + largestShip.ordinal() + 1;
            }
        }

        public int getImageIndex() {
            return this.imageIndex;
        }

        public float getRotation() {
            return this.rotation;
        }

        public float getX() {
            return this.x;
        }

        public float getY() {
            return this.y;
        }
    }

    public GalaxyScene(Game game) {
        this.B = game;
    }

    private void addFleetLineEffect(Line line) {
        line.setBlendFunction(IShape.BLENDFUNCTION_SOURCE_DEFAULT, 771);
        line.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(new AlphaModifier(0.5f, 0.6f, 1.0f), new AlphaModifier(0.5f, 1.0f, 0.6f))));
    }

    private void checkActionDown(Point point) {
        if (isPositionOnFleetControl(point)) {
            this.fleetControlPressed = true;
            this.fleetControlPressedPoint = new Point(this.fleetControl.getX(), this.fleetControl.getY());
            this.oldFleetControlX = point.getX();
            this.oldFleetControlY = point.getY();
            this.I = false;
            this.fleetControl.pressed();
        }
        this.isScroll = false;
        this.isPreviewOn = false;
        this.pressedX = point.getX();
        this.pressedY = point.getY();
        this.lastX = point.getX();
        this.lastY = point.getY();
    }

    private void checkActionMove(Point point) {
        if (this.fleetControlPressed) {
            float x = this.fleetControl.getX() + (point.getX() - this.oldFleetControlX);
            float y = this.fleetControl.getY() + (point.getY() - this.oldFleetControlY);
            if (x < 0.0f) {
                x = 0.0f;
            }
            if (y < 0.0f) {
                y = 0.0f;
            }
            if (this.fleetControl.getWidth() + x > getWidth() - 120.0f) {
                x = (getWidth() - 120.0f) - this.fleetControl.getWidth();
            }
            if (this.fleetControl.getHeight() + y > 720.0f) {
                y = 720.0f - this.fleetControl.getHeight();
            }
            this.fleetControl.setControlPosition(x, y);
            float x2 = this.fleetControlPressedPoint.getX() - this.fleetControl.getX();
            float y2 = this.fleetControlPressedPoint.getY() - this.fleetControl.getY();
            if (x2 > 50.0f || x2 < -50.0f || y2 > 50.0f || y2 < -50.0f) {
                this.fleetControlMoved = true;
            }
            this.oldFleetControlX = point.getX();
            this.oldFleetControlY = point.getY();
        } else if (this.zoom == 1.0f || this.isPreviewOn || point.getX() > this.menuButton.getX()) {
        } else {
            if (this.pressedX - point.getX() > 25.0f || this.pressedX - point.getX() < -25.0f || this.pressedY - point.getY() > 25.0f || this.pressedY - point.getY() < -25.0f) {
                this.isScroll = true;
            }
            placeGalaxy(this.galaxyEntity.getX() - (this.lastX - point.getX()), this.galaxyEntity.getY() - (this.lastY - point.getY()));
            this.lastX = point.getX();
            this.lastY = point.getY();
            setScrollBar();
        }
    }

    private void checkActionUp(Point point) {
        this.I = true;
        this.fleetControlPressed = false;
        this.fleetControlMoved = false;
        this.fleetControl.unPressed();
        if (this.isScroll) {
            return;
        }
        checkButtons(point);
        if (point.getX() < this.menuButton.getX() - 10.0f) {
            checkStarsAndFleets(point);
            checkBlackholes(point);
            checkSpaceRifts(point);
        }
    }

    private void checkBlackholes(Point point) {
        if (isPositionOnFleetControl(point)) {
            return;
        }
        Point point2 = new Point(point.getX() - this.galaxyEntity.getX(), point.getY() - this.galaxyEntity.getY());
        for (int i = 0; i < 50; i++) {
            Sprite sprite = this.blackholes.get(i);
            if (sprite.isVisible()) {
                float x = (sprite.getX() + (sprite.getWidthScaled() * 0.25f)) * this.zoom;
                float y = (sprite.getY() + (sprite.getHeightScaled() * 0.25f)) * this.zoom;
                float x2 = ((sprite.getX() * this.zoom) + (sprite.getWidthScaled() * this.zoom)) - ((sprite.getWidthScaled() * 0.25f) * this.zoom);
                float y2 = ((sprite.getY() * this.zoom) + (sprite.getHeightScaled() * this.zoom)) - ((sprite.getHeightScaled() * 0.25f) * this.zoom);
                if (point2.getX() > x && point2.getX() < x2 && point2.getY() > y && point2.getY() < y2 && this.selectedFleets.isEmpty() && this.selectedStarSystem.isEmpty()) {
                    showMessage(new BlackholeMessage(this.B.galaxy.getBlackhole(i)));
                    this.B.sounds.playSystemObjectPressSound();
                    Game game = this.B;
                    game.vibrate(game.BUTTON_VIBRATE);
                }
            }
        }
    }

    private void checkButtons(Point point) {
        if (isClicked(this.menuButton, point)) {
            menuButtonPressed();
        }
        if (isClicked(this.fleetsButton, point)) {
            fleetsButtonPressed();
        }
        if (isClicked(this.empireButton, point)) {
            empireButtonPressed();
        }
        if (isClicked(this.coloniesButton, point)) {
            coloniesButtonPressed();
        }
        if (isClicked(this.racesButton, point)) {
            racesButtonPressed();
        }
        if (isClicked(this.scienceButton, point)) {
            scienceButtonPressed();
        }
        if (isClicked(this.eventsButton, point)) {
            eventsButtonPressed();
        }
        if (isClicked(this.turnButton, point)) {
            turnButtonPressed();
        }
        if (isClicked(this.zoomButton, point)) {
            zoomButtonPressed();
        }
    }

    private void checkDimmedStars(Point point) {
        if (isPositionOnFleetControl(point)) {
            return;
        }
        Point point2 = new Point(point.getX() - this.galaxyEntity.getX(), point.getY() - this.galaxyEntity.getY());
        for (int i = 0; i < this.B.galaxy.getStarCount(); i++) {
            float sizeModifier = this.zoom == 1.0f ? (this.B.galaxy.getSize().getSizeModifier() * 35.0f) - this.outOfRangeStars.get(i).getWidthScaled() : 0.0f;
            float x = this.outOfRangeStars.get(i).getX() * this.zoom;
            float y = this.outOfRangeStars.get(i).getY() * this.zoom;
            float widthScaled = (this.outOfRangeStars.get(i).getWidthScaled() * this.zoom) + sizeModifier;
            float heightScaled = (this.outOfRangeStars.get(i).getHeightScaled() * this.zoom) + sizeModifier;
            if (this.outOfRangeStars.get(i).isVisible() && point2.getX() > x && point2.getX() < x + widthScaled && point2.getY() > y && point2.getY() < y + heightScaled) {
                Game game = this.B;
                int closestEmpireSystem = game.galaxy.getClosestEmpireSystem(i, game.getCurrentEmpire().getFriendlyStarSystems());
                int distance = this.B.galaxy.getDistance(closestEmpireSystem, i);
                int fuelCellRange = this.B.getCurrentEmpire().getTech().getFuelCellRange();
                if (fuelCellRange < distance) {
                    if (!this.B.galaxy.hasWormholes(i)) {
                        showOutOfRangeMessage(i, distance, fuelCellRange);
                    } else {
                        checkWormholeEndPoints(i, fuelCellRange, distance, closestEmpireSystem);
                    }
                } else if (!this.B.fleets.get(this.selectedFleetID).canCommunicate()) {
                    showMessage(new OutOfComRangeMessage());
                } else {
                    showMessage(new NoDirectTravelMessage());
                }
                this.B.sounds.playSystemObjectPressSound();
                Game game2 = this.B;
                game2.vibrate(game2.BUTTON_VIBRATE);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void checkForWarnings() {
        this.colonyWarningIcon.setVisible(false);
        this.empireWarningIcon.setVisible(false);
        this.B.getCurrentEmpire().redistributeFood();
        for (Colony colony : this.B.getCurrentEmpire().getColonies()) {
            if (colony.isBlockaded() || colony.isStarving() || colony.isLowPower() || colony.getPopulation() == 0) {
                this.colonyWarningIcon.setVisible(true);
                break;
            }
            while (r0.hasNext()) {
            }
        }
        if (this.B.getCurrentEmpire().hasCapital()) {
            return;
        }
        this.empireWarningIcon.setVisible(true);
    }

    private void checkForWormholes(int i) {
        if (this.B.getCurrentPlayer() < 0 || this.B.getCurrentPlayer() >= 7 || !this.B.getCurrentEmpire().isDiscoveredSystem(i)) {
            return;
        }
        int i2 = 0;
        for (Point point : this.B.galaxy.getWormholes()) {
            float f2 = i;
            if (point.getX() == f2 || point.getY() == f2) {
                this.wormholes.get(i2).setVisible(true);
            }
            i2++;
        }
    }

    private void checkSpaceRifts(Point point) {
        if (isPositionOnFleetControl(point)) {
            return;
        }
        Point point2 = new Point(point.getX() - this.galaxyEntity.getX(), point.getY() - this.galaxyEntity.getY());
        for (int i = 0; i < 50; i++) {
            TiledSprite tiledSprite = this.spaceRifts.get(i);
            if (tiledSprite.isVisible() && point2.getX() > tiledSprite.getX() * this.zoom && point2.getX() < (tiledSprite.getX() * this.zoom) + (tiledSprite.getWidthScaled() * this.zoom) && point2.getY() > tiledSprite.getY() * this.zoom && point2.getY() < (tiledSprite.getY() * this.zoom) + (tiledSprite.getHeightScaled() * this.zoom) && this.selectedFleets.isEmpty() && this.selectedStarSystem.isEmpty()) {
                showMessage(new SpaceRiftMessage(this.B.galaxy.getSpaceRifts().get(i)));
                this.B.sounds.playSystemObjectPressSound();
                Game game = this.B;
                game.vibrate(game.BUTTON_VIBRATE);
            }
        }
    }

    private void checkStarsAndFleets(Point point) {
        if (isPositionOnFleetControl(point)) {
            return;
        }
        Point point2 = new Point(point.getX() - this.galaxyEntity.getX(), point.getY() - this.galaxyEntity.getY());
        this.selectedStarSystem.clear();
        for (int i = 0; i < 50; i++) {
            float sizeModifier = this.zoom == 1.0f ? (this.B.galaxy.getSize().getSizeModifier() * 35.0f) - this.stars.get(i).getWidthScaled() : 0.0f;
            float x = this.stars.get(i).getX() * this.zoom;
            float y = this.stars.get(i).getY() * this.zoom;
            float widthScaled = (this.stars.get(i).getWidthScaled() * this.zoom) + sizeModifier;
            float heightScaled = (this.stars.get(i).getHeightScaled() * this.zoom) + sizeModifier;
            if (this.stars.get(i).isVisible() && point2.getX() > x && point2.getX() < x + widthScaled && point2.getY() > y && point2.getY() < y + heightScaled) {
                this.selectedStarSystem.add(this.B.galaxy.getStarSystem(i));
            }
        }
        this.selectedFleets.clear();
        for (int i2 = 0; i2 < this.fleetIDs.size(); i2++) {
            ShipSprite shipSprite = this.fleetIcons.get(i2);
            float x2 = (shipSprite.getX() * this.zoom) - 15.0f;
            float y2 = (shipSprite.getY() * this.zoom) - 15.0f;
            float size = (shipSprite.getSize() * this.zoom) + 15.0f;
            float size2 = (shipSprite.getSize() * this.zoom) + 15.0f;
            if (shipSprite.isVisible() && point2.getX() > x2 && point2.getX() < x2 + size && point2.getY() > y2 && point2.getY() < y2 + size2 && this.B.fleets.has(this.fleetIDs.get(i2))) {
                this.selectedFleets.add(this.B.fleets.get(this.fleetIDs.get(i2)));
            }
        }
        if (this.selectedStarSystem.size() + this.selectedFleets.size() > 1) {
            this.galaxySelectOverlay.setOverlay(this.selectedStarSystem, this.selectedFleets);
            setChildScene(this.galaxySelectOverlay, false, false, true);
            this.B.sounds.playSelectPressSound();
            Game game = this.B;
            game.vibrate(game.BUTTON_VIBRATE);
        } else if (this.selectedStarSystem.size() > 0 && this.selectedFleets.isEmpty()) {
            selectSystem(this.selectedStarSystem.get(0).getID());
        } else if (this.selectedFleets.size() == 1 && this.selectedStarSystem.isEmpty()) {
            selectFleet(this.selectedFleets.get(0));
            this.B.sounds.playFleetPressSound();
            Game game2 = this.B;
            game2.vibrate(game2.BUTTON_VIBRATE);
        }
        checkDimmedStars(point2);
    }

    private void checkSystemSelected(int i) {
        if (this.B.getCurrentEmpire().isDiscoveredSystem(i)) {
            openSystem(i);
            this.B.sounds.playStarPressSound();
            Game game = this.B;
            game.vibrate(game.BUTTON_VIBRATE);
        } else if (Game.options.isOn(OptionID.DEBUG)) {
            this.starNames.get(i).setVisible(true);
            updateSystemDisplay(i);
            this.B.getCurrentEmpire().addToDiscoveredList(i);
            openSystem(i);
        } else {
            Game game2 = this.B;
            int distance = this.B.galaxy.getDistance(game2.galaxy.getClosestEmpireSystem(i, game2.getCurrentEmpire().getSystemIDs()), i);
            StarSystem starSystem = this.B.galaxy.getStarSystem(i);
            showMessage(new UnexploredMessage(starSystem.getStarType(), (starSystem.getStarType().ordinal() * 12) + (starSystem.getStarShape() * 4), distance));
            this.B.sounds.playSystemObjectPressSound();
            Game game3 = this.B;
            game3.vibrate(game3.BUTTON_VIBRATE);
        }
    }

    private void checkTurnsTo(Fleet fleet, int i, int i2) {
        if (fleet.canCommunicate()) {
            if (fleet.isMoving() || i != fleet.getSystemID()) {
                if (fleet.isPreparingToMove() && i == fleet.getOriginSystem()) {
                    return;
                }
                this.turnsToList.get(i).setVisible(true);
                this.turnsToList.get(i).setText(Integer.toString(i2));
                float x = (this.stars.get(i).getX() + (this.stars.get(i).getWidthScaled() / 2.0f)) - ((this.turnsToList.get(i).getWidth() + 22.0f) / 2.0f);
                this.turnsToList.get(i).setPosition(x, this.stars.get(i).getY() - 10.0f);
                this.turnsToIconList.get(i).setVisible(true);
                this.turnsToIconList.get(i).setPosition(x + this.turnsToList.get(i).getWidthScaled() + 5.0f, this.stars.get(i).getY() - 10.0f);
            }
        }
    }

    private void checkWormholeEndPoints(int i, int i2, int i3, int i4) {
        boolean isDiscoveredSystem = this.B.getCurrentEmpire().isDiscoveredSystem(i);
        for (Integer num : this.B.galaxy.getWormholes(i)) {
            if (this.B.getCurrentEmpire().isDiscoveredSystem(num.intValue())) {
                isDiscoveredSystem = true;
            }
        }
        if (isDiscoveredSystem) {
            boolean z = false;
            for (Integer num2 : this.B.galaxy.getWormholes(i)) {
                int distance = this.B.galaxy.getDistance(i4, num2.intValue());
                if (distance < i3) {
                    i3 = distance;
                }
                if (i2 >= distance) {
                    z = true;
                }
            }
            if (z) {
                showMessage(new NoDirectTravelMessage());
                return;
            } else {
                showOutOfRangeMessage(i, i3, i2);
                return;
            }
        }
        showOutOfRangeMessage(i, i3, i2);
    }

    private void clearGalaxyInfo() {
        for (TiledSprite[] tiledSpriteArr : this.systemControlBackground) {
            for (TiledSprite tiledSprite : tiledSpriteArr) {
                tiledSprite.setVisible(false);
            }
        }
        for (TiledSprite[] tiledSpriteArr2 : this.systemNameBackground) {
            for (TiledSprite tiledSprite2 : tiledSpriteArr2) {
                tiledSprite2.setVisible(false);
            }
        }
        for (Text text : this.starNames) {
            text.setVisible(false);
        }
        for (Line line : this.wormholes) {
            line.setVisible(false);
        }
        for (Text text2 : this.exploreCounts) {
            text2.setVisible(false);
        }
        for (TiledSprite tiledSprite3 : this.exploreIcons) {
            tiledSprite3.setVisible(false);
        }
        for (Text text3 : this.colonizeCounts) {
            text3.setVisible(false);
        }
        for (TiledSprite tiledSprite4 : this.colonizeIcons) {
            tiledSprite4.setVisible(false);
        }
        for (Text text4 : this.buildOutpostCounts) {
            text4.setVisible(false);
        }
        for (TiledSprite tiledSprite5 : this.buildOutpostIcons) {
            tiledSprite5.setVisible(false);
        }
    }

    private void coloniesButtonPressed() {
        changeScene(this.B.coloniesScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void createButtonControl(VertexBufferObjectManager vertexBufferObjectManager) {
        this.G.setSize(111.0f, 80.0f);
        TiledSprite tiledSprite = new TiledSprite((getWidth() - 111.0f) - this.xOffset, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager);
        this.menuButton = tiledSprite;
        tiledSprite.setSize(111.0f, 80.0f);
        this.menuButton.setCurrentTileIndex(ButtonsEnum.MENU.ordinal());
        this.buttons.attachChild(this.menuButton);
        s(this.menuButton);
        TiledSprite tiledSprite2 = new TiledSprite((getWidth() - 111.0f) - this.xOffset, 80.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager);
        this.zoomButton = tiledSprite2;
        tiledSprite2.setCurrentTileIndex(ButtonsEnum.ZOOM.ordinal());
        this.zoomButton.setSize(111.0f, 80.0f);
        this.buttons.attachChild(this.zoomButton);
        s(this.zoomButton);
        TiledSprite tiledSprite3 = new TiledSprite((getWidth() - 111.0f) - this.xOffset, 160.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager);
        this.fleetsButton = tiledSprite3;
        tiledSprite3.setCurrentTileIndex(ButtonsEnum.FLEETS.ordinal());
        this.fleetsButton.setSize(111.0f, 80.0f);
        this.buttons.attachChild(this.fleetsButton);
        s(this.fleetsButton);
        EmpireButton empireButton = new EmpireButton(this.B, vertexBufferObjectManager);
        this.empireButton = empireButton;
        empireButton.setPosition((getWidth() - 111.0f) - this.xOffset, 240.0f);
        this.empireButton.setScale(0.93f);
        this.buttons.attachChild(this.empireButton);
        s(this.empireButton);
        TiledSprite tiledSprite4 = new TiledSprite((getWidth() - 111.0f) - this.xOffset, 320.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager);
        this.coloniesButton = tiledSprite4;
        tiledSprite4.setCurrentTileIndex(ButtonsEnum.COLONIES.ordinal());
        this.coloniesButton.setSize(111.0f, 80.0f);
        this.buttons.attachChild(this.coloniesButton);
        s(this.coloniesButton);
        TiledSprite tiledSprite5 = new TiledSprite((getWidth() - 111.0f) - this.xOffset, 400.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager);
        this.racesButton = tiledSprite5;
        tiledSprite5.setCurrentTileIndex(ButtonsEnum.RACES.ordinal());
        this.racesButton.setSize(111.0f, 80.0f);
        this.buttons.attachChild(this.racesButton);
        s(this.racesButton);
        TiledSprite tiledSprite6 = new TiledSprite((getWidth() - 111.0f) - this.xOffset, 480.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager);
        this.scienceButton = tiledSprite6;
        tiledSprite6.setCurrentTileIndex(ButtonsEnum.SCIENCE.ordinal());
        this.scienceButton.setSize(111.0f, 80.0f);
        this.buttons.attachChild(this.scienceButton);
        s(this.scienceButton);
        TiledSprite tiledSprite7 = new TiledSprite((getWidth() - 111.0f) - this.xOffset, 560.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager);
        this.eventsButton = tiledSprite7;
        tiledSprite7.setCurrentTileIndex(ButtonsEnum.EVENTS.ordinal());
        this.eventsButton.setSize(111.0f, 80.0f);
        this.buttons.attachChild(this.eventsButton);
        s(this.eventsButton);
        TiledSprite tiledSprite8 = new TiledSprite((getWidth() - 111.0f) - this.xOffset, 640.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager);
        this.turnButton = tiledSprite8;
        tiledSprite8.setCurrentTileIndex(ButtonsEnum.TURN.ordinal());
        this.turnButton.setSize(111.0f, 80.0f);
        this.buttons.attachChild(this.turnButton);
        s(this.turnButton);
        Text text = new Text(0.0f, 0.0f, this.B.fonts.smallInfoFont, "###%", this.E, vertexBufferObjectManager);
        this.zoomPercentText = text;
        text.setScaleCenter(0.0f, 0.0f);
        this.zoomPercentText.setScale(0.9f);
        this.zoomButton.attachChild(this.zoomPercentText);
        Text text2 = new Text(0.0f, 0.0f, this.B.fonts.smallInfoFont, this.D, this.E, vertexBufferObjectManager);
        this.eventCountText = text2;
        text2.setVisible(false);
        this.buttons.attachChild(this.eventCountText);
        Text text3 = new Text(0.0f, 0.0f, this.B.fonts.smallInfoFont, this.D, this.E, vertexBufferObjectManager);
        this.turnCountText = text3;
        this.buttons.attachChild(text3);
        TiledSprite tiledSprite9 = new TiledSprite(80.0f, 0.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.colonyWarningIcon = tiledSprite9;
        InfoIconEnum infoIconEnum = InfoIconEnum.WARNING;
        tiledSprite9.setCurrentTileIndex(infoIconEnum.ordinal());
        this.colonyWarningIcon.setVisible(false);
        blinkSprite(this.colonyWarningIcon);
        this.coloniesButton.attachChild(this.colonyWarningIcon);
        TiledSprite tiledSprite10 = new TiledSprite(80.0f, 0.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.empireWarningIcon = tiledSprite10;
        tiledSprite10.setCurrentTileIndex(infoIconEnum.ordinal());
        this.empireWarningIcon.setVisible(false);
        blinkSprite(this.empireWarningIcon);
        this.empireButton.attachChild(this.empireWarningIcon);
        attachChild(this.buttons);
        this.searchIcon = H((getWidth() - this.xOffset) - 110.0f, 25.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, GameIconEnum.SYSTEM_SEARCH.ordinal(), false);
    }

    private void createFleets(VertexBufferObjectManager vertexBufferObjectManager) {
        Sprite sprite = new Sprite(0.0f, 0.0f, this.B.graphics.selectedFleetTexture, vertexBufferObjectManager);
        this.selectedFleet = sprite;
        this.galaxyEntity.attachChild(sprite);
        blinkSprite(this.selectedFleet);
        Text text = new Text(0.0f, 0.0f, this.B.fonts.smallInfoFont, this.D, this.E, vertexBufferObjectManager);
        this.shipCount = text;
        text.setColor(new Color(0.8f, 0.8f, 0.8f));
        this.shipCount.setZIndex(19998);
        this.galaxyEntity.attachChild(this.shipCount);
        TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.shipCountIcon = tiledSprite;
        tiledSprite.setCurrentTileIndex(InfoIconEnum.SHIP.ordinal());
        this.shipCountIcon.setSize(17.0f, 17.0f);
        this.shipCountIcon.setColor(new Color(0.8f, 0.8f, 0.8f));
        this.galaxyEntity.attachChild(this.shipCountIcon);
        FleetControl fleetControl = new FleetControl(this.B, vertexBufferObjectManager, this.xOffset);
        this.fleetControl = fleetControl;
        fleetControl.setZIndex(19999);
        attachChild(this.fleetControl);
    }

    private void createNebulas() {
        Entity entity = new Entity();
        this.nebulaBackground = entity;
        this.galaxyEntity.attachChild(entity);
    }

    private void createOverlays(VertexBufferObjectManager vertexBufferObjectManager) {
        this.galaxySelectOverlay = new GalaxySelectOverlay(this.B, vertexBufferObjectManager);
        this.H = new MessageOverlay(this.B, vertexBufferObjectManager);
        this.gameVictoryOverlay = new GameVictoryOverlay(this.B, vertexBufferObjectManager);
        SystemPreviewOverlay systemPreviewOverlay = new SystemPreviewOverlay(this.B, vertexBufferObjectManager, this.xOffset);
        this.systemPreviewOverlay = systemPreviewOverlay;
        systemPreviewOverlay.setX(((getWidth() - 120.0f) / 2.0f) - 389.0f);
    }

    private void createStars(VertexBufferObjectManager vertexBufferObjectManager) {
        for (int i = 0; i < 50; i++) {
            TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, this.B.graphics.outOfRangeStarTexture, vertexBufferObjectManager);
            tiledSprite.setScaleCenter(0.0f, 0.0f);
            this.galaxyEntity.attachChild(tiledSprite);
            this.outOfRangeStars.add(tiledSprite);
            Sprite sprite = new Sprite(0.0f, 0.0f, this.B.graphics.blackholeTexture, vertexBufferObjectManager);
            sprite.setScaleCenter(0.0f, 0.0f);
            this.galaxyEntity.attachChild(sprite);
            sprite.setAlpha(0.8f);
            this.blackholes.add(sprite);
            TiledSprite tiledSprite2 = new TiledSprite(0.0f, 0.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
            tiledSprite2.setScaleCenter(0.0f, 0.0f);
            tiledSprite2.setCurrentTileIndex(GameIconEnum.SPACE_RIFT.ordinal());
            this.galaxyEntity.attachChild(tiledSprite2);
            this.spaceRifts.add(tiledSprite2);
            TiledSprite[] tiledSpriteArr = new TiledSprite[5];
            for (int i2 = 0; i2 < 5; i2++) {
                tiledSpriteArr[i2] = new TiledSprite(0.0f, 0.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
                this.galaxyEntity.attachChild(tiledSpriteArr[i2]);
            }
            this.systemNameBackground.add(tiledSpriteArr);
            Text text = new Text(0.0f, 0.0f, this.B.fonts.smallInfoFont, this.D, this.E, vertexBufferObjectManager);
            if (this.B.getLocale() == SupportedLocales.JAPANESE) {
                text.setScale(0.8f);
            }
            this.galaxyEntity.attachChild(text);
            this.starNames.add(text);
            this.starNameColors.add(Color.WHITE);
            Text text2 = new Text(0.0f, 0.0f, this.B.fonts.smallInfoFont, "0", this.E, vertexBufferObjectManager);
            this.galaxyEntity.attachChild(text2);
            this.exploreCounts.add(text2);
            TiledSprite tiledSprite3 = new TiledSprite(0.0f, 0.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
            tiledSprite3.setCurrentTileIndex(InfoIconEnum.SCOUT_ICON.ordinal());
            this.galaxyEntity.attachChild(tiledSprite3);
            this.exploreIcons.add(tiledSprite3);
            Text text3 = new Text(0.0f, 0.0f, this.B.fonts.smallInfoFont, "0", this.E, vertexBufferObjectManager);
            this.galaxyEntity.attachChild(text3);
            this.colonizeCounts.add(text3);
            TiledSprite tiledSprite4 = new TiledSprite(0.0f, 0.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
            tiledSprite4.setCurrentTileIndex(InfoIconEnum.COLONY_ICON.ordinal());
            this.galaxyEntity.attachChild(tiledSprite4);
            this.colonizeIcons.add(tiledSprite4);
            Text text4 = new Text(0.0f, 0.0f, this.B.fonts.smallInfoFont, "0", this.E, vertexBufferObjectManager);
            this.galaxyEntity.attachChild(text4);
            this.buildOutpostCounts.add(text4);
            TiledSprite tiledSprite5 = new TiledSprite(0.0f, 0.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
            tiledSprite5.setCurrentTileIndex(InfoIconEnum.WORKER_ICON.ordinal());
            this.galaxyEntity.attachChild(tiledSprite5);
            this.buildOutpostIcons.add(tiledSprite5);
        }
        for (int i3 = 0; i3 < 50; i3++) {
            Text text5 = new Text(0.0f, 0.0f, this.B.fonts.smallInfoFont, this.D, this.E, vertexBufferObjectManager);
            this.galaxyEntity.attachChild(text5);
            Color color = Color.CYAN;
            text5.setColor(color);
            this.turnsToList.add(text5);
            TiledSprite tiledSprite6 = new TiledSprite(0.0f, 0.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
            tiledSprite6.setCurrentTileIndex(InfoIconEnum.TURN.ordinal());
            tiledSprite6.setSize(17.0f, 17.0f);
            tiledSprite6.setColor(color);
            this.galaxyEntity.attachChild(tiledSprite6);
            this.turnsToIconList.add(tiledSprite6);
            TiledSprite tiledSprite7 = new TiledSprite(0.0f, 0.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager);
            tiledSprite7.setCurrentTileIndex(InfoIconEnum.COLONY_SHIP_ARRIVED.ordinal());
            tiledSprite7.setSize(15.0f, 15.0f);
            blinkSprite(tiledSprite7);
            this.galaxyEntity.attachChild(tiledSprite7);
            this.colonyShipArrivedIcons.add(tiledSprite7);
        }
    }

    private void createSystemControl(VertexBufferObjectManager vertexBufferObjectManager) {
        for (int i = 0; i < 50; i++) {
            TiledSprite[] tiledSpriteArr = new TiledSprite[3];
            for (int i2 = 0; i2 < 3; i2++) {
                tiledSpriteArr[i2] = new TiledSprite(0.0f, 0.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
                tiledSpriteArr[i2].setCurrentTileIndex(GameIconEnum.SYSTEM_CONTROL.ordinal());
                this.galaxyEntity.attachChild(tiledSpriteArr[i2]);
            }
            this.systemControlBackground.add(tiledSpriteArr);
        }
    }

    private void createWormholes(VertexBufferObjectManager vertexBufferObjectManager) {
        for (int i = 0; i < 13; i++) {
            Line line = new Line(0.0f, 0.0f, 0.0f, 0.0f, vertexBufferObjectManager);
            line.setAlpha(0.4f);
            line.setLineWidth(2.0f);
            line.setVisible(false);
            this.galaxyEntity.attachChild(line);
            this.wormholes.add(line);
        }
    }

    private void dimStar(int i) {
        this.stars.get(i).setVisible(false);
        this.outOfRangeStars.get(i).setVisible(true);
        this.starNames.get(i).setColor(new Color(0.625f, 0.625f, 0.625f));
        this.exploreCounts.get(i).setAlpha(0.4f);
        this.exploreIcons.get(i).setAlpha(0.4f);
        this.colonizeCounts.get(i).setAlpha(0.4f);
        this.colonizeIcons.get(i).setAlpha(0.4f);
        this.buildOutpostCounts.get(i).setAlpha(0.4f);
        this.buildOutpostIcons.get(i).setAlpha(0.4f);
    }

    private void dimStarsOutOfRange() {
        Fleet fleet = this.B.fleets.get(this.selectedFleetID);
        undimStars();
        if (fleet.empireID != this.B.getCurrentPlayer()) {
            return;
        }
        Empire currentEmpire = this.B.getCurrentEmpire();
        int fuelCellRange = currentEmpire.getTech().getFuelCellRange();
        int systemID = fleet.getSystemID();
        if (fleet.isPreparingToMove()) {
            systemID = fleet.getOriginSystem();
        } else if (fleet.isMoving()) {
            systemID = this.B.galaxy.getClosestSystem(fleet.getPosition());
        }
        List<Integer> systemsInRange = this.B.galaxy.getSystemsInRange(systemID, currentEmpire.id, fuelCellRange);
        for (int i = 0; i < this.B.galaxy.getStarCount(); i++) {
            int turnsToSystem = fleet.getTurnsToSystem(i);
            if (fleet.canCommunicate() && systemsInRange.contains(Integer.valueOf(i))) {
                checkTurnsTo(fleet, i, turnsToSystem);
                this.starNames.get(i).setColor(Color.WHITE);
            } else {
                dimStar(i);
            }
        }
        if (!fleet.isMoving() || fleet.isPreparingToMove()) {
            StarSystem starSystem = this.B.galaxy.getStarSystem(fleet.getOriginSystem());
            if (!fleet.isMoving()) {
                starSystem = this.B.galaxy.getStarSystem(fleet.getSystemID());
            }
            if (starSystem.hasWormholes()) {
                for (Integer num : this.B.galaxy.getWormholes(starSystem.getID())) {
                    int intValue = num.intValue();
                    this.stars.get(intValue).setVisible(true);
                    this.outOfRangeStars.get(intValue).setVisible(false);
                    this.starNames.get(intValue).setColor(Color.WHITE);
                    checkTurnsTo(fleet, intValue, fleet.getTurnsToSystem(intValue));
                }
            }
        }
    }

    private void empireButtonPressed() {
        changeScene(this.B.empireScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void eventsButtonPressed() {
        Game game = this.B;
        if (game.events.getCount(game.getCurrentPlayer()) == 1) {
            handleEvent();
        } else {
            changeScene(this.B.eventsScene);
        }
        this.B.sounds.playButtonPressSound();
        Game game2 = this.B;
        game2.vibrate(game2.BUTTON_VIBRATE);
    }

    private void fleetsButtonPressed() {
        changeScene(this.B.fleetsScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private int getFleetIconIndex(String str) {
        int i = 0;
        for (String str2 : this.fleetIDs) {
            if (str.equals(str2)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private void handleEvent() {
        Game game = this.B;
        Event event = game.events.getEvents(game.getCurrentPlayer()).get(0);
        switch (AnonymousClass1.b[event.getEventType().ordinal()]) {
            case 1:
                int intValue = ((Integer) event.getEventData(EventDataFields.SYSTEM_ID)).intValue();
                Game game2 = this.B;
                game2.fleets.removeColonyShipArrived(game2.getCurrentPlayer(), intValue);
                changeScene(this.B.systemScene, Integer.valueOf(intValue));
                break;
            case 2:
                int intValue2 = ((Integer) event.getEventData(EventDataFields.EMPIRE_ID)).intValue();
                if (this.B.empires.get(intValue2).isAlive()) {
                    changeScene(this.B.raceScene, Integer.valueOf(intValue2));
                    break;
                } else {
                    changeScene(this.B.racesScene);
                    break;
                }
            case 3:
                changeScene(this.B.planetScene, new Point(((Integer) event.getEventData(EventDataFields.SYSTEM_ID)).intValue(), ((Integer) event.getEventData(EventDataFields.ORBIT)).intValue()));
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                changeScene(this.B.eventsScene);
                return;
        }
        this.B.events.removeEvent(event);
    }

    private boolean isPositionOnFleetControl(Point point) {
        return this.selectedFleet.isVisible() && point.getX() + ((float) this.xOffset) > this.fleetControl.getX() && point.getX() + ((float) this.xOffset) < this.fleetControl.getX() + this.fleetControl.getWidth() && point.getY() > this.fleetControl.getY() && point.getY() < this.fleetControl.getY() + this.fleetControl.getHeight();
    }

    public /* synthetic */ void lambda$releasePoolElements$0(ExtendedScene extendedScene, Object obj) {
        this.nebulaBackground.detachChild(this.nebulas);
        for (AnimatedSprite animatedSprite : this.stars) {
            this.galaxyEntity.detachChild(animatedSprite);
            this.B.starSpritePool.push(animatedSprite);
        }
        this.stars.clear();
        for (ShipSprite shipSprite : this.fleetIcons) {
            this.galaxyEntity.detachChild(shipSprite);
            this.B.shipSpritePool.push(shipSprite);
        }
        this.fleetIcons.clear();
        this.fleetIndex = 0;
        this.fleetControl.releasePoolElements(extendedScene, obj);
        this.systemPreviewOverlay.releasePoolElements();
        extendedScene.getPoolElements();
        M(extendedScene, obj);
        this.B.setCurrentScene(extendedScene);
    }

    private void menuButtonPressed() {
        changeScene(this.B.menuScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void moveFleet(Fleet fleet, int i) {
        if (fleet.canCommunicate()) {
            if (this.selectedShipIDs.size() == fleet.getSize()) {
                fleet.move(i);
            } else {
                Fleet fleet2 = new Fleet(this.B.getCurrentPlayer(), fleet.getOriginSystem());
                this.B.fleets.add(fleet2);
                fleet2.setPosition(new Point(fleet.getPosition().getX(), fleet.getPosition().getY()));
                fleet2.set(fleet.isMoving(), fleet.inOrbit());
                for (String str : this.selectedShipIDs) {
                    fleet2.getShips().add(fleet.getShip(str));
                    fleet.removeShip(str);
                }
                fleet2.move(i);
            }
            this.B.getCurrentEmpire().setSelectedFleetID("none");
            this.fleetControl.close();
            setFleetIcons();
            this.B.sounds.playMoveFleetSound();
            Game game = this.B;
            game.vibrate(game.BUTTON_VIBRATE);
        }
    }

    private void openSystem(int i) {
        if (this.colonyShipArrivedIcons.get(i).isVisible()) {
            this.colonyShipArrivedIcons.get(i).setVisible(false);
            Game game = this.B;
            game.fleets.removeColonyShipArrived(game.getCurrentPlayer(), i);
        }
        changeScene(this.B.systemScene, Integer.valueOf(i));
    }

    private void placeGalaxy(float f2, float f3) {
        if (f2 > 0.0f) {
            f2 = 0.0f;
        }
        if (f3 > 0.0f) {
            f3 = 0.0f;
        }
        float x = ((this.verticalScrollBar.getX() * this.zoom) - this.verticalScrollBar.getX()) * (-1.0f);
        float f4 = ((this.zoom * 720.0f) - 720.0f) * (-1.0f);
        if (f2 < x) {
            f2 = x;
        }
        if (f3 < f4) {
            f3 = f4;
        }
        this.galaxyEntity.setPosition(f2, f3);
        Game game = this.B;
        PlayerSettings playerSettings = game.playerSettings.get(Integer.valueOf(game.getCurrentPlayer()));
        playerSettings.setZoomLevel(this.zoomLevel);
        playerSettings.setGalaxyX((int) f2);
        playerSettings.setGalaxyY((int) f3);
        setScrollBar();
    }

    private void racesButtonPressed() {
        changeScene(this.B.racesScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void scienceButtonPressed() {
        this.B.techScene.set();
        changeScene(this.B.techScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void set() {
        setFleetIcons();
        setColonyShipArrivedIcons();
        setScene();
        if (GameData.turn == 1) {
            blinkSprite(this.turnButton);
        } else {
            this.turnButton.clearEntityModifiers();
        }
        setGalaxyStateText();
    }

    private void setBlackholes() {
        for (Blackhole blackhole : this.B.galaxy.getBlackholes()) {
            Point position = blackhole.getPosition();
            Sprite sprite = this.blackholes.get(blackhole.getID());
            sprite.setPosition(position.getX() - (blackhole.getSize() * 0.25f), position.getY() - (blackhole.getSize() * 0.25f));
            sprite.setSize(blackhole.getSize(), blackhole.getSize());
            sprite.setVisible(true);
            sprite.setFlippedHorizontal(false);
            float f2 = 0.0f;
            float f3 = 360.0f;
            if (blackhole.hasAltSpinDirection()) {
                sprite.setFlippedHorizontal(true);
            } else {
                f2 = 360.0f;
                f3 = 0.0f;
            }
            sprite.setRotationCenter(sprite.getWidthScaled() / 2.0f, sprite.getHeightScaled() / 2.0f);
            sprite.clearEntityModifiers();
            sprite.registerEntityModifier(new LoopEntityModifier(new RotationModifier(blackhole.getSpinSpeed(), f2, f3)));
        }
    }

    private void setColonyShipArrivedIcons() {
        for (TiledSprite tiledSprite : this.colonyShipArrivedIcons) {
            tiledSprite.setVisible(false);
        }
        Game game = this.B;
        for (Integer num : game.fleets.getColonyShipArrivedLocations(game.getCurrentPlayer())) {
            this.colonyShipArrivedIcons.get(num.intValue()).setVisible(true);
        }
    }

    private void setCreditsWarning() {
        if (this.B.getCurrentEmpire().willHaveCreditTrouble()) {
            this.turnButton.setCurrentTileIndex(ButtonsEnum.CREDITS_WARN.ordinal());
        }
    }

    private void setEventTurnButton() {
        this.eventsButton.setAlpha(0.4f);
        this.eventCountText.setVisible(false);
        Game game = this.B;
        int count = game.events.getCount(game.getCurrentPlayer());
        if (count > 0) {
            this.eventsButton.setAlpha(1.0f);
        }
        if (count > 1) {
            this.eventCountText.setVisible(true);
            this.eventCountText.setText(Integer.toString(count));
            this.eventCountText.setX(((this.eventsButton.getX() + this.eventsButton.getWidthScaled()) - 20.0f) - this.eventCountText.getWidthScaled());
            this.eventCountText.setY(((this.eventsButton.getY() + this.eventsButton.getHeightScaled()) - 20.0f) - this.eventCountText.getHeightScaled());
        }
        if (count == 1 && this.turnButton.getCurrentTileIndex() == ButtonsEnum.EVENTS.ordinal()) {
            this.eventsButton.setAlpha(0.4f);
        }
    }

    private void setEventsToShow() {
        Game game = this.B;
        List<Event> startOfTurnEvents = game.events.getStartOfTurnEvents(game.getCurrentPlayer());
        ArrayList arrayList = new ArrayList();
        for (Event event : startOfTurnEvents) {
            int i = AnonymousClass1.b[event.getEventType().ordinal()];
            if (i == 1) {
                arrayList.add(new SystemExploredMessage(((Integer) event.getEventData(EventDataFields.SYSTEM_ID)).intValue(), true));
                event.beenPreviewed();
            } else if (i == 2) {
                arrayList.add(new DiplomaticMessage(((Integer) event.getEventData(EventDataFields.EMPIRE_ID)).intValue(), DiplomaticType.FIRST_CONTACT));
                event.beenPreviewed();
            } else if (i != 4) {
                switch (i) {
                    case 10:
                        arrayList.add(new TechDiscoveryMessage(((Integer) event.getEventData(EventDataFields.TECH_ID)).intValue(), ((Integer) event.getEventData(EventDataFields.TECH_FROM)).intValue()));
                        this.B.events.removeEvent(event);
                        continue;
                    case 11:
                        int intValue = ((Integer) event.getEventData(EventDataFields.EMPIRE_ID)).intValue();
                        if (intValue < 7) {
                            DiplomaticType diplomaticType = DiplomaticType.values()[((Integer) event.getEventData(EventDataFields.DIPLOMATIC_TYPE)).intValue()];
                            if (diplomaticType == DiplomaticType.BREAK_TREATY) {
                                arrayList.add(new DiplomaticMessage(intValue, Treaty.values()[((Integer) event.getEventData(EventDataFields.TREATY)).intValue()]));
                            } else {
                                arrayList.add(new DiplomaticMessage(intValue, diplomaticType));
                            }
                        }
                        this.B.events.removeEvent(event);
                        continue;
                    case 12:
                        int intValue2 = ((Integer) event.getEventData(EventDataFields.SYSTEM_ID)).intValue();
                        int intValue3 = ((Integer) event.getEventData(EventDataFields.ORBIT)).intValue();
                        arrayList.add(new PlanetExplorationMessage((Planet) this.B.galaxy.getSystemObject(intValue2, intValue3), this.B.getActivity().getString(R.string.exploration_explorers), ((Boolean) event.getEventData(EventDataFields.UNEXPLORED)).booleanValue()));
                        this.B.events.removeEvent(event);
                        continue;
                    case 13:
                        arrayList.add(new StarvedMessaged((Planet) this.B.galaxy.getSystemObject(((Integer) event.getEventData(EventDataFields.SYSTEM_ID)).intValue(), ((Integer) event.getEventData(EventDataFields.ORBIT)).intValue())));
                        this.B.events.removeEvent(event);
                        continue;
                    case 14:
                        arrayList.add(new AscendedEncounterMessage(((Integer) event.getEventData(EventDataFields.SYSTEM_ID)).intValue()));
                        this.B.events.removeEvent(event);
                        continue;
                    case 15:
                        int intValue4 = ((Integer) event.getEventData(EventDataFields.EMPIRE_ID)).intValue();
                        Treaty treaty = Treaty.values()[((Integer) event.getEventData(EventDataFields.TREATY)).intValue()];
                        if (this.B.getCurrentEmpire().isAtWar(intValue4)) {
                            if (treaty == Treaty.PEACE_TREATY) {
                                arrayList.add(new AIProposeTreatyMessage(intValue4, treaty));
                                this.B.events.removeEvent(event);
                                break;
                            } else {
                                break;
                            }
                        } else {
                            arrayList.add(new AIProposeTreatyMessage(intValue4, treaty));
                            this.B.events.removeEvent(event);
                            continue;
                        }
                    case 16:
                        RandomEventType randomEventType = RandomEventType.values()[((Integer) event.getEventData(EventDataFields.RANDOM_EVENT_TYPE)).intValue()];
                        int intValue5 = ((Integer) event.getEventData(EventDataFields.RANDOM_EVENT_DATA1)).intValue();
                        int intValue6 = ((Integer) event.getEventData(EventDataFields.RANDOM_EVENT_DATA2)).intValue();
                        int intValue7 = ((Integer) event.getEventData(EventDataFields.RANDOM_EVENT_DATA3)).intValue();
                        int intValue8 = ((Integer) event.getEventData(EventDataFields.RANDOM_EVENT_MESSAGE_TYPE)).intValue();
                        if (randomEventType == RandomEventType.HYPER_SPACE_FLUCTUATIONS && intValue8 == 1) {
                            if (intValue7 == this.B.getCurrentPlayer()) {
                                arrayList.add(new RandomEventMessage(randomEventType, intValue8, intValue5, intValue6, intValue7));
                            }
                        } else if (randomEventType == RandomEventType.COLONY_REVOLT) {
                            if (this.B.colonies.isColony(intValue5, intValue6)) {
                                arrayList.add(new RandomEventMessage(randomEventType, intValue8, intValue5, intValue6, intValue7));
                            }
                        } else {
                            arrayList.add(new RandomEventMessage(randomEventType, intValue8, intValue5, intValue6, intValue7));
                        }
                        this.B.events.removeEvent(event);
                        continue;
                }
            } else {
                int intValue9 = ((Integer) event.getEventData(EventDataFields.EMPIRE_ID)).intValue();
                Empire currentEmpire = this.B.getCurrentEmpire();
                if (currentEmpire.getEmpireSetting("seen_destroyed_message_" + intValue9) == 0) {
                    Empire currentEmpire2 = this.B.getCurrentEmpire();
                    currentEmpire2.setEmpireSetting("seen_destroyed_message_" + intValue9, 1);
                    arrayList.add(new EmpireDestroyedMessage(intValue9, ((Integer) event.getEventData(EventDataFields.WHY)).intValue()));
                }
                this.B.events.removeEvent(event);
            }
        }
        Options options = Game.options;
        TutorialID tutorialID = TutorialID.GALAXY;
        if (options.shouldTutorialBeShown(tutorialID)) {
            arrayList.add(new GalaxyViewTutorial());
            Game.options.markSeen(tutorialID);
            arrayList.add(new GalaxyViewButtonsTutorial(this.B.getCurrentPlayer()));
            Game.options.markSeen(TutorialID.GALAXY_BUTTONS);
        }
        Game game2 = this.B;
        if (!game2.fleets.getColonyShipArrivedLocations(game2.getCurrentPlayer()).isEmpty()) {
            Options options2 = Game.options;
            TutorialID tutorialID2 = TutorialID.COLONY_SHIP_ARRIVED;
            if (options2.shouldTutorialBeShown(tutorialID2)) {
                arrayList.add(new ColonyShipArrivedTutorial());
                Game.options.markSeen(tutorialID2);
            }
        }
        if (GameData.turn == 10) {
            Options options3 = Game.options;
            TutorialID tutorialID3 = TutorialID.SYSTEM_PEEK;
            if (options3.shouldTutorialBeShown(tutorialID3)) {
                arrayList.add(new SystemPeekTutorial());
                Game.options.markSeen(tutorialID3);
            }
        }
        if (this.B.getCurrentEmpire().getAvailableCommandPoints() < 0) {
            Options options4 = Game.options;
            TutorialID tutorialID4 = TutorialID.FLEET_MAINTENANCE;
            if (options4.shouldTutorialBeShown(tutorialID4)) {
                arrayList.add(new FleetMaintenanceTutorial());
                Game.options.markSeen(tutorialID4);
            }
        }
        if (this.B.getHumanPlayers().isEmpty()) {
            this.B.setGameEnded(true);
            Game.gameAchievements.gameOver();
        } else {
            ArrayList<Integer> arrayList2 = new ArrayList();
            for (int i2 = 0; i2 < 7; i2++) {
                if (this.B.empires.get(i2).isAlive()) {
                    arrayList2.add(Integer.valueOf(i2));
                }
            }
            if (arrayList2.size() == 1 && this.B.getCurrentPlayer() == ((Integer) arrayList2.get(0)).intValue()) {
                this.B.setGameEnded(true);
                Game.gameAchievements.checkForEmpireVictory(((Integer) arrayList2.get(0)).intValue(), 0);
                this.gameVictoryOverlay.setOverlay(((Integer) arrayList2.get(0)).intValue(), 0);
                setChildScene(this.gameVictoryOverlay, false, false, true);
                return;
            } else if (!arrayList2.isEmpty() && GameData.hasCoalitionVictoryBeenAchieved()) {
                if (arrayList2.contains(Integer.valueOf(this.B.getCurrentPlayer())) && this.B.getCurrentEmpire().getEmpireSetting("seen_coalition_victory") == 0) {
                    for (Integer num : arrayList2) {
                        int intValue10 = num.intValue();
                        this.B.empires.get(intValue10).setEmpireSetting("seen_coalition_victory", 1);
                        if (this.B.empires.get(intValue10).isHuman()) {
                            Game.gameAchievements.checkForEmpireVictory(intValue10, 1);
                        }
                    }
                }
                this.gameVictoryOverlay.setOverlay(((Integer) arrayList2.get(0)).intValue(), 1);
                setChildScene(this.gameVictoryOverlay, false, false, true);
                return;
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        this.H.setOverlay(arrayList);
        setChildScene(this.H, false, false, true);
        this.dontShowEvents = true;
    }

    private void setFleetIcons() {
        this.selectedFleet.setVisible(false);
        undimStars();
        this.shipCount.setVisible(false);
        this.shipCountIcon.setVisible(false);
        float shipSize = this.B.galaxy.getSize().getShipSize() * 1.2f;
        this.selectedFleet.setSize(shipSize, shipSize);
        for (ShipSprite shipSprite : this.fleetIcons) {
            shipSprite.setVisible(false);
        }
        for (Line line : this.fleetLines) {
            line.setVisible(false);
        }
        for (Text text : this.etas) {
            text.setVisible(false);
        }
        this.fleetIDs.clear();
        this.fleetIndex = 0;
        this.fleetDetails.clear();
        setOrbitingFleets();
        setMovingFleets();
        showSelectedFleet();
        sortChildren();
    }

    private void setFleetLines() {
        int i = 0;
        for (Fleet fleet : this.B.fleets.getList()) {
            if (fleet.isVisible(this.B.getCurrentEmpire()) && fleet.isMoving()) {
                Point point = new Point(0.0f, 0.0f);
                point.setX(fleet.getPosition().getX());
                point.setY(fleet.getPosition().getY());
                if (i >= this.fleetLines.size()) {
                    Line line = new Line(0.0f, 0.0f, 0.0f, 0.0f, this.bufferManager);
                    line.setLineWidth(2.0f);
                    addFleetLineEffect(line);
                    this.galaxyEntity.attachChild(line);
                    this.fleetLines.add(line);
                }
                Line line2 = this.fleetLines.get(i);
                line2.setVisible(true);
                StarSystem starSystem = this.B.galaxy.getStarSystem(fleet.getSystemID());
                line2.setPosition(point.getX() + (this.B.galaxy.getSize().getShipSize() / 2.0f), point.getY() + (this.B.galaxy.getSize().getShipSize() / 2.0f), starSystem.getPosition().getX() + ((starSystem.getStarSize() * 35.0f) / 2.0f), starSystem.getPosition().getY() + ((starSystem.getStarSize() * 35.0f) / 2.0f));
                if (fleet.empireID != this.B.getCurrentEmpire().id) {
                    if (fleet.empireID != 9 && !this.B.getCurrentEmpire().isAtWar(fleet.empireID)) {
                        line2.setColor(Color.BLUE);
                        if (Game.options.shouldHideFleetLines(1) && !fleet.id.equals(this.selectedFleetID)) {
                            line2.setVisible(false);
                        }
                    } else {
                        if (fleet.hasCombatShips()) {
                            line2.setColor(Color.RED);
                        } else {
                            line2.setColor(new Color(1.0f, 0.5f, 0.0f));
                        }
                        if (Game.options.shouldHideFleetLines(2) && !fleet.id.equals(this.selectedFleetID)) {
                            line2.setVisible(false);
                        }
                    }
                } else {
                    line2.setColor(Color.GREEN);
                    if (Game.options.shouldHideFleetLines(0) && !fleet.id.equals(this.selectedFleetID)) {
                        line2.setVisible(false);
                    }
                }
                i++;
            }
        }
    }

    private void setGalaxyItemsInvisible() {
        for (AnimatedSprite animatedSprite : this.stars) {
            animatedSprite.setVisible(false);
        }
        for (TiledSprite tiledSprite : this.outOfRangeStars) {
            tiledSprite.setVisible(false);
        }
        for (Sprite sprite : this.blackholes) {
            sprite.setVisible(false);
        }
        for (TiledSprite tiledSprite2 : this.spaceRifts) {
            tiledSprite2.setVisible(false);
        }
        clearGalaxyInfo();
    }

    private void setGalaxyStateText() {
        if (this.galaxyState == 1) {
            this.galaxyStateText.setText(GameData.activity.getString(R.string.hyperspace_flux));
        } else {
            this.galaxyStateText.setText("");
        }
    }

    private void setMovingFleets() {
        setFleetLines();
        int i = 0;
        for (Fleet fleet : this.B.fleets.getList()) {
            if (fleet.isVisible(this.B.getCurrentEmpire()) && fleet.isMoving()) {
                Point point = new Point(0.0f, 0.0f);
                point.setX(fleet.getPosition().getX());
                point.setY(fleet.getPosition().getY());
                if (this.fleetIndex >= this.fleetIcons.size()) {
                    ShipSprite shipSprite = this.B.shipSpritePool.get();
                    this.galaxyEntity.attachChild(shipSprite);
                    this.fleetIcons.add(shipSprite);
                    shipSprite.setZIndex(19997);
                }
                ShipSprite shipSprite2 = this.fleetIcons.get(this.fleetIndex);
                this.fleetIndex++;
                int fleetIconForGalaxy = shipSprite2.setFleetIconForGalaxy(fleet);
                shipSprite2.setPosition(point.getX(), point.getY());
                shipSprite2.setVisible(true);
                this.fleetDetails.add(new FleetIconData(shipSprite2.getX(), shipSprite2.getY(), fleet, fleetIconForGalaxy, shipSprite2.getRotation()));
                this.fleetIDs.add(fleet.id);
                if (i >= this.etas.size()) {
                    Text text = new Text(0.0f, 0.0f, this.B.fonts.smallInfoFont, this.D, this.E, this.bufferManager);
                    this.galaxyEntity.attachChild(text);
                    text.setColor(Color.GREEN);
                    text.setZIndex(19996);
                    this.etas.add(text);
                }
                Text text2 = this.etas.get(i);
                text2.setVisible(true);
                text2.setText(this.B.getActivity().getString(R.string.galaxy_eta, new Object[]{Integer.valueOf(fleet.getETA())}));
                text2.setPosition((fleet.getPosition().getX() + (shipSprite2.getSize() / 2.0f)) - (text2.getWidth() / 2.0f), fleet.getPosition().getY() + shipSprite2.getSize());
                i++;
            }
        }
    }

    private void setOrbitingFleets() {
        for (int i = 0; i < this.B.galaxy.getStarCount(); i++) {
            int i2 = 0;
            for (Fleet fleet : this.B.fleets.getFleetsInSystem(i)) {
                if (fleet.isVisible(this.B.getCurrentEmpire())) {
                    if (this.fleetIndex >= this.fleetIcons.size()) {
                        ShipSprite shipSprite = this.B.shipSpritePool.get();
                        this.galaxyEntity.attachChild(shipSprite);
                        this.fleetIcons.add(shipSprite);
                    }
                    ShipSprite shipSprite2 = this.fleetIcons.get(this.fleetIndex);
                    this.fleetIndex++;
                    int fleetIconForGalaxy = shipSprite2.setFleetIconForGalaxy(fleet);
                    shipSprite2.setX(fleet.getPosition().getX() + this.B.galaxy.getSize().getInOrbitX());
                    shipSprite2.setY(fleet.getPosition().getY() + (this.B.galaxy.getSize().getInOrbitPerPlayer() * i2));
                    shipSprite2.setZIndex(19997);
                    shipSprite2.setVisible(true);
                    this.fleetDetails.add(new FleetIconData(shipSprite2.getX(), shipSprite2.getY(), fleet, fleetIconForGalaxy, shipSprite2.getRotation()));
                    this.fleetIDs.add(fleet.id);
                    i2++;
                }
            }
        }
    }

    private void setProductionTurnButton() {
        Game game = this.B;
        for (Colony colony : game.colonies.getColonies(game.getCurrentPlayer())) {
            if (colony.needsProductionSet()) {
                if (colony.isAutoBuilding()) {
                    this.B.getCurrentEmpire().manageProduction(colony);
                } else {
                    this.turnButton.setCurrentTileIndex(ButtonsEnum.PRODUCTION_WARN.ordinal());
                    return;
                }
            }
        }
    }

    private void setScene() {
        this.fleetControl.instantClose();
        setEmpireDisplay();
        setTurnAndEventsButtons();
        showButtons();
        checkForWarnings();
        setEventsToShow();
        this.empireButton.set(this.B.getCurrentPlayer());
        this.B.getCurrentEmpire().redistributeFood();
        this.turnButton.setAlpha(1.0f);
        this.turnCountText.setAlpha(1.0f);
        showEvents();
    }

    private void setScrollBar() {
        this.verticalScrollBar.setVisible(false);
        this.horizontalScrollBar.setVisible(false);
        float f2 = this.zoom;
        float f3 = f2 * 720.0f;
        float x = f2 * (this.verticalScrollBar.getX() - this.xOffset);
        if (f3 <= 720.0f) {
            return;
        }
        this.verticalScrollBar.setVisible(true);
        this.horizontalScrollBar.setVisible(true);
        this.verticalScrollBar.setHeight((720.0f / f3) * 680.0f);
        this.verticalScrollBar.setY(((this.galaxyEntity.getY() * (-1.0f)) / f3) * 680.0f);
        this.horizontalScrollBar.setWidth(this.verticalScrollBar.getX() * (this.verticalScrollBar.getX() / x));
        this.horizontalScrollBar.setX((this.verticalScrollBar.getX() * ((this.galaxyEntity.getX() * (-1.0f)) / x)) - this.xOffset);
    }

    private void setSpaceRifts() {
        for (SpaceRift spaceRift : this.B.galaxy.getSpaceRifts()) {
            TiledSprite tiledSprite = this.spaceRifts.get(spaceRift.getID());
            tiledSprite.setVisible(true);
            tiledSprite.setPosition(spaceRift.getPosition().getX(), spaceRift.getPosition().getY());
            tiledSprite.setSize(spaceRift.getSize(), spaceRift.getSize());
            tiledSprite.setRotationCenter(tiledSprite.getWidthScaled() / 2.0f, tiledSprite.getHeightScaled() / 2.0f);
            tiledSprite.setFlippedHorizontal(false);
            float f2 = 360.0f;
            float f3 = 0.0f;
            if (spaceRift.hasAltSpinDirection()) {
                tiledSprite.setFlippedHorizontal(true);
            } else {
                f2 = 0.0f;
                f3 = 360.0f;
            }
            tiledSprite.setRotationCenter(tiledSprite.getWidthScaled() / 2.0f, tiledSprite.getHeightScaled() / 2.0f);
            tiledSprite.clearEntityModifiers();
            tiledSprite.registerEntityModifier(new LoopEntityModifier(new RotationModifier(spaceRift.getSpinSpeed(), f2, f3)));
        }
    }

    private void setStars() {
        for (StarSystem starSystem : this.B.galaxy.getStarSystems()) {
            int ordinal = (starSystem.getStarType().ordinal() * 12) + (starSystem.getStarShape() * 4);
            long[] jArr = {125, 125, 125, Functions.random.nextInt(1000) + 2000};
            int[] iArr = {ordinal, ordinal + 1, ordinal + 2, ordinal + 3};
            Point position = starSystem.getPosition();
            int id = starSystem.getID();
            this.stars.get(id).setPosition(position.getX(), position.getY());
            this.stars.get(id).setVisible(true);
            this.stars.get(id).animate(jArr, iArr, true);
            this.stars.get(id).setScale(starSystem.getStarSize());
            this.outOfRangeStars.get(id).setPosition(position.getX(), position.getY());
            this.outOfRangeStars.get(id).setCurrentTileIndex(starSystem.getStarShape());
            this.outOfRangeStars.get(id).setScale(starSystem.getStarSize());
            Text text = this.starNames.get(id);
            text.setText(starSystem.getName());
            text.setVisible(false);
            updateSystemDisplay(id);
            float y = text.getY() + text.getHeightScaled() + 2.0f;
            if (y > 680.0f) {
                y = 680.0f;
            }
            this.exploreIcons.get(id).setSize(15.0f, 15.0f);
            this.exploreIcons.get(id).setY(y);
            this.exploreCounts.get(id).setY(y);
            this.colonizeIcons.get(id).setSize(15.0f, 15.0f);
            this.colonizeIcons.get(id).setY(y);
            this.colonizeCounts.get(id).setY(y);
            this.buildOutpostIcons.get(id).setSize(15.0f, 15.0f);
            this.buildOutpostIcons.get(id).setY(y);
            this.buildOutpostCounts.get(id).setY(y);
            TiledSprite tiledSprite = this.colonyShipArrivedIcons.get(id);
            tiledSprite.setX(this.stars.get(id).getX() + this.B.galaxy.getSize().getInOrbitX());
            tiledSprite.setY(this.stars.get(id).getY() - 20.0f);
        }
    }

    private void setTechTurnButton() {
        if (this.B.getCurrentEmpire().getEmpireSetting("auto_research") != 1 && this.B.getCurrentEmpire().getCurrentTech().getID() == TechID.NONE) {
            if (this.B.getCurrentEmpire().getTech().getAvailableTechsToResearch().isEmpty()) {
                this.B.getCurrentEmpire().getTech().setTech(TechID.MINIATURIZATION);
            } else {
                this.turnButton.setCurrentTileIndex(ButtonsEnum.TECH_WARN.ordinal());
            }
        }
    }

    private void setTurnAndEventsButtons() {
        TiledSprite tiledSprite = this.turnButton;
        ButtonsEnum buttonsEnum = ButtonsEnum.TURN;
        tiledSprite.setCurrentTileIndex(buttonsEnum.ordinal());
        if (this.B.getCurrentEmpire().isType(EmpireType.AI)) {
            return;
        }
        setCreditsWarning();
        setProductionTurnButton();
        setEventTurnButton();
        setTechTurnButton();
        this.turnCountText.setVisible(false);
        if (this.turnButton.getCurrentTileIndex() == buttonsEnum.ordinal()) {
            this.turnCountText.setVisible(true);
            this.turnCountText.setText(Integer.toString(GameData.turn));
            this.turnCountText.setX(((this.turnButton.getX() + this.turnButton.getWidthScaled()) - 25.0f) - this.turnCountText.getWidthScaled());
            this.turnCountText.setY(((this.turnButton.getY() + this.turnButton.getHeightScaled()) - 12.0f) - this.turnCountText.getHeightScaled());
        }
    }

    private void showEvents() {
        if (this.dontShowEvents) {
            this.dontShowEvents = false;
        } else if (this.H.getMessageCount() != 0) {
            this.H.setOverlay();
            setChildScene(this.H, false, false, true);
        }
    }

    private void showOutOfRangeMessage(int i, int i2, int i3) {
        StarSystem starSystem = this.B.galaxy.getStarSystem(i);
        showMessage(new OutOfRangeMessage((starSystem.getStarType().ordinal() * 12) + (starSystem.getStarShape() * 4), i2, i3));
    }

    private void showSelectedFleet() {
        String selectedFleetID = this.B.getCurrentEmpire().getSelectedFleetID();
        if (!this.B.fleets.has(selectedFleetID)) {
            this.B.getCurrentEmpire().setSelectedFleetID("none");
        } else if (selectedFleetID.equals("none") || !this.B.fleets.get(selectedFleetID).isVisible(this.B.getCurrentEmpire())) {
        } else {
            selectFleet(selectedFleetID);
        }
    }

    private void turnButtonPressed() {
        if (this.turnButton.getCurrentTileIndex() == ButtonsEnum.TURN.ordinal()) {
            if (Game.options.isOn(OptionID.CLEAR_EVENTS_END_TURN)) {
                Game game = this.B;
                game.events.clearEventsForEmpire(game.getCurrentPlayer());
            }
            this.turnButton.setAlpha(0.4f);
            this.turnCountText.setAlpha(0.4f);
            this.B.turnDone();
        } else if (this.turnButton.getCurrentTileIndex() == ButtonsEnum.PRODUCTION_WARN.ordinal()) {
            Game game2 = this.B;
            Iterator<Colony> it = game2.colonies.getColonies(game2.getCurrentPlayer()).iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Colony next = it.next();
                if (next.needsProductionSet()) {
                    changeScene(this.B.productionScene, next);
                    break;
                }
            }
        } else if (this.turnButton.getCurrentTileIndex() == ButtonsEnum.TECH_WARN.ordinal()) {
            this.B.techScene.set();
            changeScene(this.B.techScene);
        } else if (this.turnButton.getCurrentTileIndex() == ButtonsEnum.CREDITS_WARN.ordinal()) {
            showMessage(new LowCreditsMessage());
        }
        this.B.sounds.playButtonPressSound();
        Game game3 = this.B;
        game3.vibrate(game3.BUTTON_VIBRATE);
    }

    private void undimStars() {
        for (int i = 0; i < this.B.galaxy.getStarCount(); i++) {
            this.stars.get(i).setVisible(true);
            this.outOfRangeStars.get(i).setVisible(false);
            this.starNames.get(i).setColor(this.starNameColors.get(i));
            this.exploreCounts.get(i).setAlpha(1.0f);
            this.exploreIcons.get(i).setAlpha(1.0f);
            this.colonizeCounts.get(i).setAlpha(1.0f);
            this.colonizeIcons.get(i).setAlpha(1.0f);
            this.buildOutpostCounts.get(i).setAlpha(1.0f);
            this.buildOutpostIcons.get(i).setAlpha(1.0f);
        }
        for (Text text : this.turnsToList) {
            text.setVisible(false);
        }
        for (TiledSprite tiledSprite : this.turnsToIconList) {
            tiledSprite.setVisible(false);
        }
    }

    private void updateSystemDisplay(int i) {
        boolean z;
        float f2;
        float f3;
        this.starNames.get(i).setText(this.B.galaxy.getStarSystem(i).getName());
        float widthScaled = this.starNames.get(i).getWidthScaled();
        Point position = this.B.galaxy.getStarSystem(i).getPosition();
        float x = (position.getX() + (this.stars.get(i).getWidthScaled() / 2.0f)) - (widthScaled / 2.0f);
        float y = position.getY() + this.stars.get(i).getHeightScaled();
        if (y > 660.0f) {
            y = 660.0f;
        }
        this.starNames.get(i).setPosition(x - ((this.starNames.get(i).getWidth() - this.starNames.get(i).getWidthScaled()) / 2.0f), y);
        int i2 = ((int) widthScaled) + 13;
        int heightScaled = ((int) this.starNames.get(i).getHeightScaled()) + 3;
        Point point = new Point(x - 5.0f, y - 2.0f);
        HashMap hashMap = new HashMap();
        SparseIntArray sparseIntArray = new SparseIntArray();
        int i3 = 0;
        int i4 = 0;
        while (true) {
            z = true;
            if (i3 >= 5) {
                break;
            }
            if (this.B.colonies.isColony(i, i3)) {
                i4++;
                Colony colony = this.B.colonies.getColony(i, i3);
                sparseIntArray.put(colony.getEmpireID(), sparseIntArray.get(colony.getEmpireID(), 0) + 1);
                Integer num = (Integer) hashMap.get(Integer.valueOf(colony.getEmpireID()));
                if (num == null) {
                    num = 0;
                }
                hashMap.put(Integer.valueOf(colony.getEmpireID()), Integer.valueOf(num.intValue() + colony.getPopulation()));
            } else if (this.B.outposts.isOutpost(i, i3)) {
                i4++;
                int empireID = this.B.outposts.getOutpost(i, i3).getEmpireID();
                sparseIntArray.put(empireID, sparseIntArray.get(empireID, 0) + 1);
            }
            i3++;
        }
        for (TiledSprite tiledSprite : this.systemNameBackground.get(i)) {
            tiledSprite.setVisible(false);
        }
        int i5 = 0;
        while (i5 < sparseIntArray.size()) {
            int keyAt = sparseIntArray.keyAt(i5);
            this.systemNameBackground.get(i)[i5].setVisible(z);
            this.systemNameBackground.get(i)[i5].setPosition(point.getX(), point.getY());
            this.systemNameBackground.get(i)[i5].setCurrentTileIndex(keyAt);
            float f4 = (int) (i2 * (sparseIntArray.get(keyAt) / i4));
            this.systemNameBackground.get(i)[i5].setSize(f4, heightScaled);
            point.setX(point.getX() + f4);
            i5++;
            z = true;
        }
        for (TiledSprite tiledSprite2 : this.systemControlBackground.get(i)) {
            tiledSprite2.setVisible(false);
        }
        if (Game.options.isOn(OptionID.CONTROL_INDICATOR)) {
            Map sortMapByValueDesc = Functions.sortMapByValueDesc(hashMap);
            int i6 = 2;
            for (Map.Entry entry : sortMapByValueDesc.entrySet()) {
                if (i6 == -1) {
                    break;
                }
                TiledSprite tiledSprite3 = this.systemControlBackground.get(i)[i6];
                tiledSprite3.setColor(Empires.getEmpireColor(((Integer) entry.getKey()).intValue()));
                tiledSprite3.setVisible(true);
                float intValue = ((Integer) entry.getValue()).intValue();
                if (((Integer) entry.getValue()).intValue() < 50) {
                    f2 = intValue * 2.25f;
                    f3 = 0.15f;
                } else if (((Integer) entry.getValue()).intValue() < 100) {
                    f2 = intValue * 2.0f;
                    f3 = 0.2f;
                } else if (((Integer) entry.getValue()).intValue() < 200) {
                    f2 = intValue * 1.6f;
                    f3 = 0.25f;
                } else if (((Integer) entry.getValue()).intValue() < 300) {
                    f2 = intValue * 1.25f;
                    f3 = 0.3f;
                } else if (((Integer) entry.getValue()).intValue() < 400) {
                    f2 = intValue * 1.0f;
                    f3 = 0.35f;
                } else {
                    f2 = intValue * 0.9f;
                    f3 = 0.4f;
                }
                float sizeModifier = f2 * this.B.galaxy.getSize().getSizeModifier();
                tiledSprite3.setSize(sizeModifier, sizeModifier);
                tiledSprite3.setAlpha(f3);
                float f5 = sizeModifier / 2.0f;
                tiledSprite3.setPosition((position.getX() + (this.stars.get(i).getWidthScaled() / 2.0f)) - f5, (position.getY() + (this.stars.get(i).getHeightScaled() / 2.0f)) - f5);
                i6--;
            }
        }
        checkForWormholes(i);
    }

    private void zoomButtonPressed() {
        setZoom(this.zoomLevel + 1);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void B(Point point) {
        if (hasChildScene() || this.isScroll) {
            return;
        }
        this.isPreviewOn = true;
        this.G.setVisible(false);
        hideButtons();
        this.searchIcon.setVisible(true);
        if (this.selectedFleet.isVisible()) {
            this.fleetControl.instantClose();
        }
        this.systemPreviewOverlay.setOverlay(point);
        setChildScene(this.systemPreviewOverlay, false, false, true);
    }

    public void L(float f2, float f3) {
        this.galaxyEntity.setX(f2);
        this.galaxyEntity.setY(f3);
        setScrollBar();
    }

    protected void M(ExtendedScene extendedScene, Object obj) {
        if (extendedScene instanceof MenuScene) {
            this.B.menuScene.openMenu();
        } else if (extendedScene instanceof ColoniesScene) {
            Game game = this.B;
            game.coloniesScene.load(game.getCurrentPlayer());
        } else if (extendedScene instanceof RacesScene) {
            this.B.racesScene.set();
        } else if (extendedScene instanceof RaceScene) {
            this.B.raceScene.set(((Integer) obj).intValue());
        } else if (extendedScene instanceof TurnScene) {
            this.B.turnScene.set();
        } else if (extendedScene instanceof SystemScene) {
            this.B.systemScene.loadSystem(((Integer) obj).intValue());
        } else if (extendedScene instanceof PlanetScene) {
            Point point = (Point) obj;
            this.B.planetScene.loadPlanet((int) point.getX(), (int) point.getY(), this.B.eventsScene);
        } else if (extendedScene instanceof ProductionScene) {
            Game game2 = this.B;
            game2.productionScene.set((Colony) obj, game2.galaxyScene);
        } else if (extendedScene instanceof SelectAttackScene) {
            Point point2 = (Point) obj;
            this.B.selectAttackScene.set((int) point2.getX(), (int) point2.getY());
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
        } else if (extendedScene instanceof EventsScene) {
            Game game3 = this.B;
            game3.eventsScene.set(game3.getCurrentPlayer());
        }
    }

    @Override // org.andengine.entity.scene.Scene
    public void back() {
        if (t()) {
            return;
        }
        if (hasChildScene()) {
            this.galaxySelectOverlay.back();
            this.B.shipSelectOverlay.back();
            this.H.back();
            this.gameVictoryOverlay.back();
            this.systemPreviewOverlay.back();
            showButtons();
            return;
        }
        changeScene(this.B.menuScene);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void checkInput(int i, Point point) {
        super.checkInput(i, point);
        if (this.selectedFleet.isVisible()) {
            if (this.fleetControlMoved) {
                this.fleetControl.D();
            } else if (isPositionOnFleetControl(point) && this.fleetControl.checkInputOnControl(i, point)) {
                this.fleetControlPressed = false;
                this.fleetControl.unPressed();
                return;
            }
        }
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
        this.bufferManager = vertexBufferObjectManager;
        this.nebulas = this.B.nebulas;
        this.xOffset = 0;
        if (getWidth() == 1480.0f) {
            this.xOffset = 100;
        }
        super.createScene(vertexBufferObjectManager, this.xOffset);
        setX(this.xOffset);
        this.C.setX(this.xOffset * (-1));
        Entity entity = new Entity();
        this.galaxyEntity = entity;
        entity.setScaleCenter(0.0f, 0.0f);
        attachChild(this.galaxyEntity);
        createNebulas();
        TiledSprite tiledSprite = new TiledSprite((getWidth() - 125.0f) - this.xOffset, 0.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        this.verticalScrollBar = tiledSprite;
        tiledSprite.setCurrentTileIndex(6);
        this.verticalScrollBar.setWidth(6.0f);
        this.verticalScrollBar.setAlpha(0.7f);
        attachChild(this.verticalScrollBar);
        TiledSprite tiledSprite2 = new TiledSprite(0.0f, 680.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
        this.horizontalScrollBar = tiledSprite2;
        tiledSprite2.setCurrentTileIndex(6);
        this.horizontalScrollBar.setHeight(6.0f);
        this.horizontalScrollBar.setAlpha(0.7f);
        attachChild(this.horizontalScrollBar);
        createSystemControl(vertexBufferObjectManager);
        createWormholes(vertexBufferObjectManager);
        createStars(vertexBufferObjectManager);
        createButtonControl(vertexBufferObjectManager);
        EmpireInfo empireInfo = new EmpireInfo(this.B, vertexBufferObjectManager, ((((int) getWidth()) / 2) - 150) - this.xOffset, this);
        this.empireInfo = empireInfo;
        empireInfo.setY(690.0f);
        attachChild(this.empireInfo);
        Text G = G(20.0f, 0.0f, this.B.fonts.smallFont, this.D, this.E, vertexBufferObjectManager, true);
        this.galaxyStateText = G;
        G.setColor(Color.RED);
        v(this.galaxyStateText);
        createFleets(vertexBufferObjectManager);
        createOverlays(vertexBufferObjectManager);
    }

    public List<FleetIconData> getFleetDetails() {
        return this.fleetDetails;
    }

    public Point getGalaxyPosition() {
        return new Point(this.galaxyEntity.getX(), this.galaxyEntity.getY());
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void getPoolElements() {
        if (this.nebulas.hasParent()) {
            this.nebulas.detachSelf();
        }
        this.nebulaBackground.attachChild(this.nebulas);
        for (int i = 0; i < 50; i++) {
            AnimatedSprite animatedSprite = this.B.starSpritePool.get();
            animatedSprite.setVisible(false);
            this.galaxyEntity.attachChild(animatedSprite);
            this.stars.add(animatedSprite);
        }
        this.fleetControl.getPoolElements();
        this.systemPreviewOverlay.getPoolElements();
    }

    public List<SystemNameDisplay> getVisibleSystems() {
        TiledSprite[] tiledSpriteArr;
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Text text : this.starNames) {
            if (text.isVisible()) {
                ArrayList arrayList2 = new ArrayList();
                for (TiledSprite tiledSprite : this.systemNameBackground.get(i)) {
                    if (tiledSprite.isVisible()) {
                        arrayList2.add(Integer.valueOf(tiledSprite.getCurrentTileIndex()));
                    }
                }
                arrayList.add(new SystemNameDisplay(text.getX(), text.getY(), text.getText().toString(), arrayList2));
            }
            i++;
        }
        return arrayList;
    }

    public float getZoom() {
        return this.zoom;
    }

    public void hideButtons() {
        this.buttons.setVisible(false);
        this.horizontalScrollBar.setVisible(false);
        this.verticalScrollBar.setVisible(false);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void refresh() {
        if (this.stars.isEmpty()) {
            for (int i = 0; i < 50; i++) {
                AnimatedSprite animatedSprite = this.B.starSpritePool.get();
                animatedSprite.setVisible(false);
                this.galaxyEntity.attachChild(animatedSprite);
                this.stars.add(animatedSprite);
            }
        }
        set();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void releasePoolElements(final ExtendedScene extendedScene, final Object obj) {
        this.B.getEngine().runOnUpdateThread(new Runnable() { // from class: com.birdshel.Uciana.Scenes.o
            {
                GalaxyScene.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                GalaxyScene.this.lambda$releasePoolElements$0(extendedScene, obj);
            }
        });
    }

    public void selectFleet(String str) {
        selectFleet(this.B.fleets.get(str));
    }

    public void selectSystem(int i) {
        if (this.selectedFleet.isVisible()) {
            Fleet fleet = this.B.fleets.get(this.selectedFleetID);
            if (this.B.getCurrentPlayer() == fleet.empireID) {
                if (fleet.getSystemID() != i) {
                    moveFleet(fleet, i);
                    return;
                } else {
                    checkSystemSelected(i);
                    return;
                }
            }
        }
        checkSystemSelected(i);
    }

    public void setEmpireDisplay() {
        clearGalaxyInfo();
        for (Integer num : this.B.getCurrentEmpire().getDiscoveredSystems()) {
            int intValue = num.intValue();
            this.starNames.get(intValue).setVisible(true);
            Color color = Color.WHITE;
            this.starNames.get(intValue).setColor(color);
            this.starNameColors.set(intValue, color);
            updateSystemDisplay(intValue);
            checkForWormholes(intValue);
            int i = 0;
            Game game = this.B;
            int unexploredPlanetsCount = game.galaxy.getUnexploredPlanetsCount(intValue, game.getCurrentPlayer());
            if (unexploredPlanetsCount != 0) {
                Text text = this.exploreCounts.get(intValue);
                TiledSprite tiledSprite = this.exploreIcons.get(intValue);
                tiledSprite.setVisible(true);
                text.setText(Integer.toString(unexploredPlanetsCount));
                text.setVisible(true);
                i = (int) (0 + text.getWidthScaled() + tiledSprite.getWidthScaled() + 7.0f);
            }
            Game game2 = this.B;
            int colonizablePlanetsCount = game2.galaxy.getColonizablePlanetsCount(intValue, game2.getCurrentPlayer());
            if (colonizablePlanetsCount != 0) {
                Text text2 = this.colonizeCounts.get(intValue);
                TiledSprite tiledSprite2 = this.colonizeIcons.get(intValue);
                tiledSprite2.setVisible(true);
                text2.setText(Integer.toString(colonizablePlanetsCount));
                text2.setVisible(true);
                i = (int) (i + text2.getWidthScaled() + tiledSprite2.getWidthScaled() + 7.0f);
            }
            Game game3 = this.B;
            int possibleOutpostCount = game3.galaxy.getPossibleOutpostCount(intValue, game3.getCurrentPlayer());
            if (possibleOutpostCount != 0) {
                Text text3 = this.buildOutpostCounts.get(intValue);
                TiledSprite tiledSprite3 = this.buildOutpostIcons.get(intValue);
                tiledSprite3.setVisible(true);
                text3.setText(Integer.toString(possibleOutpostCount));
                text3.setVisible(true);
                i = (int) (i + text3.getWidthScaled() + tiledSprite3.getWidthScaled() + 5.0f);
            }
            float x = (this.starNames.get(intValue).getX() + (this.starNames.get(intValue).getWidthScaled() / 2.0f)) - (i / 2.0f);
            if (unexploredPlanetsCount != 0) {
                this.exploreCounts.get(intValue).setX(x);
                float widthScaled = x + this.exploreCounts.get(intValue).getWidthScaled() + 5.0f;
                this.exploreIcons.get(intValue).setX(widthScaled);
                x = widthScaled + this.exploreIcons.get(intValue).getWidthScaled() + 2.0f;
            }
            if (colonizablePlanetsCount != 0) {
                this.colonizeCounts.get(intValue).setX(x);
                float widthScaled2 = x + this.colonizeCounts.get(intValue).getWidthScaled() + 5.0f;
                this.colonizeIcons.get(intValue).setX(widthScaled2);
                x = widthScaled2 + this.colonizeIcons.get(intValue).getWidthScaled() + 2.0f;
            }
            if (possibleOutpostCount != 0) {
                this.buildOutpostCounts.get(intValue).setX(x);
                this.buildOutpostIcons.get(intValue).setX(x + this.buildOutpostCounts.get(intValue).getWidthScaled() + 5.0f);
            }
        }
        for (Integer num2 : this.B.getCurrentEmpire().getKnownEmpires()) {
            int intValue2 = num2.intValue();
            if (intValue2 < 7) {
                Empire empire = this.B.empires.get(intValue2);
                if (empire.areAllies(this.B.getCurrentPlayer())) {
                    for (Integer num3 : empire.getSystemIDs()) {
                        int intValue3 = num3.intValue();
                        if (!this.B.getCurrentEmpire().isDiscoveredSystem(intValue3)) {
                            this.starNames.get(intValue3).setVisible(true);
                            Color color2 = Color.CYAN;
                            this.starNames.get(intValue3).setColor(color2);
                            this.starNameColors.set(intValue3, color2);
                            updateSystemDisplay(intValue3);
                        }
                    }
                }
            }
        }
        this.racesButton.setAlpha(1.0f);
        if (this.B.getCurrentEmpire().getKnownEmpires().isEmpty()) {
            this.racesButton.setAlpha(0.4f);
        }
        this.empireInfo.update();
    }

    public void setGalaxyState(int i) {
        this.galaxyState = i;
        setGalaxyStateText();
    }

    public void setStarsAndNebulas() {
        setGalaxyItemsInvisible();
        showButtons();
        this.nebulas.set();
        setStars();
        setWormholes();
        setBlackholes();
        setSpaceRifts();
        setZoom(this.zoomLevel);
    }

    public void setTurnButtonToTurn() {
        this.turnButton.setCurrentTileIndex(ButtonsEnum.TURN.ordinal());
    }

    public void setWormholes() {
        int i = 0;
        for (Point point : this.B.galaxy.getWormholes()) {
            StarSystem starSystem = this.B.galaxy.getStarSystem((int) point.getX());
            float x = starSystem.getPosition().getX() + ((starSystem.getStarSize() * 35.0f) / 2.0f);
            float y = starSystem.getPosition().getY() + ((starSystem.getStarSize() * 35.0f) / 2.0f);
            StarSystem starSystem2 = this.B.galaxy.getStarSystem((int) point.getY());
            this.wormholes.get(i).setPosition(x, y, starSystem2.getPosition().getX() + ((starSystem2.getStarSize() * 35.0f) / 2.0f), starSystem2.getPosition().getY() + ((starSystem2.getStarSize() * 35.0f) / 2.0f));
            i++;
        }
    }

    public void setZoom(int i) {
        if (i > 2) {
            i = 0;
        }
        this.zoomLevel = i;
        float zoomLevel = this.B.galaxy.getSize().getZoomLevel(i);
        this.zoom = zoomLevel;
        this.galaxyEntity.setScale(zoomLevel);
        placeGalaxy(this.galaxyEntity.getX(), this.galaxyEntity.getY());
        Text text = this.zoomPercentText;
        text.setText(((int) (this.zoom * 100.0f)) + "%");
        Text text2 = this.zoomPercentText;
        text2.setX(90.0f - text2.getWidthScaled());
        Text text3 = this.zoomPercentText;
        text3.setY(70.0f - text3.getHeightScaled());
    }

    public void showButtons() {
        this.buttons.setVisible(true);
        this.searchIcon.setVisible(false);
        if (this.zoom != 1.0f) {
            this.horizontalScrollBar.setVisible(true);
            this.verticalScrollBar.setVisible(true);
        }
        if (this.selectedFleet.isVisible()) {
            this.fleetControl.open();
        }
    }

    public void showShipCount() {
        this.shipCount.setText(Integer.toString(this.selectedShipIDs.size()));
        float x = (this.selectedFleet.getX() + (this.selectedFleet.getWidthScaled() / 2.0f)) - ((this.shipCount.getWidth() + 15.0f) / 2.0f);
        this.shipCount.setPosition(x, this.fleetIcons.get(getFleetIconIndex(this.selectedFleetID)).getY() - 18.0f);
        this.shipCount.setVisible(true);
        this.shipCountIcon.setPosition(x + this.shipCount.getWidthScaled() + 5.0f, this.shipCount.getY());
        this.shipCountIcon.setVisible(true);
    }

    public void showShipSelectMenu() {
        hideButtons();
        this.B.shipSelectOverlay.setOverlay(this.selectedFleetID, this, this.B.fleets.get(this.selectedFleetID).empireID == this.B.getCurrentPlayer());
        setChildScene(this.B.shipSelectOverlay, false, false, true);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void switched() {
        setScale(1.0f);
        this.galaxySelectOverlay.back();
        this.B.shipSelectOverlay.back();
        this.H.back();
        this.gameVictoryOverlay.back();
        this.systemPreviewOverlay.back();
        set();
        super.switched();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void update() {
        super.update();
    }

    public void selectFleet(Fleet fleet) {
        if (this.selectedFleet.isVisible()) {
            if (this.selectedFleetID.equals(fleet.id)) {
                this.selectedFleet.setVisible(false);
                this.shipCount.setVisible(false);
                this.shipCountIcon.setVisible(false);
                this.selectedFleetID = null;
                this.selectedShipIDs.clear();
                undimStars();
                this.B.getCurrentEmpire().setSelectedFleetID("none");
                this.fleetControl.close();
                setFleetLines();
                return;
            }
        } else {
            if (fleet.empireID == this.B.getCurrentPlayer()) {
                Game.gameAchievements.checkForAllShipTypesFleet(fleet);
            }
            if (!fleet.isMoving() && this.colonyShipArrivedIcons.get(fleet.getSystemID()).isVisible()) {
                this.colonyShipArrivedIcons.get(fleet.getSystemID()).setVisible(false);
                Game game = this.B;
                game.fleets.removeColonyShipArrived(game.getCurrentPlayer(), fleet.getSystemID());
            }
            this.fleetControl.open();
        }
        int fleetIconIndex = getFleetIconIndex(fleet.id);
        this.selectedShipIDs.clear();
        this.selectedFleetID = this.fleetIDs.get(fleetIconIndex);
        for (Ship ship : fleet.getShips()) {
            this.selectedShipIDs.add(ship.getID());
        }
        ShipSprite shipSprite = this.fleetIcons.get(fleetIconIndex);
        float widthScaled = (this.selectedFleet.getWidthScaled() - shipSprite.getSize()) / 2.0f;
        this.selectedFleet.setPosition(shipSprite.getX() - widthScaled, shipSprite.getY() - widthScaled);
        this.selectedFleet.setVisible(true);
        this.fleetControl.setControl(this.selectedFleetID, false);
        showShipCount();
        dimStarsOutOfRange();
        this.B.getCurrentEmpire().setSelectedFleetID(fleet.id);
        setFleetLines();
    }
}
