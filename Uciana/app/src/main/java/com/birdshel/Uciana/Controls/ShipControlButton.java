package com.birdshel.Uciana.Controls;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Math.Point;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponent;
import com.birdshel.Uciana.Ships.ShipComponents.Weapon;
import java.nio.CharBuffer;
import org.andengine.entity.Entity;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
class ShipControlButton extends Entity {
    private final TiledSprite background;
    private final Text count;
    private final TiledSprite icon;
    private final Text name;
    private ShipComponent shipComponent;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ShipControlButton(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        TiledSprite tiledSprite = new TiledSprite(0.0f, 20.0f, game.graphics.empireColors, vertexBufferObjectManager);
        this.background = tiledSprite;
        tiledSprite.setSize(86.0f, 86.0f);
        tiledSprite.setAlpha(0.4f);
        attachChild(tiledSprite);
        TiledSprite tiledSprite2 = new TiledSprite(10.0f, 20.0f, game.graphics.shipComponentIconsTexture, vertexBufferObjectManager);
        this.icon = tiledSprite2;
        tiledSprite2.setSize(70.0f, 70.0f);
        attachChild(tiledSprite2);
        Font font = game.fonts.smallInfoFont;
        CharBuffer wrap = CharBuffer.wrap(new char[255]);
        HorizontalAlign horizontalAlign = HorizontalAlign.CENTER;
        Text text = new Text(0.0f, 0.0f, font, wrap, new TextOptions(horizontalAlign), vertexBufferObjectManager);
        this.name = text;
        attachChild(text);
        Text text2 = new Text(0.0f, 22.0f, game.fonts.smallInfoFont, CharBuffer.wrap(new char[255]), new TextOptions(horizontalAlign), vertexBufferObjectManager);
        this.count = text2;
        attachChild(text2);
    }

    public boolean isClicked(Point point) {
        return point.getX() > getX() + this.background.getX() && point.getX() < (getX() + this.background.getX()) + this.background.getWidthScaled() && point.getY() > getY() + this.background.getY() && point.getY() < (getY() + this.background.getY()) + this.background.getHeightScaled();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ShipComponent l() {
        return this.shipComponent;
    }

    public void set(ShipComponent shipComponent) {
        this.shipComponent = shipComponent;
        this.icon.setCurrentTileIndex(shipComponent.getIconIndex());
        this.count.setText("");
        this.name.setText(shipComponent.getShortName());
        Text text = this.name;
        text.setX(43.0f - (text.getWidthScaled() / 2.0f));
        Text text2 = this.name;
        text2.setY(106.0f - text2.getHeightScaled());
        if (shipComponent instanceof Weapon) {
            this.background.setCurrentTileIndex(0);
            this.count.setText(Integer.toString(((Weapon) shipComponent).getAvailableCount()));
            return;
        }
        this.background.setCurrentTileIndex(1);
    }
}
