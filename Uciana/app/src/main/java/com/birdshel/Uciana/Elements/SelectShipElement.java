package com.birdshel.Uciana.Elements;

import com.birdshel.Uciana.Game;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Resources.InfoIconEnum;
import com.birdshel.Uciana.Ships.Fleet;
import com.birdshel.Uciana.Ships.Ship;
import com.birdshel.Uciana.Ships.ShipSpriteBattle;
import com.birdshel.Uciana.Ships.ShipType;
import java.nio.CharBuffer;
import org.andengine.entity.Entity;
import org.andengine.entity.primitive.Line;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.color.Color;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class SelectShipElement extends Entity {
    private final Line armorHpBar;
    private final TiledSprite autoButton;
    private final Text autoText;
    private final TiledSprite detailIcon;
    private final Game game;
    private final Line shieldHpBar;
    private final Sprite shipHighlight;
    private final Text shipID;
    private ShipSpriteBattle shipImage;
    private final Text shipName;
    private final TiledSprite shipTypeIcon;
    private final TiledSprite shipUsedIcon;
    private final TiledSprite shipUsedIcon2;

    public SelectShipElement(Game game, VertexBufferObjectManager vertexBufferObjectManager) {
        this.game = game;
        CharBuffer wrap = CharBuffer.wrap(new char[255]);
        TextOptions textOptions = new TextOptions(HorizontalAlign.CENTER);
        Sprite sprite = new Sprite(0.0f, 0.0f, game.graphics.scienceBarTexture, vertexBufferObjectManager);
        this.shipHighlight = sprite;
        sprite.setAlpha(0.5f);
        sprite.setSize(199.0f, 199.0f);
        attachChild(sprite);
        Text text = new Text(0.0f, 7.0f, game.fonts.smallInfoFont, wrap, textOptions, vertexBufferObjectManager);
        this.shipID = text;
        text.setColor(new Color(0.6f, 0.6f, 0.6f));
        attachChild(text);
        Text text2 = new Text(0.0f, 180.0f, game.fonts.smallInfoFont, wrap, textOptions, vertexBufferObjectManager);
        this.shipName = text2;
        attachChild(text2);
        TiledSprite tiledSprite = new TiledSprite(0.0f, 0.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.shipTypeIcon = tiledSprite;
        attachChild(tiledSprite);
        TiledSprite tiledSprite2 = new TiledSprite(155.0f, 5.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.detailIcon = tiledSprite2;
        tiledSprite2.setSize(40.0f, 40.0f);
        tiledSprite2.setCurrentTileIndex(InfoIconEnum.INFO.ordinal());
        attachChild(tiledSprite2);
        TiledSprite tiledSprite3 = new TiledSprite(40.0f, 10.0f, game.graphics.empireColors, vertexBufferObjectManager);
        this.shipUsedIcon = tiledSprite3;
        tiledSprite3.setSize(10.0f, 10.0f);
        attachChild(tiledSprite3);
        TiledSprite tiledSprite4 = new TiledSprite(40.0f, 10.0f, game.graphics.empireColors, vertexBufferObjectManager);
        this.shipUsedIcon2 = tiledSprite4;
        tiledSprite4.setSize(10.0f, 10.0f);
        attachChild(tiledSprite4);
        TiledSprite tiledSprite5 = new TiledSprite(155.0f, 5.0f, game.graphics.infoIconsTexture, vertexBufferObjectManager);
        this.autoButton = tiledSprite5;
        tiledSprite5.setSize(40.0f, 40.0f);
        attachChild(tiledSprite5);
        Text text3 = new Text(150.0f, 45.0f, game.fonts.smallInfoFont, game.getActivity().getString(R.string.ship_select_auto), vertexBufferObjectManager);
        this.autoText = text3;
        text3.setColor(new Color(0.7f, 0.7f, 0.7f));
        attachChild(text3);
        Line line = new Line(0.0f, 0.0f, 0.0f, 0.0f, vertexBufferObjectManager);
        this.shieldHpBar = line;
        line.setColor(new Color(0.16f, 0.78f, 0.98f));
        line.setLineWidth(8.0f);
        line.setAlpha(0.4f);
        attachChild(line);
        Line line2 = new Line(0.0f, 0.0f, 0.0f, 0.0f, vertexBufferObjectManager);
        this.armorHpBar = line2;
        line2.setColor(Color.GREEN);
        line2.setLineWidth(8.0f);
        line2.setAlpha(0.4f);
        attachChild(line2);
    }

    public boolean getShipAlpha() {
        return this.shipImage.getAlpha() == 1.0f;
    }

    public void initialSet() {
        ShipSpriteBattle shipSpriteBattle = this.game.shipSpriteBattlePool.get();
        this.shipImage = shipSpriteBattle;
        shipSpriteBattle.setPosition(25.0f, 25.0f);
        attachChild(this.shipImage);
        sortChildren();
    }

    public void releasePoolElements() {
        detachChild(this.shipImage);
        this.game.shipSpriteBattlePool.push(this.shipImage);
    }

    /* JADX WARN: Code restructure failed: missing block: B:69:0x0130, code lost:
        if (r1.outposts.hasOutpostInSystem(r1.getCurrentPlayer(), r8.getSystemID()) != false) goto L28;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void set(Fleet fleet, Ship ship, boolean z, boolean z2) {
        this.detailIcon.setVisible(false);
        this.shipUsedIcon.setVisible(false);
        this.shipUsedIcon2.setVisible(false);
        this.autoButton.setVisible(false);
        this.autoText.setVisible(false);
        this.shieldHpBar.setVisible(false);
        this.armorHpBar.setVisible(false);
        setShipHighlight(true);
        this.shipImage.setShip(fleet, ship, 150.0f, ship.getShipType().getScale() * 150.0f);
        this.shipImage.hideHpBars();
        this.shipID.setText(ship.getID());
        Text text = this.shipID;
        text.setX(100.0f - (text.getWidthScaled() / 2.0f));
        this.shipName.setText(ship.getName());
        Text text2 = this.shipName;
        text2.setX(100.0f - (text2.getWidthScaled() / 2.0f));
        this.shipTypeIcon.setCurrentTileIndex(InfoIconEnum.getShipIcon(ship.getShipType()));
        if (ship.isCombatShip()) {
            this.shipImage.updateShipDamage(ship);
        }
        if (fleet.empireID == this.game.getCurrentPlayer()) {
            if (ship.getShipType().isCombatShip()) {
                this.detailIcon.setVisible(true);
            }
            if (ship.getShipType() == ShipType.SCOUT) {
                this.shipUsedIcon.setVisible(true);
                this.shipUsedIcon2.setVisible(true);
                if (ship.hasBeenUsed()) {
                    this.shipUsedIcon.setCurrentTileIndex(0);
                    this.shipUsedIcon2.setCurrentTileIndex(0);
                } else {
                    this.shipUsedIcon.setCurrentTileIndex(1);
                    this.shipUsedIcon2.setCurrentTileIndex(1);
                }
                this.autoButton.setVisible(true);
                this.autoText.setVisible(true);
                if (ship.isAutoOn()) {
                    this.autoButton.setCurrentTileIndex(InfoIconEnum.ON.ordinal());
                } else {
                    this.autoButton.setCurrentTileIndex(InfoIconEnum.OFF.ordinal());
                }
            }
        }
        if (!z && ship.getShipType().isCombatShip()) {
            Game game = this.game;
            if (!game.fleets.isFleetInSystem(game.getCurrentPlayer(), fleet.getSystemID())) {
                Game game2 = this.game;
                if (!game2.colonies.hasColonyInSystem(game2.getCurrentPlayer(), fleet.getSystemID())) {
                    Game game3 = this.game;
                }
            }
            this.shieldHpBar.setVisible(true);
            this.shieldHpBar.setPosition(25.0f, 168.0f, ((ship.getShieldHitPoints() / ship.getMaxShieldHitPoints()) * 150.0f) + 25.0f, 168.0f);
            this.armorHpBar.setVisible(true);
            this.armorHpBar.setPosition(25.0f, 175.0f, ((ship.getArmorHitPoints() / ship.getMaxArmorHitPoints()) * 150.0f) + 25.0f, 175.0f);
        }
        if (!z || z2) {
            return;
        }
        if (ship.getShipType() == ShipType.BATTLESHIP || ship.getShipType() == ShipType.DREADNOUGHT) {
            this.shipImage.setAlpha(0.4f);
            this.shipID.setAlpha(0.4f);
            this.shipName.setAlpha(0.4f);
            this.shipTypeIcon.setAlpha(0.4f);
        }
    }

    public void setShipHighlight(boolean z) {
        this.shipHighlight.setVisible(z);
    }

    public boolean toggleAutoButton() {
        int currentTileIndex = this.autoButton.getCurrentTileIndex();
        InfoIconEnum infoIconEnum = InfoIconEnum.OFF;
        if (currentTileIndex == infoIconEnum.ordinal()) {
            this.autoButton.setCurrentTileIndex(InfoIconEnum.ON.ordinal());
            return true;
        }
        this.autoButton.setCurrentTileIndex(infoIconEnum.ordinal());
        return false;
    }

    public boolean wasAutoPressed(float f2, float f3) {
        return this.autoButton.isVisible() && f2 > this.autoButton.getX() && f2 < this.autoButton.getX() + this.autoButton.getWidthScaled() && f3 > this.autoButton.getY() && f3 < this.autoButton.getY() + this.autoButton.getHeightScaled();
    }

    public boolean wasDetailPressed(float f2, float f3) {
        return this.detailIcon.isVisible() && f2 > this.detailIcon.getX() && f2 < this.detailIcon.getX() + this.detailIcon.getWidthScaled() && f3 > this.detailIcon.getY() - 10.0f && f3 < (this.detailIcon.getY() + this.detailIcon.getHeightScaled()) + 10.0f;
    }
}
