package com.birdshel.Uciana.Elements;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Ships.Ship;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponent;
import com.birdshel.Uciana.Ships.ShipComponents.ShipComponentID;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

import java.nio.CharBuffer;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class ShipDescription extends Entity {
    private final Text armorHPText;
    private final TiledSprite armorIcon;
    private final Text[] componentCountTexts = new Text[6];
    private final TiledSprite[] componentIcons = new TiledSprite[6];
    private final Text description;
    private final Text shieldHPText;
    private final TiledSprite shieldIcon;

    public ShipDescription(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        Font font = game.fonts.smallInfoFont;
        HorizontalAlign horizontalAlign = HorizontalAlign.CENTER;
        Text text = new Text(0.0f, 8.0f, font, "##########", new TextOptions(horizontalAlign), vertexBufferObjectManager);
        this.armorHPText = text;
        attachChild(text);
        TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.armorIcon = tiledSprite;
        tiledSprite.setCurrentTileIndex(InfoIconEnum.ARMOR.ordinal());
        attachChild(tiledSprite);
        Text text2 = new Text(0.0f, 8.0f, game.fonts.smallInfoFont, "##########", new TextOptions(horizontalAlign), vertexBufferObjectManager);
        this.shieldHPText = text2;
        attachChild(text2);
        TiledSprite tiledSprite2 = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.shieldIcon = tiledSprite2;
        tiledSprite2.setCurrentTileIndex(InfoIconEnum.SHIELD.ordinal());
        tiledSprite2.setScaleCenter(0.0f, 0.0f);
        tiledSprite2.setSize(30.0f, 30.0f);
        attachChild(tiledSprite2);
        for (int i = 0; i < 6; i++) {
            this.componentCountTexts[i] = new Text(0.0f, 8.0f, game.fonts.smallInfoFont, "##########", new TextOptions(HorizontalAlign.CENTER), vertexBufferObjectManager);
            attachChild(this.componentCountTexts[i]);
            this.componentIcons[i] = new TiledSprite(0.0f, 0.0f, game.graphics.shipComponentIconsTexture, vertexBufferObjectManager);
            this.componentIcons[i].setScaleCenter(0.0f, 0.0f);
            this.componentIcons[i].setSize(30.0f, 30.0f);
            attachChild(this.componentIcons[i]);
        }
        Text text3 = new Text(0.0f, 8.0f, game.fonts.smallInfoFont, CharBuffer.wrap(new char[255]), new TextOptions(HorizontalAlign.CENTER), vertexBufferObjectManager);
        this.description = text3;
        attachChild(text3);
    }

    private void setDescriptionForCombatShip(Ship ship) {
        int i = 0;
        this.description.setVisible(false);
        this.armorHPText.setVisible(true);
        this.armorHPText.setText(Integer.toString(ship.getMaxArmorHitPoints()));
        float widthScaled = this.armorHPText.getWidthScaled() + 1.0f + 0.0f;
        this.armorIcon.setVisible(true);
        this.armorIcon.setX(widthScaled);
        float widthScaled2 = widthScaled + this.armorIcon.getWidthScaled() + 5.0f;
        if (ship.getShield().getID() == ShipComponentID.NO_SHIELDS) {
            this.shieldHPText.setVisible(false);
            this.shieldIcon.setVisible(false);
        } else {
            this.shieldHPText.setVisible(true);
            this.shieldHPText.setX(widthScaled2);
            this.shieldHPText.setText(Integer.toString(ship.getMaxShieldHitPoints()));
            float widthScaled3 = widthScaled2 + this.shieldHPText.getWidthScaled() + 1.0f;
            this.shieldIcon.setVisible(true);
            this.shieldIcon.setX(widthScaled3);
            widthScaled2 = widthScaled3 + this.shieldIcon.getWidthScaled() + 5.0f;
        }
        for (Text text : this.componentCountTexts) {
            text.setVisible(false);
        }
        for (TiledSprite tiledSprite : this.componentIcons) {
            tiledSprite.setVisible(false);
        }
        for (ShipComponent shipComponent : ship.getShipComponents()) {
            if (shipComponent.getComponentCount() > 1) {
                this.componentCountTexts[i].setVisible(true);
                this.componentCountTexts[i].setX(widthScaled2);
                this.componentCountTexts[i].setText(Integer.toString(shipComponent.getComponentCount()));
                widthScaled2 += this.componentCountTexts[i].getWidthScaled() + 1.0f;
            }
            this.componentIcons[i].setVisible(true);
            this.componentIcons[i].setX(widthScaled2);
            this.componentIcons[i].setCurrentTileIndex(shipComponent.getIconIndex());
            widthScaled2 += this.componentIcons[i].getWidthScaled() + 5.0f;
            i++;
        }
    }

    private void setDescriptionForNonCombatShip(Ship ship) {
        this.armorHPText.setVisible(false);
        this.armorIcon.setVisible(false);
        this.shieldHPText.setVisible(false);
        this.shieldIcon.setVisible(false);
        for (Text text : this.componentCountTexts) {
            text.setVisible(false);
        }
        for (TiledSprite tiledSprite : this.componentIcons) {
            tiledSprite.setVisible(false);
        }
        this.description.setVisible(true);
        this.description.setText(ship.getShipType().getDescription());
    }

    public void set(Ship ship) {
        if (ship.isCombatShip()) {
            setDescriptionForCombatShip(ship);
        } else {
            setDescriptionForNonCombatShip(ship);
        }
    }

    @Override // org.andengine.entity.Entity, org.andengine.entity.IEntity
    public void setAlpha(float f2) {
        this.description.setAlpha(f2);
        this.armorHPText.setAlpha(f2);
        this.armorIcon.setAlpha(f2);
        this.shieldHPText.setAlpha(f2);
        this.shieldIcon.setAlpha(f2);
        for (int i = 0; i < 6; i++) {
            this.componentCountTexts[i].setAlpha(f2);
            this.componentIcons[i].setAlpha(f2);
        }
    }
}
