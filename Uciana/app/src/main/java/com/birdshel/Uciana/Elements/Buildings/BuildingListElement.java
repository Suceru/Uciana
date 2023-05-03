package com.birdshel.Uciana.Elements.Buildings;

import com.birdshel.Uciana.Colonies.Buildings.Building;
import com.birdshel.Uciana.Colonies.Buildings.BuildingID;
import com.birdshel.Uciana.Colonies.Colony;
import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Resources.ButtonsEnum;
import java.nio.CharBuffer;
import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class BuildingListElement extends Entity {
    private BuildingID buildingID;
    private final TiledSprite buildingIcon;
    private final Text buildingName;
    private final BuildingCostElement costInfo;
    private final BuildingInfoElement info;
    private final TiledSprite sellButton;

    public BuildingListElement(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        CharBuffer wrap = CharBuffer.wrap(new char[255]);
        TextOptions textOptions = new TextOptions(HorizontalAlign.CENTER);
        Sprite sprite = new Sprite(0.0f, 0.0f, game.graphics.colonyBackgroundTexture, vertexBufferObjectManager);
        sprite.setAlpha(0.8f);
        sprite.setSize(900.0f, 100.0f);
        attachChild(sprite);
        Sprite sprite2 = new Sprite(0.0f, 0.0f, game.graphics.fadeBackgroundTexture, vertexBufferObjectManager);
        sprite2.setAlpha(0.4f);
        sprite2.setSize(900.0f, 100.0f);
        attachChild(sprite2);
        TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, game.graphics.gameIconsTexture, vertexBufferObjectManager);
        this.buildingIcon = tiledSprite;
        attachChild(tiledSprite);
        Text text = new Text(120.0f, 30.0f, game.fonts.infoFont, wrap, textOptions, vertexBufferObjectManager);
        this.buildingName = text;
        attachChild(text);
        BuildingInfoElement buildingInfoElement = new BuildingInfoElement(game, vertexBufferObjectManager);
        this.info = buildingInfoElement;
        buildingInfoElement.setPosition(120.0f, 60.0f);
        attachChild(buildingInfoElement);
        BuildingCostElement buildingCostElement = new BuildingCostElement(game, vertexBufferObjectManager);
        this.costInfo = buildingCostElement;
        buildingCostElement.setPosition(600.0f, 35.0f);
        attachChild(buildingCostElement);
        TiledSprite tiledSprite2 = new TiledSprite(780.0f, 7.0f, game.graphics.buttonsTexture, vertexBufferObjectManager);
        this.sellButton = tiledSprite2;
        tiledSprite2.setCurrentTileIndex(ButtonsEnum.CREDITS.ordinal());
        attachChild(tiledSprite2);
    }

    public BuildingID getBuildingID() {
        return this.buildingID;
    }

    public float getSellButtonX() {
        return getX() + this.sellButton.getX();
    }

    public float getSellButtonY() {
        return getY() + this.sellButton.getY();
    }

    public boolean isSellButtonClicked(Point point) {
        return this.sellButton.isVisible() && this.sellButton.getAlpha() != 0.4f && point.getX() > getX() + this.sellButton.getX() && point.getX() < (getX() + this.sellButton.getX()) + this.sellButton.getWidthScaled() && point.getY() > getY() + this.sellButton.getY() && point.getY() < (getY() + this.sellButton.getY()) + this.sellButton.getHeightScaled();
    }

    public boolean isSellButtonPressed(Point point) {
        return this.sellButton.isVisible() && point.getX() > getX() + this.sellButton.getX() && point.getX() < (getX() + this.sellButton.getX()) + this.sellButton.getWidthScaled() && point.getY() > getY() + this.sellButton.getY() && point.getY() < (getY() + this.sellButton.getY()) + this.sellButton.getHeightScaled();
    }

    public void set(Colony colony, Building building) {
        this.buildingID = building.getID();
        this.buildingIcon.setCurrentTileIndex(building.getImageIndex());
        this.buildingName.setText(building.getName());
        Text text = this.buildingName;
        text.setY(30.0f - (text.getHeightScaled() / 2.0f));
        this.info.set(building);
        this.costInfo.set(building, colony.getPlanet().getClimateCostModifier());
        this.sellButton.setVisible(building.canBeSold());
        this.sellButton.setAlpha(1.0f);
        if (colony.hasSoldABuildingThisTurn()) {
            this.sellButton.setAlpha(0.4f);
        }
    }
}
