package com.birdshel.Uciana.Overlays;

import com.birdshel.Uciana.Elements.SelectShipElement;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Messages.TextMessage;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Scenes.FleetsScene;
import com.birdshel.Uciana.Scenes.GalaxyScene;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.Ships.Ship;
import com.birdshel.Uciana.Ships.ShipComponents.WeaponStats;
import com.birdshel.Uciana.Ships.ShipType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ShipSelectOverlay extends ExtendedChildScene {
    private boolean addToQueue;
    private final TiledSprite buttonPress;
    private final TiledSprite closeButton;
    private int currentRow;
    private final SelectShipElement[] elements;
    private Fleet fleet;
    private final Text fleetHeader;
    private final Text fleetMenuFleetStatus;
    private final Text fleetMenuSelectedCount;
    private Scene fromScene;
    private boolean hasShipYard;
    private boolean isLoaded;
    private boolean isScroll;
    private float lastY;
    private int numberOfColumns;
    private float pressedY;
    private boolean refitSelect;
    private final TiledSprite scrapButton;
    private final Sprite scrollBarBackground;
    private final TiledSprite scrollBarVisible1;
    private final TiledSprite scrollBarVisible2;
    private final TiledSprite selectAllButton;
    private final TiledSprite selectAllButtonBackground;
    private String selectedFleetID;
    private List<String> selectedShipIDs;
    private final TiledSprite shipClassIcon;
    private final TiledSprite shipClassSelectButton;
    private final ShipClassSelectOverlay shipClassSelectOverlay;
    private final Scene shipList;
    private final float shipListTop;
    private final Sprite shipSelectPress;
    private int[] shipTypeCounts;
    private final List<Ship> ships;
    private boolean showControls;
    private final TiledSprite unselectAllButton;
    private final TiledSprite unselectAllButtonBackground;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShipSelectOverlay(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        super(game, vertexBufferObjectManager, false);
        int i = 0;
        this.ships = new ArrayList();
        this.elements = new SelectShipElement[30];
        this.selectedFleetID = "";
        this.selectedShipIDs = new ArrayList();
        this.isScroll = false;
        this.shipListTop = 86.0f;
        this.numberOfColumns = 5;
        if (getWidth() == 1480.0f) {
            this.numberOfColumns = 6;
        }
        Sprite t = t(0.0f, 0.0f, game.graphics.selectColonyTexture, vertexBufferObjectManager, false);
        this.shipSelectPress = t;
        t.setSize(200.0f, 200.0f);
        Scene scene = new Scene();
        this.shipList = scene;
        scene.setPosition(0.0f, 86.0f);
        scene.setBackgroundEnabled(false);
        attachChild(scene);
        while (true) {
            SelectShipElement[] selectShipElementArr = this.elements;
            if (i >= selectShipElementArr.length) {
                break;
            }
            selectShipElementArr[i] = new SelectShipElement(game, vertexBufferObjectManager);
            this.shipList.attachChild(this.elements[i]);
            i++;
        }
        Sprite sprite = new Sprite(getWidth() - 270.0f, 86.0f, game.graphics.blackenedBackgroundTexture, vertexBufferObjectManager);
        this.scrollBarBackground = sprite;
        sprite.setSize(10.0f, 634.0f);
        attachChild(sprite);
        TiledSprite tiledSprite = new TiledSprite(getWidth() - 268.0f, 86.0f, game.graphics.empireColors, vertexBufferObjectManager);
        this.scrollBarVisible1 = tiledSprite;
        tiledSprite.setCurrentTileIndex(3);
        tiledSprite.setWidth(6.0f);
        attachChild(tiledSprite);
        TiledSprite tiledSprite2 = new TiledSprite(getWidth() - 268.0f, 86.0f, game.graphics.empireColors, vertexBufferObjectManager);
        this.scrollBarVisible2 = tiledSprite2;
        tiledSprite2.setCurrentTileIndex(3);
        tiledSprite2.setWidth(6.0f);
        attachChild(tiledSprite2);
        t(0.0f, 0.0f, game.graphics.fadeBackgroundTexture, vertexBufferObjectManager, true).setSize(getWidth(), 86.0f);
        this.fleetHeader = v(0.0f, 0.0f, game.fonts.smallFont, this.E, this.F, vertexBufferObjectManager, true);
        Text v = v(5.0f, 5.0f, game.fonts.smallFont, this.E, this.F, vertexBufferObjectManager, true);
        this.fleetMenuSelectedCount = v;
        v.setColor(Color.GREEN);
        if (getWidth() == 1480.0f) {
            v.setX(25.0f);
        }
        Text v2 = v(0.0f, 35.0f, game.fonts.smallFont, this.E, this.F, vertexBufferObjectManager, true);
        this.fleetMenuFleetStatus = v2;
        v2.setColor(Color.RED);
        o(v2);
        this.buttonPress = w(0.0f, 0.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.PRESSED.ordinal(), false);
        float width = getWidth() - 120.0f;
        ITiledTextureRegion iTiledTextureRegion = game.graphics.buttonsTexture;
        ButtonsEnum buttonsEnum = ButtonsEnum.BLANK;
        this.unselectAllButtonBackground = w(width, 317.0f, iTiledTextureRegion, vertexBufferObjectManager, buttonsEnum.ordinal(), true);
        this.selectAllButtonBackground = w(getWidth() - 120.0f, 231.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, buttonsEnum.ordinal(), true);
        TiledSprite w = w(getWidth() - 120.0f, 317.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.RADIO_OFF.ordinal(), true);
        this.unselectAllButton = w;
        n(w);
        TiledSprite w2 = w(getWidth() - 120.0f, 231.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.RADIO_ON.ordinal(), true);
        this.selectAllButton = w2;
        n(w2);
        TiledSprite w3 = w(getWidth() - 120.0f, 403.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, buttonsEnum.ordinal(), true);
        this.shipClassSelectButton = w3;
        n(w3);
        TiledSprite w4 = w(getWidth() - 85.0f, 421.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager, GameIconEnum.SHIPS.ordinal(), true);
        this.shipClassIcon = w4;
        w4.setSize(50.0f, 50.0f);
        TiledSprite w5 = w(getWidth() - 120.0f, 0.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.CLOSE.ordinal(), true);
        this.closeButton = w5;
        n(w5);
        TiledSprite w6 = w(getWidth() - 120.0f, 634.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.CREDITS.ordinal(), false);
        this.scrapButton = w6;
        n(w6);
        this.shipClassSelectOverlay = new ShipClassSelectOverlay(game, vertexBufferObjectManager, this);
        this.J = new MessageOverlay(game, vertexBufferObjectManager);
    }

    private void addShips() {
        this.ships.clear();
        for (Ship ship : this.fleet.getUnretreatedShips()) {
            if (!this.refitSelect || ship.isCombatShip()) {
                this.ships.add(ship);
            }
        }
        setShipList(0);
    }

    private void autoButtonPressed(int i) {
        this.fleet.getUnretreatedShips().get(i).setAuto(this.elements[i - (this.currentRow * this.numberOfColumns)].toggleAutoButton());
        this.C.sounds.playSystemObjectPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void checkActionDown(int i, Point point, int i2, int i3) {
        Iterator<TiledSprite> it = this.H.iterator();
        while (it.hasNext()) {
            TiledSprite next = it.next();
            if (q(next, point)) {
                this.buttonPress.setPosition(next.getX(), next.getY());
                this.buttonPress.setVisible(true);
            }
        }
        if (point.getX() >= this.numberOfColumns * WeaponStats.DIMENSIONAL_ENERGY_BOMB_MIN_DAMAGE || point.getY() <= 86.0f) {
            return;
        }
        this.pressedY = point.getY();
        boolean z = false;
        this.isScroll = false;
        if (this.fleet.getUnretreatedShips().size() > i && !this.isScroll) {
            if ((!this.refitSelect || i < this.fleet.getCombatShips().size()) ? true : true) {
                this.shipSelectPress.setX(i2 * WeaponStats.DIMENSIONAL_ENERGY_BOMB_MIN_DAMAGE);
                this.shipSelectPress.setY((i3 * WeaponStats.DIMENSIONAL_ENERGY_BOMB_MIN_DAMAGE) + this.shipList.getY());
                this.shipSelectPress.setVisible(true);
            }
        }
        this.lastY = point.getY();
    }

    private void checkActionMove(int i, Point point, int i2, int i3) {
        boolean z = false;
        this.buttonPress.setVisible(false);
        this.shipSelectPress.setVisible(false);
        Iterator<TiledSprite> it = this.H.iterator();
        while (it.hasNext()) {
            TiledSprite next = it.next();
            if (q(next, point)) {
                this.buttonPress.setPosition(next.getX(), next.getY());
                this.buttonPress.setVisible(true);
            }
        }
        if (point.getY() < 86.0f) {
            return;
        }
        if (point.getX() < this.numberOfColumns * WeaponStats.DIMENSIONAL_ENERGY_BOMB_MIN_DAMAGE && this.fleet.getUnretreatedShips().size() > 15) {
            if (this.pressedY - point.getY() > 25.0f || this.pressedY - point.getY() < -25.0f) {
                this.isScroll = true;
            }
            float y = this.shipList.getY() - (this.lastY - point.getY());
            if (y > 86.0f) {
                y = 86.0f;
            }
            float round = ((Math.round((this.fleet.getUnretreatedShips().size() / this.numberOfColumns) + 0.4f) * WeaponStats.DIMENSIONAL_ENERGY_BOMB_MIN_DAMAGE) - 720) * (-1);
            if (y < round) {
                y = round;
            }
            this.shipList.setY(y);
            this.lastY = point.getY();
            setScrollBar();
            int abs = Math.abs((int) ((y - 86.0f) / 200.0f));
            if (abs != this.currentRow) {
                setShipList(abs);
            }
        }
        if (point.getX() >= this.numberOfColumns * WeaponStats.DIMENSIONAL_ENERGY_BOMB_MIN_DAMAGE || this.fleet.getUnretreatedShips().size() <= i || this.isScroll) {
            return;
        }
        if ((!this.refitSelect || i < this.fleet.getCombatShips().size()) ? true : true) {
            this.shipSelectPress.setX(i2 * WeaponStats.DIMENSIONAL_ENERGY_BOMB_MIN_DAMAGE);
            this.shipSelectPress.setY((i3 * WeaponStats.DIMENSIONAL_ENERGY_BOMB_MIN_DAMAGE) + this.shipList.getY());
            this.shipSelectPress.setVisible(true);
        }
    }

    private void checkActionUp(int i, Point point) {
        SelectShipElement[] selectShipElementArr;
        this.buttonPress.setVisible(false);
        this.shipSelectPress.setVisible(false);
        for (SelectShipElement selectShipElement : this.elements) {
            if (point.getX() > selectShipElement.getX() && point.getX() < selectShipElement.getX() + 200.0f && point.getY() > this.shipList.getY() + selectShipElement.getY() && point.getY() < this.shipList.getY() + selectShipElement.getY() + 200.0f) {
                float x = point.getX() - selectShipElement.getX();
                float y = ((point.getY() - 86.0f) - (this.shipList.getY() - 86.0f)) - selectShipElement.getY();
                if (selectShipElement.wasAutoPressed(x, y)) {
                    autoButtonPressed(i);
                    return;
                } else if (selectShipElement.wasDetailPressed(x, y)) {
                    detailButtonPressed(i);
                    return;
                }
            }
        }
        if (q(this.closeButton, point)) {
            closeButtonPressed();
        }
        if (q(this.unselectAllButton, point)) {
            unselectAllButtonPressed();
        }
        if (q(this.selectAllButton, point)) {
            selectAllButtonPressed();
        }
        if (q(this.shipClassSelectButton, point)) {
            shipClassSelectButtonPressed();
        }
        if (q(this.scrapButton, point)) {
            scrapButtonPressed();
        }
        if (point.getX() >= this.numberOfColumns * WeaponStats.DIMENSIONAL_ENERGY_BOMB_MIN_DAMAGE || point.getY() <= 86.0f || this.isScroll || i >= this.fleet.getUnretreatedShips().size()) {
            return;
        }
        if (this.elements[i - (this.currentRow * this.numberOfColumns)].getShipAlpha()) {
            shipPressed(i);
        } else {
            showMessage(new TextMessage(this.C.getActivity().getString(R.string.ship_select_refit_ship_yard_needed)));
        }
    }

    private void closeButtonPressed() {
        if (this.C.getCurrentScene() instanceof GalaxyScene) {
            if (this.showControls && this.C.galaxyScene.selectedShipIDs.isEmpty()) {
                this.C.getCurrentEmpire().setSelectedFleetID("none");
                this.C.galaxyScene.selectFleet(this.selectedFleetID);
            }
            this.C.galaxyScene.fleetControl.setControl(this.selectedFleetID, true);
            this.C.galaxyScene.showButtons();
        } else if (this.C.getCurrentScene() instanceof FleetsScene) {
            this.C.fleetsScene.updateFleets();
        }
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        back();
    }

    private void detailButtonPressed(int i) {
        Ship ship = this.fleet.getUnretreatedShips().get(i);
        if (ship.isCombatShip()) {
            this.C.shipDetailOverlay.setOverlay(ship);
            setChildScene(this.C.shipDetailOverlay, false, false, true);
            this.C.sounds.playSystemObjectPressSound();
            Game game = this.C;
            game.vibrate(game.BUTTON_VIBRATE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$releasePoolElements$0() {
        for (SelectShipElement selectShipElement : this.elements) {
            selectShipElement.releasePoolElements();
        }
        this.C.shipDetailOverlay.releasePoolElements();
    }

    private void scrapButtonPressed() {
        this.C.confirmOverlay.setOverlay(this.selectedFleetID, this.selectedShipIDs);
        setChildScene(this.C.confirmOverlay, false, false, true);
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void selectAllButtonPressed() {
        SelectShipElement[] selectShipElementArr;
        this.selectedShipIDs.clear();
        for (Ship ship : this.fleet.getUnretreatedShips()) {
            this.selectedShipIDs.add(ship.getID());
        }
        int size = this.fleet.getUnretreatedShips().size();
        int i = 0;
        for (SelectShipElement selectShipElement : this.elements) {
            if (i < size) {
                selectShipElement.setShipHighlight(true);
            }
            i++;
        }
        updateSelectedShipCount();
        updateScrapButton();
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void setHeader() {
        if (this.refitSelect) {
            this.fleetHeader.setText(this.C.getActivity().getString(R.string.ship_select_refit));
        } else {
            int size = this.fleet.getUnretreatedShips().size();
            if (size == 1) {
                this.fleetHeader.setText(this.C.getActivity().getString(R.string.ship_select_size_single, new Object[]{this.fleet.getDestinationText()}));
            } else {
                this.fleetHeader.setText(this.C.getActivity().getString(R.string.ship_select_size_many, new Object[]{Integer.valueOf(size), this.fleet.getDestinationText()}));
            }
        }
        this.fleetHeader.setX((getWidth() / 2.0f) - (this.fleetHeader.getWidth() / 2.0f));
    }

    private void setScrollBar() {
        this.scrollBarBackground.setVisible(false);
        this.scrollBarVisible1.setVisible(false);
        this.scrollBarVisible2.setVisible(false);
        int round = Math.round((this.fleet.getUnretreatedShips().size() / this.numberOfColumns) + 0.4f) * WeaponStats.DIMENSIONAL_ENERGY_BOMB_MIN_DAMAGE;
        if (round <= 634) {
            return;
        }
        this.scrollBarBackground.setVisible(true);
        this.scrollBarVisible1.setVisible(true);
        this.scrollBarVisible2.setVisible(true);
        float f2 = round;
        float f3 = (634.0f / f2) * 634.0f;
        this.scrollBarVisible1.setHeight(f3);
        this.scrollBarVisible2.setHeight(f3);
        float y = ((((this.shipList.getY() - 86.0f) * (-1.0f)) / f2) * 634.0f) + 86.0f;
        this.scrollBarVisible1.setY(y);
        this.scrollBarVisible2.setY(y);
    }

    private void setShipList(int i) {
        this.currentRow = i;
        int i2 = i;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            SelectShipElement[] selectShipElementArr = this.elements;
            if (i3 >= selectShipElementArr.length) {
                return;
            }
            selectShipElementArr[i3].setVisible(false);
            if ((this.numberOfColumns * i) + i3 < this.ships.size()) {
                if (i4 == this.numberOfColumns) {
                    i2++;
                    i4 = 0;
                }
                this.elements[i3].setVisible(true);
                this.elements[i3].set(this.fleet, this.ships.get((this.numberOfColumns * i) + i3), this.refitSelect, this.hasShipYard);
                this.elements[i3].setShipHighlight(this.selectedShipIDs.contains(this.ships.get((this.numberOfColumns * i) + i3).getID()));
                this.elements[i3].setPosition(i4 * WeaponStats.DIMENSIONAL_ENERGY_BOMB_MIN_DAMAGE, i2 * WeaponStats.DIMENSIONAL_ENERGY_BOMB_MIN_DAMAGE);
                i4++;
            }
            i3++;
        }
    }

    private void setupOverlay(String str, Scene scene, boolean z) {
        this.fromScene = scene;
        this.showControls = z;
        if (scene instanceof GalaxyScene) {
            this.selectedShipIDs = this.C.galaxyScene.selectedShipIDs;
        } else if (scene instanceof FleetsScene) {
            this.selectedShipIDs = new ArrayList();
            for (Ship ship : this.C.fleets.get(str).getShips()) {
                this.selectedShipIDs.add(ship.getID());
            }
        }
        this.refitSelect = false;
        setupOverlay(str, z);
    }

    private void shipClassSelectButtonPressed() {
        this.shipClassSelectOverlay.setOverlay(this.shipTypeCounts);
        setChildScene(this.shipClassSelectOverlay, false, false, true);
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void shipPressed(int i) {
        if (i >= this.fleet.getUnretreatedShips().size()) {
            return;
        }
        if (this.refitSelect) {
            if (i >= this.fleet.getCombatShips().size()) {
                return;
            }
            back();
            this.C.productionScene.refitShip(this.fleet.getCombatShips().get(i).getID(), this.addToQueue, this.hasShipYard);
        } else if (this.showControls) {
            togglePressedShip(i);
        }
        this.C.sounds.playBoxPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void togglePressedShip(int i) {
        if (i < this.fleet.getUnretreatedShips().size()) {
            String id = this.fleet.getUnretreatedShips().get(i).getID();
            if (this.selectedShipIDs.contains(id)) {
                this.selectedShipIDs.remove(id);
                this.elements[i - (this.currentRow * this.numberOfColumns)].setShipHighlight(false);
            } else {
                this.selectedShipIDs.add(id);
                this.elements[i - (this.currentRow * this.numberOfColumns)].setShipHighlight(true);
            }
            if (this.C.getCurrentScene() instanceof GalaxyScene) {
                this.C.galaxyScene.showShipCount();
            }
            updateSelectedShipCount();
            updateScrapButton();
        }
    }

    private void unselectAllButtonPressed() {
        this.selectedShipIDs.clear();
        for (SelectShipElement selectShipElement : this.elements) {
            selectShipElement.setShipHighlight(false);
        }
        updateSelectedShipCount();
        updateScrapButton();
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void updateScrapButton() {
        if (this.selectedShipIDs.isEmpty()) {
            this.scrapButton.setAlpha(0.4f);
        } else {
            this.scrapButton.setAlpha(1.0f);
        }
    }

    private void updateSelectedShipCount() {
        String string = this.C.getActivity().getString(R.string.ship_select_single);
        if (this.selectedShipIDs.size() != 1) {
            string = this.C.getActivity().getString(R.string.ship_select_many, new Object[]{Integer.valueOf(this.selectedShipIDs.size())});
        }
        this.fleetMenuSelectedCount.setText(string);
        if (this.fleet.canCommunicate()) {
            return;
        }
        String string2 = this.C.getActivity().getString(R.string.ship_select_out_of_comm_range_details, new Object[]{Integer.valueOf(this.fleet.getDistanceOfClosestColonyOrOutpost()), Integer.valueOf(GameData.empires.get(this.fleet.empireID).getTech().getShipCommunicationRange())});
        Text text = this.fleetMenuFleetStatus;
        text.setText(this.C.getActivity().getString(R.string.ship_select_out_of_comm_range) + "\n" + string2);
        this.fleetMenuFleetStatus.setX((getWidth() / 2.0f) - (this.fleetMenuFleetStatus.getWidthScaled() / 2.0f));
    }

    @Override // org.andengine.entity.scene.Scene
    public void back() {
        releasePoolElements();
        super.back();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    public void checkInput(int i, Point point) {
        super.checkInput(i, point);
        int x = ((int) point.getX()) / WeaponStats.DIMENSIONAL_ENERGY_BOMB_MIN_DAMAGE;
        int y = ((int) (point.getY() - this.shipList.getY())) / WeaponStats.DIMENSIONAL_ENERGY_BOMB_MIN_DAMAGE;
        int i2 = (this.numberOfColumns * y) + x;
        if (i == 0) {
            checkActionDown(i2, point, x, y);
        } else if (i == 1) {
            checkActionUp(i2, point);
        } else if (i != 2) {
        } else {
            checkActionMove(i2, point, x, y);
        }
    }

    public void getPoolElements() {
        for (SelectShipElement selectShipElement : this.elements) {
            selectShipElement.initialSet();
        }
        this.C.shipDetailOverlay.getPoolElements();
    }

    public void releasePoolElements() {
        if (this.isLoaded) {
            this.C.getEngine().runOnUpdateThread(new Runnable() { // from class: com.birdshel.Uciana.Overlays.a
                @Override // java.lang.Runnable
                public final void run() {
                    ShipSelectOverlay.this.lambda$releasePoolElements$0();
                }
            });
        }
        this.isLoaded = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void scrap() {
        for (String str : this.selectedShipIDs) {
            this.fleet.scrap(str);
        }
        if (this.fleet.getSize() == 0) {
            this.C.fleets.remove(this.fleet);
            if (this.C.getCurrentEmpire().getSelectedFleetID().equals(this.selectedFleetID)) {
                this.C.getCurrentEmpire().setSelectedFleetID("none");
            }
            Scene scene = this.fromScene;
            if (scene instanceof GalaxyScene) {
                this.C.galaxyScene.refresh();
            } else if (scene instanceof FleetsScene) {
                this.C.fleetsScene.updateFleets();
            }
            back();
            return;
        }
        updateSelectedShipCount();
        updateScrapButton();
        this.selectedShipIDs.clear();
        if (this.C.getCurrentScene() instanceof GalaxyScene) {
            this.C.galaxyScene.selectedShipIDs.clear();
            this.C.galaxyScene.refresh();
            this.C.galaxyScene.hideButtons();
        }
        setupOverlay(this.selectedFleetID, this.fromScene, this.showControls);
    }

    public void setOverlay(String str) {
        this.refitSelect = false;
        getPoolElements();
        setupOverlay(str, false);
    }

    public void setOverlayForRefit(String str, boolean z, boolean z2) {
        this.addToQueue = z;
        this.hasShipYard = z2;
        this.refitSelect = true;
        getPoolElements();
        setupOverlay(str, false);
    }

    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    protected void update() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void z(ShipType shipType, boolean z) {
        int i = 0;
        for (Ship ship : this.fleet.getUnretreatedShips()) {
            if (ship.getShipType() == shipType) {
                if (z) {
                    if (!this.selectedShipIDs.contains(ship.getID())) {
                        this.selectedShipIDs.add(ship.getID());
                    }
                } else {
                    this.selectedShipIDs.remove(ship.getID());
                }
                int i2 = this.currentRow;
                int i3 = this.numberOfColumns;
                int i4 = i - (i2 * i3);
                if (i4 >= 0) {
                    SelectShipElement[] selectShipElementArr = this.elements;
                    if (i4 < selectShipElementArr.length) {
                        selectShipElementArr[i - (i2 * i3)].setShipHighlight(z);
                    }
                }
            }
            i++;
        }
        updateSelectedShipCount();
        updateScrapButton();
    }

    public void setOverlay(String str, Scene scene, boolean z) {
        this.C.getActivity().setLocale();
        getPoolElements();
        setupOverlay(str, scene, z);
    }

    private void setupOverlay(String str, boolean z) {
        this.isLoaded = true;
        this.selectedFleetID = str;
        this.showControls = z;
        Fleet fleet = this.C.fleets.get(str);
        this.fleet = fleet;
        this.shipTypeCounts = fleet.getCountOfEachType(false);
        this.fleetMenuSelectedCount.setVisible(z);
        this.fleetMenuFleetStatus.setVisible(z);
        this.unselectAllButtonBackground.setVisible(z);
        this.selectAllButtonBackground.setVisible(z);
        this.unselectAllButton.setVisible(z);
        this.selectAllButton.setVisible(z);
        this.scrapButton.setVisible(z);
        this.shipClassSelectButton.setVisible(false);
        this.shipClassIcon.setVisible(false);
        if (z) {
            float f2 = 274.0f;
            int i = 0;
            for (ShipType shipType : ShipType.getShipTypesForShips()) {
                if (this.shipTypeCounts[shipType.id] > 0) {
                    i++;
                }
            }
            if (i > 1) {
                f2 = 231.0f;
                this.shipClassSelectButton.setVisible(true);
                this.shipClassIcon.setVisible(true);
            }
            float f3 = f2 + 86.0f;
            this.unselectAllButtonBackground.setY(f3);
            this.selectAllButtonBackground.setY(f2);
            this.unselectAllButton.setY(f3);
            this.selectAllButton.setY(f2);
        }
        this.fleetMenuFleetStatus.setText("");
        setHeader();
        addShips();
        this.shipList.setPosition(0.0f, 86.0f);
        updateSelectedShipCount();
        updateScrapButton();
        setScrollBar();
    }
}
