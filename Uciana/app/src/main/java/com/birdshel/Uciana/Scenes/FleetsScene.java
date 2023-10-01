package com.birdshel.Uciana.Scenes;

import com.birdshel.Uciana.Controls.ScrollBarControl;
import com.birdshel.Uciana.Elements.FleetListElement;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Overlays.ShipSortOverlay;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.Ships.ShipComponents.WeaponStats;
import com.birdshel.Uciana.Ships.ShipSort;
import com.birdshel.Uciana.StarSystems.Nebulas;

import org.andengine.entity.Entity;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class FleetsScene extends ExtendedScene {
    private Scene fleetList;
    private int fleetListIndex;
    private List<Fleet> fleets;
    private TiledSprite galaxyButton;
    private float lastY;
    private Entity nebulaBackground;
    private Nebulas nebulas;
    private float pressedY;
    private ScrollBarControl scrollBar;
    private Sprite selectPress;
    private ShipSortOverlay shipSortOverlay;
    private TiledSprite shipYardButton;
    private TiledSprite sortButton;
    private ShipSort sortBy;
    private Sprite systemPress;
    private final FleetListElement[] elements = new FleetListElement[6];
    private boolean isScroll = false;
    private final int ITEM_SIZE = WeaponStats.NOVA_BOMB_MAX_DAMAGE;

    public FleetsScene(Game game) {
        this.B = game;
    }

    private void checkActionDown(int i, Point point) {
        if (point.getX() < getWidth() - 120.0f) {
            this.pressedY = point.getY();
            this.isScroll = false;
            this.lastY = point.getY();
            if (this.fleets.size() > i) {
                checkPress(i, point);
            }
        }
    }

    private void checkActionMove(int i, Point point) {
        this.selectPress.setVisible(false);
        this.systemPress.setVisible(false);
        for (FleetListElement fleetListElement : this.elements) {
            fleetListElement.showShipsButtonPressInvisible(false);
        }
        if (point.getX() < getWidth() - 120.0f) {
            if (this.fleets.size() > 4) {
                if (this.pressedY - point.getY() > 25.0f || this.pressedY - point.getY() < -25.0f) {
                    this.isScroll = true;
                }
                float y = this.fleetList.getY() - (this.lastY - point.getY());
                if (y > 0.0f) {
                    y = 0.0f;
                }
                float size = ((this.fleets.size() * WeaponStats.NOVA_BOMB_MAX_DAMAGE) - 720) * (-1);
                if (y < size) {
                    y = size;
                }
                this.fleetList.setY(y);
                this.lastY = point.getY();
                this.scrollBar.update(y);
                int abs = Math.abs((int) (y / 150.0f));
                if (abs != this.fleetListIndex) {
                    setFleetList(abs);
                }
            }
            if (this.fleets.size() <= i || this.isScroll) {
                return;
            }
            checkPress(i, point);
        }
    }

    private void checkActionUp(int i, Point point) {
        this.selectPress.setVisible(false);
        this.systemPress.setVisible(false);
        for (FleetListElement fleetListElement : this.elements) {
            fleetListElement.showShipsButtonPressInvisible(false);
        }
        if (point.getX() < getWidth() - 120.0f && !this.isScroll && this.fleets.size() > i) {
            fleetPressed(i, point);
        }
        if (isClicked(this.galaxyButton, point)) {
            galaxyButtonPressed();
        } else if (isClicked(this.shipYardButton, point)) {
            shipyardButtonPressed();
        } else if (isClicked(this.sortButton, point)) {
            sortButtonPressed();
        }
    }

    private void checkPress(int i, Point point) {
        if (point.getX() > getWidth() - 440.0f) {
            float y = point.getY();
            float f2 = i * WeaponStats.NOVA_BOMB_MAX_DAMAGE;
            if (y < this.fleetList.getY() + f2 + 60.0f) {
                this.systemPress.setY(this.fleetList.getY() + f2);
                this.systemPress.setVisible(true);
                return;
            }
        }
        if (point.getX() > getWidth() - 255.0f) {
            this.elements[i - this.fleetListIndex].showShipsButtonPressInvisible(true);
            return;
        }
        this.selectPress.setY(this.fleetList.getY() + (i * WeaponStats.NOVA_BOMB_MAX_DAMAGE));
        this.selectPress.setVisible(true);
    }

    private void fleetPressed(int i, Point point) {
        Fleet fleet = this.fleets.get(i);
        if (point.getX() > getWidth() - 440.0f && point.getY() < (i * WeaponStats.NOVA_BOMB_MAX_DAMAGE) + this.fleetList.getY() + 60.0f) {
            if (!this.B.getCurrentEmpire().isDiscoveredSystem(fleet.getSystemID())) {
                return;
            }
            changeScene(this.B.systemScene, Integer.valueOf(fleet.getSystemID()));
        } else if (point.getX() > getWidth() - 255.0f) {
            showShipsPressed(i);
        } else {
            this.B.getCurrentEmpire().setSelectedFleetID(fleet.id);
            changeScene(this.B.galaxyScene);
        }
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void galaxyButtonPressed() {
        changeScene(this.B.galaxyScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    public /* synthetic */ void lambda$releasePoolElements$0(ExtendedScene extendedScene, Object obj) {
        this.nebulaBackground.detachChild(this.nebulas);
        for (FleetListElement fleetListElement : this.elements) {
            fleetListElement.releasePoolElements();
        }
        extendedScene.getPoolElements();
        L(extendedScene, obj);
        this.B.setCurrentScene(extendedScene);
    }

    private void set() {
        this.nebulas.set();
        Game game = this.B;
        ShipSort shipSort = game.empires.get(game.getCurrentPlayer()).getShipSort();
        this.sortBy = shipSort;
        this.sortButton.setCurrentTileIndex(shipSort.getValue());
        Game game2 = this.B;
        this.fleets = game2.fleets.sort(game2.getCurrentPlayer(), this.sortBy);
        this.fleetList.setPosition(0.0f, 0.0f);
        setFleetList(0);
        this.scrollBar.update(this.fleetList.getY(), this.fleets.size());
    }

    private void setFleetList(int i) {
        this.fleetListIndex = i;
        int i2 = 0;
        while (true) {
            FleetListElement[] fleetListElementArr = this.elements;
            if (i2 >= fleetListElementArr.length) {
                return;
            }
            fleetListElementArr[i2].setVisible(false);
            int i3 = i + i2;
            if (i3 < this.fleets.size()) {
                this.elements[i2].setVisible(true);
                this.elements[i2].set(this.fleets.get(i3));
                this.elements[i2].setY(i3 * WeaponStats.NOVA_BOMB_MAX_DAMAGE);
            }
            i2++;
        }
    }

    private void shipyardButtonPressed() {
        changeScene(this.B.shipDesignScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void showShipsPressed(int i) {
        this.B.shipSelectOverlay.setOverlay(this.fleets.get(i).id, this, true);
        setChildScene(this.B.shipSelectOverlay, false, false, true);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void sortButtonPressed() {
        this.shipSortOverlay.setOverlay(this.sortBy);
        setChildScene(this.shipSortOverlay, false, false, true);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void B(Point point) {
    }

    protected void L(ExtendedScene extendedScene, Object obj) {
        if (extendedScene instanceof GalaxyScene) {
            this.B.galaxyScene.setStarsAndNebulas();
        } else if (extendedScene instanceof SystemScene) {
            this.B.systemScene.loadSystem(((Integer) obj).intValue());
        } else if (extendedScene instanceof ShipDesignScene) {
            this.B.shipDesignScene.setFromFleetScene();
        }
    }

    @Override // org.andengine.entity.scene.Scene
    public void back() {
        if (t()) {
            return;
        }
        if (hasChildScene()) {
            this.B.shipSelectOverlay.back();
            this.shipSortOverlay.back();
            return;
        }
        changeScene(this.B.galaxyScene);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void checkInput(int i, Point point) {
        super.checkInput(i, point);
        int y = ((int) (point.getY() - this.fleetList.getY())) / WeaponStats.NOVA_BOMB_MAX_DAMAGE;
        if (i == 0) {
            checkActionDown(y, point);
        } else if (i == 1) {
            checkActionUp(y, point);
        } else if (i != 2) {
        } else {
            checkActionMove(y, point);
        }
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void createScene(VertexBufferObjectManager vertexBufferObjectManager) {
        super.createScene(vertexBufferObjectManager);
        this.nebulas = this.B.nebulas;
        this.nebulaBackground = new Entity();
        if (getWidth() == 1480.0f) {
            this.nebulaBackground.setX(100.0f);
        }
        attachChild(this.nebulaBackground);
        Sprite E = E(0.0f, 0.0f, this.B.graphics.selectColonyTexture, vertexBufferObjectManager, false);
        this.selectPress = E;
        E.setSize(getWidth() - 135.0f, 145.0f);
        Sprite E2 = E(getWidth() - 435.0f, 0.0f, this.B.graphics.selectColonyTexture, vertexBufferObjectManager, false);
        this.systemPress = E2;
        E2.setSize(300.0f, 60.0f);
        Scene scene = new Scene();
        this.fleetList = scene;
        scene.setPosition(0.0f, 0.0f);
        int i = 0;
        this.fleetList.setBackgroundEnabled(false);
        attachChild(this.fleetList);
        while (true) {
            FleetListElement[] fleetListElementArr = this.elements;
            if (i < fleetListElementArr.length) {
                fleetListElementArr[i] = new FleetListElement(this.B, vertexBufferObjectManager, getWidth());
                this.fleetList.attachChild(this.elements[i]);
                i++;
            } else {
                ScrollBarControl scrollBarControl = new ScrollBarControl(getWidth() - 135.0f, 0.0f, WeaponStats.NOVA_BOMB_MAX_DAMAGE, 720.0f, this.B, vertexBufferObjectManager);
                this.scrollBar = scrollBarControl;
                attachChild(scrollBarControl);
                TiledSprite H = H(getWidth() - 120.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.GALAXY.ordinal(), true);
                this.galaxyButton = H;
                s(H);
                TiledSprite H2 = H(getWidth() - 120.0f, 86.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.SHIP_YARD.ordinal(), true);
                this.shipYardButton = H2;
                s(H2);
                TiledSprite H3 = H(getWidth() - 120.0f, 632.0f, this.B.graphics.sortButtonsTexture, vertexBufferObjectManager, 0, true);
                this.sortButton = H3;
                s(H3);
                this.shipSortOverlay = new ShipSortOverlay(this.B, vertexBufferObjectManager);
                return;
            }
        }
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void getPoolElements() {
        for (FleetListElement fleetListElement : this.elements) {
            fleetListElement.getPoolElements();
        }
        if (this.nebulas.hasParent()) {
            this.nebulas.detachSelf();
        }
        this.nebulaBackground.attachChild(this.nebulas);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void refresh() {
        set();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void releasePoolElements(final ExtendedScene extendedScene, final Object obj) {
        this.B.getEngine().runOnUpdateThread(new Runnable() { // from class: com.birdshel.Uciana.Scenes.n
            {
                FleetsScene.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                FleetsScene.this.lambda$releasePoolElements$0(extendedScene, obj);
            }
        });
    }

    public void reload(ShipSort shipSort) {
        this.B.getCurrentEmpire().setShipSort(shipSort);
        refresh();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void switched() {
        this.B.shipSelectOverlay.back();
        super.switched();
        set();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void update() {
    }

    public void updateFleets() {
        float y = this.fleetList.getY();
        set();
        if (this.fleets.size() > 4) {
            if (y > 0.0f) {
                y = 0.0f;
            }
            float size = ((this.fleets.size() * WeaponStats.NOVA_BOMB_MAX_DAMAGE) - 720) * (-1);
            if (y < size) {
                y = size;
            }
            this.fleetList.setY(y);
            this.scrollBar.update(y);
            int abs = Math.abs((int) (y / 150.0f));
            if (abs != this.fleetListIndex) {
                setFleetList(abs);
            }
        }
    }
}
