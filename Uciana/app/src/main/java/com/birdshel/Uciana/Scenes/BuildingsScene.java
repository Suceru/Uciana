package com.birdshel.Uciana.Scenes;

import com.birdshel.Uciana.Colonies.Buildings.BuildingID;
import com.birdshel.Uciana.Colonies.Buildings.Buildings;
import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Controls.ScrollBarControl;
import com.birdshel.Uciana.Elements.Buildings.BuildingListElement;
import com.birdshel.Uciana.Elements.ProductionScene.ColonyInfoEntity;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Functions;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Messages.SellOneBuildingMessage;
import com.birdshel.Uciana.Overlays.MessageOverlay;
import com.birdshel.Uciana.Planets.Moon;
import com.birdshel.Uciana.Planets.Planet;
import com.birdshel.Uciana.Planets.PlanetSprite;
import com.birdshel.Uciana.Resources.ButtonsEnum;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class BuildingsScene extends ExtendedScene {
    private VertexBufferObjectManager bufferManager;
    private Scene buildingList;
    private Colony colony;
    private TiledSprite colonyBackground;
    private ColonyInfoEntity colonyInfo;
    private Text colonyName;
    private TiledSprite galaxyButton;
    private float lastY;
    private TiledSprite planetButton;
    private PlanetSprite planetIconSprite;
    private float pressedY;
    private ScrollBarControl scrollBar;
    public BuildingID selectedBuilding;
    private Sprite surface;
    private TiledSprite systemButton;
    private final ArrayList<BuildingListElement> buildingListElements = new ArrayList<>();
    private boolean isScroll = false;
    private final int ITEM_SIZE = 105;
    private final int LIST_TOP = 90;
    private final int LIST_BOTTOM = 720;

    public BuildingsScene(Game game) {
        this.B = game;
    }

    private void checkActionDown(Point point, Point point2) {
        if (point.getY() > 90.0f) {
            this.pressedY = point.getY();
            this.lastY = point.getY();
        }
        if (point.getY() > 90.0f) {
            checkPressed(point2);
        }
    }

    private void checkActionMove(Point point, Point point2) {
        if (point.getY() > 90.0f && this.colony.getBuildings().size() * 105 > 720) {
            if (this.pressedY - point.getY() > 25.0f || this.pressedY - point.getY() < -25.0f) {
                this.isScroll = true;
            }
            float y = this.buildingList.getY() - (this.lastY - point.getY());
            if (y > 90.0f) {
                y = 90.0f;
            }
            float size = ((this.colony.getBuildings().size() * 105) - 720) * (-1);
            if (y < size) {
                y = size;
            }
            this.buildingList.setY(y);
            this.lastY = point.getY();
            this.scrollBar.update(y);
        }
        if (this.isScroll || point.getY() <= 90.0f) {
            return;
        }
        checkPressed(point2);
    }

    private void checkActionUp(Point point, Point point2) {
        if (this.isScroll) {
            this.isScroll = false;
            return;
        }
        checkButtons(point);
        if (this.isScroll || point.getY() <= 90.0f) {
            return;
        }
        Iterator<BuildingListElement> it = this.buildingListElements.iterator();
        while (it.hasNext()) {
            BuildingListElement next = it.next();
            if (next.isVisible()) {
                if (next.isSellButtonClicked(point2)) {
                    this.selectedBuilding = next.getBuildingID();
                    Game game = this.B;
                    game.confirmOverlay.setOverlay(this.colony, game.buildingsScene, "sell");
                    setChildScene(this.B.confirmOverlay, false, false, true);
                    this.B.sounds.playButtonPressSound();
                    Game game2 = this.B;
                    game2.vibrate(game2.BUTTON_VIBRATE);
                    return;
                } else if (next.isSellButtonPressed(point2)) {
                    showMessage(new SellOneBuildingMessage());
                    return;
                }
            }
        }
    }

    private void checkButtons(Point point) {
        if (isClicked(this.galaxyButton, point)) {
            galaxyButtonPressed();
        } else if (isClicked(this.systemButton, point)) {
            systemButtonPressed();
        } else if (isClicked(this.planetButton, point)) {
            planetButtonPressed();
        }
    }

    private void checkPressed(Point point) {
        Iterator<BuildingListElement> it = this.buildingListElements.iterator();
        while (it.hasNext()) {
            BuildingListElement next = it.next();
            if (next.isVisible() && next.isSellButtonClicked(point)) {
                this.G.setX(next.getSellButtonX() + this.buildingList.getX());
                this.G.setY(next.getSellButtonY() + this.buildingList.getY());
                this.G.setVisible(true);
            }
        }
    }

    private void galaxyButtonPressed() {
        changeScene(this.B.galaxyScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    public /* synthetic */ void lambda$releasePoolElements$1(ExtendedScene extendedScene, Object obj) {
        detachChild(this.planetIconSprite);
        this.B.planetSpritePool.push(this.planetIconSprite);
        extendedScene.getPoolElements();
        M(extendedScene, obj);
        this.B.setCurrentScene(extendedScene);
    }

    public static /* synthetic */ int lambda$setBuildings$0(String str, String str2) {
        return Buildings.getBuilding(str).getName().compareToIgnoreCase(Buildings.getBuilding(str2).getName());
    }

    private void planetButtonPressed() {
        changeScene(this.B.planetScene);
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void resetScrollList() {
        this.buildingList.setY(90.0f);
        this.scrollBar.update(90.0f, this.colony.getBuildings().size());
    }

    private void setBackdrop(Colony colony) {
        this.surface.setVisible(true);
        Game game = this.B;
        this.surface.setTiledTextureRegion(game.graphics.setSurfaceTexture("Surfaces/" + colony.getPlanet().getClimate().getID() + ".png", game.getActivity()));
        this.surface.setX(-Functions.random.nextInt(2480 - ((int) getWidth())));
    }

    private void setBuildings() {
        int i;
        List<String> buildings = this.colony.getBuildings();
        Collections.sort(buildings, new Comparator() { // from class: com.birdshel.Uciana.Scenes.f
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int lambda$setBuildings$0;
                lambda$setBuildings$0 = BuildingsScene.lambda$setBuildings$0((String) obj, (String) obj2);
                return lambda$setBuildings$0;
            }
        });
        Iterator<BuildingListElement> it = this.buildingListElements.iterator();
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                break;
            }
            it.next().setVisible(false);
        }
        for (String str : buildings) {
            if (i >= this.buildingListElements.size()) {
                BuildingListElement buildingListElement = new BuildingListElement(this.B, this.bufferManager);
                buildingListElement.setPosition(0.0f, i * 105);
                buildingListElement.setZIndex(4);
                this.buildingList.attachChild(buildingListElement);
                this.buildingListElements.add(buildingListElement);
            }
            this.buildingListElements.get(i).set(this.colony, Buildings.getBuilding(str));
            this.buildingListElements.get(i).setVisible(true);
            i++;
        }
    }

    private void systemButtonPressed() {
        changeScene(this.B.systemScene, Integer.valueOf(this.colony.getPlanet().getSystemID()));
        this.B.sounds.playButtonPressSound();
        Game game = this.B;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void B(Point point) {
    }

    protected void M(ExtendedScene extendedScene, Object obj) {
        if (extendedScene instanceof PlanetScene) {
            this.B.planetScene.refresh();
        } else if (extendedScene instanceof GalaxyScene) {
            this.B.galaxyScene.setStarsAndNebulas();
        } else if (extendedScene instanceof SystemScene) {
            this.B.systemScene.loadSystem(((Integer) obj).intValue());
        }
    }

    @Override // org.andengine.entity.scene.Scene
    public void back() {
        if (t()) {
            return;
        }
        changeScene(this.B.planetScene);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void checkInput(int i, Point point) {
        super.checkInput(i, point);
        Point point2 = new Point(point.getX() - this.buildingList.getX(), point.getY() - this.buildingList.getY());
        if (i == 0) {
            checkActionDown(point, point2);
        } else if (i == 1) {
            checkActionUp(point, point2);
        } else if (i != 2) {
        } else {
            checkActionMove(point, point2);
        }
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void createScene(VertexBufferObjectManager vertexBufferObjectManager) {
        super.createScene(vertexBufferObjectManager);
        this.bufferManager = vertexBufferObjectManager;
        Sprite E = E(0.0f, 0.0f, this.B.graphics.planetSurfaceTexture, vertexBufferObjectManager, true);
        this.surface = E;
        E.setZIndex(3);
        this.surface.setSize(2480.0f, 720.0f);
        Scene scene = new Scene();
        this.buildingList = scene;
        scene.setPosition((getWidth() - 900.0f) / 2.0f, 90.0f);
        this.buildingList.setBackgroundEnabled(false);
        this.buildingList.setZIndex(4);
        attachChild(this.buildingList);
        Sprite E2 = E(0.0f, 0.0f, this.B.graphics.fadeBackgroundTexture, vertexBufferObjectManager, true);
        E2.setZIndex(5);
        E2.setSize(getWidth(), 86.0f);
        TiledSprite H = H(0.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.PRESSED.ordinal(), false);
        this.G = H;
        H.setZIndex(5);
        TiledSprite J = J(0.0f, 0.0f, this.B.graphics.empireColors, vertexBufferObjectManager, true);
        this.colonyBackground = J;
        J.setAlpha(0.6f);
        this.colonyBackground.setSize(getWidth(), 86.0f);
        this.colonyBackground.setZIndex(5);
        Text F = F(100.0f, 10.0f, this.B.fonts.infoFont, this.D, this.E, vertexBufferObjectManager);
        this.colonyName = F;
        F.setZIndex(5);
        ColonyInfoEntity colonyInfoEntity = new ColonyInfoEntity(this.B, vertexBufferObjectManager);
        this.colonyInfo = colonyInfoEntity;
        colonyInfoEntity.setPosition(100.0f, 45.0f);
        this.colonyInfo.setZIndex(5);
        attachChild(this.colonyInfo);
        TiledSprite H2 = H(getWidth() - 120.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.GALAXY.ordinal(), true);
        this.galaxyButton = H2;
        H2.setZIndex(5);
        s(this.galaxyButton);
        TiledSprite H3 = H(getWidth() - 240.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.SYSTEM.ordinal(), true);
        this.systemButton = H3;
        H3.setZIndex(5);
        s(this.systemButton);
        TiledSprite H4 = H(getWidth() - 360.0f, 0.0f, this.B.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.PLANET.ordinal(), true);
        this.planetButton = H4;
        H4.setZIndex(5);
        s(this.planetButton);
        ScrollBarControl scrollBarControl = new ScrollBarControl(910.0f + this.buildingList.getX(), 90.0f, 105, 630, this.B, vertexBufferObjectManager);
        this.scrollBar = scrollBarControl;
        scrollBarControl.setZIndex(5);
        attachChild(this.scrollBar);
        this.H = new MessageOverlay(this.B, vertexBufferObjectManager);
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void getPoolElements() {
        PlanetSprite planetSprite = new PlanetSprite(this.B, this.bufferManager, 100, 86);
        this.planetIconSprite = planetSprite;
        planetSprite.setZIndex(5);
        attachChild(this.planetIconSprite);
        sortChildren();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void refresh() {
        setBuildings();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    protected void releasePoolElements(final ExtendedScene extendedScene, final Object obj) {
        this.B.getEngine().runOnUpdateThread(new Runnable() { // from class: com.birdshel.Uciana.Scenes.e
            {
                BuildingsScene.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                BuildingsScene.this.lambda$releasePoolElements$1(extendedScene, obj);
            }
        });
    }

    public void sellBuilding() {
        float y = this.buildingList.getY();
        this.colony.sellBuilding(this.selectedBuilding);
        refresh();
        if (this.colony.getBuildings().size() * 105 > 634) {
            float size = ((this.colony.getBuildings().size() * 105) - 720) * (-1);
            if (y < size) {
                y = size;
            }
            this.buildingList.setY(y);
            this.scrollBar.update(y, this.colony.getBuildings().size());
        } else {
            this.buildingList.setY(90.0f);
            this.scrollBar.update(90.0f, this.colony.getBuildings().size());
        }
        this.colonyInfo.set(this.colony);
        set(this.colony);
    }

    public void set(Colony colony) {
        this.colony = colony;
        this.colonyInfo.set(colony);
        this.colonyBackground.setCurrentTileIndex(colony.getEmpireID());
        Planet planet = colony.getPlanet();
        this.planetIconSprite.setPlanet(planet, planet.getScale(this.B.buildingsScene), Moon.getScale(this.B.buildingsScene));
        this.planetIconSprite.setPosition(50.0f, 43.0f);
        this.colonyName.setText(colony.getName());
        setBackdrop(colony);
        setBuildings();
        resetScrollList();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void switched() {
        super.switched();
    }

    @Override // com.birdshel.Uciana.Scenes.ExtendedScene
    public void update() {
    }
}
