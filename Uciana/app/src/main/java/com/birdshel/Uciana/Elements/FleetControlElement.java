package com.birdshel.Uciana.Elements;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.Ships.ShipSprite;
import com.birdshel.Uciana.Ships.ShipType;
import org.andengine.entity.Entity;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class FleetControlElement extends Entity {
    private final Text autoCountText;
    private final TiledSprite autoIcon;
    private final Text shipCount;
    private final ShipSprite shipIcon;
    private final TiledSprite shipTypeIcon;

    public FleetControlElement(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        Text text = new Text(0.0f, 25.0f, game.fonts.smallInfoFont, "##########", vertexBufferObjectManager);
        this.autoCountText = text;
        attachChild(text);
        TiledSprite tiledSprite = new TiledSprite(text.getX() + text.getWidthScaled() + 5.0f, 25.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.autoIcon = tiledSprite;
        tiledSprite.setSize(20.0f, 20.0f);
        tiledSprite.setCurrentTileIndex(InfoIconEnum.ON.ordinal());
        attachChild(tiledSprite);
        Text text2 = new Text(0.0f, 125.0f, game.fonts.smallInfoFont, "##########", new TextOptions(HorizontalAlign.CENTER), vertexBufferObjectManager);
        this.shipCount = text2;
        attachChild(text2);
        TiledSprite tiledSprite2 = new TiledSprite(0.0f, 120.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.shipTypeIcon = tiledSprite2;
        attachChild(tiledSprite2);
        ShipSprite shipSprite = new ShipSprite(game, vertexBufferObjectManager);
        this.shipIcon = shipSprite;
        shipSprite.setPosition(0.0f, 20.0f);
        attachChild(shipSprite);
    }

    public void set(Fleet fleet, ShipType shipType, int i) {
        this.autoCountText.setVisible(false);
        this.autoIcon.setVisible(false);
        int autoCount = fleet.getAutoCount(shipType);
        if (autoCount > 0) {
            this.autoCountText.setVisible(true);
            this.autoCountText.setText(Integer.toString(autoCount));
            Text text = this.autoCountText;
            text.setX(50.0f - ((text.getWidthScaled() + 25.0f) / 2.0f));
            this.autoIcon.setVisible(true);
            this.autoIcon.setX(this.autoCountText.getX() + this.autoCountText.getWidthScaled() + 5.0f);
        }
        this.shipIcon.setShipIcon(fleet.empireID, shipType, fleet.getHullDesignForShipType(shipType), 100.0f, shipType.getSelectScreenSize(), fleet.getDirection(), fleet.isMoving());
        this.shipCount.setText(Integer.toString(i));
        Text text2 = this.shipCount;
        text2.setX(50.0f - ((text2.getWidthScaled() + 35.0f) / 2.0f));
        this.shipTypeIcon.setX(this.shipCount.getX() + this.shipCount.getWidthScaled() + 5.0f);
        this.shipTypeIcon.setCurrentTileIndex(InfoIconEnum.getShipIcon(shipType));
    }
}
