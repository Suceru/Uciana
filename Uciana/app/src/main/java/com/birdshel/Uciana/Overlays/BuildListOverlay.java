package com.birdshel.Uciana.Overlays;

import com.birdshel.Uciana.Colonies.Buildings.BuildingID;
import com.birdshel.Uciana.Colonies.Buildings.Buildings;
import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Colonies.Manufacturing;
import com.birdshel.Uciana.Elements.ProductionScene.BuildListElement;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Planets.Gravity;
import com.birdshel.Uciana.Players.BuildLists;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.ArrayList;
import java.util.Map;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class BuildListOverlay extends ExtendedChildScene {
    private final TiledSprite closeButton;
    private Colony colony;
    private final BuildListElement list1Button;
    private final TiledSprite list1EditButton;
    private final BuildListElement list2Button;
    private final TiledSprite list2EditButton;
    private final BuildListElement list3Button;
    private final TiledSprite list3EditButton;
    private final Sprite pressSprite;

    public BuildListOverlay(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        super(game, vertexBufferObjectManager, false);
        Sprite t = t(0.0f, 110.0f, game.graphics.whiteTexture, vertexBufferObjectManager, true);
        t.setSize(getWidth(), 500.0f);
        t.setAlpha(0.9f);
        u((getWidth() / 2.0f) - 620.0f, 125.0f, game.fonts.infoFont, game.getActivity().getString(R.string.build_list_overlay_header), this.F, vertexBufferObjectManager);
        this.I = w(0.0f, 0.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.PRESSED.ordinal(), false);
        Sprite t2 = t(0.0f, 165.0f, game.graphics.selectColonyTexture, vertexBufferObjectManager, false);
        this.pressSprite = t2;
        t2.setSize(400.0f, 350.0f);
        BuildListElement buildListElement = new BuildListElement(game, vertexBufferObjectManager, (getWidth() / 2.0f) - 620.0f, 165.0f);
        this.list1Button = buildListElement;
        attachChild(buildListElement);
        BuildListElement buildListElement2 = new BuildListElement(game, vertexBufferObjectManager, (getWidth() / 2.0f) - 200.0f, 165.0f);
        this.list2Button = buildListElement2;
        attachChild(buildListElement2);
        BuildListElement buildListElement3 = new BuildListElement(game, vertexBufferObjectManager, (getWidth() / 2.0f) + 220.0f, 165.0f);
        this.list3Button = buildListElement3;
        attachChild(buildListElement3);
        float x = buildListElement.getX() + 140.0f;
        ITiledTextureRegion iTiledTextureRegion = game.graphics.buttonsTexture;
        ButtonsEnum buttonsEnum = ButtonsEnum.BLANK;
        TiledSprite w = w(x, 520.0f, iTiledTextureRegion, vertexBufferObjectManager, buttonsEnum.ordinal(), true);
        this.list1EditButton = w;
        TiledSprite tiledSprite = new TiledSprite(40.0f, 23.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        InfoIconEnum infoIconEnum = InfoIconEnum.RENAME;
        tiledSprite.setCurrentTileIndex(infoIconEnum.ordinal());
        tiledSprite.setSize(40.0f, 40.0f);
        w.attachChild(tiledSprite);
        n(w);
        TiledSprite w2 = w(buildListElement2.getX() + 140.0f, 520.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, buttonsEnum.ordinal(), true);
        this.list2EditButton = w2;
        TiledSprite tiledSprite2 = new TiledSprite(40.0f, 23.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        tiledSprite2.setCurrentTileIndex(infoIconEnum.ordinal());
        tiledSprite2.setSize(40.0f, 40.0f);
        w2.attachChild(tiledSprite2);
        n(w2);
        TiledSprite w3 = w(buildListElement3.getX() + 140.0f, 520.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, buttonsEnum.ordinal(), true);
        this.list3EditButton = w3;
        TiledSprite tiledSprite3 = new TiledSprite(40.0f, 23.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        tiledSprite3.setCurrentTileIndex(infoIconEnum.ordinal());
        tiledSprite3.setSize(40.0f, 40.0f);
        w3.attachChild(tiledSprite3);
        n(w3);
        TiledSprite w4 = w(getWidth() - 120.0f, 24.0f, game.graphics.buttonsTexture, vertexBufferObjectManager, ButtonsEnum.CLOSE.ordinal(), true);
        this.closeButton = w4;
        n(w4);
    }

    private void checkPressed(Point point) {
        if (isClicked(this.list1Button, point)) {
            this.pressSprite.setVisible(true);
            this.pressSprite.setX(this.list1Button.getX());
        }
        if (isClicked(this.list2Button, point)) {
            this.pressSprite.setVisible(true);
            this.pressSprite.setX(this.list2Button.getX());
        }
        if (isClicked(this.list3Button, point)) {
            this.pressSprite.setVisible(true);
            this.pressSprite.setX(this.list3Button.getX());
        }
    }

    private void closeButtonPressed() {
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        back();
    }

    private void editButtonPressed(int i) {
        this.C.sounds.playButtonPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        Game game2 = this.C;
        game2.productionScene.changeScene(game2.buildListScene, Integer.valueOf(i));
    }

    private boolean isClicked(BuildListElement buildListElement, Point point) {
        return point.getX() > buildListElement.getX() && point.getX() < buildListElement.getX() + buildListElement.getWidth() && point.getY() > buildListElement.getY() && point.getY() < buildListElement.getY() + buildListElement.getHeight();
    }

    private void listButtonPressed(int i) {
        this.C.sounds.playBoxPressSound();
        Game game = this.C;
        game.vibrate(game.BUTTON_VIBRATE);
        BuildLists buildLists = this.C.getCurrentEmpire().getBuildLists();
        if (buildLists.containsList(i) && !buildLists.getItems(i).isEmpty()) {
            Manufacturing manufacturing = this.colony.getManufacturing();
            for (Map.Entry<Integer, BuildingID> entry : buildLists.getItems(i).entrySet()) {
                if (manufacturing.isQueueFull()) {
                    break;
                } else if (canBuildBuilding(entry.getValue())) {
                    manufacturing.addBuildingToQueue(Buildings.getBuilding(entry.getValue()));
                }
            }
            this.C.productionScene.refresh();
            back();
            return;
        }
        Game game2 = this.C;
        game2.productionScene.changeScene(game2.buildListScene, Integer.valueOf(i));
    }

    public boolean canBuildBuilding(BuildingID buildingID) {
        if (this.colony.hasBuilding(buildingID)) {
            return false;
        }
        if (buildingID == BuildingID.TERRAFORMING && this.colony.getPlanet().isTerraformed()) {
            return false;
        }
        if (buildingID != BuildingID.MOON_BASE || this.colony.getPlanet().hasMoon()) {
            if (buildingID == BuildingID.SOIL_ENRICHMENT && this.colony.getPlanet().getClimate().getFoodPerFarmer() == 0.0f) {
                return false;
            }
            if (buildingID != BuildingID.GRAVITY_DAMPER || this.colony.getPlanet().getGravity() == Gravity.HIGH) {
                return buildingID != BuildingID.GRAVITY_GENERATOR || this.colony.getPlanet().getGravity() == Gravity.LOW;
            }
            return false;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    public void checkInput(int i, Point point) {
        super.checkInput(i, point);
        if (i == 0) {
            checkPressed(point);
        } else if (i != 1) {
            if (i != 2) {
                return;
            }
            this.pressSprite.setVisible(false);
            checkPressed(point);
        } else {
            this.pressSprite.setVisible(false);
            if (q(this.closeButton, point)) {
                closeButtonPressed();
            }
            if (isClicked(this.list1Button, point)) {
                listButtonPressed(0);
            }
            if (isClicked(this.list2Button, point)) {
                listButtonPressed(1);
            }
            if (isClicked(this.list3Button, point)) {
                listButtonPressed(2);
            }
            if (q(this.list1EditButton, point)) {
                editButtonPressed(0);
            }
            if (q(this.list2EditButton, point)) {
                editButtonPressed(1);
            }
            if (q(this.list3EditButton, point)) {
                editButtonPressed(2);
            }
        }
    }

    public void setOverlay(Colony colony, BuildLists buildLists) {
        this.colony = colony;
        ArrayList arrayList = new ArrayList();
        if (buildLists.containsList(0)) {
            for (int i = 0; i < 5; i++) {
                if (buildLists.getItems(0).containsKey(Integer.valueOf(i))) {
                    arrayList.add(buildLists.getItems(0).get(Integer.valueOf(i)));
                }
            }
        }
        this.list1Button.set(arrayList, this);
        this.list1EditButton.setVisible(!arrayList.isEmpty());
        ArrayList arrayList2 = new ArrayList();
        if (buildLists.containsList(1)) {
            for (int i2 = 0; i2 < 5; i2++) {
                if (buildLists.getItems(1).containsKey(Integer.valueOf(i2))) {
                    arrayList2.add(buildLists.getItems(1).get(Integer.valueOf(i2)));
                }
            }
        }
        this.list2Button.set(arrayList2, this);
        this.list2EditButton.setVisible(!arrayList2.isEmpty());
        ArrayList arrayList3 = new ArrayList();
        if (buildLists.containsList(2)) {
            for (int i3 = 0; i3 < 5; i3++) {
                if (buildLists.getItems(2).containsKey(Integer.valueOf(i3))) {
                    arrayList3.add(buildLists.getItems(2).get(Integer.valueOf(i3)));
                }
            }
        }
        this.list3Button.set(arrayList3, this);
        this.list3EditButton.setVisible(!arrayList3.isEmpty());
    }

    @Override // com.birdshel.Uciana.Overlays.ExtendedChildScene
    public void update() {
    }
}
