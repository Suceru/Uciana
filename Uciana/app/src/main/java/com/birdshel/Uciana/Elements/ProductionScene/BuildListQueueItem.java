package com.birdshel.Uciana.Elements.ProductionScene;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.InputDeviceCompat;
import com.birdshel.Uciana.Colonies.Buildings.Building;
import com.birdshel.Uciana.Colonies.Buildings.BuildingID;
import com.birdshel.Uciana.Colonies.Buildings.Buildings;
import com.birdshel.Uciana.Colonies.ManufacturingType;
import com.birdshel.Uciana.Colonies.ProductionItem;
import com.birdshel.Uciana.Elements.Buildings.BuildingCostElement;
import com.birdshel.Uciana.Elements.Buildings.BuildingInfoElement;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Resources.GameIconEnum;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import java.nio.CharBuffer;
import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class BuildListQueueItem extends Entity {
    private final BuildListContainer buildListContainer;
    private final BuildingCostElement buildingCostElement;
    private final BuildingInfoElement buildingInfoElement;
    private final Game game;
    private int index;
    private final Text name;
    private final Sprite pressedQueue;
    private final Text productionCost;
    private final TiledSprite productionIcon;
    private final TiledSprite queueItemIcon;
    private final Sprite removeButton;
    private final Sprite selectPress;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BuildListQueueItem(Game game, VertexBufferObjectManager vertexBufferObjectManager, BuildListContainer buildListContainer) {
        this.game = game;
        this.buildListContainer = buildListContainer;
        CharBuffer wrap = CharBuffer.wrap(new char[255]);
        TextOptions textOptions = new TextOptions(HorizontalAlign.CENTER);
        Sprite sprite = new Sprite(0.0f, 0.0f, game.graphics.scienceBarTexture, vertexBufferObjectManager);
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
        Text text = new Text(130.0f, 15.0f, game.fonts.smallFont, wrap, textOptions, vertexBufferObjectManager);
        this.name = text;
        attachChild(text);
        Text text2 = new Text(0.0f, 0.0f, game.fonts.smallInfoFont, wrap, textOptions, vertexBufferObjectManager);
        this.productionCost = text2;
        attachChild(text2);
        TiledSprite tiledSprite3 = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.productionIcon = tiledSprite3;
        tiledSprite3.setCurrentTileIndex(InfoIconEnum.PRODUCTION.ordinal());
        attachChild(tiledSprite3);
        Sprite sprite3 = new Sprite(445.0f, 0.0f, game.graphics.selectColonyTexture, vertexBufferObjectManager);
        this.selectPress = sprite3;
        sprite3.setSize(90.0f, 60.0f);
        sprite3.setVisible(false);
        attachChild(sprite3);
        Sprite sprite4 = new Sprite(545.0f, 0.0f, game.graphics.selectColonyTexture, vertexBufferObjectManager);
        this.removeButton = sprite4;
        sprite4.setSize(90.0f, 60.0f);
        sprite4.setAlpha(0.8f);
        attachChild(sprite4);
        TiledSprite tiledSprite4 = new TiledSprite(565.0f, 5.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager);
        tiledSprite4.setCurrentTileIndex(GameIconEnum.CLOSE.ordinal());
        tiledSprite4.setScaleCenter(0.0f, 0.0f);
        tiledSprite4.setScale(0.5f);
        attachChild(tiledSprite4);
        BuildingInfoElement buildingInfoElement = new BuildingInfoElement(game, vertexBufferObjectManager);
        this.buildingInfoElement = buildingInfoElement;
        buildingInfoElement.setPosition(130.0f, 60.0f);
        attachChild(buildingInfoElement);
        BuildingCostElement buildingCostElement = new BuildingCostElement(game, vertexBufferObjectManager);
        this.buildingCostElement = buildingCostElement;
        attachChild(buildingCostElement);
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
        }
        return false;
    }

    private void checkPress(Point point) {
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

    private void removeButtonPressed() {
        this.buildListContainer.remove(this.index);
        this.game.sounds.playBoxPressSound();
        Game game = this.game;
        game.vibrate(game.BUTTON_VIBRATE);
    }

    public void checkInput(int i, Point point) {
        Point point2 = new Point(point.getX() - getX(), point.getY() - getY());
        if (i == 0) {
            checkActionDown(point2);
        } else if (i == 1) {
            checkActionUp(point2);
        } else if (i != 2) {
        } else {
            checkActionMove(point2);
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
    public void m(ProductionItem productionItem, int i) {
        this.index = i;
        this.pressedQueue.setVisible(false);
        this.buildingInfoElement.setVisible(false);
        this.buildingCostElement.setVisible(false);
        this.queueItemIcon.setPosition(25.0f, 0.0f);
        this.queueItemIcon.setSize(100.0f, 100.0f);
        this.queueItemIcon.setTiledTextureRegion((ITiledTextureRegion) this.game.graphics.gameIconsTexture);
        int ordinal = GameIconEnum.NONE.ordinal();
        ManufacturingType type = productionItem.getType();
        ManufacturingType manufacturingType = ManufacturingType.BUILDINGS;
        if (type == manufacturingType) {
            ordinal = Buildings.getBuilding(productionItem.getID()).getImageIndex();
        }
        this.queueItemIcon.setCurrentTileIndex(ordinal);
        this.name.setText(productionItem.getName());
        this.buildingInfoElement.setVisible(true);
        Building building = Buildings.getBuilding(productionItem.getID());
        this.buildingInfoElement.set(building);
        this.buildingCostElement.setVisible(true);
        this.buildingCostElement.set(building, 1.0f);
        BuildingCostElement buildingCostElement = this.buildingCostElement;
        buildingCostElement.setPosition(550.0f - buildingCostElement.getCostWidth(), 60.0f);
        this.productionCost.setText(Integer.toString(productionItem.getRequiredProduction()));
        Text text = this.productionCost;
        text.setPosition(((float) TypedValues.Position.TYPE_POSITION_TYPE) - text.getWidthScaled(), this.name.getY());
        this.productionCost.setVisible(true);
        this.productionIcon.setVisible(true);
        if (productionItem.getType() == manufacturingType && Buildings.getBuilding(productionItem.getID()).getID() == BuildingID.TRADEGOODS) {
            this.productionCost.setVisible(false);
            this.productionIcon.setVisible(false);
        }
        this.productionIcon.setPosition((float) InputDeviceCompat.SOURCE_DPAD, this.productionCost.getY() - 7.0f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void n(int i) {
        this.index = i;
    }
}
