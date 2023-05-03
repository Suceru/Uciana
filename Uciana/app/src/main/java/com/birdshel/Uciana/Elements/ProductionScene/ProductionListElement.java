package com.birdshel.Uciana.Elements.ProductionScene;

import com.birdshel.Uciana.Colonies.Buildings.Building;
import com.birdshel.Uciana.Colonies.Buildings.BuildingID;
import com.birdshel.Uciana.Elements.Buildings.BuildingCostElement;
import com.birdshel.Uciana.Elements.Buildings.BuildingInfoElement;
import com.birdshel.Uciana.Elements.ShipDescription;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Ships.Ship;
import java.nio.CharBuffer;
import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ProductionListElement extends Entity {
    private final Sprite blackedBackground;
    private Sprite blackedBackground2;
    private final BuildingCostElement buildingCostElement;
    private final TiledSprite buildingIcon;
    private final BuildingInfoElement buildingInfoElement;
    private final TiledSprite commandPointIcon;
    private final Text commandPoints;
    private final Game game;
    private final Text name;
    private final Text productionCost;
    private final TiledSprite productionListIcon;
    private Sprite queuedOverlay;
    private Sprite queuedOverlay2;
    private Sprite selectedOverlay;
    private Sprite selectedOverlay2;
    private final ShipDescription shipDescription;
    private final TiledSprite shipIcon;
    private final TiledSprite shipTypeIcon;
    private final boolean showAddButton;
    private final TiledSprite turnIcon;
    private final Text turnText;

    public ProductionListElement(Game game, VertexBufferObjectManager vertexBufferObjectManager, boolean z) {
        this.game = game;
        this.showAddButton = z;
        CharBuffer wrap = CharBuffer.wrap(new char[255]);
        TextOptions textOptions = new TextOptions(HorizontalAlign.CENTER);
        Sprite sprite = new Sprite(0.0f, 0.0f, game.graphics.colonyBackgroundTexture, vertexBufferObjectManager);
        sprite.setAlpha(0.8f);
        sprite.setSize(540.0f, 100.0f);
        attachChild(sprite);
        Sprite sprite2 = new Sprite(0.0f, 0.0f, game.graphics.fadeBackgroundTexture, vertexBufferObjectManager);
        this.blackedBackground = sprite2;
        sprite2.setAlpha(0.4f);
        sprite2.setSize(540.0f, 100.0f);
        attachChild(sprite2);
        if (z) {
            Sprite sprite3 = new Sprite(545.0f, 0.0f, game.graphics.colonyBackgroundTexture, vertexBufferObjectManager);
            sprite3.setAlpha(0.8f);
            sprite3.setSize(80.0f, 100.0f);
            attachChild(sprite3);
            Sprite sprite4 = new Sprite(545.0f, 0.0f, game.graphics.fadeBackgroundTexture, vertexBufferObjectManager);
            this.blackedBackground2 = sprite4;
            sprite4.setAlpha(0.4f);
            this.blackedBackground2.setSize(80.0f, 100.0f);
            attachChild(this.blackedBackground2);
            Sprite sprite5 = new Sprite(0.0f, 0.0f, game.graphics.scienceBarTexture, vertexBufferObjectManager);
            this.queuedOverlay = sprite5;
            sprite5.setAlpha(0.6f);
            this.queuedOverlay.setSize(540.0f, 100.0f);
            attachChild(this.queuedOverlay);
            Sprite sprite6 = new Sprite(545.0f, 0.0f, game.graphics.scienceBarTexture, vertexBufferObjectManager);
            this.queuedOverlay2 = sprite6;
            sprite6.setAlpha(0.6f);
            this.queuedOverlay2.setSize(80.0f, 100.0f);
            attachChild(this.queuedOverlay2);
            Sprite sprite7 = new Sprite(0.0f, 0.0f, game.graphics.farmingBarTexture, vertexBufferObjectManager);
            this.selectedOverlay = sprite7;
            sprite7.setAlpha(0.6f);
            this.selectedOverlay.setSize(540.0f, 100.0f);
            attachChild(this.selectedOverlay);
            Sprite sprite8 = new Sprite(545.0f, 0.0f, game.graphics.farmingBarTexture, vertexBufferObjectManager);
            this.selectedOverlay2 = sprite8;
            sprite8.setAlpha(0.6f);
            this.selectedOverlay2.setSize(80.0f, 100.0f);
            attachChild(this.selectedOverlay2);
            TiledSprite tiledSprite = new TiledSprite(550.0f, 10.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager);
            tiledSprite.setScaleCenter(0.0f, 0.0f);
            tiledSprite.setCurrentTileIndex(GameIconEnum.ADD.ordinal());
            tiledSprite.setScale(0.75f);
            attachChild(tiledSprite);
        }
        TiledSprite tiledSprite2 = new TiledSprite(0.0f, 0.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager);
        this.buildingIcon = tiledSprite2;
        attachChild(tiledSprite2);
        TiledSprite tiledSprite3 = new TiledSprite(0.0f, 0.0f, game.graphics.shipsTextures[0], vertexBufferObjectManager);
        this.shipIcon = tiledSprite3;
        attachChild(tiledSprite3);
        Text text = new Text(110.0f, 30.0f, game.fonts.smallFont, wrap, new TextOptions(AutoWrap.WORDS, 300.0f, HorizontalAlign.LEFT), vertexBufferObjectManager);
        this.name = text;
        attachChild(text);
        Text text2 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, wrap, textOptions, vertexBufferObjectManager);
        this.turnText = text2;
        attachChild(text2);
        TiledSprite tiledSprite4 = new TiledSprite(500.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.turnIcon = tiledSprite4;
        tiledSprite4.setCurrentTileIndex(InfoIconEnum.TURN.ordinal());
        attachChild(tiledSprite4);
        TiledSprite tiledSprite5 = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.productionListIcon = tiledSprite5;
        tiledSprite5.setCurrentTileIndex(InfoIconEnum.PRODUCTION.ordinal());
        attachChild(tiledSprite5);
        Text text3 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, wrap, textOptions, vertexBufferObjectManager);
        this.productionCost = text3;
        attachChild(text3);
        TiledSprite tiledSprite6 = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.commandPointIcon = tiledSprite6;
        tiledSprite6.setCurrentTileIndex(InfoIconEnum.COMMAND_POINTS.ordinal());
        attachChild(tiledSprite6);
        Text text4 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, wrap, textOptions, vertexBufferObjectManager);
        this.commandPoints = text4;
        attachChild(text4);
        TiledSprite tiledSprite7 = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.shipTypeIcon = tiledSprite7;
        attachChild(tiledSprite7);
        ShipDescription shipDescription = new ShipDescription(game, vertexBufferObjectManager);
        this.shipDescription = shipDescription;
        shipDescription.setPosition(110.0f, 60.0f);
        attachChild(shipDescription);
        BuildingInfoElement buildingInfoElement = new BuildingInfoElement(game, vertexBufferObjectManager);
        this.buildingInfoElement = buildingInfoElement;
        buildingInfoElement.setPosition(110.0f, 60.0f);
        attachChild(buildingInfoElement);
        BuildingCostElement buildingCostElement = new BuildingCostElement(game, vertexBufferObjectManager);
        this.buildingCostElement = buildingCostElement;
        buildingCostElement.setPosition(0.0f, 60.0f);
        attachChild(buildingCostElement);
    }

    private void hideElements() {
        if (this.showAddButton) {
            this.queuedOverlay.setVisible(false);
            this.queuedOverlay2.setVisible(false);
            this.selectedOverlay.setVisible(false);
            this.selectedOverlay2.setVisible(false);
            this.blackedBackground2.setVisible(false);
        }
        this.buildingIcon.setVisible(false);
        this.shipIcon.setVisible(false);
        this.turnText.setVisible(false);
        this.turnIcon.setVisible(false);
        this.productionCost.setVisible(false);
        this.productionListIcon.setVisible(false);
        this.commandPointIcon.setVisible(false);
        this.commandPoints.setVisible(false);
        this.shipTypeIcon.setVisible(false);
        this.blackedBackground.setVisible(false);
        this.shipDescription.setVisible(false);
        this.buildingInfoElement.setVisible(false);
        this.buildingCostElement.setVisible(false);
    }

    public void set(int i, Ship ship) {
        hideElements();
        this.blackedBackground.setVisible(true);
        this.blackedBackground2.setVisible(true);
        this.shipIcon.setVisible(true);
        this.shipIcon.setTiledTextureRegion((ITiledTextureRegion) this.game.graphics.shipsTextures[ship.getEmpireID()]);
        this.shipIcon.setCurrentTileIndex(ship.getShipType().getIcon(ship.getEmpireID(), ship.getHullDesign()));
        float selectScreenSize = ship.getShipType().getSelectScreenSize();
        this.shipIcon.setSize(selectScreenSize, selectScreenSize);
        float f2 = 50.0f - (selectScreenSize / 2.0f);
        this.shipIcon.setPosition(f2, f2);
        this.name.setText(ship.getName());
        Text text = this.name;
        text.setY(30.0f - (text.getHeightScaled() / 2.0f));
        this.turnText.setVisible(true);
        this.turnText.setText(this.game.getActivity().getString(R.string.production_never));
        if (i > 0) {
            double productionCost = ship.getProductionCost();
            double d2 = i;
            Double.isNaN(productionCost);
            Double.isNaN(d2);
            int ceil = (int) Math.ceil(productionCost / d2);
            if (ceil <= 1) {
                this.turnText.setText("1");
            } else {
                this.turnText.setText(Integer.toString(ceil));
            }
        }
        Text text2 = this.turnText;
        text2.setX(495.0f - text2.getWidthScaled());
        Text text3 = this.turnText;
        text3.setY(30.0f - (text3.getHeightScaled() / 2.0f));
        this.turnIcon.setVisible(true);
        this.turnIcon.setY(this.turnText.getY() - 7.0f);
        this.productionListIcon.setVisible(true);
        this.productionListIcon.setPosition(this.turnText.getX() - 40.0f, this.turnText.getY() - 7.0f);
        this.productionCost.setVisible(true);
        this.productionCost.setText(Integer.toString(ship.getProductionCost()));
        this.productionCost.setX((this.productionListIcon.getX() - this.productionCost.getWidthScaled()) - 3.0f);
        this.productionCost.setY(this.turnText.getY());
        this.commandPointIcon.setVisible(true);
        this.commandPointIcon.setPosition(this.productionCost.getX() - 40.0f, this.productionCost.getY() - 7.0f);
        this.commandPoints.setVisible(true);
        this.commandPoints.setText(Integer.toString(ship.getShipType().getCommandPointCost()));
        this.commandPoints.setX((this.commandPointIcon.getX() - this.commandPoints.getWidthScaled()) - 4.0f);
        this.commandPoints.setY(this.turnText.getY());
        this.shipTypeIcon.setVisible(true);
        this.shipTypeIcon.setCurrentTileIndex(InfoIconEnum.getShipIcon(ship.getShipType()));
        this.shipDescription.setVisible(true);
        this.shipDescription.set(ship);
    }

    public void setRefitShip() {
        hideElements();
        this.buildingIcon.setVisible(true);
        this.buildingIcon.setCurrentTileIndex(GameIconEnum.SHIPS.ordinal());
        this.name.setText(this.game.getActivity().getString(R.string.production_refit_ship));
    }

    public void showQueuedOverlay(boolean z) {
        this.queuedOverlay.setVisible(z);
        this.queuedOverlay2.setVisible(z);
    }

    public void showSelectedOverlay(boolean z) {
        this.selectedOverlay.setVisible(z);
        this.selectedOverlay2.setVisible(z);
    }

    public void set(boolean z, int i, float f2, Building building) {
        hideElements();
        this.blackedBackground.setVisible(true);
        if (this.showAddButton) {
            this.blackedBackground2.setVisible(true);
        }
        this.buildingIcon.setVisible(true);
        this.buildingIcon.setCurrentTileIndex(building.getImageIndex());
        this.name.setText(building.getName());
        Text text = this.name;
        text.setY(30.0f - (text.getHeightScaled() / 2.0f));
        if (building.getID() != BuildingID.TRADEGOODS) {
            if (z) {
                this.turnText.setVisible(true);
                this.turnText.setText("~");
                if (i > 0) {
                    double productionCost = building.getProductionCost();
                    double d2 = i;
                    Double.isNaN(productionCost);
                    Double.isNaN(d2);
                    int ceil = (int) Math.ceil(productionCost / d2);
                    if (ceil <= 1) {
                        this.turnText.setText("1");
                    } else {
                        this.turnText.setText(Integer.toString(ceil));
                    }
                }
                Text text2 = this.turnText;
                text2.setX(495.0f - text2.getWidthScaled());
                Text text3 = this.turnText;
                text3.setY(30.0f - (text3.getHeightScaled() / 2.0f));
                this.turnIcon.setVisible(true);
                this.turnIcon.setY(this.turnText.getY() - 7.0f);
            } else {
                this.turnText.setVisible(false);
                this.turnIcon.setVisible(false);
            }
            this.productionCost.setVisible(true);
            this.productionCost.setText(Integer.toString(building.getProductionCost()));
            float f3 = 540.0f;
            float heightScaled = 30.0f - (this.productionCost.getHeightScaled() / 2.0f);
            if (z) {
                f3 = this.turnText.getX();
                heightScaled = this.turnText.getY();
            }
            this.productionListIcon.setPosition(f3 - 40.0f, heightScaled - 7.0f);
            this.productionListIcon.setVisible(true);
            this.productionCost.setX((this.productionListIcon.getX() - this.productionCost.getWidthScaled()) - 3.0f);
            this.productionCost.setY(heightScaled);
            this.buildingCostElement.setVisible(true);
            this.buildingCostElement.set(building, f2);
            BuildingCostElement buildingCostElement = this.buildingCostElement;
            buildingCostElement.setX(530.0f - buildingCostElement.getCostWidth());
        }
        this.buildingInfoElement.setVisible(true);
        this.buildingInfoElement.set(building);
    }
}
