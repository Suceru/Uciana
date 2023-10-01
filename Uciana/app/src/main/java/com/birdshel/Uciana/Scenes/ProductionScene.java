package com.birdshel.Uciana.Scenes;

import androidx.constraintlayout.core.motion.utils.TypedValues;

import com.birdshel.Uciana.Colonies.Buildings.Building;
import com.birdshel.Uciana.Colonies.Buildings.BuildingID;
import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Colonies.Manufacturing;
import com.birdshel.Uciana.Colonies.ManufacturingType;
import com.birdshel.Uciana.Colonies.ProductionItem;
import com.birdshel.Uciana.Controls.ScrollBarControl;
import com.birdshel.Uciana.Elements.ProductionPercentBar;
import com.birdshel.Uciana.Elements.ProductionScene.ColonyInfoEntity;
import com.birdshel.Uciana.Elements.ProductionScene.ProductionListElement;
import com.birdshel.Uciana.Elements.ProductionScene.QueueItemContainer;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Messages.WarningMessage;
import com.birdshel.Uciana.Overlays.BuildListOverlay;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Overlays.ShipDesignsOverlay;
import com.birdshel.Uciana.Planets.Moon;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.PlanetSprite;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.Ships.Ship;
import com.birdshel.Uciana.Ships.ShipType;
import com.birdshel.Uciana.StarSystems.Nebulas;
import com.birdshel.Uciana.StarSystems.StarSystem;
import com.birdshel.Uciana.StarSystems.SunSprite;

