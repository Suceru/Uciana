package com.birdshel.Uciana.Scenes;

import com.birdshel.Uciana.Colonies.Buildings.Building;
import com.birdshel.Uciana.Colonies.Buildings.BuildingID;
import com.birdshel.Uciana.Colonies.Buildings.Buildings;
import com.birdshel.Uciana.Colonies.ManufacturingType;
import com.birdshel.Uciana.Colonies.ProductionItem;
import com.birdshel.Uciana.Controls.ScrollBarControl;
import com.birdshel.Uciana.Elements.ProductionScene.BuildListContainer;
import com.birdshel.Uciana.Elements.ProductionScene.ProductionListElement;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Players.BuildLists;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.StarSystems.Nebulas;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.andengine.entity.Entity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.color.Color;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class BuildListScene extends ExtendedScene {
    private LinkedList<ProductionItem> buildList;
    private int buildListIndex;
    private List<Building> buildingsBuildList;
    private TiledSprite cancelButton;
    private Sprite cancelButtonPressed;
    private Text cancelText;
    private TiledSprite doneButton;
    private Sprite doneButtonPressed;
    private Text doneText;
    private TiledSprite empireBackground;
    private TiledSprite empireBanner;
    private TiledSprite galaxyButton;
    private float lastY;
    private Entity nebulaBackground;
    private Nebulas nebulas;
    private int orbit;
    private TiledSprite planetButton;
    private float pressedY;
    private TiledSprite productionButton;
    private Scene productionList;
    private int productionListIndex;
    private BuildListContainer queueContainer;
    private ScrollBarControl scrollBar;
    private Sprite selectPress;
    private TiledSprite systemButton;
    private int systemID;
    private final ProductionListElement[] elements = new ProductionListElement[7];
    private boolean isScroll = false;
    private final int ITEM_SIZE = 105;
    private final int PRODUCTION_LIST_Y = 95;

    public BuildListScene(Game game) {
        this.B = game;
    }

    private void cancelButtonPressed() {
        changeScene(this.B.productionScene);
        this.B.productionScene.N();
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void checkActionDown(Point point) {
        if (point.getX() < 540.0f && point.getY() > 95.0f) {
            this.pressedY = point.getY();
            this.lastY = point.getY();
        }
        checkPress(point);
    }

    private void checkActionMove(Point point) {
        turnPressOff();
        checkPress(point);
        if (point.getX() < 540.0f && point.getY() > 95.0f) {
            if (this.buildingsBuildList.size() * 105 > 559) {
                if (this.pressedY - point.getY() > 25.0f || this.pressedY - point.getY() < -25.0f) {
                    this.isScroll = true;
                }
                float y = this.productionList.getY() - (this.lastY - point.getY());
                if (y > 95.0f) {
                    y = 95.0f;
                }
                float size = ((this.buildingsBuildList.size() * 105) - 720) * (-1);
                if (y < size) {
                    y = size;
                }
                this.productionList.setY(y);
                this.lastY = point.getY();
                this.scrollBar.update(y);
                int abs = Math.abs((int) ((y - 95.0f) / 105.0f));
                if (abs != this.productionListIndex) {
                    setBuildingsList(abs);
                }
            }
        } else {
            this.lastY = point.getY();
        }
        if (this.isScroll) {
            return;
        }
        checkPress(point);
    }

    private void checkActionUp(Point point) {
        turnPressOff();
        if (this.isScroll) {
            this.isScroll = false;
            return;
        }
        if (point.getX() < 540.0f && point.getY() > 95.0f) {
            checkProductionList(((int) (point.getY() - this.productionList.getY())) / 105);
        }
        if (isClicked(this.galaxyButton, point)) {
            galaxyButtonPressed();
        } else if (isClicked(this.systemButton, point)) {
            systemButtonPressed();
        } else if (isClicked(this.planetButton, point)) {
            planetButtonPressed();
        } else if (isClicked(this.productionButton, point)) {
            productionButtonPressed();
        } else if (isClicked(this.doneButton, point)) {
            doneButtonPressed();
        } else if (isClicked(this.cancelButton, point)) {
            cancelButtonPressed();
        }
    }

    private void checkPress(Point point) {
        int y;
        if (isClicked(this.doneButton, point)) {
            this.doneButtonPressed.setVisible(true);
            this.doneText.setColor(Color.WHITE);
        }
        if (isClicked(this.cancelButton, point)) {
            this.cancelButtonPressed.setVisible(true);
            this.cancelText.setColor(Color.WHITE);
        }
        if (point.getX() >= 540.0f || point.getY() <= 95.0f || this.isScroll || this.buildingsBuildList.size() <= (y = ((int) (point.getY() - this.productionList.getY())) / 105)) {
            return;
        }
        this.selectPress.setY(this.productionList.getY() + (y * 105));
        this.selectPress.setVisible(true);
    }

    private void checkProductionList(int i) {
        if (this.buildingsBuildList.size() <= i) {
            return;
        }
        if (this.buildList.size() < 5) {
            Building building = this.buildingsBuildList.get(i);
            String stringID = building.getStringID();
            ProductionItem productionItem = new ProductionItem(ManufacturingType.BUILDINGS, stringID, building.getName(), building.getProductionCost());
            boolean z = false;
            Iterator<ProductionItem> it = this.buildList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (it.next().getID().equals(stringID)) {
                    z = true;
                    break;
                }
            }
            if (!z) {
                this.buildList.add(productionItem);
                this.queueContainer.refresh();
            }
        }
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void doneButtonPressed() {
        BuildLists buildLists = this.B.getCurrentEmpire().getBuildLists();
        HashMap hashMap = new HashMap();
        Iterator<ProductionItem> it = this.buildList.iterator();
        int i = 0;
        while (it.hasNext()) {
            hashMap.put(Integer.valueOf(i), Buildings.getBuilding(it.next().getID()).getID());
            i++;
        }
        buildLists.setItems(this.buildListIndex, hashMap);
        changeScene(this.B.productionScene);
        this.B.productionScene.N();
        this.B.sounds.playBoxPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void galaxyButtonPressed() {
        changeScene(this.B.galaxyScene);
        this.B.productionScene.M();
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    public /* synthetic */ void lambda$releasePoolElements$1(ExtendedScene extendedScene, Object obj) {
        this.nebulaBackground.detachChild(this.nebulas);
        extendedScene.getPoolElements();
        M(extendedScene, obj);
        this.B.setCurrentScene(extendedScene);
    }

    public static /* synthetic */ int lambda$set$0(Building building, Building building2) {
        return building.getName().compareToIgnoreCase(building2.getName());
    }

    private void planetButtonPressed() {
        changeScene(this.B.planetScene);
        this.B.productionScene.M();
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void productionButtonPressed() {
        changeScene(this.B.productionScene);
        this.B.productionScene.N();
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void setBuildingsList(int i) {
        this.productionListIndex = i;
        int i2 = 0;
        while (true) {
            ProductionListElement[] productionListElementArr = this.elements;
            if (i2 >= productionListElementArr.length) {
                return;
            }
            productionListElementArr[i2].setVisible(false);
            int i3 = i + i2;
            if (i3 < this.buildingsBuildList.size()) {
                this.elements[i2].setVisible(true);
                this.elements[i2].set(false, 0, 1.0f, this.buildingsBuildList.get(i3));
                this.elements[i2].setY(i3 * 105);
            }
            i2++;
        }
    }

    private void systemButtonPressed() {
        changeScene(this.B.systemScene, Integer.valueOf(this.systemID));
        this.B.productionScene.M();
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void turnPressOff() {
        this.doneButtonPressed.setVisible(false);
        Text text = this.doneText;
        Color color = Color.BLACK;
        text.setColor(color);
        this.cancelButtonPressed.setVisible(false);
        this.cancelText.setColor(color);
        this.selectPress.setVisible(false);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void B(Point point) {
    }

    protected void M(ExtendedScene extendedScene, Object obj) {
        if (extendedScene instanceof GalaxyScene) {
            this.B.galaxyScene.setStarsAndNebulas();
        } else if (extendedScene instanceof SystemScene) {
            this.B.systemScene.loadSystem(((Integer) obj).intValue());
        } else if (extendedScene instanceof PlanetScene) {
            Game game = this.B;
            game.planetScene.loadPlanet(this.systemID, this.orbit, game.productionScene);
        } else if (extendedScene instanceof ProductionScene) {
            this.B.productionScene.O();
        }
    }

    @Override // org.andengine.entity.scene.Scene
    public void back() {
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void checkInput(int i, Point point) {
        if (point.getX() > getWidth() - 635.0f && point.getY() > this.queueContainer.getY() && point.getY() < this.queueContainer.getY() + 525.0f) {
            this.queueContainer.checkInput(i, point);
        } else {
            this.queueContainer.resetIfPressed();
        }
        if (this.queueContainer.isQueueItemMoved()) {
            if (i == 1) {
                this.queueContainer.resetMoved();
                return;
            }
            return;
        }
        super.checkInput(i, point);
        if (i == 0) {
            checkActionDown(point);
        } else if (i == 1) {
            this.queueContainer.upPress();
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
        this.nebulaBackground = new Entity();
        if (getWidth() == 1480.0f) {
            this.nebulaBackground.setX(100.0f);
        }
        attachChild(this.nebulaBackground);
        Sprite E = E(0.0f, 0.0f, this.B.graphics.selectColonyTexture, vertexBufferObjectManager, false);
        this.selectPress = E;
        E.setSize(540.0f, 100.0f);
        Scene scene = new Scene();
        this.productionList = scene;
        scene.setPosition(0.0f, 95.0f);
        this.productionList.setBackgroundEnabled(false);
        attachChild(this.productionList);
        ScrollBarControl scrollBarControl = new ScrollBarControl(540.0f, 95.0f, 105, 625.0f, this.B, vertexBufferObjectManager);
        this.scrollBar = scrollBarControl;
        attachChild(scrollBarControl);
        int i = 0;
        while (true) {
            ProductionListElement[] productionListElementArr = this.elements;
            if (i < productionListElementArr.length) {
                productionListElementArr[i] = new ProductionListElement(this.B, vertexBufferObjectManager, false);
                this.productionList.attachChild(this.elements[i]);
                i++;
            } else {
                E(0.0f, 0.0f, this.B.graphics.fadeBackgroundTexture, vertexBufferObjectManager, true).setSize(getWidth(), 95.0f);
                E(0.0f, 0.0f, this.B.graphics.colonySeparatorTexture, vertexBufferObjectManager, true).setSize(getWidth(), 95.0f);
                Game game = this.B;
                Text G = G(120.0f, 0.0f, game.fonts.infoFont, game.getActivity().getString(R.string.build_list_header), new TextOptions(HorizontalAlign.CENTER), vertexBufferObjectManager, true);
                G.setY(47.5f - (G.getHeightScaled() / 2.0f));
                TiledSprite J = J(3.0f, 3.0f, this.B.graphics.empireColors, vertexBufferObjectManager, true);
                this.empireBackground = J;
                J.setSize(95.0f, 95.0f);
                this.empireBackground.setAlpha(0.75f);
                TiledSprite J2 = J(3.0f, 3.0f, this.B.graphics.gameIconsTexture, vertexBufferObjectManager, true);
                this.empireBanner = J2;
                J2.setSize(95.0f, 95.0f);
                this.G = H(0.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.PRESSED.ordinal(), false);
                TiledSprite H = H(getWidth() - 120.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.GALAXY.ordinal(), true);
                this.galaxyButton = H;
                s(H);
                TiledSprite H2 = H(getWidth() - 240.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.SYSTEM.ordinal(), true);
                this.systemButton = H2;
                s(H2);
                TiledSprite H3 = H(getWidth() - 360.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.PLANET.ordinal(), true);
                this.planetButton = H3;
                s(H3);
                TiledSprite H4 = H(getWidth() - 480.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.PRODUCTION.ordinal(), true);
                this.productionButton = H4;
                s(H4);
                E(getWidth() - 635.0f, 95.0f, this.B.graphics.colonySeparatorTexture, vertexBufferObjectManager, true).setSize(635.0f, 525.0f);
                BuildListContainer buildListContainer = new BuildListContainer(this.B, vertexBufferObjectManager);
                this.queueContainer = buildListContainer;
                buildListContainer.setPosition(getWidth() - 635.0f, 95.0f);
                attachChild(this.queueContainer);
                TiledSprite H5 = H(getWidth() - 300.0f, 645.0f, this.B.graphics.empireColors, vertexBufferObjectManager, 10, true);
                this.doneButton = H5;
                H5.setSize(300.0f, 75.0f);
                this.doneButton.setAlpha(0.7f);
                this.doneButton.setBlendFunction(IShape.BLENDFUNCTION_SOURCE_DEFAULT, 771);
                this.doneButton.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(new AlphaModifier(1.0f, 0.6f, 0.7f), new AlphaModifier(1.0f, 0.7f, 0.6f))));
                Sprite sprite = new Sprite(2.0f, 2.0f, this.B.graphics.blackenedBackgroundTexture, vertexBufferObjectManager);
                this.doneButtonPressed = sprite;
                sprite.setVisible(false);
                this.doneButtonPressed.setSize(296.0f, 71.0f);
                this.doneButton.attachChild(this.doneButtonPressed);
                Game game2 = this.B;
                Text text = new Text(0.0f, 0.0f, game2.fonts.infoFont, game2.getActivity().getString(R.string.build_list_done), this.E, vertexBufferObjectManager);
                this.doneText = text;
                Color color = Color.BLACK;
                text.setColor(color);
                Text text2 = this.doneText;
                text2.setX(150.0f - (text2.getWidthScaled() / 2.0f));
                Text text3 = this.doneText;
                text3.setY(37.0f - (text3.getHeightScaled() / 2.0f));
                this.doneButton.attachChild(this.doneText);
                TiledSprite H6 = H(getWidth() - 620.0f, 645.0f, this.B.graphics.empireColors, vertexBufferObjectManager, 10, true);
                this.cancelButton = H6;
                H6.setSize(300.0f, 75.0f);
                this.cancelButton.setAlpha(0.7f);
                Sprite sprite2 = new Sprite(2.0f, 2.0f, this.B.graphics.blackenedBackgroundTexture, vertexBufferObjectManager);
                this.cancelButtonPressed = sprite2;
                sprite2.setVisible(false);
                this.cancelButtonPressed.setSize(296.0f, 71.0f);
                this.cancelButton.attachChild(this.cancelButtonPressed);
                Game game3 = this.B;
                Text text4 = new Text(0.0f, 0.0f, game3.fonts.infoFont, game3.getActivity().getString(R.string.build_list_cancel), this.E, vertexBufferObjectManager);
                this.cancelText = text4;
                text4.setColor(color);
                Text text5 = this.cancelText;
                text5.setX(150.0f - (text5.getWidthScaled() / 2.0f));
                Text text6 = this.cancelText;
                text6.setY(37.0f - (text6.getHeightScaled() / 2.0f));
                this.cancelButton.attachChild(this.cancelText);
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
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void refresh() {
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void releasePoolElements(final ExtendedScene extendedScene, final Object obj) {
        this.B.getEngine().runOnUpdateThread(new Runnable() { // from class: com.birdshel.Uciana.Scenes.c
            {
                BuildListScene.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                BuildListScene.this.lambda$releasePoolElements$1(extendedScene, obj);
            }
        });
    }

    public void set(int i, int i2, int i3) {
        this.systemID = i2;
        this.orbit = i3;
        this.buildListIndex = i;
        this.nebulas.set();
        this.empireBackground.setCurrentTileIndex(this.B.getCurrentPlayer());
        this.empireBanner.setCurrentTileIndex(GameIconEnum.getEmpireBanner(this.B.getCurrentPlayer()));
        List<Building> buildingList = this.B.getCurrentEmpire().getBuildingList();
        this.buildingsBuildList = buildingList;
        Collections.sort(buildingList, new Comparator() { // from class: com.birdshel.Uciana.Scenes.d
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int lambda$set$0;
                lambda$set$0 = BuildListScene.lambda$set$0((Building) obj, (Building) obj2);
                return lambda$set$0;
            }
        });
        this.buildingsBuildList.remove(Buildings.getBuilding(BuildingID.NONE));
        this.buildingsBuildList.remove(Buildings.getBuilding(BuildingID.CAPITAL));
        this.buildingsBuildList.remove(Buildings.getBuilding(BuildingID.SPY_NETWORK_RED));
        this.buildingsBuildList.remove(Buildings.getBuilding(BuildingID.SPY_NETWORK_GREEN));
        this.buildingsBuildList.remove(Buildings.getBuilding(BuildingID.SPY_NETWORK_BLUE));
        this.buildingsBuildList.remove(Buildings.getBuilding(BuildingID.SPY_NETWORK_ORANGE));
        this.buildingsBuildList.remove(Buildings.getBuilding(BuildingID.SPY_NETWORK_YELLOW));
        this.buildingsBuildList.remove(Buildings.getBuilding(BuildingID.SPY_NETWORK_PURPLE));
        setBuildingsList(0);
        this.scrollBar.update(95.0f, this.buildingsBuildList.size());
        this.productionList.setY(95.0f);
        BuildLists buildLists = this.B.getCurrentEmpire().getBuildLists();
        this.buildList = new LinkedList<>();
        for (Map.Entry<Integer, BuildingID> entry : buildLists.getItems(i).entrySet()) {
            Building building = Buildings.getBuilding(entry.getValue());
            this.buildList.add(new ProductionItem(ManufacturingType.BUILDINGS, building.getStringID(), building.getName(), building.getProductionCost()));
        }
        this.queueContainer.set(this.buildList);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void switched() {
        super.switched();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void update() {
    }
}
