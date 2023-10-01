package com.birdshel.Uciana.Elements.ProductionScene;

import com.birdshel.Uciana.Colonies.Buildings.Building;
import com.birdshel.Uciana.Colonies.Buildings.BuildingID;
import com.birdshel.Uciana.Colonies.Buildings.Buildings;
import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Colonies.Manufacturing;
import com.birdshel.Uciana.Colonies.ManufacturingType;
import com.birdshel.Uciana.Colonies.ProductionItem;
import com.birdshel.Uciana.Elements.Buildings.BuildingCostElement;
import com.birdshel.Uciana.Elements.Buildings.BuildingInfoElement;
import com.birdshel.Uciana.Elements.ShipDescription;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Ships.Ship;
import com.birdshel.Uciana.Ships.ShipType;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

import java.nio.CharBuffer;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class QueueItem extends Entity {
    private final Sprite background;
    private final BuildingCostElement buildingCostElement;
    private final BuildingInfoElement buildingInfoElement;
    private Colony colony;
    private final Game game;
    private int index;
    private final Text name;
    private final Sprite pressedQueue;
    private final Text productionCost;
    private final TiledSprite productionIcon;
    private final TiledSprite queueItemIcon;
    private final TiledSprite queueShipTypeIcon;
    private final Sprite removeButton;
    private final Sprite repeatButton;
    private final TiledSprite repeatIcon;
    private final Sprite repeatOn;
    private final Sprite selectPress;
    private final ShipDescription shipDescription;

    /* JADX INFO: Access modifiers changed from: package-private */
    public QueueItem(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        this.game = game;
        CharBuffer wrap = CharBuffer.wrap(new char[255]);
        TextOptions textOptions = new TextOptions(HorizontalAlign.CENTER);
        Sprite sprite = new Sprite(0.0f, 0.0f, game.graphics.scienceBarTexture, vertexBufferObjectManager);
        this.background = sprite;
        sprite.setSize(635.0f, 100.0f);
        sprite.setAlpha(0.8f);
        attachChild(sprite);
        Sprite sprite2 = new Sprite(0.0f, 0.0f, game.graphics.selectColonyTexture, vertexBufferObjectManager);
        this.pressedQueue = sprite2;
        sprite2.setSize(635.0f, 100.0f);
        attachChild(sprite2);
        TiledSprite tiledSprite = new TiledSprite(0.0f, 25.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager);
        tiledSprite.setSize(25.0f, 50.0f);
        tiledSprite.setAlpha(0.5f);
        attachChild(tiledSprite);
        TiledSprite tiledSprite2 = new TiledSprite(25.0f, 0.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager);
        this.queueItemIcon = tiledSprite2;
        attachChild(tiledSprite2);
        TiledSprite tiledSprite3 = new TiledSprite(25.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.queueShipTypeIcon = tiledSprite3;
        attachChild(tiledSprite3);
        Text text = new Text(130.0f, 15.0f, game.fonts.smallFont, wrap, textOptions, vertexBufferObjectManager);
        this.name = text;
        attachChild(text);
        Text text2 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, wrap, textOptions, vertexBufferObjectManager);
        this.productionCost = text2;
        attachChild(text2);
        TiledSprite tiledSprite4 = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.productionIcon = tiledSprite4;
        tiledSprite4.setCurrentTileIndex(InfoIconEnum.PRODUCTION.ordinal());
        attachChild(tiledSprite4);
        Sprite sprite3 = new Sprite(445.0f, 0.0f, game.graphics.selectColonyTexture, vertexBufferObjectManager);
        this.selectPress = sprite3;
        sprite3.setSize(90.0f, 60.0f);
        sprite3.setVisible(false);
        attachChild(sprite3);
        Sprite sprite4 = new Sprite(445.0f, 0.0f, game.graphics.farmingBarTexture, vertexBufferObjectManager);
        this.repeatOn = sprite4;
        sprite4.setSize(90.0f, 60.0f);
        sprite4.setVisible(false);
        attachChild(sprite4);
        Sprite sprite5 = new Sprite(445.0f, 0.0f, game.graphics.selectColonyTexture, vertexBufferObjectManager);
        this.repeatButton = sprite5;
        sprite5.setSize(90.0f, 60.0f);
        sprite5.setAlpha(0.8f);
        attachChild(sprite5);
        TiledSprite tiledSprite5 = new TiledSprite(470.0f, 10.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.repeatIcon = tiledSprite5;
        tiledSprite5.setCurrentTileIndex(InfoIconEnum.REPEAT.ordinal());
        tiledSprite5.setScaleCenter(0.0f, 0.0f);
        tiledSprite5.setSize(40.0f, 40.0f);
        attachChild(tiledSprite5);
        Sprite sprite6 = new Sprite(545.0f, 0.0f, game.graphics.selectColonyTexture, vertexBufferObjectManager);
        this.removeButton = sprite6;
        sprite6.setSize(90.0f, 60.0f);
        sprite6.setAlpha(0.8f);
        attachChild(sprite6);
        TiledSprite tiledSprite6 = new TiledSprite(565.0f, 5.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager);
        tiledSprite6.setCurrentTileIndex(GameIconEnum.CLOSE.ordinal());
        tiledSprite6.setScaleCenter(0.0f, 0.0f);
        tiledSprite6.setScale(0.5f);
        attachChild(tiledSprite6);
        BuildingInfoElement buildingInfoElement = new BuildingInfoElement(game, vertexBufferObjectManager);
        this.buildingInfoElement = buildingInfoElement;
        buildingInfoElement.setPosition(130.0f, 60.0f);
        attachChild(buildingInfoElement);
        BuildingCostElement buildingCostElement = new BuildingCostElement(game, vertexBufferObjectManager);
        this.buildingCostElement = buildingCostElement;
        attachChild(buildingCostElement);
        ShipDescription shipDescription = new ShipDescription(game, vertexBufferObjectManager);
        this.shipDescription = shipDescription;
        shipDescription.setPosition(130.0f, 60.0f);
        attachChild(shipDescription);
    }

    private void checkActionDown(Point point) {
        checkPress(point);
    }

    private void checkActionMove(Point point) {
        this.selectPress.setVisible(false);
        checkPress(point);
    }

    private boolean checkActionUp(Point point) {
        this.selectPress.setVisible(false);
        if (isClicked(this.removeButton, point)) {
            removeButtonPressed();
            return true;
        } else if (isClicked(this.repeatButton, point)) {
            repeatButtonPressed();
            return true;
        } else {
            return false;
        }
    }

    private void checkPress(Point point) {
        if (isClicked(this.repeatButton, point)) {
            this.selectPress.setVisible(true);
            this.selectPress.setX(this.repeatButton.getX());
        }
        if (isClicked(this.removeButton, point)) {
            this.selectPress.setVisible(true);
            this.selectPress.setX(this.removeButton.getX());
        }
    }

    private boolean isClicked(Entity entity, Point point) {
        Sprite sprite = (Sprite) entity;
        float x = sprite.getX();
        float widthScaled = sprite.getWidthScaled() + x;
        float y = sprite.getY();
        return sprite.isVisible() && ((point.getX() > x ? 1 : (point.getX() == x ? 0 : -1)) > 0 && (point.getX() > widthScaled ? 1 : (point.getX() == widthScaled ? 0 : -1)) < 0 && (point.getY() > y ? 1 : (point.getY() == y ? 0 : -1)) > 0 && (point.getY() > (sprite.getHeightScaled() + y) ? 1 : (point.getY() == (sprite.getHeightScaled() + y) ? 0 : -1)) < 0);
    }

    private void queueItemPressed() {
        ProductionItem copyOfCurrentItem = this.colony.getManufacturing().getCopyOfCurrentItem();
        this.colony.getManufacturing().setCurrentItem(this.colony.getQueue().get(this.index));
        this.colony.getQueue().remove(this.index);
        this.game.productionScene.updateProduction();
        this.colony.getQueue().addFirst(copyOfCurrentItem);
        this.game.productionScene.refreshQueue();
        this.game.productionScene.setQueuedOverlays();
        this.game.productionScene.setSelectedOverlay();
        this.game.sounds.playBoxPressSound();
        Game game = this.game;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    private void removeButtonPressed() {
        Manufacturing manufacturing = this.colony.getManufacturing();
        this.game.sounds.playBoxPressSound();
        Game game = this.game;
        game.vibrate(game.BUTTON_VIBRATE);
        if (manufacturing.getQueue().get(this.index).getID().startsWith("refit-")) {
            this.game.productionScene.showConfirmForRefitRemoval(manufacturing.getQueue().get(this.index).getName() + " (" + manufacturing.getQueue().get(this.index).getID().replace("refit-", "") + ")", this.index);
            return;
        }
        manufacturing.removeFromQueue(this.index);
        this.game.productionScene.refreshQueue();
        this.game.productionScene.setQueuedOverlays();
    }

    private void repeatButtonPressed() {
        boolean z = !this.repeatOn.isVisible();
        this.colony.getQueue().get(this.index).setRepeatOn(z);
        this.repeatOn.setVisible(z);
        this.game.productionScene.refreshQueue();
        this.game.sounds.playBoxPressSound();
        Game game = this.game;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    public void checkInput(int i, Point point) {
        Point point2 = new Point(point.getX() - getX(), point.getY() - getY());
        if (i == 0) {
            checkActionDown(point2);
        } else if (i != 1) {
            if (i != 2) {
                return;
            }
            checkActionMove(point2);
        } else if (!checkActionUp(point2) && isPressed(point) && point.getX() < 550.0f) {
            queueItemPressed();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isPressed(Point point) {
        return isVisible() && point.getY() > getY() && point.getY() < getY() + 100.0f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void l(boolean z) {
        this.pressedQueue.setVisible(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void m(ProductionItem productionItem, int i, Colony colony, boolean z) {
        int i2;
        this.colony = colony;
        this.index = i;
        if (z) {
            this.background.setAlpha(0.4f);
            this.repeatOn.setAlpha(0.5f);
            this.repeatButton.setAlpha(0.5f);
            this.repeatIcon.setAlpha(0.5f);
            this.queueItemIcon.setAlpha(0.5f);
            this.queueShipTypeIcon.setAlpha(0.5f);
        } else {
            this.background.setAlpha(0.8f);
            this.repeatOn.setAlpha(1.0f);
            this.repeatButton.setAlpha(0.8f);
            this.repeatIcon.setAlpha(1.0f);
            this.queueItemIcon.setAlpha(1.0f);
            this.queueShipTypeIcon.setAlpha(1.0f);
        }
        this.pressedQueue.setVisible(false);
        this.buildingInfoElement.setVisible(false);
        this.buildingCostElement.setVisible(false);
        this.shipDescription.setVisible(false);
        this.repeatOn.setVisible(productionItem.isRepeatOn());
        this.queueItemIcon.setPosition(25.0f, 0.0f);
        this.queueItemIcon.setSize(100.0f, 100.0f);
        ManufacturingType type = productionItem.getType();
        ManufacturingType manufacturingType = ManufacturingType.SHIP;
        if (type == manufacturingType) {
            Ship ship = colony.getManufacturing().getQueuedShipDesigns().get(productionItem.getID());
            ShipType shipType = ship.getShipType();
            int icon = shipType.getIcon(colony.getEmpireID(), ship.getHullDesign());
            this.queueItemIcon.setTiledTextureRegion((ITiledTextureRegion) this.game.graphics.shipsTextures[colony.getEmpireID()]);
            this.queueItemIcon.setCurrentTileIndex(icon);
            float selectScreenSize = shipType.getSelectScreenSize();
            this.queueItemIcon.setSize(selectScreenSize, selectScreenSize);
            TiledSprite tiledSprite = this.queueItemIcon;
            float f2 = 50.0f - (selectScreenSize / 2.0f);
            tiledSprite.setX(tiledSprite.getX() + f2);
            TiledSprite tiledSprite2 = this.queueItemIcon;
            tiledSprite2.setY(tiledSprite2.getY() + f2);
            this.queueShipTypeIcon.setVisible(true);
            this.queueShipTypeIcon.setCurrentTileIndex(InfoIconEnum.getShipIcon(shipType));
        } else {
            this.queueItemIcon.setTiledTextureRegion((ITiledTextureRegion) this.game.graphics.gameIconsTexture);
            int ordinal = GameIconEnum.NONE.ordinal();
            if (productionItem.getType() == ManufacturingType.BUILDINGS) {
                ordinal = Buildings.getBuilding(productionItem.getID()).getImageIndex();
            }
            this.queueItemIcon.setCurrentTileIndex(ordinal);
            this.queueShipTypeIcon.setVisible(false);
        }
        this.name.setText(productionItem.getName());
        if (productionItem.getType() == manufacturingType) {
            i2 = 100;
            this.shipDescription.setVisible(true);
            this.shipDescription.set(colony.getManufacturing().getQueuedShipDesigns().get(productionItem.getID()));
            if (productionItem.getID().startsWith("refit-")) {
                this.repeatButton.setVisible(false);
                this.repeatIcon.setVisible(false);
            } else {
                this.repeatButton.setVisible(true);
                this.repeatIcon.setVisible(true);
            }
        } else {
            this.buildingInfoElement.setVisible(true);
            Building building = Buildings.getBuilding(productionItem.getID());
            this.buildingInfoElement.set(building);
            this.buildingCostElement.setVisible(true);
            this.buildingCostElement.set(building, colony.getPlanet().getClimateCostModifier());
            BuildingCostElement buildingCostElement = this.buildingCostElement;
            buildingCostElement.setPosition(550.0f - buildingCostElement.getCostWidth(), 60.0f);
            this.repeatButton.setVisible(false);
            this.repeatIcon.setVisible(false);
            i2 = 0;
        }
        this.productionCost.setText(Integer.toString(productionItem.getRequiredProduction()));
        Text text = this.productionCost;
        text.setPosition((510 - i2) - text.getWidthScaled(), this.name.getY());
        this.productionCost.setVisible(true);
        this.productionIcon.setVisible(true);
        if (productionItem.getType() == ManufacturingType.BUILDINGS && Buildings.getBuilding(productionItem.getID()).getID() == BuildingID.TRADEGOODS) {
            this.productionCost.setVisible(false);
            this.productionIcon.setVisible(false);
        }
        this.productionIcon.setPosition(513 - i2, this.productionCost.getY() - 7.0f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void n(int i) {
        this.index = i;
    }
}