import org.andengine.entity.Entity;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ProductionScene extends ExtendedScene {
    private TiledSprite autoBuildButton;
    private TiledSprite blockaded;
    private TiledSprite buildListButton;
    private BuildListOverlay buildListOverlay;
    private Sprite buildListPressButton;
    private List<Building> buildingsBuildList;
    private Sprite buildingsButton;
    private TiledSprite buyNowButton;
    private List<Colony> colonies;
    private int coloniesListIndex;
    private Colony colony;
    private TiledSprite colonyBackground;
    private ColonyInfoEntity colonyInfo;
    private Text colonyName;
    private TiledSprite emptyColony;
    private TiledSprite galaxyButton;
    private float lastY;
    private TiledSprite listButton;
    private TiledSprite lowPower;
    private Entity nebulaBackground;
    private Nebulas nebulas;
    private TiledSprite nextButton;
    private TiledSprite planetButton;
    private PlanetSprite planetSprite;
    private PlanetSprite planetSpriteHeader;
    private Sprite pressedList;
    private Sprite pressedList2;
    private float pressedY;
    private TiledSprite previousButton;
    private Text productionAndTotalProd;
    private TiledSprite productionIcon;
    private TiledSprite productionImage;
    private Scene productionList;
    private int productionListIndex;
    private Text productionName;
    private Text productionPerTurn;
    private ProductionPercentBar productionPercentBar;
    private Text productionTurnsLeft;
    private QueueItemContainer queueContainer;
    private Sprite repeatButton;
    private TiledSprite repeatIcon;
    private Sprite repeatOn;
    private ScrollBarControl scrollBar;
    private Sprite selectPress;
    private TiledSprite selectedAutoBuild;
    private Sprite selectedList;
    private Sprite selectedList2;
    private List<Ship> shipBuildList;
    private TiledSprite shipDesignButton;
    private ShipDesignsOverlay shipDesignsOverlay;
    private TiledSprite shipProductionImage;
    private TiledSprite shipProductionTypeIcon;
    private TiledSprite shipYards;
    private Sprite shipsButton;
    private TiledSprite spaceDock;
    private TiledSprite starBase;
    private TiledSprite starvation;
    private SunSprite sunSprite;
    private Sprite surface;
    private TiledSprite systemButton;
    private TiledSprite totalProductionIcon;
    private TiledSprite turnsIcon;
    private TiledSprite turret1;
    private TiledSprite turret2;
    private ExtendedScene whereFrom;
    private final ProductionListElement[] elements = new ProductionListElement[6];
    private int totalItems = 0;
    private boolean isScroll = false;
    private final int ITEM_SIZE = 105;
    private final int PRODUCTION_LIST_Y = 187;

    public ProductionScene(Game game) {
        this.B = game;
    }

    private void checkBuildingProductionList(int i, Point point) {
        Manufacturing manufacturing = this.colony.getManufacturing();
        Building building = this.buildingsBuildList.get(i);
        if (point.getX() < getWidth() - 100.0f) {
            if (manufacturing.getID().startsWith("refit-")) {
                this.B.confirmOverlay.setOverlay(manufacturing.getCurrentItem().getName() + " (" + manufacturing.getCurrentItem().getID().replace("refit-", "") + ")", manufacturing, "building", building);
                setChildScene(this.B.confirmOverlay, false, false, true);
                return;
            }
            if (building.getID() == BuildingID.TRADEGOODS && this.colony.isAutoBuilding()) {
                Game game = this.B;
                game.confirmOverlay.setOverlay(this.colony, game.productionScene, "autoOff");
                setChildScene(this.B.confirmOverlay, false, false, true);
            }
            manufacturing.setBuilding(building);
        } else if (manufacturing.isQueueFull()) {
        } else {
            manufacturing.addBuildingToQueue(building);
        }
    }

    private void checkButtons(Point point) {
        Colony colony;
        if (isClicked(this.galaxyButton, point)) {
            changeScene(this.B.galaxyScene);
            this.B.sounds.playButtonPressSound();
            Game game = this.B;
            game.vibrate(game.BUTTON_VIBRATE);
        } else if (isClicked(this.systemButton, point)) {
            changeScene(this.B.systemScene, Integer.valueOf(this.colony.getSystemID()));
            this.B.sounds.playButtonPressSound();
            Game game2 = this.B;
            game2.vibrate(game2.BUTTON_VIBRATE);
        } else if (isClicked(this.planetButton, point)) {
            changeScene(this.B.planetScene);
            this.B.sounds.playButtonPressSound();
            Game game3 = this.B;
            game3.vibrate(game3.BUTTON_VIBRATE);
        } else if (isClicked(this.listButton, point)) {
            changeScene(this.B.coloniesScene);
            this.B.sounds.playButtonPressSound();
            Game game4 = this.B;
            game4.vibrate(game4.BUTTON_VIBRATE);
        } else {
            if (isClicked(this.previousButton, point)) {
                set(this.coloniesListIndex - 1, this.colonies);
                this.B.sounds.playButtonPressSound();
                Game game5 = this.B;
                game5.vibrate(game5.BUTTON_VIBRATE);
            }
            if (isClicked(this.nextButton, point)) {
                set(this.coloniesListIndex + 1, this.colonies);
                this.B.sounds.playButtonPressSound();
                Game game6 = this.B;
                game6.vibrate(game6.BUTTON_VIBRATE);
            }
            if (isClicked(this.autoBuildButton, point)) {
                this.colony.setAutoBuild(!colony.isAutoBuilding());
                this.selectedAutoBuild.setVisible(this.colony.isAutoBuilding());
                this.B.sounds.playButtonPressSound();
                Game game7 = this.B;
                game7.vibrate(game7.BUTTON_VIBRATE);
            } else if (isClicked(this.shipDesignButton, point)) {
                changeScene(this.B.shipDesignScene);
                this.B.sounds.playButtonPressSound();
                Game game8 = this.B;
                game8.vibrate(game8.BUTTON_VIBRATE);
            } else {
                if (isClicked(this.shipsButton, point)) {
                    if (this.selectedList.getX() != this.shipsButton.getX()) {
                        this.selectedList.setX(this.shipsButton.getX());
                        this.selectedList2.setX(this.shipsButton.getX());
                        setSelectedList();
                    }
                    this.B.sounds.playBoxPressSound();
                    Game game9 = this.B;
                    game9.vibrate(game9.BUTTON_VIBRATE);
                }
                if (isClicked(this.buildingsButton, point)) {
                    if (this.selectedList.getX() != this.buildingsButton.getX()) {
                        this.selectedList.setX(this.buildingsButton.getX());
                        this.selectedList2.setX(this.buildingsButton.getX());
                        setSelectedList();
                        this.surface.setX(-Functions.random.nextInt(2480 - ((int) getWidth())));
                    }
                    this.B.sounds.playBoxPressSound();
                    Game game10 = this.B;
                    game10.vibrate(game10.BUTTON_VIBRATE);
                }
                if (A(this.repeatButton, point, 0.0f, 0.0f)) {
                    this.B.sounds.playBoxPressSound();
                    Game game11 = this.B;
                    game11.vibrate(game11.BUTTON_VIBRATE);
                    boolean z = !this.colony.getManufacturing().getCurrentItem().isRepeatOn();
                    this.colony.getManufacturing().getCurrentItem().setRepeatOn(z);
                    this.repeatOn.setVisible(z);
                    this.queueContainer.refresh();
                }
                if (A(this.buyNowButton, point, 0.0f, 0.0f)) {
                    this.B.sounds.playButtonPressSound();
                    Game game12 = this.B;
                    game12.vibrate(game12.BUTTON_VIBRATE);
                    Game game13 = this.B;
                    game13.confirmOverlay.setOverlay(this.colony, game13.productionScene, "buy");
                    setChildScene(this.B.confirmOverlay, false, false, true);
                }
                if (this.colony.getQueue().size() >= 5 || !isClicked(this.buildListButton, point)) {
                    return;
                }
                this.B.sounds.playBoxPressSound();
                Game game14 = this.B;
                game14.vibrate(game14.BUTTON_VIBRATE);
                this.buildListOverlay.setOverlay(this.colony, this.B.getCurrentEmpire().getBuildLists());
                setChildScene(this.buildListOverlay, false, false, true);
            }
        }
    }

    private void checkForWarnings() {
        this.blockaded.setVisible(false);
        this.starvation.setVisible(false);
        this.lowPower.setVisible(false);
        this.emptyColony.setVisible(false);
        float x = this.colonyName.getX() + this.colonyName.getWidthScaled() + 30.0f;
        if (this.colony.isBlockaded()) {
            this.blockaded.setVisible(true);
            this.blockaded.setX(x);
            x += 40.0f;
        }
        if (this.colony.isStarving()) {
            this.starvation.setVisible(true);
            this.starvation.setX(x);
            x += 40.0f;
        }
        if (this.colony.isLowPower()) {
            this.lowPower.setVisible(true);
            this.lowPower.setX(x);
            x += 40.0f;
        }
        if (this.colony.getPopulation() == 0) {
            this.emptyColony.setVisible(true);
            this.emptyColony.setX(x);
        }
    }

    private void checkMoved(Point point) {
        if (point.getX() > getWidth() - 640.0f && point.getY() > 187.0f) {
            if (this.totalItems * 105 > 559) {
                if (this.pressedY - point.getY() > 25.0f || this.pressedY - point.getY() < -25.0f) {
                    this.isScroll = true;
                }
                float y = this.productionList.getY() - (this.lastY - point.getY());
                if (y > 187.0f) {
                    y = 187.0f;
                }
                float f2 = ((this.totalItems * 105) - 720) * (-1);
                if (y < f2) {
                    y = f2;
                }
                this.productionList.setY(y);
                this.lastY = point.getY();
                this.scrollBar.update(y);
                int abs = Math.abs((int) ((y - 187.0f) / 105.0f));
                if (abs != this.productionListIndex) {
                    if (this.selectedList.getX() == this.shipsButton.getX()) {
                        setShipList(abs);
                        return;
                    } else {
                        setBuildingsList(abs);
                        return;
                    }
                }
                return;
            }
            return;
        }
        this.lastY = point.getY();
    }

    private void checkPressed(Point point, int i) {
        if (isClicked(this.buildListButton, point)) {
            this.buildListPressButton.setVisible(true);
        }
        Iterator<TiledSprite> it = this.F.iterator();
        while (it.hasNext()) {
            TiledSprite next = it.next();
            if (isClicked(next, point)) {
                this.G.setPosition(next.getX(), next.getY());
                this.G.setVisible(true);
            }
        }
        if (point.getX() > getWidth() - 640.0f && point.getY() > 187.0f && !this.isScroll && this.totalItems > i) {
            this.selectPress.setY(this.productionList.getY() + (i * 105));
            this.selectPress.setVisible(true);
            if (point.getX() > getWidth() - 95.0f) {
                this.selectPress.setWidth(80.0f);
                this.selectPress.setX(getWidth() - 95.0f);
                if (this.colony.getManufacturing().isQueueFull()) {
                    this.selectPress.setVisible(false);
                }
            } else {
                this.selectPress.setWidth(540.0f);
                this.selectPress.setX(getWidth() - 640.0f);
            }
        }
        if (point.getX() <= getWidth() - 640.0f || point.getX() >= getWidth() - 100.0f || point.getY() >= 187.0f || point.getY() <= 86.0f) {
            return;
        }
        this.pressedList.setVisible(true);
        this.pressedList2.setVisible(true);
        if (point.getX() > getWidth() - 320.0f) {
            this.pressedList.setX(getWidth() - 320.0f);
            this.pressedList2.setX(getWidth() - 320.0f);
            return;
        }
        this.pressedList.setX(getWidth() - 640.0f);
        this.pressedList2.setX(getWidth() - 640.0f);
    }

    private void checkProductionList(Point point, int i) {
        if (this.totalItems <= i) {
            return;
        }
        if (this.selectedList.getX() == this.shipsButton.getX()) {
            checkShipProductionList(i, point);
        } else {
            checkBuildingProductionList(i, point);
        }
        updateDisplay();
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void checkShipProductionList(int i, Point point) {
        Manufacturing manufacturing = this.colony.getManufacturing();
        if (i + 1 == this.totalItems && shouldShowRefitButton()) {
            Game game = this.B;
            Fleet fleetInSystem = game.fleets.getFleetInSystem(game.getCurrentPlayer(), this.colony.getSystemID());
            boolean hasBuilding = this.colony.hasBuilding(BuildingID.SHIP_YARDS);
            if (point.getX() < getWidth() - 100.0f) {
                if (manufacturing.getID().startsWith("refit-")) {
                    this.B.confirmOverlay.setOverlay(manufacturing.getCurrentItem().getName() + " (" + manufacturing.getCurrentItem().getID().replace("refit-", "") + ")", manufacturing, "select", (Object) null);
                    setChildScene(this.B.confirmOverlay, false, false, true);
                    return;
                }
                this.B.shipSelectOverlay.setOverlayForRefit(fleetInSystem.id, false, hasBuilding);
            } else if (manufacturing.isQueueFull()) {
                return;
            } else {
                this.B.shipSelectOverlay.setOverlayForRefit(fleetInSystem.id, true, hasBuilding);
            }
            setChildScene(this.B.shipSelectOverlay, false, false, true);
            return;
        }
        Ship ship = this.shipBuildList.get(i);
        if (point.getX() < getWidth() - 100.0f) {
            if (manufacturing.getID().startsWith("refit-")) {
                this.B.confirmOverlay.setOverlay(manufacturing.getCurrentItem().getName() + " (" + manufacturing.getCurrentItem().getID().replace("refit-", "") + ")", manufacturing, "ship", ship);
                setChildScene(this.B.confirmOverlay, false, false, true);
                return;
            }
            manufacturing.setShip(ship);
        } else if (manufacturing.isQueueFull()) {
        } else {
            manufacturing.addShipToQueue(ship);
            setQueue();
        }
    }

    private void fixShipList() {
        ArrayList arrayList = new ArrayList();
        if (this.colony.hasBuilding(BuildingID.SHIP_YARDS)) {
            return;
        }
        for (Ship ship : this.shipBuildList) {
            if (ship.getShipType() != ShipType.BATTLESHIP && ship.getShipType() != ShipType.DREADNOUGHT) {
                arrayList.add(ship);
            }
        }
        this.shipBuildList = arrayList;
    }

    public /* synthetic */ void lambda$releasePoolElements$1(ExtendedScene extendedScene, Object obj) {
        this.nebulaBackground.detachChild(this.nebulas);
        this.nebulaBackground.detachChild(this.planetSprite);
        this.B.planetSpritePool.push(this.planetSprite);
        extendedScene.getPoolElements();
        P(extendedScene, obj);
        this.B.setCurrentScene(extendedScene);
    }

    public static /* synthetic */ int lambda$setProductionList$0(Building building, Building building2) {
        return building.getName().compareToIgnoreCase(building2.getName());
    }

    private void setBuildingsList() {
        setBuildingsList(0);
        showSurface();
    }

    private void setPlanet(Planet planet) {
        this.planetSprite.setPlanet(planet, planet.getScale(this.B.planetScene) * 2.5f, Moon.getScale(this.B.planetScene) * 2.5f, true);
        if (planet.hasColony()) {
            if (planet.getColony().hasBuilding(BuildingID.SPACE_DOCK)) {
                this.spaceDock.setVisible(true);
            }
            if (planet.getColony().hasBuilding(BuildingID.STAR_BASE)) {
                this.starBase.setVisible(true);
            }
            if (planet.getColony().hasBuilding(BuildingID.TORPEDO_TURRET)) {
                this.turret1.setVisible(true);
                this.turret2.setVisible(true);
            }
            if (planet.getColony().hasBuilding(BuildingID.SHIP_YARDS)) {
                this.shipYards.setVisible(true);
            }
        }
    }

    private void setQueue() {
        this.queueContainer.set(this.colony);
        this.buildListButton.setY(this.queueContainer.getBottomY());
    }

    private void setSelectedList() {
        this.productionList.setY(187.0f);
        if (this.selectedList.getX() == this.shipsButton.getX()) {
            this.totalItems = this.shipBuildList.size();
            setShipList();
        } else {
            this.totalItems = this.buildingsBuildList.size();
            setBuildingsList();
        }
        this.scrollBar.update(187.0f, this.totalItems);
        this.B.getActivity().setLocale();
    }

    private void setShipList() {
        if (shouldShowRefitButton()) {
            this.totalItems++;
        }
        setShipList(0);
        showSpace();
    }

    private void setSpritesInvisible() {
        this.productionPercentBar.hide();
        this.planetSprite.setSpritesInvisible();
        this.totalProductionIcon.setVisible(false);
        this.productionAndTotalProd.setVisible(false);
        this.productionImage.setVisible(false);
        this.shipProductionImage.setVisible(false);
        this.shipProductionTypeIcon.setVisible(false);
        this.spaceDock.setVisible(false);
        this.starBase.setVisible(false);
        this.turret1.setVisible(false);
        this.turret2.setVisible(false);
        this.shipYards.setVisible(false);
        this.surface.setVisible(false);
        this.selectedAutoBuild.setVisible(false);
        this.listButton.setVisible(false);
        this.nextButton.setVisible(false);
        this.previousButton.setVisible(false);
        if (this.whereFrom instanceof ColoniesScene) {
            this.listButton.setVisible(true);
            this.previousButton.setVisible(true);
            this.nextButton.setVisible(true);
            this.previousButton.setAlpha(1.0f);
            this.nextButton.setAlpha(1.0f);
            if (this.coloniesListIndex == 0) {
                this.previousButton.setAlpha(0.4f);
            }
            if (this.colonies.size() - 1 == this.coloniesListIndex) {
                this.nextButton.setAlpha(0.4f);
            }
        }
    }

    private void setSystemBackground(StarSystem starSystem) {
        this.nebulas.set(starSystem.getID());
        this.sunSprite.set(starSystem.getStarType());
    }

    private boolean shouldShowRefitButton() {
        Game game = this.B;
        if (game.fleets.isFleetInSystem(game.getCurrentPlayer(), this.colony.getSystemID())) {
            Game game2 = this.B;
            Fleet fleetInSystem = game2.fleets.getFleetInSystem(game2.getCurrentPlayer(), this.colony.getSystemID());
            if (fleetInSystem.hasCombatShips()) {
                if (this.colony.hasBuilding(BuildingID.SHIP_YARDS)) {
                    return true;
                }
                int[] countOfEachType = fleetInSystem.getCountOfEachType(true);
                return (countOfEachType[ShipType.DESTROYER.id] == 0 && countOfEachType[ShipType.CRUISER.id] == 0) ? false : true;
            }
            return false;
        }
        return false;
    }

    private void showSpace() {
        this.surface.setVisible(false);
    }

    private void showSurface() {
        this.surface.setVisible(true);
    }

    private void turnPressesOff() {
        this.G.setVisible(false);
        this.selectPress.setVisible(false);
        this.pressedList.setVisible(false);
        this.pressedList2.setVisible(false);
        this.buildListPressButton.setVisible(false);
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

    public void M() {
        this.buildListOverlay.back();
    }

    public void N() {
        this.buildListOverlay.setOverlay(this.colony, this.B.getCurrentEmpire().getBuildLists());
    }

    public void O() {
        this.shipBuildList = this.B.getCurrentEmpire().getShipBuildList();
        fixShipList();
        List<Building> availableBuildingList = this.colony.getAvailableBuildingList();
        this.buildingsBuildList = availableBuildingList;
        Collections.sort(availableBuildingList, new Comparator() { // from class: com.birdshel.Uciana.Scenes.e0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int lambda$setProductionList$0;
                lambda$setProductionList$0 = ProductionScene.lambda$setProductionList$0((Building) obj, (Building) obj2);
                return lambda$setProductionList$0;
            }
        });
        setSelectedList();
        setQueue();
    }

    protected void P(ExtendedScene extendedScene, Object obj) {
        if (extendedScene instanceof ColoniesScene) {
            this.B.coloniesScene.L();
        } else if (extendedScene instanceof PlanetScene) {
            if (this.listButton.isVisible()) {
                this.B.planetScene.L(this.coloniesListIndex, this.colonies);
            } else {
                this.B.planetScene.loadPlanet(this.colony.getSystemID(), this.colony.getOrbit(), this.B.productionScene);
            }
        } else if (extendedScene instanceof GalaxyScene) {
            this.B.galaxyScene.setStarsAndNebulas();
        } else if (extendedScene instanceof SystemScene) {
            this.B.systemScene.loadSystem(((Integer) obj).intValue());
        } else if (extendedScene instanceof ShipDesignScene) {
            this.B.shipDesignScene.set(this.colony.getSystemID(), this.colony.getOrbit());
        } else if (extendedScene instanceof BuildListScene) {
            this.B.buildListScene.set(((Integer) obj).intValue(), this.colony.getSystemID(), this.colony.getOrbit());
        }
    }

    @Override // org.andengine.entity.scene.Scene
    public void back() {
        if (t()) {
            return;
        }
        changeScene(this.B.planetScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void checkInput(int i, Point point) {
        super.checkInput(i, point);
        if (point.getX() < 635.0f && point.getY() > this.queueContainer.getY()) {
            this.queueContainer.checkInput(i, point);
        } else {
            this.queueContainer.resetIfPressed();
        }
        int y = ((int) (point.getY() - this.productionList.getY())) / 105;
        if (i == 0) {
            if (point.getX() > getWidth() - 640.0f && point.getY() > 187.0f) {
                this.pressedY = point.getY();
                this.lastY = point.getY();
            }
            checkPressed(point, y);
        } else if (i != 1) {
            if (i != 2) {
                return;
            }
            turnPressesOff();
            checkMoved(point);
            if (this.isScroll) {
                return;
            }
            checkPressed(point, y);
        } else {
            this.queueContainer.upPress();
            if (this.isScroll) {
                this.isScroll = false;
                return;
            }
            turnPressesOff();
            checkButtons(point);
            if (point.getX() > getWidth() - 640.0f && point.getY() > 187.0f) {
                checkProductionList(point, y);
            }
            if (isClicked(this.blockaded, point)) {
                warningPressed(this.blockaded);
            }
            if (isClicked(this.starvation, point)) {
                warningPressed(this.starvation);
            }
            if (isClicked(this.lowPower, point)) {
                warningPressed(this.lowPower);
            }
            if (isClicked(this.emptyColony, point)) {
                warningPressed(this.emptyColony);
            }
        }
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void createScene(VertexBufferObjectManager vertexBufferObjectManager) {
        super.createScene(vertexBufferObjectManager);
        this.nebulas = this.B.nebulas;
        Entity entity = new Entity();
        this.nebulaBackground = entity;
        attachChild(entity);
        SunSprite sunSprite = new SunSprite(this.B, vertexBufferObjectManager, false);
        this.sunSprite = sunSprite;
        attachChild(sunSprite);
        TiledSprite H = H(30.0f, 225.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, GameIconEnum.OUTPOST.ordinal(), false);
        this.spaceDock = H;
        H.setSize(135.0f, 135.0f);
        TiledSprite H2 = H(getWidth() - 165.0f, 580.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, GameIconEnum.STAR_BASE.ordinal(), false);
        this.starBase = H2;
        H2.setSize(135.0f, 135.0f);
        float width = getWidth() - 165.0f;
        ITiledTextureRegion iTiledTextureRegion = this.B.graphics.gameIconsTexture;
        GameIconEnum gameIconEnum = GameIconEnum.TORPEDO_TURRET;
        TiledSprite H3 = H(width, 513.0f, iTiledTextureRegion, vertexBufferObjectManager, gameIconEnum.ordinal(), false);
        this.turret1 = H3;
        H3.setSize(67.0f, 67.0f);
        TiledSprite H4 = H(getWidth() - 215.0f, 648.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, gameIconEnum.ordinal(), false);
        this.turret2 = H4;
        H4.setSize(67.0f, 67.0f);
        TiledSprite H5 = H(getWidth() - 160.0f, 225.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, GameIconEnum.SHIP_YARDS.ordinal(), false);
        this.shipYards = H5;
        H5.setSize(135.0f, 135.0f);
        Sprite E = E(0.0f, 0.0f, this.B.graphics.planetSurfaceTexture, vertexBufferObjectManager, false);
        this.surface = E;
        E.setSize(2480.0f, 720.0f);
        Sprite E2 = E(0.0f, 90.0f, this.B.graphics.fadeBackgroundTexture, vertexBufferObjectManager, true);
        E2.setSize(635.0f, 100.0f);
        E2.setAlpha(0.6f);
        Sprite E3 = E(0.0f, 90.0f, this.B.graphics.farmingBarTexture, vertexBufferObjectManager, true);
        E3.setSize(635.0f, 100.0f);
        E3.setAlpha(0.6f);
        this.productionPerTurn = F(106.0f, 100.0f, this.B.fonts.smallInfoFont, this.D, this.E, vertexBufferObjectManager);
        ITiledTextureRegion iTiledTextureRegion2 = this.B.graphics.infoIconsTexture;
        InfoIconEnum infoIconEnum = InfoIconEnum.PRODUCTION;
        this.productionIcon = H(0.0f, 95.0f, iTiledTextureRegion2, vertexBufferObjectManager, infoIconEnum.ordinal(), true);
        this.productionAndTotalProd = F(106.0f, 154.0f, this.B.fonts.smallInfoFont, this.D, this.E, vertexBufferObjectManager);
        this.totalProductionIcon = H(0.0f, 149.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, infoIconEnum.ordinal(), true);
        this.productionName = G(0.0f, 100.0f, this.B.fonts.smallFont, this.D, this.E, vertexBufferObjectManager, true);
        this.productionTurnsLeft = G(0.0f, 154.0f, this.B.fonts.smallInfoFont, this.D, this.E, vertexBufferObjectManager, true);
        this.turnsIcon = H(401.0f, 149.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.TURN.ordinal(), true);
        this.productionImage = J(0.0f, 90.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, false);
        this.shipProductionImage = J(0.0f, 90.0f, this.B.graphics.shipsTextures[0], vertexBufferObjectManager, false);
        this.shipProductionTypeIcon = J(0.0f, 90.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, false);
        Sprite E4 = E(442.0f, 102.0f, this.B.graphics.farmingBarTexture, vertexBufferObjectManager, false);
        this.repeatOn = E4;
        E4.setSize(75.0f, 75.0f);
        Sprite E5 = E(442.0f, 102.0f, this.B.graphics.selectColonyTexture, vertexBufferObjectManager, false);
        this.repeatButton = E5;
        E5.setSize(75.0f, 75.0f);
        this.repeatButton.setAlpha(0.8f);
        TiledSprite H6 = H(459.0f, 119.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.REPEAT.ordinal(), false);
        this.repeatIcon = H6;
        H6.setSize(40.0f, 40.0f);
        TiledSprite H7 = H(517.0f, 97.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.BUY_PRODUCTION.ordinal(), true);
        this.buyNowButton = H7;
        s(H7);
        ProductionPercentBar productionPercentBar = new ProductionPercentBar(this.B, vertexBufferObjectManager, 325.0f);
        this.productionPercentBar = productionPercentBar;
        productionPercentBar.setPosition(106.0f, 137.0f);
        attachChild(this.productionPercentBar);
        Sprite E6 = E(640.0f, 0.0f, this.B.graphics.selectColonyTexture, vertexBufferObjectManager, false);
        this.selectPress = E6;
        E6.setSize(540.0f, 100.0f);
        Scene scene = new Scene();
        this.productionList = scene;
        scene.setPosition(getWidth() - 640.0f, 187.0f);
        this.productionList.setBackgroundEnabled(false);
        attachChild(this.productionList);
        ScrollBarControl scrollBarControl = new ScrollBarControl(getWidth() - 10.0f, 187.0f, 105, 533.0f, this.B, vertexBufferObjectManager);
        this.scrollBar = scrollBarControl;
        attachChild(scrollBarControl);
        E(0.0f, 0.0f, this.B.graphics.fadeBackgroundTexture, vertexBufferObjectManager, true).setSize(getWidth(), 86.0f);
        E(getWidth() - 640.0f, 86.0f, this.B.graphics.fadeBackgroundTexture, vertexBufferObjectManager, true).setSize(640.0f, 100.0f);
        Sprite E7 = E(getWidth() - 640.0f, 86.0f, this.B.graphics.selectColonyTexture, vertexBufferObjectManager, true);
        this.selectedList = E7;
        E7.setSize(320.0f, 100.0f);
        Sprite E8 = E(getWidth() - 640.0f, 86.0f, this.B.graphics.colonySeparatorTexture, vertexBufferObjectManager, true);
        this.selectedList2 = E8;
        E8.setSize(320.0f, 100.0f);
        Sprite E9 = E(getWidth() - 640.0f, 86.0f, this.B.graphics.selectColonyTexture, vertexBufferObjectManager, false);
        this.pressedList = E9;
        E9.setSize(320.0f, 100.0f);
        Sprite E10 = E(getWidth() - 640.0f, 86.0f, this.B.graphics.selectColonyTexture, vertexBufferObjectManager, false);
        this.pressedList2 = E10;
        E10.setSize(320.0f, 100.0f);
        Sprite E11 = E(getWidth() - 640.0f, 86.0f, this.B.graphics.productionButton, vertexBufferObjectManager, true);
        this.buildingsButton = E11;
        E11.setSize(320.0f, 100.0f);
        Sprite E12 = E(getWidth() - 320.0f, 86.0f, this.B.graphics.productionButton, vertexBufferObjectManager, true);
        this.shipsButton = E12;
        E12.setSize(320.0f, 100.0f);
        H(getWidth() - 590.0f, 71.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, GameIconEnum.BUILDINGS.ordinal(), true).setScale(0.8f);
        Game game = this.B;
        Text F = F(0.0f, 150.0f, game.fonts.smallFont, game.getActivity().getString(R.string.production_buildings), this.E, vertexBufferObjectManager);
        F.setX(((getWidth() - 550.0f) - (F.getWidthScaled() / 2.0f)) + 10.0f);
        H(getWidth() - 270.0f, 71.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, GameIconEnum.SHIPS.ordinal(), true).setScale(0.8f);
        Game game2 = this.B;
        Text F2 = F(0.0f, 150.0f, game2.fonts.smallFont, game2.getActivity().getString(R.string.production_ships), this.E, vertexBufferObjectManager);
        F2.setX(((getWidth() - 230.0f) - (F2.getWidthScaled() / 2.0f)) + 8.0f);
        this.autoBuildButton = H(getWidth() - 440.0f, 93.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.AUTO_BUILD.ordinal(), true);
        Game game3 = this.B;
        Text G = G(0.0f, 150.0f, game3.fonts.smallInfoFont, game3.getActivity().getString(R.string.production_auto), this.E, vertexBufferObjectManager, true);
        G.setX((getWidth() - 380.0f) - (G.getWidthScaled() / 2.0f));
        s(this.autoBuildButton);
        TiledSprite H8 = H(getWidth() - 440.0f, 93.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.PRESSED.ordinal(), false);
        this.selectedAutoBuild = H8;
        H8.setAlpha(0.5f);
        TiledSprite H9 = H(getWidth() - 120.0f, 93.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.SHIP_YARD.ordinal(), true);
        this.shipDesignButton = H9;
        s(H9);
        TiledSprite J = J(0.0f, 0.0f, this.B.graphics.empireColors, vertexBufferObjectManager, true);
        this.colonyBackground = J;
        J.setAlpha(0.6f);
        this.colonyBackground.setSize(getWidth(), 86.0f);
        PlanetSprite planetSprite = new PlanetSprite(this.B, vertexBufferObjectManager, 100, 86);
        this.planetSpriteHeader = planetSprite;
        attachChild(planetSprite);
        this.colonyName = F(100.0f, 10.0f, this.B.fonts.infoFont, this.D, this.E, vertexBufferObjectManager);
        ColonyInfoEntity colonyInfoEntity = new ColonyInfoEntity(this.B, vertexBufferObjectManager);
        this.colonyInfo = colonyInfoEntity;
        colonyInfoEntity.setPosition(100.0f, 45.0f);
        attachChild(this.colonyInfo);
        TiledSprite H10 = H(0.0f, 0.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.BLOCKADE.ordinal(), true);
        this.blockaded = H10;
        H10.setSize(40.0f, 40.0f);
        blinkSprite(this.blockaded);
        TiledSprite H11 = H(0.0f, 0.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.STARVING.ordinal(), true);
        this.starvation = H11;
        H11.setSize(40.0f, 40.0f);
        blinkSprite(this.starvation);
        TiledSprite H12 = H(0.0f, 0.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.POWER_WARNING.ordinal(), true);
        this.lowPower = H12;
        H12.setSize(40.0f, 40.0f);
        blinkSprite(this.lowPower);
        TiledSprite H13 = H(0.0f, 0.0f, this.B.graphics.infoIconsTexture, vertexBufferObjectManager, InfoIconEnum.EMPTY_COLONY_WARNING.ordinal(), true);
        this.emptyColony = H13;
        H13.setSize(40.0f, 40.0f);
        blinkSprite(this.emptyColony);
        int i = 0;
        while (true) {
            ProductionListElement[] productionListElementArr = this.elements;
            if (i < productionListElementArr.length) {
                productionListElementArr[i] = new ProductionListElement(this.B, vertexBufferObjectManager, true);
                this.productionList.attachChild(this.elements[i]);
                i++;
            } else {
                this.G = H(0.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.PRESSED.ordinal(), false);
                TiledSprite H14 = H(getWidth() - 120.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.GALAXY.ordinal(), true);
                this.galaxyButton = H14;
                s(H14);
                TiledSprite H15 = H(getWidth() - 240.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.SYSTEM.ordinal(), true);
                this.systemButton = H15;
                s(H15);
                TiledSprite H16 = H(getWidth() - 360.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.PLANET.ordinal(), true);
                this.planetButton = H16;
                s(H16);
                TiledSprite H17 = H(getWidth() - 480.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.COLONIES.ordinal(), true);
                this.listButton = H17;
                s(H17);
                TiledSprite H18 = H(0.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.PREVIOUS.ordinal(), true);
                this.previousButton = H18;
                s(H18);
                TiledSprite H19 = H(115.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.NEXT.ordinal(), true);
                this.nextButton = H19;
                s(H19);
                QueueItemContainer queueItemContainer = new QueueItemContainer(this.B, vertexBufferObjectManager);
                this.queueContainer = queueItemContainer;
                queueItemContainer.setY(195.0f);
                attachChild(this.queueContainer);
                Sprite sprite = new Sprite(0.0f, 0.0f, this.B.graphics.fadeBackgroundTexture, vertexBufferObjectManager);
                sprite.setAlpha(0.4f);
                sprite.setSize(317.0f, 100.0f);
                TiledSprite tiledSprite = new TiledSprite(158.0f, 195.0f, this.B.graphics.empireColors, vertexBufferObjectManager);
                this.buildListButton = tiledSprite;
                tiledSprite.setCurrentTileIndex(4);
                this.buildListButton.setSize(317.0f, 100.0f);
                attachChild(this.buildListButton);
                this.buildListButton.attachChild(sprite);
                Sprite sprite2 = new Sprite(0.0f, 0.0f, this.B.graphics.selectColonyTexture, vertexBufferObjectManager);
                this.buildListPressButton = sprite2;
                sprite2.setVisible(false);
                this.buildListPressButton.setSize(317.0f, 100.0f);
                this.buildListButton.attachChild(this.buildListPressButton);
                TiledSprite tiledSprite2 = new TiledSprite(100.0f, 12.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
                tiledSprite2.setSize(75.0f, 75.0f);
                tiledSprite2.setCurrentTileIndex(GameIconEnum.BUILDINGS.ordinal());
                this.buildListButton.attachChild(tiledSprite2);
                TiledSprite tiledSprite3 = new TiledSprite(170.0f, 25.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager);
                tiledSprite3.setSize(50.0f, 50.0f);
                tiledSprite3.setCurrentTileIndex(GameIconEnum.MOVE.ordinal());
                this.buildListButton.attachChild(tiledSprite3);
                this.shipDesignsOverlay = new ShipDesignsOverlay(this.B, vertexBufferObjectManager);
                this.buildListOverlay = new BuildListOverlay(this.B, vertexBufferObjectManager);
                this.H = new MessageOverlay(this.B, vertexBufferObjectManager);
                return;
            }
        }
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void getPoolElements() {
        if (this.nebulas.hasParent()) {
            this.nebulas.detachSelf();
        }
        this.nebulaBackground.attachChild(this.nebulas);
        PlanetSprite planetSprite = this.B.planetSpritePool.get();
        this.planetSprite = planetSprite;
        planetSprite.setPosition(getWidth() / 2.0f, 920.0f);
        this.planetSprite.setMoonRange(665, TypedValues.Motion.TYPE_ANIMATE_RELATIVE_TO);
        this.nebulaBackground.attachChild(this.planetSprite);
    }

    public void refitShip(String str, boolean z, boolean z2) {
        Game game = this.B;
        this.shipDesignsOverlay.setOverlay(this.colony, game.fleets.getFleetInSystem(game.getCurrentPlayer(), this.colony.getSystemID()).getShip(str), z, z2);
        setChildScene(this.shipDesignsOverlay, false, false, true);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void refresh() {
        this.selectedAutoBuild.setVisible(this.colony.isAutoBuilding());
        refreshQueue();
        updateProduction();
    }

    public void refreshQueue() {
        this.queueContainer.refresh();
        this.buildListButton.setY(this.queueContainer.getBottomY());
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void releasePoolElements(final ExtendedScene extendedScene, final Object obj) {
        this.B.getEngine().runOnUpdateThread(new Runnable() { // from class: com.birdshel.Uciana.Scenes.d0
            {
                ProductionScene.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                ProductionScene.this.lambda$releasePoolElements$1(extendedScene, obj);
            }
        });
    }

    public void set(int i, List<Colony> list) {
        this.coloniesListIndex = i;
        this.colonies = list;
        set(list.get(i), this.B.coloniesScene);
    }

    public void setQueuedOverlays() {
        for (ProductionListElement productionListElement : this.elements) {
            productionListElement.showQueuedOverlay(false);
        }
        if (this.selectedList.getX() == this.buildingsButton.getX()) {
            Iterator<ProductionItem> it = this.colony.getQueue().iterator();
            while (it.hasNext()) {
                ProductionItem next = it.next();
                if (next.getType() == ManufacturingType.BUILDINGS) {
                    int i = 0;
                    for (Building building : this.buildingsBuildList) {
                        int i2 = this.productionListIndex;
                        if (i - i2 >= 0) {
                            if (i - i2 >= this.elements.length) {
                                break;
                            } else if (next.getID().equals(building.getStringID())) {
                                this.elements[i - this.productionListIndex].showQueuedOverlay(true);
                            }
                        }
                        i++;
                    }
                }
            }
        }
    }

    public void setRefitShip(Ship ship, Ship ship2, boolean z) {
        if (z) {
            this.colony.getManufacturing().addRefitShipToQueue(ship, ship2);
        } else {
            this.colony.getManufacturing().setRefitShip(ship, ship2);
        }
        Fleet fleetInSystem = this.B.fleets.getFleetInSystem(this.colony.getEmpireID(), this.colony.getSystemID());
        fleetInSystem.removeShip(ship2.getID());
        if (fleetInSystem.isEmpty()) {
            this.B.fleets.remove(fleetInSystem);
        }
        setQueue();
        updateProduction();
        setSelectedList();
    }

    public void setSelectedOverlay() {
        ProductionItem currentItem = this.colony.getManufacturing().getCurrentItem();
        int i = 0;
        for (ProductionListElement productionListElement : this.elements) {
            productionListElement.showSelectedOverlay(false);
        }
        if (this.selectedList.getX() == this.buildingsButton.getX() && currentItem.getType() == ManufacturingType.BUILDINGS) {
            for (Building building : this.buildingsBuildList) {
                int i2 = this.productionListIndex;
                if (i - i2 >= 0) {
                    if (i - i2 >= this.elements.length) {
                        return;
                    }
                    if (currentItem.getID().equals(building.getStringID())) {
                        this.elements[i - this.productionListIndex].showSelectedOverlay(true);
                    }
                }
                i++;
            }
        }
    }

    public void showConfirmForRefitRemoval(String str, int i) {
        this.B.confirmOverlay.setOverlay(str, this.colony.getManufacturing(), i);
        setChildScene(this.B.confirmOverlay, false, false, true);
    }

    public void showShipSelectOverlayForRefit() {
        boolean hasBuilding = this.colony.hasBuilding(BuildingID.SHIP_YARDS);
        Game game = this.B;
        this.B.shipSelectOverlay.setOverlayForRefit(game.fleets.getFleetInSystem(game.getCurrentPlayer(), this.colony.getSystemID()).id, false, hasBuilding);
        setChildScene(this.B.shipSelectOverlay, false, false, true);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void switched() {
        this.B.shipSelectOverlay.back();
        super.switched();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void update() {
    }

    public void updateDisplay() {
        setQueue();
        updateProduction();
        setQueuedOverlays();
        setSelectedOverlay();
    }

    public void updateProduction() {
        this.productionName.setText(this.colony.getNameOfProduction());
        this.productionTurnsLeft.setText(this.colony.getTurnsLeftOnProduction());
        float width = this.productionName.getWidth();
        float width2 = this.productionTurnsLeft.getWidth();
        this.totalProductionIcon.setVisible(false);
        this.productionAndTotalProd.setVisible(false);
        this.productionPercentBar.hide();
        this.shipProductionImage.setVisible(false);
        this.shipProductionTypeIcon.setVisible(false);
        this.productionImage.setVisible(false);
        ManufacturingType type = this.colony.getManufacturing().getType();
        ManufacturingType manufacturingType = ManufacturingType.SHIP;
        if (type == manufacturingType) {
            this.shipProductionImage.setPosition(0.0f, 90.0f);
            this.shipProductionImage.setVisible(true);
            ShipType shipType = this.colony.getManufacturing().getShipType();
            this.shipProductionImage.setTiledTextureRegion((ITiledTextureRegion) this.B.graphics.shipsTextures[this.colony.getEmpireID()]);
            this.shipProductionImage.setCurrentTileIndex(this.colony.getManufacturing().getShipIconIndex());
            float selectScreenSize = this.colony.getManufacturing().getShipType().getSelectScreenSize();
            this.shipProductionImage.setSize(selectScreenSize, selectScreenSize);
            TiledSprite tiledSprite = this.shipProductionImage;
            float f2 = 50.0f - (selectScreenSize / 2.0f);
            tiledSprite.setX(tiledSprite.getX() + f2);
            TiledSprite tiledSprite2 = this.shipProductionImage;
            tiledSprite2.setY(tiledSprite2.getY() + f2);
            this.shipProductionTypeIcon.setVisible(true);
            this.shipProductionTypeIcon.setCurrentTileIndex(InfoIconEnum.getShipIcon(shipType));
        } else if (this.colony.getManufacturing().getType() == ManufacturingType.BUILDINGS) {
            this.productionImage.setVisible(true);
            this.productionImage.setCurrentTileIndex(this.colony.getManufacturing().getImageIndex());
        }
        this.productionPercentBar.update();
        if (this.colony.getManufacturing().showGreyProgressBar()) {
            this.buyNowButton.setVisible(false);
            this.turnsIcon.setVisible(false);
            this.productionTurnsLeft.setX(506.0f - width2);
            this.productionPercentBar.setWidth(400.0f);
            this.productionPercentBar.setWidth(400.0f);
            this.productionName.setX(506.0f - width);
            this.turnsIcon.setX(476.0f);
            this.repeatOn.setVisible(false);
            this.repeatButton.setVisible(false);
            this.repeatIcon.setVisible(false);
        } else {
            this.buyNowButton.setVisible(true);
            this.buyNowButton.setAlpha(1.0f);
            this.turnsIcon.setVisible(true);
            if (this.colony.getManufacturing().getCostToFinish() > this.B.getCurrentEmpire().getCredits()) {
                this.buyNowButton.setAlpha(0.4f);
            }
            if (this.colony.getManufacturing().getCurrentItem().getType() == manufacturingType) {
                this.productionPercentBar.setWidth(325.0f);
                this.productionTurnsLeft.setX(395.0f - width2);
                this.productionName.setX(431.0f - width);
                this.turnsIcon.setX(401.0f);
                if (this.colony.getManufacturing().getCurrentItem().getID().startsWith("refit-")) {
                    this.repeatOn.setVisible(false);
                    this.repeatButton.setVisible(false);
                    this.repeatIcon.setVisible(false);
                } else {
                    this.repeatOn.setVisible(this.colony.getManufacturing().getCurrentItem().isRepeatOn());
                    this.repeatButton.setVisible(true);
                    this.repeatIcon.setVisible(true);
                }
            } else {
                this.productionPercentBar.setWidth(400.0f);
                this.productionTurnsLeft.setX(470.0f - width2);
                this.productionName.setX(506.0f - width);
                this.turnsIcon.setX(476.0f);
                this.repeatOn.setVisible(false);
                this.repeatButton.setVisible(false);
                this.repeatIcon.setVisible(false);
            }
            this.totalProductionIcon.setVisible(true);
            this.productionAndTotalProd.setVisible(true);
            int requiredProduction = this.colony.getManufacturing().getRequiredProduction();
            int finishedProduction = this.colony.getManufacturing().getFinishedProduction();
            this.productionAndTotalProd.setText(finishedProduction + " / " + requiredProduction);
            this.totalProductionIcon.setX(this.productionAndTotalProd.getX() + this.productionAndTotalProd.getWidth() + 5.0f);
        }
        this.colonyInfo.set(this.colony);
    }

    private void setBuildingsList(int i) {
        this.productionListIndex = i;
        int i2 = 0;
        while (true) {
            ProductionListElement[] productionListElementArr = this.elements;
            if (i2 < productionListElementArr.length) {
                productionListElementArr[i2].setVisible(false);
                int i3 = i + i2;
                if (i3 < this.buildingsBuildList.size()) {
                    this.elements[i2].setVisible(true);
                    this.elements[i2].set(true, this.colony.getProductionPerTurn(), this.colony.getPlanet().getClimateCostModifier(), this.buildingsBuildList.get(i3));
                    this.elements[i2].setY(i3 * 105);
                }
                i2++;
            } else {
                setQueuedOverlays();
                setSelectedOverlay();
                return;
            }
        }
    }

    public void set(Colony colony, ExtendedScene extendedScene) {
        this.colony = colony;
        this.whereFrom = extendedScene;
        this.colonyInfo.set(colony);
        setSpritesInvisible();
        setSystemBackground(this.B.galaxy.getStarSystem(colony.getSystemID()));
        setPlanet(colony.getPlanet());
        this.surface.setVisible(true);
        Game game = this.B;
        this.surface.setTiledTextureRegion(game.graphics.setSurfaceTexture("Surfaces/" + colony.getPlanet().getClimate().getID() + ".png", game.getActivity()));
        this.surface.setX(-Functions.random.nextInt(2480 - ((int) getWidth())));
        this.colonyBackground.setCurrentTileIndex(colony.getEmpireID());
        Planet planet = colony.getPlanet();
        this.planetSpriteHeader.setPlanet(planet, planet.getScale(this.B.buildingsScene), Moon.getScale(this.B.buildingsScene));
        this.colonyName.setText(colony.getName());
        this.productionPerTurn.setText("+" + colony.getProductionPerTurn());
        this.productionIcon.setX(this.productionPerTurn.getX() + this.productionPerTurn.getWidth() + 5.0f);
        this.productionPercentBar.set(colony);
        if (colony.isAutoBuilding()) {
            this.selectedAutoBuild.setVisible(true);
        }
        updateProduction();
        O();
        if (extendedScene instanceof ColoniesScene) {
            this.planetSpriteHeader.setPosition(280.0f, 43.0f);
            this.colonyName.setX(330.0f);
            this.colonyInfo.setX(330.0f);
        } else {
            this.planetSpriteHeader.setPosition(50.0f, 43.0f);
            this.colonyName.setX(100.0f);
            this.colonyInfo.setX(100.0f);
        }
        checkForWarnings();
    }

    private void setShipList(int i) {
        this.productionListIndex = i;
        int i2 = 0;
        boolean z = false;
        while (true) {
            ProductionListElement[] productionListElementArr = this.elements;
            if (i2 >= productionListElementArr.length) {
                return;
            }
            productionListElementArr[i2].setVisible(false);
            int i3 = i + i2;
            if (i3 >= this.shipBuildList.size()) {
                if (shouldShowRefitButton() && !z) {
                    this.elements[i2].setVisible(true);
                    this.elements[i2].setRefitShip();
                    this.elements[i2].setY(i3 * 105);
                    z = true;
                }
            } else {
                this.elements[i2].setVisible(true);
                this.elements[i2].set(this.colony.getProductionPerTurn(), this.shipBuildList.get(i3));
                this.elements[i2].setY(i3 * 105);
            }
            i2++;
        }
    }
}
